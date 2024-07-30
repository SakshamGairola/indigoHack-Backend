package com.service.notifications_core.CommonServices.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AirportQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String flight;
    private String airline;
    private String status;
    private String departure_gate;
    private String arrival_gate;
    private String scheduled_departure;
    private String scheduled_arrival;
    private String actual_departure;
    private String actual_arrival;
    private Instant last_updated;

    @Override
    public boolean equals(Object obj){
        if(obj.getClass() != this.getClass()){
            return false;
        }
        return Objects.equals(((AirportQuery) obj).flight,
                this.flight);
    }

}
