package com.lshlmy.study.domain.mapper;

import com.lshlmy.study.domain.entity.Authority;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lshlm on 2016/7/13.
 */
@Component
public interface AuthorityMapper {

    List<Authority> selectAuthorityByUserId(@Param("userId") Long userId);

    List<Authority> selectAuthorityByAlias(@Param("aliases") List<String>  aliases);
}
