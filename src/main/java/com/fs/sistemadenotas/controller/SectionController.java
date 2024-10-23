package com.fs.sistemadenotas.controller;

import com.fs.sistemadenotas.model.Section;
import com.fs.sistemadenotas.service.ISectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gradelify/v1/")
@CrossOrigin(value = "http://localhost:5173")
@RequiredArgsConstructor
public class SectionController {

    private final ISectionService sectionService;

    @GetMapping("/secciones")
    public ResponseEntity<List<Section>> listarSecciones() {
        var secciones = sectionService.listarSection();
        return ResponseEntity.ok(secciones);
    }
}
