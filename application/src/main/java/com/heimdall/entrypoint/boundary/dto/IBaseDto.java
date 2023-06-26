/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.boundary.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IBaseDto extends Serializable {

    LocalDateTime getCreatedOn();

    LocalDateTime getUpdatedOn();
}
