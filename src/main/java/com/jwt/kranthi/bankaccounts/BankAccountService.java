package com.jwt.kranthi.bankaccounts;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;

import com.groups.GroupsUtils;
import com.groups.dao.GroupsBaseAccess;
import com.groups.ems.accounts.action.PaginationBean;
import com.groups.ems.accounts.action.ViewPageBean;
import com.groups.ems.bankaccounts.action.GroupsBean;
import com.groups.ems.bankaccounts.action.PaginationHelper;
import com.groups.ems.bankaccounts.form.ViewBankAccountForm;
import com.jwt.kranthi.utils.KranthiBaseAccess;
import com.jwt.kranthi.utils.KranthiConstants;
import com.jwt.kranthi.utils.KranthiUtils;

import jakarta.servlet.http.HttpSession;

public class BankAccountService {
	
	public static String TABLE_NAME = "TABLE_NAME";
	
	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	public  Vector getRows(HashMap hmData, HashMap dataType) throws SQLException
	{	
		Vector vt = new Vector();
		if (hmData == null)
			return null;

		String where = "";
		String distinct = "";
		String orderBy = "";
		if (hmData.containsKey("distinct"))
		{
			hmData.remove("distinct");
			distinct = " distinct ";
		}

		if (hmData.containsKey("orderby"))
		{
			orderBy = (String) hmData.remove("orderby");
		}
		if (hmData.containsKey("where"))
		{
			where = (String) hmData.remove("where");
			where = " where  " + where;
		}

		String table = (String) hmData.remove(TABLE_NAME);

		if (hmData.size() == 0)
		{
			return vt;
		}

		Set s = hmData.keySet();

		String cols = s.toString();

		vt.addAll(s);

		cols = removeFirst(cols);

		String query = "select " + distinct + " " + cols + " from " + table
				+ where + " " + orderBy;

//		GroupsBaseAccess.executeQuery(query, conArg, vt, dataType);
		
		ResultSet rs = bankAccountRepository.executeQuery(query);
		
		KranthiBaseAccess.executeQuery(query, vt, dataType, rs);
		
		return vt;
	}
	
	protected String getQueryString(ViewBankAccountForm viewBankAccountForm)
	{
		StringBuffer where = new StringBuffer();

		String accNo = viewBankAccountForm.getSearchAccountNo().trim();
		String bnkName = viewBankAccountForm.getSearchBankName().trim();
		String bnkBranch = viewBankAccountForm.getSearchBankBranch().trim();
		String accPurpose = viewBankAccountForm.getSearchAccPurpose().trim();
		String schoolSeq = viewBankAccountForm.getSearchSchoolSeq().trim();

		HashMap hmDta = new HashMap();

		hmDta.put("ac_no", accNo);
		hmDta.put("bank_name", bnkName);
		hmDta.put("branch", bnkBranch);
		hmDta.put("acc_purpose", accPurpose);
//		hmDta.put("school_seq@=", schoolSeq);

		where.append(KranthiUtils.getWhere("", hmDta));

		if (where.toString().length() == 0)
		{
			where.append("1=1");
		}

//		where.append(" order by ac_no desc ");
		
//		boolean numberSort = isNumberSort(session,
//				viewBankAccountForm.getSortColumn());

//		if (numberSort)
//		{
//			where.append(" order by "
//					+ KranthiBaseAccess.numberSort(viewBankAccountForm
//							.getSortColumn()) + " "
//					+ viewBankAccountForm.getSortMode() + " ");
//		}
//		else
//		{
			where.append(" order by " + viewBankAccountForm.getSortColumn() + " "
					+ viewBankAccountForm.getSortMode() + " ");
//		}
		
		

		return where.toString();
	}
	
	
	protected void setViewRows(ViewBankAccountForm formArg,	boolean searchArg) throws SQLException
	{
		Vector vtRows = new Vector();
		String where = getQueryString(formArg);

		PagenationBean pbean = formArg.getPaginationBean();

		vtRows = getViewRows(where, pbean);

		String total = pbean.getTotal(); // (String)
											// session.getAttribute(GroupsConstants.TOTAL_ROWS_COUNT);

		int ttl = KranthiUtils.getInt(total);
		if (ttl > 0)
		{
			if ("update".equalsIgnoreCase(pbean.getType()))
			{
				PaginationHelper ph = new PaginationHelper(session);
				ph.updateRecordSet(pbean, connectionArg);
			}
			
			if(pbean.isRowsUpdated())
				vtRows = getViewRows(session, where, pbean, connectionArg); 
		}

		if (vtRows.size() > 0)
		{
			if ("update".equalsIgnoreCase(pbean.getType()))
			{
				PaginationHelper ph = new PaginationHelper(session);
				ph.updateRecordSet(pbean, connectionArg);
			}

			if (searchArg)
			{
				GroupsBean bean = (GroupsBean) vtRows.get(0);

				String prop1 = bean.getStrId();

				formArg.setSelectedId(prop1);
			}
			else
			{
				String prop1 = formArg.getSelectedId();
				prop1 = getSelectedIdVal(vtRows, prop1, -1);
				formArg.setSelectedId(prop1);
			}
		}
		else
		{
			formArg.setSelectedId("");
			pbean.reset();
		}

		formArg.setPaginationBean(pbean);

		session.setAttribute("PaginationBean", pbean);
		session.setAttribute("vtList", vtRows);
	}
	
	public Vector getViewRows(String where,
			PagenationBean pbean) throws SQLException
	{
		
		HashMap svb = new HashMap();
		int offset;
		int end;
		offset = pbean.getBeginInt();
		end = pbean.getEndInt();

//		svb.put(GroupsBaseAccess.TABLE_NAME, finTable);
		svb.put(KranthiBaseAccess.TABLE_NAME, getTable());
		svb.put("where", where);
		svb.put("account_seq", "");
		svb.put("financial_year_seq", "");
		svb.put("fin_year_seq", "");
		svb.put("financial_year", "");
		svb.put("opening_bal", "");
		svb.put("closing_bal", "");
		svb.put("school_seq", "");
		svb.put("school_id", "");
		svb.put("fin_disp_order", "");
		svb.put("status_cd", "");
		svb.put("status_name", "");

		ViewPageBean viewPageBean = KranthiBaseAccess.getViewRows(svb,
				String.valueOf(offset), String.valueOf(end));

		pbean.setTotal(viewPageBean.getTotalRowCount() + "");
		session.setAttribute(KranthiConstants.TOTAL_ROWS_COUNT,//
				viewPageBean.getTotalRowCount() + "");//
		Vector vt = viewPageBean.getVtRows();

//		svb = new HashMap();
//
//		svb.put("account_seq", "-1");
//		svb.put("financial_year", "1");
//		svb.put("opening_bal", "2");
//		svb.put("closing_bal", "3");
//		svb.put("school_id", "4");
//		svb.put("status_name", "5");

//		vt = getViewRows(vt, svb);
		
		vt = getRows(svb);

		return vt;

	}
	
	public static ViewPageBean getViewRows(HashMap columns, String offset,
			String end, Connection connectionArg) throws SQLException
	{
		String query = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector vt = new Vector();
		int noOfRecords = 0;
		ViewPageBean viewPageBean = new ViewPageBean(noOfRecords, vt);
		if (columns == null) return null;

		String where = "";
		String distinct = "";
		String orderBy = "";
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

		//vt.addAll(s);

		cols = removeFirst(cols);

		query = "select " + distinct + " " + cols + " from " + table + where
				+ " " + orderBy;

		query = " select SQL_CALC_FOUND_ROWS * from (" + query + ")a limit "
				+ offset + ", " + end;

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
					
					String dataType = GroupsUtils.nullToEmpty(columns
							.get(col));
					
					setResultSetVal(col,dataType,mp,rs);
				}
				vt.add(mp);
			}
			rs.close();

			rs = stmt.executeQuery("SELECT FOUND_ROWS() total ");
			if (rs.next()) noOfRecords = rs.getInt("total");

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
				if (stmt != null) stmt.close();

			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return viewPageBean;
	}
	
	public Vector getRows(HashMap hmData)
			throws SQLException
	{
		HashMap dataType = new HashMap();

		if (hmData != null && hmData.size() > 0)
		{
			Iterator itr = hmData.keySet().iterator();

			while (itr.hasNext())
			{
				String key = (String) itr.next();
				if (!TABLE_NAME.equalsIgnoreCase(key))
				{
					String val = (String) hmData.get(key);
					if (val != null && val.trim().length() > 0)
						dataType.put(key, val);
				}
			}
		}
		return getRows(hmData, dataType);
	}
	
	
	
	public String getTable() 
	{
//		UserSchoolLimits usl = new UserSchoolLimits(session);
//		String query = usl.getUserLimitQuery();

		String bnkAccTable = "select bank_account_seq, ac_no, bank_name, branch, acc_purpose from bank_accounts";

		return bnkAccTable;
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
