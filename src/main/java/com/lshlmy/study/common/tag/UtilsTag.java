package com.lshlmy.study.common.tag;

import com.lshlmy.study.common.util.DateTimeUtils;

import java.net.URLEncoder;
import java.util.Date;


/**
 * JSTL标签的函数集合
 * 
 * @author 杨海彬
 */
public class UtilsTag {

	/**
	 * 三目运算
	 * 
	 * @param condition 条件，true或false
	 * @param value1 值1
	 * @param value2 值2
	 * @return condition为true返回value1的值，为false返回value2的值
	 */
	public static Object ternary(Boolean condition, Object value1, Object value2) {
		return (condition != null && condition) ? value1 : value2;
	}

	/**
	 * 格式化时间
	 * 
	 * @param date 时间
	 * @param pattern 表达式
	 * @return 格式化后的字符串
	 */
	public static String formatDate(Date date, String pattern) {
		return date == null ? "" : DateTimeUtils.format(date, pattern);
	}

	/**
	 * 将字符串进行UTF-8编码
	 * 
	 * @param value 需编码的字符串
	 * @return 编码后的字符串
	 */
	public static String encode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (Exception e) {
			return value;
		}
	}

	/** 截断字符串并增加后缀 */
	public static String substring(String value, Integer length, String suffix) {
		String result = value;
		if (value.length() > length) {
			result = value.substring(0, length) + suffix;
		}
		return result;
	}
}