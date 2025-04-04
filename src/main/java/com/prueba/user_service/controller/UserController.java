package com.prueba.user_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.common_library.dto.UsuarioDto;
import com.prueba.user_service.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UserController {


    @Autowired
    UsuarioService userService;


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody @Valid UsuarioDto usuarioDto) {

        Map<String, Object> response = new HashMap<>();

        UsuarioDto usuario = userService.register(usuarioDto);

        response.put("success", Boolean.TRUE);
        response.put("datos", usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    
}
