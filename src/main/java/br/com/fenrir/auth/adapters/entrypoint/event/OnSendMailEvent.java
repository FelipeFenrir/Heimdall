/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.event;


import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.EmailBoundary;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * <p>
 *     Application Event for Send Email event.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Getter
public class OnSendMailEvent extends ApplicationEvent {
    private final String applicationUrl;
    private final Locale locale;
    private final EmailBoundary emailBoundary;

    /**
     * <p>
     *     Constructor method for event.
     * </p>
     * @param emailBoundary {@link EmailBoundary} in Event.
     * @param locale {@link Locale} in Event.
     * @param appUrl URL for application
     */
    public OnSendMailEvent(EmailBoundary emailBoundary, Locale locale, String appUrl) {
        super(emailBoundary);
        this.emailBoundary = emailBoundary;
        this.locale = locale;
        this.applicationUrl = appUrl;
    }
}
