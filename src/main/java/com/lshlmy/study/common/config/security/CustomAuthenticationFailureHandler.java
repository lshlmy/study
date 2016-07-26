package com.lshlmy.study.common.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lshlmy.study.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;



/**
 * 自定义登录认证失败处理器
 * 
 * @author lshlmy
 */
@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String errorMsg = null;
		if (exception instanceof CustomAuthenticationException || exception instanceof UsernameNotFoundException) {
			errorMsg = exception.getMessage();
		} else if (exception instanceof BadCredentialsException) {
			errorMsg = "登录失败，用户名或密码不正确";
		} else if (exception instanceof DisabledException) {
			errorMsg = "登录失败，该用户已被禁用";
		} else {
			LOGGER.error("登录出现异常，请求参数为[" + JsonUtil.stringify(request.getParameterMap()) + "]", exception);
			errorMsg = "登录失败，服务器内部错误，请稍后再试...";
		}
		request.setAttribute("errorMsg", errorMsg);
		request.getRequestDispatcher("/loginPage.html").forward(request, response);
	}
}