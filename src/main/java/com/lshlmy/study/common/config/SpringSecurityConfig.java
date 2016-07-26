package com.lshlmy.study.common.config;

import com.lshlmy.study.common.config.security.CustomAuthenticationEntryPoint;
import com.lshlmy.study.common.config.security.CustomAuthenticationFilter;
import com.lshlmy.study.common.config.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.util.matcher.RequestMatcher;


/**
 * @description Spring Security 注解配置
 * @author lshlmy
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService customUserDetailsService;

	/** 登录成功处理类 */
	@Autowired
	private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

	/** 登录失败处理类 */
	@Autowired
	private AuthenticationFailureHandler customAuthenticationFailureHandler;

	/** 退出成功处理类 */
	@Autowired
	private LogoutSuccessHandler customLogoutSuccessHandler;

	/** csrf匹配路径 */
	@Autowired
	private RequestMatcher csrfSecurityRequestMatcher;

	/** 异常处理类 */
	@Bean
	public LoginUrlAuthenticationEntryPoint customAuthenticationEntryPoint() {

		return new CustomAuthenticationEntryPoint("/loginPage.html");
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CustomAuthenticationProvider authenticationProvider() {
		CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	/** 用户认证过滤器 */
	@Bean
	public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
		CustomAuthenticationFilter authFilter = new CustomAuthenticationFilter();
		authFilter.setFilterProcessesUrl("/login.html");
		authFilter.setAuthenticationManager(authenticationManager());
		authFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		authFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		authFilter.setUsernameParameter("username");
		authFilter.setPasswordParameter("password");
		authFilter.setSessionAuthenticationStrategy(new SessionFixationProtectionStrategy());
		return authFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint()).and().authorizeRequests()
				.antMatchers("/index.html*").access("hasRole('ROLE_USER')").antMatchers("/**").permitAll().and()
				.addFilter(authenticationFilter()).csrf().requireCsrfProtectionMatcher(csrfSecurityRequestMatcher).and().logout()
				.deleteCookies("JSESSIONID").logoutUrl("/logout.html").logoutSuccessHandler(customLogoutSuccessHandler);
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	/** 过滤静态文件 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
}
