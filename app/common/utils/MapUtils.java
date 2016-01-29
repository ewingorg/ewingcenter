package common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import jws.Logger;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

public class MapUtils {
    
    public static final Map EMPTY_MAP = org.apache.commons.collections.MapUtils.EMPTY_MAP;
    
    
    public  static <T> T get(Map<String,T> map, String key){
        if(isEmpty(map) || StringUtils.isEmpty(key)){
            return null;
        }
        
        return map.get(key);
    }
    
    
    public static Integer getInt(Map map, String key){
        if(isEmpty(map) || StringUtils.isEmpty(key)){
            return null;
        }
        
        return map.containsKey(key) ? IntegerUtils.parseInt(map.get(key).toString()) : null;
    }
    
    public static boolean getBoolean(Map map, String key){
        if(isEmpty(map) || StringUtils.isEmpty(key)){
            return false;
        }
        
        return map.containsKey(key) ? Boolean.parseBoolean(map.get(key).toString()) : false;
    }
    
    
    public static boolean isEmpty(Map map){
        return org.apache.commons.collections.MapUtils.isEmpty(map);
    }


    public static <T> Map<String, Object> toMap(Object t, Class<T> clazz) {
        if(null == t || null == clazz){
            return EMPTY_MAP;
        }
        
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            try {
                map.put(field.getName(), field.get(t));
            } catch (Exception e) {
                Logger.error(e, e.getMessage());
            }
        }
        
        return map;
    }


    /**
     * @TESTME
     * @param map
     * @param clazz
     * @author Joeson
     */
    public static <T> T toObject(Map<String, String> map, Class<T> clazz) {
        if(isEmpty(map) || null == clazz){
            return null;
        }

        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.populate(t, map);
        } catch (Exception e) {
            Logger.error(e, e.getMessage());
        }
        
        return t;
    }

}
