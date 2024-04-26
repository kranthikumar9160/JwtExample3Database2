package com.jwt.example.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.groups.GroupsUtils;

public class KranthiBaseAccess {
	
	public static DateFormat dbDateFormat = new SimpleDateFormat(
			KranthiConstants.DB_DATE_FORMAT);
	
	public static String TABLE_NAME = "TABLE_NAME";
	
	public static final String getDbDate(GregorianCalendar calArg)
	{
		String val = "";

		if (calArg == null)
			return "";

		val = dbDateFormat.format(calArg.getTime());

		return val;
	}
	
	public static final String getDbDate(String calArg)
	{
		String val = "";

		if (calArg == null || calArg.trim().length() == 0)
			return "";

		GregorianCalendar gc = KranthiUtils.getCalendar(calArg);

		val = dbDateFormat.format(gc.getTime());

		return val;
	}
	
	public static String numberSort(String col)
	{
		String col1 = col;

		if (col1 == null || col1.length() == 0)
			return "";

		return "( 0 + " + col1 + ")";
	}
}
