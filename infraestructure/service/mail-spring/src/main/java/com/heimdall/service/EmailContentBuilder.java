/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.service;

import com.heimdall.core.domains.model.EmailContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

@Service
public class EmailContentBuilder {

    private final ITemplateEngine templateEngine;

    public EmailContentBuilder(@Autowired ITemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(EmailContent emailContent) {
        Context context = new Context();
        context.setVariables(emailContent.getContent());
        return build(emailContent.getTemplate(), context);
    }

    public String build(String templatePath, IContext context) {
        return templateEngine.process(templatePath, context);
    }
}
