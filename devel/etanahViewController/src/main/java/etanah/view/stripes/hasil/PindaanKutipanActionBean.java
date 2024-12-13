package etanah.view.stripes.hasil;

import java.io.*;
import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import able.stripes.*;
import java.math.BigDecimal;
import org.hibernate.Session;
import com.google.inject.Inject;
import etanah.kodHasilConfig;
import etanah.report.ReportUtil;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.*;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.validation.*;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Transaction;

@Wizard(startEvents = {"showForm", "doCheckHakmilik", "cari", "refreshpage", "doCheckBalance1", "getAmaun", "getAkaunBaru", "doCheckReceipt"})
@UrlBinding("/hasil/pindaan_kutipan")
public class PindaanKutipanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PindaanKutipanActionBean.class);
    private Akaun akaun;
    private Transaksi transaksi;
    private DokumenKewangan dokumenKewangan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private Permohonan permohonan;
    private Pengguna pengguna = new Pengguna();
    private AkaunDAO akaunDAO;
    private TransaksiDAO transaksiDAO;
    private HakmilikDAO hakmilikDAO;
    private DokumenKewanganDAO dokumenKewanganDAO;
    private KodAkaunDAO kodAkaunDAO;
    private PermohonanDAO permohonanDAO;
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private List<Akaun> accList;
    private List<Akaun> alist;
    private List<Transaksi> transaksiList;
    private List<Transaksi> transList;
    private List<Hakmilik> hakmilikList;
    private List<DokumenKewangan> resitList = new ArrayList<DokumenKewangan>();
    private List<DokumenKewangan> senarai = new ArrayList<DokumenKewangan>();
    private String accNo = "";
    private String accBaru;
    private String radio;
    private String radioBtn;
    private String idHakmilik;
    private String note;
    private String carian;
    private String idKewDok = "";
    private String noAk = null;
    private String resitManual = null;
    private BigDecimal byran = new BigDecimal(0);
    private boolean flag = false;
    private boolean visible = false;
    private boolean btn = true;
    private int rbt;
    private static String kodNegeri;
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");

    @Inject
    public PindaanKutipanActionBean(AkaunDAO akaunDAO, TransaksiDAO transaksiDAO, HakmilikDAO hakmilikDAO,
            KodAkaunDAO kodAkaunDAO, DokumenKewanganDAO dokumenKewanganDAO,
            PermohonanDAO permohonanDAO, HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.akaunDAO = akaunDAO;
        this.transaksiDAO = transaksiDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.kodAkaunDAO = kodAkaunDAO;
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
        this.permohonanDAO = permohonanDAO;
    }
    @Inject
    KutipanHasilManager manager;
    @Inject
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private TransactionService tranService;
    @Inject
    private kodHasilConfig hasil;

    public List<Akaun> getAlist() {
        return alist;
    }

    public void setAlist(List<Akaun> alist) {
        this.alist = alist;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getCarian() {
        return carian;
    }

    public void setCarian(String carian) {
        this.carian = carian;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public String getNoAk() {
        return noAk;
    }

    public void setNoAk(String noAk) {
        this.noAk = noAk;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<Hakmilik> getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(List<Hakmilik> hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public List<Transaksi> getTransaksiList() {
        return transaksiList;
    }

    public void setTransaksiList(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransiList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public List<Akaun> getAccList() {
        return accList;
    }

    public void setAccList(List<Akaun> accList) {
        this.accList = accList;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccBaru() {
        return accBaru;
    }

    public void setAccBaru(String accBaru) {
        this.accBaru = accBaru;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BigDecimal getByran() {
        return byran;
    }

    public void setByran(BigDecimal byran) {
        this.byran = byran;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public int getRbt() {
        return rbt;
    }

    public void setRbt(int rbt) {
        this.rbt = rbt;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

    public String getRadioBtn() {
        return radioBtn;
    }

    public void setRadioBtn(String radioBtn) {
        this.radioBtn = radioBtn;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getResitManual() {
        return resitManual;
    }

    public void setResitManual(String resitManual) {
        this.resitManual = resitManual;
    }

    public List<DokumenKewangan> getResitList() {
        return resitList;
    }

    public void setResitList(List<DokumenKewangan> resitList) {
        this.resitList = resitList;
    }
    @Inject
    private etanah.Configuration conf;

    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        } else {
            kodNegeri = "negeri";
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_kutipan_2.jsp");
    }

    public Resolution search() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pengguna = ctx.getUser();
        flag = true;
        setVisible(true);
        if (hakmilik != null) {
            searchByIDHakmilik();
        } else if (getDokumenKewangan() != null) {
            searchByNoResit();
        } else if (getPermohonan() != null) {
            searchByIDPermohonan();
        } else if (akaun != null) {
            searchByNoAkaun();
        } else if (resitManual != null) {
            searchByResitManual();
            if (resitList.size() > 0) {
                setBtn(false);
            }
        }
//        boolean test = checkPermohonan(transaksiList);
//        if (test) {
//            setVisible(false);
//        }

        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_kutipan_2.jsp");
    }

    @ValidationMethod(on = "search")
    public void validateField(ValidationErrors errors) {
        if ((hakmilik == null) && (dokumenKewangan == null) && (permohonan == null) && (akaun == null) && (resitManual == null)) {
            errors.add("field", new SimpleError("Sila Masukkan ID Hakmilik atau Nombor Resit."));
        }
    }

    public void searchByIDHakmilik() {
        Session s = sessionProvider.get();

        String acc = "";
        String acnt = "";
        transaksiList = new ArrayList<Transaksi>();
        List<Transaksi> trList = new ArrayList<Transaksi>();

        Query qAkaun = s.createQuery("SELECT a from etanah.model.Akaun a where a.hakmilik.idHakmilik =:hm AND a.kodAkaun.kod='AC' AND a.status.kod='A'");
        qAkaun.setString("hm", hakmilik.getIdHakmilik());
        akaun = (Akaun) qAkaun.uniqueResult();
        searchByNoAkaun();
    }

    public void searchByNoAkaun() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.akaun.noAkaun =:akaun "
                + "AND dk.kodDokumen.kod = 'RBY' and dk.status.kod='A'");
        q.setString("akaun", akaun.getNoAkaun());
        resitList = q.list();

        if (resitList.size() > 0) {
            setNote("Sila Pilih Salah Satu daripada Senarai pilihan.");
            setBtn(false);
        }
    }

    public void searchByNoResit() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.idDokumenKewangan =:id "
                + "AND dk.kodDokumen.kod = 'RBY' and dk.status.kod='A'");
        q.setString("id", dokumenKewangan.getIdDokumenKewangan());
        resitList = q.list();
        LOG.info("resitList.size() : " + resitList.size());

        // search using no_ruj if trans not empty
        if (resitList.isEmpty()) {
            LOG.info("::::::::::::: resitList.isEmpty()");

            String sql = "SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.noRujukan = :id OR dk.noRujukanManual =:id AND dk.kodDokumen.kod = 'RBY' and dk.status.kod='A'";
            Query q1 = s.createQuery(sql);
            q1.setString("noRuj", dokumenKewangan.getIdDokumenKewangan());

            resitList = q1.list();
        }
        LOG.info("::: size resitList : " + resitList.size());

        if (resitList.size() > 0) {
            setBtn(false);
        }

    }

    public void searchByIDPermohonan() {
        String acc = "";
        String acnt = "";
        List<Transaksi> trList = new ArrayList<Transaksi>();
        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        List<Akaun> akaunList = new ArrayList<Akaun>();
        transaksiList = new ArrayList<Transaksi>();

        String[] n1 = {"permohonan"};
        Object[] v1 = {getPermohonan()};
        List<HakmilikPermohonan> senaraiHakmilikPermohonan = hakmilikPermohonanDAO.findByEqualCriterias(n1, v1, null);

        for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
            String[] n2 = {"hakmilik"};
            Object[] v2 = {hp.getHakmilik()};
            List<Akaun> senaraiAkaun = akaunDAO.findByEqualCriterias(n2, v2, null);
            akaunList.addAll(senaraiAkaun);
        }
        for (Akaun ak : akaunList) {
            if (ak.getKodAkaun().getKod().equals("AC")) {
                trList = ak.getSemuaTransaksi();
                acc = ak.getNoAkaun();
            }
        }
        for (Transaksi tr : trList) {
            if ((tr.getStatus().getKod() != 'T') && (tr.getDokumenKewangan() != null)) {
                if (!acnt.equals(acc)) {
                    transaksiList.add(tr);
                    acnt = acc;
                }
            }
        }
        if (transaksiList.size() > 0) {
            setNote("Sila Pilih Salah Satu daripada Senarai pilihan.");
        }
        setBtn(false);


    }

    public void searchByResitManual() {
        Session s = sessionProvider.get();

        String sqlResit = "SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.noRujukanManual = :id";
        Query qResit = s.createQuery(sqlResit);
        qResit.setString("id", resitManual);
        resitList = qResit.list();
    }

    public Resolution doCheckHakmilik() {
        String results = "";
        akaun = akaunDAO.findById(accBaru);
        if (akaun != null) {
            results = "0";
        } else {
            results = "1";
        }
        return new StreamingResolution("text/plain", results);
    }

    public Resolution cari() {
        List<Akaun> akList = manager.searchAkaun(accBaru);
        alist = new ArrayList<Akaun>();
        for (Akaun ak : akList) {
            if (ak.getKodAkaun().getKod().equals("AC")) {
                alist.add(ak);
            }
        }
        return new JSP("hasil/pindaan_kutipan_4.jsp").addParameter("popup", "true");
    }

    public Resolution tutup() {
        setAccBaru(radioBtn);
        return new StreamingResolution("text/plain", radioBtn);
    }

    public Resolution kembali() {
        hakmilik = new Hakmilik();
        akaun = new Akaun();
        dokumenKewangan = new DokumenKewangan();
        idHakmilik = null;
        carian = "";
        accBaru = "";

        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_kutipan_2.jsp");
    }

    public Resolution details() {
        Session s = sessionProvider.get();

        dokumenKewangan = dokumenKewanganDAO.findById(idHakmilik);
        setByran(dokumenKewangan.getAmaunBayaran());
        akaun = dokumenKewangan.getAkaun();

        String sqlResit = "SELECT tr FROM etanah.model.Transaksi tr WHERE tr.akaunDebit.noAkaun = :id or tr.akaunKredit.noAkaun = :id "
                + "ORDER BY tr.infoAudit.tarikhMasuk, tr.kodTransaksi.keutamaan, tr.untukTahun";
        Query qResit = s.createQuery(sqlResit);
        qResit.setString("id", akaun.getNoAkaun());
        transaksiList = qResit.list();
        LOG.info("transaksiList.size() : " + transaksiList.size());

        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_kutipan_3.jsp");
    }

    @ValidationMethod(on = "details")
    public void validateRadio(ValidationErrors errors) {
        if (idHakmilik == null) {
            errors.add("radioButton", new SimpleError("Sila Pilih Salah Satu daripada pilihan."));
        }
        search();
    }

    public Resolution refreshpage() {
        details();
        accBaru = radioBtn;

        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_kutipan_3.jsp");
    }

    public Resolution save() {
//        details();
        LOG.info("idHakmilik : " + idHakmilik);
        LOG.info("idKewDok  : " + idKewDok);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        pengguna = ctx.getUser(); //penggunaDAO.findById("admin");
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();

        int year = Integer.parseInt(yy.format(now));

        List<Transaksi> tList = new ArrayList<Transaksi>();
        List<Transaksi> trList = new ArrayList<Transaksi>();
        List<Transaksi> trnsList = new ArrayList<Transaksi>();
        List<Transaksi> senaraiTransaksiKredit = new ArrayList<Transaksi>();

        tx = s.beginTransaction();
        try {
            if (akaun.getKodAkaun().getKod().equals("AC")) {
                BigDecimal baki = akaun.getBaki().add(byran);
                akaun.setBaki(baki);
                ia.setDimasukOleh(akaun.getInfoAudit().getDimasukOleh());
                ia.setTarikhMasuk(akaun.getInfoAudit().getTarikhMasuk());
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(now);
                akaun.setInfoAudit(ia);
                manager.saveOrUpdate(akaun);
                tList = akaun.getSemuaTransaksi();
                senaraiTransaksiKredit = akaun.getSenaraiTransaksiKredit();
            }
            String catatan = "";
            InfoAudit info = new InfoAudit();

            for (Transaksi t : senaraiTransaksiKredit) {            // create new transaction : bg balance
                Transaksi trans = new Transaksi();

                if (t.getStatus().getKod() == 'T') {
                    if (t.getDokumenKewangan() != null) {
                        if (t.getDokumenKewangan().getIdDokumenKewangan().equals(idHakmilik)) {
                            trans.setCawangan(t.getCawangan());
                            trans.setKodTransaksi(t.getKodTransaksi());
                            trans.setUntukTahun(t.getUntukTahun());
                            trans.setTahunKewangan(year);
                            trans.setAkaunDebit(t.getAkaunKredit());
                            trans.setAkaunKredit(t.getAkaunDebit());
                            trans.setAmaun(t.getAmaun());
                            trans.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
                            info.setDimasukOleh(pengguna);
                            info.setTarikhMasuk(now);
                            trans.setInfoAudit(info);

                            manager.save(trans);
                            t.setSejarahDokumen(t.getDokumenKewangan().getIdDokumenKewangan());
                            t.setStatus(kodStatusTransaksiKewanganDAO.findById('P'));
                            t.setSejarahDokumen(t.getDokumenKewangan().getIdDokumenKewangan());
                            if (t.getDokumenKewangan() != null) {
                                t.setDokumenKewangan(null);
                            }
                            t.setDokumenKewangan(null);
                            ia.setTarikhMasuk(t.getInfoAudit().getTarikhMasuk());
                            ia.setDimasukOleh(t.getInfoAudit().getDimasukOleh());
                            ia.setDiKemaskiniOleh(pengguna);
                            ia.setTarikhKemaskini(now);
                            t.setInfoAudit(ia);

                            manager.saveOrUpdate(t);
                        }
                    }
                }
            }

            Akaun akaunBaru = akaunDAO.findById(accBaru);
            BigDecimal total = new BigDecimal(0);//akaun baru (baki)

            if (akaunBaru.getKodAkaun().getKod().equals("AC")) {
                akaunBaru.setBaki(akaunBaru.getBaki().subtract(byran));
                total = akaunBaru.getBaki();
                manager.saveOrUpdate(akaunBaru);

                Query q = s.createQuery("SELECT tr FROM etanah.model.Transaksi tr where ((tr.akaunDebit.noAkaun =:akaun OR tr.akaunKredit.noAkaun =:akaun) "
                        + "AND tr.status.kod ='A') ORDER BY tr.kodTransaksi.keutamaan");
                q.setString("akaun", akaunBaru.getNoAkaun());
                trList = q.list();
            }
            catatan = "Pindahan Bayaran dari ID Hakmilik " + akaun.getHakmilik().getIdHakmilik() + " ke ID Hakmilik " + akaunBaru.getHakmilik().getIdHakmilik();
            DokumenKewangan dk = dokumenKewanganDAO.findById(idHakmilik);
            dk.setCatatan(catatan);
            dk.setAkaun(akaunBaru);
            manager.update(dk);

            Query q = s.createQuery("SELECT a FROM etanah.model.Akaun a where a.kodAkaun.kod='AKH' AND a.cawangan.kod =:caw");
            q.setString("caw", caw.getKod());
            Akaun akh = (Akaun) q.uniqueResult();

            if (total.doubleValue() == 0) {
                saveTransaction(trList, caw, year, pengguna, ia, akh, byran, akaunBaru);
            }
            if (total.compareTo(new BigDecimal(0)) < 0) {         // :: bayar lebih
                saveTransaction(trList, caw, year, pengguna, ia, akh, byran, akaunBaru);
                Transaksi t = new Transaksi();

                t.setAkaunKredit(akaunBaru);
                t.setAkaunDebit(akh);
                t.setAmaun(total.multiply(new BigDecimal(-1)));
                t.setCawangan(caw);
                t.setDokumenKewangan(dk);
                KodTransaksi kt = new KodTransaksi();
                kt.setKod(hasil.getProperty("cukaiTanahSemasa"));
                t.setKodTransaksi(kt);
                t.setUntukTahun(year);
                t.setTahunKewangan(year);
                ia.setDimasukOleh(pengguna);
                t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                ia.setTarikhMasuk(now);
                t.setInfoAudit(ia);
                manager.save(t);
            }

            if (total.compareTo(new BigDecimal(0)) > 0) {                       //  :: bayar kurang
                tranService.saveTransaction(trList, byran, akh, dk.getIdDokumenKewangan(), pengguna, ia, year);
            }

//            for (Transaksi t : senaraiTransaksiKredit) {            // update status old transaction : T -> P
//                t.setStatus(kodStatusTransaksiKewanganDAO.findById('P'));
//                Date d = t.getInfoAudit().getTarikhMasuk();
//                ia.setTarikhMasuk(d);
//                ia.setDimasukOleh(t.getInfoAudit().getDimasukOleh());
//                ia.setDiKemaskiniOleh(pengguna);
//                ia.setTarikhKemaskini(now);
//                t.setInfoAudit(ia);
//
//                manager.saveOrUpdate(t);
//            }
            tx.commit();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            tx.rollback();
            addSimpleError("Pembayaran tidak berjaya.");
        }
        display();
        addSimpleMessage("Pindaan Maklumat Telah Berjaya.");
        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_kutipan_5.jsp");
    }

    public void transaction(Transaksi t, String res, Pengguna pengguna, InfoAudit ia, int year) {
        Date now = new Date();
        t.setDokumenKewangan(dokumenKewanganDAO.findById(idHakmilik));
        res = dokumenKewanganDAO.findById(idHakmilik).getIdDokumenKewangan();
        t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        t.setUntukTahun(year);
        t.setInfoAudit(ia);
    }

    public void saveTransaction(List<Transaksi> trList, KodCawangan caw, int y,
            Pengguna pengguna, InfoAudit ia, Akaun akh, BigDecimal pindahan, Akaun akaunBaru) {
        Date now = new Date();
        BigDecimal bd = pindahan;
        int count = 0;
        int year = Integer.parseInt(yy.format(now));
        BigDecimal amaunRemisyen = new BigDecimal(0.00);
        boolean remisyen = false;

        for (Transaksi tr : trList) {
            if ((tr.getKodTransaksi().getKod().equals("99000")) || (tr.getKodTransaksi().getKod().equals("99001")) || (tr.getKodTransaksi().getKod().equals("99002"))
                    || (tr.getKodTransaksi().getKod().equals("99003")) || (tr.getKodTransaksi().getKod().equals("99030")) || (tr.getKodTransaksi().getKod().equals("99032"))
                    || (tr.getKodTransaksi().getKod().equals("99051")) || (tr.getKodTransaksi().getKod().equals("99052"))) {
                remisyen = true;
                amaunRemisyen = tr.getAmaun();
            }
        }

        for (Transaksi tr1 : trList) {
            count++;
            if (remisyen) {
                if (tr1.getStatus().getKod() == 'A') {
                    if ((tr1.getKodTransaksi().getKod().equals("99000")) || (tr1.getKodTransaksi().getKod().equals("99001"))
                            || (tr1.getKodTransaksi().getKod().equals("99002")) || (tr1.getKodTransaksi().getKod().equals("99003"))
                            || (tr1.getKodTransaksi().getKod().equals("99030")) || (tr1.getKodTransaksi().getKod().equals("99032"))
                            || (tr1.getKodTransaksi().getKod().equals("99051")) || (tr1.getKodTransaksi().getKod().equals("99052"))) {
                    } else {
                        Transaksi t = new Transaksi();

                        t.setCawangan(caw);
                        t.setKodTransaksi(tr1.getKodTransaksi());
                        t.setDokumenKewangan(dokumenKewanganDAO.findById(idHakmilik));
                        t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(now);
                        t.setInfoAudit(ia);
                        if (tr1.getKodTransaksi().getKod().equals("61401")) {
                            t.setAmaun(tr1.getAmaun().subtract(amaunRemisyen));

                        }
                        t.setAkaunDebit(akh);
                        t.setAkaunKredit(akaunBaru);

//                        if ((tr1.getAkaunKredit() != null) && (tr1.getAkaunDebit() != null)) {
//                            t.setAkaunDebit(akaunBaru);
//                        } else {
//                            t.setAkaunDebit(akh);}
//                        if ((tr1.getAkaunDebit() != null) && (tr1.getAkaunDebit().getNoAkaun().equals(accBaru))) {
//                            t.setAkaunKredit(akaunBaru);
//                        }
                        t.setUntukTahun(tr1.getUntukTahun());
                        t.setTahunKewangan(year);
                        manager.save(t);
                    }
                }
            } else {
                if (tr1.getStatus().getKod() == 'A') {
                    Transaksi t = new Transaksi();

                    t.setCawangan(caw);
                    t.setKodTransaksi(tr1.getKodTransaksi());
                    t.setAmaun(tr1.getAmaun());
                    t.setDokumenKewangan(dokumenKewanganDAO.findById(idHakmilik));
                    t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(now);
                    t.setInfoAudit(ia);

                    if ((tr1.getAkaunKredit() != null) && (tr1.getAkaunDebit() != null)) {
                        t.setAkaunDebit(akaunBaru);
                    } else {
                        t.setAkaunDebit(akh);
                    }
                    if ((tr1.getAkaunDebit() != null) && (tr1.getAkaunDebit().getNoAkaun().equals(accBaru))) {
                        t.setAkaunKredit(akaunBaru);
                    }
                    t.setUntukTahun(tr1.getUntukTahun());
                    t.setTahunKewangan(year);
                    manager.save(t);
                }
            }
        }
    }

    @ValidationMethod(on = "save")
    public void validateSave(ValidationErrors errors) {
        akaun = akaunDAO.findById(accBaru);
        if (akaun == null) {
            errors.add("radioButton", new SimpleError("Tiada Rekod untuk Nombor Akaun yang dimasukkan."));
        }
        details();
    }

    public void display() {
        dokumenKewangan = dokumenKewanganDAO.findById(idHakmilik);

        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.idDokumenKewangan = :id");
        q.setString("id", dokumenKewangan.getIdDokumenKewangan());
        senarai = q.list();
    }

    public Resolution doCheckBalance1() {
        String results = "";
        Akaun a = akaunDAO.findById(getContext().getRequest().getParameter("idHakmilik"));

        if (a != null) {
            if (a.getStatus().getKod().equals("B")) {
                results = "B";
            } else {
                results = "xx";
            }
        } else {
            results = "1";
        }
//         else {
//            List<Akaun> list = new ArrayList<Akaun>();
//            list = manager.searchAkaun(getContext().getRequest().getParameter("idHakmilik"));
//            if (list.size() > 0) {
//                results = "xx";
//            } else {
//                results = "1";
//            }
//        }
        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckReceipt() {
        String results = "0";
        DokumenKewangan dk = dokumenKewanganDAO.findById(getContext().getRequest().getParameter("resit"));

        if (dk != null) {
            if (dk.getCatatan() != null) {
                results = "0";
            } else {
                results = "1";
            }
        } else {
            results = "0";
        }
        return new StreamingResolution("text/plain", results);
    }

    public Resolution getAmaun() {
        String results = "";
        Akaun a = akaunDAO.findById(getContext().getRequest().getParameter("idHakmilik"));

        if (a != null) {
            String x = a.getBaki().toString();
            results = x;
        } else {
            results = "-";
        }
        return new StreamingResolution("text/plain", results);
    }

    public Resolution getAkaunBaru() {
        String results = "";
        Akaun a = akaunDAO.findById(getContext().getRequest().getParameter("idHakmilik"));
        if (a != null) {
            String x = a.getCawangan().getKod();
            results = x;
        } else {
            results = "-";
        }
        return new StreamingResolution("text/plain", results);
    }

    public boolean checkPermohonan(List<Transaksi> senaraiTransaksi) {
        boolean checking = false;
        List<HakmilikPermohonan> senaraiPermohonanHakmilik = new ArrayList<HakmilikPermohonan>();
        if (senaraiTransaksi.size() > 0) {
            Transaksi t = new Transaksi();
            t = senaraiTransaksi.get(0);
            Akaun akKredit = t.getAkaunKredit();

            String statusTK = "tiada";
            String cukaiMohon = "tiada";

            for (Transaksi transTK : senaraiTransaksi) {
                if (transTK.getPermohonan() != null) {    // pembayaran untuk permohonan
                    if (transTK.getPermohonan().getStatus().equals("TK")) // TK = TIDAK AKTIF (status permohonan for module pendaftaran)
                    {
                        statusTK = "ada";
                    }
                } else {
                    if (transTK.getAkaunKredit() != null) { // pembayaran cukai tanah
                        String[] nama = {"hakmilik.idHakmilik"};
                        Object[] nilai = {transTK.getAkaunKredit().getHakmilik().getIdHakmilik()};
                        senaraiPermohonanHakmilik.clear();
                        LOG.info("(clear) senaraiPermohonanHakmilik.size :" + senaraiPermohonanHakmilik.size());
                        senaraiPermohonanHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(nama, nilai, null);
                        LOG.info("(chk) senaraiPermohonanHakmilik.size :" + senaraiPermohonanHakmilik.size());

                        for (HakmilikPermohonan sph : senaraiPermohonanHakmilik) {
                            if (sph.getPermohonan().getStatus() == null) {
                                continue;
                            }
                            if (!(sph.getPermohonan().getStatus().equals("SL") || sph.getPermohonan().getStatus().equals("TK"))) { // SL = Selesai, TK = Tidak Aktif
                                cukaiMohon = "ada";
                            }
                            LOG.info("Permohonan/Perserahan :" + sph.getPermohonan().getIdPermohonan() + ", status :" + sph.getPermohonan().getStatus().toString());
                        }
                    }
                }
            }

            if (cukaiMohon.equals("ada")) { //untuk cukai tanah
                setBtn(true);
                addSimpleError(
                        "Terdapat permohonan yang masih AKTIF. Tidak dibenarkan membuat pembatalan.");
                checking = true;
            }
        }
        return checking;
    }

    private List<Transaksi> removeDuplicateKewDok(List<Transaksi> senaraiTransaksi) {
        Map<DokumenKewangan, Transaksi> map = new HashMap<DokumenKewangan, Transaksi>();
        for (int i = 0; i
                < senaraiTransaksi.size(); i++) {
            DokumenKewangan dk = senaraiTransaksi.get(i).getDokumenKewangan();
            if (map.containsKey(dk)) {
                continue;
            } else {
                map.put(dk, senaraiTransaksi.get(i));
            }
        }
        senaraiTransaksi = new ArrayList<Transaksi>(map.values());
        return senaraiTransaksi;
    }

    public List<DokumenKewangan> getSenarai() {
        return senarai;
    }

    public void setSenarai(List<DokumenKewangan> senarai) {
        this.senarai = senarai;
    }
}
