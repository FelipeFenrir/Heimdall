/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.event;

import com.heimdall.entrypoint.boundary.dto.IEmailDto;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
public class OnSendMailEvent extends ApplicationEvent {
    private final String applicationUrl;
    private final Locale locale;
    private final IEmailDto emailBoundary;

    public OnSendMailEvent(IEmailDto emailBoundary, Locale locale, String appUrl) {
        super(emailBoundary);
        this.emailBoundary = emailBoundary;
        this.locale = locale;
        this.applicationUrl = appUrl;
    }
}
