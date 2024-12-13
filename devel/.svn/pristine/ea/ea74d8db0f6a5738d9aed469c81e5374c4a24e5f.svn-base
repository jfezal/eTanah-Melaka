/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.NotifikasiBayaranService;
import etanah.service.NotifikasiService;
import etanah.view.strata.CommonService;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/utility/notifikasibayaran")
public class NotifikasiBayaranActionBean extends AbleActionBean {

    @Inject
    CommonService comm;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    NotifikasiBayaranService notifikasiBayaranService;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanTuntutanDAO permohonanTuntutanDAO;
    private List<PermohonanTuntutan> listMohonTuntut = new ArrayList<PermohonanTuntutan>();
    private List<PermohonanTuntutanKos> listMohonTuntutKos = new ArrayList<PermohonanTuntutanKos>();
    private List<PermohonanTuntutanBayar> listMohonTuntutBayar = new ArrayList<PermohonanTuntutanBayar>();
    private List<PermohonanTuntutanButiran> listMohonTuntutButir = new ArrayList<PermohonanTuntutanButiran>();
    private Date date = new Date();
    private static final Logger LOG = Logger.getLogger(NotifikasiBayaranActionBean.class);

    @DefaultHandler
    public Resolution checkBayaran() {
        boolean sendMsg = false;
        listMohonTuntut = notifikasiBayaranService.findMohonTuntutbyDate(date);

        for (PermohonanTuntutan pt : listMohonTuntut) {
            LOG.info("-----------"+pt.getPermohonan().getIdPermohonan());
            listMohonTuntutKos.clear();
            listMohonTuntutBayar.clear();
            listMohonTuntutButir = notifikasiBayaranService.findMohonTuntutButir(pt.getIdTuntut());
            BigDecimal jumBayar = BigDecimal.ZERO;
            BigDecimal jumTuntut = BigDecimal.ZERO;
            for (PermohonanTuntutanButiran ptb : listMohonTuntutButir) {
                PermohonanTuntutanBayar mohonTuntutBayar = new PermohonanTuntutanBayar();
                mohonTuntutBayar = notifikasiBayaranService.findMohonTuntutBayar(ptb.getPermohonanTuntutanKos().getIdKos());
                if (mohonTuntutBayar != null) {
                    jumBayar = jumBayar.add(mohonTuntutBayar.getAmaun());
                }
                jumTuntut = jumTuntut.add(ptb.getPermohonanTuntutanKos().getAmaunTuntutan());
                if (ptb.getPermohonanTuntutanKos().getKodTuntut() != null) {
                    if (ptb.getPermohonanTuntutanKos().getKodTuntut().getKod().equals("DISB4A")) {
                        LOG.info("IF DISB4A THEN  send message ");
                        sendMsg = true;
                    }
                }
            }
            BigDecimal amaun = jumTuntut.subtract(jumBayar);
            if (amaun.compareTo(BigDecimal.ZERO) == 0 || amaun.compareTo(BigDecimal.ZERO) != -1) {
                LOG.info("IF amaun.compareTo(BigDecimal.ZERO) == 0 OR amaun.compareTo(BigDecimal.ZERO) != -1 ");
                if (sendMsg) {
                    LOG.info("IF sendMsg is true");
                    notifikasi(pt.getPermohonan(), pt);
                    withdrawTask(pt.getPermohonan().getIdPermohonan());
                    
                    /* for penguatkuasaan strata */
                    if (pt.getKodTransaksiTuntut().getKod().equals("STR001")) {
                        LOG.info("IF pt.getKodTransaksiTuntut().getKod().equals(STR001)");
                        KodUrusan kodUrusan = new KodUrusan();
                        if (pt.getPermohonan().getKodUrusan().getKod().equals("PBBS")) {
                            kodUrusan = kodUrusanDAO.findById("P14A");
                        } else {
                            kodUrusan = kodUrusanDAO.findById("P22B");
                        }
                        Permohonan permohonanBaru = initiateNewTask(pt.getPermohonan(), kodUrusan);
                        notifikasiBayaranService.strataPenguatkuasaan(permohonanBaru, pt.getPermohonan());
                    }
                } else {
                    LOG.info("ELSE IF sendMsg is false");
                    withdrawTask(pt.getPermohonan().getIdPermohonan());
                    if (pt.getKodTransaksiTuntut().getKod().equals("STR001")) {
                        LOG.info("IF pt.getKodTransaksiTuntut().getKod().equals(STR001)");
                        KodUrusan kodUrusan = new KodUrusan();
                        if (pt.getPermohonan().getKodUrusan().getKod().equals("PBBS")) {
                            kodUrusan = kodUrusanDAO.findById("P14A");
                        } else {
                            kodUrusan = kodUrusanDAO.findById("P22B");
                        }
                        Permohonan permohonanBaru = initiateNewTask(pt.getPermohonan(), kodUrusan);
                        notifikasiBayaranService.strataPenguatkuasaan(permohonanBaru, pt.getPermohonan());
                    }
                }
            }
        }
        return null;
    }

    private void notifikasi(Permohonan permohonan, PermohonanTuntutan pt) {
        LOG.info("------------- NOTIFIKASI ---------------");
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Kemasukan Permohonan Pemberimilikan Tanah Kerajaan (Seksyen 76 KTN)");
        n.setMesej(permohonan.getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman");
        Pengguna p = new Pengguna();
        p = penggunaDAO.findById("pptkanan1");
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("39"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
//            context.addMessage("Makluman kepada Pentadbir Tanah telah dihantar.");
    }

    public void withdrawTask(String idPermohonan) {
         LOG.info("------------- WITHDRAW TASK ---------------");
        try {
            List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
            if (senaraiTask.isEmpty()) {
                addSimpleError("Permohonan tidak di jumpai!");
            } else {
                Task task = (Task) senaraiTask.get(0);
                if (task != null) {
                    String taskId = task.getSystemAttributes().getTaskId();
                    WorkFlowService.withdrawTask(taskId);
                }
            }
        } catch (StaleObjectException ex) {
            java.util.logging.Logger.getLogger(NotifikasiBayaranActionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WorkflowException ex) {
            java.util.logging.Logger.getLogger(NotifikasiBayaranActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Permohonan initiateNewTask(Permohonan permohonan, KodUrusan ku) {
         LOG.info("------------- INITIATE NEW TASK ---------------");
        Permohonan permohonanBaru = null;
        if (true) {
            try {
                Pengguna peng = new Pengguna();
                peng = penggunaDAO.findById("ptstrata1");
                permohonanBaru = comm.createPermohonanBaru(permohonan, ku, peng);


            } catch (WorkflowException ex) {
                java.util.logging.Logger.getLogger(NotifikasiBayaranActionBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (StaleObjectException ex) {
                java.util.logging.Logger.getLogger(NotifikasiBayaranActionBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(NotifikasiBayaranActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return permohonanBaru;
    }
}
