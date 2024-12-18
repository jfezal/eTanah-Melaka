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
import etanah.model.HakmilikPermohonan;
import etanah.model.KandunganFolder;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.ref.pengambilan.myetapp.RefPeringkat;
import etanah.service.acq.integrationflow.MyeTappIntegrationFlowService;
import etanah.service.acq.service.MyEtappMapDataService;
import etanah.service.acq.service.MyEtappPendaftaranService;
import etanah.service.ambil.MyEtappService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/etapp/hantar_borang_k")
public class HantarBorangKActionBean extends BasicTabActionBean {

    @Inject
    MyeTappIntegrationFlowService myeTappIntegrationFlowService;
    @Inject
    MyEtappMapDataService myEtappMapDataService;
    @Inject
    MyEtappPendaftaranService myEtappPendaftaranService;
    @Inject
    MyEtappService myEtappService;
    @Inject
    PermohonanDAO permohonanDAO;
    
    String idPermohonan;
    Permohonan permohonan;
    List<HakmilikEndorsanForm> listHakmilikEndos;
    List<HakmilikPermohonan> listHakmilikPermohonan;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] u = new String[2];
        u[0] = "ABTKB";
        u[1] = "ABT-K";
        listHakmilikEndos = myEtappMapDataService.findListHakmilikEndors(idPermohonan, u);
        String kodDokumen[] =  new String[2];
            kodDokumen[0] = "K";
             kodDokumen[1] = "SIIK";
              senaraiKandungan = myEtappService.senaraiDokumen(idPermohonan,kodDokumen);

        if (listHakmilikEndos.isEmpty()) {
            listHakmilikPermohonan = myEtappService.findHakmilikByIdPermohonan(idPermohonan);
        }
        return new JSP("etapp/hantar_borang_k.jsp").addParameter("tab", "true");
    }

    public Resolution endors() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        myEtappPendaftaranService.endors(permohonan, 2, null, pengguna);
        return new JSP("etapp/hantar_borang_k.jsp");
    }

    public Resolution selesai() throws IOException {
        RefPeringkat ref = new RefPeringkat();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        myeTappIntegrationFlowService.completeTask(ref.SEK8_HANTAR_BK, permohonan, pengguna, permohonan.getCawangan().getDaerah());
        return new JSP("etapp/hantar_borang_k.jsp");
    }

    public List<HakmilikEndorsanForm> getListHakmilikEndos() {
        return listHakmilikEndos;
    }

    public void setListHakmilikEndos(List<HakmilikEndorsanForm> listHakmilikEndos) {
        this.listHakmilikEndos = listHakmilikEndos;
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

    public List<HakmilikPermohonan> getListHakmilikPermohonan() {
        return listHakmilikPermohonan;
    }

    public void setListHakmilikPermohonan(List<HakmilikPermohonan> listHakmilikPermohonan) {
        this.listHakmilikPermohonan = listHakmilikPermohonan;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }


}
