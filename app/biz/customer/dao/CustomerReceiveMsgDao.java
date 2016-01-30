package biz.customer.dao;

import java.util.List;

import common.constants.IsEff;
import common.constants.IsHot;
import common.constants.IsOnline;
import jws.dal.sqlbuilder.Condition;
import jws.dal.sqlbuilder.Sort;
import biz.customer.ddl.CustomerReceiveMsg;
import biz.resource.ddl.WebResource;
import extend.jdbc.BaseDao;

/**
 * 用户地址相关的操作
 * 
 * @author chenxuegui.cxg@alibaba-inc.com
 * @createDate 2016年1月29日
 */
public class CustomerReceiveMsgDao extends BaseDao {

    public static List<CustomerReceiveMsg> findByCustomerId(Integer cusId) {
        Condition condition = new Condition("CustomerReceiveAddress.customerId", "=", cusId);
        return list(CustomerReceiveMsg.class, condition);
    }

}
