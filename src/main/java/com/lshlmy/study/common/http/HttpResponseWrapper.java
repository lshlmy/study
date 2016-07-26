package com.lshlmy.study.common.http;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * HttpServletResponse包装类
 * 
 * @author 微觉零度
 */
public class HttpResponseWrapper extends HttpServletResponseWrapper {

	/** HttpServletRequest包装类 */
	private HttpRequestWrapper request;

	/**
	 * 构造方法
	 * 
	 * @param response HttpServletResponse
	 * @param request HttpServletRequest包装类
	 */
	public HttpResponseWrapper(HttpServletResponse response, HttpRequestWrapper request) {
		super(response);
		this.request = request;
	}

	/**
	 * 响应输出普通字符串
	 * 
	 * @param result 要输出的字符串
	 */
	public void outputString(String result) {
		super.setContentType("text/html;charset=UTF-8");
		try {
			super.getWriter().write(result);
		} catch (Exception e) {
		}
	}

	/**
	 * 响应输出JSON或JSONP字符串
	 * 
	 * @param status 状态,true=成功,false=失败
	 */
	public void outputJson(boolean status) {
		outputJson(status, null);
	}

	/**
	 * 响应输出JSON或JSONP字符串
	 * 
	 * @param status 状态,true=成功,false=失败
	 * @param message 消息内容
	 */
	public void outputJson(boolean status, String message) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		jsonObject.put("message", message);
		outputJson(jsonObject);
	}

	/**
	 * 响应输出JSON或JSONP字符串
	 * 
	 * @param result 要输出的JSONObject或JSONArray对象
	 */
	public void outputJson(JSON result) {
		String jsonpCallback = this.request.getJsonpCallback();
		if (StringUtils.isNotEmpty(jsonpCallback)) {
			outputString(jsonpCallback + "(" + result.toString() + ");");
		} else {
			super.setContentType("application/json;charset=UTF-8");
			try {
				super.getWriter().write(result.toString());
			} catch (Exception e) {
			}
		}
	}
}