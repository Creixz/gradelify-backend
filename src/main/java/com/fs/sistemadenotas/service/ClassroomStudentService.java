package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.dto.StudentDetailDTO;
import com.fs.sistemadenotas.dto.StudentEnrollmentDTO;
import com.fs.sistemadenotas.exception.StudentAlreadyEnrolledException;
import com.fs.sistemadenotas.model.ClassroomStudent;
import com.fs.sistemadenotas.model.Grade;
import com.fs.sistemadenotas.model.Student;
import com.fs.sistemadenotas.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomStudentService implements IClassroomStudentService{

    private final ClassroomStudentRepository classroomStudentRepository;
    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;
    private final SubjectRepository subjectRepository;
    private final GradeRepository gradeRepository;

    @Override
    public List<Student> listarStudentsPorClassroomYBimestre(Integer classroomId, Integer bimestre) {

        List<Integer> studentsId = classroomStudentRepository.findStudentIdsByClassroomIdAndBimester(classroomId, bimestre);

        return studentRepository.findAllById(studentsId);
    }

    @Override
    public Page<StudentDetailDTO> getStudentDetailsByLevel(Integer idLevel, Pageable pageable) {
        return classroomStudentRepository.findStudentDetailsByLevel(idLevel, pageable);
    }

    @Override
    public Page<StudentDetailDTO> getAllStudentDetails(Pageable pageable) {
        Page<StudentDetailDTO> studentDetails = classroomStudentRepository.findAllStudentDetails(pageable);

        studentDetails.forEach(studentDetail -> {
            Integer studentId = studentDetail.getId();
            studentDetail.setLevel(classroomStudentRepository.findLevelNameByStudentIdAndBimestre(studentId, 0));
            studentDetail.setBimestre1Classroom(classroomStudentRepository.findClassroomByStudentAndBimestre(studentId, 1));
            studentDetail.setBimestre2Classroom(classroomStudentRepository.findClassroomByStudentAndBimestre(studentId, 2));
            studentDetail.setBimestre3Classroom(classroomStudentRepository.findClassroomByStudentAndBimestre(studentId, 3));
            studentDetail.setBimestre4Classroom(classroomStudentRepository.findClassroomByStudentAndBimestre(studentId, 4));
        });

        return studentDetails;
    }

    @Override
    public void addStudentsToClassroomAndEnrollInSubjects(StudentEnrollmentDTO enrollmentDTO) {
        var classroom = classroomRepository.findById(enrollmentDTO.getClassroomId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid classroom ID"));
        var subjects = subjectRepository.findAllExceptId6();

        for (Integer studentId : enrollmentDTO.getStudentIds()) {
            var student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid student ID"));

            List<ClassroomStudent> existingEnrollments = classroomStudentRepository.findByStudentIdEstudianteAndBimestre(studentId, enrollmentDTO.getBimestre());
            if (!existingEnrollments.isEmpty()) {
                throw new StudentAlreadyEnrolledException("El estudiante con ID " + studentId + " ya está inscrito en otro salón para el bimestre " + enrollmentDTO.getBimestre());
            }

            for (var subject : subjects) {
                ClassroomStudent classroomStudent = new ClassroomStudent();
                classroomStudent.setClassroom(classroom);
                classroomStudent.setStudent(student);
                classroomStudent.setBimestre(enrollmentDTO.getBimestre());
                classroomStudent.setSubject(subject);

                classroomStudentRepository.save(classroomStudent);

                // Crear y guardar el registro de calificaciones
                Grade grade = new Grade();
                grade.setClassroomStudent(classroomStudent);
                gradeRepository.save(grade);
            }
        }
    }

    @Override
    public void removeStudentFromClassroom(Integer classroomId, Integer studentId, Integer bimestre) {
        // Encuentra todos los registros de ClassroomStudent
        List<ClassroomStudent> classroomStudents = classroomStudentRepository.findByClassroomIdClassroomAndStudentIdEstudianteAndBimestre(classroomId, studentId, bimestre);

        // Para cada ClassroomStudent, encuentra y elimina las calificaciones asociadas
        for (ClassroomStudent classroomStudent : classroomStudents) {
            // Encuentra todas las calificaciones asociadas con el ClassroomStudent
            List<Grade> grades = gradeRepository.findByClassroomStudent(classroomStudent);

            // Elimina todas las calificaciones
            gradeRepository.deleteAll(grades);
        }

        // Elimina todos los registros de ClassroomStudent
        classroomStudentRepository.deleteAll(classroomStudents);
    }

    @Override
    public List<Student> listarEstudiantesNoInscritosEnNingunSalonYBimestre(Integer bimestre) {
        return studentRepository.findStudentsNotInAnyClassroomAndBimester(bimestre);
    }

    @Override
    public List<Student> listarEstudiantesNoInscritosEnNingunSalonYBimestreYNivel(Integer bimestre, Integer levelId) {
        return studentRepository.findStudentsNotInAnyClassroomAndBimesterAndLevel(bimestre, levelId);
    }


}
