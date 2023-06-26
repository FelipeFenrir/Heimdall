/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.configuration.web;

import com.heimdall.configuration.security.properties.AuthProperties;

import lombok.AllArgsConstructor;

import org.jetbrains.annotations.NotNull;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.time.ZoneOffset;
import java.util.Locale;
import java.util.TimeZone;

@AllArgsConstructor
@Configuration
public class WebMvcConfig extends DelegatingWebMvcConfiguration {

    private static final String LANGUAGE_PARAM = "lang";
    public static final Locale DEFAULT_LOCALE = Locale.US;
    public static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone(ZoneOffset.UTC);

    private static final String[] ALLOWED_METHODS = {"POST", "GET", "PUT", "OPTIONS", "DELETE"};
    private static final String[] ALLOWED_HEADERS = {
        "Authorization",
        "Origin",
        "X-Requested-With",
        "Content-Type",
        "Accept",
        "remember-me"
    };

    private final AuthProperties authProperties;

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        super.addInterceptors(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //CUSTOM OAUTH CONFIRM ACCESS
        registry
            .addViewController(AuthProperties.CONFIRM_ACCESS)
            .setViewName(AuthProperties.AUTHORIZE_VIEW);
        super.addViewControllers(registry);
    }

    @Override
    public void addResourceHandlers(@Nullable ResourceHandlerRegistry registry) {
        if (registry != null) {
            // add Resource Handler and location for WebJar and other statics
            registry
                .addResourceHandler(ResourcePathConstants.PATH_PATTERNS)
                .addResourceLocations(ResourcePathConstants.RESOURCE_LOCATION);
            super.addResourceHandlers(registry);
        }
    }

    @NotNull
    @Bean(name = "localeResolver")
    @Override
    public LocaleResolver localeResolver() {
//        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
//        resolver.setDefaultLocale(DEFAULT_LOCALE);
//        resolver.setDefaultTimeZone(DEFAULT_TIMEZONE);
//        return resolver;

        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(DEFAULT_LOCALE);
        resolver.setDefaultTimeZone(DEFAULT_TIMEZONE);
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName(LANGUAGE_PARAM);
        return interceptor;
    }
}
