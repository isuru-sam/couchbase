package com.general.generalapp.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtil {
	public static final String DATE_TIME_WITH_MILLISECONDS = "yyyy-MM-dd'T'hh:mm:ss.SSS"; 
	public static String getCurrentTime(String dateTimeFormat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateTimeFormat);
        return simpleDateFormat.format(new Date());
    }
	
	public static Date getDate(String date1) {
		//String str_date = "2017-01-07";
		String str_date = date1;
		DateFormat formatter;
		Date date =null;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(str_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static String convertToDateString(Date d) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String s = formatter.format(d);
		return s;
	}
	
}
