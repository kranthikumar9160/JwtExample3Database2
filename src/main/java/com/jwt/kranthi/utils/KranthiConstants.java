package com.jwt.kranthi.utils;

import com.groups.ApplicationDependable;
import com.groups.DefaultApplicationDependableImpl;

public class KranthiConstants {
	public static final String DB_DATE_FORMAT = "yyyy-MM-dd";
	public static final String TOTAL_ROWS_COUNT = "TOTAL_ROWS_COUNT";
	public static final String DB_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String MONTH_DATE_FORMAT = "MMM-yyyy";
	
	public static ApplicationDependable getApplicationDependable()
	{
		if (applicationDependable == null)
			applicationDependable = new DefaultApplicationDependableImpl();

		return applicationDependable;
	}
}
