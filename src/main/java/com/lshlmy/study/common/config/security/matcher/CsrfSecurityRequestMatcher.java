package com.lshlmy.study.common.config.security.matcher;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

/**
 * @description CSRF RequestMatcher
 * @author lshlmy
 */
@Component("csrfSecurityRequestMatcher")
public class CsrfSecurityRequestMatcher implements RequestMatcher {

	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

	public boolean matches(HttpServletRequest request) {

		if (!allowedMethods.matcher(request.getMethod()).matches()) {
			return true;
		}
		return false;
	}
}
