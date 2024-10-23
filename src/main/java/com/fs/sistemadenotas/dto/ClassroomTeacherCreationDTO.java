package com.fs.sistemadenotas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomTeacherCreationDTO {
    private Integer classroomId;
    private List<Integer> teacherIds;
}
