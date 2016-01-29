package extend.voinfo.entity;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author chenxg@ucweb.com
 * @createDate 2015年8月28日
 *
 */
public class FieldInfo {

    public String field;

    public String name;

    public String description;

    public FieldInfo() {

    }

    public FieldInfo(String field, String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static boolean isEmpty(FieldInfo fieldInfo) {
        if (null == fieldInfo || StringUtils.isEmpty(fieldInfo.name)) {
            return true;
        }

        return false;
    }

}
