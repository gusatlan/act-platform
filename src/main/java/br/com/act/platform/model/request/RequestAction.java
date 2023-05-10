package br.com.act.platform.model.request;

import br.com.act.platform.model.enums.ActionType;
import jakarta.validation.constraints.NotNull;

public final class RequestAction<T> {

    @NotNull(message = "Action not be null")
    private ActionType action = ActionType.UPSERT;

    @NotNull(message = "Entity not be null")
    private T entity;

    public RequestAction() {
    }

    private RequestAction(final ActionType action, final T entity) {
        this.action = action;
        this.entity = entity;
    }

    public ActionType getAction() {
        return action;
    }

    public T getEntity() {
        return entity;
    }

    public static final class Builder<T> {
        private ActionType action;
        private T entity;

        public Builder<T> withAction(final ActionType action) {
            this.action = action;
            return this;
        }

        public Builder<T> withEntity(final T entity) {
            this.entity = entity;
            return this;
        }

        public RequestAction<T> build() {
            return new RequestAction<>(action, entity);
        }
    }

}
