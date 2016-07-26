package com.lshlmy.study.common.config.oauth2;

import com.lshlmy.study.service.impl.AuthorityServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
 * Created by lshlm on 2016/7/14.
 */
public class CustomClientDetails extends Client implements ClientDetails {

    public CustomClientDetails(Client client) {
        super(client);
    }

    @Override
    public String getClientId() {
        return super.getClientIdAlias();
    }

    @Override
    public Set<String> getResourceIds() {
        return super.getResourceIds();
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return super.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return super.getScope();
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return super.getAuthorizedGrantTypes();
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return super.getRegisteredRedirectUris();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return super.getGrantedAuthorities();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return super.getRefreshTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return super.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
