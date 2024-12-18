package com.etanah.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.rowset.OracleCachedRowSet;

import com.etanah.portal.pojo.PortalUsers;

import etanah.ws.utility.StagingDB;

/**
 * @author Sudhakar
 *
 */
public class PortalUsersDao {

	public List<PortalUsers> findById(){
		
		List<PortalUsers> list=new ArrayList<PortalUsers> ();
		
		Connection conn =null;
		PreparedStatement ps =null;
		ResultSet rs =null;
		StringBuilder sql=new StringBuilder();
		sql.append("select distinct username,no_kp,email from portal_users where reminder='Y' ");
		try {
			StagingDB db = new StagingDB();
			conn=db.getConnection();
			 ps = conn.prepareStatement(sql.toString());
			 rs = ps.executeQuery();
			 OracleCachedRowSet ocrs = new OracleCachedRowSet();
			ocrs.populate(rs);
			
			if (db.getRowCount(ocrs) > 0) {
					ocrs.beforeFirst();
					
					while(ocrs.next()){
						list.add(new PortalUsers(ocrs.getString(1),ocrs.getString(2),ocrs.getString(3)));
					}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{			
			try {
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					if(conn!=null) conn.close();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
}
