package pl.arturzaczek.demoSchool.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.arturzaczek.demoSchool.dto.StudentResponse;
import pl.arturzaczek.demoSchool.dto.TeacherDTO;
import pl.arturzaczek.demoSchool.jpa.entities.Role;
import pl.arturzaczek.demoSchool.jpa.entities.User;
import pl.arturzaczek.demoSchool.jpa.repositories.RoleRepository;
import pl.arturzaczek.demoSchool.jpa.repositories.UserRepository;
import pl.arturzaczek.demoSchool.service.MailService;
import pl.arturzaczek.demoSchool.service.UserContextService;
import pl.arturzaczek.demoSchool.service.UserService;
import pl.arturzaczek.demoSchool.utils.RandomUserHelper;
import pl.arturzaczek.demoSchool.utils.RoleEnum;
import pl.arturzaczek.demoSchool.utils.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MailService mailService;
    private final UserContextService userContextService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RandomUserHelper randomUserHelper;
    private final UserMapper userMapper;

    public ResponseEntity<List<StudentResponse>> getUsersList() {
        return ResponseEntity.ok(userRepository
                .findAll()
                .stream()
                .map(userMapper::mapUserToStudentResponse)
                .collect(Collectors.toList()));
    }

    @Transactional
    public void saveNewUser(final User user){
        final String createdPassword = UUID.randomUUID().toString();
        user.setPasswordHash(passwordEncoder.encode(createdPassword));
        getOrCreateDefaultRole(user);
        getOrCreateStudentRole(user);
        mailService.sendRegistrationEmail(user, createdPassword);
        userRepository.save(user);
    }

    @Transactional
    public void save20users() {
        randomUserHelper.createAndSave20TestUsers();
    }

    public ResponseEntity<StudentResponse> getStudentById(final Long studentId) {
        final Optional<User> byId = userRepository.findById(studentId);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.mapUserToStudentResponse(byId.get()));
    }

    public ResponseEntity<Void> deleteById(final Long studentId) {
        final Optional<User> byId = userRepository.findById(studentId);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(studentId);
        return ResponseEntity.ok().build();
    }

    protected void getOrCreateDefaultRole(final User user) {
        final Role role = roleRepository
                .findByRoleName(RoleEnum.ROLE_USER.toString())
                .orElseGet(() -> roleRepository.save(new Role(RoleEnum.ROLE_USER.toString())));
        user.addRole(role);
    }

    protected void getOrCreateStudentRole(final User user) {
        final Role role = roleRepository
                .findByRoleName(RoleEnum.ROLE_USER.toString())
                .orElseGet(() -> roleRepository.save(new Role(RoleEnum.ROLE_USER.toString())));
        user.addRole(role);
    }

    @Override
    public List<TeacherDTO> getSchoolTeachersList() {
        return userRepository.findAll()
                .stream()
                .filter(this::isTeacher)
                .map(userMapper::mapUserToTeacherDTO)
                .collect(Collectors.toList());
    }

    private boolean isTeacher(final User user) {
        return user.getRoleSet().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList())
                .contains("ROLE_TEACHER");
    }

    @Override
    public ResponseEntity<StudentResponse> getStudentsProfile() {
        final Long loggedId = userContextService.getLoggedId();
        return getStudentById(loggedId);
    }
}