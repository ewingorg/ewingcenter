package external.wxpaysdk.api.paycallback.vo;

import extend.voinfo.annotation.XmlField;
import external.wxpaysdk.vo.XmlFieldVo;

/**
 * 支付结果通用通知 - 回调参数
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月24日
 *
 */
public class PayCallBackReqParam implements XmlFieldVo{
    
    /**
     * SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    @XmlField(value="return_code")
    private String returnCode;

    /**
     * 返回信息 - 返回信息，如非空，为错误原因 
     */
    @XmlField(value="return_msg")
    private String returnMsg;

    /*************************以下字段在return_code为SUCCESS的时候有返回 ********************/
    /**
     * 公众账号ID - 微信分配的公众账号ID（企业号corpid即为此appId）
     */
    @XmlField(value="appid")
    private String appId;
    
    /**
     * 商户号 - 微信支付分配的商户号
     */
    @XmlField(value="mch_id")
    private String mchId;
    
    /**
     * 设备号 - 微信支付分配的终端设备号
     */
    @XmlField(value="device_info")
    private String deviceInfo;
    
    /**
     * 随机字符串 - 随机字符串，不长于32位
     */
    @XmlField(value="nonce_str")
    private String nonceStr;
    
    /**
     * 签名
     */
    @XmlField(value="sign")
    private String sign;
    
    /**
     * 业务结果 - SUCCESS/FAIL
     */
    @XmlField(value="result_code")
    private String resultCode;
    
    /**
     * 错误代码 - 错误返回的信息描述
     */
    @XmlField(value="err_code")
    private String errCode;
    
    /**
     * 错误代码描述 - 错误返回的信息描述
     */
    @XmlField(value="err_code_des")
    private String errCodeDes;
    
    /**
     * 用户标识 - 用户在商户appid下的唯一标识
     */
    @XmlField(value="openid")
    private String openId;
    
    /**
     * 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     */
    @XmlField(value="is_subscribe")
    private String isSubscribe;
    
    /**
     *  交易类型 - JSAPI、NATIVE、APP 
     */
    @XmlField(value="trade_type")
    private String tradeType;
    
    /**
     * 付款银行 - 银行类型，采用字符串类型的银行标识，银行类型见银行列表
     */
    @XmlField(value="bank_type")
    private String bankType;
    
    /**
     * 货币类型 - 符合ISO4217标准的三位字母代码，默认人民币：CNY
     */
    @XmlField(value="total_fee")
    private String totalFee;
    
    /**
     * 现金支付金额 - 现金支付金额订单现金支付金额
     */
    @XmlField(value="cash_fee")
    private String cashFee;
    
    /**
     * 现金支付货币类型 - 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
     */
    @XmlField(value="cash_fee_type")
    private String cashFeeType;
    
    /**
     * 代金券或立减优惠金额 - 代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额
     */
    @XmlField(value="coupon_fee")
    private String couponFee;
    
    /**
     * 代金券或立减优惠使用数量 
     */
    @XmlField(value="coupon_count")
    private String couponCount;
    
    /**
     * @FIXME
     * 代金券或立减优惠ID - 代金券或立减优惠ID,$n为下标，从0开始编号
     */
    @XmlField(value="coupon_id_$n")
    private String couponId$n;
    
    /**
     * @FIXME
     * 单个代金券或立减优惠支付金额,$n为下标，从0开始编号
     */
    @XmlField(value="coupon_fee_$n")
    private String couponFee$n;
    
    /**
     *  微信支付订单号 
     */
    @XmlField(value="transaction_id")
    private String transactionId;
    
    /**
     * 商户系统的订单号，与请求一致
     */
    @XmlField(value="out_trade_no")
    private String outTradeNo;
    
    /**
     * 商家数据包 - 商户系统的订单号，与请求一致
     */
    @XmlField(value="attach")
    private String attach;
    
    /**
     * 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    @XmlField(value="time_end")
    private String timeEnd;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getCashFee() {
        return cashFee;
    }

    public void setCashFee(String cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public String getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(String couponFee) {
        this.couponFee = couponFee;
    }

    public String getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(String couponCount) {
        this.couponCount = couponCount;
    }

    public String getCouponId$n() {
        return couponId$n;
    }

    public void setCouponId$n(String couponId$n) {
        this.couponId$n = couponId$n;
    }

    public String getCouponFee$n() {
        return couponFee$n;
    }

    public void setCouponFee$n(String couponFee$n) {
        this.couponFee$n = couponFee$n;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
    
    
    
}
