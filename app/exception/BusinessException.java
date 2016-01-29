package exception;

import common.constants.ResponseCode;


/**
 * 业务错误异常。
 */
public class BusinessException extends RuntimeException {
    /**
     * 业务CODE码，详情见 {@link common.constants.uc.common.constants.open.base.ResponseCode}。
     */
    private int code;

    public BusinessException() {
        super();
        setErrorCode(ResponseCode.UNKOWN_ERROR);
    }

    
    public BusinessException(Throwable cause) {
        super(cause);
        setErrorCode(ResponseCode.UNKOWN_ERROR);
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
        setErrorCode(ResponseCode.UNKOWN_ERROR);
    }
    
    public BusinessException(Throwable cause, int errorCode) {
        super(cause);
        setErrorCode(errorCode);
    }

    public BusinessException(int errorCode, String message) {
        super(message);
        setErrorCode(errorCode);
    }

    public BusinessException(int errorCode, String message, Object... args) {
        super(String.format(message, args));
        setErrorCode(errorCode);
    }

    public int getErrorCode() {
        return code;
    }

    public void setErrorCode(int errorCode) {
        this.code = errorCode;
    }

}
