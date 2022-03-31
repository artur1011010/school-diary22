package pl.arturzaczek.demoSchool.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.dto.GradeValueEnum;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;
import pl.arturzaczek.demoSchool.service.RoleService;
import pl.arturzaczek.demoSchool.service.StudentService;
import pl.arturzaczek.demoSchool.service.UserService;
import pl.arturzaczek.demoSchool.utils.RoleEnum;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostConstruct
    public void initializeTestUsers() {
        try {
            final User admin = new User("Admin", "Admin", "admin@gmail.com");
            final User teacher = new User("Teacher", "Teacher", "teacher@gmail.com");
            admin.setPasswordHash(passwordEncoder.encode("admin"));
            teacher.setPasswordHash(passwordEncoder.encode("teacher"));
            roleService.getORCreateDefaultRole(admin, RoleEnum.ROLE_USER);
            roleService.getORCreateDefaultRole(admin, RoleEnum.ROLE_ADMIN);
            roleService.getORCreateDefaultRole(teacher, RoleEnum.ROLE_USER);
            roleService.getORCreateDefaultRole(teacher, RoleEnum.ROLE_TEACHER);
            userRepository.saveAll(List.of(admin, teacher));
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
