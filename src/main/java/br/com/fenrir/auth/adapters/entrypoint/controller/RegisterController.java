/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.controller;

import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations.UserBoundaryMapper;

import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.Locale;

/**
 * <p>
 *     MVC Controller for registration interaction pages.
 * </p>
 *
 * @author Felipe de Amdrade Batista
 */
public interface RegisterController {
    /**
     * <p>
     *     Show Registration page.
     * </p>
     * @param modelAndView {@link ModelAndView} object.
     * @param user {@link UserBoundaryMapper} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView showRegistrationPage(ModelAndView modelAndView, UserBoundaryMapper user);

    /**
     * <p>
     *     Process Registration Form.
     * </p>
     * @param modelAndView {@link ModelAndView} object.
     * @param user  {@link UserBoundaryMapper} object.
     * @param locale {@link Locale} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid UserBoundaryMapper user, Locale locale);

    /**
     * <p>
     *     Show confirmation page for registration.
     * </p>
     * @param modelAndView {@link ModelAndView} object.
     * @param token Register confirmation token.
     * @param mobile Mobile verification flag.
     * @param passwordError Error on password verification flag.
     * @param termError Error on term is not acceptable.
     * @param locale {@link Locale} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView showConfirmationPage(ModelAndView modelAndView, String token, boolean mobile, boolean passwordError,
                                      boolean termError, Locale locale);

    /**
     * <p>
     *     Redirect to confirmation page.
     * </p>
     * @param token Register confirmation token.
     * @return Page name to redirect.
     */
    String showRedirectConfirmationPage(String token);

    /**
     * <p>
     *     Process confirmation form.
     * </p>
     * @param modelAndView {@link ModelAndView} object.
     * @param token Register confirmation token.
     * @param password New Password.
     * @param confirmPassword repeat the new Password.
     * @param term Flag indicate if term is acceptable.
     * @param locale {@link Locale} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView processConfirmationForm(ModelAndView modelAndView, String token, String password,
                                         String confirmPassword, Boolean term, Locale locale);

}
