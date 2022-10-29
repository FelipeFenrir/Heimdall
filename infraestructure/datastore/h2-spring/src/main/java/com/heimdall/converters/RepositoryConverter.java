/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.converters;

import java.io.Serializable;

/**
 * <p>
 *     Convert Domain to Entity-Repository object and Entity-Repository in Domain object.
 * </p>
 * @param <T> Entity Table Object.
 * @param <P> Entity Domain Object.
 * @author Felipe de Andrade Batista
 */
public interface RepositoryConverter<T extends Serializable, P extends Serializable> {

    /**
     * Convert Domain to Entity-Repository object.
     * @param domainObject Domain object.
     * @return Entity Repository object for persistence.
     */
    default T mapToRepository(final P domainObject) {
        throw new UnsupportedOperationException();
    }

    /**
     * Convert Entity-Repository object to Domain.
     * @param repositoryObject Entity Repository.
     * @return Domain object.
     */
    default P mapToEntity(final T repositoryObject) {
        throw new UnsupportedOperationException();
    }
}
