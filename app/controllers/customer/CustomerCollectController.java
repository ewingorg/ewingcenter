package controllers.customer;

import java.util.List;

import apifw.exception.BusinessException;
import biz.customer.ddl.CustomerCollect;
import biz.customer.ddl.CustomerOrder;
import biz.customer.dto.CustomerCollectDto;
import biz.customer.service.CustomerCollectService;
import biz.customer.service.CustomerOrderService;

import com.google.common.collect.Lists;
import common.constants.IsEff;
import common.constants.ResponseCode;
import common.utils.BeanCopy;
import common.utils.LogUtil;

import controllers.base.BaseController;

public class CustomerCollectController extends BaseController {

    /**
     * 根据商户id获取订单列表
     * 
     * @param cusId 商户Id
     */
    public static void getByCusId(Integer cusId) {
        checkRequired(cusId, "cusId");

        List<CustomerCollectDto> dtoList = Lists.newArrayList();
        try {
            List<CustomerCollect> list = CustomerCollectService.findByCustomerId(cusId);
            for (CustomerCollect collect : list) {
                CustomerCollectDto dto = new CustomerCollectDto();
                BeanCopy.copy(dto, collect);
                dtoList.add(dto);
            }
        } catch (BusinessException e) {
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        } catch (Exception e) {
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        }

        outJsonSuccess(dtoList);
    }

    /**
     * 保存操作
     * 
     * @param order
     */
    public static void saveOrder(CustomerOrder order) {
        checkRequired(order, "order");

        boolean result = false;
        try {
            order.iseff = IsEff.EFFECTIVE.getMsg();
            result = CustomerOrderService.save(order);
        } catch (BusinessException e) {
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        } catch (Exception e) {
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        }

        json(result, "保存成功", "保存失败");
    }
}
