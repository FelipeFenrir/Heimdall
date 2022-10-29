/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.infrastructure.adapters.commons.config.web.impl;

import br.com.fenrir.auth.adapters.commons.config.auth.AuthProperties;
import br.com.fenrir.auth.adapters.commons.config.web.ServiceWebSecurityConfigurer;
import br.com.fenrir.auth.adapters.commons.constants.ResourcePathConstants;
import br.com.fenrir.auth.adapters.entrypoint.constants.PageConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * <p>
 *      WebSecurityConfig class.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final int TOKEN_VALIDITY_SECONDS = 24 * 60 * 60;

    @Autowired(required = false)
    private List<ServiceWebSecurityConfigurer> allWebSecurityConfigurer;

    @Autowired
    private UserDetailsService userDetailsService;

    private final String loginUrl = PageConstants.PATH_SEPARATOR.concat(PageConstants.LOGIN_PAGE);
    private final String profileUrl = PageConstants.PATH_SEPARATOR.concat(PageConstants.PROFILE_PAGE);
    private final String logoutSuccessUrl = PageConstants.PATH_SEPARATOR.concat(PageConstants.LOGIN_PAGE).concat(PageConstants.LOGOUT_CONDITION);

    /**
     * <p>
     *      Configure(HttpSecurity) allows configuration of web-based security at a
     *      resource level, based on a selection match - e.g. The example below
     * </p>
     * <p>
     *      Restricts the URLs that start with /admin/ to users that have ADMIN role,
     *      and declares that any other URLs need to be successfully authenticated.
     * </p>
     * @param http {@link HttpSecurity}
     * @throws Exception ApiError on configuration in web Security
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http
                // # Inicio - Exige solicitação HTTPS
                //                .requiresChannel()
                //                .anyRequest()
                //                .requiresSecure()
                //                .and()
                // # Fim -  Exige solicitação HTTPS
                .csrf()
                .ignoringAntMatchers(AuthProperties.OAUTH_PATH)
                .and()
                    .exceptionHandling()
                .and()
                    // # Enable CORS
                    .cors()
                .and()
                    // # Set login page
                    .formLogin()
                        .loginPage(loginUrl)
                        .permitAll()
                        .defaultSuccessUrl(profileUrl)
//                .and()
//                    .requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
                .and()
                    // # These requests are permitted without authorization
                    .authorizeRequests()
                        .antMatchers(
                                //"/", // # Index Page
                                PageConstants.NOT_LOGGED_PAGES)
                            .permitAll()
                        // # These requests are secured by the following way
                        .anyRequest()
                            .authenticated()
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                    // # Set logout handling
                    .logout()
                        .logoutSuccessUrl(logoutSuccessUrl)
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                    //.logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
                    .rememberMe()
                        .key("unique-and-secret")
                        .rememberMeCookieName("remember-me-cookie-name")
                        .tokenValiditySeconds(TOKEN_VALIDITY_SECONDS)
                .and()
                    // # Disable Frame Option in Headers
                    .headers()
                        .frameOptions()
                        .disable();
        // @formatter:on
    }

    /**
     * <p>
     * Configure(WebSecurity) is used for configuration settings that impact
     * global security (ignore resources, set debug mode, reject requests by
     * implementing a custom firewall definition). For example, the following
     * method would cause any request that starts with /resources/* to be
     * ignored for authentication purposes.
     * </p>
     * @param web {@link WebSecurity}.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers(ResourcePathConstants.PATH_SECURITY_PATTERNS);
        if (allWebSecurityConfigurer != null) {
            for (ServiceWebSecurityConfigurer serviceWebSecurityConfigurer : allWebSecurityConfigurer) {
                serviceWebSecurityConfigurer.configure(web);
            }
        }
    }

    /**
     * Encoder for Password.
     *
     * @return {@link PasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Configure Custom UserDataMapper and password Encoder in Web Security.
     * @param auth {@link AuthenticationManagerBuilder}.
     * @throws Exception ApiError in Authentication Builder Configure.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
//                .parentAuthenticationManager(authenticationManager())
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());

        // nullify parent auth manager to prevent infinite loop when userDetails not
        // found for username
//        auth.parentAuthenticationManager(null);
    }

    /**
     * Get Authentication Manager Bean.
     * @return {@link AuthenticationManager}.
     * @throws Exception ApiError in Authentication Manager.
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Configure Prefix Roles
//    /**
//     * Bean of RoleDataMapper Prefix Processor.
//     * @return {@link DefaultRolesPrefixPostProcessor}.
//     */
//    @Bean
//    public static DefaultRolesPrefixPostProcessor defaultRolesPrefixPostProcessor() {
//        return new DefaultRolesPrefixPostProcessor();
//    }

    // ## Configure Async SecurityContext Propagator.
//    /**
//     * Pool Task Executor Configuration.
//     * @return {@link ThreadPoolTaskExecutor}.
//     */
//    @Bean
//    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(10);
//        executor.setMaxPoolSize(100);
//        executor.setQueueCapacity(50);
//        executor.setThreadNamePrefix("async-");
//        return executor;
//    }
//
//    /**
//     * Configure Delegation of Security Context in Async.
//     * @param delegate {@link ThreadPoolTaskExecutor}.
//     * @return {@link DelegatingSecurityContextAsyncTaskExecutor}.
//     */
//    @Bean
//    public DelegatingSecurityContextAsyncTaskExecutor taskExecutor(ThreadPoolTaskExecutor delegate) {
//        return new DelegatingSecurityContextAsyncTaskExecutor(delegate);
//    }
    // ## End of Async SecurityContext Propagator
}
