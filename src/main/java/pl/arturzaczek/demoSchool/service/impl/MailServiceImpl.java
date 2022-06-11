package pl.arturzaczek.demoSchool.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
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

    public void sendMail(final String to,
                         final String subject,
                         final String text,
                         final boolean isHtmlContent) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, isHtmlContent);
        javaMailSender.send(mimeMessage);
    }

    public void createRegistrationMail(final String email) {
        final String contentToSend = content.replace("?", email);
        try {
            sendMail(email, title, contentToSend, true);
        } catch (MessagingException ex) {
            log.error("Error during sending email\n{}", ex.getMessage());
        }
    }
}
