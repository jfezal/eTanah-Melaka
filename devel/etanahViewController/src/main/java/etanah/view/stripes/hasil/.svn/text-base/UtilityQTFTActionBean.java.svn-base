package etanah.view.stripes.hasil;

import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import org.hibernate.*;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.*;

/**
 * @author haqqiem 25 March 2015 10:57:43 AM
 */
@Wizard(startEvents = {"Step1", "doCheckHakmilik", "doCheckTunggakan"})
@UrlBinding("/hasil/qtft")
public class UtilityQTFTActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(UtilityQTFTActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/utility_qtft.jsp";

    private static String kodNegeri;
    private String rbtAkaun;
    private Hakmilik hakmilik = new Hakmilik();
    private Akaun akaun = new Akaun();
    private HakmilikSebelum hakmilikSebelum = new HakmilikSebelum();
    private HakmilikAsal hakmilikAsal = new HakmilikAsal();

    private List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();

    @Inject
    private etanah.Configuration conf;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private HakmilikSebelumDAO hakmilikSebelumDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    protected AkaunDAO akaunDAO;
    @Inject
    KutipanHasilManager manager;
    @Inject
    protected KodTransaksiDAO kodTransaksiDAO;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution doCheckHakmilik() {
        LOG.info("*****ID HAKMILIK :" + getContext().getRequest().getParameter("idHakmilik"));
        String results = "0";
        LOG.info("akaun");
        Hakmilik h = hakmilikDAO.findById((getContext().getRequest().getParameter("idHakmilik")).trim());
        if (h != null) {
            if (!h.getKodStatusHakmilik().getKod().equals("B")) {
                results = "1";
            } else {
                Session s = sessionProvider.get();
                String sql = "SELECT u FROM etanah.model.HakmilikSebelum u "
                        + "WHERE u.hakmilikSebelum = :id";
                Query q = s.createQuery(sql);
                q.setString("id", (getContext().getRequest().getParameter("idHakmilik")).trim());
                List<HakmilikSebelum> hs = q.list();
                if (hs.isEmpty()) {
                    results = "3";
                } else {
                    results = "4";

                }
            }
        }
        LOG.debug("results : " + results);
        return new StreamingResolution("text/plain", results);
    }

    @HandlesEvent("Step2")
    public Resolution carianHakmilik() {
        Session s = sessionProvider.get();
        String sql = "SELECT u FROM etanah.model.HakmilikSebelum u WHERE u.hakmilikSebelum = :id";
        Query q = s.createQuery(sql);
        q.setString("id", hakmilikSebelum.getHakmilikSebelum());
        List<HakmilikSebelum> hs = q.list();
        if (hs.size() > 0) {
            for (HakmilikSebelum hm : hs) {
                senaraiAkaun.addAll(hm.getHakmilik().getSenaraiAkaun());
            }
            akaun = hakmilikDAO.findById(hakmilikSebelum.getHakmilikSebelum()).getAkaunCukai();
        } else {
            sql = "SELECT u FROM etanah.model.HakmilikAsal u WHERE u.hakmilikAsal = :id";
            q = s.createQuery(sql);
            q.setString("id", hakmilikSebelum.getHakmilikSebelum());
            List<HakmilikAsal> ha = q.list();
            if (ha.size() > 0) {
                for (HakmilikAsal hm : ha) {
                    senaraiAkaun.addAll(hm.getHakmilik().getSenaraiAkaun());
                }
                akaun = hakmilikDAO.findById(hakmilikSebelum.getHakmilikSebelum()).getAkaunCukai();
            }
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }

    @HandlesEvent("Step3")
    public Resolution process() {
        LOG.info("rbtAkaun : " + rbtAkaun);
        LOG.info("hakmilikSebelum.getHakmilikSebelum() : " + hakmilikSebelum.getHakmilikSebelum());
        Akaun a = akaunDAO.findById(rbtAkaun);
        senaraiHakmilik.add(hakmilikDAO.findById(hakmilikSebelum.getHakmilikSebelum()));

        if (processTunggakan(senaraiHakmilik, a, "")) {
            addSimpleMessage("Process telah berjaya. Sila ke menu Pertanyaan Hakmilik untuk semakan transaksi.");
        } else {
            addSimpleError("Process tidak berjaya. Sila hubungi pentadbir sistem untuk maklumat lanjut.");
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public boolean processTunggakan(List<Hakmilik> list, Akaun akaunBaru, String table) {
        boolean noError = true;
        Transaksi trans = new Transaksi();
        Akaun akaunSblm;
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        Date now = new Date();

        for (Hakmilik hm : list) {
            akaunSblm = new Akaun();
            BigDecimal balance = new BigDecimal(0.0);

            //find noAkaun for qt
            String sql = "SELECT u FROM etanah.model.Akaun u WHERE u.kodAkaun.kod='AC' "
                    + "AND u.status.kod='B' AND u.hakmilik.idHakmilik = :id";
            Query q = s.createQuery(sql);
            q.setString("id", hm.getIdHakmilik());
            akaunSblm = (Akaun) q.uniqueResult();
//            LOG.info("akaun lama : " + akaunSblm.getNoAkaun());

            //find noAkaun for ft
            if (akaunBaru == null) {
                if (table.equals("sblm")) {
                    String sqlBaru = "SELECT ka.noAkaun FROM etanah.model.HakmilikSebelum h, etanah.model.Akaun ka "
                            + "WHERE h.hakmilik.idHakmilik = ka.hakmilik.idHakmilik "
                            + "AND ka.kodAkaun.kod = 'AC' AND ka.status.kod='A' "
                            + "AND h.hakmilikSebelum = :hs ";
                    Query qBaru = s.createQuery(sqlBaru);
                    qBaru.setString("hs", hm.getIdHakmilik());
                    akaunBaru = akaunDAO.findById((String) qBaru.uniqueResult());
                }

                if (table.equals("asal")) {
                    String sqlBaru = "SELECT ka.noAkaun FROM etanah.model.HakmilikAsal h, etanah.model.Akaun ka "
                            + "WHERE h.hakmilik.idHakmilik = ka.hakmilik.idHakmilik "
                            + "AND ka.kodAkaun.kod = 'AC' AND ka.status.kod='A' "
                            + "AND h.hakmilikAsal = :hs ";
                    Query qBaru = s.createQuery(sqlBaru);
                    qBaru.setString("hs", hm.getIdHakmilik());
                    if (qBaru.uniqueResult() != null) {
                        akaunBaru = akaunDAO.findById((String) qBaru.uniqueResult());
                    }
                }
            }
            if (akaunBaru != null) {
                LOG.info("akaun baru : " + akaunBaru.getNoAkaun());
            }

            List<Transaksi> senaraiTransaksiLama = akaunSblm.getSemuaTransaksi();
            LOG.info("senaraiTransaksiLama.size() : " + senaraiTransaksiLama.size());
            tx = s.beginTransaction();
            try {
                for (Transaksi tr : senaraiTransaksiLama) {
//                    if (tr.getUntukTahun() != (now.getYear() + 1900)) {
                    trans = new Transaksi();
                    LOG.info(tr.getIdTransaksi());

                    trans.setCawangan(akaunBaru.getCawangan());
//                        if (tr.getKodTransaksi().getKod().equals("61401")) {
//                            trans.setKodTransaksi(kodTransaksiDAO.findById("61402"));
//                        } else {
                    trans.setKodTransaksi(tr.getKodTransaksi());
//                        }
                    trans.setUntukTahun(tr.getUntukTahun());
                    trans.setKuantiti(0);
                    trans.setAmaun(tr.getAmaun());
                    trans.setStatus(tr.getStatus());
                    trans.setKodBatal(tr.getKodBatal());
                    trans.setInfoAudit(tr.getInfoAudit());
                    trans.setPerihal(tr.getPerihal());
                    trans.setTahunKewangan(tr.getTahunKewangan());
                    trans.setBayaranAgensi("N");
                    if ((tr.getAkaunDebit() != null) && (tr.getAkaunDebit() == akaunSblm)) {
                        trans.setAkaunDebit(akaunBaru);
                        balance = balance.add(tr.getAmaun());
                    }
                    if ((tr.getAkaunKredit() != null) && (tr.getAkaunKredit() == akaunSblm)) {
                        trans.setAkaunKredit(akaunBaru);
                        trans.setAkaunDebit(tr.getAkaunDebit());
                        balance = balance.subtract(tr.getAmaun());
                    }
//                    }
                    manager.save(trans);
                }
                LOG.info("BALANCE : " + balance);
                akaunBaru.setBaki(akaunBaru.getBaki().add(balance));
                manager.saveOrUpdate(akaunBaru);

                tx.commit();
            } catch (Exception ex) {
                LOG.error(ex.getMessage(), ex);
                tx.rollback();
                return false;
            }
        }
        return noError;
    }

    public Resolution doCheckTunggakan() {
        LOG.info("*****NO AKAUN :" + getContext().getRequest().getParameter("noAkaun"));
        Akaun a = akaunDAO.findById(getContext().getRequest().getParameter("noAkaun"));
        String results = "0";
        int year = new Date().getYear() + 1900;
        LOG.info("year : " + year);

        for (Transaksi t : a.getSemuaTransaksi()) {
            if (t.getUntukTahun() < year) {
                results = "1";
            }
        }
        LOG.debug("results : " + results);
        return new StreamingResolution("text/plain", results);
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikSebelum getHakmilikSebelum() {
        return hakmilikSebelum;
    }

    public void setHakmilikSebelum(HakmilikSebelum hakmilikSebelum) {
        this.hakmilikSebelum = hakmilikSebelum;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public String getRbtAkaun() {
        return rbtAkaun;
    }

    public void setRbtAkaun(String rbtAkaun) {
        this.rbtAkaun = rbtAkaun;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public HakmilikAsal getHakmilikAsal() {
        return hakmilikAsal;
    }

    public void setHakmilikAsal(HakmilikAsal hakmilikAsal) {
        this.hakmilikAsal = hakmilikAsal;
    }
}
