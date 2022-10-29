/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.service;

import com.heimdall.ports.integration.TokenGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import java.util.Collection;

/**
 * <p>
 *      Token Service implementation.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
//@Service
@AllArgsConstructor
public class TokenService implements TokenGateway {

    //@Autowired
    private TokenStore tokenStore;

    @Override
    public void revokeTokens(String username) {
        log.info("Revoking tokens for {}", username);

        if (!(tokenStore instanceof JdbcTokenStore)) {
            log.info("Token store is not instance of JdbcTokenStore. Cannot revoke tokens!");
            return;
        }

        Collection<OAuth2AccessToken> tokens =
                ((JdbcTokenStore) tokenStore).findTokensByUserName(username);

        for (OAuth2AccessToken token : tokens) {
            log.debug("Revoking access token {}", token);
            tokenStore.removeAccessToken(token);

            log.debug("Revoking refresh token {}", token.getRefreshToken());
            tokenStore.removeRefreshToken(token.getRefreshToken());
        }
    }
}
