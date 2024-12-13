/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common.b1;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanB1DAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.gis.GISPelanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonanB1;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPlotKpsn;
import etanah.model.PermohonanPlotPelan;
import etanah.model.gis.GISPelan;
import etanah.model.gis.GISPelanPK;
import etanah.service.common.PelanB1Service;
import etanah.util.FileUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author john
 */
@UrlBinding("/common/b1/kemasukan_pelan_b1")
public class KemasukanPelanB1ActionBean extends AbleActionBean {

    @Inject
    PelanB1Service pelanB1Service;
    @Inject
    HakmilikPermohonanB1DAO hakmilikPermohonanB1DAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    GISPelanDAO pelanGISDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    private String nolot;
    private BigDecimal luass;
    private String nopelanakui;
    private String unitukur;
    private FileBean file;
    private List<Hakmilik> senaraiHakmilikQT;
    private String idPermohonan;
private Pengguna pguna;
    Long idPlot;
    String idHakmilikQT;
    Permohonan permohonan;
    private static final Logger LOG = Logger.getLogger(KemasukanPelanB1ActionBean.class);

    @DefaultHandler
    public Resolution muatNaik() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        PermohonanPlotPelan plot = pelanB1Service.findPlotPelanBIdMohon(idPermohonan);
        senaraiHakmilikQT = pelanB1Service.findListQT(idPermohonan);
        idPlot = plot.getIdPlot();

        return new ForwardResolution("/WEB-INF/jsp/common/b1/kemasukan_pelan_b1.jsp").addParameter("popup", "true");
    }
 @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
    }
    public Resolution simpan() throws IOException, Exception {
        PermohonanPlotKpsn kpsn = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot)).getPermohonanPlotKpsn();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        GISPelan pelan = new GISPelan();
        GISPelanPK pk = new GISPelanPK();
        KodNegeri kn = kodNegeriDAO.findById(conf.getKodNegeri());
        KodDaerah kd = kpsn.getDaerah();
        KodBandarPekanMukim bpm = kpsn.getBpm();
        KodSeksyen seksyen1 = kpsn.getSeksyen();
        if (seksyen1 != null) {
            pk.setKodSeksyen(String.valueOf(seksyen1.getSeksyen()));
        } else {
            pk.setKodSeksyen("000");

        }
        pk.setKodNegeri(kn);
        pk.setKodDaerah(kd);
        pk.setKodMukim(bpm.getbandarPekanMukim());

        pk.setNoLot(nolot);
        pk.setJenisPelan("B1");
        pelan.setPelanGISPK(pk);

        pelan.setLuas(luass);
        pelan.setNoPelanAkui(nopelanakui);
//        pelan.setPelanTif(pelantif);
        pelan.setUnitUkur(unitukur);
        pelan.setTrhKemaskini(new Date());
        if (file != null) {
            pelan.setPelanTif(File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (seksyen1 != null ? File.separator + seksyen1.getSeksyen() : "") + File.separator + file.getFileName().toLowerCase());
        }

//        pelan.setTrhKemaskini(new Date());
        pelanGISDAO.saveOrUpdate(pelan);
        if (file != null) {
            String loc = conf.getPelanPath() + File.separator + pelan.getPelanGISPK().getJenisPelan() + File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (seksyen1 != null ? File.separator + seksyen1.getSeksyen() : "");
            FileUtil fileUtil = new FileUtil(loc);
            System.out.print("sssss" + file.getFileName().toLowerCase());
            fileUtil.saveFile(file.getFileName().toLowerCase(), file.getInputStream());

        }
        HakmilikPermohonanB1 mhB1 = pelanB1Service.findHakmilikB1ByIdHakmilik(idHakmilikQT, idPermohonan);
        mhB1.setKodUnitLuas(kodUOMDAO.findById(unitukur));
        mhB1.setLuasTerlibat(luass);
        mhB1.setNoLot(nolot);
        mhB1.setNoPelanAkui(nopelanakui);
        pelanB1Service.simpanHakmilikPermohonanB1(mhB1);
        tx.commit();
        senaraiHakmilikQT = pelanB1Service.findListQT(idPermohonan);

        return new ForwardResolution("/WEB-INF/jsp/common/b1/kemasukan_pelan_b1.jsp").addParameter("popup", "true");
    }

    public String getNolot() {
        return nolot;
    }

    public void setNolot(String nolot) {
        this.nolot = nolot;
    }

    public BigDecimal getLuass() {
        return luass;
    }

    public void setLuass(BigDecimal luass) {
        this.luass = luass;
    }

    public String getNopelanakui() {
        return nopelanakui;
    }

    public void setNopelanakui(String nopelanakui) {
        this.nopelanakui = nopelanakui;
    }

    public String getUnitukur() {
        return unitukur;
    }

    public void setUnitukur(String unitukur) {
        this.unitukur = unitukur;
    }

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Long getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(Long idPlot) {
        this.idPlot = idPlot;
    }

    public String getIdHakmilikQT() {
        return idHakmilikQT;
    }

    public void setIdHakmilikQT(String idHakmilikQT) {
        this.idHakmilikQT = idHakmilikQT;
    }

    public List<Hakmilik> getSenaraiHakmilikQT() {
        return senaraiHakmilikQT;
    }

    public void setSenaraiHakmilikQT(List<Hakmilik> senaraiHakmilikQT) {
        this.senaraiHakmilikQT = senaraiHakmilikQT;
    }
    
    

}
