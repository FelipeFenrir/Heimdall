/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.mvc.controller;

import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.Locale;

/**
 * <p>
 *     MVC Controller for user interaction pages.
 * </p>
 *
 * @author Felipe de Amdrade Batista
 */
public interface IUserController {

    /**
     * <p>
     *     Show User profile page.
     * </p>
     *
     * @return Profile page name.
     */
    String showProfilePage();

    /**
     * <p>
     *     Show the "Me" page, this is "My Profile Page".
     * </p>
     *
     * @param modelAndView {@link ModelAndView} object.
     * @param principal {@link Principal} object.
     * @param locale {@link Locale} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView showMePage(ModelAndView modelAndView, Principal principal, Locale locale);

    /**
     * <p>
     *     Show delete account page.
     * </p>
     *
     * @param locale {@link Locale} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView showDeleteAccountPage(Locale locale);

    /**
     * <p>
     *     Process account deletion.
     * </p>
     *
     * @param principal {@link Principal} object.
     * @param locale {@link Locale} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView processDeleteAccount(Principal principal, Locale locale);

    /**
     * <p>
     *     Show success deleted account page.
     * </p>
     *
     * @param request {@link HttpServletRequest} object.
     * @param locale {@link Locale} object.
     * @param success Indicative success flag.
     * @return {@link ModelAndView} object.
     */
    ModelAndView showDeleteSuccessPage(HttpServletRequest request, Locale locale, boolean success);
}
