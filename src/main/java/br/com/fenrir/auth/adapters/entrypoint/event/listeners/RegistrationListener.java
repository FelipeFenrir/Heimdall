/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.event.listeners;

import br.com.fenrir.auth.adapters.entrypoint.converters.implementations.EmailDeliveryConverter;
import br.com.fenrir.auth.adapters.entrypoint.event.OnSendMailEvent;
import br.com.fenrir.auth.core.usecase.EmailUseCase;

import br.com.fenrir.auth.core.usecase.exceptions.SendEmailException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * <p>
 *     Listener on registration flow.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<OnSendMailEvent> {

    @Autowired
    private EmailDeliveryConverter emailDeliveryConverter;

    @Autowired
    private EmailUseCase emailUseCase;

    /**
     * <p>
     *     In Event start.
     * </p>
     * @param event Event on {@link OnSendMailEvent}.
     */
    @Override
    public void onApplicationEvent(@NonNull final OnSendMailEvent event) {
        this.sendEmail(event);
    }

    /**
     * <p>
     *      This method send the e-mail in event start.
     * </p>
     * @param event {@link ApplicationEvent} object.
     */
    private void sendEmail(final OnSendMailEvent event) {
        log.info("Event on Registration Complete started.");

        final String applicationUrl = event.getApplicationUrl();

        // This moment the send e-mail use case is used.
        try {
            emailUseCase.sendEmail(emailDeliveryConverter.mapToEntity(event.getEmailBoundary()));
        } catch (SendEmailException e) {
            log.error("Error to send Email event.");
            // TODO: Aplicar exceção correta.
            e.printStackTrace();
        }
    }
}
