package ru.yandex.autoschool.zlogger.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alexwyrm on 11/14/14.
 */
public class TimestampFormatter {

    private static String defaultFormat = "yy/M/d 'at' H:mm";

    public static String humanize(Timestamp time) {
        return humanize(time, defaultFormat);
    }

    public static String humanize(Timestamp time, String formatString) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatString);
        Date date = new Date(time.getTime());
        return formatter.format(date);
    }

}
