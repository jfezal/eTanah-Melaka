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
import etanah.service.acq.service.MyEtappMapDataService;
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
@UrlBinding("/etapp/hantar_borang_d")
public class HantarBorangDActionBean extends BasicTabActionBean {

    @Inject
    MyeTappIntegrationFlowService myeTappIntegrationFlowService;
    @Inject
    MyEtappMapDataService myEtappMapDataService;
    @Inject
    PermohonanDAO permohonanDAO;

    @Inject
    MyEtappService myEtappService;
    String idPermohonan;
    Permohonan permohonan;
    List<HakmilikEndorsanForm> listHakmilikEndos;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] u = new String[1];
        u[0] = "ABT-D";
        String[] kodDokumen = new String[2];
        kodDokumen[0] = "D";
        kodDokumen[1] = "SIID";
        listHakmilikEndos = myEtappMapDataService.findListHakmilikEndors(idPermohonan, u);

        senaraiKandungan = myEtappService.senaraiDokumen(idPermohonan, kodDokumen);

        return new JSP("etapp/hantar_borang_d.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {

        return new JSP("etapp/hantar_borang_d.jsp");
    }

    public Resolution selesai() throws IOException {
        RefPeringkat ref = new RefPeringkat();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        myeTappIntegrationFlowService.completeTask(ref.SEK8_HANTAR_BD, permohonan, pengguna, permohonan.getCawangan().getDaerah());
        return new JSP("etapp/hantar_borang_d.jsp");
    }

    public MyeTappIntegrationFlowService getMyeTappIntegrationFlowService() {
        return myeTappIntegrationFlowService;
    }

    public void setMyeTappIntegrationFlowService(MyeTappIntegrationFlowService myeTappIntegrationFlowService) {
        this.myeTappIntegrationFlowService = myeTappIntegrationFlowService;
    }

    public MyEtappMapDataService getMyEtappMapDataService() {
        return myEtappMapDataService;
    }

    public void setMyEtappMapDataService(MyEtappMapDataService myEtappMapDataService) {
        this.myEtappMapDataService = myEtappMapDataService;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
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

    public List<HakmilikEndorsanForm> getListHakmilikEndos() {
        return listHakmilikEndos;
    }

    public void setListHakmilikEndos(List<HakmilikEndorsanForm> listHakmilikEndos) {
        this.listHakmilikEndos = listHakmilikEndos;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

}
