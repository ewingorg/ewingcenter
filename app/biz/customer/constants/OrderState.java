package biz.customer.constants;

/**
 * 客户订单状态
 */
public enum OrderState {

    
    UN_COMMIT(0, "未提交"),

    UN_PAY(1, "未付款"),
    
    UN_SEND(2, "待发货"),
    
    WAIT_RECEIVE(3, "待收货");

    private int value;
    private String msg;

    OrderState(int value, String msg) {
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
