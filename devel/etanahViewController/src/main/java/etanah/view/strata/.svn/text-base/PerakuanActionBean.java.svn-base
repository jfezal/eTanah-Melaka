/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Murali 13-07-2011
 */
@UrlBinding("/strata/kuatkuasa_perakuan")
public class PerakuanActionBean extends BasicTabActionBean {

    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    StrataPtService ptService;
    @Inject
    PermohonanDAO permohonanDAO;
    private FasaPermohonan fasaPermohonan;
    private Permohonan permohonan;
    private String ulasan;

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/kuatkuasa/perakuan_kertas_siasatan.jsp").addParameter("tab", "true");
    }

    public Resolution viewPerakuan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("strata/kuatkuasa/perakuan_kertas_siasatan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        fasaPermohonan = ptService.findMohonFasa(idPermohonan, "perakuan");
        System.out.println("--------fasaPermohonan Rehydrate()---------" + fasaPermohonan);
        if (fasaPermohonan != null) {
            ulasan = fasaPermohonan.getUlasan();
        }
    }

    public Resolution simpanPerakuan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        System.out.println("--------Saving in fasaPermohonan---------");
        stageId = "perakuan";
        fasaPermohonan = ptService.findMohonFasa(idPermohonan, stageId);

        if (fasaPermohonan != null) {
            System.out.println("--------fasaPermohonan not null---------::");
            infoAudit = fasaPermohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            System.out.println("--------fasaPermohonan is null---------::");
            fasaPermohonan = new FasaPermohonan();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
        fasaPermohonan.setInfoAudit(infoAudit);
        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setCawangan(permohonan.getCawangan());
        fasaPermohonan.setIdPengguna(pguna.getIdPengguna());
        fasaPermohonan.setUlasan(ulasan);
        fasaPermohonan.setIdAliran(stageId);
        ptService.saveFasaMohon(fasaPermohonan);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(PerakuanActionBean.class, "showForm");
//        return new JSP("strata/kuatkuasa/perakuan_kertas_siasatan.jsp").addParameter("tab", "true");
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public StrataPtService getPtService() {
        return ptService;
    }

    public void setPtService(StrataPtService ptService) {
        this.ptService = ptService;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
}