package com.lemon.phoenix.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat ymdhmsFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	//给我一个date，返回20180329：年月日
	/**
	 * 返回年月日：yyyyMMdd
	 * @param date
	 * @return
	 */
	public static String getYMDString(Date date){
		return ymdFormat.format(date);
	}
	
	public static String getYmdHmsString(Date date){
		return ymdhmsFormat.format(date);
	}
	
	/**
	 * 获得今天年月y日字符串：20180329
	 * @return
	 */
	public static String getTodayYMDString(){
		return getYMDString(new Date());
	}
	
	/**
	 * 获得此刻
	 * @return
	 */
	public static String getNowYmdHmsString(){
		return getYmdHmsString(new Date());
	}
	

	public static void main(String[] args) {
		System.out.println(getYmdHmsString(new Date()));
		
	}

}
