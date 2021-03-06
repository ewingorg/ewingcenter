package extend.job.dao;

import java.util.Date;

import extend.jdbc.BaseDao;
import extend.job.ProcessStatus;
import extend.job.model.JobProcessLog;

/**
 * 定时作业日志记录
 * 
 * @author tanson lam
 * @createDate 2015年3月2日
 * 
 */
public class JobProcessLogDao extends BaseDao {
    /**
     * 任务开始记录日志
     * 
     * @param name 任务名称
     */
    public static JobProcessLog logBegin(String name) {
        JobProcessLog log = new JobProcessLog();
        log.setName(name);
        log.setResult(ProcessStatus.BEGIN.getCode());
        log.setCreateTime(new Date().getTime());
        log.setUpdateTime(new Date().getTime());
        addSelectLastId(log);
        return log;
    }

    /**
     * 任务出错时记录日志
     * 
     * @param logId 日志主键id
     * @param message 执行结果信息
     */
    public static void logErr(Integer logId, String message) {
        JobProcessLog log = findById(logId, JobProcessLog.class);
        log.setResult(ProcessStatus.ERROR.getCode());
        log.setMessage(message);
        log.setUpdateTime(new Date().getTime());
        update(log);
    }

    /**
     * 任务成功执行后记录日志
     * 
     * @param logId 日志主键id
     * @param message 执行结果信息
     */
    public static void logSuc(Integer logId, String message) {
        JobProcessLog log = findById(logId, JobProcessLog.class);
        log.setResult(ProcessStatus.SUCCESS.getCode());
        log.setMessage(message);
        log.setUpdateTime(new Date().getTime());
        update(log);
    }
}
