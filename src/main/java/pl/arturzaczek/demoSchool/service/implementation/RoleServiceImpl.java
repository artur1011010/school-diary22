package pl.arturzaczek.demoSchool.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.entities.Role;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.model.repositories.RoleRepository;
import pl.arturzaczek.demoSchool.service.RoleService;
import pl.arturzaczek.demoSchool.utils.RoleEnum;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public void getORCreateDefaultRole(final User user) {
        final Role role = roleRepository
                .findByRoleName(RoleEnum.ROLE_USER.toString())
                .orElseGet(() -> roleRepository.save(new Role(RoleEnum.ROLE_USER.toString())));
        user.addRole(role);
    }

    public void getORCreateDefaultRole(final User user,final RoleEnum roleEnum) {
        final Role role = roleRepository
                .findByRoleName(roleEnum.toString())
                .orElseGet(() -> roleRepository.save(new Role(roleEnum.toString())));
        user.addRole(role);
    }

}
