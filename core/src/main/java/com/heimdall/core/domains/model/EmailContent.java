/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.model;

import java.util.Map;

/**
 * <p>
 *      Domain Interface of {@link EmailContent}.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface EmailContent extends BaseDomain {

    /**
     * <p>
     *     Template of Content mail.
     * </p>
     * @return The template of mail content.
     */
    String getTemplate();

    /**
     * <p>
     *     Email content texts to replacer in template.
     * </p>
     *
     * @return {@link EmailContent} Map of Content.
     */
    Map<String, Object> getContent();
}
