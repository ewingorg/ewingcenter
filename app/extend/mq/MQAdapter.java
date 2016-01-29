package extend.mq;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.Session;

import jws.Jws;
import jws.Logger;
import jws.mq.MQException;

/**
 * MQ实现适配器
 * 
 * @author tanson lam
 * @createDate 2015年2月13日
 * 
 */
public class MQAdapter implements MQFactory {
    private MQProducer producer;
    private MQReceiver receiver;
    private MQFactory mqFactory; 
    private static String MQFACTORY_CLAZZ = null;
    private static final MQAdapter instance = new MQAdapter();

    private MQAdapter() {
        try {
            MQFACTORY_CLAZZ = Jws.configuration.getProperty("mq.factory.clazz",
                    "mq.activemq.ActiveMQFactory");
            mqFactory = (MQFactory) ClassLoaderUtil.loadClass(MQFACTORY_CLAZZ).newInstance();
            producer = mqFactory.getMQProducer();
            receiver = mqFactory.getMQReceiver();
        } catch (Exception e) {
            Logger.error(e, e.getMessage());
        }
    }

    public static MQAdapter getInstance() {
        return instance;
    }

    @Override
    public Session getSession(Boolean transacted, int ackMode) throws JMSException {
        return mqFactory.getSession(transacted, ackMode);
    }

    @Override
    public void returnSession(Session session) throws JMSException {
        mqFactory.returnSession(session);
    }

    @Override
    public MQProducer getMQProducer() {
        return producer;
    }

    @Override
    public MQReceiver getMQReceiver() {
        return receiver;
    }
}
