package exception;

/**
 * 查找开发者异常
 * 
 * @author tanson lam
 * @createDate 2015年3月3日
 * 
 */
public class DeveloperNotFoundException extends Exception {

    public DeveloperNotFoundException() {
        super();
    }

    public DeveloperNotFoundException(String message) {
        super(message);
    }

    public DeveloperNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeveloperNotFoundException(Throwable cause) {
        super(cause);
    }
}
