/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.gis.GISPelanDAO;
import etanah.dao.gis.PelanGISDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.gis.GISPelan;
import etanah.model.gis.GISPelanPK;
import etanah.model.gis.PelanGIS;
import etanah.model.gis.PelanGISPK;
import etanah.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
@UrlBinding("/utiliti/kemasukanPelan")
public class KemasukanPelanManualActionBean extends AbleActionBean {

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

    private List<KodDaerah> listKodDaerah;
    private List<KodBandarPekanMukim> listBPM;
    private List<KodSeksyen> listKodSeksyen;
    private String daerah;
    private String mukim;
    private String seksyen;
    private String noLot;
    private String noPT;
    private BigDecimal luas;
    private String nopelanakui;
    private String pelantif;
    private String unitukur;
    private FileBean file;
    private String jenispelan;

    @DefaultHandler
    public Resolution showForm() {
        listKodDaerah = kodDaerahDAO.findAll();
        listBPM = kodBandarPekanMukimDAO.findAll();
        listKodSeksyen = kodSeksyenDAO.findAll();
        return new JSP("utiliti/kemasukan_pelan_manual.jsp");
    }

    public Resolution filterSeksyen() {
        seksyen = (String) getContext().getRequest().getParameter("seksyen");
        daerah = (String) getContext().getRequest().getParameter("daerah");
        mukim = (String) getContext().getRequest().getParameter("mukim");
//        reportName = getContext().getRequest().getParameter("namaReport");
//        report = getContext().getRequest().getParameter("report");
//        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        listKodDaerah = kodDaerahDAO.findAll();

        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (mukim == null || mukim.equals("")) {
            sql = "select seks from KodSeksyen seks ";
            q = s.createQuery(sql);
        } else {
            sql = "select seks from KodSeksyen seks where seks.kodBandarPekanMukim.kod = :kod order by seks.kod asc ";
            q = s.createQuery(sql);
            q.setString("kod", mukim);
        }
        listKodSeksyen = q.list();
        daerah = (String) getContext().getRequest().getParameter("daerah");
//        reportName = getContext().getRequest().getParameter("namaReport");
//        report = getContext().getRequest().getParameter("report");
//        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (daerah == null || daerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", daerah);
        }
        listBPM = q.list();
        return new JSP("utiliti/kemasukan_pelan_manual.jsp");
    }

    public Resolution filterMukim() {
        daerah = (String) getContext().getRequest().getParameter("daerah");
//        reportName = getContext().getRequest().getParameter("namaReport");
//        report = getContext().getRequest().getParameter("report");
//        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        listKodDaerah = kodDaerahDAO.findAll();
        listKodSeksyen = kodSeksyenDAO.findAll();
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (daerah == null || daerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", daerah);
        }
        listBPM = q.list();
        listKodSeksyen = kodSeksyenDAO.findAll();
        return new JSP("utiliti/kemasukan_pelan_manual.jsp");
    }

    public Resolution simpan() throws IOException, Exception {

        if (StringUtils.isNotBlank(daerah) || StringUtils.isNotBlank(mukim) || StringUtils.isNotBlank(noLot) || StringUtils.isNotBlank(jenispelan)) {
        
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        GISPelan pelan = new GISPelan();
        GISPelanPK pk = new GISPelanPK();
        KodNegeri kn = kodNegeriDAO.findById(conf.getKodNegeri());
        KodDaerah kd = kodDaerahDAO.findById(daerah);
        KodBandarPekanMukim bpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(mukim));
        KodSeksyen seksyen1 = null;
        if (StringUtils.isNotBlank(seksyen)) {
            seksyen1 = kodSeksyenDAO.findById(Integer.parseInt(seksyen));
            pk.setKodSeksyen(String.valueOf(seksyen1.getSeksyen()));
        } else {
            pk.setKodSeksyen("000");

        }
        pk.setKodNegeri(kn);
        pk.setKodDaerah(kd);
        pk.setKodMukim(bpm.getbandarPekanMukim());

        pk.setNoLot(noLot);
        pk.setJenisPelan(jenispelan);
        pelan.setPelanGISPK(pk);

        pelan.setLuas(luas);
        pelan.setNoPelanAkui(nopelanakui);
//        pelan.setPelanTif(pelantif);
        pelan.setUnitUkur(unitukur);
        pelan.setTrhKemaskini(new Date());
        pelan.setPelanTif(File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (StringUtils.isNotBlank(seksyen) ? File.separator + seksyen1.getSeksyen() : "") + File.separator + file.getFileName().toLowerCase());
//        pelan.setTrhKemaskini(new Date());
        pelanGISDAO.saveOrUpdate(pelan);
        String loc = conf.getPelanPath() + File.separator + pelan.getPelanGISPK().getJenisPelan() + File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (StringUtils.isNotBlank(seksyen) ? File.separator + seksyen1.getSeksyen() : "");
        FileUtil fileUtil = new FileUtil(loc);
        System.out.print("sssss" + file.getFileName().toLowerCase());
        fileUtil.saveFile(file.getFileName().toLowerCase(), file.getInputStream());
        tx.commit();
        }else{
        
            addSimpleError("Semak data kemasukan anda");}
        resetValue();
        return new ForwardResolution("/WEB-INF/jsp/utiliti/kemasukan_pelan_manual.jsp");
    }

    public void resetValue() {
        noLot = "";
        jenispelan = "";
        noPT = "";
        luas = new BigDecimal(0);
        nopelanakui = "";
        pelantif = "";
        unitukur = "";
        seksyen = "";
        daerah = "";
        mukim = "";
        file = null;
        listKodDaerah = kodDaerahDAO.findAll();
        listBPM = kodBandarPekanMukimDAO.findAll();
        listKodSeksyen = kodSeksyenDAO.findAll();

    }

    public List<KodDaerah> getListKodDaerah() {
        return listKodDaerah;
    }

    public void setListKodDaerah(List<KodDaerah> listKodDaerah) {
        this.listKodDaerah = listKodDaerah;
    }

    public List<KodBandarPekanMukim> getListBPM() {
        return listBPM;
    }

    public void setListBPM(List<KodBandarPekanMukim> listBPM) {
        this.listBPM = listBPM;
    }

    public List<KodSeksyen> getListKodSeksyen() {
        return listKodSeksyen;
    }

    public void setListKodSeksyen(List<KodSeksyen> listKodSeksyen) {
        this.listKodSeksyen = listKodSeksyen;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public KodDaerahDAO getKodDaerahDAO() {
        return kodDaerahDAO;
    }

    public void setKodDaerahDAO(KodDaerahDAO kodDaerahDAO) {
        this.kodDaerahDAO = kodDaerahDAO;
    }

    public KodNegeriDAO getKodNegeriDAO() {
        return kodNegeriDAO;
    }

    public void setKodNegeriDAO(KodNegeriDAO kodNegeriDAO) {
        this.kodNegeriDAO = kodNegeriDAO;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getMukim() {
        return mukim;
    }

    public void setMukim(String mukim) {
        this.mukim = mukim;
    }

    public String getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoPT() {
        return noPT;
    }

    public void setNoPT(String noPT) {
        this.noPT = noPT;
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

    public String getJenispelan() {
        return jenispelan;
    }

    public void setJenispelan(String jenispelan) {
        this.jenispelan = jenispelan;
    }

}
