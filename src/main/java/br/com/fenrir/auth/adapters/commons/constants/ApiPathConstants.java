/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.commons.constants;

/**
 * API Contsnts.
 *
 * @author Felipe de Andrade Batista
 */
public class ApiPathConstants {
    /**
     * USERS API.
     */
    public static final String API_USER_URL = "/api/v1/users";

    /**
     * LIST OF API.
     */
    public static final String[] ANT_PATTERNS = {
            API_USER_URL
    };

    /**
     * <p>
     *     Private constructor to prevent instance this class.
     * </p>
     */
    private ApiPathConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
