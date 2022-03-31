package pl.arturzaczek.demoSchool.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GradeValueEnum {
    EXCELLENT("celująca", 6, "od 96 do 100 proc"),
    VERY_GOOD("bardzo dobra", 5, "od 86 do 95 proc"),
    GOOD("dobra", 4, "od 71 do 85 proc"),
    SATISFACTORY("dostateczna", 3, "od 56 do 70 proc"),
    SUFFICIENT("dopuszczająca", 2, "od 41 do 55 proc"),
    INSUFFICIENT("niedostateczna", 1, "od 0 do 40 proc");

    private final String polishName;
    private final Integer gradeValueAsInt;
    private final String desc;

}
