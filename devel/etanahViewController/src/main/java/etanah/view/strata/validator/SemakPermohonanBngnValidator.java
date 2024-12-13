
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.KodStatusPermohonanDAO;
import etanah.model.KodStatusPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodJabatan;
import etanah.model.KodRujukan;
import etanah.model.KodKeputusan;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.PermohonanRujukanLuar;
import etanah.view.etanahContextListener;
import etanah.view.kaunter.ProsesTukarGanti;
import etanah.service.StrataPtService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import etanah.service.common.TaskDebugService;
import java.util.Map;
import etanah.workflow.WorkFlowService;

/**
 *
 * @author w.fairul
 */
public class SemakPermohonanBngnValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    etanah.Configuration conf;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodStatusPermohonanDAO kodStatusPermohonanDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private TaskDebugService tds;
    @Inject
    PenggunaDAO penggunaDao;
    private static final Logger LOG = Logger.getLogger(SemakPermohonanBngnValidator.class);
    private Hakmilik hakmilik;
    private String idHakmilik;
    private PermohonanRujukanLuar permohonanRujukanLuar;

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
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        try {
            FasaPermohonan mohonFasa = new FasaPermohonan();
//            Permohonan mohonRMHS = new Permohonan();
//            Permohonan mohonRTPS = new Permohonan();

            permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
            if (permohonan != null) {
                KodKeputusan kodkpsn = kodKeputusanDAO.findById("Z1");
                if (kodkpsn != null) {
                    permohonan.setKeputusan(kodkpsn);
                }
                KodStatusPermohonan kodsts = kodStatusPermohonanDAO.findById("AK");
                LOG.info(kodsts.getKod());
                permohonan.setStatus(kodsts.getKod());
                permohonan.setKeputusanOleh(peng);
                permohonan.setTarikhKeputusan(new Date());
                strService.updateMohon(permohonan);
            }

            //check urusan: RMHS1 registered or not if registered, have to complete it first
            List<Permohonan> mohonRMHSList = new ArrayList();

            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBS")) {
                mohonRMHSList = null;
                mohonRMHSList = strService.findIDMohonByKodUrusan(permohonan.getIdPermohonan(), "RMHS1");
                for (Permohonan mohonRMHS : mohonRMHSList) {
                    if (mohonRMHS != null) {
                        mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                        Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                        String group = (String) m.get("participant");
                        String acquserNama = (String) m.get("acquiredBy");
                        if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                            if (acquserNama == null) {
                                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                                String groupNama = peng.getPerananUtama().getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                        + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + groupNama);
                            } else {
                                peng = penggunaDao.findById(acquserNama);
                                String acqby = peng.getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                        + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + acqby);
                            }
                            return null;
                        }
                    }
                }
            }

            //check urusan: RMH1A, RMHS1 registered or not if registered, have to complete it first
            if (permohonan.getKodUrusan().getKod().equals("PBBD")) {
                mohonRMHSList = null;
                mohonRMHSList = strService.findIDMohonByKodUrusan(permohonan.getIdPermohonan(), "RMH1A");
                LOG.debug("----RMH1A Registered----:" + mohonRMHSList.size());
                for (Permohonan mohonRMHS : mohonRMHSList) {
                    LOG.debug("----RMH1A Idmohon----:" + mohonRMHS.getIdPermohonan());
                    if (mohonRMHS != null) {
                        mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                        Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                        String group = (String) m.get("participant");
                        String acquserNama = (String) m.get("acquiredBy");
                        if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                            if (acquserNama == null) {
                                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                                String groupNama = peng.getPerananUtama().getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                        + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + groupNama);
                            } else {
                                peng = penggunaDao.findById(acquserNama);
                                String acqby = peng.getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                        + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + acqby);
                            }
                            return null;
                        }
                    }
                }
                List<Permohonan> mohonRMHSList1 = new ArrayList();
                mohonRMHSList1 = null;
                mohonRMHSList1 = strService.findIDMohonByKodUrusan(permohonan.getIdPermohonan(), "RMHS1");
                LOG.debug("----RMH1A Registered----:" + mohonRMHSList.size());
                for (Permohonan mohonRMHS : mohonRMHSList1) {
                    LOG.debug("----RMH1A Idmohon----:" + mohonRMHS.getIdPermohonan());
                    if (mohonRMHS != null) {
                        mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                        Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                        String group = (String) m.get("participant");
                        String acquserNama = (String) m.get("acquiredBy");
                        if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                            if (acquserNama == null) {
                                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                                String groupNama = peng.getPerananUtama().getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                        + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + groupNama);
                            } else {
                                peng = penggunaDao.findById(acquserNama);
                                String acqby = peng.getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                        + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + acqby);
                            }
                            return null;
                        }
                    }
                }
            }

            //check urusan: RMHS5 registered or not if registered, have to complete it first
            if (permohonan.getKodUrusan().getKod().equals("PSBS")) {
                mohonRMHSList = null;
                mohonRMHSList = strService.findIDMohonByKodUrusan(permohonan.getIdPermohonan(), "RMHS5");
                LOG.debug("----RMHS5 Registered----:" + mohonRMHSList.size());
                for (Permohonan mohonRMHS : mohonRMHSList) {
                    LOG.debug("----RMHS5 Idmohon----:" + mohonRMHS.getIdPermohonan());
                    if (mohonRMHS != null) {
                        mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                        Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                        String group = (String) m.get("participant");
                        String acquserNama = (String) m.get("acquiredBy");
                        if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                            if (acquserNama == null) {
                                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                                String groupNama = peng.getPerananUtama().getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                        + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + groupNama);
                            } else {
                                peng = penggunaDao.findById(acquserNama);
                                String acqby = peng.getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                        + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + acqby);
                            }
                            return null;
                        }
                    }
                }
            }

            //check urusan: RMHS6, RTPS registered or not if registered, have to complete it first
            if (permohonan.getKodUrusan().getKod().equals("PHPC")) {
                mohonRMHSList = null;
                mohonRMHSList = strService.findIDMohonByKodUrusan(permohonan.getIdPermohonan(), "RMHS6");
                LOG.debug("----RMHS6 Registered----:" + mohonRMHSList.size());
                for (Permohonan mohonRMHS : mohonRMHSList) {
                    LOG.debug("----RMHS6 Idmohon----:" + mohonRMHS.getIdPermohonan());
                    if (mohonRMHS != null) {
                        mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                        Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                        String group = (String) m.get("participant");
                        String acquserNama = (String) m.get("acquiredBy");
                        if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                            if (acquserNama == null) {
                                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                                String groupNama = peng.getPerananUtama().getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                        + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + groupNama);
                            } else {
                                peng = penggunaDao.findById(acquserNama);
                                String acqby = peng.getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                        + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + acqby);
                            }
                            return null;
                        }
                    }
                }

                /* mohonRTPS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RTPS");
                if (mohonRTPS != null) {
                mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRTPS.getIdPermohonan(), "keputusan");
                Map m = tds.traceTask(mohonRTPS.getIdPermohonan());
                String group = (String) m.get("participant");
                String acquserNama = (String) m.get("acquiredBy");
                if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                if (acquserNama == null) {
                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                String groupNama = peng.getPerananUtama().getNama();
                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Pindah Milik Hakmilik Strata \n\n<br>"
                + "ID Permohonan: " + mohonRTPS.getIdPermohonan() + ", Pengguna: " + groupNama);
                } else {
                peng = penggunaDao.findById(acquserNama);
                String acqby = peng.getNama();
                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Pindah Milik Hakmilik Strata \n\n<br>"
                + "ID Permohonan: " + mohonRTPS.getIdPermohonan() + ", Pengguna: " + acqby);
                }
                return null;
                }
                } */
            }
            //check urusan: RMHS7, RTPS registered or not if registered, have to complete it first
            if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
                mohonRMHSList = null;
                mohonRMHSList = strService.findIDMohonByKodUrusan(permohonan.getIdPermohonan(), "RMHS7");
                LOG.debug("----RMHS7 Registered----:" + mohonRMHSList.size());
                for (Permohonan mohonRMHS : mohonRMHSList) {
                    LOG.debug("----RMHS7 Idmohon----:" + mohonRMHS.getIdPermohonan());
                    if (mohonRMHS != null) {
                        mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                        Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                        String group = (String) m.get("participant");
                        String acquserNama = (String) m.get("acquiredBy");
                        if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                            if (acquserNama == null) {
                                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                                String groupNama = peng.getPerananUtama().getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                        + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + groupNama);
                            } else {
                                peng = penggunaDao.findById(acquserNama);
                                String acqby = peng.getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                        + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + acqby);
                            }
                            return null;
                        }
                    }
                }

                /*mohonRTPS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RTPS");
                if (mohonRTPS != null) {
                mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRTPS.getIdPermohonan(), "keputusan");
                Map m = tds.traceTask(mohonRTPS.getIdPermohonan());
                String group = (String) m.get("participant");
                String acquserNama = (String) m.get("acquiredBy");
                if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                if (acquserNama == null) {
                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                String groupNama = peng.getPerananUtama().getNama();
                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Pindah Milik Hakmilik Strata \n\n<br>"
                + "ID Permohonan: " + mohonRTPS.getIdPermohonan() + ", Pengguna: " + groupNama);
                } else {
                peng = penggunaDao.findById(acquserNama);
                String acqby = peng.getNama();
                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Pindah Milik Hakmilik Strata \n\n<br>"
                + "ID Permohonan: " + mohonRTPS.getIdPermohonan() + ", Pengguna: " + acqby);
                }
                return null;
                }
                }*/
            }

            if (permohonan.getKodUrusan().getKod().equals("PHPC") || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
                KodUrusan kod = new KodUrusan();
                List<Hakmilik> senaraiHakmilik1 = new ArrayList<Hakmilik>();
                List<Hakmilik> senaraiHakmilik3 = new ArrayList<Hakmilik>();
                for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                    senaraiHakmilik3.add(hakmilikPermohonan.getHakmilik());
                }
                for (HakmilikPermohonan hakmilikPermohonan : hk) {
                    LOG.info("--adding Hakmilik to senaraiHakmilik1--" + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    senaraiHakmilik1.add(hakmilikPermohonan.getHakmilik());
                    LOG.info("--if urusan PHPP/PHPC--");
                    kod = kodUrusanDAO.findById("PBCTM");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                }
                LOG.info("--senaraiHakmilik1 size--" + senaraiHakmilik1.size());
                LOG.info("--senaraiHakmilik1 sending to pendaftaran--" + senaraiHakmilik1);
                Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPHPP(kod, peng, senaraiHakmilik1, permohonan);
                LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                LOG.info("--Sending Strata Idmohon to Reg/Saving in Mohon Rujuluar--:");
                PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
                permohonanRujLuar.setCawangan(peng.getKodCawangan());
                permohonanRujLuar.setPermohonan(mohonReg);
                permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                permohonanRujLuar.setNoRujukan("1");
                permohonanRujLuar.setHakmilik(senaraiHakmilik1.get(0));
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String formattedDate = dateFormat.format(date);
                    LOG.info("--formattedDate--" + formattedDate);
                    permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                } catch (Exception e) {
                    LOG.error(e.getMessage());
                }
                KodRujukan kodRujukan;
                kodRujukan = kodRujukanDAO.findById("FL");
                permohonanRujLuar.setKodRujukan(kodRujukan);
                strService.SimpanMohonRujukLuar(permohonanRujLuar);
                context.addMessage(" - Integrasi Dengan Unit Pendaftaran. "+mohonReg.getIdPermohonan()
                        +" - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Permohonan Pecah Bahagian/Cantum Petak).");
                LOG.info("--Saved in Mohon Rujuluar--:");
            } else {
                LOG.info("--if not urusan PHPP/PHPC--");
                List<Hakmilik> senaraiHakmilik3 = new ArrayList<Hakmilik>();
                for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                    senaraiHakmilik3.add(hakmilikPermohonan.getHakmilik());
                }
                if (!senaraiHakmilik3.isEmpty()) {
                    //Need To generate before Noting
                    LOG.info("buat urusan tukar ganti");
                    for (Hakmilik hm : senaraiHakmilik3) {
                        if (hm.getNoVersiDhde() == 0) {
                            prosesTukarGanti(peng, senaraiHakmilik3);
                        }
                    }
                }
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                String[] name = {"idHakmilik"};
                Object[] value = {idHakmilik};
                List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
                KodUrusan kod = kodUrusanDAO.findById("PBBM");
                LOG.info(kod.getNama());
                LOG.info(permohonan.getFolderDokumen());
                Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, permohonan);
                LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                LOG.info("--Sending Strata Idmohon to Reg/Saving in Mohon Rujuluar--:");
                PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
                permohonanRujLuar.setCawangan(peng.getKodCawangan());
                permohonanRujLuar.setPermohonan(mohonReg);
                permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                permohonanRujLuar.setNoRujukan("1");
                permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String formattedDate = dateFormat.format(date);
                    LOG.info("--formattedDate--" + formattedDate);
                    permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                } catch (Exception e) {
                    LOG.error(e.getMessage());
                }
                KodRujukan kodRujukan;
                kodRujukan = kodRujukanDAO.findById("FL");
                permohonanRujLuar.setKodRujukan(kodRujukan);
                strService.SimpanMohonRujukLuar(permohonanRujLuar);
                LOG.info("--Saved in Mohon Rujuluar--:");

                context.addMessage(" - Penghantaran Berjaya. "+mohonReg.getIdPermohonan()+" - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Permohonan Pecah Bahagi Bangunan).");
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
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
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        //return proposedOutcome;
        return "back";
    }

    private void prosesTukarGanti(Pengguna pengguna, List<Hakmilik> senaraiHakmilik) {
        //urusan tukar ganti
        ProsesTukarGanti tukarGanti = etanahContextListener.newInstance(ProsesTukarGanti.class);
        String kodNegeri = conf.getProperty("kodNegeri");
//        KodCawangan caw = pengguna.getKodCawangan();

        if (!senaraiHakmilik.isEmpty()) {
            KodCawangan caw = senaraiHakmilik.get(0).getCawangan();
            List<Permohonan> senaraiPermohonanTukarGanti = tukarGanti.doTukarGanti(kodNegeri, pengguna, senaraiHakmilik);
            if (!senaraiPermohonanTukarGanti.isEmpty()) {
                for (Permohonan p : senaraiPermohonanTukarGanti) {
                    KodUrusan ku = p.getKodUrusan();
                    try {
                        WorkFlowService.initiateTask(ku.getIdWorkflow(),
                                p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                                p.getKodUrusan().getNama());

                        //fikri suruh pakai getidworkflow yang biasa
//                        WorkFlowService.initiateTask(ku.getIdWorkflowIntegration(),
//                            p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
//                            p.getKodUrusan().getNama());

//                        if (ku.getKePTG() == 'Y') {
//                            WorkFlowService.initiateTask(ku.getIdWorkflow(),
//                                    p.getIdPermohonan(), caw.getKod() + ",00", pengguna.getIdPengguna(),
//                                    ku.getNama());
//                        } else if (ku.getKePTG() == 'T') {
//                            WorkFlowService.initiateTask(ku.getIdWorkflow(),
//                                    p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
//                                    ku.getNama());
//                        }
                    } catch (Exception e) {
                        LOG.error(e);
                    }
                }
            }
        }


    }
}
