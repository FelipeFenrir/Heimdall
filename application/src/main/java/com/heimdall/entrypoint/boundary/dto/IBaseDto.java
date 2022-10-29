/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.boundary.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *      <h2>IBaseDto.</h2>
 *      Superclass that contains all the necessary fields.
 * </p>
 * @author Felipe de Andrade Batista
 */
public interface IBaseDto extends Serializable {

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
