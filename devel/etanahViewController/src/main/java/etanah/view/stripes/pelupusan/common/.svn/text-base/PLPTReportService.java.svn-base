/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.view.stripes.pelupusan.plpt.validator.ReportV2Validator;
import java.io.File;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class PLPTReportService {

    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    etanah.Configuration conf;
    private static final Logger LOGGER = Logger.getLogger(PLPTReportService.class);

    Pengguna pengguna;
    Permohonan permohonan;

    public void generateReport(
            Permohonan p, HashMap reportMap, Pengguna pengguna) {
        String dokumenPath = conf.getProperty("document.path");
        this.permohonan = p;
        this.pengguna = pengguna;
        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{permohonan.getIdPermohonan()};
        FolderDokumen folderDok = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
        System.out.println("THIS IS REPORTMAP.SIZE AFTER DIVIDE BY TWO :" + reportMap.size() / 2);

        for (int i = 1; i <= reportMap.size() / 2; i++) {
            //reportMap.get("name"+String.valueOf(i));
            //dokumen = saveOrUpdateDokumen(folderDok, kodDokumenDAO.findById(reportMap.get("kod"+String.valueOf(i)).toString()), p.getIdPermohonan());
            Dokumen dokumen = saveOrUpdateDokumen(folderDok, kodDokumenDAO.findById(reportMap.get("reportKod" + String.valueOf(i)).toString()), p.getIdPermohonan());
            String path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(dokumen.getIdDokumen());
            reportUtil.generateReport(reportMap.get("reportName" + String.valueOf(i)).toString(), params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), dokumen.getIdDokumen(), reportUtil.getContentType());
        }
    }

    @Transactional
    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    @Transactional
    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOGGER.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOGGER.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            if (!doc.getKodDokumen().getKod().equals("RENC")) {
                ia.setTarikhKemaskini(new java.util.Date());
            }
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOGGER.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getNama() + "-" + id);
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

}
