package pl.arturzaczek.demoSchool.service;

import org.springframework.http.ResponseEntity;
import pl.arturzaczek.demoSchool.model.dto.StudentResponse;
import pl.arturzaczek.demoSchool.model.dto.UserRegisterForm;
import pl.arturzaczek.demoSchool.model.entities.User;
import java.util.List;

public interface UserService {

    void registerUser(UserRegisterForm userRegisterForm);
    List<StudentResponse> getUsersList();
    void saveUser(User user);
    void save20users();
    ResponseEntity<StudentResponse> getStudentById(Long student_id);
    ResponseEntity deleteById(Long long_id);
    boolean checkIfUserExist(String email);
    List<User> getUserList();
}
