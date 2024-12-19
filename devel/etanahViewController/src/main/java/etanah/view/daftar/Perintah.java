/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.daftar;

import able.stripes.JSP;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.Permohonan;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.nota.pembetulanActionBean;

import java.util.List;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author khairil
 */
@UrlBinding("/daftar/perintah")
public class Perintah extends AbleActionBean {
    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;

    private List<HakmilikPermohonan> senaraiHakmilik;
    private final static Logger LOG = Logger.getLogger(Perintah.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    @DefaultHandler 
    public Resolution showForm() {
        return new JSP("daftar/kemasukan_perintah.jsp").addParameter("tab", "true");
    }

    @HandlesEvent("perintahPembetulan")
    public Resolution showFormPembetulan() {
        if (permohonan != null) {
            senaraiHakmilik = permohonan.getSenaraiHakmilik();
        }        
        return new JSP("daftar/pembetulan/perintah_pembetulan.jsp").addParameter("tab", "true");
    }

    public Resolution getPartialPage() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        String pilihan = ctx.getRequest().getParameter("urusan_pilihan");
        if (isDebug) LOG.debug("pilihan =" + pilihan);
        if (StringUtils.isNotBlank(pilihan)) {
            if (pilihan.equals("1"))
                return new RedirectResolution(pembetulanActionBean.class, "asasHakmilik");
            else if (pilihan.equals("2"))
                return new RedirectResolution(pembetulanActionBean.class, "senaraiPemilik");
            else if (pilihan.equals("3"))
                return new RedirectResolution(pembetulanActionBean.class, "maklumatSilap");
        }
        return new JSP("daftar/pembetulan/perintah_pembetulan_partial.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
//        permohonan = ((etanahActionBeanContext) getContext()).getPermohonan();
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if (permohonan.getSenaraiRujukanLuar().size() > 0) {
                permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0); //TODO : multiple rujukan luar?
                
            }
        }
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    

}
