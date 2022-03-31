package pl.arturzaczek.demoSchool.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "role_name")
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
    public Role() {
    }
}
