/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.controller.implementations;

import br.com.fenrir.auth.adapters.commons.constants.MessageBundleConstants;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations.UserBoundaryMapper;
import br.com.fenrir.auth.adapters.entrypoint.controller.UserController;
import br.com.fenrir.auth.adapters.entrypoint.constants.PageConstants;
import br.com.fenrir.auth.adapters.entrypoint.converters.implementations.UserDeliveryConverter;
import br.com.fenrir.auth.adapters.entrypoint.util.PrincipalUtils;
import br.com.fenrir.auth.adapters.entrypoint.constants.MvObjectConstants;
import br.com.fenrir.auth.core.usecase.UserUseCase;
import br.com.fenrir.auth.core.usecase.exceptions.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.util.Locale;

/**
 * <p>
 *     Implementation of Controller in MVC.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@Controller
public class UserMvcController implements UserController {

    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private UserDeliveryConverter userDeliveryConverter;

    @Autowired
    private MessageSource messages;

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
            UserBoundaryMapper userBM = userDeliveryConverter.mapToBoundary(userUseCase.getUser(user.getUsername()));
            modelAndView.addObject("user", userBM);
            return modelAndView;
        } catch (UserNotFoundException exception) {
            log.info("This User is not registered: {}", user.getUsername());
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage(MessageBundleConstants.USER_NOT_REGISTERED,
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
                messages.getMessage(MessageBundleConstants.DELETE_WARNING, null, locale));

        return modelAndView;
    }

    @Override
    @PostMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.DELETE_ACCOUNT_PAGE)
    public ModelAndView processDeleteAccount(Principal principal, Locale locale) {
        log.debug("User deletion requested for: {}", principal.getName());

        final UserDetails user = PrincipalUtils.getInAuthObject(principal, locale);

        try {
            userUseCase.deleteUser(user.getUsername());
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
                    messages.getMessage(MessageBundleConstants.DELETE_SUCCESS, null, locale));

            return modelAndView;
        }

        return new ModelAndView(PageConstants.PATH_REDIRECT + PageConstants.PROFILE_PAGE);
    }
}
