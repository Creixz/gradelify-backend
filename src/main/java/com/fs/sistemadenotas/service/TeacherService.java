package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.dto.TeacherResponse;
import com.fs.sistemadenotas.model.Teacher;
import com.fs.sistemadenotas.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService {

    private final TeacherRepository userRepository;

    @Override
    public List<TeacherResponse> listarUsuarios() {

        var usuarios = userRepository.findAll();

        return usuarios.stream()
                .map(usuario -> new TeacherResponse(
                        usuario.getIdTeacher(),
                        usuario.getUsername(),
                        usuario.getCorreo(),
                        usuario.getNombre(),
                        usuario.getApellidos(),
                        usuario.getRol(),
                        usuario.getSubject(),
                        usuario.getEstado()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Teacher> listarUsuariosPorIdSubject(Integer idSubject) {
        return userRepository.findBySubjectId(idSubject);
    }

    @Override
    public Teacher buscarUsuarioPorId(Integer idUsuario) {
        return userRepository.findById(idUsuario).orElse(null);
    }

    @Override
    public Teacher buscarUsuarioPorUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Teacher guardarUsuario(Teacher usuario) {
        return userRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Teacher usuario) {
        userRepository.delete(usuario);
    }

    @Override
    public void cambiarEstadoProfesor(Integer teacherId) {
        userRepository.cambiarEstado(teacherId);
    }

    @Override
    public void cambiarEstadoProfesorConUsername(String username) {
        userRepository.cambiarEstadoConUsername(username);
    }
}
