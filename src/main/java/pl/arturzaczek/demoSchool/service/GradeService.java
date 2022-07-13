package pl.arturzaczek.demoSchool.service;

import pl.arturzaczek.demoSchool.dto.GradeDTO;

public interface GradeService {
    void addGradeToStudentById(final Long studentId, final GradeDTO gradeDTO);
}
