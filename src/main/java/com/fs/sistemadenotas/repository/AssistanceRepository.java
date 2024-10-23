package com.fs.sistemadenotas.repository;

import com.fs.sistemadenotas.model.Assistance;
import com.fs.sistemadenotas.model.ClassroomStudent;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssistanceRepository extends JpaRepository<Assistance, Integer> {

    List<Assistance> findByClassroomStudent_Classroom_IdClassroomAndClassroomStudent_Bimestre(Integer idClassroom, Integer bimestre);
    List<Assistance> findByClassroomStudent_Student_IdEstudianteAndClassroomStudent_Classroom_IdClassroomAndClassroomStudent_Bimestre(Integer idEstudiante, Integer idClassroom, Integer bimestre);
    List<Assistance> findByClassroomStudent_Student_IdEstudianteAndClassroomStudent_Classroom_IdClassroomAndClassroomStudent_BimestreAndClassroomStudent_Subject_IdSubject(Integer idEstudiante, Integer idClassroom, Integer bimestre, Integer idSubject);
    List<Assistance> findByClassroomStudent_Classroom_IdClassroomAndClassroomStudent_BimestreAndClassroomStudent_Subject_IdSubject(Integer idClassroom, Integer bimestre, Integer idSubject);
    List<Assistance> findByClassroomStudent_Classroom_IdClassroomAndClassroomStudent_BimestreAndClassroomStudent_Subject_IdSubjectAndSemana(Integer idClassroom, Integer bimestre, Integer idSubject, Integer semana);

    Optional<Assistance> findByClassroomStudentAndSemana(ClassroomStudent classroomStudent, Integer semana);

    void deleteByClassroomStudentAndSemana(ClassroomStudent classroomStudent, Integer semana);
}
