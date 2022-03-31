package pl.arturzaczek.demoSchool.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterForm {
    private String formName;
    private String formLastName;
    private String email;
    private String password;

    public UserRegisterForm(String formName, String formLastName, String email, String password) {
        this.formName = formName;
        this.formLastName = formLastName;
        this.email = email;
        this.password = password;
    }
}
