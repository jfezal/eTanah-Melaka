<%@ page import="java.util.TimeZone" %>

<%
	TimeZone tz = TimeZone.getDefault();
	out.println("the default timezone is " + tz.getDisplayName(true, TimeZone.LONG));
	out.println("the default timezone ID is " + tz.getID());
	out.println("useDaylightTime = " + tz.useDaylightTime());
        out.println("Date = " + new java.util.Date());

%>
<br/>
<%
	TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
	tz = TimeZone.getDefault();
	out.println("the default timezone is " + tz.getDisplayName(true, TimeZone.LONG));
	out.println("the default timezone ID is " + tz.getID());
	out.println("useDaylightTime = " + tz.useDaylightTime());
        out.println("Date = " + new java.util.Date());
%>

