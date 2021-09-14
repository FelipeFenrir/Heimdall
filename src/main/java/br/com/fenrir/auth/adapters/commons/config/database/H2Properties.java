/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.commons.config.database;

import br.com.fenrir.commons.utils.enums.EnumInfraProfile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


/**
 * Properties of H2 Database.
 * @author Felipe de Andrade Batista
 */
@Profile(value = {
        EnumInfraProfile.ProfileConstants.TEST_ENVIRONMENT,
        EnumInfraProfile.ProfileConstants.NATIVE_ENVIRONMENT
})
@Getter
@Setter
@Component
public class H2Properties {

    @Value("${spring.h2.console.enabled:false}")
    private boolean isEnabled;

    @Value("${spring.h2.console.path:/h2}")
    private String h2BasePath;

    /**
     * Get Path and Content Path expression of H2 Database.
     * @return Path and Content Path expression of H2 Database.
     */
    public String[] getH2Path() {
        return new String[] {h2BasePath.concat("**"), h2BasePath.concat("/**")};
    }
}