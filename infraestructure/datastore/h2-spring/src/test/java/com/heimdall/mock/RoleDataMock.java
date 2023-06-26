package com.heimdall.mock;

import com.heimdall.entity.RoleDataEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleDataMock {

    public static final String ROLE_NAME_MASTER = "MASTER";
    public static final String ROLE_NAME_ADMIN = "ADMIN";
    public static final String ROLE_NAME_USER = "USER";

    public static final String TEMPLATE_NEWVALID_MASTER = "newValidMaster";
    public static final String TEMPLATE_NEWVALID_ADMIN = "newValidAdmin";
    public static final String TEMPLATE_NEWVALID_USER = "newValidUser";

    public static RoleDataEntity newValidMaster() {
        var role = new RoleDataEntity();
        role.setName(ROLE_NAME_MASTER);
        return role;
    }

    public static RoleDataEntity newValidAdmin() {
        var role = new RoleDataEntity();
        role.setName(ROLE_NAME_ADMIN);
        return role;
    }

    public static RoleDataEntity newValidUser() {
        var role = new RoleDataEntity();
        role.setName(ROLE_NAME_USER);
        return role;
    }
}
