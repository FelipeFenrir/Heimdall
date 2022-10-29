package com.heimdall.ports.datastore.standart;

/**
 * <p>
 *     Generic Data Store operation interface.
 * </p>
 * @param <T> Model to be used
 */
public interface UnstoreModel<T> {
    T delete(T model);
}
