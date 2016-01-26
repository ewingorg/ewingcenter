package job.model;

import static jws.dal.common.DbType.*;
import jws.dal.annotation.Column;
import jws.dal.annotation.GeneratedValue;
import jws.dal.annotation.GenerationType;
import jws.dal.annotation.Id;
import jws.dal.annotation.Table;

/**
 * 定时作业进程投票选举
 *
 * @author CodeGenerator
 * @date 2014-09-15 16:32:26
 */
@Table(name = "job_vote")
public class JobVote {

    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    public Integer id;

    /**
     * 集群组名称
     */
    @Column(name = "node", type = Varchar)
    public String node;

    /**
     * 集群名称
     */
    @Column(name = "cluster", type = Varchar)
    public String cluster;

    /**
     * 创建时间
     */
    @Column(name = "create_time", type = BigInt)
    public Long createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time", type = BigInt)
    public Long updateTime;

    public String getCluster() {
        return cluster;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public Integer getId() {
        return id;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

}
