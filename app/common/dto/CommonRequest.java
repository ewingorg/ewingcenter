package common.dto;

import java.io.Serializable;
import java.util.Map;

public class CommonRequest implements Serializable {
    /**
     * 请求id
     */
    public Long id;

    /**
     * 加密方式 （md5)
     */
    public String encrypt;

    /**
     * 签名
     */
    public String sign;

    /**
     * 请求参数
     */
    public Map<String, String> data;

    /**
     * 请求者信息
     */
    public Client client = new Client();

    public class Client implements java.io.Serializable {
        /**
         * 请求者标识(community.9game.cn)
         */
        public String caller;

        /**
         * 其他信息(null)
         */
        public String ex;
    }

}
