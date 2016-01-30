package common.constants;

/**
 * 返回状态响应码
 * 
 * @author uc
 * 
 */
public class ResponseCode {

    /**
     * 成功
     */
    public static final int OK = 2000000;

    /**
     * 重定向
     */
    public static final int REDIRECT = 3000001;

    /**** 系统内部错误码 ***************************************************************************/

    /**
     * 服务端内部异常
     */
    public static final int INTERNAL_ERROR = 5000000;

    /**
     * 未知错误
     */
    public static final int UNKOWN_ERROR = 5000001;

    /**
     * 系统参数无效:参数缺失/参数值无效
     */
    public static final int PARAM_ILLEGAL = 5000002;

    /***
     * 请求不可识别错误异常
     */
    public static final int UNRECOGNIZED_RESPONSE = 5000003;

    /***
     * 请求不是期待错误异常
     */
    public static final int UNEXPECTED_RESPONSE = 5000004;

    /***
     * URL请求、转换实体错误异常
     */
    public static final int BEAN_COVERT_ERROR = 5000005;

}
