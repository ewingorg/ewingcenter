package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jws.Logger;
import jws.mvc.Http;
import jws.mvc.Http.Header;
import jws.mvc.Http.Request;
import jws.mvc.Http.Response;
import jws.mvc.With;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import util.ResponseUtils;
import apifw.common.RequestJson;
import apifw.exception.BusinessException;
import apifw.interceptors.ExceptionHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taobao.mtop4.utils.StringUtil;

import controllers.base.CommonResponse;
import controllers.base.ResponseCode;
import core.json.JsonUtil;
import eu.bitwalker.useragentutils.UserAgent;

@With({ ExceptionHandler.class })
public class BaseController extends BaseApiController {

    protected static final String REQUIRED = "_required";

    protected static ExecutorService executor = Executors.newCachedThreadPool();

    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    protected static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd mm:HH:ss");

    protected static final Gson requestGson = new GsonBuilder().serializeNulls()
            .disableHtmlEscaping().create();

    protected static final Gson responseGson = new GsonBuilder().setPrettyPrinting()
            .serializeNulls().disableHtmlEscaping().create();

    public static void checkRequestUrl() {
        return;
    }

    /**
     * 获取request
     * 
     * @return
     */
    protected static Request getRequest() {
        return jws.mvc.Http.Request.current();
    }

    /**
     * 获取response
     * 
     * @return
     */
    protected static Response getResponse() {
        return jws.mvc.Http.Response.current();
    }

    /**
     * 获取入参值对
     * 
     * @return
     */
    protected static Map<String, String> getParams() {
        return getRequest().params.allSimple();
    }

    /**
     * 将入参封装进clazz对象返回
     * 
     * @param clazz
     * @return
     * @throws Exception
     */
    protected static <T> T getParams(Class<T> clazz) throws Exception {
        T dest = clazz.newInstance();
        try {
            BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
            BeanUtils.copyProperties(dest, getParams());
        } catch (Exception e) {
            Logger.error(e, "解析json格式异常。");
            throw new BusinessException(ResponseCode.UNKOWN_ERROR, "json格式错误。");
        }
        return dest;
    }

    protected static RequestJson getParamJson() {
        return requestGson.fromJson(getParams().get("param"), RequestJson.class);
    }

    protected static <T> T getParamJson(Class<T> clazz) {
        T dest = null;
        RequestJson requestJson = null;
        try {
            String method = Request.current().method;
            if (method.equalsIgnoreCase("POST"))
                requestJson = requestGson.fromJson(getParams().get("body"), RequestJson.class);
            else if (method.equalsIgnoreCase("GET"))
                requestJson = requestGson.fromJson(getParams().get("param"), RequestJson.class);
            Object newObject = clazz.newInstance();
            BeanUtils.populate(newObject, (Map) requestJson.getData());
            return (T) newObject;
        } catch (Exception e) {
            Logger.error(e, "解析json格式异常。");
            throw new BusinessException(ResponseCode.UNKOWN_ERROR, "json格式错误。");
        }
    }

    public static void main(String[] args) {
        String md5 = DigestUtils.md5Hex("123");
        System.out.println(md5);
    }

    /**
     * 取出客户端传递的字符参数
     */
    protected static String get(String key) {
        return getParams().get(key);
    }

    /**
     * 取出客户端传递的Integer类型参数
     * 
     * @param key
     */
    protected static Integer getInteger(String key) {
        return getInteger(key, null);
    }

    /**
     * 取出客户端传递的Integer类型参数
     * 
     * @param key
     * @param defaultValue
     */
    protected static Integer getInteger(String key, Integer defaultValue) {
        String value = get(key);
        if (StringUtils.isNotBlank(value)) {
            try {
                return Integer.valueOf(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("参数" + key + "应该是长整形");
            }
        }
        return defaultValue;
    }

    /**
     * 取出客户端传递的Long类型参数
     */
    protected static Long getLong(String key) {
        return getLong(key, null);
    }

    /**
     * 取出客户端传递的Long类型参数
     * 
     * @param key
     * @param defaultValue
     */
    protected static Long getLong(String key, Long defaultValue) {
        String value = get(key);
        if (StringUtils.isNotBlank(value)) {
            try {
                return Long.valueOf(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("参数" + key + "应该是长整形");
            }
        }
        return defaultValue;
    }

    /**
     * 获取客户端JSON中传递的数组对象，转化为JAVA字符串集合对象，使用“,”号分隔。
     * 
     * @param key 数组参数名称
     * @return 字符串集合对象
     */
    protected static List<String> getStringList(String key) {
        String value = get(key);
        if (null == value) {
            return Collections.emptyList();
        }

        String[] arrValue = value.split(",");
        List<String> ret = new ArrayList<String>(arrValue.length);
        for (int i = 0; i < arrValue.length; i++) {
            ret.add(arrValue[i]);
        }
        return ret;
    }

    /**
     * 获取客户端JSON中传递的数组对象，转化为JAVA字符串集合对象，使用“,”号分隔。
     * 
     * @param key 数组参数名称
     * @return 字符串集合对象
     */
    protected static List<Integer> getIntegerList(String key) {
        String value = get(key);
        if (null == value) {
            return Collections.emptyList();
        }

        String[] arrValue = value.split(",");
        List<Integer> ret = new ArrayList<Integer>(arrValue.length);
        for (int i = 0; i < arrValue.length; i++) {
            ret.add(Integer.valueOf(arrValue[i]));
        }
        return ret;
    }

    /**
     * 检查必填参数，一旦发现为空立即返回错误<br>
     * 支持ajax请求
     * 
     * @param paraName
     * @param paraValue
     */
    protected static <T> void checkParamNull(String paraName, T paraValue) {
        if (null == paraValue) {
            jsonFailed(ResponseCode.PARAM_ILLEGAL, paraName + " is null.");
        }
    }

    /**
     * 检查必须的参数，一旦发现为空则立即返回错误，检查异常后，通过了response对象写入到响应流中。<br>
     * 支持ajax请求
     * 
     * @param obj
     * @param message
     */
    protected static void checkRequired(Object obj, String message) {
        boolean isNullOrEmpty = (null == obj)
                || (obj instanceof String && obj.toString().isEmpty())
                || obj.toString().equalsIgnoreCase("null");
        if (isNullOrEmpty) {
            jsonFailed(ResponseCode.PARAM_ILLEGAL, "缺少必填参数：" + message);
        }
    }

    /**
     * 检查必须的参数，一旦发现为false则立即返回错误，检查异常后，通过了response对象写入到响应流中。<br>
     * 支持ajax请求
     * 
     * @param isTrue
     * @param message
     */
    protected static void checkBoolean(boolean isTrue, String message) {
        if (!isTrue) {
            jsonFailed(ResponseCode.PARAM_ILLEGAL, "参数检查出错：" + message);
        }
    }

    /**
     * 根据正则表达式验证参数<br>
     * 支持ajax请求
     * 
     * @param value
     * @param key
     * @param pattern
     */
    protected static void checkPattern(String value, String key, String expression) {
        Pattern pattern = Pattern.compile(expression);
        if (!pattern.matcher(value).matches()) {
            jsonFailed(ResponseCode.PARAM_ILLEGAL, "参数不合法：" + key);
        }
    }

    /**
     * json格式成功返回
     */
    protected static void jsonSuccess() {
        jsonSuccess(null);
    }

    /**
     * json格式成功返回
     * 
     * @param data
     */
    protected static <T> void outJsonSuccess(T data) {
        jsonSuccess(data, null);
    }

    /**
     * json格式成功返回
     * 
     * @param data
     * @param message
     */
    protected static <T> void jsonSuccess(T data, String message) {
        String msg = StringUtils.isBlank(message) ? "Ok" : message;
        Map<String, Object> jsonMap = ResponseUtils
                .populateResponseJson(ResponseCode.OK, msg, data);
        if (isJsonpRequest()) {
            String json = JsonUtil.tranBean2String(data).toString();
            String jsonpResut = "success_jsonpCallback(" + json + ")";
            renderJSON(jsonpResut);
        } else
            renderJSON(jsonMap);
    }

    /**
     * 失败json格式返回
     * 
     * @param code 状态码
     * @param message 信息
     */
    protected static void jsonFailed(int code, String message) {
        String msg = StringUtils.isBlank(message) ? "error" : message;
        int c = code <= 0 ? ResponseCode.UNKOWN_ERROR : code;
        jsonFailed(new CommonResponse.State(c, msg));
    }

    private static boolean isJsonpRequest() {
        if (!StringUtil.isEmpty(request.current().params.get("callbackparam")))
            return true;
        return false;
    }

    /**
     * 失败josn格式返回
     * 
     * @param state CommonResponse的状态结构体（状态码及信息）
     */
    protected static void jsonFailed(CommonResponse.State state) {
        if (state == null) {
            state = new CommonResponse.State(ResponseCode.UNKOWN_ERROR, "error");
        }
        Map<String, Object> jsonMap = ResponseUtils.populateResponseJson(state.code, state.msg,
                null);
        if (isJsonpRequest()) {
            String json = JsonUtil.tranBean2String(jsonMap).toString();
            String jsonpResut = "success_jsonpCallback(" + json + ")";
            renderJSON(jsonpResut);
        } else
            renderJSON(jsonMap);
    }

    /**
     * 页面渲染
     * 
     * @param fileName 渲染的html文件名，包含后缀
     * @param type 渲染的页面类型 1 为普通开发者页面，2为管理员的页面
     */
    protected static void render(String fileName, String type) {
        renderTemplate(type + "/" + fileName);
    }

    /**
     * 页面渲染
     * 
     * @param fileName 渲染的html文件名，包含后缀
     * @param type 渲染的页面类型 1 为普通开发者页面，2为管理员的页面
     */
    protected static void render(String fileName, String type, Map<String, Object> args) {
        renderTemplate(type + "/" + fileName, args);
    }

    /**
     * 取得客户端的ip地址
     * 
     * @return
     */
    protected static String getClientIp() {
        Map<String, Header> headers = Request.current().headers;
        String ip = StringUtils.EMPTY;
        if (StringUtils.isEmpty(ip) && headers.containsKey("x-forwarded-for")) {
            Header forward = headers.get("x-forwarded-for");
            ip = forward != null ? StringUtils.split(
                    StringUtils.defaultIfEmpty(forward.value(), ","), ",")[0] : StringUtils.EMPTY;
        }
        if (StringUtils.isEmpty(ip) && headers.containsKey("x-real-ip")) {
            Header real = headers.get("x-real-ip");
            ip = real != null ? real.value() : StringUtils.EMPTY;
        }
        if (StringUtils.isEmpty(ip) && StringUtils.isNotEmpty(Request.current().remoteAddress)) {
            ip = Request.current().remoteAddress;
        }
        return ip;
    }

    /**
     * 取得浏览器版本信息
     * 
     * @return
     */
    protected static String getBroswerInfo() {
        Map<String, Header> headers = Request.current().headers;
        String browser = "unknow";
        if (headers.containsKey("user-agent")) {
            String ua = headers.get("user-agent") != null ? headers.get("user-agent").value()
                    : StringUtils.EMPTY;
            String uc = getUcBrowser(ua);
            if (StringUtils.isEmpty(uc)) {
                browser = getOtherBrowser(ua);
            } else {
                browser = uc;
            }
        }
        return browser;
    }

    /**
     * UC浏览器<br>
     * Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 UBrowser/5.0.966.41 Safari/537.36
     * 
     * @param userAgentString
     * @return
     */
    private static String getUcBrowser(String userAgentString) {
        if (StringUtils.isEmpty(userAgentString))
            return null;
        final String regx = "UBrowser\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)";
        Matcher matcher = Pattern.compile(regx).matcher(userAgentString);
        if (matcher.find()) {
            String fullVersionString = matcher.group(1);// 全版本号
            // String majorVersion = matcher.group(2);// 主版本
            // String minorVersion = "0";// 小版本号
            // if (matcher.groupCount() > 2)
            // minorVersion = matcher.group(3);
            return "UBrowser " + fullVersionString;
        }
        return null;
    }

    /**
     * 其它浏览器
     * 
     * @param userAgentString
     * @return
     */
    private static String getOtherBrowser(String userAgentString) {
        if (StringUtils.isEmpty(userAgentString))
            return null;
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        String oth = userAgent.getBrowser().getName() + " "
                + userAgent.getBrowserVersion().getVersion();
        return oth;
    }

    /**
     * 操作系统
     * 
     * @param userAgentString
     * @return
     */
    protected static String getOperatingSystem() {
        Map<String, Header> headers = Request.current().headers;
        String os = "未知";
        if (headers.containsKey("user-agent")) {
            String ua = headers.get("user-agent") != null ? headers.get("user-agent").value()
                    : StringUtils.EMPTY;
            if (StringUtils.isEmpty(ua))
                return os;
            UserAgent userAgent = UserAgent.parseUserAgentString(ua);
            os = userAgent.getOperatingSystem().getName();
        }
        return os;
    }

    public static String getController() {
        Request req = Http.Request.current();
        if (req != null) {
            return req.controller;
        } else {
            return StringUtils.EMPTY;
        }
    }

    public static String getAction() {
        Request req = Http.Request.current();
        if (req != null) {
            return StringUtils.replace(req.action, getController() + ".", StringUtils.EMPTY);
        } else {
            return StringUtils.EMPTY;
        }
    }

}