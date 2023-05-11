package br.com.act.platform.util;

import java.util.Base64;

public final class EncodeUtils {

    private EncodeUtils() {
    }

    public static String encodeBase64(byte[] content) {
        return Base64.getEncoder().encodeToString(content);
    }
}
