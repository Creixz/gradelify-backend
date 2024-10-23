package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.dto.ClassroomRequest;
import com.fs.sistemadenotas.model.Classroom;
import com.fs.sistemadenotas.model.Level;
import com.fs.sistemadenotas.model.Section;
import com.fs.sistemadenotas.repository.ClassroomRepository;
import com.fs.sistemadenotas.repository.LevelRepository;
import com.fs.sistemadenotas.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomService implements IClassroomService{

    private final ClassroomRepository classroomRepository;
    private final LevelRepository levelRepository;
    private final SectionRepository sectionRepository;

    @Override
    public List<Classroom> listarClassroom() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom buscarClassroomPorId(Integer idClassroom) {
        return classroomRepository.findById(idClassroom).orElse(null);
    }

    @Override
    public Classroom guardarClassroom(ClassroomRequest classroomRequest) {

        Level level = levelRepository.findById(classroomRequest.getIdLevel()).orElseThrow();
        Section section = sectionRepository.findById(classroomRequest.getIdSection()).orElseThrow();

        Classroom classroom = Classroom.builder()
                .section(section)
                .level(level)
                .estado(1)
                .build();

        classroomRepository.save(classroom);

        return classroom;

    }

    @Override
    public void eliminarClassroom(Classroom classroom) {
        classroomRepository.delete(classroom);
    }

    @Override
    public void cambiarEstadoSalon(Integer idClassroom) {
        classroomRepository.cambiarEstado(idClassroom);
    }

    @Override
    public List<Classroom> getClassroomsWithSectionFour() {
        return classroomRepository.findBySectionIdSection(4);
    }
}
