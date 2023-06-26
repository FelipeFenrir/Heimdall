/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.mvc.controller.implementations;

import com.heimdall.entrypoint.mvc.constants.MvObjectConstants;
import com.heimdall.entrypoint.mvc.constants.PageConstants;
import com.heimdall.entrypoint.mvc.controller.IAuthenticationController;

import com.heimdall.ports.command.UserCommand;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.security.Principal;
import java.util.Locale;

@Slf4j
@Controller
public class AuthenticationMvcController implements IAuthenticationController {

    private final MessageSource messages;

    private final UserCommand userCommand;

    public AuthenticationMvcController(MessageSource messages, UserCommand userCommand) {
        this.messages = messages;
        this.userCommand = userCommand;
    }

    @Override
    @GetMapping(PageConstants.PATH_SEPARATOR)
    public String showHomePage() {
        log.debug("Show home page.");
        return PageConstants.INDEX_PAGE;
    }

    @Override
    @GetMapping(PageConstants.PATH_SEPARATOR + PageConstants.LOGIN_PAGE)
    public String redirectLoginPage() {
        log.debug("Redirect to login page if not authenticated.");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            log.info("User is logged in, redirect to profile page.");
            return PageConstants.PATH_REDIRECT + PageConstants.PROFILE_PAGE;
        }
        return PageConstants.LOGIN_PAGE;
    }

    @Override
    @GetMapping(PageConstants.PATH_SEPARATOR + PageConstants.LOGOUT_MODE_PAGE)
    public String redirectLogoutPage() {
        log.debug("Redirect to logout mode page.");
        return PageConstants.PATH_SEPARATOR + PageConstants.LOGOUT_MODE_PAGE;
    }

    @Override
    @PostMapping(PageConstants.PATH_SEPARATOR + PageConstants.LOGOUT_PAGE)
    public String performLogout(HttpServletRequest request) {
        log.info("Perform one click logout.");

        // Current user was validated -> Clear securityContext
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();

        // Invalidate session
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return PageConstants.PATH_REDIRECT + PageConstants.LOGIN_PAGE + PageConstants.LOGOUT_CONDITION;
    }

    @Override
    @PostMapping(PageConstants.PATH_SEPARATOR + PageConstants.GLOBAL_LOGOUT_PAGE)
    public ModelAndView performGlobalLogout(Principal principal, Locale locale) {
        log.info("Perform global logout.");
        ModelAndView modelAndView = new ModelAndView(
                PageConstants.PATH_SEPARATOR + PageConstants.LOGOUT_MODE_PAGE);

        // Revoke tokens
        //userCommand.revokeAllTokens(principal.getName());

        modelAndView.addObject(MvObjectConstants.CONFIRMATION_MESSAGE,
                messages.getMessage("logout.globalConfirmation", null, locale));

        return modelAndView;
    }
}
