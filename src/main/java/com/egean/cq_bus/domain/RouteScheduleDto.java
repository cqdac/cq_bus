package com.egean.cq_bus.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RouteScheduleDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("departureTimes")
    private List<String> departureTimes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getDepartureTimes() {
        return departureTimes;
    }

    public void setDepartureTimes(List<String> departureTimes) {
        this.departureTimes = departureTimes;
    }
}
