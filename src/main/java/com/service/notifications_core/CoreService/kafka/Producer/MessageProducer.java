package com.service.notifications_core.CoreService.kafka.Producer;

import com.service.notifications_core.CommonServices.model.AirportQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    public void sendMessage(String topic, String message) {
//        kafkaTemplate.send(topic, message);
//    }

    @Autowired
    private KafkaTemplate<String, Object> statusChangeUpdate;

    public void sendStatusChangeUpdate(String topic, AirportQuery message) {
        statusChangeUpdate.send(topic, message);
    }

}