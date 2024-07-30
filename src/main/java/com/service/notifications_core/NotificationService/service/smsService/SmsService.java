package com.service.notifications_core.NotificationService.service.smsService;

public interface SmsService {
    void send(String toPhoneNumber, String message);
}
