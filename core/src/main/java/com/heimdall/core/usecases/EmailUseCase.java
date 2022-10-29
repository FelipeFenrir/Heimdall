/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.usecases;

import com.heimdall.core.domains.model.Email;
import com.heimdall.ports.command.EmailCommand;
import com.heimdall.core.exceptions.SendEmailException;
import com.heimdall.ports.integration.EmailGateway;

import lombok.AllArgsConstructor;

/**
 * <p>
 *     Send Email use case implementation.
 *     This class implements the Facade Design Pattern.
 * </p>
 * @author Felipe de Andrade Batista
 */
@AllArgsConstructor
public class EmailUseCase implements EmailCommand {

    private final EmailGateway emailGateway;

    @Override
    public void sendEmail(Email email) throws SendEmailException {
        //log.debug("Sending email");
        emailGateway.prepareAndSend(email);
    }
}
