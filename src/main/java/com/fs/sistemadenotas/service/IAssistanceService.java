package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.model.Assistance;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAssistanceService {

    public List<Assistance> getAssistanceByClassroomAndBimestre(Integer idClassroom, Integer bimestre);

    public List<Assistance> getAssistanceByStudentAndClassroomAndBimestre(Integer idEstudiante, Integer idClassroom, Integer bimestre);

    public Assistance saveOrUpdateAssistance(Assistance assistance);

    public List<Assistance> getAssistanceByStudentAndClassroomAndBimestreAndSubject(Integer idEstudiante, Integer idClassroom, Integer bimestre, Integer idSubject);

    public List<Assistance> getAssistanceByClassroomAndBimestreAndSubject(Integer idClassroom, Integer bimestre, Integer idSubject);

    public List<Assistance> getAssistanceByClassroomAndBimestreAndSubjectAndSemana(Integer idClassroom, Integer bimestre, Integer idSubject, Integer semana);

    @Transactional
    public Assistance updateAssistance(Integer studentId, Integer classroomId, Integer subjectId, Integer bimestre, Integer week, String status);
}
