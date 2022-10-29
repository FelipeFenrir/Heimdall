package com.heimdall.ports.datastore.standart;

/**
 * <p>
 *     Generic Data Store operation interface.
 * </p>
 * @param <T> Model to be used
 */
public interface StoreModel<T> {
    T save(T model);
}
