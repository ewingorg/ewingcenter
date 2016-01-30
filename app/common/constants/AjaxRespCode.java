package common.constants;

import org.apache.commons.lang.StringUtils;

public enum AjaxRespCode {
    /**
     * 成功
     */
    CODE_SUC(2000000, "成功"), 
    /**
     * 错误
     */
    CODE_ERROR(5000000, "出错"),
    
    /**
     * 未知错误
     */
    CODE_UNKNOW(5000001, "未知");

    public int value;
    public String name;

    AjaxRespCode(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String getCn(Integer value) {
        if (null == value) {
            return StringUtils.EMPTY;
        }
        for (AjaxRespCode type : AjaxRespCode.values()) {
            if (type.value == value.intValue()) {
                return type.name;
            }
        }
        return StringUtils.EMPTY;
    }

}
