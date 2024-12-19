/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/laporan_consent")
public class LaporanConsentActionBean extends AbleActionBean {

    private String report_p_kod_caw;
    private String reportName;
    private String report_p_trh_mula;
    private String report_p_trh_tamat;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        report_p_kod_caw =  pengguna.getKodCawangan().getKod();
        reportName = "CONSLaporanPemohon.rdf";
        return new ForwardResolution("/WEB-INF/jsp/consent/laporan_consent.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public String getReport_p_kod_caw() {
        return report_p_kod_caw;
    }

    public void setReport_p_kod_caw(String report_p_kod_caw) {
        this.report_p_kod_caw = report_p_kod_caw;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReport_p_trh_mula() {
        return report_p_trh_mula;
    }

    public void setReport_p_trh_mula(String report_p_trh_mula) {
        this.report_p_trh_mula = report_p_trh_mula;
    }

    public String getReport_p_trh_tamat() {
        return report_p_trh_tamat;
    }

    public void setReport_p_trh_tamat(String report_p_trh_tamat) {
        this.report_p_trh_tamat = report_p_trh_tamat;
    }
    
}
