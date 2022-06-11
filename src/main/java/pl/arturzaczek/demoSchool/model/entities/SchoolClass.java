package pl.arturzaczek.demoSchool.model.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SCHOOL_CLASS")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column (name = "added_date")
    protected LocalDateTime addedDate;
    private String schoolClassName;
    @OneToOne
    private User classTeacher;
    @OneToMany(mappedBy = "schoolClass")
    private List<User> studentsList = new ArrayList<>();
}
