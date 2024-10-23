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
public class StudentEnrollmentDTO {

    private Integer classroomId;
    private Integer bimestre;
    private List<Integer> studentIds;
}
