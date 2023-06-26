/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.mvc.controller;

import com.heimdall.entrypoint.boundary.dto.implementations.UserDto;

import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Locale;

/**
 * <p>
 *     MVC Controller for credentials interaction pages.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface ICredentialsController {

    /**
     * <p>
     *     Show forgotten password page.
     * </p>
     * @param modelAndView {@link ModelAndView} object.
     * @param user {@link UserDto} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView showForgottenPasswordPage(ModelAndView modelAndView, UserDto user);

    /**
     * <p>
     *      Process forgotten password form.
     * </p>
     * @param modelAndView {@link ModelAndView} object.
     * @param user {@link UserDto} object.
     * @param locale {@link Locale} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView processForgottenPasswordForm(ModelAndView modelAndView, UserDto user, Locale locale);

    /**
     * <p>
     *      Show reset password page.
     * </p>
     * @param modelAndView {@link ModelAndView} object.
     * @param token
     * @param mobile
     * @param passwordError
     * @param locale {@link Locale} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView showResetPassPage(ModelAndView modelAndView, String token, boolean mobile, boolean passwordError,
                                   Locale locale);

    /**
     * <p>
     *      Redirect reset password page.
     * </p>
     * @param token
     * @return
     */
    String showRedirectResetPassPage(String token);

    /**
     * <p>
     *      Process reset password form.
     * </p>
     * @param modelAndView {@link ModelAndView} object.
     * @param token
     * @param password
     * @param confirmPassword
     * @param locale {@link Locale} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView processResetPassForm(ModelAndView modelAndView, String token, String password, String confirmPassword,
                                      Locale locale);

    /**
     * <p>
     *      Change password.
     * </p>
     * @return {@link ModelAndView} object.
     */
    ModelAndView changePassword();

    /**
     * <p>
     *      Process password change.
     * </p>
     * @param modelAndView {@link ModelAndView} object.
     * @param currentPassword
     * @param newPassword
     * @param confirmPassword
     * @param locale {@link Locale} object.
     * @param principal
     * @return {@link ModelAndView} object.
     */
    ModelAndView processPasswordChange(ModelAndView modelAndView, String currentPassword, String newPassword,
                                       String confirmPassword, Locale locale, Principal principal);

    /**
     * <p>
     *      Change email.
     * </p>
     * @return {@link ModelAndView} object.
     */
    ModelAndView changeEmail();

    /**
     * <p>
     *      Process email change.
     * </p>
     * @param modelAndView {@link ModelAndView} object.
     * @param password
     * @param email
     * @param locale {@link Locale} object.
     * @param principal
     * @return {@link ModelAndView} object.
     */
    ModelAndView processEmailChange(ModelAndView modelAndView, String password, String email, Locale locale,
                                    Principal principal);

    /**
     * <p>
     *      Show verification email page.
     * </p>
     * @param modelAndView {@link ModelAndView} object.
     * @param token
     * @param locale {@link Locale} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView showVerifyEmailPage(ModelAndView modelAndView, String token, Locale locale);
}
