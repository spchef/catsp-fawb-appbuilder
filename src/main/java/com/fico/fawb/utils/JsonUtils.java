package com.fico.fawb.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static  <T> T toObject(byte[] bytes, Class<T> classType) {
        try {
            return MAPPER.readValue(bytes, classType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
