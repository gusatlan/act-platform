package br.com.act.platform.model.cashflow;

import br.com.act.platform.util.DateUtils;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public final class CashFlow {
    @NotNull(message = "Date not be null")
    private LocalDate date;

    @NotNull(message = "Total not be null")
    private BigDecimal total = BigDecimal.ZERO;
    private Collection<ItemCashFlow> items;

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Collection<ItemCashFlow> getItems() {
        return items;
    }

    public CashFlow() {
    }

    private CashFlow(final LocalDate date,
                     final Collection<ItemCashFlow> items,
                     final BigDecimal total) {
        this.date = date;
        this.items = items;
        this.total = total;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CashFlow other && date != null && date.isEqual(other.getDate());
    }

    @Override
    public int hashCode() {
        return date != null ? date.hashCode() : 0;
    }

    public static final class Builder {
        private LocalDate date = DateUtils.now().toLocalDate();
        private Collection<ItemCashFlow> items = new HashSet<>();

        private Collection<ItemCashFlow> applyItems(final Collection<ItemCashFlow> items) {
            return items == null ? new HashSet<>() :
                    items
                            .stream()
                            .filter(ItemCashFlow::isValid)
                            .collect(Collectors.toSet());
        }

        private BigDecimal calculateTotal() {
            BigDecimal total = BigDecimal.ZERO;

            if (items != null) {
                total = items
                        .stream()
                        .map(it ->
                                it.getValue().multiply(BigDecimal.valueOf(it.getType().getSignal()))
                        )
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO);
            }

            return total;
        }

        public Builder withDate(final LocalDate date) {
            this.date = date != null ? date : DateUtils.now().toLocalDate();
            return this;
        }

        public Builder withDate(final LocalDateTime date) {
            return withDate(date != null ? date.toLocalDate() : null);
        }

        public Builder withItems(final Collection<ItemCashFlow> items) {
            this.items = applyItems(items);
            return this;
        }

        public CashFlow build() {
            return new CashFlow(date, items, calculateTotal());
        }
    }
}
