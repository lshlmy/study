package com.lshlmy.study.domain.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author lshlmy
 * @description 用户实体类
 */
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    public enum UserType {
        /**
         * 学生
         */
        STUDENT("学生"),
        /**
         * 教师
         */
        TEACHER("教师");

        private String name;

        private UserType(String name) {
            this.name = name;
        }

        /**
         * 获取枚举的描述值
         */
        public String getName() {
            return this.name;
        }

        /**
         * 获取枚举的真实值
         */
        public String getValue() {
            return this.name();
        }

    }

    @Id
    @GeneratedValue(generator = "UUID")
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型
     */
    private UserType userType;

    /**
     * 拥有权限
     */
    private List<Authority> authority;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户类型
     *
     * @return userType 用户类型
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * 设置用户类型
     *
     * @param userType 用户类型
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    /**
     * 设置拥有权限
     *
     * @param authority 拥有权限
     */
    public void setAuthority(List<Authority> authority) {
        this.authority = authority;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authority;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", userType=" + userType + ", authority="
                + authority + "]";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
