package exception;
/**
 * 
 * 计费异常
 * 
 * @author tanson lam
 * @createDate 2015年3月20日
 *
 */
public class BillingException extends Exception {

    public BillingException() {
        super();
    }

    public BillingException(String message) {
        super(message);
    }

    public BillingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BillingException(Throwable cause) {
        super(cause);
    }
}
