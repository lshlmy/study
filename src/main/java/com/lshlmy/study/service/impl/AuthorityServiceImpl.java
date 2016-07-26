package com.lshlmy.study.service.impl;

import com.lshlmy.study.domain.entity.Authority;
import com.lshlmy.study.domain.mapper.AuthorityMapper;
import com.lshlmy.study.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lshlmy on 2016/7/24.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public List<Authority> findAuthorityByAlias(List<String> aliases) {
        return authorityMapper.selectAuthorityByAlias(aliases);
    }
}
