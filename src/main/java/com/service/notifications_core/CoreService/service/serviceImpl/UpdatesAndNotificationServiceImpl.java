package com.service.notifications_core.CoreService.service.serviceImpl;

import com.service.notifications_core.CommonServices.model.AirportQuery;
import com.service.notifications_core.CommonServices.service.AirportQueryService;
import com.service.notifications_core.CoreService.common.FlightStatus;
import com.service.notifications_core.CoreService.kafka.Producer.MessageProducer;
import com.service.notifications_core.CoreService.service.FetchFlightDataService;
import com.service.notifications_core.CoreService.service.UpdatesAndNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UpdatesAndNotificationServiceImpl implements UpdatesAndNotificationService {

    @Autowired
    AirportQueryService airportQueryService;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    FetchFlightDataService fetchFlightDataService;

    @Override
    public void checkFlightStatusesAndSendNotification() {
//      fetch data from Airport
        List<AirportQuery> flightsStatuses = fetchFlightDataService.fetchData("", "");

        List<String> flights = new ArrayList<>();

        for (AirportQuery status: flightsStatuses){
            flights.add(status.getFlight());
        }

        Map<String, AirportQuery> storedFlightStatuses = airportQueryService.findAllByFlight(flights);

        for(AirportQuery flight: flightsStatuses){

            AirportQuery storedFlight = storedFlightStatuses.get(flight.getFlight());

//          check if flight status changed
            if (!flightStatusChanged(flight, storedFlight)){
                continue;
            }

            System.out.println(flight);

//          push to kafka with flight object
            messageProducer.sendStatusChangeUpdate("statusChanged",flight);

//            System.out.println(flight.getFlight() + ": " + flight.getStatus());
            airportQueryService.saveOrUpdate(flight, storedFlight);

        }

    }

    public boolean flightStatusChanged(AirportQuery flight, AirportQuery storedFlight) {

        if(storedFlight != null){
            return !storedFlight.getStatus().equalsIgnoreCase(flight.getStatus());
        } else {
            return FlightStatus.isValidStatus(flight.getStatus());
        }

    }

}
