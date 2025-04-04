package com.prueba.user_service.exception;

public class UsuarioExistenteException extends RuntimeException {

    public UsuarioExistenteException(String mensaje) {
        super(mensaje);
    }
    
}
