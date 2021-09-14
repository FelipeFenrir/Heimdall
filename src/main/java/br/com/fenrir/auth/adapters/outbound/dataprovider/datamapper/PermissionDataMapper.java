/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 *      Permission Data Mapper class from dataprovider layer.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Entity
@Table(name = "permissions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings({"PersistenceUnitPresent"})
public class PermissionDataMapper extends BaseDataMapper implements Serializable {

    private static final long serialVersionUID = 6682717553223623346L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "nativeGenerator")
    @GenericGenerator(name = "nativeGenerator", strategy = "native")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
