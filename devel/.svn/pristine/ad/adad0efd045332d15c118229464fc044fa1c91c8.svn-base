package etanah.view.stripes.pelupusan.kaunter;

import etanah.view.daftar.kaunter.*;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodUrusanDAO;

import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;

import etanah.model.Pengguna;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanRujukanLuar;
import etanah.report.ReportUtil;
import etanah.service.PelupusanService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikUrusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.kaunter.UrusanValue;
import etanah.view.stripes.LinkActionBean;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.initiateService.InitiateTaskService;
import etanah.view.stripes.pelupusan.validator.CatitTanahValidator;
import etanah.view.stripes.pelupusan.validator.GenerateIdPerserahanWorkflow;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.HandlesEvent;
import oracle.bpel.services.workflow.StaleObjectException;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.hibernate.Transaction;

/**
 * Based on given idPermohonan/idPerserahan, suggest the next Urusan for the
 * Permohonan. The cases: 1 If the given Permohonan is NOT completed: 1.1 If the
 * Permohonan is at SPOC's senaraiTugasan, show the Workflow & required action
 * from user (e.g. Submit more documents, pay fees etc) 1.2 If not in SPOC's
 * senaraiTugasan, display the status 2 If Permohonan is completed, check the
 * next suggested Urusan for the Permohonan. E.g. after Consent's
 * "Kelulusan Pindahmilik", next is Registration's "PindahMilik Tanah". 3 If
 * Permohonan is completed but rejected, suggest Urusan for Rayuan.
 * 
 * @author hishammk
 * 
 */
@UrlBinding("/pelupusan/kaunter/kesinambungan")
public class KesinambunganUrusan extends PermohonanKaunter  {

    private Permohonan permohonan;
    @Inject
    private PermohonanDAO permohonanDAO;
    private List taskList;
    private String size = "";
    public List listValue = new ArrayList();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    @Inject
    private GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    
    @Inject
    private DisLaporanTanahService disLaporanTanahService;
    
    @Inject
    private KodUrusanDAO kodUrusanDAO ;
    
    @Inject
    InitiateTaskService its;
    
    @Inject
    PelupusanService pelupusanService;
    
    @Inject 
    KodKeputusanDAO kodKeputusanDAO;
    
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    
   @Inject
   HakmilikPermohonanService hakmilikPermohonanService;
   
   @Inject
   DokumenDAO dokumenDAO;
   
   @Inject
   KodDokumenDAO kodDokumenDAO;
   
   @Inject
   KodKlasifikasiDAO kodKlasifikasiDAO;
   
   @Inject
    private etanah.Configuration conf;
   
   @Inject
    private ReportUtil reportUtil;
   
   @Inject
   private FasaPermohonanService fasaPermohonanService;
    
    
    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    @DefaultHandler
    public Resolution insertIdPermohonan() {
        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/main.jsp");
    }
    private static Logger LOG = Logger.getLogger(KesinambunganUrusan.class);
    private static boolean isDebug = LOG.isDebugEnabled();   
   

    public Resolution checkPermohonan() throws WorkflowException {
        if (permohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/main.jsp");
        }
        String idPermohonan = permohonan.getIdPermohonan();
        if (idPermohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/main.jsp");
        }

        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan == null) {
            LOG.warn("Permohonan " + idPermohonan + " tidak dijumpai.");
            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/main.jsp");
        }

        // check if waiting for public's action
        // TODO: need to check if SPOC own this task
        if (semakBayaran(permohonan.getIdPermohonan())) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            Task impl = (Task) taskList.get(0);
            LOG.debug("TASK ID: " + impl.getSystemAttributes().getTaskId());
            return new RedirectResolution(LinkActionBean.class).addParameter(
                    "taskId", impl.getSystemAttributes().getTaskId()).addParameter("idPermohonan", permohonan.getIdPermohonan());
        } else {

            String kodUrusan = permohonan.getKodUrusan().getKod();
            KodKeputusan kpsn = permohonan.getKeputusan();

            // OTHER cases depend on keputusan
            if (kpsn == null) {

                addSimpleMessage("Urusan ini sedang diproses");
                return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");

            } else if ("D".equals(kpsn.getKod()) || "L".equals(kpsn.getKod())) {

                KodUrusan urusanSlps = permohonan.getKodUrusan().getKodUrusanSelepas();
                List<HakmilikPermohonan> listHp = permohonan.getSenaraiHakmilik();
                if ("PBMT".equals(kodUrusan)) {
                    String stage = fasaPermohonanService.getLastStage(permohonan.getIdPermohonan());
                    if ("semak_pu".equals(stage)) {
                        urusanSlps = kodUrusanDAO.findById("HSBM");
                        addSimpleMessage("Urusan ini telah selesai. Urusan yang dicadangkan berikutnya adalah "
                                + "seperti dibawah.");
                        return nextUrusanFromPrevUrusan(idPermohonan, urusanSlps.getKod(),
                                urusanSlps.getJabatan().getKod(), listHp);
                    } else if ("g_terima_pa_b1".equals(stage)) {
                        urusanSlps = kodUrusanDAO.findById("HKBM");
                        addSimpleMessage("Urusan ini telah selesai. Urusan yang dicadangkan berikutnya adalah "
                                + "seperti dibawah.");
                        return nextUrusanFromPrevUrusan(idPermohonan, urusanSlps.getKod(),
                                urusanSlps.getJabatan().getKod(), listHp);
                    } else {
                        return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");
                    }
                } else {
                    if (urusanSlps == null) {
                        return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");
                    } else {
                        addSimpleMessage("Urusan ini telah selesai. Urusan yang dicadangkan berikutnya adalah "
                                + "seperti dibawah.");
                        
                        return nextUrusanFromPrevUrusan(idPermohonan, urusanSlps.getKod(),
                                urusanSlps.getJabatan().getKod(), listHp);
                    }
                }
            } else if ("T".equals(kpsn.getKod())) { // TOLAK

                KodUrusan urusanRayuan = permohonan.getKodUrusan().getKodUrusanRayuan();

                if (urusanRayuan == null) {

                    addSimpleMessage("Urusan telah ditolak");
                    return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");

                } else {

                    addSimpleMessage("Urusan telah ditolak. Untuk pemohonan semula atau rayuan, "
                            + "sila pilih urusan dibawah.");
                    List<HakmilikPermohonan> listHp = permohonan.getSenaraiHakmilik();
                    RedirectResolution r = nextUrusanFromPrevUrusan(idPermohonan, urusanRayuan.getKod(),
                            urusanRayuan.getJabatan().getKod(), listHp);
                    r.addParameter("tambahUrusan", "Y");
                    return r;

                }

            } else if ("G".equals(kpsn)) { // Gantung

                addSimpleMessage("Urusan ini telah digantung. Sila hubungi Unit "
                        + permohonan.getKodUrusan().getJabatanNama());
                return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");

            } else {

                return null;

            }

        }
    }

    private boolean semakBayaran(String idPermohonan) throws WorkflowException {
        boolean semak = false;
        LOG.debug("-----idPermohonan----------" + idPermohonan);
        HttpSession ses = getContext().getRequest().getSession();
        Pengguna pengguna = (Pengguna) ses.getAttribute(etanahActionBeanContext.KEY_USER);
        IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());

        if (idPermohonan != null) {
            taskList = WorkFlowService.queryTasksByIdMohon(ctx, idPermohonan);
            size = taskList.size() + " Tugasan";
            LOG.debug("Size----------" + size);
            ses.setAttribute("size", size);
            listValue = new ArrayList();
            LOG.debug("-----list----------" + listValue);
            LOG.debug("-----list----------" + listValue.size());
            if (taskList.size() > 0) {
                semak = true;
            }
        }
        return semak;
    }

    private RedirectResolution nextUrusanFromPrevUrusan(String idPermohonanLama, String nextUrusan,
            String kodJabatan, List<HakmilikPermohonan> listHp) {
        RedirectResolution r = new RedirectResolution(PermohonanKaunter.class, "Step6b");
        r.addParameter("senaraiUrusan[0].kodUrusan", nextUrusan);
        r.addParameter("senaraiUrusan[0].kodUrusanPilih", nextUrusan);
        r.addParameter("senaraiUrusan[0].idPermohonanSebelum", idPermohonanLama);
        r.addParameter("strIdMohon", idPermohonanLama);
        r.addParameter("senaraiUrusan[0].kodJabatan", kodJabatan);

        // adding senarai hakmilik
//        for (int i = 0; i < listHp.size(); i++) {
//            HakmilikPermohonan hp = listHp.get(i);
//            r.addParameter("hakmilikPermohonan[" + i + "].hakmilik.idHakmilik", hp.getHakmilik().getIdHakmilik());
//        }

        return r;
    }
    
    @Override
    @HandlesEvent("Step6c")
    public Resolution save() {
       etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
       Pengguna pengguna = ctx.getUser();
       
       List<UrusanValue> senaraiUrusan = getSenaraiUrusan();
       ArrayList<UrusanValue> senaraiPermohonan = new ArrayList<UrusanValue>();
       LOG.debug("senaraiUrusan.size()=" + senaraiUrusan.size()); 
       Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        
       String kodNegeri = ctx.getKodNegeri();
       String documentPath = conf.getProperty("document.path");
       
       int cnt = 0;
       FolderDokumen fd = new FolderDokumen();
       fd.setTajuk("-");
       fd.setInfoAudit(ia);
        
        
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            // save the content
            ArrayList<KandunganFolder> akf = new ArrayList<KandunganFolder>();
            for (cnt = 0; cnt < senaraiUrusan.size(); cnt++) {
                UrusanValue uv = senaraiUrusan.get(cnt);
                String stage = "";
                Permohonan permohonanSebelum = permohonanDAO.findById(uv.getIdPermohonanSebelum());
                if (permohonanSebelum != null) {
                    stage = fasaPermohonanService.getLastStage(permohonanSebelum.getIdPermohonan());
                }


                Permohonan p = save(permohonanSebelum, kodNegeri, pengguna, stage);       
                if (tx.wasCommitted()) {
                    tx = s.beginTransaction();
                }
                uv.setIdPermohonan(p.getIdPermohonan());
                // surat akuan penerimaan
                Dokumen akuanPenerimaan = new Dokumen();
                akuanPenerimaan.setKodDokumen(kodDokumenDAO.findById("UNKN1"));
                akuanPenerimaan.setFormat("application/pdf");
                akuanPenerimaan.setInfoAudit(ia);
                akuanPenerimaan.setKlasifikasi(klasifikasiAm);
                akuanPenerimaan.setNoVersi("1.0");
                akuanPenerimaan.setTajuk("Akuan Penerimaan " + p.getIdPermohonan());
                dokumenDAO.save(akuanPenerimaan);
                KandunganFolder kf1 = new KandunganFolder();
                kf1.setFolder(fd);
                kf1.setDokumen(akuanPenerimaan);
                kf1.setInfoAudit(ia);
                akf.add(kf1);
                uv.setAkuanPenerimaan(akuanPenerimaan);
                senaraiPermohonan.add(uv);

            }

            for (UrusanValue uv : senaraiPermohonan) {
                if (uv.getAkuanPenerimaan() == null) {
                    continue;
                }

                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator) + uv.getAkuanPenerimaan().getIdDokumen();

                if ("04".equals(kodNegeri)) {
                    reportUtil.generateReport("HSLResitAkuanPenerimaan002_MLK.rdf",
                            new String[]{"p_id_mohon"},
                            new String[]{uv.getIdPermohonan()},
                            path, pengguna);
                } else {
                    reportUtil.generateReport("HSLResitAkuanPenerimaan002.rdf",
                            new String[]{"p_id_mohon"},
                            new String[]{uv.getIdPermohonan()},
                            path, pengguna);
                }
                uv.getAkuanPenerimaan().setNamaFizikal(reportUtil.getDMSPath());
                dokumenDAO.update(uv.getAkuanPenerimaan());
            }

            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            LOG.error(ex);
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/main.jsp");
        }
        
       
       ctx.removeWorkdata();
       setSenaraiPermohonan(senaraiPermohonan);
       addSimpleMessage("Urusan telah berjaya didaftarkan.");

       return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/cetak_resit.jsp");
    }
    
    
    
    public Permohonan save(Permohonan permohonan, String kodNegeri, Pengguna peng, String stageName) {        
               
        int numUrusan = permohonan.getKodUrusan().getKod().equals("MCMCL") ? 1
                : permohonan.getKodUrusan().getKod().equals("MMMCL") ? 2
                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 3
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 4
                : permohonan.getKodUrusan().getKod().equals("PHLA") ? 5
                : permohonan.getKodUrusan().getKod().equals("RAYT") ? 6
                : permohonan.getKodUrusan().getKod().equals("PBHL") ? 7
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 8
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 9
                : 0;
        KodUrusan kod = new KodUrusan();
        InfoAudit infoAudit = new InfoAudit();
        
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        
        
        
        switch (numUrusan) {
            case 1: //MCMCL                
                if (stageName.equals("kemasukan")) {                   
                    kod = kodUrusanDAO.findById("MCLM");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (stageName.equals("sedia_surat_tolak")) {
                    
                    kod = kodUrusanDAO.findById("MCLM"); //KOD MCLM IS NOT FOR THIS STAGE, NEED TO VERIFY
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (stageName.equals("sedia_cukai_baru")) {
                  
                    kod = kodUrusanDAO.findById("MCLL");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        PermohonanRujukanLuar mohonRujLuar = new PermohonanRujukanLuar();
                        mohonRujLuar = pelupusanService.findPermohonanRujByNoFail(permohonan.getIdPermohonan());
                        if (mohonRujLuar != null) {
                            /*
                             * CHECKING HAKMILIK_URUSAN
                             */
                            HakmilikUrusan hu = new HakmilikUrusan();
                            hu = pelupusanService.findHakmilikUrusanByIdMohon(mohonRujLuar.getPermohonan().getIdPermohonan());
                            if (hu != null) {
                                if (hu.getAktif() == 'Y') {
                                    permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                                    its.setHakmilikPermohonan(permohonan, permohonanBaru);
                                }
                            }
                        } 
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (stageName.equals("bayaran_proses")) {                    
                    kod = kodUrusanDAO.findById("MCLL");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        PermohonanRujukanLuar mohonRujLuar = new PermohonanRujukanLuar();
                        mohonRujLuar = pelupusanService.findPermohonanRujByNoFail(permohonan.getIdPermohonan());
                        if (mohonRujLuar != null) {
                            /*
                             * CHECKING HAKMILIK_URUSAN
                             */
                            HakmilikUrusan hu = new HakmilikUrusan();
                            hu = pelupusanService.findHakmilikUrusanByIdMohon(mohonRujLuar.getPermohonan().getIdPermohonan());
                            if (hu != null) {
                                if (hu.getAktif() == 'Y') {
                                    permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                                    its.setHakmilikPermohonan(permohonan, permohonanBaru);
                                } 
                            }
                        } 
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 2: //MMMCL
                
                if (stageName.equals("kemasukan")) {
                
                    kod = kodUrusanDAO.findById("MCLM");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuarMultipleHakmilik(permohonanBaru, permohonan.getIdPermohonan(), "FL", permohonan);
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }

//                    kod = kodUrusanDAO.findById("SBKBG"); // Hold dulu - Changed to SBKSM
                    kod = kodUrusanDAO.findById("SBKSM");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuarMultipleHakmilik(permohonanBaru, permohonan.getIdPermohonan(), "FL", permohonan);
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    FasaPermohonan fasaPermohonan = new FasaPermohonan();
                    fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "kemasukan");
                    if (fasaPermohonan != null) {
                        fasaPermohonan.setPermohonan(permohonan);
                        fasaPermohonan.setIdPengguna(peng.getIdPengguna());
                        InfoAudit info = new InfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        fasaPermohonan.setInfoAudit(info);
                        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();

                        for (HakmilikPermohonan hm : senaraiHakmilik) {
                            if (hm.getHakmilik().getKodHakmilik().getKod().equals("HSM")) {
                                fasaPermohonan.setKeputusan(kodKeputusanDAO.findById("YQ"));
                            } else if (hm.getHakmilik().getKodHakmilik().getKod().equals("PM") || hm.getHakmilik().getKodHakmilik().getKod().equals("GM")) {
                                fasaPermohonan.setKeputusan(kodKeputusanDAO.findById("YT"));
                            }
                        }
                        LOG.debug("--update keputusan based on jenis hakmilik--");
                        fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);
                    }
                } else if (stageName.equals("perakuan_ptd")) {                   
                    FasaPermohonan fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "perakuan_ptd");
                    if (fasaPermohonan != null) {
                        if (fasaPermohonan.getKeputusan().getKod().equals("L")) {
                            kod = kodUrusanDAO.findById("MCLL");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            Permohonan permohonanBaru = new Permohonan();
                            its.setPengguna(peng);
                            try {
                                permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                                its.setHakmilikPermohonan(permohonan, permohonanBaru);
                                its.setMohonRujukanLuarMultipleHakmilik(permohonanBaru, permohonan.getIdPermohonan(), "FL", permohonan);
                            } catch (WorkflowException ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (StaleObjectException ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (fasaPermohonan.getKeputusan().getKod().equals("T")) {
                            kod = kodUrusanDAO.findById("SBKSB");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            Permohonan permohonanBaru = new Permohonan();
                            its.setPengguna(peng);
                            try {
                                permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                                its.setHakmilikPermohonan(permohonan, permohonanBaru);
                                its.setMohonRujukanLuarMultipleHakmilik(permohonanBaru, permohonan.getIdPermohonan(), "FL", permohonan);
                            } catch (WorkflowException ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (StaleObjectException ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                }//Sementara untuk pembatalan noting 
                else if (stageName.equals("sedia_surat_tolak")) {
                   
                    kod = kodUrusanDAO.findById("SBTB");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);

                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (stageName.equals("maklum_bayaran")) {
                    
                    //kod = kodUrusanDAO.findById("MCLL"); 
                    kod = kodUrusanDAO.findById("SBKSL");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuarMultipleHakmilik(permohonanBaru, permohonan.getIdPermohonan(), "FL", permohonan);
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                else if (stageName.equals("sedia_jadual")) {                    

                    LOG.info("Initiate HSBM or HKBM");                   
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());

                
                    FasaPermohonan fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "kemasukan");
                    String[] name = {"permohonan"};
                    Object[] value = {permohonan};
                    List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                    List<HakmilikPermohonan> senaraiHakmilikHSBM = new ArrayList<HakmilikPermohonan>();
                    List<HakmilikPermohonan> senaraiHakmilikHKBM = new ArrayList<HakmilikPermohonan>();
                    String jenisHM = "";
        

                    if (fasaPermohonan.getKeputusan().getKod().equals("YQ")) {
                        kod = kodUrusanDAO.findById("HSBM");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan, stageName);
                        return generateIdPerserahanWorkflow.getPermohonan();
                    } else if (fasaPermohonan.getKeputusan().getKod().equals("YT")) {
                        kod = kodUrusanDAO.findById("HKBM");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan, stageName);
                        return generateIdPerserahanWorkflow.getPermohonan();
                    }
                }

                break;
            
            case 4:
                //PTGSA
              
                if (stageName.equals("18TerimaWartaPNB")) {
                    
                    kod = kodUrusanDAO.findById("IGSAB");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;
            case 5:
                //PHLA
               
                if (stageName.equals("38SedPelan")) {
                    
                    FasaPermohonan mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "20BtPrnth");
                    if (mohonFasa != null) {
                        if (mohonFasa.getKeputusan() != null) {
                            if (mohonFasa.getKeputusan().getKod().equals("AW")) {
                                kod = kodUrusanDAO.findById("HLLA");
                            } else if (mohonFasa.getKeputusan().getKod().equals("XW")) {
                                kod = kodUrusanDAO.findById("HLLS");
                            }
                        }
                    }
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (stageName.equals("45TrmSalinanPA")) {                    
                    FasaPermohonan mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "20BtPrnth");
                    if (mohonFasa != null) {
                        if (mohonFasa.getKeputusan() != null) {
                            if (mohonFasa.getKeputusan().getKod().equals("AW")) {
                                kod = kodUrusanDAO.findById("HLLA");
                            } else if (mohonFasa.getKeputusan().getKod().equals("XW")) {
                                kod = kodUrusanDAO.findById("HLLS");
                            }
                        }
                    }
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (stageName.equals("35SmkdanCtkTndtngn")) {                    
                    FasaPermohonan mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "20BtPrnth");
                    if (mohonFasa != null) {
                        if (mohonFasa.getKeputusan() != null) {
                            if (mohonFasa.getKeputusan().getKod().equals("AW")) {
                                kod = kodUrusanDAO.findById("HLLA");
                            } else if (mohonFasa.getKeputusan().getKod().equals("XW")) {
                                kod = kodUrusanDAO.findById("HLLS");
                            }
                        }
                    }
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;
            case 6:
                //RAYT (Kes tiada hakmilik)
               
                if (stageName.equals("027_SediaPU") || stageName.equals("g_terima_pa_b1")) {                    

                    LOG.info("Initiate HSBM or HKBM");                    
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                  
                    String[] name = {"permohonan"};
                    Object[] value = {permohonan};
                    List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                    List<HakmilikPermohonan> senaraiHakmilikHSBM = new ArrayList<HakmilikPermohonan>();
                    List<HakmilikPermohonan> senaraiHakmilikHKBM = new ArrayList<HakmilikPermohonan>();

                    for (HakmilikPermohonan hm : senaraiHakmilik) {
                        if (hm.getKodHakmilik().getKod().equals("HSM") || hm.getKodHakmilik().getKod().equals("HMM") || hm.getKodHakmilik().getKod().equals("HSD")) {
                            senaraiHakmilikHSBM.add(hm);
                        } else if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("GMM")
                                || hm.getKodHakmilik().getKod().equals("PN") || hm.getKodHakmilik().getKod().equals("PM")) {
                            senaraiHakmilikHKBM.add(hm);
                        }
                    }

                    if (senaraiHakmilikHSBM.size() > 0) {
                        kod = kodUrusanDAO.findById("HSBM");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHSBM, permohonan, stageName);
                        return generateIdPerserahanWorkflow.getPermohonan();
                    } else if (senaraiHakmilikHKBM.size() > 0) {
                        kod = kodUrusanDAO.findById("HKBM");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHKBM, permohonan, stageName);
                        return generateIdPerserahanWorkflow.getPermohonan();
                    }
                }

                break;
            case 7:
                //PBHL
               Permohonan permohonanBaru = new Permohonan();
                if (stageName.equals("22SedButir")) {                   
                    List<HakmilikPermohonan> senaraiHakmilikTerlibat = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", permohonan.getIdPermohonan());
                    Hakmilik hm = new Hakmilik();
                    hm = senaraiHakmilikTerlibat.get(0).getHakmilik();
                    List<HakmilikUrusan> hmUrusan = new ArrayList<HakmilikUrusan>();
                    hmUrusan = hakmilikUrusanService.findHakmilikUrusanByIdHakmilik(hm.getIdHakmilik());
                    boolean isTrue = false;
                    List<Permohonan> senaraisemakmohon = pelupusanService.getsenaraiIdMohon(permohonan.getIdPermohonan());
                    if (senaraisemakmohon.size() == 0) {
                      
                        LOG.info("senaraisemakmohon.size()");
                        kod = kodUrusanDAO.findById("HLLB");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        
                        its.setPengguna(peng);
                        try {
                            permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                            its.setHakmilikPermohonan(permohonan, permohonanBaru);
                            its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                        } catch (WorkflowException ex) {
                            java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (StaleObjectException ex) {
                            java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                    } else {
                        List<Permohonan> senaraimohondahdaftar = pelupusanService.getsenaraiIdMohonwithStatus(permohonan.getIdPermohonan());                        
                    }
                } else if (stageName.equals("29Semakan")) {                    
                    kod = kodUrusanDAO.findById("HKBM");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());                    
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                 
                return permohonanBaru;
            case 8:
            
                if (stageName.equals("terima_pa_B1")) {                    

                    LOG.info("Initiate HSBM or HKBM");                    
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());

                  
                    String[] name = {"permohonan"};
                    Object[] value = {permohonan};
                    List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                    List<HakmilikPermohonan> senaraiHakmilikHSBM = new ArrayList<HakmilikPermohonan>();
                    List<HakmilikPermohonan> senaraiHakmilikHKBM = new ArrayList<HakmilikPermohonan>();
                    String jenisHM = "";

                    for (HakmilikPermohonan hm : senaraiHakmilik) {
                        if (hm.getKodHakmilik().getKod().equals("HSM") || hm.getKodHakmilik().getKod().equals("HMM") || hm.getKodHakmilik().getKod().equals("HSD")) {
                            senaraiHakmilikHSBM.add(hm);
                        } else if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("GMM")
                                || hm.getKodHakmilik().getKod().equals("PN") || hm.getKodHakmilik().getKod().equals("PM")) {
                            senaraiHakmilikHKBM.add(hm);
                        }
                    }

                    if (senaraiHakmilikHSBM.size() > 0) {
                        kod = kodUrusanDAO.findById("HSBM");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHSBM, permohonan, stageName);
                        return generateIdPerserahanWorkflow.getPermohonan();
                    } else if (senaraiHakmilikHKBM.size() > 0) {
                        kod = kodUrusanDAO.findById("HKBM");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHKBM, permohonan, stageName);
                        return generateIdPerserahanWorkflow.getPermohonan();
                    }
                }
                break;
            case 9:
                //PBMT
               
                if (kodNegeri.equals("04")) {
                    if (stageName.equals("g_penyediaan_pu_pt") || stageName.equals("semak_charting_hakmilik") || stageName.equals("g_penyediaan_pu2)")) {
                      
                        LOG.info("Initiate HSBM or HKBM");                       
                        infoAudit = new InfoAudit();
                        infoAudit.setDimasukOleh(peng);
                        infoAudit.setTarikhMasuk(new java.util.Date());


//                    String idMohon = context.getPermohonan().getPermohonanSebelum().getIdPermohonan();
                        String[] name = {"permohonan"};
                        Object[] value = {permohonan};
                        List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                        List<HakmilikPermohonan> senaraiHakmilikHSBM = new ArrayList<HakmilikPermohonan>();
                        List<HakmilikPermohonan> senaraiHakmilikHKBM = new ArrayList<HakmilikPermohonan>();

                        for (HakmilikPermohonan hm : senaraiHakmilik) {
                            if (hm.getKodHakmilik().getKod().equals("HSM") || hm.getKodHakmilik().getKod().equals("HMM") || hm.getKodHakmilik().getKod().equals("HSD")) {
                                senaraiHakmilikHSBM.add(hm);
                            } else if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("GMM")
                                    || hm.getKodHakmilik().getKod().equals("PN") || hm.getKodHakmilik().getKod().equals("PM")) {
                                senaraiHakmilikHKBM.add(hm);
                            }
                        }

                        if (senaraiHakmilikHSBM.size() > 0) {
                            kod = kodUrusanDAO.findById("HSBM");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHSBM, permohonan, stageName);
                            return generateIdPerserahanWorkflow.getPermohonan();
                        } else if (senaraiHakmilikHKBM.size() > 0) {
                            kod = kodUrusanDAO.findById("HKBM");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHKBM, permohonan, stageName);
                            return generateIdPerserahanWorkflow.getPermohonan();

                        }
                    }
                } else {
                    if (stageName.equals("semak_pu")) {                       
                        List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
                        listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "K");

                        LOG.info("Initiate HSBM");                      
                        infoAudit = new InfoAudit();
                        infoAudit.setDimasukOleh(peng);
                        infoAudit.setTarikhMasuk(new java.util.Date());


//                    String idMohon = context.getPermohonan().getPermohonanSebelum().getIdPermohonan();
//                        String[] name = {"permohonan"};
//                        Object[] value = {permohonan};
//                        List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                        List<HakmilikPermohonan> senaraiHakmilikHSBMPendaftar = new ArrayList<HakmilikPermohonan>();
                        List<HakmilikPermohonan> senaraiHakmilikHSBMDaerah = new ArrayList<HakmilikPermohonan>();
                        InfoAudit info = new InfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        if (listMohonKelompok.size() > 0) {
                            LOG.info("Kelompok mode");
                            Permohonan p = new Permohonan();
                            for (PermohonanKelompok pk : listMohonKelompok) {
                                p = pk.getPermohonan();
                                LOG.info("Kod Urusan : " + p.getKodUrusan().getKod());
                                LOG.info("Size HMP : " + p.getSenaraiHakmilik().size());
                                for (HakmilikPermohonan hm : p.getSenaraiHakmilik()) {
                                    hm.setInfoAudit(info);
                                    if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("PN")) {
                                        hm.setKodHakmilikSementara(disLaporanTanahService.getKodHakmilikDAO().findById("HSD"));
                                        if (hm.getKodHakmilik().getKod().equals("PN")) {
                                            hm.setPegangan("P");
                                            hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                            hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                        } else {
                                            hm.setPegangan("S");
                                            hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                            hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                        }
                                        hm.setPermohonan(p);
                                        hakmilikPermohonanService.saveSingleHakmilikPermohonan(hm);
                                        senaraiHakmilikHSBMPendaftar.add(hm);
                                    } else if (hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("PM")) {
                                        hm.setKodHakmilikSementara(disLaporanTanahService.getKodHakmilikDAO().findById("HSM"));
                                        if (hm.getKodHakmilik().getKod().equals("PM")) {
                                            hm.setPegangan("P");
                                            hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                            hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                        } else {
                                            hm.setPegangan("S");
                                            hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                            hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                        }
                                        hm.setPermohonan(p);
                                        hakmilikPermohonanService.saveSingleHakmilikPermohonan(hm);
                                        senaraiHakmilikHSBMDaerah.add(hm);
                                    }
                                }

                                LOG.info("Size Hakmilik Daerah : " + senaraiHakmilikHSBMDaerah.size());
                                LOG.info("Size Hakmilik Pendaftar : " + senaraiHakmilikHSBMPendaftar.size());
                                if (senaraiHakmilikHSBMPendaftar.size() > 0) {
                                    LOG.info("HSBM Pendaftar");
                                    kod = kodUrusanDAO.findById("HSBM");
                                    LOG.info(kod.getNama());
                                    LOG.info(permohonan.getFolderDokumen());
                                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPendaftar(kod, peng, senaraiHakmilikHSBMPendaftar, permohonan, stageName);
                                    return generateIdPerserahanWorkflow.getPermohonan();
                                }

                                if (senaraiHakmilikHSBMDaerah.size() > 0) {
                                    kod = kodUrusanDAO.findById("HSBM");
                                    LOG.info("HSBM Daerah");
                                    LOG.info(kod.getNama());
                                    LOG.info(permohonan.getFolderDokumen());
                                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHSBMDaerah, permohonan, stageName);
                                    return generateIdPerserahanWorkflow.getPermohonan();
                                }
                            }
                        } else {
                            LOG.info("Kod Urusan : " + permohonan.getKodUrusan().getKod());
                            LOG.info("Size HMP : " + permohonan.getSenaraiHakmilik().size());
                            for (HakmilikPermohonan hm : permohonan.getSenaraiHakmilik()) {
                                hm.setInfoAudit(info);
                                if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("PN")) {
                                    hm.setKodHakmilikSementara(disLaporanTanahService.getKodHakmilikDAO().findById("HSD"));
                                    if (hm.getKodHakmilik().getKod().equals("PN")) {
                                        hm.setPegangan("P");
                                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                        hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                    } else {
                                        hm.setPegangan("S");
                                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                        hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                    }
                                    hm.setPermohonan(permohonan);
                                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hm);
                                    senaraiHakmilikHSBMPendaftar.add(hm);
                                } else if (hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("PM")) {
                                    hm.setKodHakmilikSementara(disLaporanTanahService.getKodHakmilikDAO().findById("HSM"));
                                    if (hm.getKodHakmilik().getKod().equals("PM")) {
                                        hm.setPegangan("P");
                                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                        hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                    } else {
                                        hm.setPegangan("S");
                                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                        hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                    }
                                    hm.setPermohonan(permohonan);
                                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hm);
                                    senaraiHakmilikHSBMDaerah.add(hm);
                                }
                            }
                            LOG.info("Size Hakmilik Daerah : " + senaraiHakmilikHSBMDaerah.size());
                            LOG.info("Size Hakmilik Pendaftar : " + senaraiHakmilikHSBMPendaftar.size());
                            if (senaraiHakmilikHSBMPendaftar.size() > 0) {
                                LOG.info("HSBM Pendaftar");
                                kod = kodUrusanDAO.findById("HSBM");
                                LOG.info(kod.getNama());
                                LOG.info(permohonan.getFolderDokumen());
                                generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPendaftar(kod, peng, senaraiHakmilikHSBMPendaftar, permohonan, stageName);
                                return generateIdPerserahanWorkflow.getPermohonan();
                            }

                            if (senaraiHakmilikHSBMDaerah.size() > 0) {
                                kod = kodUrusanDAO.findById("HSBM");
                                LOG.info("HSBM Daerah");
                                LOG.info(kod.getNama());
                                LOG.info(permohonan.getFolderDokumen());
                                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHSBMDaerah, permohonan, stageName);
                                return generateIdPerserahanWorkflow.getPermohonan();
                            }
                        }

                    } else if (stageName.equals("g_terima_pa_b1")) {
                     
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "g_terima_pa_b1");

                        if (mohonFasa != null) {
                            if (mohonFasa.getKeputusan() != null) {
                                if (mohonFasa.getKeputusan().getKod().equals("LO")) {
                                    LOG.info("Initiate HKBM");                                    
                                    infoAudit = new InfoAudit();
                                    infoAudit.setDimasukOleh(peng);
                                    infoAudit.setTarikhMasuk(new java.util.Date());
                                    String[] name = {"permohonan"};
                                    Object[] value = {permohonan};
                                    List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                                    List<HakmilikPermohonan> senaraiHakmilikHKBMPendaftar = new ArrayList<HakmilikPermohonan>();
                                    List<HakmilikPermohonan> senaraiHakmilikHKBMDaerah = new ArrayList<HakmilikPermohonan>();
                                    InfoAudit info = new InfoAudit();
                                    info.setDiKemaskiniOleh(peng);
                                    info.setTarikhKemaskini(new java.util.Date());
                                    boolean statusQT = false;
                                    for (HakmilikPermohonan hm : senaraiHakmilik) {
                                        hm.setInfoAudit(info);
                                        if (hm.getKodHakmilikSementara() != null) { //From QT to FT
                                            if (hm.getKodHakmilikTetap().getKod().equals("GRN") || hm.getKodHakmilikTetap().getKod().equals("PN")) {
                                                hm.setKodHakmilikSementara(null);
                                                if (hm.getKodHakmilik().getKod().equals("PN")) {
                                                    hm.setPegangan("P");
                                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                                } else {
                                                    hm.setPegangan("S");
                                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                                }
                                                hm.setPermohonan(permohonan);
                                                hm = hakmilikPermohonanService.save(hm);
                                                senaraiHakmilikHKBMPendaftar.add(hm);
                                            } else if (hm.getKodHakmilikTetap().getKod().equals("GM") || hm.getKodHakmilikTetap().getKod().equals("PM")) {
                                                hm.setKodHakmilikSementara(null);
                                                if (hm.getKodHakmilik().getKod().equals("PM")) {
                                                    hm.setPegangan("P");
                                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                                } else {
                                                    hm.setPegangan("S");
                                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                                }
                                                hm.setPermohonan(permohonan);
                                                hm = hakmilikPermohonanService.save(hm);
                                                senaraiHakmilikHKBMDaerah.add(hm);
                                            }
                                            statusQT = Boolean.TRUE;
                                        } else { //Straight FT
                                            if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("PN")) {
                                                hm.setKodHakmilikSementara(null);
                                                if (hm.getKodHakmilik().getKod().equals("PN")) {
                                                    hm.setPegangan("P");
                                                } else {
                                                    hm.setPegangan("S");
                                                }
                                                hm.setPermohonan(permohonan);
                                                hm = hakmilikPermohonanService.save(hm);
                                                senaraiHakmilikHKBMPendaftar.add(hm);
                                            } else if (hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("PM")) {
                                                hm.setKodHakmilikSementara(null);
                                                if (hm.getKodHakmilik().getKod().equals("PM")) {
                                                    hm.setPegangan("P");
                                                } else {
                                                    hm.setPegangan("S");
                                                }
                                                hm.setPermohonan(permohonan);
                                                hm = hakmilikPermohonanService.save(hm);
                                                senaraiHakmilikHKBMDaerah.add(hm);
                                            }
                                            statusQT = Boolean.FALSE;
                                        }
                                    }

                                    if (senaraiHakmilikHKBMPendaftar.size() > 0) {
                                        if (statusQT) {
                                            kod = kodUrusanDAO.findById("HKGHS");
                                            LOG.info("HKGHS Pendaftar");
                                        } else {
                                            kod = kodUrusanDAO.findById("HKBM");
                                            LOG.info("HKBM Pendaftar");
                                        }

                                        LOG.info(kod.getNama());
                                        LOG.info(permohonan.getFolderDokumen());
                                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPendaftar(kod, peng, senaraiHakmilikHKBMPendaftar, permohonan, stageName);
                                        return generateIdPerserahanWorkflow.getPermohonan();
                                    }

                                    if (senaraiHakmilikHKBMDaerah.size() > 0) {
                                        if (statusQT) {
                                            kod = kodUrusanDAO.findById("HKGHS");
                                            LOG.info("HKGHS Daerah");
                                        } else {
                                            kod = kodUrusanDAO.findById("HKBM");
                                            LOG.info("HKBM Daerah");
                                        }
                                        LOG.info(kod.getNama());
                                        LOG.info(permohonan.getFolderDokumen());
                                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHKBMDaerah, permohonan, stageName);
                                        return generateIdPerserahanWorkflow.getPermohonan();
                                    }
                                }
                            }
                        }
                    } 
                }

                break;
        }
        return null;
    }
    
    
}
