package com.lshlmy.study.service;

import com.lshlmy.study.common.config.oauth2.Client;

import java.util.Optional;

/**
 * Created by lshlm on 2016/7/19.
 */
public interface ClientService {
    Optional<Client> findByClientIdAlias(String clientId);

    Integer insert(Client client);
}
