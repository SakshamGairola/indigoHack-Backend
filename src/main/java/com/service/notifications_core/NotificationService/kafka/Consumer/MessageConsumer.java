package com.service.notifications_core.NotificationService.kafka.Consumer;

import com.service.notifications_core.NotificationService.service.emailService.EmailService;
import com.service.notifications_core.NotificationService.service.smsService.SmsService;
import com.service.notifications_core.CommonServices.model.AirportQuery;
import com.service.notifications_core.CommonServices.model.Customer;
import com.service.notifications_core.CommonServices.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MessageConsumer {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SmsService smsService;

    @Autowired
    EmailService emailService;


    @KafkaListener(topics = "statusChanged", groupId = "my-group-id")
    public void statusChangedListen(AirportQuery flight) {
//      consume and send notification
        List<Customer> customerInFlight = customerRepository.findAllByFlight(flight.getFlight());

        String message = getUpdateMessage(flight);

        for(Customer customer: customerInFlight){
//          send sms
            if(customer.getPhoneNumber() != null) {
                smsService.send(customer.getPhoneNumber(), message);
            }
//          send email
            if(customer.getEmail() != null) {
                emailService.send(customer.getEmail(), "Flight Status updated",message);
            }
        }
        System.out.println("Received message: " + flight);
    }

    private static String getUpdateMessage(AirportQuery flight) {
        return String.format(
                "Your status of flight \"%s\" has been updated. " +
                        "Flight status: %s, Arrival Time: %s, Departure Time: %s, Arrival Gate: %s, Departure Gate: %s.",
                flight.getFlight(),
                Optional.ofNullable(flight.getStatus()).orElse("-"),
                Optional.ofNullable(flight.getScheduled_arrival()).orElse("-"),
                Optional.ofNullable(flight.getScheduled_departure()).orElse("-"),
                Optional.ofNullable(flight.getArrival_gate()).orElse("-"),
                Optional.ofNullable(flight.getDeparture_gate()).orElse("-")
        );
    }

}
