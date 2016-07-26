package com.lshlmy.study.common.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lshlmy.study.common.http.HttpRequestWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.lshlmy.study.common.config.security.CustomAuthenticationToken.LoginType;


/**
 * 自定义认证过滤器
 * 
 * @author lshlmy
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpRequestWrapper request = new HttpRequestWrapper(httpRequest);
		boolean isLogin = false;
		SecurityContextImpl context = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if (context != null) {
			Authentication authentication = context.getAuthentication();
			isLogin = authentication == null ? false : authentication.isAuthenticated();
		}
		String loginToken = request.getStringQuietly("loginToken");
		if (StringUtils.isEmpty(loginToken) || isLogin) {
			super.doFilter(req, res, chain);
		}

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		HttpRequestWrapper request = new HttpRequestWrapper((HttpServletRequest) req);
		LoginType loginType = request.getEnumQuietly("loginType", LoginType.class);
		String zsToken = request.getStringQuietly("zsToken");
		String userType = request.getStringQuietly("userType");
		String username = StringUtils.defaultIfEmpty(obtainUsername(request), "").trim();
		String password = StringUtils.defaultIfEmpty(obtainPassword(request), "");
		if (StringUtils.isEmpty(zsToken)) {
			zsToken = request.getStringQuietly("ticket");
			if (StringUtils.isNotEmpty(zsToken)) {
				request.setAttribute("CAS_CHECK_LOGIN_CALLBACK", true);
			}
		}
		CustomAuthenticationToken authRequest = new CustomAuthenticationToken(username, password, loginType, userType, zsToken);
		setDetails(request, authRequest);
		Authentication authentication = getAuthenticationManager().authenticate(authRequest);
		return authentication;
	}


}