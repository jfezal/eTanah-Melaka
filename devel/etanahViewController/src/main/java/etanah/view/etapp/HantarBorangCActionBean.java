/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.KandunganFolder;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.ref.pengambilan.myetapp.RefPeringkat;
import etanah.service.acq.integrationflow.MyeTappIntegrationFlowService;
import etanah.service.ambil.MyEtappService;
import etanah.view.etanahActionBeanContext;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/etapp/hantar_borang_c")
public class HantarBorangCActionBean extends AbleActionBean {

    @Inject
    MyeTappIntegrationFlowService myeTappIntegrationFlowService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
            MyEtappService myEtappService;
    String idPermohonan;
    Permohonan permohonan;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();

    @DefaultHandler
    public Resolution showForm() {
      String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
      String kodDokumen[] = new String[3];
         kodDokumen[0] = "C";
            kodDokumen[1] = "D";
             kodDokumen[2] = "SIID";
      senaraiKandungan = myEtappService.senaraiDokumen(idPermohonan,kodDokumen);
        return new JSP("etapp/hantar_borang_c.jsp");
    }

    public Resolution simpan() {

        return new JSP("etapp/hantar_borang_c.jsp");
    }

    public Resolution selesai() throws IOException {
        RefPeringkat ref = new RefPeringkat();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
      String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        myeTappIntegrationFlowService.completeTask(ref.SEK8_HANTAR_BORANGC, permohonan, pengguna, permohonan.getCawangan().getDaerah());
        return new JSP("etapp/hantar_borang_c.jsp");
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

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

 
    
}
