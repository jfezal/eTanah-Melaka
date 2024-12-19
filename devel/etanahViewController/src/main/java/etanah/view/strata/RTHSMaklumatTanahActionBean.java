/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.service.PembangunanService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

/**
 *
 * @author Sreenivasa Reddy Munagala
 */
@UrlBinding("/strata/RTHSMaklumat_tanah")
public class RTHSMaklumatTanahActionBean extends BasicTabActionBean {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idPermohonanTerdahulu;
    private static final Logger LOG = Logger.getLogger(MaklumatTanahActionBean.class);

    public String getIdPermohonanTerdahulu() {
        return idPermohonanTerdahulu;
    }

    public void setIdPermohonanTerdahulu(String idPermohonanTerdahulu) {
        this.idPermohonanTerdahulu = idPermohonanTerdahulu;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }


    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idPermohonanTerdahulu2=(String)getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
        idPermohonanTerdahulu = (String) getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
         
        stageId = "keputusanPTG";
        if (idPermohonanTerdahulu != null && !idPermohonanTerdahulu.trim().equals("")||(idPermohonanTerdahulu2!=null)) {
    
          permohonan = permohonanDAO.findById(idPermohonanTerdahulu);
          
         if(permohonan==null){
             
             addSimpleError(" Id Permohonan ini tiada di dalam pangkalan data.");
             getContext().getRequest().setAttribute("edit", Boolean.FALSE);
             return new JSP("strata/Lanjutan_Tempo_Mohon/tanah.jsp").addParameter("tab", "true");
         }
          
            FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(idPermohonanTerdahulu2, stageId);
             System.out.println("FASA PERMOHONAN"+fp);
            if(fp!=null){
                addSimpleError("IDPermohonan ini telah diproses");
                getContext().getRequest().setAttribute("edit", Boolean.FALSE);
             return new JSP("strata/Lanjutan_Tempo_Mohon/tanah.jsp").addParameter("tab", "true");
            }
            permohonan = permohonanDAO.findById(idPermohonanTerdahulu);
            idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            hakmilik = hakmilikDAO.findById(idHakmilik);

            return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumatTanah.jsp").addParameter("tab", "true");
            
        }else{
                    
             addSimpleError(" Sila masukkan ID Permohonan terdahulu.");
             getContext().getRequest().setAttribute("edit", Boolean.FALSE);
             return new JSP("strata/Lanjutan_Tempo_Mohon/tanah.jsp").addParameter("tab", "true");
         }

    }
}

