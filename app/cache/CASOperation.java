package cache;

/**
 * 定义CAS的操作
 * 
 * @author tanson lam
 * @createDate 2015年3月4日
 * 
 */
public interface CASOperation<OrgValue, ChangeValue, Result> {
    /**
     * 更新
     * 
     * @param orgValue
     * @param changeValue
     * @return
     */
    public Result update(OrgValue orgValue, ChangeValue changeValue);
}
