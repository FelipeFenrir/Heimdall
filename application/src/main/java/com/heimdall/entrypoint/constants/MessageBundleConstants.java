/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.constants;

/**
 * <p>
 *     Message Bundle attribute name constants references.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public class MessageBundleConstants {
    public static final String COMMON_ERROR_INSTANCE_AUTH = "common.error.instanceAuth";

    public static final String TERM_NOT_ACCEPTED = "term.notaccepted";

    public static final String PASSWORD_NOT_MATCHING = "password.notMatching";
    public static final String PASSWORD_NOT_UNIQUE = "password.notUnique";
    public static final String PASSWORD_INCORRECT = "password.incorrect";
    public static final String PASSWORD_CHANGE_SUCCESS = "password.changeSuccess";

    public static final String EMAIL_TITLE = "email.title";
    public static final String EMAIL_REGISTRATION = "email.registration";
    public static final String EMAIL_REGISTRATION_SUBJECT = "email.registration.subject";
    public static final String EMAIL_RESET_PASSWORD = "email.resetPassword";
    public static final String EMAIL_CHANGE_SUCCESS = "email.changeSuccess";
    public static final String EMAIL_CHANGE_FAILURE = "email.changeFailure";
    public static final String EMAIL_VERIFICATION = "email.verification";
    public static final String EMAIL_VERIFICATION_SUCCESS = "email.verificationSuccess";
    public static final String EMAIL_VERIFICATION_FAILURE = "email.verificationFailure";

    public static final String USER_NOT_REGISTERED = "registration.userNotRegistered";
    public static final String EMAIL_ALREADY_REGISTERED = "registration.emailExists";
    public static final String REGISTRATION_CONFIRMATION_EMAIL = "registration.confirmationEmail";
    public static final String PASSWORD_MATCHING = "registration.passwordSuccess";
    public static final String RESET_PASSWORD_EMAIL = "registration.passwordResetEmail";

    public static final String INVALID_TOKEN = "registration.invalidToken";
    public static final String EXPIRED_TOKEN = "forgottenPassword.expiredToken";

    public static final String LOGOUT_GLOBAL_CONFIRMATION = "logout.globalConfirmation";

    public static final String DELETE_WARNING = "delete.warning";
    public static final String DELETE_SUCCESS = "delete.success";

    public static final String API_COMMON_RESPONSE_NO_CONTENT = "api.common.response.204.nocontent";
    public static final String API_COMMON_RESPONSE_NOT_FOUND = "api.common.response.404.resource.notfound";
    public static final String API_COMMON_RESPONSE_BAD_REQUEST_TYPE_MISMATCH =
            "api.common.response.400.badrequest.typemismatch";
    public static final String API_COMMON_RESPONSE_BAD_REQUEST = "api.common.response.400.badrequest";

    /**
     * <p>
     *     Private constructor to prevent instance this class.
     * </p>
     */
    private MessageBundleConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
