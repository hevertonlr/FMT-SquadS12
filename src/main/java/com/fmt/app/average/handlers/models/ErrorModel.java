package com.fmt.app.average.handlers.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorModel {
    private HttpStatus status;
    private String message;
}
