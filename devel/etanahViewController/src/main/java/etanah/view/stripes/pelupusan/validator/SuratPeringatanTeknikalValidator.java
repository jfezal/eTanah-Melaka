/**
 * 
 * @author Mohd Hairudin Mansur
 * @version 27042011
 */

package etanah.view.stripes.pelupusan.validator;

import java.io.File;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

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
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;


public class SuratPeringatanTeknikalValidator implements StageListener {
	
	@Inject
    etanah.Configuration conf;
	@Inject
    FolderDokumenDAO folderDokumenDAO;
	@Inject
    KodDokumenDAO kodDokumenDAO;
	@Inject
    ReportUtil reportUtil;
	@Inject
    SemakDokumenService semakDokumenService;
	@Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
	@Inject
    DokumenService dokumenService;
	@Inject
    KandunganFolderService kandunganFolderService;
	
	private static Pengguna pguna;
	private static final Logger LOG = Logger.getLogger(SuratPeringatanTeknikalValidator.class);

	@Override
	public void afterComplete(StageContext arg0) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	@Override
	public String beforeComplete(StageContext context, String proposedOutcome) {
		proposedOutcome = "HP";
		
		return proposedOutcome;
	}

	@Override
	public boolean beforeGenerateReports(StageContext arg0) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean beforeStart(StageContext arg0) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void onGenerateReports(StageContext context) {
		Permohonan p = context.getPermohonan();
		String gen1 = "";
        String code1 = "";
        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{p.getIdPermohonan()};
        String path1 = "";
        String dokumenPath = conf.getProperty("document.path");
        Dokumen d = null;
        KodDokumen kd = null;

        FolderDokumen fd = folderDokumenDAO.findById(p.getFolderDokumen().getFolderId());
        //List<Task> l = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
		
		gen1 = "DISUlanganUlasanTeknikal_NS.rdf";
        code1 = "PER";
        kd = kodDokumenDAO.findById(code1);
        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
        path1 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
        reportUtil.generateReport(gen1, params, values, dokumenPath + path1, pguna);
        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

	}
	
	private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
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
            ia.setDiKemaskiniOleh(pguna);
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
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

}
