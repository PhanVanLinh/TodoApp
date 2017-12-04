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

    public static Calendar getToday(){
        return Calendar.getInstance();
    }

    public static String getCurrentDateInString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_DB_FORMAT, Locale.US);
        return dateFormat.format(getToday().getTime());
    }

    public static String getYesterDateInString() {
        Calendar today = getToday();
        today.add(Calendar.DATE, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_DB_FORMAT, Locale.US);
        return dateFormat.format(today.getTime());
    }
}
