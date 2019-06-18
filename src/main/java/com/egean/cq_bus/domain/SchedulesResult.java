package com.egean.cq_bus.domain;

import java.util.Comparator;
import java.util.List;

public class SchedulesResult{

    private String id;
    //上行发班时间
    private List<String> departureTimesUp;
    //下行发班时间
    private List<String> departureTimesDown;

    private List<String> departureTimes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getDepartureTimesUp() {
        return departureTimesUp;
    }

    public void setDepartureTimesUp(List<String> departureTimesUp) {
        this.departureTimesUp = departureTimesUp;
    }

    public List<String> getDepartureTimesDown() {
        return departureTimesDown;
    }

    public void setDepartureTimesDown(List<String> departureTimesDown) {
        this.departureTimesDown = departureTimesDown;
    }

    public List<String> getDepartureTimes() {
        return departureTimes;
    }

    public void setDepartureTimes(List<String> departureTimes) {
        this.departureTimes = departureTimes;
    }

    // 字符串按照整型排序比较器
    public static class Str2IntComparator implements Comparator<SchedulesResult> {
        private boolean reverseOrder; // 是否倒序
        public Str2IntComparator(boolean reverseOrder) {
            this.reverseOrder = reverseOrder;
        }

        @Override
        public int compare(SchedulesResult o1, SchedulesResult o2) {
            if(reverseOrder){
                return Integer.parseInt(o1.getId().replace("Bus #","")) - Integer.parseInt(o2.getId().replace("Bus #",""));
            }
            else{
                return Integer.parseInt(o2.getId().replace("Bus #","")) - Integer.parseInt(o1.getId().replace("Bus #",""));
            }
        }
    }
}
