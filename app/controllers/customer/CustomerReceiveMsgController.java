package controllers.customer;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.google.common.collect.Lists;

import biz.customer.ddl.CustomerReceiveMsg;
import biz.customer.dto.CustomerReceiveMsgDto;
import biz.customer.service.CustomerReceiveMsgService;
import common.constants.IsEff;
import common.constants.ResponseCode;
import common.utils.BeanCopy;
import common.utils.LogUtil;
import apifw.exception.BusinessException;
import controllers.base.BaseController;

/**
 * 用户收获地址相关的action
 * 
 * @author chenxuegui.cxg@alibaba-inc.com
 * @createDate 2016年1月29日
 *
 */
public class CustomerReceiveMsgController extends BaseController {
    
    /**
     * 根据商户id获取商户收获地址列表
     * @param cusId 商户Id
     */
    public static void getByCusId(Integer cusId){
        checkRequired(cusId, "cusId");
        
        List<CustomerReceiveMsgDto> dtoList = Lists.newArrayList();
        try{
            List<CustomerReceiveMsg> list = CustomerReceiveMsgService.findByCustomerId(cusId);
            for(CustomerReceiveMsg address : list){
                CustomerReceiveMsgDto dto = new CustomerReceiveMsgDto();
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
     * @param msg
     */
    public static void saveMsg(CustomerReceiveMsg msg){
        checkRequired(msg, "msg");
        
        boolean result = false;
        try{
            msg.iseff = IsEff.EFFECTIVE.getMsg();
            result = CustomerReceiveMsgService.save(msg);
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
