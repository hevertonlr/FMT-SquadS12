package com.fmt.app.average.handlers;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
