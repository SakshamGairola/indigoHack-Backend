package com.service.notifications_core.CommonServices.repository;

import com.service.notifications_core.CommonServices.model.AirportQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AirportQueryRepository extends JpaRepository<AirportQuery, UUID> {

    List<AirportQuery> findByFlightIn(List<String> flights);

}
