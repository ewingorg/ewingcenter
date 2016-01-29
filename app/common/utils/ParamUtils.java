package common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jws.Logger;

import org.apache.commons.lang.StringUtils;

/**
 * Desc:参数处理工具类 Author: xiewq < xiewq@ucweb.com > Create: 2012-6-19 Copyright © 2004 - 2012 UC Mobile Ltd. All Rights Reserved
 */
public class ParamUtils {
    private static final String ELLIPSIS = "****";// 4个星号省略手机号码中间四位

    static final DateFormat DATATIME_FORMAT = new SimpleDateFormat("yyyy年M月d日 HH:mm:ss");

    /**
     * 
     * @desc 将Map中的数据组装成url
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String mapToUrl(Map<String, String> params, String charset) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String key : params.keySet()) {
            String value = params.get(key);

            if (isFirst) {
                isFirst = false;
            } else {
                sb.append("&");
            }

            if (value != null) {
                sb.append(key + "=" + (charset == null ? value : URLEncoder.encode(value, charset)));
            } else {
                sb.append("&" + key + "=");
            }
        }
        return sb.toString();
    }

    /**
     * @desc 获得按Map键升序排序的字符串 如a=88&f=22&t=uu 或 a=88f=22t=uu
     * @param params
     * @param notIn 不需要计算的key
     * @param withChar 返回的字符串是否包含“&”字符
     * @return
     */
    public static <T>  String getSortStrFromMap(Map<String, T> params, String[] notIn, boolean withChar) {
        StringBuilder content = new StringBuilder();

        // 按照key做排序
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);

            if (notIn != null && Arrays.asList(notIn).indexOf(key) >= 0)
                continue;
 
            String value = String.valueOf(params.get(key)); 

            if (withChar == true) {
                if (value != null) {
                    content.append((index == 0 ? "" : "&") + key + "=" + value);
                } else {
                    content.append((index == 0 ? "" : "&") + key + "=");
                }
            } else {
                if (value != null) {
                    content.append(key + "=" + value);
                } else {
                    content.append(key + "=");
                }
            }
            index++;
        }
        return content.toString();
    }

    /**
     * 把参数map转换成<String, String>格式
     * 
     * @param param
     */
    public static Map<String, String> formatParamMap(Map<String, Object> param) {
        Map<String, String> formatMap = new HashMap();
        for (Iterator<String> i = param.keySet().iterator(); i.hasNext();) {
            String key = i.next();
            Object value = param.get(key);
            String val = value == null ? "" : value.toString();
            formatMap.put(key, val);
        }
        return formatMap;
    }

    /**
     * 
     * @desc 邮件格式检查 合法E-mail地址： 1.必须包含一个并且只有一个符号“@” 2.第一个字符不得是“@”或者“.” 3.不允许出现“@.”或者.@ 4.结尾不得是字符“@”或者“.” 5.允许“@”前的字符中出现“＋” 6.不允许“＋”在最前面，或者“＋@”
     * @param email
     * @return
     */
    public static boolean emailAuthentication(String email) {
        if (StringUtils.isEmpty(email)) {
            return true;
        }
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(check);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 
     * @desc 判断是否为正整数（非0）
     * @param str
     * @return
     */
    public static boolean digitalAuthentication(String str) {
        if (StringUtils.isEmpty(str)) {
            return true;
        }
        String check = "^+?[1-9][0-9]*$";
        Pattern p = Pattern.compile(check);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 
     * @desc 长度校验
     * @param str
     * @return
     */
    public static boolean lengthAuthentication(String str, Integer maxlenth) {
        if (StringUtils.isEmpty(str)) {
            return true;
        }
        return str.length() <= maxlenth;
    }

    /**
     * 
     * @desc 显示手机号，“前四后三”——即只显示前四位，后三位，隐藏中间4位：例子：1862****753。
     * @return
     */
    public static String formatMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return mobile;
        }
        if (mobile.length() < 4) {
            return mobile;
        }
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(mobile.substring(0, 4));
            sb.append(ELLIPSIS);
            sb.append(mobile.substring(8, 11));
        } catch (Exception e) {
            Logger.error(e, e.getMessage());
            return "";
        }
        return sb.toString();
    }

    /**
     * 
     * @desc 密保邮箱@前缀、前两位明文显示，后面用4个星号“****”代替；
     * @return
     */
    public static String formatEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return email;
        }
        String str1 = "";
        String str2 = "";
        try {
            String[] emailArr = email.split("@");
            str1 = emailArr[0].substring(0, 2);
            str2 = emailArr[1];
        } catch (Exception e) {
            // handle exception
            Logger.error(e, e.getMessage());
            return "";
        }
        return str1 + "****@" + str2;
    }

    /**
     * 
     * @desc 将html中的< >分别替换为&lt; &gt;
     * @param html
     * @return
     */
    public static String html2Xml(String html) {
        if (StringUtils.isEmpty(html)) {
            return html;
        }
        html = html.replaceAll("<", "&lt;");
        html = html.replaceAll(">", "&gt;");
        return html;
    }

    /**
     * @desc 外链接的处理
     * @param content
     * @return
     */
    public static String replaceOutlink4Content(String content) {
        if (StringUtils.isEmpty(content)) {
            return content;
        }
        String regex = "(www.|http:)([A-Za-z0-9_/.])*(.com|.cn)";
        /* 取得同样值的另外一个字符串 */
        String newString = new String(content);
        int posStart = 0;
        int posEnd = 0;
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        String aimStr = "";
        String newAimStr = "***";

        while (matcher.find()) {
            posStart = matcher.start();
            posEnd = matcher.end();
            aimStr = content.substring(posStart, posEnd);
            // 如果是以下的几种链接，则不用屏蔽
            String compareStr = aimStr.toLowerCase();
            if (compareStr.indexOf("9game.cn") > 0) {
                continue;
            } else if (compareStr.indexOf("ucweb.com") > 0) {
                continue;
            } else if (compareStr.indexOf("uc.cn") > 0) {
                continue;
            }
            newString = newString.replace(aimStr, newAimStr);
        }

        return newString;
    }

    /**
     * @desc 运营对内容的格式要求和内容输出格式的要求处理
     * @param content
     * @return
     */
    public static String ContentRestriction(String content) {
        if (StringUtils.isEmpty(content)) {
            return content;
        }
        content = html2Xml(content);
        content = replaceOutlink4Content(content);
        return content;
    }

    /**
     * @desc 判断字符串是否是超长的
     * @param text 要判断的字符串，其中汉字是当成2个字节长度（GBK码）处理
     * @param maxLength 最大的长度(汉字长度的2倍)，如50个汉字，则maxLength应为100
     * @return
     */
    public static Boolean isOverLength(String text, int maxLength) {
        if (StringUtils.isEmpty(text))
            return false;

        try {
            return (text.getBytes("gbk").length > maxLength * 2);
        } catch (UnsupportedEncodingException e) {
            Logger.error(e, e.getMessage());
        }
        return false;
    }

    /**
     * @desc 将格式如 a=88&f=22&t=uu装换为map("a",88);
     * @param resp
     * @return
     */
    public static Map urlParamToMap(String resp) {
        if (resp == null) {
            return null;
        }
        Map respMap = new HashMap();
        String[] respArrStr = resp.split("&");
        for (String str : respArrStr) {
            if (StringUtils.isNotEmpty(str)) {
                String[] paramArrStr = str.split("=");
                if (paramArrStr != null && paramArrStr.length > 1) {
                    respMap.put(paramArrStr[0], paramArrStr[1]);
                }
            }

        }
        return respMap;
    }

    /**
     * 源字符串是否存在dest字符串
     * 
     * @param src 用短号分割的字符串
     * @param dest 是否为src中的一个配置项目
     * @return
     */
    public static boolean isExisit(String src, String dest) {
        if (StringUtils.isEmpty(src) || StringUtils.isEmpty(dest)) {
            return false;
        }
        String[] tmpArray = src.split(",");
        for (String s : tmpArray) {
            if (s.equals(dest)) {
                return true;
            }
        }
        return false;
    }

    /**
     * byte转MB字符串
     * 
     * @param l
     * @return e.g.: 17.49M
     */
    public static String bytesToMB(Long l) {
        double t = Math.round(l / 10000) / 100.0;
        return String.valueOf(t + "M");
    }

    /**
     * Long to datetime
     * 
     * @param l e.g.: 1406634413546L
     * @return e.g.: yyyy年M月d日 HH:mm:ss
     */
    public static String toDatetime(Long l) {
        return DATATIME_FORMAT.format(new Date(l));
    }
}
