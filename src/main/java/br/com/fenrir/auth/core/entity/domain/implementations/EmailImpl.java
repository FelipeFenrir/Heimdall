/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.domain.implementations;

import br.com.fenrir.auth.core.entity.domain.Email;
import br.com.fenrir.auth.core.entity.domain.EmailContent;
import br.com.fenrir.auth.core.entity.util.SelfValidating;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 *      Domain {@link Email} Implementation Class.
 *      This class has the Design Pattern Builder implemented using Lombok.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class EmailImpl extends SelfValidating<EmailImpl> implements Email {

    @NotNull
    @NotEmpty
    private String to;
    @NotNull
    @NotEmpty
    private String from;
    @NotNull
    @NotEmpty
    private String subject;
    private EmailContent emailContent;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
