package exception;

/**
 * Mtop client expcetion.
 * 
 * @author tanson lam
 * @createDate 2015年2月12日
 * 
 */
public class MtopClientInvokeException extends Exception {

    public MtopClientInvokeException() {
        super();
    }

    public MtopClientInvokeException(String message) {
        super(message);
    }

    public MtopClientInvokeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MtopClientInvokeException(Throwable cause) {
        super(cause);
    }
}
