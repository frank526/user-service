package com.prueba.user_service.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.common_library.dto.UsuarioDto;
import com.prueba.common_library.entity.Usuario;
import com.prueba.user_service.AuthAux.AuthRequest;
import com.prueba.user_service.exception.UserValidationException;
import com.prueba.user_service.repository.UserRepository;
import com.prueba.user_service.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  UserRepository repo;

  @Override
  public UsuarioDto register(UsuarioDto usuarioDto) {

    if (repo.existsByEmail(usuarioDto.getCorreo())) {
      throw new UserValidationException("Ya existe un usuario con ese correo electronico");
    }

    if (repo.existsByPassword(usuarioDto.getContrasena())) {
      throw new UserValidationException("Ya existe un usuario con esa contrasena");
    }

    Usuario usuario = Usuario.builder()
        .name(usuarioDto.getNombre())
        .lastName(usuarioDto.getApellido())
        .userName(usuarioDto.getUserName())
        .email(usuarioDto.getCorreo())
        .password(usuarioDto.getContrasena())
        .rol(usuarioDto.getRol()).build();

    Usuario usuarioCreated = repo.save(usuario);

    UsuarioDto usuarioDtoCreated = UsuarioDto.builder()
        .nombre(usuarioCreated.getName())
        .apellido(usuarioCreated.getLastName())
        .userName(usuarioCreated.getUserName())
        .correo(usuarioCreated.getEmail())
        .id(usuarioCreated.getId())
        .rol(usuarioCreated.getRol()).build();

    return usuarioDtoCreated;

  }

  @Override
  public boolean validate(AuthRequest request) throws Exception {
  
    Usuario usuario = repo.findByPassword(request.getPassword());

    if(Objects.isNull(usuario)){
      throw new Exception("No se encontro usuario por el password");
    }

    if(!usuario.getUserName().equals(request.getUsername())){
      throw new Exception("El username no coincide");
    }

    return true;


  }
    

    
}
