package common.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 网址工具
 * 
 * @author cairf
 * 
 * @2014-8-15 下午2:40:32
 */
public class UrlUtils {

	/**
	 * url编码
	 * 
	 * @param url
	 * @return
	 */
	public static String encodeURL(String url) {
		if (StringUtils.isBlank(url)) {
			url = StringUtils.EMPTY;
		}
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
	}

	/**
	 * url解码
	 * 
	 * @param url
	 * @return
	 */
	public static String decodeURL(String url) {
		if (StringUtils.isBlank(url)) {
			url = StringUtils.EMPTY;
		}
		try {
			return URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
	}

	/**
	 * 拼凑url
	 * 
	 * @param scheme
	 * @param serverName
	 * @param serverPort
	 * @param requestURI
	 * @param queryString
	 * @return
	 */
	public static String buildFullRequestUrl(String scheme, String serverName, int serverPort, String requestURI, String queryString) {
		scheme = scheme.toLowerCase();
		StringBuilder url = new StringBuilder();
		url.append(scheme).append("://").append(serverName);
		if ("http".equals(scheme)) {
			if (serverPort != 80) {
				url.append(":").append(serverPort);
			}
		} else if ("https".equals(scheme)) {
			if (serverPort != 443) {
				url.append(":").append(serverPort);
			}
		}
		url.append(requestURI);
		if (queryString != null) {
			url.append("?").append(queryString);
		}
		return url.toString();
	}

	/**
	 * 拼凑url
	 * 
	 * @param servletPath
	 * @param requestURI
	 * @param contextPath
	 * @param pathInfo
	 * @param queryString
	 * @return
	 */
	public static String buildRequestUrl(String servletPath, String requestURI, String contextPath, String pathInfo, String queryString) {
		StringBuilder url = new StringBuilder();
		if (StringUtils.isNotBlank(servletPath)) {
			servletPath = servletPath.substring(1);
			url.append(servletPath);
			if (pathInfo != null) {
				url.append(pathInfo);
			}
		} else {
			url.append(requestURI.substring(contextPath.length()));
		}
		if (queryString != null) {
			queryString = StringUtils.split(queryString, "&")[0];
			url.append("?").append(queryString);
		}
		return url.toString();
	}

	/**
	 * 是否有效跳转地址(绝对地址或相对地址)
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isValidRedirectUrl(String url) {
		return isRelativePath(url) || isAbsoluteUrl(url);
	}

	/**
	 * 是否是相对路径（以/或./或../开头）
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isRelativePath(String url) {
		return url != null && (url.startsWith("/") || url.startsWith("./") || url.startsWith("../"));
	}

	/**
	 * 是否是绝对地址(以协议开头)
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isAbsoluteUrl(String url) {
		final Pattern ABSOLUTE_URL = Pattern.compile("\\A[a-z.+-]+://.*", Pattern.CASE_INSENSITIVE);
		return ABSOLUTE_URL.matcher(url).matches();
	}

	/**
	 * url连接
	 * 
	 * @param baseUrl
	 * @param relativeUrl
	 * @return
	 */
	public static String concat(String absoluteUrl, String relativePath) {
		String parseUrl = StringUtils.EMPTY;
		if (!isAbsoluteUrl(absoluteUrl))
			return parseUrl;
		if (StringUtils.isEmpty(relativePath))
			return absoluteUrl;
		try {
			parseUrl = new URL(new URL(absoluteUrl), relativePath).toString();
		} catch (Exception e) {
			parseUrl = absoluteUrl + "/" + relativePath;
		}
		return parseUrl;
	}

	/**
	 * 匹配一级域名
	 * 
	 * @param url
	 * @return
	 */
	public static String getBaseDomain(String url) {
		String baseDomain = StringUtils.EMPTY;
		if (!isAbsoluteUrl(url)) {
			return baseDomain;
		}
		baseDomain = StringUtils.lowerCase(url);
		Matcher m = Pattern.compile("^http://[^/]+").matcher(baseDomain);
		while (m.find()) {
			baseDomain = m.group();
		}
		return baseDomain;
	}

	/**
	 * 是否同一个站点
	 * 
	 * @param site1
	 * @param site2
	 * @return
	 */
	public static boolean isSameSite(String site1, String site2) {
		return StringUtils.equalsIgnoreCase(StringUtils.removeEnd(getBaseDomain(site1), ":80"), StringUtils.removeEnd(getBaseDomain(site2), ":80"));
	}

	public static void main(String[] args) {
		String url = "http://www.baidu.com/";
		String url1 = "http://www.baidu.com";
		String url2 = "/index.html";
		String url3 = "http://www.aaa.com/bbb//123/345.htm";
		String url4 = "http://www.aaa.com:8089/ccc//123/345.htm";
		System.out.println(isAbsoluteUrl(url) ? url : "http://www.baidu.com/" + url);
		System.out.println(isAbsoluteUrl(url2) ? url2 : "http://www.baidu.com/" + url2);
		System.out.println(getBaseDomain(url3));
		System.out.println(isSameSite(url3, url4));
		System.out.println(concat(url, url2));
		System.out.println(concat(url1, url2));
		System.out.println(concat(url3, url2));
		System.out.println(concat(url3, url4));
	}
}
