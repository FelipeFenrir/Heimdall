/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.boundary.factory;

import com.heimdall.entrypoint.boundary.dto.IEmailContentDto;
import com.heimdall.entrypoint.boundary.dto.IEmailDto;

/**
 * <p>
 *      Interface of {@link IEmailDto} Factory.
 * </p>
 * <p>
 *     This class use Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface IEmailDtoFactory {

    /**
     * <p>
     *     Create {@link IEmailDto} object.
     * </p>
     * @param to "To" of Email.
     * @param from "From" of Email.
     * @param subject "Subject" of Email.
     * @param content {@link IEmailContentDto} object representing Email Content.
     * @return {@link IEmailDto} object.
     */
    IEmailDto create(String to, String from, String subject, IEmailContentDto content);

    /**
     * <p>
     *     Create {@link IEmailDto} object with content variables of Registration Template.
     * </p>
     * @param to "To" of Email.
     * @param from "From" of Email.
     * @param subject "Subject" of Email.
     * @param title Title of Content.
     * @param message Message of Content.
     * @param link Link to confirm registration.
     * @return {@link IEmailDto} object.
     */
    IEmailDto createRegistrationMail(String to, String from, String subject, String title, String message,
                                         String link);
}
