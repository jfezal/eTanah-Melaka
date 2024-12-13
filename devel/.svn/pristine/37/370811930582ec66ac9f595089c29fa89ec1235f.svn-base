<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page 	import="java.sql.*"
			import="com.google.inject.*" 
			import="etanah.view.*" 
			import="org.hibernate.*"
			import="org.hibernate.impl.*"
			import="org.hibernate.cfg.*"    
			%>

<%
	StringBuilder html = new StringBuilder("<table width='600' border='1' cellspacing='0' cellpadding='0' bordercolor='#006699' bordercolordark='#006699' bordercolorlight='#006699' style='border-collapse:collapse'>");
	String tableName = request.getParameter("t");
	if (tableName == null || tableName.length() < 0){
		return;
	}
	String pkName = null;
	Connection c = null;
	Statement stmt = null;
	ResultSet rs = null;
	try{
		Injector injector = etanahContextListener.getInjector();
		com.google.inject.Provider<Session> sessionProvider = injector.getProvider(Session.class);
		
		Settings settings = ((SessionFactoryImpl) sessionProvider.get().getSessionFactory()).getSettings();
		
		c = settings.getConnectionProvider().getConnection();
		
		// get the PK of the table
		DatabaseMetaData dbMeta = c.getMetaData();
		System.out.println(c.getMetaData().getURL());
		rs = dbMeta.getPrimaryKeys(null, null, tableName);
		if (rs.next()){
			// only one PK is supported
			pkName = rs.getString("COLUMN_NAME");
			rs.close();
			
			stmt = c.createStatement();
			rs = stmt.executeQuery("select * from " + tableName + " order by " + pkName);
			ResultSetMetaData rsMeta = rs.getMetaData();
			int colSize = 0;
			// control traversing of RS to always get the PK first
			int [] traverseOrder = null;
			while (rs.next()){
				if (traverseOrder == null){
					colSize = rsMeta.getColumnCount();
					traverseOrder = new int[colSize];
					int j = 1;
					boolean foundPK = false;
					html.append("<tr>");
					for (int i = 1; i <= colSize; i++){
						String name = rsMeta.getColumnName(i);
						if (!foundPK && pkName.equals(name)){
							traverseOrder[0] = i;
							foundPK = true;
						} else{
							traverseOrder[j++] = i;
						}
						html.append("<td height='20' align='center' bgcolor='#2891CD' class='roll'>").append(name).append("</td>");
					}
				}
				html.append("</tr><tr>");
				String pkVal = rs.getString(traverseOrder[0]);
				html.append("<td class='selectdata' height='20' width='80' align='center'><a href=\"javascript:editVal('").append(tableName).append("',")
						.append(pkVal).append(");\">").append(pkVal)
						.append("</a></td>");
				for (int i = 1; i < colSize; i++){
					String val = rs.getString(traverseOrder[i]);
					html.append("<td class='selectdata' align='left'>&nbsp;").append(val == null? "&nbsp;": val)
							.append("</td>");
				}
				html.append("</tr>");
			}
			html.append("</table>");
		} else{
			html.append("<tr><td height='20' align='center' bgcolor='#2891CD' class='roll'>Fail tidak wujud atau tidak sah!</td></tr></table>");
		}
		
	} catch (SQLException e){
		e.printStackTrace();
		RuntimeException re = new RuntimeException(e.getMessage());
		re.initCause(e);
		throw re;
	} finally{
		// TODO close resources
	}
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rujukan : <%= tableName %></title>
</head>
<body>
<link href="<%=request.getContextPath()%>/styles/wsms.css" rel="stylesheet" type="text/css">
<script language="javascript">

function editVal(table, pk){
	var win = window.open('ref-column.jsp?skin=alt&t=' + table + '&id=' + pk, 'editValWdw',
			'height=200,width=450,location=no,menubar=no,resizable=yes,scrollbars=yes,toobar=no');
}

</script>
<table width="740" border="0" cellspacing="0" cellpadding="0" bgcolor="#E8F0F6">
<tr>
  <td valign="top" height="400">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>	
     <td height="20" class="header" colspan="3" align="left">&nbsp;&nbsp;Rujukan : <%= tableName %></td>
  </tr>
  <tr><td colspan="3" height="10"></td></tr>
<tr><td width="20"></td>
<td colspan="2" align="left" valign="top" class="normal"><a href="javascript:editVal('<%= tableName %>',0);">Tambah</a></td>
</tr>
<tr><td colspan="3" height="10"></td></tr>
<tr>
<td></td>
<td colspan="2" align="left"><%= html.toString() %></td>
</tr>
<tr><td colspan="3" height="10"></td></tr>
</table>
</td></tr>
</table>
</body>
</html>