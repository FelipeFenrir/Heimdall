/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.mvc.constants;

public class MvObjectConstants {
    public static final String CONFIRMATION_MESSAGE = "confirmationMessage";
    public static final String SUCCESS_MESSAGE = "successMessage";
    public static final String WARNING_MESSAGE = "warningMessage";
    public static final String ERROR_MESSAGE = "errorMessage";

    public static final String SUCCESS_VERIFICATION = "success";
    public static final String MOBILE_VERIFICATION = "mobile";
    public static final String PASSWORD_ERROR_VERIFICATION = "passwordError";
    public static final String TERM_ERROR_VERIFICATION = "termError";
    public static final String INVALID_TOKEN_ERROR_VERIFICATION = "invalidToken";

    public static final String USER_OBJ = "user";
    public static final String TOKEN_OBJ = "token";
    public static final String CONFIRMATION_TOKEN_OBJ = "confirmationToken";

    public static final String LOGIN_LINK_PAGE = "loginLink";

    /**
     * <p>
     *     Private constructor to prevent instance this class.
     * </p>
     */
    private MvObjectConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
