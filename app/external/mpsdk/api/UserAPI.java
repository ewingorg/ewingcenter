package external.mpsdk.api;

import java.util.Collection;
import java.util.List;

import external.mpsdk.vo.api.FollowList;
import external.mpsdk.vo.api.Follower;
import external.mpsdk.vo.api.Follower2;
import external.mpsdk.vo.api.WebAuthorizationDto;
import external.mpsdk.vo.api.WebAuthorizationUserInfo;

/**
 * 微信用户信息接口
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public interface UserAPI {

    /**
     * 设置备注名地址
     */
    static String user_remark = "/user/info/updateremark?access_token=";

    /**
     * 用户列表地址
     */
    static String user_list = "/user/get?access_token=%s&next_openid=%s";

    /**
     * 用户基本信息地址
     */
    static String user_info = "/user/info?access_token=%s&openid=%s&lang=%s";

    /**
     * 批量用户基本信息地址
     */
    static String batch_user_info = "/user/info/batchget?access_token=";
    
    /**
     * 网页授权获取用户基本信息 --第一步：用户同意授权，获取code
     */
    static String web_authorize_1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=123#wechat_redirect";

    /**
     * 网页授权获取用户基本信息 --第二步：通过code换取网页授权access_token
     */
    static String web_authorize_2 = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    
    /**
     * 网页授权获取用户基本信息 --第三步:刷新access_token（如果需要）
     */
    static String web_authorize_3 = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    /**
     * 网页授权获取用户基本信息 --第三步:刷新access_token（如果需要）
     */
    static String web_authorize_4 = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=%s";
    
    /**
     * 设置用户备注名
     * 
     * @param openId
     *            用户标识
     * @param remark
     *            新的备注名,长度必须小于30字符
     * @return true 或 false
     */
    boolean updateRemark(String openId, String remark);

    /**
     * 获取关注用户列表
     * 
     * @param nextOpenId
     *            第一个拉取的OPENID,不填默认从头开始拉取
     * @return 关注列表{@link FollowList}
     */
    FollowList getFollowerList(String nextOpenId);

    /**
     * 获取用户基本信息(包括UnionID机制)
     * 
     * @param openId
     *            用户的标识
     * @param lang
     *            国家地区语言版本,zh_CN 简体,zh_TW 繁体,en 英语
     * @return 关注用户{@link Follower}
     */
    Follower getFollower(String openId, String lang);

    /**
     * 批量获取用户基本信息[最多拉取100条]
     * 
     * @param users
     *            批量用户集合{@link Follower2}
     * @return 关注用户{@link Follower}
     */
    List<Follower> getFollowers(Collection<Follower2> users);
    
    
    /**
     * 网页授权获取用户基本信息- 第一步：用户同意授权
     * 第一步：用户同意授权，获取code,code通过redirect返回，返回接口查看WxAuthorizeApiControllers.getWebAccessTocken
     * 第二步：通过code获取网页授权access_token（此access_token与基础支持的access_token不同），及openId（如果第一步scope=snsapi_base）
     * 第三步：刷新access_token（如果需要）
     * 第四步：拉取用户信息(第一步scope=snsapi_userinfo)
     * 附：检验授权凭证（access_token）是否有效
     * 
     * @param appId 公众号的唯一标识
     * @param redirectUrl 授权后重定向的回调链接地址，请使用urlencode对链接进行处理
     * @param scope 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return
     * @author Joeson
     */
    void getWebAuthorizationCode(String appId,String redirectUrl, String scope,String state);
    
    /**
     * 网页授权获取用户基本信息-第二步：通过code换取网页授权access_token
     * 
     * @param appId 公众号的唯一标识
     * @param appsecret 公众号的appsecret
     * @param code 填写第一步获取的code参数
     * @author Joeson
     */
    WebAuthorizationDto getWebAccessToken(String appId,String secret, String code);
    
    
    /**
     * 网页授权获取用户基本信息-第三步：刷新access_token（如果需要）
     * @param appId 公众号的唯一标识
     * @param grandType 填写为refresh_token
     * @param refercshToken 填写通过access_token获取到的refresh_token参数
     * @return
     * @author Joeson
     */
    WebAuthorizationDto refreshWebAccessToken(String appId);
    
    /**
     * 网页授权获取用户基本信息-第四步：拉取用户信息(需scope为 snsapi_userinfo)
     * @param accessToken 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * @param openId 用户的唯一标识
     * @param lang 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return
     * @author Joeson
     */
    WebAuthorizationUserInfo getWebUserInfo(String accessToken, String openId,String lang);
    
    
}
