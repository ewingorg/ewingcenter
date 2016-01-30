package common.utils;

import jws.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * 
 * 补充jws event log的不足
 * 
 * @author cairf@ucweb.com
 * @createDate 2015年4月17日
 *
 */
public class LogUtil {
    public static void info(String message, Object... args) {
        Logger.info(message, args);
    }
    
    public static void info(String logger, String message, Object... args) {
        if(StringUtils.isEmpty(logger)){
            info(message, args);
            return;
        }
        
        Logger.event(logger, message, args);
    }

    public static void error(Throwable e) {
        Logger.error(e, "exception stack");
    }
    
    public static void error(String logger, Throwable e) {
        if(StringUtils.isEmpty(logger)){
            error(e);
            return;
        }
        
        Logger.error(e, "exception stack");// 也打到jws.log
        info(logger, ExceptionUtils.getFullStackTrace(e));
    }
    
    public static void error(Throwable e, String message, Object... args) {
        Logger.error(e, message, args);
    }
    
    public static void error(String logger, Throwable e, String message, Object... args) {
        if(StringUtils.isEmpty(logger)){
            error(e, message, args);
            return;
        }
        
        info(logger, message, args);
        error(logger, e);
    }
    
    public static void warn(String message, Object... args) {
        Logger.warn(message, args);
    }
    
    public static void warn(String logger, String message, Object... args) {
        if(StringUtils.isEmpty(logger)){
            warn(message, args);
            return;
        }
        
        Logger.warn(message, args);// 也打到jws.log
        info(logger, message, args);
    }

    public static void warn(Throwable e, String message, Object... args) {
        Logger.warn(e, message, args);
    }
    
    public static void warn(String logger, Throwable e, String message, Object... args) {
        if(StringUtils.isEmpty(logger)){
            warn(e, message, args);
            return;
        }
        
        Logger.warn(logger, e, message, args);// 也打到jws.log
        info(logger, message, args);
        info(logger, ExceptionUtils.getFullStackTrace(e));
    }
}
