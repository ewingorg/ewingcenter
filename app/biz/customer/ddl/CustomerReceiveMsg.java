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
import extend.json.annotation.Comment;

/**
 * 用户收获地址
 * 
 * @author chenxuegui.cxg@alibaba-inc.com
 * @createDate 2016年1月29日
 */
@Table(name = "customer_receive_msg")
public class CustomerReceiveMsg
{

	/**
	 * 微信开发者用户Id
	 */
	@Id
	@GeneratedValue(generationType = GenerationType.Auto)
	@Column(name = "id", type = Int)
	@Comment(name = "主键Id")
	public Integer id;

	/**
	 * 用户id
	 */
	@Column(name = "customer_id", type = Int)
	@Comment(name = "客户id")
	public Integer customerId;

	/**
     * 收件人
     */
    @Column(name = "receiver", type = Varchar)
    @Comment(name = "收件人")
    public String receiver;
    
    /**
     * 收件人
     */
    @Column(name = "phone", type = Varchar)
    @Comment(name = "电话")
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
     * 是否默认 0:不是 1:是
     */
    @Column(name = "is_default", type = Char)
    @Comment(name = "是否生效 0:没生效 1:生效")
    public String isDefault;
    
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
