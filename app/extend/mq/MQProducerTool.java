package extend.mq;

import java.util.concurrent.TimeUnit;

import javax.jms.Session;

import jws.mq.MQException;

/**
 * 消息队列的发送工具类
 * 
 * @author tanson lam
 * @createDate 2015年2月16日
 * 
 */
public class MQProducerTool implements MQProducer {

    private static final MQProducerTool instance = new MQProducerTool();
    private final MQProducer producer;
    private final int MAX_ERROR_NETWORK_TYR = 3;
    private final long ERROR_SLEEP_TIME = 10;

    private MQProducerTool() {
        producer = MQAdapter.getInstance().getMQProducer();
    }

    public static MQProducerTool getInstance() {
        return instance;
    }

    @Override
    public <T extends MQMessage<T>> void sendQueueMessage(String queue, T message) {
        try {
            producer.sendQueueMessage(queue, message);
        } catch (Throwable e) {
            int errTime = 0;
            boolean ret = false;
            while (!ret) {
                try {
                    producer.sendQueueMessage(queue, message);
                    ret = true;
                    break;
                } catch (Throwable e1) {
                    errTime++;
                }
                if (errTime >= MAX_ERROR_NETWORK_TYR)
                    break;
                try {
                    TimeUnit.MILLISECONDS.sleep(ERROR_SLEEP_TIME);
                } catch (InterruptedException e1) {

                }
            }
            if (!ret)
                throw new MQException(e.getMessage(), e);
        }
    }

    @Override
    public void sendQueueMessage(String queue, String message) {
        try {
            producer.sendQueueMessage(queue, message);
        } catch (Throwable e) {
            int errTime = 0;
            boolean ret = false;
            while (!ret) {
                try {
                    producer.sendQueueMessage(queue, message);
                    ret = true;
                    break;
                } catch (Throwable e1) {
                    errTime++;
                }
                if (errTime >= MAX_ERROR_NETWORK_TYR)
                    break;
                try {
                    TimeUnit.MILLISECONDS.sleep(ERROR_SLEEP_TIME);
                } catch (InterruptedException e1) {

                }
            }
            if (!ret)
                throw new MQException(e.getMessage(), e);
        }

    }

    @Override
    public <T extends MQMessage<T>> void sendQueueMessage(Session session, String queue, T message) {
        try {
            producer.sendQueueMessage(session, queue, message);
        } catch (Throwable e) {
            int errTime = 0;
            boolean ret = false;
            while (!ret) {
                try {
                    producer.sendQueueMessage(session, queue, message);
                    ret = true;
                    break;
                } catch (Throwable e1) {
                    errTime++;
                }
                if (errTime >= MAX_ERROR_NETWORK_TYR)
                    break;
                try {
                    TimeUnit.MILLISECONDS.sleep(ERROR_SLEEP_TIME);
                } catch (InterruptedException e1) {

                }
            }
            if (!ret)
                throw new MQException(e.getMessage(), e);
        }
    }

    @Override
    public void sendQueueMessage(Session session, String queue, String message) {
        try {

            producer.sendQueueMessage(session, queue, message);
        } catch (Throwable e) {
            int errTime = 0;
            boolean ret = false;
            while (!ret) {
                try { 
                    producer.sendQueueMessage(session, queue, message);
                    ret = true;
                    break;
                } catch (Throwable e1) {
                    errTime++;
                }
                if (errTime >= MAX_ERROR_NETWORK_TYR)
                    break;
                try {
                    TimeUnit.MILLISECONDS.sleep(ERROR_SLEEP_TIME);
                } catch (InterruptedException e1) {

                }
            }
            if (!ret)
                throw new MQException(e.getMessage(), e);
        }
    }

}
