package com.prueba.user_service.exception;

public class UserValidationException extends RuntimeException {

    public UserValidationException(String mensaje) {
        super(mensaje);
    }
    
}
