package com.fs.sistemadenotas.controller;

import com.fs.sistemadenotas.dto.StudentCreationDTO;
import com.fs.sistemadenotas.dto.StudentRequest;
import com.fs.sistemadenotas.model.Student;
import com.fs.sistemadenotas.model.Teacher;
import com.fs.sistemadenotas.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gradelify/v1/")
@CrossOrigin(value = "http://localhost:5173")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    @GetMapping("/students")
    public List<Student> mostrarStudents() {
        return studentService.listarStudent();
    }

    /*@PostMapping("/students")
    public ResponseEntity<Student> guardarStudent(@RequestBody Student student) {
        Student studentResponse = studentService.guardarStudent(student);
        return ResponseEntity.ok(studentResponse);
    }*/

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody StudentCreationDTO studentDTO) {
        Student createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> buscarStudentPorId(@PathVariable Integer id) {
        Student student = studentService.buscarStudentPorId(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students/{idStudent}/toggle-state")
    public ResponseEntity<String> toggleStudentState(@PathVariable Integer idStudent) {
        try {
            studentService.cambiarEstadoAlumno(idStudent);
            return ResponseEntity.ok("Estado del alumno cambiado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cambiar el estado del alumno: " + e.getMessage());
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> actualizarStudent(@PathVariable Integer id, @RequestBody StudentRequest studentRequest) {
        Student student = studentService.buscarStudentPorId(id);

        if(student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        student.setCodigoEstudiante(studentRequest.getCodigoEstudiante());
        student.setNombres(studentRequest.getNombres());
        student.setApellidos(studentRequest.getApellidos());
        student.setEstado(1);

        Student studentResponse = studentService.guardarStudent(student);

        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @GetMapping("/students/level/{levelId}")
    public ResponseEntity<List<Student>> getStudentsByLevel(@PathVariable Integer levelId) {
        List<Student> students = studentService.getStudentsByLevel(levelId);
        return ResponseEntity.ok(students);
    }
}
