/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.factory;

import br.com.fenrir.auth.core.entity.domain.EmailContent;

import java.util.Map;

/**
 * <p>
 *      Interface of {@link EmailContent} Factory.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface EmailContentFactory {

    /**
     * <p>
     *     Create {@link EmailContent} object.
     * </p>
     * @param template Template of Mail Content.
     * @param content Map with Key-Value of all variables of Email Template.
     * @return {@link EmailContent} object.
     */
    EmailContent create(String template, Map<String, Object> content);
}
