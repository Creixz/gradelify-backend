package com.fs.sistemadenotas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "assistance")
public class Assistance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assistance")
    private Integer idAssistance;

    @Column
    private Integer semana;

    @Column
    private String estadoAsistencia;

    @ManyToOne
    @JoinColumn(name = "id_classroom_student")
    private ClassroomStudent classroomStudent;
}
