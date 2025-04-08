package com.prueba.user_service.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
public class UserRegisterRequest {

   @NotBlank(message = "El nombre es obligatorio")
   private String nombre;
   @NotBlank(message = "El apellido es obligatorio")
   private String apellido;
   @Email(message = "El correo no tiene un formato válido")
   @NotBlank(message = "El correo es obligatorio")
   private String correo;
   @NotBlank(message = "La contrasena es obligatoria")
   private String contrasena;
   @NotBlank(message = "El rol es obligatorio")
   private String rol;
   @NotBlank(message = "El user name es obligatorio")
   private String userName;
    
}
