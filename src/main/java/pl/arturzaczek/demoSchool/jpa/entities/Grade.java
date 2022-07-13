package pl.arturzaczek.demoSchool.jpa.entities;

import lombok.*;
import pl.arturzaczek.demoSchool.dto.GradeValueEnum;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
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
