package pl.arturzaczek.demoSchool.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.dto.SchoolClassDTO;
import pl.arturzaczek.demoSchool.model.repositories.SchoolClassRepository;
import pl.arturzaczek.demoSchool.service.SchoolClassService;
import pl.arturzaczek.demoSchool.utils.SchoolClassMapper;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchoolClassServiceImpl implements SchoolClassService {
    private final SchoolClassRepository schoolClassRepository;
    private final SchoolClassMapper schoolClassMapper;

    @Override
    public void postNewSchoolClass(final SchoolClassDTO schoolClassDTO) {
        schoolClassRepository.save(schoolClassMapper.dtoToSchoolClass(schoolClassDTO));
    }

    @Override
    public List<SchoolClassDTO> getSchoolClassList() {
        return schoolClassRepository.findAll().stream()
                .map(schoolClassMapper::schoolClassToDTO)
                .collect(Collectors.toList());
    }

    //todo do przetestowania
    @Override
    public void deleteSchoolClassById(final Long id) {
        schoolClassRepository.deleteById(id);
    }
}
