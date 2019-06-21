package com.egean.cq_bus.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeUtils {

    /**
     * 判断某个时间是否在制定的时间段内
     * @param time 时间毫秒值
     * @param startTimeStr 开始时间段字符串 如 7:00
     * @param endTimeStr 结束时间段字符串 如 22:00
     * @return 返回boolean 类型
     * @throws ParseException
     */
    public static boolean isTimeRange(long time,String startTimeStr,String endTimeStr)throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date now = df.parse(df.format(new Date(time)));
        Date begin = df.parse(startTimeStr);
        Date end = df.parse(endTimeStr);
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(now);
        Calendar beginTime = Calendar.getInstance();
        beginTime.setTime(begin);
        Calendar endTime = Calendar.getInstance();
        endTime.setTime(end);
        if (nowTime.before(endTime) && nowTime.after(beginTime)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 制定格式化时间
     * @param fromat 格式化格式   如YYYY-MM-dd HH:mm:ss等
     * @param time 需要格式化时间的毫秒值
     * @return
     */
    public static String getCommondTime(String fromat,long time){
        SimpleDateFormat sdf = new SimpleDateFormat(fromat);
        return sdf.format(new Date(time));
    }

    public static void timeZoneChange(){
        try{
            SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            System.out.println("foo:" + foo.format(new Date()));

            Calendar gc = GregorianCalendar.getInstance();
            System.out.println("gc.getTime():" + gc.getTime());
            System.out.println("gc.getTimeInMillis():" + new Date(gc.getTimeInMillis()));

            // 当前系统默认时区的时间：
            Calendar calendar = new GregorianCalendar();
            System.out.print("时区：" + calendar.getTimeZone().getID() + "  ");
            System.out.println("时间：" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
            // 美国洛杉矶时区
            TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
            // 时区转换
            calendar.setTimeZone(tz);
            System.out.print("时区：" + calendar.getTimeZone().getID() + "  ");
            System.out.println("时间：" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
            Date time = new Date();

            // 1、取得本地时间：
            java.util.Calendar cal = java.util.Calendar.getInstance();

            // 2、取得时间偏移量：
            int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);

            // 3、取得夏令时差：
            int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);

            // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
            cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

            // 之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。
            System.out.println("UTC:" + new Date(cal.getTimeInMillis()));

            Calendar calendar1 = Calendar.getInstance();
            TimeZone tztz = TimeZone.getTimeZone("GMT");
            calendar1.setTimeZone(tztz);
            System.out.println(calendar.getTime());
            System.out.println(calendar.getTimeInMillis());

            // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            // df.setTimeZone(TimeZone.getTimeZone("UTC"));
            // System.out.println(df.parse("2014-08-23T09:20:05Z").toString());

            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date t = new Date();
            System.out.println(df1.format(t));
            System.out.println(df1.format(df1.parse("2014-08-27T18:02:59.676Z")) + "***********");
            df1.setTimeZone(TimeZone.getTimeZone("UTC"));
            System.out.println(df1.format(t));
            System.out.println("-----------");
            System.out.println(df1.format(df1.parse("2014-08-27T18:02:59.676Z")) + "***********");
            System.out.println("2014-08-27T18:02:59.676Z");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * UTC就是世界标准时间，与北京时间相差八个时区（相关文章）。所以只要将UTC时间转化成一定格式的时间，再在此基础上加上8个小时就得到北京时间了。
     * @param UTCStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static String UTCToCST(String UTCStr, String format,int minutes) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        date = sdf.parse(UTCStr);
        System.out.println("UTC时间: " + date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 8);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minutes);
        //calendar.getTime() 返回的是Date类型，也可以使用calendar.getTimeInMillis()获取时间戳
        System.out.println("北京时间: " + sdf.format(calendar.getTime()));
        return sdf.format(calendar.getTime());
    }

    public static void main(String[] obj){
        try{
//            System.out.println(TimeUtils.isTimeRange(System.currentTimeMillis(),"14:00","15:00"));
//
//            System.out.println(TimeUtils.getCommondTime("HH",System.currentTimeMillis()));
//            timeZoneChange();
            UTCToCST("2018-12-28 00:00:00", "yyyy-MM-dd HH:mm:ss",0);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
