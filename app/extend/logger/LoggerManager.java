package extend.logger;

/**
 * 日志管理类
 * 
 * @author tanson lam
 * @createDate 2015年3月13日
 * 
 */
public class LoggerManager {
    public static org.apache.log4j.Logger sendjfbListenerLogger = org.apache.log4j.Logger
            .getLogger("jws.sendjfb-listener");
    
    public static org.apache.log4j.Logger sendcheckinjfbListenerLogger = org.apache.log4j.Logger
            .getLogger("jws.sendcheckinjfb-listener");
    
    public static org.apache.log4j.Logger sendactivityjfbListenerLogger = org.apache.log4j.Logger
            .getLogger("jws.sendactivityjfb-listener");

    public static org.apache.log4j.Logger sendmessageListenerLogger = org.apache.log4j.Logger
            .getLogger("jws.sendmessage-listener");
    
    public static org.apache.log4j.Logger querychargejoblogger = org.apache.log4j.Logger
            .getLogger("jws.query-charge-job");
    
    public static org.apache.log4j.Logger jfbsenderLogger = org.apache.log4j.Logger
            .getLogger("jws.jfb-sender");
    
    public static org.apache.log4j.Logger jfbresenderLogger = org.apache.log4j.Logger
            .getLogger("jws.jfb-resender");
    
    public static org.apache.log4j.Logger billingchargeLogger = org.apache.log4j.Logger
            .getLogger("jws.billing-charge");
    
    public static org.apache.log4j.Logger jfbactivityListenerLogger = org.apache.log4j.Logger
            .getLogger("jws.jfbactivity-listener");
    
    public static org.apache.log4j.Logger billingjobLogger = org.apache.log4j.Logger
            .getLogger("jws.billing-job");
    
    public static org.apache.log4j.Logger querydeductjoblogger = org.apache.log4j.Logger
            .getLogger("jws.query-deduct-job");
    
    public static org.apache.log4j.Logger jfbForStopListenerLogger = org.apache.log4j.Logger
            .getLogger("jws.jfbforstop-listener");
    
}
