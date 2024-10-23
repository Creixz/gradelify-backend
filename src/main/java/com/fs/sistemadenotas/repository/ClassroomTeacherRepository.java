package com.fs.sistemadenotas.repository;

import com.fs.sistemadenotas.model.Classroom;
import com.fs.sistemadenotas.model.ClassroomTeacher;
import com.fs.sistemadenotas.model.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomTeacherRepository extends JpaRepository<ClassroomTeacher, Integer> {

    @Query("SELECT ct.teacher FROM ClassroomTeacher ct WHERE ct.classroom.id = :classroomId")
    List<Teacher> findTeachersByClassroomId(@Param("classroomId") Integer classroomId);

    @Query("SELECT ct.classroom FROM ClassroomTeacher ct WHERE ct.teacher.id = :teacherId")
    List<Classroom> findClassroomsByTeacherId(@Param("teacherId") Integer teacherId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ClassroomTeacher ct WHERE ct.classroom.id = :classroomId AND ct.teacher.id = :teacherId")
    void deleteByClassroomIdAndTeacherId(@Param("classroomId") Integer classroomId, @Param("teacherId") Integer teacherId);
}
