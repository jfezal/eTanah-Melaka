package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoResit2;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.*;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author abdul.hakim
 * Pembelian Pelan for Negeri Sembilan
 *
 */
@Wizard(startEvents = {"showForm"})
@UrlBinding("/hasil/beli_pelan")
public class PembelianPelanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PembelianPelanActionBean.class);
    private DokumenKewangan dokumenKewangan;
    private KodUrusan kodUrusan;
    private Akaun akaun;
    private Transaksi transaksi;
    private Pengguna pguna;

    private DokumenKewanganDAO dokumenKewanganDAO;
    private KodTransaksiDAO kodTransaksiDAO;
    private KodUrusanDAO kodUrusanDAO;
    private KodDokumenDAO kodDokumenDAO;
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    private KodBankDAO kodBankDAO;
    private AkaunDAO akaunDAO;
    private TransaksiDAO transaksiDAO;
    private KodAkaunDAO kodAkaunDAO;
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;

    private BigDecimal jumlahCaj = (BigDecimal.ZERO);
    private BigDecimal jumCaraBayar = (BigDecimal.ZERO);
    private BigDecimal total = (BigDecimal.ZERO);
    private BigDecimal totalBayaran = (BigDecimal.ZERO);
    private List<Integer> bil = new ArrayList<Integer>();
    private String akaunKutHarian;
    private String idKewDok;
    private String jawatan;
    private String mod = "";
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/pembelian_pelan.jsp";
    private static final String PAYMENT_VIEW = "/WEB-INF/jsp/hasil/pembelian_pelan_1.jsp";
    private static final String pelan = "73151";
    private static final String semak_pelan = "40020";
    private static final String kod_urusan = "MDBP";
    private List<String> chkbox = new ArrayList<String>();
    private List<KodUrusan> senaraiKodUrusan = new ArrayList<KodUrusan>();
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private List<Transaksi> transList = new ArrayList<Transaksi>();
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<DokumenKewangan> senaraiKewDok = new ArrayList<DokumenKewangan>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    @Inject
    public PembelianPelanActionBean(DokumenKewanganDAO dokumenKewanganDAO, KodTransaksiDAO kodTransaksiDAO,
                                    KodUrusanDAO kodUrusanDAO, KodDokumenDAO kodDokumenDAO,
                                    KodStatusDokumenKewanganDAO kodStautsDokumenKewanganDAO, KodCaraBayaranDAO kodCaraBayaranDAO,
                                    KodBankDAO kodBankDAO, AkaunDAO akaunDAO, TransaksiDAO transaksiDAO, KodAkaunDAO kodAkaunDAO,
                                    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO){
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.kodTransaksiDAO = kodTransaksiDAO;
        this.kodUrusanDAO = kodUrusanDAO;
        this.kodDokumenDAO= kodDokumenDAO;
        this.kodStatusDokumenKewanganDAO = kodStautsDokumenKewanganDAO;
        this.kodCaraBayaranDAO = kodCaraBayaranDAO;
        this.kodBankDAO = kodBankDAO;
        this.akaunDAO = akaunDAO;
        this.transaksiDAO = transaksiDAO;
        this.kodAkaunDAO = kodAkaunDAO;
        this.kodStatusTransaksiKewanganDAO = kodStatusTransaksiKewanganDAO;
    }

    @Inject
    GeneratorNoResit2 noResitGenerator2;

    @Inject
    KodKutipanDAO kodKutipanDAO;

    @Inject
    KutipanHasilManager manager;

    @Inject
    private ReportUtil reportUtil;

    @Inject
    private etanah.Configuration conf;

    @Inject
    PenyataPemungutService pp;

    @Inject
    private ModKutipService modKutip;

    @DefaultHandler
    public Resolution showForm() {
        kodUrusan = kodUrusanDAO.findById(kod_urusan);
        senaraiKodUrusan.add(kodUrusan);
        senaraiTransaksi = getSenaraiTrans();
        jumlahCaj = kodUrusan.getCaj();
        collectPayment();

        return new ForwardResolution(DEFAULT_VIEW);
    }

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public List getSenaraiTrans(){
        Session s = sessionProvider.get();
        Query q = s.createQuery("from KodTransaksi u where u.kod in (:kod1,:kod2)");
        q.setString("kod1", pelan);
        q.setString("kod2", semak_pelan);
        return q.list();
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
        return new ForwardResolution(DEFAULT_VIEW);
    }

    @DontValidate
    public Resolution main() {
            return new RedirectResolution(PembelianPelanActionBean.class);
    }

    public Resolution save(){
        
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        pguna = pengguna;
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        String a = pengguna.getJawatan();
        jawatan = a.replace(" ", "_");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try{
            String resit  = noResitGenerator2.getAndLockSerialNo(pguna);
            mod = modKutip.loadPenyerahFromSession(ctx);
            // resit
            DokumenKewangan dk = new DokumenKewangan();
            dk.setIdDokumenKewangan(resit);
            // set idResit to pageContext
            ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
            dk.setAmaunBayaran(jumCaraBayar);
            dk.setKodDokumen(kodDokumenDAO.findById("PLK"));
            dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
            dk.setInfoAudit(ia);
            dk.setCawangan(caw);
            dk.setTarikhTransaksi(new java.util.Date());
            dk.setIsuKepada(dokumenKewangan.getIsuKepada());
            dk.setIdKaunter(pengguna.getIdKaunter());
            if (mod != null && mod.length() > 0) dk.setMod(kodKutipanDAO.findById(mod.charAt(0)));
                dk.setMod(kodKutipanDAO.findById('B'));
            // save cara bayaran

            ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
            for (CaraBayaran cb : senaraiCaraBayaran) {
                if(cb.getAmaun() != null){
                    if (cb.getAmaun().intValue() > 0) {
                        KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                        if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {  // clear if null
                            cb.setBank(null);
                            cb.setBankCawangan(null);
                        }
                        if (cb.getBank() != null) {
                            KodBank bank = kodBankDAO.findById(cb.getBank().getKod());
                            cb.setBank(bank);
                        }if((crByr.getKod().equals("KW"))||(crByr.getKod().equals("WP"))){
                            cb.setBank(kodBankDAO.findById("PMB"));
                        }
                        cb.setKodCaraBayaran(crByr);
                        cb.setCawangan(caw);
                        cb.setInfoAudit(ia);
                        cb.setAktif('Y');
                        manager.saveOrUpdate(cb);
                        DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                        for (int i = 0; i < senaraiCaraBayaran.size(); i++) {
                            CaraBayaran c = senaraiCaraBayaran.get(i);
                            if(c.getKodCaraBayaran() != null){
                                if (c.getKodCaraBayaran().getKod().equals("T")) {
                                    dk.setAmaunTunai(c.getAmaun());
                                }
                            }
                        }
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
            idKewDok = dk.getIdDokumenKewangan();

            updateKutipanHarian(resit, totalBayaran, pengguna);
            DokumenKewangan dok = dokumenKewanganDAO.findById(resit);
            pp.savePenyataPemungut(dok);
            displayPayment(resit);
            tx.commit();
            addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        }catch(Exception ex) {
            noResitGenerator2.rollbackAndUnlockSerialNo(pguna);
            LOG.error(ex.getMessage(), ex);
            tx.rollback();
            addSimpleError("Pembayaran tidak berjaya.");
        }finally{noResitGenerator2.commitAndUnlockSerialNo(pguna);}
        return new ForwardResolution(PAYMENT_VIEW);
    }

    private void updateKutipanHarian(String resit, BigDecimal totalBayaran, Pengguna pengguna){
        Session s = sessionProvider.get();
        List<Akaun>akaunList = new ArrayList<Akaun>();
        KodAkaun kodAkaun = kodAkaunDAO.findById("AKH");
        String [] n1 = {"kodAkaun"};
        Object [] v1 = {kodAkaun};
        akaunList = akaunDAO.findByEqualCriterias(n1, v1, null);

        for (Akaun ak : akaunList) {
            if(ak.getCawangan().getKod().equals(pengguna.getKodCawangan().getKod())){
                akaunKutHarian = ak.getNoAkaun();
            }
        }
        akaun = new Akaun();
        akaun = akaunDAO.findById(akaunKutHarian);
        s.lock(akaun, LockMode.UPGRADE);
        akaun.setBaki((akaun.getBaki().add(totalBayaran)));

        manager.saveOrUpdate(akaun);

        String kod = "";
        Integer qty = 0;
        for (int i = 0; i < 2; i++) {
            kod = chkbox.get(i);
            qty = bil.get(i);

            if((kod != null)&&(qty != null)){
                createTransaction(resit, akaunKutHarian, pengguna, kod, qty);
            }
        }
    }

    public void createTransaction(String noResit, String noAcc, Pengguna pengguna, String kod, Integer qty){
            Date now = new Date();
            InfoAudit ia = new InfoAudit();
            int year = Integer.parseInt(sdf.format(now));

            transaksi = new Transaksi();
            transaksi.setCawangan(pengguna.getKodCawangan());
            transaksi.setKodTransaksi(kodTransaksiDAO.findById(kod));
            if(kod.equals(pelan)){
                if(qty > 1){
                    transaksi.setAmaun(new BigDecimal((3*(qty-1))).add(new BigDecimal(5)));
                }else if(qty == 1){
                    transaksi.setAmaun(new BigDecimal(5*qty));
                }
            }
            if(kod.equals(semak_pelan)){
                transaksi.setAmaun(new BigDecimal(6*qty));
            }
            transaksi.setDokumenKewangan(dokumenKewanganDAO.findById(noResit));
            transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
            transaksi.setUntukTahun(year);
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(now);
            transaksi.setInfoAudit(ia);
            transaksi.setAkaunDebit(akaunDAO.findById(noAcc));
            transaksi.setKuantiti(qty);
            transaksi.setBayaranAgensi("N");
            manager.save(transaksi);
    }

    public void displayPayment(String resit){
        dokumenKewangan = new DokumenKewangan();
        dokumenKewangan = dokumenKewanganDAO.findById(resit);
        senaraiKewDok.add(dokumenKewangan);

        String [] n1 = {"dokumenKewangan"};
        Object [] v1 = {dokumenKewangan};
        transList = transaksiDAO.findByEqualCriterias(n1, v1, null);


    }

    public Resolution cetak() throws FileNotFoundException {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        Date now = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmm")).format(now);
        String idKew = getContext().getRequest().getParameter("idKew");
        String documentPath = conf.getProperty("document.path");
        String path = tarikh + File.separator + String.valueOf(idKew);
        reportUtil.generateReport("HSLResitJualanPelan.rdf",
                new String[]{"p_id_kew_dok"},
                new String[]{idKew},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);

    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public List<KodUrusan> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(List<KodUrusan> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public String getAkaunKutHarian() {
        return akaunKutHarian;
    }

    public void setAkaunKutHarian(String akaunKutHarian) {
        this.akaunKutHarian = akaunKutHarian;
    }

    public List<DokumenKewangan> getSenaraiKewDok() {
        return senaraiKewDok;
    }

    public void setSenaraiKewDok(List<DokumenKewangan> senaraiKewDok) {
        this.senaraiKewDok = senaraiKewDok;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<String> getChkbox() {
        return chkbox;
    }

    public void setChkbox(List<String> chkbox) {
        this.chkbox = chkbox;
    }

    public List<Integer> getBil() {
        return bil;
    }

    public void setBil(List<Integer> bil) {
        this.bil = bil;
    }

    public BigDecimal getJumCaraBayar() {
        return jumCaraBayar;
    }

    public void setJumCaraBayar(BigDecimal jumCaraBayar) {
        this.jumCaraBayar = jumCaraBayar;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public BigDecimal getTotalBayaran() {
        return totalBayaran;
    }

    public void setTotalBayaran(BigDecimal totalBayaran) {
        this.totalBayaran = totalBayaran;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }
}
