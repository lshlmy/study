package com.lshlmy.study.service.impl;

import com.lshlmy.study.common.config.oauth2.Client;
import com.lshlmy.study.domain.mapper.ClientMapper;
import com.lshlmy.study.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by lshlm on 2016/7/19.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Optional<Client> findByClientIdAlias(String clientId) {
        Client client = new Client();
        client.setClientIdAlias(clientId);
        return Optional.ofNullable(clientMapper.selectOne(client));
    }

    @Override
    public Integer insert(Client client) {
        return clientMapper.insert(client);
    }
}
