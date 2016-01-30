package controllers.customer;

import java.util.List;

import com.google.common.collect.Lists;

import apifw.exception.BusinessException;
import biz.customer.constants.OrderState;
import biz.customer.ddl.CustomerOrder;
import biz.customer.ddl.CustomerReceiveMsg;
import biz.customer.dto.CustomerOrderDto;
import biz.customer.dto.CustomerReceiveMsgDto;
import biz.customer.service.CustomerOrderService;
import biz.customer.service.CustomerReceiveMsgService;
import common.constants.IsEff;
import common.constants.ResponseCode;
import common.utils.BeanCopy;
import common.utils.LogUtil;
import controllers.base.BaseController;

/**
 * 
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月31日
 */
public class CustomerOrderController extends BaseController{
    
    /**
     * 根据商户id获取订单列表
     * @param cusId 商户Id
     * @param state 订单状态{@link OrderState}
     */
    public static void getByCusId(Integer cusId, Integer state){
        checkRequired(cusId, "cusId");
        
        List<CustomerOrderDto> dtoList = Lists.newArrayList();
        try{
            List<CustomerOrder> list = CustomerOrderService.findByCustomerId(cusId, state);
            for(CustomerOrder address : list){
                CustomerOrderDto dto = new CustomerOrderDto();
                BeanCopy.copy(dto, address);
                dtoList.add(dto);
            }
        }catch(BusinessException e){
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        }catch(Exception e){
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        }
        
        outJsonSuccess(dtoList);
    }
    
    /**
     * 保存操作
     * @param order
     */
    public static void saveOrder(CustomerOrder order){
        checkRequired(order, "order");
        
        boolean result = false;
        try{
            order.iseff = IsEff.EFFECTIVE.getMsg();
            result = CustomerOrderService.save(order);
        }catch(BusinessException e){
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        }catch(Exception e){
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        }
        
        json(result, "保存成功", "保存失败");
    }
}
