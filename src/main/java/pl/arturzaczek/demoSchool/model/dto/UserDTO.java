package pl.arturzaczek.demoSchool.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
public class UserDTO {
    private Long id;
    private LocalDateTime addedDate;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
}
