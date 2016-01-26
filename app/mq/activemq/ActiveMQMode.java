package mq.activemq;

/**
 * ActiveMQ的消息模式
 * 
 * @author tanson lam
 * @createDate 2015年2月13日
 * 
 */
enum ActiveMQMode {
    /**
     * 发布/订阅域
     */
    TOPIC,
    /**
     * 点对点域
     */
    QUEUE;
}
