/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.boundary.factory;

import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.EmailBoundary;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.EmailContentBoundary;

/**
 * <p>
 *      Interface of {@link EmailBoundary} Factory.
 * </p>
 * <p>
 *     This class use Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface EmailBoundaryFactory {

    /**
     * <p>
     *     Create {@link EmailBoundary} object.
     * </p>
     * @param to "To" of Email.
     * @param from "From" of Email.
     * @param subject "Subject" of Email.
     * @param content {@link EmailContentBoundary} object representing Email Content.
     * @return {@link EmailBoundary} object.
     */
    EmailBoundary create(String to, String from, String subject, EmailContentBoundary content);

    /**
     * <p>
     *     Create {@link EmailBoundary} object with content variables of Registration Template.
     * </p>
     * @param to "To" of Email.
     * @param from "From" of Email.
     * @param subject "Subject" of Email.
     * @param title Title of Content.
     * @param message Message of Content.
     * @param link Link to confirm registration.
     * @return {@link EmailBoundary} object.
     */
    EmailBoundary createRegistrationMail(String to, String from, String subject, String title, String message,
                                         String link);
}
