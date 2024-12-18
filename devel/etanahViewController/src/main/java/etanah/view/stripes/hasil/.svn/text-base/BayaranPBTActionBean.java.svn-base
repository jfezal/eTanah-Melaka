package etanah.view.stripes.hasil;

import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import org.hibernate.Query;
import java.math.BigDecimal;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import java.text.ParseException;
import able.stripes.AbleActionBean;
import etanah.sequence.GeneratorNoResit2;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.*;
import org.hibernate.Transaction;

/**
 * @author haqqiem
 */

@Wizard(startEvents = {"showForm"})
@UrlBinding("/hasil/bayaran_pbt")
public class BayaranPBTActionBean extends AbleActionBean {
    private static final Logger logger = Logger.getLogger(BayaranPBTActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/bayaran_pbt.jsp";
    private static final String PAYMENT_VIEW = "/WEB-INF/jsp/hasil/bayaran_pbt_1.jsp";
    private static final String DISPLAY_VIEW = "/WEB-INF/jsp/hasil/bayaran_pbt_2.jsp";
    
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    private Transaksi transaksi = new Transaksi();
    private KodUrusan kodUrusan = new KodUrusan();
    private DokumenKewangan dokumenKewangan = new DokumenKewangan();
    
    private List<String> rujukan = new ArrayList<String>();
    private List<String> tarikhCek = new ArrayList<String>();
    private List<Integer> kuantiti = new ArrayList<Integer>();
    private List<Transaksi> listTrans = new ArrayList<Transaksi>();
    private List<KodUrusan> listUrusan = new ArrayList<KodUrusan>();
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private List<KodUrusan> senaraiKodUrusan = new ArrayList<KodUrusan>();
    private List<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<KodTransaksi> senaraiKodTransaksi = new ArrayList<KodTransaksi>();
    private List<DokumenKewanganBayaran> dkbList = new ArrayList<DokumenKewanganBayaran>();

    private int bil = 0;
    private String mod;
    private String negeri;
    private String penyerahNegeri;
    private boolean balanceFlag = false;
    private BigDecimal jumlah = new BigDecimal(0.00);
    private BigDecimal jumlahBayar;
    private BigDecimal returnBalance;

    @Inject AkaunDAO akaunDAO;
    @Inject KodBankDAO kodBankDAO;
    @Inject KodAkaunDAO kodAkaunDAO;
    @Inject ModKutipService modKutip;
    @Inject KodKutipanDAO kodKutipDAO;
    @Inject TransaksiDAO transaksiDAO;
    @Inject KodUrusanDAO kodUrusanDAO;
    @Inject KodNegeriDAO kodNegeriDAO;
    @Inject PenyataPemungutService pp;
    @Inject KodDokumenDAO kodDokumenDAO;
    @Inject KutipanHasilManager manager;
    @Inject KodTransaksiDAO kodTransaksiDAO;
    @Inject private etanah.Configuration conf;
    @Inject KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject GeneratorNoResit2 noResitGenerator2;
    @Inject DokumenKewanganDAO dokumenKewanganDAO;
    @Inject DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    @Inject KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {

        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if("04".equals(conf.getProperty("kodNegeri"))){
            negeri = "melaka";
            sql = "SELECT ku FROM etanah.model.KodUrusan ku WHERE ku.kodTransaksi.kod in ('51530','51531','51532') ORDER BY ku.kodTransaksi.kod ";
        }
        q = s.createQuery(sql);
        senaraiKodUrusan = q.list();

        collectUrusan();

        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution collectUrusan() {
        bil +=5;
        if ((listUrusan == null)||(listTrans == null)) {
            listUrusan = new ArrayList<KodUrusan>();
        }
        if ((listUrusan.size() == 0)||(listTrans.size() == 0)) {
            for (int i = 0; i < bil; i++) {
                KodUrusan kod = new KodUrusan();
                Transaksi kodTr = new Transaksi();
                listUrusan.add(kod);
                listTrans.add(kodTr);
            }
            bil = listUrusan.size();
        }else{
            for (int i = 0; i < bil; i++) {
                KodUrusan kod = new KodUrusan();
                Transaksi kodTr = new Transaksi();
                listUrusan.add(kod);
                listTrans.add(kodTr);
            }
            bil = listUrusan.size();
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }

    @HandlesEvent("Step1")
    public Resolution payment() {
        collectPayment();
        return new ForwardResolution(PAYMENT_VIEW);
    }

    public Resolution collectPayment() {
        if (senaraiCaraBayaran == null) {
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        }
        if (senaraiCaraBayaran.size() == 0) {
            for (int i = 0; i < 5; i++) {
                CaraBayaran cr = new CaraBayaran();
                cr.setAmaun(BigDecimal.ZERO);
                senaraiCaraBayaran.add(cr);
            }
        }

        return new ForwardResolution(PAYMENT_VIEW);
    }

    @HandlesEvent("Step2")
    public Resolution bayar() throws ParseException{
        logger.info("-- INSIDE bayar --");
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        DokumenKewangan dk = new DokumenKewangan();
        List<DokumenKewangan> senaraiDK = new ArrayList<DokumenKewangan>();

        Akaun akh = new Akaun();
        akh = akaunDAO.findById(akaunKutipanHarian(pengguna));
        int year = Integer.parseInt(yy.format(now));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            logger.info("listUrusan.size() : "+listUrusan.size());
            for (int x = 0; x < listUrusan.size(); x++) {
                KodUrusan ursn = new KodUrusan();
                ursn = kodUrusanDAO.findById(listUrusan.get(x).getKod());
                if (ursn != null) {
                    String noPbt = rujukan.get(x);
                    KodTransaksi kt = kodTransaksiDAO.findById(ursn.getKodTransaksi().getKod());
                    Integer qty = kuantiti.get(x);
                    BigDecimal a = new BigDecimal(0.00);
                    if (ursn.getKod().equals("SSDOK")) {
                        if (qty <= 5) {
                            a = new BigDecimal(50);
                        } else {
                            BigDecimal aa = new BigDecimal(qty - 5);
                            a = (aa.multiply(new BigDecimal(10))).add(new BigDecimal(50));
                        }
                    } else {
                        a = listUrusan.get(x).getCaj().multiply(new BigDecimal(qty));
                    }
                    dk = new DokumenKewangan();
                    dk = createReceipt(ctx, caw, pengguna, now, ia, a, noPbt);

                    transaksi = new Transaksi();
                    transaksi.setAmaun(a);
                    transaksi.setKodTransaksi(kt);
                    transaksi.setKodUrusan(ursn.getKod());
                    transaksi.setCawangan(caw);
                    transaksi.setDokumenKewangan(dk);
                    transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                    transaksi.setInfoAudit(ia);
                    transaksi.setUntukTahun(year);
                    transaksi.setAkaunDebit(akh);
                    transaksi.setKuantiti(qty);
                    transaksi.setPerihal(ursn.getNama());

                    manager.save(transaksi);
                    senaraiDK.add(dk);
                }
            }
            pp.savePenyataPemungut1(senaraiDK, dk);
            transaksiList(senaraiDK);
            caraBayar(dk.getIdDokumenKewangan());
            tx.commit();
            addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        }catch (Exception ex) {
            noResitGenerator2.rollbackAndUnlockSerialNo(pengguna);
            logger.error(ex.getMessage(), ex);
            tx.rollback();
            addSimpleError("Pembayaran tidak berjaya.");
        }finally{noResitGenerator2.commitAndUnlockSerialNo(pengguna);}
        return new ForwardResolution (DISPLAY_VIEW);
    }

    public void caraBayar(String resit){
        dokumenKewangan = dokumenKewanganDAO.findById(resit);
        String [] n1 = {"dokumenKewangan"};
        Object [] v1 = {dokumenKewangan};
        dkbList = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);
    }

    public void transaksiList(List<DokumenKewangan> senaraiDK){
        List<Transaksi> listTr = new ArrayList<Transaksi>();
        for (DokumenKewangan dk : senaraiDK) {
            dokumenKewangan = new DokumenKewangan();
            dokumenKewangan = dokumenKewanganDAO.findById(dk.getIdDokumenKewangan());
            String [] n1 = {"dokumenKewangan"};
            Object [] v1 = {dokumenKewangan};
            List<Transaksi>listT = transaksiDAO.findByEqualCriterias(n1, v1, null);
            listTr.addAll(listT);
        }
        senaraiTransaksi = listTr;
    }

    public String akaunKutipanHarian(Pengguna pguna) {
        logger.info("-- INSIDE akaunKutipanHarian --");
        KodAkaun kodAkaun = kodAkaunDAO.findById("AKH");
        String noAkaun = "";
        String[] n1 = {"kodAkaun"};
        Object[] v1 = {kodAkaun};
        List<Akaun> akList = new ArrayList<Akaun>();
        akList = akaunDAO.findByEqualCriterias(n1, v1, null);
        for (Akaun acc : akList) {
            if (acc.getCawangan().getKod().equals(pguna.getKodCawangan().getKod())) {
                noAkaun = acc.getNoAkaun();
            }
        }
        logger.info("jumlahBayar : " + jumlahBayar);
        Akaun ak = akaunDAO.findById(noAkaun);
        ak.setBaki(ak.getBaki().add(jumlahBayar));
        manager.saveOrUpdate(ak);
        return noAkaun;
    }

    public DokumenKewangan createReceipt(etanahActionBeanContext ctx, KodCawangan caw, Pengguna pengguna,
                                                                Date now, InfoAudit ia, BigDecimal amaun, String nomborAkaun) throws ParseException {
        DokumenKewangan dk = new DokumenKewangan();

        mod = modKutip.loadPenyerahFromSession(ctx);

//        String resit = noResitGenerator.generateNoResit(ctx.getKodNegeri(), caw, pengguna);
        String resit = noResitGenerator2.getAndLockSerialNo(pengguna);
        // resit
        dk = new DokumenKewangan();
        dk.setIdDokumenKewangan(resit);
        ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
        dk.setAmaunBayaran(amaun);
        dk.setNoRujukan(nomborAkaun);
        dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
        dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
        dk.setIsuKepada(dokumenKewangan.getIsuKepada());
        if(dokumenKewangan.getIsuKepadaAlamat1() != null)
            dk.setIsuKepadaAlamat1(dokumenKewangan.getIsuKepadaAlamat1());
        if(dokumenKewangan.getIsuKepadaAlamat2() != null)
            dk.setIsuKepadaAlamat2(dokumenKewangan.getIsuKepadaAlamat2());
        if(dokumenKewangan.getIsuKepadaAlamat3() != null)
            dk.setIsuKepadaAlamat3(dokumenKewangan.getIsuKepadaAlamat3());
        if(dokumenKewangan.getIsuKepadaAlamat4() != null)
            dk.setIsuKepadaAlamat4(dokumenKewangan.getIsuKepadaAlamat4());
        dk.setIsuKepadaPoskod(dokumenKewangan.getIsuKepadaPoskod());
        dk.setIsuKepadaNegeri(dokumenKewangan.getIsuKepadaNegeri());
        if(dokumenKewangan.getIsuKepadaEmail() != null);
            dk.setIsuKepadaEmail(dokumenKewangan.getIsuKepadaEmail());
        if(dokumenKewangan.getIsuKepadaNoTelefon1() != null);
            dk.setIsuKepadaNoTelefon1(dokumenKewangan.getIsuKepadaNoTelefon1());
        KodNegeri kn = kodNegeriDAO.findById(penyerahNegeri);
        dk.setIsuKepadaNegeri(kn);
        dk.setInfoAudit(ia);
        dk.setCawangan(caw);
        if (mod != null && mod.length() > 0) dk.setMod(kodKutipDAO.findById(mod.charAt(0)));
        dk.setTarikhTransaksi(now);
        dk.setIdKaunter(pengguna.getIdKaunter());

        // save cara bayaran
        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
        for (int f = 0; f < senaraiCaraBayaran.size(); f++) {
            CaraBayaran cb = senaraiCaraBayaran.get(f);
            if (cb.getAmaun() != null) {
                if (cb.getAmaun().intValue() > 0) {
                    KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                    if ((crByr.getKod().equals("CB")) || (crByr.getKod().equals("CC")) || (crByr.getKod().equals("CT")) ||
                            (crByr.getKod().equals("CL")) || (crByr.getKod().equals("IB")) || (crByr.getKod().equals("BC"))) {
                        Date d = sdf.parse(tarikhCek.get(f));
                        cb.setTarikhCek(d);
                    }
                    if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {  // clear if null
                        cb.setBank(null);
                        cb.setBankCawangan(null);
                    }
                    if (cb.getBank() != null) {
                        KodBank bank = kodBankDAO.findById(cb.getBank().getKod());
                        cb.setBank(bank);
                    }
                    if ((crByr.getKod().equals("KW")) || (crByr.getKod().equals("WP"))) {
                        cb.setBank(kodBankDAO.findById("PMB"));
                    }
                    cb.setKodCaraBayaran(crByr);
                    cb.setCawangan(caw);
                    cb.setInfoAudit(ia);
                    cb.setAktif('Y');
                    DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                    for (int i = 0; i < senaraiCaraBayaran.size(); i++) {
                        CaraBayaran c = senaraiCaraBayaran.get(i);
                        if (c.getKodCaraBayaran() != null) {
                            if (c.getKodCaraBayaran().getKod().equals("T")) {
                                dk.setAmaunTunai(c.getAmaun());
                                if(jumlahBayar.compareTo(jumlah) == 1){
                                    cb.setAmaun(jumlah);
                                    returnBalance = jumlahBayar.subtract(jumlah);
                                    balanceFlag = true;
                                }
                            } else {
                                dk.setAmaunTunai(new BigDecimal(0));
                            }
                        }
                    }
                    manager.saveOrUpdate(cb);
                    dkb.setCaraBayaran(cb);
                    dkb.setDokumenKewangan(dk);
                    dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                    dkb.setInfoAudit(ia);
                    dkb.setAktif('Y');
                    adkb.add(dkb);
                }
            }
        }
        dk.setSenaraiBayaran(adkb);
        manager.saveOrUpdate(dk);

        return dk;
    }

    public Resolution kembali(){
        return new RedirectResolution(BayaranPBTActionBean.class);
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public List<Transaksi> getListTrans() {
        return listTrans;
    }

    public void setListTrans(List<Transaksi> listTrans) {
        this.listTrans = listTrans;
    }

    public List<KodUrusan> getListUrusan() {
        return listUrusan;
    }

    public void setListUrusan(List<KodUrusan> listUrusan) {
        this.listUrusan = listUrusan;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public List<KodUrusan> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(List<KodUrusan> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public List<KodTransaksi> getSenaraiKodTransaksi() {
        return senaraiKodTransaksi;
    }

    public void setSenaraiKodTransaksi(List<KodTransaksi> senaraiKodTransaksi) {
        this.senaraiKodTransaksi = senaraiKodTransaksi;
    }

    public List<String> getRujukan() {
        return rujukan;
    }

    public void setRujukan(List<String> rujukan) {
        this.rujukan = rujukan;
    }

    public List<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(List<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public List<String> getTarikhCek() {
        return tarikhCek;
    }

    public void setTarikhCek(List<String> tarikhCek) {
        this.tarikhCek = tarikhCek;
    }

    public BigDecimal getJumlahBayar() {
        return jumlahBayar;
    }

    public void setJumlahBayar(BigDecimal jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(String penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public List<DokumenKewanganBayaran> getDkbList() {
        return dkbList;
    }

    public void setDkbList(List<DokumenKewanganBayaran> dkbList) {
        this.dkbList = dkbList;
    }

    public List<Integer> getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(List<Integer> kuantiti) {
        this.kuantiti = kuantiti;
    }

    public boolean isBalanceFlag() {
        return balanceFlag;
    }

    public void setBalanceFlag(boolean balanceFlag) {
        this.balanceFlag = balanceFlag;
    }

    public BigDecimal getReturnBalance() {
        return returnBalance;
    }

    public void setReturnBalance(BigDecimal returnBalance) {
        this.returnBalance = returnBalance;
    }
}
