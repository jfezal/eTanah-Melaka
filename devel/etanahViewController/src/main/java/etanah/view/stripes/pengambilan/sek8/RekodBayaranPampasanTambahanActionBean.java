/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.service.ambil.PengambilanAPTService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pengambilan.share.form.BayaranPampasanTambahanForm;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/pampasanTambahan")
public class RekodBayaranPampasanTambahanActionBean extends BasicTabActionBean {
    
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    Permohonan permohonan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    BayaranPampasanTambahanForm form;
    BigDecimal pampasanTambahan;
    
    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            form = new BayaranPampasanTambahanForm();
            form = pengambilanAPTService.setValuePampasan(idPermohonan);
            
        }
        return new JSP("pengambilan/APT/rekodBayaranPampasanTambahan.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            PermohonanPengambilan mohonAmbil = pengambilanAPTService.findPermohonanPengambilanByIdMH(idPermohonan);
            mohonAmbil.setDepositTambahan(pampasanTambahan);
            pengambilanAPTService.saveOrUpdatePermohonanPengambilan(mohonAmbil);
        }
        addSimpleMessage("Maklumat berjaya disimpan");
        return new JSP("pengambilan/APT/rekodBayaranPampasanTambahan.jsp").addParameter("tab", "true");
    }
    
    public BayaranPampasanTambahanForm getForm() {
        return form;
    }
    
    public void setForm(BayaranPampasanTambahanForm form) {
        this.form = form;
    }
    
    public void setPampasanTambahan(BigDecimal pampasanTambahan) {
        this.pampasanTambahan = pampasanTambahan;
    }
    
}
