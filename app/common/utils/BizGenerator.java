package common.utils;

import java.util.Date;
import java.util.UUID;

import jws.Jws;

/**
 * 业务流水工具类
 * 
 * @author tanson lam
 * @createDate 2015年2月12日
 * 
 */
public class BizGenerator {
    /**
     * UC开放平台流水号标识
     */
    private final static String BIZ_PRE_FIX = Jws.configuration.getProperty("billing.bizprefix",
            "UCOPEN");

    /**
     * 生成业务流水号
     * 
     * @return
     */
    public static String generateBizNum() {
        return BIZ_PRE_FIX + "-" + UUID.randomUUID().toString()+DateUtils.DF_SHORT_TIME_FORMAT.format(new Date());
    }

    /**
     * 判断是否合法的业务流水号
     * 
     * @return
     */
    public static Boolean validateBizNum(String bizNum) {
        return bizNum.startsWith(BIZ_PRE_FIX);
    }

    public static String getBizPrefix() {
        return BIZ_PRE_FIX;
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++)
        System.out.println(generateBizNum());
    }
}
