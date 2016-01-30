package controllers.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import common.constants.AjaxRespCode;
import common.utils.JsonUtils;

import controllers.BaseApiController;

/**
 * Action校验方法的类
 * 
 * @author chenxg@ucweb.com
 * @createDate 2015年8月26日
 *
 */
public class ValidObjectController extends BaseApiController {

    /**
     * 检查非空，如果空Ajax返回
     * 
     * @author Joeson
     */
    protected static <T> T checkNotNullOfAjax(T obj, String errroMsg) {
        if (null == obj) {
            renderAjax(AjaxRespCode.CODE_ERROR.value, errroMsg);
        }

        return obj;
    }

    /**
     * 检查非空，如果空Msg Html頁面返回
     * 
     * @author Joeson
     */
    protected static <T> T checkNotNullOfMsgHtml(T obj, String errorMsg) {
        return checkNotNullOfMsgHtml(obj, errorMsg, StringUtils.EMPTY);
    }

    /**
     * 检查非空，如果空Msg Html頁面返回
     * 
     * @author Joeson
     */
    protected static <T> T checkNotNullOfMsgHtml(T obj, String errorMsg, String okUrl) {
        if (obj == null) {
            renderMessageHtml(errorMsg, okUrl);
        }

        return obj;
    }

    /**
     * 检查状态是否true，ajax返回结果
     * 
     * @param exp 表达式
     * @param sucMsg 成功提示
     * @param errorMsg 错误提示
     * @author Joeson
     */
    protected static void renderAjax(boolean exp, String sucMsg, String errorMsg) {
        if (exp) {
            renderAjax(AjaxRespCode.CODE_SUC.value, sucMsg);
        } else {
            renderAjax(AjaxRespCode.CODE_ERROR.value, errorMsg);
        }
    }

    /**
     * 检查状态是否true，如果true Ajax返回错误信息
     * 
     * @author Joeson
     */
    protected static void checkFalseOfAjax(boolean exp, String errorMsg) {
        if (exp) {
            renderAjax(AjaxRespCode.CODE_ERROR.value, errorMsg);
        }
    }
    
    /**
     * 检查状态是否true，如果ture Msg Html頁面返回错误信息
     * 
     * @author Joeson
     */
    protected static void checkFalseOfMsgHtml(boolean exp, String msg) {
        if (exp) {
            renderMessageHtml(msg, StringUtils.EMPTY);
        }
    }

    /**
     * 检查状态是否true，如果ture Msg Html頁面返回错误信息
     * 
     * @author Joeson
     */
    protected static void checkFalseOfMsgHtml(boolean exp, String msg, String okUrl) {
        if (exp) {
            renderMessageHtml(msg, okUrl);
        }
    }

    /**
     * 检查是否为true
     * 
     * @author Joeson
     */
    protected static boolean checkState(boolean exp) {
        return exp ? true : false;
    }

    /**
     * Ajax请求返回类,前端js结合 ajax.js使用
     * 
     * @param state 状态码
     * @param msg 信息
     * @author Joeson
     */
    protected static void renderAjax(Integer state, String msg) {
        JsonObject resJson = new JsonObject();
        resJson.addProperty("state", state);
        resJson.addProperty("msg", msg);
        renderText(resJson.toString());
    }

    /**
     * Ajax请求返回类,前端js结合 ajax.js使用
     * 
     * @param state 状态码
     * @param msg 信息
     * @author Joeson
     */
    protected static void renderAjax(Integer state, Map<String, Object> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        JsonObject resJson = JsonUtils.parse(jsonStr);
        resJson.addProperty("state", state);
        renderText(resJson.toString());
    }

    /**
     * 跳转到message html页面
     * 
     * @param message 返回信息
     * @param okUrl 点击确定按钮之后跳转的地址
     * @author Joeson
     */
    protected static void renderMessageHtml(String message, String okUrl) {
        Map<String, Object> contextMap = new HashMap<String, Object>();
        contextMap.put("message", message);
        contextMap.put("okUrl", StringUtils.defaultIfEmpty(okUrl, "javascript:history.back(-1);"));
        renderTemplate("errors/message.html", contextMap);
    }

    protected static void renderMessageHtml(boolean suc, String sucMsg, String errorMsg,
            String okUrl) {
        Map<String, Object> contextMap = new HashMap<String, Object>();
        contextMap.put("message", suc ? sucMsg : errorMsg);
        contextMap.put("okUrl", StringUtils.defaultIfEmpty(okUrl, "javascript:history.back(-1);"));
        renderTemplate("errors/message.html", contextMap);
    }
}
