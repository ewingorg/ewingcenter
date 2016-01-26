package mq.activemq;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import jws.Jws;
import jws.Logger;
import mq.MQFactory;
import mq.MQProducer;
import mq.MQReceiver;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

/**
 * ActiveMQ连接类工厂类
 * 
 * @author tanson lam
 * @createDate 2015年2月10日
 * 
 */
public class ActiveMQFactory implements MQFactory {
    private ActiveMQPool connectionPool;
    private final String BROKER_USER;
    private final String BROKER_PASSWORD;
    private final String BROKER_URL;

    public ActiveMQFactory() {
        BROKER_USER = Jws.configuration.getProperty("activemq.broker.user",
                ActiveMQConnection.DEFAULT_USER);
        BROKER_PASSWORD = Jws.configuration.getProperty("activemq.broker.password",
                ActiveMQConnection.DEFAULT_PASSWORD);
        if (System.getProperty("ativemq.broker.url") != null)
            BROKER_URL = System.getProperty("activemq.broker.url");
        else
            BROKER_URL = Jws.configuration.getProperty("activemq.broker.url", "");

        connectionPool = new ActiveMQPool(BROKER_USER, BROKER_PASSWORD, BROKER_URL);
    }

    public ActiveMQFactory(String brokerUser, String brokerPwd, String brokerURL) {
        BROKER_USER = brokerUser;
        BROKER_PASSWORD = brokerPwd;
        BROKER_URL = brokerURL;
        connectionPool = new ActiveMQPool(brokerUser, brokerPwd, brokerURL);
    }

    public MessageConsumer createConsumer(Session session, ActiveMQMode mode, String subject)
            throws JMSException {
        Destination destination = createDestination(session, mode, subject);
        MessageConsumer consumer = session.createConsumer(destination);
        return consumer;
    }

    private Destination createDestination(Session session, ActiveMQMode mode, String subject)
            throws JMSException {
        if (session != null && mode != null && subject != null) {
            if (mode.equals(ActiveMQMode.TOPIC)) {
                return session.createTopic(subject);
            } else if (mode.equals(ActiveMQMode.QUEUE)) {
                return session.createQueue(subject);
            }
        }
        return null;
    }

    public MessageProducer createProducer(Session session, ActiveMQMode mode, String subject,
            int deliveryMode) throws JMSException {
        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        return producer;
    }

    @Override
    public MQProducer getMQProducer() {
        return new ActiveMQProducer(this);
    }

    @Override
    public MQReceiver getMQReceiver() {
        return new ActiveMQReceiver(this);
    }

    @Override
    public Session getSession(Boolean transacted, int ackMode) throws JMSException {
        return connectionPool.getConn().createSession(transacted, ackMode);
    }

    @Override
    public void returnSession(Session session) throws JMSException {
        if (session != null)
            session.close();
    }

}
