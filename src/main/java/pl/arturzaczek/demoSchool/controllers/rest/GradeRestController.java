package pl.arturzaczek.demoSchool.controllers.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.arturzaczek.demoSchool.model.dto.GradeDTO;
import pl.arturzaczek.demoSchool.service.GradeService;

@RestController
@RequestMapping("/rest")
@Slf4j
@RequiredArgsConstructor
public class GradeRestController {

    private final GradeService gradeService;

    @PostMapping("/grade/{student}")
    public void addGradeToStudentById(@PathVariable final String student, @RequestBody final GradeDTO gradeDTO) {
        log.debug("url= /rest/grade/{student}, method=addGradeToStudentById(), STUDENT: " + student + " , GRADE: " + gradeDTO);
        Long id = Long.parseLong(student);
        gradeService.addGradeToStudentById(id, gradeDTO);
    }
}

