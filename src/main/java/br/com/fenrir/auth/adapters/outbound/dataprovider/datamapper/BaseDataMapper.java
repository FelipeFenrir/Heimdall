/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * <p>
 *      <h2>BaseDataMapper.</h2>
 *      MappedSuperclass that contains all the necessary fields.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class BaseDataMapper implements Serializable {

    private static final long serialVersionUID = 8606463085240844337L;

    /**
     * <p>
     *      The version is used to ensure integrity when performing the merge
     *      operation and for optimistic concurrency control.
     * </p>
     */
    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    /**
     * <p>
     *      Creation timestamp of the containing entity.
     * </p>
     */
    @CreationTimestamp
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    /**
     * <p>
     *      Update timestamp of the containing entity.
     * </p>
     */
    @UpdateTimestamp
    @Column(name = "updated_on", nullable = false)
    private LocalDateTime updatedOn;
}
