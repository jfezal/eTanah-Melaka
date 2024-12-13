
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanUrusan;
import etanah.service.ConsentPtdService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class IntegrationPendaftaran implements StageListener {

    @Inject
    ConsentPtdService consentService;
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
    private static final Logger LOG = Logger.getLogger(IntegrationPendaftaran.class);
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

        if (permohonan.getKeputusan() != null) {
            if (permohonan.getKeputusan().getKod().equals("L")) {

                LOG.info("Integration process....");
                Pengguna pengguna = (Pengguna) context.getPengguna();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

                List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();

                for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                    senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());
                }

                KodUrusan kodUrusan = new KodUrusan();

                PermohonanUrusan permohonanUrusan = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "URUSAN BANTAHAN");

                LOG.info("1.---->>" + permohonanUrusan.getCatatan());

                if (permohonanUrusan.getCatatan().equals("TMADT")) {
                    kodUrusan = kodUrusanDAO.findById("TMAMB");
                } else if (permohonanUrusan.getCatatan().equals("PMADT") || permohonanUrusan.getCatatan().equals("CGADT")) {
                    kodUrusan = kodUrusanDAO.findById("PMTB");
                }

                LOG.info("2.---->>" + kodUrusan.getNama());
                LOG.info(permohonan.getFolderDokumen());
                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kodUrusan, pengguna, senaraiHakmilik, permohonan);
            }
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
