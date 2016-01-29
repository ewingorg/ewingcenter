package external.mpsdk.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import extend.cache.MemoryCache;
import external.mpsdk.vo.api.AccessToken;

/**
 * 本地缓存AccessToken信息
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class AccessTokenMemoryCache implements MemoryCache<AccessToken> {

    private Map<String, AccessToken> ats = new ConcurrentHashMap<String, AccessToken>();

    @Override
    public AccessToken get(String mpId) {
        return ats.get(mpId);
    }

    @Override
    public void set(String mpId, AccessToken object) {
        ats.put(mpId, object);
    }

    @Override
    public void remove(String mpId) {
        ats.remove(mpId);
    }

}
