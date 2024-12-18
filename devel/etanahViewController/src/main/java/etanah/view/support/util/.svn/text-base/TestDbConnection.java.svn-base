package etanah.view.support.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.inject.Inject;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;

@UrlBinding ("/util/test-db")
public class TestDbConnection extends AbleActionBean {

	public static final String TEST_SQL_DEFAULT  = "select 9 from dual";
	
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    private static final Logger LOG = Logger.getLogger(TestDbConnection.class);
	
	@DefaultHandler
	public Resolution perform(){
		String html = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		try{
			LOG.info("getting Hibernate session ... ");
			Session s = sessionProvider.get();
			LOG.info("getting Db Connection ... ");
			Connection conn = s.connection();
			stmt = conn.createStatement();
			LOG.info("executing statement (" + TEST_SQL_DEFAULT + ") ...");
			rs = stmt.executeQuery(TEST_SQL_DEFAULT);
			LOG.info("checking out select row");
			if (rs.next()){
				LOG.info("first row's column's value is " + rs.getString(1));
			} else{
				LOG.info("no row selected");
			}
		} catch (Exception e){
			html = "Db Connection is NOT OK. Exception is " + e.getMessage();
			LOG.error(e.getMessage(), e);
		} finally{
			if (rs != null) try { rs.close(); } catch (Exception e1) {}
			if (stmt != null) try { stmt.close(); } catch (Exception e2) {}
		}
		
		if (html == null) html = "Db Connection is OK. No exception occured";
		
		return new StreamingResolution("text/plain", html);
	}
	
}
