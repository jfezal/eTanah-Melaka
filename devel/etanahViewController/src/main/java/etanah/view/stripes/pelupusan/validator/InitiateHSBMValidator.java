
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;

import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;

import java.io.File;
import java.util.List;

import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;

import org.apache.log4j.Logger;

/**
 *
 * @author w.fairul
 */
public class InitiateHSBMValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(InitiateHSBMValidator.class);
    private static Pengguna pengguna;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String stage;


    public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

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
    	Permohonan p = context.getPermohonan();
        setPengguna(context.getPengguna());
        //KodUrusan ku = p.getKodUrusan();
        String kodNegeri = conf.getProperty("kodNegeri");
        
        if (p != null) {
            try {

                String gen1 = "";
                String gen2 = "";
                String code1 = "";
                String code2 = "";
                String[] params = new String[]{"p_id_mohon"};
                String[] values = new String[]{p.getIdPermohonan()};
                String path = "";
                String path2 = "";
                String dokumenPath = conf.getProperty("document.path");
                Dokumen d = null;
                KodDokumen kd = null;

                FolderDokumen fd = folderDokumenDAO.findById(p.getFolderDokumen().getFolderId());
                List<Task> l = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
                LOG.info("list task size: " + l.size());
                
                if(kodNegeri.equalsIgnoreCase("04")) {	// melaka
                	generateRptMelaka(l, gen1, code1, kd, fd, d, path, p, params, values, gen2, code2, path2, dokumenPath);
                } else if(kodNegeri.equalsIgnoreCase("05")) {	// ns
                	generateRptNS(l, gen1, code1, kd, fd, d, path, p, params, values, gen2, code2, path2, dokumenPath);
                }

            } catch (WorkflowException ex) {
                LOG.error(ex.getMessage());
            }
        }
    }
    
    /**
     * 
     * @param l
     * @param gen1
     * @param code1
     * @param kd
     * @param fd
     * @param d
     * @param path
     * @param p
     * @param params
     * @param values
     * @param gen2
     * @param code2
     * @param path2
     * @param dokumenPath
     * @author Mohd Hairudin Mansur
     * @version 25042011
     */
    private void generateRptMelaka(List<Task> l, String gen1, String code1, KodDokumen kd, FolderDokumen fd, Dokumen d, 
    									String path, Permohonan p, String[] params, String[] values, String gen2, String code2, String path2, 
    									String dokumenPath) {
    	
    	
        for (Task t : l) {
        	if(t.getSystemAttributes().getStage() != null) {
        		stage = t.getSystemAttributes().getStage();
                LOG.info(stage);
                if (stage.contentEquals("g_penyediaan_pu")) {
                    gen1 = "DISSrtIringanPU_MLK.rdf";
                    LOG.info(gen1);
                    code1 = "0C";
                    kd = kodDokumenDAO.findById(code1);
                    LOG.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    
                    gen2 = "DISBorangPU_MLK.rdf";
                    LOG.info(gen2);
                    code2 = "PU";
                    kd = kodDokumenDAO.findById(code2);
                    LOG.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
        	}
        	else {
        		// do nothing
        	}
        }
    }
    
    /**
     * 
     * @param l
     * @param gen1
     * @param code1
     * @param kd
     * @param fd
     * @param d
     * @param path
     * @param p
     * @param params
     * @param values
     * @param gen2
     * @param code2
     * @param path2
     * @param dokumenPath
     * @author Mohd Hairudin Mansur
     * @version 25042011
     */
    private void generateRptNS(List<Task> l, String gen1, String code1, KodDokumen kd, FolderDokumen fd, Dokumen d, 
			String path, Permohonan p, String[] params, String[] values, String gen2, String code2, String path2, 
			String dokumenPath) {
    	
    	for (Task t : l) {
    		if(t.getSystemAttributes().getStage() != null) {
    			stage = t.getSystemAttributes().getStage();
                LOG.info(stage);
                if (stage.contentEquals("g_penyediaan_pu_pt")) {
                    gen1 = "DISSrtIringanPU_NS.rdf";
                    LOG.info(gen1);
                    code1 = "SIPU";
                    kd = kodDokumenDAO.findById(code1);
                    LOG.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    
                    gen2 = "DISBorangPU_NS.rdf";
                    LOG.info(gen2);
                    code2 = "PU";
                    kd = kodDokumenDAO.findById(code2);
                    LOG.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
    		}
    		else {
    			// do nothing
    		}
        }
    }
    
    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
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

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        String stageName = context.getStageName() ;

        //            permohonan.getPermohonanSebelum().
        LOG.info("Initiate HSBM");
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());


        String idMohon = context.getPermohonan().getIdPermohonan() ;
//        idHakmilik = "" + a ;
        String[] name = {"idMohon"};
        Object[] value = {idMohon};
        List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
        KodUrusan kod = kodUrusanDAO.findById("HSBM");
        LOG.info(kod.getNama());
        LOG.info(permohonan.getFolderDokumen());
        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan, stageName);
       

        return proposedOutcome;
//        return null ;
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

	public static void setPengguna(Pengguna pengguna) {
		InitiateHSBMValidator.pengguna = pengguna;
	}

	public static Pengguna getPengguna() {
		return pengguna;
	}
}
