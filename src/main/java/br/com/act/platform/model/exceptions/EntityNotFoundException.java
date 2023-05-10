package br.com.act.platform.model.exceptions;

public class EntityNotFoundException extends ApplicationException{

    public EntityNotFoundException() {
        super("Entity not found");
    }

    public EntityNotFoundException(final String message) {
        super(message);
    }
}
