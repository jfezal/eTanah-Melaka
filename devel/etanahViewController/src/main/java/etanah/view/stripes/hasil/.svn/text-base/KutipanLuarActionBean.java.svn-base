package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author haqqiem
 * 
 */
@UrlBinding("/hasil/kutipan_luar")
public class KutipanLuarActionBean extends AbleActionBean {

    private static Logger LOG = Logger.getLogger(KutipanLuarActionBean.class);
    SimpleDateFormat dfm = new java.text.SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dfmTime = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ssa");
    SimpleDateFormat dfmTime1 = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    private String URL = "jdbc:oracle:thin:@primus:1521/etanah";
    private static String MAIN_PAGE = "hasil/kutipan_luar.jsp";
    private FileBean txtFile;
    private String fileName;
    private static List<TransaksiTemp> senaraiTransaksi;
    private List<Map<String, Object>> senaraiTrans;
    String temp[];
    @Inject
    PenggunaDAO pgunaDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject KutipanHasilManager manager;
    @Inject KodDokumenDAO kodDokumenDAO;
    @Inject KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject KodKutipanDAO kodKutipanDAO;
    @Inject KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject KodBankDAO kodBankDAO;
    @Inject CaraBayarService caraBayarService;
    @Inject DokumenKewanganDAO dokumenKewanganDAO;
    @Inject KodTransaksiDAO kodTransaksiDAO;
    @Inject KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject PenyataPemungutService pp;
    @Inject TransactionService tranService;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP(MAIN_PAGE);
    }

    public Resolution check() {
        if (txtFile == null) {
            addSimpleError("Fail tidak dijumpai.");
        } else {
            try {

                fileName = txtFile.getFileName();
                Scanner scanner = new Scanner(txtFile.getInputStream()).useDelimiter("\n");
                processFile(scanner);
            } catch (Exception ex) {
                LOG.error("[ ERROR kutipan agensi ]", ex);
            }
        }
        return new JSP(MAIN_PAGE);
    }

    private void processFile(Scanner scanner) throws Exception {
        Date tarikh = null;
        String noAkaun = null;
        String noResit = null;
        BigDecimal bayaran = null;
        String kodCaraBayar = null;
        String caraBayar = null;
        BigDecimal amaunCaraBayar = null;
        String noRujukan = null;
        String kodBank = null;
        String pengguna = null;
        String tempDate = null;
        String resit = "";

        Map<String, Object> map = new HashMap<String, Object>();

        List<Map<String, Object>> senaraiTransTemp  = new ArrayList<Map<String, Object>>();
        senaraiTransaksi = new ArrayList<TransaksiTemp>();
        senaraiTrans = new ArrayList<Map<String, Object>>();


        while (scanner.hasNext()) {

            map = new HashMap<String, Object>();
            String content = scanner.next();

            TransaksiTemp trans = new TransaksiTemp();

            String delimiter = "\\|";
            temp = content.split(delimiter);
            for (int i = 0; i < temp.length; i++) {
                    LOG.info(temp[i]);
                    tarikh = dfmTime.parse(temp[0]);
                    noAkaun = temp[1];
                    noResit = temp[2];
                    bayaran = new BigDecimal(temp[3]);
                    kodCaraBayar = temp[4];
                    caraBayar = temp[5];
                    amaunCaraBayar = new BigDecimal(temp[6]);
                    noRujukan = temp[7];
                    kodBank = temp[8];
                    pengguna = temp[9];
                }

                trans.tarikh = tarikh;
                trans.noAkaun = noAkaun;
                trans.noResit = noResit;
                trans.bayaran = bayaran;
                trans.idCaraBayar = kodCaraBayar;
                trans.kodCaraBayar = caraBayar;
                trans.amaunCaraBayar = amaunCaraBayar;
                trans.noRujukan = noRujukan;
                trans.kodBank = kodBank;
                trans.pengguna = pengguna;

                senaraiTransaksi.add(trans);
                map.put("tarikh", trans.tarikh);
                map.put("noAkaun", trans.noAkaun);
                map.put("noResit", trans.noResit);
                map.put("bayaran", trans.bayaran);

            if(resit.equals(noResit)){continue;}
            else{
                senaraiTrans.add(map);
                resit = noResit;}
        }
    }

    public Resolution uploadFile() {
        LOG.info("uploadFile");
        LOG.info("senaraiTransaksi.size() : " + senaraiTransaksi.size());
        Pengguna pguna = new Pengguna();
        Akaun akh = new Akaun();
        Akaun akaun = new Akaun();
        CaraBayaran cb = new CaraBayaran();
        KodCaraBayaran kcb = new KodCaraBayaran();
        KodCawangan caw = new KodCawangan();
        KodBank kb = new KodBank();
        BigDecimal amaun = new BigDecimal(0.00);
        BigDecimal amaunCaraBayar = new BigDecimal(0.00);
        Date trkh = null;
        String resit = null;
        String resitTemp = "";
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        List<CaraBayaran> senaraiCaraBayar = new ArrayList<CaraBayaran>();
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        DokumenKewangan dk = new DokumenKewangan();
        List<DokumenKewangan> listDK = new ArrayList<DokumenKewangan>();

//        for (TransaksiTemp t : senaraiTransaksi) {
        for (int m=0; m<senaraiTransaksi.size(); m++) {
            TransaksiTemp t= senaraiTransaksi.get(m);
            tx = s.beginTransaction();
            try{
                pguna = pgunaDAO.findById(t.pengguna);

                String queryAKH = "SELECT a FROM etanah.model.Akaun a WHERE a.kodAkaun='AKH' AND a.cawangan =:kodCawangan";
                Query qAKH = sessionProvider.get().createQuery(queryAKH);
                qAKH.setString("kodCawangan", pguna.getKodCawangan().getKod());
                akh  =(Akaun) qAKH.uniqueResult();

                akaun = akaunDAO.findById(t.noAkaun);
                amaun = t.bayaran;
                amaunCaraBayar = t.amaunCaraBayar;
                trkh = t.tarikh;
                resit = t.noResit;
                caw = pguna.getKodCawangan();
                kcb = kodCaraBayaranDAO.findById(t.kodCaraBayar);
                if(t.kodBank != null){
                    kb = kodBankDAO.findById(t.kodBank);
                }

                Query qTrans = s.createQuery("SELECT tr FROM etanah.model.Transaksi tr where ((tr.akaunDebit.noAkaun =:akaun OR tr.akaunKredit.noAkaun =:akaun) " +
                                                   "AND tr.status.kod ='A') ORDER BY tr.kodTransaksi.keutamaan, tr.untukTahun");
                qTrans.setString("akaun",akaun.getNoAkaun());
                List<Transaksi> trList = qTrans.list();

                if(resitTemp.equals(resit)){
                    cb = caraBayarService.saveCaraBayaran(pguna, kcb, t.noRujukan, kb, t.amaunCaraBayar, dk, akaun);}
                else if(!resitTemp.equals(resit)){
                    BigDecimal baki = updateAkaun(akaun, amaun, pguna, ia, akh);
                    LOG.info("BAKI : "+baki);
                    dk = saveDokumenKewangan(resit, caw, pguna, akaun, amaun, trkh, kcb);
                    resitTemp = dk.getIdDokumenKewangan();
                    cb = caraBayarService.saveCaraBayaran(pguna, kcb, t.noRujukan, kb, t.amaunCaraBayar, dk, akaun);
                    if(baki.compareTo(new BigDecimal(0)) == 0){
                        saveTransaction(trList, caw, dk, pguna, ia, akh, akaun);
                        listDK.add(dk);
                    }
                    if(baki.compareTo(new BigDecimal(0)) < 0){
//                        LOG.info("----------------------------BAYAR LEBIH----------------------------");
                        saveTransaction(trList, caw, dk, pguna, ia, akh, akaun);
                        saveTransactionLebih(caw, dk, pguna, ia, akh, akaun, baki);
                        listDK.add(dk);
//                        LOG.info("----------------------------BAYAR LEBIH FINISH----------------------------");
                    }
                    if(baki.compareTo(new BigDecimal(0)) > 0){
//                        LOG.info("----------------------------BAYAR KURANG----------------------------");
                        tranService.saveTransaction(trList, amaun, akh, resit, pguna, ia, year);
//                        saveTransaction(trList, caw, dk, pguna, ia, akh, akaun);
//                        saveTransactionLebih(caw, dk, pguna, ia, akh, akaun, baki);
                        listDK.add(dk);
//                        LOG.info("----------------------------BAYAR KURANG FINISH----------------------------");
                    }//FIXME URGENT (KUTIPAN LUAR : BAYAR LEBIH)
                }
            }catch (Exception ex) {
                LOG.error(ex.getMessage(), ex);
                tx.rollback();
                addSimpleError("Pembayaran tidak berjaya.");
                return new JSP(MAIN_PAGE);
            }
        }
        tx.commit();
        pp.savePenyataPemungutKutipanLuar(listDK);
        addSimpleMessage("Maklumat telah berjaya di muat naik. Sila semak transaksi.");
        return new JSP(MAIN_PAGE);
    }

    public void saveTransactionLebih(KodCawangan caw, DokumenKewangan dk, Pengguna pguna, InfoAudit ia, Akaun akh, Akaun ak, BigDecimal baki){
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        Transaksi t = new Transaksi();

        t.setCawangan(caw);
        t.setKodTransaksi(kodTransaksiDAO.findById("61401"));
        t.setAmaun(baki.multiply(new BigDecimal(-1)));
        t.setDokumenKewangan(dk);
        t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(now);
        t.setInfoAudit(ia);
        t.setAkaunKredit(ak);
        t.setAkaunDebit(akh);
        t.setUntukTahun(year);
        t.setTahunKewangan(year);
        manager.save(t);
    }

    public BigDecimal  updateAkaun(Akaun akaun, BigDecimal amaun, Pengguna pguna, InfoAudit ia, Akaun akh) {
        BigDecimal total = akaun.getBaki().subtract(amaun);
        LOG.info(total);
        Date now = new Date();

        ia.setDimasukOleh(akaun.getInfoAudit().getDimasukOleh());
        ia.setTarikhMasuk(akaun.getInfoAudit().getTarikhMasuk());
        ia.setDiKemaskiniOleh(pguna);
        ia.setTarikhKemaskini(now);

        akaun.setInfoAudit(ia);
        akaun.setBaki(total);

        manager.saveOrUpdate(akaun);

        // AKH ikut id pengutip semasa kutipan luar dibuat
        akh.setBaki(akh.getBaki().add(amaun));
        akh.setInfoAudit(ia);

        manager.saveOrUpdate(akh);
        return total;
    }

    public DokumenKewangan saveDokumenKewangan(String noResit, KodCawangan caw, Pengguna pguna, Akaun akaun,
            BigDecimal amaun, Date trkh, KodCaraBayaran kcb) {
        DokumenKewangan dk = new DokumenKewangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(now);

        dk.setIdDokumenKewangan(noResit);
        dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
        dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
        dk.setInfoAudit(ia);
        dk.setCawangan(caw);
        if(kcb.getKod().equals("T")){dk.setAmaunTunai(amaun);}
        dk.setIdKaunter(pguna.getIdKaunter());
        dk.setIsuKepada(akaun.getPemegang().getNama());

        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
//        adkb = saveCarpaBayaran(senaraiCaraBayaran, tarikhCek, ia, pguna, dk, adkb, amaun);
        dk.setSenaraiBayaran(adkb);
        dk.setAkaun(akaun);
        dk.setMod(kodKutipanDAO.findById('L'));
        dk.setAmaunBayaran(amaun);
        dk.setTarikhTransaksi(trkh);

        manager.saveOrUpdate(dk);
        return dk;
    }

    public ArrayList<DokumenKewanganBayaran> saveCaraBayaran(List<CaraBayaran> senaraiCaraBayaran, List<String> tarikhCek, InfoAudit ia,
                                                                                                                                      Pengguna pguna, DokumenKewangan dk,
                                                                                                                                      ArrayList<DokumenKewanganBayaran> adkb, BigDecimal jumcaj) throws ParseException {
        for (int f = 0; f < senaraiCaraBayaran.size(); f++) {
            CaraBayaran cb = senaraiCaraBayaran.get(f);
            if (cb.getAmaun() != null) {
                if (cb.getAmaun().intValue() > 0) {
                    KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                    if (!crByr.getKod().equals("T")) {
                        Date d = dfmTime.parse(tarikhCek.get(f));
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
                    cb.setCawangan(pguna.getKodCawangan());
                    cb.setInfoAudit(ia);
                    cb.setAktif('Y');
                    if (crByr.getKod().equals("T")) {
                            cb.setAmaun(jumcaj);
                    }
                    manager.saveOrUpdate(cb);
                    DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                    for (int i = 0; i < senaraiCaraBayaran.size(); i++) {
                        CaraBayaran c = senaraiCaraBayaran.get(i);
                        if (c.getKodCaraBayaran() != null) {
                            if (c.getKodCaraBayaran().getKod().equals("T")) {
                                dk.setAmaunTunai(c.getAmaun());
                            }
                        }
                    }
                    dkb.setCaraBayaran(cb);
                    dkb.setDokumenKewangan(dk);
                    dkb.setAmaun(cb.getAmaun());
                    dkb.setInfoAudit(ia);
                    dkb.setAktif('Y');
                    adkb.add(dkb);
                }
            }
        }
        return adkb;
    }

    public void saveTransaction(List<Transaksi>trList, KodCawangan caw, DokumenKewangan dk, Pengguna pengguna,
                            InfoAudit ia, Akaun akh,  Akaun ak){
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        for(Transaksi tr1 : trList){
            if((tr1.getStatus().getKod()=='A')&&(tr1.getAmaun().compareTo(new BigDecimal(0))>0)){
                Transaksi t = new Transaksi();

                t.setCawangan(caw);
                t.setKodTransaksi(tr1.getKodTransaksi());
                t.setAmaun(tr1.getAmaun());
                t.setDokumenKewangan(dk);
                t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(now);
                t.setInfoAudit(ia);
                if((tr1.getAkaunKredit()!=null)&&(tr1.getAkaunDebit()!=null)){
                    t.setAkaunDebit(ak);
                }else{
                    t.setAkaunDebit(akh);
                }
                if((tr1.getAkaunDebit()!=null)&&(tr1.getAkaunDebit().getNoAkaun().equals(ak.getNoAkaun()))){
                    t.setAkaunKredit(ak);
                }
                t.setUntukTahun(tr1.getUntukTahun());
                t.setTahunKewangan(year);
                manager.save(t);
            }
        }
    }

    public FileBean getTxtFile() {
        return txtFile;
    }

    public void setTxtFile(FileBean txtFile) {
        this.txtFile = txtFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Map<String, Object>> getSenaraiTrans() {
        return senaraiTrans;
    }

    public void setSenaraiTrans(List<Map<String, Object>> senaraiTrans) {
        this.senaraiTrans = senaraiTrans;
    }

    public List<TransaksiTemp> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<TransaksiTemp> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public static class TransaksiTemp {
//        public Long transID;

        public Date tarikh;
        public String noAkaun;
        public String noResit;
        public BigDecimal bayaran;
        public String idCaraBayar;
        public String kodCaraBayar;
        public BigDecimal amaunCaraBayar;
        public String noRujukan;
        public String kodBank;
        public String pengguna;
    }
}
