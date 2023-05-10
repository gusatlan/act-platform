package br.com.act.platform.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonBaseTest {

    protected ObjectMapper mapper = JsonUtils.buildMapper();

    final <T> String writeJson(final T value) throws JsonProcessingException {
        return JsonUtils.write(value, mapper);
    }

    final <T> T readJson(final String value, final Class<T> clazz) throws JsonProcessingException {
        return JsonUtils.read(value, clazz, mapper);
    }
}
