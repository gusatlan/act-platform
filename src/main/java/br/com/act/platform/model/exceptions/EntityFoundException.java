package br.com.act.platform.model.exceptions;

public class EntityFoundException extends ApplicationException{

    public EntityFoundException() {
        super("Entity found");
    }

    public EntityFoundException(final String message) {
        super(message);
    }
}
