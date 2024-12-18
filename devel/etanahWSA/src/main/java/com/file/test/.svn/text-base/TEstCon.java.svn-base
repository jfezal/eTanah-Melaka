package com.file.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import oracle.jdbc.pool.OracleDataSource;

public class TEstCon {

	public TEstCon() {
		Connection conn=null;
		PreparedStatement pst=null;
		  //String url = "jdbc:oracle:thin:@10.66.128.45:1521/ServiceName=etnsstg";
		String url ="jdbc:oracle:thin:@ (DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.66.128.45)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = etnsstg)))";
	        String username = "portal";
	        String password = "portal123";
	        
	        try {
	        	OracleDataSource ods = new OracleDataSource();
	        	
	        	ods.setDriverType("thin");
	        	ods.setServerName("10.66.128.45");
	        	ods.setNetworkProtocol("smtp");
	        	ods.setDatabaseName("etnsstg");
	        	ods.setPortNumber(1521);
	        	ods.setUser("portal");
	        	ods.setPassword("portal123");
	        	
	            conn =DriverManager.getConnection(url, username, password);
	            	//ods.getConnection(); 
	            	//DriverManager.getConnection(url, username, password);
	            conn.setAutoCommit(false);
	            pst=conn.prepareStatement("select full_name,no_kp from portal_users ");
	            ResultSet rs=pst.executeQuery();
	            while(rs.next()){
	            	System.out.println("[Value :"+rs.getString(1));
	            }
	            
	        } catch (Exception se) {
	            System.out.println("OracleDB.getConnection: SQL Exception.");
	        }
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TEstCon();

	}

}
