package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.dto.TeacherResponse;
import com.fs.sistemadenotas.model.Teacher;

import java.util.List;

public interface ITeacherService {

    public List<TeacherResponse> listarUsuarios();

    public List<Teacher> listarUsuariosPorIdSubject(Integer idSubject);

    public Teacher buscarUsuarioPorId(Integer idUsuario);

    public Teacher buscarUsuarioPorUsername(String username);

    public Teacher guardarUsuario(Teacher usuario);

    public void eliminarUsuario(Teacher usuario);

    public void cambiarEstadoProfesor(Integer teacherId);

    public void cambiarEstadoProfesorConUsername(String username);
}
