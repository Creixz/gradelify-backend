package com.fs.sistemadenotas.repository;

import com.fs.sistemadenotas.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("SELECT s FROM Subject s WHERE s.idSubject != 6")
    List<Subject> findAllExceptId6();

}
