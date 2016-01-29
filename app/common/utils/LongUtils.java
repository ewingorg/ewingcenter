package common.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2015年10月10日
 *
 */
public class LongUtils {

    /**
     * 转化long
     */
    public static long parseLong(String value) {
        if (StringUtils.isEmpty(value)) {
            return 0;
        }
        
        Long result = null;
        try {
            result = Long.parseLong(value);
        } catch (Exception e) {
            return 0;
        }

        return result;
    }
    
    
    /**
     * nullOrZero
     *
     * @param i
     * @return boolean
     */
    public static boolean nullOrZero(Long i) {
        if (null == i || i <= 0)
            return true;
        return false;
    }
    
    
    /**
     * nullOrZero
     *
     * @param i
     * @return boolean
     */
    public static Long toLong(Integer i) {
        if (null == i || i <= 0)
            return 0l;
        return i.longValue();
    }
}
