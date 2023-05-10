package br.com.act.platform.util;

import br.com.act.platform.model.enums.ActionType;
import br.com.act.platform.model.request.RequestAction;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

class RequestActionTest extends JsonBaseTest {

    @Test
    @SuppressWarnings("unchecked")
    void shouldMarshallUnmarshall() throws JsonProcessingException {
        String entity = DateUtils.now().format(DateTimeFormatter.ISO_DATE);
        RequestAction<String> request = new RequestAction.Builder<String>()
                .withAction(ActionType.UPSERT)
                .withEntity(entity)
                .build();
        String json = writeJson(request);
        RequestAction<String> requestUnmarshall = readJson(json, request.getClass());

        Assertions.assertNotNull(request);
        Assertions.assertNotNull(request.getAction());
        Assertions.assertNotNull(request.getEntity());
        Assertions.assertEquals(entity, request.getEntity());
        Assertions.assertEquals(ActionType.UPSERT, request.getAction());

        Assertions.assertEquals(request.getAction(), requestUnmarshall.getAction());
        Assertions.assertEquals(request.getEntity(), requestUnmarshall.getEntity());
    }
}
