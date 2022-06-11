package pl.arturzaczek.demoSchool.controllers.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.arturzaczek.demoSchool.model.dto.StudentResponse;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/rest")
@Slf4j
@RequiredArgsConstructor
public class StudentRestController {
    private final UserService userService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponse>> getStudents() {
        log.debug("url= /rest/students, method=getStudents()");
        return userService.getUsersList();
    }

    @PostMapping("/student")
    public ResponseEntity<Void> addStudent(@RequestBody final User user) {
        log.debug("url= /rest/student, method=addStudent() STUDENT: " + user);
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/add20Students")
    public ResponseEntity<Void> add20Students() {
        userService.save20users();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable final Long studentId) {
        log.debug("url= /rest/student/{student_id}, method=getStudentById() STUDENT: " + studentId);
        return userService.getStudentById(studentId);
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable final Long studentId) {
        log.debug("url= /rest/student/{student_id}, method=deleteStudentById() STUDENT: " + studentId);
        userService.deleteById(studentId);
        return ResponseEntity.ok().build();
    }
}
