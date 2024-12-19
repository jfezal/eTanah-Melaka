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
	StringBuilder html = new StringBuilder("<table width='450' border='0' cellspacing='0' cellpadding='0' bgcolor='#E8F0F6'>");
	String tableName = request.getParameter("t");
	if (tableName == null || tableName.length() < 0){
		return;
	}
	html.append("<tr><td height='20' width='150' align='right' class='normal'>Fail</td><td width='10' align='center' class='normal'>:</td><td align='left' class='normal'><b>").append(tableName).append("</b></td></tr>");
	String id = request.getParameter("id");
	if (id == null || id.length() < 1) id = "0";
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
		rs = dbMeta.getPrimaryKeys(null, null, tableName);
		rs.next();
		// only one PK is supported
		pkName = rs.getString("COLUMN_NAME");
		rs.close();
				
		stmt = c.createStatement();
		String query = "select * from " + tableName;
		if (!"0".equals(id)) 
			query += " where " + pkName + "='" + id + "'";
		rs = stmt.executeQuery(query);
		ResultSetMetaData rsMeta = rs.getMetaData();
		int colSize = rsMeta.getColumnCount();
		// control traversing of RS to always get the PK first
		String [] traverseOrder = null;
		if (rs.next()){
			if (traverseOrder == null){
				traverseOrder = new String[colSize];
				int j = 1;
				boolean foundPK = false;
				for (int i = 1; i <= colSize; i++){
					String name = rsMeta.getColumnName(i);
					if (!foundPK && pkName.equals(name)){
						traverseOrder[0] = name;
						foundPK = true;
					} else{
						traverseOrder[j++] = name;
					}
				}
			}
			html.append("<tr>");
			//String pkVal = rs.getString(traverseOrder[0]);
			html.append("<td height='20' width='150' align='right' class='normal'>")
					.append(traverseOrder[0])
					.append("</td><td width='10' align='center' class='normal'>:</td><td align='left' class='normal'><b>");
			if ("0".equals(id)){
				html.append("<input type=\"text\" class='selectdata' size='30' maxlength='25' name=\"").append(pkName)
						.append("\">");
			} else{
				html.append("<input type=\"hidden\" class='normal' name=\"").append(pkName)
						.append("\"").append(" value=\"").append(id).append("\">")
						.append(id);
			}
			html.append("</b></td></tr>");
			for (int i = 1; i < colSize; i++){
				String val = rs.getString(traverseOrder[i]);
				html.append("<tr><td height='20' width='150' align='right' class='normal'>")
						.append(traverseOrder[i])
						.append("</td><td width='10' align='center' class='normal'>:</td><td align='left'><input size=\"50\" maxlength='45' class='selectdata' name=\"")
						.append(traverseOrder[i])
						.append("\" type=\"text\" value=\"")
						.append("0".equals(id)? "" : (val == null? "": val))
						.append("\"></td></tr>");
			}
			html.append("</tr>");
		}
		html.append("</table>");
	} catch (SQLException e){
		e.printStackTrace();
		throw new RuntimeException(e.getMessage());
	} finally{
		// todo
	}
%>
    
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>REKOD</title>
</head>
<body>
<script language="JavaScript"> 
function save()
{
    var frm = document.editrow;
	frm.submit();
}
</script>
<form name="editrow" method="post" action="edit-row.jsp">
<input type="hidden" name="t" value="<%= tableName %>">
<table width="450" border="0" cellspacing="0" cellpadding="0" bgcolor="#E8F0F6">
<tr>
  <td valign="top" height="200">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
<% if ("0".equals(id)) {%>
	<tr>	
     <td height="20" class="header" colspan="3" align="left">&nbsp;&nbsp;TAMBAH</td>
  </tr>
<% } else{%>
	<tr>	
     <td height="20" class="header" colspan="3" align="left">&nbsp;&nbsp;EDIT</td>
  </tr>
<% }%>
<tr><td colspan="3" height="10"></td></tr>
<tr><td colspan="3"><%= html.toString() %></td></tr>
<tr><td colspan="3" height="15"></td></tr>
<tr>
<td colspan="3" height="20" align="center"><a href="javascript:save();">SIMPAN</a></td>
</tr><!--input type="submit" value="Save"-->
</table>
</td></tr>
</table>
</form>
</body>
</html>