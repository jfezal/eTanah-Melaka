/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLHandshakeException;
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
 * @author faidzal
 */
@UrlBinding("/224242424/353566363636363")
public class InIntegrationActionBean extends AbleActionBean {

    @Inject
    JupemInIntegration jup;
    @Inject
    StrataPtService strataService;
    @Inject
    PermohonanDAO permohonanDAO;
    private static final Logger LOG = Logger.getLogger(InIntegrationActionBean.class);

    @DefaultHandler
    public Resolution getInboundGIS() throws IOException, Exception {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String idAliran = getContext().getRequest().getParameter("idAliran");


        LOG.info("idPermohonan : " + idPermohonan);
        LOG.info("idAliran : " + idAliran);
        if (peng != null && idPermohonan != null && idAliran != null) {
            jup.setIa(strataService.getInfo(peng));
            jup.setIdAliran(idAliran);
            jup.setIdPermohonan(idPermohonan);
            String msg = jup.inboundGIS(false);
        }
        return new JSP("utiliti/InboundIntegration.jsp").addParameter("idPermohonan", "").addParameter("idAliran", "");
    }

    public Resolution executeCMD() throws IOException, Exception {
        String peng = getContext().getRequest().getParameter("pengguna");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String idAliran = getContext().getRequest().getParameter("idAliran");
        LOG.info("idPermohonan : " + idPermohonan);
        LOG.info("idAliran : " + idAliran);
        if (peng != null && idPermohonan != null && idAliran != null) {
            jup.setIa(jup.getInfoPenguna(peng));
            jup.setIdAliran(idAliran);
            jup.setIdPermohonan(idPermohonan);
            String msg = jup.inboundGIS(true);
        }
        return null;
    }

    public Resolution initiateTask() throws WorkflowException, StaleObjectException {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonanL");
        String outCome = getContext().getRequest().getParameter("outCome");
        String pengguna = getContext().getRequest().getParameter("pengguna");
        LOG.info("idPermohonan : " + idPermohonan + " outCome : " + outCome + " Pengguna : " + pengguna);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        jup.initiateTaskbyId(permohonan, outCome, pengguna);

        return new ForwardResolution("/WEB-INF/jsp/utiliti/InboundIntegration.jsp");
    }


 @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public JupemInIntegration getJup() {
        return jup;
    }

    public void setJup(JupemInIntegration jup) {
        this.jup = jup;
    }
}
