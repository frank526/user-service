package com.prueba.user_service.service;

import com.prueba.common_library.dto.UsuarioDto;
import com.prueba.user_service.AuthAux.AuthRequest;


public interface UsuarioService {

    UsuarioDto register(UsuarioDto usuario);

    boolean validate(AuthRequest request) throws Exception;




    
}
