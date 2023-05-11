package br.com.act.platform.model;

import br.com.act.platform.model.cashflow.ReportCashFlow;
import br.com.act.platform.model.request.RequestCashFlowReport;
import br.com.act.platform.util.DateUtils;
import br.com.act.platform.util.EncodeUtils;
import br.com.act.platform.util.JsonBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

class ReportCashFlowTest extends JsonBaseTest {

    private byte[] readFile() throws URISyntaxException, IOException {
        return Files.readAllBytes(Path.of(getClass().getClassLoader().getResource("test.pdf").toURI()));
    }

    private RequestCashFlowReport generateRequest() {
        return new RequestCashFlowReport.Builder()
                .withRequestId("1234567890")
                .withBeginDate(DateUtils.now().toLocalDate())
                .withEndDate(DateUtils.now().toLocalDate())
                .build();
    }

    private ReportCashFlow generateReportCashFlow() throws URISyntaxException, IOException {
        return new ReportCashFlow.Builder()
                .withRequest(generateRequest())
                .withContent(readFile())
                .build();
    }

    @Test
    void shouldGenerateContent() throws URISyntaxException, IOException {
        final ReportCashFlow report = generateReportCashFlow();
        final String base64Original = EncodeUtils.encodeBase64(readFile());

        Assertions.assertNotNull(report);
        Assertions.assertNotNull(report.getRequest());
        Assertions.assertNotNull(report.getRequest().getBeginDate());
        Assertions.assertNotNull(report.getRequest().getEndDate());

        Assertions.assertEquals("1234567890", report.getRequest().getRequestId());
        Assertions.assertEquals(DateUtils.now().toLocalDate(), report.getRequest().getBeginDate());
        Assertions.assertEquals(DateUtils.now().toLocalDate(), report.getRequest().getEndDate());

        Assertions.assertNotNull(report.getContent());
        Assertions.assertNotNull(report.getLinkContent());
        Assertions.assertTrue(report.getLinkContent().contains(report.getContent()));

        Assertions.assertEquals(base64Original, report.getContent());
    }

    @Test
    void shouldMarshallUnmarshall() throws URISyntaxException, IOException {
        final ReportCashFlow report = generateReportCashFlow();
        final String json = writeJson(report);
        final ReportCashFlow reportUnmarshall = readJson(json, ReportCashFlow.class);

        Assertions.assertEquals(report.getRequest().getRequestId(), reportUnmarshall.getRequest().getRequestId());
        Assertions.assertEquals(report.getRequest().getBeginDate(), reportUnmarshall.getRequest().getBeginDate());
        Assertions.assertEquals(report.getRequest().getEndDate(), reportUnmarshall.getRequest().getEndDate());
        Assertions.assertEquals(report.getContent(), reportUnmarshall.getContent());
        Assertions.assertEquals(report.getLinkContent(), reportUnmarshall.getLinkContent());
    }

}
