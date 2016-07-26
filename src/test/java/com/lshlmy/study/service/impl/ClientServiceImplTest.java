package com.lshlmy.study.service.impl;

import com.lshlmy.study.common.SpringTest;
import com.lshlmy.study.common.config.oauth2.Client;
import com.lshlmy.study.service.ClientService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by lshlm on 2016/7/19.
 */
public class ClientServiceImplTest extends SpringTest {

    @Autowired
    @Qualifier("clientServiceImpl")
    private ClientService clientService;

    @Test
    public void test() {
        Client client = new Client();
        //client.setId(1L);
        client.setClientIdAlias("tonr");
        client.setClientSecret("secret");
        Set<String> set = new HashSet<>();
        set.add("write");
        set.add("write");
        set.add("trust");
        client.setScope(set);
        clientService.insert(client);
    }
}