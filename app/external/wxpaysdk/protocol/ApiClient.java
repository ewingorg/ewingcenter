package external.wxpaysdk.protocol;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.security.KeyStore;
import java.util.Map;

import javax.net.ssl.SSLContext;

import jws.Logger;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 微信支付请求客户端工具
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月24日
 *
 */
public class ApiClient {
    private static SignGenerator signGenerator = new SignGenerator();
    
    public static <T> T post(String url, Map<String,Object> params, Class<T> clazz){
        String content = post(url, params);
        if(StringUtils.isEmpty(content)){
            return null;
        }

        T t = null;
        try {
            t = XmlMsgHelper.toObject(content, clazz);
        } catch (Exception e) {
            Logger.error(e, e.getMessage());
        }
        
        return t;
    }
    
    public static String post(String url, Map<String,Object> params){
        if(StringUtils.isEmpty(url) || MapUtils.isEmpty(params)){
            return StringUtils.EMPTY;
        }
        
        String sign = signGenerator.getSign(params);
        params.put("sign", sign);
        return post(url, XmlMsgHelper.map2Xml(params));
    }
    
    public static String post(String url,String params){
        //@TODO 配置证书文件以及商户ID
        File certOfP12 = null;
        String clientId = null;
        
        try {
            return post(certOfP12, clientId, url, params);
        } catch (Exception e) {
            Logger.error(e, e.getMessage());
            return StringUtils.EMPTY;
        }
    }

    /**
     * post 支持wx支付安全
     * @param certOfP12 p12证书文件
     * @param clientId 商户ID
     * @throws Exception
     * @author Joeson
     * @return
     */
    private static String post(File certOfP12, String clientId, String url, String params)
            throws Exception {
        // 指定读取证书格式为PKCS12
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        // 读取本机存放的PKCS12证书文件
        FileInputStream instream = new FileInputStream(certOfP12);
        try {
            // 指定PKCS12的密码(商户ID)
            keyStore.load(instream, clientId.toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, clientId.toCharArray()).build();
        // 指定TLS版本
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,
                new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        // 设置httpclient的SSLSocketFactory
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            HttpUriRequest post = RequestBuilder.post().setUri(new URI(url))
                    .setEntity(new StringEntity(params)).build();

            CloseableHttpResponse response = httpclient.execute(post);
            HttpEntity entity = response.getEntity();
            String content = IOUtils.toString(entity.getContent());
            EntityUtils.consume(entity);

            Logger.info(String.format("url[%s],clientId[%s], content[%s]", url, clientId, content));
            return content;
        } finally {
            httpclient.close();
        }
    }
}
