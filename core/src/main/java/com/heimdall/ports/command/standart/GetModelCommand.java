package com.heimdall.ports.command.standart;

import java.util.List;

/**
 * <p>
 *     Generic Command Interface.
 * </p>
 * @param <T> Model to be used
 */
public interface GetModelCommand<T> {
    T getDetails(final Long id);
    List<T> getAll();
}
