package com.service.notifications_core.CoreService.common;

import lombok.Getter;

@Getter
public enum  FlightStatus {
    ON_TIME("OnTime"),
    DELAYED("Delayed"),
    CANCELLED("Cancelled");

    private final String status;

    FlightStatus(String status) {
        this.status = status;
    }

    public static boolean isValidStatus(String status) {

        for (FlightStatus flightStatus : FlightStatus.values()) {
            if (flightStatus.getStatus().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;

    }

}
