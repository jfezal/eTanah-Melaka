/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.view.ListUtil;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;
import etanah.view.etanahActionBeanContext;
import org.apache.log4j.Logger;

@UrlBinding("/consent/laporan_km")
public class LaporanKMActionBean extends AbleActionBean {

    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    etanahActionBeanContext ctx;
    private String reportName;

    private static final Logger LOGGER = Logger.getLogger(LaporanKMActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/consent/laporan_km.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        reportName = "CONS_RingkasanKM_MLK.rdf";
        LOGGER.info("::: RDF " + reportName + " :::");
    }

    public Resolution mengikutJenis() {

        reportName = "CONS_RingkasanKM_MLK.rdf";
        LOGGER.info("::: RDF " + reportName + " :::");

        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

}
