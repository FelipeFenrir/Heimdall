/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.infrastructure.adapters.commons.config.auth;

import br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper.UserDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * CustomTokenEnhancer class.
 * This class customize the JWT Token.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    /**
     * Enhance Authentication Token.
     * @param accessToken {@link OAuth2AccessToken}
     * @param authentication {@link OAuth2Authentication}
     * @return {@link OAuth2AccessToken}
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
            OAuth2Authentication authentication) {
        UserDataMapper userDataMapper = (UserDataMapper) authentication.getPrincipal();

        Map<String, Object> info = new LinkedHashMap<>(
                accessToken.getAdditionalInformation());

        //info.put("username", userDataMapper.getUsername());
        //info.put("email", userDataMapper.getEmail());
        info.put("tenant", userDataMapper.getTenantid());

        log.debug("Add tenant UUID( {} ) in Token of UserDataMapper {}.", userDataMapper.getTenantid(), userDataMapper.getUsername());

        DefaultOAuth2AccessToken customAccessToken =
                new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);

        return super.enhance(customAccessToken, authentication);
    }
}
