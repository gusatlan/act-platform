package br.com.act.platform.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtils {
    private DateUtils() {
    }

    private static final ZoneId DEFAULT_ZONE_ID = getDefaultZoneId();
    private static final int NANO = 999999999;

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ZoneId getDefaultZoneId() {
        return ZoneId.of("America/Sao_Paulo");
    }

    public static LocalDateTime convert(final String value) {
        return LocalDateTime.parse(value, fmt);
    }

    public static LocalDateTime convert(final Date date) {
        return date.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDateTime();
    }

    public static Date convert(final LocalDateTime date) {
        return Date.from(date.atZone(DEFAULT_ZONE_ID).toInstant());
    }

    public static LocalDateTime atStartOfDay(final LocalDateTime date) {
        return date.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static LocalDateTime atEndOfDay(final LocalDateTime date) {
        return date.withHour(23).withMinute(59).withSecond(59).withNano(NANO);
    }

    public static LocalDateTime now() {
        return LocalDateTime.now(DEFAULT_ZONE_ID);
    }
}
