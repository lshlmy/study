package com.lshlmy.study.domain.mapper;

import com.lshlmy.study.domain.entity.Authority;
import com.lshlmy.study.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @description User Mapper
 * @author lshlmy
 */
@Component
public interface UserMapper{

	User selectByUsername(String username) throws UsernameNotFoundException;
}
