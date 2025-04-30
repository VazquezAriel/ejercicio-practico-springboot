package com.minegocio.alquimiasoft.exception;

public class CustomerValidationException extends RuntimeException {
    
    public CustomerValidationException(String message) {
        super(message);
    }

    public CustomerValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
