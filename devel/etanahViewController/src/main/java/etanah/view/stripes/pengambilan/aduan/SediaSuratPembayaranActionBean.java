/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.aduan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.view.stripes.pengambilan.share.form.PengambilanHakmilikForm;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author user
 */
@UrlBinding("/pengambilan/aduan_kerosakan/sedia_surat_pembayaran")
public class SediaSuratPembayaranActionBean extends AbleActionBean {

    @Inject
    AduanService aduanService;
    @Inject
    PermohonanDAO permohonanDAO;
    String idPermohonan;
    String urusan;
    List<PengambilanHakmilikForm> listHakmilikPermohonan;

    @DefaultHandler
    public Resolution showForm() {
        listHakmilikPermohonan = new ArrayList<PengambilanHakmilikForm>();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);
        for(HakmilikPermohonan hp: p.getSenaraiHakmilik()){
            PengambilanHakmilikForm e = new PengambilanHakmilikForm();
            e.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
            e.setJumPihak(Integer.parseInt(aduanService.findJumlahPihakTuntut(idPermohonan)+""));
            e.setJumlahTuntutan(new BigDecimal(aduanService.findJumlahTuntutan(idPermohonan)+""));
        listHakmilikPermohonan.add(e);
        }
        
        return new JSP("/pengambilan/aduan_kerosakan/sediaSuratPembayaran.jsp").addParameter("tab", "true");

    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<PengambilanHakmilikForm> getListHakmilikPermohonan() {
        return listHakmilikPermohonan;
    }

    public void setListHakmilikPermohonan(List<PengambilanHakmilikForm> listHakmilikPermohonan) {
        this.listHakmilikPermohonan = listHakmilikPermohonan;
    }

 
    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }
    
    

}// end class

