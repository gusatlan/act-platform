package br.com.act.platform.util;

import jakarta.validation.*;

import java.util.HashSet;
import java.util.Set;

public final class ValidationUtils {

    public static <T> Set<ConstraintViolation<T>> validate(final T obj) {
        Set<ConstraintViolation<T>> violations = new HashSet<>();

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            violations = factory.getValidator().validate(obj);
        } catch(ValidationException e) {
            e.printStackTrace();
        }

        return violations;
    }
}
