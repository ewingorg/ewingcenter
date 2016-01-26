package cache;

/**
 * 定义需要缓存的数据操作
 * 
 * @author tanson lam
 * @createDate 2015年3月14日
 * 
 */
public interface CacheOperation {

    public <T> T cache();
}
