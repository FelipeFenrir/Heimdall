/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.outbound.smtp.service;

import br.com.fenrir.auth.core.entity.domain.EmailContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

/**
 * <p>
 *      {@link EmailContentBuilder} is a implementation of build content email body with HTML template using Thymeleaf
 *      template engine.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Service
public class EmailContentBuilder {

    private final ITemplateEngine templateEngine;

    /**
     * <p>
     *     Constructor method.
     * </p>
     * @param templateEngine {@link ITemplateEngine} is a template processor engine.
     */
    @Autowired
    public EmailContentBuilder(ITemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    /**
     * <p>
     *      Populate email template with custom message.
     * </p>
     * @param emailContent {@link EmailContent} object with all content variables of email body.
     * @return Template processed with email content body.
     */
    public String build(EmailContent emailContent) {
        Context context = new Context();
        context.setVariables(emailContent.getContent());
        return build(emailContent.getTemplate(), context);
    }

    /**
     * <p>
     *      Populate email template with custom message.
     * </p>
     *
     * @param templatePath Path of Template Mail HTML.
     * @param context {@link IContext} Thymeleaf context object.
     * @return Template processed with all variables.
     */
    public String build(String templatePath, IContext context) {
        return templateEngine.process(templatePath, context);
    }
}
