/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

import etanah.view.pengambilan.validator.*;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;
import etanah.service.PengambilanService;
import etanah.service.PengambilanService1;
import etanah.dao.KodKeputusanDAO;
import etanah.model.*;
import etanah.service.StrataPtService;
import etanah.view.strata.CommonService;
import etanah.service.common.LelongService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nordiyana
 */
public class suspendedUrusanPenarikanBalikValidator implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PengambilanService1 pengambilanService1;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    private static final Logger LOG = Logger.getLogger(suspendedUrusanPenarikanBalikValidator.class);
    private String idSebelum;
    @Inject
    CommonService comm;
    @Inject
    StrataPtService strService;
    @Inject
    etanah.Configuration conf;
    @Inject
    LelongService lelongService;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();

        Permohonan psblm = permohonan.getPermohonanSebelum();
        List<HakmilikPermohonan> senaraiHakmilikPsblm = psblm.getSenaraiHakmilik();
        List<HakmilikPermohonan> senaraiHakmilikPB = permohonan.getSenaraiHakmilik();





        if (permohonan.getPermohonanSebelum() != null) {
            Pengguna p = (Pengguna) context.getPengguna();
            InfoAudit ia = p.getInfoAudit();
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDiKemaskiniOleh(permohonan.getInfoAudit().getDimasukOleh());
            FasaPermohonan fasaPermohonan = pengambilanService1.findFasaPermohonan(idPermohonan);
            FasaPermohonan fasaPermohonanMMK = pengambilanService1.findFasaPermohonanMMK(idPermohonan);
            LOG.info("-------fasaPermohonanMMK : " + fasaPermohonanMMK.getKeputusan().getKod());

            if (fasaPermohonan.getKeputusan().getKod().equals("TL")) {
                LOG.info("--------Tolak!!!-------");
                proposedOutcome = "A";
                permohonan = permohonanDAO.findById(psblm.getIdPermohonan());
                permohonan.setStatus("A");
                lelongService.saveOrUpdate(permohonan);
            }
            if (fasaPermohonan.getKeputusan().getKod().equals("L")) {
                LOG.info("--------Lulus!!!-------");
                HakmilikPermohonan permohonanHakmilik = new HakmilikPermohonan();
                permohonan = permohonanDAO.findById(psblm.getIdPermohonan());

//                for(HakmilikPermohonan senaraiHakmilik : senaraiHakmilikPsblm){
                for (int i = 0; i < senaraiHakmilikPsblm.size(); i++) {
                    try{
                    if (senaraiHakmilikPB.get(i).getHakmilik().getIdHakmilik().equals(senaraiHakmilikPsblm.get(i).getHakmilik().getIdHakmilik()) && psblm.getIdPermohonan().equals(senaraiHakmilikPsblm.get(i).getPermohonan().getIdPermohonan()) ) {
                        permohonanHakmilik = pengambilanService1.findByIdHakmilikIdPermohonan(psblm.getIdPermohonan(), senaraiHakmilikPsblm.get(i).getHakmilik().getIdHakmilik());
                        permohonanHakmilik.setPenarikBalikan("1");
                        hakmilikPermohonanDAO.saveOrUpdate(permohonanHakmilik);
                    }
                    }catch(Exception j){LOG.debug("error update hakmilik >> "+j.getMessage());
                    }
                }
//                                proposedOutcome = "BP";
//                                permohonan = permohonanDAO.findById(psblm.getIdPermohonan());
//                                permohonan.setStatus("BP");
//                                lelongService.saveOrUpdate(permohonan);
            }







        }




        return proposedOutcome;
    }

    public String getIdSebelum() {
        return idSebelum;
    }

    public void setIdSebelum(String idSebelum) {
        this.idSebelum = idSebelum;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
