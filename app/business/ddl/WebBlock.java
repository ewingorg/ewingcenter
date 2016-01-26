package business.ddl;

import static jws.dal.common.DbType.Char;
import static jws.dal.common.DbType.DateTime;
import static jws.dal.common.DbType.Int;
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
 * 分类配置
 *
 * @author CodeGenerator
 * @date 2016-01-26 10:10:42
 */
@Table(name = "web_block")
public class WebBlock {
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "")
    public Integer id;
    
    /**
     * 父id
     */
    @Column(name = "parent_id", type = Int)
    @Comment(name = "父id")
    public Integer parentId;
    
    /**
     * 0:导航分类 1:分类链接 2:产品分类
     */
    @Column(name = "group_type", type = Char)
    @Comment(name = "0:导航分类 1:分类链接 2:产品分类")
    public String groupType;
    
    /**
     * 组key
     */
    @Column(name = "group_key", type = Varchar)
    @Comment(name = "组key")
    public String groupKey;
    
    /**
     * 菜单名称
     */
    @Column(name = "name", type = Varchar)
    @Comment(name = "菜单名称")
    public String name;
    
    /**
     * 菜单图片链接
     */
    @Column(name = "image_url", type = Varchar)
    @Comment(name = "菜单图片链接")
    public String imageUrl;
    
    /**
     * 外链接
     */
    @Column(name = "link_url", type = Varchar)
    @Comment(name = "外链接")
    public String linkUrl;
    
    /**
     * 排序
     */
    @Column(name = "rank", type = Int)
    @Comment(name = "排序")
    public Integer rank;
    
    /**
     * 是否生效 0:没生效 1:生效
     */
    @Column(name = "iseff", type = Char)
    @Comment(name = "是否生效 0:没生效 1:生效")
    public String iseff;
    
    /**
     * 
     */
    @Column(name = "create_time", type = DbType.BigInt)
    @Comment(name = "")
    public Long createTime;
    
    /**
     * 
     */
    @Column(name = "last_update", type = DbType.BigInt)
    @Comment(name = "")
    public Long lastUpdate;
    
    /**
     * 
     */
    @Column(name = "tempalte", type = Varchar)
    @Comment(name = "")
    public String tempalte;
    
    /**
     * 
     */
    @Column(name = "short_desc", type = Varchar)
    @Comment(name = "")
    public String shortDesc;
}
