/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.commons.config.auth;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailedService;
//import org.springframework.security.oauth2.common.OAuth2RefreshToken;
//import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.DefaultSecurityContextAccessor;
//import org.springframework.security.oauth2.provider.SecurityContextAccessor;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
//import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

/**
 * <p>
 * CustomOauth2RequestFactory class. Class to customize requests from Oauth2
 * provider.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@Component
public class CustomOauth2RequestFactory extends DefaultOAuth2RequestFactory {

//    @Autowired
//    private TokenStore tokenStore;
//
//    @Autowired
//    private UserDetailedService userDetailsService;
//
//    private SecurityContextAccessor securityContextAccessor
//            = new DefaultSecurityContextAccessor();

    /**
     * Construtor Method.
     *
     * @param clientDetailsService {@link ClientDetailsService}
     */
    public CustomOauth2RequestFactory(ClientDetailsService clientDetailsService) {
        super(clientDetailsService);
    }

    @Override
    public TokenRequest createTokenRequest(Map<String, String> requestParameters,
            ClientDetails authenticatedClient) {
        if (requestParameters.get("grant_type").equals("refresh_token")) {
            log.debug("DEBUG: CustomOauth2RequestFactory - createTokenRequest {}",
                    requestParameters.get("grant_type"));

//            OAuth2RefreshToken refreshtoken
//                    = tokenStore.readRefreshToken(
//                            requestParameters.get("refresh_token"));
//            OAuth2Authentication authentication
//                    = tokenStore.readAuthenticationForRefreshToken(refreshtoken);
//            SecurityContextHolder.getContext()
//                    .setAuthentication(
//                            new UsernamePasswordAuthenticationToken(
//                                    authentication.getName(),
//                                    null,
//                                    userDetailsService.loadUserByUsername(
//                                            authentication.getName()
//                                    ).getAuthorities()
//                            )
//                    );
        }
        return super.createTokenRequest(requestParameters, authenticatedClient);
    }

//    @Override
//    public TokenRequest createTokenRequest(AuthorizationRequest authorizationRequest,
//            String grantType) {
//        log.debug("DEBUG: CustomOauth2RequestFactory - createTokenRequest {}",
//                grantType);
//        return super.createTokenRequest(authorizationRequest, grantType);
//    }

//    @Override
//    public void setSecurityContextAccessor(SecurityContextAccessor securityContextAccessor) {
//        this.securityContextAccessor = securityContextAccessor;
//        super.setSecurityContextAccessor(securityContextAccessor);
//    }

//    @Override
//    public AuthorizationRequest createAuthorizationRequest(Map<String, String> authorizationParameters) {
//        AuthorizationRequest request = super.createAuthorizationRequest(authorizationParameters);
//        log.debug("DEBUG: CustomOauth2RequestFactory - getAuthorities {}",
//                securityContextAccessor.getAuthorities());
//        if (securityContextAccessor.isUser()) {
//            request.setAuthorities(securityContextAccessor.getAuthorities());
//        }
//
//        return request;
//    }
}
