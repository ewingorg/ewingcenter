package extend.job.dao;

import java.util.List;

import jws.dal.sqlbuilder.Condition;
import extend.jdbc.BaseDao;
import extend.job.model.JobDefine;

/**
 * 定时任务定义表dao类
 * 
 * @author tanson lam
 * @createDate 2015年2月28日
 * 
 */
public class JobDefineDao extends BaseDao {
    /**
     * 显示所有生效的定义任务
     * 
     * @return
     */
    public static List<JobDefine> listEffJob(String cluster) {
        Condition condition = new Condition("JobDefine.cluster", "=", cluster);
        condition.add(new Condition("JobDefine.isEnable", "=", "1"), "AND"); 
        return list(JobDefine.class, condition);
    }

}
