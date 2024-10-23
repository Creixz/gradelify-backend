package com.fs.sistemadenotas.repository;

import com.fs.sistemadenotas.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.estado = CASE WHEN s.estado = 1 THEN 0 ELSE 1 END WHERE s.idEstudiante = :studentId")
    void cambiarEstado(Integer studentId);

    @Query("SELECT s FROM Student s JOIN ClassroomStudent cs ON s.id = cs.student.id JOIN Classroom c ON cs.classroom.id = c.id WHERE c.level.id = :levelId")
    List<Student> findByLevelId(@Param("levelId") Integer levelId);

    @Query("SELECT s FROM Student s WHERE s.idEstudiante NOT IN " +
            "(SELECT cs.student.idEstudiante FROM ClassroomStudent cs WHERE cs.bimestre = :bimestre)")
    List<Student> findStudentsNotInAnyClassroomAndBimester(@Param("bimestre") Integer bimestre);

    @Query("SELECT s FROM Student s " +
            "WHERE s.idEstudiante NOT IN " +
            "(SELECT cs.student.idEstudiante FROM ClassroomStudent cs WHERE cs.bimestre = :bimestre) " +
            "AND s.idEstudiante IN " +
            "(SELECT s2.idEstudiante FROM Student s2 JOIN ClassroomStudent cs2 ON s2.idEstudiante = cs2.student.idEstudiante JOIN Classroom c ON cs2.classroom.idClassroom = c.idClassroom WHERE c.level.idLevel = :levelId)")
    List<Student> findStudentsNotInAnyClassroomAndBimesterAndLevel(@Param("bimestre") Integer bimestre, @Param("levelId") Integer levelId);



}
