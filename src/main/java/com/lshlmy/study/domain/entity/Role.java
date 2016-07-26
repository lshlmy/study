package com.lshlmy.study.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @description TODO
 * @author sini
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	private Long id;

	/** 角色名 */
	private String roleName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取角色名
	 * 
	 * @return roleName 角色名
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 设置角色名
	 *
	 * @param roleName 角色名
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
