package pl.arturzaczek.demoSchool.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.dto.GradeValueEnum;
import pl.arturzaczek.demoSchool.jpa.entities.SchoolClass;
import pl.arturzaczek.demoSchool.jpa.entities.User;
import pl.arturzaczek.demoSchool.jpa.repositories.SchoolClassRepository;
import pl.arturzaczek.demoSchool.jpa.repositories.UserRepository;
import pl.arturzaczek.demoSchool.service.RoleService;
import pl.arturzaczek.demoSchool.service.StudentService;
import pl.arturzaczek.demoSchool.service.UserService;
import pl.arturzaczek.demoSchool.utils.RoleEnum;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final SchoolClassRepository schoolClassRepository;
    private final UserService userService;

    @PostConstruct
    public void initializeTestUsers() {
        try {
            final User admin = User.builder()
                    .firstName("Admin")
                    .lastName("Admin")
                    .email("admin@gmail.com")
                    .passwordHash(passwordEncoder.encode("admin"))
                    .build();
            roleService.getORCreateDefaultRole(admin, RoleEnum.ROLE_USER);
            roleService.getORCreateDefaultRole(admin, RoleEnum.ROLE_ADMIN);

            final User teacher = User.builder()
                    .firstName("Piotr")
                    .lastName("Nauczyciel")
                    .email("teacher@gmail.com")
                    .passwordHash(passwordEncoder.encode("teacher"))
                    .build();


            final User teacher2 = User.builder()
                    .firstName("Dorota")
                    .lastName("Szkolna")
                    .email("nauczyciel@gmail.com")
                    .passwordHash(passwordEncoder.encode("nauczyciel"))
                    .build();

            roleService.getORCreateDefaultRole(teacher, RoleEnum.ROLE_USER);
            roleService.getORCreateDefaultRole(teacher, RoleEnum.ROLE_TEACHER);

            roleService.getORCreateDefaultRole(teacher2, RoleEnum.ROLE_USER);
            roleService.getORCreateDefaultRole(teacher2, RoleEnum.ROLE_TEACHER);

            SchoolClass schoolClass1 = SchoolClass.builder()
                    .schoolClassName("3-B")
                    .addedDate(LocalDateTime.now())
                    .classTeacher(teacher)
                    .build();

            SchoolClass schoolClass2 = SchoolClass.builder()
                    .schoolClassName("1-A")
                    .addedDate(LocalDateTime.now())
                    .classTeacher(teacher)
                    .build();

            userRepository.saveAll(List.of(admin, teacher, teacher2));
            schoolClassRepository.saveAll(List.of(schoolClass1, schoolClass2));
            userService.save20users();
            log.info("\n test users created");
        } catch (RuntimeException ex) {
            log.error("Error during user creation {}", ex.getMessage());
        }
    }

    public List<String> getGrades() {
        return Arrays.stream(GradeValueEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
