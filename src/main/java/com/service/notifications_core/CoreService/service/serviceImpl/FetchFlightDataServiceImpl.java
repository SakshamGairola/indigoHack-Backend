package com.service.notifications_core.CoreService.service.serviceImpl;

import com.service.notifications_core.AirportService.apiCall.AirportCaller;
import com.service.notifications_core.CommonServices.model.AirportQuery;
import com.service.notifications_core.CoreService.service.FetchFlightDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchFlightDataServiceImpl implements FetchFlightDataService {

    @Override
    public List<AirportQuery> fetchData(String airline, String caller){
        return new AirportCaller().getAirLineData(airline, caller);
    }

}
