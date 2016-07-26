package com.lshlmy.study.common.config.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


/**
 * 自定义认证Token对象
 * 
 * @author lshlmy
 */
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;

	/** 登录类型枚举 */
	public enum LoginType {
		/** 统一登录 */
		UNITE("统一登录"),
		/** 本地登录 */
		LOCAL("本地登录"),
		/** 微信登录 */
		WECHAT("微信登录"),
		/** 测试登录 */
		TEST("测试登录");

		private String name;

		private LoginType(String name) {
			this.name = name;
		}

		/** 获取枚举的描述值 */
		public String getName() {
			return this.name;
		}

		/** 获取枚举的真实值 */
		public String getValue() {
			return this.name();
		}
	}

	/** 登录类型 */
	private LoginType loginType;
	/** 用户类型 */
	private String userType;
	/** 统一登录认证凭据 */
	private String zsToken;

	/** 构造方法 */
	public CustomAuthenticationToken(Object principal, Object credentials, LoginType loginType, String userType, String zsToken) {
		super(principal, credentials);
		this.loginType = loginType;
		this.userType = userType;
		this.zsToken = zsToken;
	}

	/** 构造方法 */
	public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities,
			LoginType loginType, String userType, String zsToken) {
		super(principal, credentials, authorities);
		this.loginType = loginType;
		this.userType = userType;
		this.zsToken = zsToken;
	}

	/**
	 * 获取登录类型
	 * 
	 * @return loginType 登录类型
	 */
	public LoginType getLoginType() {
		return loginType;
	}

	/**
	 * 获取用户类型
	 * 
	 * @return userType 用户类型
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * 获取统一登录认证凭据
	 * 
	 * @return zsToken 统一登录认证凭据
	 */
	public String getZsToken() {
		return zsToken;
	}

	/** 是否统一登录 */
	public boolean isUniteLogin() {
		return this.loginType == LoginType.UNITE;
	}

	/** 是否本地登录 */
	public boolean isLocalLogin() {
		return this.loginType == LoginType.LOCAL;
	}

	/** 是否微信登录 */
	public boolean isWechatLogin() {
		return this.loginType == LoginType.WECHAT;
	}

	/** 是否测试登录 */
	public boolean isTestLogin() {
		return this.loginType == LoginType.TEST;
	}
}