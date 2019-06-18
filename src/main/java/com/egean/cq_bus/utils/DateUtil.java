package com.egean.cq_bus.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
	public static SimpleDateFormat formatDateYM = new SimpleDateFormat(
			"yyyy-MM");

	public static SimpleDateFormat formatDate2NN = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static SimpleDateFormat formatDate2NNX = new SimpleDateFormat(
			"yyyyMMdd");

	/** 精确到毫秒的格式 */
	public static SimpleDateFormat formatDate2NNM = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.sss");

	/** 精确到秒的格式 */
	public static SimpleDateFormat formatFullDateTime2NN = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/** 精确到秒的格式 */
	public static SimpleDateFormat formatFullDateTime2NNs = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	/** 精确到秒的格式 */
	public static SimpleDateFormat formatTime2NNs = new SimpleDateFormat(
			"HH:mm");

	/**
	 * 取得当前日期 此方法名称与返回值含义不一致，不推荐使用。 取得当前日期及时间请用 getNow()
	 * 
	 * @retu rn 当前日期
	 * @since 0.1
	 */
	public static Date getToday() {
		return Calendar.getInstance().getTime();
	}


	public static String getBeforeDate(Date date,int days)  
	{  
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar calendar = Calendar.getInstance();     
		calendar.setTime(date);  
		calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR) - days);  
		return df.format(calendar.getTime());  
	}  

	public static String getAfterDate(Date date,int days)  
	{  
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar calendar = Calendar.getInstance();     
		calendar.setTime(date);  
		calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR) + days);  
		return df.format(calendar.getTime());  
	}  

	/**
	 * 取得当前时间
	 * 
	 * @return 当前时间
	 */
	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 求两个日期中较小的日期
	 * 
	 * 
	 * @param 日期1
	 *            ，日期2
	 * @return 返回小日期
	 * 
	 * @since 0.1
	 */
	public static Date getSmallDate(Date date1, Date date2) {
		return date1.compareTo(date2) < 0 ? date1 : date2;
	}

	/**
	 * 求两个日期中较大的日期
	 * 
	 * 
	 * @param 日期1
	 *            ，日期2
	 * @return 返回大日期
	 * 
	 * @since 0.1
	 */
	public static Date getBigDate(Date date1, Date date2) {
		return date1.compareTo(date2) > 0 ? date1 : date2;
	}

	/**
	 * 在给定日期上增减一段时间
	 * 
	 * 
	 * @param monthAmount
	 *            月数
	 * @param date
	 *            给定日期
	 * @return 增减后的日期
	 */
	public static Date addMonth2Date(int monthAmount, Date date) {
		Date newDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, monthAmount);
			newDate = calendar.getTime();
		}
		return newDate;
	}

	/**
	 * 在给定日期上增减一段时间
	 * 
	 * 
	 * @param dayAmount
	 *            天数
	 * @param date
	 *            给定日期
	 * @return 增减后的日期
	 */
	public static Date addDay2Date(int dayAmount, Date date) {
		Date newDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, dayAmount);
			newDate = calendar.getTime();
		}
		return newDate;
	}

	/**
	 * 在给定日期上增减一段时间
	 * 
	 * 
	 * @param hourAmount
	 *            小时数
	 * 
	 * @param date
	 *            给定日期
	 * @return 增减后的日期
	 */
	public static Date addHour2Date(int hourAmount, Date date) {
		Date newDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, hourAmount);
			newDate = calendar.getTime();
		}
		return newDate;
	}

	/**
	 * 在给定日期上增减一段时间
	 * 
	 * 
	 * @param minuteAmount
	 *            分钟数
	 * 
	 * @param date
	 *            给定日期
	 * @return 增减后的日期
	 */
	public static Date addMinute2Date(int minuteAmount, Date date) {
		Date newDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, minuteAmount);
			newDate = calendar.getTime();
		}
		return newDate;
	}

	/**
	 * 此方法将给出的和日期格式化成格式化的字符串。(只含有年月日)
	 * 
	 * @param 待格式的日期
	 * @return 格式化的字符串。例：1983-12-29
	 * @since 0.1
	 */
	public static String formatDate2NN(Date date) {
		return formatDate2NN.format(date);
	}

	/**
	 * 此方法将给出的和日期格式化成格式化的字符串。(只含有年月日)
	 * 
	 * @param 待格式的日期
	 * @return 格式化的字符串。例：1983-12-29
	 * @since 0.1
	 */
	public static String formatDate2NNX(Date date) {
		return formatDate2NNX.format(date);
	}

	/**
	 * 此方法将给出的和日期格式化成格式化的字符串。 （含有年月日时分秒)
	 * 
	 * @param 待格式的日期
	 * @return 格式化的字符串。例：1983-12-29 08:05:12
	 * @since 0.1
	 */
	public static String formatFullDateTime2NN(Date date) {
		if (null != date) {
			return formatFullDateTime2NN.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 此方法将给出的和日期格式化成格式化的字符串。 （含有年月日时分秒毫秒)
	 * 
	 * @param 待格式的日期
	 * @return 格式化的字符串。例：1983-12-29 08:05:12.234
	 * @since 0.1
	 */
	public static String formatFullDateTime2NNM(Date date) {
		return formatDate2NNM.format(date);
	}

	/**
	 * 此方法将给出的和日期格式化成格式化的字符串。(只含有年月)
	 * 
	 * @param 待格式的日期
	 * @return 格式化的字符串。例：1983-12
	 * @since 0.1
	 */
	public static String formatDateYM(Date date) {
		return formatDateYM.format(date);
	}

	/**
	 * 将字符串解析成日期类型，如果字符串含有/则按/分割,否则以-分
	 * 
	 * 
	 * 
	 * @param 待解析的字符串
	 * 
	 * 
	 * @return 解析后的日期
	 * @since 0.1
	 */
	public static Date getDateYM(String dateStr) {
		Date date = null;
		try {
			if (dateStr != null) {
				String separator = dateStr.indexOf('/') > 0 ? "/" : "-";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy"
						+ separator + "MM");
				date = simpleDateFormat.parse(dateStr);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return date;
	}

	/**
	 * 将字符串解析成日期类型，如果字符串含有/则按/分割,否则以-分
	 * 
	 * 
	 * @param 待解析的字符串
	 * 
	 * @return 解析后的日期
	 * @since 0.1
	 */
	public static Date getDate(String dateStr) {
		Date date = null;
		try {
			if (dateStr != null) {
				String separator = dateStr.indexOf('/') > 0 ? "/" : "-";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy"
						+ separator + "MM" + separator + "dd");
				date = simpleDateFormat.parse(dateStr);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return date;
	}

	/**
	 * 将字符串解析成日期时间类型，如果字符串含有/则按/分割,否则以-分
	 * 
	 * 
	 * @param dateTimeStr
	 *            待解析的字符串
	 * 
	 * @return 解析后的日期
	 */
	public static Date getDateTime(String dateTimeStr) {
		Date date = null;
		try {
			String separator = dateTimeStr.indexOf('/') > 0 ? "/" : "-";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy"
					+ separator + "MM" + separator + "dd HH:mm:ss");
			date = simpleDateFormat.parse(dateTimeStr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return date;
	}

	/**
	 * 取得传入日期的年
	 * 
	 * @param 待解析的日期
	 * @return 该日期的年份
	 * @since 0.1
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 取得传入日期的月
	 * 
	 * @param 待解析的日期
	 * @return 该日期的月份
	 * @since 0.1
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 取得传入日期的日
	 * 
	 * @param 待解析的日期
	 * @return 该日期的月份
	 * @since 0.1
	 */
	public static int getday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DATE);
	}

	/**
	 * 两个日期的月份差
	 * 
	 * @param 起始日期
	 *            ，结束日期
	 * 
	 * @return 两日期的月分差，例1998-4-21~1998-6-21 相差两个月 返回2
	 * @since 0.1
	 */
	public static int getDiffMonth(Date fromDate, Date toDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(fromDate);
		int fromYear = c.get(Calendar.YEAR);
		int fromMonth = c.get(Calendar.MONTH) + 1;
		c.setTime(toDate);
		int toYear = c.get(Calendar.YEAR);
		int toMonth = c.get(Calendar.MONTH) + 1;
		int monthCount = 0;

		if (toYear == fromYear) {
			monthCount = toMonth - fromMonth;
		} else if (toYear - fromYear == 1) {
			monthCount = 12 - fromMonth + toMonth;
		} else {
			monthCount = 12 - fromMonth + 12 * (toYear - fromYear - 1)
					+ toMonth;
		}
		return monthCount;
	}

	/**
	 * 两个日期的天数差
	 * 
	 * @param 起始日期
	 *            ，结束日期
	 * 
	 * @return 两日期的月分差，例1998-4-21~1998-4-25 相差两个月 返回4
	 * @since 0.1
	 */
	public static int getDiffDays(Date fromDate, Date toDate) {
		return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
	}

    /**
     * 两个日期的小时数差
     *
     * @param 起始日期
     *            ，结束日期
     *
     * @return 两日期的月分差，例1998-4-21~1998-4-25 相差两个月 返回4
     * @since 0.1
     */
    public static int getDiffHoures(Date fromDate, Date toDate) {
        return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60));
    }

	/**
	 * 两个日期的秒数差
	 */
	public static Long getDiffSecond(Date fromDate, Date toDate) {
		Calendar fromCal = Calendar.getInstance();
		fromCal.setTime(fromDate);
		fromCal.set(Calendar.MILLISECOND, 0);

		Calendar toCal = Calendar.getInstance();
		toCal.setTime(toDate);
		toCal.set(Calendar.MILLISECOND, 0);
		Long diff = (toCal.getTime().getTime() - fromCal.getTime().getTime()) / 1000;
		return diff;
	}

	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}

	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		return dayOfMonth;
	}

	/**
	 * 生成时间戳
	 * 
	 * @return String
	 */
	public static Long getTimestamp() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取某日的0点0时0分0妙时间的Date对象
	 * 
	 * @param datetime
	 * @return Date
	 */
	public static Date getDayStart(Date datetime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(datetime);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取某日的23点59时59分59妙998毫秒时间的Date对象
	 * 
	 * @param datetime
	 * @return Date
	 */
	public static Date getDayEnd(Date datetime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(datetime);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 998);
		return cal.getTime();
	}

	/**
	 * 获得当前时间，格式yyyy-MM-dd hh:mm:ss
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDate() {
		return getCurrentDate("yyyy-MM-dd hh:mm:ss");
	}

	/**
	 * 获得当前时间，格式yyyy-MM-dd
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDate2() {
		return getCurrentDate("yyyy-MM-dd");
	}

	/**
	 * 获得当前时间，格式自定义
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format) {
		Calendar day = Calendar.getInstance();
		day.add(Calendar.DATE, 0);
		SimpleDateFormat sdf = new SimpleDateFormat(format);// "yyyy-MM-dd"
		String date = sdf.format(day.getTime());
		return date;
	}

	/**
	 * 获得昨天时间，格式自定义
	 * 
	 * @param format
	 * @return
	 */
	public static String getYesterdayDate(String format) {
		Calendar day = Calendar.getInstance();
		day.add(Calendar.DATE, -1);
		SimpleDateFormat sdf = new SimpleDateFormat(format);// "yyyy-MM-dd"
		String date = sdf.format(day.getTime());
		return date;
	}

	/**
	 * 得到当前的时间,并转为固定格式的字符串
	 * 
	 * @param time
	 *            得到的毫秒时间
	 * @return 标准时间
	 */
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sDateTime = sdf.format(date); // 得到精确到秒的表示：2011-11-11 21:08:00
		return sDateTime;
	}
	
	public static String getCurrentTimess() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String sDateTime = sdf.format(date); // 得到精确到秒的表示：2011-11-11 21:08:00
		return sDateTime;
	}

	public static String getCurrentTimes() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sDateTime = sdf.format(date); // 得到精确到秒的表示：2011-11-11 21:08:00
		return sDateTime;
	}

	/**
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return 举例： compareDate("2009-09-12", null, 0);//比较天
	 *         compareDate("2009-09-12", null, 1);//比较月
	 *         compareDate("2009-09-12", null, 2);//比较年
	 */
	public static int compareDate(String startDay, String endDay, int stype) {

		if(stype != 3){
			int n = 0;
			@SuppressWarnings("unused")
			String[] u = { "天", "月", "年" };
			String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

			endDay = endDay == null ? getCurrentDate("yyyy-MM-dd") : endDay;

			DateFormat df = new SimpleDateFormat(formatStyle);
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			try {
				c1.setTime(df.parse(startDay));
				c2.setTime(df.parse(endDay));
			} catch (Exception e3) {
				System.out.println("wrong occured");
			}
			// List list = new ArrayList();
			while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
				// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
				n++;
				if (stype == 1) {
					c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
				} else {
					c1.add(Calendar.DATE, 1); // 比较天数，日期+1
				}
			}
			n = n - 1;
			if (stype == 2) {
				n = (int) n / 365;
			}
//			System.out.println(startDay+" -- "+endDay+" 相差多少"+u[stype]+":"+n);
			return n;
		}else{
			long hours = (getDateTime(startDay).getTime() - getDateTime(endDay).getTime())/(60*60*1000);
			//			SimpleDateFormat myFormatter = new SimpleDateFormat( "yyyy-MM-dd ");
			//			java.util.Date date= myFormatter.parse( "2003-06-01 ");
			//			java.util.Date mydate= myFormatter.parse( "2003-05-01 ");
			//			long day=(date.getTime()-mydate.getTime())/(24*60*60*1000);
			return (int)hours;
		}

	}

	/**
	 * 判断时间是否符合时间格式
	 */
	public static boolean isDate(String date, String dateFormat) {
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(
					dateFormat);
			format.setLenient(false);
			try {
				format.format(format.parse(date));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 实现给定某日期，判断是星期几 date:必须yyyy-MM-dd格式
	 */
	public static String getWeekday(String date) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdw = new SimpleDateFormat("E");
		Date d = null;
		try {
			d = sd.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sdw.format(d);
	}

	/**
	 * 用来全局控制 上一周，本周，下一周的周数变化
	 */
	private static int weeks = 0;

	/**
	 * 获得当前日期与本周一相差的天数
	 */
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	/**
	 * 获得本周星期一的日期
	 */
	public static String getCurrentMonday(String format) {
		weeks = 0;
		int mondayPlus = getMondayPlus();
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, mondayPlus);
		SimpleDateFormat sdf = new SimpleDateFormat(format);// "yyyy-MM-dd"
		String date = sdf.format(currentDate.getTime());
		return date;
	}

	/**
	 * 获得上周星期一的日期
	 */
	public static String getPreviousMonday(String format) {
		weeks--;
		int mondayPlus = getMondayPlus();
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		SimpleDateFormat sdf = new SimpleDateFormat(format);// "yyyy-MM-dd"
		String date = sdf.format(currentDate.getTime());
		return date;
	}

	/**
	 * 获得下周星期一的日期
	 */
	public static String getNextMonday(String format) {
		weeks++;
		int mondayPlus = getMondayPlus();
		// GregorianCalendar currentDate = new GregorianCalendar();
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		SimpleDateFormat sdf = new SimpleDateFormat(format);// "yyyy-MM-dd"
		String date = sdf.format(currentDate.getTime());
		return date;
	}

	/**
	 * 获得相应周的周日的日期 此方法必须写在getCurrentMonday，getPreviousMonday或getNextMonday方法之后
	 */
	public static String getSunday(String format) {
		int mondayPlus = getMondayPlus();
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, mondayPlus + 7 * weeks + 6);
		SimpleDateFormat sdf = new SimpleDateFormat(format);// "yyyy-MM-dd"
		String date = sdf.format(currentDate.getTime());
		return date;
	}

	/**
	 * method 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp）
	 * 
	 * @param dateString
	 *            需要转换为timestamp的字符串
	 * @return dataTime timestamp
	 */
	public final static java.sql.Timestamp string2Time(String dateString) {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);// 设定格式
		dateFormat.setLenient(false);
		Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// java.sql.Timestamp dateTime = new java.sql.Timestamp(date.getTime());
		return new java.sql.Timestamp(date.getTime());// Timestamp类型,timeDate.getTime()返回一个long型
	}

	/**
	 * method 将字符串类型的日期转换为一个Date（java.sql.Date）
	 * 
	 * @param dateString
	 *            需要转换为Date的字符串
	 * @return dataTime Date
	 */
	public final static java.sql.Date string2Date(String dateString) {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		dateFormat.setLenient(false);
		Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// java.sql.Date dateTime = new java.sql.Date(date.getTime());// sql类型
		return new java.sql.Date(date.getTime());
	}

	// 记录考勤， 记录迟到、早退时间
	public static String getState() {
		String state = "正常";
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date();
		try {
			Date d1 = df.parse("08:00:00");
			Date d2 = df.parse(df.format(d));
			Date d3 = df.parse("18:00:00");

			int t1 = (int) d1.getTime();
			int t2 = (int) d2.getTime();
			int t3 = (int) d3.getTime();
			if (t2 < t1) {

				long between = (t1 - t2) / 1000;// 除以1000是为了转换成秒
				long hour1 = between % (24 * 3600) / 3600;
				long minute1 = between % 3600 / 60;

				state = "迟到 ：" + hour1 + "时" + minute1 + "分";

			} else if (t2 < t3) {
				long between = (t3 - t2) / 1000;// 除以1000是为了转换成秒
				long hour1 = between % (24 * 3600) / 3600;
				long minute1 = between % 3600 / 60;
				state = "早退 ：" + hour1 + "时" + minute1 + "分";
			}
			return state;
		} catch (Exception e) {
			return state;
		}

	}

	/**
	 * 将毫秒时间转换为标准时间
	 * 
	 * @param time
	 *            得到的毫秒时间
	 * @return 标准时间
	 */
	public static String millisecondtotime(String time) {
		time = time.substring(6, time.length() - 2);
		long longtime = Long.parseLong(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
		Date dt = new Date();
		dt.setTime(longtime);
		String sDateTime = sdf.format(dt); // 得到精确到秒的表示：2011-11-11 21:08:00
		return sDateTime;
	}

	/**
	 * 将毫秒时间转换为标准时间
	 * 
	 * @param time
	 *            得到的毫秒时间
	 * @return 标准时间
	 */
	public static String millisecondtodata(String time) {
		time = time.substring(6, time.length() - 2);
		long longtime = Long.parseLong(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,HH,mm");
		// 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
		Date dt = new Date();
		dt.setTime(longtime);
		String sDateTime = sdf.format(dt);
		return sDateTime;
	}



	public static final long ONE_MIN_MILLISECONDS = 1000*60;  
	/**
	 * 分钟比较
	 * @param dateStr
	 * @param newDate
	 * @return 
	 */
	public static int getExpiredMin(String dateStr, String newDate){  
		int ret = -1;  

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  

		Date date;  
		try {  
			String msgdate = newDate.replaceAll("/", "-");
			date = sdf.parse(msgdate); 	
			////Log.e("---", "date = " + date.getTime()/1000 ); 
			String msgdates = dateStr.replaceAll("/", "-");
			Date dateNow = sdf.parse(msgdates);  	              
			long times = date.getTime() - dateNow.getTime();  
			if(times > 0){  
				ret = ((int)(times / ONE_MIN_MILLISECONDS));  
			}else{  
				ret = -1;  
			}  
		} catch (ParseException e) {  
			e.printStackTrace();  
		}  

		//Log.d("---", "dateStr = " + dateStr + " mins = " + ret);  
		return ret;  
	}  
	/**
	 * 时间解析
	 * @param timesamp
	 * @return
	 */
	public static String getChatTime(String timesamp) {
		String result = "";
		try {

			if(timesamp!=null){
				String[] time = timesamp.split(" ");
				String msg = time[0].replaceAll("/", "-");
				String[] days = msg.split("-");
				int year = Integer.parseInt(days[0]);
				int month = Integer.parseInt(days[1]);
				int day = Integer.parseInt(days[2]);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
				String date=sdf.format(new Date());
				int newYear = Integer.parseInt(date);

				if(newYear == year){
					result = month + "月" + day+"日  "+ time[1].substring(0,time[1].lastIndexOf(":"));
				}else {
					result = year + "年" + month + "月" + day+"日  "+ time[1].substring(0,time[1].lastIndexOf(":"));
				}


			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 根据出生日期获取年龄
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	public static int getAge(Date birthDay) { 
		int age = 20;
		try {
			if(birthDay != null){
		        Calendar cal = Calendar.getInstance(); 
		        
		        if (cal.before(birthDay)) { 
		            throw new IllegalArgumentException( 
		                "The birthDay is before Now.It's unbelievable!"); 
		        } 
		        int yearNow = cal.get(Calendar.YEAR); 
		        int monthNow = cal.get(Calendar.MONTH); 
		        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); 
		        cal.setTime(birthDay); 
		  
		        int yearBirth = cal.get(Calendar.YEAR); 
		        int monthBirth = cal.get(Calendar.MONTH); 
		        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH); 
		  
		        age = yearNow - yearBirth; 
		  
		        if (monthNow <= monthBirth) { 
		            if (monthNow == monthBirth) { 
		                if (dayOfMonthNow < dayOfMonthBirth) age--; 
		            }else{ 
		                age--; 
		            } 
		        } 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return age; 
    }
	
	/**
	 * 根据身份证获取出生日期
	 * @param idcNo
	 * @return
	 */
	public static String getBirthdayFromIdcNo(String idcNo){
		try {
			String bh = idcNo.substring(6,14);
			System.out.println(bh);
			String year = bh.substring(0,4);
			System.out.println(year);
			String month = bh.substring(4,6);
			System.out.println(month);
			String day = bh.substring(6,8);
			return year+"-"+month+"-"+day;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		try {
			System.out.println(getAge(sdf.parse("2000-09-19")));
			
			Calendar cal = Calendar.getInstance();
			System.out.println(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
