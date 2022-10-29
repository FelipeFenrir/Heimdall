/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.infrastructure.adapters.commons.config.database;

import br.com.fenrir.auth.adapters.commons.config.web.ServiceWebSecurityConfigurer;
import br.com.fenrir.commons.utils.enums.EnumInfraProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

/**
 * This class implements security for H2 resources in project.
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@Profile(value = {
        EnumInfraProfile.ProfileConstants.TEST_ENVIRONMENT,
        EnumInfraProfile.ProfileConstants.NATIVE_ENVIRONMENT
})
@ConditionalOnProperty(value = "spring.h2.console.enabled", havingValue = "true")
@Configuration
public class H2WebSecurityConfig implements ServiceWebSecurityConfigurer {

    {
        log.info("INFO: H2 WebSecurity Configuration.");
    }

    @Autowired
    private H2Properties h2Properties;

    @Override
    public void configure(HttpSecurity http) throws Exception { }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(h2Properties.getH2Path());
    }
}
