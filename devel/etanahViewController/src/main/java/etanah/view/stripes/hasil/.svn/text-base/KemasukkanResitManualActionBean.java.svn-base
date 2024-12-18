package etanah.view.stripes.hasil;
import java.io.*;
import java.text.*;
import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import java.math.BigDecimal;
import com.google.inject.Inject;
import etanah.report.ReportUtil;
import able.stripes.AbleActionBean;
import etanah.kodHasilConfig;
import etanah.sequence.GeneratorNoResit;
import net.sourceforge.stripes.action.*;
import etanah.view.etanahActionBeanContext;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author abdul.hakim
 */
@Wizard(startEvents = {"showForm","displayDetails","doCheckNoResit", "getAmaun"})
@UrlBinding("/hasil/resit_manual")
public class KemasukkanResitManualActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(KemasukkanResitManualActionBean.class);
    private boolean flag = false;
    private String noResit;
    private String tarikh;
    private String idHakmilik;
    private String idKewDok;
    private String btn2;
    private String btn1;
    private BigDecimal jumlahCaj = new BigDecimal(0);
    private double jumCaraBayar;
    private BigDecimal jum;
    private BigDecimal z;
    private BigDecimal jumDenda;
    private BigDecimal notis;
    private BigDecimal denda;
    private Akaun akaun;
    private Hakmilik hakmilik;
    private CaraBayaran caraBayaran;
    private KodCaraBayaran kodCaraBayaran;
    private Pihak pemegang;
    private Pihak pihak;
    private DokumenKewangan dokumenKewangan;
    private KodAkaun kodAkaun;
    private Transaksi transaksi;
    private AkaunDAO akaunDAO;
    private HakmilikDAO hakmilikDAO;
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    private CaraBayaranDAO caraBayaranDAO;
    private TransaksiDAO transaksiDAO;
    private PihakDAO pihakDAO;
    private KodDokumenDAO kodDokumenDAO;
    private KodKutipanDAO kodKutipanDAO;
    private HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    private KodTransaksiDAO kodTransaksiDAO;
    private KodBankDAO kodBankDAO;
    private KodAkaunDAO kodAkaunDAO;
    private DokumenKewanganDAO dokumenKewanganDAO;
    private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    private List<Akaun> akaunList;
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<HakmilikPihakBerkepentingan> pihakList;
    private List<Transaksi> transList;
    private List<DokumenKewanganBayaran> dkbList;
    private List<String> tarikhCek = new ArrayList<String>();
    private List<String> listAkaun = new ArrayList<String>();
    private List<String> listTr = new ArrayList<String>();
    private List<String> kewangan38 = new ArrayList<String>();
    private List<String> juruwang = new ArrayList<String>();
    private List<String> trkhWang38 = new ArrayList<String>();
    private List<Character> modBayaran = new ArrayList<Character>();
    private List<KodTransaksi> senaraiKodTransaksi = new ArrayList<KodTransaksi>();
    private int  bil = 0;
    private String idKewDoc;
    private String noAkaun;
    private static String kodNegeri;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    
    @Inject
    public KemasukkanResitManualActionBean(AkaunDAO akaunDAO, HakmilikDAO hakmilikDAO, KodCaraBayaranDAO kodCaraBayaranDAO, KodKutipanDAO kodKutipanDAO,
                                           CaraBayaranDAO caraBayaranDAO, TransaksiDAO transaksiDAO, PihakDAO pihakDAO, KodTransaksiDAO kodTransaksiDAO,
                                           HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO, KodDokumenDAO kodDokumenDAO,
                                           KodBankDAO kodBankDAO, KodAkaunDAO kodAkaunDAO, DokumenKewanganDAO dokumenKewanganDAO,
                                           DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO, KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO) {
        this.akaunDAO = akaunDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.kodCaraBayaranDAO = kodCaraBayaranDAO;
        this.caraBayaranDAO = caraBayaranDAO;
        this.transaksiDAO = transaksiDAO;
        this.pihakDAO = pihakDAO;
        this.hakmilikPihakBerkepentinganDAO = hakmilikPihakBerkepentinganDAO;
        this.kodDokumenDAO = kodDokumenDAO;
        this.kodBankDAO = kodBankDAO;
        this.kodAkaunDAO = kodAkaunDAO;
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.dokumenKewanganBayaranDAO = dokumenKewanganBayaranDAO;
        this.kodStatusTransaksiKewanganDAO = kodStatusTransaksiKewanganDAO;
        this.kodTransaksiDAO = kodTransaksiDAO;
        this.kodKutipanDAO = kodKutipanDAO;
    }

    @Inject
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;

    @Inject
    KutipanHasilManager manager;

    @Inject
    GeneratorNoResit noResitGenerator;

    @Inject
    private ReportUtil reportUtil;

    @Inject
    private etanah.Configuration conf;
    
    @Inject
    private kodHasilConfig hasil;

    @Inject
    private TransactionService tranService;

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

    public CaraBayaran getCaraBayaran() {
        return caraBayaran;
    }

    public void setCaraBayaran(CaraBayaran caraBayaran) {
        this.caraBayaran = caraBayaran;
    }

    public KodCaraBayaran getKodCaraBayaran() {
        return kodCaraBayaran;
    }

    public void setKodCaraBayaran(KodCaraBayaran kodCaraBayaran) {
        this.kodCaraBayaran = kodCaraBayaran;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }
    
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Akaun> getAkaunList() {
        return akaunList;
    }

    public void setAkaunList(List<Akaun> akaunList) {
        this.akaunList = akaunList;
    }

    public String getBtn2() {
        return btn2;
    }

    public void setBtn2(String btn2) {
        this.btn2 = btn2;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getPihakList() {
        return pihakList;
    }
    
    public void setPihakList(List<HakmilikPihakBerkepentingan> pihakList) {
        this.pihakList = pihakList;
    }

    public Pihak getPemegang() {
        return pemegang;
    }

    public void setPemegang(Pihak pemegang) {
        this.pemegang = pemegang;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }
    
    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public BigDecimal getJumDenda() {
        return jumDenda;
    }
    
    public void setJumDenda(BigDecimal jumDenda) {
        this.jumDenda = jumDenda;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public BigDecimal getZ() {
        return z;
    }

    public void setZ(BigDecimal z) {
        this.z = z;
    }

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public double getJumCaraBayar() {
        return jumCaraBayar;
    }

    public void setJumCaraBayar(double jumCaraBayar) {
        this.jumCaraBayar = jumCaraBayar;
    }

    public List<DokumenKewanganBayaran> getDkbList() {
        return dkbList;
    }

    public void setDkbList(List<DokumenKewanganBayaran> dkbList) {
        this.dkbList = dkbList;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public String getBtn1() {
        return btn1;
    }

    public void setBtn1(String btn1) {
        this.btn1 = btn1;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public KodAkaun getKodAkaun() {
        return kodAkaun;
    }

    public void setKodAkaun(KodAkaun kodAkaun) {
        this.kodAkaun = kodAkaun;
    }

    public BigDecimal getJum() {
        return jum;
    }

    public void setJum(BigDecimal jum) {
        this.jum = jum;
    }

    public String getIdKewDoc() {
        return idKewDoc;
    }

    public void setIdKewDoc(String idKewDoc) {
        this.idKewDoc = idKewDoc;
    }

    @DefaultHandler
    public Resolution showForm() {
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        collectPayment();
        return new ForwardResolution("/WEB-INF/jsp/hasil/kemasukkan_resit_manual.jsp");
    }

    public Resolution search() {
        flag = true;
        jumCaraBayar = 0.0;
        if(hakmilik != null){
            String [] n1 = {"hakmilik"};
            Object [] v1 = {getHakmilik()};
            akaunList = new ArrayList<Akaun>();
            List<Akaun>aList = akaunDAO.findByEqualCriterias(n1, v1, null);
            for(Akaun ak : aList){
                if(ak.getKodAkaun().getKod().equals("AC")){
                    jumCaraBayar += ak.getBaki().doubleValue();
                    jum = ak.getBaki();
                    akaunList.add(ak);
                }
            }
        }
        if(akaun != null){
            akaun = akaunDAO.findById(akaun.getNoAkaun());
            jumCaraBayar += akaun.getBaki().doubleValue();
            akaunList = new ArrayList<Akaun>();
            akaunList.add(akaun);
            hakmilik = hakmilikDAO.findById(akaun.getHakmilik().getIdHakmilik());
        }
        collectPayment();
        return new ForwardResolution("/WEB-INF/jsp/hasil/kemasukkan_resit_manual.jsp");
    }

    public Resolution collectPayment() {
        bil +=5;
        if (senaraiCaraBayaran == null) {
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        }
        if (senaraiCaraBayaran.size() == 0) {
            for (int i = 0; i < bil; i++) {
                CaraBayaran cr = new CaraBayaran();
                cr.setAmaun(BigDecimal.ZERO);
                senaraiCaraBayaran.add(cr);
            }
            bil = senaraiCaraBayaran.size();
        }
        else{
            for (int i = 0; i < bil; i++) {
                CaraBayaran cr = new CaraBayaran();
                senaraiCaraBayaran.add(cr);
            }
            bil = senaraiCaraBayaran.size();
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/kemasukkan_resit_manual.jsp");
    }

    public Resolution addCaraBayar() {
        showForm();
        return new ForwardResolution("/WEB-INF/jsp/hasil/kemasukkan_resit_manual.jsp");
    }

    public Resolution cancel(){
        flag =false;
        hakmilik = new Hakmilik();
        noResit = "";
        tarikh = "";
        senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        return new ForwardResolution("/WEB-INF/jsp/hasil/kemasukkan_resit_manual.jsp");
    }

    public Resolution save() throws ParseException{                             // melaka
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        DokumenKewangan dk = null;

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();

        int year = Integer.parseInt(yy.format(now));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        List<Transaksi> listT = new ArrayList<Transaksi>();
        
        String ac = "";                                                         //update Akaun Kutipan Harian
        kodAkaun = kodAkaunDAO.findById("AKH");
        String [] n1  = {"kodAkaun"};
        Object [] v1 = {getKodAkaun()};
        List<Akaun> accountList = akaunDAO.findByEqualCriterias(n1, v1, null);
        for(Akaun acnt : accountList){
            if(acnt.getCawangan().getKod().equals(caw.getKod())){
                ac = acnt.getNoAkaun();
            }
        }
        Akaun akaunKutHarian = akaunDAO.findById(ac);
        BigDecimal bal = akaunKutHarian.getBaki().add(new BigDecimal(jumCaraBayar));
        akaunKutHarian.setBaki(bal);
        manager.saveOrUpdate(akaunKutHarian);
        List<Transaksi> listTrans = new ArrayList<Transaksi>();
        for (int i = 0; i < 5; i++) {
            if(!listTr.get(i).equals("0")){
                listT = new ArrayList<Transaksi>();
                listTrans = new ArrayList<Transaksi>();
                CaraBayaran cb = senaraiCaraBayaran.get(i);
                if(listAkaun.get(i)!=null){
                    LOG.info("----if----");
                    Akaun ak = akaunDAO.findById(listAkaun.get(i));
                    if(ak != null){
                        for (Transaksi tr : ak.getSenaraiTransaksiDebit()) {
                            if((tr.getAkaunKredit() !=null)&&(tr.getAkaunKredit().getKodAkaun().getKod().equals("AKH"))){
                                continue;}
                            else{
                                LOG.info("----hahaha----");
                                if(tr.getDokumenKewangan() == null)
                                listTrans.add(tr);
                            }
                        }
                        listT.addAll(listTrans);

                        String resit = noResitGenerator.generate(ctx.getKodNegeri(), caw , pengguna);
                        String no_manual = kewangan38.get(i);
                        // resit
                        dk = new DokumenKewangan();
                        dk.setIdDokumenKewangan(resit);
                        // set idResit to pageContext
                        ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
                        dk.setAmaunBayaran(cb.getAmaun());
                        dk.setNoRujukanManual(no_manual);
                        String tarikh_manual = trkhWang38.get(i);
                        dk.setTarikhTransaksi(sdf.parse(tarikh_manual));
                        dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
                        dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
                        dk.setInfoAudit(ia);
                        dk.setCawangan(caw);

                        ////////////////////////////////////////
                        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
                        if(cb.getAmaun() != null){
                            if (cb.getAmaun().intValue() > 0) {
                                KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                                cb.setKodCaraBayaran(crByr);
                                cb.setCawangan(caw);
                                cb.setInfoAudit(ia);
                                manager.saveOrUpdate(cb);
                                DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                                if (cb.getKodCaraBayaran().getKod().equals("T")) {
                                    dk.setAmaunTunai(cb.getAmaun());
                                }
                                dkb.setCaraBayaran(cb);
                                dkb.setDokumenKewangan(dk);
                                dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                                dkb.setInfoAudit(ia);
                                dkb.setAktif('Y');
                                adkb.add(dkb);
                            }
                        }
                        idKewDoc = dk.getIdDokumenKewangan();
                        dk.setSenaraiBayaran(adkb);
                        dk.setAkaun(ak);
                        dk.setIsuKepada(juruwang.get(i));
                        manager.saveOrUpdate(dk);

                        BigDecimal tot = new BigDecimal(0);                     //update Akaun Cukai
                        tot = ak.getBaki().subtract(cb.getAmaun());
                        ak.setBaki(tot);
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(now);
                        ak.setInfoAudit(ia);
                        manager.saveOrUpdate(ak);

                        int count =0;
                        LOG.info("listT : "+listT.size());
                        for (Transaksi t : listT) {
                            LOG.info("count : "+count);
                            if((t.getStatus().getKod() == 'A')&&(t.getDokumenKewangan() == null)){
                                LOG.info("Status A");
                                Transaksi trans = new Transaksi();
                                trans.setCawangan(caw);
                                trans.setKodTransaksi(t.getKodTransaksi());
                                trans.setAmaun(t.getAmaun());
                                trans.setDokumenKewangan(dk);
                                trans.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                                trans.setUntukTahun(t.getUntukTahun());
                                trans.setTahunKewangan(year);
                                trans.setBayaranAgensi("N");
                                ia.setDimasukOleh(pengguna);
                                ia.setTarikhMasuk(now);
                                trans.setInfoAudit(ia);
                                if(t.getKodTransaksi().getKod().equals("61401")){
                                    trans.setAkaunDebit(akaunKutHarian);
                                }if((t.getAkaunKredit()!=null)&&(t.getAkaunDebit()!=null)){
                                    trans.setAkaunDebit(ak);
                                }
                                if((t.getAkaunDebit()!=null)&&(t.getAkaunDebit().getNoAkaun().equals(ak.getNoAkaun()))){
                                    trans.setAkaunKredit(ak);
                                    trans.setAkaunDebit(akaunKutHarian);
                                }
                                manager.save(trans);
                            }
                            count ++;
                        }
                        //kurang
                        if(tot.doubleValue() > 0){
                            LOG.info("kurang");
                            Transaksi trans = new Transaksi();
                            trans.setCawangan(caw);
                            trans.setKodTransaksi(kodTransaksiDAO.findById("61016"));
                            trans.setAmaun(tot);
                            //                        trans.setDokumenKewangan(dk);
                            trans.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                            trans.setUntukTahun(year);
                            trans.setTahunKewangan(year);
                            trans.setBayaranAgensi("N");
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(now);
                            trans.setInfoAudit(ia);
                            trans.setAkaunDebit(ak);
                            manager.save(trans);
                        }
                        //lebih
                        if(tot.doubleValue() < 0){
                            LOG.info("lebih");
                            Transaksi trans = new Transaksi();
                            trans.setCawangan(caw);
                            trans.setKodTransaksi(kodTransaksiDAO.findById("61611"));
                            trans.setAmaun(tot.multiply(new BigDecimal(-1)));
                            trans.setDokumenKewangan(dk);
                            trans.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                            trans.setUntukTahun(year);
                            trans.setTahunKewangan(year);
                            trans.setBayaranAgensi("N");
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(now);
                            trans.setInfoAudit(ia);
                            trans.setAkaunKredit(ak);
                            trans.setAkaunDebit(akaunKutHarian);
                            manager.save(trans);
                        }
                    }
                }
                else {
                    LOG.info("----else----");
//                    String resit = noResitGenerator.generate(ctx.getKodNegeri(), caw , pengguna);
                    String resit = kewangan38.get(i);
                    // resit
                    dk = new DokumenKewangan();
                    dk.setIdDokumenKewangan(resit);
                    // set idResit to pageContext
                    ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
                    dk.setAmaunBayaran(cb.getAmaun());
                    dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
                    dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
                    dk.setInfoAudit(ia);
                    dk.setNoRujukanManual(resit);
                    dk.setCawangan(caw);

                    ////////////////////////////////////////
                    ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
                    if (cb.getAmaun() != null) {
                        if (cb.getAmaun().intValue() > 0) {
                            KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                            cb.setKodCaraBayaran(crByr);
                            cb.setCawangan(caw);
                            cb.setInfoAudit(ia);
                            manager.saveOrUpdate(cb);
                            DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                            if (cb.getKodCaraBayaran().getKod().equals("T")) {
                                dk.setAmaunTunai(cb.getAmaun());
                            }
                            dkb.setCaraBayaran(cb);
                            dkb.setDokumenKewangan(dk);
                            dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                            dkb.setInfoAudit(ia);
                            dkb.setAktif('Y');
                            adkb.add(dkb);
                        }
                    }
                    idKewDoc = dk.getIdDokumenKewangan();
                    dk.setSenaraiBayaran(adkb);
                    dk.setIsuKepada(juruwang.get(i));
                    manager.saveOrUpdate(dk);

                    Transaksi trans = new Transaksi();
                    trans.setCawangan(caw);
                    trans.setKodTransaksi(kodTransaksiDAO.findById(listTr.get(i)));
                    trans.setAmaun(cb.getAmaun());
                    trans.setDokumenKewangan(dk);
                    trans.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                    trans.setBayaranAgensi("N");
                    trans.setUntukTahun(year);
                    trans.setTahunKewangan(year);
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(now);
                    trans.setInfoAudit(ia);
                    trans.setAkaunDebit(akaunKutHarian);
                    manager.save(trans);
                }
            }
        }
//        caraBayaran();
        preview();
        return new ForwardResolution("/WEB-INF/jsp/hasil/kemasukkan_resit_manual_1.jsp");
    }

    public void transaction(List<Transaksi>listT, KodCawangan caw, String res, Pengguna pengguna,
                            InfoAudit ia, Akaun akaunKutHarian, Akaun ak) {
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        for (Transaksi t : listT) {
            if ((t.getStatus().getKod() == 'A')&&(t.getDokumenKewangan() == null)) {
                Transaksi trans = new Transaksi();
                trans.setCawangan(caw);
                trans.setKodTransaksi(t.getKodTransaksi());
                trans.setAmaun(t.getAmaun());
                DokumenKewangan dk = dokumenKewanganDAO.findById(res);
                trans.setDokumenKewangan(dk);
                trans.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                trans.setBayaranAgensi("N");
                trans.setUntukTahun(t.getUntukTahun());
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(now);
                trans.setInfoAudit(ia);
                trans.setTahunKewangan(year);
                if (t.getKodTransaksi().getKod().equals("61401")) {
                    trans.setAkaunDebit(akaunKutHarian);
                }
                if ((t.getAkaunKredit() != null) && (t.getAkaunDebit() != null)) {
                    trans.setAkaunDebit(ak);
                }
                if ((t.getAkaunDebit() != null) && (t.getAkaunDebit().getNoAkaun().equals(ak.getNoAkaun()))) {
                    trans.setAkaunKredit(ak);
                    trans.setAkaunDebit(akaunKutHarian);
                }
                manager.save(trans);
            }
        }
    }

    @Inject
    PenyataPemungutService penyataPemungut;

    public Resolution save1() throws ParseException{
        LOG.info("save1");
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        DokumenKewangan dk = null;

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();

        int year = Integer.parseInt(yy.format(now));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        List<Transaksi> listT = new ArrayList<Transaksi>();
        //update Akaun Kutipan Harian
        String ac = "";
        kodAkaun = kodAkaunDAO.findById("AKH");
        String [] n1  = {"kodAkaun"};
        Object [] v1 = {getKodAkaun()};
        List<Akaun> accountList = akaunDAO.findByEqualCriterias(n1, v1, null);
        for(Akaun acnt : accountList){
            if(acnt.getCawangan().getKod().equals(caw.getKod())){
                ac = acnt.getNoAkaun();
            }
        }
        Akaun akaunKutHarian = akaunDAO.findById(ac);
        BigDecimal bal = akaunKutHarian.getBaki().add(new BigDecimal(jumCaraBayar));
        akaunKutHarian.setBaki(bal);
        manager.saveOrUpdate(akaunKutHarian);
        for (int i = 0; i < 5; i++) {
            CaraBayaran cb = senaraiCaraBayaran.get(i);
            if(listAkaun.get(i)!=null){
                LOG.info("----if----");
                Akaun ak = akaunDAO.findById(listAkaun.get(i));
                if(ak != null){
                    Session s = sessionProvider.get();
                    Query q = s.createQuery("SELECT tr FROM etanah.model.Transaksi tr where ((tr.akaunDebit.noAkaun =:noRujMan OR tr.akaunKredit.noAkaun =:noRujMan) " +
                                                       "AND tr.status.kod ='A' AND tr.dokumenKewangan IS NULL) ORDER BY tr.kodTransaksi.keutamaan");
                    q.setString("noRujMan",ak.getNoAkaun());
                    listT = q.list();
                    
                    String resit = noResitGenerator.generate(ctx.getKodNegeri(), caw , pengguna);
                    String no_manual = kewangan38.get(i);
                    // resit
                    dk = new DokumenKewangan();
                    dk.setIdDokumenKewangan(resit);
                    // set idResit to pageContext
                    ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
                    dk.setAmaunBayaran(cb.getAmaun());
                    dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
                    dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
                    dk.setNoRujukanManual(no_manual);
                    String tarikh_manual = trkhWang38.get(i);
                    dk.setTarikhTransaksi(sdf.parse(tarikh_manual));
                    dk.setInfoAudit(ia);
                    dk.setCawangan(caw);

                    ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
                    if(cb.getAmaun() != null){
                        if (cb.getAmaun().compareTo(new BigDecimal (0)) == 1) {
                            KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                            cb.setKodCaraBayaran(crByr);
                            cb.setCawangan(caw);
                            if(!crByr.getKod().equals("T")){
                                String trkhCek = tarikhCek.get(i);
                                cb.setTarikhCek(sdf.parse(trkhCek));
                            }
                            if(crByr.getKod().equals("T")){
                                cb.setBank(null);
                            }
                            cb.setInfoAudit(ia);
                            manager.saveOrUpdate(cb);
                            DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                            if (cb.getKodCaraBayaran().getKod().equals("T")) {
                                dk.setAmaunTunai(cb.getAmaun());
                            }
                            dkb.setCaraBayaran(cb);
                            dkb.setDokumenKewangan(dk);
                            dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                            dkb.setInfoAudit(ia);
                            dkb.setAktif('Y');
                            adkb.add(dkb);
                        }
                    }
                    idKewDoc = dk.getIdDokumenKewangan();
                    dk.setSenaraiBayaran(adkb);
                    dk.setAkaun(ak);
                    dk.setIsuKepada(juruwang.get(i));
                    dk.setMod(kodKutipanDAO.findById(modBayaran.get(i)));
                    dk.setIdKaunter(pengguna.getIdKaunter());
                    manager.saveOrUpdate(dk);

                    //update Cukai Tanah
                    BigDecimal tot = new BigDecimal(0);
                    tot = ak.getBaki().subtract(cb.getAmaun());
                    ak.setBaki(tot);
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(now);
                    ak.setInfoAudit(ia);
                    manager.saveOrUpdate(ak);

                    if(tot.compareTo(new BigDecimal(0))==0){                        //  :: bayar cukup2
                        transaction(listT, caw, resit, pengguna, ia, akaunKutHarian, ak);
                    }
                    if(tot.compareTo(new BigDecimal(0))<0){                         //  :: bayar lebih
                        transaction(listT, caw, resit, pengguna, ia, akaunKutHarian, ak);
                        Transaksi trans = new Transaksi();
                        trans.setCawangan(caw);
                        trans.setKodTransaksi(kodTransaksiDAO.findById(hasil.getProperty("cukaiTanahSemasa")));
                        trans.setAmaun(tot.multiply(new BigDecimal(-1)));
                        trans.setDokumenKewangan(dk);
                        trans.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                        trans.setUntukTahun(year);
                        trans.setBayaranAgensi("N");
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(now);
                        trans.setInfoAudit(ia);
                        trans.setTahunKewangan(year);
                        trans.setAkaunKredit(ak);
                        trans.setAkaunDebit(akaunKutHarian);
                        manager.save(trans);
                    }
                    
                    BigDecimal byran = cb.getAmaun();
                    if(tot.compareTo(new BigDecimal(0)) > 0){                       //  :: bayar kurang
                        tranService.saveTransaction(listT, byran, akaunKutHarian, resit, pengguna, ia, year);
                    }
                    penyataPemungut.savePenyataPemungut(dk);
                }
            }
        }
//        caraBayaran();
        preview1();
        return new ForwardResolution("/WEB-INF/jsp/hasil/kemasukkan_resit_manual_1.jsp");
    }

    public void transactions(Transaksi t, String resit, Pengguna pengguna, InfoAudit ia, int year){
        Date now = new Date();
        t.setDokumenKewangan(dokumenKewanganDAO.findById(resit));
        t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
        t.setTahunKewangan(year);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
        t.setUntukTahun(year);
        t.setInfoAudit(ia);
    }

    public void caraBayaran(){
        dkbList = new ArrayList<DokumenKewanganBayaran>();
        dokumenKewangan = dokumenKewanganDAO.findById(idKewDok);
        String [] n1 = {"dokumenKewangan"};
        Object [] v1 = {getDokumenKewangan()};
        dkbList = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);
    }
    
    public void preview(){
        LOG.info("preview");
        transaksi = new Transaksi();
        transList = new ArrayList<Transaksi>();
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        List<Transaksi> tList = new ArrayList<Transaksi>();
        List<Transaksi> listT = new ArrayList<Transaksi>();
        for (int i = 0; i < 5; i++) {
        List<Transaksi> trnsList = new ArrayList<Transaksi>();
            List<DokumenKewangan> dkList = new ArrayList<DokumenKewangan>();
            DokumenKewangan dk = new DokumenKewangan();
            if(!listTr.get(i).equals("0")){
                Session s = sessionProvider.get();
                Query q = s.createQuery("select dk from etanah.model.DokumenKewangan dk where dk.noRujukanManual =:noRujMan");
                q.setString("noRujMan",kewangan38.get(i));
                dkList = q.list();

                dk = dkList.get(0);
                String[] n1 = {"dokumenKewangan"};
                Object[] v1 = {dk};
                trnsList = transaksiDAO.findByEqualCriterias(n1, v1, null);
            LOG.info("trnsList.size() : " +trnsList.size());
            }

            LOG.info("trnsList.size() : " +trnsList.size());
            for (Transaksi t : trnsList) {
                if((t.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTanahSemasa")))&&(t.getTahunKewangan()==year))
                    listT.add(t);
            }
        }
        
        transList.addAll(listT);
        LOG.info("-----transList.size() : " +transList.size());
        for(Transaksi t : transList){
            LOG.info("t.getDokumenKewangan().getAmaunBayaran() : " +t.getDokumenKewangan().getAmaunBayaran());
            jumlahCaj = jumlahCaj.add(t.getDokumenKewangan().getAmaunBayaran());
        }
    }

    public void preview1(){
        transaksi = new Transaksi();
        transList = new ArrayList<Transaksi>();
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        List<Transaksi> tList = new ArrayList<Transaksi>();
        List<Transaksi> listT = new ArrayList<Transaksi>();
        for (int i = 0; i < 5; i++) {
            List<Transaksi> trnsList = new ArrayList<Transaksi>();
            List<DokumenKewangan> dkList = new ArrayList<DokumenKewangan>();
            DokumenKewangan dk = new DokumenKewangan();
            if(kewangan38.get(i) != null){
                Session s = sessionProvider.get();
                Query q = s.createQuery("select dk from etanah.model.DokumenKewangan dk where dk.noRujukanManual =:noRujMan");
                q.setString("noRujMan",kewangan38.get(i));
                dkList = q.list();
                LOG.info("dkList.size() : "+dkList.size());
                
                dk = dkList.get(0);
                String[] n1 = {"dokumenKewangan"};
                Object[] v1 = {dk};
                trnsList = transaksiDAO.findByEqualCriterias(n1, v1, null);
                listT.add(trnsList.get(0));
            }

            LOG.info("transList.size() : " +transList.size());
//            for (Transaksi t : trnsList) {
////                if((t.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTanahSemasa")))&&(t.getUntukTahun()==year)){
////                    if(!t.getKodTransaksi().getKod().equals(kodHasil)){
//                        listT.add(trnsList.get(0));
////                        kodHasil = t.getKodTransaksi().getKod();
////                    }
////                }
//            }
        }
        transList.addAll(listT);
        LOG.info("transList.size() : " +transList.size());
        for(Transaksi t : transList){
            LOG.info("t.getDokumenKewangan().getAmaunBayaran() : " +t.getDokumenKewangan().getAmaunBayaran());
            jumlahCaj = jumlahCaj.add(t.getDokumenKewangan().getAmaunBayaran());
        }
    }

    public Resolution doCheckNoResit() {
        String results = "0";
//        DokumenKewangan dk = dokumenKewanganDAO.findById((getContext().getRequest().getParameter("noResit")).trim());
        String resitMan = getContext().getRequest().getParameter("noResit");
        Session s = sessionProvider.get();
        Query q = s.createQuery("select dk from etanah.model.DokumenKewangan dk where dk.noRujukanManual =:noRujMan");
        q.setString("noRujMan",resitMan);
        List<DokumenKewangan> listDK = q.list();
        LOG.info("________________"+listDK.size());
        if (listDK.size() > 0) {
            results = "1";
        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution cetak() throws FileNotFoundException {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        Date now = new Date();
        File f = null;
        String t = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        String idKew = getContext().getRequest().getParameter("idKew");
        String idHakmilik = getContext().getRequest().getParameter("idhakmilik");
        String documentPath = File.separator + "tmp" + File.separator;
        String path = t+ File.separator + String.valueOf(idHakmilik);
        reportUtil.generateReport("HSL_RST_BIL_CUKAI_009.rdf",
                new String[]{"p_id_kew_dok"},
                new String[]{idKew},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public Resolution getAmaun() {
        String results = null;
        Akaun a = akaunDAO.findById(getContext().getRequest().getParameter("id"));
        if(a != null){
            String x = a.getBaki().toString();
            results = x;
        }
        return new StreamingResolution("text/plain", results);
    }

    public Resolution cetakBelakangResit() throws FileNotFoundException {
         etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        Date now = new Date();
        File f = null;
        String trkh = (new SimpleDateFormat("ddMMyyyyhhmm")).format(now);
        String idKew = getContext().getRequest().getParameter("idKew");
//        String idHakmilik = getContext().getRequest().getParameter("idhakmilik");
        String documentPath = File.separator + "tmp" + File.separator;
        String path = trkh + File.separator + String.valueOf(idKew);
        reportUtil.generateReport("HSLResitBayaranBelakangCekNS.rdf",
                new String[]{"p_id_cara_bayar"},
                new String[]{idKew},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public Resolution main() {
        hakmilik = new Hakmilik();
        akaun = new Akaun();
        senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        tarikh = "";
        noResit = "";
        return new RedirectResolution(KemasukkanResitManualActionBean.class);
    }

    public List<String> getTarikhCek() {
        return tarikhCek;
    }

    public void setTarikhCek(List<String> tarikhCek) {
        this.tarikhCek = tarikhCek;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    public List<KodTransaksi> getSenaraiKodTransaksi() {

        Session s = sessionProvider.get();
//        Query q = s.createQuery("from etanah.model.KodTransaksi u where u.aktif ='Y' and u.kod not in ('61044','61602','61402','76152'," +
//                "'61605','99000','99001','99002','99003','99004','99005','99007','99011','99030','99031','99032') order by u.kod ASC");
        Query q = s.createQuery("from etanah.model.KodTransaksi u where u.aktif ='Y' and u.kod in ('61401') order by u.kod ASC");
        senaraiKodTransaksi = q.list();
        
        return senaraiKodTransaksi;
    }

    public List<String> getKewangan38() {
        return kewangan38;
    }

    public void setKewangan38(List<String> kewangan38) {
        this.kewangan38 = kewangan38;
    }

    public List<String> getListAkaun() {
        return listAkaun;
    }

    public void setListAkaun(List<String> listAkaun) {
        this.listAkaun = listAkaun;
    }

    public List<String> getListTr() {
        return listTr;
    }

    public void setListTr(List<String> listTr) {
        this.listTr = listTr;
    }

    public List<String> getJuruwang() {
        return juruwang;
    }

    public void setJuruwang(List<String> juruwang) {
        this.juruwang = juruwang;
    }

    public List<String> getTrkhWang38() {
        return trkhWang38;
    }

    public void setTrkhWang38(List<String> trkhWang38) {
        this.trkhWang38 = trkhWang38;
    }

    public List<Character> getModBayaran() {
        return modBayaran;
    }

    public void setModBayaran(List<Character> modBayaran) {
        this.modBayaran = modBayaran;
    }
}