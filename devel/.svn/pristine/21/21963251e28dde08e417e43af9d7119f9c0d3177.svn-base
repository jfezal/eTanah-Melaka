package etanah.view.kaunter;

import net.sourceforge.stripes.action.Resolution;

import org.apache.log4j.Logger;

import etanah.model.Penyerah;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

public abstract class RequestPenyerahInfo extends AbleActionBean {
		
	public static final String DELIM = "__^$__";
	
	private static final Logger LOG = Logger.getLogger(RequestPenyerahInfo.class);
	private static final boolean debug = LOG.isDebugEnabled();
        @Inject
        protected com.google.inject.Provider<Session> sessionProvider;        
	
	public abstract Resolution findPenyerah();
        public abstract List<Penyerah> findPenyerahByParam(Map<String, String[]> map);
		
	protected String compose(Penyerah p){
        StringBuilder s = new StringBuilder();
        s.append(p.getJenisPengenalan() != null ?
        			p.getJenisPengenalan().getKod() : "").append(DELIM)
        		.append(p.getNoPengenalan() != null? p.getNoPengenalan() : "O").append(DELIM)
        		.append(p.getNama()).append(DELIM)
        		.append(p.getAlamat1() != null ? p.getAlamat1() : "").append(DELIM)
        		.append(p.getAlamat2() != null ? p.getAlamat2() : "").append(DELIM)
        		.append(p.getAlamat3() != null ? p.getAlamat3() : "").append(DELIM)
        		.append(p.getAlamat4() != null ? p.getAlamat4() : "").append(DELIM)
        		.append(p.getPoskod() != null ? p.getPoskod() : "").append(DELIM)
        		.append(p.getNegeri() != null ? p.getNegeri().getKod() : "").append(DELIM)
        		.append(p.getNoTelefon1() != null ? p.getNoTelefon1() : "").append (DELIM)
                        .append(p.getEmel() != null ? p.getEmel() : "").append (DELIM)
                        .append(Character.toString(p.getAktif()) !=null ? p.getAktif():"");
        if (debug) LOG.debug(s);
        
        return s.toString();
	}

}
