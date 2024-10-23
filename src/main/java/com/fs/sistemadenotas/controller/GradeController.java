package com.fs.sistemadenotas.controller;

import com.fs.sistemadenotas.dto.StudentGradeBySubjectDTO;
import com.fs.sistemadenotas.dto.StudentGradeDTO;
import com.fs.sistemadenotas.service.IGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gradelify/v1/")
@CrossOrigin(value = "http://localhost:5173")
@RequiredArgsConstructor
public class GradeController {

    private final IGradeService gradeService;

    @GetMapping("/notas-estudiante/{idEstudiante}/salon/{idSalon}/bimestre/{bimestre}")
    public List<StudentGradeDTO> getStudentGrades(@PathVariable Integer idEstudiante,
                                                  @PathVariable Integer idSalon,
                                                  @PathVariable Integer bimestre) {
        return gradeService.getStudentGrades(idEstudiante, idSalon, bimestre);
    }


    @GetMapping("/notas-estudiante/{idClassroom}/bimestre/{bimestre}/curso/{idCurso}")
    public ResponseEntity<List<StudentGradeBySubjectDTO>> getStudentGradesByClassroomBimestreAndSubject(
            @PathVariable Integer idClassroom,
            @PathVariable Integer bimestre,
            @PathVariable Integer idCurso) {
        List<StudentGradeBySubjectDTO> studentGrades = gradeService.getStudentGradesByClassroomBimestreAndSubject(idClassroom, bimestre, idCurso);
        return ResponseEntity.ok(studentGrades);
    }

    @PutMapping("/actualizar-nota/{idClassroom}/bimestre/{bimestre}/curso/{idCurso}/estudiante/{idEstudiante}")
    public ResponseEntity<Void> actualizarNota(
            @PathVariable Integer idClassroom,
            @PathVariable Integer bimestre,
            @PathVariable Integer idCurso,
            @PathVariable Integer idEstudiante,
            @RequestBody Map<String, String> request) {
        String campo = request.get("campo");
        String valor = request.get("valor");
        gradeService.actualizarNota(idClassroom, bimestre, idCurso, idEstudiante, campo, valor);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
