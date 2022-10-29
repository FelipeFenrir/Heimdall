/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.infrastructure.adapters.commons.config.auth;

import br.com.fenrir.auth.adapters.commons.constants.ApiPathConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * <p>
 *     <h1>OAuth2ResourceServer class.</h1>
 * </p>
 * <p>
 *     Configure Resource Server.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@Configuration
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    /**
     * Configure HttpSecurity in ResourceServer.
     * @param http {@link HttpSecurity}
     * @throws Exception Configuration ApiError
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
                .disable()
                .anonymous()
            .and()
                .csrf()
                    .disable()
                    .anonymous()
            .and()
                 // # Enable CORS
                .cors()
            .and()
//                // These paths are secured by this SecurityFilterChain
                .requestMatchers()
                    .antMatchers(
                            ApiPathConstants.API_USER_URL,
                            ApiPathConstants.API_USER_URL.concat("/*"))
            .and()
                // These from the above are secured by the following way
                .authorizeRequests()
                    .anyRequest()
                    .authenticated()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
            .and()
                // # Disable Frame Option in Headers
                .headers()
                    .frameOptions()
                    .disable();
    }

    /**
     * Configure ResourceServer with ResourceID.
     * @param resources {@link ResourceServerSecurityConfigurer}
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(resourceId);
    }

    /**
     * Bean of RoleDataMapper Prefix Processor.
     * @return {@link DefaultRolesPrefixPostProcessor}.
     */
    @Bean
    public static DefaultRolesPrefixPostProcessor defaultRolesPrefixPostProcessor() {
        return new DefaultRolesPrefixPostProcessor();
    }

//    private static class OAuthRequestedMatcher implements RequestMatcher {
//        public boolean matches(HttpServletRequest request) {
//            String auth = request.getHeader("Authorization");
//            // Determine if the client request contained an OAuth Authorization
//            boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
//            boolean haveAccessToken = request.getParameter("access_token")!=null;
//            return haveOauth2Token || haveAccessToken;
//        }
//    }
}
