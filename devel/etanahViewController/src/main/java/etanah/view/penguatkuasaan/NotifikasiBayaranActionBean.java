/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodPeranan;
import etanah.model.Kompaun;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.NotifikasiBayaranService;
import etanah.service.NotifikasiService;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.CommonService;
import etanah.view.stripes.SenaraiTugasanActionBean;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
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

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/notifikasi_bayaran")
public class NotifikasiBayaranActionBean extends AbleActionBean {

    @Inject
    CommonService comm;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanTuntutanDAO permohonanTuntutanDAO;
    @Inject
    NotifikasiBayaranService notifikasiBayaranService;
    @Inject
    PermohonanDAO permohonanDAO;
    private List<PermohonanTuntutan> listMohonTuntut = new ArrayList<PermohonanTuntutan>();
    private Date date = new Date();
    private static final Logger LOG = Logger.getLogger(NotifikasiBayaranActionBean.class);
    private String idPermohonan;
    private String taskId;
    private String nextStage;
    private Pengguna pengguna;
    private List<Kompaun> senaraiKompaun;
    private Kompaun kompaun;
    private Permohonan permohonan;
    private String keputusan;
    private String stageId;

    @DefaultHandler
    public Resolution checkBayaran() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
            if (stageId.equalsIgnoreCase("bayaran_kos_remedi")) {
                senaraiKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "BR");
            }
        } else {
            senaraiKompaun = enforcementService.findKompaunByID(idPermohonan);
        }

        BigDecimal totalPayment = BigDecimal.ZERO;
        LOG.info("------------size senaraiKompaun-------------- : " + senaraiKompaun.size());

        for (int i = 0; i < senaraiKompaun.size(); i++) {
            kompaun = senaraiKompaun.get(i);

            if (kompaun.getResit() == null) {
                PermohonanTuntutanBayar ptb = enforceService.findMohonTuntutBayar(kompaun.getPermohonanTuntutanKos().getIdKos());
                if (ptb == null) {
                    listMohonTuntut = enforceService.findMohonTuntutbyDate(date, idPermohonan);
                    System.out.println("list mohon tuntut : " + listMohonTuntut.size());
                }

                if (kompaun.getAmaun() != null) {
                    totalPayment = totalPayment.add(kompaun.getAmaun());
                }
            }
        }

        LOG.info("total payment : " + totalPayment);

        if (listMohonTuntut.size() != 0) {
            notifikasi(permohonan, listMohonTuntut.size(), totalPayment);
            skipOneStage();
        }

//        String messageAgihTugasan = "Tugasan telah berjaya di hantar ke peringkat : " + nextStage;
        String messageAgihTugasan = permohonan.getIdPermohonan() + "- Penghantaran Berjaya.";
        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", messageAgihTugasan);
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/skip_stage.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        BPelService service = new BPelService();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task task = null;
            task = service.getTaskFromBPel(taskId, pengguna);
            if (task != null) {
                stageId = task.getSystemAttributes().getStage();
            }
        } else {
            stageId = "sedia_borang7b";
        }

        LOG.info("stageId : " + stageId);



    }

    private void notifikasi(Permohonan permohonan, Integer totalUnpaid, BigDecimal totalAmount) {
        LOG.info("------------- NOTIFIKASI ---------------");
        ArrayList kumpulanBpel1 = new ArrayList<String>();
        if (permohonan.getCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
            kumpulanBpel1.add("puuptg"); // PUU
            kumpulanBpel1.add("ppuuptg"); //PPUU
        } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
            kumpulanBpel1.add("ppuuptd"); // PPUU
        }

        List<KodPeranan> listPerananKumpBpel = new ArrayList<KodPeranan>();
        listPerananKumpBpel = enforcementService.senaraiKumpBpel(kumpulanBpel1);
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Status Pembayaran Kompaun");
        n.setMesej(permohonan.getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah dihantar kepada Pegawai Undang-Undang untuk makluman. Terdapat " + totalUnpaid + " kompaun/denda yang masih belum dijelaskan berjumlah RM" + totalAmount + " dan tempoh pembayaran telah tamat");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        n.setCawangan(pengguna.getKodCawangan());
//        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
//        list.add(kodPerananDAO.findById("PUU"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, pengguna.getKodCawangan(), listPerananKumpBpel);
//            context.addMessage("Makluman kepada Pentadbir Tanah telah dihantar.");
    }

    public String skipOneStage() {
        LOG.info("------------- SKIP ONE STAGE ---------------");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("429")) {
                keputusan = "GB";
            } else {
                keputusan = "XY";// 'XY'-Tiada terima bayaran 
            }
            System.out.println("keputusan :" + keputusan);
        }
        try {
            taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
            nextStage = WorkFlowService.updateTaskOutcome(taskId, ctx, keputusan);
            System.out.println("next stage : " + nextStage);
        } catch (WorkflowException ex) {
            java.util.logging.Logger.getLogger(NotifikasiBayaranActionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StaleObjectException ex) {
            java.util.logging.Logger.getLogger(NotifikasiBayaranActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nextStage;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getNextStage() {
        return nextStage;
    }

    public void setNextStage(String nextStage) {
        this.nextStage = nextStage;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
