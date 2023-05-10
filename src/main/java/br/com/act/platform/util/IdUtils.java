package br.com.act.platform.util;

import java.util.UUID;

public final class IdUtils {

    public static String createUUID() {
        return UUID.randomUUID().toString().trim().toLowerCase();
    }
}
