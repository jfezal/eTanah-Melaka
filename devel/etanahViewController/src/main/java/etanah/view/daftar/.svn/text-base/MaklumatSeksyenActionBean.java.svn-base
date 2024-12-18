/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/maklumat_seksyen")
public class MaklumatSeksyenActionBean extends AbleActionBean{
    
    @Inject
    PermohonanService permohonanService;
    private static String idPermohonan;
    private static Pengguna pengguna;
    private Permohonan permohonan;
    private static String SEKSYEN_PH30A = "415(1)(a) KTN";
    private static String SEKSYEN_PH30B = "415(3) KTN";

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("daftar/kemasukan_seksyen_ktn.jsp").addParameter("tab", "true");
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if(StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanService.findById(idPermohonan);
            if(permohonan != null && StringUtils.isBlank(permohonan.getRujukanUndang2())) {
                if(permohonan.getKodUrusan().getKod().equals("PH30A")) {
                    permohonan.setRujukanUndang2(SEKSYEN_PH30A);
                } else if(permohonan.getKodUrusan().getKod().equals("PH30B")) {
                    permohonan.setRujukanUndang2(SEKSYEN_PH30B);
                }
            }
        }
    }

    public Resolution save(){

        InfoAudit ia = new InfoAudit();
        if(permohonan != null) ia = permohonan.getInfoAudit();
        ia.setTarikhKemaskini(new Date());
        ia.setDiKemaskiniOleh(pengguna);
        permohonan.setInfoAudit(ia);
        try{
            permohonanService.saveOrUpdate(permohonan);
        } catch (Exception ex) {
            addSimpleError("Kemaskini Data Gagal");
            return new JSP("daftar/kemasukan_seksyen_ktn.jsp").addParameter("tab", "true");
        }
        
        addSimpleMessage("Kemaskini Data Berjaya");
        return new JSP("daftar/kemasukan_seksyen_ktn.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

}
