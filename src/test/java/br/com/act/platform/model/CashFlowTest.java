package br.com.act.platform.model;

import br.com.act.platform.model.cashflow.CashFlow;
import br.com.act.platform.model.cashflow.ItemCashFlow;
import br.com.act.platform.model.enums.ItemCashFlowType;
import br.com.act.platform.util.JsonBaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

class CashFlowTest extends JsonBaseTest {

    private LocalDateTime getDate() {
        return LocalDateTime.of(2023, 5, 10, 8, 1, 0);
    }

    private Collection<ItemCashFlow> generateItems(final ItemCashFlowType lastType, final BigDecimal lastValue) {
        Collection<ItemCashFlow> items = new HashSet<>();
        LocalDateTime baseDate = getDate();

        for (int i = 0; i < 100; i++) {
            ItemCashFlowType type = i % 2 == 0 ? ItemCashFlowType.CREDIT : ItemCashFlowType.DEBIT;
            baseDate = baseDate.plusMinutes(1L);

            items.add(new ItemCashFlow.Builder()
                    .withDate(baseDate)
                    .withType(type)
                    .withValue(100.56)
                    .withDescription(String.format("Description %s %s", type, i))
                    .build()
            );
        }

        items.add(
                new ItemCashFlow.Builder()
                        .withDescription("Desempate")
                        .withDate(baseDate.plusHours(1L))
                        .withType(lastType)
                        .withValue(lastValue)
                        .build()
        );

        return items;
    }

    @Test
    void shouldCheckTotal() {
        final ItemCashFlowType lastType1 = ItemCashFlowType.DEBIT;
        final ItemCashFlowType lastType2 = ItemCashFlowType.CREDIT;
        final BigDecimal lastValue1 = new BigDecimal("123.45").multiply(BigDecimal.valueOf(lastType1.getSignal()));
        final BigDecimal lastValue2 = new BigDecimal("123.45").multiply(BigDecimal.valueOf(lastType2.getSignal()));

        final Collection<ItemCashFlow> items1 = generateItems(lastType1, lastValue1);
        final Collection<ItemCashFlow> items2 = generateItems(lastType2, lastValue2);

        final CashFlow cashFlow1 = new CashFlow.Builder().withDate(getDate()).withItems(items1).build();
        final CashFlow cashFlow2 = new CashFlow.Builder().withDate(getDate()).withItems(items2).build();

        Assertions.assertEquals(lastValue1, cashFlow1.getTotal());
        Assertions.assertEquals(lastValue2, cashFlow2.getTotal());
    }

    @Test
    void shouldMarshallUnmarshall() throws JsonProcessingException {
        final CashFlow cashFlow = new CashFlow.Builder()
                .withDate(getDate())
                .withItems(generateItems(ItemCashFlowType.CREDIT, BigDecimal.ONE))
                .build();
        final String json = writeJson(cashFlow);
        final CashFlow cashFlowUnmarshall = readJson(json, CashFlow.class);

        Assertions.assertEquals(cashFlow.getDate(), cashFlowUnmarshall.getDate());
        Assertions.assertEquals(cashFlow.getTotal(), cashFlowUnmarshall.getTotal());
    }

}
