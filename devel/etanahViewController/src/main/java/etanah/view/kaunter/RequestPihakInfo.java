package etanah.view.kaunter;

import java.util.List;
import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.inject.Inject;

import etanah.dao.PihakDAO;
import etanah.model.Pihak;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;

@HttpCache (allow = false)
@UrlBinding ("/common/req_pihak_info")
public class RequestPihakInfo extends AbleActionBean {
	
	public String jenisPengenalan;
	
	public String noPengenalan;
	
	public static final String DELIM = "__^$__";
	
	@Inject
	protected com.google.inject.Provider<Session> sessionProvider;
	
	final static Logger LOG = Logger.getLogger(RequestPihakInfo.class);
	final static boolean debug = LOG.isDebugEnabled();
	
	@DefaultHandler
    public Resolution doCheckHakmilik() {
        LOG.debug("jenisPengenalan=" + jenisPengenalan + 
        		",noPengenalan=" + noPengenalan);
        
        List l =  sessionProvider.get().createQuery(
        		"select p from Pihak p left join fetch p.negeri n " +
        		"where p.jenisPengenalan.id = :jenisPengenalan and " +
        		"p.noPengenalan = :noPengenalan")
        		.setString("jenisPengenalan", jenisPengenalan)
        		.setString("noPengenalan", noPengenalan)
        		.list();
        String results = "";
        
        if (l.size() > 0){
        	if (l.size() > 1) LOG.warn("There are more than 1 result");
        	Pihak p = (Pihak) l.get(0);
        	LOG.debug("Pihak found! idPihak=" + p.getIdPihak());
	        StringBuilder s = new StringBuilder();
//	        s.append(p.getJenisPengenalan() != null ?
//        			p.getJenisPengenalan().getKod() : "").append(DELIM)
//                                .append(p.getNoPengenalan() != null? p.getNoPengenalan() : "").append(DELIM)
//                                .append(p.getNama() != null ? p.getNama() : "").append(DELIM)
//	        		.append(p.getSuratAlamat1() != null ? p.getSuratAlamat1() : "").append(DELIM)
//	        		.append(p.getSuratAlamat2() != null ? p.getSuratAlamat2() : "").append(DELIM)
//	        		.append(p.getSuratAlamat3() != null ? p.getSuratAlamat3() : "").append(DELIM)
//	        		.append(p.getSuratAlamat4() != null ? p.getSuratAlamat4() : "").append(DELIM)
//	        		.append(p.getSuratPoskod() != null ? p.getSuratPoskod() : "").append(DELIM)
//	        		.append(p.getSuratNegeri() != null ? p.getSuratNegeri().getKod() : "").append(DELIM)
//	        		.append(p.getNoTelefon1() != null ? p.getNoTelefon1() : "").append(DELIM)
//	        		.append(p.getEmail() != null ? p.getEmail() : "");
                // Get Alamat details instead of SuratAlamat
                s.append(p.getJenisPengenalan() != null ?
        			p.getJenisPengenalan().getKod() : "").append(DELIM)
                                .append(p.getNoPengenalan() != null? p.getNoPengenalan() : "").append(DELIM)
                                .append(p.getNama() != null ? p.getNama() : "").append(DELIM)
	        		.append(p.getAlamat1() != null ? p.getAlamat1() : "").append(DELIM)
	        		.append(p.getAlamat2() != null ? p.getAlamat2() : "").append(DELIM)
	        		.append(p.getAlamat3() != null ? p.getAlamat3() : "").append(DELIM)
	        		.append(p.getAlamat4() != null ? p.getAlamat4() : "").append(DELIM)
	        		.append(p.getPoskod() != null ? p.getPoskod() : "").append(DELIM)
	        		.append(p.getNegeri() != null ? p.getNegeri().getKod() : "").append(DELIM)
	        		.append(p.getNoTelefon1() != null ? p.getNoTelefon1() : "").append(DELIM)
	        		.append(p.getEmail() != null ? p.getEmail() : "");
	        results = s.toString();
	        LOG.debug(s);
        } else{
        	LOG.debug("Pihak not found");
        }
        
        return new StreamingResolution("text/plain", results);
    }

}
