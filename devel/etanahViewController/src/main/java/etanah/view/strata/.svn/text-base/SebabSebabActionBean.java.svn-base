/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PembangunanService;
import etanah.service.common.PermohonanService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Sreenivasa Reddy Munagala
 */
@UrlBinding("/strata/sebab_pindaan")
public class SebabSebabActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanService mohonSericevvice;
    private Permohonan permohonan;
    @Inject
    PembangunanService devService;
    private String sebab;
    private String idPermohonan;

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        sebab = permohonan.getSebab();
    }

    @DefaultHandler
    public Resolution showForm() {

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pinda_permohanan/sebab_pindaan.jsp").addParameter("tab", "true");
    }

    public Resolution readOnly() {

        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("strata/pinda_permohanan/sebab_pindaan.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        getContext().getRequest().getSession().removeAttribute("catatan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        permohonan = permohonanDAO.findById(idPermohonan);

        permohonan.setSebab(sebab);
        permohonan.setInfoAudit(infoAudit);

        boolean flag = true;

        if (sebab == null) {
            flag = false;
            addSimpleError(" Sila masukkan sebab-sebab Pindaan.");
        }
        if (flag) {
            addSimpleMessage("Maklumat Permohonan berjaya disimpan.");
        }

        if (sebab != null) {
            getContext().getRequest().getSession().setAttribute("catatan", sebab);
            permohonan = mohonSericevvice.saveOrUpdate(permohonan);

            getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
            //getContext().getRequest().getSession().getAttribute("catatan");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new JSP("strata/pinda_permohanan/sebab_pindaan.jsp").addParameter("tab", "true");
        } else {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new JSP("strata/pinda_permohanan/sebab_pindaan.jsp").addParameter("tab", "true");
        }
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }
    
    
}
