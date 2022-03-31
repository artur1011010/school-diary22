package pl.arturzaczek.demoSchool.model.dto;

import lombok.Data;

@Data
public class GradeDTO {
    private String subjectName;
    private GradeValueEnum gradeValueEnum;
}
