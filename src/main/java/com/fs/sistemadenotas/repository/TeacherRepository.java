package com.fs.sistemadenotas.repository;

import com.fs.sistemadenotas.model.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Optional<Teacher> findByUsername(String username);

    @Query("SELECT t FROM Teacher t WHERE t.subject.idSubject = :idSubject")
    List<Teacher> findBySubjectId(@Param("idSubject") Integer idSubject);

    @Modifying
    @Transactional
    @Query("UPDATE Teacher t SET t.estado = CASE WHEN t.estado = 1 THEN 0 ELSE 1 END WHERE t.idTeacher = :teacherId")
    void cambiarEstado(Integer teacherId);

    @Modifying
    @Transactional
    @Query("UPDATE Teacher t SET t.estado = CASE WHEN t.estado = 1 THEN 0 ELSE 1 END WHERE t.username = :username")
    void cambiarEstadoConUsername(@Param("username") String username);
}
