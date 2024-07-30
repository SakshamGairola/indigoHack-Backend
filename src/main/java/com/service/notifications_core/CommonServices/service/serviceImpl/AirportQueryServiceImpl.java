package com.service.notifications_core.CommonServices.service.serviceImpl;

import com.service.notifications_core.CommonServices.model.AirportQuery;
import com.service.notifications_core.CommonServices.repository.AirportQueryRepository;
import com.service.notifications_core.CommonServices.service.AirportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AirportQueryServiceImpl implements AirportQueryService {


    @Autowired
    AirportQueryRepository airportQueryRepository;

    @Override
    public Map<String, AirportQuery> findAllByFlight(List<String> flights) {
        List<AirportQuery> flightsResultSet = airportQueryRepository.findByFlightIn(flights);

        Map<String, AirportQuery> storedFlightsMap = new HashMap<>();

        for(AirportQuery flight: flightsResultSet){
            storedFlightsMap.put(flight.getFlight(), flight);
        }

        return storedFlightsMap;
    }

    @Override
    public void saveOrUpdate(AirportQuery flight, AirportQuery storedFlight) {

        if(storedFlight == null){
            airportQueryRepository.save(
                    AirportQuery
                            .builder()
                            .flight(flight.getFlight())
                            .airline(flight.getAirline())
                            .status(flight.getStatus())
                            .departure_gate(flight.getDeparture_gate())
                            .arrival_gate(flight.getArrival_gate())
                            .scheduled_departure(flight.getScheduled_departure())
                            .actual_arrival(flight.getActual_arrival())
                            .actual_departure(flight.getActual_departure())
                            .last_updated(flight.getLast_updated())
                            .build()
            );
            return;
        }

        storedFlight.setStatus(flight.getStatus());
        storedFlight.setDeparture_gate(flight.getDeparture_gate());
        storedFlight.setArrival_gate(flight.getArrival_gate());
        storedFlight.setScheduled_departure(flight.getScheduled_departure());
        storedFlight.setActual_arrival(flight.getActual_arrival());
        storedFlight.setActual_departure(flight.getActual_departure());
        storedFlight.setLast_updated(flight.getLast_updated());

        airportQueryRepository.save(storedFlight);

    }
}
