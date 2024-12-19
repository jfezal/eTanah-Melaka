/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.ref.pengambilan.sek4.RefPeringkat;
import etanah.service.AgensiService;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pembangunan.tugasan.TugasanActionBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/pengambilan/terima_pelan_pa")
public class TerimaPelanPAActionBean extends AbleActionBean {

    @Inject
    Sek8aIntegrationFlowService sek8aIntegrationFlowService;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    AgensiService agensiService;
    @Inject
    KodRujukanDAO kodRujukanDAO;

    @Inject
    PermohonanDAO permohonanDAO;
    Permohonan permohonan;
    String idPermohonan;
    String noFail;
    Date tarikhTerima;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        //idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        PermohonanRujukanLuar mohonruj = agensiService.findPermohonanRujByIdPermohonanKodRuj(permohonan.getIdPermohonan());
        if (mohonruj != null) {
            noFail = mohonruj.getNoRujukan();
            tarikhTerima = mohonruj.getTarikhTerima();
        }

        return new JSP("/pengambilan/APT/terima_pelan_pa.jsp");
    }

    public Resolution save() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        simpanData(pengguna, permohonan);
        return new JSP("/pengambilan/APT/terima_pelan_pa.jsp");
    }

    public Resolution hantar() {
        //idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        //simpanData(pengguna, permohonan);

        RefPeringkat ref = new RefPeringkat();
        etanah.ref.pengambilan.sek8a.RefPeringkat ref8a = new etanah.ref.pengambilan.sek8a.RefPeringkat();

        sek8aIntegrationFlowService.completeTask(ref8a.AGIH_PA, permohonan, pengguna);

        return new RedirectResolution(TugasanActionBean.class);
    }

    public void simpanData(Pengguna pengguna, Permohonan p) {
        InfoAudit infoAudit = new InfoAudit();

        PermohonanRujukanLuar mohonruj = agensiService.findPermohonanRujByIdPermohonanKodRuj(p.getIdPermohonan());
        if (mohonruj == null) {
            mohonruj = new PermohonanRujukanLuar();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new Date());
        } else {
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new Date());
        }
        mohonruj.setPermohonan(permohonan);
        mohonruj.setInfoAudit(infoAudit);
        mohonruj.setNoRujukan(noFail);
        mohonruj.setKodRujukan(kodRujukanDAO.findById("FL"));
        mohonruj.setTarikhTerima(tarikhTerima);

        agensiService.simpanpermohonanRujukanLuar(mohonruj);
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public Date getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(Date tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

}
