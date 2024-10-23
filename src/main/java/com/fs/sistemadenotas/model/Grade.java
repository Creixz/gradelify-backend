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
@Table(name = "grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grade")
    private Integer idGrade;

    @Column(name = "pa_1", nullable = false)
    private String pa_1 = "-";

    @Column(name = "pa_2", nullable = false)
    private String pa_2 = "-";

    @Column(name = "pa_3", nullable = false)
    private String pa_3 = "-";

    @Column(name = "pc", nullable = false)
    private String pc = "-";

    @Column(name = "rc", nullable = false)
    private String rc = "-";


    @ManyToOne
    @JoinColumn(name = "id_classroom_student")
    private ClassroomStudent classroomStudent;
}
