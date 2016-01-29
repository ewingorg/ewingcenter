package extend.voinfo;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;

import extend.voinfo.annotation.XmlField;
import extend.voinfo.entity.EntityInfo;
import extend.voinfo.entity.FieldInfo;
import external.wxpaysdk.api.paycallback.vo.PayCallBackReqParam;
import external.wxpaysdk.api.paycallback.vo.PayCallBackResDto;
import external.wxpaysdk.api.unifiedorders.vo.UnifiedOrdersReqParam;
import external.wxpaysdk.api.unifiedorders.vo.UnifiedOrdersResDto;
import external.wxpaysdk.vo.XmlFieldVo;

/**
 * 获取注解@XmlField的相关信息的类
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月26日
 *
 */
public class XmlFieldAnnotationManager extends FieldAnnotationManager {

    private static final Object lock = new Object();

    private static Map<String, EntityInfo> entityInfoMap = Maps.newHashMap();

    /**
     * class simple name 到Class对象的映射
     */
    private static Map<String, Class> classMap = Maps.newHashMap();

    private XmlFieldAnnotationManager() {
        Class[] clazzs = new Class[] { PayCallBackReqParam.class, PayCallBackResDto.class,
                UnifiedOrdersReqParam.class, UnifiedOrdersResDto.class };
        
        for(Class clazz : clazzs){ 
            EntityInfo entityInfo = load(clazz);
            entityInfoMap.put(clazz.getSimpleName(), entityInfo);
            classMap.put(clazz.getSimpleName(), clazz);
        }
    }

    /**
     * 获取Instance实例
     */
    public static XmlFieldAnnotationManager getInstace() {
        if (null == instance) {
            synchronized (lock) {
                if (null == instance) {
                    instance = new XmlFieldAnnotationManager();
                }
            }
        }

        return (XmlFieldAnnotationManager) instance;
    }

    private static EntityInfo load(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();

        EntityInfo entityInfo = new EntityInfo();
        for (Field field : fields) {
            XmlField xmlField = field.getAnnotation(XmlField.class);
            String xmlFieldStr = null != xmlField ? xmlField.value() : StringUtils.EMPTY;
            
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.name = xmlFieldStr;
            fieldInfo.field = field.getName();

            //<key,value> => <xmlValue: fieldInfo>
            entityInfo.addField(StringUtils.isNotEmpty(xmlFieldStr) ? xmlFieldStr : field.getName(), fieldInfo);
        }
        
        
        return entityInfo;
    }

    /**
     * 解析AuditEntity属性
     */
    private void parseAuditEntity(List<String> classNameList) {
        if(CollectionUtils.isEmpty(classNameList)){
            return;
        }

        for (String className : classNameList) {
            // 解析className
            EntityInfo info = reloadEntityInfo(className);
            entityInfoMap.put(className, info);
        }

    }

    @Override
    protected EntityInfo reloadEntityInfo(String className) {
        if(StringUtils.isEmpty(className)){
            return null;
        }

        return load(classMap.get(className));
    }
    
}
