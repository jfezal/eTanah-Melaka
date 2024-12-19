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
			import="java.text.*"
			import="java.util.*" 
			%>
	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adding/Updating Row ...</title>
</head>
<body>

<%
	String tableName = request.getParameter("t");
	HashMap params = new HashMap();
	Enumeration en = request.getParameterNames();
	// serialize the columns into HashMap
	while (en.hasMoreElements()){
		String key = (String) en.nextElement();
		if (!"t".equals(key)){
			String val = request.getParameter(key);
			params.put(key, val);
			System.out.println(key + "=" + val);
		}
			
	}
	
	String pkName = null;
	Connection c = null;
	PreparedStatement stmt = null;
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
		
		StringBuilder q = new StringBuilder("select * from ");
		q.append(tableName).append(" where ").append(pkName).append("='").append(params.get(pkName).toString())
				.append("'");
		// try selecting the row to check if exist
		stmt = c.prepareStatement(q.toString());
		//int pkey = Integer.parseInt((String) params.get(pkName));
		//stmt.setInt(1, pkey);
		rs = stmt.executeQuery();
		q.setLength(0);
		if (rs.next()){
			// execute update
			rs.close();
			q.append("update ").append(tableName).append(" set ");
			for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext(); ){
				String key = iter.next();
				if (!key.toString().equals(pkName)){
					q.append(key).append("=?,");
				}
			}
			q.setLength(q.length() - 1); // cut out the comma
			q.append(" where ").append(pkName).append("=?");
		} else{
			// execute insert
			rs.close();
			q.append("insert into ").append(tableName).append(" (");
			StringBuilder q2 = new StringBuilder();
			for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext(); ){
				String key = iter.next();
				if (!key.toString().equals(pkName)){
					q.append(key).append(",");
					q2.append("?,");
				}
			}
			q2.append("?");
			q.append(pkName).append(") values (").append(q2).append(")");
		}
		System.out.println("Query:" + q);
		stmt = c.prepareStatement(q.toString());
		int idx = 1;
		for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext(); ){
			String key = iter.next();
			String val = (String) params.get(key);
			// skip PK set at last
			if (key.equals(pkName)) continue;
			
			rs = dbMeta.getColumns(null, null, tableName, (String) key);
			rs.next();
			int colType = rs.getInt("DATA_TYPE");
			
			// setting null, accept empty string as null
			if (val == null || val.length() == 0){
				stmt.setNull(idx++, colType);
				continue;
			}
			
			switch (colType){
			case Types.LONGVARCHAR:
			case Types.VARCHAR:
			case Types.CHAR:
				//System.out.println("Set " + key + " as String");
				stmt.setString(idx++, val);
				break;
				
			case Types.DECIMAL:
			case Types.INTEGER:
			case Types.SMALLINT:
			case Types.TINYINT:
				//System.out.println("Set " + key + " as int");
				int vi = Integer.parseInt(val);
				stmt.setInt(idx++, vi);
				break;
				
			case Types.BIGINT:				
				//System.out.println("Set " + key + " as long");
				long vl = Integer.parseInt(val);
				stmt.setLong(idx++, vl);
				break;
				
			case Types.NUMERIC:
			case Types.DOUBLE:
				//System.out.println("Set " + key + " as double");
				double vd = Double.parseDouble(val);
				stmt.setDouble(idx++, vd);
				break;
				
			case Types.REAL:
			case Types.FLOAT:
				//System.out.println("Set " + key + " as float");
				float vf = Float.parseFloat(val);
				stmt.setFloat(idx++, vf);
				break;		
				
			case Types.DATE:
			case Types.TIMESTAMP:
				// TODO: must get the correct format
				//System.out.println("Set " + key + " as Date");
				SimpleDateFormat vt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				stmt.setDate(idx++, new java.sql.Date(vt.parse(val).getTime()));
				break;		
			}
		}
		System.out.println(params.get(pkName).toString());
		stmt.setString(idx, params.get(pkName).toString()); 
		System.out.println(stmt.executeUpdate());
				
		out.println("<script language=\"javascript\">window.opener.location.reload();window.close();</script>");
		c.commit();
	} catch (SQLException e){
		e.printStackTrace();
		out.println(e.getMessage());
	} finally{
		//ConnectionManager.close(c, stmt, rs);
	}
	
%>

</body>
</html>
