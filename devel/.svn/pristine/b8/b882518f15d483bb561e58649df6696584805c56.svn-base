package etanah.view.stripes.hasil;

import java.util.List;
import etanah.model.*;
import java.util.ArrayList;
import org.hibernate.Query;
import java.math.BigDecimal;
import org.hibernate.Session;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import etanah.dao.*;
import etanah.view.etanahActionBeanContext;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import net.sourceforge.stripes.action.Wizard;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author abdul.hakim
 * 17-April-2011
 *
 */
@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/pindahan_deposit")
public class PindahanAkaunDepositActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(PindahanAkaunDepositActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/pindahan_akaun_deposit.jsp";

    private String noRujukan;
    private String daerah;
    private String noAkaun;
    private boolean flag = false;
    private BigDecimal baki = new BigDecimal(0.00);
    private String kodNegeri;

    private Akaun akaun = new Akaun();
    private Transaksi transaksi;

    private List<BigDecimal> amaunDebit = new ArrayList<BigDecimal>();
    private List<BigDecimal> amaunKredit = new ArrayList<BigDecimal>();
    private List<String> chkbox = new ArrayList<String>();
    private List<KodTransaksi> senaraiKodTransaksi = new ArrayList<KodTransaksi>();
    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject GenerateIdPermohonanWorkflow gipw;

    @Inject
    private etanah.Configuration conf;

    @Inject
    KodUrusanDAO kodUrusanDAO;

    @Inject
    AkaunDAO akaunDAO;

    @Inject
    PermohonanDAO permohonanDAO;

    @Inject
    KutipanHasilManager manager;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        logger.info("Step1");
        if ("04".equals(conf.getProperty("kodNegeri"))) {kodNegeri = "melaka";}
        else{kodNegeri = "negeriSembilan";}

        return new ForwardResolution(DEFAULT_VIEW);
    }

    @HandlesEvent("Step2")
    public Resolution searching() {
        logger.info(".:: Step 2 ::.");

        searchingAkaun(getContext().getRequest().getParameterMap());

        flag = true;
        logger.info("senaraiAkaun.size() : "+senaraiAkaun.size());
        for (Akaun ak : senaraiAkaun) {
            ak.setBaki(ak.getBaki().multiply(new BigDecimal(-1)));
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public void searchingAkaun(Map<String, String[]> param){
        logger.info("searchingAkaun");
        Session s = sessionProvider.get();
        String query = "SELECT a FROM etanah.model.Akaun a WHERE a.kodAkaun.kod = 'AD' AND a.status='A'";

        if (isNotBlank(param.get("noAkaun"))) {
            logger.info("noAkaun " +noAkaun);
            query += "AND a.noAkaun LIKE :noAkaun ";
        }

        if (isNotBlank(param.get("daerah"))) {
            logger.info("daerah " +daerah);
            query += "AND a.cawangan.kod = :caw ";
        }

        Query q = s.createQuery(query);

        if (isNotBlank(param.get("noAkaun"))) {
            logger.info("noAkaun " +noAkaun);
            q.setString("noAkaun","%"+ param.get("noAkaun")[0]+"%");
        }

        if (isNotBlank(param.get("daerah"))) {
            logger.info("daerah " +daerah);
            q.setString("caw", param.get("daerah")[0]);
        }

        senaraiAkaun = q.list();
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

    @HandlesEvent("Step3")
    public Resolution save(){
        logger.info("STEP3");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        KodUrusan ku = kodUrusanDAO.findById("BBD");
        String id = gipw.generateIdPermohonanBayaranDeposit(ku, p);
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        logger.info("---------------------------"+id);
        if(!id.equalsIgnoreCase("false")){
            addSimpleMessage("Permohonan telah berjaya diwujudkan.");
            for (String s : chkbox) {
                if(s!=null){
                    logger.info("noAkaun : "+s);
                    Akaun a = akaunDAO.findById(s);
                        a.setPermohonan(permohonanDAO.findById(id));
                            ia.setDimasukOleh(a.getInfoAudit().getDimasukOleh());
                            ia.setTarikhMasuk(a.getInfoAudit().getTarikhMasuk());
                            ia.setDiKemaskiniOleh(p);
                            ia.setTarikhKemaskini(now);
                        a.setInfoAudit(ia);

                    manager.saveOrUpdate(a);
                }
            }
        }
        
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<BigDecimal> getAmaunDebit() {
        return amaunDebit;
    }

    public void setAmaunDebit(List<BigDecimal> amaunDebit) {
        this.amaunDebit = amaunDebit;
    }

    public List<BigDecimal> getAmaunKredit() {
        return amaunKredit;
    }

    public void setAmaunKredit(List<BigDecimal> amaunKredit) {
        this.amaunKredit = amaunKredit;
    }

    public List<KodTransaksi> getSenaraiKodTransaksi() {
        return senaraiKodTransaksi;
    }

    public void setSenaraiKodTransaksi(List<KodTransaksi> senaraiKodTransaksi) {
        this.senaraiKodTransaksi = senaraiKodTransaksi;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public List<String> getChkbox() {
        return chkbox;
    }

    public void setChkbox(List<String> chkbox) {
        this.chkbox = chkbox;
    }
}
