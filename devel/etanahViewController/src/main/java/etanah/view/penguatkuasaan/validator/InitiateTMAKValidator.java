
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.EnforceService;
import etanah.service.StrataPtService;
import etanah.service.common.EnforcementService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
public class InitiateTMAKValidator implements StageListener {

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
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(InitiateTMAKValidator.class);
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String stageId;
    private String keputusan;
    private PermohonanNota permohonanNota;

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

        //permohonan.getPermohonanSebelum().
//        if (permohonan.getKeputusan().getKod().equals("WJ")) {
        LOG.info("Initiate TMAK");
        Pengguna peng = (Pengguna) context.getPengguna();

        stageId = context.getStageName();
        LOG.info("--------------stage id------------- : " + stageId);

        permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
        LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
        try {

            LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
            if (permohonanNota != null) {
                LOG.info("::: kandungan nota :" + permohonanNota.getNota());
                context.addMessage("Sila Masukkan Nota/Kertas Minit Untuk Permohonan : " + permohonan.getIdPermohonan());
                return null;
            }


            List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();

            for (FasaPermohonan fp : senaraiFasa) {
                if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                    if (fp.getKeputusan() != null) {
                        keputusan = fp.getKeputusan().getKod();
                        LOG.info("--------------keputusan untuk stage " + stageId + "--------------- : " + keputusan);
                    }
                }
            }

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                    if (keputusan.equalsIgnoreCase("HK") || keputusan.equalsIgnoreCase("HA")) {
                        initiateTMAK(permohonan, peng, stageId);
                    }
                }

            } else {
                initiateTMAK(permohonan, peng, stageId);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }

        return proposedOutcome;
//        return null;
    }

    public void initiateTMAK(Permohonan permohonan, Pengguna pengguna, String stageId) {
        LOG.info("-------initiateTMAK-------");
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();

        List<HakmilikPermohonan> listHakmilikPermohonan = permohonan.getSenaraiHakmilik();

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49") && stageId.equalsIgnoreCase("kpsn_siasatan")) {
            listHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getPermohonanSebelum().getIdPermohonan());

            if (!listHakmilikPermohonan.isEmpty()) {

                if (listHakmilikPermohonan.get(0).getNomborRujukan() != null) {
                    listHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getPermohonanSebelum().getIdPermohonan(), permohonan.getIdPermohonan());
                }

            }
        }
        for (HakmilikPermohonan hakmilikPermohonan : listHakmilikPermohonan) {
            senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());
        }

        PermohonanRujukanLuar mohonRujukanLuar = new PermohonanRujukanLuar();
        mohonRujukanLuar.setNoRujukan(idHakmilik);
        mohonRujukanLuar.setTarikhRujukan(now);
        KodUrusan kod = kodUrusanDAO.findById("TMAK");
        LOG.info(kod.getNama());
        LOG.info(permohonan.getFolderDokumen());
//        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, pengguna, senaraiHakmilik, permohonan);
        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, pengguna, senaraiHakmilik, permohonan, stageId, listHakmilikPermohonan);
    }

    @Override
    public void afterComplete(StageContext context) {
        Permohonan permohonan = context.getPermohonan();

        stageId = context.getStageName();
        LOG.info("--------------stage id------------- : " + stageId);

        permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
        LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
        if (permohonanNota == null) {
            LOG.info("::: kandungan nota tidak null ::: ");
            PermohonanNota nota = enforcementService.findNotEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
            if (nota != null) {
                LOG.info("::: update status nota to T = tidak aktif ::: ");
                nota.setStatusNota('T');
                enforceService.simpanNota(nota);
            }
        }
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

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

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public PermohonanNota getPermohonanNota() {
        return permohonanNota;
    }

    public void setPermohonanNota(PermohonanNota permohonanNota) {
        this.permohonanNota = permohonanNota;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}