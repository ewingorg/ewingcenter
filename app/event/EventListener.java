/**
 * 
 */
package event;

import java.util.Map;

import com.google.common.eventbus.Subscribe;

import jws.templates.Template;
import jws.templates.TemplateLoader;

/**
 * 事件监听处理器
 * 
 * @author tanson lam
 * @createDate 2014年12月27日
 * 
 */
public abstract class EventListener {
    /**
     * 定义事件名称
     * @return
     */
    public abstract String eventName();

    /**
     * 事件处理
     */
    @Subscribe
    public abstract void handle(Event event);

}
