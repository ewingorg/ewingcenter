package common.constants;

/**
 * 数据是否生效标识
 */
public enum IsEff {

    
    INEFFECTIVE(0, "失效"),

 
    EFFECTIVE(1, "生效");

    private int value;
    private String msg;

    IsEff(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}
