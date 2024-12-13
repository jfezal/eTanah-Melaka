/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.NotaSiasatan;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class SediaSuratEFValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(SediaSuratEFValidator.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    ReportUtil reportUtil;
    @Inject
    DokumenService dokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    AduanService aduanService;

    @Inject
    PengambilanAPTService pengambilanAPTService;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        NotaSiasatan notaSiasatan = aduanService.findByIdMohon(context.getPermohonan().getIdPermohonan());
        String namaReport[] = new String[1];
        String kodReport[] = new String[1];
        namaReport[0] = "ACQSrtNotaSiasatan_MLK.rdf";
        kodReport[0] = "SIIE";
        janaDokumen(context.getPermohonan(), context.getPengguna(), namaReport[0], kodReport[0]);
        for (HakmilikPermohonan hp : context.getPermohonan().getSenaraiHakmilik()) {

            namaReport = new String[1];
            kodReport = new String[1];
            namaReport[0] = "ACQDocE_MLK.rdf";
            kodReport[0] = "E";

            BorangPerHakmilik ph = aduanService.findByIdMHNBE(hp.getId(), "NBE");
            if (ph != null) {
                Dokumen d = janaDokumenPH(context.getPermohonan(), context.getPengguna(), namaReport[0], kodReport[0], ph);
                ph.setDokumen(d);
                aduanService.saveBorangPerHakmilik(ph);
                List<BorangPerPB> listPb = aduanService.findBorangPBbyKodNotis(ph, "NBF");
                for (BorangPerPB pb : listPb) {
                    namaReport = new String[1];
                    kodReport = new String[1];
                    namaReport[0] = "ACQDocF_MLK.rdf";
                    kodReport[0] = "F";
                    Dokumen dd = janaDokumenPb(context.getPermohonan(), context.getPengguna(), namaReport[0], kodReport[0], pb);
                    pb.setDokumen(dd);
                    aduanService.saveBorangPerPB(pb);

                }
            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome
    ) {
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context
    ) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome
    ) {
        return "back";
    }

    @Override
    public void afterPushBack(StageContext context
    ) {
    }

    public Dokumen janaDokumenPb(Permohonan permohonan, Pengguna pengguna, String namaReport, String kodReport, BorangPerPB ph) {
        Dokumen d = null;
        try {
            LOG.info("------------generate report for Kod Dokumen --------------:::" + kodReport + "(" + namaReport + ")");
            String[] params = new String[]{"p_id_mohon", "p_id_borang_pb"};
            String[] values = new String[]{permohonan.getIdPermohonan(), ph.getId() + ""};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            KodDokumen kd = null;
            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            String reportName = "";
            kd = kodDokumenDAO.findById(kodReport);
            reportName = namaReport;
            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan(), pengguna, true);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public Dokumen janaDokumenPH(Permohonan permohonan, Pengguna pengguna, String namaReport, String kodReport, BorangPerHakmilik ph) {
        Dokumen d = null;
        try {
            LOG.info("------------generate report for Kod Dokumen --------------:::" + kodReport + "(" + namaReport + ")");
            String[] params = new String[]{"p_id_mohon", "p_id_borang_hm"};
            String[] values = new String[]{permohonan.getIdPermohonan(), ph.getId() + ""};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            KodDokumen kd = null;
            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            String reportName = "";
            kd = kodDokumenDAO.findById(kodReport);
            reportName = namaReport;
            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan(), pengguna, true);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public Dokumen janaDokumen(Permohonan permohonan, Pengguna pengguna, String namaReport, String kodReport) {
        Dokumen d = null;
        try {
            LOG.info("------------generate report for Kod Dokumen --------------:::" + kodReport + "(" + namaReport + ")");
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            KodDokumen kd = null;
            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            String reportName = "";
            kd = kodDokumenDAO.findById(kodReport);
            reportName = namaReport;
            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan(), pengguna, false);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, Pengguna pengguna, Boolean multiple) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        if (!multiple) {
            doc = semakDokumenService.semakDokumen(kd, fd, id);
        }
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
        if (!multiple) {
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

        }
        return doc;
    }

}
