package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tanson lam 2014年9月3日
 *
 */
public class DateUtil {
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DAY_FORMAT = "yyyy-MM-dd";
    private static final String SHORT_DAY_FORMAT = "yyyyMMdd";
    private static final String SHORT_TIME_FORMAT = "yyyyMMddHHmmss";
    private static final String INVALID_TIME = "0000-00-00 00:00:00";
    public static final SimpleDateFormat DF_TIME_FORMAT = new SimpleDateFormat(TIME_FORMAT);
    public static final SimpleDateFormat DF_DAY_FORMAT = new SimpleDateFormat(DAY_FORMAT);
    public static final SimpleDateFormat DF_SHORT_DAY_FORMAT = new SimpleDateFormat(
            SHORT_DAY_FORMAT);
    public static final SimpleDateFormat DF_SHORT_TIME_FORMAT = new SimpleDateFormat(
            SHORT_TIME_FORMAT);

    public static Date formatDate(String dateStr) throws ParseException {
        if (INVALID_TIME.contains(dateStr))
            return new Date();
        if (dateStr.length() == TIME_FORMAT.length()) {
            return new SimpleDateFormat(TIME_FORMAT).parse(dateStr);
        } else if (dateStr.length() == DAY_FORMAT.length()) {
            return new SimpleDateFormat(DAY_FORMAT).parse(dateStr);
        } else {
            return new Date();
        }

    }

    public static long getNowDaytime() {
        try {
            return DF_DAY_FORMAT.parse(DF_DAY_FORMAT.format(new Date())).getTime();
        } catch (Exception e) {
        }
        return 0l;
    }

    public static Date convert(Date d, SimpleDateFormat simpleDateFormat) throws ParseException {
        try {
            return simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        } catch (ParseException e) {
            throw e;
        }
    }

    public static long getTime(String dateStr) throws ParseException {
        return formatDate(dateStr).getTime();
    }

}
