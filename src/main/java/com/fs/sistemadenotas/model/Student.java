package com.fs.sistemadenotas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private Integer idEstudiante;

    @Column(length = 10, nullable = false, unique = true)
    private String codigoEstudiante;

    @Column(length = 50, nullable = false)
    private String nombres;

    @Column(length = 50, nullable = false)
    private String apellidos;

    @Column(name = "estado", nullable = false)
    private Integer estado;
}
