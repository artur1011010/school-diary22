package pl.arturzaczek.demoSchool.service;

import java.util.List;

public interface StudentService {
    void initializeTestUsers();

    List<String> getGrades();
}
