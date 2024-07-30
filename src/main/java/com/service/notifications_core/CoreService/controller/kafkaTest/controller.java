package com.service.notifications_core.CoreService.controller.kafkaTest;

import com.service.notifications_core.CoreService.kafka.Producer.MessageProducer;
import com.service.notifications_core.CommonServices.model.AirportQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @Autowired
    private MessageProducer messageProducer;

    @GetMapping("/send")
    public String sendMessage() {
        String topic="topic1";
        AirportQuery message = AirportQuery.builder()
                .flight("AA123")
                .airline("American Airlines")
                .status("OnTime")
                .departure_gate("A1")
                .arrival_gate("B2")
                .scheduled_departure("2024-07-26T13:00:00+00:00")
                .scheduled_arrival("2024-07-26T15:00:00+00:00")
                .build();
        messageProducer.sendStatusChangeUpdate(topic, message);
        return "Message sent: " + message;
    }

}