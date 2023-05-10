package br.com.act.platform.model.enums;

public enum ItemCashFlowType {
    DEBIT(-1), CREDIT(1);

    private Integer signal;

    ItemCashFlowType(final Integer signal) {
        this.signal = signal;
    }
}
