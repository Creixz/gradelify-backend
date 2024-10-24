package com.fs.sistemadenotas.controller;

import com.fs.sistemadenotas.dto.AuthResponse;
import com.fs.sistemadenotas.dto.LoginRequest;
import com.fs.sistemadenotas.dto.RegisterRequest;
import com.fs.sistemadenotas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gradelify/v1/")
@CrossOrigin(value = "https://gradelify-frontend-react-app.vercel.app/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
