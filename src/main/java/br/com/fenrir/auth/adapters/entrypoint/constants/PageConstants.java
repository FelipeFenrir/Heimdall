/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.constants;

/**
 * <p>
 *
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public class PageConstants {

    /**
     * <p>
     *     Path Separator.
     * </p>
     */
    public static final String PATH_SEPARATOR = "/";
    /**
     * <p>
     *     Redirect command.
     * </p>
     */
    public static final String PATH_REDIRECT = "redirect:/";

    /**
     * <p>
     *     Index page.
     * </p>
     */
    public static final String INDEX_PAGE = "index";
    /**
     * <p>
     *     Me page.
     * </p>
     */
    public static final String ME_PAGE = "me";
    /**
     * <p>
     *     Profile page.
     * </p>
     */
    public static final String PROFILE_PAGE = "profile";
    /**
     * <p>
     *     Login page.
     * </p>
     */
    public static final String LOGIN_PAGE = "login";
    /**
     * <p>
     *     Perform logout mode page.
     * </p>
     */
    public static final String LOGOUT_MODE_PAGE = "performlogout";
    /**
     * <p>
     *     Logout page.
     * </p>
     */
    public static final String LOGOUT_PAGE = "logout";
    /**
     * <p>
     *     Global logout page.
     * </p>
     */
    public static final String GLOBAL_LOGOUT_PAGE = "globalLogout";
    /**
     * <p>
     *     Register page.
     * </p>
     */
    public static final String REGISTRATION_PAGE = "register";
    /**
     * <p>
     *     Register confirmation page.
     * </p>
     */
    public static final String CONFIRMATION_PAGE = "confirm";
    /**
     * <p>
     *     Redirect confirmation page.
     * </p>
     */
    public static final String CONFIRMATION_REDIRECT_PAGE = "confirmRedirect";
    /**
     * <p>
     *     Forgotten password page.
     * </p>
     */
    public static final String FORGOTTEN_PASSWORD_PAGE = "forgotten";
    /**
     * <p>
     *     Reset password page.
     * </p>
     */
    public static final String RESET_PASSWORD_PAGE = "resetpass";
    /**
     * <p>
     *     Reset password redirection page.
     * </p>
     */
    public static final String RESET_PASSWORD_REDIRECT_PAGE = "resetpassRedirect";
    /**
     * <p>
     *     Verification email page.
     * </p>
     */
    public static final String VERIFY_EMAIL_PAGE = "verifyEmail";
    /**
     * <p>
     *    Change password page.
     * </p>
     */
    public static final String CHANGE_PASSWORD_PAGE = "changePassword";
    /**
     * <p>
     *     Change email page.
     * </p>
     */
    public static final String CHANGE_EMAIL_PAGE = "changeEmail";
    /**
     * <p>
     *     Delete account page.
     * </p>
     */
    public static final String DELETE_ACCOUNT_PAGE = "deleteAccount";
    /**
     * <p>
     *     Delete account success page.
     * </p>
     */
    public static final String DELETE_ACCOUNT_SUCCESS_PAGE = "deleteSuccess";
    /**
     * <p>
     *     Common error page.
     * </p>
     */
    public static final String COMMON_ERROR_PAGE = "error";

    /**
     * <p>
     *     Logout conditional query.
     * </p>
     */
    public static final String LOGOUT_CONDITION = "?logout";
    /**
     * <p>
     *     Token conditional query.
     * </p>
     */
    public static final String TOKEN_CONDITION = "?token";

    /**
     * <p>
     *     List of 'not logged area" pages.
     * </p>
     */
    public static final String[] NOT_LOGGED_PAGES = {
            PageConstants.PATH_SEPARATOR.concat(PageConstants.LOGIN_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.REGISTRATION_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.CONFIRMATION_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.CONFIRMATION_REDIRECT_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.FORGOTTEN_PASSWORD_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.RESET_PASSWORD_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.RESET_PASSWORD_REDIRECT_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.VERIFY_EMAIL_PAGE),
            PageConstants.PATH_SEPARATOR.concat(PageConstants.COMMON_ERROR_PAGE)};

    /**
     * <p>
     *     Private constructor to prevent instance this class.
     * </p>
     */
    private PageConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
