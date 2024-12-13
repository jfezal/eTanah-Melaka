/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodPerananDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodPeranan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.report.ReportUtil;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author afham
 */
public class MaklumanKeputusanMMKN implements StageListener {

    @Inject
    PelupusanService plpService;
    @Inject
    ReportValidator reportValidator;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    private String stage;
    private static Pengguna pengguna;
    @Inject
    ReportUtil reportUtil;
    private static final Logger LOG = Logger.getLogger(MaklumanKeputusanMMKN.class);
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan p = context.getPermohonan();
        pengguna = context.getPengguna();
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

                if (kodNegeri.equalsIgnoreCase("04")) {	// melaka
                    generateRptMelaka(l, gen1, code1, kd, fd, d, path, p, params, values, gen2, code2, path2, dokumenPath);
                } else if (kodNegeri.equalsIgnoreCase("05")) {	// ns
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
            if (t.getSystemAttributes().getStage() != null) {
                stage = t.getSystemAttributes().getStage();
                LOG.info(stage);
                if (stage.contentEquals("rekod_keputusan_mmkn")) {
                    if (p.getKodUrusan().getKod().equals("PPTR") || p.getKodUrusan().getKod().equals("PPRU")) {
                        gen1 = "DISSKpsnMMKNPPTR_MLK.rdf";
                    } else {
                        gen1 = "DISSKpsnMMKNPLPS_MLK.rdf";
                    }

                    LOG.info(gen1);
                    code1 = "SKM";
                    kd = kodDokumenDAO.findById(code1);
                    LOG.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
            } else {
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
            if (t.getSystemAttributes().getStage() != null) {
                stage = t.getSystemAttributes().getStage();
                LOG.info(stage);
                // no report to generate (yet)
            } else {
                // do nothing
            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String outcome = "";

        try {
            outcome = checkstageID(context);

        } catch (WorkflowException ex) {
            LOG.error(ex.getMessage());
            return null;
        }

//        if(outcome != null){


//            Notifikasi n = new Notifikasi();
////            n.setTajuk("Makluman Keputusan MMKN");
////            n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + "Keputusan MMKN : " + context.getPermohonan().getKeputusan().getNama());
//            Pengguna p = context.getPengguna();
////            n.setCawangan(p.getKodCawangan());
//            List<KodPeranan> list;
//            list = new ArrayList<KodPeranan>();
//            String[] a = {"225","220","227"};
//                     
//            LOG.info("Makluman Keputusan MMKN");
//            InfoAudit ia = new InfoAudit();
//            ia.setDimasukOleh(p);
//            ia.setTarikhMasuk(new Date());
//            
//            for(int i = 0; i < a.length; i++){
//	            LOG.info(kodPerananDAO.findById(a[i]+""));
//	            list = plpService.getSenaraiKodPerananByID(a[i]);
//	            KodPeranan kp = kodPerananDAO.findById(a[i]+"") ;
//	            String msj = null;
//	            if(context.getPermohonan().getKodUrusan().getKod().equals("PBMT")){
//	                msj = "Permohonan Pemberimilikan Tanah Kerajaan (Seksyen 76 KTN) ";
//	            }
//	            else if(context.getPermohonan().getKodUrusan().getKod().equals("PLPS")){
//	                msj = "Permohonan Lesen Pendudukan Sementara (Seksyen 65 KTN) ";
//	            }
//	            else if(context.getPermohonan().getKodUrusan().getKod().equals("PPRU")){
//	                msj = "Permohonan Permit Menggunakan Ruang Udara (Seksyen 75A KTN) ";
//	            }
//	            else if(context.getPermohonan().getKodUrusan().getKod().equals("PPTR")){
//	                msj = "Permohonan Memajak Tanah Rizab (Seksyen 63 KTN) ";
//	            }
//	            
//	            if(context.getPermohonan().getKeputusan().getKod().equals("L")) {
//	            	msj += "telah <b>diluluskan</b> oleh Pihak Berkuasa Negeri.";
//	            }
//	            else if(context.getPermohonan().getKeputusan().getKod().equals("T")) {
//	            	msj += "telah <b>ditolak</b> oleh Pihak Berkuasa Negeri.";
//	            }
//	            n.setTajuk("Makluman Keputusan MMKN");
//	            n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + msj);
//	            n.setCawangan(p.getKodCawangan());
//	            n.setInfoAudit(ia) ;
//	            
//	            //LOG.info("Maklum kepada :" + kp.getNama());
//	            notifikasiService.addRoleToNotifikasi(n, p.getKodCawangan(), kp);
//            }
//        }

        proposedOutcome = outcome;
        if (proposedOutcome == null) {
            context.addMessage("Sila Masukkan Keputusan MMKN dahulu");
        }
//        proposedOutcome = null ;
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

    public String checkstageID(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        HakmilikPermohonan hp = plpService.findMohonHakmilik(permohonan.getIdPermohonan());
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            LOG.info(stage);
            if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                if (stage.contentEquals("rekod_keputusan_mmkn")) {
                    if (hp.getKeputusan() == null) {
                        value = null;
                        return value;
                    } else {
                        value = hp.getKeputusan().getKod();
                        return value;
                    }
                }
            } else {
                if (stage.contentEquals("rekod_keputusan_mmkn")) {
                    if (permohonan.getKeputusan() == null) {
                        value = null;
                        return value;
                    } else {
                        value = permohonan.getKeputusan().getKod();
                        return value;
                    }
                }
            }
        }

        return value;
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
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
