package pl.arturzaczek.demoSchool.dto;

import lombok.Data;

@Data
public class GradeDTO {
    private String subjectName;
    private GradeValueEnum gradeValueEnum;
}
