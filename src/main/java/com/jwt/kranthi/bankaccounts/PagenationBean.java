package com.jwt.kranthi.bankaccounts;

import com.groups.GroupsUtils;
import com.jwt.kranthi.utils.KranthiBaseBean;
import com.jwt.kranthi.utils.KranthiUtils;

public class PagenationBean extends KranthiBaseBean
{	
	private static final long serialVersionUID = 1L;

	private String current = "";

	private String next = "";

	private String total = "";
	
	private String oldTotal = "";

	private int pageNo = 0;

	private int noOfpages = 0;

	private String forward = "";

	private Pagination pagination;

	private String type = "";

	private String recordSet = "0";

	private String begin = "";

	private String end = "";

	public PaginationBean()
	{
		reset();
	}

	public void reset()
	{
		current = "0";
		next = "0";
		total = "0";
		oldTotal="0";
		pageNo = 0;
		noOfpages = 0;
		recordSet = "0";
		begin = "0";
		end = "0";
		type = "";
	}

	public String getBegin()
	{
		return begin;
	}
	
	public int getBeginInt()
	{
		int begin = KranthiUtils.getInt(this.begin);
		return begin < 0 ? 0 : begin;
	}

	public void setBegin(String begin)
	{
		this.begin = begin;
	}

	public String getEnd()
	{
		return end;
	}
	public int getEndInt()
	{
		int end = KranthiUtils.getInt(this.end);
		return end < 0 ? 0 : end;
	}

	public void setEnd(String end)
	{
		this.end = end;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getNoOfpages()
	{
		return noOfpages;
	}

	public void setNoOfpages(int noOfpages)
	{
		this.noOfpages = noOfpages;
	}

	public Pagination getPagination()
	{
		return pagination;
	}

	public void setPagination(Pagination pagination)
	{
		this.pagination = pagination;
	}

	public int getPageNo()
	{
		return pageNo;
	}

	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}

	public String getForward()
	{
		return forward;
	}

	public void setForward(String forward)
	{
		this.forward = forward;
	}

	public String getCurrent()
	{
		return current;
	}

	public void setCurrent(String current)
	{
		this.current = current;
	}

	public String getNext()
	{
		return next;
	}

	public void setNext(String next)
	{
		this.next = next;
	}

	public String getTotal()
	{
		return total;
	}

	public void setTotal(String total)
	{
		this.oldTotal = this.total;
		this.total = total;
		
	}

	public String getRecordSet()
	{
		return recordSet;
	}

	public void setRecordSet(String recordSet)
	{
		this.recordSet = recordSet;
	}

	public int getTotalRowCount()
	{
		int total = GroupsUtils.getInt(this.total);
		return total < 0 ? 0 : total;
	}

	public String getOldTotal()
	{
		return oldTotal;
	}

	public void setOldTotal(String oldTotal)
	{
		this.oldTotal = oldTotal;
	}

	
}
