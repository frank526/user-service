package com.prueba.user_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.common_library.dto.UsuarioDto;
import com.prueba.user_service.dto.UserRegisterRequest;
import com.prueba.user_service.security.filter.JwtAuthenticationFilter;
import com.prueba.user_service.service.UsuarioService;

import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/users")
public class UserController {


    @Autowired
    UsuarioService userService;


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserRegisterRequest usuarioDto) {

        Map<String, Object> response = new HashMap<>();

        UsuarioDto usuario = userService.register(usuarioDto);

        response.put("success", Boolean.TRUE);
        response.put("datos", usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    
}
