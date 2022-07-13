package pl.arturzaczek.demoSchool.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
@RequiredArgsConstructor
public class SchoolClassController {

    @GetMapping("/student-classes")
    public String getStudentsClasses() {
        log.info("url= /student-classes, method=getStudentsClasses()");
        return "school-class/class-list";
    }
}
