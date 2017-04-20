package com.airline.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String toMySqlDateString(String dateString) {
		String mySqlDateString = null;
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			date = new SimpleDateFormat("MM/dd/yyyy").parse(dateString);
			mySqlDateString = dateFormatter.format(date);
		} catch (ParseException e) { }
		
		return mySqlDateString;
	}
}
