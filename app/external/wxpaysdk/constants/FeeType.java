package external.wxpaysdk.constants;

/**
 * 货币类型
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月24日
 */
public enum FeeType {

    CNY("人民币");
    
    private String msg;
    
    FeeType(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
}
