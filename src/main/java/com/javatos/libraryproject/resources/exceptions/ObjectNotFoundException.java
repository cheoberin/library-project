package com.javatos.libraryproject.resources.exceptions;

public class ObjectNotFoundException extends RuntimeException {


    public ObjectNotFoundException(String message, Throwable cause) {
        super(message);
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
