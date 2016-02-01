package biz.customer.ddl;

import static jws.dal.common.DbType.Char;
import static jws.dal.common.DbType.DateTime;
import static jws.dal.common.DbType.Int;
import static jws.dal.common.DbType.Varchar;
import jws.dal.annotation.Column;
import jws.dal.annotation.GeneratedValue;
import jws.dal.annotation.GenerationType;
import jws.dal.annotation.Id;
import jws.dal.annotation.Table;
import extend.voinfo.annotation.Comment;

/**
 * 客户信息
 *
 * @author CodeGenerator
 * @date 2016-01-31 05:03:25
 */
@Table(name = "customer")
public class Customer {
    
    /**
     * id
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "id")
    public Integer id;
    
    /**
     * 客户名称
     */
    @Column(name = "customer_name", type = Varchar)
    @Comment(name = "客户名称")
    public String customerName;
    
    /**
     * nickname
     */
    @Column(name = "nick_name", type = Varchar)
    @Comment(name = "nickname")
    public String nickName;
    
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
     * email
     */
    @Column(name = "email", type = Varchar)
    @Comment(name = "email")
    public String email;
    
    /**
     * 电话
     */
    @Column(name = "phone", type = Varchar)
    @Comment(name = "电话")
    public String phone;
    
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
    public Long createTime;
    
    /**
     * 
     */
    @Column(name = "last_update", type = DateTime)
    @Comment(name = "")
    public Long lastUpdate;
}
