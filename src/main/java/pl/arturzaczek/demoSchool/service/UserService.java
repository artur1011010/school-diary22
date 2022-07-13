package pl.arturzaczek.demoSchool.service;

import org.springframework.http.ResponseEntity;
import pl.arturzaczek.demoSchool.dto.StudentResponse;
import pl.arturzaczek.demoSchool.dto.TeacherDTO;
import pl.arturzaczek.demoSchool.dto.UserRegisterForm;
import pl.arturzaczek.demoSchool.jpa.entities.User;
import java.util.List;

public interface UserService {

    void registerUser(UserRegisterForm userRegisterForm);
    ResponseEntity<List<StudentResponse>> getUsersList();
    void saveUser(User user);
    void save20users();
    ResponseEntity<StudentResponse> getStudentById(Long student_id);
    ResponseEntity deleteById(Long long_id);
    boolean checkIfUserExist(String email);
    List<User> getUserList();
    List<TeacherDTO> getSchoolTeachersList();
}
