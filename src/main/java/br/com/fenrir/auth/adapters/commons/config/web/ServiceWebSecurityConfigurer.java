/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.commons.config.web;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

/**
 * This interface apply the Spring Configuration concept for HTTPSecurity and WebSecurity Configuration.
 *
 * @author Felipe de Andrade Batista
 */
public interface ServiceWebSecurityConfigurer {

    /**
     * Implements all rules for Security in HTTPSecurity.
     * @param http {@link HttpSecurity} object.
     * @throws Exception Error in configuration.
     */
    void configure(HttpSecurity http) throws Exception;

    /**
     * Implements all rules for Security in WebSecurity.
     * @param web {@link WebSecurity} object.
     * @throws Exception Error in configuration.
     */
    void configure(WebSecurity web) throws Exception;
}
