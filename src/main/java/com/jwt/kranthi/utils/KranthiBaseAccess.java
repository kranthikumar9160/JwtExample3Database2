package com.jwt.kranthi.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import com.jwt.kranthi.bankaccounts.ViewPageBean;

public class KranthiBaseAccess {
	
	public static DateFormat dbDateFormat = new SimpleDateFormat(
			KranthiConstants.DB_DATE_FORMAT);
	
	public static String TABLE_NAME = "TABLE_NAME";
	
	public static String DEBUG = "DEBUG";
	
	public static DateFormat dbDateTimeFormat = new SimpleDateFormat(
			KranthiConstants.DB_DATE_TIME_FORMAT);
	
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
	
	public static final void executeQuery(String whereArg, Vector inputArg, HashMap dataTypes, ResultSet rs)
			throws SQLException
	{
		executeQuery(whereArg, inputArg, dataTypes, rs, false);
	}

	public static final void executeQuery(String whereArg, Vector inputArg, HashMap dataTypes, ResultSet rs,
			boolean firstRowArg) throws SQLException
	{
		Statement st = null;
//		ResultSet rs = null;
		String query;
		query = whereArg;

		String debug = KranthiUtils.nullToEmpty(dataTypes.remove(DEBUG));

		if (!debug.equalsIgnoreCase("N"))
			System.out.println("" + query);

		HashMap hmData = null;
		Vector localVtRows = new Vector();

		try
		{
//			st = connectionArg.createStatement();
//			rs = st.executeQuery(query);

			if (inputArg.size() == 0)
				return;
			String val = null;
			while (rs.next())
			{
				hmData = new HashMap();
				for (int i = 0; i < inputArg.size(); i++)
				{
					String col = (String) inputArg.get(i);
					String dataType = KranthiUtils.nullToEmpty(dataTypes
							.get(col));
					val = rs.getString(col);

					setResultSetVal(col, dataType, hmData, rs);

				}
				if (inputArg.size() == 1)
					localVtRows.add(val);
				else
					localVtRows.add(hmData);

				if (firstRowArg)
					return;
			}
			inputArg.clear();
			inputArg.addAll(localVtRows);
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
		}

	}
	
	private static void setResultSetVal(String col, String dataType,
			HashMap hmData, ResultSet rs) throws SQLException
	{
		String col1 = col;

		col1 = getColumn(col1);

		String val = rs.getString(col1);

		if (dataType.toLowerCase().startsWith("date"))
		{
			updateDateVal(col1, dataType, hmData, rs);
		}
		else if (dataType.equalsIgnoreCase("blob"))
		{
			Blob blob = rs.getBlob(col1);
			if (blob != null)
			{
				InputStream ip = blob.getBinaryStream();
				byte[] bt = getBytes(ip);
				hmData.put(col1, bt);
			}
		}
		else
		{
			hmData.put(col1, val);
		}

	}
	
	private static void updateDateVal(String col, String dataType, HashMap mp,
			ResultSet rs) throws SQLException
	{
		String val = "";
		int in = dataType.indexOf("@");
		String strOpt = "";
		if (in != -1)
		{
			dataType = dataType.substring(in + 1);
		}

		in = dataType.indexOf("@");

		if (in != -1)
		{
			strOpt = dataType.substring(in + 1);
			dataType = dataType.substring(0, in);
		}

		GregorianCalendar gc1 = null;
		GregorianCalendar gctime = null;
		java.sql.Date gc = rs.getDate(col);
		if (rs.getTimestamp(col) != null)
		{
			gctime = new GregorianCalendar();
			gctime.setTime(rs.getTimestamp(col));
		}

		val = "";
		if (gc != null)
		{
			gc1 = new GregorianCalendar();
			Date d = new Date();
			d.setTime(gc.getTime());
			gc1.setTime(d);
			if (dataType.equalsIgnoreCase("string"))
			{
				if (strOpt.equals("datetime"))
				{
					val = KranthiUtils.getDateTime(gctime);
				}
				else
				{
					val = KranthiUtils.getDate(gc1);
				}
				mp.put(col, val);
			}
			else if (dataType.equalsIgnoreCase("month"))
			{
				val = KranthiUtils.getDate(gc1,
						KranthiConstants.MONTH_DATE_FORMAT);

				mp.put(col, val);
			}
			else if (dataType.equalsIgnoreCase("date"))
			{
				mp.put(col, gc1);
			}
		}
		else
		{
			if (dataType.equalsIgnoreCase("string"))
			{
				mp.put(col, val);
			}
			else
			{
				mp.put(col, gc1);
			}
		}

	}
	
	public static byte[] getBytes(InputStream ip)
	{
		byte[] bt = null;

		if (ip != null)
		{
			BufferedInputStream bis = null;

			try
			{
				bis = new BufferedInputStream(ip);
				bt = new byte[bis.available()];

				bis.read(bt);
			}
			catch (IOException e)
			{
				KranthiUtils.logException(e);
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if (bis != null)
						bis.close();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}

		}

		return bt;
	}
	
	private static String getColumn(String col)
	{
		if (col == null)
			return "";

		col = col.trim();
		if (col.indexOf(' ') != -1)
		{
			col = col.substring(col.lastIndexOf(' '));
		}
		if (col.indexOf('.') != -1)
		{
			col = col.substring(col.lastIndexOf('.') + 1);
		}
		col = col.trim();
		return col;
	}
	
	public static final String getDbDateTime(GregorianCalendar calArg)
	{
		String val = "";

		if (calArg == null)
			return "";

		val = dbDateTimeFormat.format(calArg.getTime());

		return val;
	}
	
	public static ViewPageBean getViewRows(HashMap columns, String offset,
			String end) throws SQLException
	{
		String query = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector vt = new Vector();
		int noOfRecords = 0;
		ViewPageBean viewPageBean = new ViewPageBean(noOfRecords, vt);
		if (columns == null)
			return null;

		String where = "";
		String distinct = "";
		String orderBy = "";

		query = getViewPageQuery(columns, offset, end);
		//
		// if (columns.containsKey("distinct"))
		// {
		// columns.remove("distinct");
		// distinct = " distinct ";
		// }
		//
		// if (columns.containsKey("orderby"))
		// {
		// orderBy = (String) columns.remove("orderby");
		// }
		// if (columns.containsKey("where"))
		// {
		// where = (String) columns.remove("where");
		// where = " where  " + where;
		// }
		//
		// String table = (String) columns.remove(TABLE_NAME);
		//
		// if (columns.size() == 0)
		// {
		// return null;
		// }
		//
		// String cols = s.toString();
		//
		// // vt.addAll(s);
		//
		// cols = removeFirst(cols);
		//
		// query = "select " + distinct + " " + cols + " from " + table + where
		// + " " + orderBy;
		//
		// query = " select SQL_CALC_FOUND_ROWS * from (" + query + ")a limit "
		// + offset + ", " + end;

		Set s = columns.keySet();

		Object obj[] = s.toArray();

		try
		{
			System.out.println(" " + query);
			stmt = connectionArg.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next())
			{
				HashMap mp = new HashMap();
				for (int i = 0; i < obj.length; i++)
				{
					String col = (String) obj[i];

					String dataType = KranthiUtils.nullToEmpty(columns.get(col));

					setResultSetVal(col, dataType, mp, rs);
				}
				vt.add(mp);
			}
			rs.close();

			rs = stmt.executeQuery("SELECT FOUND_ROWS() total ");
			if (rs.next())
				noOfRecords = rs.getInt("total");

			viewPageBean.setTotalRowCount(noOfRecords);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw e;
		}
		finally
		{
			try
			{
				if (stmt != null)
					stmt.close();

			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return viewPageBean;
	}
	
	private static String getViewPageQuery(HashMap columns, String offset,
			String end)
	{
		String where = "";
		String distinct = "";
		String orderBy = "";
		String query = "";

		if (columns.containsKey("distinct"))
		{
			columns.remove("distinct");
			distinct = " distinct ";
		}

		if (columns.containsKey("orderby"))
		{
			orderBy = (String) columns.remove("orderby");
		}
		if (columns.containsKey("where"))
		{
			where = (String) columns.remove("where");
			where = " where  " + where;
		}

		String table = (String) columns.remove(TABLE_NAME);

		if (columns.size() == 0)
		{
			return null;
		}

		Set s = columns.keySet();

		String cols = s.toString();

		// vt.addAll(s);

		cols = removeFirst(cols);

		query = "select " + distinct + " " + cols + " from " + table + where
				+ " " + orderBy;

		// this is condition is for to get the query for export rows where
		// offset and end values should not considered.
		if (offset != null && end != null)
			query = " select SQL_CALC_FOUND_ROWS * from (" + query
					+ ")a limit " + offset + ", " + end;

		return query;
	}
	
	public static String removeFirst(String strDataArg)
	{
		String strData = strDataArg;
		if (strData == null || strData.length() == 0)
		{
			return "";
		}
		strData = strData.trim();
		strData = strData.substring(1);
		strData = strData.substring(0, strData.length() - 1);

		return strData;
	}
}
