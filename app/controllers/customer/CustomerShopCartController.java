package controllers.customer;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.google.common.collect.Lists;

import biz.customer.ddl.CustomerReceiveMsg;
import biz.customer.ddl.CustomerShopCart;
import biz.customer.dto.CustomerReceiveMsgDto;
import biz.customer.dto.CustomerShopCartDto;
import biz.customer.service.CustomerReceiveMsgService;
import biz.customer.service.CustomerShopCartService;
import common.constants.IsEff;
import common.constants.ResponseCode;
import common.utils.BeanCopy;
import common.utils.LogUtil;
import apifw.exception.BusinessException;
import controllers.base.BaseController;

/**
 * 购物车相关的action
 * 
 * @author chenxuegui.cxg@alibaba-inc.com
 * @createDate 2016年1月29日
 */
public class CustomerShopCartController extends BaseController {
    
    /**
     * 根据商户id获取商户收获地址列表
     * @param cusId 商户Id
     */
    public static void getByCusId(Integer cusId){
        checkRequired(cusId, "cusId");
        
        List<CustomerShopCartDto> dtoList = Lists.newArrayList();
        try{
            List<CustomerShopCart> list = CustomerShopCartService.findByCustomerId(cusId);
            for(CustomerShopCart cart : list){
                CustomerShopCartDto dto = new CustomerShopCartDto();
                BeanCopy.copy(dto, cart);
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
     * @param cart
     */
    public static void saveShopCart(CustomerShopCart cart){
        checkRequired(cart, "cart");
        
        boolean result = false;
        try{
            cart.iseff = IsEff.EFFECTIVE.getMsg();
            result = CustomerShopCartService.save(cart);
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
