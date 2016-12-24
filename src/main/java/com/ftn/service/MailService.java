package com.ftn.service;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Alex on 11/30/16.
 */
@Service
public class MailService {

    @Autowired
    MailSender mailSender;

    @Async
    public void sendVerificationMail(HttpServletRequest request, String recipient, String confirmationCode) {

        final SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Verify Account");
        message.setFrom("isatriofantastico@gmail.com");
        message.setTo(recipient);
        message.setText(request.getRequestURL().toString() + "/" + confirmationCode);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            System.out.println(e.toString());
        }
    }
}
