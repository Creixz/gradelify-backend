package com.fs.sistemadenotas.controller;

import com.fs.sistemadenotas.model.Level;
import com.fs.sistemadenotas.service.ILevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gradelify/v1/")
@CrossOrigin(value = "https://gradelify-frontend-react-app.vercel.app/")
@RequiredArgsConstructor
public class LevelController {

    private final ILevelService levelService;

    @GetMapping("/grados")
    public ResponseEntity<List<Level>> listarGrados() {
        var grados = levelService.listarLevel();
        return ResponseEntity.ok(grados);
    }
}
