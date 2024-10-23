package com.fs.sistemadenotas.controller;

import com.fs.sistemadenotas.dto.ClasroomBimestreRequest;
import com.fs.sistemadenotas.dto.StudentDetailDTO;
import com.fs.sistemadenotas.dto.StudentEnrollmentDTO;
import com.fs.sistemadenotas.exception.StudentAlreadyEnrolledException;
import com.fs.sistemadenotas.model.Student;
import com.fs.sistemadenotas.service.IClassroomStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gradelify/v1/")
@CrossOrigin(value = "http://localhost:5173")
@RequiredArgsConstructor
public class ClassroomStudentController {

    private final IClassroomStudentService classroomStudentService;

    @GetMapping("classroom-students")
    public ResponseEntity<List<Student>> mostrarStudents(
            @RequestParam("classroomId") Integer classroomId,
            @RequestParam("bimestre") Integer bimestre) {

        List<Student> students = classroomStudentService.listarStudentsPorClassroomYBimestre(classroomId, bimestre);
        return ResponseEntity.ok(students);
    }

    @GetMapping("classroom-students-details")
    public ResponseEntity<Page<StudentDetailDTO>> getStudentDetailsByLevel(
            @RequestParam Integer idLevel,
            Pageable pageable) {

        Page<StudentDetailDTO> studentDetails = classroomStudentService.getStudentDetailsByLevel(idLevel, pageable);

        if (studentDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(studentDetails, HttpStatus.OK);
    }

    @GetMapping("classroom-allStudents-details")
    public ResponseEntity<Page<StudentDetailDTO>> getAllStudentDetails(Pageable pageable) {

        Page<StudentDetailDTO> studentDetails = classroomStudentService.getAllStudentDetails(pageable);

        if (studentDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(studentDetails, HttpStatus.OK);
    }

    @PostMapping("/classroom-students/add")
    public ResponseEntity<?> addStudentsToClassroom(@RequestBody StudentEnrollmentDTO enrollmentDTO) {
        try {
            classroomStudentService.addStudentsToClassroomAndEnrollInSubjects(enrollmentDTO);
            return ResponseEntity.ok().build();
        } catch (StudentAlreadyEnrolledException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/classroom-students/remove")
    public ResponseEntity<?> removeStudentFromClassroom(@RequestParam Integer classroomId, @RequestParam Integer studentId, @RequestParam Integer bimestre) {
        classroomStudentService.removeStudentFromClassroom(classroomId, studentId, bimestre);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/classroom-students/not-in-any-classroom")
    public ResponseEntity<List<Student>> getStudentsNotInAnyClassroomAndBimesterAndLevel(@RequestParam Integer bimestre, @RequestParam Integer levelId) {
        List<Student> students = classroomStudentService.listarEstudiantesNoInscritosEnNingunSalonYBimestreYNivel(bimestre, levelId);
        return ResponseEntity.ok(students);
    }
}
