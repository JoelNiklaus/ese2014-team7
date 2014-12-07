package org.sample.model;

import java.util.Calendar;
import java.util.Date;

public class DateConverter{

	/**
	 * Parses Date from String.
	 * @param dateString  	format: dd.mm.yyyy
	 * @return
	 */
	public static Date parseDate(String dateString)
	{
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
	
		int month, day, year;
		
		try
		{		
			day = Integer.parseInt(dateString.substring(0, 2));
			month = Integer.parseInt(dateString.substring(3, 5))-1;
			year = Integer.parseInt(dateString.substring(6, 10));
			
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			
			date = cal.getTime();
		}
		catch(Exception ex)
		{
			System.out.println(ex + ": " + ex.getMessage());
			return null;
		}
		
		return date;
	}

}
