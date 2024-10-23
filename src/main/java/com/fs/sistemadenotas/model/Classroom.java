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
@Table(name = "classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classroom")
    private Integer idClassroom;

    @ManyToOne
    @JoinColumn(name = "id_level")
    private Level level;

    @ManyToOne
    @JoinColumn(name = "id_section")
    private Section section;

    @Column(name = "estado", nullable = false)
    private Integer estado;
}
