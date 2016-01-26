package mq;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.jms.MessageListener;
import javax.jms.Session;

import jws.Jws;
import jws.Logger;
import jws.mq.MQException;

/**
 * 消息队列的消费者工具类
 * 
 * @author tanson lam
 * @createDate 2015年2月16日
 * 
 */
public class MQReceiverTool extends MQListener implements MQReceiver {

    private static final MQReceiverTool instance = new MQReceiverTool();

    public MQReceiverTool() {
        super(MQAdapter.getInstance().getMQReceiver());
    }

    public static MQReceiverTool getInstance() {
        return instance;
    }

    @Override
    public void setMessageListener(String queue, MessageListener messageListener)
            throws MQException {
        receiver.setMessageListener(queue, messageListener);
    }

    @Override
    public void setMessageListener(Session session, String queue, MessageListener messageListener)
            throws MQException {
        receiver.setMessageListener(session, queue, messageListener);
    }

    @Override
    public Map<String, Class<? extends MessageListener>> getMessageListener() {
        Map<String, Class<? extends MessageListener>> listeners = new HashMap<String, Class<? extends MessageListener>>();
        // listeners.put("FirstQueue", MessageProcess.class);

        // 解析配置文件中消息队列监听器
        Enumeration<?> enumeration = Jws.configuration.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString().trim();
            if (key.startsWith("messagequeue.listener.") && !key.endsWith(".open") && !key.endsWith(".number")) {
                String queue = key.trim().split("\\.")[2].trim();
                String listenerClazzName = Jws.configuration.getProperty(key);
                String listenerOpen = Jws.configuration.getProperty(key+".open", "true").trim();
                if (listenerOpen != null && listenerOpen.equals("true")) {
                    Class listenClazz;
                    try {
                        listenClazz = ClassLoaderUtil.loadClass(listenerClazzName);
                        listeners.put(queue, listenClazz);
                    } catch (ClassNotFoundException e) {
                        Logger.error(e, "listenerClazzName"+listenerClazzName+":"+e.getMessage());
                    }
                }
            }
        }
        return listeners;
    }

}
