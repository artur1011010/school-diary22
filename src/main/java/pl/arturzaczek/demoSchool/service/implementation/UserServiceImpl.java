package pl.arturzaczek.demoSchool.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.dto.StudentResponse;
import pl.arturzaczek.demoSchool.model.dto.UserRegisterForm;
import pl.arturzaczek.demoSchool.model.entities.Role;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.model.repositories.RoleRepository;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;
import pl.arturzaczek.demoSchool.service.MailService;
import pl.arturzaczek.demoSchool.service.UserService;
import pl.arturzaczek.demoSchool.utils.RandomUserHelper;
import pl.arturzaczek.demoSchool.utils.RoleEnum;
import pl.arturzaczek.demoSchool.utils.StudentMapper;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${mail.content}")
    private String content;

    @Value("${mail.title}")
    private String title;

    private final MailService mailService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RandomUserHelper randomUserHelper;
    private final StudentMapper studentMapper;

    public void registerUser(final UserRegisterForm userRegisterForm) {
        final User user = User.builder()
                .firstName(userRegisterForm.getFormName())
                .lastName(userRegisterForm.getFormLastName())
                .email(userRegisterForm.getEmail())
                .passwordHash(passwordEncoder.encode(userRegisterForm.getPassword()))
                .build();
        user.setAddedDate(LocalDateTime.now());
        getORCreateDefaultRole(user);
        userRepository.save(user);
        createRegistrationMail(userRegisterForm);
    }

    private void createRegistrationMail(final UserRegisterForm userRegisterForm) {
        final String contentToSend = content.replace("?", userRegisterForm.getEmail());
        try {
            mailService.sendMail(userRegisterForm.getEmail(), title, contentToSend, true);
        } catch (MessagingException ex) {
            log.error("Error during sending email\n{}", ex.getMessage());
        }
    }

    public List<StudentResponse> getUsersList() {
        return userRepository
                .findAll()
                .stream()
                .map(studentMapper::mapUserToStudentResponse)
                .collect(Collectors.toList());
    }

    public void saveUser(final User user) {
        userRepository.save(user);
    }

    public void save20users() {
        final List<User> randomUserM = randomUserHelper.createRandomUserM();
        final List<User> randomUserF = randomUserHelper.createRandomUserF();
        randomUserM.addAll(randomUserF);
        userRepository.saveAll(randomUserM);
    }

    public ResponseEntity<StudentResponse> getStudentById(final Long student_id) {
        final Optional<User> byId = userRepository.findById(student_id);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentMapper.mapUserToStudentResponse(byId.get()));
    }

    public ResponseEntity deleteById(final Long long_id) {
        final Optional<User> byId = userRepository.findById(long_id);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(long_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    public boolean checkIfUserExist(final String email) {
        return userRepository
                .findFirstByEmail(email)
                .isPresent();
    }

    protected void getORCreateDefaultRole(final User user) {
        final Role role = roleRepository
                .findByRoleName(RoleEnum.ROLE_USER.toString())
                .orElseGet(() -> roleRepository.save(new Role(RoleEnum.ROLE_USER.toString())));
        user.addRole(role);
    }

    protected void getORCreateDefaultRole(final User user, final RoleEnum roleEnum) {
        final Role role = roleRepository
                .findByRoleName(roleEnum.toString())
                .orElseGet(() -> roleRepository.save(new Role(roleEnum.toString())));
        user.addRole(role);
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }
}
