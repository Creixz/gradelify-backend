package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.dto.StudentDetailDTO;
import com.fs.sistemadenotas.dto.StudentEnrollmentDTO;
import com.fs.sistemadenotas.model.ClassroomStudent;
import com.fs.sistemadenotas.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClassroomStudentService {

    public List<Student> listarStudentsPorClassroomYBimestre(Integer classroomId, Integer bimestre);

    public Page<StudentDetailDTO> getStudentDetailsByLevel(Integer idLevel, Pageable pageable);

    public Page<StudentDetailDTO> getAllStudentDetails(Pageable pageable);

    @Transactional
    public void addStudentsToClassroomAndEnrollInSubjects(StudentEnrollmentDTO enrollmentDTO);

    @Transactional
    public void removeStudentFromClassroom(Integer classroomId, Integer studentId, Integer bimestre);

    public List<Student> listarEstudiantesNoInscritosEnNingunSalonYBimestre(Integer bimestre);

    public List<Student> listarEstudiantesNoInscritosEnNingunSalonYBimestreYNivel(Integer bimestre, Integer levelId);

}
