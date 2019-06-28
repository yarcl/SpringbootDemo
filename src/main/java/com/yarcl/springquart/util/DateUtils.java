package com.yarcl.springquart.util;

import org.thymeleaf.expression.Lists;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhi on 2019/6/28.
 * <li>Instant代替 Date，</li>
 * <li>LocalDateTime代替 Calendar，</li>
 * <li>DateTimeFormatter 代替 SimpleDateFormat.</li> 注意：如果是共享变量，则可能会出现线程问题。<br>
 */
public class DateUtils {
    // 时间元素
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String WEEK = "week";
    private static final String DAY = "day";
    private static final String HOUR = "hour";
    private static final String MINUTE = "minute";
    private static final String SECOND = "second";

    // 根据指定格式显示日期和时间
    /** yyyy-MM-dd */
    private static final DateTimeFormatter yyyyMMdd_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /** yyyy-MM-dd HH:mm:ss */
    private static final DateTimeFormatter yyyyMMddHHmmss_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 根据日期格式，获取当前时间
     *
     * @param formatStr 日期格式<br>
     *        <li>yyyy</li>
     *        <li>yyyy-MM-dd</li>
     *        <li>yyyy-MM-dd HH:mm:ss</li>
     *        <li>HH:mm:ss</li>
     *        <li>yyyy年MM月dd日</li>
     *        <li>yyyy年MM月dd日HH时</li>
     *        <li>yyyy年MM月dd日HH时mm分</li>
     *        <li>yyyy年MM月dd日HH时mm分ss秒</li>
     *        <li>HH时mm分ss秒</li>
     *         构建方式:DateTimeFormatter.ofPattern("HH时mm分ss秒")
     * @return 12时49分35秒
     * @author zero 2019/03/30
     */
    public static String getTime(String formatStr) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatStr));
    }

    /**
     * 获取当前日期之后（之后）的节点事件<br>
     * <ul>
     * 比如当前时间为：2019-03-30 10:20:30
     * </ul>
     * <li>node="hour",num=5L:2019-03-30 15:20:30</li>
     * <li>node="day",num=1L:2019-03-31 10:20:30</li>
     * <li>node="year",num=1L:2020-03-30 10:20:30</li>
     *
     * @param node 节点元素（“year”,"month","week","day","huor","minute","second"）
     * @param num 第几天（+：之后，-：之前）
     * @return 之后或之后的日期
     * @author zero 2019/03/30
     */
    public static String getAfterOrPreNowTime(String node, Long num) {
        LocalDateTime now = LocalDateTime.now();
        return convertTime2Format(node, now, num, yyyyMMddHHmmss_EN);
    }

    /**
     * 获取与当前日期相距num个之后（之前）的日期<br>
     * <ul>
     * 比如当前时间为：2019-03-30 10:20:30的格式日期
     * <li>node="hour",num=5L:2019-03-30 15:20:30</li>
     * <li>node="day",num=1L:2019-03-31 10:20:30</li>
     * <li>node="year",num=1L:2020-03-30 10:20:30</li>
     * </ul>
     *
     * @param dtf 格式化当前时间格式（dtf = yyyyMMddHHmmss_EN）
     * @param node 节点元素（“year”,"month","week","day","huor","minute","second"）
     * @param num （+：之后，-：之前）
     * @return 之后之前的日期
     * @author zero 2019/03/30
     */
    public static String getAfterOrPreNowTime(DateTimeFormatter dtf, String node, Long num) {
        LocalDateTime now = LocalDateTime.now();
        return convertTime2Format(node, now, num, dtf);
    }

    public static String convertTime2Format(String node, LocalDateTime time, Long num, DateTimeFormatter dtf)  {
        if (HOUR.equals(node)) {
            return time.plusHours(num).format(dtf);
        } else if (DAY.equals(node)) {
            return time.plusDays(num).format(dtf);
        } else if (WEEK.equals(node)) {
            return time.plusWeeks(num).format(dtf);
        } else if (MONTH.equals(node)) {
            return time.plusMonths(num).format(dtf);
        } else if (YEAR.equals(node)) {
            return time.plusYears(num).format(dtf);
        } else if (MINUTE.equals(node)) {
            return time.plusMinutes(num).format(dtf);
        } else if (SECOND.equals(node)) {
            return time.plusSeconds(num).format(dtf);
        } else {
            return null;
        }
    }

    /**
     * 获取指定日期之前之后的第index的日，周，月，年的日期
     *
     * @param date 指定日期格式：yyyy-MM-dd
     * @param node 时间节点元素（日周月年）
     * @param index 之前之后第index个日期
     * @return yyyy-MM-dd 日期字符串
     */
    public static String getAfterOrPreDate(String date, String node, int index) {
        date = date.trim();
        if (DAY.equals(node)) {
            return LocalDate.parse(date).plus(index, ChronoUnit.DAYS).format(yyyyMMdd_EN);
        } else if (WEEK.equals(node)) {
            return LocalDate.parse(date).plus(index, ChronoUnit.WEEKS).format(yyyyMMdd_EN);
        } else if (MONTH.equals(node)) {
            return LocalDate.parse(date).plus(index, ChronoUnit.MONTHS).format(yyyyMMdd_EN);
        } else if (YEAR.equals(node)) {
            return LocalDate.parse(date).plus(index, ChronoUnit.YEARS).format(yyyyMMdd_EN);
        } else {
            return null;
        }
    }

    /**
     * 计算两个日期字符串之间相差多少个周期（天，月，年）
     * @param date1 yyyy-MM-dd
     * @param date2 yyyy-MM-dd
     * @param node 三者之一:(day，month,year)
     * @return 相差多少周期
     * @author zero 2019/03/31
     */
    public static int peridCount(String date1, String date2, String node) {
        date1 = date1.trim();
        date2 = date2.trim();
        if (DAY.equals(node)) {
            return Period.between(LocalDate.parse(date1), LocalDate.parse(date2)).getDays();
        } else if (MONTH.equals(node)) {
            return Period.between(LocalDate.parse(date1), LocalDate.parse(date2)).getMonths();
        } else if (YEAR.equals(node)) {
            return Period.between(LocalDate.parse(date1), LocalDate.parse(date2)).getYears();
        }
        return 0;
    }

    /**
     * 切割日期。按照周期切割成小段日期段。例如： <br>
     *
     * @param startDate 开始日期（yyyy-MM-dd）
     * @param endDate 结束日期（yyyy-MM-dd）
     * @param period 周期（天，周，月，年）
     * @return 切割之后的日期集合
     * @author zero 2019/04/02
     * @example
     *          <li>startDate="2019-02-28",endDate="2019-03-05",period="day"</li>
     *          <li>结果为：[2019-02-28, 2019-03-01, 2019-03-02, 2019-03-03, 2019-03-04, 2019-03-05]</li><br>
     *          <li>startDate="2019-02-28",endDate="2019-03-25",period="week"</li>
     *          <li>结果为：[2019-02-28,2019-03-06, 2019-03-07,2019-03-13, 2019-03-14,2019-03-20,
     *          2019-03-21,2019-03-25]</li><br>
     *          <li>startDate="2019-02-28",endDate="2019-05-25",period="month"</li>
     *          <li>结果为：[2019-02-28, 2019-03-31, 2019-04-30,2019-05-25]</li><br>
     *          <li>startDate="2019-02-28",endDate="2020-05-25",period="year"</li>
     *          <li>结果为：[2019-02-28,2019-12-31, 2020-01-01,2020-05-25]</li><br>
     */
    public static List<String> getPieDateRange(String startDate, String endDate, String period) {
        List<String> result = new ArrayList<>();
        LocalDate end = LocalDate.parse(endDate, yyyyMMdd_EN);
        LocalDate start = LocalDate.parse(startDate, yyyyMMdd_EN);
        LocalDate tmp = start;
        switch (period) {
            case DAY:
                while (start.isBefore(end) || start.isEqual(end)) {
                    result.add(start.toString());
                    start = start.plusDays(1);
                }
                break;
            case WEEK:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    if (tmp.plusDays(6).isAfter(end)) {
                        result.add(end.toString());
                    } else {
                        result.add(tmp.plusDays(6).toString());
                    }
                    tmp = tmp.plusDays(7);
                }
                break;
            case MONTH:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    LocalDate lastDayOfMonth = tmp.with(TemporalAdjusters.lastDayOfMonth());
                    if (lastDayOfMonth.isAfter(end)) {
                        result.add(end.toString());
                    } else {
                        result.add(lastDayOfMonth.toString());
                    }
                    tmp = lastDayOfMonth.plusDays(1);
                }
                break;
            case YEAR:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    LocalDate lastDayOfYear = tmp.with(TemporalAdjusters.lastDayOfYear());
                    if (lastDayOfYear.isAfter(end)) {
                        result.add(end.toString());
                    } else {
                        result.add(lastDayOfYear.toString());
                    }
                    tmp = lastDayOfYear.plusDays(1);
                }
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 指定日期月的最后一天
     *
     * @param curDate 日期格式,与dft保持一致
     * @param period
     * @param firstOrLast true：第一天，false：最后一天
     * @param dft 该格式为指定的格式类型
     * @return
     * @author zero 2019/04/13
     */
    public static String getLastDayOfMonth(String curDate, String period, boolean firstOrLast, DateTimeFormatter dft) {
        switch (period) {
            case DAY:
                if (firstOrLast) {
                    return LocalDate.parse(curDate, dft).with(TemporalAdjusters.firstDayOfMonth()).toString();
                } else {
                    return LocalDate.parse(curDate, dft).with(TemporalAdjusters.lastDayOfMonth()).toString();
                }
            case YEAR:
                if (firstOrLast) {
                    return LocalDate.parse(curDate, yyyyMMdd_EN).with(TemporalAdjusters.firstDayOfYear()).toString();
                } else {
                    return LocalDate.parse(curDate, yyyyMMdd_EN).with(TemporalAdjusters.lastDayOfYear()).toString();
                }
            default:
                return null;
        }
    }

    /**
     * 获取指定日期当月的最后或第一个星期日期
     *
     * @param curDay 指定日期（yyyy-MM-dd）
     * @param dayOfWeek 周几（1~7）
     * @param lastOrFirst true：最后一个，false本月第一个
     * @return 日期（yyyy-MM-dd）
     */
    public static String getFirstOrLastWeekDate(String curDay, int dayOfWeek, boolean lastOrFirst) {
        dayOfWeek = dayOfWeek < 1 || dayOfWeek > 7 ? 1 : dayOfWeek;
        if (lastOrFirst) {
            return LocalDate.parse(curDay).with(TemporalAdjusters.lastInMonth(DayOfWeek.of(dayOfWeek))).toString();
        } else {
            return LocalDate.parse(curDay).with(TemporalAdjusters.firstInMonth(DayOfWeek.of(dayOfWeek))).toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("===================");
        // String curDate = "2019-04-10"; // 指定日期
        // System.out.println(getLastDayOfMonth(curDate, true));
        // System.out.println(getLastDayOfMonth(curDate, false));
        // System.out.println(getLastDayOfYear(curDate, true));
        // System.out.println(getLastDayOfYear(curDate, false));
        // System.out.println("===================");
        // String startDate = "2019-02-28", endDate = "2019-03-05", period = DAY;
        // System.out.println(getPieDateRange(startDate, endDate, period));
        // System.out.println("===================");
        // System.out.println(getNextWeekDate("2019-02-28", 1, false));
        // System.out.println(getPieDateRange("2019-12-28", "2020-03-01", DAY));
        // System.out.println("===================");
        // System.out.println(getFirstOrLastWeekDate("2019-04-02", 0, false));
        // System.out.println(getPreWeekDate("2019-04-02", 2, false));
        // System.out.println(getNextWeekDate("2019-04-02", 2, false));
        // System.out.println("===================");
        // System.out.println("当前时间戳：" + Instant.now());
        // System.out.println("当前时间：" + LocalDateTime.now());
        // System.out.println("===================");
        // System.out.println(peridCount("2019-01-30", "2019-03-31", MONTH));
        // System.out.println(isLeapYear("2019-03-31"));
        // System.out.println(LocalDate.now().isLeapYear());
        // System.out.println("===================");
        // System.out.println(getAfterOrPreDate("2019-03-31", WEEK, -1));
        // System.out.println(getAfterOrPreDayDate(-5));
        // System.out.println(getAfterOrPreDayDate(-3));
        // System.out.println(getAfterOrPreDayDate(6));
        // System.out.println(getAfterOrPreYearDate(6));
        // System.out.println(getAfterOrPreWeekDate(1));
        // System.out.println("===================");
        // clock();
        // isBirthday(7, 13);
        // LocalDate date0 = LocalDate.of(2019, Month.OCTOBER, 31);
        // LocalDate date = LocalDate.of(2019, 3, 31);
        // System.out.println(date0.equals(LocalDate.now()));
        // System.out.println(date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth());
        // System.out.println(getNowTime_EN());
        // System.out.println(getAfterNowTimeSimp(SECOND, 5L));
        // System.out.println("===================");
        // System.out.println(getAfterNowTime(SECOND, 5L));
        // System.out.println(getAfterNowTimePlus(yyyyMMddHHmm_EN, SECOND, 5L));
        // System.out.println("===================");
        // System.out.println(getNowDate_EN());
        // System.out.println(getNowDate_CN());
        // System.out.println(getTime("HH:mm:ss"));
        // System.out.println(getNowTime_EN());
        // System.out.println(getNowTime_CN());
        // System.out.println(getNowLocalTime_shot());
        // System.out.println(getNowLocalTime_full());
        // System.out.println(getNowLocalTime_long());
        // System.out.println(getNowLocalTime_medium());
        // System.out.println(getNodeTime("week"));
        // System.out.println(transformWeekEN2Num(null));
        // System.out.println("===================");
    }

}
