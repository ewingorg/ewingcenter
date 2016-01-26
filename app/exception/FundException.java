package exception;

/**
 * 资金处理异常
 * 
 * @author tanson lam
 * @createDate 2015年3月9日
 * 
 */
public class FundException extends RuntimeException {

    public FundException() {
        super();
    }

    public FundException(String message) {
        super(message);
    }

    public FundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FundException(Throwable cause) {
        super(cause);
    }
}
