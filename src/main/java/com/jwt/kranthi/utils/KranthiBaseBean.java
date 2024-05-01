package com.jwt.kranthi.utils;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class KranthiBaseBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	protected String createUser;

	protected String updateUser;

	protected GregorianCalendar createDate;

	protected GregorianCalendar updateDate;

	public final GregorianCalendar getCreateDate()
	{
		return createDate;
	}

	public final void setCreateDate(GregorianCalendar createDate)
	{
		this.createDate = createDate;
	}

	public final String getCreateUser()
	{
		return createUser;
	}

	public final void setCreateUser(String createUser)
	{
		this.createUser = createUser;
	}

	public final GregorianCalendar getUpdateDate()
	{
		return updateDate;
	}

	public final void setUpdateDate(GregorianCalendar updateDate)
	{
		this.updateDate = updateDate;
	}

	public final String getUpdateDate(GregorianCalendar date)
	{
		if (date == null)
			return "";

		String val = KranthiBaseAccess.getDbDateTime(date);
		return val;
	}

	public final String getCreateDate(GregorianCalendar date)
	{
		if (date == null)
			return "";

		String val = GroupsBaseAccess.getDbDateTime(date);
		return val;
	}

	public final String getUpdateUser()
	{
		return updateUser;
	}

	public final void setUpdateUser(String updateUser)
	{
		this.updateUser = updateUser;
	}

}
