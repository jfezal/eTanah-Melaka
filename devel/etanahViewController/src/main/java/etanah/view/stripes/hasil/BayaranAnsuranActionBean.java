package etanah.view.stripes.hasil;

import etanah.dao.*;
import etanah.model.*;
import java.text.SimpleDateFormat;
import org.hibernate.Session;
import com.google.inject.Inject;
import etanah.report.ReportUtil;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import etanah.sequence.GeneratorNoResit2;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Wizard;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 * SPOC Khusus for Negeri Sembilan
 * @author abdul.hakim
 */
@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/bayaran_ansuran")
public class BayaranAnsuranActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(BayaranAnsuranActionBean.class);

    private Akaun akaun;
    private Hakmilik hakmilik;
    private Permohonan permohonan;
    private Transaksi transaksi;
    private DokumenKewangan dokumenKewangan;

    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private List<Transaksi> transList = new ArrayList<Transaksi>();
    private List<DokumenKewangan> senaraiKewDokumen = new ArrayList<DokumenKewangan>();
    private List<DokumenKewanganBayaran> senaraiDKB = new ArrayList<DokumenKewanganBayaran>();
    private ArrayList jadual = new ArrayList();
    private ArrayList year = new ArrayList();
    private List<String> tarikhCek = new ArrayList<String>();
    private String bulan = null;
    private String radio = null;
    private String idKewDok = null;
    private static String kodNegeri = null;
    private int tempoh = 0;
    
    private BigDecimal total = new BigDecimal(0);
    private BigDecimal balance = new BigDecimal(0);
    private BigDecimal monthly = new BigDecimal(0);
    private BigDecimal lastPayment = new BigDecimal(0);
    private BigDecimal jumCaraBayar = new BigDecimal(0);
    private BigDecimal bakiAwal = new BigDecimal(0);
    private BigDecimal denda = new BigDecimal(0);
    private BigDecimal bayar = new BigDecimal(0);

    private AkaunDAO akaunDAO;
    private HakmilikDAO hakmilikDAO;
    private CaraBayaranDAO caraBayaranDAO;
    private TransaksiDAO transaksiDAO;
    private KodBankDAO kodBankDAO;
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    private KodAkaunDAO kodAkaunDAO;
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    private DokumenKewanganDAO dokumenKewanganDAO;
    private KodTransaksiDAO kodTransaksiDAO;
    private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    private KodDokumenDAO kodDokumenDAO;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");

    @Inject
    public BayaranAnsuranActionBean(AkaunDAO akaunDAO, HakmilikDAO hakmilikDAO, CaraBayaranDAO caraBayaranDAO,
            TransaksiDAO transaksiDAO, KodBankDAO kodBankDAO, KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO,
            KodCaraBayaranDAO kodCaraBayaranDAO, KodAkaunDAO kodAkaunDAO, KodDokumenDAO kodDokumenDAO,
            KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO, DokumenKewanganDAO dokumenKewanganDAO,
            KodTransaksiDAO kodTransaksiDAO, DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO) {
        this.akaunDAO = akaunDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.caraBayaranDAO = caraBayaranDAO;
        this.transaksiDAO = transaksiDAO;
        this.kodBankDAO = kodBankDAO;
        this.kodCaraBayaranDAO = kodCaraBayaranDAO;
        this.kodAkaunDAO = kodAkaunDAO;
        this.kodStatusTransaksiKewanganDAO = kodStatusTransaksiKewanganDAO;
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.kodTransaksiDAO = kodTransaksiDAO;
        this.dokumenKewanganBayaranDAO = dokumenKewanganBayaranDAO;
        this.kodStatusDokumenKewanganDAO = kodStatusDokumenKewanganDAO;
        this.kodDokumenDAO = kodDokumenDAO;
    }
    @Inject
    KutipanHasilManager manager;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private ReportUtil reportUtil;
    @Inject
//    GeneratorNoResit noResitGenerator;
    private GeneratorNoResit2 noResitGenerator2;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private etanah.kodHasilConfig hasil;
    @Inject
    PenyataPemungutService pp;
    @Inject
    KodKutipanDAO kodKutipDAO;
    @Inject
    private ModKutipService modKutip;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        radio = null;
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "negeri";
        }
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT a FROM etanah.model.Akaun a where a.baki > 0 AND a.ansuran = 'Y' ");
        senaraiAkaun = q.list();
        logger.info("senaraiAkaun.size() : "+senaraiAkaun.size());

        return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran_ansuran_1.jsp");
    }

    @HandlesEvent("Step2")
    public Resolution search() {
        logger.info("idHakmilik : "+hakmilik.getIdHakmilik());
        akaun = getAkaunCukai(hakmilik.getIdHakmilik());
        logger.info("akaun.getNoAkaun() : "+akaun.getNoAkaun());

        Session s = sessionProvider.get();
        Query q = s.createQuery("select a FROM etanah.model.Akaun a where a.noAkaun = :noAkaun AND a.ansuran = 'Y' ");
        q.setString("noAkaun", akaun.getNoAkaun());
        senaraiAkaun = q.list();
        logger.info("senaraiAkaun.size() : "+senaraiAkaun.size());

        return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran_ansuran_1.jsp");
    }

    public Akaun getAkaunCukai(String idHakmilik){
        logger.info("..:: inside getAkaunCukai::..");
        logger.info("idHakmilik : "+idHakmilik);

        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT a FROM etanah.model.Akaun a where a.hakmilik.idHakmilik = :idHakmilik AND a.kodAkaun.kod = 'AC' ");
        q.setString("idHakmilik", idHakmilik);

        return (Akaun) q.uniqueResult();
    }

    @HandlesEvent ("Step3")
    public Resolution collectPayment() throws ParseException {
        logger.info("radio : "+radio);
        hakmilik = hakmilikDAO.findById(radio);
        akaun = getAkaunCukai(hakmilik.getIdHakmilik());
        String kod = null;
        if(kodNegeri.equalsIgnoreCase("melaka")){
            kod="BACT";
        }else{kod="RBYA";}
        Session s = sessionProvider.get();

        Query qTrans = s.createQuery("SELECT t FROM etanah.model.Transaksi t where t.akaunKredit.noAkaun = :noAkaun OR t.akaunDebit.noAkaun = :noAkaun " +
                                                              "order by t.status.kod, t.kodTransaksi.keutamaan,t.untukTahun");
        qTrans.setString("noAkaun", akaun.getNoAkaun());
        senaraiTransaksi = qTrans.list();
//        List<Transaksi>senaraiTr = akaun.getSemuaTransaksi();
//        for (Transaksi t : senaraiTr) {
//            if(t.getAkaunDebit().getNoAkaun().equals(akaun.getNoAkaun())){
//                t.setAmaun(t.getAmaun().multiply(new BigDecimal(1)));
//            }
//            senaraiTransaksi.add(t);
//        }

        for(Transaksi tr : senaraiTransaksi){
            if(tr.getStatus().getKod()!='B')
            total = total.add( tr.getAmaun());
        }
        balance = akaun.getBaki();

        Date now = new Date();
        List<Transaksi> transDebit = akaun.getSenaraiTransaksiDebit();
        List<Transaksi> transKredit = akaun.getSenaraiTransaksiKredit();
        int year = Integer.parseInt(yy.format(now));

        for (Transaksi t : transDebit) {
            bakiAwal = bakiAwal.add(t.getAmaun());
            if((t.getKodTransaksi().getKod().equals("76152"))&&(t.getUntukTahun() == year)){
                denda = t.getAmaun();
            }
        }

        for (Transaksi t : transKredit) {
            bayar = bayar.add(t.getAmaun());
        }

        if(akaun.getAnsuran() == 'Y'){
            Query q = null;
            q = s.createQuery("select mh from etanah.model.HakmilikPermohonan mh where mh.hakmilik.idHakmilik =  :idHakmilik and mh.permohonan.kodUrusan.kod = :kod and "
                    + "mh.permohonan.keputusan.kod = 'L' and mh.permohonan.status <> 'TK'");
            q.setString("idHakmilik", akaun.getHakmilik().getIdHakmilik());
            q.setString("kod", kod);
            List<HakmilikPermohonan> hpList = q.list();
            HakmilikPermohonan hp = hpList.get(0);
            permohonan = new Permohonan();

            permohonan = permohonanDAO.findById(hp.getPermohonan().getIdPermohonan());
            tempoh = permohonan.getTempohBulan();
            table(permohonan.getTempohBulan(), permohonan.getTarikhMula());
        }else{
            tempoh = 2;
        }
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
        return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran_ansuran_2.jsp");
    }

    @HandlesEvent ("Step4")
    public Resolution save() throws ParseException{
        hakmilik = hakmilikDAO.findById(radio);
        akaun = getAkaunCukai(hakmilik.getIdHakmilik());

        DokumenKewangan dk = null;
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        int tahun = Integer.parseInt(yy.format(now));
        InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        String ac = null;
        boolean flag = false;
        String kod = null;

        Session sess = sessionProvider.get();
        Transaction tx = sess.beginTransaction();
        tx = sess.beginTransaction();
            try{
        //        String resit = noResitGenerator.generateNoResit(ctx.getKodNegeri(), caw , pengguna);
                String resit = noResitGenerator2.getAndLockSerialNo(pengguna);
                String mod = modKutip.loadPenyerahFromSession(ctx);
                dk = new DokumenKewangan();
                dk.setIdDokumenKewangan(resit);
                dk.setAmaunBayaran(jumCaraBayar);
                dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
                dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
                dk.setAkaun(akaun);
                dk.setIsuKepada(akaun.getPemegang().getNama());
                dk.setInfoAudit(ia);
                dk.setCawangan(caw);
                dk.setIdKaunter(pengguna.getIdKaunter());
                dk.setTarikhTransaksi(now);
                if (mod != null && mod.length() > 0) dk.setMod(kodKutipDAO.findById(mod.charAt(0)));
                // save cara bayaran

                    ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
                    for (int f=0; f<senaraiCaraBayaran.size(); f++) {
                        CaraBayaran cb = senaraiCaraBayaran.get(f);
                        if(cb.getAmaun() != null){
                            if (cb.getAmaun().intValue() > 0) {
                                KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                                if (!crByr.getKod().equals("T")) {
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
                                if((crByr.getKod().equals("KW"))||(crByr.getKod().equals("WP"))){
                                    cb.setBank(kodBankDAO.findById("PMB"));
                                }
                                cb.setKodCaraBayaran(crByr);
                                cb.setCawangan(caw);
                                cb.setAktif('Y');
                                cb.setInfoAudit(ia);
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
                    idKewDok = dk.getIdDokumenKewangan();
                    dk.setSenaraiBayaran(adkb);
                    manager.saveOrUpdate(dk);

                dokumenKewangan = dokumenKewanganDAO.findById(idKewDok);
                Session s = sessionProvider.get();
                Query q = s.createQuery("SELECT a FROM etanah.model.Akaun a where a.kodAkaun.kod = :kodAkaun AND a.cawangan.kod = :caw ");
                q.setString("kodAkaun", hasil.getProperty("akaunKutipanHarian"));
                q.setString("caw", pengguna.getKodCawangan().getKod());

                Akaun akh =  (Akaun) q.uniqueResult();

                BigDecimal bal = akh.getBaki().add(jumCaraBayar);
                akh.setBaki(bal);
                manager.saveOrUpdate(akh);                              //update baki Akaun Kutipan Harian;

        //        Transaksi Kredit
                Query qTransKredit = s.createQuery("SELECT t FROM etanah.model.Transaksi t where t.akaunKredit.noAkaun = :noAkaun AND t.status.kod = 'T' " +
                                                                      "order by t.kodTransaksi.keutamaan");
                qTransKredit.setString("noAkaun", akaun.getNoAkaun());
                List<Transaksi> senaraiTransaksiKredit =qTransKredit.list();

        //        Transaksi Debit
                Query qTransDebit = s.createQuery("SELECT t FROM etanah.model.Transaksi t where t.akaunDebit.noAkaun = :noAkaun AND t.status.kod = 'A' " +
                                                                    "AND t.amaun > 0 order by t.kodTransaksi.keutamaan");
                qTransDebit.setString("noAkaun", akaun.getNoAkaun());
                List<Transaksi> senaraiTransaksiDebit =qTransDebit.list();

                BigDecimal bakiBayar = jumCaraBayar;
                BigDecimal bakiTrans = new BigDecimal(0);

                List<Transaksi> listTemp = new ArrayList<Transaksi>();
                logger.info("listTemp.size() : "+listTemp.size());

                if(senaraiTransaksiKredit.size() == 0){
                    logger.info("size == 00");
                    for(Transaksi t : senaraiTransaksiDebit){
                        transaksi = new Transaksi();

                        transaksi.setCawangan(caw);
                        transaksi.setDokumenKewangan(dokumenKewangan);
                        transaksi.setKodTransaksi(t.getKodTransaksi());
                        transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                        transaksi.setUntukTahun(t.getUntukTahun());
                        transaksi.setAkaunDebit(akh);
                        transaksi.setAkaunKredit(akaun);
                        transaksi.setTahunKewangan(tahun);
                        transaksi.setPerihal("1");
                        transaksi.setBayaranAgensi("N");
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(now);
                        transaksi.setInfoAudit(ia);

                        if(bakiBayar.compareTo(t.getAmaun()) == 1){
                            transaksi.setAmaun(t.getAmaun());
                            bakiBayar = bakiBayar.subtract(t.getAmaun());
                            manager.save(transaksi);
                        }else{
                            transaksi.setAmaun(bakiBayar);
                            manager.save(transaksi);
                            break;
                        }
                    }
                } else {
                    for (Transaksi t : senaraiTransaksiDebit) {
                        BigDecimal temp = new BigDecimal (0);

                        for (Transaksi tr : senaraiTransaksiKredit) {
                            if ((tr.getKodTransaksi().getKod().equals(t.getKodTransaksi().getKod())) && (tr.getUntukTahun() == t.getUntukTahun())) {
                                temp = temp.add(tr.getAmaun());
                                bakiTrans = t.getAmaun().subtract(temp);
                                kod = t.getKodTransaksi().getKod();
                                listTemp.remove(t);
                            }
                        }
                        if (temp.compareTo(t.getAmaun()) < 0) {
                            listTemp.add(t);
                        }
                    }
                    installment(listTemp, bakiTrans, pengguna, ia, akh, now, dokumenKewangan, kod, tahun);
                }
                DokumenKewangan dok = dokumenKewanganDAO.findById(resit);
                pp.savePenyataPemungut(dok);

        //         update akaun Akaun Cukai Tanah: Ansuran == 'Y'
                BigDecimal m = akaun.getBaki().subtract(jumCaraBayar);
                akaun.setBaki(akaun.getBaki().subtract(jumCaraBayar));
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(now);
                if(m.compareTo(new BigDecimal(0)) <= 0){
                    akaun.setAnsuran('T');
                }
                akaun.setInfoAudit(ia);
                manager.saveOrUpdate(akaun);
                caraBayaran();
                preview();
            }catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                tx.rollback();
                noResitGenerator2.rollbackAndUnlockSerialNo(pengguna);
                addSimpleError("Pembayaran tidak berjaya.");
            }finally{
                logger.info("finally");
                noResitGenerator2.commitAndUnlockSerialNo(pengguna);
            }
        return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran_ansuran_3.jsp");
    }

    public void installment(List<Transaksi> listTemp, BigDecimal bakiTrans, Pengguna pengguna, InfoAudit ia, Akaun akh, Date now,
                                        DokumenKewangan dk, String kod, int tahun) {
        BigDecimal bakiBayar = jumCaraBayar;
        BigDecimal tempValue = new BigDecimal(0);
        
        List<String> resit = new ArrayList<String>();
        
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT DISTINCT(t.dokumenKewangan.idDokumenKewangan) FROM etanah.model.Transaksi t where t.akaunKredit.noAkaun = :akaun");
        q.setString("akaun", akaun.getNoAkaun());
        resit = q.list();
        
        logger.info("resit.size() : "+resit.size());
        int bilangan = resit.size();
        
        for (Transaksi t : listTemp) {
            transaksi = new Transaksi();
            tempValue = t.getAmaun();
            if(bakiTrans.compareTo(new BigDecimal(0)) > 0){
                tempValue = bakiTrans;
            }

            if ((bakiBayar.compareTo(tempValue) == 0) || (bakiBayar.compareTo(tempValue) == -1)) {
                transaksi.setCawangan(pengguna.getKodCawangan());
                transaksi.setDokumenKewangan(dk);
                transaksi.setKodTransaksi(t.getKodTransaksi());
                transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                transaksi.setUntukTahun(t.getUntukTahun());
                transaksi.setTahunKewangan(tahun);
                transaksi.setAkaunDebit(akh);
                transaksi.setAkaunKredit(akaun);
                transaksi.setPerihal(""+bilangan);
                transaksi.setBayaranAgensi("N");
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(now);
                transaksi.setInfoAudit(ia);
                transaksi.setAmaun(bakiBayar);

                bakiBayar = bakiBayar.subtract(tempValue);
                bakiTrans = bakiTrans.subtract(tempValue);
                if(bakiTrans.compareTo(new BigDecimal(0)) < 0){
                    bakiTrans = new BigDecimal(0);
                }
                manager.save(transaksi);
                if(bakiBayar.compareTo(new BigDecimal(0)) <= 0){
                  break;
                }
            }

            if(bakiBayar.compareTo(tempValue) == 1){
                transaksi.setCawangan(pengguna.getKodCawangan());
                transaksi.setDokumenKewangan(dk);
                transaksi.setKodTransaksi(t.getKodTransaksi());
                transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                transaksi.setUntukTahun(t.getUntukTahun());
                transaksi.setTahunKewangan(tahun);
                transaksi.setAkaunDebit(akh);
                transaksi.setAkaunKredit(akaun);
                transaksi.setPerihal(""+bilangan);
                transaksi.setBayaranAgensi("N");
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(now);
                transaksi.setInfoAudit(ia);
                transaksi.setAmaun(tempValue);

                manager.save(transaksi);
                bakiBayar = bakiBayar.subtract(tempValue);
                bakiTrans = bakiTrans.subtract(tempValue);
                if(bakiBayar.compareTo(new BigDecimal(0)) <= 0){
                  break;
                }
            }
        }
        if(bakiBayar.compareTo(new BigDecimal(0)) > 0){
            transaksi = new Transaksi();
            String t = new SimpleDateFormat("yyyy").format(now);

            transaksi.setCawangan(pengguna.getKodCawangan());
                transaksi.setDokumenKewangan(dk);
                transaksi.setKodTransaksi(kodTransaksiDAO.findById(hasil.getProperty("cukaiTanahSemasa")));
                transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                transaksi.setUntukTahun(Integer.parseInt(t));
                transaksi.setTahunKewangan(tahun);
                transaksi.setAkaunDebit(akh);
                transaksi.setAkaunKredit(akaun);
                transaksi.setPerihal(""+bilangan);
                transaksi.setBayaranAgensi("N");
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(now);
                transaksi.setInfoAudit(ia);
                transaksi.setAmaun(bakiBayar);
                manager.save(transaksi);
        }
    }

    public void preview(){
        total = new BigDecimal(0);
        hakmilik = hakmilikDAO.findById(radio);
        akaun = getAkaunCukai(hakmilik.getIdHakmilik());
        
        dokumenKewangan = dokumenKewanganDAO.findById(idKewDok);
        senaraiKewDokumen.add(dokumenKewangan);
        total = dokumenKewangan.getAmaunBayaran();
    }

    public void caraBayaran(){
        senaraiDKB = new ArrayList<DokumenKewanganBayaran>();
        dokumenKewangan = dokumenKewanganDAO.findById(idKewDok);
        String [] n1 = {"dokumenKewangan"};
        Object [] v1 = {getDokumenKewangan()};
        senaraiDKB = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);
    }

    public void table(int tt, Date d) throws ParseException{
        BigDecimal x = akaun.getAmaunMatang();
        BigDecimal temp = x.remainder(new BigDecimal(tt));
        if(temp.compareTo(new BigDecimal(0)) == 0){
            monthly = x.divide(new BigDecimal(tt));
            lastPayment = monthly;
        }
        if(temp.compareTo(new BigDecimal(0)) == 1){
            int dd = tt-1;
            double m = Math.ceil(x.doubleValue()/tt);
            monthly = new BigDecimal(m);
            lastPayment = x.subtract(monthly.multiply(new BigDecimal(dd)));
        }

        int month = d.getMonth();

        for(int i=0;i<tt;i++){
            int bln = month +i;
            String t = new SimpleDateFormat("yyyy").format(d);
            int tahun = Integer.parseInt(t);
            if(bln > 11){
                bln = bln - 12;
                tahun = tahun + 1;
            }

            if(bln == 0)
                bulan = "Januari";
            if(bln == 1)
                bulan = "Februari";
            if(bln == 2)
                bulan = "Mac";
            if(bln == 3)
                bulan = "April";
            if(bln == 4)
                bulan = "Mei";
            if(bln == 5)
                bulan = "Jun";
            if(bln == 6)
                bulan = "Julai";
            if(bln == 7)
                bulan = "Ogos";
            if(bln == 8)
                bulan = "September";
            if(bln == 9)
                bulan = "Oktober";
            if(bln == 10)
                bulan = "November";
            if(bln == 11)
                bulan = "Disember";

            jadual.add(bulan);
            year.add(tahun);
        }
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public ArrayList getJadual() {
        return jadual;
    }

    public void setJadual(ArrayList jadual) {
        this.jadual = jadual;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public List<DokumenKewanganBayaran> getSenaraiDKB() {
        return senaraiDKB;
    }

    public void setSenaraiDKB(List<DokumenKewanganBayaran> senaraiDKB) {
        this.senaraiDKB = senaraiDKB;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public ArrayList getYear() {
        return year;
    }

    public void setYear(ArrayList year) {
        this.year = year;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public int getTempoh() {
        return tempoh;
    }

    public void setTempoh(int tempoh) {
        this.tempoh = tempoh;
    }

    public BigDecimal getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(BigDecimal lastPayment) {
        this.lastPayment = lastPayment;
    }

    public BigDecimal getMonthly() {
        return monthly;
    }

    public void setMonthly(BigDecimal monthly) {
        this.monthly = monthly;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public BigDecimal getJumCaraBayar() {
        return jumCaraBayar;
    }

    public void setJumCaraBayar(BigDecimal jumCaraBayar) {
        this.jumCaraBayar = jumCaraBayar;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

    public List<String> getTarikhCek() {
        return tarikhCek;
    }

    public void setTarikhCek(List<String> tarikhCek) {
        this.tarikhCek = tarikhCek;
    }

    public BigDecimal getBakiAwal() {
        return bakiAwal;
    }

    public void setBakiAwal(BigDecimal bakiAwal) {
        this.bakiAwal = bakiAwal;
    }

    public BigDecimal getBayar() {
        return bayar;
    }

    public void setBayar(BigDecimal bayar) {
        this.bayar = bayar;
    }

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public List<DokumenKewangan> getSenaraiKewDokumen() {
        return senaraiKewDokumen;
    }

    public void setSenaraiKewDokumen(List<DokumenKewangan> senaraiKewDokumen) {
        this.senaraiKewDokumen = senaraiKewDokumen;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
