/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.converters.implementations;

import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.EmailContentBoundary;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations.EmailContentBoundaryMapper;
import br.com.fenrir.auth.adapters.entrypoint.converters.DeliveryConverter;
import br.com.fenrir.auth.core.entity.domain.EmailContent;
//import br.com.fenrir.auth.core.entity.domain.implementations.EmailContentImpl;
import br.com.fenrir.auth.core.entity.factory.implementations.EmailContentFactoryImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     Converter from {@link EmailContent} Domain.
 * </p>
 * <p>
 *     Convert {@link EmailContent} to {@link EmailContentBoundary}.
 *     Convert {@link EmailContentBoundary} to {@link EmailContent}.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class EmailContentDeliveryConverter implements DeliveryConverter<EmailContentBoundary, EmailContent> {

    @Override
    public EmailContentBoundary mapToBoundary(EmailContent domainObject) {
        return EmailContentBoundaryMapper.builder()
                .template(domainObject.getTemplate())
                .content(domainObject.getContent())
                .build();
    }

    @Override
    public EmailContent mapToEntity(EmailContentBoundary boundaryObject) {
//        return EmailContentImpl.builder()
//                .template(boundaryObject.getTemplate())
//                .content(boundaryObject.getContent())
//                .build();
        return new EmailContentFactoryImpl().create(boundaryObject.getTemplate(), boundaryObject.getContent());
    }
}
