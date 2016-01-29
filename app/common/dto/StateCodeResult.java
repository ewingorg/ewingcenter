package common.dto;


/**
 * API执行状态结果
 * 类/接口注释
 * 
 * @author wenxy@ucweb.com
 * @createDate 2014年8月28日
 *
 */
public class StateCodeResult extends RuntimeException {

    public final int code;
    public final String message;
    public final Object data;

    private StateCodeResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static void prompt(int code) {
        throw new StateCodeResult(code, null, null);
    }

    public static void prompt(int code, String message) {
        throw new StateCodeResult(code, message, null);
    }

    public static void prompt(int code, String message, Object data) {
        throw new StateCodeResult(code, message, data);
    }
}
