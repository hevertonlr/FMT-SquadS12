package com.fmt.app.average.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmt.app.average.handlers.models.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static ResponseEntity<?> getError(HttpStatus status, Exception e){
        ErrorModel error = ErrorModel.builder()
                .status(status)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    public static String objetoParaJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static String objetoParaQueryParam(Object obj) {
        List<String> queryParam = new ArrayList<>();
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (!field.canAccess(obj)) field.setAccessible(true);

                queryParam.add(field.getName() + "=" + field.get(obj));
            }
        } catch (Exception e) {
            return "";
        }

        return "?" + String.join("&", queryParam);
    }
}
