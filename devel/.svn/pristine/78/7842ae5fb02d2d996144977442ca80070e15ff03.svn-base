/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
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

/**
 *
 * @author nurfaizati
 */
@UrlBinding("/hasil/penyediaan8Apt")
public class Penyediaan8AptActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(Penyediaan8AptActionBean.class);
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
    DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;

    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    
    private List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List senaraiNoRujukan;

    private boolean visible = false;
    private String noDasar;
    private Integer tempohTahun;
    private String kodStatus;
    private String idHakmilik;
    private String negeri;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        if("04".equals(conf.getProperty("kodNegeri")))
            negeri = "melaka";
        return new JSP("hasil/penyediaan_8A_pt.jsp").addParameter("tab", "true");
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.debug("idPermohonan :"+idPermohonan);

        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiNoRujukan = new ArrayList();
            try{
                List<HakmilikPermohonan> senaraiHakmilikPermohonanAll = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilikPermohonanAll) {
                    if(hp.getHakmilik().getAkaunCukai().getBaki().intValue() > 0)
                        senaraiHakmilikPermohonan.add(hp);
                        LOG.info("idHakmilik :"+hp.getHakmilik().getIdHakmilik());
                        String [] tname ={"hakmilik"};
                        Object [] tvalue={hp.getHakmilik()};
                        List<DasarTuntutanCukaiHakmilik> senaraiDasarHakmilik = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(tname, tvalue, null);
                        senaraiNoRujukan.add(senaraiDasarHakmilik.get(0).getNoRujukan());
                }
            }catch(Exception ex){
                LOG.error("rehydrate ex: "+ex);
            }
        }
        LOG.info("rehydrate:finish");
    }

    public Resolution cetak8A() throws FileNotFoundException{
         etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        Date now1 = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now1);
//        String idKew = getContext().getRequest().getParameter("idKew");
//        String idHakmilik = hakmilik.getIdHakmilik();
//        String documentPath = File.separator + "tmp" + File.separator;
        String documentPath = conf.getProperty("document.path");
        String path = tarikh + File.separator + String.valueOf(idPermohonan);
        reportUtil.generateReport("hasilNotis8A.rdf",
                //                new String[]{"p_id_kew_dok"},
                new String[]{"p_id_mohon"},
                new String[]{idPermohonan},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public Resolution popup(){
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

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public List getSenaraiNoRujukan() {
        return senaraiNoRujukan;
    }

    public void setSenaraiNoRujukan(List senaraiNoRujukan) {
        this.senaraiNoRujukan = senaraiNoRujukan;
    }
}
