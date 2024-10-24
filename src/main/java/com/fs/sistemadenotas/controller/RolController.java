package com.fs.sistemadenotas.controller;

import com.fs.sistemadenotas.model.Rol;
import com.fs.sistemadenotas.service.IRolService;
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
public class RolController {

    private final IRolService rolService;

    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> allRoles() {
        var roles = rolService.listarRol();
        return ResponseEntity.ok(roles);
    }
}
