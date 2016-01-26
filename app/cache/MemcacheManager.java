package cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import jws.Jws;
import jws.Logger;
import jws.exceptions.ConfigurationException;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.DefaultHashAlgorithm;
import net.spy.memcached.FailureMode;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;
import net.spy.memcached.transcoders.SerializingTranscoder;

/**
 * 
 * Memcache工具类
 * 
 * @author tanson lam
 * @createDate 2015年3月4日
 *
 */
public class MemcacheManager {

    private static MemcacheManager uniqueInstance;

    MemcachedClient client;

    SerializingTranscoder tc;

    ClassLoader classLoader;

    /**
     * 用于互斥的锁
     */
    private static byte[] lock = new byte[0];

    public static MemcacheManager getInstance() throws IOException {
        return getInstance(false);
    }

    public static MemcacheManager getInstance(boolean forceClientInit) throws IOException {

        if (uniqueInstance == null) {
            synchronized (lock) {
                if (uniqueInstance == null) {
                    uniqueInstance = new MemcacheManager();
                }
            }
        } else if (forceClientInit) {
            // When you stop the client, it sets the interrupted state of this thread to true. If you try to reinit it with the same thread
            // in this state,
            // Memcached client errors out. So a simple call to interrupted() will reset this flag
            Thread.interrupted();
            uniqueInstance.initClient();
        }
        return uniqueInstance;

    }

    private MemcacheManager() throws IOException {
        initClient();
    }

    public void initClient() throws IOException {
        System.setProperty("net.spy.log.LoggerImpl", "net.spy.memcached.compat.log.Log4JLogger");

        ConnectionFactoryBuilder builder = new ConnectionFactoryBuilder()
                .setProtocol(ConnectionFactoryBuilder.Protocol.BINARY)
                .setReadBufferSize(1024 * 1024).setOpTimeout(3 * 1000).setUseNagleAlgorithm(true)
                .setFailureMode(FailureMode.Retry);

        List<InetSocketAddress> addrs;
        if (Jws.initialized) {
            if (Jws.configuration.containsKey("memcached.host")) {
                addrs = AddrUtil.getAddresses(Jws.configuration.getProperty("memcached.host"));
            } else if (Jws.configuration.containsKey("memcached.1.host")) {
                int nb = 1;
                String addresses = "";
                while (Jws.configuration.containsKey("memcached." + nb + ".host")) {
                    addresses += Jws.configuration.get("memcached." + nb + ".host") + " ";
                    nb++;
                }
                addrs = AddrUtil.getAddresses(addresses);
            } else {
                throw new ConfigurationException("Bad configuration for memcached: missing host(s)");
            }

            if (Jws.configuration.containsKey("memcached.user")) {
                String memcacheUser = Jws.configuration.getProperty("memcached.user");
                String memcachePassword = Jws.configuration.getProperty("memcached.password");
                if (memcachePassword == null) {
                    throw new ConfigurationException(
                            "Bad configuration for memcached: missing password");
                }

                // Use plain SASL to connect to memcached
                AuthDescriptor ad = new AuthDescriptor(new String[] { "PLAIN" },
                        new PlainCallbackHandler(memcacheUser, memcachePassword));
                builder.setAuthDescriptor(ad);
            }

            if (Jws.configuration.getProperty("memcached.useConsistentHash", "false")
                    .equals("true")) {
                builder.setHashAlg(DefaultHashAlgorithm.KETAMA_HASH).setLocatorType(
                        ConnectionFactoryBuilder.Locator.CONSISTENT);
            }
            classLoader = Jws.classloader;
        } else {
            classLoader = this.getClass().getClassLoader();
            addrs = AddrUtil.getAddresses(System.getProperty("memcached.host"));
        }

        tc = new SerializingTranscoder() {

            @Override
            protected Object deserialize(byte[] data) {
                try {
                    return new ObjectInputStream(new ByteArrayInputStream(data)) {

                        @Override
                        protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException,
                                ClassNotFoundException {
                            return Class.forName(desc.getName(), false, classLoader);
                        }
                    }.readObject();
                } catch (Exception e) {
                    Logger.error(e, "Could not deserialize");
                }
                return null;
            }

            @Override
            protected byte[] serialize(Object object) {
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    new ObjectOutputStream(bos).writeObject(object);
                    return bos.toByteArray();
                } catch (IOException e) {
                    Logger.error(e, "Could not serialize");
                }
                return null;
            }
        };
        client = new MemcachedClient(builder.build(), addrs);
    }

    public void add(String key, Object value, int expiration) {
        client.add(key, expiration, value, tc);
    }

    public Object get(String key) {
        Future<Object> future = client.asyncGet(key, tc);
        try {
            return future.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            future.cancel(false);
        }
        return null;
    }

    public void clear() {
        client.flush();
    }

    public void delete(String key) {
        client.delete(key);
    }

    public Map<String, Object> get(String[] keys) {
        Future<Map<String, Object>> future = client.asyncGetBulk(tc, keys);
        try {
            return future.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            future.cancel(false);
        }
        return Collections.<String, Object> emptyMap();
    }

    public long incr(String key, int by) {
        return client.incr(key, by, 0);
    }

    public long incr(String key, int by, long value) {
        return client.incr(key, by, value);
    }
    
    public long incr(String key, int by, long value, int expiration) {
        return client.incr(key, by, value, expiration);
    }

    public long decr(String key, int by) {
        return client.decr(key, by, 0);
    }

    public long decr(String key, int by, long value) {
        return client.decr(key, by, value);
    }
    
    public long decr(String key, int by, long value, int expiration) {
        return client.decr(key, by, value, expiration);
    }

    public void replace(String key, Object value, int expiration) {
        client.replace(key, expiration, value, tc);
    }

    public boolean safeAdd(String key, Object value, int expiration) {
        Future<Boolean> future = client.add(key, expiration, value, tc);
        try {
            return future.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            future.cancel(false);
        }
        return false;
    }

    public boolean safeDelete(String key) {
        Future<Boolean> future = client.delete(key);
        try {
            return future.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            future.cancel(false);
        }
        return false;
    }

    public boolean safeReplace(String key, Object value, int expiration) {
        Future<Boolean> future = client.replace(key, expiration, value, tc);
        try {
            return future.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            future.cancel(false);
        }
        return false;
    }

    public boolean safeSet(String key, Object value, int expiration) {
        Future<Boolean> future = client.set(key, expiration, value, tc);
        try {
            return future.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            future.cancel(false);
        }
        return false;
    }

    public void set(String key, Object value, int expiration) {
        client.set(key, expiration, value, tc);
    }

    public void stop() {
        client.shutdown();
    }

    public CASValue<Object> gets(String key) {
        return client.gets(key,tc);
    }

    public boolean CASReplace(String key, Object changeValue, Object initialValue, int expiration,
            CASOperation operation, int maxCASTime) {
        boolean ret = false;
        int errCASTime = 0;
        while (!ret) {

            CASValue<Object> oldValue = null;
            try { 
                oldValue = client.gets(key, tc);

                if (oldValue == null) {
                    ret = safeAdd(key, initialValue, expiration);
                } else {
                    Object value = operation.update(oldValue.getValue(), changeValue);
                    if (value == null)
                        return false;
                    CASResponse r =  client.cas(key, oldValue.getCas(), expiration, value, tc); 
                    switch (r) {
                    case OK:
                        ret = true;
                        break;
                    case NOT_FOUND:
                        ret = false;
                        errCASTime++;
                        break;
                    case EXISTS:
                        ret = false;
                        errCASTime++;
                        break;
                    }
                }
            } catch (Exception e) {
                Logger.error(e, e.getMessage());
                break;
            }
            if (errCASTime >= maxCASTime)
                break;
            if (!ret)
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {

                }

        }
        return ret;
    }

    public boolean CASReplace(String key, Object changeValue, Object initialValue, int expiration,
            CASOperation operation, int maxCASTime, CASValue<Object> casValue) {
        boolean ret = false;
        int errCASTime = 0;
        while (!ret) {  
            try {  
                if (casValue == null) {
                    ret = safeAdd(key, initialValue, expiration);
                } else {
                    Object value = operation.update(casValue.getValue(), changeValue);
                    if (value == null)
                        return false;
                    CASResponse r = client.cas(key, casValue.getCas(), expiration, value, tc); 
                    switch (r) {
                    case OK:
                        ret = true;
                        break;
                    case NOT_FOUND:
                        ret = false;
                        errCASTime++;
                        break;
                    case EXISTS:
                        ret = false;
                        errCASTime++;
                        break;
                    }
                }
            } catch (Exception e) {
                Logger.error(e, e.getMessage());
                break;
            }
            if (errCASTime >= maxCASTime)
                break;
            if (!ret)
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {

                }

        }
        return ret;
    }

    public void flush() {
        client.flush();
    }
}
