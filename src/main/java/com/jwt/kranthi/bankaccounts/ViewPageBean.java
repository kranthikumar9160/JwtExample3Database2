package com.jwt.kranthi.bankaccounts;

import java.util.List;
import java.util.Vector;

public class ViewPageBean extends GroupsBaseBean
{
	int totalRowCount = 0;

	Vector vtRows;

	List rows;

	public ViewPageBean(int totalRowCount, Vector vtRows)
	{
		this.totalRowCount = totalRowCount;
		this.vtRows = vtRows;
	}

	public ViewPageBean(List vtRows)
	{
		this.rows = vtRows;
	}

	public ViewPageBean()
	{

	}

	public int getTotalRowCount()
	{
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount)
	{
		this.totalRowCount = totalRowCount;
	}

	public Vector getVtRows()
	{
		return vtRows;
	}

	public void setVtRows(Vector vtRows)
	{
		this.vtRows = vtRows;
	}

	public List getRows()
	{
		return rows;
	}

	public void setRows(List rows)
	{
		this.rows = rows;
	}

}
