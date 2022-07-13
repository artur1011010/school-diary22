package pl.arturzaczek.demoSchool.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClassDTO {
    private Long classId;
    private LocalDateTime addedDate;
    private String schoolClassName;
    private Long classTeacherId;
    private String SchoolClassTeacherNameAndSurname;
}
