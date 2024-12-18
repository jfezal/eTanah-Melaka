/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanUrusan;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/maklumat_rayuan")
public class MaklumatRayuanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    ConsentPtdService conService;
    private Permohonan permohonan;
    private PermohonanUrusan permohonanUrusan;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_rayuan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("consent/maklumat_rayuan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanUrusan = conService.findMohonUrusanByPerihal(idPermohonan, "RAYUAN");
        }
    }

    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            PermohonanUrusan permohonanUrusanTemp = conService.findMohonUrusanByPerihal(idPermohonan, "RAYUAN");;

            if (permohonanUrusanTemp == null) {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanUrusanTemp = new PermohonanUrusan();

            } else {
                infoAudit = permohonanUrusanTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }

            permohonanUrusanTemp.setCawangan(permohonan.getCawangan());
            permohonanUrusanTemp.setPermohonan(permohonan);
            permohonanUrusanTemp.setSebab(permohonanUrusan.getSebab());
            permohonanUrusanTemp.setInfoAudit(infoAudit);
            permohonanUrusanTemp.setPerihal("RAYUAN");
            conService.simpanPermohonanUrusan(permohonanUrusanTemp);

        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_rayuan.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanUrusan getPermohonanUrusan() {
        return permohonanUrusan;
    }

    public void setPermohonanUrusan(PermohonanUrusan permohonanUrusan) {
        this.permohonanUrusan = permohonanUrusan;
    }
}
