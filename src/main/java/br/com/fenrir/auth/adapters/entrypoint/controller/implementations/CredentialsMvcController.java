/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.controller.implementations;

import br.com.fenrir.auth.adapters.commons.config.auth.AuthProperties;
import br.com.fenrir.auth.adapters.commons.constants.MessageBundleConstants;
import br.com.fenrir.auth.adapters.entrypoint.constants.PageConstants;
import br.com.fenrir.auth.adapters.entrypoint.converters.implementations.UserDeliveryConverter;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations.UserBoundaryMapper;
import br.com.fenrir.auth.adapters.entrypoint.controller.CredentialsController;
import br.com.fenrir.auth.adapters.entrypoint.constants.MvObjectConstants;
import br.com.fenrir.auth.adapters.entrypoint.util.PrincipalUtils;
import br.com.fenrir.auth.core.usecase.AccountUseCase;
import br.com.fenrir.auth.core.usecase.UserUseCase;
import br.com.fenrir.auth.core.usecase.exceptions.ResetEmailTokenNotFoundException;
import br.com.fenrir.auth.core.usecase.exceptions.ResetPassTokenNotFoundException;
import br.com.fenrir.auth.core.usecase.exceptions.UserNotEnabledException;
import br.com.fenrir.auth.core.usecase.exceptions.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * <p>
 *     Implementation of Controller in MVC.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@Controller
public class CredentialsMvcController implements CredentialsController {

    @Autowired
    private MessageSource messages;

    @Autowired
    private UserDeliveryConverter userDeliveryConverter;

    @Autowired
    private AccountUseCase accountUseCase;
    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private AuthProperties properties;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    @GetMapping(PageConstants.PATH_SEPARATOR + PageConstants.FORGOTTEN_PASSWORD_PAGE)
    public ModelAndView showForgottenPasswordPage(ModelAndView modelAndView, UserBoundaryMapper user) {
        modelAndView.addObject(MvObjectConstants.USER_OBJ, user);
        modelAndView.setViewName(PageConstants.FORGOTTEN_PASSWORD_PAGE);
        return modelAndView;
    }

    @Override
    @PostMapping(PageConstants.PATH_SEPARATOR + PageConstants.FORGOTTEN_PASSWORD_PAGE)
    public ModelAndView processForgottenPasswordForm(ModelAndView modelAndView, UserBoundaryMapper user, Locale locale) {
        log.debug("Process forgotten password form.");
        modelAndView.setViewName(PageConstants.FORGOTTEN_PASSWORD_PAGE);

        try{
            // Process the creation of reset password token
            accountUseCase.createResetPasswordToken(userDeliveryConverter.mapToEntity(user),
                    properties.getExpiryDateResetToken());

            // new registration event
            //eventPublisher.publishEvent(new OnSendMailEvent(user, locale));

            modelAndView.addObject(MvObjectConstants.CONFIRMATION_MESSAGE, messages.getMessage(
                    MessageBundleConstants.RESET_PASSWORD_EMAIL, new Object[]{user.getEmail()}, locale));
            modelAndView.setViewName(PageConstants.FORGOTTEN_PASSWORD_PAGE);
            return modelAndView;
        } catch (UserNotFoundException ex) {
            log.warn("This user not found: {}", user);
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage(MessageBundleConstants.USER_NOT_REGISTERED,
                            new Object[]{user.getEmail()}, locale));
            return modelAndView;
        } catch (UserNotEnabledException ex) {
            log.warn("This user is not enable: {}", user);
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage(MessageBundleConstants.USER_NOT_REGISTERED,
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
                    messages.getMessage(MessageBundleConstants.PASSWORD_NOT_MATCHING, null, locale));
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
            accountUseCase.confirmResetPass(token, password);
            modelAndView.addObject(MvObjectConstants.SUCCESS_MESSAGE,
                    messages.getMessage(MessageBundleConstants.PASSWORD_MATCHING, null, locale));
            return modelAndView;
        } catch (ResetPassTokenNotFoundException ex) {
            log.warn("Token not found or token is expired, token: {}", token);
            modelAndView.addObject(MvObjectConstants.INVALID_TOKEN_ERROR_VERIFICATION,
                    messages.getMessage(MessageBundleConstants.INVALID_TOKEN, null, locale));
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
                    messages.getMessage(MessageBundleConstants.PASSWORD_NOT_UNIQUE, null, locale));
            return modelAndView;
        }
        if (!newPassword.equals(confirmPassword)) {
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage(MessageBundleConstants.PASSWORD_NOT_MATCHING, null, locale));
            return modelAndView;
        }

        final String encodeCurrentPass = passwordEncoder.encode(currentPassword);
        final UserDetails userDetails = PrincipalUtils.getInAuthObject(principal, locale);
        final boolean success = accountUseCase.changePassword(userDetails.getUsername(), encodeCurrentPass, newPassword);

        if (!success) {
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage(MessageBundleConstants.PASSWORD_INCORRECT, null, locale));
            return modelAndView;
        }

        modelAndView.addObject(MvObjectConstants.SUCCESS_MESSAGE,
                messages.getMessage(MessageBundleConstants.PASSWORD_CHANGE_SUCCESS, null, locale));
        // revoke all tokens from this User.
        userUseCase.revokeAllTokens(principal.getName());
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
        final boolean success = accountUseCase.createResetEmailToken(userDetails.getUsername(), encodedPass, email,
                properties.getExpiryDateResetToken());

        if (!success) {
            log.debug("Fail to create a token to change email from user.");
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE, messages.getMessage(
                    MessageBundleConstants.EMAIL_CHANGE_FAILURE, null, locale));
            return modelAndView;
        }

        // new registration event
        //eventPublisher.publishEvent(new OnEmailChangeCompleteEvent(user, locale));

        modelAndView.addObject(MvObjectConstants.SUCCESS_MESSAGE, messages.getMessage(
                MessageBundleConstants.EMAIL_CHANGE_SUCCESS, new Object[]{email}, locale));
        return modelAndView;
    }

    @Override
    @GetMapping(PageConstants.PATH_REDIRECT + PageConstants.VERIFY_EMAIL_PAGE)
    public ModelAndView showVerifyEmailPage(ModelAndView modelAndView, @RequestParam("token") String token,
                                            Locale locale) {
        log.debug("Show the verification email page after email change.");
        try {
            final String newEmail = accountUseCase.confirmResetEmail(token);

            modelAndView.addObject(MvObjectConstants.SUCCESS_MESSAGE, messages.getMessage(
                    MessageBundleConstants.EMAIL_VERIFICATION_SUCCESS, new Object[]{newEmail}, locale));
            modelAndView.setViewName(PageConstants.VERIFY_EMAIL_PAGE);
            return modelAndView;
        } catch (ResetEmailTokenNotFoundException e) {
            log.debug("No user found for this token: {}", token);
            modelAndView.addObject(MvObjectConstants.ERROR_MESSAGE,
                    messages.getMessage(MessageBundleConstants.EMAIL_VERIFICATION_FAILURE, null, locale));
            modelAndView.setViewName(PageConstants.VERIFY_EMAIL_PAGE);
            return modelAndView;
        }
    }
}
