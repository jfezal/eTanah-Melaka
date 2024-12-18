/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

/**
 *
 * @author khairul.hazwan
 */
import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodStatusUlasanJabatanTeknikal;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.UlasanJabatanTeknikal;
import etanah.service.PelupusanService;
import etanah.service.PengambilanService;
import etanah.service.common.TaskDebugService;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanah;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Rohan
 */
public class JabatanTeknikalValidator implements StageListener {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    private TaskDebugService ts;
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    private List<PermohonanKelompok> senaraiPermohonanKelompok;
    private static final Logger LOG = Logger.getLogger(JabatanTeknikalValidator.class);
    @Inject
    SBMSIntegrationFlowService sBMSIntegrationFlowService;

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
        Pengguna pengguna = context.getPengguna();
        IWorkflowContext ctxW = null;
        senaraiRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
        if (permohonan.getKodUrusan().getKod().equals("PTSP")) {
            senaraiRujukanLuar = pengambilanService.getSenaraiRujLuarByIDPermohonanAgensi1(permohonan.getIdPermohonan());
        } else {
            senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi(permohonan.getIdPermohonan());
        }
        Boolean flagSubmit = Boolean.TRUE;
//            LOG.info("--------------senaraiRujukanLuar------------------------"+senaraiRujukanLuar.size());
        for (PermohonanRujukanLuar prl : senaraiRujukanLuar) {
            if (StringUtils.isNotBlank(prl.getUlasanMandatori())) {
                if (prl.getUlasanMandatori().equals("Y")) {
                    if (prl.getUlasan() == null) {
                        context.addMessage("Sila Masukkan Ulasan Teknikal Yang Mandatori.");
                        flagSubmit = Boolean.FALSE;
                        return null;
                    }
                }
            }
        }

        if (flagSubmit) {
            List<UlasanJabatanTeknikal> senaraiJTekUlas = new ArrayList<UlasanJabatanTeknikal>();
            senaraiJTekUlas = pelupusanService.findUlasanJabatanTeknikal(permohonan.getIdPermohonan());
            for (UlasanJabatanTeknikal jTekUlas : senaraiJTekUlas) {
                if (jTekUlas.getKodStatusUlasanJabatanTeknikal().getKod().equals("BAR")) {
                    KodStatusUlasanJabatanTeknikal kodStsJTek = new KodStatusUlasanJabatanTeknikal();
                    kodStsJTek = disLaporanTanahService.getKodStatusUlasanJabatanTeknikalDAO().findById("SEL");
                    jTekUlas.setInfoAudit(disLaporanTanahService.findAudit(jTekUlas, "update", pengguna));
                    jTekUlas.setKodStatusUlasanJabatanTeknikal(kodStsJTek);
                    disLaporanTanahService.getjTeknikalService().saveOrUpdateUlasanJTek(jTekUlas);
                }
            }
        }
        //Check kelompok 
        senaraiPermohonanKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohon(permohonan.getIdPermohonan());
        try {
            if (senaraiPermohonanKelompok.size() > 0) {
                PermohonanKelompok permohonanKelompok = senaraiPermohonanKelompok.get(0);
                Permohonan p = permohonanKelompok.getPermohonanInduk();
                List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
                DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
                int count = 0;
                List<PermohonanKelompok> senaraiKelompokIdMohonInduk = new ArrayList<PermohonanKelompok>();
                senaraiKelompokIdMohonInduk = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(permohonanKelompok.getPermohonanInduk().getIdPermohonan());
                if (senaraiKelompokIdMohonInduk.size() > 0) {
                    if (senaraiKelompokIdMohonInduk.get(0).getJenisKelopok().equals("T") || senaraiKelompokIdMohonInduk.get(0).getJenisKelopok().equals("R")) {
                        for (PermohonanKelompok pk : senaraiKelompokIdMohonInduk) {
                            if (pk.getPermohonan() != null) {
                                senaraiPermohonan.add(pk.getPermohonan());
                            }
                            Map m = ts.traceTask(pk.getPermohonan().getIdPermohonan());
                            String idAliranTerkini = (String) m.get("stage");

                            //
                            if (idAliranTerkini.equals("ulasanadunteksediajkbb")) {
                                FasaPermohonan fasaPermohonan = new FasaPermohonan();
                                fasaPermohonan = disLaporanTanahService.getPelupusanService().findMohonFasaByIdMohonIdPengguna(pk.getPermohonan().getIdPermohonan(), "ulasanadunteksediajkbb");

                                if (fasaPermohonan != null) {
                                    if (fasaPermohonan.getKeputusan() != null) {
                                        if (fasaPermohonan.getKeputusan().getKod().equals("Y")) {
                                            count++;
                                        }
                                    }
                                }
                            } else if (idAliranTerkini.equals("sediarencanaptg")) {
                                FasaPermohonan fasaPermohonan = new FasaPermohonan();
                                fasaPermohonan = disLaporanTanahService.getPelupusanService().findMohonFasaByIdMohonIdPengguna(pk.getPermohonan().getIdPermohonan(), "sediarencanaptg");

                                if (fasaPermohonan != null) {
                                    if (fasaPermohonan.getKeputusan() != null) {
                                        if (fasaPermohonan.getKeputusan().getKod().equals("Y")) {
                                            count++;
                                        }
                                    }
                                }
                            } else if (idAliranTerkini.equals("ulasanadunteksediapertimb")) {
                                FasaPermohonan fasaPermohonan = new FasaPermohonan();
                                fasaPermohonan = disLaporanTanahService.getPelupusanService().findMohonFasaByIdMohonIdPengguna(pk.getPermohonan().getIdPermohonan(), "ulasanadunteksediapertimb");

                                if (fasaPermohonan != null) {
                                    if (fasaPermohonan.getKeputusan() != null) {
                                        if (fasaPermohonan.getKeputusan().getKod().equals("Y")) {
                                            count++;
                                        }
                                    }
                                }
                            } else {
                                context.addMessage("Permohonan ini tidak boleh diselesai kerana terlibat dengan permohonan bertindih.");
                                return null;
                            }

                        }
                        LOG.info("Size Senarai Kelompok : " + senaraiKelompokIdMohonInduk.size());
                        LOG.info("Size Count : " + count);
                        if (count == senaraiKelompokIdMohonInduk.size()) {
                            if (senaraiPermohonan.size() > 0) {
                                for (Permohonan pr : senaraiPermohonan) {
                                    InfoAudit ia = new InfoAudit();
                                    ia = pr.getInfoAudit();
                                    pr.setStatus("SD");
                                    pr.setInfoAudit(ia);
                                    pelupusanService.simpanPermohonan(pr);
                                }
                            }

                            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                                    p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                                    p.getKodUrusan().getNama());
                            LOG.info("Try skip stage");
                            String taskId = new String();
                            String nextStage = new String();
                            ctxW = WorkFlowService.authenticate(pengguna.getIdPengguna());
                            List<Task> taskList = null;
                            do {
                                taskList = WorkFlowService.queryTasksByIdMohon(ctxW, p.getIdPermohonan());
                            } while (taskList.size() == 0);
                            if (taskList.size() > 0) {
                                Task a = taskList.get(0);
                                taskId = a.getSystemAttributes().getTaskId();
                                LOG.info("Task Id :" + taskId);
                                WorkFlowService.acquireTask(taskId, ctxW);
                                nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxW, "L");
                                LOG.info("Stage Permohonan Berkelompok :" + nextStage);
                                LOG.info("Copy Data (HakmilikPermohonan)");
                                HakmilikPermohonan hp = new HakmilikPermohonan();
                                hp = permohonan.getSenaraiHakmilik().get(0); //Default Hakmilikpermohonan
                                if (hp != null) {
                                    HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                                    mohonHakmilikBaru.setInfoAudit(p.getInfoAudit());
                                    mohonHakmilikBaru.setPermohonan(p);
                                    mohonHakmilikBaru.setKeteranganCukaiBaru(hp.getKeteranganCukaiBaru() != null ? hp.getKeteranganCukaiBaru() : new String());
                                    mohonHakmilikBaru.setKadarPremium(hp.getKadarPremium() != BigDecimal.ZERO ? hp.getKadarPremium() : BigDecimal.ZERO);
                                    mohonHakmilikBaru.setSyaratNyataBaru(hp.getSyaratNyataBaru() != null ? hp.getSyaratNyataBaru() : null);
                                    mohonHakmilikBaru.setSekatanKepentinganBaru(hp.getSekatanKepentinganBaru() != null ? hp.getSekatanKepentinganBaru() : null);
                                    mohonHakmilikBaru.setKodMilik(hp.getKodMilik() != null ? hp.getKodMilik() : null);
                                    mohonHakmilikBaru.setAgensiUpahUkur(hp.getAgensiUpahUkur() != null ? hp.getAgensiUpahUkur() : null);
                                    if (hp.getHakmilik() != null) {
                                        mohonHakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, mohonHakmilikBaru, hp.getHakmilik(), new String[]{}, disLaporanTanahService);
                                        mohonHakmilikBaru.setHakmilik(hp.getHakmilik());
                                    } else {
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
                                        mohonHakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, mohonHakmilikBaru, hp.getHakmilik(), new String[]{kbpm, ksek, khakmilik, klot, hp.getNoLot(), hp.getLokasi(), kktanah, ksyarat, kguna, ksekatan, hp.getPegangan(), tP, luas, kuom, cukai}, disLaporanTanahService);
                                    }
                                    disLaporanTanahService.getPelupusanService().simpanHakmilikPermohonan(mohonHakmilikBaru);

                                    NoPt noPt = new NoPt();
                                    noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{Long.toString(hp.getId())}, 0);
                                    if (noPt != null) {
                                        LOG.info("Copy Data(No PT)");
                                        NoPt noPtNew = new NoPt();
                                        noPtNew.setInfoAudit(p.getInfoAudit());
                                        noPtNew.setHakmilikPermohonan(mohonHakmilikBaru);
                                        noPtNew.setKodBpm(noPt.getKodBpm() != null ? noPt.getKodBpm() : null);
                                        noPtNew.setNoPtSementara(noPt.getNoPtSementara());

                                        disLaporanTanahService.getPelupusanService().simpanSaveOnlyNoPt(noPtNew);
                                    }

                                    LOG.info("Copy Data (Laporan Tanah)");
                                    LaporanTanah lt = new LaporanTanah();
                                    lt = disLaporanTanahService.getPelupusanService().findLaporanTanahByIdPermohonan(permohonan.getIdPermohonan());
                                    if (lt != null) {
                                        LaporanTanah mohonLaporTanah = new LaporanTanah();
                                        List<LaporanTanahSempadan> laporTanahSempadanList = new ArrayList<LaporanTanahSempadan>();
                                        laporTanahSempadanList = disLaporanTanahService.getPelupusanService().findLaporTanahSmpdnByIdLapor(lt.getIdLaporan());
                                        DisLaporanTanah disLaporanTanah = new DisLaporanTanah();
                                        mohonLaporTanah.setInfoAudit(p.getInfoAudit());
                                        mohonLaporTanah.setPermohonan(p);
                                        mohonLaporTanah.setHakmilikPermohonan(mohonHakmilikBaru);
                                        mohonLaporTanah = disLaporanTanah.copyPropertiesLaporanTanah(p, mohonLaporTanah, lt, disLaporanTanahService);

                                        disLaporanTanahService.getPlpservice().simpanLaporanTanah(mohonLaporTanah);

                                        if (laporTanahSempadanList.size() > 0) {
                                            for (LaporanTanahSempadan lts : laporTanahSempadanList) {
                                                LaporanTanahSempadan ltsTemp = new LaporanTanahSempadan();
                                                ltsTemp.setInfoAudit(p.getInfoAudit());
                                                ltsTemp.setLaporanTanah(mohonLaporTanah);
                                                ltsTemp.setGuna(lts.getGuna());
                                                ltsTemp.setKeadaanTanah(lts.getKeadaanTanah());
                                                ltsTemp.setJenisSempadan(lts.getJenisSempadan());
                                                ltsTemp.setMilikKerajaan(lts.getMilikKerajaan());
                                                ltsTemp.setNoLot(lts.getNoLot() != null ? lts.getNoLot() : new String());
                                                ltsTemp.setKodLot(lts.getKodLot() != null ? lts.getKodLot() : null);
                                                disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(ltsTemp);
                                            }
                                        }
                                    }
                                }

                                LOG.info("Copy Data (MohonRujukanLuar)");
                                if (permohonan.getSenaraiRujukanLuar().size() > 0) {
                                    for (PermohonanRujukanLuar prl : permohonan.getSenaraiRujukanLuar()) {
                                        PermohonanRujukanLuar prlTemp = new PermohonanRujukanLuar();
                                        prlTemp.setInfoAudit(p.getInfoAudit());
                                        prlTemp.setAgensi(prl.getAgensi() != null ? prl.getAgensi() : null);
                                        prlTemp.setPermohonan(p);
                                        prlTemp.setUlasan(prl.getUlasan() != null ? prl.getUlasan() : new String());
                                        prlTemp.setNamaAgensi(prl.getNamaAgensi() != null ? prl.getNamaAgensi() : new String());
                                        prlTemp.setNoRujukan(prl.getNoRujukan() != null ? prl.getNoRujukan() : new String());
                                        prlTemp.setTarikhRujukan(prl.getTarikhRujukan() != null ? prl.getTarikhRujukan() : new java.util.Date());
                                        prlTemp.setKodRujukan(prl.getKodRujukan());
                                        prlTemp.setCawangan(prl.getCawangan());

                                        disLaporanTanahService.getPelupusanService().simpanPermohonanRujukanLuar(prlTemp);
                                    }
                                }
                            }

                            context.addMessage("Permohonan ini telah berjaya dihantar");
                            return null;
                        } else {
                            context.addMessage("Permohonan ini tidak boleh diselesai kerana terlibat dengan permohonan berkelompok.");
                            return null;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(JabatanTeknikalValidator.class.getName()).log(Level.SEVERE, null, ex);
        }

//        if (context.getStageName().equals("ulasanadunteksediajkbb") && context.getPermohonan().getKodUrusan().getKod().equals("SBMS")) {
//
//            sBMSIntegrationFlowService.initiateRencanaJKBB(context.getPermohonan());
//        }
        if (context.getStageName().equals("ulasan_adun") && context.getPermohonan().getKodUrusan().getKod().equals("SBMS")) {

            sBMSIntegrationFlowService.insertTugasanTable(permohonan, "UJTA", pengguna.getIdPengguna());
        }
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
