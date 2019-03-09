package logic.yjkbreath.breathData.support;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @Auther fanhanxi
 * @Date 2019/1/3
 * @Description:
 */
public class MyDateUtil {
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date strToDate(String time){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(TIME_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(time);
        return dateTime.toDate();
    }

    public static String dateToStr(Date time){
        DateTime dateTime = new DateTime(time);
        return dateTime.toString(TIME_FORMAT);
    }
}
