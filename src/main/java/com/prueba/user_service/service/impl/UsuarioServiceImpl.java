package com.prueba.user_service.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba.common_library.dto.UsuarioDto;
import com.prueba.common_library.dto.UsuarioDto.UsuarioDtoBuilder;
import com.prueba.common_library.entity.Usuario;
import com.prueba.user_service.exception.UsuarioExistenteException;
import com.prueba.user_service.repository.UserRepository;
import com.prueba.user_service.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  UserRepository repo;

  @Override
  public UsuarioDto register(UsuarioDto usuarioDto) {

    if (repo.existsByEmail(usuarioDto.getCorreo())) {
      throw new UsuarioExistenteException("Ya existe un usuario con ese correo electronico");
    }

    if (repo.existsByPassword(usuarioDto.getContrasena())) {
      throw new UsuarioExistenteException("Ya existe un usuario con esa contrasena");
    }

    Usuario usuario = Usuario.builder()
        .name(usuarioDto.getNombre())
        .lastName(usuarioDto.getApellido())
        .email(usuarioDto.getCorreo())
        .password(usuarioDto.getContrasena())
        .rol(usuarioDto.getRol()).build();

    Usuario usuarioCreated = repo.save(usuario);

    UsuarioDto usuarioDtoCreated = UsuarioDto.builder()
        .nombre(usuarioCreated.getName())
        .apellido(usuarioCreated.getLastName())
        .correo(usuarioCreated.getEmail())
        .id(usuarioCreated.getId())
        .rol(usuarioCreated.getRol()).build();

    return usuarioDtoCreated;

  }
    

    
}
