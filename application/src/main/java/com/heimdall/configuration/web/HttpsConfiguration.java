/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.configuration.web;

import lombok.extern.slf4j.Slf4j;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Slf4j
@Profile(value = {
        "test",
        "native",
        "development"
        })
@ConditionalOnProperty(value = "server.ssl.enabled", havingValue = "true")
@Configuration
public class HttpsConfiguration {

    @Value("${server.port}")
    private int serverPort;

    @Value("${server.http.port}")
    private int httpPort;

    @Value("${server.http.scheme}")
    private String httpScheme;

    private static final String PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";
    private static final String USER_CONSTRAINT = "CONFIDENTIAL";
    private static final String PATTERN = "/*";

    {
        log.info("INFO: Initialyzing HTTPS Configuration.");
    }

    /**
     * Enable SSL Trafic.
     *
     * @return {@link ServletWebServerFactory}
     */
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint(USER_CONSTRAINT);
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern(PATTERN);
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
        return tomcat;
    }

    /**
     * Method to Redirect HTTP to HTTPS.
     * <br>
     * Example:
     * <br>
     * <p>
     * We need to redirect from HTTP to HTTPS. Without SSL, this application
     * used
     * <br>port server.port. With SSL it will use port server.http.port. So, any request for server.port
     * needs to be
     * <br>redirected to HTTPS on server.http.port.</p>
     */
    private Connector httpToHttpsRedirectConnector() {
        Connector connector = new Connector(PROTOCOL);
        connector.setScheme(httpScheme);
        connector.setPort(httpPort);
        connector.setSecure(false);
        connector.setRedirectPort(serverPort);
        return connector;
    }
}

