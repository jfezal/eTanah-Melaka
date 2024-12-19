/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.FasaPermohonan;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author zadhirul.farihim
 */
public class KeputusanMMKValidator implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    private FasaPermohonan fasaPermohonan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private PermohonanKertas mohonKertas;
    private KodDokumen kd = new KodDokumen();
    private static final Logger LOG = Logger.getLogger(KeputusanMMKValidator.class);

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext sc) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusanmmk");
        LOG.info("------FASA PERMOHONAN : " + fasaPermohonan);
        mohonKertas = strService.findKertasByKod(permohonan.getIdPermohonan(), "MMK");
        LOG.info("------PERMOHONAN KERTAS : " + mohonKertas);

        try {

            /*validate keputusan*/
            if (fasaPermohonan.getKeputusan() == null) {
                LOG.info("fasaPermohonan not null BUT keputusan is null");
                context.addMessage("Sila masukkan Keputusan dan simpan Keputusan sebelum meneruskan ke butang Selesai' ");
                return null;
            }
     
           /*update table mohon*/ 
            if (fasaPermohonan.getKeputusan() != null) {                         
             permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());          
             LOG.debug("--------permohonan----:" + permohonan.getIdPermohonan());
            
             if(permohonan != null){
             // KodKeputusan kodkpsn = mohonFasa.getKeputusan();
              permohonan.setKeputusan(fasaPermohonan.getKeputusan());
              Pengguna peng = (Pengguna) context.getPengguna();
              permohonan.setKeputusanOleh(peng);
              permohonan.setTarikhKeputusan(new Date());
              strService.updateMohon(permohonan);
          }   
            }

            /*validate tarikh sidang*/
            if (mohonKertas.getTarikhSidang() == null) {
                context.addMessage("Sila masukkan dan simpan Tarikh Sidang sebelum meneruskan ke butang Selesai. ");
                return null;
            }


        } catch (Exception e) {
            context.addMessage("Terdapat masalah. Sila hubungi pentadbir sistem. Ralat : " + e);
            LOG.error(e.getMessage());
            return null;
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
