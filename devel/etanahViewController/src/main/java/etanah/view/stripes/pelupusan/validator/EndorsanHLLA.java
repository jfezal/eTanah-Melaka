/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.HakmilikPermohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.service.NotifikasiService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import etanah.service.common.HakmilikService;
import etanah.service.common.PermohonanRujukanLuarService;
import org.apache.log4j.Logger;

/**
 *
 * @author 
 */
public class EndorsanHLLA implements StageListener {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanRujukanLuarService mrlservice;
    @Inject
    HakmilikService hakmilikservice;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String keputusan;
    private static final Logger LOG = Logger.getLogger(EndorsanHLLA.class);

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
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
        LOG.info("Initiate HLLA");

        Pengguna peng = (Pengguna) context.getPengguna();
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
            senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());
        }


        if (context.getStageName().equals("35SmkdanCtkTndtngn")) {
            KodUrusan kod = kodUrusanDAO.findById("HLLA");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());

            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan, context.getStageName());

        } else if (context.getStageName().equals("A02Penempatan")) {
            KodUrusan kod = kodUrusanDAO.findById("HSBM");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());

            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan, context.getStageName());

        } else if (context.getStageName().equals("A04Penempatan")) {
            KodUrusan kod = kodUrusanDAO.findById("HKBM");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());

            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan, context.getStageName());
        } else if (context.getStageName().equals("45PenyediaanBrgJdlPertama")) {
            KodUrusan kod = kodUrusanDAO.findById("IRMB");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());

            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan, context.getStageName());
        } else if (context.getStageName().equals("g_kemaskini_hakmilik")) {
            KodUrusan kod = kodUrusanDAO.findById("LMTP");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());

            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan, context.getStageName());
        }
        
//        }

        return proposedOutcome;
//        return null;
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
