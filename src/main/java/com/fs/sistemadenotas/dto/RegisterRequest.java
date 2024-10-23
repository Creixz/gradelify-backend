package com.fs.sistemadenotas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String password;
    private String nombre;
    private String apellidos;
    private String correo;
    private Integer idRol;
    private Integer idSubject;

}
