package com.fs.sistemadenotas.repository;

import com.fs.sistemadenotas.model.Classroom;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Classroom c SET c.estado = CASE WHEN c.estado = 1 THEN 0 ELSE 1 END WHERE c.idClassroom = :classroomId")
    void cambiarEstado(Integer classroomId);

    List<Classroom> findBySectionIdSection(Integer idSection);

}
