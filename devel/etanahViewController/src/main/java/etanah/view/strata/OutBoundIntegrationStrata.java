package etanah.view.strata;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Integrasi;
import etanah.model.IntegrasiPermohonan;
import etanah.model.IntegrasiPermohonanButir;
import etanah.model.IntegrasiPermohonanDokumen;
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
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
public class OutBoundIntegrationStrata {

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
    private static final Logger LOG = Logger.getLogger(OutBoundIntegrationStrata.class);
    private IntegrasiPermohonan integrasiPermohonan;
    private Integrasi integ = new Integrasi();
    private Permohonan permohonan = new Permohonan();
    private Pengguna pengguna = new Pengguna();
    InfoAudit infoAudit = new InfoAudit();
    String taskId = new String();
    private String stageId;

    public String copyfile() {
        String error = "";
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        LOG.info("PENGGUNA : " + pengguna.getIdPengguna());
        LOG.info("TASK ID : " + taskId);
        String idPermohonan = permohonan.getIdPermohonan();
        BPelService serviceBpel = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        integ = strService.getInteg(permohonan.getKodUrusan().getKod(), stageId);

        if (integ != null) {
            integrasiPermohonan = service.getByidMohonNaliran(permohonan.getIdPermohonan(), integ.getIdAliranHantar());
            if (integrasiPermohonan == null) {
                integrasiPermohonan = new IntegrasiPermohonan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new Date());
                infoAudit.setDimasukOleh(pengguna);
            } else {
                infoAudit = integrasiPermohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new Date());
            }
            boolean flag = true;
            List<Dokumen> listDoc = service.findDokumenlist(idPermohonan, stageId);
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
                        LOG.info("Transfer file started : " + doc.getIdDokumen());
                        service.copyDirectory(src, dst);
                        LOG.info("Finished : " + doc.getIdDokumen());


                    } catch (IOException ex) {
                        error = ex.toString();
                        java.util.logging.Logger.getLogger(OutBoundIntegrationStrata.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                integMohonButir(destPath);
                SimpanIntegrasiPermohonanDokumen();
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
                        java.util.logging.Logger.getLogger(OutBoundIntegrationStrata.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else {
            error = "Id aliran ini tiada dalam urusan penghantaran dokumen ke JUPEM";
        }
        return error;
    }

    public IntegrasiPermohonan integMohon(IntegrasiPermohonan integrasiPermohonan) {
        integrasiPermohonan.setKodUrusan(permohonan.getKodUrusan());
        integrasiPermohonan.setPermohonan(permohonan);
        integrasiPermohonan.setIdAliran(stageId);
        integrasiPermohonan.setIdAliranTerima(integ.getIdAliranTerima());
        integrasiPermohonan.setInfoAudit(infoAudit);
        service.simpanInIntegrasiPermohonan(integrasiPermohonan);
        return integrasiPermohonan;
    }

    public IntegrasiPermohonanButir integMohonButir(String destPath) {
        IntegrasiPermohonanButir ipb = strService.findByIDIntegIntegrasiPermohonanButir(integrasiPermohonan.getIdInteg());
        if (ipb == null) {
            ipb = new IntegrasiPermohonanButir();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        } else {
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }
        ipb.setIntegrasiPermohonan(integrasiPermohonan);
        //ipb.setNamaFolderHantar(destPath);
        ipb.setNamaFolderHantar(integrasiPermohonan.getIdAliran());
        ipb.setNamaFolderTerima(integrasiPermohonan.getIdAliranTerima());
        ipb.setInfoAudit(infoAudit);
        service.simpanIntegrasiPermohonanButir(ipb);
        return ipb;
    }

    public void SimpanIntegrasiPermohonanDokumen() {
        IntegrasiPermohonanButir integrasiPermohonanButir = strService.findByIDIntegIntegrasiPermohonanButir(integrasiPermohonan.getIdInteg());
        if (integrasiPermohonanButir != null) {
            IntegrasiPermohonanDokumen integrasiPermohonanDokumen = strService.findByIDButirIntegrasiPermohonanDokumen(integrasiPermohonanButir.getIdButir());
            if (integrasiPermohonanDokumen == null) {
                integrasiPermohonanDokumen = new IntegrasiPermohonanDokumen();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            integrasiPermohonanDokumen.setIntegrasiPermohonanButir(integrasiPermohonanButir);
            String[] kodursns = {"P8", "P22A", "P40A", "RTHS", "RTPS", "PPRUS"};
            if (ArrayUtils.contains(kodursns, permohonan.getKodUrusan().getKod())) {
                Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "PLKTA");
                integrasiPermohonanDokumen.setKodDokumen(d.getKodDokumen()); //dapat dari mana?
                integrasiPermohonanDokumen.setNamaFizikal(d.getNamaFizikal()); //dapat dari mana?
            } else {
                Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");
                integrasiPermohonanDokumen.setKodDokumen(d.getKodDokumen()); //dapat dari mana?
                integrasiPermohonanDokumen.setNamaFizikal(d.getNamaFizikal()); //dapat dari mana?
            }
            integrasiPermohonanDokumen.setInfoAudit(infoAudit);
            integrasiPermohonanDokumen = strService.simpanMohonIntegDokumen(integrasiPermohonanDokumen);
        }
    }

    public void saveMohonFasa() {
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
