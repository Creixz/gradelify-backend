package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.dto.StudentGradeBySubjectDTO;
import com.fs.sistemadenotas.dto.StudentGradeDTO;
import com.fs.sistemadenotas.repository.GradeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService implements IGradeService{

    private final GradeRepository gradeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<StudentGradeDTO> getStudentGrades(Integer idEstudiante, Integer idSalon, Integer bimestre) {
        return gradeRepository.findStudentGradesByClassroomAndBimestre(idEstudiante, idSalon, bimestre);
    }

    @Override
    public List<StudentGradeBySubjectDTO> getStudentGradesByClassroomBimestreAndSubject(Integer idClassroom, Integer bimestre, Integer idCurso) {
        return gradeRepository.findStudentGradesByClassroomBimestreAndSubject(idClassroom, bimestre, idCurso);
    }

    @Override
    public void actualizarNota(Integer idClassroom, Integer bimestre, Integer idCurso, Integer idEstudiante, String campo, String valor) {
        switch (campo) {
            case "pa1":
                gradeRepository.actualizarPa1(idClassroom, bimestre, idCurso, idEstudiante, valor);
                break;
            case "pa2":
                gradeRepository.actualizarPa2(idClassroom, bimestre, idCurso, idEstudiante, valor);
                break;
            case "pa3":
                gradeRepository.actualizarPa3(idClassroom, bimestre, idCurso, idEstudiante, valor);
                break;
            case "pc":
                gradeRepository.actualizarPc(idClassroom, bimestre, idCurso, idEstudiante, valor);
                break;
            case "rc":
                gradeRepository.actualizarRc(idClassroom, bimestre, idCurso, idEstudiante, valor);
                break;
            default:
                throw new IllegalArgumentException("Campo de nota no válido: " + campo);
        }
    }


}
