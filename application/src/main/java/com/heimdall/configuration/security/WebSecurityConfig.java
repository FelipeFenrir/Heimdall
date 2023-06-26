/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.configuration.security;

import com.heimdall.configuration.security.properties.AuthProperties;
import com.heimdall.configuration.security.token.JWTConverter;
import com.heimdall.configuration.web.ResourcePathConstants;
import com.heimdall.entrypoint.mvc.constants.PageConstants;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
public class WebSecurityConfig {

    private static final int TOKEN_VALIDITY_SECONDS = 24 * 60 * 60;

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    public WebSecurityConfig(
        UserDetailsService userDetailsService,
        PasswordEncoder passwordEncoder
    ) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.userDetailsService);
        authProvider.setPasswordEncoder(this.passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        OAuth2AuthorizationServerConfiguration
            .applyDefaultSecurity(http);

        // @formatter:off
        http
            // # Inicio - Exige solicitação HTTPS
            //                .requiresChannel()
            //                .anyRequest()
            //                .requiresSecure()
            //                .and()
            // # Fim -  Exige solicitação HTTPS
            .csrf()
                .ignoringRequestMatchers(AuthProperties.OAUTH_PATH)
            .and()
                .exceptionHandling()
            .and()
                // # Enable CORS
                .cors()
            .and()
                // # Set login page
                .formLogin()
                .loginPage(PageConstants.getLoginPagePath())
                .permitAll()
                .defaultSuccessUrl(PageConstants.getProfilePagePath())
            .and()
                // # These requests are permitted without authorization
                .authorizeHttpRequests()
                    .requestMatchers(PageConstants.getNotLoggedPages()).permitAll()
                // # These requests are secured by the following way
                .anyRequest().authenticated()
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
                // # Set logout handling
                .logout()
                .logoutSuccessUrl(PageConstants.getLogoutSucessPagePath())
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                //.logoutSuccessHandler(customLogoutSuccessHandler)
            .and()
                .rememberMe()
                .key("unique-and-secret")
                .rememberMeCookieName("remember-me-cookie-name")
                .tokenValiditySeconds(TOKEN_VALIDITY_SECONDS)
            .and()
                // # fix H2 database console: Refused to display ' in a frame because it set 'X-Frame-Options' to 'deny'
                .headers()
                .frameOptions()
                .sameOrigin();
        // @formatter:on

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(ResourcePathConstants.PATH_SECURITY_PATTERNS);
    }
}
