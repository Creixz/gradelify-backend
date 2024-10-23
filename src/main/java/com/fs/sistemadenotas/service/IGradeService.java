package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.dto.StudentGradeBySubjectDTO;
import com.fs.sistemadenotas.dto.StudentGradeDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface IGradeService {

    public List<StudentGradeDTO> getStudentGrades(Integer idEstudiante, Integer idSalon, Integer bimestre);

    public List<StudentGradeBySubjectDTO> getStudentGradesByClassroomBimestreAndSubject(Integer idClassroom, Integer bimestre, Integer idCurso);

    @Transactional
    public void actualizarNota(Integer idClassroom, Integer bimestre, Integer idCurso, Integer idEstudiante, String campo, String valor);

}
