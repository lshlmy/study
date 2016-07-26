package com.lshlmy.study.common.util;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * @description JSON数据处理工具类
 * @author lshlmy
 */
public class JsonUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
	}

	/** 设置Date时间转换格式 */
	public void setDateFormate(String dateFormatStr) {
		objectMapper.setDateFormat(new SimpleDateFormat(dateFormatStr));

	}

	/** 将对象转换成JSON字符串 */
	public static String stringify(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/** 将JSON字符串转换成对象 */
	public static <T> T parse(String json, Class<T> clazz) {
		if (StringUtils.isNotBlank(json)) {
			try {
				return objectMapper.readValue(json, clazz);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return null;
	}
}
