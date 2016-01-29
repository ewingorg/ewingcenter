/**
 * 
 */
package extend.mq;

/**
 * MQ异常类
 * 
 * @author tanson lam
 * @createDate 2015年2月10日
 * 
 */
class MQException extends Exception {

    public MQException() {
        super();
    }

    public MQException(String message) {
        super(message);
    }

    public MQException(String message, Throwable cause) {
        super(message, cause);
    }

    public MQException(Throwable cause) {
        super(cause);
    }
}
