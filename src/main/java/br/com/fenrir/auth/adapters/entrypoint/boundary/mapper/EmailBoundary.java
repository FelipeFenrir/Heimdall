/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.boundary.mapper;

import java.io.Serializable;

/**
 * <p>
 *     Email Boundary Mapper from delivery layer.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface EmailBoundary extends Serializable {

    /**
     * <p>
     *     Get "To" of Email.
     * </p>
     * @return the "To" property of email.
     */
    String getTo();

    /**
     * <p>
     *     Get "From" of Email.
     * </p>
     * @return the "To" property of email.
     */
    String getFrom();

    /**
     * <p>
     *     Get "Subject" of Email.
     * </p>
     * @return the "Subject" property of email.
     */
    String getSubject();

    /**
     * <p>
     *     Get {@link EmailContentBoundary} object with all variables of Mail Content.
     * </p>
     * @return {@link EmailContentBoundary} object.
     */
    EmailContentBoundary getEmailContent();
}
