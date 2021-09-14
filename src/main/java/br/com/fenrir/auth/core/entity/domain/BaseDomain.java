/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *     Base entity domain class.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface BaseDomain extends Serializable {

    /**
     * <p>
     *      Creation timestamp of the containing entity.
     * </p>
     */
    LocalDateTime getCreatedOn();

    /**
     * <p>
     *      Update timestamp of the containing entity.
     * </p>
     */
    LocalDateTime getUpdatedOn();
}
