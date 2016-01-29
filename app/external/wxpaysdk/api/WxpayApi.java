package external.wxpaysdk.api;

import external.wxpaysdk.api.applyrefund.vo.ApplyRefundReqParam;
import external.wxpaysdk.api.applyrefund.vo.ApplyRefundResDto;
import external.wxpaysdk.api.closeorder.vo.CloseOrderReqParam;
import external.wxpaysdk.api.closeorder.vo.CloseOrderResDto;
import external.wxpaysdk.api.downloadbill.vo.DownloadBillReqParam;
import external.wxpaysdk.api.downloadbill.vo.DownloadBillResDto;
import external.wxpaysdk.api.orderquery.vo.OrderQueryReqParam;
import external.wxpaysdk.api.orderquery.vo.OrderQueryResDto;
import external.wxpaysdk.api.payitilreport.vo.PayitilReportReqParam;
import external.wxpaysdk.api.payitilreport.vo.PayitilReportResDto;
import external.wxpaysdk.api.refundquery.vo.RefundQueryReqParam;
import external.wxpaysdk.api.refundquery.vo.RefundQueryResDto;
import external.wxpaysdk.api.unifiedorders.vo.UnifiedOrdersReqParam;
import external.wxpaysdk.api.unifiedorders.vo.UnifiedOrdersResDto;

/**
 * 微信支付接口
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月27日
 *
 */
public interface WxpayApi {
    
    static final String unified_order_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    static final String order_query_url = "https://api.mch.weixin.qq.com/pay/orderquery";
    
    static final String clode_order_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    static final String refund_query_url = "https://api.mch.weixin.qq.com/pay/refundquery";
    
    static final String download_bill_url = "https://api.mch.weixin.qq.com/pay/downloadbill";  
    
    static final String payitil_report_url = "https://api.mch.weixin.qq.com/payitil/report";

    /**
     * 统一下单API<br/>
     * 应用场景：<br/>
     * 除被扫支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易回话标识后再按扫码、JSAPI、APP等不同场景生成交易串调起支付。 
     * 
     * @author Joeson
     */
    UnifiedOrdersResDto unifiedOrders(UnifiedOrdersReqParam param);
    
    /**
     * 订单查询API《br/>
     * 应用场景：<br/>
     * 1、当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知； <br/> 
     * 2、调用支付接口后，返回系统错误或未知交易状态情况； <br/>
     * 3、调用被扫支付API，返回USERPAYING的状态； <br/>
     * 4、调用关单或撤销接口API之前，需确认支付状态； 
     * 
     * @author Joeson
     */
    OrderQueryResDto orderQuery(OrderQueryReqParam param);
    
    
    /**
     * 关闭订单API<br/>
     * 应用场景：<br/>
     * 1、商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；<br/>
     * 2、系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。 
     * 
     * @author Joeson
     */
    CloseOrderResDto orderQuery(CloseOrderReqParam param);
    
    
    /**
     * <b>请求需要双向证书</b>
     * 应用场景：<br/>
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。 <br/>
     * 注意： <br/>
     * 1、交易时间超过一年的订单无法提交退款； <br/>
     * 2、微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。一笔退款失败后重新提交，要采用原来的退款单号。总退款金额不能超过用户实际支付金额。 <br/>
     * @author Joeson
     */
    ApplyRefundResDto applyRefund(ApplyRefundReqParam param);
    
    
    /**
     * <b>应用场景</b><br/>
     * 提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。
     * @param param
     * @return
     * @author Joeson
     */
    RefundQueryResDto refundQuery(RefundQueryReqParam param);
    
    /**
     * <b>应用场景</b><br/>
     * 商户可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和微信侧数据不一致，通过对账单核对后可校正支付状态。<br/> 
     * <b>注意：</b><br/>
     * 1、微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致，bill_type为REVOKED； <br/>
     * 2、微信在次日9点启动生成前一天的对账单，建议商户10点后再获取； <br/>
     * 3、对账单中涉及金额的字段单位为“元”。 
     * @author Joeson
     */
    DownloadBillResDto downBill(DownloadBillReqParam param);
    
    /**
     * <b>应用场景</b><br/>
     * 商户在调用微信支付提供的相关接口时，会得到微信支付返回的相关信息以及获得整个接口的响应时间。为提高整体的服务水平，协助商户一起提高服务质量，微信支付提供了相关接口调用耗时和返回信息的主动上报接口，微信支付可以根据商户侧上报的数据进一步优化网络部署，完善服务监控，和商户更好的协作为用户提供更好的业务体验。 
     * @author Joeson
     */
    PayitilReportResDto payitilReport(PayitilReportReqParam param);
}
