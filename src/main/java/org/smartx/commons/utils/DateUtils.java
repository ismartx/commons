package org.smartx.commons.utils;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author kext
 */
public final class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_PATTERN_WITHOUT_SECOND = "yyyy-MM-dd HH:mm";

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String DATE_PATTERN_CHINESE = "yyyy年MM月dd日";

    public static final String DATE_PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";

    /**
     * format date by given pattern
     */
    public static String formatDate(final Date date, String pattern) {
        if (date == null) {
            return null;
        }
        if (pattern == null) {
            pattern = DEFAULT_PATTERN;
        }
        final SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static String formatDateTime(final Date date) {
        final SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
        return formatter.format(date);
    }

    public static String formatDate(final Date date) {
        if (null == date) {
            return "";
        }
        final SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        return formatter.format(date);
    }

    public static Date parseDate(final String date) {
        final SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            logger.error(LoggerUtils.getErrorMsg("Parse Exception, date={}, pattern={}",
                    new Object[]{date, DATE_PATTERN}), e);
            return null;
        }
    }

    public static Date parseDate(final String date, String pattern) {
        if (pattern == null) {
            pattern = DATE_PATTERN;
        }
        final SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            logger.error(LoggerUtils.getErrorMsg("Parse Exception, date={}, pattern={}",
                    new Object[]{date, pattern}), e);
            return null;
        }
    }

    public static Date parseDateTime(final String dateTime) {
        return parseDate(dateTime, DEFAULT_PATTERN);
    }

    public static Date parseDate(final Long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_PATTERN);
        String date = format.format(timestamp);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            logger.error(LoggerUtils
                    .getErrorMsg("Parse Exception, timestamp={}", new Object[]{timestamp}), e);
            return null;
        }
    }

    public static int calculateAge(Date date) {
        if (date == null) {
            return 0;
        }
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Long age = localDate.until(LocalDate.now(), ChronoUnit.YEARS);
        return age.intValue();
    }

    public static boolean isWeekend(Date date) {
        if (date == null) {
            return false;
        }
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfWeek() == DayOfWeek.SATURDAY
                || localDate.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    /**
     * 计算明天，跳过周末
     */
    public static Date calcTmrByPassingWeekend(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate tmrLocalDate;
        switch (localDate.getDayOfWeek().getValue()) {
            case 5:
                tmrLocalDate = localDate.plusDays(3);
                break;
            case 6:
                tmrLocalDate = localDate.plusDays(2);
                break;
            default:
                tmrLocalDate = localDate.plusDays(1);
                break;
        }
        return Date.from(tmrLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date addDays(Date date, int amount) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, amount);
    }

    public static Date toDateTime(Date date, String time) {
        try {
            return new SimpleDateFormat(DEFAULT_PATTERN).parse(formatDate(date) + " " + time);
        } catch (ParseException e) {
            logger.error(LoggerUtils.getErrorMsg("To datetime Exception, date={}, time={}",
                    new Object[]{date, time}), e);
            return null;
        }
    }

    public static Date toDateTimeWithoutSecond(Date date, String time) {
        try {
            return new SimpleDateFormat(DEFAULT_PATTERN_WITHOUT_SECOND)
                    .parse(formatDate(date) + " " + time);
        } catch (ParseException e) {
            logger.error(LoggerUtils
                    .getErrorMsg("To datetime without second Exception, date={}, time={}",
                            new Object[]{date, time}), e);
            return null;
        }
    }

    public static boolean isSameDay(Date date1, Date date2) {
        return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
    }

    public static Date addHours(final Date date, final int amount) {
        return org.apache.commons.lang3.time.DateUtils.addHours(date, amount);
    }

    public static Date addMinutes(final Date date, final int amount) {
        return org.apache.commons.lang3.time.DateUtils.addMinutes(date, amount);
    }

    public static Date addSeconds(final Date date, final int amount) {
        return org.apache.commons.lang3.time.DateUtils.addSeconds(date, amount);
    }

    /**
     * 计算两个日期相差天数
     *
     * @return 正数，date1 > date2
     */
    public static int calculateDateGap(Date date1, Date date2) {
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate2.until(localDate1).getDays();
    }

    public static int calculateSecondGap(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / 1000);
    }

    /**
     * 获取当天的时间
     */
    public static Date getCurrentDay() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        return ca.getTime();
    }

    /**
     * 获取当前周
     */
    public static Integer getCurrentWeek() {
        Calendar ca = Calendar.getInstance();
        ca.setFirstDayOfWeek(Calendar.MONDAY);
        return ca.get(Calendar.WEEK_OF_YEAR);

    }

    /**
     * 获取当前时间的年-周
     */
    public static Integer getCurrentYearWeek() {
        return getYearWeek(new Date());
    }

    /**
     * 获取指定时间的年-周
     */
    public static Integer getYearWeek(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.setFirstDayOfWeek(Calendar.MONDAY);
        DateTime dt = new DateTime(ca);
        return dt.weekyear().get() * 100 + dt.weekOfWeekyear().get();
    }

    public static long calculateDateGap(long date1, long date2) {
        return calculateDateGap(new Date(date1), new Date(date2));
    }

    /**
     * 获取指定日期的开始时间
     */
    public static Date getStartOfDay(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        return cld.getTime();
    }

    /**
     * 获取指定日期的最后时间
     */
    public static Date getEndOfDay(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.HOUR_OF_DAY, 23);
        cld.set(Calendar.MINUTE, 59);
        cld.set(Calendar.SECOND, 59);
        cld.set(Calendar.MILLISECOND, 999);
        return cld.getTime();
    }

}
