package com.fs.sistemadenotas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentGradeDTO {

    private String curso;
    private String pa1;
    private String pa2;
    private String pa3;
    private String pc;
    private String rc;


}
