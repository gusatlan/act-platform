package br.com.act.platform.model.exceptions;

import jakarta.validation.ConstraintViolation;

import java.util.Collection;
import java.util.stream.Collectors;

public class EntityInvalidException extends ApplicationException {

    public EntityInvalidException() {
        super("Entity invalid");
    }

    public EntityInvalidException(final String message) {
        super(message);
    }

    public <T> EntityInvalidException(final Collection<ConstraintViolation<T>> violations) {
        super(violations != null ?
                violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", ")) :
                "Entity invalid"
        );
    }
}
