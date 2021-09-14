/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.factory.implementations;

import br.com.fenrir.auth.core.entity.domain.EmailContent;
import br.com.fenrir.auth.core.entity.domain.implementations.EmailContentImpl;
import br.com.fenrir.auth.core.entity.enumerators.EnumContentTemplate;
import br.com.fenrir.auth.core.entity.factory.EmailContentFactory;

import java.util.Map;

/**
 * <p>
 *      Domain {@link EmailContent} Factory.
 *      This is a implementation of the Design Pattern Abstract Factory.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class EmailContentFactoryImpl implements EmailContentFactory {

    @Override
    public EmailContent create(String template, Map<String, Object> content) {
        return EmailContentImpl.builder()
                .template(EnumContentTemplate.getTemplateByName(template))
                .content(content)
                .build();
    }
}
