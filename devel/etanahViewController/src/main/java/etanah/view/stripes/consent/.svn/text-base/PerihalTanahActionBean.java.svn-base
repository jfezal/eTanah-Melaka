/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/perihal_tanah")
public class PerihalTanahActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    ConsentPtdService consentService;
    private List<HakmilikPermohonan> senaraiHakmilik;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    private HakmilikPermohonan hakmilikPermohonan;
    private static final Logger LOG = Logger.getLogger(PerihalTanahActionBean.class);
    etanahActionBeanContext ctx;
    private String idHakmilik;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/perihal_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("consent/perihal_tanah.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpanLaporanTanah"})
    public void rehydrate() {

        ctx = (etanahActionBeanContext) getContext();

        String idPermohonan = (String) ctx.getRequest().getSession().getAttribute("idPermohonan");

        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        LOG.info("hakmilik :" + idHakmilik);
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiHakmilik = permohonan.getSenaraiHakmilik();

            if (StringUtils.isNotBlank(idHakmilik)) {
                hakmilikPermohonan = consentService.findMohonHakmilikByIdH(idPermohonan, idHakmilik);
                laporanTanah = consentService.findLaporanTanahByIdMH(idPermohonan, String.valueOf(hakmilikPermohonan.getId()));
            } else {
                hakmilikPermohonan = consentService.findMohonHakmilikByIdH(idPermohonan, senaraiHakmilik.get(0).getHakmilik().getIdHakmilik());
                laporanTanah = consentService.findLaporanTanahByIdMH(idPermohonan, String.valueOf(senaraiHakmilik.get(0).getId()));
            }
        }
    }

    public Resolution selectHakmilikEdit() {
        ctx.getRequest().setAttribute("edit", Boolean.TRUE);
        return showForm();

    }

    public Resolution selectHakmilik() {
        return showForm2();

    }

    public Resolution simpan() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        if (laporanTanah != null) {
            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setHakmilikPermohonan(hakmilikPermohonan);
            laporanTanah.setInfoAudit(infoAudit);

        } else {
            laporanTanah = new LaporanTanah();
            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setHakmilikPermohonan(hakmilikPermohonan);
            laporanTanah.setInfoAudit(infoAudit);

        }

        consentService.simpanLaporanTanah(laporanTanah);
        consentService.simpanHakmilikPermohonan(hakmilikPermohonan);

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/consent/perihal_tanah.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }
}
