/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.model;

import java.util.Collection;
import java.util.UUID;

/**
 * <p>
 *      Domain Interface of {@link Role}.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface Role extends BaseDomain {

    /**
     * <p>
     *      Get ID of {@link Role}.
     * </p>
     * @return ID of {@link Role}.
     */
    UUID getId();

    /**
     * <p>
     *      Get Name of {@link Role} Entity.
     * </p>
     * @return {@link Role} name.
     */
    String getName();

    /**
     * <p>
     *      Get list of {@link Permission} on this {@link Role}.
     * </p>
     * @return List of {@link Role} {@link Permission}.
     */
    Collection<Permission> getPermission();
}
