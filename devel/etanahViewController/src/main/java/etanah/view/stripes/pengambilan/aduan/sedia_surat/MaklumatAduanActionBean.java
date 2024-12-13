/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.aduan.sedia_surat;

import etanah.view.stripes.pengambilan.aduan.semakan.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanAduanDAO;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAduan;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author user
 */
@UrlBinding("/pengambilan/aduan_kerosakan/sedia_surat/maklumat_aduan")
public class MaklumatAduanActionBean extends AbleActionBean {

    
    private Permohonan permohonan;
    private String idPermohonan;
    private PermohonanAduan pemohonAduan;
    private String aduan;
    
    @Inject
    PermohonanAduanDAO permohonanAduanDAO;
    @Inject
    AduanService aduanService;
    String report_p_id_mohon;

    @DefaultHandler
    public Resolution showForm() {
        
        System.out.println("inside show form MaklumatAduanActionBean ++ ");
        
        
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
    
        
        pemohonAduan = aduanService.findAduanByIdMohon(idPermohonan);
        
        setAduan(pemohonAduan.getPerihal());
        
        System.out.println("perihal Aduan ++> " + getAduan() );
        
        return new JSP("pengambilan/aduan_kerosakan/sedia_surat/maklumat_aduan.jsp").addParameter("tab", "true");

    }
    
    public Resolution maklumatPengadu() {

        //return new JSP("pengambilan/aduan_kerosakan/kemasukan.jsp").addParameter("tab", "true");
        return new JSP("pengambilan/aduan_kerosakan/agihanTugas.jsp").addParameter("tab", "true");

    }
    public Resolution agihTugas() {

        //return new JSP("pengambilan/aduan_kerosakan/kemasukan.jsp").addParameter("tab", "true");
        return new JSP("pengambilan/aduan_kerosakan/agihanTugas.jsp").addParameter("tab", "true");

    }

    public Resolution view() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        Permohonan mohon = aduanService.findfromdb(idPermohonan);
        report_p_id_mohon = mohon.getIdPermohonan();
        return new JSP("pengambilan/aduan_kerosakan/kemasukan.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("inside rehydrate form MaklumatAduanActionBean ++ ");
    }
    
    
    public void simpan() {
    }// end methosd

    public String getReport_p_id_mohon() {
        return report_p_id_mohon;
    }

    public void setReport_p_id_mohon(String report_p_id_mohon) {
        this.report_p_id_mohon = report_p_id_mohon;
    }

    public String getAduan() {
        return aduan;
    }

    public void setAduan(String aduan) {
        this.aduan = aduan;
    }
    
    
    
    
}
