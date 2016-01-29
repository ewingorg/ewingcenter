package external.wxpaysdk.api.paycallback.vo;

import extend.voinfo.annotation.XmlField;
import external.wxpaysdk.vo.XmlFieldVo;

/**
 * 支付结果通用通知 - 回调返回数据
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月24日
 *
 */
public class PayCallBackResDto implements XmlFieldVo{
    
    /**
     * 返回状态码 - SUCCESS/FAIL SUCCESS表示商户接收通知成功并校验成功
     */
    @XmlField(value="return_code")
    private String returnCode;

    /**
     * 返回信息 - 返回信息，如非空，为错误原因 
     */
    @XmlField(value="return_msg")
    private String returnMsg;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
    
    
}
