package common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import jws.Logger;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * bean copy工具
 * 
 * @author wenxy@ucweb.com
 * @createDate 2014年9月4日
 *
 */
public class BeanCopy {

    public static <T> void copy(T dest, T orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException e) {
            Logger.error("IllegalAccessException", e.getMessage());
        } catch (InvocationTargetException e) {
            Logger.error("InvocationTargetException", e.getMessage());
        }
    }
    
    /**
     * copy
     * @param dest
     * @param orig
     * @param ignoreNull 忽略null,即orig为空的字段不做copy
     * @author Joeson
     */
    public static void copy(Object dest, Object orig, boolean ignoreNull) {
        Class destClz = dest.getClass();
        Class origClz = orig.getClass();

        Field[] destFields = destClz.getDeclaredFields();
        Field[] origFields = origClz.getDeclaredFields();

        for (Field origField : origFields) {
            try {
                String fieldName = origField.getName();
                origField.setAccessible(true);
                if (!ignoreNull || null != origField.get(orig)) {
                    for (Field destField : destFields) {
                        if (destField.getName().equals(origField.getName())
                                && destField.getType().equals(origField.getType())) {
                            destField.setAccessible(true);
                            Object o = origField.get(orig);
                            BeanUtils.copyProperty(dest, fieldName, o);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                Logger.error(e, "copyNonNull exception.");
                throw new RuntimeException("copyNonNull exception.." + e.getMessage());
            }
        }
    }
    
    

    /**
     * 将源对象的值复制到目标对象的字段,排除properties指定的字段
     */
    public static <T> void copyOtherProperties(T dest, T orig, Map<String, String> properties) {

        Class<T> destClz = (Class<T>) dest.getClass();
        Class<T> origClz = (Class<T>) orig.getClass();

        Field[] destFields = destClz.getDeclaredFields();
        Field[] origFields = origClz.getDeclaredFields();

        for (Field aField : destFields) {
            try {
                String fieldName = aField.getName();
                aField.setAccessible(true);
                if (!properties.containsKey(fieldName)) {
                    for (Field origField : origFields) {

                        if (origField.getName().equals(aField.getName())
                                && origField.getType().equals(aField.getType())) {
                            origField.setAccessible(true);
                            Object o = origField.get(orig);
                            BeanUtils.copyProperty(dest, fieldName, o);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                Logger.error(e, "copyNonNull exception.");
                throw new RuntimeException("copyNonNull exception.." + e.getMessage());
            }
        }
    }
}
