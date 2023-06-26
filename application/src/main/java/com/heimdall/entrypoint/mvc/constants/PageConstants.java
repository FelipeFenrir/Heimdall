/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.mvc.constants;

public class PageConstants {

    public static final String PATH_SEPARATOR = "/";

    public static final String PATH_REDIRECT = "redirect:/";

    public static final String INDEX_PAGE = "index";

    public static final String ME_PAGE = "me";

    public static final String PROFILE_PAGE = "profile";

    public static final String LOGIN_PAGE = "login";

    public static final String LOGOUT_MODE_PAGE = "performlogout";

    public static final String LOGOUT_PAGE = "logout";

    public static final String GLOBAL_LOGOUT_PAGE = "globalLogout";

    public static final String REGISTRATION_PAGE = "register";

    public static final String CONFIRMATION_PAGE = "confirm";

    public static final String CONFIRMATION_REDIRECT_PAGE = "confirmRedirect";

    public static final String FORGOTTEN_PASSWORD_PAGE = "forgotten";

    public static final String RESET_PASSWORD_PAGE = "resetpass";

    public static final String RESET_PASSWORD_REDIRECT_PAGE = "resetpassRedirect";

    public static final String VERIFY_EMAIL_PAGE = "verifyEmail";

    public static final String CHANGE_PASSWORD_PAGE = "changePassword";

    public static final String CHANGE_EMAIL_PAGE = "changeEmail";

    public static final String DELETE_ACCOUNT_PAGE = "deleteAccount";

    public static final String DELETE_ACCOUNT_SUCCESS_PAGE = "deleteSuccess";

    public static final String COMMON_ERROR_PAGE = "error";

    public static final String LOGOUT_CONDITION = "?logout";

    public static final String TOKEN_CONDITION = "?token";

    private PageConstants() {
        throw new IllegalStateException("Utility Class");
    }

    public static String getLoginPagePath() {
        return PageConstants.PATH_SEPARATOR
            .concat(PageConstants.LOGIN_PAGE);
    }

    public static String getProfilePagePath() {
        return PageConstants.PATH_SEPARATOR
            .concat(PageConstants.PROFILE_PAGE);
    }

    public static String getLogoutSucessPagePath() {
        return PageConstants.PATH_SEPARATOR
            .concat(PageConstants.LOGIN_PAGE)
            .concat(PageConstants.LOGOUT_CONDITION);
    }

    public static String[] getNotLoggedPages() {
        return new String[] {
            PageConstants.PATH_SEPARATOR.concat(PageConstants.LOGIN_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.REGISTRATION_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.CONFIRMATION_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.CONFIRMATION_REDIRECT_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.FORGOTTEN_PASSWORD_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.RESET_PASSWORD_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.RESET_PASSWORD_REDIRECT_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.VERIFY_EMAIL_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.COMMON_ERROR_PAGE)
        };
    }
}
