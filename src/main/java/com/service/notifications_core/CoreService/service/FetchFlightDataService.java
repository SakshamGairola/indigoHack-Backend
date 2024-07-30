package com.service.notifications_core.CoreService.service;

import com.service.notifications_core.CommonServices.model.AirportQuery;

import java.util.List;

public interface FetchFlightDataService {

    public List<AirportQuery> fetchData(String airline, String caller);

}
