/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.commons.config.auth;

//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * CustomLogoutSuccessHandler class.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
//@Slf4j
//@Component
public class CustomLogoutSuccessHandler {
//public class CustomLogoutSuccessHandler extends
//        AbstractAuthenticationTargetUrlRequestHandler
//        implements LogoutSuccessHandler
//{

//    private static final String BEARER_AUTHENTICATION = "Bearer ";
//    private static final String HEADER_AUTHORIZATION = "authorization";
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request,
//            HttpServletResponse response,
//            Authentication authentication)
//            throws IOException, ServletException {
//
//        String token = request.getHeader(HEADER_AUTHORIZATION);
//
//        if (token != null && token.startsWith(BEARER_AUTHENTICATION)) {
//            OAuth2AccessToken oAuth2AccessToken = tokenStore
//                    .readAccessToken(token.split(" ")[0]);
//            if (oAuth2AccessToken != null) {
//                tokenStore.removeAccessToken(oAuth2AccessToken);
//            }
//        }
//        response.setStatus(HttpServletResponse.SC_OK);
//    }
}
