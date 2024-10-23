package com.fs.sistemadenotas.repository;

import com.fs.sistemadenotas.dto.StudentGradeBySubjectDTO;
import com.fs.sistemadenotas.dto.StudentGradeDTO;
import com.fs.sistemadenotas.model.ClassroomStudent;
import com.fs.sistemadenotas.model.Grade;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    @Query("SELECT new com.fs.sistemadenotas.dto.StudentGradeDTO(" +
            "sub.subjectName, g.pa_1, g.pa_2, g.pa_3, g.pc, g.rc) " +
            "FROM ClassroomStudent cs " +
            "LEFT JOIN cs.classroom c " +
            "LEFT JOIN cs.subject sub " +
            "LEFT JOIN Grade g ON g.classroomStudent.idClassroomStudent = cs.idClassroomStudent " +
            "WHERE cs.student.id = :idEstudiante " +
            "AND cs.classroom.id = :idSalon " +
            "AND cs.bimestre = :bimestre")
    List<StudentGradeDTO> findStudentGradesByClassroomAndBimestre(
            @Param("idEstudiante") Integer idEstudiante,
            @Param("idSalon") Integer idSalon,
            @Param("bimestre") Integer bimestre);

    List<Grade> findByClassroomStudent(ClassroomStudent classroomStudent);

    @Query("SELECT new com.fs.sistemadenotas.dto.StudentGradeBySubjectDTO(s.idEstudiante, s.codigoEstudiante, s.apellidos, s.nombres, " +
            "COALESCE(g.pa_1, ''), COALESCE(g.pa_2, ''), COALESCE(g.pa_3, ''), COALESCE(g.pc, ''), COALESCE(g.rc, '')) " +
            "FROM Student s " +
            "JOIN ClassroomStudent cs ON s.idEstudiante = cs.student.idEstudiante " +
            "LEFT JOIN Grade g ON cs.idClassroomStudent = g.classroomStudent.idClassroomStudent " +
            "WHERE cs.classroom.idClassroom = :idClassroom " +
            "AND cs.bimestre = :bimestre " +
            "AND cs.subject.idSubject = :idCurso " +
            "AND s.estado = 1")
    List<StudentGradeBySubjectDTO> findStudentGradesByClassroomBimestreAndSubject(Integer idClassroom, Integer bimestre, Integer idCurso);

    @Modifying
    @Transactional
    @Query("UPDATE Grade g SET g.pa_1 = :valor WHERE g.classroomStudent.classroom.idClassroom = :idClassroom AND g.classroomStudent.bimestre = :bimestre AND g.classroomStudent.subject.idSubject = :idCurso AND g.classroomStudent.student.idEstudiante = :idEstudiante")
    void actualizarPa1(@Param("idClassroom") Integer idClassroom, @Param("bimestre") Integer bimestre, @Param("idCurso") Integer idCurso, @Param("idEstudiante") Integer idEstudiante, @Param("valor") String valor);

    @Modifying
    @Transactional
    @Query("UPDATE Grade g SET g.pa_2 = :valor WHERE g.classroomStudent.classroom.idClassroom = :idClassroom AND g.classroomStudent.bimestre = :bimestre AND g.classroomStudent.subject.idSubject = :idCurso AND g.classroomStudent.student.idEstudiante = :idEstudiante")
    void actualizarPa2(@Param("idClassroom") Integer idClassroom, @Param("bimestre") Integer bimestre, @Param("idCurso") Integer idCurso, @Param("idEstudiante") Integer idEstudiante, @Param("valor") String valor);

    @Modifying
    @Transactional
    @Query("UPDATE Grade g SET g.pa_3 = :valor WHERE g.classroomStudent.classroom.idClassroom = :idClassroom AND g.classroomStudent.bimestre = :bimestre AND g.classroomStudent.subject.idSubject = :idCurso AND g.classroomStudent.student.idEstudiante = :idEstudiante")
    void actualizarPa3(@Param("idClassroom") Integer idClassroom, @Param("bimestre") Integer bimestre, @Param("idCurso") Integer idCurso, @Param("idEstudiante") Integer idEstudiante, @Param("valor") String valor);

    @Modifying
    @Transactional
    @Query("UPDATE Grade g SET g.pc = :valor WHERE g.classroomStudent.classroom.idClassroom = :idClassroom AND g.classroomStudent.bimestre = :bimestre AND g.classroomStudent.subject.idSubject = :idCurso AND g.classroomStudent.student.idEstudiante = :idEstudiante")
    void actualizarPc(@Param("idClassroom") Integer idClassroom, @Param("bimestre") Integer bimestre, @Param("idCurso") Integer idCurso, @Param("idEstudiante") Integer idEstudiante, @Param("valor") String valor);

    @Modifying
    @Transactional
    @Query("UPDATE Grade g SET g.rc = :valor WHERE g.classroomStudent.classroom.idClassroom = :idClassroom AND g.classroomStudent.bimestre = :bimestre AND g.classroomStudent.subject.idSubject = :idCurso AND g.classroomStudent.student.idEstudiante = :idEstudiante")
    void actualizarRc(@Param("idClassroom") Integer idClassroom, @Param("bimestre") Integer bimestre, @Param("idCurso") Integer idCurso, @Param("idEstudiante") Integer idEstudiante, @Param("valor") String valor);
}
