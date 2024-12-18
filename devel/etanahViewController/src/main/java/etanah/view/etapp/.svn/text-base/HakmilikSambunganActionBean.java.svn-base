/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.InfoWarta;
import etanah.model.etapp.EtappHakmilikSambungan;
import etanah.ref.pengambilan.myetapp.RefPeringkat;
import etanah.service.acq.integrationflow.MyeTappIntegrationFlowService;
import etanah.service.ambil.MyEtappService;
import etanah.view.BasicTabActionBean;
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

@UrlBinding("/etapp/hakmilik_sambungan")
public class HakmilikSambunganActionBean extends BasicTabActionBean{
    
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    MyeTappIntegrationFlowService myeTappIntegrationFlowService;
    @Inject
    MyEtappService myEtappService;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Permohonan permohonan;
    List<HakmilikPermohonan> listHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
    List<EtappHakmilikSambunganForm> listHakmilikSambungan =new ArrayList<EtappHakmilikSambunganForm>() ;
    
       @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        RefPeringkat ref = new RefPeringkat();

        listHakmilikPermohonan = myEtappService.findHakmilikByIdPermohonan(idPermohonan);
        listHakmilikSambungan = myEtappService.setHakmilikSambungan(idPermohonan);
//        findEtappHakmilikSambByIdMohon(idPermohonan);
        return new JSP("etapp/hakmilik_sambungan.jsp").addParameter("tab", "true");
    }

    public Resolution selesai() throws IOException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        RefPeringkat ref = new RefPeringkat();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        myeTappIntegrationFlowService.completeTask(ref.SEK8_HANTAR_HS, permohonan, pengguna, permohonan.getCawangan().getDaerah());

        return new JSP("etapp/hakmilik_sambungan.jsp");
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public MyeTappIntegrationFlowService getMyeTappIntegrationFlowService() {
        return myeTappIntegrationFlowService;
    }

    public void setMyeTappIntegrationFlowService(MyeTappIntegrationFlowService myeTappIntegrationFlowService) {
        this.myeTappIntegrationFlowService = myeTappIntegrationFlowService;
    }

    public MyEtappService getMyEtappService() {
        return myEtappService;
    }

    public void setMyEtappService(MyEtappService myEtappService) {
        this.myEtappService = myEtappService;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
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
    
    
}
