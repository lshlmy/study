package com.lshlmy.study.domain.mapper;

import com.lshlmy.study.common.config.oauth2.Client;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by lshlm on 2016/7/19.
 */
@Component
public interface ClientMapper extends Mapper<Client> {

}
