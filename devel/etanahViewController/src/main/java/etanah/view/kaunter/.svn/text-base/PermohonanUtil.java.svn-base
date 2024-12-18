package etanah.view.kaunter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import etanah.Configuration;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.UrusanDokumen;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.KaunterService;

@Singleton
public class PermohonanUtil {
	
    public static final String KOD_JABATAN_PENDAFTARAN = "16";
    
    public static final String KOD_DOKUMEN_AKUAN_PENERIMAAN = "UNKN1";
    
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    @Inject
    private PermohonanDAO permohonanDAO;
	
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    
    @Inject
    private KaunterService kaunterService;
    
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    
    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;
	
    final Map<String, String[]> URUSAN_INFO = new HashMap<String, String []>();
    
    private static final Logger LOG = Logger.getLogger(PermohonanUtil.class);
    private static final boolean debug = LOG.isDebugEnabled();

    public PermohonanUtil(){
    	// loading configuration file: kaunterUrusanInfo
    	Properties p = new Properties();
    	try {
			p.load(Configuration.class.getResourceAsStream("/kaunterUrusanInfo.properties"));
			Set<String> setKodUrusan = p.stringPropertyNames();
			for (Iterator<String> i = setKodUrusan.iterator(); i.hasNext();){
				String kodUrusan = i.next();
				if (debug) LOG.debug("Configuration loading " + kodUrusan);
				String info = p.getProperty(kodUrusan);
				if (debug) LOG.debug("Configuration for " + kodUrusan + ":" + info);
				if (info == null || info.length() == 0) {
					LOG.warn("No configuration for " + kodUrusan);
					continue;
				}
				String [] infos = info.split("\\|");
				if (debug) LOG.debug("infos.length " + infos.length);
				if (infos.length != 3){
					String [] b = new String[3];
					for (int x = 0; x < infos.length && x < 3; x++){
						b[x] = infos[x];
					}
					infos = b;
				}
				for (int j = 0; j < 3; j++){
					if (infos[j] != null) {
						infos[j] = infos[j].trim();
						if (infos[j].length() == 0) infos[j] = null;
					}
				}
				URUSAN_INFO.put(kodUrusan, infos);
			}
			
		} catch (Exception e) {
			LOG.fatal("Configuration for Kaunter cannot be loaded!");
			LOG.fatal(e.getMessage(), e);
		}
    	
    }
    
    public TransaksiValue calculateFeeForDocuments(UrusanPermohonan uv, String kodUrusan, int kuantiti){
		if (kuantiti > 0){
			KodUrusan urusan = kodUrusanDAO.findById(kodUrusan);
			// create a new transaction
			TransaksiValue t5 = new TransaksiValue();
            t5.utkUrusan = uv;			
            t5.kodUrusan = kodUrusan;
            t5.namaUrusan = urusan.getNama();
            t5.kuantiti = kuantiti;
            t5.amaun = urusan.getCaj().multiply(new BigDecimal(kuantiti));
            t5.kodTransaksi = urusan.getKodTransaksi().getKod();
            return t5;
		} else{
			return null;
		}
    }

    ArrayList<String> getIdHakmilikFromSiri(String idHakmilikDari, String idHakmilikKe) {
        StringBuilder from = new StringBuilder();
        for (int i = idHakmilikDari.length() - 1; i >= 0; i--) {
            char c = idHakmilikDari.charAt(i);
            if (c >= '0' && c <= '9') {
                from.insert(0, c);
            } else {
                break;
            }
        }
        long lFrom = Long.parseLong(from.toString());
        String pre = idHakmilikDari.substring(0, idHakmilikDari.length() - from.length());

        // to
        long lTo = 0l;
        try {
            lTo = Long.parseLong(idHakmilikKe.substring(pre.length(), idHakmilikDari.length()));
        } catch (NumberFormatException e) {
            throw new RuntimeException("ID Hakmilik bersiri tidak sah");
        }

        ArrayList<String> listIdHakmilik = new ArrayList<String>();
        // validate the series along the way
        if (idHakmilikDari.length() != idHakmilikKe.length() ||
                !idHakmilikDari.substring(0, pre.length()).equals(idHakmilikKe.substring(0, pre.length())) ||
                lTo < lFrom) {
            throw new RuntimeException("ID Hakmilik bersiri tidak sah");
        } else {

            listIdHakmilik.add(idHakmilikDari);
            listIdHakmilik.add(idHakmilikKe);
            DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
            df.setGroupingUsed(false);
            df.setMinimumIntegerDigits(from.length());
            for (long l = lFrom + 1; l < lTo; l++) {
                String id = pre + df.format(l);
                listIdHakmilik.add(id);
            }
        }

        return listIdHakmilik;
    }
    
    /**
     * Update list with idHakmilik from idHakmilik bersiri given.
     * @param list to be updated
     * @param idHakmilikDari
     * @param idHakmilikKe
     * @param df
     */
    void updateIdHakmilikFromBersiri(List<String> list, List<String> listIdHakmilikDari, 
    			List<String> listIdHakmilikKe){
    	if (listIdHakmilikDari == null || listIdHakmilikDari.size() == 0  ||
    			listIdHakmilikKe == null || listIdHakmilikKe.size() == 0) return;
    	
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.setGroupingUsed(false);

        StringBuilder from = new StringBuilder();
    	for (int x = 0; x < listIdHakmilikDari.size(); x++){
    		String idHakmilikDari = listIdHakmilikDari.get(x);
    		String idHakmilikKe = listIdHakmilikKe.get(x);
    		if (idHakmilikDari == null || idHakmilikKe == null) continue;
    		
    		from.setLength(0); // reset
	        // from
	        for (int i = idHakmilikDari.length() - 1; i >= 0; i--) {
	            char c = idHakmilikDari.charAt(i);
	            if (c >= '0' && c <= '9') {
	                from.insert(0, c);
	            } else {
	                break;
	            }
	        }
	        long lFrom = Long.parseLong(from.toString());
	        String pre = idHakmilikDari.substring(0, idHakmilikDari.length() - from.length());
	
	        // validate both are the same
	        if (!idHakmilikKe.startsWith(pre)){
	        	LOG.warn("IdHakmilik bersiri tidak sah. IdHakmilikDari=" + idHakmilikDari +
	        			", IdHakmilikKe=" + idHakmilikKe);
	        	continue;
	        }
	        
	        // to
	        long lTo = Long.parseLong(idHakmilikKe.substring(pre.length(), idHakmilikDari.length()));
	
	        list.add(idHakmilikDari);
	        df.setMinimumIntegerDigits(from.length());
	        for (long l = lFrom + 1; l < lTo; l++) {
	            String id = pre + df.format(l);
	            list.add(id);
	        }
	        list.add(idHakmilikKe);
    	}
    }
    
    /**
     * Given the list of idHakmilik both nonserial and serial, compose a string to
     * display the complete list
     * @param hakmilikPermohonan
     * @param idHakmilikSiriDari
     * @param idHakmilikSiriKe
     * @return
     */
    String getSenaraiHakmilikUrusan(ArrayList<String> hakmilikPermohonan,
            List<String> listHakmilikBersiri) {

        StringBuilder list = new StringBuilder();
        int s = 0;
        if (hakmilikPermohonan != null && hakmilikPermohonan.size() > 0){
        	s = hakmilikPermohonan.size();
	        for (int j = 0; j < s; j++) {
	            String hp = hakmilikPermohonan.get(j);
	            if (hp != null && hp.trim().length() > 0) {
	                if (j != 0) list.append(", ");
	                list.append(hp);
	            }
	        }
        }
        
        // hakmilik bersiri
        if (listHakmilikBersiri != null && listHakmilikBersiri.size() > 0){
        	if (s > 0) list.append(", ");
        	s = listHakmilikBersiri.size();
        	for (int i = 0; i < s; i++){
        		if (i != 0) list.append(",");
        		list.append(listHakmilikBersiri.get(i));
        	}
        }

        return list.toString();
    }

    public List<UrusanDokumen> getSenaraiDokumen(ArrayList<String> senaraiUrusan) {
        return kaunterService.getUrusanDokumen(senaraiUrusan);
    }

	int getCountValidSuratSWD(ArrayList<String> suratSWD) {
		int c = 0;
		for (String s : suratSWD){
			if (StringUtils.isNotBlank(s)) ++c;
		}
		
		return c;
	}
	
}
