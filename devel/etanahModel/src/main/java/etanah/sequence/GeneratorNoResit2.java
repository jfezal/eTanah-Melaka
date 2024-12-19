package etanah.sequence;

import able.stripes.AbleActionBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cfg.Settings;
import org.hibernate.impl.SessionFactoryImpl;

import com.google.inject.Inject;

import etanah.model.Pengguna;
import org.apache.commons.lang.StringUtils;

/**
 * Implementation of safe generation of no.resit using lock implementation
 * at db level.
 * @author hishammk
 *
 */
public class GeneratorNoResit2 {
	
	public static final int DEFAULT_SERIAL_LOCK_TIMEOUT = 600000; // in ms (10min)
	
	public static final int DEFAULT_SERIAL_LOCK_WAIT_INTERVAL = 5000; // in ms (5sec)
	
	public static final int DEFAULT_SERIAL_LOCK_RETRY = 10;
	
	private static final boolean DEFAULT_SERIAL_LOCK_AUTO_CREATE = true;
	
    protected com.google.inject.Provider<Session> sessionProvider;
    private String kodNegeri;
    
    private static Logger LOG = Logger.getLogger(GeneratorNoResit2.class);
    private static boolean debug = LOG.isDebugEnabled();
    
    @Inject
    public GeneratorNoResit2(com.google.inject.Provider<Session> sessionProvider){
        this.sessionProvider = sessionProvider;
        this.kodNegeri = System.getProperty("etanah/kodNegeri");
        
        // TODO implement customization of default values
    }

    public String getAndLockSerialNo(Pengguna p){
        int serialNo = -1;
        String fullSerialNo = null;
        
        String kodCaw = p.getKodCawangan().getKod();
    	
    	// WARNING: Connection used must be different from caller so that we could commit independently
        int i = 0;
		for (; i < DEFAULT_SERIAL_LOCK_RETRY; i++){
		    Connection c = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        boolean locked = false;
	        boolean newSiri = false;
	        boolean lastAutoCommit = true;
			// try to lock serial no
			try{
			    c = getConnection();
			    lastAutoCommit = c.getAutoCommit();
			    c.setAutoCommit(true);
	            ps = c.prepareStatement("select no_siri, dikunci, trh_kunci, kunci_siri from kew_dokumen_siri " +
	            		"where kod_caw = ? and id_kaunter = ? and kod_dokumen is null");
	            ps.setString(1, kodCaw);
	            ps.setString(2, p.getIdKaunter());
	            rs = ps.executeQuery();
	            
	            if (rs.next()){
	                if (debug) LOG.debug(p.getIdPengguna() + ":serial no found");
	            	String idPengguna = rs.getString(2);
	            	if (idPengguna != null){ // serial is locked
	            		if (idPengguna.equals(p.getIdPengguna())){ // locked by himself
	            		    if (debug) LOG.debug(p.getIdPengguna() + ":locked by self");
	            			serialNo = rs.getInt(4) + 1;
	            		} else{ // locked by other user
	            			// check if lock expires
	            			Timestamp dateLocked = rs.getTimestamp(3);
	            			if (debug) LOG.debug("diff=" + ((new java.util.Date()).getTime() - dateLocked.getTime()));
	            			if ((new java.util.Date()).getTime() - dateLocked.getTime() > DEFAULT_SERIAL_LOCK_TIMEOUT){
	            				// expired & can reset the lock
	            			    LOG.debug(p.getIdPengguna() + ":lock expired, locked at " + dateLocked);
	            				serialNo = rs.getInt(1) + 1;
	            			} else{
	            			    LOG.debug(p.getIdPengguna() + ":locked by " + idPengguna);
	            			    locked = true;
	            			}
	            		}
	            	} else{ // not locked
	            	    serialNo = rs.getInt(1) + 1;
	            	}	                   	
	            } else{
	            	// serial not exist yet
                    if (debug) LOG.debug("serial no not found");
	                newSiri = true;
	            	if (DEFAULT_SERIAL_LOCK_AUTO_CREATE){ // create one
	            	    LOG.info(p.getIdPengguna() + ":creating a new series for " + p.getIdPengguna());
                        ps.close();
	            		serialNo = 1;
	            		ps = c.prepareStatement("insert into kew_dokumen_siri (kod_caw, id_kaunter, kod_dokumen, no_siri, " +
	            				"dikunci, trh_kunci, kunci_siri,id_siri) " +
	            				"values (?, ?, null, 0, ?, ?, 1,?)");
	            		ps.setString(1, p.getKodCawangan().getKod());
	            		ps.setString(2, p.getIdKaunter());
	            		ps.setString(3, p.getIdPengguna());
	            		ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
	            		ps.setInt(5, getSequenceIdSiri());
	            		ps.execute();
	            	} else{
	            		throw new RuntimeException("No Siri tak wujud untuk " + p.getIdPengguna() + " (AutoCreate mode currently disabled)");
	            	}
	            }
	            
	            if (!locked){
	                if (!newSiri){
    		        	ps.close();
    		        	// set the lock
    		        	LOG.debug(p.getIdPengguna() + ":locking for " + p.getIdPengguna());
    		        	ps = c.prepareStatement("update kew_dokumen_siri set " +
    		        			"dikunci = ?, " +
    		        			"trh_kunci = ?, " +
    		        			"kunci_siri = ? " +
    		        			"where kod_caw = ? and id_kaunter = ? and kod_dokumen is null");
    		        	ps.setString(1, p.getIdPengguna());
    		        	ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
    		        	ps.setInt(3, serialNo);
    		        	ps.setString(4, kodCaw);
    		        	ps.setString(5, p.getIdKaunter());
    		        	ps.executeUpdate();
	                }
		        	break;
	            } else{
	                if (debug) LOG.debug(p.getIdPengguna() + ":waiting for the lock to be released, i = " + i);
	                try{
	                    Thread.sleep(DEFAULT_SERIAL_LOCK_WAIT_INTERVAL);
	                } catch (InterruptedException e) {
	                    LOG.error(e.getMessage(), e);
	                }
	            }
				c.commit();
	        } catch (SQLException e){
	            LOG.error(e.getMessage(), e);
	            serialNo = -1;
			} finally{
			    try {c.setAutoCommit(lastAutoCommit);} catch (SQLException e) {}
	            if (rs != null) try {rs.close();} catch (SQLException e) {}
	            if (ps != null) try {ps.close();} catch (SQLException e) {}
                    if (c != null)
                        try {
                            LOG.debug("closing conn ?");
                        c.close();
                    }  catch (SQLException e) {}
	            // TODO what to do with connection? do i close?
	        }
		}
		if (i == DEFAULT_SERIAL_LOCK_RETRY){
		    LOG.warn(p.getIdPengguna() + ":Unable to get a serial no, cause it has been locked!");
		}
		
        if (serialNo > 0) {
            String tarikh = (new SimpleDateFormat("yyMMdd")).format(new java.util.Date());
//             String kodDaerah = p.getKodCawangan().getKod();
            String kodDaerah = ""; //kod daerah will null for PTG !!
            if ( p.getKodCawangan().getDaerah() != null ) {
                kodDaerah = p.getKodCawangan().getDaerah().getKod();
            }

            String kodCawangan = p.getKodCawangan().getKod();
            String kaunter = p.getIdKaunter();

            NumberFormat df = NumberFormat.getInstance();
            df.setMaximumIntegerDigits(4);
            df.setMinimumIntegerDigits(4);
            df.setGroupingUsed(false);
            LOG.info("Kod Negeri "+kodNegeri);
            if(kodNegeri.equals("04")){
                LOG.info("Resit Melaka");                          //format receipt number for Melaka
                if (StringUtils.isBlank(kodDaerah)) kodDaerah = "00";
                fullSerialNo = kodDaerah.charAt(1) + kodCawangan + kaunter + tarikh + df.format(serialNo);
                // kod_daerah, kod_cawangan, id_kaunter, tarikh, sequence
            }else{
                LOG.info("Resit Negeri Sembilan");           //format receipt number for Negeri Sembilan
//                fullSerialNo = tarikh + kodCawangan + "01" + kaunter + df.format(serialNo);   //lama
                fullSerialNo = tarikh + kodCawangan + "1" + kaunter + df.format(serialNo);                                              // speks 15angka
                // tarikh, kod_cawangan (pusat_kutipan), kod_kutipan (01 - kaunter, 02 - kutpn luar), id_kaunter, sequence
            }
        }

        return fullSerialNo;
    }
    
    public void commitAndUnlockSerialNo(Pengguna  p){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean autoCommit = false;
        try{
            c = getConnection();
            autoCommit = c.getAutoCommit();
            c.setAutoCommit(true);

            // have to check if currently locked by user
            ps = c.prepareStatement("select no_siri, dikunci, trh_kunci, kunci_siri from kew_dokumen_siri " +
                    "where kod_caw = ? and id_kaunter = ? and kod_dokumen is null");
            ps.setString(1, p.getKodCawangan().getKod());
            ps.setString(2, p.getIdKaunter());
            rs = ps.executeQuery();
            if (rs.next()){
                String dikunci = rs.getString(2);
                if (dikunci == null || !dikunci.equals(p.getIdPengguna())){
                    // silent fail
                    LOG.warn("Invalid unlock request by " + p.getIdPengguna() + " to unlock serial no locked by " + dikunci);
                    return;
                }
            } else{
                return;
            }
            int newNoSiri = rs.getInt(4);
            
            ps.close();
            // set the lock
            ps = c.prepareStatement("update kew_dokumen_siri set " +
                    "no_siri = ?, " +
                    "dikunci = null, " +
                    "trh_kunci = null, " +
                    "kunci_siri = null " +
                    "where kod_caw = ? and id_kaunter = ? and kod_dokumen is null");
            ps.setInt(1, newNoSiri);
            ps.setString(2, p.getKodCawangan().getKod());
            ps.setString(3, p.getIdKaunter());
            ps.executeUpdate();
        } catch (SQLException e){
            LOG.error(e.getMessage(), e);
        } finally{
            try {c.setAutoCommit(autoCommit);} catch (SQLException e) {}
            if (rs != null) try {rs.close();} catch (SQLException e) {}
            if (ps != null) try {ps.close();} catch (SQLException e) {}
            if (c != null) 
                try {
                    LOG.debug("closing conn ?");
                c.close();
            } catch (SQLException e) {}
        }
    }
    
    public void rollbackAndUnlockSerialNo(Pengguna p){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean autoCommit = false;
        try{
            c = getConnection();
            autoCommit = c.getAutoCommit();
            c.setAutoCommit(true);

            // have to check if currently locked by user
            ps = c.prepareStatement("select dikunci, trh_kunci, kunci_siri from kew_dokumen_siri " +
                    "where kod_caw = ? and id_kaunter = ? and kod_dokumen is null");
            ps.setString(1, p.getKodCawangan().getKod());
            ps.setString(2, p.getIdKaunter());
            rs = ps.executeQuery();
            if (rs.next()){
                String dikunci = rs.getString(1);
                if (dikunci == null || !dikunci.equals(p.getIdPengguna())){
                    // silent fail
                    LOG.warn("Invalid unlock request by " + p.getIdPengguna() + " to unlock serial no locked by " + dikunci);
                    return;
                }
            } else{
                return;
            }
            
            ps.close();
            // set the lock
            ps = c.prepareStatement("update kew_dokumen_siri set " +
                    "dikunci = null, " +
                    "trh_kunci = null, " +
                    "kunci_siri = null " +
                    "where kod_caw = ? and id_kaunter = ? and kod_dokumen is null");
            ps.setString(1, p.getKodCawangan().getKod());
            ps.setString(2, p.getIdKaunter());
            ps.executeUpdate();
        } catch (SQLException e){
            LOG.error(e.getMessage(), e);
        } finally{
            try {c.setAutoCommit(autoCommit);} catch (SQLException e) {}
            if (rs != null) try {rs.close();} catch (SQLException e) {}
            if (ps != null) try {ps.close();} catch (SQLException e) {}
            if (c != null)
                try {
                    LOG.debug("closing conn ?");
                c.close();
            } catch (SQLException e) {}
        }
    }

    private Connection getConnection() throws SQLException{
        Settings settings = ((SessionFactoryImpl) sessionProvider.get().getSessionFactory()).getSettings();
        Connection c = settings.getConnectionProvider().getConnection();
        LOG.debug("****************connection class " + c.getClass());
        return c;
    }

    private int getSequenceIdSiri() throws SQLException {
        PreparedStatement ps = null;
        Connection c = null;
        ResultSet rs = null;
        int idSiri = 0;

        Settings settings = ((SessionFactoryImpl) sessionProvider.get().getSessionFactory()).getSettings();
        c = settings.getConnectionProvider().getConnection();
        ps = c.prepareStatement("select count(*) from kew_dokumen_siri");

        rs = ps.executeQuery();
        if(rs.next()){
            idSiri = rs.getInt(1)+1;
        }

        LOG.info("idSiri : "+idSiri);
        return idSiri;
    }
}
