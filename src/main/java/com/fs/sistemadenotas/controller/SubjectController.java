package com.fs.sistemadenotas.controller;

import com.fs.sistemadenotas.model.Subject;
import com.fs.sistemadenotas.service.ISubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gradelify/v1/")
@CrossOrigin(value = "http://localhost:5173")
@RequiredArgsConstructor
public class SubjectController {

    private final ISubjectService subjectService;

    @GetMapping("/cursos")
    public ResponseEntity<List<Subject>> allSubjects() {
        var subjects = subjectService.listarSubject();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/cursos/{id}")
    public ResponseEntity<Subject> findSubjectById(@PathVariable Integer id) {
        var subject = subjectService.buscarSubjectPorId(id);
        return ResponseEntity.ok(subject);
    }
}
