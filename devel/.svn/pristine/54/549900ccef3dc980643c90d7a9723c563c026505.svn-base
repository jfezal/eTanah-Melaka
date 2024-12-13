
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.model.InfoAudit;
import etanah.model.FasaPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import etanah.service.common.TaskDebugService;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author w.fairul
 */
public class LaporanTanahValidator implements StageListener {

    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private TaskDebugService tds;
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    etanah.Configuration conf;
    private PermohonanStrata pemilik;
    List<PermohonanStrata> mohonStrata;
    private static final Logger LOG = Logger.getLogger(LaporanTanahValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Pengguna peng = (Pengguna) context.getPengguna();
        try {
            Permohonan permohonan = context.getPermohonan();
            permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
            String[] name = {"permohonan"};
            Object[] object = {permohonan};
            mohonStrata = permohonanStrataDAO.findByEqualCriterias(name, object, null);
            if (mohonStrata.size() > 0) {
                pemilik = mohonStrata.get(0);
            }
            FasaPermohonan mohonFasa = new FasaPermohonan();
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
                    /*if (kodSts) {
                        context.addMessage(" Maaf. Id mohon ini " + idMohon + " masih dalam peringkat endorsan. \n\n<br> "
                                + "Permohonan ini dapat diteruskan sekiranya mendapat kelulusan.");
                        return null;
                    }*/
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
                    /*if (kodSts) {
                        context.addMessage(" Maaf. Id mohon ini " + idMohon + " masih dalam peringkat endorsan. \n\n<br> "
                                + "Permohonan ini dapat diteruskan sekiranya mendapat kelulusan.");
                        return null;
                    }*/
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
        Permohonan permohonan = ctx.getPermohonan();
        LaporanTanah laporanTanah = strService.findLaporanTanahByIdPermohonan(permohonan.getIdPermohonan());


        if (laporanTanah != null) {
            PermohonanStrata mohonStrata = strService.findID(permohonan.getIdPermohonan());
            if (mohonStrata != null) {
                mohonStrata.setSempadanUtaraNoLot(laporanTanah.getSempadanUtaraNoLot());
                mohonStrata.setSempadanUtaraKegunaan(laporanTanah.getSempadanUtaraKegunaan());
                mohonStrata.setSempadanSelatanNoLot(laporanTanah.getSempadanSelatanNoLot());
                mohonStrata.setSempadanSelatanKegunaan(laporanTanah.getSempadanSelatanKegunaan());
                mohonStrata.setSempadanBaratNoLot(laporanTanah.getSempadanBaratNoLot());
                mohonStrata.setSempadanBaratKegunaan(laporanTanah.getSempadanBaratKegunaan());
                mohonStrata.setSempadanTimurNoLot(laporanTanah.getSempadanTimurNoLot());
                mohonStrata.setSempadanTimurKegunaan(laporanTanah.getSempadanTimurKegunaan());
                strService.SimpanLaporanTanah(mohonStrata);

//                laporanTanah.setSempadanBaratNoLot(mohonStrata.getSempadanBaratNoLot());
//                laporanTanah.setSempadanTimurNoLot(mohonStrata.getSempadanTimurNoLot());
//                laporanTanah.setSempadanSelatanNoLot(mohonStrata.getSempadanSelatanNoLot());
//                laporanTanah.setSempadanUtaraNoLot(mohonStrata.getSempadanUtaraNoLot());
//                laporanTanah.setSempadanBaratKegunaan(mohonStrata.getSempadanBaratKegunaan());
//                laporanTanah.setSempadanSelatanKegunaan(mohonStrata.getSempadanSelatanKegunaan());
//                laporanTanah.setSempadanTimurKegunaan(mohonStrata.getSempadanTimurKegunaan());
//                laporanTanah.setSempadanUtaraKegunaan(mohonStrata.getSempadanUtaraKegunaan());
//                strService.simpanLaporan(laporanTanah);
            }
        }

        PermohonanTandatanganDokumen ptd = strService.findMohonDokTT(ctx.getPermohonan().getIdPermohonan(), ctx.getPengguna().getIdPengguna(), "LT");
        System.out.println("---------------LT : ----------- : " + ctx.getPermohonan().getIdPermohonan() + " ---pengguna: " + ctx.getPengguna().getIdPengguna());

        System.out.println("ptd is :  " + ptd);
        InfoAudit ia = new InfoAudit();

        Pengguna pguna = ctx.getPengguna();
        if (ptd == null) {
            System.out.println("new ptd");
            ptd = new PermohonanTandatanganDokumen();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = ptd.getInfoAudit();
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDiKemaskiniOleh(pguna);
        }
        ptd.setCawangan(ctx.getPengguna().getKodCawangan());
        ptd.setKodDokumen(kodDokumenDAO.findById("LT"));
        ptd.setPermohonan(ctx.getPermohonan());
        ptd.setInfoAudit(ia);
        ptd.setDiTandatangan(ctx.getPengguna().getIdPengguna());
        ptd = strService.saveDokumenTT(ptd);
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
