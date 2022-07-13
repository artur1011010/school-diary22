package pl.arturzaczek.demoSchool.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.arturzaczek.demoSchool.service.StudentService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/addStudent")
    public String addStudent() {
        log.info("url= /addStudent, method=addStudent()");
        return "student/addStudent";
    }

    @GetMapping("/studentsList")
    public String getStudentsList() {
        log.info("url= /studentsList, method=getStudentsList()");
        return "student/studentsList";
    }

    @GetMapping("/studentsList-student")
    public String studentsListStudent() {
        log.info("url= /studentsList-student, method=studentsListStudent()");
        return "student/studentsListStudent";
    }

    @GetMapping("/studentProfile")
    public String getStudentProfile() {
        log.info("url= /studentProfile, method=getStudentProfile()");
        return "studentProfile";
    }

    @GetMapping("/studentProfile/{id}")
    public String getStudentProfile2(@PathVariable final String id, final Model model) {
        model.addAttribute("student_id", id);
        model.addAttribute("grades", studentService.getGrades());
        log.info("url= /studentProfile/{}, method=getStudentProfile2()", id);
        return "studentProfile";
    }
}
