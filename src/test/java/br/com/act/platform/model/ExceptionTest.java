package br.com.act.platform.model;

import br.com.act.platform.model.cashflow.ItemCashFlow;
import br.com.act.platform.model.enums.ItemCashFlowType;
import br.com.act.platform.model.exceptions.ApplicationException;
import br.com.act.platform.model.exceptions.EntityFoundException;
import br.com.act.platform.model.exceptions.EntityInvalidException;
import br.com.act.platform.model.exceptions.EntityNotFoundException;
import br.com.act.platform.util.ValidationUtils;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class ExceptionTest {

    private ItemCashFlow generateItemCashFlow() {
        return new ItemCashFlow.Builder()
                .withDescription("Compra insumos")
                .withType(ItemCashFlowType.DEBIT)
                .build();
    }

    private Set<ConstraintViolation<ItemCashFlow>> generateViolations() {
        return ValidationUtils.validate(generateItemCashFlow());
    }

    @Test
    void shouldApplicationException() {
        String message = "Minha exception";
        ApplicationException ex = new ApplicationException();
        ApplicationException ex2 = new ApplicationException(ex.getMessage());
        ApplicationException ex3 = new ApplicationException(message);

        Assertions.assertEquals(ex.getMessage(), ex2.getMessage());
        Assertions.assertEquals(message, ex3.getMessage());
        Assertions.assertNotEquals(ex2.getMessage(), ex3.getMessage());
    }

    @Test
    void shouldEntityFoundException() {
        String message = "Entidade encontrada";
        EntityFoundException ex = new EntityFoundException();
        EntityFoundException ex2 = new EntityFoundException(ex.getMessage());
        EntityFoundException ex3 = new EntityFoundException(message);

        Assertions.assertEquals(ex.getMessage(), ex2.getMessage());
        Assertions.assertEquals(message, ex3.getMessage());
        Assertions.assertNotEquals(ex2.getMessage(), ex3.getMessage());
    }

    @Test
    void shouldEntityNotFoundException() {
        String message = "Entidade encontrada";
        EntityNotFoundException ex = new EntityNotFoundException();
        EntityNotFoundException ex2 = new EntityNotFoundException(ex.getMessage());
        EntityNotFoundException ex3 = new EntityNotFoundException(message);

        Assertions.assertEquals(ex.getMessage(), ex2.getMessage());
        Assertions.assertEquals(message, ex3.getMessage());
        Assertions.assertNotEquals(ex2.getMessage(), ex3.getMessage());
    }

    @Test
    void shouldEntityInvalidException() {
        String message = "Entidade inválida";
        Set<ConstraintViolation<ItemCashFlow>> violations = generateViolations();
        EntityInvalidException ex = new EntityInvalidException();
        EntityInvalidException ex2 = new EntityInvalidException(ex.getMessage());
        EntityInvalidException ex3 = new EntityInvalidException(message);
        EntityInvalidException ex4 = new EntityInvalidException(violations);

        Assertions.assertEquals(ex.getMessage(), ex2.getMessage());
        Assertions.assertEquals(message, ex3.getMessage());
        Assertions.assertNotEquals(ex2.getMessage(), ex3.getMessage());

        Assertions.assertNotEquals(ex.getMessage(), ex4.getMessage());
        Assertions.assertNotEquals(ex2.getMessage(), ex4.getMessage());
        Assertions.assertNotEquals(ex3.getMessage(), ex4.getMessage());

        violations
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(it -> Assertions.assertTrue(ex4.getMessage().contains(it)));
    }
}
