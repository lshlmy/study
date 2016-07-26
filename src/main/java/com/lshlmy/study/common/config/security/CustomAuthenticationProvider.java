package com.lshlmy.study.common.config.security;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description Spring Security 认证处理器
 * @author sini
 */
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private PasswordEncoder passwordEncoder;
	private UserDetailsService userDetailsService;
	private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

	public void setAuthoritiesMapper(GrantedAuthoritiesMapper authoritiesMapper) {
		this.authoritiesMapper = authoritiesMapper;
		super.setAuthoritiesMapper(authoritiesMapper);
	}

	/**
	 * 设置passwordEncoder
	 *
	 * @param passwordEncoder passwordEncoder
	 */
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * 设置userDetailsService
	 *
	 * @param userDetailsService userDetailsService
	 */
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		String plaintextPassword = authentication.getCredentials().toString();
		if (!passwordEncoder.matches(plaintextPassword, userDetails.getPassword())) {
			throw new CustomAuthenticationException("登录失败，帐号或密码错误");
		}

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails loadedUser;
		try {
			loadedUser = this.userDetailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException notFound) {
			throw notFound;
		} catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}
		if (loadedUser == null) {
			throw new InternalAuthenticationServiceException(
					"CustomUserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}

	@Override
	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
		CustomAuthenticationToken authRequest = (CustomAuthenticationToken) authentication;
		CustomAuthenticationToken result = new CustomAuthenticationToken(principal, authentication.getCredentials(),
				authoritiesMapper.mapAuthorities(user.getAuthorities()), authRequest.getLoginType(), authRequest.getUserType(),
				authRequest.getZsToken());
		result.setDetails(authentication.getDetails());
		return result;
	}

}
