/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.service;

import com.heimdall.core.domains.model.Email;
import com.heimdall.core.exceptions.SendEmailException;
import com.heimdall.ports.integration.EmailGateway;

import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.nio.charset.StandardCharsets;

@Slf4j
public class EmailService implements EmailGateway {

    private final JavaMailSender mailSender;
    private final EmailContentBuilder mailContentBuilder;

    public EmailService(JavaMailSender mailSender, EmailContentBuilder mailContentBuilder) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

    @Override
    public void prepareAndSend(Email email) throws SendEmailException {
        try {
            log.debug("Preparing email to send.");
            MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(
                        mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name());

                messageHelper.setFrom(email.getFrom());
                messageHelper.setTo(email.getTo());
                messageHelper.setSubject(email.getSubject());

                String content = mailContentBuilder.build(email.getEmailContent());
                messageHelper.setText(content, true);
            };
            log.debug("Sending email with subject {} to {}.", email.getSubject(), email.getTo());
            mailSender.send(mimeMessagePreparator);
        } catch (MailException mailException) {
            log.error("A error on application event of email send.");
            throw new SendEmailException("A problem occurs on email send application event.");
        }
    }
}
