package etanah.view.stripes.hasil;

import java.util.*;
import etanah.dao.*;
import org.hibernate.*;
import etanah.model.*;
import java.math.BigDecimal;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.*;
import etanah.sequence.GeneratorNoResit2;
import etanah.view.etanahActionBeanContext;

/**
 *
 * @author abdul.hakim
 */

@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/cukai")
public class CukaiActionBean  extends AbleActionBean {
    private static final Logger logger = Logger.getLogger(CukaiActionBean.class);
    private static final boolean debug = logger.isDebugEnabled();
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/cukai.jsp";
    private static final String PAYMENT_VIEW = "/WEB-INF/jsp/hasil/cukai_bayaran.jsp";
    private static final String RECEIPT_VIEW = "/WEB-INF/jsp/hasil/cukai_resit.jsp";
    
    public Akaun akaun;
    public Hakmilik hakmilik;
    public Pengguna pguna;
    
    private ArrayList<Hakmilik> hList = new ArrayList<Hakmilik>();
    private ArrayList<Akaun> accList = new ArrayList<Akaun>();
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<DokumenKewanganBayaran> dkbList = new ArrayList<DokumenKewanganBayaran>();
    private List<Transaksi> transList = new ArrayList<Transaksi>();
    private List<String> tarikhCek = new ArrayList<String>();
    private String id = null;
    
    private static String kodNegeri;
    private int bilHakmilik = 6;
    private int bil = 0;
    private int typeOfPayment = 0;    
    private boolean bakiFlag = false;
    private boolean button = false;
    private BigDecimal jumlahCaj = new BigDecimal(0.00);
    private BigDecimal jumCaraBayar = new BigDecimal(0.00);
    private BigDecimal returnBalance = new BigDecimal(0.00);
    
    @Inject AkaunDAO akaunDAO;
    @Inject KutipanHasilManager manager;
    @Inject private etanah.Configuration conf;
    @Inject private ModKutipService modKutip;
    @Inject private PenyataPemungutService pp;
    @Inject private KutipanHasilService hasilService;
    @Inject private KodKutipanDAO kodKutipanDAO;
    @Inject private KodDokumenDAO kodDokumenDAO;
    @Inject private CaraBayarService caraBayarService;
    @Inject private GeneratorNoResit2 noResitGenerator2;
    @Inject protected com.google.inject.Provider<Session> sessionProvider;
    @Inject private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject KutipanHasilActionBean hasil;
    
    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "negeriSembilan";
        }
        akaun = new Akaun();
        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    public Resolution update() {
        logger.info("UPDATES "+bilHakmilik);
        for (int i = 0; i < bilHakmilik; i++) {
            Hakmilik hm = new Hakmilik();
            Akaun acc = new Akaun();
            hList.add(hm);
            accList.add(acc);
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    @HandlesEvent("Step2")
    public Resolution paymentInfo() {
        collectPayment();
        if(akaun != null){
            Akaun ak = akaunDAO.findById(akaun.getNoAkaun());
            accList.add(ak);
        }
        
        for (Akaun a : accList) {
            jumlahCaj = jumlahCaj.add(a.getBaki());
        }
        
        return new ForwardResolution(PAYMENT_VIEW);
    }
    
    public Resolution collectPayment() {
        bil +=5;
        if (senaraiCaraBayaran == null) {
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        }
        if (senaraiCaraBayaran.isEmpty()) {
            for (int i = 0; i < bil; i++) {
                CaraBayaran cr = new CaraBayaran();
                cr.setAmaun(BigDecimal.ZERO);
                senaraiCaraBayaran.add(cr);
            }
            bil = senaraiCaraBayaran.size();
        }
//        else{
//            for (int i = 0; i < bil; i++) {
//                CaraBayaran cr = new CaraBayaran();
//                senaraiCaraBayaran.add(cr);
//            }
//            bil = senaraiCaraBayaran.size();
//        }
//         for (Akaun ak : list) {
//            setAccount(ak.getNoAkaun());
//        }
        return new ForwardResolution(PAYMENT_VIEW);
    }
    
    public Resolution deleteSelected(){
        List<Akaun> listAkaun = accList; 
        accList = new ArrayList<Akaun>();
        for (Akaun ak : listAkaun) {
            if(!ak.getNoAkaun().equals(id)){accList.add(ak);}
        }
        bil=new Integer(0);
        senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        paymentInfo();
        return new ForwardResolution(PAYMENT_VIEW);
    }
    
    @HandlesEvent("Step3")
    public Resolution save() {
        logger.info("_____.:: INSIDE SAVE START ::._____");
        if (akaun != null) {
            Akaun ak = akaunDAO.findById(akaun.getNoAkaun());
            accList.add(ak);
        }

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        String mod = null;
        String remisyen = "";
        BigDecimal amaunRemisyen = new BigDecimal(0.00);
        BigDecimal bd = new BigDecimal(0.00);
        DokumenKewangan dk;

        pguna = ctx.getUser();
        KodCawangan caw = pguna.getKodCawangan();
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());

        //Find Akaun Kutipan Harian (AKH)
        String query = "SELECT a FROM etanah.model.Akaun a WHERE a.cawangan.kod =:kod AND a.kodAkaun.kod = 'AKH' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kod", pguna.getKodCawangan().getKod());
        Akaun akh = (Akaun) q.uniqueResult();
        logger.info("akh.getNoAkaun() : " + akh.getNoAkaun());
        BigDecimal bal = new BigDecimal(0);
        if (bakiFlag) {
            bal = akh.getBaki().add(jumlahCaj);
        } else {
            bal = akh.getBaki().add(jumCaraBayar);
        }
        tx = s.beginTransaction();
        try {
            akh.setBaki(bal);
            manager.saveOrUpdate(akh);

            KodKutipan kodKutip = new KodKutipan();
            mod = modKutip.loadPenyerahFromSession(ctx);
            if (mod.equalsIgnoreCase("Lewat")) {kodKutip = kodKutipanDAO.findById('L');}
            if (mod.equalsIgnoreCase("Biasa")) {kodKutip = kodKutipanDAO.findById('B');}
            KodDokumen kd = kodDokumenDAO.findById("RBY");
            KodStatusDokumenKewangan statusKD = kodStatusDokumenKewanganDAO.findById("A");
            KodStatusTransaksiKewangan statusTrans = kodStatusTransaksiKewanganDAO.findById('T');

            for (int m = 0; m < accList.size(); m++) {
                Akaun ac = accList.get(m);
                logger.info("ac.getNoAkaun() : " + ac.getNoAkaun());
                BigDecimal bakiAwal = ac.getBaki();
                
                String queryDebit = "SELECT tr FROM etanah.model.Transaksi tr where ((tr.akaunDebit.noAkaun =:noAkaun " +
                                    "AND tr.status.kod ='A')) ORDER BY tr.kodTransaksi.keutamaan";
                Query qDebit = sessionProvider.get().createQuery(queryDebit);
                qDebit.setString("noAkaun", ac.getNoAkaun());
                List<Transaksi>listTr = qDebit.list();
                logger.info("listTr.size() : "+listTr.size());
                
                 //kalau dlm list ade akaun yg dh bayar
                if(ac.getBaki().compareTo(new BigDecimal(0))<=0){       
//                if((ak.getBaki().compareTo(new BigDecimal(0))<=0)&&(lbh==null)){
                continue;}
                else{
                    String resit = noResitGenerator2.getAndLockSerialNo(pguna);      //nombor resit
                    logger.info("resit : "+resit);
                    //set Dokumen Kewangan
                    dk = new DokumenKewangan();
                    dk.setIdDokumenKewangan(resit);
                    if(akaun!=null){dk.setAmaunBayaran(jumCaraBayar);}
                    else{dk.setAmaunBayaran(ac.getBaki());}                 //FIXME (bayar pukal...tp kurang)
                    dk.setKodDokumen(kd);
                    dk.setStatus(statusKD);
                    dk.setInfoAudit(ia);
                    dk.setCawangan(caw);
                    dk.setIdKaunter(pguna.getIdKaunter());
                    BigDecimal bg = new BigDecimal(0);
                    
                    // save cara bayaran
                    ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
                    adkb = caraBayarService.saveCaraBayaran(senaraiCaraBayaran, tarikhCek, ia, pguna, ac.getNoAkaun(), bakiFlag, bg, dk, adkb,jumlahCaj);
                    
                    dk.setSenaraiBayaran(adkb);
                    dk.setMod(kodKutip);
                    if (bakiFlag == true) {
                        dk.setAmaunBayaran(ac.getBaki());
                    }
                    dk.setAkaun(ac);
                    dk.setTarikhTransaksi(now);
                    dk.setIsuKepada(ac.getPemegang().getNama());
                    manager.saveOrUpdate(dk);
                    
                    /***** UPDATE Baki Akaun START *****/
                    BigDecimal tot = new BigDecimal(0);
                    if (bakiFlag == true) {
                       ac.setBaki(new BigDecimal(0));
                    }else{
                        ac.setBaki(ac.getBaki().subtract(jumCaraBayar));
                    }
                        ia.setDiKemaskiniOleh(pguna);
                        ia.setTarikhKemaskini(now);
                    ac.setInfoAudit(ia);
                    manager.saveOrUpdate(ac);
                    /***** UPDATE Baki Akaun FINISH *****/
                    
                    String queryTransaction = "SELECT t FROM etanah.model.Transaksi t WHERE t.akaunKredit.noAkaun = :noAkaun OR t.akaunDebit.noAkaun = :noAkaun "
                                                           + "AND t.status.kod = 'A' ORDER BY t.kodTransaksi.keutamaan";
                    Query qTransaction = sessionProvider.get().createQuery(queryTransaction);
                    qTransaction.setString("noAkaun", ac.getNoAkaun());
                    List<Transaksi> senaraiTransaksi = qTransaction.list();
                    logger.info("senaraiTransaksi.size() : "+senaraiTransaksi.size());
                    List<Transaksi> senaraiTransaksiA = new ArrayList<Transaksi>();
                    for (Transaksi tr : senaraiTransaksi) {
                        logger.info("tr.getKodTransaksi().getKod() : "+tr.getKodTransaksi().getKod());
                        if(tr.getStatus().getKod()=='A'){
                            logger.info("TRANSAKSI A");
                            if((tr.getKodTransaksi().getKod().equals("99000"))||(tr.getKodTransaksi().getKod().equals("99001"))||
                                    (tr.getKodTransaksi().getKod().equals("99002"))||(tr.getKodTransaksi().getKod().equals("99003"))){
                                remisyen="remisyen";
                                amaunRemisyen = tr.getAmaun();
                            }else if (tr.getKodTransaksi().getKod().equals("99030")){
                                remisyen = "remCukai";
                                amaunRemisyen = tr.getAmaun();
                            }else if (tr.getKodTransaksi().getKod().equals("99050")){
                                remisyen = "remDenda";
                                amaunRemisyen = tr.getAmaun();
                            }else if (tr.getKodTransaksi().getKod().equals("99051")){
                                remisyen = "remTunggakan";
                                amaunRemisyen = tr.getAmaun();
                            }
                            senaraiTransaksiA.add(tr);
                            
                            if((tr.getAkaunDebit()!=null)&&(tr.getAkaunDebit().getNoAkaun().equals(ac.getNoAkaun()))){
                                bd = bd.add(tr.getAmaun());
                            }else{
                                bd = bd.subtract(tr.getAmaun());
                            }
                        }
                    }
                    logger.info("bakiAwal : "+bakiAwal);
                    logger.info("jumCaraBayar : "+jumCaraBayar);
                    logger.info("bd : "+bd);
                    logger.info("remisyen : "+remisyen);
                    //bayaran kali kedua
                    if((bakiAwal.doubleValue() > 0.0)&&(bakiAwal.doubleValue() < bd.doubleValue())){
                        //        Transaksi Kredit
                        Query qTransKredit = sessionProvider.get().createQuery("SELECT t FROM etanah.model.Transaksi t where t.akaunKredit.noAkaun = :noAkaun AND t.status.kod = 'T' " +
                                                                              "order by t.kodTransaksi.keutamaan");
                        qTransKredit.setString("noAkaun", ac.getNoAkaun());
                        List<Transaksi> senaraiTransaksiKredit =qTransKredit.list();
                        
                        //        Transaksi Debit
                        Query qTransDebit = sessionProvider.get().createQuery("SELECT t FROM etanah.model.Transaksi t where t.akaunDebit.noAkaun = :noAkaun AND t.status.kod = 'A' " +
                                                                            "order by t.kodTransaksi.keutamaan");
                        qTransDebit.setString("noAkaun", ac.getNoAkaun());
                        List<Transaksi> senaraiTransaksiDebit =qTransDebit.list();

                        hasilService.bayaranKedua(senaraiTransaksiDebit, senaraiTransaksiKredit, pguna, ia, akh, now, dk, year, ac, bakiAwal);
                    }
                    //bayar kurang
                    else if(bakiAwal.compareTo(jumCaraBayar)>0){
                        logger.info("...:: BAYAR KURANG ::...");             
                        hasilService.bayarKurang(senaraiTransaksiA, pguna, caw, dk, akh, ac.getNoAkaun(), jumCaraBayar);
                    }
                    else if(bakiAwal.compareTo(jumCaraBayar)<=0){
                        if(remisyen.equalsIgnoreCase("")){
                            for (Transaksi tr : listTr) {
                                if (tr.getStatus().getKod() == 'A') {
                                    Transaksi trans = new Transaksi();
                                    BigDecimal amaunTemp = new BigDecimal(0);
                                    amaunTemp = tr.getAmaun();
                                    trans.setCawangan(caw);
                                    trans.setKodTransaksi(tr.getKodTransaksi());
                                    trans.setAmaun(amaunTemp);
                                    trans.setDokumenKewangan(dk);
                                    trans.setStatus(statusTrans);
                                    trans.setUntukTahun(tr.getUntukTahun());
                                    trans.setTahunKewangan(year);
                                    ia.setDimasukOleh(pguna);
                                    ia.setTarikhMasuk(now);
                                    trans.setInfoAudit(ia);
                                    trans.setAkaunKredit(ac);
                                    trans.setAkaunDebit(akh);
                                    trans.setBayaranAgensi("N");
                                    manager.save(trans);
                                }
                            }  
                            if (jumCaraBayar.compareTo(bakiAwal)>0) {
                                tot = bakiAwal.subtract(jumCaraBayar);
                                hasil.bayaranLebih(tot, caw, dk, pguna, now, ia, year, akh, ac.getNoAkaun());
                            }
                        }
                        if(remisyen.equalsIgnoreCase("remisyen")){
                            
                        }
                        if(remisyen.equalsIgnoreCase("remCukai")){
                            
                        }
                        if(remisyen.equalsIgnoreCase("remDenda")){
                            
                        }
                        if(remisyen.equalsIgnoreCase("remTunggakan")){
                            
                        }
                    }
                    pp.savePenyataPemungut(dk);
                    
                    String queryDKB = "SELECT dkb FROM etanah.model.DokumenKewanganBayaran dkb WHERE "
                                               + "dkb.dokumenKewangan.idDokumenKewangan =:resit";
                    Query qDKB = sessionProvider.get().createQuery(queryDKB);
                    qDKB.setString("resit", dk.getIdDokumenKewangan());
                    dkbList = qDKB.list();
                    findTransaksi(dk, ac);
                    jumlahCaj = dk.getAmaunBayaran();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            tx.rollback();
            noResitGenerator2.rollbackAndUnlockSerialNo(pguna);
            addSimpleError("Pembayaran tidak berjaya.");
        } finally {
            tx.commit();
            noResitGenerator2.commitAndUnlockSerialNo(pguna);
        }
        return new ForwardResolution(RECEIPT_VIEW);
    }
    
    public List<Transaksi> findTransaksi(DokumenKewangan dk, Akaun ac) {
        List<Transaksi> listTr = new ArrayList<Transaksi>();

        String query = "SELECT t FROM etanah.model.Transaksi t where t.dokumenKewangan.idDokumenKewangan = :resit AND t.status.kod = 'T'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("resit", dk.getIdDokumenKewangan());
        List<Transaksi> trnsList = q.list();
        String akaunTry = "";
        
        if (trnsList.size() > 1) {
            for (int i = 0; i < trnsList.size(); i++) {
                Transaksi t = trnsList.get(i);
                for (int y = i + 1; y < trnsList.size(); y++) {
                    Transaksi tr = trnsList.get(y);
                    if (!t.getAkaunKredit().getNoAkaun().equals(akaunTry)) {
                        t.setAkaunKredit(ac);
                        transList.add(t);
                        akaunTry = tr.getAkaunKredit().getNoAkaun();
                    }
                }
            }
        } else {
            for (Transaksi tt : trnsList) {
                tt.setAkaunKredit(ac);
                transList.add(tt);
            }
        }

        return listTr;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public ArrayList<Hakmilik> gethList() {
        return hList;
    }

    public void sethList(ArrayList<Hakmilik> hList) {
        this.hList = hList;
    }

    public ArrayList<Akaun> getAccList() {
        return accList;
    }

    public void setAccList(ArrayList<Akaun> accList) {
        this.accList = accList;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public int getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(int typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public List<String> getTarikhCek() {
        return tarikhCek;
    }

    public void setTarikhCek(List<String> tarikhCek) {
        this.tarikhCek = tarikhCek;
    }

    public BigDecimal getJumCaraBayar() {
        return jumCaraBayar;
    }

    public void setJumCaraBayar(BigDecimal jumCaraBayar) {
        this.jumCaraBayar = jumCaraBayar;
    }

    public boolean isBakiFlag() {
        return bakiFlag;
    }

    public void setBakiFlag(boolean bakiFlag) {
        this.bakiFlag = bakiFlag;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DokumenKewanganBayaran> getDkbList() {
        return dkbList;
    }

    public void setDkbList(List<DokumenKewanganBayaran> dkbList) {
        this.dkbList = dkbList;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public BigDecimal getReturnBalance() {
        return returnBalance;
    }

    public void setReturnBalance(BigDecimal returnBalance) {
        this.returnBalance = returnBalance;
    }
}
