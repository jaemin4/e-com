package com.ecom.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    public static String toJson(Object param) {
        try {
            return objectMapper.writeValueAsString(param);
        } catch (JsonProcessingException e) {
            //throw new RuntimeException("JSON 변환 오류", e);
            log.warn("JSON 변환 오류 ", e);
            return "Json 변환 오류";
        }
    }
}
