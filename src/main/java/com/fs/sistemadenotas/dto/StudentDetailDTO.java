package com.fs.sistemadenotas.dto;

import com.fs.sistemadenotas.model.Classroom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailDTO {

    private Integer id;
    private String codigo;
    private String nombres;
    private String apellidos;
    private Integer estado;

    // Los campos para las clases de los bimestres se agregan después
    private String level;
    private Classroom bimestre1Classroom;
    private Classroom bimestre2Classroom;
    private Classroom bimestre3Classroom;
    private Classroom bimestre4Classroom;

    public StudentDetailDTO(Integer id, String codigo, String nombres, String apellidos, Integer estado) {
        this.id = id;
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.estado = estado;
    }
}
