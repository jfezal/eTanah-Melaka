/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKategoriTanah;
import etanah.model.KodLot;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.service.common.TaskDebugService;
import etanah.view.etanahActionBeanContext;
import etanah.view.penguatkuasaan.validator.GenerateIdPerserahanHakisanWorkflow;
import etanah.view.stripes.SenaraiTugasanActionBean;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_hakisan_tanah")
public class JenisHakisanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(JenisHakisanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    GenerateIdPerserahanHakisanWorkflow generateIdPerserahanHakisanWorkflow;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private TaskDebugService ts;
    private String idLaporTanahSpdn;
    private String idLapor;
    private LaporanTanah laporanTanah;
    private Hakmilik hakmilik;
    private String jenisSempadan;
    private String milikKerajaan;
    private String idHakmilik;
    private String keadaanTanah;
    private String kodKategoriTanah;
    private String catatan;
    private String kodLot;
    private String noLot;
    private Permohonan permohonan;
    private String idPermohonan;
    private String kod;
    private Pengguna pguna;
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    private String statusCarian;
    private String noLotCarian;
    private String kodLotCarian;
    private String kodKategoriTanahCarian;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private String jenisHakisan;
    private String luas;
    private String kodLuasCarian;
    private String kodLuas;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idMH;
    private String statusTanah;
    private Boolean statusEdit = Boolean.FALSE;
    private String messageAgihTugasan = "";
    private List<HakmilikPermohonan> senaraiHakmilikPerserahan;
    private List<HakmilikPermohonan> senaraiHakmilikPendaftaran;
    private List<Permohonan> senaraiPermohonanPendaftaran = new ArrayList<Permohonan>();
    private List<Permohonan> senaraiPermohonanPenguatkuasaan = new ArrayList<Permohonan>();
    private String stageId;
    IWorkflowContext ctxOnBehalf = null;
    private String nextStage;
    private String taskId;
    private String currentStage = "";
    private String statusHakisan;
    private PermohonanNota permohonanNota;
    private boolean statusNotaExist = Boolean.TRUE;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("/penguatkuasaan/maklumat_jenis_hakisan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("/penguatkuasaan/maklumat_jenis_hakisan.jsp").addParameter("tab", "true");
    }

    public Resolution addRecordJenisHakisan() {
        logger.info(":::::addRecordJenisHakisan");
        return new JSP("penguatkuasaan/popup_tambah_hakisan.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        } else {
            stageId = "semak_laporan2";
        }
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getPermohonanSebelum() != null) {
                //1) After create new IP
                logger.info(":::: id permohonan sebelum exist ::::" + permohonan.getPermohonanSebelum().getIdPermohonan());
                permohonan = permohonan.getPermohonanSebelum();

                senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan());
                if (!senaraiHakmilikPermohonan.isEmpty()) {

                    if (senaraiHakmilikPermohonan.get(0).getNomborRujukan() != null) {
                        senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan(), idPermohonan);
                    }
                }
            } else {
                senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan());
            }

            logger.info("size hakmilik permohonan : " + senaraiHakmilikPermohonan.size());

            System.out.println("-------------stageId--- :::" + stageId);

            permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
            logger.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
            if (permohonanNota != null) {
                logger.info("::: kandungan nota :" + permohonanNota.getNota());
                statusNotaExist = false;
            }
        }
    }

    public Resolution editRecordJenisHakisan() {
        logger.info(":::::editRecordJenisHakisan");
        idMH = getContext().getRequest().getParameter("id");
        if (StringUtils.isNotBlank(idMH)) {
            statusEdit = true;
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));
            if (hakmilikPermohonan != null) {
                if (hakmilikPermohonan.getHakmilik() != null) {
                    idHakmilik = hakmilikPermohonan.getHakmilik().getIdHakmilik();
                    statusTanah = "H";
                } else {
                    statusTanah = "K";
                }

                if (hakmilikPermohonan.getJenisPenggunaanTanah() != null) {
                    kodKategoriTanah = hakmilikPermohonan.getJenisPenggunaanTanah().getKod();
                }
                if (hakmilikPermohonan.getLot() != null) {
                    kodLot = hakmilikPermohonan.getLot().getKod();
                }
                if (hakmilikPermohonan.getKodUnitLuas() != null) {
                    kodLuas = hakmilikPermohonan.getKodUnitLuas().getKod();
                }

                noLot = hakmilikPermohonan.getNoLot();
                luas = hakmilikPermohonan.getLuasTerlibat().toString();
                jenisHakisan = hakmilikPermohonan.getJenisHakisan();
            }
        }
        return new JSP("penguatkuasaan/popup_tambah_hakisan.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        return new RedirectResolution(JenisHakisanActionBean.class, "locate");
    }

    public Resolution simpan() {

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idMH = getContext().getRequest().getParameter("idMH");
        System.out.println("id hakmilik: " + idHakmilik);
        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }

        if (StringUtils.isNotBlank(idMH)) {
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));
        }

        if (hakmilikPermohonan != null) {
            ia = hakmilikPermohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            hakmilikPermohonan = new HakmilikPermohonan();
        }

        hakmilikPermohonan.setPermohonan(permohonan);
        hakmilikPermohonan.setInfoAudit(ia);
        hakmilikPermohonan.setNoLot(noLot);
        hakmilikPermohonan.setLuasTerlibat(BigDecimal.valueOf(Double.parseDouble(luas)));


        if (hakmilik != null) {
            System.out.println("hakmilik save : " + hakmilik.getIdHakmilik());
            hakmilikPermohonan.setHakmilik(hakmilik);
        } else {
            hakmilikPermohonan.setHakmilik(null);
        }
        if (kodKategoriTanah != null) {
            KodKategoriTanah kttn = kodKategoriTanahDAO.findById(kodKategoriTanah);
            hakmilikPermohonan.setJenisPenggunaanTanah(kttn);
        } else {
            hakmilikPermohonan.setJenisPenggunaanTanah(null);
        }

        if (kodLot != null) {
            KodLot kl = kodLotDAO.findById(kodLot);
            hakmilikPermohonan.setLot(kl);
        } else {
            hakmilikPermohonan.setLot(null);
        }

        if (kodLuas != null) {
            KodUOM k = kodUOMDAO.findById(kodLuas);
            hakmilikPermohonan.setKodUnitLuas(k);
        } else {
            hakmilikPermohonan.setKodUnitLuas(null);
        }

        hakmilikPermohonan.setJenisHakisan(jenisHakisan);

        enforceService.simpanhakmilikPermohonan(hakmilikPermohonan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(JenisHakisanActionBean.class, "locate");
    }

    public Resolution findHakmilik() {
        logger.info("-----------findHakmilik-------------");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        logger.info("id hakmilik carian :" + idHakmilik);
        hakmilik = enforceService.semakIdHakmilik(idHakmilik);

        if (hakmilik != null) {
            statusCarian = "W"; //W = wujud
            noLot = hakmilik.getNoLot();
            luas = hakmilik.getLuas().toString();
            if (hakmilik.getLot() != null) {
                kodLotCarian = hakmilik.getLot().getKod();
            }
            if (hakmilik.getKegunaanTanah().getKategoriTanah().getKod() != null) {
                kodKategoriTanahCarian = hakmilik.getKegunaanTanah().getKategoriTanah().getKod();
            }

            if (hakmilik.getKodUnitLuas() != null) {
                kodLuasCarian = hakmilik.getKodUnitLuas().getKod();
            }
        } else {
            statusCarian = "TW"; //TW = Tidak Wujud
        }
        System.out.println(":::::: keputusan carian hakmilik ::::::");
        System.out.println("statusCarian :" + statusCarian);
        System.out.println("no lot :" + noLot);
        System.out.println("kodLotCarian :" + kodLotCarian);
        System.out.println("kodKategoriTanahCarian :" + kodKategoriTanahCarian);
        System.out.println("luas :" + luas);
        System.out.println("kodLuasCarian :" + kodLuasCarian);


        return new JSP("penguatkuasaan/popup_tambah_hakisan.jsp").addParameter("popup", "true");
    }

    public Resolution deleteJenisHakisan() {
        logger.info("deleteJenisHakisan");
        idMH = getContext().getRequest().getParameter("id");


        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            if (StringUtils.isNotBlank(idMH)) {
                hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));
                if (hakmilikPermohonan != null) {
                    hakmilikPermohonanDAO.delete(hakmilikPermohonan);
                }
            }

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(JenisHakisanActionBean.class, "locate");
    }

    public Resolution generateIdPerserahan() throws WorkflowException {

        senaraiHakmilikPerserahan = enforceService.findListHakmilik(permohonan.getIdPermohonan());
        logger.info("size senaraiHakmilikPermohonan : " + senaraiHakmilikPermohonan.size());

        messageAgihTugasan += "Penghantaran Berjaya untuk : \n";
        //1)Initiate task for new id permohonan
        for (int i = 0; i < senaraiHakmilikPerserahan.size(); i++) {
            hakmilikPermohonan = senaraiHakmilikPerserahan.get(i);
            if (hakmilikPermohonan.getJenisHakisan().equalsIgnoreCase("P")) { // P = Hakisan Penuh
                initiateTugasanPendaftaran(permohonan, pguna, "TMA", hakmilikPermohonan);
                System.out.println(" BIL : " + i + "::: KOD URUSAN TMA");
            } else if (hakmilikPermohonan.getJenisHakisan().equalsIgnoreCase("S")) { // S = Hakisan Sebahagian
                initiateTugasanPendaftaran(permohonan, pguna, "STMA", hakmilikPermohonan);
                System.out.println(" BIL : " + i + "::: KOD URUSAN STMA");
            }

        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(JenisHakisanActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

//        senaraiPermohonanPendaftaran = enforcementService.getSenaraiPermohonanPendaftaran(permohonan.getIdPermohonan());
//        for (int i = 0; i < senaraiPermohonanPendaftaran.size(); i++) {
//            Permohonan p = senaraiPermohonanPendaftaran.get(i);
//            messageAgihTugasan += p.getIdPermohonan() + ": Permohonan Perserahan untuk " + p.getKodUrusan().getKod() + ". \n";
//
//            senaraiPermohonanPenguatkuasaan = enforcementService.getSenaraiPermohonanPendaftaran(p.getIdPermohonan());
//            for (int j = 0; j < senaraiPermohonanPenguatkuasaan.size(); j++) {
//                Permohonan mohon = senaraiPermohonanPenguatkuasaan.get(j);
//                trace(mohon.getIdPermohonan());
//                messageAgihTugasan += mohon.getIdPermohonan() + ": Tugasan di hantar ke peringkat " + currentStage + ". \n \n";
//            }
//        }



        senaraiPermohonanPenguatkuasaan = enforcementService.getSenaraiPermohonanPendaftaran(permohonan.getIdPermohonan());
        for (int j = 0; j < senaraiPermohonanPenguatkuasaan.size(); j++) {
            Permohonan mohon = senaraiPermohonanPenguatkuasaan.get(j);
            trace(mohon.getIdPermohonan());
            messageAgihTugasan += mohon.getIdPermohonan() + ". \n";

            senaraiPermohonanPendaftaran = enforcementService.getSenaraiPermohonanPendaftaran(mohon.getIdPermohonan());
            for (int i = 0; i < senaraiPermohonanPendaftaran.size(); i++) {
                Permohonan p = senaraiPermohonanPendaftaran.get(i);
                messageAgihTugasan += p.getIdPermohonan() + ": Permohonan Perserahan untuk " + p.getKodUrusan().getKod() + ". \n \n";
            }
        }

        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());

        if (l.isEmpty()) {
            try {
                Thread.sleep(5000);
                l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
            } catch (Exception ex) {
                logger.error(ex);
            }
        }
        if (l != null) {
            for (Task t : l) {
                String taskId = t.getSystemAttributes().getTaskId();
                if (org.apache.commons.lang.StringUtils.isNotBlank(taskId)) {
                    try {
                        System.out.println("task id :::: " + taskId);
                        WorkFlowService.withdrawTask(taskId);
                        logger.info("Pembatalan Berjaya :: " + permohonan.getIdPermohonan());
                    } catch (StaleObjectException ex) {
                        ex.printStackTrace();
                        logger.error(ex);
                    }
                }
            }
        }

        messageAgihTugasan = messageAgihTugasan.replace("\n", "<br>");

        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", messageAgihTugasan);
    }

    public String trace(String idMohon) throws WorkflowException {
        Map m = ts.traceTask(idMohon);
        currentStage = (String) m.get("stage");
        System.out.println("currentStage ::::::: " + currentStage);

        return currentStage;
    }

    public void initiateTugasanPendaftaran(Permohonan permohonan, Pengguna pengguna, String kodUrusan, HakmilikPermohonan mohonHakmilik) {
        logger.info("-------initiateTugasanPendaftaran-------");
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        senaraiHakmilikPendaftaran = enforceService.findSenaraiHakmilik(permohonan.getIdPermohonan(), mohonHakmilik.getHakmilik().getIdHakmilik());
        logger.info(":::::::::::::size senaraiHakmilikPendaftaran :" + senaraiHakmilikPendaftaran.size());

        KodUrusan kod = kodUrusanDAO.findById(kodUrusan);

        logger.info("1 : " + kod.getKod());
        logger.info("2 : " + kod.getNama());
        generateIdPerserahanHakisanWorkflow.generateIdPerserahanWorkflow(kod, pengguna, mohonHakmilik.getHakmilik().getIdHakmilik(), senaraiHakmilikPendaftaran, permohonan);
    }

    public Resolution simpanJenisHakisan() {
        logger.info("------------simpanJenisHakisan--------------");
        logger.info("::::::::::size senaraiHakmilikPermohonan::::::::::  " + senaraiHakmilikPermohonan.size());
        for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
            idMH = getContext().getRequest().getParameter("idMohonHakmilik" + i);
            statusHakisan = getContext().getRequest().getParameter("statusHakisan" + i);
            if (StringUtils.isNotBlank(idMH)) {
                hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));
                if (hakmilikPermohonan != null) {
                    InfoAudit ia = new InfoAudit();
                    ia = hakmilikPermohonan.getInfoAudit();
                    ia.setDiKemaskiniOleh(pguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    hakmilikPermohonan.setInfoAudit(ia);
                    hakmilikPermohonan.setJenisHakisan(statusHakisan);
                    enforceService.simpanhakmilikPermohonan(hakmilikPermohonan);

                }
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("penguatkuasaan/maklumat_jenis_hakisan.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getIdLaporTanahSpdn() {
        return idLaporTanahSpdn;
    }

    public void setIdLaporTanahSpdn(String idLaporTanahSpdn) {
        this.idLaporTanahSpdn = idLaporTanahSpdn;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getJenisSempadan() {
        return jenisSempadan;
    }

    public void setJenisSempadan(String jenisSempadan) {
        this.jenisSempadan = jenisSempadan;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String getKodLot() {
        return kodLot;
    }

    public void setKodLot(String kodLot) {
        this.kodLot = kodLot;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public String getMilikKerajaan() {
        return milikKerajaan;
    }

    public void setMilikKerajaan(String milikKerajaan) {
        this.milikKerajaan = milikKerajaan;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getKodKategoriTanah() {
        return kodKategoriTanah;
    }

    public void setKodKategoriTanah(String kodKategoriTanah) {
        this.kodKategoriTanah = kodKategoriTanah;
    }

    public String getIdLapor() {
        return idLapor;
    }

    public void setIdLapor(String idLapor) {
        this.idLapor = idLapor;
    }

    public String getStatusCarian() {
        return statusCarian;
    }

    public void setStatusCarian(String statusCarian) {
        this.statusCarian = statusCarian;
    }

    public String getKodLotCarian() {
        return kodLotCarian;
    }

    public void setKodLotCarian(String kodLotCarian) {
        this.kodLotCarian = kodLotCarian;
    }

    public String getNoLotCarian() {
        return noLotCarian;
    }

    public void setNoLotCarian(String noLotCarian) {
        this.noLotCarian = noLotCarian;
    }

    public String getKodKategoriTanahCarian() {
        return kodKategoriTanahCarian;
    }

    public void setKodKategoriTanahCarian(String kodKategoriTanahCarian) {
        this.kodKategoriTanahCarian = kodKategoriTanahCarian;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getJenisHakisan() {
        return jenisHakisan;
    }

    public void setJenisHakisan(String jenisHakisan) {
        this.jenisHakisan = jenisHakisan;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getKodLuasCarian() {
        return kodLuasCarian;
    }

    public void setKodLuasCarian(String kodLuasCarian) {
        this.kodLuasCarian = kodLuasCarian;
    }

    public String getKodLuas() {
        return kodLuas;
    }

    public void setKodLuas(String kodLuas) {
        this.kodLuas = kodLuas;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdMH() {
        return idMH;
    }

    public void setIdMH(String idMH) {
        this.idMH = idMH;
    }

    public String getStatusTanah() {
        return statusTanah;
    }

    public void setStatusTanah(String statusTanah) {
        this.statusTanah = statusTanah;
    }

    public Boolean getStatusEdit() {
        return statusEdit;
    }

    public void setStatusEdit(Boolean statusEdit) {
        this.statusEdit = statusEdit;
    }

    public GenerateIdPerserahanHakisanWorkflow getGenerateIdPerserahanHakisanWorkflow() {
        return generateIdPerserahanHakisanWorkflow;
    }

    public void setGenerateIdPerserahanHakisanWorkflow(GenerateIdPerserahanHakisanWorkflow generateIdPerserahanHakisanWorkflow) {
        this.generateIdPerserahanHakisanWorkflow = generateIdPerserahanHakisanWorkflow;
    }

    public GeneratorIdPermohonan getIdPermohonanGenerator() {
        return idPermohonanGenerator;
    }

    public void setIdPermohonanGenerator(GeneratorIdPermohonan idPermohonanGenerator) {
        this.idPermohonanGenerator = idPermohonanGenerator;
    }

    public String getMessageAgihTugasan() {
        return messageAgihTugasan;
    }

    public void setMessageAgihTugasan(String messageAgihTugasan) {
        this.messageAgihTugasan = messageAgihTugasan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPerserahan() {
        return senaraiHakmilikPerserahan;
    }

    public void setSenaraiHakmilikPerserahan(List<HakmilikPermohonan> senaraiHakmilikPerserahan) {
        this.senaraiHakmilikPerserahan = senaraiHakmilikPerserahan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPendaftaran() {
        return senaraiHakmilikPendaftaran;
    }

    public void setSenaraiHakmilikPendaftaran(List<HakmilikPermohonan> senaraiHakmilikPendaftaran) {
        this.senaraiHakmilikPendaftaran = senaraiHakmilikPendaftaran;
    }

    public List<Permohonan> getSenaraiPermohonanPendaftaran() {
        return senaraiPermohonanPendaftaran;
    }

    public void setSenaraiPermohonanPendaftaran(List<Permohonan> senaraiPermohonanPendaftaran) {
        this.senaraiPermohonanPendaftaran = senaraiPermohonanPendaftaran;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public IWorkflowContext getCtxOnBehalf() {
        return ctxOnBehalf;
    }

    public void setCtxOnBehalf(IWorkflowContext ctxOnBehalf) {
        this.ctxOnBehalf = ctxOnBehalf;
    }

    public String getNextStage() {
        return nextStage;
    }

    public void setNextStage(String nextStage) {
        this.nextStage = nextStage;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public String getStatusHakisan() {
        return statusHakisan;
    }

    public void setStatusHakisan(String statusHakisan) {
        this.statusHakisan = statusHakisan;
    }

    public PermohonanNota getPermohonanNota() {
        return permohonanNota;
    }

    public void setPermohonanNota(PermohonanNota permohonanNota) {
        this.permohonanNota = permohonanNota;
    }

    public boolean isStatusNotaExist() {
        return statusNotaExist;
    }

    public void setStatusNotaExist(boolean statusNotaExist) {
        this.statusNotaExist = statusNotaExist;
    }
}
