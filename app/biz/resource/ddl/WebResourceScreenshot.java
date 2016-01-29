package biz.resource.ddl;

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
 * 资源截图
 *
 * @author CodeGenerator
 * @date 2016-01-26 10:10:42
 */
@Table(name = "web_resource_screenshot")
public class WebResourceScreenshot {
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "")
    public Integer id;
    
    /**
     * 资源id，表web_resource主键
     */
    @Column(name = "resource_id", type = Int)
    @Comment(name = "资源id，表web_resource主键")
    public Integer resourceId;
    
    /**
     * 主题名称
     */
    @Column(name = "name", type = Varchar)
    @Comment(name = "主题名称")
    public String name;
    
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
