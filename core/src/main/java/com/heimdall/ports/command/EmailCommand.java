/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.ports.command;

import com.heimdall.core.domains.model.Email;
import com.heimdall.core.exceptions.SendEmailException;

/**
 * <p>
 *      Send Email use case interface.
 * </p>
 * <p>
 *     This class use Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface EmailCommand {

    //TODO: Extends of Command Port

    /**
     * <p>
     *     Send email according of his template.
     * </p>
     * @param email {@link Email} object.
     */
    void sendEmail(Email email) throws SendEmailException;
}
