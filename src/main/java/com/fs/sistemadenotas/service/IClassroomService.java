package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.dto.ClassroomRequest;
import com.fs.sistemadenotas.model.Classroom;

import java.util.List;

public interface IClassroomService {

    public List<Classroom> listarClassroom();

    public Classroom buscarClassroomPorId(Integer idClassroom);

    public Classroom guardarClassroom(ClassroomRequest classroomRequest);

    public void eliminarClassroom(Classroom classroom);

    public void cambiarEstadoSalon(Integer idClassroom);

    public List<Classroom> getClassroomsWithSectionFour();
}
