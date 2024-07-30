package com.service.notifications_core.NotificationService.service.smsService.smsServiceImpl;

import com.service.notifications_core.NotificationService.service.smsService.SmsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsServiceImpl implements SmsService {

    @Value("${twilio.account_SID}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth_token}")
    private String AUTH_TOKEN;

    @Value("${twilio.phone_number}")
    private String phoneNumber;

    @Override
    public void send(String toPhoneNumber, String message){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(phoneNumber), message).create();
    }
}
