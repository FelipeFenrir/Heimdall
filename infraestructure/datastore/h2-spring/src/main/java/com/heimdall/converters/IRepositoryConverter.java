/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.converters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *     Convert Domain to Entity-Repository object and Entity-Repository in Domain object.
 * </p>
 * @param <T> Entity Table Object.
 * @param <P> Entity Domain Object.
 * @author Felipe de Andrade Batista
 */
public interface IRepositoryConverter<T extends Serializable, P extends Serializable> {

    /**
     * Convert Domain to Entity-Repository object.
     * @param domainObject Domain object.
     * @return Entity Repository object for persistence.
     */
    default T mapToRepository(final P domainObject) {
        throw new UnsupportedOperationException();
    }

    default List<T> mapToRepository(final List<P> domainObject) {
        return Objects.isNull(domainObject) || domainObject.isEmpty() ?
            List.of() :
            domainObject.stream().map(this::mapToRepository).collect(Collectors.toList());
    }

    /**
     * Convert Entity-Repository object to Domain.
     * @param repositoryObject Entity Repository.
     * @return Domain object.
     */
    default P mapToDomain(final T repositoryObject) {
        throw new UnsupportedOperationException();
    }

    default List<P> mapToDomain(final List<T> repositoryObject) {
        return Objects.isNull(repositoryObject) || repositoryObject.isEmpty() ?
            List.of() :
            repositoryObject.stream().map(this::mapToDomain).collect(Collectors.toList());
    }

    default Page<P> mapToDomain(Page<T> repositoryObject){
        List<P> list = this.mapToDomain(repositoryObject.getContent());
        return new PageImpl<>(list, repositoryObject.getPageable(), repositoryObject.getTotalElements());
    }
}
