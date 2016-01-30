package biz.customer.ddl;

import static jws.dal.common.DbType.Char;
import static jws.dal.common.DbType.DateTime;
import static jws.dal.common.DbType.Int;
import static jws.dal.common.DbType.TinyInt;
import static jws.dal.common.DbType.Varchar;
import jws.dal.annotation.Column;
import jws.dal.annotation.GeneratedValue;
import jws.dal.annotation.GenerationType;
import jws.dal.annotation.Id;
import jws.dal.annotation.Table;

import org.apache.commons.net.ntp.TimeStamp;

import extend.voinfo.annotation.Comment;

/**
 * 客户订单
 *
 * @author CodeGenerator
 * @date 2016-01-31 03:42:24
 */
@Table(name = "customer_order")
public class CustomerOrder {
    
    /**
     * Id
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "Id")
    public Integer id;
    
    /**
     * 客户id
     */
    @Column(name = "customer_id", type = Int)
    @Comment(name = "客户id")
    public Integer customerId;
    
    /**
     * 资源id
     */
    @Column(name = "resource_id", type = Int)
    @Comment(name = "资源id")
    public Integer resourceId;
    
    /**
     * 收获人
     */
    @Column(name = "receiver", type = Varchar)
    @Comment(name = "收获人")
    public String receiver;
    
    /**
     * 手机号码
     */
    @Column(name = "phone", type = Varchar)
    @Comment(name = "手机号码")
    public String phone;
    
    /**
     * 省份
     */
    @Column(name = "province", type = Varchar)
    @Comment(name = "省份")
    public String province;
    
    /**
     * 城市
     */
    @Column(name = "city", type = Varchar)
    @Comment(name = "城市")
    public String city;
    
    /**
     * 区
     */
    @Column(name = "area", type = Varchar)
    @Comment(name = "区")
    public String area;
    
    /**
     * 具体位置
     */
    @Column(name = "address", type = Varchar)
    @Comment(name = "具体位置")
    public String address;
    
    /**
     * 价格
     */
    @Column(name = "price", type = Varchar)
    @Comment(name = "价格")
    public String price;

    /**
     * 数量
     */
    @Column(name = "count", type = Int)
    @Comment(name = "数量")
    public String count;
    
    /**
     * 付款方式
     */
    @Column(name = "pay_way", type = TinyInt)
    @Comment(name = "付款方式 0：在线付款 1：货到付款")
    public Integer payWay;
    
    /**
     * 状态 0 未提交 1 未付款 2已付款
     */
    @Column(name = "state", type = TinyInt)
    @Comment(name = "状态 0 未提交 1 未付款 2已付款")
    public Integer state;
    
    /**
     * 是否生效 0:没生效 1:生效
     */
    @Column(name = "iseff", type = Char)
    @Comment(name = "是否生效 0:没生效 1:生效")
    public String iseff;
    
    /**
     * 
     */
    @Column(name = "create_time", type = DateTime)
    @Comment(name = "")
    public TimeStamp createTime;
    
    /**
     * 
     */
    @Column(name = "last_update", type = DateTime)
    @Comment(name = "")
    public TimeStamp lastUpdate;
}
