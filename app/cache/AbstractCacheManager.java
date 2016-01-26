package cache;

import java.io.IOException;

import jws.Logger;

/**
 * 缓存管理抽象类
 * 
 * @author tanson lam
 * @createDate 2015年3月4日
 * 
 */
public class AbstractCacheManager {
    /**
     * 最大的缓存时间.30天
     */
    public static final int MAX_EXPIRE_TIME = 60 * 60 * 24 * 30;

    protected MemcacheManager mc;

    public AbstractCacheManager() {
        try {
            mc = MemcacheManager.getInstance();
        } catch (IOException e) {
            Logger.error(e, "fail to initialize the mc client!");
        }
    }

    /**
     * 获取定义在{@link CacheOperation}缓存数据
     * 
     * @param key
     * @param expiration
     * @param operation
     * @return
     */
    public <T> T getCacheObject(String key, int expiration, CacheOperation operation) {
        T object = (T) mc.get(key);
        if (object == null) {
            object = operation.cache();
            if (object != null)
                mc.safeSet(key, object, expiration);
        }
        return object;
    }
    
    /**
     * 删除缓存数据
     * @param key
     */
    public void delete(String key){
        mc.delete(key);
    }
}
