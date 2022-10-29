/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.infrastructure.adapters.commons.config.auth;

import br.com.fenrir.auth.adapters.commons.config.JDBCTokenStoreConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * <p>
 OAuth2AuthorizationServer class. This class configure the Authorization Server.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private JDBCTokenStoreConfig jdbcStoreConfig;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthProperties properties;

    @Autowired
    private JWTConverter converter;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    /**
     * Factory to build a {@link CustomOauth2RequestFactory}.
     *
     * @return {@link OAuth2RequestFactory}.
     */
    @Bean
    public OAuth2RequestFactory requestFactory() {
        CustomOauth2RequestFactory requestFactory
                = new CustomOauth2RequestFactory(clientDetailsService());
        requestFactory.setCheckUserScopes(true);
        return requestFactory;
    }

    /**
     * Token Store to JWT tokens.
     *
     * @return {@link TokenStore}.
     */
    public TokenStore tokenStore()
            throws RuntimeException {
        return new JdbcTokenStore(jdbcStoreConfig.dataSource());
    }
//
//    /**
//     * Approval Token Store to JWT tokens.
//     *
//     * @return {@link ApprovalStore}
//     */
//    @Bean
//    public ApprovalStore approvalStore() {
//        JdbcApprovalStore jdbcApprovalStore
//                = new JdbcApprovalStore(jdbcStoreConfig.dataSource());
//        return jdbcApprovalStore;
//    }
//

    /**
     * Authorization Code Services with JDBC Datasource.
     *
     * @return {@link AuthorizationCodeServices}.
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(jdbcStoreConfig.dataSource());
    }

    /**
     * Client Details Service with JDBC Datasource.
     *
     * @return {@link JdbcClientDetailsService}.
     */
    public ClientDetailsService clientDetailsService() {
        JdbcClientDetailsService jdbcClientDetailsService
                = new JdbcClientDetailsService(jdbcStoreConfig.dataSource());
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }

    /**
     * Token Services.
     *
     * @return {@link DefaultTokenServices}.
     */
    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setTokenEnhancer(jwtAccessTokenConverter());
        tokenServices.setAuthenticationManager(authenticationManager);
        tokenServices.setClientDetailsService(clientDetailsService());
        tokenServices.setSupportRefreshToken(true);
        return tokenServices;
    }

    /**
     * Token converter using a JKS Resource for JWT token.
     *
     * @return {@link JwtAccessTokenConverter}.
     */
    public JwtAccessTokenConverter jwtAccessTokenConverter()
            throws RuntimeException {
        return converter.jwtAccessTokenConverter();
    }

    /**
     * Filter for token Endpoint.
     *
     * @return {@link TokenEndpointAuthenticationFilter}.
     */
    @Bean
    public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter() {
        return new TokenEndpointAuthenticationFilter(
                authenticationManager, requestFactory()
        );
    }

    /**
     * Configure Security of Authorization Server.
     * @param security {@link AuthorizationServerSecurityConfigurer}.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess(AuthProperties.PERMIT_ALL)
                .checkTokenAccess(AuthProperties.IS_AUTHENTICATED)
                .passwordEncoder(passwordEncoder)
                .allowFormAuthenticationForClients();
    }

    /**
     * Configure Authorization Server Endpoints.
     * @param endpoints {@link AuthorizationServerEndpointsConfigurer}.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(userDetailsService);
        endpoints.tokenEnhancer(jwtAccessTokenConverter());
        endpoints.tokenStore(tokenStore());
//        endpoints.approvalStore(approvalStore());
        endpoints.approvalStoreDisabled();
        endpoints.setClientDetailsService(clientDetailsService());
        endpoints.authorizationCodeServices(authorizationCodeServices());
        endpoints.reuseRefreshTokens(false);

        if (properties.getCheckUserScopes()) {
            endpoints.requestFactory(requestFactory());
        }
    }

    /**
     * Configure ClientDetailsService.
     * @param clients {@link ClientDetailsServiceConfigurer}.
     * @throws Exception ApiError on Configure ClientDetailsService.
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * Listener to Log Auth Sucess Event.
     *
     * @param event {@link AuthenticationSuccessEvent}.
     */
    @Profile(value = {"test", "native", "development"})
    @EventListener
    public void authSuccessEventListener(AuthenticationSuccessEvent event) {

        Authentication auth = event.getAuthentication();
        log.debug("DEBUG: LOGIN SUCCESS [CRED = {}] [PRINCIPAL = {}] ",
                auth.getDetails(), auth.getPrincipal());
    }

    /**
     * Listener to Log Auth Failed Event.
     *
     * @param event {@link AbstractAuthenticationFailureEvent}.
     */
    @Profile(value = {"test", "native", "development"})
    @EventListener
    public void authFailedEventListener(AbstractAuthenticationFailureEvent event) {

        Authentication auth = event.getAuthentication();

        log.debug("DEBUG: LOGIN FAILED [CRED = {}] [PRINCIPAL = {}] ",
                auth.getCredentials(), auth.getPrincipal());
    }
}
