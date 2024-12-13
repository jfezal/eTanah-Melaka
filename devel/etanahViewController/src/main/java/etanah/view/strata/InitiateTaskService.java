/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.StrataPtService;
import etanah.service.common.TaskDebugService;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
public class InitiateTaskService {

    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    StrataPtService strService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    private static final Logger LOG = Logger.getLogger(InitiateTaskService.class);
    Pengguna pengguna;
    @Inject
    private GeneratorIdPermohonan idGenMohon;
    @Inject
    private TaskDebugService tds;

    public Permohonan createPermohonanBaru(Permohonan permohonan, KodUrusan ku) throws WorkflowException, StaleObjectException, Exception {
        KodCawangan caw = pengguna.getKodCawangan();
        Permohonan p = new Permohonan();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        FolderDokumen fd = new FolderDokumen();
        long idFolder = Long.parseLong(tarikh);
//            fd = permohonan.getFolderDokumen();
        fd.setTajuk("TEST_" + tarikh); // TODO
        fd.setInfoAudit(ia);
        fd.setFolderId(idFolder);
        folderDokumenDAO.save(fd);
        p.setStatus("TA");
        p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
        p.setCawangan(pengguna.getKodCawangan());
        p.setKodUrusan(ku);
        p.setFolderDokumen(fd);
        if (permohonan != null) {
            if(permohonan.getKodUrusan().getKod().equals("PNB")){
                p.setPermohonanSebelum(permohonan.getPermohonanSebelum());
            }else{
                p.setPermohonanSebelum(permohonan);
            }
            p.setPenyerahNama(permohonan.getPenyerahNama());
            p.setPenyerahNoRujukan(permohonan.getPenyerahNoRujukan());
            p.setKodPenyerah(permohonan.getKodPenyerah());
            p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
            p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
            if (permohonan.getPenyerahAlamat3() != null) {
                p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
            }

            if (permohonan.getPenyerahAlamat4() != null) {
                p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
            }
            p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
            p.setPenyerahNegeri(permohonan.getPenyerahNegeri());
            p.setIdWorkflow(p.getKodUrusan().getIdWorkflowIntegration());

        }
        p.setInfoAudit(ia);
        savePermohonan(p);
        /*WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
         p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
         p.getKodUrusan().getNama());*/
        Hakmilik hk = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
        WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                p.getIdPermohonan(), hk.getCawangan().getKod(), pengguna.getIdPengguna(),
                p.getKodUrusan().getNama());
        return p;
    }

    public void setHakmilikPermohonan(Permohonan permohonan, Permohonan permohonanBaru) {
        for (HakmilikPermohonan mh : permohonan.getSenaraiHakmilik()) {
            HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
            mohonHakmilik.setPermohonan(permohonanBaru);
            mohonHakmilik.setHakmilik(mh.getHakmilik());
            mohonHakmilik.setInfoAudit(permohonanBaru.getInfoAudit());
            mohonHakmilik.setHakmilik(mh.getHakmilik());
            mohonHakmilik = strService.saveHakmilikPermohonan(mohonHakmilik);
        }
    }

    public void setMohonPihak(Permohonan permohonanBaru, Hakmilik hakmilik, String kodPB) {
        PermohonanPihak mohonPihak = new PermohonanPihak();
        mohonPihak.setPihak(strService.findPihakByIdHakmilikNKod(hakmilik.getIdHakmilik(), kodPB).getPihak());
        mohonPihak.setCawangan(permohonanBaru.getCawangan());
        mohonPihak.setHakmilik(hakmilik);
        mohonPihak.setInfoAudit(permohonanBaru.getInfoAudit());
        mohonPihak.setJenis(kodJenisPihakBerkepentinganDAO.findById(kodPB));
        mohonPihak.setPermohonan(permohonanBaru);
        mohonPihak = strService.saveMohonPihak(mohonPihak);
    }

    public void setMohonRujukanLuar(Permohonan permohonanBaru, String noRuj, String kodRuj) {
        PermohonanRujukanLuar mohonRujukLuar = new PermohonanRujukanLuar();
        mohonRujukLuar.setPermohonan(permohonanBaru);
        mohonRujukLuar.setCawangan(permohonanBaru.getCawangan());
        mohonRujukLuar.setNoRujukan(noRuj);
        //mohonRujukLuar.setNoFail(permohonanBaru.getIdPermohonan());
        mohonRujukLuar.setNoFail(permohonanBaru.getPermohonanSebelum().getIdPermohonan());
        mohonRujukLuar.setInfoAudit(permohonanBaru.getInfoAudit());
        mohonRujukLuar.setTarikhRujukan(new Date());
        mohonRujukLuar.setKodRujukan(kodRujukanDAO.findById(kodRuj));
        mohonRujukLuar = strService.saveMohonRujukLuar(mohonRujukLuar);
    }

    public Permohonan generatePermohonanBaru(Permohonan permohonan, KodUrusan ku, Pengguna pengguna) throws WorkflowException, StaleObjectException, Exception {
        LOG.info("----initiating ID Mohon----:");
        KodCawangan caw = permohonan.getCawangan();
        Permohonan p = new Permohonan();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        FolderDokumen fd = new FolderDokumen();
        long idFolder = Long.parseLong(tarikh);
//            fd = permohonan.getFolderDokumen();
        fd.setTajuk("TEST_" + tarikh); // TODO
        fd.setInfoAudit(ia);
        fd.setFolderId(idFolder);
        folderDokumenDAO.save(fd);
        p.setStatus("TA");
        p.setIdPermohonan(idGenMohon.generate(conf.getProperty("kodNegeri"), caw, ku));
        LOG.info("----ID Mohon Generated----:");
        p.setCawangan(permohonan.getCawangan());
        p.setKodUrusan(ku);
        p.setFolderDokumen(fd);
        if (permohonan != null) {
            p.setPermohonanSebelum(permohonan);
            p.setPenyerahNama(permohonan.getPenyerahNama());
            p.setKodPenyerah(permohonan.getKodPenyerah());
            p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
            p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
            p.setIdWorkflow(p.getKodUrusan().getIdWorkflow());
            if (permohonan.getPenyerahAlamat3() != null) {
                p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
            }

            if (permohonan.getPenyerahAlamat4() != null) {
                p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
            }
            p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
            p.setPenyerahNegeri(permohonan.getPenyerahNegeri());
            //added by murali: saving stageIdSblm
            Map m = tds.traceTask(permohonan.getIdPermohonan());
            String stageId = (String) m.get("stage");
            LOG.info("--stageId--:" + stageId);
            if (stageId != null) {
                p.setIdStagePermohonanSebelum(stageId);
            }
        }
        p.setInfoAudit(ia);
        //permohonanDAO.save(p);
        savePermohonan(p);
        LOG.info("----generated IDMohon----:" + p.getIdPermohonan());
        LOG.info("----generated kodurusan----:" + p.getKodUrusan().getKod());
        WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                p.getIdPermohonan(), p.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                p.getKodUrusan().getNama());

        return p;
    }

    @Transactional
    public void savePermohonan(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}
