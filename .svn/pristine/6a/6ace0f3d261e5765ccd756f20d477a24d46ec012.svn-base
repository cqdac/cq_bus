package com.egean.cq_bus.analysis.domain;

import com.egean.cq_bus.domain.SchedulesResult;

import java.util.*;

/**
 * 用于公交时间段统计分析 timeRangeMap key 标识小时时间例如 5 代表5点
 */
public class TimeRange {

    private static Map<Integer, List<Analysis>> timeRangeMap;
    static{
        timeRangeMap = new HashMap<>();
        timeRangeMap.put(5,new ArrayList<Analysis>());
        timeRangeMap.put(6,new ArrayList<Analysis>());
        timeRangeMap.put(7,new ArrayList<Analysis>());
        timeRangeMap.put(8,new ArrayList<Analysis>());
        timeRangeMap.put(9,new ArrayList<Analysis>());
        timeRangeMap.put(10,new ArrayList<Analysis>());
        timeRangeMap.put(11,new ArrayList<Analysis>());
        timeRangeMap.put(12,new ArrayList<Analysis>());
        timeRangeMap.put(13,new ArrayList<Analysis>());
        timeRangeMap.put(14,new ArrayList<Analysis>());
        timeRangeMap.put(15,new ArrayList<Analysis>());
        timeRangeMap.put(16,new ArrayList<Analysis>());
        timeRangeMap.put(17,new ArrayList<Analysis>());
        timeRangeMap.put(18,new ArrayList<Analysis>());
        timeRangeMap.put(19,new ArrayList<Analysis>());
        timeRangeMap.put(20,new ArrayList<Analysis>());
        timeRangeMap.put(21,new ArrayList<Analysis>());
        timeRangeMap.put(22,new ArrayList<Analysis>());
        timeRangeMap.put(23,new ArrayList<Analysis>());
    }

    public static Map<Integer, List<Analysis>> getTimeRangeMap() {
        return timeRangeMap;
    }

    /**
     * 获取发车班次和客流量
     * @return Object[]  0=busCounts[]   1=kCounts[]
     */
    public static Object[] getBusCountsAndKcount(){
        Integer[] busCounts = new Integer[timeRangeMap.size()];
        Integer[] kCounts = new Integer[timeRangeMap.size()];
        Set<Integer> set = timeRangeMap.keySet();
        List<Analysis> analysisList;
        int index = 0;
        int busCount = 0;
        int kCount = 0;
        int total_busCount = 0;
        int total_kCount = 0;
        for (Integer hour : set) {
            analysisList = timeRangeMap.get(hour);
            Collections.sort(analysisList,new Analysis.Str2IntComparator(true));
            for (Analysis analysis:analysisList) {
                busCount+= analysis.getBusCount();
                kCount+= analysis.getCount();
//                break;
                total_busCount+= analysis.getBusCount();
                total_kCount += analysis.getCount();
            }
            analysisList.clear();
            busCounts[index] = busCount;
            kCounts[index] = kCount;
            index++;
            busCount = 0;
            kCount = 0;
        }
        System.out.println("总的发车班次："+total_busCount);
        System.out.println("总的客流量："+total_kCount);
        return new Object[]{busCounts,kCounts};
    }
}
