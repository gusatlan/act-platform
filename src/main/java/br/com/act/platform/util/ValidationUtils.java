package br.com.act.platform.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.ValidatorFactory;

import java.util.HashSet;
import java.util.Set;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static <T> Set<ConstraintViolation<T>> validate(final T obj) {
        Set<ConstraintViolation<T>> violations = new HashSet<>();

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            violations = factory.getValidator().validate(obj);
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        return violations;
    }
}
