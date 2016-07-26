package com.lshlmy.study.common.filter;

import com.lshlmy.study.common.http.HttpRequestWrapper;
import com.lshlmy.study.common.http.HttpResponseWrapper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @description 自定义过滤器（编码及封装http）
 * @author lshlmy
 */
public class HttpRequestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		HttpRequestWrapper request = new HttpRequestWrapper(httpRequest);
		HttpResponseWrapper response = new HttpResponseWrapper(httpResponse, request);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
