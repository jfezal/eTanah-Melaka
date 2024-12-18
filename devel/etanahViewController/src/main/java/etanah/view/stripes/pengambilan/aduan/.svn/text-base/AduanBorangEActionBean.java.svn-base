/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.aduan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.service.pengambilan.aduan.AduanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author NURBAIZURA
 */


@UrlBinding("/pengambilan/aduan_borangE")
public class AduanBorangEActionBean extends AbleActionBean {

    @Inject
    AduanService aduanService;
    String report_p_id_mohon;

    @DefaultHandler
    public Resolution showForm() {
        
        System.out.println("inside show form xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx ");
        return new JSP("pengambilan/aduan_kerosakan/rekodKeputusanPerbincangan.jsp").addParameter("tab", "true");

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
    }

    public String getReport_p_id_mohon() {
        return report_p_id_mohon;
    }

    public void setReport_p_id_mohon(String report_p_id_mohon) {
        this.report_p_id_mohon = report_p_id_mohon;
    }
}

