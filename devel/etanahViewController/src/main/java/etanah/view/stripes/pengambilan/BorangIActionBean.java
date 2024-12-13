/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;


import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.model.KodCawangan;
import etanah.model.Permohonan;
import etanah.model.Notis;
import etanah.model.Dokumen;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.KodNotis;
import etanah.model.HakmilikPermohonan;
import etanah.service.PengambilanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.service.common.PerbicaraanService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
/**
 *
 * @author nordiyana
 */
@UrlBinding("pengambilan/borangI")
public class BorangIActionBean extends AbleActionBean {
      @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    Permohonan permohonan;
    @Inject
    PengambilanService service;
    String statusPermohonan;
    private KodCawangan cawangan;

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pengambilan/BorangI.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2(){
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/PerintahBersih.jsp").addParameter("tab", "true");
    }
    public Resolution showForm3(){
         getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/DerafPerintah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4(){
         getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/PerintahBersih.jsp").addParameter("tab", "true");
    }

@Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getSebab() != null) {
                        statusPermohonan = permohonan.getKeputusan().getKod();
            }


    }
    }


     public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p= permohonanDAO.findById(idPermohonan);

        if(p!=null){
          if (statusPermohonan == null)
        {
                addSimpleError("Sila Masukkan Keputusan.");


    }else {
            permohonan.setSebab(statusPermohonan);
            permohonan.setKeputusanOleh(peng);
            cawangan = permohonan.getCawangan();
            service.simpanPermohonan(permohonan);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
    }


        }

        rehydrate();

//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/BorangJ.jsp").addParameter("tab", "true");
    }



    public String getStatusPermohonan() {
        return statusPermohonan;
    }

    public void setStatusPermohonan(String statusPermohonan) {
        this.statusPermohonan = statusPermohonan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }





}




