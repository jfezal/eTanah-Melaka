package etanah.view.stripes.common;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Integrasi;
import etanah.model.IntegrasiPermohonan;
import etanah.model.IntegrasiPermohonanButir;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanDokumenIringan;
import etanah.service.BPelService;
import etanah.service.JupemService;
import etanah.service.StrataPtService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
public class OutBoundIntegration {

    @Inject
    StrataPtService strService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    JupemService service;
    private static final Logger LOG = Logger.getLogger(OutBoundIntegration.class);
    private IntegrasiPermohonan integrasiPermohonan;
    private Integrasi integ = new Integrasi();
    private Permohonan permohonan = new Permohonan();
    private Pengguna pengguna = new Pengguna();
    InfoAudit infoAudit = new InfoAudit();
    String taskId = new String();
    private String stageId;

    public String copyfile() {
        LOG.info("---- Copying file to mohon_integ ----:");
        String error = "";
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        LOG.info("PENGGUNA : " + pengguna.getIdPengguna());
        LOG.info("TASK ID : " + taskId);
        String idPermohonan = permohonan.getIdPermohonan();
//        Permohonan p = permohonanDAO.findById(idPermohonan);
        BPelService serviceBpel = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        if (this.permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLP")) {
            LOG.debug("ambil");
            stageId = "g_penyediaan_pu";
        }
        
        LOG.info("---- stageId ----:" + stageId);
        LOG.info("---- this.permohonan.getKodUrusan().getKod() ----:" + this.permohonan.getKodUrusan().getKod());
//        stageId = "g_semakkemasukan";
        integ = new Integrasi();
        LOG.info(this.permohonan.getKodUrusan().getKod());

        integ = service.getInteg(this.permohonan.getKodUrusan().getKod(), stageId);
        //  LOG.debug(integ.getKodUrusan());
        if (integ != null) {
            integrasiPermohonan = service.getByidMohonNaliran(this.permohonan.getIdPermohonan(), integ.getIdAliranHantar());
            if (integrasiPermohonan == null) {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new Date());
                infoAudit.setDimasukOleh(pengguna);
                integrasiPermohonan = new IntegrasiPermohonan();
            } else {
                infoAudit = integrasiPermohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new Date());
            }
            boolean flag = true;
            List<Dokumen> listDoc = service.findDokumenlist(idPermohonan, stageId);
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PJTK")) {
                listDoc = service.findDokumenlist(idPermohonan, stageId, permohonan.getKodUrusan().getKod());
            }
            LOG.debug("::: size listDoc :" + listDoc.size());
            String sourcePath = conf.getProperty("document.path");
            String destPath = conf.getProperty("gis.outbound.path");
            destPath = destPath + File.separator + this.permohonan.getIdPermohonan() + stageId;
            File f = new File(destPath);
            f.mkdir();
            for (Dokumen doc : listDoc) {
                if (flag == false) {
                    if (strService.getFormat(doc.getFormat()) != null) {
                        flag = true;
                    } else {
                        flag = false;
//                    JOptionPane jop = new JOptionPane();
//                    JOptionPane.showMessageDialog(jop,
//                            "Sila Muat Naik Dokumen Berkaitan",
//                            "Perhatian" + doc.getKodDokumen().getKod(),
//                            JOptionPane.ERROR_MESSAGE);
                        error = "Sila Muat Naik Dokumen Berkaitan";
//                    break;

                    }
                } else {
                    break;
                }

            }
            LOG.debug("flag >> " + flag);
            if (flag) {
                LOG.debug("masuk flag");
                integrasiPermohonan = integMohon(integrasiPermohonan);
                for (Dokumen doc : listDoc) {
                    File src = new File(sourcePath + File.separator + doc.getNamaFizikal());
//                    String[] s = doc.getNamaFizikal().split(Pattern.quote("\\"));
//                    String filename = "";
//                    for (int v = 0; v < s.length; v++) {
//                        filename = s[s.length - 1];
//                    }
//                    File dst = new File(destPath + File.separator + filename);
                    String fn = "";
                    int start = doc.getNamaFizikal().lastIndexOf("/");
                    if (start != -1) {
                        fn = doc.getNamaFizikal().substring(start);
                    }
                    int s = doc.getNamaFizikal().lastIndexOf("\\");
                    if (s != -1) {
                        fn = doc.getNamaFizikal().substring(s);
                    }

                    int a = fn.lastIndexOf(".");
                    if (a == -1) {
                        fn = fn + ".pdf";
                    }
                    LOG.info("fn::" + fn);
                    File dst = new File(destPath + File.separator + fn);

                    try {
                        LOG.info("Transfer file started (" + doc.getKodDokumen().getKod() + "): " + doc.getIdDokumen());
                        service.copyDirectory(src, dst);
                        LOG.info("Finished : " + doc.getIdDokumen());


                    } catch (IOException ex) {
                        error = ex.toString();
                        java.util.logging.Logger.getLogger(OutBoundIntegration.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                integMohonButir(destPath);
            }

            List<Dokumen> docIringan = service.findDocByMohonIringan(permohonan);
            if (!docIringan.isEmpty()) {
                for (Dokumen docI : docIringan) {
                    File src = new File(sourcePath + File.separator + docI.getNamaFizikal());
                    File dst = new File(destPath + File.separator + docI.getIdDokumen() + strService.getFormat(docI.getFormat()));
                    try {
                        LOG.info("Transfer file started : " + docI.getIdDokumen());
                        service.copyDirectory(src, dst);
                        LOG.info("Finished : " + docI.getIdDokumen());

                        integMohonButir(destPath);
                    } catch (IOException ex) {
                        error = ex.toString();
                        java.util.logging.Logger.getLogger(OutBoundIntegration.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        } else {
//            JOptionPane jop = new JOptionPane();
//            JOptionPane.showMessageDialog(jop,
//                    "Id aliran ini tiada dalam urusan penghantaran dokumen ke JUPEM",
//                    "Perhatian",
//                    JOptionPane.ERROR_MESSAGE);
            error = "Id aliran ini tiada dalam urusan penghantaran dokumen ke JUPEM";
        }

        LOG.debug("error >> " + error);

        return error;
    }

    public String copyfile1d() {
        LOG.info("---- Copying file to mohon_integ ----:");
        String error = "";
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        LOG.info("PERMOHONAN SBLM: " + permohonan.getPermohonanSebelum().getIdPermohonan());
        LOG.info("PENGGUNA cuurent id : " + pengguna.getIdPengguna());
        LOG.info("TASK ID current id: " + taskId);

        stageId = "g_sedialaporan";
        LOG.info("---- idSblm--stageId ----:" + stageId);
        integ = new Integrasi();
        LOG.info(this.permohonan.getKodUrusan().getKod());

        integ = service.getInteg(permohonan.getPermohonanSebelum().getKodUrusan().getKod(), stageId);
        LOG.info("---- integ ----:" + integ);

        if (integ != null) {
            integrasiPermohonan = service.getByidMohonNaliran(permohonan.getPermohonanSebelum().getIdPermohonan(), integ.getIdAliranHantar());
            LOG.info("---- integrasiPermohonan ----:" + integrasiPermohonan);
            if (integrasiPermohonan == null) {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new Date());
                infoAudit.setDimasukOleh(pengguna);
                integrasiPermohonan = new IntegrasiPermohonan();
            } else {
                infoAudit = integrasiPermohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new Date());
            }
            boolean flag = true;
            List<Dokumen> listDoc = service.findDokumenlist(permohonan.getPermohonanSebelum().getIdPermohonan(), stageId);
            LOG.info("---- listDoc ----:" + listDoc);
            String sourcePath = conf.getProperty("document.path");
            String destPath = conf.getProperty("gis.outbound.path");
            destPath = destPath + File.separator + permohonan.getPermohonanSebelum().getIdPermohonan() + stageId;
            LOG.info("---- destPath ----:" + destPath);
            File f = new File(destPath);
            f.mkdir();
            for (Dokumen doc : listDoc) {
                if (flag == false) {
                    if (strService.getFormat(doc.getFormat()) != null) {
                        flag = true;
                    } else {
                        flag = false;
                        error = "Sila Muat Naik Dokumen Berkaitan";
                    }
                } else {
                    break;
                }
            }
            if (flag) {
                integrasiPermohonan = integMohon(integrasiPermohonan);
                for (Dokumen doc : listDoc) {
                    File src = new File(sourcePath + File.separator + doc.getNamaFizikal());
                    String fn = "";
                    int start = doc.getNamaFizikal().lastIndexOf("/");
                    if (start != -1) {
                        fn = doc.getNamaFizikal().substring(start);
                    }
                    int s = doc.getNamaFizikal().lastIndexOf("\\");
                    if (s != -1) {
                        fn = doc.getNamaFizikal().substring(s);
                    }

                    int a = fn.lastIndexOf(".");
                    if (a == -1) {
                        fn = fn + ".pdf";
                    }
                    LOG.info("fn::" + fn);
                    File dst = new File(destPath + File.separator + fn);
                    try {
                        LOG.info("-----Transfer file started----- : " + doc.getIdDokumen());
                        service.copyDirectory(src, dst);
                        LOG.info("-----Finished----- : " + doc.getIdDokumen());
                    } catch (IOException ex) {
                        error = ex.toString();
                        java.util.logging.Logger.getLogger(OutBoundIntegration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                integMohonButir(destPath);
            }

            List<Dokumen> docIringan = service.findDocByMohonIringan(permohonan);
            if (!docIringan.isEmpty()) {
                for (Dokumen docI : docIringan) {
                    File src = new File(sourcePath + File.separator + docI.getNamaFizikal());
                    File dst = new File(destPath + File.separator + docI.getIdDokumen() + strService.getFormat(docI.getFormat()));
                    try {
                        LOG.info("Transfer file started : " + docI.getIdDokumen());
                        service.copyDirectory(src, dst);
                        LOG.info("Finished : " + docI.getIdDokumen());
                        integMohonButir(destPath);
                    } catch (IOException ex) {
                        error = ex.toString();
                        java.util.logging.Logger.getLogger(OutBoundIntegration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else {
            error = "Id aliran ini tiada dalam urusan penghantaran dokumen ke JUPEM";
        }
        return error;
    }

    public IntegrasiPermohonan integMohon(IntegrasiPermohonan integrasiPermohonan) {
        LOG.info("---- saving IntegrasiPermohonan ----");
        integrasiPermohonan.setKodUrusan(this.permohonan.getKodUrusan());
        integrasiPermohonan.setPermohonan(this.permohonan);
        integrasiPermohonan.setIdAliran(stageId);
        integrasiPermohonan.setIdAliranTerima(integ.getIdAliranTerima());
        integrasiPermohonan.setInfoAudit(infoAudit);
        service.simpanInIntegrasiPermohonan(integrasiPermohonan);
        return integrasiPermohonan;
    }

    public IntegrasiPermohonanButir integMohonButir(String destPath) {
        LOG.info("---- saving IntegrasiPermohonanButir ----");
        IntegrasiPermohonanButir ipb = new IntegrasiPermohonanButir();
        ipb.setIntegrasiPermohonan(integrasiPermohonan);
        ipb.setNamaFolderHantar(destPath);
        ipb.setInfoAudit(infoAudit);
        service.simpanIntegrasiPermohonanButir(ipb);
        return ipb;
    }

    public void saveMohonFasa() {
        LOG.info("---- saving MohonFasa ----");
        FasaPermohonan mohonFasa = new FasaPermohonan();
        mohonFasa.setIdPengguna(pengguna.getIdPengguna());
        mohonFasa.setPermohonan(this.permohonan);
        mohonFasa.setInfoAudit(infoAudit);
        mohonFasa.setIdAliran(taskId);
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Integrasi getInteg() {
        return integ;
    }

    public void setInteg(Integrasi integ) {
        this.integ = integ;
    }

    public IntegrasiPermohonan getIntegrasiPermohonan() {
        return integrasiPermohonan;
    }

    public void setIntegrasiPermohonan(IntegrasiPermohonan integrasiPermohonan) {
        this.integrasiPermohonan = integrasiPermohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public JupemService getService() {
        return service;
    }

    public void setService(JupemService service) {
        this.service = service;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public StrataPtService getStrService() {
        return strService;
    }

    public void setStrService(StrataPtService strService) {
        this.strService = strService;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
