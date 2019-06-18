package com.egean.cq_bus.analysis.domain;


import java.util.Comparator;

public class Analysis {

    private int busCount;
    private int count;

    private TimeHms timeHms;

    public TimeHms getTimeHms() {
        return timeHms;
    }

    public void setTimeHms(TimeHms timeHms) {
        this.timeHms = timeHms;
    }

    public int getBusCount() {
        return busCount;
    }

    public void setBusCount(int busCount) {
        this.busCount = busCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    /**
     * 按照统计时间分钟排序
     */
    public static class Str2IntComparator implements Comparator<Analysis> {
        // 是否倒序
        private boolean reverseOrder;
        public Str2IntComparator(boolean reverseOrder) {
            this.reverseOrder = reverseOrder;
        }

        @Override
        public int compare(Analysis o1, Analysis o2) {
            if(reverseOrder){
                return o2.getTimeHms().getMinute() - o1.getTimeHms().getMinute();
            }
            else{
                return o1.getTimeHms().getMinute() - o2.getTimeHms().getMinute();
            }
        }
    }

    /**
     * 按照客流量排序
     */
    public static class KCountComparator implements Comparator<Analysis> {
        // 是否倒序
        private boolean reverseOrder;
        public KCountComparator(boolean reverseOrder) {
            this.reverseOrder = reverseOrder;
        }

        @Override
        public int compare(Analysis o1, Analysis o2) {
            if(reverseOrder){
                return o2.getCount() - o1.getCount();
            }
            else{
                return o1.getCount() - o2.getCount();
            }
        }
    }

    /**
     * 按照发车数量排序
     */
    public static class busCountComparator implements Comparator<Analysis> {
        // 是否倒序
        private boolean reverseOrder;
        public busCountComparator(boolean reverseOrder) {
            this.reverseOrder = reverseOrder;
        }

        @Override
        public int compare(Analysis o1, Analysis o2) {
            if(reverseOrder){
                return o2.getBusCount() - o1.getBusCount();
            }
            else{
                return o1.getBusCount() - o2.getBusCount();
            }
        }
    }
}
