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
import etanah.service.common.PemohonService;
import etanah.model.*;
import etanah.service.PengambilanService;
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
import org.hibernate.Query;
import org.hibernate.Session;
import java.math.BigDecimal;
import org.hibernate.Transaction;
import java.text.ParseException;

@UrlBinding("/pengambilan/suratMakluman")
public class SuratMaklumanPercetakanWartaActionBean extends AbleActionBean
{
    private static Logger logger = Logger.getLogger(SuratMaklumanPercetakanWartaActionBean.class);
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PemohonService pemohonService;

    private String idPermohonan;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/suratMaklumanPercetakanWarta.jsp").addParameter("tab", "true");
    }
}


