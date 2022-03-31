package pl.arturzaczek.demoSchool.service;

import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.utils.RoleEnum;

public interface RoleService {
    void getORCreateDefaultRole(User user);

    void getORCreateDefaultRole(User user, RoleEnum roleEnum);
}
