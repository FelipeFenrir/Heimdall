/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.domain;

/**
 * <p>
 *      Domain Interface of {@link Permission}.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface Permission extends BaseDomain {

    /**
     * <p>
     *      Get ID of {@link Permission} Entity.
     * </p>
     * @return {@link Permission} ID.
     */
    long getId();

    /**
     * <p>
     *      Get Name of {@link Permission} Entity.
     * </p>
     * @return {@link Permission} name.
     */
    String getName();
}
