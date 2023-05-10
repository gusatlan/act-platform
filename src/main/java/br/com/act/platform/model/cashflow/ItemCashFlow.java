package br.com.act.platform.model.cashflow;

import br.com.act.platform.model.enums.ItemCashFlowType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class ItemCashFlow implements Comparable<ItemCashFlow> {
    @NotNull(message = "Date not be null")
    private LocalDateTime date;

    @NotNull(message = "Item type not be null")
    private ItemCashFlowType type;

    @NotNull(message = "Value not be null")
    @Min(value = 0, message = "Value must be positive")
    private BigDecimal value;

    @NotNull(message = "Description not be null")
    @NotBlank(message = "Description not be empty")
    private String description;

    public LocalDateTime getDate() {
        return date;
    }

    public ItemCashFlowType getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public ItemCashFlow() {
    }

    private ItemCashFlow(final LocalDateTime date,
                         final ItemCashFlowType type,
                         final BigDecimal value,
                         final String description
    ) {
        this.date = date;
        this.type = type;
        this.value = value;
        this.description = description;
    }

    @Override
    public int compareTo(ItemCashFlow itemCashFlow) {
        return itemCashFlow != null && itemCashFlow.date != null && date != null ? date.compareTo(itemCashFlow.date) : 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ItemCashFlow other &&
                isValid() &&
                other.isValid() &&
                date.isEqual(other.getDate()) &&
                type.equals(other.getType()) &&
                value.equals(other.getValue()) &&
                description.trim().equalsIgnoreCase(other.getDescription().trim());
    }

    @Override
    public int hashCode() {
        return (date != null ? date.hashCode() : 0) ^
                (type != null ? type.hashCode() : 0) ^
                (value != null ? value.hashCode() : 0) ^
                (description != null ? description.hashCode() : 0);
    }

    @JsonIgnore
    public Boolean isValid() {
        return date != null && type != null && value != null && value.doubleValue() >= 0D && description != null && !description.isBlank();
    }


    public static final class Builder {
        private LocalDateTime date;
        private ItemCashFlowType type;
        private BigDecimal value;
        private String description;

        public Builder withDate(final LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder withType(final ItemCashFlowType type) {
            this.type = type;
            return this;
        }

        public Builder withValue(final BigDecimal value) {
            this.value = value.abs();
            return this;
        }

        public Builder withValue(final Double value) {
            return withValue(BigDecimal.valueOf(value));
        }

        public Builder withDescription(final String description) {
            this.description = description != null ? description.trim().toUpperCase() : null;
            return this;
        }

        public ItemCashFlow build() {
            return new ItemCashFlow(date, type, value, description);
        }
    }
}
