/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.emmkn.IntegrasiMohonStatus;
import etanah.service.common.IntegrasiService;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Izam
 */
@HttpCache(allow = false)
@UrlBinding("/util/log-emmkn")
public class LogEMMKNActionBean extends AbleActionBean {
    private static final Logger logger = Logger.getLogger(LogEMMKNActionBean.class);
    private static final String VIEWPAGE = "common/logEMMKN.jsp";

    private IntegrasiMohonStatus ims;
    private List<IntegrasiMohonStatus> imsList;

    private String idMohon;

    @Inject
    private IntegrasiService intgServ;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP(VIEWPAGE);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws NamingException {
        imsList = intgServ.allLogs();
    }

    public Resolution searchLog() {
        if(StringUtils.isNotBlank(idMohon)) {
            List<IntegrasiMohonStatus> im = intgServ.findByIdMohon(idMohon);
            if(im != null && im.size() > 0) {
                imsList = im;

                addSimpleMessage("Carian log e-MMKN untuk Id Permohonan " + idMohon + " berjaya dijumpai.");

            } else {
                addSimpleError("Log e-MMKN untuk Id Permohonan " + idMohon + " tidak wujud.");
            }
        } else {
            imsList = intgServ.allLogs();
        }

        return new JSP(VIEWPAGE);
    }

    public IntegrasiMohonStatus getIms() {
        return ims;
    }

    public void setIms(IntegrasiMohonStatus ims) {
        this.ims = ims;
    }

    public List<IntegrasiMohonStatus> getImsList() {
        return imsList;
    }

    public void setImsList(List<IntegrasiMohonStatus> imsList) {
        this.imsList = imsList;
    }

    public String getIdMohon() {
        return idMohon;
    }

    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }
}
