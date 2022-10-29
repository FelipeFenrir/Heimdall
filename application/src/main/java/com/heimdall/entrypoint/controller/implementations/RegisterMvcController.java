/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.controller.implementations;

import com.heimdall.infrastructure.adapters.commons.config.auth.AuthProperties;
import com.heimdall.infrastructure.adapters.commons.constants.MessageBundleConstants;
import com.heimdall.infrastructure.adapters.entrypoint.boundary.factory.implementations.EmailBoundaryFactoryImpl;
import com.heimdall.infrastructure.adapters.entrypoint.boundary.mapper.EmailBoundary;
import com.heimdall.infrastructure.adapters.entrypoint.constants.PageConstants;
import com.heimdall.infrastructure.adapters.entrypoint.boundary.mapper.implementations.UserBoundaryMapper;
import com.heimdall.entrypoint.controller.IRegisterController;
import com.heimdall.infrastructure.adapters.entrypoint.constants.MvObjectConstants;
import com.heimdall.infrastructure.adapters.entrypoint.event.OnSendMailEvent;

import com.heimdall.core.ports.command.AccountCommand;
import com.heimdall.core.exceptions.UserAlreadyExistException;
import com.heimdall.core.exceptions.UserNotFoundException;

import com.fenrir.commons.utils.EnvUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

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
public class RegisterMvcController implements IRegisterController {

    // This base path of this application.
    private final String baseURL = EnvUtil.getInstance().getCompleteNetHost().get(EnvUtil.HTTPS_SCHEME);

    @Autowired
    private MessageSource messages;

    @Autowired
    private AuthProperties properties;

    @Autowired
    private AccountCommand accountCommand;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    @GetMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.REGISTRATION_PAGE)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserBoundaryMapper user) {
        modelAndView.addObject(MvObjectConstants.USER_OBJ, user);
        modelAndView.setViewName(PageConstants.REGISTRATION_PAGE);
        return modelAndView;
    }

    @Override
    @PostMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.REGISTRATION_PAGE)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid UserBoundaryMapper user,
                                                Locale locale) {
        log.debug("Process registration form for {}", user);
        modelAndView.setViewName(PageConstants.REGISTRATION_PAGE);
        try {
            // register new user and send confirmation e-mail
            accountCommand.registerUserByEmail(user.getEmail());

            // new registration event
            final String subject = messages.getMessage(MessageBundleConstants.EMAIL_REGISTRATION_SUBJECT, null,
                    locale);
            final String title = messages.getMessage(MessageBundleConstants.EMAIL_TITLE, null, locale);
            final String message = messages.getMessage(MessageBundleConstants.EMAIL_REGISTRATION, null, locale);
            final String link = PageConstants.PATH_SEPARATOR
                    .concat(PageConstants.CONFIRMATION_REDIRECT_PAGE)
                    .concat(PageConstants.TOKEN_CONDITION)
                    .concat("=")
                    .concat(user.getConfirmationToken());
            final EmailBoundary email = new EmailBoundaryFactoryImpl().createRegistrationMail(
                    user.getEmail(), properties.getEmailFrom(), subject, title, message, link);
            eventPublisher.publishEvent(new OnSendMailEvent(email, locale, baseURL));

            modelAndView.addObject(MvObjectConstants.CONFIRMATION_MESSAGE, messages.getMessage(
                    MessageBundleConstants.REGISTRATION_CONFIRMATION_EMAIL, new Object[]{user.getEmail()}, locale));
            return modelAndView;
        } catch (UserAlreadyExistException e) {
            log.warn("This user already exists: {}", user);
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage(MessageBundleConstants.EMAIL_ALREADY_REGISTERED,
                            new Object[]{user.getEmail()}, locale));
            return modelAndView;
        }
    }

    @Override
    @GetMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.CONFIRMATION_PAGE)
    public ModelAndView showConfirmationPage(ModelAndView modelAndView,
                               @RequestParam("token") String token,
                               @RequestParam(value = "mobile", required = false, defaultValue = "false") boolean mobile,
                               @RequestParam(value = "passwordError", required = false) boolean passwordError,
                               @RequestParam(value = "termError", required = false) boolean termError,
                               Locale locale) {
        log.debug("Show confirmation page");

        modelAndView.setViewName(PageConstants.CONFIRMATION_PAGE);

        if (termError) {
            log.debug("The terms and conditions were not accepted!");
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE, messages.getMessage(
                    MessageBundleConstants.TERM_NOT_ACCEPTED, null, locale));
        }

        if (passwordError) {
            log.debug("Passwords are not matching!");
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE, messages.getMessage(
                    MessageBundleConstants.PASSWORD_NOT_MATCHING, null, locale));
        }

        modelAndView.addObject(MvObjectConstants.CONFIRMATION_TOKEN_OBJ, token);

        // mobile param to model and view
        modelAndView.addObject(MvObjectConstants.MOBILE_VERIFICATION, mobile);
        return modelAndView;
    }

    @Override
    @GetMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.CONFIRMATION_REDIRECT_PAGE)
    public String showRedirectConfirmationPage(@RequestParam("token") String token) {
        log.debug("Redirect to confirmation page with confirmation token.");
        return PageConstants.PATH_REDIRECT + PageConstants.CONFIRMATION_PAGE + PageConstants.TOKEN_CONDITION
                + "=" + token;
    }

    @Override
    @PostMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.CONFIRMATION_PAGE)
    public ModelAndView processConfirmationForm(ModelAndView modelAndView,
                                                @RequestParam("token") String token,
                                                @RequestParam("password") String password,
                                                @RequestParam("confirmPassword") String confirmPassword,
                                                @RequestParam("terms") Boolean term, Locale locale) {
        log.debug("Process the registration confirmation form.");

        modelAndView.setViewName(PageConstants.CONFIRMATION_PAGE);

        if (!password.equals(confirmPassword)) {
            modelAndView.setView(new RedirectView(PageConstants.CONFIRMATION_PAGE));
            modelAndView.addObject(MvObjectConstants.TOKEN_OBJ, token);
            modelAndView.addObject(MvObjectConstants.PASSWORD_ERROR_VERIFICATION, true);
            return modelAndView;
        }

        if (!term) {
            modelAndView.setView(new RedirectView(PageConstants.CONFIRMATION_PAGE));
            modelAndView.addObject(MvObjectConstants.TOKEN_OBJ, token);
            modelAndView.addObject(MvObjectConstants.TERM_ERROR_VERIFICATION, true);
            return modelAndView;
        }

        try {
            accountCommand.confirmUserByEmail(token, password);
            modelAndView.addObject(MvObjectConstants.LOGIN_LINK_PAGE,
                    PageConstants.PATH_SEPARATOR + PageConstants.LOGIN_PAGE);

            modelAndView.addObject(MvObjectConstants.SUCCESS_MESSAGE,
                    messages.getMessage(MessageBundleConstants.PASSWORD_MATCHING, null, locale));
            return modelAndView;
        } catch (UserNotFoundException exception) {
            throw new UsernameNotFoundException("No user found for token!");
        }
    }
}
