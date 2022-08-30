package pl.arturzaczek.demoSchool.service;

import pl.arturzaczek.demoSchool.jpa.entities.User;

public interface MailService {
    void sendRegistrationEmail(User user,  String createdPassword);
}
