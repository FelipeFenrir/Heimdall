/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.event.listeners;

import com.heimdall.entrypoint.converters.implementations.EmailDeliveryConverter;
import com.heimdall.entrypoint.event.OnSendMailEvent;
import com.heimdall.core.exceptions.SendEmailException;
import com.heimdall.ports.command.EmailCommand;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<OnSendMailEvent> {

    @Autowired
    private EmailDeliveryConverter emailDeliveryConverter;

    @Autowired
    private EmailCommand emailCommand;

    @Override
    public void onApplicationEvent(@NonNull final OnSendMailEvent event) {
        this.sendEmail(event);
    }

    private void sendEmail(final OnSendMailEvent event) {
        log.info("Event on Registration Complete started.");

        final String applicationUrl = event.getApplicationUrl();

        // This moment the send e-mail use case is used.
        try {
            emailCommand.sendEmail(emailDeliveryConverter.mapToEntity(event.getEmailBoundary()));
        } catch (SendEmailException e) {
            log.error("Error to send Email event.");
            // TODO: Aplicar exceção correta.
            e.printStackTrace();
        }
    }
}
