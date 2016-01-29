package extend.mq.activemq;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import jws.Jws;
import jws.Logger;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.StackObjectPool;

/**
 * ActiveMQ 连接池
 * 
 * @author tanson lam
 * @createDate 2015月
 */
public class ActiveMQPool implements PoolableObjectFactory {

    private final String BROKER_USER;
    private final String BROKER_PASSWORD;
    private final String BROKER_URL;
    private PooledConnection connection;
    private ActiveMQConnectionFactory factory;
    private PooledConnectionFactory pooledConnectionFactory;
    private final Integer MAXCONNECTION = Integer.valueOf(Jws.configuration.getProperty(
            "activemq.maxconnection", "5"));
    private AtomicInteger connectionCounter = new AtomicInteger();
    private LinkedList<PooledConnection> pooledConnectionCacahe = new LinkedList<PooledConnection>();
    private Random random = new Random();
    private final ObjectPool pool = new StackObjectPool(this);
    private Object connectionPoolLock = new Object();
    private ExecutorService connectionInitExecutor;

    public ActiveMQPool() {
        BROKER_USER = Jws.configuration.getProperty("activemq.broker.user",
                ActiveMQConnection.DEFAULT_USER);
        BROKER_PASSWORD = Jws.configuration.getProperty("activemq.broker.password",
                ActiveMQConnection.DEFAULT_PASSWORD);
        BROKER_URL = Jws.configuration.getProperty("activemq.broker.url");
        initFactory();
    }

    private void initFactory() {
        factory = new ActiveMQConnectionFactory(BROKER_USER, BROKER_PASSWORD, BROKER_URL);
        pooledConnectionFactory = new PooledConnectionFactory(factory);
        // session数
        int maximumActive = 200;
        pooledConnectionFactory.setMaximumActiveSessionPerConnection(maximumActive);
        // pooledConnectionFactory.setIdleTimeout(1);
        pooledConnectionFactory.setMaxConnections(MAXCONNECTION);
        pooledConnectionFactory.setBlockIfSessionPoolIsFull(false);
        // pooledConnectionFactory.setExpiryTimeout(1000);
        connectionInitExecutor = Executors.newFixedThreadPool(MAXCONNECTION, new ThreadFactory() {
            int threadNum = 0;

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("ActiveMQ initconn thread:" + (threadNum++));
                t.setDaemon(true);
                return t;
            }

        });
    }

    public ActiveMQPool(String brokerUser, String brokerPwd, String brokerURL) {
        BROKER_USER = brokerUser;
        BROKER_PASSWORD = brokerPwd;
        BROKER_URL = brokerURL;
        initFactory();
    }

    public void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException e) {
            Logger.error(e, e.getMessage());
        }
    }

    public PooledConnection getConn() throws JMSException {
        try {
            return (PooledConnection) pool.borrowObject();
        } catch (Exception e) {
            throw new JMSException(e.getMessage());
        }
    }

    public void setConn(PooledConnection conn) {
        this.connection = conn;
    }

    @Override
    public void activateObject(Object arg0) throws Exception {

    }

    @Override
    public void destroyObject(Object arg0) throws Exception {
        PooledConnection conn = (PooledConnection) arg0;
        conn.close();
    }

    @Override
    public PooledConnection makeObject() throws Exception {
        /*
         * System.out.println("connectionCounter.get()" + connectionCounter.get() + " pooledConnectionCacahe size:" +
         * pooledConnectionCacahe.size());
         */
        if (connectionCounter.get() < MAXCONNECTION) {
            PooledConnection connection = (PooledConnection) pooledConnectionFactory
                    .createConnection();
            connection.setExceptionListener(new ConnectionExceptionListener(connection));
            connectionInitExecutor.execute(new LazyInitConnectionRunnable(connection));
            addConnectionPool(connection);
            return connection;
        }
        if (pooledConnectionCacahe.size() == 0)
            return null;
        return pooledConnectionCacahe.get(random.nextInt(pooledConnectionCacahe.size()));
    }

    class ReconnectionThread extends Thread {

    }

    /**
     * 连接异常的监听器，任意一个连接出错都清除掉整连接池
     */
    class ConnectionExceptionListener implements ExceptionListener {
        private PooledConnection connection;

        public ConnectionExceptionListener(PooledConnection connection) {
            this.connection = connection;
        }

        @Override
        public void onException(JMSException exception) {
            clearConnectionPool();
        }
    }

    private void addConnectionPool(PooledConnection connection) {
        synchronized (connectionPoolLock) {
            connectionCounter.incrementAndGet();
            pooledConnectionCacahe.add(connection);
        }

    }

    private void clearConnectionPool() {
        synchronized (connectionPoolLock) {
            pooledConnectionCacahe.clear();
            connectionCounter.set(0);
            pooledConnectionFactory.clear();
        }
    }

    /**
     * 初始化connection
     */
    class LazyInitConnectionRunnable implements Runnable {
        private PooledConnection connection;

        public LazyInitConnectionRunnable(PooledConnection connection) {
            this.connection = connection;
        }

        public void run() {
            try {
                connection.start();
            } catch (JMSException e) {
                Logger.error(e, e.getMessage());
            }
        }
    }

    @Override
    public void passivateObject(Object arg0) throws Exception {

    }

    @Override
    public boolean validateObject(Object arg0) {
        return true;
    }

}