package extend.mq;

import java.util.HashMap;
import java.util.Map;

import javax.jms.MessageListener;

import org.apache.activemq.ActiveMQConnection;

import jws.Jws;
import jws.Logger;

/**
 * 消息队列的监听者抽象类
 * 
 * @author tanson lam
 * @createDate 2015年2月26日
 * 
 */
public abstract class MQListener {
    protected final MQReceiver receiver;

    protected Map<String, Class<? extends MessageListener>> queueListnerMap = new HashMap<String, Class<? extends MessageListener>>();

    public MQListener(final MQReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * 获取定义的消息监听器
     * 
     * @return
     */
    public abstract Map<String, Class<? extends MessageListener>> getMessageListener();

    /**
     * 添加消息监听器
     * 
     * @param queue 消息队列
     * @param messageListenerClazz 消息队列的监听器类
     */
    public void addMessageListener(String queue,
            Class<? extends MessageListener> messageListenerClazz) {
        if (queueListnerMap.get(queue) == null)
            queueListnerMap.put(queue, messageListenerClazz);
    }

    /**
     * 启动所有队列监听器
     */
    public void startListeners() {
        Logger.info("Messagequeue start listeners");
        queueListnerMap.putAll(getMessageListener());
        if (queueListnerMap.isEmpty())
            return;
        for (String queue : queueListnerMap.keySet()) {
            Class<? extends MessageListener> messageListenerClazz = queueListnerMap.get(queue);
            try {
                Logger.info("Messagequeue initialize listener, queue[" + queue + "] listenerClazz["
                        + messageListenerClazz.getName() + "]");
                Integer listenerNum = Integer.valueOf(Jws.configuration.getProperty(
                        "messagequeue.listener." + queue + ".number", "1"));
                for (int i = 0; i < listenerNum; i++)
                    receiver.setMessageListener(queue, messageListenerClazz.newInstance());
            } catch (InstantiationException e) {
                Logger.error(e, e.getMessage());
            } catch (IllegalAccessException e) {
                Logger.error(e, e.getMessage());
            }
        }
    }

}
