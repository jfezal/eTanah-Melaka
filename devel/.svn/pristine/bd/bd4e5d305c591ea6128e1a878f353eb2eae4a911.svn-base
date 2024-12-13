/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.initiateService;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodCaraPermohonanDAO;
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
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.Date;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author shazwan referring to faidzal strata
 */
public class InitiateTaskService {

    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    private GeneratorIdPermohonan idGeneratorIdPermohonan;
    @Inject
    StrataPtService strService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
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
    @Inject
    KodCaraPermohonanDAO kodCaraPermohonanDAO ;
    private static final Logger LOG = Logger.getLogger(InitiateTaskService.class);
    Pengguna pengguna;

    public Permohonan createPermohonanBaru(Permohonan permohonan, KodUrusan ku, String stageName) throws WorkflowException, StaleObjectException, Exception {
        KodCawangan caw = new KodCawangan();
        String kodNegeri = conf.getProperty("kodNegeri");
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTGSA") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT")) {
            caw = permohonan.getCawangan();
        } else {
            if (!permohonan.getSenaraiHakmilik().isEmpty()) {
                caw = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getCawangan();
                LOG.info("caw:---->>> "+ caw);
            } else {
                caw = pengguna.getKodCawangan();
            }
        }
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
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT") && kodNegeri.equals("05")) { //Initiate urusan lulus bersyarat
            p.setIdPermohonan(idGeneratorIdPermohonan.generate(conf.getProperty("kodNegeri"), caw, ku));
        } else {
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            LOG.info(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
        }

        p.setCawangan(caw);
        p.setKodUrusan(ku);
        p.setFolderDokumen(fd);
        if (ku.getIdWorkflowIntegration() != null) {
            p.setIdWorkflow(ku.getIdWorkflowIntegration());
        } else {
            p.setIdWorkflow(ku.getIdWorkflow());
        }
        p.setIdStagePermohonanSebelum(stageName);
        p.setCaraPermohonan(kodCaraPermohonanDAO.findById("UD"));
        if (permohonan != null) {
            p.setPermohonanSebelum(permohonan);
//            p.setPenyerahNama(permohonan.getPenyerahNama());
            p.setPenyerahNama(("Unit Pelupusan"));
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

        }
        p.setInfoAudit(ia);
        permohonanDAO.save(p);
//        LOG.info("Kod Urusan Initiate :" + p.getKodUrusan().getKod());
//        LOG.info("Id Workflow Integration :" + p.getKodUrusan().getIdWorkflowIntegration());
        /*WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
         p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
         p.getKodUrusan().getNama());*/
        //Please consult with Shazwan first
        if (ku.getIdWorkflowIntegration() != null) {
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                    p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());
        } else {
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                    p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());
        }
        return p;
    }

    public void setHakmilikPermohonan(Permohonan permohonan, Permohonan permohonanBaru) {
        for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
            HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
            DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
            mohonHakmilik.setPermohonan(permohonanBaru);
            if (hp.getHakmilik() != null) {
                mohonHakmilik.setHakmilik(hp.getHakmilik());
            }
            String kbpm = new String();
            String ksek = new String();
            String khakmilik = new String();
            String klot = new String();
            String kktanah = new String();
            String ksyarat = new String();
            String kguna = new String();
            String ksekatan = new String();
            String tP = new String();
            String luas = new String();
            String kuom = new String();
            String cukai = new String();

            kbpm = hp.getBandarPekanMukimBaru() != null ? String.valueOf(hp.getBandarPekanMukimBaru().getKod()) : null;
            ksek = hp.getKodSeksyen() != null ? String.valueOf(hp.getKodSeksyen().getKod()) : null;
            khakmilik = hp.getKodHakmilik() != null ? hp.getKodHakmilik().getKod() : null;
            klot = hp.getLot() != null ? hp.getLot().getKod() : null;
            kktanah = hp.getKategoriTanahBaru() != null ? hp.getKategoriTanahBaru().getKod() : null;
            ksyarat = hp.getSyaratNyataBaru() != null ? hp.getSyaratNyataBaru().getKod() : null;
            kguna = hp.getKodKegunaanTanah() != null ? hp.getKodKegunaanTanah().getKod() : null;
            ksekatan = hp.getSekatanKepentinganBaru() != null ? hp.getSekatanKepentinganBaru().getKod() : null;
            tP = hp.getTempohPajakan() != null ? String.valueOf(hp.getTempohPajakan()) : null;
            luas = hp.getLuasTerlibat() != null ? hp.getLuasTerlibat().toString() : null;
            kuom = hp.getKodUnitLuas() != null ? hp.getKodUnitLuas().getKod() : null;
            cukai = hp.getCukaiBaru() != null ? hp.getCukaiBaru().toString() : null;
            mohonHakmilik = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, mohonHakmilik, hp.getHakmilik(), new String[]{kbpm, ksek, khakmilik, klot, hp.getNoLot(), hp.getLokasi(), kktanah, ksyarat, kguna, ksekatan, hp.getPegangan(), tP, luas, kuom, cukai}, disLaporanTanahService);
            mohonHakmilik.setInfoAudit(permohonanBaru.getInfoAudit());
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
//        mohonRujukLuar.setNoRujukan(noRuj);
//        mohonRujukLuar.setNoFail(permohonanBaru.getIdPermohonan());
        mohonRujukLuar.setNoFail(noRuj);
//        mohonRujukLuar.setNoFail(permohonanBaru.getPermohonanSebelum().getIdPermohonan());
        mohonRujukLuar.setCatatan(permohonanBaru.getPermohonanSebelum().getSebab());
        mohonRujukLuar.setInfoAudit(permohonanBaru.getInfoAudit());

        if (!StringUtils.isEmpty(kodRuj)) {
            mohonRujukLuar.setTarikhRujukan(new Date());
            mohonRujukLuar.setKodRujukan(kodRujukanDAO.findById(kodRuj));
        }
        mohonRujukLuar = strService.saveMohonRujukLuar(mohonRujukLuar);
    }

    public void setMohonRujukanLuarMultipleHakmilik(Permohonan permohonanBaru, String noRuj, String kodRuj, Permohonan permohonan) {
        for (HakmilikPermohonan mh : permohonan.getSenaraiHakmilik()) {
            PermohonanRujukanLuar mohonRujukLuar = new PermohonanRujukanLuar();
            mohonRujukLuar.setHakmilik(mh.getHakmilik());
            mohonRujukLuar.setPermohonan(permohonanBaru);
            mohonRujukLuar.setCawangan(permohonanBaru.getCawangan());
//        mohonRujukLuar.setNoRujukan(noRuj);
//        mohonRujukLuar.setNoFail(permohonanBaru.getIdPermohonan());
            mohonRujukLuar.setNoFail(noRuj);
            mohonRujukLuar.setInfoAudit(permohonanBaru.getInfoAudit());

            if (!StringUtils.isEmpty(kodRuj)) {
                mohonRujukLuar.setTarikhRujukan(new Date());
                mohonRujukLuar.setKodRujukan(kodRujukanDAO.findById(kodRuj));
            }
            mohonRujukLuar = strService.saveMohonRujukLuar(mohonRujukLuar);
        }
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}
