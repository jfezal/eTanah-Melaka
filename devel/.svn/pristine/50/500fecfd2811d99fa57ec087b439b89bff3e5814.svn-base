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
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.InfoWarta;
import etanah.ref.pengambilan.myetapp.RefPeringkat;
import etanah.service.acq.integrationflow.MyeTappIntegrationFlowService;
import etanah.service.acq.service.MyEtappMapDataService;
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
@UrlBinding("/etapp/terima_borang_d")
public class TerimaBorangDActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    MyeTappIntegrationFlowService myeTappIntegrationFlowService;
    @Inject
    MyEtappService myEtappService;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Permohonan permohonan;
    List<HakmilikPermohonan> listHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
    String tarikhWarta;
    String noWarta;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        RefPeringkat ref = new RefPeringkat();
        InfoWarta infoWarta = myEtappService.findInfoWarta(idPermohonan, ref.SEK8_ENDORS_BD);
        tarikhWarta = sdf.format(infoWarta.getTrhWarta());
        noWarta = infoWarta.getNoWarta();
        listHakmilikPermohonan = myEtappService.findHakmilikByIdPermohonan(idPermohonan);
        return new JSP("etapp/terima_borang_d.jsp").addParameter("tab", "true");
    }

    public Resolution selesai() throws IOException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        RefPeringkat ref = new RefPeringkat();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        myeTappIntegrationFlowService.completeTask(ref.SEK8_ENDORS_BD, permohonan, pengguna, permohonan.getCawangan().getDaerah());

        return new JSP("etapp/terima_borang_d.jsp");
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonan() {
        return listHakmilikPermohonan;
    }

    public void setListHakmilikPermohonan(List<HakmilikPermohonan> listHakmilikPermohonan) {
        this.listHakmilikPermohonan = listHakmilikPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

}
