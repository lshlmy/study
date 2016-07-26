package com.lshlmy.study.common.config.oauth2;

import com.lshlmy.study.domain.handler.CommaListTypeHander;
import com.lshlmy.study.domain.handler.CommaSetTypeHander;
import org.apache.ibatis.type.JdbcType;
import org.springframework.security.core.GrantedAuthority;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by lshlm on 2016/7/19.
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 3454621269186792861L;

    @Id
    @GeneratedValue(generator = "UUID")
    private Long id;

    @Column(name = "client_id_alias")
    private String clientIdAlias;

    @Column(name = "resource_id")
    @ColumnType(jdbcType = JdbcType.VARCHAR, typeHandler = CommaSetTypeHander.class)
    private Set<String> resourceIds;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "scope")
    @ColumnType(jdbcType = JdbcType.VARCHAR, typeHandler = CommaSetTypeHander.class)
    private Set<String> scope;

    @Column(name = "authorized_grant_type")
    @ColumnType(jdbcType = JdbcType.VARCHAR, typeHandler = CommaSetTypeHander.class)
    private Set<String> authorizedGrantTypes;

    @Column(name = "registered_redirect_uri")
    @ColumnType(jdbcType = JdbcType.VARCHAR, typeHandler = CommaSetTypeHander.class)
    private Set<String> registeredRedirectUris;

    @Column(name = "granted_authority_alias")
    @ColumnType(jdbcType = JdbcType.VARCHAR, typeHandler = CommaListTypeHander.class)
    private List<String> grantedAuthorityAlias;

    @Transient
    private List<GrantedAuthority> grantedAuthorities;

    @Column(name = "access_token_validity_second")
    private Integer accessTokenValiditySeconds;

    @Column(name = "refresh_token_validity_second")
    private Integer refreshTokenValiditySeconds;


    public Client() {
    }

    public Client(Client client) {
        this();
        setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds());
        setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds());
        setGrantedAuthorities(client.getGrantedAuthorities());
        setAuthorizedGrantTypes(client.getAuthorizedGrantTypes());
        setClientIdAlias(client.getClientIdAlias());
        setClientSecret(client.getClientSecret());
        setRegisteredRedirectUris(client.getRegisteredRedirectUris());
        setScope(client.getScope());
        setResourceIds(client.getResourceIds());
    }

    public Client(Long id, String clientIdAlias, Set<String> resourceIds, String clientSecret, Set<String> scope, Set<String> authorizedGrantTypes, Set<String> registeredRedirectUris, List<String> grantedAuthorityAlias, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds) {
        this.id = id;
        this.clientIdAlias = clientIdAlias;
        this.resourceIds = resourceIds;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUris = registeredRedirectUris;
        this.grantedAuthorityAlias = grantedAuthorityAlias;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public String getClientIdAlias() {
        return clientIdAlias;
    }

    public void setClientIdAlias(String clientIdAlias) {
        this.clientIdAlias = clientIdAlias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public Set<String> getRegisteredRedirectUris() {
        return registeredRedirectUris;
    }

    public void setRegisteredRedirectUris(Set<String> registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris;
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public List<String> getGrantedAuthorityAlias() {
        return grantedAuthorityAlias;
    }

    public void setGrantedAuthorityAlias(List<String> grantedAuthorityAlias) {
        this.grantedAuthorityAlias = grantedAuthorityAlias;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }
}
