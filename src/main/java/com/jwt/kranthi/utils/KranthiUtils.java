package com.jwt.kranthi.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;


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

		return KranthiCalendar.getCalendar(calendar);
	}
	
	public static String nullToEmpty(String s)
	{
		if (s == null)
			return "";
		else
			return s.trim();
	}
	
	public static String getDateTime(Calendar calendar)
	{
		// String date;
		// date = getDate(calendar, GroupsConstants.DATE_FORMAT + " HH:mm:ss");
		// return date;

		return KranthiCalendar.getDateTime(calendar);

	}
	
	public static String getDate(Calendar calendar)
	{
		return KranthiCalendar.getDate(calendar);
		// return getDate(calendar, GroupsConstants.DATE_FORMAT);

	}
	
	public static String getDate(Calendar calendar, String formateArg)
	{
		// String date = null;
		// SimpleDateFormat smpleDateFormate = null;
		// smpleDateFormate = new SimpleDateFormat(formateArg);
		//
		// if (calendar == null)
		// return "";
		// try
		// {
		// date = smpleDateFormate.format(calendar.getTime());
		// }
		// catch (Exception e)
		// {
		// date = "";
		// e.printStackTrace();
		// }
		// return date;

		return KranthiCalendar.getDate(calendar, formateArg);

	}
	
	public static void logException(Exception e)
	{
		logException(null, e);
	}

	public static void logException(String contextMessage, Exception exArg)
	{
		StringBuffer sb = new StringBuffer();

		if (exArg == null)
			return;

		try
		{

			// createLogDir(logFileName);

			String exMsg = getPrintExeMessage(exArg);

			// fos = new FileOutputStream(logFile);
			if (exMsg != null)
			{
				if (contextMessage != null)
				{
					sb.append("\r\n ============================================================ \r\n");

					sb.append("\r\n =================== " + contextMessage
							+ " ===================== \r\n");

					sb.append("\r\n ============================================================ \r\n");
				}

				sb.append("\r\n===================Start Exception tracing at "
						+ new Date() + " \r\n");

				sb.append(exMsg);
				sb.append("\r\n===================End Exception tracing at "
						+ new Date() + "================\r\n");

				appendTologFile(sb.toString());

				// OutputStreamWriter writer = new OutputStreamWriter(
				// new FileOutputStream("x.txt", true), "UTF-8");
				// BufferedWriter fbw = new BufferedWriter(writer);
				// fbw.write("append txt...");
				// fbw.newLine();
				// fbw.close();
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static final void appendTologFile(String data)
	{
		String logFileName = KranthiConstants.getApplicationDependable()
				.getWarName();

		FileWriter fw = null;
		BufferedWriter bw = null;
		try
		{
			File logFile = new File(logFileName);

			if (!logFile.exists())
				logFile.createNewFile();

			if (data != null)
			{
				fw = new FileWriter(logFile, true);
				bw = new BufferedWriter(fw);

				bw.append(data);
			}

		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try
			{
				if (bw != null)
					bw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static int getInt(String val)
	{
		try
		{
			val = replaceComma(val);
			int v = Integer.parseInt(val);
			return v;
		}
		catch (Exception exArg)
		{
			return -1;
		}
	}
	
	public static String replaceComma(String valArg)
	{
		if (valArg == null)
			return "";

		valArg = valArg.replace(",", "");

		return valArg;
	}
	
	public static int getInt(Object val)
	{
		return getInt((String) val);
	}
}
