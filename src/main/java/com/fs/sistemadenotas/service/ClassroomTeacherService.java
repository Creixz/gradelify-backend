package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.dto.ClassroomTeacherCreationDTO;
import com.fs.sistemadenotas.dto.TeacherResponse;
import com.fs.sistemadenotas.model.Classroom;
import com.fs.sistemadenotas.model.ClassroomTeacher;
import com.fs.sistemadenotas.model.Teacher;
import com.fs.sistemadenotas.repository.ClassroomRepository;
import com.fs.sistemadenotas.repository.ClassroomTeacherRepository;
import com.fs.sistemadenotas.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomTeacherService implements IClassroomTeacherService {

    private final ClassroomTeacherRepository classroomTeacherRepository;
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;

    @Override
    public List<TeacherResponse> findTeachersByClassroomId(Integer classroomId) {
        var teachers = classroomTeacherRepository.findTeachersByClassroomId(classroomId);

        return teachers.stream()
                .map(teacher -> new TeacherResponse(
                        teacher.getIdTeacher(),
                        teacher.getUsername(),
                        teacher.getCorreo(),
                        teacher.getNombre(),
                        teacher.getApellidos(),
                        teacher.getRol(),
                        teacher.getSubject(),
                        teacher.getEstado()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Classroom> findClassroomsByTeacherId(Integer teacherId) {
        return classroomTeacherRepository.findClassroomsByTeacherId(teacherId);
    }

    @Override
    public ClassroomTeacher saveTeacherInClassroom(Integer classroomId, Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        Classroom classroom = classroomRepository.findById(classroomId).orElse(null);

        ClassroomTeacher classroomTeacher = ClassroomTeacher.builder()
                .teacher(teacher)
                .classroom(classroom)
                .build();

        classroomTeacherRepository.save(classroomTeacher);

        return classroomTeacher;
    }

    @Override
    public void deleteTeacherFromClassroom(Integer classroomId, Integer teacherId) {
        classroomTeacherRepository.deleteByClassroomIdAndTeacherId(classroomId,teacherId);
    }

    @Override
    public void addTeachersToClassroom(ClassroomTeacherCreationDTO dto) {
        Classroom classroom = classroomRepository.findById(dto.getClassroomId())
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        for (Integer teacherId : dto.getTeacherIds()) {
            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            ClassroomTeacher classroomTeacher = new ClassroomTeacher();
            classroomTeacher.setClassroom(classroom);
            classroomTeacher.setTeacher(teacher);
            classroomTeacherRepository.save(classroomTeacher);
        }
    }
}
