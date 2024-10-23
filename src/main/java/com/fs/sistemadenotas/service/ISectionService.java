package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.model.Section;

import java.util.List;

public interface ISectionService {

    public List<Section> listarSection();

    public Section buscarSectionPorId(Integer idSection);

    public Section guardarSection(Section section);

    public void eliminarSection(Section section);
}
