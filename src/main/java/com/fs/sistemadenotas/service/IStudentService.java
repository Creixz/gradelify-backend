package com.fs.sistemadenotas.service;


import com.fs.sistemadenotas.dto.StudentCreationDTO;
import com.fs.sistemadenotas.model.Student;

import java.util.List;

public interface IStudentService {

    public List<Student> listarStudent();

    public Student buscarStudentPorId(Integer idStudent);

    public Student guardarStudent(Student student);

    public void eliminarStudent(Student student);

    public void cambiarEstadoAlumno(Integer idStudent);

    public Student createStudent(StudentCreationDTO studentDTO);

    public List<Student> getStudentsByLevel(Integer levelId);
}
