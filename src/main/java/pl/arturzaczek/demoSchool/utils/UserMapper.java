package pl.arturzaczek.demoSchool.utils;

import org.springframework.stereotype.Component;
import pl.arturzaczek.demoSchool.dto.StudentResponse;
import pl.arturzaczek.demoSchool.dto.TeacherDTO;
import pl.arturzaczek.demoSchool.jpa.entities.User;

@Component
public class UserMapper {
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

    public TeacherDTO mapUserToTeacherDTO(final User user){
        return TeacherDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
