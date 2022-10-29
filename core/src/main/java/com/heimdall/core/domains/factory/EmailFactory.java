/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.factory;

import com.heimdall.core.domains.model.Email;
import com.heimdall.core.domains.model.EmailContent;

/**
 * <p>
 *      Interface of {@link Email} Factory.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface EmailFactory {

    /**
     * <p>
     *     Create {@link Email} object.
     * </p>
     * @param to Representation of To person on Email Message.
     * @param from Representation of From person on Email Message.
     * @param subject Representation of Subject on Email Message.
     * @param emailContent {@link EmailContent} object.
     * @return {@link Email} object.
     */
    Email create(String to, String from, String subject, EmailContent emailContent);
}
