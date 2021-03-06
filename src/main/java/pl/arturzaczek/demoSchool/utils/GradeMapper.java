package pl.arturzaczek.demoSchool.utils;

import org.springframework.stereotype.Component;
import pl.arturzaczek.demoSchool.dto.GradeDTO;
import pl.arturzaczek.demoSchool.jpa.entities.Grade;

@Component
public class GradeMapper {

    public Grade mapDTOtoGradeEntity(final GradeDTO gradeDTO){
        return Grade.builder()
                .gradeValueEnum(gradeDTO.getGradeValueEnum())
                .subject(gradeDTO.getSubjectName())
                .build();
    }
}
