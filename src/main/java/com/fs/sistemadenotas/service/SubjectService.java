package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.model.Subject;
import com.fs.sistemadenotas.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService implements ISubjectService{

    private final SubjectRepository subjectRepository;


    @Override
    public List<Subject> listarSubject() {
        return subjectRepository.findAllExceptId6();
    }

    @Override
    public Subject buscarSubjectPorId(Integer idSubject) {
        return subjectRepository.findById(idSubject).orElse(null);
    }

    @Override
    public Subject guardarRol(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void eliminarSubject(Subject subject) {
        subjectRepository.delete(subject);
    }
}
