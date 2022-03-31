package pl.arturzaczek.demoSchool.model.entities;

import lombok.*;
import org.apache.commons.collections.CollectionUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "USER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roleSet;
    @Column(name = "first_name", length = 150)
    private String firstName;
    @Column(name = "last_name", length = 100)
    private String lastName;
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Grade> gradeList;
    @Column(name = "password_hash", length = 100)
    private String passwordHash;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public void addRole(final Role role) {
        if (CollectionUtils.isEmpty(roleSet)) {
            roleSet = new HashSet<>();
        }
        roleSet.add(role);
    }

    public void addToGradeList(final Grade grade) {
        if (CollectionUtils.isEmpty(gradeList)) {
            gradeList = new ArrayList<>();
        }
        gradeList.add(grade);
    }
}
