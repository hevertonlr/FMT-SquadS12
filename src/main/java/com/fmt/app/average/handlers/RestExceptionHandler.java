package com.fmt.app.average.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import static com.fmt.app.average.Utils.Util.getError;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handler(Exception e){
        return getError(HttpStatus.INTERNAL_SERVER_ERROR,e);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handler(DataIntegrityViolationException e){
        return getError(HttpStatus.BAD_REQUEST,e);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handler(NotFoundException e){
        return getError(HttpStatus.NOT_FOUND,e);
    }
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<?> handler(InvalidException e){
        return getError(HttpStatus.BAD_REQUEST,e);
    }
    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<?> handler(InvalidOperationException e){
        return getError(HttpStatus.NOT_IMPLEMENTED,e);
    }
}
