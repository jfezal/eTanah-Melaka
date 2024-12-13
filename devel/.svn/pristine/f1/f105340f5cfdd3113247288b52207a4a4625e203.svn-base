/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutan;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.TaskDebugService;
import etanah.view.strata.CommonService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
public class PenyediaanSuratLulusTolakValidator implements StageListener {

    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    @Inject
    FasaPermohonanDAO mohonFasaDAO;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    private TaskDebugService tds;
    Dokumen dokumen;
    String path;
    Pengguna pengguna;
    KodDokumen kd = null;
    private static final Logger LOGGER = Logger.getLogger(PenyediaanSuratLulusTolakValidator.class);
    FolderDokumen fd;
    private Hakmilik hakmilik;
    private String idHakmilik;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan p = context.getPermohonan();
        this.pengguna = context.getPengguna();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        String reportName;
        String path = "";
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();
        fd = folderDokumenDAO.findById(context.getPermohonan().getFolderDokumen().getFolderId());


        if ((kodNegeri.equals("04")) && (p.getKodUrusan().getKod().equals("PBBS"))) {
            mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan1");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    t.add("STRSLulusPecahBahagi_MLK.rdf");
                    t2.add("SKPBP");
                    reportGen(p, t, t2);
                } else {
                    t.add("STRSTolakPecahBahagi_MLK.rdf");
                    t2.add("STPBP");
                    reportGen(p, t, t2);
                }
            }
        }
        if ((kodNegeri.equals("04")) && (p.getKodUrusan().getKod().equals("PBBD"))) {
            mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan1");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    t.add("STRSLulusPecahBahagi_MLK.rdf");
                    t2.add("SKPBP");
                    reportGen(p, t, t2);
                } else {
                    t.add("STRSTolakPecahBahagi_MLK.rdf");
                    t2.add("STPBP");
                    reportGen(p, t, t2);
                }
            }
        }
        if ((kodNegeri.equals("04")) && (p.getKodUrusan().getKod().equals("PBS"))) {
            mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan1");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    t.add("STRSLulusPecahBahagi_MLK.rdf");
                    t2.add("SKPBP");
                    reportGen(p, t, t2);
                } else {
                    t.add("STRSTolakPecahBahagi_MLK.rdf");
                    t2.add("STPBP");
                    reportGen(p, t, t2);
                }
            }
        }
        if ((kodNegeri.equals("04")) && (p.getKodUrusan().getKod().equals("PHPC"))) {
            mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan1");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    t.add("STRSLulusPecahBahagi_MLK.rdf");
                    t2.add("SKPBP");
                    reportGen(p, t, t2);
                } else {
                    t.add("STRSTolakPecahBahagi_MLK.rdf");
                    t2.add("STPBP");
                    reportGen(p, t, t2);
                }
            }
        }
        if ((kodNegeri.equals("04")) && (p.getKodUrusan().getKod().equals("PHPP"))) {
            mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan1");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    t.add("STRSLulusPecahBahagi_MLK.rdf");
                    t2.add("SKPBP");
                    reportGen(p, t, t2);
                } else {
                    t.add("STRSTolakPecahBahagi_MLK.rdf");
                    t2.add("STPBP");
                    reportGen(p, t, t2);
                }
            }
        }

        if ((kodNegeri.equals("05")) && (p.getKodUrusan().getKod().equals("PBBS"))) {
            mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
//                    t.add("STRSTuntutBayarPecahBahagi_NS.rdf");
//                    t2.add("SKPBP");
//                    reportGen(p, t, t2);

                    t.add("STRSLulusPecahBahagi_NS.rdf");
                    t2.add("SLLB");
                    reportGen(p, t, t2);
                } else {
                    t.add("STRSTolakPecahBahagi_NS.rdf");
                    t2.add("STPBP");
                    reportGen(p, t, t2);
                }
            }
        }

        //PBBD
        if ((kodNegeri.equals("05")) && (p.getKodUrusan().getKod().equals("PBBD"))) {
            mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
//                    t.add("STRSTuntutBayarPecahBahagi_NS.rdf");
//                    t2.add("SKPBP");
//                    reportGen(p, t, t2);

                    t.add("STRSLulusPecahBahagi_NS.rdf");
                    t2.add("SLLB");
                    reportGen(p, t, t2);
                } else {
                    t.add("STRSTolakPecahBahagi_NS.rdf");
                    t2.add("STPBP");
                    reportGen(p, t, t2);
                }
            }
        }

        if ((kodNegeri.equals("05")) && (p.getKodUrusan().getKod().equals("PBS"))) {
            mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
//                    t.add("STRSTuntutBayarPecahBahagi_NS.rdf");
//                    t2.add("SKPBP");
//                    reportGen(p, t, t2);

                    t.add("STRSLulusPecahBahagi_NS.rdf");
                    t2.add("SLLB");
                    reportGen(p, t, t2);
                } else {
                    t.add("STRSTolakPecahBahagi_NS.rdf");
                    t2.add("STPBP");
                    reportGen(p, t, t2);
                }
            }
        }

        if ((kodNegeri.equals("05")) && (p.getKodUrusan().getKod().equals("PBBSS"))) {
            mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
//                    t.add("STRSTuntutBayarPecahBahagi_NS.rdf");
//                    t2.add("SKPBP");
//                    reportGen(p, t, t2);

                    t.add("STRSLulusPecahBahagi_NS.rdf");
                    t2.add("SLLB");
                    reportGen(p, t, t2);
                } else {
                    t.add("STRSTolakPecahBahagi_NS.rdf");
                    t2.add("STPBP");
                    reportGen(p, t, t2);
                }
            }
        }
        if ((kodNegeri.equals("05")) && (p.getKodUrusan().getKod().equals("PHPC"))) {
            mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
//                    t.add("STRSTuntutBayarPecahBahagi_NS.rdf");
//                    t2.add("SKPBP");
//                    reportGen(p, t, t2);

                    t.add("STRSLulusPecahBahagi_NS.rdf");
                    t2.add("SLLB");
                    reportGen(p, t, t2);
                } else {
                    t.add("STRSTolakPecahBahagi_NS.rdf");
                    t2.add("STPBP");
                    reportGen(p, t, t2);
                }
            }
        }
        if ((kodNegeri.equals("05")) && (p.getKodUrusan().getKod().equals("PHPP"))) {
            mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
//                    t.add("STRSTuntutBayarPecahBahagi_NS.rdf");
//                    t2.add("SKPBP");
//                    reportGen(p, t, t2);

                    t.add("STRSLulusPecahBahagi_NS.rdf");
                    t2.add("SLLB");
                    reportGen(p, t, t2);
                } else {
                    t.add("STRSTolakPecahBahagi_NS.rdf");
                    t2.add("STPBP");
                    reportGen(p, t, t2);
                }
            }
        }

        PermohonanTuntutan mohonTuntut = new PermohonanTuntutan();
        mohonTuntut = comm.findMohonTuntutByKod(p.getIdPermohonan(), "STR001");
        if (mohonTuntut != null) {
            mohonTuntut.setTarikhTuntutan(new Date());
            Date tarikhAkhir;
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, 30);
            tarikhAkhir = cal.getTime();
            mohonTuntut.setTarikhAkhirBayaran(tarikhAkhir);
            mohonTuntut = comm.saveTuntut(mohonTuntut);
        }

    }

    private void reportGen(Permohonan p, List<String> t, List<String> t2) {
        String dokumenPath = conf.getProperty("document.path");
        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{p.getIdPermohonan()};
        Dokumen d = null;
        KodDokumen kd = null;

        for (int i = 0; i < t.size(); i++) {
            String gen = t.get(i);
            String code = t2.get(i);
            kd = kodDokumenDAO.findById(code);
            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(gen, params, values, dokumenPath + path, this.pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOGGER.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(this.pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOGGER.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(this.pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOGGER.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getKod() + "-" + id);
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(this.pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        String[] name = {"idHakmilik"};
        Object[] value = {idHakmilik};
        FasaPermohonan mohonFasa = new FasaPermohonan();
        fd = folderDokumenDAO.findById(context.getPermohonan().getFolderDokumen().getFolderId());
//        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();

      if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                || permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PBTS")
                || permohonan.getKodUrusan().getKod().equals("PBBSS")) {                                                            
        //check urusan: PNB registered or not if registered, have to complete it first
        try {
            Permohonan mohonPNB = new Permohonan();
            mohonPNB = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "PNB");
            LOGGER.debug("----PNB----Registered----:" + mohonPNB);
            if (mohonPNB != null) {
                mohonFasa = strService.findFasaPermohonanByIdAliran(mohonPNB.getIdPermohonan(), "keputusan");
                LOGGER.debug("----mohonFasa----:" + mohonFasa);
                Map m = tds.traceTask(mohonPNB.getIdPermohonan());
                String user = (String) m.get("participant");
                LOGGER.debug("----mohonPNB----user----:" + user);
                if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                    context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Penarikan Balik Permohonan Pecah Bahagi Bangunan \n\n<br>"
                            + "ID Permohonan: " + mohonPNB.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                    return null;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
      }

//        commented on 20/07/2012
//        if (mohonFasa != null) {
//            if (!mohonFasa.getKeputusan().equals(null)) {
//                if (mohonFasa.getKeputusan().getKod().equals("L")) {
//                    List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
//                    KodUrusan kod = kodUrusanDAO.findById("PBBB");
//                    LOGGER.info(kod.getNama());
//                    LOGGER.info(permohonan.getFolderDokumen());
//                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
//
//                } else {
//                    List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
//                    KodUrusan kod = kodUrusanDAO.findById("PBBL");
//                    LOGGER.info(kod.getNama());
//                    LOGGER.info(permohonan.getFolderDokumen());
//                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
//
//                }
//            }
//        }

        /**
         * ADA STAGE UNTUK JUPEM
         * Hantar surat permohonan Telah Diluluskan
         */
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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
