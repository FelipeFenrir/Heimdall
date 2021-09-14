/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.domain.implementations;

import br.com.fenrir.auth.core.entity.domain.EmailContent;
import br.com.fenrir.auth.core.entity.util.SelfValidating;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 *      Domain {@link EmailContent} Implementation Class.
 *      This class has the Design Pattern Builder implemented using Lombok.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class EmailContentImpl extends SelfValidating<EmailContentImpl> implements EmailContent {

    @NotNull
    @NotBlank
    private String template;
    @NotNull
    private Map<String, Object> content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
