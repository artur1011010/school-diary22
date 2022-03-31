package pl.arturzaczek.demoSchool.utils;

import org.springframework.stereotype.Component;
import pl.arturzaczek.demoSchool.model.dto.StudentResponse;
import pl.arturzaczek.demoSchool.model.entities.User;

@Component
public class StudentMapper {
    public StudentResponse mapUserToStudentResponse(final User user){
        return StudentResponse.builder()
                .id(user.getId())
                .birthDate(user.getBirthDate())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gradeList(user.getGradeList())
                .email(user.getEmail())
                .build();
    }
}
