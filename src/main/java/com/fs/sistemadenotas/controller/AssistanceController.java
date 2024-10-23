package com.fs.sistemadenotas.controller;

import com.fs.sistemadenotas.model.Assistance;
import com.fs.sistemadenotas.service.IAssistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gradelify/v1/")
@CrossOrigin(value = "http://localhost:5173")
@RequiredArgsConstructor
public class AssistanceController {

    private final IAssistanceService assistanceService;

    @GetMapping("/assistance/{idClassroom}/{bimestre}")
    public ResponseEntity<List<Assistance>> getAssistanceByClassroomAndBimestre(
            @PathVariable Integer idClassroom,
            @PathVariable Integer bimestre) {
        List<Assistance> assistances = assistanceService.getAssistanceByClassroomAndBimestre(idClassroom, bimestre);
        return ResponseEntity.ok(assistances);
    }

    @GetMapping("/assistance/{idEstudiante}/{idClassroom}/{bimestre}")
    public ResponseEntity<List<Assistance>> getAssistanceByStudentAndClassroomAndBimestre(
            @PathVariable Integer idEstudiante,
            @PathVariable Integer idClassroom,
            @PathVariable Integer bimestre) {
        List<Assistance> assistances = assistanceService.getAssistanceByStudentAndClassroomAndBimestre(idEstudiante, idClassroom, bimestre);
        return ResponseEntity.ok(assistances);
    }

    @GetMapping("/assistance/{idEstudiante}/{idClassroom}/{bimestre}/{idSubject}")
    public ResponseEntity<List<Assistance>> getAssistanceByStudentAndClassroomAndBimestreAndSubject(
            @PathVariable Integer idEstudiante,
            @PathVariable Integer idClassroom,
            @PathVariable Integer bimestre,
            @PathVariable Integer idSubject) {
        List<Assistance> assistances = assistanceService.getAssistanceByStudentAndClassroomAndBimestreAndSubject(idEstudiante, idClassroom, bimestre, idSubject);
        return ResponseEntity.ok(assistances);
    }

    @GetMapping("/assistance/classroom/{idClassroom}/bimestre/{bimestre}/subject/{idSubject}")
    public ResponseEntity<List<Assistance>> getAssistanceByClassroomAndBimestreAndSubject(
            @PathVariable Integer idClassroom,
            @PathVariable Integer bimestre,
            @PathVariable Integer idSubject) {
        List<Assistance> assistances = assistanceService.getAssistanceByClassroomAndBimestreAndSubject(idClassroom, bimestre, idSubject);
        return ResponseEntity.ok(assistances);
    }

    @GetMapping("/assistance/classroom/{idClassroom}/bimestre/{bimestre}/subject/{idSubject}/semana/{semana}")
    public ResponseEntity<List<Assistance>> getAssistanceByClassroomAndBimestreAndSubjectAndSemana(
            @PathVariable Integer idClassroom,
            @PathVariable Integer bimestre,
            @PathVariable Integer idSubject,
            @PathVariable Integer semana) {
        List<Assistance> assistances = assistanceService.getAssistanceByClassroomAndBimestreAndSubjectAndSemana(idClassroom, bimestre, idSubject, semana);
        return ResponseEntity.ok(assistances);
    }

    @PostMapping("/assistance")
    public ResponseEntity<Assistance> saveOrUpdateAssistance(@RequestBody Assistance assistance) {
        Assistance updatedAssistance = assistanceService.saveOrUpdateAssistance(assistance);
        return ResponseEntity.ok(updatedAssistance);
    }

    @PutMapping("/assistance/{classroomId}/student/{studentId}/subject/{subjectId}/bimestre/{bimestre}/week/{week}")
    public ResponseEntity<Assistance> updateAssistance(
            @PathVariable Integer classroomId,
            @PathVariable Integer studentId,
            @PathVariable Integer subjectId,
            @PathVariable Integer bimestre,
            @PathVariable Integer week,
            @RequestParam String status) {
        Assistance updatedAssistance = assistanceService.updateAssistance(studentId, classroomId, subjectId, bimestre, week, status);
        return ResponseEntity.ok(updatedAssistance);
    }


}
