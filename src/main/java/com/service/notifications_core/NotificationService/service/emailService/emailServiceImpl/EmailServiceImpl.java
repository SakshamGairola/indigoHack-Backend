package com.service.notifications_core.NotificationService.service.emailService.emailServiceImpl;

import com.service.notifications_core.NotificationService.service.emailService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(String toEmail, String sub, String message){

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("IndigoH2H");
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(sub);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

}
