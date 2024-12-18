/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common.b1;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.gis.GISPelanDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.PermohonanPlotKpsn;
import etanah.model.gis.GISPelan;
import etanah.model.gis.GISPelanPK;
import etanah.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author john
 */
@UrlBinding("/common/b1/muat_naik")
public class MuatNaikB1ActionBean extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    GISPelanDAO pelanGISDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    private String noLot;
    private BigDecimal luas;
    private String nopelanakui;
    private String pelantif;
    private String unitukur;
    private FileBean file;
    String idPlot;
    String idPermohonan;

    public Resolution simpan() throws IOException, Exception {
        PermohonanPlotKpsn kpsn = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot)).getPermohonanPlotKpsn();

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

        pk.setNoLot(noLot);
        pk.setJenisPelan("B1");
        pelan.setPelanGISPK(pk);

        pelan.setLuas(luas);
        pelan.setUnitUkur(unitukur);
        pelan.setTrhKemaskini(new Date());
        pelan.setPelanTif(File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (seksyen1 != null ? File.separator + seksyen1.getSeksyen() : "") + File.separator + file.getFileName().toLowerCase());
//        pelan.setTrhKemaskini(new Date());
        pelanGISDAO.saveOrUpdate(pelan);
        String loc = conf.getPelanPath() + File.separator + pelan.getPelanGISPK().getJenisPelan() + File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (seksyen1 != null ? File.separator + seksyen1.getSeksyen() : "");
        FileUtil fileUtil = new FileUtil(loc);
        System.out.print("sssss" + file.getFileName().toLowerCase());
        fileUtil.saveFile(file.getFileName().toLowerCase(), file.getInputStream());
        tx.commit();

        return new JSP("pembangunan/common/muat_naik_b1.jsp").addParameter("popup", "true");
    }

    public Resolution muatNaik() {

        return new JSP("pembangunan/common/muat_naik_b1.jsp").addParameter("popup", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public String getNopelanakui() {
        return nopelanakui;
    }

    public void setNopelanakui(String nopelanakui) {
        this.nopelanakui = nopelanakui;
    }

    public String getPelantif() {
        return pelantif;
    }

    public void setPelantif(String pelantif) {
        this.pelantif = pelantif;
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

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(String idPlot) {
        this.idPlot = idPlot;
    }

}
