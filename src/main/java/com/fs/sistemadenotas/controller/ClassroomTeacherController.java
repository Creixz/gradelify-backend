package com.fs.sistemadenotas.controller;

import com.fs.sistemadenotas.dto.ClassroomTeacherCreationDTO;
import com.fs.sistemadenotas.dto.ClassroomTeacherRequest;
import com.fs.sistemadenotas.dto.TeacherResponse;
import com.fs.sistemadenotas.model.Classroom;
import com.fs.sistemadenotas.model.ClassroomTeacher;
import com.fs.sistemadenotas.service.IClassroomTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gradelify/v1/")
@CrossOrigin(value = "https://gradelify-frontend-react-app.vercel.app/")
@RequiredArgsConstructor
public class ClassroomTeacherController {

    private final IClassroomTeacherService classroomTeacherService;

    @GetMapping("/classroom-teachers/{classroomId}")
    public ResponseEntity<List<TeacherResponse>> findTeachersByClassroomId(@PathVariable Integer classroomId) {
        List<TeacherResponse> teachers = classroomTeacherService.findTeachersByClassroomId(classroomId);
        if (teachers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/teacher-classrooms/{teacherId}")
    public ResponseEntity<List<Classroom>> getClassroomsByTeacherId(@PathVariable Integer teacherId) {
        List<Classroom> classrooms = classroomTeacherService.findClassroomsByTeacherId(teacherId);
        if (classrooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(classrooms);
    }

    /*@PostMapping("/classroom-teacher")
    public ResponseEntity<ClassroomTeacher> saveTeacherInClassroom(@RequestBody ClassroomTeacherRequest request) {
        Integer classroomId = request.getClassroomId();
        Integer teacherId = request.getTeacherId();

        ClassroomTeacher savedTeacher = classroomTeacherService.saveTeacherInClassroom(classroomId, teacherId);
        if (savedTeacher != null) {
            return ResponseEntity.ok(savedTeacher);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Puedes cambiar el HttpStatus según corresponda
        }
    }*/

    @PostMapping("/classroom-teachers")
    public ResponseEntity<Void> addTeachersToClassroom(@RequestBody ClassroomTeacherCreationDTO dto) {
        classroomTeacherService.addTeachersToClassroom(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove-teacher")
    public ResponseEntity<String> removeTeacherFromClassroom(@RequestParam Integer classroomId, @RequestParam Integer teacherId) {
        classroomTeacherService.deleteTeacherFromClassroom(classroomId, teacherId);
        return new ResponseEntity<>("Teacher removed from classroom successfully", HttpStatus.OK);
    }
}
