/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;
import org.apache.log4j.Logger;

@UrlBinding("/hasil/laporan_tanah_2")
public class LporanTanah6A_2ActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(LporanTanah6A_2ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikDAO hakmilikDAO;
    private Hakmilik hakmilik;
    @Inject
    PermohonanDAO permohonanDAO;
    Permohonan permohonan;
    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    private List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private boolean visible = false;
    private String noDasar;
    private Integer tempohTahun;
    private String kodStatus;
    private String idHakmilik;
    private String now;
    private boolean flag = false;
    private boolean flag1 = false;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("hasil/laporan_6A.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        setFlag(true);
        setFlag1(true);
        return new JSP("hasil/laporan_6A.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate:start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.debug("idPermohonan :" + idPermohonan);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            try {
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                LOG.debug("senaraiHakmilikPermohonan :" + senaraiHakmilikPermohonan.size());
            } catch (Exception ex) {
                LOG.error("rehydrate ex: " + ex);
            }
        }
        now = sdf.format(new java.util.Date());
        LOG.info("rehydrate:finish");
    }

    public Resolution popup() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
            setFlag1(true);
        return new JSP("consent/hasil_borang_lapor_tanah.jsp").addParameter("popup", "true");
    }

    public Resolution laporanTanah() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new JSP("hasil/hakmilik_details.jsp").addParameter("popup", "true");
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Integer getTempohTahun() {
        return tempohTahun;
    }

    public void setTempohTahun(Integer tempohTahun) {
        this.tempohTahun = tempohTahun;
    }

    public String getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(String kodStatus) {
        this.kodStatus = kodStatus;
    }

    public String getNoDasar() {
        return noDasar;
    }

    public void setNoDasar(String noDasar) {
        this.noDasar = noDasar;
    }

    public List<DasarTuntutanCukaiHakmilik> getSenaraiDasarTuntutanCukaiHakmilik() {
        return senaraiDasarTuntutanCukaiHakmilik;
    }

    public void setSenaraiDasarTuntutanCukaiHakmilik(List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik) {
        this.senaraiDasarTuntutanCukaiHakmilik = senaraiDasarTuntutanCukaiHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    /**
     * @return the flag
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * @return the flag1
     */
    public boolean isFlag1() {
        return flag1;
    }

    /**
     * @param flag1 the flag1 to set
     */
    public void setFlag1(boolean flag1) {
        this.flag1 = flag1;
    }
}







