
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author w.fairul
 */
public class InitiateHKBMValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private static final Logger LOG = Logger.getLogger(InitiateHKBMValidator.class);
    private Hakmilik hakmilik;
    private String idHakmilik;

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

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
        String stageName = context.getStageName() ;
        if (!permohonan.getKodUrusan().getKod().equals("PBGSA")) {
            if (proposedOutcome.equals("LO")) {
                //            permohonan.getPermohonanSebelum().
                LOG.info("Initiate HKBM");
                Pengguna peng = (Pengguna) context.getPengguna();
//        InfoAudit infoAudit = new InfoAudit();
//        infoAudit.setDimasukOleh(peng);
//        infoAudit.setTarikhMasuk(new java.util.Date());
//        idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
//        String[] name = {"idHakmilik"};
//        Object[] value = {idHakmilik};
//        List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
                String idMohon = context.getPermohonan().getIdPermohonan();
//        idHakmilik = "" + a ;
                String[] name = {"idMohon"};
                Object[] value = {idMohon};
                List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                KodUrusan kod = kodUrusanDAO.findById("HKBM");
                LOG.info(kod.getNama());
                LOG.info(permohonan.getFolderDokumen());
                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan, stageName);
            }
        } else if (permohonan.getKodUrusan().getKod().equals("PBGSA")) {
            //            permohonan.getPermohonanSebelum().
            LOG.info("Initiate HKBM");
            Pengguna peng = (Pengguna) context.getPengguna();
            //        InfoAudit infoAudit = new InfoAudit();
            //        infoAudit.setDimasukOleh(peng);
            //        infoAudit.setTarikhMasuk(new java.util.Date());
            //        idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            //        String[] name = {"idHakmilik"};
            //        Object[] value = {idHakmilik};
            //        List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
            String idMohon = context.getPermohonan().getIdPermohonan();
            //        idHakmilik = "" + a ;
            /*String[] name = {"idMohon"};
            Object[] value = {idMohon};
             * */
            idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            String[] name = {"idMohon"};
             Object[] value = {idMohon};
            List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
            // kod = kodUrusanDAO.findById("HKBM"); Changes based on email from Faizal Ali 12/04/2012
            // KodUrusan kod = kodUrusanDAO.findById("HKSTB");            
            KodUrusan kod = kodUrusanDAO.findById("HKBM");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());           
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan, stageName);
        }

        return proposedOutcome;
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
