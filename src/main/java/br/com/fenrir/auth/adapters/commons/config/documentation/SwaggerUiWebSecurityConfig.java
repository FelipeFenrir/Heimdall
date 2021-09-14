/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.commons.config.documentation;

import br.com.fenrir.auth.adapters.commons.config.web.ServiceWebSecurityConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

/**
 * This class implements security for Swagger resources in project.
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@ConditionalOnProperty(value = "app.swagger.enabled", havingValue = "true")
@Configuration
public class SwaggerUiWebSecurityConfig implements ServiceWebSecurityConfigurer {

    {
        log.info("INFO: Swagger WebSecurity Configuration.");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception { }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers(SwaggerProperties.AUTH_WHITELIST);
    }
}
