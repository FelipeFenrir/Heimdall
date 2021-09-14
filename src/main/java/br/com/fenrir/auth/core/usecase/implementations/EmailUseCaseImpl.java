/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.usecase.implementations;

import br.com.fenrir.auth.core.entity.domain.Email;
import br.com.fenrir.auth.core.usecase.EmailUseCase;
import br.com.fenrir.auth.core.usecase.exceptions.SendEmailException;
import br.com.fenrir.auth.core.usecase.ports.EmailGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     Send Email use case implementation.
 *     This class implements the Facade Design Pattern.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
@AllArgsConstructor
public class EmailUseCaseImpl implements EmailUseCase {

    private final EmailGateway emailGateway;

    @Override
    public void sendEmail(Email email) throws SendEmailException {
        log.debug("Sending email");
        emailGateway.prepareAndSend(email);
    }
}
