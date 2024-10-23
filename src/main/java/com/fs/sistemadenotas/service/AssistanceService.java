package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.model.Assistance;
import com.fs.sistemadenotas.model.ClassroomStudent;
import com.fs.sistemadenotas.repository.AssistanceRepository;
import com.fs.sistemadenotas.repository.ClassroomStudentRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssistanceService implements IAssistanceService {

    private final AssistanceRepository assistanceRepository;

    private final ClassroomStudentRepository classroomStudentRepository;

    @Override
    public List<Assistance> getAssistanceByClassroomAndBimestre(Integer idClassroom, Integer bimestre) {
        return assistanceRepository.findByClassroomStudent_Classroom_IdClassroomAndClassroomStudent_Bimestre(idClassroom, bimestre);
    }

    @Override
    public List<Assistance> getAssistanceByStudentAndClassroomAndBimestre(Integer idEstudiante, Integer idClassroom, Integer bimestre) {
        return assistanceRepository.findByClassroomStudent_Student_IdEstudianteAndClassroomStudent_Classroom_IdClassroomAndClassroomStudent_Bimestre(idEstudiante, idClassroom, bimestre);
    }

    @Override
    public Assistance saveOrUpdateAssistance(Assistance assistance) {
        return assistanceRepository.save(assistance);
    }

    @Override
    public List<Assistance> getAssistanceByStudentAndClassroomAndBimestreAndSubject(Integer idEstudiante, Integer idClassroom, Integer bimestre, Integer idSubject) {
        return assistanceRepository.findByClassroomStudent_Student_IdEstudianteAndClassroomStudent_Classroom_IdClassroomAndClassroomStudent_BimestreAndClassroomStudent_Subject_IdSubject(idEstudiante, idClassroom, bimestre, idSubject);
    }

    @Override
    public List<Assistance> getAssistanceByClassroomAndBimestreAndSubject(Integer idClassroom, Integer bimestre, Integer idSubject) {
        return assistanceRepository.findByClassroomStudent_Classroom_IdClassroomAndClassroomStudent_BimestreAndClassroomStudent_Subject_IdSubject(idClassroom, bimestre, idSubject);
    }

    @Override
    public List<Assistance> getAssistanceByClassroomAndBimestreAndSubjectAndSemana(Integer idClassroom, Integer bimestre, Integer idSubject, Integer semana) {
        return assistanceRepository.findByClassroomStudent_Classroom_IdClassroomAndClassroomStudent_BimestreAndClassroomStudent_Subject_IdSubjectAndSemana(idClassroom, bimestre, idSubject, semana);
    }

    @Override
    public Assistance updateAssistance(Integer studentId, Integer classroomId, Integer subjectId, Integer bimestre, Integer week, String status) {
        Optional<ClassroomStudent> classroomStudentOpt = classroomStudentRepository.findByClassroom_IdClassroomAndStudent_IdEstudianteAndSubject_IdSubjectAndBimestre(classroomId, studentId, subjectId, bimestre);
        if (classroomStudentOpt.isPresent()) {
            ClassroomStudent classroomStudent = classroomStudentOpt.get();
            Optional<Assistance> assistanceOpt = assistanceRepository.findByClassroomStudentAndSemana(classroomStudent, week);
            Assistance assistance = null;
            if (assistanceOpt.isPresent()) {
                if ("-".equals(status)) {
                    assistanceRepository.deleteByClassroomStudentAndSemana(classroomStudent, week);
                } else {
                    assistance = assistanceOpt.get();
                    assistance.setEstadoAsistencia(status);
                    assistance = assistanceRepository.save(assistance);
                }
            } else {
                if (!"-".equals(status)) {
                    assistance = new Assistance();
                    assistance.setClassroomStudent(classroomStudent);
                    assistance.setSemana(week);
                    assistance.setEstadoAsistencia(status);
                    assistance = assistanceRepository.save(assistance);
                }
            }
            return assistance;
        } else {
            throw new IllegalArgumentException("ClassroomStudent not found");
        }
    }
}
