/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import etanah.view.utility.JupemInIntegration;
import java.io.IOException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/penguatkuasaan/hantarGISFail")
public class HantarGISFailKeDMSActionBean extends AbleActionBean {

    @Inject
    JupemInIntegration jup;
    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanDAO permohonanDAO;
    private static final Logger LOG = Logger.getLogger(HantarGISFailKeDMSActionBean.class);
    private Permohonan permohonan;
    private Pengguna pengguna;
    private String idPermohonan;
    private String idAliran;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/hantarDMS.jsp").addParameter("tab", "true");
    }

    public Resolution getInboundGIS() throws IOException, Exception {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        idAliran = "g_terima_pa_b1";


        LOG.info("idPermohonan : " + idPermohonan);
        LOG.info("idAliran : " + idAliran);
        if (peng != null && idPermohonan != null && idAliran != null) {
            jup.setIa(enforceService.getInfo(peng));
            jup.setIdAliran(idAliran);
            jup.setIdPermohonan(idPermohonan);
            String msg = jup.inboundGIS(false);
            System.out.println("msg :" + msg);
        }
        addSimpleMessage("Data telah berjaya diterima. Sila semak di tab dokumen");
        return new JSP("penguatkuasaan/hantarDMS.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idAliran = "g_hantar_pu";
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
    }
}
