package etanah.service.daftar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.inject.Inject;

import etanah.Configuration;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.WakilKuasa;
import etanah.sequence.GeneratorIdPerserahan;

public class SuratWakilKuasaService {
	
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;      

    @Inject
    private PermohonanDAO permohonanDAO;
    
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    
    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;
    
    @Inject
    private Configuration configuration;
    
    private final static Logger LOG = Logger.getLogger(SuratWakilKuasaService.class);
	
	/**
	 * Validate if all the Surat No.Perserahan are valid.
	 * @param listNoSurat
	 * @return list of No Perserahan not valid
	 */
	public List<String> validateNoSurat(List<String> listNoSurat){
		if (listNoSurat == null || listNoSurat.size() == 0) return null;
		
		Session s = sessionProvider.get();
		List<WakilKuasa> listW = s.createQuery("from WakilKuasa w where w.idWakil in (:listNoSurat) " +
				"and w.aktif IN ('Y', 'M')")
				.setParameterList("listNoSurat", listNoSurat).list();
		
		LOG.debug("listW.size()=" + listW.size());
		if (listW.size() == 0) return listNoSurat;
				
		// not same, need to find the invalid surat
		ArrayList<String> invalidW = new ArrayList<String>();
		for (String id : listNoSurat){
			boolean found = false;
			for (WakilKuasa w : listW){
				if (w.getIdWakil().equals(id)){
					found = true;
					break;
				}
			}
			if (!found)
				invalidW.add(id);
		}

		// check expiry
		for (WakilKuasa w : listW){
			Date now = new Date();
			if (w.getTarikhTamat() != null &&  w.getTarikhTamat().after(now)){ // expired
				invalidW.add(w.getIdWakil());
				continue;
			}
			
			Date d = w.getTarikhDaftar();
			if (w.getTempohTahun() > 0 || w.getTempohBulan() > 0 || w.getTempohHari() > 0){
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				c.add(Calendar.YEAR, w.getTempohTahun());
				c.add(Calendar.MONTH, w.getTempohBulan());
				c.add(Calendar.DAY_OF_MONTH, w.getTempohHari());
				if (c.after(d)){ // expired
					invalidW.add(w.getIdWakil());
					continue;
				}
			}
		}
		
		return invalidW;
	}
        
        //Added by Aizuddin to validate perserahan SM
        public List<String> validateSM(List<String> listNoPerserahan){
		if (listNoPerserahan == null || listNoPerserahan.size() == 0) return null;
		
		Session s = sessionProvider.get();
		List<Permohonan> listW = s.createQuery("from Permohonan m where m.idPermohonan in (:listNoPerserahan) " +
				"and m.keputusan = 'D' and m.status = 'SL' and m.kodUrusan.kod = 'SMB'")
				.setParameterList("listNoPerserahan", listNoPerserahan).list();
		
		LOG.debug("listW.size()=" + listW.size());
		if (listW.size() == 0) return listNoPerserahan;
				
		// not same, need to find the invalid surat
		ArrayList<String> invalidW = new ArrayList<String>();
		for (String id : listNoPerserahan){
			boolean found = false;
			for (Permohonan p : listW){
				if (p.getIdPermohonan().equals(id)){
					found = true;
					break;
				}
			}
			if (!found)
				invalidW.add(id);
		}
		return invalidW;
	}
	
	public Permohonan generatePermohonanSuratBaru(Dokumen suratAttached, Pengguna pengguna, 
			String idPenyerah, KodPenyerah penyerahKod, KodJenisPengenalan penyerahJenisPengenalan, 
			String penyerahNoPengenalan, String penyerahNama, String penyerahAlamat1, String penyerahAlamat2, 
			String penyerahAlamat3, String penyerahAlamat4, String penyerahPoskod, KodNegeri penyerahNegeri, 
			String penyerahEmail, String penyerahNoTelefon, Permohonan permohonanSebelum){
		KodUrusan urusanSWB = kodUrusanDAO.findById("SWB");
		
        String idPermohonan =  idPerserahanGenerator.generate(
            		configuration.getProperty("kodNegeri"), pengguna.getKodCawangan(), urusanSWB);

        suratAttached.setNoRujukan(idPermohonan);
        
        Permohonan p = new Permohonan();
        p.setStatus("TA");
        p.setIdPermohonan(idPermohonan);
        p.setCawangan(pengguna.getKodCawangan());
        p.setKodUrusan(urusanSWB);
        InfoAudit iaPermohonan = new InfoAudit();
        // need to set the exact date for Permohonan
        Date d = new Date();
        iaPermohonan.setTarikhMasuk(d);
        iaPermohonan.setDimasukOleh(pengguna);
        p.setInfoAudit(iaPermohonan);
        // folder
        FolderDokumen fd = new FolderDokumen();
        fd.setTajuk(idPermohonan);
        fd.setInfoAudit(iaPermohonan);
        folderDokumenDAO.save(fd);
        
        KandunganFolder kf = new KandunganFolder();
        kf.setDokumen(suratAttached);
        kf.setFolder(fd);
        kf.setInfoAudit(iaPermohonan);
        kandunganFolderDAO.save(kf);
        
        p.setFolderDokumen(fd);
        // penyerah
        if (idPenyerah != null && idPenyerah.length() > 0 &&
        		!"0".equals(idPenyerah)){
        	p.setIdPenyerah(Long.parseLong(idPenyerah));
        }
        if (penyerahKod != null && penyerahKod.getKod() != null &&
        		!"0".equals(penyerahKod.getKod())){
        	p.setKodPenyerah(penyerahKod);
        }
        p.setPenyerahJenisPengenalan(penyerahJenisPengenalan);
        p.setPenyerahNoPengenalan(penyerahNoPengenalan);
        p.setPenyerahNama(penyerahNama);
        p.setPenyerahAlamat1(penyerahAlamat1);
        p.setPenyerahAlamat2(penyerahAlamat2);
        p.setPenyerahAlamat3(penyerahAlamat3);
        p.setPenyerahAlamat4(penyerahAlamat4);
        p.setPenyerahPoskod(penyerahPoskod);
        if (penyerahNegeri != null) {
            p.setPenyerahNegeri(penyerahNegeri);
        }
        p.setPenyerahEmail(penyerahEmail);
        p.setPenyerahNoTelefon1(penyerahNoTelefon);
        p.setUntukTahun(d.getYear() + 1900);
        
        if (permohonanSebelum != null)
        	p.setPermohonanSebelum(permohonanSebelum);
        
        permohonanDAO.save(p);
        
        return p;
		
	}

}
