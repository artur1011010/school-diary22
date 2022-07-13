package pl.arturzaczek.demoSchool.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.dto.GradeDTO;
import pl.arturzaczek.demoSchool.jpa.entities.Grade;
import pl.arturzaczek.demoSchool.jpa.entities.User;
import pl.arturzaczek.demoSchool.jpa.repositories.UserRepository;
import pl.arturzaczek.demoSchool.service.GradeService;
import pl.arturzaczek.demoSchool.utils.GradeMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final UserRepository userRepository;
    private final GradeMapper gradeMapper;

    public void addGradeToStudentById(final Long studentId, final GradeDTO gradeDTO) {
        log.info("addGradeToStudentById: \n{}", gradeDTO);
        final User user = userRepository
                .findById(studentId)
                .orElseGet(() -> User.builder()
                        .firstName("Error")
                        .build());
        final Grade grade = gradeMapper.mapDTOtoGradeEntity(gradeDTO);
        grade.setStudent(user.getId());
        user.addToGradeList(grade);
        userRepository.save(user);
    }
}