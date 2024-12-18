/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;
import etanah.view.etanahActionBeanContext;
import org.apache.log4j.Logger;

@UrlBinding("/consent/LaporanKelulusanTPTGActionBean")
public class LaporanKelulusanTPTGActionBean extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    etanahActionBeanContext ctx;
    private String reportName;

    private static final Logger LOGGER = Logger.getLogger(LaporanKelulusanPtgActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/consent/laporan_kelulusan_tptg.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        reportName = "CONS_RingkasanTPTG_KM_MLK.rdf";
        LOGGER.info("::: RDF " + reportName + " :::");
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

}
