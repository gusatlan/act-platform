package br.com.act.platform.model.enums;

public enum ItemCashFlowType {
    DEBIT(-1), CREDIT(1);

    private final Integer signal;

    public Integer getSignal() {
        return signal;
    }

    ItemCashFlowType(final Integer signal) {
        this.signal = signal;
    }
}
