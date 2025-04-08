package com.prueba.user_service.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prueba.common_library.dto.UsuarioDto;
import com.prueba.common_library.entity.Usuario;
import com.prueba.user_service.dto.AuthRequest;
import com.prueba.user_service.dto.UserRegisterRequest;
import com.prueba.user_service.exception.UserValidationException;
import com.prueba.user_service.repository.UserRepository;
import com.prueba.user_service.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  UserRepository repo;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UsuarioDto register(UserRegisterRequest usuarioDto) {

    if (repo.existsByEmail(usuarioDto.getCorreo())) {
      throw new UserValidationException("Ya existe un usuario con ese correo electronico");
    }

    if (repo.existsByPassword(usuarioDto.getContrasena())) {
      throw new UserValidationException("Ya existe un usuario con esa contrasena");
    }

    if (repo.existsByUserName(usuarioDto.getUserName())) {
      throw new UserValidationException("Ya existe un usuario con ese user name");
    }

    Usuario usuario = mapToUsuario(usuarioDto);

    Usuario usuarioCreated = repo.save(usuario);

    UsuarioDto usuarioDtoCreated = mapToUsuarioDto(usuarioCreated);

    return usuarioDtoCreated;

  }

  private Usuario mapToUsuario(UserRegisterRequest usuarioDto){

    Usuario usuario = Usuario.builder()
        .name(usuarioDto.getNombre())
        .lastName(usuarioDto.getApellido())
        .userName(usuarioDto.getUserName())
        .email(usuarioDto.getCorreo())
        .password(passwordEncoder.encode(usuarioDto.getContrasena()))
        .rol(usuarioDto.getRol()).build();

        return usuario;
  }

  private UsuarioDto mapToUsuarioDto(Usuario usuarioCreated){

    UsuarioDto usuarioDtoCreated = UsuarioDto.builder()
    .nombre(usuarioCreated.getName())
    .apellido(usuarioCreated.getLastName())
    .userName(usuarioCreated.getUserName())
    .correo(usuarioCreated.getEmail())
    .contrasena(usuarioCreated.getPassword())
    .id(usuarioCreated.getId())
    .rol(usuarioCreated.getRol()).build();

    return usuarioDtoCreated;
  }


  @Override
  public void validate(AuthRequest request){

    Usuario usuario = repo.findByUserName(request.getUsername());

    if(Objects.isNull(usuario)){
      throw new UserValidationException("No se encontro el usuario");
    }

    if(!passwordEncoder.matches(request.getPassword(), usuario.getPassword())){
      throw new UserValidationException("El password no coincide");
    }


  }
    

    
}
