/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.boundary.factory.implementations;

import br.com.fenrir.auth.adapters.entrypoint.boundary.factory.EmailBoundaryFactory;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.EmailBoundary;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.EmailContentBoundary;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations.EmailBoundaryMapper;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations.RegisterEmailContentBoundaryMapper;

/**
 * <p>
 *      Boundary class {@link EmailBoundary} Factory.
 *      This is a implementation of the Design Pattern Abstract Factory.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class EmailBoundaryFactoryImpl implements EmailBoundaryFactory {

    @Override
    public EmailBoundary create(String to, String from, String subject, EmailContentBoundary content) {
        return EmailBoundaryMapper.builder()
                .to(to)
                .from(from)
                .subject(subject)
                .emailContent(content)
                .build();
    }

    @Override
    public EmailBoundary createRegistrationMail(String to, String from, String subject, String title, String message,
                                                String link) {

        return EmailBoundaryMapper.builder()
                .to(to)
                .from(from)
                .subject(subject)
                .emailContent(
                        RegisterEmailContentBoundaryMapper.builder()
                                .title(title)
                                .message(message)
                                .link(link)
                                .build())
                .build();
    }
}
