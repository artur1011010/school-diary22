package pl.arturzaczek.demoSchool.service;

import org.springframework.http.ResponseEntity;
import pl.arturzaczek.demoSchool.dto.StudentResponse;
import pl.arturzaczek.demoSchool.dto.TeacherDTO;
import pl.arturzaczek.demoSchool.dto.UserRegisterForm;
import pl.arturzaczek.demoSchool.jpa.entities.User;
import java.util.List;

public interface UserService {

    ResponseEntity<List<StudentResponse>> getUsersList();
    void save20users();
    ResponseEntity<StudentResponse> getStudentById(Long student_id);
    ResponseEntity<Void> deleteById(Long long_id);
    List<TeacherDTO> getSchoolTeachersList();
    void saveNewUser(User user);
}
