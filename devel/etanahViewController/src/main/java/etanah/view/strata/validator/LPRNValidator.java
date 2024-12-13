
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.service.common.TaskDebugService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import etanah.model.Pengguna;
import etanah.view.strata.CommonService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @ Murali
 */
public class LPRNValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    CommonService comm;
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    private TaskDebugService tds;
    @Inject
    etanah.Configuration conf;
    private String taskId;
    private String stageId;
    private static final Logger LOG = Logger.getLogger(LPRNValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Pengguna peng = context.getPengguna();
        comm.setPengguna(peng);
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();

        if (kodNegeri.equals("04")) {
            if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD")
                    || mohon.getKodUrusan().getKod().equals("PSBS") || mohon.getKodUrusan().getKod().equals("PBS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "semak_kertas_pertimbangan");

                t.add("STRKertasPTG_MLK.rdf");
                t2.add("KPTG");
                comm.reportGen(mohon, t, t2);
            }
            if (mohon.getKodUrusan().getKod().equals("PHPC")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "semakkertasptg");

                t.add("STRKertasPTGPHPC_MLK.rdf");
                t2.add("KPTG");
                comm.reportGen(mohon, t, t2);
            }
            if (mohon.getKodUrusan().getKod().equals("PHPP")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "semakkertasptg");

                t.add("STRKertasPTGPHPP_MLK.rdf");
                t2.add("KPTG");
                comm.reportGen(mohon, t, t2);
            }
            
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        try {

            FasaPermohonan mohonFasa = new FasaPermohonan();
            Pengguna peng = (Pengguna) context.getPengguna();
            List<Permohonan> mohonRMHSList = new ArrayList();

            //check urusan: RMHS1 registered or not if registered, have to complete it first
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

                //Permohonan mohonRMHS1 = new Permohonan();
                //mohonRMHS1 = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RMHS1");
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

//                mohonRTPS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RTPS");
//                if (mohonRTPS != null) {
//                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRTPS.getIdPermohonan(), "keputusan");
//                    Map m = tds.traceTask(mohonRTPS.getIdPermohonan());
//                    String group = (String) m.get("participant");
//                    String acquserNama = (String) m.get("acquiredBy");
//                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
//                        if (acquserNama == null) {
//                            peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
//                            String groupNama = peng.getPerananUtama().getNama();
//                            context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Pindah Milik Hakmilik Strata \n\n<br>"
//                                    + "ID Permohonan: " + mohonRTPS.getIdPermohonan() + ", Pengguna: " + groupNama);
//                        } else {
//                            peng = penggunaDao.findById(acquserNama);
//                            String acqby = peng.getNama();
//                            context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Pindah Milik Hakmilik Strata \n\n<br>"
//                                    + "ID Permohonan: " + mohonRTPS.getIdPermohonan() + ", Pengguna: " + acqby);
//                        }
//                        return null;
//                    }
//                }
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

//                mohonRTPS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RTPS");
//                if (mohonRTPS != null) {
//                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRTPS.getIdPermohonan(), "keputusan");
//                    Map m = tds.traceTask(mohonRTPS.getIdPermohonan());
//                    String group = (String) m.get("participant");
//                    String acquserNama = (String) m.get("acquiredBy");
//                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
//                        if (acquserNama == null) {
//                            peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
//                            String groupNama = peng.getPerananUtama().getNama();
//                            context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Pindah Milik Hakmilik Strata \n\n<br>"
//                                    + "ID Permohonan: " + mohonRTPS.getIdPermohonan() + ", Pengguna: " + groupNama);
//                        } else {
//                            peng = penggunaDao.findById(acquserNama);
//                            String acqby = peng.getNama();
//                            context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Pindah Milik Hakmilik Strata \n\n<br>"
//                                    + "ID Permohonan: " + mohonRTPS.getIdPermohonan() + ", Pengguna: " + acqby);
//                        }
//                        return null;
//                    }
//                }
            }

            //check urusan: PNB registered or not if registered, have to complete it first
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PSBS")) {
                Permohonan mohonPNB = new Permohonan();
                mohonPNB = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "PNB");
                LOG.debug("----PNB----Registered----:" + mohonPNB);
                if (mohonPNB != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonPNB.getIdPermohonan(), "keputusan");
                    Map m = tds.traceTask(mohonPNB.getIdPermohonan());
                    String group = (String) m.get("participant");
                    String acquserNama = (String) m.get("acquiredBy");
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        if (acquserNama == null) {
                            peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                            String groupNama = peng.getPerananUtama().getNama();
                            context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Penarikan Balik Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                    + "ID Permohonan: " + mohonPNB.getIdPermohonan() + ", Pengguna: " + groupNama);
                        } else {
                            peng = penggunaDao.findById(acquserNama);
                            String acqby = peng.getNama();
                            context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Penarikan Balik Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                    + "ID Permohonan: " + mohonPNB.getIdPermohonan() + ", Pengguna: " + acqby);
                        }
                        return null;
                    }
                }
            }

            /*if urusan PBBM registered, it have to finish first then only can proceed pbbs/pbbd/pbs/psbs */
            String kodNegeri = conf.getProperty("kodNegeri");
            if (kodNegeri.equals("04")) {
                if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                        || permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PSBS")) {
                    List<Permohonan> senaraiPBBM = new ArrayList<Permohonan>();
                    senaraiPBBM = strService.findListPermohonan(permohonan.getIdPermohonan());
                    boolean kodSts = false;
                    String idMohon = "";
                    for (Permohonan pm : senaraiPBBM) {
                        if (pm.getKodUrusan().getKod().equals("PBBM")) {
                            if (!pm.getStatus().equals("SL")) {
                                kodSts = true;
                                idMohon = pm.getIdPermohonan();
                            }
                        }
                    }
                    stageId = context.getStageName();
                    if (stageId.equals("semakkertasptg") || stageId.equals("perakuan")) {
                        if (kodSts) {
//                            context.addMessage(" Maaf. Id mohon ini " + idMohon + " masih dalam peringkat endorsan. \n\n<br> "
//                                    + "Permohonan ini dapat diteruskan sekiranya mendapat kelulusan.");
//                            return null;

                            context.addMessage(" Pemberitahuan : Id mohon ini " + idMohon + " masih dalam peringkat endorsan. \n\n<br> "
                                    + "Permohonan ini masih boleh diteruskan.");
                        }
                    }
                }
                if (permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC")) {
                    List<Permohonan> senaraiPBBM = new ArrayList<Permohonan>();
                    senaraiPBBM = strService.findListPermohonan(permohonan.getIdPermohonan());
                    boolean kodSts = false;
                    String idMohon = "";
                    for (Permohonan pm : senaraiPBBM) {
                        if (pm.getKodUrusan().getKod().equals("PBCTM")) {
                            if (!pm.getStatus().equals("SL")) {
                                kodSts = true;
                                idMohon = pm.getIdPermohonan();
                            }
                        }
                    }
                    stageId = context.getStageName();
                    if (stageId.equals("semakkertasptg") || stageId.equals("perakuan")) {
                        if (kodSts) {
//                            context.addMessage(" Maaf. Id mohon ini " + idMohon + " masih dalam peringkat endorsan. \n\n<br> "
//                                    + "Permohonan ini dapat diteruskan sekiranya mendapat kelulusan.");
//                            return null;
                            context.addMessage(" Pemberitahuan : Id mohon ini " + idMohon + " masih dalam peringkat endorsan. \n\n<br> "
                                    + "Permohonan ini masih boleh diteruskan.");
                        }
                    }
                }
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
}
