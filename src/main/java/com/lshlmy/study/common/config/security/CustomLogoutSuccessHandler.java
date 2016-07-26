package com.lshlmy.study.common.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;


/**
 * @description Spring Security 成功退出-处理类
 * @author lshlmy
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String url = request.getParameter("to");
		url = StringUtils.isEmpty(url) ? "loginPage.html" : url;
		response.sendRedirect(url);
	}
}
