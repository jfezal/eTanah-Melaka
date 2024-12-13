package etanah.service;

import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.Permohonan;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PermohonanService;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

public class SemakDokumenService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    DokumenService dokumenService;
    @Inject PermohonanService permohonanService;
    private static final Logger LOGGER = Logger.getLogger(SemakDokumenService.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();

    public boolean semakDok (String idPermohonan, String kodDokumen) {
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan == null) return false;

        FolderDokumen fd = permohonan.getFolderDokumen();
        if (fd == null) return false;
        String idKumpulan = permohonan.getIdKumpulan();
        
        List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
        
        if (StringUtils.isNotBlank(idKumpulan)) {
            KodDokumen kd = kodDokumenDAO.findById(kodDokumen);
            Dokumen d = semakDokumen2(kd, fd, idPermohonan);
            if (d == null) {
              List<Permohonan> senaraiPermohonan = permohonanService.getPermohonanByIdKump(idKumpulan);
              for (Permohonan p : senaraiPermohonan) {                  
//                  if (p.getIdPermohonan().equals(idPermohonan)) continue;
                  fd = p.getFolderDokumen();
                  permohonan = p;
                  for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        Hakmilik h = hp.getHakmilik();
                        d = semakDokumen2(kd, fd, h.getIdHakmilik());
                        if ( d != null) return true;
                    }
              }              
            } else return true;
        } else {

            for(KandunganFolder kf : senaraiKand) {
                if ( kf == null || kf.getDokumen() == null ) continue;
                if (kf.getDokumen().getKodDokumen().getKod().equals(kodDokumen)) return true;
            }
        }
        
        return false;
    }

    public boolean semakDokumen(String idPermohonan, String kodDokumen) {
        if (StringUtils.isNotBlank(idPermohonan) && StringUtils.isNotBlank(kodDokumen)) {
            Permohonan p = permohonanDAO.findById(idPermohonan);

            if (p != null) {
                FolderDokumen fd = p.getFolderDokumen();
                KodDokumen kd = kodDokumenDAO.findById(kodDokumen);
                if (fd != null && kd != null) {
                    
                    Dokumen d = null;
                    if (p.getSenaraiHakmilik().size() > 1) {
                        for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                                Hakmilik h = hp.getHakmilik();
                                d = semakDokumen(kd, fd, h.getIdHakmilik());
//                                LOGGER.debug("is dokumen null = " + (d == null ? "true" : "false"));
                                if(d == null)
                                    return false;
                        }                        
                    } else {
                        d = semakDokumen(kd, fd, idPermohonan);
                        if (d == null) {
                            for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                                Hakmilik h = hp.getHakmilik();
                                d = semakDokumen(kd, fd, h.getIdHakmilik());
//                                LOGGER.debug("is dokumen null = " + (d == null ? "true" : "false"));
                                if(d == null)
                                    return false;
                        }
                        }
//                        LOGGER.debug("is dokumen null = " + (d == null ? "true" : "false"));
                    }

                    if (d != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Dokumen semakDokumen(KodDokumen kd, FolderDokumen fd, String id) {
        Dokumen d = null;
        List<KandunganFolder> l = kandunganFolderService.findByIdFolder(fd);
        for (KandunganFolder kf : l) {
//            if (!kf.getDokumen().getKodDokumen().getKod().equals(kd.getKod())) continue;
            LOGGER.debug("id dokumen = " + kf.getDokumen().getIdDokumen());
            LOGGER.debug("kod dokumen = " + kd.getKod());
            LOGGER.debug("id = " + id);
//            if (kf == null || kf.getDokumen() == null) continue;
            d = dokumenService.findByIDKodDokumen(kf.getDokumen().getIdDokumen(), kd, id);            
            if (d != null) {
                return d;
            }
        }
        return null;
    }


    public Dokumen semakDokumen2 (KodDokumen dk, FolderDokumen fd, String id) {
        
        List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
        for (KandunganFolder kf : senaraiKand) {
            Dokumen d = kf.getDokumen();
            LOGGER.debug("kod dokumen =" + d.getKodDokumen().getKod());
//            LOGGER.debug("nilai dalaman =" + d.getDalamanNilai1());
            if(d.getDalamanNilai1()!=null){
                if ( d.getKodDokumen().getKod().equals(dk.getKod())
                    && d.getDalamanNilai1().contains(id))
                return d;
            }            
        }
        return null;
    }
}
