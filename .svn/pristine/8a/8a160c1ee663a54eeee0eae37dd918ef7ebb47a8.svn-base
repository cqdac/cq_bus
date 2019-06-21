package com.egean.cq_bus.domain;

import java.util.Comparator;

public class Bus {

    private String busId;
    private String departureTime;
    private int direction;
    private int shift;

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    // 字符串按照整型排序比较器
    public static class ShiftComparator implements Comparator<Bus> {
        private boolean reverseOrder; // 是否倒序
        public ShiftComparator(boolean reverseOrder) {
            this.reverseOrder = reverseOrder;
        }

        @Override
        public int compare(Bus o1, Bus o2) {
            if(reverseOrder){
                return o2.shift-o1.shift;
            }
            else{
                return o1.shift-o2.shift;
            }
        }
    }
}
