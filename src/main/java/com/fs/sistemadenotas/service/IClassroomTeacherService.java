package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.dto.ClassroomTeacherCreationDTO;
import com.fs.sistemadenotas.dto.TeacherResponse;
import com.fs.sistemadenotas.model.Classroom;
import com.fs.sistemadenotas.model.ClassroomTeacher;

import java.util.List;

public interface IClassroomTeacherService {

    public List<TeacherResponse> findTeachersByClassroomId(Integer classroomId);

    public List<Classroom> findClassroomsByTeacherId(Integer teacherId);

    public ClassroomTeacher saveTeacherInClassroom(Integer classroomId, Integer teacherId);

    public void deleteTeacherFromClassroom(Integer classroomId, Integer teacherId);

    public void addTeachersToClassroom(ClassroomTeacherCreationDTO dto);
}
