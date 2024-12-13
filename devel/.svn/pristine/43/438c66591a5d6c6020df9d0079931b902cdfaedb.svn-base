
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.PermohonanStrata;
import etanah.model.UrusanDokumen;
import etanah.model.strata.BadanPengurusan;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.KodKategoriBangunanDAO;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.PermohonanBangunan;
import etanah.model.InfoAudit;
import etanah.workflow.StageContext;
import etanah.service.BPelService;
import etanah.model.IntegrasiPermohonanButir;
import etanah.dao.IntegrasiPermohonanDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.IntegrasiPermohonan;
import etanah.model.KodUrusan;
import etanah.model.Dokumen;
import etanah.service.JupemService;
import etanah.workflow.StageListener;
import etanah.service.common.TaskDebugService;
import java.io.*;
import java.util.*;

/**
 *
 * @author w.fairul
 */
public class PermohonanBangunanValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private TaskDebugService tds;
    @Inject
    ReportUtil reportUtil;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private KodKategoriBangunanDAO kodKategoriBangunanDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    private PermohonanStrata pemilik;
    private BadanPengurusan mc;
    private List<PermohonanStrata> mohonStrata;
    private List<Dokumen> senaraiDokumen = new ArrayList<Dokumen>();
    private List<String> senaraiKodDokumen = new ArrayList<String>();
    private static final Logger LOG = Logger.getLogger(PermohonanBangunanValidator.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataValidatorService semakPermohonanService;
    @Inject
    PenggunaDAO penggunaDao;
    private String stageId;
    IntegrasiPermohonan integrasiPermohonan;
    KodUrusan mohonKodUrusan;
    private List<IntegrasiPermohonanButir> senaraiButiran;
    private List<PermohonanBangunan> senaraiPermohonanBangunan;
    private KodUrusan kodUrusan;
    String outputPath;
    private List<Dokumen> list2;
    String fname;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {

        Pengguna pengguna = context.getPengguna();
        Permohonan permohonan = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        HakmilikPermohonan hakmilikP = permohonan.getSenaraiHakmilik().get(0);  //assume 1 permohonan = 1 hakmilik
        Hakmilik hakmilik = hakmilikP.getHakmilik();
        String dokumenPath = conf.getProperty("document.path");
        Dokumen e = null;
        String path = null;
        String[] params = null;
        String[] values = null;
        String[] par = {"permohonan"};
        Object[] val = {permohonan};
        String reportName = null;
        KodDokumen kd = new KodDokumen();
        LOG.debug("Permohonan: " + permohonan.getIdPermohonan());
        List<FasaPermohonan> L = fasaPermohonanDAO.findByEqualCriterias(par, val, null);
        LOG.debug("SIZE KEPUTUSAN: " + L.size());
        LOG.debug("KEPUTUSAN: " + L.get(0).getKeputusan().getKod());
        if (permohonan.getKeputusan().getKod().equals("L")) {
            reportName = "STR_S_Tolak_BelahBahagiBgn.rdf";
            kd.setKod("SKL");
        } else {
            reportName = "REGVDocABT-D001.rdf";
            kd.setKod("VDOC");
        }
        params = new String[]{"p_id_mohon"};
        values = new String[]{permohonan.getIdPermohonan()};

        FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
        e = saveOrUpdateDokumen(fd, kd, hakmilikP.getHakmilik().getIdHakmilik(), pengguna);
        path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
        reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
        updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        mohonStrata = strService.findIDMS(permohonan.getIdPermohonan());
        mc = strService.findBdn(permohonan.getIdPermohonan());
        try {

            if (permohonan.getSenaraiPemohon().isEmpty()) {
                context.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
                return null;
            } else if (mohonStrata.isEmpty()) {
                if (permohonan.getPermohonanSebelum() == null) {
                    context.addMessage("Sila Isi Maklumat Permohonan Selengkapnya  : " + permohonan.getIdPermohonan());
                    return null;
                }
            } else if (!mohonStrata.isEmpty()) {
                for (PermohonanStrata mS : mohonStrata) {
                    if (StringUtils.isEmpty(mS.getNama()) && StringUtils.isEmpty(mS.getNamaSkim()) && StringUtils.isEmpty(mS.getPemilikNama())) {
//                    if (mS.getNama() == null && mS.getNamaSkim() == null && mS.getPemilikNama()== null) {
                        context.addMessage("Sila semak Nama Projek dan Nama Skim  : " + permohonan.getIdPermohonan());
                        return null;
                    }
                    break;
                }
            } else if (mc == null) {
                context.addMessage("Sila masukkan maklumat bangunan : " + permohonan.getIdPermohonan());
                return null;
            } else if (permohonan.getSenaraiBangunan().isEmpty()) {
                context.addMessage("Sila masukkan maklumat jadual petak : " + permohonan.getIdPermohonan());
                return null;
            }

            senaraiDokumen = strService.findUrusanDokWajib(permohonan.getIdPermohonan(), permohonan.getKodUrusan().getKod());
            LOG.info("senaraiDokumen=====" + senaraiDokumen.size());

            int count = 0;
            for (Dokumen d : senaraiDokumen) {
                if (d.getNamaFizikal() == null) {
                    senaraiKodDokumen.add(d.getKodDokumen().getKod());
                    count++;
                }
            }
//pat pindaan
//            if (count > 0) {
//                context.addMessage("Sila Muat Naik/Imbas Kod Dokumen Terlebih Dahulu :" + senaraiKodDokumen);
//                return null;
//            }

            FasaPermohonan mohonFasa = new FasaPermohonan();
//            Permohonan mohonRMHS = new Permohonan();
            Permohonan mohonRTPS = new Permohonan();
            Pengguna peng = context.getPengguna();

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

            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PSBS")) {
                //check urusan: PNB registered or not if registered, have to complete it first
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

            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")) {
                stageId = context.getStageName();
                if (stageId.equals("kemasukan")) {
                    senaraiPermohonanBangunan = strService.checkMhnBangunanExist(permohonan.getIdPermohonan());
                    String x = senaraiPermohonanBangunan.get(0).getNamaLain();
                    LOG.info(x);
                    if (x != null) {
                        int i = 1;
                        for (PermohonanBangunan permohonanBangunan : senaraiPermohonanBangunan) {
                            if (permohonanBangunan.getNamaLain() != null && permohonanBangunan.getKekal() == 'T') {
                                permohonanBangunan.setNama("P" + i);
                                permohonanBangunan.setKodKategoriBangunan(kodKategoriBangunanDAO.findById("P"));
                                strService.simpanBangunan(permohonanBangunan);
                            }
                            i++;
                        }

                            Long unitSyerPetak = strService.countUnitSyer(permohonan.getIdPermohonan());
                            Long unitSyerBngn = strService.countUnitSyerMhnBngn(permohonan.getIdPermohonan());

                            Long unitSyerAll = unitSyerBngn - unitSyerPetak;
                            if (unitSyerAll != 0) {
                                context.addMessage("Sila Pastikan Maklumat Jadual Petak Adalah Tepat.");
                                return null;
                            }
                        
                    }
                }
            }
            boolean PBS = false;
            boolean PBBD = false;
            boolean PBBS = false;
            stageId = context.getStageName();
            if (stageId.equals("kemasukan")) {
                if (permohonan.getKodUrusan().getKod().equals("PBBS")
                        || permohonan.getKodUrusan().getKod().equals("PBBD")
                        || permohonan.getKodUrusan().getKod().equals("PBBD")) {

                    for (PermohonanBangunan bngnCheck : senaraiPermohonanBangunan) {
                        if (bngnCheck.getKodKategoriBangunan().getKod().equals("P")) {
                            PBS = true;
                        } else if (bngnCheck.getKodKategoriBangunan().getKod().equals("L")) {
                            PBBD = true;
                        } else if (bngnCheck.getKodKategoriBangunan().getKod().equals("B")) {
                            PBBS = true;
                        }
                    }
                    if ((PBS == true && PBBD == true & PBBS == true)
                            || (PBS == true && PBBD == false & PBBS == false)
                            || (PBS == true && PBBD == true & PBBS == false)
                            || (PBS == true && PBBD == false & PBBS == true)) {
                        if (!permohonan.getKodUrusan().getKod().equals("PBS")) {
                            context.addMessage("Kod Urusan Anda Masukan Adalah Salah. Sila Rujuk Pada Tab Jadual Petak");
                        }
                    } else if ((PBS == false && PBBD == true & PBBS == true)
                            || (PBS == false && PBBD == true & PBBS == false)) {
                        if (!permohonan.getKodUrusan().getKod().equals("PBBD")) {
                            context.addMessage("Kod Urusan Anda Masukan Adalah Salah. Sila Rujuk Pada Tab Jadual Petak");
                        }
                    } else if (PBS == false && PBBD == false & PBBS == true) {
                        if (!permohonan.getKodUrusan().getKod().equals("PBBS")) {
                            context.addMessage("Kod Urusan Anda Masukan Adalah Salah. Sila Rujuk Pada Tab Jadual Petak");
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }

        return proposedOutcome;
//        return null;
    }

    public boolean proceed() {
        boolean msg = false;
        for (;;) {
            if (msg == false) {
                break;
            }
        }
        return msg;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, Pengguna peng) {
        InfoAudit ia = new InfoAudit();
        KodKlasifikasi kodKlas = kodKlasifikasiDAO.findById(1); //kod klasifikasi : 1 = Am, 2 = Terhad, 3 = Sulit, 4 = Rahsia, 5 = Rahsia Besar
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("doc null");
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            LOG.debug("doc :" + doc.getIdDokumen());
            ia.setDimasukOleh(doc.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(doc.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setKlasifikasi(kodKlas);
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        doc.setTajuk(kd.getKod());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
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
