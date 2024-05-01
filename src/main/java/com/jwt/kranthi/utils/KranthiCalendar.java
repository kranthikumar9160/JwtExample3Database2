package com.jwt.kranthi.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;
import org.springframework.format.annotation.DateTimeFormat;

import com.groups.GroupsConstants;
import com.groups.GroupsCustomeException;
import com.groups.GroupsUtils;

public class KranthiCalendar
{
	public static int SEC = 1000;

	public static int MIN = 60 * SEC;

	public static int HOUR = 60 * MIN;

	public static DateFormat attachmentDateTimeFormat = new SimpleDateFormat(
			"yyyyMMddhhmmss");

	public static DateFormat attachmentDateTimeMillFormat = new SimpleDateFormat(
			"yyyyMMddhhmmssSSS");

	public static GregorianCalendar getGregorianCalendar()
	{
		GregorianCalendar gc = null;

		TimeZone tz = TimeZone.getTimeZone(GroupsConstants
				.getApplicationDependable().getTimeZone());
		gc = new GregorianCalendar(tz);

		return gc;
	}

	public static GregorianCalendar trimTime(GregorianCalendar gc)
	{
		GregorianCalendar gc1 = gc;

		if (gc == null)
			return null;

		gc1 = new GregorianCalendar(gc.get(Calendar.YEAR),
				gc.get(Calendar.MONTH), gc.get(Calendar.DATE), 0, 0, 0);

		return gc1;
	}

	public static GregorianCalendar getMonthDate()
	{
		return getMonthDate(new GregorianCalendar());
	}

	public static GregorianCalendar getMonthDate(GregorianCalendar gcArg)
	{
		return GroupsUtils.getMonthMinDate(gcArg);
	}

	public static GregorianCalendar getMonthEndDate()
	{
		return getMonthEndDate(new GregorianCalendar());
	}

	public static GregorianCalendar getMonthEndDate(GregorianCalendar gcArg)
	{
		return GroupsUtils.getMonthMaxDate(gcArg);
	}

	public static GregorianCalendar setDate(GregorianCalendar gc)
	{
		return setDate(1, gc);
	}

	public static GregorianCalendar setDate(int dt, GregorianCalendar gc)
	{
		GregorianCalendar gc1 = gc;

		if (gc == null)
			return null;

		gc1 = (GregorianCalendar) gc1.clone();
		gc1.set(Calendar.DATE, dt);

		return gc1;
	}

	public static String toString(GregorianCalendar gc)
	{
		if (gc == null)
			return null;

		String dt = GroupsUtils.getDate(gc);

		return dt;
	}

	public static String toStringTime(GregorianCalendar gc)
	{
		if (gc == null)
			return null;

		String dt = GroupsUtils.getDateTime(gc);

		return dt;
	}

	public static List<GregorianCalendar> getMonths(boolean dateReset,
			GregorianCalendar startArg, GregorianCalendar endArg)
	{
		ArrayList arrayList = new ArrayList();

		if (startArg == null && endArg == null)
			return arrayList;

		GregorianCalendar start = startArg;
		GregorianCalendar end = endArg;

		if (start != null)
		{
			start = (GregorianCalendar) start.clone();
			start = trimTime(start);

			if (dateReset)
				start = setDate(start);
		}

		if (end != null)
		{
			end = (GregorianCalendar) end.clone();
			end = trimTime(end);

			if (dateReset)
				end = setDate(end);
		}

		if (start == null)
		{
			arrayList.add(end);

			return arrayList;
		}

		if (end == null)
		{
			arrayList.add(start);
			return arrayList;
		}

		if (start.after(end))
		{
			GregorianCalendar tmp = null;
			tmp = start;
			start = end;
			end = tmp;
		}

		GregorianCalendar gc = (GregorianCalendar) start.clone();
		arrayList.add(gc);

		while (start.before(end))
		{
			start.add(Calendar.MONTH, 1);
			gc = (GregorianCalendar) start.clone();
			arrayList.add(gc);
		}

		return arrayList;
	}

	public static List<GregorianCalendar> getMonths(GregorianCalendar startArg,
			GregorianCalendar endArg)
	{
		return getMonths(true, startArg, endArg);
	}

	public static String getDate()
	{
		return getDate(new GregorianCalendar(), GroupsConstants.DATE_FORMAT);
	}

	public static String getDate(Calendar calendar)
	{

		return getDate(calendar, GroupsConstants.DATE_FORMAT);

	}

	public static String getMonthDateString()
	{
		return getDate(new GregorianCalendar(),
				GroupsConstants.MONTH_DATE_FORMAT);
	}

	public static String getMonthDateString(Calendar calendar)
	{

		return getDate(calendar, GroupsConstants.MONTH_DATE_FORMAT);

	}

	public static String getDate(Calendar calendar, String formateArg)
	{
		String date = null;
		SimpleDateFormat smpleDateFormate = null;

		if (calendar == null)
			return "";
		try
		{
			smpleDateFormate = new SimpleDateFormat(formateArg);

			date = smpleDateFormate.format(calendar.getTime());
		}
		catch (Exception e)
		{
			date = "";
			e.printStackTrace();
		}
		return date;

	}

	public static String getDate(Date calendar, String formateArg)
	{
		String date = null;
		SimpleDateFormat smpleDateFormate = null;

		if (calendar == null)
			return "";

		try
		{
			smpleDateFormate = new SimpleDateFormat(formateArg);

			date = smpleDateFormate.format(calendar);
		}
		catch (Exception e)
		{
			date = "";
			e.printStackTrace();
		}
		return date;

	}

	public static String getDate(Date createDate)
	{
		return getDate(createDate, GroupsConstants.DATE_FORMAT);
	}

	public static String getDateTime(Date createDate)
	{
		return getDate(createDate, GroupsConstants.DATE_FORMAT_WITH_TIME);
	}

	public static String getAttachementTimeStamp()
	{
		return getAttachementTimeStamp(attachmentDateTimeFormat);
	}

	public static String getAttachementTimeMillisStamp()
	{
		return getAttachementTimeStamp(attachmentDateTimeMillFormat);
	}

	public static String getAttachementTimeStamp(DateFormat format)
	{
		String date = null;

		GregorianCalendar calendar = getGregorianCalendar();
		try
		{
			date = format.format(calendar.getTime());
		}
		catch (Exception e)
		{
			date = "";
			e.printStackTrace();
		}
		return date;

	}

	public static String getDateTime(Calendar calendar)
	{
		String date;
		date = getDate(calendar, GroupsConstants.DATE_FORMAT_WITH_TIME);
		return date;
	}

	public static GregorianCalendar getCalendarWithoutTime()
	{
		String date = getDate();
		return getCalendar(date);
	}

	public static GregorianCalendar getCalendarWithTime()
	{
		return new GregorianCalendar();
	}

	public static GregorianCalendar getCalendar(String calendar)
	{
		if (calendar == null || calendar.equals(""))
			return null;
		GregorianCalendar date = null;

		date = getCalendar(calendar, GroupsConstants.DATE_FORMAT);

		return date;
	}

	public static GregorianCalendar getCalendarWithTime(String calendar)
	{
		if (calendar == null || calendar.equals(""))
			return null;
		GregorianCalendar date = null;

		date = getCalendar(calendar, GroupsConstants.DATE_FORMAT_WITH_TIME);

		return date;
	}

	public static GregorianCalendar getCalendar(String calendar,
			String formateArg)
	{
		if (calendar == null || calendar.equals(""))
			return null;

		GregorianCalendar date = null;
		SimpleDateFormat smpleDateFormate = null;

		try
		{
			smpleDateFormate = new SimpleDateFormat(formateArg);

			date = new GregorianCalendar();
			date.setTime(smpleDateFormate.parse(calendar));
		}
		catch (Exception e)
		{
			date = null;
			e.printStackTrace();
		}

		return date;

	}

	public static String getStrDate(String calendar, boolean db)
	{
		String strDate = "";
		String formate = db ? "yyyy-mm-dd" : GroupsConstants.DATE_FORMAT;
		strDate = getStrDate(calendar, formate);
		return strDate;
	}

	public static String getStrDate(String calendar, String formteArg)
	{
		GregorianCalendar date = new GregorianCalendar();
		String strDate = "";
		SimpleDateFormat smpleDateFormate = null;
		// 2010-09-29
		smpleDateFormate = new SimpleDateFormat(formteArg);
		if (calendar == null)
			return "";
		try
		{
			date.setTime(smpleDateFormate.parse(calendar));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		strDate = getDate(date);
		return strDate;

	}

	public static long getCurrentTimeInMillis()
	{
		GregorianCalendar today = getGregorianCalendar();

		today = trimTime(today);

		GregorianCalendar curr = getGregorianCalendar();

		long currTime = curr.getTimeInMillis() - today.getTimeInMillis();

		return currTime;
	}

	public static void main(String[] args)
	{
		System.out.println("\n " + getDate(getMonthDate()));
	}

	public static GregorianCalendar validateDate(String dt, String errorKey)
			throws GroupsCustomeException
	{
		GregorianCalendar date = null;

		if (dt == null || dt.trim().length() == 0)
			return null;

		date = GroupsUtils.getCalendar(dt.trim());

		if (date == null)
		{
			throw new GroupsCustomeException(errorKey);
		}
		return date;
	}

	public static GregorianCalendar validateDateWithTime(String dt,
			String errorKey) throws GroupsCustomeException
	{
		GregorianCalendar date = null;

		if (dt == null || dt.trim().length() == 0)
			return null;

		date = getCalendarWithTime(dt.trim());

		if (date == null)
		{
			throw new GroupsCustomeException(errorKey);
		}

		return date;
	}

	public static GregorianCalendar validateMonthDate(String dt, String errorKey)
			throws GroupsCustomeException
	{
		GregorianCalendar date = null;

		if (dt == null || dt.trim().length() == 0)
			return null;

		date = GroupsUtils.getCalendar(dt.trim(),
				GroupsConstants.MONTH_DATE_FORMAT);

		if (date == null)
		{
			throw new GroupsCustomeException(errorKey);
		}

		return date;
	}

	public static int getDiffDay(String fromDate, String toDate)
	{
		DateTimeFormatter dateStringFormat = DateTimeFormat
				.forPattern("dd-MMM-yyyy");
		DateTime firstTime = dateStringFormat.parseDateTime(fromDate);
		DateTime secondTime = dateStringFormat.parseDateTime(toDate);
		int days = Days.daysBetween(new LocalDate(firstTime),
				new LocalDate(secondTime)).getDays();

		// System.out.println("Days between the two dates " + days);

		return days;
	}

	public static int getDiffMonths(String fromDate, String toDate)
	{
		DateTimeFormatter dateStringFormat = DateTimeFormat
				.forPattern("dd-MMM-yyyy");
		DateTime firstTime = dateStringFormat.parseDateTime(fromDate);
		DateTime secondTime = dateStringFormat.parseDateTime(toDate);

		int months = Months.monthsBetween(new LocalDate(firstTime),
				new LocalDate(secondTime)).getMonths();

		return months;
	}

	public static String getDiffYearAndMonths(String fromDate, String toDate)
	{
		DateTimeFormatter dateStringFormat = DateTimeFormat
				.forPattern("dd-MMM-yyyy");
		DateTime firstTime = dateStringFormat.parseDateTime(fromDate);
		DateTime secondTime = dateStringFormat.parseDateTime(toDate);
		int month = Months.monthsBetween(new LocalDate(firstTime),
				new LocalDate(secondTime)).getMonths();
		// System.out.println("Months between the two dates " + month);

		int year = Years.yearsBetween(new LocalDate(firstTime),
				new LocalDate(secondTime)).getYears();
		// System.out.println("Years between the two dates " + year);

		StringBuffer sb = new StringBuffer();

		boolean yearadd = false;
		if (year > 0)
		{
			int mod = month / 12;

			mod = mod == 0 ? 1 : mod;

			month = month - mod * 12;

			yearadd = true;
			if (year == 1)
			{
				sb.append(year + " Year");
			}
			else
			{
				sb.append(year + " Years");
			}
		}
		String tmp = "";
		if (yearadd)
		{
			tmp = " and";
		}

		if (month > 0)
		{
			if (month == 1)
			{
				sb.append(tmp + " " + month + " Month ");
			}
			else
			{
				sb.append(tmp + " " + month + " Months ");
			}
		}
		return sb.toString().trim();
	}

	public static LocalDate getJodaDate()
	{
		return getJodaLocalDate(new GregorianCalendar());
	}

	public static DateTime getJodaDate(GregorianCalendar gc)
	{
		DateTime date = new DateTime(gc);

		return date;
	}

	public static LocalDate getJodaLocalDate()
	{
		return getJodaLocalDate(new GregorianCalendar());
	}

	public static LocalDate getJodaLocalDate(GregorianCalendar gc)
	{
		LocalDate date = new LocalDate(gc);

		return date;
	}

	public static GregorianCalendar addDays(GregorianCalendar gc, int days)
	{
		GregorianCalendar gc1 = (GregorianCalendar) gc.clone();

		gc1.add(Calendar.DATE, days);

		return gc1;
	}
}
