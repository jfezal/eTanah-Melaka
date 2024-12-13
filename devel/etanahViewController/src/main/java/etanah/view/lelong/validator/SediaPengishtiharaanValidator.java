/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author mazurahayati.yusop
 */
public class SediaPengishtiharaanValidator implements StageListener {

    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private static final Logger LOG = Logger.getLogger(SediaPengishtiharaanValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("------beforeComplete-------");
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();
        
        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
        if (!listPermohonan.isEmpty()) {
            LOG.info("----PPTL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
        
        List<Permohonan> listPermohonan2 = lelongService.getListPPBL(idPermohonan);
        if (!listPermohonan2.isEmpty()) {       
            LOG.info("----PPBL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Pembatalan 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(permohonan.getIdPermohonan());
//        if (!listPermohonan.isEmpty()) {
//            context.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }

//        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanRekodBidaan(permohonan.getIdPermohonan());
//        if (fasaPermohonan != null) {
//            lelongService.delete(fasaPermohonan);
//        }
             FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanRekodBidaan(permohonan.getIdPermohonan());
        if (fasaPermohonan != null) {
            FasaPermohonanLog fasaPermohonanLog = lelongService.findFasaPermohonanLog(fasaPermohonan);
            if(fasaPermohonanLog!=null){
                lelongService.deletetest(fasaPermohonanLog,fasaPermohonan);
            }
        }

        FasaPermohonan fasa = lelongService.findFasaPermohonanSemakPembida(permohonan.getIdPermohonan());
        if (fasa != null) {
            FasaPermohonan ff = lelongService.findFasaPermohonanSediaPengisytiharan(permohonan.getIdPermohonan());
            if (ff != null) {
                ff.setKeputusan(fasa.getKeputusan());
                lelongService.saveUpdate2(ff);
            } else {
                ff = new FasaPermohonan();
                Pengguna p = (Pengguna) context.getPengguna();
                InfoAudit info = p.getInfoAudit();
                info.setDimasukOleh(p);
                info.setTarikhMasuk(new java.util.Date());
                ff.setCawangan(fasa.getCawangan());
                ff.setIdPengguna(fasa.getIdPengguna());
                ff.setInfoAudit(info);
                ff.setIdAliran("sediaPengisytiharan");
                ff.setPermohonan(permohonan);
                ff.setTarikhHantar(new java.util.Date());
                if (StringUtils.isNotEmpty(fasa.getUlasan())) {
                    ff.setUlasan(fasa.getUlasan());
                }
                ff.setKeputusan(fasa.getKeputusan());
                lelongService.saveUpdate2(ff);
            }
            proposedOutcome = fasa.getKeputusan().getKod();
        }

        FolderDokumen fd = permohonan.getFolderDokumen();
        List<KandunganFolder> listKD = lelongService.getListALLDokumen(fd.getFolderId());
        if (!listKD.isEmpty()) {
            KodDokumen kod = null;
            for (KandunganFolder kf : listKD) {
                if (kf.getDokumen().getKodDokumen().getKod().equals("MEMO")) {
                    kod = kodDokumenDAO.findById("MEMOL");
                    Dokumen d = kf.getDokumen();
                    d.setKodDokumen(kod);
                    lelongService.saveOrUpdatDokumen(d);
                    kf.setDokumen(d);
                    lelongService.saveOrUpdate(kf);
                }
                if (kf.getDokumen().getKodDokumen().getKod().equals("KM")) {
                    kod = kodDokumenDAO.findById("KMLM");
                    Dokumen d = kf.getDokumen();
                    d.setKodDokumen(kod);
                    lelongService.saveOrUpdatDokumen(d);
                    kf.setDokumen(d);
                    lelongService.saveOrUpdate(kf);
                }
            }
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }
}
