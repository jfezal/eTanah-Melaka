/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.PermohonanService;
import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author User
 */
@UrlBinding("/pelupusan/butir_butir_cadangan")
public class ButirbutirCadanganActionBean extends AbleActionBean {

    private Permohonan permohonan;
    @Inject
    PermohonanService permohonanService;
    private InfoAudit ia;
    private Pengguna peng;
    private String idPermohonan;
    private String sebab;
    private String catatan;
    private String nilaiKeputusan;
    private String ulasan;
    private boolean display;
    
    @DefaultHandler
    public Resolution showForm() {
        display = Boolean.FALSE;
        return new JSP("pelupusan/butir_butir_cadangan.jsp").addParameter("tab", "true");
    }
    public Resolution viewForm() {
        display = Boolean.TRUE;
        return new JSP("pelupusan/butir_butir_cadangan.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanService.findById(idPermohonan);
            
            sebab = !StringUtils.isBlank(permohonan.getSebab())?permohonan.getSebab():"";
            catatan = !StringUtils.isBlank(permohonan.getCatatan())?permohonan.getCatatan():"";
            nilaiKeputusan = permohonan.getNilaiKeputusan()!=null?permohonan.getNilaiKeputusan().toString():"";
            ulasan = !StringUtils.isBlank(permohonan.getUlasan())?permohonan.getUlasan():"";
            
     }

    public Resolution simpanButirButir() {

             ia = new InfoAudit();
             peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
             ia.setDimasukOleh(peng);
             ia.setTarikhMasuk(new java.util.Date());
             
             String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
             permohonan = permohonanService.findById(idPermohonan);
             permohonan.setSebab(sebab);
             permohonan.setCatatan(catatan);
             permohonan.setNilaiKeputusan(new BigDecimal(nilaiKeputusan));
             permohonan.setUlasan(ulasan);
             permohonan.setInfoAudit(ia);
             permohonanService.saveOrUpdate(permohonan);
             addSimpleMessage("Maklumat telah berjaya disimpan");
             return new JSP("pelupusan/butir_butir_cadangan.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNilaiKeputusan() {
        return nilaiKeputusan;
    }

    public void setNilaiKeputusan(String nilaiKeputusan) {
        this.nilaiKeputusan = nilaiKeputusan;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    

}
