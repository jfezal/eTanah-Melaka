/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
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
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodPeranan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.NotifikasiService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PermohonanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/pembatalan_perserahan")
public class PembatalanPerserahanAction extends AbleActionBean {

    private String idPerserahan;
    private Permohonan perserahan;
    private Pengguna pengguna;
    private String sebab;
    private String taskId;
    private String stage;
    private String status;
    private boolean isExist = false;
    private boolean cetak = false;
    private Dokumen d1;
    private List<Dokumen> senaraiDokumen;
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
    @Inject
    ReportUtil reportUtil;
    @Inject
    PermohonanService permohonanService;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    private static final Logger LOG = Logger.getLogger(PembatalanPerserahanAction.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/utiliti/pembatalan_perserahan.jsp");
    }

   
    @HandlesEvent("search")
    public Resolution cariPerserahan() {
        if (idPerserahan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
        } else {
                if(idPerserahan.contains("SB")){
                    perserahan = permohonanService.findById(idPerserahan);

                    if (perserahan != null) {
                        status = perserahan.getStatus();
                        isExist = true;
                    } else {
                        addSimpleError("Perserahan tidak di jumpai");
                    }
                }
                else{
                    try {
                        if (isDebug) {
                            LOG.debug("idPerserahan =" + idPerserahan);
                        }
                        List senaraiTask = WorkFlowService.queryTasksByAdmin(idPerserahan);
                        if (senaraiTask.isEmpty()) {
                            addSimpleError("Perserahan tidak di jumpai");
                        } else {
                            if (isDebug) {
                                LOG.debug("size = " + senaraiTask.size());
                            }
                            List<Task> l = WorkFlowService.queryTasksByAdmin(idPerserahan);
                            if (l != null) {
                                for (Task t : l) {
                                    stage = t.getSystemAttributes().getStage();
                                    taskId = t.getSystemAttributes().getTaskId();
                                    perserahan = permohonanService.findById(idPerserahan);

                                    if (perserahan != null) {
                                        status = perserahan.getStatus();
//                                if ((stage.equalsIgnoreCase("keputusan")) || ("SS".equals(status))) {
//                                  if(stage.equalsIgnoreCase("keputusan"))  {
//                                    isExist = false;
//                                    addSimpleError("Permohonan " + idPerserahan + " tidak dibolehkan untuk pembatalan");
//                                    return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/pembatalan_perserahan.jsp");
//                                }
                                        isExist = true;
                                    } else {
                                        addSimpleError("Perserahan tidak di jumpai");
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        LOG.error(ex);
                        addSimpleError(ex.getMessage());
                    }
            }
        }
        return new JSP("daftar/utiliti/pembatalan_perserahan.jsp");
    }

    @HandlesEvent("save")
    public Resolution batalPerserahan() {
          if (idPerserahan.contains("SB")) {
                if (sebab == null) {
                    addSimpleError("Sila Masukkan Sebab Pembatalan");
                    return new JSP("daftar/utiliti/pembatalan_perserahan.jsp");
                } else {
                    perserahan = permohonanService.findById(idPerserahan);
                    KodKeputusan k = new KodKeputusan();
                    k.setKod("TK");
                    perserahan.setKeputusan(k);
                    perserahan.setSebab(sebab);
                    perserahan.setStatus("TK");
                    InfoAudit ia = new InfoAudit();
                    ia = perserahan.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new Date());
                    permohonanService.saveOrUpdate(perserahan);


                    String gen1 = "";
                    String code1 = "";
                    String[] params = new String[]{"p_id_mohon"};
                    String[] values = new String[]{perserahan.getIdPermohonan()};
                    String path = "";
                    String dokumenPath = conf.getProperty("document.path");
                    Dokumen d = null;
                    KodDokumen kd = null;
                    FolderDokumen fd = folderDokumenDAO.findById(perserahan.getFolderDokumen().getFolderId());

                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        gen1 = "REGSuratBatalPerserahan_MLK.rdf";
                    } else {
                        gen1 = "REGSuratBatalPerserahan_NS.rdf";
                    }
                    code1 = "SBRP";
                    kd = kodDokumenDAO.findById(code1);
                    d = saveOrUpdateDokumen(fd, kd, perserahan.getIdPermohonan());
                    path = perserahan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    isExist = false;
                    cetak = true;
                    d1 = dokumenService.findDok(perserahan.getIdPermohonan(), "SBRP");
                    notifikasi(perserahan, pengguna);
                   
                    addSimpleMessage("Pembatalan Berjaya");

                    return new JSP("daftar/utiliti/pembatalan_perserahan.jsp");

                }
            }
        else{
        try {
            if (sebab == null) {
                addSimpleError("Sila Masukkan Sebab Pembatalan");
                return new JSP("daftar/utiliti/pembatalan_perserahan.jsp");
            }
            if (isDebug) {
                LOG.debug("taskId = " + taskId);
            }
            if (StringUtils.isNotBlank(taskId)) {                

                perserahan = permohonanService.findById(idPerserahan);
                KodKeputusan k = new KodKeputusan();
                k.setKod("TK");
                perserahan.setKeputusan(k);
                perserahan.setSebab(sebab);
                perserahan.setStatus("TK");
                InfoAudit ia = new InfoAudit();
                ia = perserahan.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                permohonanService.saveOrUpdate(perserahan);


                String gen1 = "";
                String code1 = "";
                String[] params = new String[]{"p_id_mohon"};
                String[] values = new String[]{perserahan.getIdPermohonan()};
                String path = "";
                String dokumenPath = conf.getProperty("document.path");
                Dokumen d = null;
                KodDokumen kd = null;
                FolderDokumen fd = folderDokumenDAO.findById(perserahan.getFolderDokumen().getFolderId());

                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    gen1 = "REGSuratBatalPerserahan_MLK.rdf";
                } else {
                    gen1 = "REGSuratBatalPerserahan_NS.rdf";
                }
                code1 = "SBRP";
                kd = kodDokumenDAO.findById(code1);
                d = saveOrUpdateDokumen(fd, kd, perserahan.getIdPermohonan());
                path = perserahan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                isExist = false;
                cetak = true;
                d1 = dokumenService.findDok(perserahan.getIdPermohonan(), "SBRP");
                notifikasi(perserahan, pengguna);
                WorkFlowService.withdrawTask(taskId);
                addSimpleMessage("Pembatalan Berjaya");
                
                if(idPerserahan.contains("DEV")){
                    checkWithTimer(idPerserahan);
                }
                
                return new JSP("daftar/utiliti/pembatalan_perserahan.jsp");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LOG.error(ex);
            addSimpleError(ex.getMessage());
            return new JSP("daftar/utiliti/pembatalan_perserahan.jsp");
        }
        }
        
    
        return new JSP("daftar/utiliti/pembatalan_perserahan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (StringUtils.isBlank(idPerserahan)) {
            idPerserahan = (String) getContext().getRequest().getParameter("perserahan.idPermohonan");
            if (idPerserahan != null) {
                perserahan = permohonanService.findById(idPerserahan);
            }
            
        }
        
        if (isDebug) {
            LOG.debug("idPerserahan =" + idPerserahan);
        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pengguna = ctx.getUser();
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
        doc.setTajuk("SBRP -" + id);
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

    public void notifikasi(Permohonan p, Pengguna pguna) {
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Pembatalan Perserahan - " + p.getIdPermohonan());
        n.setMesej("Permohonan Pembatalan Perserahan " + p.getIdPermohonan() + "<br/><br/>Sebab Pembatalan :" + p.getSebab());
        n.setCawangan(pguna.getKodCawangan());
        ArrayList<KodPeranan> listrole = new ArrayList<KodPeranan>();
        listrole.add(kodPerananDAO.findById("7"));
        listrole.add(kodPerananDAO.findById("4"));
        listrole.add(kodPerananDAO.findById("13"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, pguna.getKodCawangan(), listrole);
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }

    public Permohonan getPerserahan() {
        return perserahan;
    }

    public void setPerserahan(Permohonan perserahan) {
        this.perserahan = perserahan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isIsExist() {
        return isExist;
    }

    public void setIsExist(boolean isExist) {
        this.isExist = isExist;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public boolean isCetak() {
        return cetak;
    }

    public void setCetak(boolean cetak) {
        this.cetak = cetak;
    }

    public Dokumen getD1() {
        return d1;
    }

    public void setD1(Dokumen d1) {
        this.d1 = d1;
    }

    private void checkWithTimer(String idPerserahan) throws WorkflowException, StaleObjectException {
        long startTime = System.currentTimeMillis();
        boolean done = false;
        try {
        while (!done) {
            List<Task> l = WorkFlowService.queryTasksByAdmin(idPerserahan);
            if (l != null) {
                        for (Task t : l) {
                            stage = t.getSystemAttributes().getStage();
                            taskId = t.getSystemAttributes().getTaskId();
                            WorkFlowService.withdrawTask(taskId);
                            //addSimpleMessage("Pembatalan Berjaya");
                        }
            }
            
            if((System.currentTimeMillis()-startTime)< 3*60*1000){
                done = false;
            }else{
                done = true;
            }
            
            Thread.sleep(1*60*1000);
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
 
    }
    }
}
