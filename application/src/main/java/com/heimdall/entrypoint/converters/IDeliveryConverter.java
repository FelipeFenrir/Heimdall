/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.converters;

import java.io.Serializable;

/**
 * Convert Domain to Entity-Boundary object and Entity-Boundary in Domain object.
 * @param <T> Entity Boundary Object.
 * @param <P> Entity Domain Object.
 * @author Felipe de Andrade Batista
 */
public interface IDeliveryConverter<T extends Serializable, P extends Serializable> {

    /**
     * Convert Domain to Entity-Boundary object.
     * @param domainObject Domain object.
     * @return Entity Boundary object for delivery.
     */
    default T mapToBoundary(final P domainObject) {
        throw new UnsupportedOperationException();
    }

    /**
     * Convert Entity-Boundary object to Domain.
     * @param boundaryObject Entity Boundary.
     * @return Domain object.
     */
    default P mapToEntity(final T boundaryObject) {
        throw new UnsupportedOperationException();
    }
}
