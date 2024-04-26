package com.jwt.example.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import com.groups.utils.GroupsCalendar;


public class KranthiUtils {
	
	public static DateFormat dbDateFormat = new SimpleDateFormat(
			KranthiConstants.DB_DATE_FORMAT);
	
	public static String getWhere(HashMap hmDataArg)
	{
		return getWhere("where", hmDataArg);
	}

	public static String getWhere(String typeArg, HashMap hmDataArg)
	{
		boolean yes = false;

		StringBuffer sb = new StringBuffer();

		boolean init = true;

		Iterator itr = hmDataArg.keySet().iterator();

		String key = null;

		String val = null;

		String opertor = " like ";

		boolean isDate = false;

		while (itr.hasNext())
		{
			key = (String) itr.next();
			isDate = false;
			val = KranthiUtils.nullToEmpty(hmDataArg.get(key));
			opertor = " like ";

			if (key.indexOf("@") != -1)
			{
				opertor = key.substring(key.indexOf("@") + 1);
				key = key.substring(0, key.indexOf("@"));
			}
			if (key.indexOf("#") != -1)
			{
				String dt = key.substring(key.indexOf("#") + 1);
				key = key.substring(0, key.indexOf("#"));

				if ("to_db_date".equalsIgnoreCase(dt))
				{
					isDate = true;
					val = KranthiBaseAccess.getDbDate(val);
				}
			}

			if (val.length() > 0)
			{
				if (init)
				{
					init = false;
					if ("where".equalsIgnoreCase(typeArg))
					{
						sb.append(" where ");
					}
					else if ("and".equalsIgnoreCase(typeArg))
					{
						sb.append(" and ");
					}
					else
					{
						sb.append(" ");
					}
				}
				if (yes)
				{
					sb.append(" and ");
				}

				if (key.toString().startsWith("and"))
				{
					sb.append(" " + val + " ");
				}
				else
				{
					if (opertor.equalsIgnoreCase("in"))
					{
						sb.append(" " + key + " " + opertor + " " + val + " ");
					}
					else
					{
						if ("like".equalsIgnoreCase(opertor.trim()))
						{
							if (!isDate)
								val = GroupsUtils.convertSearchField(val);
						}

						sb.append(" " + key + opertor + " '" + val + "' ");
					}
				}
				yes = true;
			}
		}

		return sb.toString();

	}
	
	public static String nullToEmpty(Object s)
	{
		if (s == null)
			return "";
		else
			return (s.toString()).trim();
	}
	
	public static final String getDbDate(GregorianCalendar calArg)
	{
		String val = "";

		if (calArg == null)
			return "";

		val = dbDateFormat.format(calArg.getTime());

		return val;
	}
	
	public static GregorianCalendar getCalendar(String calendar)
	{
		// if (calendar == null || calendar.equals(""))
		// return null;
		// GregorianCalendar date = null;
		//
		// date = getCalendar(calendar, GroupsConstants.DATE_FORMAT);
		//
		// return date;

		return GroupsCalendar.getCalendar(calendar);
	}
	
}
