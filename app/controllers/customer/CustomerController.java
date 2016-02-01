package controllers.customer;

import apifw.exception.BusinessException;
import biz.customer.ddl.Customer;
import biz.customer.dto.CustomerDto;
import biz.customer.service.CustomerService;

import common.constants.IsEff;
import common.constants.ResponseCode;
import common.utils.BeanCopy;
import common.utils.LogUtil;

import controllers.base.BaseController;

/**
 * 客户信息的相关操作
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月31日
 */
public class CustomerController extends BaseController {

    /**
     * 根据客户id获取客户
     * 
     * @param cusId 商户Id
     */
    public static void getByCusId(Integer cusId) {
        checkRequired(cusId, "cusId");

        CustomerDto dto = new CustomerDto();
        try {
            Customer customer = CustomerService.findById(cusId, Customer.class);
            BeanCopy.copy(dto, customer);
        } catch (BusinessException e) {
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        } catch (Exception e) {
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        }

        outJsonSuccess(dto);
    }

    /**
     * 保存操作
     * 
     * @param order
     */
    public static void saveOrder(Customer customer) {
        checkRequired(customer, "customer");

        boolean result = false;
        try {
            customer.iseff = IsEff.EFFECTIVE.getMsg();
            result = CustomerService.save(customer);
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
