/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.mvc.controller.implementations;

import com.heimdall.configuration.security.properties.AuthProperties;
import com.heimdall.entrypoint.mvc.constants.MvObjectConstants;
import com.heimdall.entrypoint.mvc.constants.PageConstants;
import com.heimdall.entrypoint.mvc.controller.IRegisterController;
import com.heimdall.entrypoint.boundary.factory.implementations.EmailDtoFactory;
import com.heimdall.entrypoint.boundary.dto.IEmailDto;
import com.heimdall.entrypoint.boundary.dto.implementations.UserDto;
import com.heimdall.entrypoint.event.OnSendMailEvent;

import com.heimdall.core.exceptions.UserAlreadyExistException;
import com.heimdall.core.exceptions.UserNotFoundException;
import com.heimdall.ports.command.AccountCommand;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
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

@Slf4j
@Controller
public class RegisterMvcController implements IRegisterController {

    private final String baseURL;

    private final MessageSource messages;

    private final AuthProperties properties;

    private final AccountCommand accountCommand;

    private final ApplicationEventPublisher eventPublisher;

    public RegisterMvcController(MessageSource messages,
                                 AuthProperties properties,
                                 AccountCommand accountCommand,
                                 ApplicationEventPublisher eventPublisher,
                                 @Value("auth.redirectionUrl") String baseURL) {
        this.messages = messages;
        this.properties = properties;
        this.accountCommand = accountCommand;
        this.eventPublisher = eventPublisher;
        this.baseURL = baseURL;
    }

    @Override
    @GetMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.REGISTRATION_PAGE)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserDto user) {
        modelAndView.addObject(MvObjectConstants.USER_OBJ, user);
        modelAndView.setViewName(PageConstants.REGISTRATION_PAGE);
        return modelAndView;
    }

    @Override
    @PostMapping(path = PageConstants.PATH_SEPARATOR + PageConstants.REGISTRATION_PAGE)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid UserDto user,
                                                Locale locale) {
        log.debug("Process registration form for {}", user);
        modelAndView.setViewName(PageConstants.REGISTRATION_PAGE);
        try {
            // register new user and send confirmation e-mail
            accountCommand.registerUserByEmail(user.getEmail());

            // new registration event
            final String subject = messages.getMessage("email.registration.subject", null,
                    locale);
            final String title = messages.getMessage("email.title", null, locale);
            final String message = messages.getMessage("email.registration", null, locale);
            final String link = PageConstants.PATH_SEPARATOR
                    .concat(PageConstants.CONFIRMATION_REDIRECT_PAGE)
                    .concat(PageConstants.TOKEN_CONDITION)
                    .concat("=")
                    .concat(user.getConfirmationToken());
            final IEmailDto email = new EmailDtoFactory().createRegistrationMail(
                    user.getEmail(), properties.getEmailFrom(), subject, title, message, link);

            eventPublisher.publishEvent(new OnSendMailEvent(email, locale, baseURL));

            modelAndView.addObject(MvObjectConstants.CONFIRMATION_MESSAGE, messages.getMessage(
                    "registration.confirmationEmail", new Object[]{user.getEmail()}, locale));
            return modelAndView;
        } catch (UserAlreadyExistException e) {
            log.warn("This user already exists: {}", user);
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage("registration.emailExists",
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
                    "term.notaccepted", null, locale));
        }

        if (passwordError) {
            log.debug("Passwords are not matching!");
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE, messages.getMessage(
                    "password.notMatching", null, locale));
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
                    messages.getMessage("registration.passwordSuccess", null, locale));
            return modelAndView;
        } catch (UserNotFoundException exception) {
            throw new UsernameNotFoundException("No user found for token!");
        }
    }
}
