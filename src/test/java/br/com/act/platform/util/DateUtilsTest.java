package br.com.act.platform.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

public class DateUtilsTest {

    private LocalDateTime generateLocalDateTime() {
        return LocalDateTime.of(2023, 5, 10, 8, 17, 0);
    }

    private Date generateDate() {
        return Date.from(generateLocalDateTime().atZone(DateUtils.getDefaultZoneId()).toInstant());
    }

    @Test
    void shouldConvertDateLocalDateTime() {
        Date date = generateDate();
        LocalDateTime localDateTime = generateLocalDateTime();

        Assertions.assertEquals(date, DateUtils.convert(localDateTime));
        Assertions.assertEquals(localDateTime, DateUtils.convert(date));
    }

    @Test
    void shouldStartOfDay() {
        LocalDateTime startDay = DateUtils.atStartOfDay(generateLocalDateTime());

        Assertions.assertEquals(0, startDay.getHour());
        Assertions.assertEquals(0, startDay.getMinute());
        Assertions.assertEquals(0, startDay.getSecond());
        Assertions.assertEquals(0, startDay.getNano());

        Assertions.assertNotEquals(23, startDay.getHour());
        Assertions.assertNotEquals(59, startDay.getMinute());
        Assertions.assertNotEquals(59, startDay.getSecond());
        Assertions.assertNotEquals(999999999, startDay.getNano());
    }

    @Test
    void shouldEndOfDay() {
        LocalDateTime endDay = DateUtils.atEndOfDay(generateLocalDateTime());

        Assertions.assertEquals(23, endDay.getHour());
        Assertions.assertEquals(59, endDay.getMinute());
        Assertions.assertEquals(59, endDay.getSecond());
        Assertions.assertEquals(999999999, endDay.getNano());

        Assertions.assertNotEquals(0, endDay.getHour());
        Assertions.assertNotEquals(0, endDay.getMinute());
        Assertions.assertNotEquals(0, endDay.getSecond());
        Assertions.assertNotEquals(0, endDay.getNano());
    }

    @Test
    void shouldNow() {
        LocalDateTime expected = LocalDateTime.now(DateUtils.getDefaultZoneId()).withNano(0).withSecond(0);
        LocalDateTime now = DateUtils.now().withNano(0).withSecond(0);

        Assertions.assertEquals(expected, now);
        Assertions.assertNotEquals(expected, DateUtils.now());
    }

}
