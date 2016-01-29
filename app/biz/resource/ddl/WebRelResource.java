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
 * 分类和资源关系表
 *
 * @author CodeGenerator
 * @date 2016-01-26 10:10:42
 */
@Table(name = "web_rel_resource")
public class WebRelResource {
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "")
    public Integer id;
    
    /**
     * 分类id
     */
    @Column(name = "category_id", type = Int)
    @Comment(name = "分类id")
    public Integer categoryId;
    
    /**
     * 资源id
     */
    @Column(name = "resource_id", type = Int)
    @Comment(name = "资源id")
    public Integer resourceId;
    
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
     * 
     */
    @Column(name = "tempalte", type = Varchar)
    @Comment(name = "")
    public String tempalte;
}
