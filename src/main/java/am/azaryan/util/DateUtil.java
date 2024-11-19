package am.azaryan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat SDF_SQL = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat sqlTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat SDF_DATE_TIME_SQL = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static final SimpleDateFormat dateTimeLocalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    private static final SimpleDateFormat SDF_WEB_DATE = new SimpleDateFormat("yyyy-MM-dd");

    public static Date converStringToDate (String date) throws ParseException {
        return SDF_SQL.parse(date);
    }

    public static String convertDateToString (Date date) {
        return SDF_SQL.format(date);
    }
    public static Date fromStringToDate(String dateStr) throws ParseException {
        return SDF.parse(dateStr);
    }

    public static Date fromWebStringToDate(String dateStr) throws ParseException {
        return SDF_WEB_DATE.parse(dateStr);
    }

    public static String fromDateToWebString(Date date) throws ParseException {
        return SDF_WEB_DATE.format(date);
    }

    public static Date fromSqlStringToDate(String dateStr) throws ParseException {
        return SDF_SQL.parse(dateStr);
    }

    public static Date fromSqlStringToDateTime(String dateStr) throws ParseException {
        return SDF_DATE_TIME_SQL.parse(dateStr);
    }

    public static String fromDateToString(Date date) {
        return SDF.format(date);
    }

    public static String fromDateToSqlString(Date date) {
        return SDF_SQL.format(date);
    }

    public static String fromDateToSqlDateTimeString(Date createdAt) {
        return SDF_DATE_TIME_SQL.format(createdAt);
    }

    public static Date sqlStringTimeToDate(String sql) throws ParseException {
        return sqlTimeFormat.parse(sql);
    }

    public static String dateToSqlTimeString(Date date) {
        return sqlTimeFormat.format(date);
    }

    public static Date webTimeStringToDate(String webTime) throws ParseException {
        try {
            return dateTimeLocalFormat.parse(webTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateToWebTimeString(Date date) {
        return dateTimeLocalFormat.format(date);
    }
}
