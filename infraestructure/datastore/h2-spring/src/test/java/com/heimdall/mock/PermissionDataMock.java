package com.heimdall.mock;

import com.heimdall.entity.PermissionDataEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PermissionDataMock {

    public static final String PERMISSION_NAME = "all";

    public static final String TEMPLATE_NEWVALID = "new_valid";

    public static PermissionDataEntity newValid() {
        var permission = new PermissionDataEntity();
        permission.setName(PERMISSION_NAME);
        return permission;
    }
}
