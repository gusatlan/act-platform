package br.com.act.platform.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class JsonUtils {

    private JsonUtils() {
    }

    private static final ObjectMapper mapper = buildMapper();

    public static ObjectMapper buildMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED);

        return mapper;
    }

    public static <T> String write(final T value, final ObjectMapper objectMapper) throws JsonProcessingException {
        return (objectMapper != null ? objectMapper : mapper).writeValueAsString(value);
    }

    public static <T> T read(final String value, final Class<T> clazz, final ObjectMapper objectMapper) throws JsonProcessingException {
        return (objectMapper != null ? objectMapper : mapper).readValue(value, clazz);
    }

}
