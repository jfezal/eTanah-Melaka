/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;

@UrlBinding("/pengambilan/janaJadualPertama")
public class JanaJadualPertamaSeksyen11ActionBean extends AbleActionBean
{
    private static Logger logger = Logger.getLogger(JanaJadualPertamaSeksyen11ActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/izinlaluaktabekalanletrik/janaJadualPertamaSeksyen11.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pengambilan/negerisembilan/izinlaluaktabekalanletrik/imbasJadualPertamaSeksyen11.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("pengambilan/negerisembilan/izinlaluaktabekalanletrik/sediaSuratKeputusanMMK.jsp").addParameter("tab", "true");
    }
}


