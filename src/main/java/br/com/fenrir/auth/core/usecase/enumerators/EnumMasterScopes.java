/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.core.usecase.enumerators;

/**
 * <p>
 *     Enum for common Roles in Auth Server.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public enum EnumMasterScopes {
    /**
     * <p>
     *     RoleDataMapper Master.
     * </p>
     */
    ROLE_MASTER("ROLE_MASTER", "Master role with all permissions."),
    /**
     * <p>
     *     RoleDataMapper Admin.
     * </p>
     */
    ROLE_ADMIN("ROLE_ADMIN", "Admin role of Tenant, contains permission of all user operations."),
    /**
     * <p>
     *     RoleDataMapper UserDataMapper.
     * </p>
     */
    ROLE_USER("ROLE_USER", "Base UserDataMapper role.");


    private final String name;
    private final String description;

    /**
     * <p>
     *     Constructor.
     * </p>
     */
    EnumMasterScopes(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getNameOfRule() {
        return this.name;
    }

    public String getDescriptionOfRule() {
        return this.description;
    }
}
