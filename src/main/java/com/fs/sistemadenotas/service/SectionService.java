package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.model.Section;
import com.fs.sistemadenotas.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService implements ISectionService{

    private final SectionRepository sectionRepository;

    @Override
    public List<Section> listarSection() {
        return sectionRepository.findAll();
    }

    @Override
    public Section buscarSectionPorId(Integer idSection) {
        return sectionRepository.findById(idSection).orElse(null);
    }

    @Override
    public Section guardarSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void eliminarSection(Section section) {
        sectionRepository.delete(section);
    }
}
