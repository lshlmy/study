package com.lshlmy.study.service.impl;

import com.lshlmy.study.domain.entity.Authority;
import com.lshlmy.study.domain.entity.User;
import com.lshlmy.study.domain.mapper.AuthorityMapper;
import com.lshlmy.study.domain.mapper.UserMapper;
import com.lshlmy.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lshlm on 2016/7/13.
 */
@Service("customUserDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthorityMapper authorityMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("登录失败，用户名不存在");
        }
        List<Authority> authorities = authorityMapper.selectAuthorityByUserId(user.getId());
        user.setAuthority(authorities);
        return user;
    }

}
