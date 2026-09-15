package com.github.dstewart.toastman.util.prettifier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPrettifier extends Prettifier {
    @Override
    public String prettify(String body) throws PrettifyException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object jsonObject = mapper.readValue(body, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (JsonProcessingException ex) {
            throw new PrettifyException(ex.getMessage());
        }
    }
}
