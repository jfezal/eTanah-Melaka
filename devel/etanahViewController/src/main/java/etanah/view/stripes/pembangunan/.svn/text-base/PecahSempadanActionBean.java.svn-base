/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import etanah.model.Permohonan;
import etanah.model.FasaPermohonan;
import etanah.model.Pengguna;
import etanah.dao.PermohonanDAO;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/kelulusan_pecahSempadan")
public class PecahSempadanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    
    private Permohonan permohonan;
    private FasaPermohonan fasaMohon;
    private String keputusan;
    private Pengguna pengguna;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/pecahSempadan/dev_kelulusan_pecah_sempadan.jsp").addParameter("tab", "true");
    }
   
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

         if (!(permohonan.getSenaraiFasa().isEmpty())) {

            for (int i = 0; i < permohonan.getSenaraiFasa().size(); i++) {

                fasaMohon = new FasaPermohonan();
                fasaMohon = permohonan.getSenaraiFasa().get(i);

                if(fasaMohon.getIdAliran().equals("semakcetakkeputusanmmk")){
                    keputusan = fasaMohon.getKeputusan().getNama();
                }
            }
         }

    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }


}
