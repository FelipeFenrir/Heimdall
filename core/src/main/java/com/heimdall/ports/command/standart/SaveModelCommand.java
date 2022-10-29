package com.heimdall.ports.command.standart;

/**
 * <p>
 *     Generic Command Interface.
 * </p>
 * @param <T> Model to be used
 */
public interface SaveModelCommand<T> {
    T save(final T model);
}
