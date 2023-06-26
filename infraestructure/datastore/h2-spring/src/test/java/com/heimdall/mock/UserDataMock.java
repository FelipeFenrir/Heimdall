package com.heimdall.mock;

import com.heimdall.entity.UserDataEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.UUID;

@UtilityClass
public class UserDataMock {

    public static final String USER_USERNAME = "teste";
    public static final String USER_EMAIL = "teste@teste.com.br";
    public static final String USER_PENDINGEMAIL = "teste@teste.com.br";
    public static final String USER_PASSWORD = "teste";
    public static final String USER_TENANTID = "teste";
    public static final Boolean USER_ENABLED = true;
    public static final String USER_CONFIRMATIONTOKEN = UUID.randomUUID().toString();
    public static final Boolean USER_ACCEPTTERM = true;
    public static final Boolean USER_ACCOUNTNONLOCKED = true;
    public static final Boolean USER_ACCOUNTNONEXPIRED = true;
    public static final Boolean USER_CREDENTIALSNONEXPIRED = true;

    public static final String TEMPLATE_NEWVALID = "new_valid";
    public static final String TEMPLATE_NEWVALID_COMPLETE = "new_valid_complete";

    public static UserDataEntity newValid() {
        var user = new UserDataEntity();
        user.setUsername(USER_USERNAME)
            .setEmail(USER_EMAIL)
            .setPassword(USER_PASSWORD)
            .setTenantid(USER_TENANTID)
            .setEnabled(USER_ENABLED)
            .setAceptTerm(USER_ACCEPTTERM);
        return user;
    }

    public static UserDataEntity newValidComplete() {

        var roles = List.of(
            RoleDataMock.newValidMaster(),
            RoleDataMock.newValidAdmin(),
            RoleDataMock.newValidUser()
        );

        var user = new UserDataEntity();
        user.setUsername(USER_USERNAME)
            .setEmail(USER_EMAIL)
            .setPendingEmail(USER_PENDINGEMAIL)
            .setPassword(USER_PASSWORD)
            .setTenantid(USER_TENANTID)
            .setEnabled(USER_ENABLED)
            .setConfirmationToken(USER_CONFIRMATIONTOKEN)
            .setAceptTerm(USER_ACCEPTTERM)
            .setAccountNonLocked(USER_ACCOUNTNONLOCKED)
            .setAccountNonExpired(USER_ACCOUNTNONEXPIRED)
            .setCredentialsNonExpired(USER_CREDENTIALSNONEXPIRED)
            .setRoleDataMappers(roles);
        return user;
    }
}
