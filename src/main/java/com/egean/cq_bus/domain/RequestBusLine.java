package com.egean.cq_bus.domain;

import java.util.List;

public class RequestBusLine {

    List<ObjectivesDto> objectivesDtos;
    private int numberOfBuses;
    private int minDispatchGap;
    private int maxDispatchGap;
    private int numberOfUplinkBuses;
    private int numberOfDownlinkBuses;
    private String startTime;
    private String endTime;
    private String lineNum;

    public List<ObjectivesDto> getObjectivesDtos() {
        return objectivesDtos;
    }

    public void setObjectivesDtos(List<ObjectivesDto> objectivesDtos) {
        this.objectivesDtos = objectivesDtos;
    }

    public int getNumberOfBuses() {
        return numberOfBuses;
    }

    public void setNumberOfBuses(int numberOfBuses) {
        this.numberOfBuses = numberOfBuses;
    }

    public int getMinDispatchGap() {
        return minDispatchGap;
    }

    public void setMinDispatchGap(int minDispatchGap) {
        this.minDispatchGap = minDispatchGap;
    }

    public int getMaxDispatchGap() {
        return maxDispatchGap;
    }

    public void setMaxDispatchGap(int maxDispatchGap) {
        this.maxDispatchGap = maxDispatchGap;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public int getNumberOfUplinkBuses() {
        return numberOfUplinkBuses;
    }

    public void setNumberOfUplinkBuses(int numberOfUplinkBuses) {
        this.numberOfUplinkBuses = numberOfUplinkBuses;
    }

    public int getNumberOfDownlinkBuses() {
        return numberOfDownlinkBuses;
    }

    public void setNumberOfDownlinkBuses(int numberOfDownlinkBuses) {
        this.numberOfDownlinkBuses = numberOfDownlinkBuses;
    }
}
