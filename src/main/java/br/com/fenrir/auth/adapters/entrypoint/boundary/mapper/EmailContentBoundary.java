/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.boundary.mapper;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 *     Email Content Boundary Mapper from delivery layer.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface EmailContentBoundary extends Serializable {

    /**
     * <p>
     *     Template of mail content.
     * </p>
     * @return Content mail template.
     */
    String getTemplate();

    /**
     * <p>
     *     Provide a method to get all variables of Mail Content.
     * </p>
     * @return Map (Key-Value) of all variables of Mail Content.
     */
    Map<String, Object> getContent();
}
