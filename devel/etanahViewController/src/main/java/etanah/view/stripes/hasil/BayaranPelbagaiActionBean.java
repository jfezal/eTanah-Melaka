package etanah.view.stripes.hasil;

import java.io.*;
import java.text.*;
import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import org.hibernate.*;
import etanah.sequence.*;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import etanah.report.ReportUtil;
import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.*;
import etanah.view.etanahActionBeanContext;

/**
 *
 * @author abdul.hakim
 */
@Wizard(startEvents = {"showForm", "doCheckHakmilik", "filterByKodHasil"})
@UrlBinding("/hasil/bayaran_pelbagai")
public class BayaranPelbagaiActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(BayaranPelbagaiActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/bayaran_pelbagai.jsp";
    private static final String PAYMENT_VIEW = "/WEB-INF/jsp/hasil/bayaran_pelbagai_1.jsp";
    private static final String DISPLAY_VIEW = "/WEB-INF/jsp/hasil/bayaran_pelbagai_2.jsp";
    private KodUrusan kodUrusan;
    private DokumenKewangan dokumenKewangan;
    private Transaksi transaksi;
    private KodUrusanDAO kodUrusanDAO;
    private DokumenKewanganDAO dokumenKewanganDAO;
    private KodAkaunDAO kodAkaunDAO;
    private TransaksiDAO transaksiDAO;
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    private KodDokumenDAO kodDokumenDAO;
    private KodBankDAO kodBankDAO;
    private AkaunDAO akaunDAO;
    private String urusan = "Bayaran Pelbagai";
    private int bil = 0;
    private static String negeri;
    private BigDecimal jumlah;
    private BigDecimal jumlahBayar;
    private List<KodUrusan> senaraiKodUrusan = new ArrayList<KodUrusan>();
    private List<KodUrusan> senaraiUrusan = new ArrayList<KodUrusan>();
    private List<KodUrusan> listUrusan = new ArrayList<KodUrusan>();
//    private List<KodUrusan> pecahanUrusan = new ArrayList<KodUrusan>();
    private List<Transaksi> listTrans = new ArrayList<Transaksi>();
    private List<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private List<Integer> kuantiti = new ArrayList<Integer>();
    private List<Integer> amount = new ArrayList<Integer>();
    private List<String> tarikhCek = new ArrayList<String>();
//    private List<String> testing = new ArrayList<String>();
    private List<DokumenKewanganBayaran> dkbList = new ArrayList<DokumenKewanganBayaran>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    private String[] senaraiBayaran;
    private List<KodTransaksi> senaraiKodTransaksi = new ArrayList<KodTransaksi>();
    private String mod;
    private BigDecimal returnBalance = new BigDecimal(0);
    private static String report = "";
    private boolean balanceFlag = false;
    private String penyerahNegeri;

    @Inject
    public BayaranPelbagaiActionBean(KodUrusanDAO kodUrusanDAO, DokumenKewanganDAO dokumenKewanganDAO, AkaunDAO akaunDAO,
            KodCaraBayaranDAO kodCaraBayaranDAO, KodBankDAO kodBankDAO, KodAkaunDAO kodAkaunDAO,
            KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO, KodDokumenDAO kodDokumenDAO,
            KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO, TransaksiDAO transaksiDAO,
            DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO) {
        this.kodUrusanDAO = kodUrusanDAO;
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.kodCaraBayaranDAO = kodCaraBayaranDAO;
        this.kodBankDAO = kodBankDAO;
        this.kodStatusDokumenKewanganDAO = kodStatusDokumenKewanganDAO;
        this.kodDokumenDAO = kodDokumenDAO;
        this.kodAkaunDAO = kodAkaunDAO;
        this.akaunDAO = akaunDAO;
        this.kodStatusTransaksiKewanganDAO = kodStatusTransaksiKewanganDAO;
        this.transaksiDAO = transaksiDAO;
        this.dokumenKewanganBayaranDAO = dokumenKewanganBayaranDAO;
    }
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KutipanHasilManager manager;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
//    GeneratorNoResit noResitGenerator;
    GeneratorNoResit2 noResitGenerator2;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PenyataPemungutService pp;
    @Inject
    KutipanHasilActionBean kut;
    @Inject
    KodKutipanDAO kodKutipDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    private ModKutipService modKutip;
    @Inject
    GeneratorNoFail generatorNoFail;
    @Inject
    PermohonanDAO permohonanDAO;

    @DefaultHandler
    public Resolution showForm() {

        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "melaka";
            report = "HSLResitPelbagai_MLK.rdf";
            sql = "SELECT ku FROM etanah.model.KodUrusan ku "
                    + "WHERE ku.kodTransaksi.kod in ('61403','61499','61500','61600','61601','61602','61899','71599','71708','71711','71713','71740',"
                    + "'71803','71899','72440','72454','72455','72456','72458','72460','72481','72482','72483','72484',"
                    + "'72485','72486','72487','72488','72489','72490','72499','73105','73151','73153','73301','73601',"
                    + "'73604','73699','74254','74256','74257','74258','74999','76159','76160','76199','81199','58007',"
                    + "'58010','58012','79501','79503','79552','79564','79659','79660','79661','79662','79737','50129',"
                    + "'40171','50129','51522','51529','51530','51531','51532','74201','72467','73999',"
                    + "'76128','72494','77405', '82504','76127','79505') "
                    + "ORDER BY ku.kodTransaksi.kod ";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            negeri = "negeri";
            report = "HSLResitPelbagai.rdf";
            sql = "SELECT ku FROM etanah.model.KodUrusan ku "
                    + "WHERE ku.kodTransaksi.kod IN ('51113', '53997', '53999', '61403', '61999', '65096', '71711', '71799', '71784', '71803', '72199', '72499', "
                    + "'73151', '73199', '73601', '73605', '76105','74999', '76152', '76199', '79501', '89999', '99020', '99021', '99022', '99023', '99024', '99025', '99026', '99027') "
                    + "AND ku.jabatan.kod NOT IN ('16','22') ORDER BY ku.kodTransaksi.kod ";
        }
//        Session s = sessionProvider.get();
//        Query q = s.createQuery("from etanah.model.KodUrusan u where u.urusanBayaran = :nama order by u.kod asc");
//        q.setCharacter("nama", 'Y');
//
//        senaraiUrusan = q.list();
//
//        int x = showReports().length;
//        for (int i = 0; i < x; i++) {
//            String kod = senaraiBayaran[i];
//            KodTransaksi kodTransaksi = kodTransaksiDAO.findById(kod);
//            senaraiKodTransaksi.add(kodTransaksi);
//        }
        q = s.createQuery(sql);
        senaraiKodUrusan = q.list();

        collectUrusan();

        return new ForwardResolution(DEFAULT_VIEW);
    }

    private String[] showReports() {

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            senaraiBayaran = new String[]{"40171", "51530", "51531",
                "51532", "79505", "79502", "81103",
                "81199", "74999", "79564", "72438", "51522",
                "79503", "73301", "71720", "71708", "71713",
                "71719", "79599", "74201", "81101", "72467"};

        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            senaraiBayaran = new String[]{"51113", "53997", "53999", "61403", "61999", "65096", "71711",
                "71784", "71799", "71803", "72199", "72499",
                "73151", "73199", "73601", "73605", "74999",
                "76152", "76199", "79501", "99020", "99021",
                "99022", "99023", "99024", "99025", "99026", "99027"};

        }
        return senaraiBayaran;
    }

//      onchange function
//    public Resolution filterByKodHasil(){
//        LOG.info("kodHasil :"+getContext().getRequest().getParameter("kodHasil"));
//        String kodHasil = getContext().getRequest().getParameter("kodHasil");
//        LOG.info("kodHasil :"+kodHasil);
//        String sql = null;
//        Session s = sessionProvider.get();
//        Query q = null;
////        if(kodHasil == null || kodHasil.equals("") || kodHasil.equals("0")){
////            sql = "SELECT ku FROM KodUrusan ku WHERE ku.kodTransaksi.kod = 'Y' and ku.urusanKaunter = 'Y' order by ku.kod";
////            q = s.createQuery(sql);
////        }else{
//            sql = "SELECT ku FROM KodUrusan ku WHERE ku.kodTransaksi.kod = :kod and ku.aktif = 'Y' order by ku.kod";
//            q = s.createQuery(sql);
//            q.setString("kod", kodHasil);
////        }
//        pecahanUrusan = q.list();
//        LOG.info("pecahanUrusan.size() : "+pecahanUrusan.size());
//        return new ForwardResolution(DEFAULT_VIEW).addParameter("popup", "true");
//    }
    public Resolution collectUrusan() {
        bil += 8;
        if ((listUrusan == null) || (listTrans == null)) {
            listUrusan = new ArrayList<KodUrusan>();
        }
        if ((listUrusan.size() == 0) || (listTrans.size() == 0)) {
            for (int i = 0; i < bil; i++) {
                KodUrusan kod = new KodUrusan();
                Transaksi kodTr = new Transaksi();
                listUrusan.add(kod);
                listTrans.add(kodTr);
            }
            bil = listUrusan.size();
        } else {
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

    public Resolution doCheckHakmilik() {
        String kUrusan = getContext().getRequest().getParameter("kod");
        String results = "";
        kodUrusan = kodUrusanDAO.findById(kUrusan);
        if (kodUrusan != null) {
            String caj = kodUrusan.getCaj().toString();
            results = caj;
        } else {
            results = "xx";
        }
        return new StreamingResolution("text/plain", results);
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

    public Resolution payment() {
        collectPayment();
        return new ForwardResolution(PAYMENT_VIEW);
    }

    public Resolution bayar() throws ParseException {
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
//            if(mod.equalsIgnoreCase("pelbagai")){
//                for (int x=0; x< listUrusan.size(); x++) {
//
//
//                    KodUrusan ursn = new KodUrusan();
//                    ursn = listUrusan.get(x);
//                    if(!ursn.getKod().equals("0")){
//                        Integer bil = kuantiti.get(x);
//                        BigDecimal amaun = listUrusan.get(x).getCaj().multiply(new BigDecimal(bil));
//
//                        dk = new DokumenKewangan();
//                        dk = createReceipt(ctx, caw, pengguna, now, ia, amaun);
//
//                        transaksi = new Transaksi();
//                        kodUrusan = kodUrusanDAO.findById(ursn.getKod());
//
//                        transaksi.setCawangan(caw);
//                        transaksi.setKodTransaksi(kodUrusan.getKodTransaksi());
//                        transaksi.setAmaun(listUrusan.get(x).getCaj().multiply(new BigDecimal(bil)));
//                        transaksi.setDokumenKewangan(dk);
//                        transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
//                        transaksi.setInfoAudit(ia);
//                        transaksi.setUntukTahun(year);
//                        transaksi.setTahunKewangan(year);
//                        transaksi.setAkaunDebit(akh);
//                        transaksi.setKuantiti(bil);
//                        transaksi.setKodUrusan(kodUrusan.getKod());
//
//                        manager.save(transaksi);
//                        senaraiDK.add(dk);
//                    }
//                }
//            }
//            if (mod.equalsIgnoreCase("daerah")) {
            for (int x = 0; x < listUrusan.size(); x++) {
                KodUrusan ursn = new KodUrusan();
                ursn = kodUrusanDAO.findById(listUrusan.get(x).getKod());
                Permohonan mohon = new Permohonan();
                if (ursn != null) {
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
                    dk = createReceipt(ctx, caw, pengguna, now, ia, a);

                    transaksi = new Transaksi();
                    transaksi.setAmaun(a);
                    transaksi.setKodTransaksi(kt);
                    transaksi.setKodUrusan(ursn.getKod());
                    transaksi.setCawangan(caw);
                    transaksi.setDokumenKewangan(dk);
                    transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                    transaksi.setInfoAudit(ia);
                    transaksi.setUntukTahun(year);
                    transaksi.setTahunKewangan(year);
                    transaksi.setBayaranAgensi("N");
                    transaksi.setAkaunDebit(akh);
                    transaksi.setKuantiti(qty);
                    transaksi.setPerihal(ursn.getNama());

                    if ((ursn.getKodPerserahan() != null) && (ursn.getKodPerserahan().getKod().equals("MD"))) {
                        mohon = createNoFile(ursn, pengguna, dk, transaksi, ia);
                        transaksi.setPermohonan(mohon);
                    }

                    manager.save(transaksi);
                    senaraiDK.add(dk);
                }
            }
//            }
            pp.savePenyataPemungut1(senaraiDK, dk);
            transaksiList(senaraiDK);
            caraBayar(dk.getIdDokumenKewangan());
            tx.commit();
            addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        } catch (Exception ex) {
            noResitGenerator2.rollbackAndUnlockSerialNo(pengguna);
            LOG.error(ex.getMessage(), ex);
            tx.rollback();
            addSimpleError("Pembayaran tidak berjaya.");
        } finally {
            noResitGenerator2.commitAndUnlockSerialNo(pengguna);
        }
        return new ForwardResolution(DISPLAY_VIEW);
    }

    public DokumenKewangan createReceipt(etanahActionBeanContext ctx, KodCawangan caw, Pengguna pengguna,
            Date now, InfoAudit ia, BigDecimal amaun) throws ParseException {
        DokumenKewangan dk = new DokumenKewangan();

        mod = modKutip.loadPenyerahFromSession(ctx);

//        String resit = noResitGenerator.generateNoResit(ctx.getKodNegeri(), caw, pengguna);
        String resit = noResitGenerator2.getAndLockSerialNo(pengguna);
        // resit
        dk = new DokumenKewangan();
        dk.setIdDokumenKewangan(resit);
        ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
        dk.setAmaunBayaran(amaun);
        dk.setNoRujukan(dokumenKewangan.getNoRujukan());
        dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
        dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
        dk.setIsuKepada(dokumenKewangan.getIsuKepada());
        if (dokumenKewangan.getIsuKepadaAlamat1() != null) {
            dk.setIsuKepadaAlamat1(dokumenKewangan.getIsuKepadaAlamat1());
        }
        if (dokumenKewangan.getIsuKepadaAlamat2() != null) {
            dk.setIsuKepadaAlamat2(dokumenKewangan.getIsuKepadaAlamat2());
        }
        if (dokumenKewangan.getIsuKepadaAlamat3() != null) {
            dk.setIsuKepadaAlamat3(dokumenKewangan.getIsuKepadaAlamat3());
        }
        if (dokumenKewangan.getIsuKepadaAlamat4() != null) {
            dk.setIsuKepadaAlamat4(dokumenKewangan.getIsuKepadaAlamat4());
        }
        dk.setIsuKepadaPoskod(dokumenKewangan.getIsuKepadaPoskod());
        dk.setIsuKepadaNegeri(dokumenKewangan.getIsuKepadaNegeri());
        if (dokumenKewangan.getIsuKepadaEmail() != null);
        dk.setIsuKepadaEmail(dokumenKewangan.getIsuKepadaEmail());
        if (dokumenKewangan.getIsuKepadaNoTelefon1() != null);
        dk.setIsuKepadaNoTelefon1(dokumenKewangan.getIsuKepadaNoTelefon1());
        KodNegeri kn = kodNegeriDAO.findById(penyerahNegeri);
        dk.setIsuKepadaNegeri(kn);
        dk.setInfoAudit(ia);
        dk.setCawangan(caw);
        if (mod != null && mod.length() > 0) {
            dk.setMod(kodKutipDAO.findById(mod.charAt(0)));
        }
        dk.setTarikhTransaksi(now);
        dk.setIdKaunter(pengguna.getIdKaunter());

        // save cara bayaran
        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
        for (int f = 0; f < senaraiCaraBayaran.size(); f++) {
            CaraBayaran cb = senaraiCaraBayaran.get(f);
            if (cb.getAmaun() != null) {
                BigDecimal amaun1 = cb.getAmaun(); 
                if (cb.getAmaun().intValue() > 0) {
                    KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                    if ((!crByr.getKod().equals("T"))) {
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
//                    if ((crByr.getKod().equals("KW")) || (crByr.getKod().equals("WP"))) {
//                        cb.setBank(kodBankDAO.findById("PMB"));
//                    }
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
                                if (jumlahBayar.compareTo(jumlah) == 1) {
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
                } else if (amaun1 != null) {
                    KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                    if ((crByr.getKod().equals("CB")) || (crByr.getKod().equals("CC")) || (crByr.getKod().equals("CT"))
                            || (crByr.getKod().equals("CL")) || (crByr.getKod().equals("IB")) || (crByr.getKod().equals("BC"))) {
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
                                if (jumlahBayar.compareTo(jumlah) == 1) {
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

    public Permohonan createNoFile(KodUrusan ku, Pengguna pengguna, DokumenKewangan dk, Transaksi trans, InfoAudit ia) {
        Permohonan mohon = new Permohonan();
        KodCawangan caw = pengguna.getKodCawangan();

        mohon.setIdPermohonan(generatorNoFail.generate(conf.getProperty("kodNegeri"), caw, ku));
        mohon.setCawangan(pengguna.getKodCawangan());
        mohon.setKodUrusan(ku);
        mohon.setTransaksi(trans);
        mohon.setPenyerahNama(dk.getIsuKepada());
        mohon.setPenyerahAlamat1(dk.getIsuKepadaAlamat1());
        mohon.setPenyerahAlamat2(dk.getIsuKepadaAlamat2());
        mohon.setPenyerahAlamat3(dk.getIsuKepadaAlamat3());
        mohon.setPenyerahAlamat4(dk.getIsuKepadaAlamat4());
        mohon.setPenyerahPoskod(dk.getIsuKepadaPoskod());
        mohon.setPenyerahNegeri(dk.getIsuKepadaNegeri());
        mohon.setStatus("TK");
        mohon.setInfoAudit(ia);

        permohonanDAO.save(mohon);
        return mohon;
    }

    public Resolution kembali() {
        return new RedirectResolution(BayaranPelbagaiActionBean.class);
    }

    public void transaksiList(List<DokumenKewangan> senaraiDK) {
        List<Transaksi> listTr = new ArrayList<Transaksi>();
        for (DokumenKewangan dk : senaraiDK) {
            dokumenKewangan = new DokumenKewangan();
            dokumenKewangan = dokumenKewanganDAO.findById(dk.getIdDokumenKewangan());
            String[] n1 = {"dokumenKewangan"};
            Object[] v1 = {dokumenKewangan};
            List<Transaksi> listT = transaksiDAO.findByEqualCriterias(n1, v1, null);
            listTr.addAll(listT);
        }
        senaraiTransaksi = listTr;
    }

    public void caraBayar(String resit) {
        dokumenKewangan = dokumenKewanganDAO.findById(resit);
        String[] n1 = {"dokumenKewangan"};
        Object[] v1 = {dokumenKewangan};
        dkbList = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);
    }

    public String akaunKutipanHarian(Pengguna pguna) {
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
        Akaun ak = akaunDAO.findById(noAkaun);
        ak.setBaki(ak.getBaki().add(jumlahBayar));
        manager.saveOrUpdate(ak);
        return noAkaun;
    }

    public Resolution cetakResit() throws FileNotFoundException {
        Date now = new Date();
        File f = null;
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();

        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        String idKew = getContext().getRequest().getParameter("idKew");

        String documentPath = conf.getProperty("document.path");
        String path = tarikh + File.separator + String.valueOf(idKew);
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            reportUtil.generateReport("HSLResitPelbagai_MLK.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{idKew},
                    documentPath + path, null);
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            reportUtil.generateReport("HSLResitPelbagai.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{idKew},
                    documentPath + path, null);
        }

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public List<KodUrusan> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(List<KodUrusan> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public List<KodUrusan> getListUrusan() {
        return listUrusan;
    }

    public void setListUrusan(List<KodUrusan> listUrusan) {
        this.listUrusan = listUrusan;
    }

    public List<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(List<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public List<Integer> getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(List<Integer> kuantiti) {
        this.kuantiti = kuantiti;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getJumlahBayar() {
        return jumlahBayar;
    }

    public void setJumlahBayar(BigDecimal jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }

    public List<DokumenKewanganBayaran> getDkbList() {
        return dkbList;
    }

    public void setDkbList(List<DokumenKewanganBayaran> dkbList) {
        this.dkbList = dkbList;
    }

    public List<String> getTarikhCek() {
        return tarikhCek;
    }

    public void setTarikhCek(List<String> tarikhCek) {
        this.tarikhCek = tarikhCek;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public String[] getSenaraiBayaran() {
        return senaraiBayaran;
    }

    public void setSenaraiBayaran(String[] senaraiBayaran) {
        this.senaraiBayaran = senaraiBayaran;
    }

    public List<KodTransaksi> getSenaraiKodTransaksi() {
        return senaraiKodTransaksi;
    }

    public void setSenaraiKodTransaksi(List<KodTransaksi> senaraiKodTransaksi) {
        this.senaraiKodTransaksi = senaraiKodTransaksi;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public List<Integer> getAmount() {
        return amount;
    }

    public void setAmount(List<Integer> amount) {
        this.amount = amount;
    }

    public List<Transaksi> getListTrans() {
        return listTrans;
    }

    public void setListTrans(List<Transaksi> listTrans) {
        this.listTrans = listTrans;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public BigDecimal getReturnBalance() {
        return returnBalance;
    }

    public void setReturnBalance(BigDecimal returnBalance) {
        this.returnBalance = returnBalance;
    }

    public boolean isBalanceFlag() {
        return balanceFlag;
    }

    public void setBalanceFlag(boolean balanceFlag) {
        this.balanceFlag = balanceFlag;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public String getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(String penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public List<KodUrusan> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(List<KodUrusan> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }
}
