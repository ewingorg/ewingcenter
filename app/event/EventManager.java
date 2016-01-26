package event;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jws.Logger;

import com.google.common.eventbus.EventBus;
import com.taobao.mtop4.utils.StringUtil;

/**
 * 事件服务类
 * 
 * @author tanson lam
 * @createDate 2014年12月27日
 * 
 */
public class EventManager {
    /**
     * EventBus是事件处理机制集合。
     */
    private final Map<String, EventBus> eventBusMap = new HashMap<String, EventBus>();

    private static Set<Class<? extends EventListener>> processors = new HashSet<Class<? extends EventListener>>();

    static {
     /*   processors.add(NoneBalanceListener.class);
        processors.add(LowBalanceAlertListener.class);
        processors.add(ExceedJfbBudgetListener.class);*/
    }
    /**
     * 初始化事件管理对象实例
     */
    private final static EventManager singleIns = new EventManager();
    /**
     * 异步消息发送执行者
     */
    private final ExecutorService postExecutor = Executors.newFixedThreadPool(10);

    /**
     * 构造器
     */
    private EventManager() {
        for (Class<? extends EventListener> fundEventProcess : processors) {
            try {
                Constructor<?> contructor = fundEventProcess.getConstructors()[0];
                contructor.setAccessible(true);
                EventListener process = (EventListener) contructor.newInstance();
                EventBus eventBus = new EventBus(process.eventName());
                eventBus.register(process);
                eventBusMap.put(process.eventName(), eventBus);
            } catch (Throwable e) {
                Logger.error(e, "fail to init FundEventManager");
            }
        }
    }

    public void addListener(EventListener eventListener) {
        EventBus eventBus = new EventBus(eventListener.eventName());
        eventBus.register(eventListener);
        eventBusMap.put(eventListener.eventName(), eventBus);
    }

    /**
     * 单例模式
     * 
     * @return
     */
    public static EventManager getManager() {
        return singleIns;
    }

    /**
     * 异步发送时间通知
     * 
     * @param event
     */
    public void AsyncSendEvent(final Event event) {
        if (event == null || StringUtil.isEmpty(event.eventName()))
            return;
        postExecutor.execute(new Runnable() {
            public void run() {
                eventBusMap.get(event.eventName()).post(event);
            }
        });
    }

    /**
     * 异步发送时间通知
     * 
     * @param event
     */
    public void sendEvent(final Event event) {
        eventBusMap.get(event.eventName()).post(event);
    }

}
