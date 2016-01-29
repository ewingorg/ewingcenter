package external.wxpaysdk.protocol;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import common.utils.ParamUtils;

/**
 * 微信支付接口的签名方法<br/>
 * 
 * <pre>
 * 举例: 
 * 假设传送的参数如下：
 * appid： wxd930ea5d5a258f4f
 * mch_id： 10000100
 * device_info： 1000
 * body： test
 * nonce_str： ibuaiVcKdpRxkhJA<br/>
 * <b>注意：</b>
 * 微信支付API接口协议中包含字段nonce_str，主要保证签名不可预测。我们推荐生成随机数算法如下：调用随机数函数生成，将得到的值转换为字符串。
 * 
 * 第一步：对参数按照key=value的格式，并按照参数名ASCII字典序排序如下： 
 * stringA="appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";
 * 
 * 第二步：拼接API密钥：
 * stringSignTemp="stringA&key=192006250b4c09247ec02edce69f6a2d" 
 * sign=MD5(stringSignTemp).toUpperCase()="9A0A8659F005D6984697E2CA0A9CF3B7"
 * 最终得到最终发送的数据： 
 * <xml> 
 * <appid>wxd930ea5d5a258f4f</appid> 
 * <mch_id>10000100</mch_id> 
 * <device_info>1000<device_info> 
 * <body>test</body> 
 * <nonce_str>ibuaiVcKdpRxkhJA</nonce_str> 
 * <sign>9A0A8659F005D6984697E2CA0A9CF3B7</sign> 
 * <xml>
 * </pre>
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月23日
 *
 */
public class SignGenerator {

    public String getSign(Map<String, Object> params) {
        // @TODO 配置秘钥
        String key = null;

        return getSign(params, key);
    }

    /**
     * 获取签名
     * 
     * @param params
     * @param key key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
     * @return
     * @author Joeson
     */
    private String getSign(Map<String, Object> params, String key) {
        if (MapUtils.isEmpty(params) || StringUtils.isEmpty(key)) {
            return StringUtils.EMPTY;
        }

        String stringA = ParamUtils.getSortStrFromMap(params, null, true);
        String stringSignTemp = stringA + "&" + key;
        return StringUtils.upperCase(DigestUtils.md5Hex(stringSignTemp));
    }

}
