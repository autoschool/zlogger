package ru.yandex.autoschool.zlogger.utils;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static ru.yandex.autoschool.zlogger.utils.TimestampFormatter.humanize;

/**
 * Created by alexwyrm on 11/15/14.
 */
public class TimestampFormatterTest {

    private long testLongTime = 43200000;
    private String defaultFormatString = "70/1/1 at 12:00";

    @Test
    public void shouldProperlyHumanize() {
        Timestamp timestamp = new Timestamp(testLongTime  - TimeZone.getDefault().getOffset(testLongTime));
        String result = humanize(timestamp);
        assertThat("Incorrect default formatting", result, equalTo(defaultFormatString));
    }

}
