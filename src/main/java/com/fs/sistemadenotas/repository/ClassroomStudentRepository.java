package com.fs.sistemadenotas.repository;

import com.fs.sistemadenotas.dto.StudentDetailDTO;
import com.fs.sistemadenotas.model.Classroom;
import com.fs.sistemadenotas.model.ClassroomStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassroomStudentRepository extends JpaRepository<ClassroomStudent, Integer> {

    @Query("SELECT cs.student.id FROM ClassroomStudent cs WHERE cs.classroom.id = :classroomId AND cs.bimestre = :bimestre")
    List<Integer> findStudentIdsByClassroomIdAndBimester(Integer classroomId, Integer bimestre);

    List<ClassroomStudent> findByClassroomIdClassroomAndStudentIdEstudianteAndBimestre(Integer classroomId, Integer studentId, Integer bimestre);
    List<ClassroomStudent> findByStudentIdEstudianteAndBimestre(Integer studentId, Integer bimestre);
    
    @Query("SELECT new com.fs.sistemadenotas.dto.StudentDetailDTO(s.id, s.codigoEstudiante, s.nombres, s.apellidos, " +
            "COALESCE(MAX(CASE WHEN cs.bimestre = 1 THEN CONCAT(c.level.levelName, ' ', se.sectionName) END), 'N/A'), " +
            "COALESCE(MAX(CASE WHEN cs.bimestre = 2 THEN CONCAT(c.level.levelName, ' ', se.sectionName) END), 'N/A'), " +
            "COALESCE(MAX(CASE WHEN cs.bimestre = 3 THEN CONCAT(c.level.levelName, ' ', se.sectionName) END), 'N/A'), " +
            "COALESCE(MAX(CASE WHEN cs.bimestre = 4 THEN CONCAT(c.level.levelName, ' ', se.sectionName) END), 'N/A')) " +
            "FROM ClassroomStudent cs " +
            "JOIN cs.student s " +
            "JOIN cs.classroom c " +
            "JOIN c.level l " +
            "JOIN c.section se " +
            "WHERE c.level.idLevel = :idLevel " +
            "GROUP BY s.id, s.codigoEstudiante, s.nombres, s.apellidos " +
            "ORDER BY s.apellidos ASC")
    Page<StudentDetailDTO> findStudentDetailsByLevel(@Param("idLevel") Integer idLevel, Pageable pageable);

    @Query("SELECT new com.fs.sistemadenotas.dto.StudentDetailDTO(s.idEstudiante as id, s.codigoEstudiante as codigo, s.nombres as nombres, s.apellidos as apellidos, s.estado as estado) FROM Student s")
    Page<StudentDetailDTO> findAllStudentDetails(Pageable pageable);

    @Query("SELECT cs.classroom.level.levelName " +
            "FROM ClassroomStudent cs " +
            "WHERE cs.student.idEstudiante = :studentId " +
            "AND cs.bimestre = :bimestre")
    String findLevelNameByStudentIdAndBimestre(@Param("studentId") Integer studentId, @Param("bimestre") Integer bimestre);

    @Query("SELECT cs.classroom FROM ClassroomStudent cs WHERE cs.student.idEstudiante = :studentId AND cs.bimestre = :bimestre")
    Classroom findClassroomByStudentAndBimestre(@Param("studentId") Integer studentId, @Param("bimestre") Integer bimestre);

    Optional<ClassroomStudent> findByClassroom_IdClassroomAndStudent_IdEstudianteAndSubject_IdSubjectAndBimestre(Integer classroomId, Integer studentId, Integer subjectId, Integer bimestre);
}

