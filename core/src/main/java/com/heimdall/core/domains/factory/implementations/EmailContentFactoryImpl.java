/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.factory.implementations;

import com.heimdall.core.domains.model.EmailContent;
import com.heimdall.core.domains.model.implementations.EmailContentImpl;
import com.heimdall.core.domains.enumerators.EnumContentTemplate;
import com.heimdall.core.domains.factory.EmailContentFactory;

import java.util.Map;

public class EmailContentFactoryImpl implements EmailContentFactory {

    @Override
    public EmailContent create(String template, Map<String, Object> content) {
        return EmailContentImpl.builder()
                .template(EnumContentTemplate.getTemplateByName(template))
                .content(content)
                .build();
    }
}
