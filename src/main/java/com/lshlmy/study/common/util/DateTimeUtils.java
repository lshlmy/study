package com.lshlmy.study.common.util;

import java.util.Calendar;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 日期时间工具类
 * 
 * @author lshlmy
 */
public class DateTimeUtils {

	/** 获取最小时间 */
	private static Date getMinTime(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/** 获取最大时间 */
	private static Date getMaxTime(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/** 获取指定日期的最小时间 */
	public static Date getDayMinTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getMinTime(calendar);
	}

	/** 获取指定日期的最大时间 */
	public static Date getDayMaxTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getMaxTime(calendar);
	}

	/** 获取指定日期下一天的最小时间 */
	public static Date getNextDayMinTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return getMinTime(calendar);
	}

	/** 获取指定日期下一天的最大时间 */
	public static Date getNextDayMaxTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return getMaxTime(calendar);
	}

	/** 获取指定日期上一天的最小时间 */
	public static Date getPreviousDayMinTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return getMinTime(calendar);
	}

	/** 获取指定日期上一天的最大时间 */
	public static Date getPreviousDayMaxTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return getMaxTime(calendar);
	}

	/** 获取指定月份第一天的最小时间 */
	public static Date getMonthMinTime(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return getMinTime(calendar);
	}

	/** 获取指定月份最后一天的最大时间 */
	public static Date getMonthMaxTime(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getMaxTime(calendar);
	}

	/** 获取指定月份第一天的最小时间 */
	public static Date getMonthMinTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return getMinTime(calendar);
	}

	/** 获取指定月份最后一天的最大时间 */
	public static Date getMonthMaxTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getMaxTime(calendar);
	}

	/** 获取指定月份下个月第一天的最小时间 */
	public static Date getNextMonthMinTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return getMinTime(calendar);
	}

	/** 获取指定月份下个月最后一天的最大时间 */
	public static Date getNextMonthMaxTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getMaxTime(calendar);
	}

	/** 获取指定月份上个月第一天的最小时间 */
	public static Date getPreviousMonthMinTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return getMinTime(calendar);
	}

	/** 获取指定月份上个月最后一天的最大时间 */
	public static Date getPreviousMonthMaxTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getMaxTime(calendar);
	}

	/** 增加或减少x年 */
	public static Date addYear(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, amount);
		return calendar.getTime();
	}

	/** 增加或减少x月 */
	public static Date addMonth(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		return calendar.getTime();
	}

	/** 增加或减少x天 */
	public static Date addDay(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, amount);
		return calendar.getTime();
	}

	/** 增加或减少x时 */
	public static Date addHour(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, amount);
		return calendar.getTime();
	}

	/** 增加或减少x分 */
	public static Date addMinute(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, amount);
		return calendar.getTime();
	}

	/** 增加或减少x秒 */
	public static Date addSecond(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, amount);
		return calendar.getTime();
	}

	/** 根据表达式把时间字符串转换成时间对象 */
	public static Date parse(String value, String pattern) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(value);
		} catch (Exception e) {
			throw new RuntimeException("将字符串转成日期对象出现异常", e);
		}
	}

	/** 根据表达式把时间对象转换成时间字符串 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/** 判断两个时间是否为同一天 */
	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		return DateUtils.isSameDay(date1, date2);
	}
}