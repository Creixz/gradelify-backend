package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.model.Subject;

import java.util.List;

public interface ISubjectService {

    public List<Subject> listarSubject();

    public Subject buscarSubjectPorId(Integer idSubject);

    public Subject guardarRol(Subject subject);

    public void eliminarSubject(Subject subject);
}
