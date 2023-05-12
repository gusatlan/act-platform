package br.com.act.platform.util;

import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

class ReportUtilsTest {

    @Test
    void shouldGenerateReport() throws JRException, IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("test_report.jasper")) {
            byte[] report = ReportUtils.exportReport(is, new HashMap<String, Object>(), null);

            Assertions.assertNotNull(report);
            Assertions.assertFalse(report.length == 0);
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}
