package toong.vn.bebetter.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by PhanVanLinh on 12/3/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class DateTimeUtil {
    public static String DATE_TIME_DB_FORMAT = "MMM-dd-yyyy";

    public static Calendar getToday() {
        return Calendar.getInstance();
    }

    private Calendar getPreviousDay(Calendar calendar) {
        calendar.add(Calendar.DATE, -1);
        return calendar;
    }

    private Calendar getNextDay(Calendar calendar) {
        calendar.add(Calendar.DATE, 1);
        return calendar;
    }

    public static String getTodayInString() {
        return getDateInString(getToday());
    }

    public static String getYesterdayInString() {
        Calendar yesterday = getToday();
        yesterday.add(Calendar.DATE, -1);
        return getDateInString(yesterday);
    }

    public static String getDateInString(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_DB_FORMAT, Locale.US);
        return dateFormat.format(calendar.getTime());
    }
}
