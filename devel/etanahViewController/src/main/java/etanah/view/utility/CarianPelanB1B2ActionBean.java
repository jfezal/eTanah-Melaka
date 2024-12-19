/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.Configuration;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.gis.GISPelanDAO;
import etanah.dao.gis.PelanGISDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.gis.GISPelan;
import etanah.model.gis.GISPelanNew;
import etanah.model.gis.GISPelanPK;
import etanah.model.gis.PelanGIS;
import etanah.model.gis.PelanGISPK;
import etanah.service.daftar.PermohonanPihakKemaskiniService;
import etanah.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
@UrlBinding("/utiliti/carian_pelan_path")
public class CarianPelanB1B2ActionBean extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    GISPelanDAO GISPelanDAO;
    @Inject
    PelanGISDAO pelanGISDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    PermohonanDAO mohonDao;
    @Inject
    PermohonanPihakKemaskiniService permohonanPihakKkiniService;

    private List<KodDaerah> listKodDaerah;
    private List<KodBandarPekanMukim> listBPM;
    private List<KodSeksyen> listKodSeksyen;
    private List<GISPelanNew> listpelanGIS;
    private List<GISPelan> listpelanGIS1;
    private String daerah;
    private String mukim;
    private String seksyen;
    private String noLot;
    private String kodDaerahDel;
    private String jenisPelanDel;
    private String kodMukimDel;
    private String kodSeksyenDel;
    private String noLotDel;
    private String noPT;
    private BigDecimal luas;
    private String nopelanakui;
    private String pelantif;
    private String unitukur;
    private FileBean file;
    private String jenispelan;
    private String b1path;
    private String b2path;

    @DefaultHandler
    public Resolution showForm() {
        listKodDaerah = kodDaerahDAO.findAll();
        listBPM = kodBandarPekanMukimDAO.findAll();
        listKodSeksyen = kodSeksyenDAO.findAll();
        return new JSP("utiliti/carian_pelan_b1b2.jsp");
    }

    public Resolution filterSeksyen() {
//        seksyen = (String) getContext().getRequest().getParameter("seksyen");
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
        return new JSP("utiliti/carian_pelan_b1b2.jsp");
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
        return new JSP("utiliti/carian_pelan_b1b2.jsp");
    }

    public Resolution cari() throws IOException, Exception {
//        String daerah = "";
//        String mukim = "";
//        String seksyen = "";
//        String noLot = "";
        b1path = conf.getPelanPath() + "B1";
        b2path = conf.getPelanPath() + "B2";
        KodNegeri negeri = kodNegeriDAO.findById(conf.getKodNegeri());
        KodDaerah kodDaerah = kodDaerahDAO.findById(daerah);
        KodSeksyen seksyen1 = null;
        KodBandarPekanMukim bpm = null;
        GISPelanPK pelanGISPK = new GISPelanPK();
        pelanGISPK.setKodDaerah(kodDaerah);
        pelanGISPK.setKodNegeri(negeri);
        if (StringUtils.isNotBlank(mukim)) {
            bpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(mukim));
            pelanGISPK.setKodMukim(bpm.getbandarPekanMukim());
        }
        if (StringUtils.isNotBlank(seksyen)) {
            seksyen1 = kodSeksyenDAO.findById(Integer.parseInt(seksyen));
            pelanGISPK.setKodSeksyen(String.valueOf(seksyen1.getSeksyen()));
        } else {
            pelanGISPK.setKodSeksyen("000");

        }

        String[] tname = {"pelanGISPK"};
        Object[] model = {pelanGISPK};
        List<PelanGIS> listPelan = pelanGISDAO.findByEqualCriterias(tname, model, null);
        System.out.print(listPelan);
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (StringUtils.isBlank(daerah) && (StringUtils.isBlank(seksyen)) && (StringUtils.isBlank(mukim))) {
            sql = "select pelan from GISPelanNew pelan ";
            q = s.createQuery(sql);
        } else {
            sql = "select pelan from etanah.model.gis.GISPelanNew pelan  where "
                    + "pelan.jenisPelan = :jenispelan ";
            if (StringUtils.isNotBlank(daerah)) {
                sql += "and pelan.kodDaerah.kod = :daerah  ";
            }

            if (StringUtils.isNotBlank(mukim)) {
                sql += " and pelan.kodMukim =:mukim";
            }

            sql += " and pelan.kodSeksyen = :seksyen";

      
                if (StringUtils.isNotBlank(noLot)) {
                    sql += " and pelan.noLot = :noLot";
                }

//            if (StringUtils.isNotBlank(noLot)) {
//                sql += " and pelan.pelanGISPK.noLot = :noLot";
//            }

            q = s.createQuery(sql);
            if (StringUtils.isNotBlank(daerah)) {
                q.setString("daerah", daerah);
            }
            if (StringUtils.isNotBlank(seksyen)) {
                q.setString("seksyen", seksyen1.getSeksyen());
            } else {
                q.setString("seksyen", "000");
            }
            if (StringUtils.isNotBlank(mukim)) {
                q.setString("mukim", bpm.getbandarPekanMukim());
            }

            if (StringUtils.isNotBlank(noLot)) {
                q.setString("noLot", noLot);
            }

            q.setString("jenispelan", jenispelan);
        }
        listpelanGIS = q.list();
        if (!q.list().isEmpty()) {
//            System.out.print("lalal" + q.uniqueResult());
        }
        resetValue();
//        return new ForwardResolution("/WEB-INF/jsp/utiliti/carian_pelan_b1b2.jsp");
        return new JSP("utiliti/carian_pelan_b1b2.jsp");
    }

    public Resolution delete() throws IOException, Exception {

//        String daerah = "";
//        String mukim = "";
//        String seksyen = "";
//        String noLot = "";
        b1path = conf.getPelanPath() + "B1";
        b2path = conf.getPelanPath() + "B2";
//            String kodDaerahDel = getContext().getRequest().getParameter("daerah");
        KodNegeri negeri = kodNegeriDAO.findById(conf.getKodNegeri());
        KodDaerah kodDaerah = kodDaerahDAO.findById(kodDaerahDel);
        KodSeksyen seksyen1 = null;
        KodBandarPekanMukim bpm = null;
        GISPelanPK pelanGISPK = new GISPelanPK();
        pelanGISPK.setKodDaerah(kodDaerah);
        pelanGISPK.setKodNegeri(negeri);
        if (StringUtils.isNotBlank(kodMukimDel)) {
            bpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(kodMukimDel));
            pelanGISPK.setKodMukim(bpm.getbandarPekanMukim());
        }
        if (StringUtils.isNotBlank(kodSeksyenDel)) {
            if (!kodSeksyenDel.equals("000")) {
                seksyen1 = kodSeksyenDAO.findById(Integer.parseInt(kodSeksyenDel));
                pelanGISPK.setKodSeksyen(String.valueOf(seksyen1.getSeksyen()));
            }

        } else {
            pelanGISPK.setKodSeksyen("000");

        }

        String[] tname = {"pelanGISPK"};
        Object[] model = {pelanGISPK};
        List<PelanGIS> listPelan = pelanGISDAO.findByEqualCriterias(tname, model, null);
        System.out.print(listPelan);
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (StringUtils.isBlank(kodDaerahDel) && (StringUtils.isBlank(kodSeksyenDel)) && (StringUtils.isBlank(kodMukimDel))) {
            sql = "select pelan from GISPelan pelan ";
            q = s.createQuery(sql);
        } else {
            sql = "select pelan from etanah.model.gis.GISPelan pelan  where "
                    + "pelan.pelanGISPK.jenisPelan = :jenispelan ";
            if (StringUtils.isNotBlank(kodDaerahDel)) {
                sql += "and pelan.pelanGISPK.kodDaerah.kod = :daerah  ";
            }

            if (StringUtils.isNotBlank(kodMukimDel)) {
                sql += " and pelan.pelanGISPK.kodMukim =:mukim";
            }

            sql += " and pelan.pelanGISPK.kodSeksyen = :seksyen";

            if (StringUtils.isNotBlank(noLotDel)) {
                sql += " and pelan.pelanGISPK.noLot = :noLot";
            }

            q = s.createQuery(sql);
            if (StringUtils.isNotBlank(kodDaerahDel)) {
                q.setString("daerah", kodDaerahDel);
            }
            if (StringUtils.isNotBlank(kodSeksyenDel)) {
                q.setString("seksyen", kodSeksyenDel);
            } else {
                q.setString("seksyen", "000");
            }
            if (StringUtils.isNotBlank(kodMukimDel)) {
                q.setString("mukim", kodMukimDel);
            }

            if (StringUtils.isNotBlank(noLotDel)) {
                q.setString("noLot", noLotDel);
            }

            q.setString("jenispelan", jenisPelanDel);
        }
        listpelanGIS1 = q.list();

        if (noLotDel != null) {
            for (GISPelan gis : listpelanGIS1) {
               permohonanPihakKkiniService.delete(gis);
            }
        } else {
            addSimpleError("SILA MASUKAN NO LOT TERLEBIH DAHULU");
        }

        if (!q.list().isEmpty()) {
//            System.out.print("lalal" + q.uniqueResult());
        }

//        resetValue();
        
         daerah = kodDaerahDel;
        seksyen = kodSeksyenDel;
        mukim = kodMukimDel;
        noLot = noLotDel;

        jenispelan = getContext().getRequest().getParameter("jenispelan");
//        KodBandarPekanMukim bpm = null;
//        KodSeksyen seksyen1 = null;
//        if (StringUtils.isNotBlank(seksyen)) {
//            seksyen1 = kodSeksyenDAO.findById(Integer.parseInt(seksyen));
//
//        }
        if (StringUtils.isNotBlank(mukim)) {
            bpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(mukim));

        }
//        String sql = null;
//        Session s = sessionProvider.get();
//        Query q = null;

        sql = "select pelan from etanah.model.gis.GISPelan pelan  where "
                + "pelan.pelanGISPK.jenisPelan = :jenispelan "
                + "and pelan.pelanGISPK.kodDaerah.kod = :daerah  "
                + "and pelan.pelanGISPK.kodMukim =:mukim "
                + "and pelan.pelanGISPK.kodSeksyen = :seksyen ";

        sql = sql + "and pelan.pelanGISPK.noLot = :noLot";

        q = s.createQuery(sql);
        q.setString("daerah", daerah);
        if (StringUtils.isNotBlank(seksyen)) {
            q.setString("seksyen", seksyen);
        } else {
            q.setString("seksyen", "000");
        }
        q.setString("mukim", mukim);

        q.setString("noLot", noLot);

        q.setString("jenispelan", jenispelan);
        String path = "";
        GISPelan gis = (GISPelan) q.uniqueResult();
//        if (gis.getPelanGISPK().getJenisPelan().equals("B1")) {
//            b1path = conf.getPelanPath() + "B1" + gis.getPelanTif();
//            path = b1path;
//
//        } else {
//            b2path = conf.getPelanPath() + "B2" + gis.getPelanTif();
//            path = b2path;
//        }

//        File file = new File(path);
//        FileInputStream fis = null;
//        if (file.exists()) {
//            fis = new FileInputStream(file);
//        }
        addSimpleMessage("Kemaskini Telah Berjaya");
//        return new ForwardResolution("/WEB-INF/jsp/utiliti/carian_pelan_b1b2.jsp");
        return new JSP("utiliti/carian_pelan_b1b2.jsp");
    }

    public Resolution viewPelan() throws FileNotFoundException {
        daerah = getContext().getRequest().getParameter("daerah");
        seksyen = getContext().getRequest().getParameter("seksyen");
        mukim = getContext().getRequest().getParameter("mukim");
        noLot = getContext().getRequest().getParameter("noLot");

        jenispelan = getContext().getRequest().getParameter("jenispelan");
        KodBandarPekanMukim bpm = null;
        KodSeksyen seksyen1 = null;
//        if (StringUtils.isNotBlank(seksyen)) {
//            seksyen1 = kodSeksyenDAO.findById(Integer.parseInt(seksyen));
//
//        }
        if (StringUtils.isNotBlank(mukim)) {
            bpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(mukim));

        }
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;

        sql = "select pelan from etanah.model.gis.GISPelanNew pelan  where "
                + "pelan.jenisPelan = :jenispelan "
                + "and pelan.kodDaerah.kod = :daerah  "
                + "and pelan.kodMukim =:mukim "
                + "and pelan.kodSeksyen = :seksyen ";

     
            sql = sql + "and pelan.noLot = :noLot";
     


       // sql = sql + "and pelan.pelanGISPK.noLot = :noLot";


        q = s.createQuery(sql);
        q.setString("daerah", daerah);
        if (StringUtils.isNotBlank(seksyen)) {
            q.setString("seksyen", seksyen);
        } else {
            q.setString("seksyen", "000");
        }
        q.setString("mukim", mukim);

        q.setString("noLot", noLot);

        q.setString("jenispelan", jenispelan);
        String path = "";
        GISPelanNew gis = (GISPelanNew) q.uniqueResult();
        if (gis.getJenisPelan().equals("B1")) {
            b1path = conf.getPelanPath() + "B1" + gis.getPelanTif();
            path = b1path;

        } else {
            b2path = conf.getPelanPath() + "B2" + gis.getPelanTif();
            path = b2path;
        }

        File file = new File(path);
        FileInputStream fis = null;
        if (file.exists()) {
            fis = new FileInputStream(file);
        }

        return new StreamingResolution("image/tiff", fis).setFilename(file.getName());
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

    public PelanGISDAO getPelanGISDAO() {
        return pelanGISDAO;
    }

    public void setPelanGISDAO(PelanGISDAO pelanGISDAO) {
        this.pelanGISDAO = pelanGISDAO;
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

    public List<GISPelanNew> getListpelanGIS() {
        return listpelanGIS;
    }

    public void setListpelanGIS(List<GISPelanNew> listpelanGIS) {
        this.listpelanGIS = listpelanGIS;
    }

    public String getB1path() {
        return b1path;
    }

    public void setB1path(String b1path) {
        this.b1path = b1path;
    }

    public String getB2path() {
        return b2path;
    }

    public void setB2path(String b2path) {
        this.b2path = b2path;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public GISPelanDAO getGISPelanDAO() {
        return GISPelanDAO;
    }

    public void setGISPelanDAO(GISPelanDAO GISPelanDAO) {
        this.GISPelanDAO = GISPelanDAO;
    }

    public KodSeksyenDAO getKodSeksyenDAO() {
        return kodSeksyenDAO;
    }

    public void setKodSeksyenDAO(KodSeksyenDAO kodSeksyenDAO) {
        this.kodSeksyenDAO = kodSeksyenDAO;
    }

    public KodBandarPekanMukimDAO getKodBandarPekanMukimDAO() {
        return kodBandarPekanMukimDAO;
    }

    public void setKodBandarPekanMukimDAO(KodBandarPekanMukimDAO kodBandarPekanMukimDAO) {
        this.kodBandarPekanMukimDAO = kodBandarPekanMukimDAO;
    }

    public PermohonanDAO getMohonDao() {
        return mohonDao;
    }

    public void setMohonDao(PermohonanDAO mohonDao) {
        this.mohonDao = mohonDao;
    }

    public String getKodDaerahDel() {
        return kodDaerahDel;
    }

    public void setKodDaerahDel(String kodDaerahDel) {
        this.kodDaerahDel = kodDaerahDel;
    }

    public String getJenisPelanDel() {
        return jenisPelanDel;
    }

    public void setJenisPelanDel(String jenisPelanDel) {
        this.jenisPelanDel = jenisPelanDel;
    }

    public String getKodMukimDel() {
        return kodMukimDel;
    }

    public void setKodMukimDel(String kodMukimDel) {
        this.kodMukimDel = kodMukimDel;
    }

    public String getKodSeksyenDel() {
        return kodSeksyenDel;
    }

    public void setKodSeksyenDel(String kodSeksyenDel) {
        this.kodSeksyenDel = kodSeksyenDel;
    }

    public String getNoLotDel() {
        return noLotDel;
    }

    public void setNoLotDel(String noLotDel) {
        this.noLotDel = noLotDel;
    }

    public List<GISPelan> getListpelanGIS1() {
        return listpelanGIS1;
    }

    public void setListpelanGIS1(List<GISPelan> listpelanGIS1) {
        this.listpelanGIS1 = listpelanGIS1;
    }
    
    

}
