package pl.arturzaczek.demoSchool.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.arturzaczek.demoSchool.dto.SchoolClassDTO;
import pl.arturzaczek.demoSchool.jpa.entities.SchoolClass;
import pl.arturzaczek.demoSchool.jpa.entities.User;

@Component
public class SchoolClassMapper {
    public SchoolClass dtoToSchoolClass(final SchoolClassDTO source) {
        return SchoolClass.builder()
                .schoolClassName(source.getSchoolClassName())
                .addedDate(source.getAddedDate())
                .id(source.getClassId())
                .build();
    }

    public SchoolClassDTO schoolClassToDTO(final SchoolClass source) {
        final User classTeacher = source.getClassTeacher();
        final String nameAndSurname;
        if (classTeacher != null) {
            nameAndSurname = classTeacher.getFirstName() + " " + classTeacher.getLastName();
        } else {
            nameAndSurname = StringUtils.EMPTY;
        }
        return SchoolClassDTO.builder()
                .schoolClassName(source.getSchoolClassName())
                .addedDate(source.getAddedDate())
                .classId(source.getId())
                .SchoolClassTeacherNameAndSurname(nameAndSurname)
                .build();
    }
}
