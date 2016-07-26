package com.lshlmy.study.service;

import com.lshlmy.study.common.config.oauth2.Client;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * @author lshlmy
 * @description User 业务逻辑
 */

public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);
}
