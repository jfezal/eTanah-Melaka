package etanah.view.stripes.hasil;

import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import java.math.BigDecimal;
import com.google.inject.Inject;
import java.text.SimpleDateFormat;
import able.stripes.AbleActionBean;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoResit;
import etanah.sequence.GeneratorNoResit2;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import org.hibernate.Transaction;
import java.text.ParseException;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
/**
 *SPOC Khusus untuk Melaka
 * @author abdul.hakim
 */
@Wizard (startEvents = {"Step1"})
@UrlBinding("/hasil/ansuran")
public class AnsuranActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(AnsuranActionBean.class);
    private static final boolean debug = LOG.isDebugEnabled();

    private Akaun akaun;
    private Hakmilik hakmilik;
    private Transaksi transaksi;
    private KodAkaun kodAkaun;
    private DokumenKewangan dokumenKewangan;
    private Permohonan permohonan;

    private AkaunDAO akaunDAO;
    private HakmilikDAO hakmilikDAO;
    private CaraBayaranDAO caraBayaranDAO;
    private TransaksiDAO transaksiDAO;
    private KodDokumenDAO kodDokumenDAO;
    private KodBankDAO kodBankDAO;
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    private KodAkaunDAO kodAkaunDAO;
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    private DokumenKewanganDAO dokumenKewanganDAO;
    private KodTransaksiDAO kodTransaksiDAO;
    private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;

    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private List<Transaksi> transList = new ArrayList<Transaksi>();
    private List<DokumenKewanganBayaran> senaraiDKB = new ArrayList<DokumenKewanganBayaran>();

    private boolean flag = false;
    private String radio;
    private double jumCaraBayar;
    private double total;
    private String noAkaun;
    private String idKewDok;
    private static String kodNegeri;
    private ArrayList jadual = new ArrayList();
    private ArrayList year = new ArrayList();
    private int tempoh;
    private double firstPayment;
    private BigDecimal monthly = new BigDecimal(0);
    private BigDecimal lastPayment = new BigDecimal(0);
    private String bulan = null;
    private BigDecimal balance = new BigDecimal(0);
    private String mod = "";

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Inject
    public AnsuranActionBean(AkaunDAO akaunDAO, HakmilikDAO hakmilikDAO, CaraBayaranDAO caraBayaranDAO,
                             TransaksiDAO transaksiDAO, KodDokumenDAO kodDokumenDAO, KodBankDAO kodBankDAO,
                             KodCaraBayaranDAO kodCaraBayaranDAO, KodAkaunDAO kodAkaunDAO,
                             KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO, DokumenKewanganDAO dokumenKewanganDAO,
                             KodTransaksiDAO kodTransaksiDAO, DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO) {
        this.akaunDAO = akaunDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.caraBayaranDAO = caraBayaranDAO;
        this.transaksiDAO = transaksiDAO;
        this.kodDokumenDAO = kodDokumenDAO;
        this.kodBankDAO = kodBankDAO;
        this.kodCaraBayaranDAO = kodCaraBayaranDAO;
        this.kodAkaunDAO = kodAkaunDAO;
        this.kodStatusTransaksiKewanganDAO = kodStatusTransaksiKewanganDAO;
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.kodTransaksiDAO = kodTransaksiDAO;
        this.dokumenKewanganBayaranDAO = dokumenKewanganBayaranDAO;
    }

    @Inject
    private ModKutipService modKutip;
    
    @Inject
    KodKutipanDAO kodKutipDAO;
    
    @Inject
    KutipanHasilManager manager;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    private PermohonanDAO permohonanDAO;

    @Inject
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;

    @Inject
    private ReportUtil reportUtil;

    @Inject
//    GeneratorNoResit noResitGenerator;
    GeneratorNoResit2 noResitGenerator2;

    @Inject
    private etanah.Configuration conf;

    @Inject
    private etanah.kodHasilConfig hasil;

    @Inject
    PenyataPemungutService pp;

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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setList(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public double getJumCaraBayar() {
        return jumCaraBayar;
    }

    public void setJumCaraBayar(double jumCaraBayar) {
        this.jumCaraBayar = jumCaraBayar;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public List<DokumenKewanganBayaran> getSenaraiDKB() {
        return senaraiDKB;
    }

    public BigDecimal getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(BigDecimal lastPayment) {
        this.lastPayment = lastPayment;
    }

    public void setSenaraiDKB(List<DokumenKewanganBayaran> senaraiDKB) {
        this.senaraiDKB = senaraiDKB;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public KodAkaun getKodAkaun() {
        return kodAkaun;
    }

    public void setKodAkaun(KodAkaun kodAkaun) {
        this.kodAkaun = kodAkaun;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public ArrayList getJadual() {
        return jadual;
    }

    public void setJadual(ArrayList jadual) {
        this.jadual = jadual;
    }

    public int getTempoh() {
        return tempoh;
    }

    public void setTempoh(int tempoh) {
        this.tempoh = tempoh;
    }

    public ArrayList getYear() {
        return year;
    }

    public void setYear(ArrayList year) {
        this.year = year;
    }

    public double getFirstPayment() {
        return firstPayment;
    }

    public void setFirstPayment(double firstPayment) {
        this.firstPayment = firstPayment;
    }

    public BigDecimal getMonthly() {
        return monthly;
    }

    public void setMonthly(BigDecimal monthly) {
        this.monthly = monthly;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @HandlesEvent ("Step1")
    @DefaultHandler
    public Resolution selectTransaction(){
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/ansuran_1.jsp");
    }

    @HandlesEvent ("Step2")
    public Resolution search(){
        List<Akaun> list = new ArrayList<Akaun>();
        if(akaun != null){
            list = manager.searchAkaun(akaun.getNoAkaun());
        }
        if(hakmilik != null){
            Session s = sessionProvider.get();
            Query q = s.createQuery("SELECT a FROM etanah.model.Akaun a where a.kodAkaun = 'ACT' AND a.hakmilik.idHakmilik = :hakmilik ");
            q.setString("hakmilik", hakmilik.getIdHakmilik());
            list = q.list();
//            String [] n1 = {"hakmilik"};
//            Object [] v1 = {getHakmilik()};
//            list = akaunDAO.findByEqualCriterias(n1, v1, null);
        }
        for(Akaun ak : list){
            if(ak.getKodAkaun().getKod().equals(hasil.getProperty("ansuranCukaiTanah"))){
                double bal = ak.getBaki().doubleValue() *-1;
                ak.setBaki(new BigDecimal(bal));
                senaraiAkaun.add(ak);
            }else{
                senaraiAkaun.add(ak);
            }
        }
        flag = true;
        return new ForwardResolution("/WEB-INF/jsp/hasil/ansuran_1.jsp");
    }

    @ValidationMethod(on = "Step2")
    public void validateField(ValidationErrors errors) {
        if ((akaun == null)&&(hakmilik == null)) {
            errors.add(" ", new SimpleError("Sila Masukkan Nombor Akaun atau ID Hakmilik."));
        }
    }

    @HandlesEvent ("Step3")
    public Resolution collectPayment() throws ParseException {
        akaun = akaunDAO.findById(radio);

        List<Transaksi>senaraiTr = akaun.getSemuaTransaksi();
        for (Transaksi t : senaraiTr) {
            if(t.getAkaunDebit().getNoAkaun().equals(akaun.getNoAkaun())){
                t.setAmaun(t.getAmaun().multiply(new BigDecimal(-1)));
            }
            senaraiTransaksi.add(t);
        }

        for(Transaksi tr : senaraiTransaksi){
            if(tr.getStatus().getKod()!='B')
            total += tr.getAmaun().doubleValue();
        }
        balance = akaun.getAmaunMatang().add(akaun.getBaki());

        if(akaun.getKodAkaun().getKod().equals(hasil.getProperty("ansuranCukaiTanah"))){
            Session s = sessionProvider.get();
            Query q = null;
            if("04".equals(conf.getProperty("kodNegeri"))){
                q = s.createQuery("select mh from etanah.model.HakmilikPermohonan mh where mh.hakmilik.idHakmilik =:idHakmilik and mh.permohonan.kodUrusan.kod = 'BACT'");
            }
            if("05".equals(conf.getProperty("kodNegeri"))){
                q = s.createQuery("select mh from etanah.model.HakmilikPermohonan mh where mh.hakmilik.idHakmilik =:idHakmilik and mh.permohonan.kodUrusan.kod = 'RBYA'");
            }
            q.setString("idHakmilik", akaun.getHakmilik().getIdHakmilik());
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
        return new ForwardResolution("/WEB-INF/jsp/hasil/ansuran_2.jsp");
    }

    public void table(int tt, Date d) throws ParseException{
        BigDecimal x = new BigDecimal(0.00);
        BigDecimal deposit = new BigDecimal(0);
        List<Transaksi> tList = new ArrayList<Transaksi>();
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT t FROM etanah.model.Transaksi t where t.akaunKredit.noAkaun =:noAkaun AND t.permohonan.idPermohonan is not null");
        q.setString("noAkaun", akaun.getNoAkaun());
        Transaksi tr = (Transaksi) q.uniqueResult();
        deposit = tr.getAmaun();
//        System.out.println("deposit : "+deposit);
        
        if(kodNegeri.equals("melaka")){
            x = akaun.getAmaunMatang().subtract(deposit);
        }else{
            x = akaun.getBaki();
        }
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
    
    @ValidationMethod(on = "Step3")
    public void validateRadio(ValidationErrors errors) {
        if (radio == null) {
            errors.add(" ", new SimpleError("Sila Pilih Salah Satu Pilihan."));
        }
    }

    @HandlesEvent ("Step4")
    public Resolution save() throws ParseException{
        akaun = akaunDAO.findById(akaun.getNoAkaun());
        
        DokumenKewangan dk = null;
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        mod = modKutip.loadPenyerahFromSession(ctx);

        Pengguna pengguna = ctx.getUser(); //penggunaDAO.findById("admin");
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        String ac = "";
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        String resit = null;

        tx = s.beginTransaction();
        try{
//            String resit = noResitGenerator.generateNoResit(ctx.getKodNegeri(), caw , pengguna);
            resit = noResitGenerator2.getAndLockSerialNo(pengguna);
            // resit
            dk = new DokumenKewangan();
            dk.setIdDokumenKewangan(resit);
            // set idResit to pageContext
            ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
            dk.setAmaunBayaran(new  BigDecimal(jumCaraBayar));
            dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
            dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
            dk.setInfoAudit(ia);
            dk.setCawangan(caw);
            dk.setTarikhTransaksi(now);
            if (mod != null && mod.length() > 0) {
                dk.setMod(kodKutipDAO.findById(mod.charAt(0)));
            }
            // save cara bayaran

            ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
            for (int f=0; f<senaraiCaraBayaran.size(); f++) {
                CaraBayaran cb = senaraiCaraBayaran.get(f);
                if(cb.getAmaun() != null){
                    if (cb.getAmaun().intValue() > 0) {
                        KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                        if((crByr.getKod().equals("CB"))||(crByr.getKod().equals("CC"))||(crByr.getKod().equals("CT"))||
                                (crByr.getKod().equals("CL"))||(crByr.getKod().equals("IB"))||(crByr.getKod().equals("BC"))){
//                            Date d = sdf.parse(tarikhCek.get(f));
//                            cb.setTarikhCek(d);
                        }
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
//            String tmp = dk.getIdDokumenKewangan();
            idKewDok = dk.getIdDokumenKewangan();
            dk.setSenaraiBayaran(adkb);
            manager.saveOrUpdate(dk);

        //update Akaun Kutipan Harian
        kodAkaun = kodAkaunDAO.findById(hasil.getProperty("akaunKutipanHarian"));
        String [] n2  = {"kodAkaun"};
        Object [] v2 = {getKodAkaun()};
        List<Akaun> accountList = akaunDAO.findByEqualCriterias(n2, v2, null);
        for(Akaun acnt : accountList){
            if(acnt.getCawangan().getKod().equals(caw.getKod())){
                ac = acnt.getNoAkaun();
            }
        }
        Akaun acc = akaunDAO.findById(ac);
        double bal = acc.getBaki().doubleValue() + jumCaraBayar;
        acc.setBaki(new BigDecimal(bal));
        manager.saveOrUpdate(acc);

        transaksi = new Transaksi();

        transaksi.setCawangan(caw);
        transaksi.setKodTransaksi(kodTransaksiDAO.findById(hasil.getProperty("cukaiTanahAnsuran")));
        transaksi.setAmaun(new BigDecimal(jumCaraBayar));
        transaksi.setDokumenKewangan(dk);
        transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
        transaksi.setInfoAudit(ia);
        transaksi.setAkaunDebit(acc);
        transaksi.setAkaunKredit(akaun);
        manager.save(transaksi);

        pp.savePenyataPemungut(dk);

        //update akaun Ansuran Cukai Tanah
        double tot = 0.0;

        tot = akaun.getBaki().doubleValue() - jumCaraBayar;
        akaun.setBaki(new BigDecimal(tot));
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(now);
        akaun.setInfoAudit(ia);
        manager.saveOrUpdate(akaun);
        }catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            tx.rollback();
            noResitGenerator2.rollbackAndUnlockSerialNo(pengguna);
            addSimpleError("Pembayaran tidak berjaya.");
        }finally{
            noResitGenerator2.commitAndUnlockSerialNo(pengguna);
        }
        caraBayaran();
        preview();
        return new ForwardResolution("/WEB-INF/jsp/hasil/ansuran_3.jsp");
    }

    @ValidationMethod(on = "Step4")
    public void validateAmount(ValidationErrors errors) throws ParseException {
        if (jumCaraBayar < 1.0) {
            errors.add(" ", new SimpleError("Sila Masukkan jumlah yang hendak dibayar."));
        }
        collectPayment();
    }

    public void preview(){
        total = 0.0;
        dokumenKewangan = dokumenKewanganDAO.findById(idKewDok);
        String [] n1 = {"dokumenKewangan"};
        Object [] v1 = {getDokumenKewangan()};
        transList = transaksiDAO.findByEqualCriterias(n1, v1, null);

        for(Transaksi tr : transList){
            total += tr.getAmaun().doubleValue();
        }
    }

    public void caraBayaran(){
        senaraiDKB = new ArrayList<DokumenKewanganBayaran>();
        dokumenKewangan = dokumenKewanganDAO.findById(idKewDok);
        String [] n1 = {"dokumenKewangan"};
        Object [] v1 = {getDokumenKewangan()};
        senaraiDKB = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);
    }

    @HandlesEvent ("Step5")
    public Resolution main() {
        hakmilik = new Hakmilik();
        akaun = new Akaun();
        senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        radio = "";
        return new ForwardResolution("/WEB-INF/jsp/hasil/ansuran_1.jsp");
    }

    public Resolution cetak() throws FileNotFoundException {
        Date now = new Date();
        File f = null;
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        String t = (new SimpleDateFormat("ddMMyyyyhhmm")).format(now);
        String idKew = getContext().getRequest().getParameter("idKew");
        String documentPath = File.separator + "tmp" + File.separator;
        String path = t+ File.separator + String.valueOf(idKew);
        if("04".equals(conf.getProperty("kodNegeri"))){
            reportUtil.generateReport("HSLResitPelbagai_MLK.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{idKew},
                    documentPath + path, null);
        }else{
            reportUtil.generateReport("HSLResitPelbagai.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{idKew},
                    documentPath + path, null);
        }

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);

    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }
}
