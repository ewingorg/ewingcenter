package controllers.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import biz.customer.constants.ReceiveAddressDefault;
import biz.customer.ddl.CustomerReceiveMsg;
import biz.customer.dto.CustomerReceiveMsgDto;
import biz.customer.service.CustomerReceiveMsgService;
import common.constants.IsEff;
import common.constants.ResponseCode;
import common.utils.BeanCopy;
import common.utils.LogUtil;
import apifw.common.RequestJson;
import apifw.exception.BusinessException;
import controllers.base.BaseController;

/**
 * 用户收获地址相关的action
 * 
 * @author chenxuegui.cxg@alibaba-inc.com
 * @createDate 2016年1月29日
 */
public class CustomerReceiveMsgController extends BaseController {

    /**
     * 根据商户id获取商户收获地址列表
     * 
     * @param cusId 商户Id
     */
    public static void getByCusId() {
        //@FIXME
//        Integer cusId = getParamJson(Integer.class);
//        checkRequired(cusId, "cusId");
        
        Integer cusId = 1;
        
        List<CustomerReceiveMsgDto> dtoList = Lists.newArrayList();
        CustomerReceiveMsgDto def = new CustomerReceiveMsgDto();
        try {
            //获取默认的地址，一个
            List<CustomerReceiveMsg> defaultList = CustomerReceiveMsgService.find(cusId, ReceiveAddressDefault.DEFAULT.getValue());
            if(CollectionUtils.isNotEmpty(defaultList)){
                BeanCopy.copy(def, defaultList.get(0));
            }
            
            //列表
            List<CustomerReceiveMsg> list = CustomerReceiveMsgService.find(cusId, ReceiveAddressDefault.UN_DEFAULT.getValue());
            for (CustomerReceiveMsg address : list) {
                CustomerReceiveMsgDto dto = new CustomerReceiveMsgDto();
                BeanCopy.copy(dto, address);
                dtoList.add(dto);
            }
        } catch (BusinessException e) {
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        } catch (Exception e) {
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        }

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("dtoList", dtoList);
        map.put("default", def);
        outJsonSuccess(map);
    }

    /**
     * 保存操作
     * 
     * @param msg
     */
    public static void saveMsg() {
        CustomerReceiveMsg msg = getParamJson(CustomerReceiveMsg.class);
        checkRequired(msg, "msg");

        boolean result = false;
        try {
            msg.iseff = IsEff.EFFECTIVE.getValue();
            result = CustomerReceiveMsgService.save(msg);
        } catch (BusinessException e) {
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        } catch (Exception e) {
            LogUtil.error(e, e.getMessage());
            jsonFailed(ResponseCode.INTERNAL_ERROR, e.getMessage());
        }

        json(result, "保存成功", "保存失败");
    }

    /**
     * 修改操作
     * 
     * @param msg
     */
    public static void updateMsg(CustomerReceiveMsg msg) {
        checkRequired(msg, "msg");

        boolean result = false;
        try {
            msg.iseff = IsEff.EFFECTIVE.getMsg();
            result = CustomerReceiveMsgService.update(msg);
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
