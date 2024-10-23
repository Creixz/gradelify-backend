package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.dto.AuthResponse;
import com.fs.sistemadenotas.dto.LoginRequest;
import com.fs.sistemadenotas.dto.RegisterRequest;
import com.fs.sistemadenotas.model.Rol;
import com.fs.sistemadenotas.model.Subject;
import com.fs.sistemadenotas.model.Teacher;
import com.fs.sistemadenotas.repository.RolRepository;
import com.fs.sistemadenotas.repository.SubjectRepository;
import com.fs.sistemadenotas.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TeacherRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;
    private final SubjectRepository subjectRepository;

    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        Teacher userDetails = userRepository.findByUsername(request.getUsername()).orElseThrow();
        if (userDetails.getEstado() == 1) {
            // Generar el token JWT
            String token = jwtService.getToken(userDetails);
            // Devolver la respuesta con el token
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        Rol rol = rolRepository.findByIdRol(request.getIdRol()).orElseThrow();
        Subject subject = subjectRepository.findById(request.getIdSubject()).orElseThrow();


        Teacher usuario = Teacher.builder()
                .username(request.getCorreo().substring(0, request.getCorreo().indexOf('@')))
                .password(passwordEncoder.encode(request.getPassword()))
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .correo(request.getCorreo())
                .subject(subject)
                .rol(rol)
                .estado(1)
                .build();

        userRepository.save(usuario);

        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }

}
