package external.wxpaysdk.api;

import java.util.Map;

import jws.Logger;
import common.utils.JsonUtils;
import common.utils.MapUtils;
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
import external.wxpaysdk.protocol.ApiClient;

/**
 * 支付接口的实现
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月24日
 *
 */
public class WxpayApiImpl implements WxpayApi {

    @Override
    public UnifiedOrdersResDto unifiedOrders(UnifiedOrdersReqParam param) {
        Map<String, Object> paramsMap = MapUtils.toMap(param, UnifiedOrdersReqParam.class);
        UnifiedOrdersResDto result = ApiClient.post(unified_order_url, paramsMap,
                UnifiedOrdersResDto.class);

        Logger.info(JsonUtils.toJson(result));
        return result;
    }

    @Override
    public OrderQueryResDto orderQuery(OrderQueryReqParam param) {

        Map<String, Object> paramsMap = MapUtils.toMap(param, OrderQueryReqParam.class);

        OrderQueryResDto result = ApiClient
                .post(order_query_url, paramsMap, OrderQueryResDto.class);

        Logger.info(JsonUtils.toJson(result));
        return result;
    }

    @Override
    public CloseOrderResDto orderQuery(CloseOrderReqParam param) {
        Map<String, Object> paramsMap = MapUtils.toMap(param, CloseOrderReqParam.class);

        CloseOrderResDto result = ApiClient
                .post(order_query_url, paramsMap, CloseOrderResDto.class);

        Logger.info(JsonUtils.toJson(result));
        return result;
    }
    
    @Override
    public ApplyRefundResDto applyRefund(ApplyRefundReqParam param) {
        Map<String, Object> paramsMap = MapUtils.toMap(param, ApplyRefundReqParam.class);

        ApplyRefundResDto result = ApiClient
                .post(order_query_url, paramsMap, ApplyRefundResDto.class);

        Logger.info(JsonUtils.toJson(result));
        return result;
    }

    @Override
    public RefundQueryResDto refundQuery(RefundQueryReqParam param) {
        Map<String, Object> paramsMap = MapUtils.toMap(param, RefundQueryReqParam.class);

        RefundQueryResDto result = ApiClient
                .post(order_query_url, paramsMap, RefundQueryResDto.class);

        Logger.info(JsonUtils.toJson(result));
        return result;
    }

    @Override
    public DownloadBillResDto downBill(DownloadBillReqParam param) {
        Map<String, Object> paramsMap = MapUtils.toMap(param, DownloadBillReqParam.class);

        DownloadBillResDto result = ApiClient
                .post(order_query_url, paramsMap, DownloadBillResDto.class);
        Logger.info(JsonUtils.toJson(result));
        return result;
    }

    @Override
    public PayitilReportResDto payitilReport(PayitilReportReqParam param) {
        Map<String, Object> paramsMap = MapUtils.toMap(param, PayitilReportReqParam.class);

        PayitilReportResDto result = ApiClient
                .post(order_query_url, paramsMap, PayitilReportResDto.class);
        Logger.info(JsonUtils.toJson(result));
        return result;
    }

}
