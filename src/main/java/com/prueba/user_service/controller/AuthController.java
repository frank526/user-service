package com.prueba.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.user_service.AuthAux.AuthRequest;
import com.prueba.user_service.AuthAux.AuthResponse;
import com.prueba.user_service.security.JwtService;
import com.prueba.user_service.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    @Autowired
    UsuarioService userService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        // Aquí iría la lógica de autenticación (validar usuario y contraseña)
System.out.println("init");

Boolean resp= false;
        try {

           resp= userService.validate(request);
        } catch (Exception e) {
            System.out.println("error "+e);
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("continue");

        if(!resp){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }

        

        



        //if ("user".equals(request.getUsername()) && "password".equals(request.getPassword())) {

        String token = null;
        try{
             token = jwtService.generateToken(request.getUsername());
        }catch(Exception e){

        }
            

            return ResponseEntity.ok(new AuthResponse(token));
       // }
       

    }
    
}
