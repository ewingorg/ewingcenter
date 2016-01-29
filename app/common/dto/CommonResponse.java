package common.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import common.constants.ResponseCode;

/**
 * 统一响应
 * 
 * @author cairf
 *
 * @2014-7-17 下午5:05:44
 * @param <T>
 */
public class CommonResponse<T> {
    public Long id;
    public State state;
    public T data;

    /**
     * Portal界面根据该字段判断是否成功，该字段必须返回
     */
    public boolean success;

    public static class State {

        public Integer code;
        public String msg;

        public State(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String toString() {
            return String.valueOf(this.code);
        }
    }

    public void setState(State state) {
        if (null == state) {
            return;
        }
        this.id = 123456L;
        this.state = state;
        setSuccess();
    }

    public String getStateString() {
        if (null == state) {
            return "";
        }
        return state.toString();
    }

    private void setSuccess() {
        if (ResponseCode.OK == state.code) {
            this.success = true;
        } else {
            this.success = false;
        }
    }

    public String toString() {
        return new ToStringBuilder(this).append("success", this.success)
                .append("code", this.state.code).append("msg", this.state.msg)
                .append("data", this.data).toString();
    }
}
