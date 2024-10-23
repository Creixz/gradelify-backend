package com.fs.sistemadenotas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreationDTO {

    private String codigoEstudiante;
    private String nombres;
    private String apellidos;
    private Integer idClassroom;
}
