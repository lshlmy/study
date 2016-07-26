package com.lshlmy.study.common.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * 自定义认证入口点
 * 
 * @author 杨海彬
 */
public class CustomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	/**
	 * 构造方法
	 * 
	 * @param loginFormUrl 默认登录页
	 */
	public CustomAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) {
		return super.determineUrlToUseForThisRequest(request, response, exception);
	}
}