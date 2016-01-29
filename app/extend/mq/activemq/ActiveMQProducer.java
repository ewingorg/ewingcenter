package extend.mq.activemq;

import java.util.concurrent.ConcurrentHashMap;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import extend.mq.MQMessage;
import extend.mq.MQProducer;
import jws.Logger;
import jws.mq.MQException;

/**
 * ActiveMQ消息生产者
 * 
 * @author tanson lam
 * @createDate 2015年2月12日
 * 
 */
class ActiveMQProducer implements MQProducer {
    private final ActiveMQFactory activeMQFactory;

    public ActiveMQProducer(ActiveMQFactory activeMQFactory) {
        this.activeMQFactory = activeMQFactory;
    }

    @Override
    public <T extends MQMessage<T>> void sendQueueMessage(String queue, T message) {
        Session session = null;
        try {
            session = activeMQFactory.getSession(false, Session.AUTO_ACKNOWLEDGE); 
            TextMessage textMessage = session.createTextMessage(message.object2Json());
            MessageProducer messageProducer = activeMQFactory.createProducer(session,
                    ActiveMQMode.QUEUE, queue, DeliveryMode.PERSISTENT);
            messageProducer.send(textMessage);
        } catch (Exception e) {
            throw new MQException("error occur in send message to queue[" + queue + "]", e);
        } finally {
            try {
                activeMQFactory.returnSession(session);
            } catch (JMSException e) {
                Logger.error(e,e.getMessage());
            }
        }
    }

    @Override
    public void sendQueueMessage(String queue, String message) {
        Session session = null;
        try {
            session = activeMQFactory.getSession(false, Session.AUTO_ACKNOWLEDGE);
            TextMessage textMessage = session.createTextMessage(message);
            MessageProducer messageProducer = activeMQFactory.createProducer(session,
                    ActiveMQMode.QUEUE, queue, DeliveryMode.PERSISTENT);
            messageProducer.send(textMessage);
        } catch (Exception e) {
            throw new MQException("error occur in send message to queue[" + queue + "]", e);
        } finally {
            try {
                activeMQFactory.returnSession(session);
            } catch (JMSException e) {
                Logger.error(e,e.getMessage());
            }
        }
    }

    @Override
    public <T extends MQMessage<T>> void sendQueueMessage(Session session, String queue, T message) {
        if (session == null)
            throw new MQException("session can not be null!");
        try {
            TextMessage textMessage = session.createTextMessage(message.object2Json());
            MessageProducer messageProducer = activeMQFactory.createProducer(session,
                    ActiveMQMode.QUEUE, queue, DeliveryMode.PERSISTENT);
            messageProducer.send(textMessage);
        } catch (Exception e) {
            throw new MQException("error occur in send message to queue[" + queue + "]", e);
        }
    }

    @Override
    public void sendQueueMessage(Session session, String queue, String message) {
        if (session == null)
            throw new MQException("session can not be null!");
        try {
            TextMessage textMessage = session.createTextMessage(message);
            MessageProducer messageProducer = activeMQFactory.createProducer(session,
                    ActiveMQMode.QUEUE, queue, DeliveryMode.PERSISTENT);
            messageProducer.send(textMessage);
        } catch (Exception e) {
            throw new MQException("error occur in send message to queue[" + queue + "]", e);
        }
    }
}
