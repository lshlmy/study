package com.lshlmy.study.common.config.oauth2;

import com.lshlmy.study.service.AuthorityService;
import com.lshlmy.study.service.ClientService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lshlm on 2016/7/14.
 */
@Service
public class CustomClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientService.findByClientIdAlias(clientId).orElseThrow(() -> new ClientRegistrationException
                (String.format("Client %s does not exist!", clientId)));
        List<GrantedAuthority> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(client.getGrantedAuthorityAlias())){
            list.addAll(authorityService.findAuthorityByAlias(client.getGrantedAuthorityAlias()));
        }
        client.setGrantedAuthorities(list);
        return new CustomClientDetails(client);
    }

}
