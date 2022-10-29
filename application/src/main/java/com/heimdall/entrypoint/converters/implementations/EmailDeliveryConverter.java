/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.converters.implementations;

import com.heimdall.entrypoint.boundary.dto.IEmailDto;
import com.heimdall.entrypoint.boundary.dto.implementations.EmailDto;
import com.heimdall.entrypoint.converters.IDeliveryConverter;
import com.heimdall.core.domains.model.Email;
import com.heimdall.core.domains.model.implementations.EmailImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *     Converter from {@link Email} Domain.
 * </p>
 * <p>
 *     Convert {@link Email} to {@link EmailDto}.
 *     Convert {@link EmailDto} to {@link Email}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class EmailDeliveryConverter implements IDeliveryConverter<IEmailDto, Email> {

    @Autowired
    private EmailContentDeliveryConverter emailContentDeliveryConverter;

    @Override
    public IEmailDto mapToBoundary(Email domainObject) {
        return EmailDto.builder()
                .to(domainObject.getTo())
                .from(domainObject.getFrom())
                .subject(domainObject.getSubject())
                .emailContent(emailContentDeliveryConverter.mapToBoundary(domainObject.getEmailContent()))
                .build();
    }

    @Override
    public Email mapToEntity(IEmailDto boundaryObject) {
        return EmailImpl.builder()
                .to(boundaryObject.getTo())
                .from(boundaryObject.getFrom())
                .subject(boundaryObject.getSubject())
                .emailContent(emailContentDeliveryConverter.mapToEntity(boundaryObject.getEmailContent()))
                .build();
    }
}
