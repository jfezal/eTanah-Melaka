/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author nordiyana
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

@UrlBinding("/pengambilan/maklumat_permohonan")
public class MaklumatPermohonanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    ConsentPtdService conService;
    private Permohonan permohonan;
    private String idHakmilik;

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("common/paparan_maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("daftar/paparan_dokumen_pendaftar.jsp").addParameter("tab", "true");
    }
    
     public Resolution showForm4() {
        return new JSP("pengambilan/menentukan_tarikh_mesyuarat_dan_bicara.jsp").addParameter("tab", "true");
    }

    

    public Resolution paparanMaklumatPermohonanDaftar() {
        if(permohonan != null){
            idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        }
        return new JSP("daftar/paparan_maklumat_permohonan_daftar.jsp").addParameter("tab", "true");
    }

    public Resolution paparanSenaraiPerserahan(){
        return new JSP("daftar/common/senarai_perserahan.jsp").addParameter("tab", "true");
    }

    public Resolution paparanSurat(){
        return new JSP("daftar/common/paparan_surat.jsp").addParameter("tab","true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getPermohonanSebelum() != null) {
                if (StringUtils.isNotBlank(permohonan.getPermohonanSebelum().getIdPermohonan())) {
                    permohonan = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                }
            }
        }
    }

    public Resolution simpanPermohonan() {
        conService.simpanPermohonan(permohonan);
        return new JSP("common/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

}
