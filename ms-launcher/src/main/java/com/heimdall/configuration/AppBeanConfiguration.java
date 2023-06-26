/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.configuration;

import com.heimdall.converters.impl.*;
import com.heimdall.core.usecases.AccountUseCase;
import com.heimdall.core.usecases.EmailUseCase;
import com.heimdall.core.usecases.UserUseCase;
import com.heimdall.entrypoint.converters.implementations.*;
import com.heimdall.ports.command.AccountCommand;
import com.heimdall.ports.command.EmailCommand;
import com.heimdall.ports.command.UserCommand;
import com.heimdall.ports.datastore.*;
import com.heimdall.ports.integration.EmailGateway;
import com.heimdall.repository.ResetEmailTokenRepository;
import com.heimdall.repository.ResetPasswordTokenRepository;
import com.heimdall.repository.RoleRepository;
import com.heimdall.repository.UserRepository;
import com.heimdall.service.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;


@Slf4j
@Configuration
public class AppBeanConfiguration {

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    public EmailContentBuilder emailContentBuilder;

    //Entrypoint - Delivery - Converter
    @Bean
    public EmailContentDeliveryConverter emailContentDeliveryConverter() {
        return new EmailContentDeliveryConverter();
    }

    @Bean
    public EmailDeliveryConverter emailDeliveryConverter(EmailContentDeliveryConverter emailContentDeliveryConverter) {
        return new EmailDeliveryConverter(emailContentDeliveryConverter);
    }

    @Bean
    public PermissionDeliveryConverter permissionDeliveryConverter() {
        return new PermissionDeliveryConverter();
    }

    @Bean
    public RoleDeliveryConverter roleDeliveryConverter(PermissionDeliveryConverter permissionDeliveryConverter) {
        return new RoleDeliveryConverter(permissionDeliveryConverter);
    }

    @Bean
    public UserDeliveryConverter userDeliveryConverter(RoleDeliveryConverter roleDeliveryConverter) {
        return new UserDeliveryConverter(roleDeliveryConverter);
    }

    //Integration - H2 - Converter
    @Bean
    public PermissionDataConverter permissionDataConverter() {
        return new PermissionDataConverter();
    }

    @Bean
    public RoleDataConverter roleDataConverter(PermissionDataConverter permissionDataConverter) {
        return new RoleDataConverter(permissionDataConverter);
    }

    @Bean
    public UserDataConverter userDataConverter(RoleDataConverter roleDataConverter) {
        return new UserDataConverter(roleDataConverter);
    }

    @Bean
    public ResetEmailTokenDataConverter resetEmailTokenDataConverter(UserDataConverter userDataConverter) {
        return new ResetEmailTokenDataConverter(userDataConverter);
    }

    @Bean
    public ResetPasswordTokenDataConverter resetPasswordTokenDataConverter(UserDataConverter userDataConverter) {
        return new ResetPasswordTokenDataConverter(userDataConverter);
    }

    //Integration - Port
    @Bean
    public EmailGateway emailGateway(
        JavaMailSender mailSender,
        EmailContentBuilder mailContentBuilder
    ) {
        return new EmailService(mailSender, mailContentBuilder);
    }

    //Datastore - Port
    @Bean
    public ResetEmailTokenGateway resetEmailTokenGateway(
        ResetEmailTokenRepository resetEmailTokenRepository,
        ResetEmailTokenDataConverter resetEmailTokenDataConverter
    ) {
        return new ResetEmailTokenService(resetEmailTokenRepository, resetEmailTokenDataConverter);
    }

    @Bean
    public ResetPasswordTokenGateway resetPasswordTokenGateway(
        ResetPasswordTokenRepository resetPasswordTokenRepository,
        ResetPasswordTokenDataConverter resetPasswordTokenDataConverter
    ) {
        return new ResetPasswordTokenService(resetPasswordTokenRepository, resetPasswordTokenDataConverter);
    }

    @Bean
    public RoleGateway roleGateway(
        RoleRepository roleRepository,
        RoleDataConverter roleDataConverter
    ) {
        return new RoleService(roleRepository, roleDataConverter);
    }

    @Bean
    public UserGateway userGateway(
        UserRepository userRepository,
        UserDataConverter userDataConverter
    ) {
        return new UserService(userRepository, userDataConverter);
    }

    //Command - Port
    @Bean
    public AccountCommand accountCommand(
        UserGateway userGateway,
        RoleGateway roleGateway,
        ResetPasswordTokenGateway resetPasswordTokenGateway,
        ResetEmailTokenGateway resetEmailTokenGateway
    ) {
        return new AccountUseCase(userGateway, roleGateway, resetPasswordTokenGateway, resetEmailTokenGateway);
    }

    @Bean
    public EmailCommand emailCommand(
        EmailGateway emailGateway
    ) {
        return new EmailUseCase(emailGateway);
    }

    @Bean
    public UserCommand userCommand(
        UserGateway userGateway
    ) {
        return new UserUseCase(userGateway);
    }
}
