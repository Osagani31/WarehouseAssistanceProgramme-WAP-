package com.warehouse.project.exceptions;

public class NameValueRequiredException extends RuntimeException{

    public NameValueRequiredException(String message){
        super(message);
    }
}
