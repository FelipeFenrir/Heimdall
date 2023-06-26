/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.mvc.controller.implementations;

import com.heimdall.configuration.security.utils.PrincipalUtils;
import com.heimdall.entrypoint.converters.implementations.UserDeliveryConverter;
import com.heimdall.entrypoint.mvc.constants.MvObjectConstants;
import com.heimdall.entrypoint.mvc.constants.PageConstants;
import com.heimdall.entrypoint.boundary.dto.implementations.UserDto;
import com.heimdall.entrypoint.mvc.controller.IUserController;

import com.heimdall.core.exceptions.UserNotFoundException;
import com.heimdall.ports.command.UserCommand;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.security.Principal;
import java.util.Locale;

@Slf4j
@AllArgsConstructor
@Controller
public class UserMvcController implements IUserController {

    private final UserCommand userCommand;

    private final UserDeliveryConverter userDeliveryConverter;

    private final MessageSource messages;

    @Override
    @GetMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.PROFILE_PAGE)
    public String showProfilePage() {
        return PageConstants.PROFILE_PAGE;
    }

    @Override
    @GetMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.ME_PAGE)
    public ModelAndView showMePage(ModelAndView modelAndView, Principal principal, Locale locale) {
        log.debug("Me page requested for: {}", principal.getName());

        modelAndView.setViewName(PageConstants.ME_PAGE);

        final UserDetails user = PrincipalUtils.getInAuthObject(principal, locale);

        try {
            UserDto userBM = userDeliveryConverter.mapToBoundary(userCommand.getUser(user.getUsername()));
            modelAndView.addObject("user", userBM);
            return modelAndView;
        } catch (UserNotFoundException exception) {
            log.info("This User is not registered: {}", user.getUsername());
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage("common.error.instanceAuth",
                            new Object[]{user.getUsername()}, locale));

            return modelAndView;
        }
    }

    @Override
    @GetMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.DELETE_ACCOUNT_PAGE)
    public ModelAndView showDeleteAccountPage(Locale locale) {
        log.debug("Request for user deletion page.");
        ModelAndView modelAndView = new ModelAndView(PageConstants.DELETE_ACCOUNT_PAGE);

        modelAndView.addObject(MvObjectConstants.WARNING_MESSAGE,
                messages.getMessage("delete.warning", null, locale));

        return modelAndView;
    }

    @Override
    @PostMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.DELETE_ACCOUNT_PAGE)
    public ModelAndView processDeleteAccount(Principal principal, Locale locale) {
        log.debug("User deletion requested for: {}", principal.getName());

        final UserDetails user = PrincipalUtils.getInAuthObject(principal, locale);

        try {
            userCommand.deleteUser(user.getUsername());
            ModelAndView modelAndView = new ModelAndView(
                    PageConstants.PATH_REDIRECT + PageConstants.DELETE_ACCOUNT_SUCCESS_PAGE);
            modelAndView.addObject(MvObjectConstants.SUCCESS_VERIFICATION, true);
            return modelAndView;
        } catch (UserNotFoundException exception) {
            log.info("User not found : {}", user.getUsername());
            throw new UsernameNotFoundException("User not found!");
        }
    }

    @Override
    @GetMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.DELETE_ACCOUNT_SUCCESS_PAGE)
    public ModelAndView showDeleteSuccessPage(HttpServletRequest request, Locale locale,
                                          @RequestParam(value = "success", required = false) boolean success) {
        log.debug("Success user deletion page.");

        if (success) {
            // log user out
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();

            // Invalidate session
            final HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            ModelAndView modelAndView = new ModelAndView(PageConstants.DELETE_ACCOUNT_SUCCESS_PAGE);
            modelAndView.addObject(MvObjectConstants.CONFIRMATION_MESSAGE,
                    messages.getMessage("delete.success", null, locale));

            return modelAndView;
        }

        return new ModelAndView(PageConstants.PATH_REDIRECT + PageConstants.PROFILE_PAGE);
    }
}
