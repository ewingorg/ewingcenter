package common.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import common.dto.CommonResponse;

import apifw.common.ResponseJson;

/**
 * 响应帮助类
 * 
 * @author luozj
 * 
 * @date 2013年9月4日
 * 
 */
public class ResponseUtils {

    /**
     * 组装json响应格式-格式0
     * 
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static Map<String, Object> populateSimpleResponseJson(int code, String message) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("code", code);
        jsonMap.put("msg", StringUtils.isBlank(message) ? "Ok" : message);
        return jsonMap;
    }

    /**
     * 组装json响应格式-格式1
     * 
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static Map<String, Object> populateSimpleResponseJson(int code, String message,
            Object data) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("code", code);
        jsonMap.put("msg", StringUtils.isBlank(message) ? "Ok" : message);
        jsonMap.put("data", data);
        return jsonMap;
    }

    /**
     * 组装json响应格式-格式2
     * 
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static Map<String, Object> populateResponseJson(int code, String message, Object data) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Map<String, Object> stateMap = new HashMap<String, Object>();
        stateMap.put("code", code);
        stateMap.put("msg", StringUtils.isBlank(message) ? "Ok" : message);
        jsonMap.put("state", stateMap);
        jsonMap.put("data", data);
        return jsonMap;
    }

    /**
     * 组装json响应格式3
     * 
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static <T> Map<String, Object> populateFileResponseJson(int code, String message, T data) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Map<String, Object> stateMap = new HashMap<String, Object>();
        stateMap.put("code", code);
        stateMap.put("msg", StringUtils.isBlank(message) ? "Ok" : message);
        jsonMap.put("state", stateMap);
        jsonMap.put("data", data);
        jsonMap.put("success", true);
        return jsonMap;
    }

    /**
     * 创建Response错误码与信息响应对象
     * 
     * @param code 错误码
     * @param msg 错误信息
     * @return response对象
     */
    public static CommonResponse createResponse(Integer code, String msg) {
        CommonResponse response = new CommonResponse();
        CommonResponse.State state = new CommonResponse.State(code, msg);
        response.setState(state);
        return response;
    }

    /**
     * 创建Response错误码与信息响应对象
     * 
     * @param <T>
     * 
     * @param code 错误码
     * @param msg 错误信息
     * @return response对象
     */
    public static <T> CommonResponse createResponse(Integer code, String msg, T data) {
        CommonResponse response = new CommonResponse();
        CommonResponse.State state = new CommonResponse.State(code, msg);
        response.setState(state);
        response.data = data;
        return response;
    }

}
