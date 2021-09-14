/*
 * Copyright (c) 2020. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth;

import br.com.fenrir.commons.utils.EnvUtil;
import br.com.fenrir.multitenant.MultiTenantFilter;
import br.com.fenrir.multitenant.TenantIdentifierResolver;
import br.com.fenrir.multitenant.TokenMultiTenantFilter;
import br.com.fenrir.multitenant.utils.MultiTenantConstants;

import java.net.InetAddress;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;

/**
 * Main Class of Auth Server.
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@SpringBootApplication
@ComponentScan(excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        MultiTenantConstants.class,
        MultiTenantFilter.class,
        TenantIdentifierResolver.class,
        TokenMultiTenantFilter.class
    })
}, basePackages = {"br.com.fenrir"})
public class AuthServer {

    private static final String TIME_ZONE = "Etc/UTC";

    {
        log.info("Initializing Auth-Server.");
    }

    @Autowired
    private Environment environment;

    /**
     * Constructor.
     */
    public AuthServer() {
    }

    /**
     * Main method for run app.
     *
     * @param args Initialization Arguments.
     */
    public static void main(String[] args) {
        log.info("Initializing Application Context.");
        SpringApplication.run(AuthServer.class, args);
        log.info("Ready-to-use Auth-Server.");
    }

    /**
     * Set Default TimeZone.
     */
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));

        EnvUtil.setServerPort(environment.getProperty("server.port"));
        EnvUtil.setHttpPort(environment.getProperty("server.http.port"));
        EnvUtil.setApplicationPath(environment.getProperty("server.servlet.context-path"));
        EnvUtil.setHostAddress(InetAddress.getLoopbackAddress().getHostAddress());
        EnvUtil.setHostName(InetAddress.getLoopbackAddress().getHostName());
    }
}
