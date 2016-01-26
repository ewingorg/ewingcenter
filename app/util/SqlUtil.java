/**
 * 
 */
package util;

/**
 * Sql工具类
 * 
 * @author tanson lam
 * @createDate 2014年9月17日
 * 
 */
public class SqlUtil {
    /**
     * 将数组转换成传入sql in条件使用的字符串。
     * @param objs
     * @return
     */
    public static String array2InString(Object[] objs) {
        if (objs == null || objs.length == 0)
            return "";
        StringBuffer str = new StringBuffer();
        if (objs[0] instanceof java.lang.String) {
            for (Object obj : objs) {
                str.append("'").append(obj).append("'").append(",");
            }
        } else {
            for (Object obj : objs) {
                str.append(obj).append(",");
            }
        }
        return str.substring(0, str.length()-1);

    }
}
