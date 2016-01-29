package extend.voinfo;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jws.Jws;
import jws.Logger;
import jws.dal.common.Entity;
import jws.dal.manager.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import extend.voinfo.annotation.Comment;
import extend.voinfo.entity.EntityInfo;
import extend.voinfo.entity.FieldInfo;
import extend.voinfo.exception.AuditParseEntityException;

/**
 * AuditEntity处理管理器
 * 
 * @author chenxg@ucweb.com
 * @createDate 2015年8月28日
 *
 */
public class CommentAnnotationManager extends FieldAnnotationManager{

    private static final Object lock = new Object();

    /**
     * class.xml文件的位置
     */
    private static final String classesXml = Jws.configuration.getProperty("dal.classesxml",
            "classes.xml");

    private static Map<String, EntityInfo> entityInfoMap = Maps.newHashMap();

    private CommentAnnotationManager() {
        // 1、获取所有ddl的className
        List<String> classNameList = loadClassName();

        // 2、解析AuditField注解
        parseAuditEntity(classNameList);
    }

    /**
     * 获取Instance实例
     */
    public static CommentAnnotationManager getInstace() {
        if (null == instance) {
            synchronized (lock) {
                if (null == instance) {
                    instance = new CommentAnnotationManager();
                }
            }
        }

        return (CommentAnnotationManager) instance;
    }

    /**
     * 获取所有ddl的className
     */
    private List<String> loadClassName() {

        File file = new File(classesXml);
        List<String> classNameList = Lists.newArrayList();

        try {
            // 加载xml
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(file);
            doc.normalize();

            // 解析xml
            NodeList li = doc.getElementsByTagName("package");
            for (int n = 0; n < li.getLength(); n++) {
                Node packageNode = li.item(n);
                NamedNodeMap packageAtt = packageNode.getAttributes();
                String packageString = packageAtt.getNamedItem("name").getNodeValue();
                if (packageString == null) {
                    String message = "parent <package/> must contain \"name\" attribute \ncheck file: "
                            + classesXml;
                    throw new AuditParseEntityException(message);
                }

                Node cacheEntityNode = packageAtt.getNamedItem("cache-entity");
                if (cacheEntityNode != null && cacheEntityNode.getNodeValue().equals("true")) {
                    // 缓存客户端实体类
                    continue;
                }

                // 遍历所有的子节点
                NodeList nodes = packageNode.getChildNodes();
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node classNode = nodes.item(i);
                    if (classNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (!classNode.getNodeName().equals("class")) {
                            // XML异常，<package />只支持<class />节点
                            String message = "parent <package/> only support child node <class/> \ncheck file: "
                                    + classesXml;
                            throw new AuditParseEntityException(message);
                        }

                        NamedNodeMap attributes = classNode.getAttributes();

                        Node nameAttribute = attributes.getNamedItem("name");
                        if (nameAttribute == null) {
                            // XML异常，<class />必须有name属性
                            String message = "<class/> must contain \"name\" attribute \ncheck file: "
                                    + classesXml;
                            throw new AuditParseEntityException(message);
                        }

                        classNameList.add(attributes.getNamedItem("name").getNodeValue());
                    }
                }
            }
        } catch (Exception e) {
            Logger.error(e, e.getMessage());
        }

        return classNameList;
    }

    /**
     * 解析AuditEntity属性
     */
    private void parseAuditEntity(List<String> classNameList) {

        for (String className : classNameList) {
            // 解析className
            EntityInfo info = reloadEntityInfo(className);
            entityInfoMap.put(className, info);
        }

    }

    @Override
    protected EntityInfo reloadEntityInfo(String className) {
        if (StringUtils.isEmpty(className)) {
            return null;
        }

        Entity entity = EntityManager.getInstance().getEntity(className);
        if (null == entity) {
            // XML异常，<class />必须有name属性
            String message = String.format("no such DDL : %s \ncheck file: " + classesXml,
                    className);
            throw new AuditParseEntityException(message);
        }

        EntityInfo info = new EntityInfo();
        List<Field> fields = entity.getFields();
        for (Field field : fields) {
            Comment annotation = field.getAnnotation(Comment.class);

            String fieldName = null != annotation && StringUtils.isNotEmpty(annotation.name()) ? annotation
                    .name() : field.getName();
            String fieldDes = null != annotation ? annotation.description() : StringUtils.EMPTY;
            FieldInfo fieldInfo = new FieldInfo(field.getName(), fieldName, fieldDes);
            info.addField(field.getName(), fieldInfo);
        }

        return info;
    }


}
