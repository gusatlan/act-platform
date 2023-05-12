package br.com.act.platform.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Map;
import java.util.UUID;

public final class ReportUtils {

    private ReportUtils() {
    }

    public static InputStream getReportFile(final String filename) {
        return ReportUtils.class.getResourceAsStream(filename);
    }

    private static JasperReport retrieveReport(final InputStream is) throws JRException, IOException {
        JasperReport report = null;
        try (InputStream input = is) {
            try {
                report = (JasperReport) JRLoader.loadObject(input);
            } catch (Exception ex) {
                report = JasperCompileManager.compileReport(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return report;
    }

    private static JasperPrint fillReport(
            final JasperReport report,
            final Map<String, Object> params,
            final Connection connection
    ) throws JRException {
        JasperPrint print = null;

        try {
            if (connection != null) {
                print = JasperFillManager.fillReport(report, params, connection);
            } else {
                print = JasperFillManager.fillReport(report, params);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return print;
    }

    public static byte[] exportReport(final InputStream is, final Map<String, Object> params, final Connection connection) throws JRException, IOException {
        return JasperExportManager.exportReportToPdf(fillReport(retrieveReport(is), params, connection));
    }
}
