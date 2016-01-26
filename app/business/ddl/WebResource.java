package business.ddl;

import static jws.dal.common.DbType.Char;
import static jws.dal.common.DbType.DateTime;
import static jws.dal.common.DbType.Float;
import static jws.dal.common.DbType.Int;
import static jws.dal.common.DbType.Text;
import static jws.dal.common.DbType.Varchar;
import jws.dal.annotation.Column;
import jws.dal.annotation.GeneratedValue;
import jws.dal.annotation.GenerationType;
import jws.dal.annotation.Id;
import jws.dal.annotation.Table;
import jws.dal.common.DbType;

import java.sql.Timestamp;

import core.json.annotation.Comment;

/**
 * 资源
 *
 * @author CodeGenerator
 * @date 2016-01-26 10:10:42
 */
@Table(name = "web_resource")
public class WebResource {
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "")
    public Integer id;
    
    /**
     * 用户ID
     */
    @Column(name = "user_id", type = Int)
    @Comment(name = "用户ID")
    public Integer userId;
    
    /**
     * 资源名称
     */
    @Column(name = "name", type = Varchar)
    @Comment(name = "资源名称")
    public String name;
    
    /**
     * 模板id，web_template的主键
     */
    @Column(name = "template_id", type = Int)
    @Comment(name = "模板id，web_template的主键")
    public Integer templateId;
    
    /**
     * 资源分类id，category的主键
     */
    @Column(name = "category_id", type = Int)
    @Comment(name = "资源分类id，category的主键")
    public Integer categoryId;
    
    /**
     * 资源标签id，多个标签以","分隔
     */
    @Column(name = "tag_id", type = Varchar)
    @Comment(name = "资源标签id，多个标签以")
    public String tagId;
    
    /**
     * 菜单图片链接
     */
    @Column(name = "image_url", type = Varchar)
    @Comment(name = "菜单图片链接")
    public String imageUrl;
    
    /**
     * 简单描述
     */
    @Column(name = "short_desc", type = Varchar)
    @Comment(name = "简单描述")
    public String shortDesc;
    
    /**
     * 详细描述
     */
    @Column(name = "long_desc", type = Text)
    @Comment(name = "详细描述")
    public String longDesc;
    
    /**
     * 产品成本价
     */
    @Column(name = "cost", type = Float)
    @Comment(name = "产品成本价")
    public Float cost;
    
    /**
     * 产品价格
     */
    @Column(name = "price", type = Float)
    @Comment(name = "产品价格")
    public Float price;
    
    /**
     * 产品单位
     */
    @Column(name = "unit", type = Varchar)
    @Comment(name = "产品单位")
    public String unit;
    
    /**
     * 产品重量
     */
    @Column(name = "weight", type = Varchar)
    @Comment(name = "产品重量")
    public String weight;
    
    /**
     * 赠送积分
     */
    @Column(name = "gift_score", type = Varchar)
    @Comment(name = "赠送积分")
    public String giftScore;
    
    /**
     * 库存
     */
    @Column(name = "stock_num", type = Int)
    @Comment(name = "库存")
    public Integer stockNum;
    
    /**
     * 品牌ID
     */
    @Column(name = "brand_id", type = Int)
    @Comment(name = "品牌ID")
    public Integer brandId;
    
    /**
     * 促销类型 0:满减促销  1:折扣促销  2:返券促销
     */
    @Column(name = "promotion_type", type = Int)
    @Comment(name = "促销类型 0:满减促销  1:折扣促销  2:返券促销")
    public Integer promotionType;
    
    /**
     * 是否热门推荐
     */
    @Column(name = "is_hot", type = Int)
    @Comment(name = "是否热门推荐")
    public Integer isHot;
    /**
     * 是否上架 0:下架 1:上架
     */
    @Column(name = "is_online", type = Int)
    @Comment(name = "是否上架 0:下架 1:上架")
    public Integer isOnline;
    
    /**
     * 是否置顶 0:不 1:是
     */
    @Column(name = "is_top", type = Int)
    @Comment(name = "是否置顶 0:不 1:是")
    public Integer isTop;
    
    /**
     * 是否需要物流 0:否 1:是
     */
    @Column(name = "need_carry", type = Int)
    @Comment(name = "是否需要物流 0:否 1:是")
    public Integer needCarry;
    
    /**
     * 备注
     */
    @Column(name = "remark", type = Varchar)
    @Comment(name = "备注")
    public String remark;
    
    /**
     * 搜索关键词
     */
    @Column(name = "search_keyword", type = Varchar)
    @Comment(name = "搜索关键词")
    public String searchKeyword;
    
    /**
     * 页面标题
     */
    @Column(name = "page_title", type = Varchar)
    @Comment(name = "页面标题")
    public String pageTitle;
    
    /**
     * 页面关键词
     */
    @Column(name = "page_keyword", type = Varchar)
    @Comment(name = "页面关键词")
    public String pageKeyword;
    
    /**
     * 页面描述
     */
    @Column(name = "page_desc", type = Varchar)
    @Comment(name = "页面描述")
    public String pageDesc;
    
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
    
    /**
     * ÊÇ·ñÁÐ³ö 0:²»ÏÔÊ¾ 1:ÏÔÊ¾
     */
    @Column(name = "is_show", type = Int)
    @Comment(name = "ÊÇ·ñÁÐ³ö 0:²»ÏÔÊ¾ 1:ÏÔÊ¾")
    public Integer isShow;
}
