package com.lshlmy.study.domain.entity;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @description TODO
 * @author lshlmy
 */
public class Authority implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	private Long id;

	/** 权限 */
	@Column(name = "AUTHORITY_ALIAS")
	private String authorityAlias;

	/** 权限名称 */
	@Column(name = "AUTHORITY_NAME")
	private String authorityName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorityAlias() {
		return authorityAlias;
	}

	public void setAuthorityAlias(String authorityAlias) {
		this.authorityAlias = authorityAlias;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	@Override
	public String toString() {
		return "Authority{" +
				"id='" + id + '\'' +
				", authorityAlias='" + authorityAlias + '\'' +
				", authorityName='" + authorityName + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && this.id != null && obj instanceof Authority && this.id.equals(((Authority) obj).getId());
	}

	@Override
	public String getAuthority() {
		return this.authorityAlias;
	}
}
