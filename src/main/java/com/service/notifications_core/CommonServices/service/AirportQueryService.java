package com.service.notifications_core.CommonServices.service;

import com.service.notifications_core.CommonServices.model.AirportQuery;

import java.util.List;
import java.util.Map;

public interface AirportQueryService {

    Map<String, AirportQuery> findAllByFlight(List<String> flights);

    Void saveOrUpdate(AirportQuery flight, AirportQuery storedFlight);

}
