/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.boundary.factory.implementations;

import com.heimdall.entrypoint.boundary.dto.IEmailContentDto;
import com.heimdall.entrypoint.boundary.dto.IEmailDto;
import com.heimdall.entrypoint.boundary.dto.implementations.EmailDto;
import com.heimdall.entrypoint.boundary.dto.implementations.RegisterEmailContentDto;
import com.heimdall.entrypoint.boundary.factory.IEmailBoundaryFactory;

public class EmailBoundaryFactory implements IEmailBoundaryFactory {

    @Override
    public IEmailDto create(String to, String from, String subject, IEmailContentDto content) {
        return EmailDto.builder()
                .to(to)
                .from(from)
                .subject(subject)
                .emailContent(content)
                .build();
    }

    @Override
    public IEmailDto createRegistrationMail(String to, String from, String subject, String title, String message,
                                                String link) {

        return EmailDto.builder()
                .to(to)
                .from(from)
                .subject(subject)
                .emailContent(
                    RegisterEmailContentDto.builder()
                                .title(title)
                                .message(message)
                                .link(link)
                                .build())
                .build();
    }
}
