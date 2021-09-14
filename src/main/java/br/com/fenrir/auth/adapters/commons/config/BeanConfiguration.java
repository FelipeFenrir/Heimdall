/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.commons.config;

import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl.ResetEmailTokenDataConverter;
import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl.ResetPasswordTokenDataConverter;
import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl.RoleDataConverter;
import br.com.fenrir.auth.adapters.outbound.dataprovider.converters.impl.UserDataConverter;
import br.com.fenrir.auth.adapters.outbound.dataprovider.repository.ResetEmailTokenRepository;
import br.com.fenrir.auth.adapters.outbound.dataprovider.repository.ResetPasswordTokenRepository;
import br.com.fenrir.auth.adapters.outbound.dataprovider.repository.RoleRepository;
import br.com.fenrir.auth.adapters.outbound.dataprovider.repository.UserRepository;
import br.com.fenrir.auth.adapters.outbound.dataprovider.service.ResetEmailTokenService;
import br.com.fenrir.auth.adapters.outbound.dataprovider.service.ResetPasswordTokenService;
import br.com.fenrir.auth.adapters.outbound.dataprovider.service.RoleService;
import br.com.fenrir.auth.adapters.outbound.dataprovider.service.TokenService;
import br.com.fenrir.auth.adapters.outbound.dataprovider.service.UserService;
import br.com.fenrir.auth.adapters.outbound.smtp.service.EmailContentBuilder;
import br.com.fenrir.auth.adapters.outbound.smtp.service.EmailService;
import br.com.fenrir.auth.core.usecase.implementations.AccountUseCaseImpl;
import br.com.fenrir.auth.core.usecase.implementations.EmailUseCaseImpl;
import br.com.fenrir.auth.core.usecase.implementations.UserUseCaseImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * <p>
 *     Class to configuration beans.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@Configuration
public class BeanConfiguration {

    @Autowired
    private ResetEmailTokenRepository resetEmailTokenRepository;

    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Bean
    ResetEmailTokenDataConverter resetEmailTokenDataConverter() {
        return new ResetEmailTokenDataConverter();
    }

    @Bean
    ResetPasswordTokenDataConverter resetPasswordTokenDataConverter() {
        return new ResetPasswordTokenDataConverter();
    }

    @Bean
    RoleDataConverter roleDataConverter() {
        return new RoleDataConverter();
    }

    @Bean
    UserDataConverter userDataConverter() {
        return new UserDataConverter();
    }

    @Bean
    ResetEmailTokenService resetEmailTokenGateway() {
        log.debug("Setup of Reset Email Token Gateway Implementation Bean.");
        return new ResetEmailTokenService(resetEmailTokenRepository, resetEmailTokenDataConverter());
    }

    @Bean
    ResetPasswordTokenService resetPasswordTokenGateway() {
        log.debug("Setup of Reset Password Token Gateway Implementation Bean.");
        return new ResetPasswordTokenService(resetPasswordTokenRepository, resetPasswordTokenDataConverter());
    }

    @Bean
    RoleService roleGateway() {
        log.debug("Setup of Role Gateway Implementation Bean.");
        return new RoleService(roleRepository, roleDataConverter());
    }

    @Bean
    TokenService tokenGateway() {
        log.debug("Setup of Token Gateway Implementation Bean.");
        return new TokenService(tokenStore);
    }

    @Bean
    UserService userGateway() {
        log.debug("Setup of User Gateway Implementation Bean.");
        return new UserService(userRepository, userDataConverter());
    }

    @Bean
    AccountUseCaseImpl accountUseCaseImpl() {
        log.debug("Setup of Account Use Case Bean.");
        return new AccountUseCaseImpl(userGateway(), roleGateway(), resetPasswordTokenGateway(),
                resetEmailTokenGateway());
    }

    @Bean
    UserUseCaseImpl userUseCaseImpl() {
        log.debug("Setup of User Use Case Bean.");
        return new UserUseCaseImpl(userGateway(), tokenGateway());
    }

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailContentBuilder emailContentBuilder;

    @Bean
    EmailService emailGateway() {
        log.debug("Setup of Send Email Gateway Implementation Bean.");
        return new EmailService(javaMailSender, emailContentBuilder);
    }

    @Bean
    EmailUseCaseImpl emailUseCase() {
        log.debug("Setup of Send Email Use Case Bean.");
        return new EmailUseCaseImpl(emailGateway());
    }
}
