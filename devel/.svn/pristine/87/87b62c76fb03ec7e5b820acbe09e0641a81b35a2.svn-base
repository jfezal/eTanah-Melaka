/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.PengambilanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/bayaran_pampasan_mahkamah")
public class BayaranPampasanMahkamahActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;

    @Inject
    PengambilanService pengambilanService;

    private static final Logger LOG = Logger.getLogger(BayaranPampasanMahkamahActionBean.class);
    private String idPermohonan;
    private String idHakmilik;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/Bayaran_Pampasan_Mahkamah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
    }

    public Resolution hakmilikDetails() {

        if (idPermohonan != null && idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilikPermohonan= pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            if(hakmilikPermohonan != null){
                hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                if(hakmilikPerbicaraan != null) {
                    senaraiPerbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranByKetarangan(hakmilikPerbicaraan.getIdPerbicaraan(), "Mahkamah");
                }
            }
        }

        return new JSP("pengambilan/Bayaran_Pampasan_Mahkamah.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }



}
