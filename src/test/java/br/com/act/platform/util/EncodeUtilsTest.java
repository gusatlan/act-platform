package br.com.act.platform.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

class EncodeUtilsTest {

    @Test
    void shouldEncode() {
        final String text = "Just a random string with purpose to teste the encoding Base64";
        final String encoded = EncodeUtils.encodeBase64(text.getBytes(StandardCharsets.UTF_8));
        final String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);

        Assertions.assertNotNull(encoded);
        Assertions.assertNotNull(decoded);
        Assertions.assertNotEquals(text, encoded);
        Assertions.assertEquals(text, decoded);
    }

}
