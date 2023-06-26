/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.mvc.controller.implementations;

import com.heimdall.configuration.security.properties.AuthProperties;
import com.heimdall.configuration.security.utils.PrincipalUtils;
import com.heimdall.entrypoint.mvc.constants.MvObjectConstants;
import com.heimdall.entrypoint.mvc.constants.PageConstants;
import com.heimdall.entrypoint.converters.implementations.UserDeliveryConverter;
import com.heimdall.entrypoint.boundary.dto.implementations.UserDto;
import com.heimdall.entrypoint.mvc.controller.ICredentialsController;

import com.heimdall.ports.command.AccountCommand;
import com.heimdall.ports.command.UserCommand;
import com.heimdall.core.exceptions.ResetEmailTokenNotFoundException;
import com.heimdall.core.exceptions.ResetPassTokenNotFoundException;
import com.heimdall.core.exceptions.UserNotEnabledException;
import com.heimdall.core.exceptions.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Locale;

@Slf4j
@Controller
public class CredentialsMvcController implements ICredentialsController {

    private final MessageSource messages;

    private final UserDeliveryConverter userDeliveryConverter;

    private final AccountCommand accountCommand;

    private final UserCommand userCommand;

    private final AuthProperties properties;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher eventPublisher;

    public CredentialsMvcController(MessageSource messages, UserDeliveryConverter userDeliveryConverter,
                                    AccountCommand accountCommand, UserCommand userCommand, AuthProperties properties,
                                    PasswordEncoder passwordEncoder, ApplicationEventPublisher eventPublisher) {
        this.messages = messages;
        this.userDeliveryConverter = userDeliveryConverter;
        this.accountCommand = accountCommand;
        this.userCommand = userCommand;
        this.properties = properties;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @GetMapping(PageConstants.PATH_SEPARATOR + PageConstants.FORGOTTEN_PASSWORD_PAGE)
    public ModelAndView showForgottenPasswordPage(ModelAndView modelAndView, UserDto user) {
        modelAndView.addObject(MvObjectConstants.USER_OBJ, user);
        modelAndView.setViewName(PageConstants.FORGOTTEN_PASSWORD_PAGE);
        return modelAndView;
    }

    @Override
    @PostMapping(PageConstants.PATH_SEPARATOR + PageConstants.FORGOTTEN_PASSWORD_PAGE)
    public ModelAndView processForgottenPasswordForm(ModelAndView modelAndView, UserDto user, Locale locale) {
        log.debug("Process forgotten password form.");
        modelAndView.setViewName(PageConstants.FORGOTTEN_PASSWORD_PAGE);

        try{
            // Process the creation of reset password token
            accountCommand.createResetPasswordToken(userDeliveryConverter.mapToEntity(user),
                    properties.getExpiryDateResetToken());

            // new registration event
            //eventPublisher.publishEvent(new OnSendMailEvent(user, locale));

            modelAndView.addObject(MvObjectConstants.CONFIRMATION_MESSAGE, messages.getMessage(
                    "registration.passwordResetEmail", new Object[]{user.getEmail()}, locale));
            modelAndView.setViewName(PageConstants.FORGOTTEN_PASSWORD_PAGE);
            return modelAndView;
        } catch (UserNotFoundException ex) {
            log.warn("This user not found: {}", user);
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage("registration.userNotRegistered",
                            new Object[]{user.getEmail()}, locale));
            return modelAndView;
        } catch (UserNotEnabledException ex) {
            log.warn("This user is not enable: {}", user);
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage("registration.userNotRegistered",
                            new Object[]{user.getEmail()}, locale));
            return modelAndView;
        }
    }

    @Override
    @GetMapping(PageConstants.PATH_SEPARATOR + PageConstants.RESET_PASSWORD_PAGE)
    public ModelAndView showResetPassPage(ModelAndView modelAndView,
                                          @RequestParam("token") String token,
                                          @RequestParam(value = "mobile", required = false, defaultValue = "false")
                                                      boolean mobile,
                                          @RequestParam(value = "passwordError", required = false)
                                                      boolean passwordError,
                                          Locale locale) {
        log.debug("Prepare to show reset password page.");
        modelAndView.setViewName(PageConstants.RESET_PASSWORD_PAGE);

        if (passwordError) {
            log.debug("Passwords are not matching!");
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage("password.notMatching", null, locale));
        }

        modelAndView.addObject(MvObjectConstants.CONFIRMATION_TOKEN_OBJ, token);
        modelAndView.addObject(MvObjectConstants.MOBILE_VERIFICATION, mobile);
        return modelAndView;
    }

    @Override
    @GetMapping(PageConstants.PATH_SEPARATOR + PageConstants.RESET_PASSWORD_REDIRECT_PAGE)
    public String showRedirectResetPassPage(@RequestParam("token") String token) {
        return PageConstants.PATH_REDIRECT.concat(
                PageConstants.RESET_PASSWORD_PAGE).concat(
                        PageConstants.TOKEN_CONDITION).concat("=").concat(token);
    }

    @Override
    @PostMapping(PageConstants.PATH_SEPARATOR + PageConstants.RESET_PASSWORD_PAGE)
    public ModelAndView processResetPassForm(ModelAndView modelAndView,
                                             @RequestParam("token") String token,
                                             @RequestParam("password") String password,
                                             @RequestParam("confirmPassword") String confirmPassword, Locale locale) {
        log.debug("Process Reset Pass");
        modelAndView.setViewName(PageConstants.RESET_PASSWORD_PAGE);

        if (!password.equals(confirmPassword)) {
            modelAndView.setView(new RedirectView(PageConstants.RESET_PASSWORD_PAGE));
            modelAndView.addObject(MvObjectConstants.TOKEN_OBJ, token);
            modelAndView.addObject(MvObjectConstants.PASSWORD_ERROR_VERIFICATION, true);
            return modelAndView;
        }

        try{
            modelAndView.addObject(MvObjectConstants.LOGIN_LINK_PAGE,
                    PageConstants.PATH_SEPARATOR.concat(PageConstants.LOGIN_PAGE));
            accountCommand.confirmResetPass(token, password);
            modelAndView.addObject(MvObjectConstants.SUCCESS_MESSAGE,
                    messages.getMessage("password.changeSuccess", null, locale));
            return modelAndView;
        } catch (ResetPassTokenNotFoundException ex) {
            log.warn("Token not found or token is expired, token: {}", token);
            modelAndView.addObject(MvObjectConstants.INVALID_TOKEN_ERROR_VERIFICATION,
                    messages.getMessage("registration.invalidToken", null, locale));
            return modelAndView;
        }
    }

    @Override
    @GetMapping(PageConstants.PATH_REDIRECT + PageConstants.CHANGE_PASSWORD_PAGE)
    public ModelAndView changePassword() {
        return new ModelAndView(PageConstants.CHANGE_PASSWORD_PAGE);
    }

    @Override
    @PostMapping(PageConstants.PATH_REDIRECT + PageConstants.CHANGE_PASSWORD_PAGE)
    public ModelAndView processPasswordChange(ModelAndView modelAndView,
                                              @RequestParam("currentPassword") String currentPassword,
                                              @RequestParam("newPassword") String newPassword,
                                              @RequestParam("confirmPassword") String confirmPassword,
                                              Locale locale, Principal principal) {
        log.debug("Process password change.");
        modelAndView.setViewName(PageConstants.CHANGE_PASSWORD_PAGE);

        // new password must be different from the old one
        if (currentPassword.equals(newPassword)) {
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage("password.notUnique", null, locale));
            return modelAndView;
        }
        if (!newPassword.equals(confirmPassword)) {
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage("password.notMatching", null, locale));
            return modelAndView;
        }

        final String encodeCurrentPass = passwordEncoder.encode(currentPassword);
        final UserDetails userDetails = PrincipalUtils.getInAuthObject(principal, locale);
        final boolean success = accountCommand.changePassword(userDetails.getUsername(), encodeCurrentPass, newPassword);

        if (!success) {
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage("password.incorrect", null, locale));
            return modelAndView;
        }

        modelAndView.addObject(MvObjectConstants.SUCCESS_MESSAGE,
                messages.getMessage("password.changeSuccess", null, locale));
        // revoke all tokens from this User.
        //userCommand.revokeAllTokens(principal.getName());
        return modelAndView;
    }

    @Override
    @GetMapping(PageConstants.PATH_REDIRECT + PageConstants.CHANGE_EMAIL_PAGE)
    public ModelAndView changeEmail() {
        return new ModelAndView(PageConstants.CHANGE_EMAIL_PAGE);
    }

    @Override
    @PostMapping(PageConstants.PATH_REDIRECT + PageConstants.CHANGE_EMAIL_PAGE)
    public ModelAndView processEmailChange(ModelAndView modelAndView,
                                           @RequestParam("password") String password,
                                           @RequestParam("email") String email,
                                           Locale locale, Principal principal) {
        log.debug("Process email change.");
        modelAndView.setViewName(PageConstants.CHANGE_EMAIL_PAGE);

        final UserDetails userDetails = PrincipalUtils.getInAuthObject(principal, locale);
        final String encodedPass = passwordEncoder.encode(password);
        final boolean success = accountCommand.createResetEmailToken(userDetails.getUsername(), encodedPass, email,
                properties.getExpiryDateResetToken());

        if (!success) {
            log.debug("Fail to create a token to change email from user.");
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE, messages.getMessage(
                    "email.changeFailure", null, locale));
            return modelAndView;
        }

        // new registration event
        //eventPublisher.publishEvent(new OnEmailChangeCompleteEvent(user, locale));

        modelAndView.addObject(MvObjectConstants.SUCCESS_MESSAGE, messages.getMessage(
                "email.changeSuccess", new Object[]{email}, locale));
        return modelAndView;
    }

    @Override
    @GetMapping(PageConstants.PATH_REDIRECT + PageConstants.VERIFY_EMAIL_PAGE)
    public ModelAndView showVerifyEmailPage(ModelAndView modelAndView, @RequestParam("token") String token,
                                            Locale locale) {
        log.debug("Show the verification email page after email change.");
        try {
            final String newEmail = accountCommand.confirmResetEmail(token);

            modelAndView.addObject(MvObjectConstants.SUCCESS_MESSAGE, messages.getMessage(
                    "email.verificationSuccess", new Object[]{newEmail}, locale));
            modelAndView.setViewName(PageConstants.VERIFY_EMAIL_PAGE);
            return modelAndView;
        } catch (ResetEmailTokenNotFoundException e) {
            log.debug("No user found for this token: {}", token);
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage("email.verificationFailure", null, locale));
            modelAndView.setViewName(PageConstants.VERIFY_EMAIL_PAGE);
            return modelAndView;
        }
    }
}
