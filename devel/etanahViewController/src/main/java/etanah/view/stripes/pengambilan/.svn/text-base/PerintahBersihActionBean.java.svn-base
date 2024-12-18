/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.model.KodCawangan;
import etanah.model.Permohonan;
import etanah.model.Notis;
import etanah.model.Dokumen;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.KodNotis;
import etanah.model.HakmilikPermohonan;
import etanah.service.PengambilanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.service.common.PerbicaraanService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
/**
 *
 * @author nordiyana
 */
@UrlBinding("pengambilan/perintah")
public class PerintahBersihActionBean extends AbleActionBean {


    @DefaultHandler
    public Resolution showForm() {
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/DerafPerintah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/PerintahBersih.jsp").addParameter("tab", "true");
    }
    public Resolution showForm3() {
         getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/DerafPerintah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
         getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/PerintahBersih.jsp").addParameter("tab", "true");
    }

    

    
}




