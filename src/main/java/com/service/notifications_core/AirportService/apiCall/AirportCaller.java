package com.service.notifications_core.AirportService.apiCall;

import com.service.notifications_core.CommonServices.model.AirportQuery;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AirportCaller {

    public static int counter = 0;
    private final List<AirportQuery> querySet = new ArrayList<>();

    public AirportCaller(){
        querySet.addAll(Arrays.asList(
                // on-time flight
                AirportQuery.builder()
                        .flight("AA123")
                        .airline("American Airlines")
                        .status("OnTime")
                        .departure_gate("A1")
                        .arrival_gate("B2")
                        .scheduled_departure("2024-07-26T13:00:00+00:00")
                        .scheduled_arrival("2024-07-26T15:00:00+00:00")
                        .build(),

                // delayed flight
                AirportQuery.builder()
                        .flight("AA456")
                        .airline("American Airlines")
                        .status("Delayed")
                        .departure_gate("A2")
                        .arrival_gate("B3")
                        .scheduled_departure("2024-07-26T14:00:00+00:00")
                        .scheduled_arrival("2024-07-26T16:00:00+00:00")
                        .build(),
                // cancelled flight
                AirportQuery.builder()
                        .flight("AA789")
                        .airline("American Airlines")
                        .status("Cancelled")
                        .scheduled_departure("2024-07-26T12:00:00+00:00")
                        .scheduled_arrival("2024-07-26T12:50:00+00:00")
                        .build(),

                // an example of departed flight that's in-bound
                AirportQuery.builder()
                        .flight("AA012")
                        .airline("American Airlines")
                        .status("Departed")
                        .departure_gate("A3")
                        .arrival_gate("B4")
                        .scheduled_departure("2024-07-26T13:00:00+00:00")
                        .scheduled_arrival("2024-07-26T13:00:00+00:00")
                        .actual_departure("2024-07-26T13:05:00+00:00")
                        .build(),

                // an example of arrived flight (that was delayed by 2 hours)
                AirportQuery.builder()
                        .flight("AA345")
                        .airline("American Airlines")
                        .status("Arrived")
                        .departure_gate("A4")
                        .arrival_gate("B5")
                        .scheduled_departure("2024-07-26T17:00:00+00:00")
                        .scheduled_arrival("2024-07-26T18:00:00+00:00")
                        .actual_departure("2024-07-26T19:03:00+00:00")
                        .actual_arrival("2024-07-26T20:15:00+00:00")
                        .build(),

                // an example of flight that's scheduled
                AirportQuery
                        .builder()
                        .flight("AA678")
                        .airline("American Airlines")
                        .status("Scheduled")
                        .departure_gate("A5")
                        .arrival_gate("B6")
                        .scheduled_departure("2024-07-27T22:00:00+00:00")
                        .scheduled_arrival("2024-07-27T23:00:00+00:00")
                        .build()
        ));

    }

    public List<AirportQuery> getAirLineData(String airlineName){
        counter += 1;
        airlineName = "indigo";
        if (counter%5 ==0){
            querySet.get(0).setStatus("Delayed");
        }

        for (AirportQuery item: querySet ){
            item.setLast_updated(Instant.now());
        }

        return querySet;
    }

}
