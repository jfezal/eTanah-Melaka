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
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.ref.pengambilan.myetapp.RefPeringkat;
import etanah.service.acq.integrationflow.MyeTappIntegrationFlowService;
import etanah.view.etanahActionBeanContext;
import java.io.IOException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/etapp/terima_borang_k")
public class TerimaBorangKActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    MyeTappIntegrationFlowService myeTappIntegrationFlowService;
    Permohonan permohonan;

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        return new JSP("etapp/terima_borang_k.jsp");
    }

    public Resolution selesai() throws IOException {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        RefPeringkat ref = new RefPeringkat();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        myeTappIntegrationFlowService.completeTask(ref.SEK8_ENDORS_BK, permohonan, pengguna, permohonan.getCawangan().getDaerah());

        return new JSP("etapp/terima_borang_d.jsp");
    }
}
