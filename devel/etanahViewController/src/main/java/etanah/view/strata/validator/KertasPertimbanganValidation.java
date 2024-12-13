
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.service.common.TaskDebugService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.Map;
import java.util.logging.Level;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import etanah.model.Pengguna;
import etanah.dao.PenggunaDAO;
import java.util.Map;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author w.fairul
 */
public class KertasPertimbanganValidation implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    StrataPtService strService;
    @Inject
    private TaskDebugService tds;
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(KertasPertimbanganValidation.class);

    @Override
    public boolean beforeStart(StageContext context) {
        return false;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        //Permohonan mohonRMHS = new Permohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        List<Permohonan> mohonRMHSList = new ArrayList();

        //check urusan: RMHS1 registered or not if registered, have to complete it first
        if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBS")) {
            mohonRMHSList = null;
            mohonRMHSList = strService.findIDMohonByKodUrusan(permohonan.getIdPermohonan(), "RMHS1");
            for (Permohonan mohonRMHS : mohonRMHSList) {
                if (mohonRMHS != null) {
                    try {
                        mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                        Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                        String group = (String) m.get("participant");
                        String acquserNama = (String) m.get("acquiredBy");
                        if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                            if (acquserNama == null) {
                                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                                String groupNama = peng.getPerananUtama().getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>" + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + groupNama);
                            } else {
                                peng = penggunaDao.findById(acquserNama);
                                String acqby = peng.getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>" + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + acqby);
                            }
                            return null;
                        }
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(KertasPertimbanganValidation.class.getName()).log(Level.SEVERE, null, ex);
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
                    try {
                        mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                        Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                        String group = (String) m.get("participant");
                        String acquserNama = (String) m.get("acquiredBy");
                        if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                            if (acquserNama == null) {
                                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                                String groupNama = peng.getPerananUtama().getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>" + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + groupNama);
                            } else {
                                peng = penggunaDao.findById(acquserNama);
                                String acqby = peng.getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>" + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + acqby);
                            }
                            return null;
                        }
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(KertasPertimbanganValidation.class.getName()).log(Level.SEVERE, null, ex);
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
                    try {
                        mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                        Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                        String group = (String) m.get("participant");
                        String acquserNama = (String) m.get("acquiredBy");
                        if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                            if (acquserNama == null) {
                                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                                String groupNama = peng.getPerananUtama().getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>" + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + groupNama);
                            } else {
                                peng = penggunaDao.findById(acquserNama);
                                String acqby = peng.getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>" + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + acqby);
                            }
                            return null;
                        }
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(KertasPertimbanganValidation.class.getName()).log(Level.SEVERE, null, ex);
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
                    try {
                        mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                        Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                        String group = (String) m.get("participant");
                        String acquserNama = (String) m.get("acquiredBy");
                        if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                            if (acquserNama == null) {
                                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                                String groupNama = peng.getPerananUtama().getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>" + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + groupNama);
                            } else {
                                peng = penggunaDao.findById(acquserNama);
                                String acqby = peng.getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>" + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + acqby);
                            }
                            return null;
                        }
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(KertasPertimbanganValidation.class.getName()).log(Level.SEVERE, null, ex);
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
                    try {
                        mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                        Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                        String group = (String) m.get("participant");
                        String acquserNama = (String) m.get("acquiredBy");
                        if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                            if (acquserNama == null) {
                                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                                String groupNama = peng.getPerananUtama().getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>" + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + groupNama);
                            } else {
                                peng = penggunaDao.findById(acquserNama);
                                String acqby = peng.getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>" + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + acqby);
                            }
                            return null;
                        }
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(KertasPertimbanganValidation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        //check urusan: RMHS7, RTPS registered or not if registered, have to complete it first
        if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
            mohonRMHSList = null;
            mohonRMHSList = strService.findIDMohonByKodUrusan(permohonan.getIdPermohonan(), "RMHS7");
            LOG.debug("----RMHS7 Registered----:" + mohonRMHSList.size());
            for (Permohonan mohonRMHS : mohonRMHSList) {
                LOG.debug("----RMHS7 Idmohon----:" + mohonRMHS.getIdPermohonan());
                if (mohonRMHS != null) {
                    try {
                        mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                        Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                        String group = (String) m.get("participant");
                        String acquserNama = (String) m.get("acquiredBy");
                        if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                            if (acquserNama == null) {
                                peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                                String groupNama = peng.getPerananUtama().getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>" + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + groupNama);
                            } else {
                                peng = penggunaDao.findById(acquserNama);
                                String acqby = peng.getNama();
                                context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>" + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + acqby);
                            }
                            return null;
                        }
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(KertasPertimbanganValidation.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                if (kodSts) {
                    context.addMessage("- Penghantaran Berjaya. Id mohon ini " + idMohon + " masih dalam peringkat endorsan. \n\n<br> "
                            + "Permohonan ini boleh diteruskan");
//                    return null;
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
                if (kodSts) {
                    context.addMessage(" Maaf. Id mohon ini " + idMohon + " masih dalam peringkat endorsan. \n\n<br> "
                            + "Permohonan ini dapat diteruskan sekiranya mendapat kelulusan.");
                    return null;
                }
            }
        }

        if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                || permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PSBS")
                || permohonan.getKodUrusan().getKod().equals("PBTS")) {

            try {
                //check urusan: PNB registered or not if registered, have to complete it first
                Permohonan mohonPNB = new Permohonan();
                mohonPNB = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "PNB");
                LOG.debug("----PNB----Registered----:" + mohonPNB);
                if (mohonPNB != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonPNB.getIdPermohonan(), "keputusan");
                    LOG.debug("----mohonFasa----:" + mohonFasa);
                    Map m = tds.traceTask(mohonPNB.getIdPermohonan());
                    String user = (String) m.get("participant");
                    String group = (String) m.get("participant");
                    String acquserNama = (String) m.get("acquiredBy");
                    LOG.debug("----mohonPNB----user----:" + user);
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        if (acquserNama == null) {
                            peng = penggunaDao.findById(group.substring(group.indexOf(".") + 1));
                            String groupNama = peng.getPerananUtama().getNama();
                            context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                    + "ID Permohonan: " + mohonPNB.getIdPermohonan() + ", Pengguna: " + groupNama);
                        } else {
                            peng = penggunaDao.findById(acquserNama);
                            String acqby = peng.getNama();
                            context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                    + "ID Permohonan: " + mohonPNB.getIdPermohonan() + ", Pengguna: " + acqby);
                        }
                        return null;
                    }
                }
                if (permohonan.getSenaraiTuntutanKos().isEmpty()) {
                    context.addMessage("Sila masukkan maklumat bayaran  : " + permohonan.getIdPermohonan());
                    return null;
                }
                Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");
                if (d != null) {
                    if ((d != null) && (d.getNamaFizikal() == null)) {
                        context.addMessage("Sila klik tab 'Dokumen' untuk memuatnaik fail xml Jadual Petak (JPP).");
                        return null;
                    }
                } else {
                    context.addMessage("Sila klik tab 'Dokumen' untuk tambah dan muatnaik dokumen - Jadual Petak (JPP).");
                    return null;
                }
            } catch (Exception e) {
                LOG.error(e.getMessage());
                return null;
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
