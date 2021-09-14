/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.boundary.mapper;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *      <h2>BaseBoundaryMapper.</h2>
 *      Superclass that contains all the necessary fields.
 * </p>
 * @author Felipe de Andrade Batista
 */
public interface BaseBoundaryMapper extends Serializable {

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
