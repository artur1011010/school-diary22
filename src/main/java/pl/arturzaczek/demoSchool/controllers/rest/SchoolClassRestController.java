package pl.arturzaczek.demoSchool.controllers.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.arturzaczek.demoSchool.model.dto.SchoolClassDTO;
import pl.arturzaczek.demoSchool.model.dto.TeacherDTO;
import pl.arturzaczek.demoSchool.service.SchoolClassService;
import pl.arturzaczek.demoSchool.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
@Slf4j
public class SchoolClassRestController {
    private final SchoolClassService schoolClassService;
    private final UserService userService;

    @PostMapping("/school-class")
    public ResponseEntity<Void> createNewSchoolClass(@RequestBody final SchoolClassDTO schoolClassDTO) {
        log.info("POST /rest/school-class, with body: {}", schoolClassDTO);
        schoolClassService.postNewSchoolClass(schoolClassDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/school-class")
    public ResponseEntity<List<SchoolClassDTO>> getSchoolClassList() {
        log.info("GET /rest/school-class");
        return ResponseEntity.ok(schoolClassService.getSchoolClassList());
    }

    @GetMapping("/teacher")
    public ResponseEntity<List<TeacherDTO>> getSchoolTeachersList() {
        log.info("GET /rest/school-class");
        return ResponseEntity.ok(userService.getSchoolTeachersList());
    }

    @DeleteMapping("/school-class/{id}")
    public ResponseEntity<Void> delteSchoolClass(@PathVariable Long id) {
        log.info("DELETE /rest/school-class");
        schoolClassService.deleteSchoolClassById(id);
        return ResponseEntity.ok().build();
    }



}
