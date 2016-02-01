package biz.customer.constants;

/**
 * 客户订单状态
 */
public enum ReceiveAddressDefault {

    UN_DEFAULT(0, "不是默认"),

    DEFAULT(1, "默认");

    private int value;
    private String msg;

    ReceiveAddressDefault(int value, String msg) {
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
