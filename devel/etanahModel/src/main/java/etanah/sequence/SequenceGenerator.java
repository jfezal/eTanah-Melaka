package etanah.sequence;

import java.sql.*;

import org.hibernate.Session;

import com.google.inject.Inject;

import etanah.model.KodCawangan;
import org.apache.log4j.Logger;

/**
 * The sequence generator utilizing table "turutan"
 * 
 * http://blog.dagitab.com/index.php?blog=1&p=30&disp=single&more=1&c=1&tb=1&pb=1
 * 
 * @author hishammk
 *
 */
public abstract class SequenceGenerator {
    
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
		
	public abstract String generate(String kodNegeri, KodCawangan caw, Object obj);
        protected static final Logger LOG = Logger.getLogger(SequenceGenerator.class);
        protected static final boolean IS_DEBUG = LOG.isDebugEnabled();

	protected long getSerialNo(String sequenceName){
		Connection c = sessionProvider.get().connection();
		Statement s = null;
		ResultSet rs = null;
		try{
			s = c.createStatement();
			// TODO remove Oracle specific
			rs = s.executeQuery("select " + sequenceName + ".nextval from dual");
			rs.next();
			return rs.getLong(1);
		} catch (SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e.getMessage() + " Ensure SEQUENCE " + sequenceName
					+ "  exists!");
		} finally{
			if (rs != null) try { rs.close(); } catch (Exception e) {}
			if (s != null) try { s.close(); } catch (Exception e) {}
			// if (c != null) try { c.close(); } catch (Exception e) {}
		}
	}

}
