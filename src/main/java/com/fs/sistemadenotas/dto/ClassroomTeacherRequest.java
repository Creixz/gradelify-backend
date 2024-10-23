package com.fs.sistemadenotas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomTeacherRequest {

    private Integer classroomId;
    private Integer teacherId;
}
