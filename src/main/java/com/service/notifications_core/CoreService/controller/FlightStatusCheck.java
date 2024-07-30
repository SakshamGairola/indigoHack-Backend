package com.service.notifications_core.CoreService.controller;

import com.service.notifications_core.AirportService.apiCall.AirportCaller;
import com.service.notifications_core.CommonServices.model.AirportQuery;
import com.service.notifications_core.CoreService.service.FetchFlightDataService;
import com.service.notifications_core.CoreService.service.UpdatesAndNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class FlightStatusCheck {

    @Autowired
    UpdatesAndNotificationService updatesAndNotificationService;

    @Autowired
    FetchFlightDataService fetchFlightDataService;

    @Scheduled(cron ="0 */5 * * * *")
    public void Testthis(){
        System.out.println("CRON TRIGGERED AT: "+ new java.util.Date());
    }

    @GetMapping("/trigger-notification")
    public ResponseEntity<Map<String, Object>> triggerNotifications() {

        updatesAndNotificationService.checkFlightStatusesAndSendNotification();

        return new ResponseEntity<>(Map.of("result","Done!", "counter", AirportCaller.counter), HttpStatus.OK);
    }

    @GetMapping("/check-status")
    public ResponseEntity<Map<String, Object>> checkFlightStatus() {
        List<AirportQuery> airportQueryResult = fetchFlightDataService.fetchData("", "");
        return new ResponseEntity<>(Map.of("airportQueryResult", airportQueryResult), HttpStatus.OK);
    }

    @GetMapping("/check-changes")
    public ResponseEntity<Map<String, Object>> triggerKafka(){
        if(AirportCaller.counter % 5 == 0){
            List<AirportQuery> airportQueryResult = fetchFlightDataService.fetchData("", "dashboard");
            return new ResponseEntity<>(Map.of("airportQueryResult", airportQueryResult), HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("airportQueryResult", new ArrayList<>()), HttpStatus.OK);
    }

}
