package pl.arturzaczek.demoSchool.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private LocalDateTime addedDate;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
}
