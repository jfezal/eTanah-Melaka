/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.model.Permohonan;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.dao.PermohonanDAO;
import etanah.model.PermohonanUrusan;
import java.util.List;
import etanah.service.common.UrusniagaService;
import net.sourceforge.stripes.action.HandlesEvent;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.view.etanahActionBeanContext;
/**
 *
 * @author khairil
 */
@UrlBinding("/pendaftaran/maklumat_ismen")
public class KemasukanMaklumatIsmenActionBean extends AbleActionBean {
    
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    UrusniagaService uManager;
    private Permohonan permohonan;
    private PermohonanUrusan urusan;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/maklumat_kemasukan_ismen.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("idPermohonan :" + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            List<PermohonanUrusan> urs = uManager.findAll();
            for (PermohonanUrusan permohonanUrusan : urs) {
                System.out.println("urusan " + permohonanUrusan.getPermohonan().getIdPermohonan());
                if (permohonanUrusan.getPermohonan().equals(permohonan)) {
                    urusan = permohonanUrusan;
                    break;
                }
            }

        }
    }

    @HandlesEvent("save")
    public Resolution saveOrUpdate(){
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        urusan.setInfoAudit(info);
        urusan.setCawangan(peng.getKodCawangan());
        urusan.setPermohonan(permohonan);
        uManager.saveOrUpdate(urusan);

        addSimpleMessage("Kemasukan Data Berjaya");
        return new JSP("daftar/kemasukan_maklumat_urusniaga.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanUrusan getUrusan() {
        return urusan;
    }

    public void setUrusan(PermohonanUrusan urusan) {
        this.urusan = urusan;
    }


}
