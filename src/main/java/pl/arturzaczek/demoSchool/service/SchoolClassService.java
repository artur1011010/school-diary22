package pl.arturzaczek.demoSchool.service;

import pl.arturzaczek.demoSchool.model.dto.SchoolClassDTO;

import java.util.List;

public interface SchoolClassService {
    void postNewSchoolClass(SchoolClassDTO schoolClassDTO);
    List<SchoolClassDTO> getSchoolClassList();
    void deleteSchoolClassById(Long id);
}
