package br.com.act.platform.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IdUtilsTest {

    @Test
    void shouldGenerateId() {
        final String id = IdUtils.createUUID();

        Assertions.assertNotNull(id);
        Assertions.assertFalse(id.isBlank());
    }
}
