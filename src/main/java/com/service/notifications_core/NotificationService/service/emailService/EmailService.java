package com.service.notifications_core.NotificationService.service.emailService;

public interface EmailService {

    void send(String toEmail, String sub, String message);

}
