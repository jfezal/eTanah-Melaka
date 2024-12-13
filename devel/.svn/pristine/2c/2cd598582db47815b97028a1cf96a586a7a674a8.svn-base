/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

/**
 *
 * @author abu.mansur
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanCukai;
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
import java.util.ArrayList;
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

@UrlBinding("/hasil/notis_6a")
public class Notis6AActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(Notis6AActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikDAO hakmilikDAO;
    private Hakmilik hakmilik;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    Permohonan permohonan;
    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    private List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<DasarTuntutanCukai> listDasar;
    private List senaraiNoRujukan;
    private DasarTuntutanCukai dasarTuntutanCukai;
    private boolean visible = false;
    private String noDasar;
    private Integer tempohTahun;
    private String kodStatus;
    private String idHakmilik;
    private String now;
    private String kodNegeri;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
             kodNegeri = "melaka";
        }

         return new JSP("hasil/notis_6A.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate:start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.debug("idPermohonan :" + idPermohonan);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiNoRujukan = new ArrayList();
            try {
                List<HakmilikPermohonan> senaraiHakmilikPermohonanAll = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilikPermohonanAll) {
                    if (hp.getHakmilik().getAkaunCukai().getBaki().intValue() > 0) {
                        senaraiHakmilikPermohonan.add(hp);
                        LOG.info("idHakmilik :"+hp.getHakmilik().getIdHakmilik());
                        String [] tname ={"hakmilik"};
                        Object [] tvalue={hp.getHakmilik()};
                        List<DasarTuntutanCukaiHakmilik> senaraiDasarHakmilik = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(tname, tvalue, null);
                        for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarHakmilik) {
                            if(dtch.getStatus().getKod().equals("ST")){ // ST = Sedang Dituntut in table kod_sts_tuntutan_cukai
                                noDasar = dtch.getDasarTuntutanCukai().getIdDasar();
                                senaraiNoRujukan.add(dtch.getNoRujukan());
                                LOG.info("noDasar :"+noDasar);
                            }
                        }
                        
                    }
                }
                LOG.debug("senaraiHakmilikPermohonan :" + senaraiHakmilikPermohonan.size());
            } catch (Exception ex) {
                LOG.error("rehydrate ex: " + ex);
            }
        }
        now = sdf.format(new java.util.Date());
        LOG.info("rehydrate:finish");
        String[] name = {"hakmilik"};
        Object[] value = {senaraiHakmilikPermohonan.get(0)};
        List<DasarTuntutanCukaiHakmilik> listHakmilik = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(name, value, null);

    }

    public Resolution cetak6A() throws FileNotFoundException {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Date now1 = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now1);
        String documentPath = conf.getProperty("document.path");
        String path = tarikh + File.separator + String.valueOf(idPermohonan);
        reportUtil.generateReport("hasilNotis6A.rdf",
                //                new String[]{"p_id_kew_dok"},
                new String[]{"p_id_mohon"},
                new String[]{idPermohonan},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public Resolution popup() {
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
     * @return the listDasar
     */
    public List<DasarTuntutanCukai> getListDasar() {
        return listDasar;
    }

    /**
     * @param listDasar the listDasar to set
     */
    public void setListDasar(List<DasarTuntutanCukai> listDasar) {
        this.listDasar = listDasar;
    }

    /**
     * @return the dasarTuntutanCukai
     */
    public DasarTuntutanCukai getDasarTuntutanCukai() {
        return dasarTuntutanCukai;
    }

    /**
     * @param dasarTuntutanCukai the dasarTuntutanCukai to set
     */
    public void setDasarTuntutanCukai(DasarTuntutanCukai dasarTuntutanCukai) {
        this.dasarTuntutanCukai = dasarTuntutanCukai;
    }

    /**
     * @return the kodNegeri
     */
    public String getKodNegeri() {
        return kodNegeri;
    }

    /**
     * @param kodNegeri the kodNegeri to set
     */
    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List getSenaraiNoRujukan() {
        return senaraiNoRujukan;
    }

    public void setSenaraiNoRujukan(List senaraiNoRujukan) {
        this.senaraiNoRujukan = senaraiNoRujukan;
    }
}
