/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author primainfo
 */
@UrlBinding("/pelupusan/notaBaru")
public class NotaBaruActionBean extends AbleActionBean {
@Inject
protected com.google.inject.Provider<Session> sessionProvider;
@Inject
PermohonanDAO permohonanDAO;
@Inject
PermohonanNotaDAO permohonanNotaDAO;
@Inject
EnforceService enforceService;
@Inject
PengambilanService pengambilanService;
private Permohonan permohonan;
private PermohonanNota permohonanNota;
private String idPermohonan;
private long idMohonNota;
private String nota;
private String stageId;
private List<PermohonanNota> listNota;
private static final Logger log = Logger.getLogger(NotaBaruActionBean.class);

@DefaultHandler
public Resolution showForm(){

    return new JSP ("/pelupusan/pbgsa/nota_baru.jsp").addParameter("tab","true");
}

@Before (stages = {LifecycleStage.BindingAndValidation})
public void rehydrate(){
    Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

     BPelService service = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

            if (StringUtils.isNotBlank(taskId)) {
                Task task = null;
                task = service.getTaskFromBPel(taskId, peng);
                if (task != null) {
                    stageId = task.getSystemAttributes().getStage();
                }
            }
    permohonan = permohonanDAO.findById(idPermohonan);
    permohonanNota = enforceService.findNotaByIdMohon(idPermohonan, stageId);
    System.out.println("--permohonanNota--" + permohonanNota);

    if (permohonanNota != null) {
        idMohonNota = permohonanNota.getIdMohonNota();
        nota = permohonanNota.getNota();
    }

    listNota = pengambilanService.findListNotaByIdMohon(idPermohonan, stageId);
    System.out.println("--listNota--" + listNota);
}

public Resolution simpan(){

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanNota = enforceService.findNotaByIdMohon(idPermohonan, stageId);
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("--stageId--" + stageId);
        }

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());

        if (permohonanNota == null) {
            permohonanNota = new PermohonanNota();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = permohonanNota.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        permohonanNota.setPermohonan(permohonan);
        permohonanNota.setInfoAudit(ia);
        permohonanNota.setCawangan(peng.getKodCawangan());
        permohonanNota.setIdMohonNota(idMohonNota);
        permohonanNota.setNota(nota);
        permohonanNota.setIdAliran(stageId);


        enforceService.simpanNota(permohonanNota);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();

    return new JSP ("/pelupusan/pbgsa/nota_baru.jsp").addParameter("tab","true");
}

 public Resolution delete() {
        log.info("hapus Nota");
        idMohonNota = Long.parseLong(getContext().getRequest().getParameter("idMohonNota"));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            PermohonanNota p = permohonanNotaDAO.findById(idMohonNota);
            permohonanNotaDAO.delete(p);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(NotaBaruActionBean.class, "locate");
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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public PermohonanNota getPermohonanNota() {
        return permohonanNota;
    }

    public void setPermohonanNota(PermohonanNota permohonanNota) {
        this.permohonanNota = permohonanNota;
    }

    public List<PermohonanNota> getListNota() {
        return listNota;
    }

    public void setListNota(List<PermohonanNota> listNota) {
        this.listNota = listNota;
    }

    public long getIdMohonNota() {
        return idMohonNota;
    }

    public void setIdMohonNota(long idMohonNota) {
        this.idMohonNota = idMohonNota;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }




}
