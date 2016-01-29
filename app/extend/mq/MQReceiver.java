package extend.mq;

import javax.jms.MessageListener;
import javax.jms.Session;

import jws.mq.MQException;

/**
 * 消息队列的消费者接口
 * 
 * @author tanson lam
 * @createDate 2015年2月13日
 * 
 */
public interface MQReceiver {

    /**
     * 设置消息接收监听器
     * 
     * @param queue 队列名称
     * @param messageListener 消息接收监听器
     * @throws MQException
     */
    public void setMessageListener(String queue, MessageListener messageListener)
            throws MQException;

    /**
     * 设置消息接收监听器
     * 
     * @param session
     * @param queue
     * @param messageListener
     * @throws MQException
     */
    public void setMessageListener(Session session, String queue, MessageListener messageListener)
            throws MQException;

}
