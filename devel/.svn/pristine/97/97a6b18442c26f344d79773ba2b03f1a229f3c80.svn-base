/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodRizabDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.model.TanahRizabPermohonan;
import etanah.model.Permohonan;
import etanah.service.common.TanahRizabService;
import java.text.ParseException;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import org.apache.commons.lang.StringUtils;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodRizab;
import etanah.view.etanahActionBeanContext;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;


/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/sijilUpahUkur")
public class sijiupahukurActionBean extends AbleActionBean {

@DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/sedia_deraf_sijilUpahUkur.jsp").addParameter("tab", "true");
    }


}
