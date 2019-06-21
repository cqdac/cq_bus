package com.egean.cq_bus.utils;

import java.util.*;

public class MapSort {


    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, Integer> sortMapByKey(Map<String, Integer> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Integer> sortMap = new TreeMap<String, Integer>(new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }


    /**
     * 使用 Map按value进行排序
     * @param oriMap
     * @return
     */
    public static Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap,boolean isDesc) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator(isDesc));

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }


    static class MapKeyComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {

            return str1.compareTo(str2);
        }
    }

    static class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {

        boolean isDesc = false;
        MapValueComparator(boolean isDesc){
            this.isDesc = isDesc;
        }

        @Override
        public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

            if(isDesc){
                return me2.getValue().compareTo(me1.getValue());
            }else{
                return me1.getValue().compareTo(me2.getValue());
            }

        }
    }
}
