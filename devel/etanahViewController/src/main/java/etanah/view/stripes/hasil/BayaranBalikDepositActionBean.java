package etanah.view.stripes.hasil;

import java.util.List;
import etanah.dao.*;
import java.util.Map;
import etanah.model.*;
import java.util.ArrayList;
import org.hibernate.Query;
import java.math.BigDecimal;
import org.hibernate.Session;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.Wizard;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author abdul.hakim
 */
@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/bayaran_balik_deposit")
public class BayaranBalikDepositActionBean extends AbleActionBean {
    private static final Logger logger = Logger.getLogger(BayaranBalikDepositActionBean.class);

    private KodAkaun kodAkaun;
    private Akaun akaun;

    private AkaunDAO akaunDAO;

    private String rbtn;
    private String jenisAkaun;
    private String noAkaun;
    private String nama;
    private String batchNo;
    private BigDecimal amaun = new BigDecimal(0.00);
    private String noRujukan;
    private String active;
    private boolean flag = false;
    private List<KodAkaun> senaraiKodAkaun = new ArrayList<KodAkaun>();
    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();

    @Inject
    public BayaranBalikDepositActionBean(AkaunDAO akaunDAO) {
        this.akaunDAO = akaunDAO;
    }

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        logger.info("Step1");
        String sql = "SELECT ka FROM KodAkaun ka where ka.aktif = 'Y' AND ka.kategoriAkaun.kod = 'AA' order by ka.kod";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);

        senaraiKodAkaun = q.list();
        logger.info("senaraiKodAkaun.size() : "+senaraiKodAkaun.size());
        return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran_balik.jsp");
    }

    @HandlesEvent("Step2")
    public Resolution search() {
        logger.info("Step2");
        flag = true;
        senaraiAkaun = searchingAkaun(getContext().getRequest().getParameterMap());
        logger.info("senaraiAkaun.size() : "+senaraiAkaun.size());

        showForm();
        return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran_balik.jsp");
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

    public List<Akaun> searchingAkaun(Map<String, String[]> param) {
        String query = "SELECT a FROM etanah.model.Akaun a WHERE a.status.kod = 'A' AND a.kodAkaun.kategoriAkaun.kod = 'AA' ";

        if (isNotBlank(param.get("jenisAkaun"))) {
            query += "AND a.kodAkaun = :kodAkaun ";
        }

        if (isNotBlank(param.get("noAkaun"))) {
            query += "AND a.noAkaun = :noAkaun ";
        }

        if (isNotBlank(param.get("nama"))) {
            query += "AND a.pemegang.nama = :nama ";
        }

        if (isNotBlank(param.get("batchNo"))) {
            query += "AND a.kumpulan.idKumpulan = :batch ";
        }

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("jenisAkaun"))) {
            q.setString("kodAkaun", param.get("jenisAkaun")[0]);
        }

        if (isNotBlank(param.get("noAkaun"))) {
            q.setString("noAkaun", param.get("noAkaun")[0]);
        }

        if (isNotBlank(param.get("nama"))) {
            q.setString("nama", param.get("nama")[0]);
        }

        if (isNotBlank(param.get("batchNo"))) {
            q.setString("batch", param.get("batchNo")[0]);
        }

        List<Akaun> akaunList = q.list();
        return akaunList;
    }

    @HandlesEvent("Step3")
    public Resolution details() {
        logger.info("Step3");
        logger.info("radio button : "+rbtn);

        akaun = akaunDAO.findById(rbtn);
        logger.info("akaun.getNoAkaun() : "+akaun.getNoAkaun());

        return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran_balik_1.jsp");
    }

    @HandlesEvent("Step4")
    public Resolution main() {
            return new RedirectResolution(BayaranBalikDepositActionBean.class);
    }

    @HandlesEvent("Step5")
    public Resolution simpan() {
            return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran_balik_1.jsp");
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public KodAkaun getKodAkaun() {
        return kodAkaun;
    }

    public void setKodAkaun(KodAkaun kodAkaun) {
        this.kodAkaun = kodAkaun;
    }

    public List<KodAkaun> getSenaraiKodAkaun() {
        return senaraiKodAkaun;
    }

    public void setSenaraiKodAkaun(List<KodAkaun> senaraiKodAkaun) {
        this.senaraiKodAkaun = senaraiKodAkaun;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public String getJenisAkaun() {
        return jenisAkaun;
    }

    public void setJenisAkaun(String jenisAkaun) {
        this.jenisAkaun = jenisAkaun;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public String getRbtn() {
        return rbtn;
    }

    public void setRbtn(String rbtn) {
        this.rbtn = rbtn;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }
}
