/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.controller;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.Locale;

/**
 * <p>
 *     MVC Controller for login and logout interaction pages.
 * </p>
 *
 * @author Felipe de Amdrade Batista
 */
public interface AuthenticationController {

    /**
     * <p>
     *      Show index page.
     * </p>
     * @return Index page reference.
     */
    String showHomePage();

    /**
     * <p>
     *      Return login page or redirect user to profile if already logged in.
     * </p>
     * @return Login page reference or Profile page reference
     */
    String redirectLoginPage();

    /**
     * <p>
     *      Redirect to logout mode page.
     * </p>
     * @return Logout mode page reference.
     */
    String redirectLogoutPage();

    /**
     * <p>
     *      Perform simple logout event.
     *      One click logout with invalidate session.
     * </p>
     * @param request {@link HttpServletRequest} object.
     * @return Logout page reference.
     */
    String performLogout(HttpServletRequest request);

    /**
     * <p>
     *     Perform global logout event.
     *     Invalidate session and revokes all tokens.
     * </p>
     * @param principal {@link Principal} object.
     * @param locale {@link Locale} object.
     * @return {@link ModelAndView} object.
     */
    ModelAndView performGlobalLogout(Principal principal, Locale locale);
}
