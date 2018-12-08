package xb.dev.tools.jpa.util;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author lwl
 * @date 2018/7/25 11:27
 * @description
 */
public class DateUtils {
    public static final byte TYPE_AFTER = 0;
    public static final byte TYPE_BEFORE = 1;


    private DateUtils(){}
    /**
     *  date转localDateTime
     * */
    public static LocalDateTime dateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    /**
     *  date转localDate
     * */
    public static LocalDate dateToLocalDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return localDateTime.toLocalDate();
    }

    /**
     *  date转localTime
     * */
    public static LocalTime dateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return localDateTime.toLocalTime();
    }


    /**
     *  localDate转Date
     * */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     *  localTime转Date
     * */
    public static Date LocalTimeToDate(LocalTime localTime) {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     *  获取指定天数之后或之前的日期
     * */
    public static Date getSpecifiedDayFromNow(byte type, int days){
        LocalDate localDate = LocalDate.now();
        switch (type){
            case TYPE_AFTER:
                localDate=localDate.plus(days, ChronoUnit.DAYS);
                break;
            case TYPE_BEFORE:
                localDate=localDate.minus(days, ChronoUnit.DAYS);
                break;
            default:
        }
        return localDateToDate(localDate);
    }

    /**
     *  获取指定月数之后或之前的日期
     * */
    public static Date getSpecifiedMonthFromNow(byte type, int month){
        LocalDate localDate = LocalDate.now();
        switch (type){
            case TYPE_AFTER:
                localDate=localDate.plus(month, ChronoUnit.MONTHS);
                break;
            case TYPE_BEFORE:
                localDate=localDate.minus(month, ChronoUnit.MONTHS);
                break;
            default:
        }
        return localDateToDate(localDate);
    }

    /**
     *  获取指定年数之后或之前的日期
     * */
    public static Date getSpecifiedYearFromNow(byte type, int year){
        LocalDate localDate = LocalDate.now();
        switch (type){
            case TYPE_AFTER:
                localDate=localDate.plus(year, ChronoUnit.YEARS);
                break;
            case TYPE_BEFORE:
                localDate=localDate.minus(year, ChronoUnit.YEARS);
                break;
                default:
        }
        return localDateToDate(localDate);
    }

    /**
     * 获取距离指定间隔多少天的时间
     */
    public static Date getSpecifiedTimeFromBegin(int type, int days, Date begin) {
        LocalDateTime localDateTime = dateToLocalDateTime(begin);
        LocalDateTime resultDateTime = changeDate(type, days, localDateTime);
        assert resultDateTime != null;
        return localDateTimeToDate(resultDateTime);
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    private static LocalDateTime changeDate(int type, int days, LocalDateTime localDateTime) {
        switch (type) {
            case TYPE_AFTER:
                return localDateTime.plus(days, ChronoUnit.DAYS);
            case TYPE_BEFORE:
                return localDateTime.minus(days, ChronoUnit.DAYS);
            default:
                break;
        }
        return null;
    }
    public static void main(String[] args) {
        getSpecifiedTimeFromBegin(TYPE_AFTER, 2, new Date());
    }

}
