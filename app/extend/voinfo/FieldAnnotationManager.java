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
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月26日
 *
 */
public abstract class FieldAnnotationManager {
    
    protected static volatile FieldAnnotationManager instance = null;

    private static Map<String, EntityInfo> entityInfoMap = Maps.newHashMap();

    /**
     * 获取属性FieldInfo
     * 
     * @param className
     * @param fieldName
     * @author Joeson
     */
    public FieldInfo getFieldInfo(String className, String field) {
        if (StringUtils.isEmpty(className) || StringUtils.isEmpty(field)) {
            return new FieldInfo();
        }

        EntityInfo entityInfo = this.getEntityInfo(className);
        return null == entityInfo ? null : entityInfo.getFieldName(field);
    }

    /**
     * 获取对象EntityInfo
     * 
     * @author Joeson
     */
    public EntityInfo getEntityInfo(String className) {
        if (StringUtils.isEmpty(className)) {
            return new EntityInfo();
        }

        EntityInfo entityInfo = entityInfoMap.get(className);
        if (null == entityInfo) {
            // 解析className
            entityInfo = reloadEntityInfo(className);
            entityInfoMap.put(className, entityInfo);
        }
        return null == entityInfo ? new EntityInfo() : entityInfo;
    }
    
    protected abstract EntityInfo reloadEntityInfo(String className);

}
