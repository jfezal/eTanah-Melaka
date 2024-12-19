
package etanah.view.stripes.pelupusan ;

import etanah.model.HakmilikPermohonan ;
import etanah.model.Permohonan ;
import etanah.dao.HakmilikPermohonanDAO ;
import etanah.dao.PermohonanDAO ;
import able.stripes.AbleActionBean ;
import able.stripes.JSP ;
import com.google.inject.Inject ;
import etanah.dao.HakmilikDAO;
import etanah.model.Hakmilik;
import etanah.service.HakmilikService;
import etanah.service.PelupusanService;
import java.util.List;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author afham
 */

@UrlBinding("/pelupusan/surat_hakmilik_MCL_kekal")
public class PenyediaanSuratHakMilikKekalMCL_ActionBean extends AbleActionBean {
    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    @Inject
    HakmilikDAO hakmilikDAO ;
     @Inject
    HakmilikService hakmilikService;
     @Inject
    PelupusanService plpservice ;
    private Permohonan permohonan ;
    private HakmilikPermohonan hakmilikPermohonan ;
    private Hakmilik hakmilik ;



    @DefaultHandler
    public Resolution showForm(){
        return new JSP ("pelupusan/surat_hakmilik_kekal_MCL.jsp").addParameter("tab", "true") ;
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;

        String[] tname = {"permohonan"} ;
        Object[] tvalue = {permohonan};

        List senaraiHakMilik = hakmilikPermohonanDAO.findByEqualCriterias(tname, tvalue, null) ;
        hakmilikPermohonan = (HakmilikPermohonan) senaraiHakMilik.get(0) ;
        hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik()) ;


    }

    public Resolution simpanHakmilik() {
//            hakmilikPermohonan.setCatatan(null)
        System.out.print("HAKMILIKPERMOHONAN: "+hakmilikPermohonan.getId());
              plpservice.simpanHakmilikPermohonan(hakmilikPermohonan) ;
//            hakmilikService.saveHakmilik(hmp.getHakmilik());

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/surat_hakmilik_kekal_MCL.jsp").addParameter("tab", "true");
    }


    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
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




}
