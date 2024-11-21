package edu.icet.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender javaMailSender;

    public boolean sendEmailWithHtmlContent(String subject, String recipient, String messageBody){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("quantumclothings.info@gmail.com");
            mimeMessageHelper.setTo(recipient);
            mimeMessageHelper.setText(messageBody, true);
            mimeMessageHelper.setSubject(subject);

            javaMailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
}
