package br.com.act.platform.model;

import br.com.act.platform.model.cashflow.ItemCashFlow;
import br.com.act.platform.model.enums.ItemCashFlowType;
import br.com.act.platform.util.DateUtils;
import br.com.act.platform.util.JsonBaseTest;
import br.com.act.platform.util.ValidationUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemCashFlowTest extends JsonBaseTest {

    @Test
    void shouldMarshallUnmarshall() throws JsonProcessingException {
        ItemCashFlow item = new ItemCashFlow.Builder()
                .withDate(DateUtils.now())
                .withType(ItemCashFlowType.DEBIT)
                .withValue(1684.99)
                .withDescription("Compra de insumos")
                .build();
        String json = writeJson(item);
        ItemCashFlow itemUnmarshall = readJson(json, ItemCashFlow.class);

        Assertions.assertEquals(item.getDate(), itemUnmarshall.getDate());
        Assertions.assertEquals(item.getType(), itemUnmarshall.getType());
        Assertions.assertEquals(item.getValue(), itemUnmarshall.getValue());
        Assertions.assertEquals(item.getDescription(), itemUnmarshall.getDescription());
    }

    @Test
    void shouldBeValidate() {
        ItemCashFlow itemValid = new ItemCashFlow.Builder()
                .withDate(DateUtils.now())
                .withType(ItemCashFlowType.DEBIT)
                .withValue(1684.99)
                .withDescription("Compra de insumos")
                .build();

        ItemCashFlow itemInvalid1 = new ItemCashFlow.Builder()
                .withDescription("Compra de parafusos")
                .build();


        Assertions.assertTrue(itemValid.isValid());
        Assertions.assertFalse(itemInvalid1.isValid());

        Assertions.assertTrue(ValidationUtils.validate(itemValid).isEmpty());
        Assertions.assertEquals(3, ValidationUtils.validate(itemInvalid1).size());
    }
}
