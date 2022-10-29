/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.model;

/**
 * <p>
 *      Domain Interface of {@link Email}.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface Email extends BaseDomain {

    /**
     * <p>
     *     Get Email To.
     * </p>
     * @return {@link Email} to.
     */
    String getTo();

    /**
     * <p>
     *     Get Email From.
     * </p>
     * @return {@link Email} From.
     */
    String getFrom();

    /**
     * <p>
     *     Get Email Subject.
     * </p>
     * @return {@link Email} Subject.
     */
    String getSubject();

    /**
     * <p>
     *     Get all Content of Email.
     * </p>
     * @return {@link EmailContent} Content.
     */
    EmailContent getEmailContent();
}
