package extend.mq.activemq;

import java.util.WeakHashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import extend.mq.MQMessage;
import extend.mq.MQReceiver;
import jws.Logger;
import jws.mq.MQException;

/**
 * ActiveMQ消息消费者
 * 
 * @author tanson lam
 * @createDate 2015年2月13日
 * 
 */
class ActiveMQReceiver implements MQReceiver {

    private final ActiveMQFactory activeMQFactory;

    public ActiveMQReceiver(ActiveMQFactory activeMQFactory) {
        this.activeMQFactory = activeMQFactory;
    }

    @Override
    public void setMessageListener(String queue, MessageListener messageListener)
            throws MQException {
        Session session = null;
        try {
            session = activeMQFactory.getSession(false, Session.CLIENT_ACKNOWLEDGE);
            MessageConsumer consumer = activeMQFactory.createConsumer(session, ActiveMQMode.QUEUE,
                    queue);
            consumer.setMessageListener(messageListener);
        } catch (Exception e) {
            throw new MQException("error occur in receive message from queue[" + queue + "]", e);
        }
    }

    @Override
    public void setMessageListener(Session session, String queue, MessageListener messageListener)
            throws MQException {
        try {
            MessageConsumer consumer = activeMQFactory.createConsumer(session, ActiveMQMode.QUEUE,
                    queue);
            consumer.setMessageListener(messageListener);
        } catch (Exception e) {
            throw new MQException("error occur in receive message from queue[" + queue + "]", e);
        }
    }

}
