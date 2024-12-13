package etanah.view.stripes.hasil;

import java.util.List;
import etanah.model.*;
import java.util.ArrayList;
import etanah.dao.hasil.*;
import org.hibernate.Query;
import etanah.model.hasil.*;
import org.hibernate.Session;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import etanah.dao.AkaunDAO;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * 30-March-2011
 * 
 */
@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/kutipan_tanpa_denda")
public class KutipanTanpaDendaActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(KutipanTanpaDendaActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/kutipan_tanpa_denda.jsp";
    private static final String NEXT_VIEW = "/WEB-INF/jsp/hasil/kutipan_tanpa_denda_1.jsp";
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    private String kodNegeri;
    private String namaKumpulan = null;
    private String rbtIdKumpulan;
    private boolean flag = false;
    private int tahun = 0;
    private int min = 0;
    private int max = 0;
    private BigDecimal remisyen = new BigDecimal(0);
    private BigDecimal notis = new BigDecimal(0);
    private BigDecimal cukaiSebenar = new BigDecimal(0);
    private BigDecimal dendaSemasa = new BigDecimal(0);
    private BigDecimal dendaTunggakan = new BigDecimal(0);
    private BigDecimal tunggakan = new BigDecimal(0);
    private TagAkaunAhli tagAkaunAhli;
    private TagAkaun tagAkaun;
    private Akaun akaun = new Akaun();
    private Hakmilik hakmilik = new Hakmilik();
    private List<TagAkaunAhli> senaraiTagAkaunAhli = new ArrayList<TagAkaunAhli>();
    private List<TagAkaun> senaraiTagAkaun = new ArrayList<TagAkaun>();
    @Inject
    private etanah.Configuration conf;
    @Inject
    private TagAkaunDAO tagAkaunDAO;
    @Inject
    private AkaunDAO akaunDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.kodHasilConfig hasil;
    @Inject
    private KutipanHasilManager manager;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        logger.info("..:: Step 1 ::..");
        akaun = new Akaun();
        namaKumpulan = null;

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        } else {
            kodNegeri = "negeriSembilan";
        }
        logger.info("kodNegeri : " + kodNegeri);
        return new ForwardResolution(DEFAULT_VIEW);
    }

    @HandlesEvent("Step2")
    public Resolution searching() {
        logger.info("..:: Step 2 ::..");

        if (akaun.getNoAkaun() != null) {
        logger.info("..:: Step 2 ::..hakmilik");
            searchByHakmilik();
        }
        if (namaKumpulan != null) {
        logger.info("..:: Step 2 ::..kumpulan");
            searchByKumpulan();
        }
        if (hakmilik.getIdHakmilik() != null) {
            logger.info("..:: Step 2 ::..hakmilik");
            String query = "SELECT ak FROM etanah.model.Akaun ak WHERE ak.hakmilik =:hm ";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("hm",  hakmilik.getIdHakmilik());
            akaun = (Akaun) q.uniqueResult();
            searchByHakmilik();
        }
        flag = true;

        return new ForwardResolution(DEFAULT_VIEW);
    }

    public void searchByKumpulan() {
        logger.info("..:: searchByKumpulan ::..");

        String query = "SELECT ta FROM etanah.model.hasil.TagAkaun ta WHERE ta.nama LIKE :namaKumpulan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("namaKumpulan", "%" + namaKumpulan + "%");
        senaraiTagAkaun = q.list();

        logger.info("senaraiTagAkaun.size() : " + senaraiTagAkaun.size());
    }

    public void searchByHakmilik() {
        logger.info("..:: searchByHakmilik ::..");
        senaraiTagAkaun = new ArrayList<TagAkaun>();
        BigDecimal rem = new BigDecimal(0);
        BigDecimal denda = new BigDecimal(0);

        Date now = new Date();
        tahun = Integer.parseInt(yy.format(now));
        min = tahun;

        akaun = akaunDAO.findById(akaun.getNoAkaun());
        hakmilik = akaun.getHakmilik();
        List<Transaksi> trList = akaun.getSemuaTransaksi();
        cukaiSebenar = hakmilik.getCukaiSebenar();

        for (Transaksi tr : trList) {
            if (tr.getDokumenKewangan() == null) {
                if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("notis6A"))) {
                    notis = tr.getAmaun();
                }
                if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("dendaLewat"))) {
                    if (tr.getAkaunKredit() == null) {
                        if (tr.getUntukTahun() == tahun) {
                            dendaSemasa = tr.getAmaun();
                        } else {
                            dendaTunggakan = dendaTunggakan.add(tr.getAmaun());
                        }
                        denda = denda.add(tr.getAmaun());
                    }
                }
                if ((tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyen.remsb"))) || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyen.remts")))
                        || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyen.remri"))) || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyen.remtd")))
                        || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyenTanah"))) || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyenTunggak")))
                        || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyenDenda")))) {
                    rem = rem.add(tr.getAmaun());
                }
                if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTanahTunggakan"))) {
                    tunggakan = tunggakan.add(tr.getAmaun());
                    if (tr.getUntukTahun() < min) {
                        min = tr.getUntukTahun();
                    }
                    max = min;
                    if ((tr.getUntukTahun() > max) && (max != tahun)) {
                        max = tr.getUntukTahun();
                    }
                }
            }
        }
        if (rem.compareTo(new BigDecimal(0)) < 0) {
            remisyen = rem.multiply(new BigDecimal(-1));
        }
        if (rem.compareTo(new BigDecimal(0)) > 0) {
            remisyen = rem;
        }

        logger.info("akaun.getNoAkaun() : " + akaun.getNoAkaun());
    }

    @HandlesEvent("Step3")
    public Resolution selectKumpulan() {
        logger.info("..:: Inside Step 3 ::..");
        logger.info("..:: Inside Step 3 ::.." + rbtIdKumpulan);

        tagAkaun = tagAkaunDAO.findById(rbtIdKumpulan);
        senaraiTagAkaunAhli = tagAkaun.getSenaraiAhli();
        logger.info("senaraiTagAkaunAhli.size() : " + senaraiTagAkaunAhli.size());

        return new ForwardResolution(NEXT_VIEW);
    }

    @HandlesEvent("Step4")
    public Resolution save() {
        logger.info("..:: Inside Step 4 ::..");

        List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        String query = "SELECT ta FROM etanah.model.hasil.TagAkaunAhli ta where ta.tagAkaun.idTag = :namaKumpulan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("namaKumpulan", rbtIdKumpulan);
        senaraiTagAkaunAhli = q.list();

        for (TagAkaunAhli ahli : senaraiTagAkaunAhli) {
            senaraiAkaun.add(ahli.getAkaun());
        }

        int year = Integer.parseInt(yy.format(now));

        for (Akaun akaun : senaraiAkaun) {
            boolean flagDenda = false;
            List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
            senaraiTransaksi = akaun.getSenaraiTransaksiDebit();
            BigDecimal amaun = new BigDecimal(0.00);

            for (Transaksi transaksi : senaraiTransaksi) {
                if ((transaksi.getKodTransaksi().getKod().equals(hasil.getProperty("dendaLewat"))) && (transaksi.getUntukTahun() == year)) {
                    logger.info("idTrans : " + transaksi.getIdTransaksi());
                    amaun = transaksi.getAmaun();
                    manager.deleteTransaksi(transaksi);
                    flagDenda = true;
                }
            }
            if (flagDenda) {
                akaun.setBaki(akaun.getBaki().subtract(amaun));
                akaun.setInfoAudit(ia);
                manager.saveOrUpdate(akaun);
            }
        }

        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new RedirectResolution(KutipanTanpaDendaActionBean.class);
    }

    @HandlesEvent("Step5")
    public Resolution deleteSingle() {
        logger.info("..:: Inside Step 5 ::..");
        Akaun a = akaunDAO.findById(akaun.getNoAkaun());
        
        BigDecimal amaun = new BigDecimal(0.00);
        boolean flagDenda = false;
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        InfoAudit ia = new  InfoAudit();

        List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
        senaraiTransaksi = akaun.getSenaraiTransaksiDebit();
        String query = "SELECT tr FROM etanah.model.Transaksi tr WHERE tr.akaunDebit = :noAkaun ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noAkaun",  a.getNoAkaun());
        senaraiTransaksi = q.list();

        for (Transaksi transaksi : senaraiTransaksi) {
            if ((transaksi.getKodTransaksi().getKod().equals(hasil.getProperty("dendaLewat"))) && (transaksi.getUntukTahun() == year)) {
                logger.info("idTrans : " + transaksi.getIdTransaksi());
                amaun = transaksi.getAmaun();
                manager.deleteTransaksi(transaksi);
                flagDenda = true;
            }
        }
        if (flagDenda) {
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            a.setBaki(a.getBaki().subtract(amaun));
            a.setInfoAudit(ia);
            manager.saveOrUpdate(a);
        }

        addSimpleMessage("Denda bagi ID Hakmilik " + a.getHakmilik().getIdHakmilik() + " telah berjaya dihapuskan.");
        return new RedirectResolution(KutipanTanpaDendaActionBean.class);
    }

    public List<TagAkaunAhli> getSenaraiTagAkaunAhli() {
        return senaraiTagAkaunAhli;
    }

    public void setSenaraiTagAkaunAhli(List<TagAkaunAhli> senaraiTagAkaunAhli) {
        this.senaraiTagAkaunAhli = senaraiTagAkaunAhli;
    }

    public String getNamaKumpulan() {
        return namaKumpulan;
    }

    public void setNamaKumpulan(String namaKumpulan) {
        this.namaKumpulan = namaKumpulan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public TagAkaunAhli getTagAkaunAhli() {
        return tagAkaunAhli;
    }

    public void setTagAkaunAhli(TagAkaunAhli tagAkaunAhli) {
        this.tagAkaunAhli = tagAkaunAhli;
    }

    public TagAkaun getTagAkaun() {
        return tagAkaun;
    }

    public void setTagAkaun(TagAkaun tagAkaun) {
        this.tagAkaun = tagAkaun;
    }

    public List<TagAkaun> getSenaraiTagAkaun() {
        return senaraiTagAkaun;
    }

    public void setSenaraiTagAkaun(List<TagAkaun> senaraiTagAkaun) {
        this.senaraiTagAkaun = senaraiTagAkaun;
    }

    public String getRbtIdKumpulan() {
        return rbtIdKumpulan;
    }

    public void setRbtIdKumpulan(String rbtIdKumpulan) {
        this.rbtIdKumpulan = rbtIdKumpulan;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public BigDecimal getCukaiSebenar() {
        return cukaiSebenar;
    }

    public void setCukaiSebenar(BigDecimal cukaiSebenar) {
        this.cukaiSebenar = cukaiSebenar;
    }

    public BigDecimal getDendaSemasa() {
        return dendaSemasa;
    }

    public void setDendaSemasa(BigDecimal dendaSemasa) {
        this.dendaSemasa = dendaSemasa;
    }

    public BigDecimal getDendaTunggakan() {
        return dendaTunggakan;
    }

    public void setDendaTunggakan(BigDecimal dendaTunggakan) {
        this.dendaTunggakan = dendaTunggakan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public BigDecimal getRemisyen() {
        return remisyen;
    }

    public void setRemisyen(BigDecimal remisyen) {
        this.remisyen = remisyen;
    }

    public BigDecimal getTunggakan() {
        return tunggakan;
    }

    public void setTunggakan(BigDecimal tunggakan) {
        this.tunggakan = tunggakan;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
