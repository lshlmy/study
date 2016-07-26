package com.lshlmy.study.service;

import com.lshlmy.study.domain.entity.Authority;

import java.util.List;

/**
 * Created by lshlmy on 2016/7/24.
 */
public interface AuthorityService {
     List<Authority> findAuthorityByAlias(List<String>  alias);
}
