/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.ports.integration;

import com.heimdall.core.domains.model.Email;
import com.heimdall.core.exceptions.SendEmailException;

/**
 * <p>
 *     {@link Email} gateway interface.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface EmailGateway {

    /**
     * <p>
     *     Prepare email body and send.
     * </p>
     * @param email {@link Email} Object.
     * @exception SendEmailException Send Email Operation is not possible.
     */
    void prepareAndSend(Email email) throws SendEmailException;
}
