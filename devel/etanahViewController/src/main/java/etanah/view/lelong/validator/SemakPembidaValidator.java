/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodStatusLelongan;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnforcementService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
public class SemakPembidaValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(SemakPembidaValidator.class);
    @Inject
    LelongService lelongService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    private List<FasaPermohonan> senaraifasamohon;
    @Inject
    private etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KandunganFolderService kandunganFolderService;

    private String stageId;
    private FasaPermohonan fasa;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {

        Permohonan permohonan = context.getPermohonan();
        stageId = context.getStageName();
        Pengguna pengguna = context.getPengguna();

        LOG.info("--------------stage id onGenerateReports------------- : " + stageId);
        if (conf.getProperty("kodNegeri").equals("04")) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPJP")) {
                if (stageId.equalsIgnoreCase("semakPembida")) {
                    List<FasaPermohonan> senaraiFasa = lelongService.findListFasaPermohonanSemakPembida(permohonan.getIdPermohonan());
                    List<Lelongan> senaraiLelong = lelongService.findbyIdLelongAKList(permohonan);
                    if (senaraiFasa.size() > 0) {
                        FasaPermohonan f = senaraiFasa.get(0);
//                        if (StringUtils.isNotBlank(f.getIdAliran())) {
                        if (f.getKeputusan() != null) {
                            if (f.getKeputusan().getKod().equalsIgnoreCase("AA")) {
                                if (senaraiLelong.size() > 0) {
                                    LOG.info("--------------stage id------------- : " + stageId + "(" + f.getKeputusan().getKod() + ")");
                                    janaDokumen(permohonan, pengguna, "LLGNotisGantianLelong_MLK.rdf", "SNG");
                                }
                            }
                        }
//                            }
//                        }
                    }
                }
            }
        }
    }

    public void janaDokumen(Permohonan permohonan, Pengguna pengguna, String namaReport, String kodReport) {
        try {
            //if ("04".equals(conf.getProperty("kodNegeri"))) {
            //for Melaka, need to generate report for Perlantikan Pegawai Penyiasat
            LOG.info("------------generate report for Kod Dokumen --------------:::" + kodReport + "(" + namaReport + ")");

            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen d = null;
            KodDokumen kd = null;

            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            String reportName = "";

            kd = kodDokumenDAO.findById(kodReport);
            reportName = namaReport;
            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan(), pengguna);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        if (!etanah.util.StringUtils.isBlank(kd.getNama())) {
            doc.setTajuk(kd.getNama());
        }
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome
    ) {
        LOG.info("------beforeComplete-------");
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();

        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
        if (!listPermohonan.isEmpty()) {
            LOG.info("----PPTL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }

        List<Permohonan> listPermohonan2 = lelongService.getListPPBL(idPermohonan);
        if (!listPermohonan2.isEmpty()) {
            LOG.info("----PPBL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Pembatalan 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }

//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(permohonan.getIdPermohonan());
//        if (!listPermohonan.isEmpty()) {
//            context.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }
//        FasaPermohonan fasa = lelongService.findFasaPermohonanSemakPembida(permohonan.getIdPermohonan());
        List<FasaPermohonan> senaraiFasa = lelongService.findListFasaPermohonanSemakPembida(permohonan.getIdPermohonan());

        if (senaraiFasa.size() > 0) {
            fasa = senaraiFasa.get(0);
        }

//        senaraifasamohon = lelongService.getPermonanFasaRekodBidaan1(permohonan.getIdPermohonan());
//           if (senaraifasamohon  != null) {
//            FasaPermohonanLog fasaPermohonanLog = lelongService.findFasaPermohonanLog(senaraifasamohon.get(0) );
//            if(fasaPermohonanLog!=null){
//                lelongService.deletetest(fasaPermohonanLog,senaraifasamohon.get(0) );
//            }
//        }
        List<FasaPermohonan> fasaPermohonanList = lelongService.findPermonanFasaRekodBidaanList(permohonan.getIdPermohonan());
        FasaPermohonan fasaPermohonan = fasaPermohonanList.get(0);
        if (fasaPermohonan != null) {
            FasaPermohonanLog fasaPermohonanLog = lelongService.findFasaPermohonanLog(fasaPermohonan);
            if (fasaPermohonanLog != null) {
                lelongService.deletetest(fasaPermohonanLog, fasaPermohonan);
            }
        }
//            FasaPermohonan fasaPermohonan = lelongService.FasaPermohonanRekodBidaan(permohonan.getIdPermohonan());
//
//        if (fasaPermohonan != null) {
//
//            lelongService.delete(fasaPermohonan);
//        }

        List<Lelongan> ll = lelongService.listLelonganAK(permohonan.getIdPermohonan());
        if (fasa.getKeputusan().getKod().equals("LS") || fasa.getKeputusan().getKod().equals("AA")) {
            if (ll.isEmpty()) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Tetapkan Tarikh Lelongan Di Tab Maklumat Keputusan");
                return null;
            } else {
                for (Lelongan lel : ll) {
                    if (lel.getTarikhLelong() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan");
                        return null;
                    }
                }
            }
        }
        int bil = 0;
        for (Lelongan lelongan : ll) {
            if (lelongan.getBil() == 4) {
                bil = lelongan.getBil();
                lelongService.delete(lelongan);
            }
        }
        LOG.info("bil : " + bil);

        if (fasa.getKeputusan().getKod().equals("LS")) {
            FolderDokumen fd = permohonan.getFolderDokumen();
            List<KandunganFolder> listKD = lelongService.getListDokumenIystiharJual(fd.getFolderId());
            if (!listKD.isEmpty()) {
                KodDokumen kod = null;
                for (KandunganFolder kf : listKD) {
                    if (kf.getDokumen().getKodDokumen().getKod().equals("PJ")) {
                        kod = kodDokumenDAO.findById("PJLM");
                        Dokumen d = kf.getDokumen();
                        d.setKodDokumen(kod);
                        lelongService.saveOrUpdatDokumen(d);
                        kf.setDokumen(d);
                        lelongService.saveOrUpdate(kf);
                    }
                }
            }
        }
        if (fasa.getKeputusan().getKod().equals("AA")) {

            ll = lelongService.listLelonganAK(permohonan.getIdPermohonan());
            if (!ll.isEmpty()) {
                for (Lelongan lel : ll) {
                    if (lel.getJurulelong() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Jurulelong Berlesen Di Tab Maklumat Keputusan");
                        return null;
                    }
                }
            }

            FolderDokumen fd = permohonan.getFolderDokumen();
            List<KandunganFolder> listKD = lelongService.getListDokumenLEL(fd.getFolderId());
            if (!listKD.isEmpty()) {
                KodDokumen kod = null;
                for (KandunganFolder kf : listKD) {
                    if (kf.getDokumen().getKodDokumen().getKod().equals("LEL")) {
                        kod = kodDokumenDAO.findById("LELLM");
                        Dokumen d = kf.getDokumen();
                        d.setKodDokumen(kod);
                        lelongService.saveOrUpdatDokumen(d);
                        kf.setDokumen(d);
                        lelongService.saveOrUpdate(kf);
                    }
                }
            }
        }
        if (bil == 4 && !fasa.getKeputusan().getKod().equals("RM")) {
            context.addMessage(permohonan.getIdPermohonan() + " - Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
            return null;
        }
        List<PermohonanTuntutanKos> list = new ArrayList<PermohonanTuntutanKos>();
        if (fasa.getKeputusan().getKod().equals("RM")) {
            //lelongan kali kedua,dah bayar bayaran di spoc,terus pg rujuk mahkamah
            List<PermohonanTuntutanKos> listPT = lelongService.listPT(permohonan.getIdPermohonan());
            if (!listPT.isEmpty()) {
                for (PermohonanTuntutanKos pp : listPT) {
                    List<PermohonanTuntutanBayar> listBayar = lelongService.listTB(pp.getIdKos());
                    if (!listBayar.isEmpty()) {
                        list.add(pp);
                    }
                }
            }
            List<Lelongan> listLel = lelongService.getLeloganALLDESC(permohonan.getIdPermohonan());
            for (Lelongan lelongan : listLel) {
                if (lelongan.getBil() == 4) {
                    lelongService.delete(lelongan);
                }
            }

            listLel = lelongService.getLeloganALLDESC(permohonan.getIdPermohonan());
            int bil2 = listLel.get(0).getBil();
            LOG.info("Bil2" + bil2);
            for (Lelongan lel : listLel) {
                KodStatusLelongan kod = null;
                if (lel.getBil() == bil2) {
                    LOG.info("----RM----");
                    kod = kodStatusLelonganDAO.findById("RM");
                    lel.setKodStatusLelongan(kod);
                    lelongService.saveOrUpdate(lel);
                }
            }
        }
        LOG.info("list : " + list.size());
        if (list.isEmpty()) {
            proposedOutcome = fasa.getKeputusan().getKod();
        } else {
            KodKeputusan kod = kodKeputusanDAO.findById("AD");
            proposedOutcome = kod.getKod();
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context
    ) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context
    ) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome
    ) {
        return proposedOutcome;
    }

    /**
     * @return the senaraifasamohon
     */
    public List<FasaPermohonan> getSenaraifasamohon() {
        return senaraifasamohon;
    }

    /**
     * @param senaraifasamohon the senaraifasamohon to set
     */
    public void setSenaraifasamohon(List<FasaPermohonan> senaraifasamohon) {
        this.senaraifasamohon = senaraifasamohon;
    }
}
