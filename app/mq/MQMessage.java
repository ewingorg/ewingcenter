package mq;

import com.google.gson.Gson;

/**
 * 消息队列需要发送的对象需要实现该接口，消息的发送和接收都是json的文本格式。
 * 
 * @author tanson lam
 * @createDate 2015年2月12日
 * 
 */
public abstract class MQMessage<T> {
    
    private static Gson gson = new Gson();
    /**
     * 对象转换成json
     * @return
     */
    public String object2Json() { 
        return gson.toJson(this);
    }
    /**
     * json转换成对象
     * @param json
     * @return
     */
    public T json2Object(String json) {
        return (T) gson.fromJson(json, this.getClass());
    }
}
