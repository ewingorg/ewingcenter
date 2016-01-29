package biz.resource.dto;


/**
 * 产品规格
 * 
 * @author tansonlam
 * @createDate 2016年1月26日
 * 
 */
public class ProductSpec {
    /**
     * 规格ID
     */
    private Integer id;

    /**
     * 资源id，表web_resource主键
     */
    private Integer resourceId;

    /**
     * 规格名称
     */
    private String spec;

    /**
     * 规格组名称
     */
    private String rootSpec;
    /**
     * 排位
     */
    private Integer rank;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getRootSpec() {
        return rootSpec;
    }

    public void setRootSpec(String rootSpec) {
        this.rootSpec = rootSpec;
    }

}
