package etanah.view.stripes.ispeks;

import java.text.*;
import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import org.hibernate.*;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.*;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.hasil.KutipanHasilManager;

/**
 * @author haqqiem 07 Jul 2015
 */
@Wizard(startEvents = {"Step1"})
@UrlBinding("/ispeks/gantian_cek")
public class IspeksGantianCekTakLakuActionBean extends AbleActionBean {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(IspeksGantianCekTakLakuActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/ispeks/gantian_cek_tak_laku.jsp";
    private static String kodNegeri;

    private DokumenKewangan dokumenKewangan = new DokumenKewangan();
    private DokumenKewanganBayaran dokumenKewanganBayaran;
    private CaraBayaran caraBayar;
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private List<DokumenKewangan> senaraiResit = new ArrayList<DokumenKewangan>();
    private List<DokumenKewanganBayaran> senaraiDokumenKewangan = new ArrayList<DokumenKewanganBayaran>();

    private boolean flag = false;
    private String kodCaraBayar = "";
    private String noRujukan = "";
    private String cawangan = "";
    private String tarikhCek = "";
    private String bank = "";
    private BigDecimal amaun = new BigDecimal(0.00);

    @Inject
    private etanah.Configuration conf;

    @Inject
    private DokumenKewanganDAO dokumenKewanganDAO;

    @Inject
    private KodCaraBayaranDAO kodCaraBayaranDAO;

    @Inject
    private KodBankDAO kodBankDAO;

    @Inject
    private CaraBayaranDAO caraBayaranDAO;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    private KutipanHasilManager manager;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }

    @HandlesEvent("Step2")
    public Resolution carianResit() {
        flag = true;
        Session s = sessionProvider.get();
        dokumenKewangan = dokumenKewanganDAO.findById(dokumenKewangan.getIdDokumenKewangan());
        if (dokumenKewangan != null) {
            String sql = "SELECT a FROM etanah.model.DokumenKewanganBayaran a "
                    + "WHERE a.dokumenKewangan.idDokumenKewangan = :id";
            Query q = s.createQuery(sql);
            q.setString("id", dokumenKewangan.getIdDokumenKewangan());
            dokumenKewanganBayaran = (DokumenKewanganBayaran) q.uniqueResult();

            caraBayar = dokumenKewanganBayaran.getCaraBayaran();
            if ((caraBayar != null) && ((caraBayar.getKodCaraBayaran().getKod().equals("CB"))
                    || (caraBayar.getKodCaraBayaran().getKod().equals("CL"))
                    || (caraBayar.getKodCaraBayaran().getKod().equals("CS"))
                    || (caraBayar.getKodCaraBayaran().getKod().equals("CT")))) {
                senaraiDokumenKewangan = caraBayar.getSenaraiDokumenKewanganBayaran();
                amaun = caraBayar.getAmaun();
            }
            senaraiDokumenKewangan = caraBayar.getSenaraiDokumenKewanganBayaran();

            for (DokumenKewanganBayaran dkb : senaraiDokumenKewangan) {
                senaraiResit.add(dkb.getDokumenKewangan());
            }
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }

//    public Resolution carianResit() {
//        Session s = sessionProvider.get();
//        if (dokumenKewangan.getIdDokumenKewangan() != null) {
//            LOG.info("===== NO RESIT =====");
//            dokumenKewangan = dokumenKewanganDAO.findById(dokumenKewangan.getIdDokumenKewangan());
//            LOG.info("resit : "+dokumenKewangan.getIdDokumenKewangan());
//            if (dokumenKewangan != null) {
////                senaraiTransaksi = dokumenKewangan.getSenaraiTransaksi();
//                LOG.info("-------- senaraiTransaksi.size() : " + senaraiTransaksi.size());
//
//                String sql = "SELECT a FROM etanah.model.DokumenKewanganBayaran a "
//                        + "WHERE a.dokumenKewangan.idDokumenKewangan = :id";
//                Query q = s.createQuery(sql);
//                q.setString("id", dokumenKewangan.getIdDokumenKewangan());
//                dokumenKewanganBayaran = (DokumenKewanganBayaran) q.uniqueResult();
//
//                caraBayar = dokumenKewanganBayaran.getCaraBayaran();
//                if ((caraBayar != null) && ((caraBayar.getKodCaraBayaran().getKod().equals("CB"))
//                        || (caraBayar.getKodCaraBayaran().getKod().equals("CL"))
//                        || (caraBayar.getKodCaraBayaran().getKod().equals("CS"))
//                        || (caraBayar.getKodCaraBayaran().getKod().equals("CT")))) {
//                    senaraiDokumenKewangan = caraBayar.getSenaraiDokumenKewanganBayaran();
//                    flag = true;
//                }
//            }
//        } else if (caraBayar.getNoRujukan() != null) {
//            LOG.info("===== NO RUJUKAN =====");
//            String sql = "SELECT a FROM etanah.model.CaraBayaran a "
//                    + "WHERE a.noRujukan = :ruj";
//            Query q = s.createQuery(sql);
//            q.setString("ruj", caraBayar.getNoRujukan());
//            caraBayar = (CaraBayaran) q.uniqueResult();
//            if ((caraBayar != null) && ((caraBayar.getKodCaraBayaran().getKod().equals("CB"))
//                    || (caraBayar.getKodCaraBayaran().getKod().equals("CL"))
//                    || (caraBayar.getKodCaraBayaran().getKod().equals("CS"))
//                    || (caraBayar.getKodCaraBayaran().getKod().equals("CT")))) {
//                senaraiDokumenKewangan = caraBayar.getSenaraiDokumenKewanganBayaran();
//                flag = true;
//            }
//        }
//        for (DokumenKewanganBayaran dkb : senaraiDokumenKewangan) {
//           senaraiResit.add(dkb.getDokumenKewangan());
//        }
//        LOG.info("senaraiResit.size() : "+senaraiResit.size());
//
//        return new ForwardResolution(DEFAULT_VIEW);
//    }
    @HandlesEvent("Step3")
    public Resolution save() throws ParseException {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            CaraBayaran cb = new CaraBayaran();
            cb.setCawangan(caw);
            cb.setKodCaraBayaran(kodCaraBayaranDAO.findById(kodCaraBayar));
            cb.setNoRujukan(noRujukan);
            cb.setBank(kodBankDAO.findById(bank));
            cb.setBankCawangan(cawangan);
            cb.setAmaun(amaun);
            cb.setAktif('Y');
            cb.setTarikhCek(sdf.parse(tarikhCek));
            cb.setInfoAudit(ia);

            manager.saveOrUpdate(cb);

            caraBayar = caraBayaranDAO.findById(caraBayar.getIdCaraBayaran());
            senaraiDokumenKewangan = caraBayar.getSenaraiDokumenKewanganBayaran();
            for (DokumenKewanganBayaran dkb : senaraiDokumenKewangan) {
                if (dkb.getDokumenKewangan().getAkaun() != null) {
                    senaraiAkaun.add(dkb.getDokumenKewangan().getAkaun());
                }
                dkb.setCaraBayaran(cb);

                manager.updateDokumenKewanganBayaran(dkb);
            }

            if (senaraiAkaun.size() > 0) {
                for (Akaun ak : senaraiAkaun) {
                    LogAkaunKewangan log = new LogAkaunKewangan();

                    log.setAkaun(ak);
                    log.setPerkara("Gantian Cek");
                    log.setDataBaru(cb.getKodCaraBayaran().getKod() + " - " + cb.getKodCaraBayaran().getNama()
                            + " <br/>" + " No. Rujukan : " + cb.getNoRujukan()
                            + " <br/>" + " Cawangan : " + cb.getBankCawangan()
                            + " <br/>" + " Tarikh Cek : " + sdf.format(cb.getTarikhCek()));
                    log.setDataLama(caraBayar.getKodCaraBayaran().getKod() + " - " + caraBayar.getKodCaraBayaran().getNama()
                            + " <br/>" + " No. Rujukan : " + caraBayar.getNoRujukan()
                            + " <br/>" + " Cawangan : " + caraBayar.getBankCawangan()
                            + " <br/>" + " Tarikh Cek : " + sdf.format(caraBayar.getTarikhCek()));
                    log.setCatatan("Gantian Cek Tak Laku");
                    log.setPengguna(pengguna);
                    log.setTarikhMasuk(now);

                    manager.save(log);
                }
            }

            tx.commit();
            addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            tx.rollback();
            addSimpleError("Pembayaran tidak berjaya.");
        }
        return new RedirectResolution(IspeksGantianCekTakLakuActionBean.class);
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public CaraBayaran getCaraBayar() {
        return caraBayar;
    }

    public void setCaraBayar(CaraBayaran caraBayar) {
        this.caraBayar = caraBayar;
    }

    public static String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public DokumenKewanganBayaran getDokumenKewanganBayaran() {
        return dokumenKewanganBayaran;
    }

    public void setDokumenKewanganBayaran(DokumenKewanganBayaran dokumenKewanganBayaran) {
        this.dokumenKewanganBayaran = dokumenKewanganBayaran;
    }

    public List<DokumenKewangan> getSenaraiResit() {
        return senaraiResit;
    }

    public void setSenaraiResit(List<DokumenKewangan> senaraiResit) {
        this.senaraiResit = senaraiResit;
    }

    public List<DokumenKewanganBayaran> getSenaraiDokumenKewangan() {
        return senaraiDokumenKewangan;
    }

    public void setSenaraiDokumenKewangan(List<DokumenKewanganBayaran> senaraiDokumenKewangan) {
        this.senaraiDokumenKewangan = senaraiDokumenKewangan;
    }

    public String getKodCaraBayar() {
        return kodCaraBayar;
    }

    public void setKodCaraBayar(String kodCaraBayar) {
        this.kodCaraBayar = kodCaraBayar;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getCawangan() {
        return cawangan;
    }

    public void setCawangan(String cawangan) {
        this.cawangan = cawangan;
    }

    public String getTarikhCek() {
        return tarikhCek;
    }

    public void setTarikhCek(String tarikhCek) {
        this.tarikhCek = tarikhCek;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public List<KodCaraBayaran> getSenaraiKodCaraBayaran() {
        try {
            String query = "SELECT u FROM etanah.model.KodCaraBayaran u WHERE u.aktif = 'Y' "
                    + "AND u.kod in ('CB','CL','CS','CT') ORDER BY u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodBank> getSenaraiKodBank() {
        try {
            String query = "SELECT u FROM etanah.model.KodBank u WHERE u.aktif = 'Y' ORDER BY u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }
}
