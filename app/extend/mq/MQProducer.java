package extend.mq;

import javax.jms.Session;

/**
 * 消息队列的发送者接口
 * 
 * @author tanson lam
 * @createDate 2015年2月12日
 * 
 */
public interface MQProducer {

    /**
     * 发送队列信息
     * 
     * @param queue 队列名称
     * @param message 发送对象
     */
    public <T extends MQMessage<T>> void sendQueueMessage(String queue, T message);

    /**
     * 发送队列信息
     * 
     * @param queue 队列名称
     * @param message 发送文本
     */
    public void sendQueueMessage(String queue, String message);

    /**
     * 发送队列信息
     * 
     * @param session 
     * @param queue 队列名称
     * @param message 发送对象
     */
    public <T extends MQMessage<T>> void sendQueueMessage(Session session, String queue, T message);

    /**
     * 发送队列信息
     * 
     * @param session 
     * @param queue 队列名称
     * @param message 发送文本
     */
    public void sendQueueMessage(Session session, String queue, String message);

}
