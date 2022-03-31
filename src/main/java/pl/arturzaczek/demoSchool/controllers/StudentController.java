package pl.arturzaczek.demoSchool.controllers;

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
        log.debug("url= /addStudent, method=addStudent()");
        return "student/addStudent";
    }

    @GetMapping("/studentsList")
    public String getStudentsList() {
        log.debug("url= /studentsList, method=getStudentsList()");
        return "student/studentsList";
    }

    @GetMapping("/studentProfile")
    public String getStudentProfile() {
        return "studentProfile";
    }

    @GetMapping("/studentProfile/{id}")
    public String getStudentProfile2(@PathVariable final String id, final Model model) {
        model.addAttribute("student_id", id);
        model.addAttribute("grades", studentService.getGrades());
        return "studentProfile";
    }
}
