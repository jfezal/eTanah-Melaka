package etanah.view.stripes.hasil;

import java.text.ParseException;
import java.util.*;
import etanah.dao.*;
import able.stripes.*;
import etanah.model.*;
import java.math.BigDecimal;
import etanah.report.ReportUtil;
import com.google.inject.Inject;
import etanah.sequence.GeneratorNoResit;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.*;
import etanah.view.etanahActionBeanContext;
import etanah.service.KaunterService;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */
@HttpCache(allow = false)
@Wizard(startEvents = {"showForm", "search", "showEditPemohon", "delete", "chooseIDHakmilik", "cetak", "deleteSelected", "storeNoAkaun"})
@UrlBinding("/hasil/kutipan_hasil_n9")
public class KutipanHasilActionBeanN9 extends AbleActionBean{
    private static final Logger logger = Logger.getLogger(KutipanHasilActionBeanN9.class);

    private KodAkaun kodAkaun;
    private BigDecimal total;
    private BigDecimal returnBalance = new BigDecimal(0.00);

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");

    private AkaunDAO accDAO;
    private KodDokumenDAO kodDokumenDAO;
    private DokumenKewanganDAO dokumenKewanganDAO;

    private List<Akaun> list = new ArrayList<Akaun>();
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private ArrayList<Hakmilik> hakmilikList = new ArrayList<Hakmilik>();

    @Inject
    public KutipanHasilActionBeanN9(DokumenKewanganDAO dokumenKewanganDAO, KodDokumenDAO kodDokumenDAO, AkaunDAO accDAO) {
        this.accDAO = accDAO;
        this.kodDokumenDAO = kodDokumenDAO;
        this.dokumenKewanganDAO = dokumenKewanganDAO;
    }

    @Inject
    KutipanHasilManager manager;
    @Inject
    KutipanHasilService hasilService;
    @Inject
    private KodAkaunDAO kodAkaunDAO;
    @Inject
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject
    private KaunterService kaunterService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private etanah.kodHasilConfig hasil;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject
    private KodKutipanDAO kodKutipanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    GeneratorNoResit noResitGenerator;
    @Inject
    CaraBayarService caraBayarService;

    public ArrayList bayaranN9(ArrayList<Hakmilik> hakmilikList, BigDecimal jumCaraBayar, String mod, boolean bakiFlag, List<String> tarikhCek,
                                           ArrayList kewDokID, BigDecimal jumlahCaj, Pengguna pengguna, etanahActionBeanContext ctx) throws ParseException {
        logger.info("---------------------------------bayaranN9---------------------------------");

        ArrayList arr = new ArrayList();
        DokumenKewangan dk = null;

        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));

        InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());

        String ac = "";
        
        String resit = null;
        ArrayList<DokumenKewanganBayaran> listAdkb = new ArrayList<DokumenKewanganBayaran>();
        for (int m = 0; m < hakmilikList.size(); m++) {
            BigDecimal amnt = new BigDecimal(0);
            BigDecimal amnt1 = new BigDecimal(0);

            Hakmilik h = hakmilikList.get(m);

            String queryAcc = "SELECT a FROM etanah.model.Akaun a where a.hakmilik.idHakmilik = :idHakmilik AND a.kodAkaun.kod = 'AC' ";
            Query qAcc = sessionProvider.get().createQuery(queryAcc);
            qAcc.setString("idHakmilik", h.getIdHakmilik());
            Akaun a = (Akaun) qAcc.uniqueResult();
            String noAkaun = a.getNoAkaun();
            logger.info("a.noAkaun : "+a.getNoAkaun()+"  "+noAkaun);

            List<Transaksi> listT = new ArrayList<Transaksi>();
          
            String query =  "SELECT tr FROM etanah.model.Transaksi tr where ((tr.akaunDebit.noAkaun =:noAkaun " +
                                 "AND tr.status.kod ='A') AND tr.amaun > 0) ORDER BY tr.kodTransaksi.keutamaan";
//                    "SELECT t FROM etanah.model.Transaksi t where t.akaunKredit.noAkaun = :noAkaun OR t.akaunDebit.noAkaun = :noAkaun";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("noAkaun", noAkaun);
            List<Transaksi>listTr = (List<Transaksi>)q.list();
            logger.info("listTr.size() : "+listTr.size());

            Akaun k = accDAO.findById(noAkaun);
            BigDecimal amt = new BigDecimal(0);
            for (Transaksi tr : listTr) {
                if (tr.getAmaun().doubleValue() > 0) {
                    amt = amt.add(tr.getAmaun());
                }
                if (tr.getAkaunKredit() == null) {
                    listT.add(tr);
                }
            }

            resit = noResitGenerator.generateNoResit(ctx.getKodNegeri(), caw , pengguna);
            // resit
            dk = new DokumenKewangan();
            dk.setIdDokumenKewangan(resit);
            // set idResit to pageContext
            ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
//            dk.setAmaunBayaran(jumCaraBayar);
            dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
            dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
            dk.setInfoAudit(ia);
            dk.setCawangan(caw);
            // save cara bayaran
            BigDecimal bg = new BigDecimal(0);
            kewDokID.add(resit);

            ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
            adkb = caraBayarService.saveCaraBayaran(senaraiCaraBayaran, tarikhCek, ia, pengguna, noAkaun, bakiFlag, bg, dk, adkb, jumlahCaj);
            listAdkb = adkb;
            String tmp = dk.getIdDokumenKewangan();
            arr.add(tmp);
            dk.setIsuKepada((accDAO.findById(noAkaun).getPemegang().getNama()));
            dk.setSenaraiBayaran(adkb);
            dk.setAkaun(accDAO.findById(noAkaun));
            if(mod.equalsIgnoreCase("Lewat"))
                dk.setMod(kodKutipanDAO.findById('L'));
            if(mod.equalsIgnoreCase("Biasa"))
                dk.setMod(kodKutipanDAO.findById('B'));
            if(bakiFlag == true){
                Akaun x = accDAO.findById(noAkaun);
                dk.setAmaunBayaran(x.getBaki());
            }
            if(bakiFlag == false){
                dk.setAmaunBayaran(jumCaraBayar);
            }
            manager.saveOrUpdate(dk);

             //update akaunCukai Tanah
            BigDecimal tot = new BigDecimal(0);
//            for (int x = 0; x < akaunList.size(); x++) {
//                akn = aList.get(x);
                tot = k.getBaki().subtract(jumCaraBayar);
                if(bakiFlag == true){
                    k.setBaki(new BigDecimal(0));
                }else{
                    k.setBaki(tot);
                }
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(now);
                k.setInfoAudit(ia);
                manager.saveOrUpdate(k);
//            }

            //update Akaun Kutipan Harian : START
            kodAkaun = kodAkaunDAO.findById("AKH");
            String [] n1  = {"kodAkaun"};
            Object [] v1 = {getKodAkaun()};
            List<Akaun> accountList = accDAO.findByEqualCriterias(n1, v1, null);
            for(Akaun acnt : accountList){
                if(acnt.getCawangan().getKod().equals(caw.getKod())){
                    ac = acnt.getNoAkaun();
                }
            }
            Akaun akh = accDAO.findById(ac);
            BigDecimal bal = new BigDecimal(0);
            if(bakiFlag){
                bal = akh.getBaki().add(bg);
            }else{
                bal = akh.getBaki().add(jumCaraBayar);
            }
            akh.setBaki(bal);
            manager.saveOrUpdate(akh);
            //update Akaun Kutipan Harian : FINISH


            boolean rem = false;
            BigDecimal amaunRem = new BigDecimal(0);
            BigDecimal totalTransList = new BigDecimal(0);
            List<Transaksi> senaraiTransaksiA = new ArrayList<Transaksi>();
            for(Transaksi t : listT){
                if(t.getKodTransaksi().getKod().equals("99000")||t.getKodTransaksi().getKod().equals("99001")||
                        t.getKodTransaksi().getKod().equals("99002")||t.getKodTransaksi().getKod().equals("99003")){
                        rem = true;
                        amaunRem = t.getAmaun();
                }
                if(t.getStatus().getKod() == 'A'){
                    if((t.getAkaunDebit()!=null)&&(t.getAkaunDebit().getNoAkaun().equals(noAkaun))){
                        totalTransList = totalTransList.add(t.getAmaun());
                    }else{
                        totalTransList = totalTransList.subtract(t.getAmaun());
                    }
                    senaraiTransaksiA.add(t);
                }
            }
            List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
            if((jumlahCaj.doubleValue() > 0.0)&&(jumlahCaj.doubleValue() < totalTransList.doubleValue())){
                hasilService.saveSecondPayment(k.getSenaraiTransaksiDebit(), k.getSenaraiTransaksiKredit(), h,
                                               totalTransList, jumlahCaj, jumCaraBayar, resit, pengguna, akh);

            }else{
                if(jumlahCaj.doubleValue() <= 0.0){
                    bayarSemula(pengguna, caw, dk, akh, noAkaun, jumCaraBayar);
                }if(((jumlahCaj.doubleValue() > 0.0)&&(jumlahCaj.compareTo(jumCaraBayar)<=0))) {
                    if(rem){
                        for (int e = 0; e < listT.size(); e++) {
                            Akaun aa = accDAO.findById(noAkaun);
                            Transaksi tr = listT.get(e);
                            if((tr.getStatus().getKod()=='A')){
                                if((tr.getKodTransaksi().getKod().equals("99000"))||(tr.getKodTransaksi().getKod().equals("99001"))||
                                    (tr.getKodTransaksi().getKod().equals("99002"))||(tr.getKodTransaksi().getKod().equals("99003"))){
                                }else{
                                    if(tr.getKodTransaksi().getKod().equals("76152")){  //for NS
        //                            if(tr.getKodTransaksi().getKod().equals("61402")){  //for Melaka
                                        if((tr.getAkaunKredit()!=null)&&(tr.getAkaunKredit().getNoAkaun().equals(noAkaun))){
                                            tr.setAmaun(tr.getAmaun().multiply(new BigDecimal(-1)));
                                        }
                                        senaraiTransaksi.add(tr);
                                    }else{
                                        Transaksi trans = new Transaksi();
                                        trans.setCawangan(caw);
                                        trans.setKodTransaksi(tr.getKodTransaksi());
                                        if(trans.getKodTransaksi().getKod().equals("61401")){
                                            trans.setAmaun(tr.getAmaun().subtract(amaunRem));
                                        }else{
                                            trans.setAmaun(tr.getAmaun());
                                        }
                                        trans.setDokumenKewangan(dk);
                                        trans.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                                        trans.setUntukTahun(tr.getUntukTahun());
                                        trans.setTahunKewangan(year);
                                            ia.setDimasukOleh(pengguna);
                                            ia.setTarikhMasuk(now);
                                        trans.setInfoAudit(ia);
                                        if((tr.getAkaunKredit()!=null)&&(tr.getAkaunDebit()!=null)){
                                            trans.setAkaunDebit(aa);
                                        }else{
                                            trans.setAkaunDebit(akh);
                                        }
                                        if((tr.getAkaunDebit()!=null)&&(tr.getAkaunDebit().getNoAkaun().equals(noAkaun))){
                                            trans.setAkaunKredit(a);
                                        }
                                        manager.save(trans);
                                    }
                                }
                                if (tr.getAmaun().doubleValue() > 0) {
                                    amnt = amnt.add(tr.getAmaun());
                                } else if (tr.getAmaun().doubleValue() < 0) {
                                    amnt1 = amnt1.add(tr.getAmaun());
                                }
                            }
                        }
                    }
                    else{
                        for (int e = 0; e < listT.size(); e++) {
                            Akaun aa = accDAO.findById(noAkaun);
                            Transaksi tr = listT.get(e);
                            if(tr.getStatus().getKod()=='A'){
//                                if(tr.getKodTransaksi().getKod().equals("76152")){      // for NS
//                                    System.out.println("-______________________________________________________-");
//        //                        if(tr.getKodTransaksi().getKod().equals("61402")){      // for Melaka
//                                    if((tr.getAkaunKredit()!=null)&&(tr.getAkaunKredit().getNoAkaun().equals(noAkaun))){
//                                        tr.setAmaun(tr.getAmaun().multiply(new BigDecimal(-1)));
//                                    }
//                                    senaraiTransaksi.add(tr);
//                                }else{
                                    Transaksi trans = new Transaksi();
                                    trans.setCawangan(caw);
                                    trans.setKodTransaksi(tr.getKodTransaksi());
                                    trans.setAmaun(tr.getAmaun());
                                    trans.setDokumenKewangan(dk);
                                    trans.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                                    trans.setUntukTahun(tr.getUntukTahun());
                                    trans.setTahunKewangan(year);
                                        ia.setDimasukOleh(pengguna);
                                        ia.setTarikhMasuk(now);
                                    trans.setInfoAudit(ia);
                                    if((tr.getAkaunKredit()!=null)&&(tr.getAkaunDebit()!=null)){
                                        trans.setAkaunDebit(aa);
                                    }else{
                                        trans.setAkaunDebit(akh);
                                    }
                                    if((tr.getAkaunDebit()!=null)&&(tr.getAkaunDebit().getNoAkaun().equals(noAkaun))){
                                        trans.setAkaunKredit(aa);
                                    }
                                    manager.save(trans);
//                                }
                            }
                            if (tr.getAmaun().doubleValue() > 0) {
                                amnt = amnt.add(tr.getAmaun());
                            } else if (tr.getAmaun().doubleValue() < 0) {
                                amnt1 = amnt1.add(tr.getAmaun());
                            }
                        }
                    }
                    BigDecimal jumDenda = new BigDecimal(0);
                    for(Transaksi tr : senaraiTransaksi){
                        jumDenda = jumDenda.add(tr.getAmaun());
                    }
                    String kodTransaksi = "";

                    if(bakiFlag){
                        returnBalance = tot.multiply(new BigDecimal(-1));
                    }else{
                        returnBalance = BigDecimal.ZERO;
                    }
                    if (tot.doubleValue() < 0) {
                        if (bakiFlag == false) {
                            Transaksi t = new Transaksi();
                            Akaun akaun = new Akaun();
                            akaun.setNoAkaun(noAkaun);
                            t.setAkaunKredit(akaun);
                            t.setAkaunDebit(akh);
                            total = tot.multiply(new BigDecimal(-1));
                            t.setAmaun(total);
                            t.setCawangan(caw);
                            t.setDokumenKewangan(dokumenKewanganDAO.findById(dk.getIdDokumenKewangan()));
                            KodTransaksi kt = new KodTransaksi();
                            kt.setKod(hasil.getProperty("cukaiTanahSemasa"));     //for NS
                            //                kt.setKod("82599");     //for Melaka
                            t.setKodTransaksi(kt);
                            t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(now);
                            t.setInfoAudit(ia);
                            t.setUntukTahun(year);
                            t.setTahunKewangan(year);
                            manager.save(t);
                        }
                    }
                }if(((jumlahCaj.doubleValue() > 0.0)&&(jumlahCaj.compareTo(jumCaraBayar)==1))){
                    hasilService.bayarKurang1(senaraiTransaksiA, pengguna, caw, dk, akh, noAkaun, jumCaraBayar,year, ia);
                }
            }
        }
        return kewDokID;
    }

    public void bayarSemula(Pengguna pengguna, KodCawangan caw, DokumenKewangan dk, Akaun acc, String account, BigDecimal jumCaraBayar){
        Transaksi t = new Transaksi();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        Akaun akaun = new Akaun();
        akaun.setNoAkaun(account);
        t.setAkaunKredit(akaun);
        t.setAkaunDebit(acc);
        t.setAmaun(jumCaraBayar);
        t.setCawangan(caw);
        t.setDokumenKewangan(dokumenKewanganDAO.findById(dk.getIdDokumenKewangan()));
        KodTransaksi kt = new KodTransaksi();
        kt.setKod(hasil.getProperty("cukaiTanahSemasa"));
        t.setKodTransaksi(kt);
        t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        t.setInfoAudit(ia);
        t.setUntukTahun(year);
        t.setTahunKewangan(year);
        manager.save(t);
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public List<Akaun> getList() {
        return list;
    }

    public void setList(List<Akaun> list) {
        this.list = list;
    }

    public ArrayList<Hakmilik> getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(ArrayList<Hakmilik> hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public KodAkaun getKodAkaun() {
        return kodAkaun;
    }

    public void setKodAkaun(KodAkaun kodAkaun) {
        this.kodAkaun = kodAkaun;
    }

    public BigDecimal getReturnBalance() {
        return returnBalance;
    }

    public void setReturnBalance(BigDecimal returnBalance) {
        this.returnBalance = returnBalance;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
