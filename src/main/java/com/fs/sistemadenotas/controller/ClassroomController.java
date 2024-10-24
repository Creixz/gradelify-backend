package com.fs.sistemadenotas.controller;

import com.fs.sistemadenotas.dto.ClassroomRequest;
import com.fs.sistemadenotas.model.Classroom;
import com.fs.sistemadenotas.service.IClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gradelify/v1/")
@CrossOrigin(value = "https://gradelify-frontend-react-app.vercel.app/")
@RequiredArgsConstructor
public class ClassroomController {

    private final IClassroomService classroomService;

    @GetMapping("/classrooms")
    public List<Classroom> mostrarUsuarios() {

        return classroomService.listarClassroom();
    }

    @PostMapping("/classrooms")
    public ResponseEntity<Classroom> agregarClasroom(@RequestBody ClassroomRequest classroomRequest) {

        Classroom classroom = classroomService.guardarClassroom(classroomRequest);

        return ResponseEntity.ok(classroom);
    }

    @PutMapping("/classrooms/{idClassroom}/toggle-state")
    public ResponseEntity<String> toggleClassroomState(@PathVariable Integer idClassroom) {
        try {
            classroomService.cambiarEstadoSalon(idClassroom);
            return ResponseEntity.ok("Estado del salón cambiado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cambiar el estado del salón: " + e.getMessage());
        }
    }

    @GetMapping("/classrooms/section/4")
    public ResponseEntity<List<Classroom>> getClassroomsWithSectionFour() {
        List<Classroom> classrooms = classroomService.getClassroomsWithSectionFour();
        return new ResponseEntity<>(classrooms, HttpStatus.OK);
    }

    @GetMapping("/classrooms/{idClassroom}")
    public ResponseEntity<Classroom> buscarClassroomById(@PathVariable Integer idClassroom) {
        Classroom classroom = classroomService.buscarClassroomPorId(idClassroom);
        return new ResponseEntity<>(classroom, HttpStatus.OK);
    }




}
