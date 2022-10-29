package com.heimdall.ports.datastore.standart;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *     Generic Data Store operation interface.
 * </p>
 * @param <T> Model to be used
 */
public interface RetrieveStoredModel<T> {
    Optional<T> findById(final Long id);
    List<T> findAll();
}
