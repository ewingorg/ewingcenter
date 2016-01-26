package exception;

/**
 * 非法的API请求参数
 * 类/接口注释
 * 
 * @author wenxy@ucweb.com
 * @createDate 2014年8月28日
 *
 */
public class IllegalParamException extends RuntimeException {

    public IllegalParamException() {
        super();
    }

    public IllegalParamException(String msg) {
        super(msg);
    }
}
