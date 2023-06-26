/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.converters.implementations;

import com.heimdall.entrypoint.boundary.dto.IEmailContentDto;
import com.heimdall.entrypoint.boundary.dto.implementations.EmailContentDto;
import com.heimdall.entrypoint.converters.IDeliveryConverter;

import com.heimdall.core.domains.model.EmailContent;
//import com.heimdall.core.entity.domain.implementations.EmailContentImpl;
import com.heimdall.core.domains.factory.implementations.EmailContentFactoryImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class EmailContentDeliveryConverter implements IDeliveryConverter<IEmailContentDto, EmailContent> {

    @Override
    public IEmailContentDto mapToBoundary(EmailContent domainObject) {
        return EmailContentDto.builder()
                .template(domainObject.getTemplate())
                .content(domainObject.getContent())
                .build();
    }

    @Override
    public EmailContent mapToEntity(IEmailContentDto boundaryObject) {
//        return EmailContentImpl.builder()
//                .template(boundaryObject.getTemplate())
//                .content(boundaryObject.getContent())
//                .build();
        return new EmailContentFactoryImpl().create(boundaryObject.getTemplate(), boundaryObject.getContent());
    }
}
