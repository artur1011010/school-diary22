package pl.arturzaczek.demoSchool.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ROLE_USER("USER"),
    ROLE_TEACHER("TEACHER"),
    ROLE_PRINCIPAL("PRINCIPAL"),
    ROLE_ADMIN("ADMIN"),
    ROLE_STUDENT("STUDENT"),
    ROLE_PARENT("PARENT");
    private String role;
}
