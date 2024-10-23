package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.dto.StudentCreationDTO;
import com.fs.sistemadenotas.model.Classroom;
import com.fs.sistemadenotas.model.ClassroomStudent;
import com.fs.sistemadenotas.model.Student;
import com.fs.sistemadenotas.model.Subject;
import com.fs.sistemadenotas.repository.ClassroomRepository;
import com.fs.sistemadenotas.repository.ClassroomStudentRepository;
import com.fs.sistemadenotas.repository.StudentRepository;
import com.fs.sistemadenotas.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;

    private final ClassroomRepository classroomRepository;

    private final ClassroomStudentRepository classroomStudentRepository;

    private final SubjectRepository subjectRepository;

    @Override
    public List<Student> listarStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student buscarStudentPorId(Integer idStudent) {
        return studentRepository.findById(idStudent).orElse(null);
    }

    @Override
    public Student guardarStudent(Student student) {

        return studentRepository.save(student);
    }

    @Override
    public void eliminarStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public void cambiarEstadoAlumno(Integer idStudent) {
        studentRepository.cambiarEstado(idStudent);
    }

    @Override
    public Student createStudent(StudentCreationDTO studentDTO) {
        Student student = new Student();
        student.setCodigoEstudiante(studentDTO.getCodigoEstudiante());
        student.setNombres(studentDTO.getNombres());
        student.setApellidos(studentDTO.getApellidos());
        student.setEstado(1);
        Student savedStudent = studentRepository.save(student);

        Classroom classroom = classroomRepository.findById(studentDTO.getIdClassroom()).orElse(null);

        Subject subject = subjectRepository.findById(6).orElse(null);

        ClassroomStudent classroomStudent = new ClassroomStudent();
        classroomStudent.setStudent(savedStudent);
        classroomStudent.setClassroom(classroom);
        classroomStudent.setBimestre(0);
        classroomStudent.setSubject(subject);
        classroomStudentRepository.save(classroomStudent);

        return savedStudent;
    }

    @Override
    public List<Student> getStudentsByLevel(Integer levelId) {
        return studentRepository.findByLevelId(levelId);
    }
}
