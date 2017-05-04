package com.kuke.web.core.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/** 日期格式yyyy-MM-dd字符串常量 */
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	/** 日期格式yyyyMMdd字符串常量 */
	private static final String DATE_FORMAT_NUM = "yyyyMMdd";

	/** 日期格式yyyy-MM-dd HH:mm:ss字符串常量 */
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** 日期格式yyyy-MM字符串常量 */
	private static final String DATE_FORMATTo = "yyMMddHHmmss";
	private static final String DATEFORMATTo = "yyMMdd";

	private static final String DATEFORMATToYEAR = "yyyy";
	private static final String DATEFORMATToMONTH = "MM";
	private static final String DATEFORMATToDAY = "dd";
	private static final String DATEFORMATToHOURS = "HH";
	private static final String DATEFORMATToValue = "mmss";
	private static final String DATE_FORAMT_YYYYMM = "yyyyMM";
	
	/**
	 * 
	 * @Title: parseFormatYYYYMM
	 * @Description: 日期转String 格式yyyyMM
	 * @param date
	 * @return String 返回类型
	 */
	public static String parseFormatYYYYMM(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORAMT_YYYYMM);
		return sdf.format(date);
	}

	/**
	 * 时间转字符串 getYMD(这里用一句话描述这个方法的作用)
	 * 
	 * @param date
	 * @return
	 * @return String
	 * @exception
	 * 
	 */
	public static String getYMD(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);
	}

	/**
	 * 时间转字符串 getYMD(这里用一句话描述这个方法的作用)
	 * 
	 * @param date
	 * @return
	 * @return String
	 * @exception
	 * 
	 */
	public static String getYMDNum(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NUM);
		return sdf.format(date);
	}

	/**
	 * 将日期Date对象转换成yyyy-MM-dd HH:mm:ss字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getYMDHMSNum(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		return sdf.format(date);
	}

	public static String getDateTime(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		return sdf.format(date);
	}

	public static String getEndDateTime(Date date) {
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		return sdf.format(date);
	}

	/**
	 * 字符串转时间 getDateYMD(这里用一句话描述这个方法的作用)
	 * 
	 * @param date
	 * @return
	 * @return String
	 * @exception
	 * 
	 */
	public static Date getDateYMD(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			System.out.println("该异常可以忽略");
		}
		return null;
	}

	public static Date getDateYMDHMS(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			System.out.println("该异常可以忽略");
		}
		return null;
	}

	/**
	 * 设置为改天的23点59分59秒
	 * 
	 * @param date
	 * @return
	 */
	public static Long getLastTime(Date date) {
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		return date.getTime();
	}

	/**
	 * 获取年月日时分秒
	 * 
	 * @param date
	 * @return
	 */
	public static String getYMDHMSD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		return sdf.format(date);
	}

	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		return sdf.format(new Date());
	}

	// 返回相差的小时
	public static double getDatePoor(Date endDate, Date nowDate) {
		long nh = 1000 * 60 * 60;
		// 获得两个时间的毫秒时间差异
		double diff = nowDate.getTime() - endDate.getTime();
		// 计算差多少天
		// 计算差多少小时
		double hour = diff / nh;
		DecimalFormat df = new DecimalFormat(".00");
		return Double.parseDouble(df.format(hour));

	}

	// 返回一个字符串和当前时间的小时差
	public static double getDiffForCurrent(String endDate) {
		return getDatePoor(getDateYMDHMS(endDate), new Date());
	}

	public static void main(String[] args) {
		System.out.println(getDiffForCurrent("2015-03-02 11:00:00"));
	}

	// YY-MM-DD
	public static String getYMDTO(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATTo);
		return sdf.format(date);
	}

	// YYMMDD
	public static String getYMD2TO(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMATTo);
		return sdf.format(date);
	}

	// 获取一个时间戳
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	// 字符串转时间戳
	public static Timestamp getTimestamp(String datestr) {
		if (datestr == null) {
			return null;
		}
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(datestr);
			return ts;
		} catch (Exception e) {
			return null;
		}
	}

	// YY
	public static String getYMDTOYEAR(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMATToYEAR);
		return sdf.format(date);
	}

	// MM
	public static String getYMDTOMONTH(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMATToMONTH);
		return sdf.format(date);
	}

	// YY
	public static String getYMDTODAY(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMATToDAY);
		return sdf.format(date);
	}

	// YY
	public static String getYMDTOHOURS(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMATToHOURS);
		return sdf.format(date);
	}

	public static String DATEFORMATToValueS(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMATToValue);
		return sdf.format(date);
	}
	
	public static Date parse(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat();
		return sdf.parse(date);
	}

}
