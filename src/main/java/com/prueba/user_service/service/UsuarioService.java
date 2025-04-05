package com.prueba.user_service.service;

import com.prueba.common_library.dto.UsuarioDto;
import com.prueba.user_service.dto.AuthRequest;
import com.prueba.user_service.dto.UserRegisterRequest;


public interface UsuarioService {

    UsuarioDto register(UserRegisterRequest usuario);

    void validate(AuthRequest request);


    
}
