package com.jwt.kranthi.bankaccounts;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.groups.api.systemevents.EventsDBAdapter;
import com.groups.spring.annotations.GroupsComponent;

import jakarta.servlet.http.HttpSession;

@GroupsComponent
public interface ApplicationDependable extends Serializable
{
	public String getAppID();

	public boolean canSOPPrint();

	public String getWarName();

	public String getLicenseAppName();

	public String getTempLocationPath();

	public String getTimeZone();

	public String getTempLocation();

	public String getConfigFileName();

	public EventsDBAdapter getEventsDBAdapter();

	public void setEventsDBAdapter(EventsDBAdapter eventsDBAdapter);

	public String getAdminMailID(Connection connectionArg) throws SQLException;

	public String getUserHttpSessionInvalidRedirectPath();

	public String getLoginPagePath(String type);

	public String getAppHomeDir();

	public String getMDRajGroupHomeDir();

	// public String getHomeDir();

	public void setMDRajGroupHomeDir(String dir);

	public String getDBBackupDir();

	public String getSponsorHomeDir();
	
	public String getSponsorJasperReportImagesDir(String sponsorID);

	public String getSponsorHomeDir(String sponsorID);

	public String getAttachmentsDir();

	public String getAttachmentsDir(String sponsorID);

	public String getDBDatasource();

	public String getDBDatabase();

	public String getDBUser();

	public String getDBPassword();

	public String getDBHost();

	public String getDBPort();

	public String getDBURL();

	public void setThreadLocalVars(HttpSession session, Map attributes)
			throws Exception;

	List<String> getSessionAttributesPersistent();
}
