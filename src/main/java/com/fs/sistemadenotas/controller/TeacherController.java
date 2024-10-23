package com.fs.sistemadenotas.controller;

import com.fs.sistemadenotas.dto.TeacherRequest;
import com.fs.sistemadenotas.dto.TeacherResponse;
import com.fs.sistemadenotas.model.Rol;
import com.fs.sistemadenotas.model.Subject;
import com.fs.sistemadenotas.model.Teacher;
import com.fs.sistemadenotas.service.IRolService;
import com.fs.sistemadenotas.service.ISubjectService;
import com.fs.sistemadenotas.service.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gradelify/v1/")
@CrossOrigin(value = "http://localhost:5173")
@RequiredArgsConstructor
public class TeacherController {

    private final ITeacherService teacherService;
    private final IRolService rolService;
    private final ISubjectService subjectService;

    @GetMapping("/profesores")
    public ResponseEntity<List<TeacherResponse>> showAllTeacher() {
        var teachers = teacherService.listarUsuarios();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/profesores/s")
    public ResponseEntity<List<Teacher>> showTeacherByIdSubject(@RequestParam("idSubject") Integer idSubject) {
        var teaachers = teacherService.listarUsuariosPorIdSubject(idSubject);
        return ResponseEntity.ok(teaachers);
    }

    @PutMapping("/profesores/{teacherId}/toggle-state")
    public ResponseEntity<String> toggleTeacherState(@PathVariable Integer teacherId) {
        try {
            teacherService.cambiarEstadoProfesor(teacherId);
            return ResponseEntity.ok("Estado del profesor cambiado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cambiar el estado del profesor: " + e.getMessage());
        }
    }

    @PutMapping("/profesores/username/{username}/toggle-state")
    public ResponseEntity<String> toggleTeacherStateWithUsername(@PathVariable String username) {
        try {
            teacherService.cambiarEstadoProfesorConUsername(username);
            return ResponseEntity.ok("Estado del profesor cambiado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cambiar el estado del profesor: " + e.getMessage());
        }
    }

    @GetMapping("/profesores/{id}")
    public ResponseEntity<TeacherResponse> buscarTeacherPorId(@PathVariable Integer id) {
        Teacher teacher = teacherService.buscarUsuarioPorId(id);
        if (teacher != null) {
            TeacherResponse teacherResponse = TeacherResponse.builder()
                    .idTeacher(teacher.getIdTeacher())
                    .username(teacher.getUsername())
                    .nombre(teacher.getNombre())
                    .apellidos(teacher.getApellidos())
                    .correo(teacher.getCorreo())
                    .rol(teacher.getRol())
                    .estado(teacher.getEstado())
                    .subject(teacher.getSubject())
                    .build();

            return ResponseEntity.ok(teacherResponse);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/profesores/{id}")
    public ResponseEntity<TeacherResponse> actualizarTeacher(@PathVariable Integer id, @RequestBody TeacherRequest teacherRequest) {
        Teacher teacher = teacherService.buscarUsuarioPorId(id);
        Rol rol = rolService.buscarRolPorId(teacherRequest.getIdRol());
        Subject subject = subjectService.buscarSubjectPorId(teacherRequest.getIdSubject());

        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualizar la información del profesor con los datos proporcionados en el request
        teacher.setUsername(teacherRequest.getUsername());
        teacher.setNombre(teacherRequest.getNombre());
        teacher.setApellidos(teacherRequest.getApellidos());
        teacher.setCorreo(teacherRequest.getCorreo());
        teacher.setRol(rol);
        teacher.setSubject(subject);

        // Guardar los cambios en el repositorio
        teacher = teacherService.guardarUsuario(teacher);

        // Construir y devolver la respuesta
        TeacherResponse teacherResponse = TeacherResponse.builder()
                .idTeacher(teacher.getIdTeacher())
                .username(teacher.getUsername())
                .nombre(teacher.getNombre())
                .apellidos(teacher.getApellidos())
                .correo(teacher.getCorreo())
                .rol(teacher.getRol())
                .subject(teacher.getSubject())
                .estado(teacher.getEstado())
                .build();

        return new ResponseEntity<>(teacherResponse, HttpStatus.OK);
    }


}
