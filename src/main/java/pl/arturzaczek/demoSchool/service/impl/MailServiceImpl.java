package pl.arturzaczek.demoSchool.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.jpa.entities.User;
import pl.arturzaczek.demoSchool.service.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Value("${mail.content}")
    private String content;

    @Value("${mail.title}")
    private String title;

    private final JavaMailSender javaMailSender;

    public void sendRegistrationEmail(final User user, final String createdPassword) {
        String resultContent = content.replaceFirst("\\?", user.getEmail());
        resultContent = resultContent.replaceFirst("\\?", createdPassword);
        try {
            sendMail(user.getEmail(), title, resultContent);
        }catch (MessagingException | RuntimeException e){
            log.error("error during sending email: {}", e.getMessage());
        }
    }

    private void sendMail(final String to, final String title, final String mailContent) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(mailContent, true);
        javaMailSender.send(mimeMessage);
    }
}
