/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.commons.config.web.impl;

import br.com.fenrir.auth.adapters.commons.config.auth.AuthProperties;
import br.com.fenrir.auth.adapters.commons.constants.ResourcePathConstants;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * <p>
 * WebConfig class.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private static final String LANGUAGE_PARAM = "lang";

    private static final String[] ALLOWED_METHODS = {"POST", "GET", "PUT", "OPTIONS", "DELETE"};
    private static final String[] ALLOWED_HEADERS = {
            "Authorization", "Origin", "X-Requested-With",
            "Content-Type", "Accept", "remember-me"};

    @Autowired
    private AuthProperties authProperties;

    /**
     * CORS Filter in Web Config.
     *
     * @param registry {@link CorsRegistry}
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // @formatter:off
        registry
                .addMapping("/**")
                .allowedOrigins(authProperties.getCorsAllowedOrigins())
                .allowCredentials(true)
                .allowedMethods(ALLOWED_METHODS)
                //.maxAge(3600)
                .allowedHeaders(ALLOWED_HEADERS);
        // @formatter:on
    }

    /**
     * Add locale all custom interceptors.
     *
     * @param registry {@link InterceptorRegistry}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * Config View controllers and View Names.
     *
     * @param registry {@link ViewControllerRegistry}
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //CUSTOM OAUTH CONFIRM ACCESS
        registry.addViewController(AuthProperties.CONFIRM_ACCESS).setViewName(AuthProperties.AUTHORIZE_VIEW);
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    /**
     * Config Ressource Handler.
     *
     * @param registry {@link ResourceHandlerRegistry}
     */
    @Override
    public void addResourceHandlers(@Nullable ResourceHandlerRegistry registry) {
        if (registry != null) {
            // add Resource Handler and location for WebJar and other statics
            registry
                    .addResourceHandler(ResourcePathConstants.PATH_PATTERNS)
                    .addResourceLocations(ResourcePathConstants.RESOURCE_LOCATION);
        }
    }

    /**
     * <p>
     * Resolver for localizations.
     * </p>
     *
     * @return {@link LocaleResolver}
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    /**
     * <p>
     * Localization interceptors.
     * </p>
     *
     * @return {@link LocaleChangeInterceptor}
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName(LANGUAGE_PARAM);
        return lci;
    }
}
