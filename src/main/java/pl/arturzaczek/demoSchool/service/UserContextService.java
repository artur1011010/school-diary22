package pl.arturzaczek.demoSchool.service;

import java.util.List;

public interface UserContextService {
    String getLoggedAs();

    Long getLoggedId();

    boolean hasRole(String roleName);

    boolean hasAnyRole(List<String> roleNames);

    boolean isLogged();

    public boolean isLoggedAsStudent();

    boolean isLoggedAsSchoolEmployee();
}
