package br.com.act.platform.model.exceptions;

public class ApplicationException extends Exception {

    public ApplicationException() {
        super("Application Exception");
    }

    public ApplicationException(final String message) {
        super(message);
    }
}
