package pl.arturzaczek.demoSchool.model.entities;

import lombok.*;
import pl.arturzaczek.demoSchool.model.dto.GradeValueEnum;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GRADE")
public class Grade extends BaseEntity {

    private String subject;
    private Long userId;
    @Enumerated(value = EnumType.STRING)
    private GradeValueEnum gradeValueEnum;

    public  void setStudent(Long id){
        this.userId = id;
    }
}
