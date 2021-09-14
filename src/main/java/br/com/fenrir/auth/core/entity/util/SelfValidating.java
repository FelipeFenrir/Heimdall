/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.util;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

/**
 * <p>
 *      Class to perform a self validation on other classes.
 * </p>
 * @param <T> Class to be self-validating
 * @author Felipe de Andrade Batista
 */
public abstract class SelfValidating<T> {

    private final Validator validator;

    /**
     * <p>
     *      Constructor Method.
     * </p>
     */
    protected SelfValidating() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * <p>
     *      Evaluates all Bean Validations on the attributes of this
     *      instance.
     * </p>
     */
    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T)this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
