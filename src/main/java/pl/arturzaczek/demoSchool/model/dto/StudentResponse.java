package pl.arturzaczek.demoSchool.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.arturzaczek.demoSchool.model.entities.Grade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private List<Grade> gradeList = new ArrayList<>();

}

