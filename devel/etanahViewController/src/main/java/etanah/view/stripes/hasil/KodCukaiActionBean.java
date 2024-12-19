package etanah.view.stripes.hasil;

import java.util.*;
import etanah.dao.*;
import org.hibernate.*;
import etanah.model.*;
import able.stripes.JSP;
import etanah.view.ListUtil;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.*;

/**
 *
 * @author haqqiem
 */
@Wizard(startEvents = {"Step1", "editKadar", "getPerihalSyaratNyata", "addKadar", "filterByBpm"})
@UrlBinding("/hasil/kod_cukai")
public class KodCukaiActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KodCukaiActionBean.class);
    private static final String TEST_PAGE = "/WEB-INF/jsp/hasil/kod_cukai.jsp";
    private static final String POPUP_PAGE = "/WEB-INF/jsp/hasil/kod_cukai_1.jsp";

    private Pengguna pengguna = new Pengguna();
    private KodKadarCukai kodKadarCukai = new KodKadarCukai();

    private List<KodKadarCukai> senaraiKadarCukai = new ArrayList<KodKadarCukai>();
    private List<KodKegunaanTanah> senaraiGunaTanah = new ArrayList<KodKegunaanTanah>();
    private List<KodBandarPekanMukim> senaraiBpm = new ArrayList<KodBandarPekanMukim>();
    private List<KodSyaratNyata> senaraiSyarat = new ArrayList<KodSyaratNyata>();

    private Character status = null;
    private String kodGunaTanah = null;
    private String kodBpm = null;
    private String gunaTanahBaru = null;
    private String gunaSyaratBaru = null;
    private String bpmBaru = null;
    private String kodCaw = null;
    private String kodNegeri;
    private String flag = null;

    @Inject
    private etanah.Configuration conf;

    @Inject
    private ListUtil listUtil;

    @Inject
    private KodSyaratNyataDAO kodSyaratNyataDAO;

    @Inject
    private KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    
    @Inject
    private KodKegunaanTanahDAO kodKegunaanTanahDAO;
    
    @Inject
    private KutipanHasilManager manager;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        } else {
            kodNegeri = "negeri";
        }
        return new ForwardResolution(TEST_PAGE);
    }

    @HandlesEvent("Step2")
    public Resolution search() {
        LOG.info("Step2");
        senaraiKadarCukai = searchingKodKadar(getContext().getRequest().getParameterMap());
        LOG.info("senaraiKadarCukai.size() : " + senaraiKadarCukai.size());

        showForm();
        return new ForwardResolution("/WEB-INF/jsp/hasil/kod_cukai.jsp");
    }

    public List<KodKadarCukai> searchingKodKadar(Map<String, String[]> param) {
        String query = "SELECT a FROM etanah.model.KodKadarCukai a WHERE a.kod IS NOT NULL ";

        if (isNotBlank(param.get("kodBpm"))) {
            query += "AND a.bandarPekanMukim.kod = :kodBpm ";
        }

        if (isNotBlank(param.get("kodGunaTanah"))) {
            query += "AND a.kegunaanTanah.kod = :kodGunaTanah ";
        }

        query += "ORDER BY a.kod ASC";

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("kodBpm"))) {
            q.setString("kodBpm", param.get("kodBpm")[0]);
        }

        if (isNotBlank(param.get("kodGunaTanah"))) {
            q.setString("kodGunaTanah", param.get("kodGunaTanah")[0]);
        }

        List<KodKadarCukai> list = q.list();
        return list;
    }

    public static boolean isNotBlank(String[] str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String[] str) {
        if (str == null) {
            return true;
        }
        if (str.length > 0) {
            return isBlank(str[0]);
        }
        return true;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public Resolution editKadar() {
        LOG.info("------------------------------- INSIDE editKadar() -------------------------------");
        flag = "edit";
        String kod = getContext().getRequest().getParameter("kod");
        LOG.info("\n Kod : " + kod);

        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT u FROM etanah.model.KodKadarCukai u where u.kod = :kod");
        q.setString("kod", kod);

        kodKadarCukai = (KodKadarCukai) q.uniqueResult();
        bpmBaru = kodKadarCukai.getBandarPekanMukim().getKod() + "";
        gunaTanahBaru = kodKadarCukai.getKegunaanTanah().getKod();

        senaraiBpm = listUtil.getSenaraiKodBandarMukim();
        senaraiGunaTanah = listUtil.getSenaraiKegunaanTanah();
        status = kodKadarCukai.getAktif();
        kodCaw = kodKadarCukai.getBandarPekanMukim().getCawangan().getKod();
        if (kodKadarCukai.getSyaratNyata() != null) {
            gunaSyaratBaru = kodKadarCukai.getSyaratNyata().getKod();
        }

        Query qSyarat = s.createQuery("SELECT ks FROM etanah.model.KodSyaratNyata ks where ks.cawangan.kod = :kod");
        qSyarat.setString("kod", kodCaw);
        senaraiSyarat = qSyarat.list();

        LOG.info("\n" + bpmBaru + "\n" + gunaTanahBaru + "\n" + senaraiSyarat.size());
        return new JSP("hasil/kod_cukai_1.jsp").addParameter("popup", "true");
    }

    public Resolution addKadar() {
        LOG.info("------------------------------- INSIDE addKadar() -------------------------------");
        flag = "add";
        senaraiBpm = listUtil.getSenaraiKodBandarMukim();
        senaraiGunaTanah = listUtil.getSenaraiKegunaanTanah();

        LOG.info("\n" + bpmBaru + "\n" + gunaTanahBaru + "\n" + senaraiSyarat.size());
        return new JSP("hasil/kod_cukai_1.jsp").addParameter("popup", "true");
    }

    public Resolution filterByBpm() {
        flag = "add";
        String bpm = getContext().getRequest().getParameter("kod");
        KodBandarPekanMukim kodbandar = kodBandarPekanMukimDAO.findById(Integer.parseInt(bpm));

        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        sql = "SELECT ku from KodSyaratNyata ku where ku.cawangan.kod = :kod";
        q = s.createQuery(sql);
        q.setString("kod", kodbandar.getCawangan().getKod());
        senaraiSyarat = q.list();

        bpmBaru = bpm;
        senaraiBpm = listUtil.getSenaraiKodBandarMukim();
        senaraiGunaTanah = listUtil.getSenaraiKegunaanTanah();

        return new ForwardResolution(POPUP_PAGE).addParameter("popup", "true");
    }

    public Resolution getPerihalSyaratNyata() {
        LOG.info("*****getPerihalSyaratNyata:" + getContext().getRequest().getParameter("kod"));
        String results = "0";
        String kod = getContext().getRequest().getParameter("kod");
        KodSyaratNyata kd = kodSyaratNyataDAO.findById(kod);
        if (kd != null) {
            results = kd.getSyarat();
        }
        LOG.debug("results : " + results);
        return new StreamingResolution("text/plain", results);
    }

    @HandlesEvent("Step3")
    public Resolution save() {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Session s = sessionProvider.get();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        pengguna =ctx.getUser();
        
        Query q = s.createQuery("SELECT kc FROM etanah.model.KodKadarCukai kc ORDER BY kc.kod DESC");
        List<KodKadarCukai> list = q.list();
        KodKadarCukai kc = list.get(0);
        
        LOG.info("\n KOD : "+(kc.getKod()+1)+"\n KOD_BPM : "+bpmBaru+"\n KOD_GUNA_TANAH : "+gunaTanahBaru+"\n KOD_SYARAT_NYATA : "+gunaSyaratBaru+
                    "\n KADAR_MP : "+kodKadarCukai.getKadarMeterPersegi()+"\n MINIMA : "+kodKadarCukai.getKadarMinimum()+"\n AKTIF : "+status);
        
        KodBandarPekanMukim bpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(bpmBaru));
        KodKegunaanTanah kkt = kodKegunaanTanahDAO.findById(gunaTanahBaru);
        KodSyaratNyata ksn = new KodSyaratNyata();
        if(gunaSyaratBaru != null)
            ksn = kodSyaratNyataDAO.findById(gunaSyaratBaru);
        
        kodKadarCukai.setKod(kc.getKod()+1);
        kodKadarCukai.setBandarPekanMukim(bpm);
        kodKadarCukai.setKegunaanTanah(kkt);
        if(gunaSyaratBaru != null)
            kodKadarCukai.setSyaratNyata(ksn);
        kodKadarCukai.setKadarMeterPersegi(kodKadarCukai.getKadarMeterPersegi());
        kodKadarCukai.setKadarMinimum(kodKadarCukai.getKadarMinimum());
        kodKadarCukai.setAktif(status);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
        kodKadarCukai.setInfoAudit(ia);
        
        bpmBaru = kodKadarCukai.getBandarPekanMukim().getKod() + "";
        gunaTanahBaru = kodKadarCukai.getKegunaanTanah().getKod();

        senaraiBpm = listUtil.getSenaraiKodBandarMukim();
        senaraiGunaTanah = listUtil.getSenaraiKegunaanTanah();
        
        LOG.info("----- "+kodKadarCukai.getKod());
        manager.save(kodKadarCukai);
        
        Query qSyarat = s.createQuery("SELECT ks FROM etanah.model.KodSyaratNyata ks where ks.cawangan.kod = :kod");
        qSyarat.setString("kod", kodKadarCukai.getBandarPekanMukim().getCawangan().getKod());
        senaraiSyarat = qSyarat.list();
        
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        return new ForwardResolution(POPUP_PAGE).addParameter("popup", "true");
    }

    @HandlesEvent("Step4")
    public Resolution update() {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Session s = sessionProvider.get();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        pengguna =ctx.getUser();
        flag = "edit";
               
        LOG.info("\n KOD : "+(kodKadarCukai.getKod()+1)+"\n KOD_BPM : "+bpmBaru+"\n KOD_GUNA_TANAH : "+gunaTanahBaru+"\n KOD_SYARAT_NYATA : "+gunaSyaratBaru+
                    "\n KADAR_MP : "+kodKadarCukai.getKadarMeterPersegi()+"\n MINIMA : "+kodKadarCukai.getKadarMinimum()+"\n AKTIF : "+status);
        
        KodBandarPekanMukim bpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(bpmBaru));
        KodKegunaanTanah kkt = kodKegunaanTanahDAO.findById(gunaTanahBaru);
        KodSyaratNyata ksn = new KodSyaratNyata();
        if(gunaSyaratBaru != null)
            ksn = kodSyaratNyataDAO.findById(gunaSyaratBaru);
        
//        kodKadarCukai.setKod(kc.getKod()+1);
        kodKadarCukai.setBandarPekanMukim(bpm);
        kodKadarCukai.setKegunaanTanah(kkt);
        if(gunaSyaratBaru != null)
            kodKadarCukai.setSyaratNyata(ksn);
        kodKadarCukai.setKadarMeterPersegi(kodKadarCukai.getKadarMeterPersegi());
        kodKadarCukai.setKadarMinimum(kodKadarCukai.getKadarMinimum());
        kodKadarCukai.setAktif(status);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
        kodKadarCukai.setInfoAudit(ia);
        
        LOG.info("----- "+kodKadarCukai.getKod());
        manager.saveOrUpdate(kodKadarCukai);
        
        Query qSyarat = s.createQuery("SELECT ks FROM etanah.model.KodSyaratNyata ks where ks.cawangan.kod = :kod");
        qSyarat.setString("kod", kodKadarCukai.getBandarPekanMukim().getCawangan().getKod());
        senaraiSyarat = qSyarat.list();
        
        addSimpleMessage("Maklumat Telah Berjaya Dikemaskini.");
        return new ForwardResolution(POPUP_PAGE).addParameter("popup", "true");
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getKodGunaTanah() {
        return kodGunaTanah;
    }

    public void setKodGunaTanah(String kodGunaTanah) {
        this.kodGunaTanah = kodGunaTanah;
    }

    public String getKodBpm() {
        return kodBpm;
    }

    public void setKodBpm(String kodBpm) {
        this.kodBpm = kodBpm;
    }

    public List<KodKadarCukai> getSenaraiKadarCukai() {
        return senaraiKadarCukai;
    }

    public void setSenaraiKadarCukai(List<KodKadarCukai> senaraiKadarCukai) {
        this.senaraiKadarCukai = senaraiKadarCukai;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public KodKadarCukai getKodKadarCukai() {
        return kodKadarCukai;
    }

    public void setKodKadarCukai(KodKadarCukai kodKadarCukai) {
        this.kodKadarCukai = kodKadarCukai;
    }

    public String getGunaTanahBaru() {
        return gunaTanahBaru;
    }

    public void setGunaTanahBaru(String gunaTanahBaru) {
        this.gunaTanahBaru = gunaTanahBaru;
    }

    public String getBpmBaru() {
        return bpmBaru;
    }

    public void setBpmBaru(String bpmBaru) {
        this.bpmBaru = bpmBaru;
    }

    public List<KodKegunaanTanah> getSenaraiGunaTanah() {
        return senaraiGunaTanah;
    }

    public void setSenaraiGunaTanah(List<KodKegunaanTanah> senaraiGunaTanah) {
        this.senaraiGunaTanah = senaraiGunaTanah;
    }

    public List<KodBandarPekanMukim> getSenaraiBpm() {
        return senaraiBpm;
    }

    public void setSenaraiBpm(List<KodBandarPekanMukim> senaraiBpm) {
        this.senaraiBpm = senaraiBpm;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public String getGunaSyaratBaru() {
        return gunaSyaratBaru;
    }

    public void setGunaSyaratBaru(String gunaSyaratBaru) {
        this.gunaSyaratBaru = gunaSyaratBaru;
    }

    public List<KodSyaratNyata> getSenaraiSyarat() {
        return senaraiSyarat;
    }

    public void setSenaraiSyarat(List<KodSyaratNyata> senaraiSyarat) {
        this.senaraiSyarat = senaraiSyarat;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
