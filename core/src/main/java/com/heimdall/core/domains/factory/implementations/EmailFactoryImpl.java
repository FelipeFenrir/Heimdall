/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.factory.implementations;

import com.heimdall.core.domains.model.Email;
import com.heimdall.core.domains.model.EmailContent;
import com.heimdall.core.domains.model.implementations.EmailImpl;
import com.heimdall.core.domains.factory.EmailFactory;

/**
 * <p>
 *      Domain {@link Email} Factory.
 *      This is a implementation of the Design Pattern Abstract Factory.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class EmailFactoryImpl implements EmailFactory {

    @Override
    public Email create(String to, String from, String subject, EmailContent emailContent) {
        return EmailImpl.builder().to(to).from(from).subject(subject).emailContent(emailContent).build();
    }
}
