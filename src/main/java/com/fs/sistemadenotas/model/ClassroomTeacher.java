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
@Table(name = "classroom_teacher")
public class ClassroomTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classroom_teacher")
    private Integer idClassroomTeacher;

    @ManyToOne
    @JoinColumn(name = "id_classroom")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;
}
