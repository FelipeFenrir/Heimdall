/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.converters.implementations;

import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.EmailBoundary;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations.EmailBoundaryMapper;
import br.com.fenrir.auth.adapters.entrypoint.converters.DeliveryConverter;
import br.com.fenrir.auth.core.entity.domain.Email;
import br.com.fenrir.auth.core.entity.domain.implementations.EmailImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *     Converter from {@link Email} Domain.
 * </p>
 * <p>
 *     Convert {@link Email} to {@link EmailBoundaryMapper}.
 *     Convert {@link EmailBoundaryMapper} to {@link Email}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class EmailDeliveryConverter implements DeliveryConverter<EmailBoundary, Email> {

    @Autowired
    private EmailContentDeliveryConverter emailContentDeliveryConverter;

    @Override
    public EmailBoundary mapToBoundary(Email domainObject) {
        return EmailBoundaryMapper.builder()
                .to(domainObject.getTo())
                .from(domainObject.getFrom())
                .subject(domainObject.getSubject())
                .emailContent(emailContentDeliveryConverter.mapToBoundary(domainObject.getEmailContent()))
                .build();
    }

    @Override
    public Email mapToEntity(EmailBoundary boundaryObject) {
        return EmailImpl.builder()
                .to(boundaryObject.getTo())
                .from(boundaryObject.getFrom())
                .subject(boundaryObject.getSubject())
                .emailContent(emailContentDeliveryConverter.mapToEntity(boundaryObject.getEmailContent()))
                .build();
    }
}
