package br.com.act.platform.model;

import br.com.act.platform.model.request.RequestCashFlowReport;
import br.com.act.platform.util.JsonBaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class RequestCashFlowReportTest extends JsonBaseTest {

    private RequestCashFlowReport generateRequestCashFlowReport(final LocalDate beginDate, final LocalDate endDate, final String requestId) {
        RequestCashFlowReport.Builder builder = new RequestCashFlowReport.Builder();
        
        if(beginDate != null) {
            builder.withBeginDate(beginDate);
        }

        if(endDate != null) {
            builder.withBeginDate(endDate);
        }

        if(requestId != null) {
            builder.withRequestId(requestId);
        }

        return builder.build();
    }

    @Test
    void shouldBuild() {
        LocalDate beginDate = LocalDate.of(2023, 5, 11);
        LocalDate endDate = LocalDate.of(2023, 5, 11);
        String requestId = "202305110721";

        RequestCashFlowReport request1 = generateRequestCashFlowReport(beginDate, endDate, requestId);
        RequestCashFlowReport request2 = generateRequestCashFlowReport(null, null, null);
        RequestCashFlowReport request3 = generateRequestCashFlowReport(beginDate, null, requestId);

        Assertions.assertEquals(requestId, request1.getRequestId());
        Assertions.assertEquals(beginDate, request1.getBeginDate());
        Assertions.assertEquals(endDate, request1.getBeginDate());

        Assertions.assertNotEquals(requestId, request2.getRequestId());
        Assertions.assertEquals(request2.getBeginDate(), request2.getEndDate());
        Assertions.assertEquals(LocalDate.now(), request2.getBeginDate());
        Assertions.assertEquals(LocalDate.now(), request2.getEndDate());

        Assertions.assertEquals(requestId, request1.getRequestId());
        Assertions.assertEquals(beginDate, request3.getBeginDate());
        Assertions.assertEquals(LocalDate.now(), request3.getEndDate());
    }
    
    @Test
    void shouldMarshallUnmarshall() throws JsonProcessingException {
        RequestCashFlowReport request = generateRequestCashFlowReport(null, null, null);
        String json = writeJson(request) ;
        RequestCashFlowReport requestUnmarshall = readJson(json, RequestCashFlowReport.class);
        
        Assertions.assertEquals(request.getRequestId(), requestUnmarshall.getRequestId());
        Assertions.assertEquals(request.getBeginDate(), requestUnmarshall.getBeginDate());
        Assertions.assertEquals(request.getEndDate(), requestUnmarshall.getEndDate());
    }
}
