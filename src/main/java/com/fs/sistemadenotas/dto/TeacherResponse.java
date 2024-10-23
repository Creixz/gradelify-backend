package com.fs.sistemadenotas.dto;

import com.fs.sistemadenotas.model.Rol;
import com.fs.sistemadenotas.model.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponse {

    private Integer idTeacher;
    private String username;
    private String correo;
    private String nombre;
    private String apellidos;
    private Rol rol;
    private Subject subject;
    private Integer estado;
}
