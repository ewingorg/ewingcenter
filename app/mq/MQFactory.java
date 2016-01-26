package mq;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * 消息工厂接口
 * 
 * @author tanson lam
 * @createDate 2015年2月13日
 * 
 */
public interface MQFactory {
    /**
     * 获取消息生产者
     * 
     * @return
     */
    public MQProducer getMQProducer();

    /**
     * 获取消息接受者
     * 
     * @return
     */
    public MQReceiver getMQReceiver();

    /**
     * 获取session
     * 
     * @param transacted 是否事务
     * @param ackMode session会话应答方式，参考{@link javax.jms.Session}
     *            <ul>
     *            <li>AUTO_ACKNOWLEDGE = 1;</li>
     *            <li>CLIENT_ACKNOWLEDGE = 2;</li>
     *            <li>DUPS_OK_ACKNOWLEDGE = 3;</li>
     *            <li>SESSION_TRANSACTED = 0;</li>
     *            </ul>
     * 
     * @return 返回session实例
     * @throws JMSException
     */

    public Session getSession(Boolean transacted, int ackMode) throws JMSException;

    /**
     * 回收session到session对象池
     * 
     * @param session
     * @throws JMSException
     */
    public void returnSession(Session session) throws JMSException;
}
