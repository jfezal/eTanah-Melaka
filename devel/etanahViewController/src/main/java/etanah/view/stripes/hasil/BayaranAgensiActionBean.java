package etanah.view.stripes.hasil;

import java.text.ParseException;
import java.util.*;
import etanah.model.*;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import etanah.dao.*;
import etanah.sequence.GeneratorNoResit2;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.Wizard;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Transaction;

/**
 *
 * @author abdul.hakim
 */

@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/bayaran_agensi")
public class BayaranAgensiActionBean extends AbleActionBean{
    private static final Logger logger = Logger.getLogger(BayaranAgensiActionBean.class);

    private String tarikhDari;
    private String tarikhHingga;
    private String agensi;
    private static String kodNegeri;
    private boolean flag = false;

    private static BigDecimal cukaiTanah = new BigDecimal(0);
    private static BigDecimal tunggakanCukaiTanah = new BigDecimal(0);
    private static BigDecimal dendaLewat = new BigDecimal(0);
    private static BigDecimal notis = new BigDecimal(0);
    private BigDecimal jumlah = new BigDecimal(0);
    private BigDecimal jumlahBayar = new BigDecimal(0);
    private static BigDecimal temp = new BigDecimal(0);
    
    private KodPBT pbt;
    private DokumenKewangan dokumenKewangan;
    private Transaksi transaksi;

    private List<KodPBT> senaraiKodPBT = new ArrayList<KodPBT>();
    private List<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<String> tarikhCek = new ArrayList<String>();
    private List<DokumenKewanganBayaran> dkbList = new ArrayList<DokumenKewanganBayaran>();
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private static List<Object> tempList = new ArrayList<Object>();
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");

    @Inject
    private etanah.Configuration conf;

    @Inject
    PenyataPemungutService pp;

    @Inject
    private KodCawanganDAO kodCawanganDAO;

    @Inject
    private AkaunDAO akaunDAO;

    @Inject
    private KodAkaunDAO kodAkaunDAO;

    @Inject
    private KodBankDAO kodBankDAO;

    @Inject
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;

    @Inject
    private KodDokumenDAO kodDokumenDAO;

    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    
    @Inject
    private KodKutipanDAO kodKutipanDAO;

    @Inject
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    
    
    @Inject
    private ModKutipService modKutip;

    @Inject
    private KodAgensiKutipanDAO kodAgensiKutipanDAO;

    @Inject
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    private DokumenKewanganDAO dokumenKewanganDAO;

    @Inject
    private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;

    @Inject
    TransaksiDAO transaksiDAO;
    
    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        logger.info("Step1");
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "negeriSembilan";
        }

        tempList = new ArrayList<Object>();
        cukaiTanah = new BigDecimal(0);
        tunggakanCukaiTanah = new BigDecimal(0);
        dendaLewat = new BigDecimal(0);

        senaraiKodPBT = senaraiPBT();
        logger.info("senaraiKodPBT.size() : "+senaraiKodPBT.size());

        return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran_agensi.jsp");
     }

    public List<KodPBT> senaraiPBT(){
        List<KodPBT> senaraiKod = new ArrayList<KodPBT>();

        String sql = "select pbt from KodAgensiKutipan pbt order by pbt.kod";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);
        senaraiKod = q.list();

        return senaraiKod;
    }

    @HandlesEvent("Step2")
    public Resolution searching() throws ParseException {
        flag = true;
        logger.info("pbt : "+agensi);
        logger.info("tarikhDari : "+tarikhDari);
        logger.info("tarikhHingga : "+tarikhHingga);

        tempList = new ArrayList<Object>();
        cukaiTanah = new BigDecimal(0);
        tunggakanCukaiTanah = new BigDecimal(0);
        dendaLewat = new BigDecimal(0);
        
        KodAgensiKutipan ak = kodAgensiKutipanDAO.findById(agensi);
        Akaun a = akaunDAO.findById(ak.getAkaun().getNoAkaun());
        /*Akaun a;
        if (!"JOMPY".equals(ak.getKod())) {
            a = akaunDAO.findById(ak.getAkaun().getNoAkaun());
        } else {
            a = akaunDAO.findById("JOMPY");
        }*/



        Date from = sdf.parse(tarikhDari);
        Date untill = sdf.parse(tarikhHingga);
        Calendar c = Calendar.getInstance();
            c.setTime(untill);
            c.add(Calendar.DATE, 1);
        Date newDate = c.getTime();

//        SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.infoAudit.tarikhMasuk BETWEEN to_date('25/11/2010','dd/MM/yyyy') AND to_date('27/11/2010','dd/MM/yyyy')

        Session s1 = sessionProvider.get();
        String sql1 ="SELECT sum(kt.amaun), kt.kodTransaksi.kod " +
                            "FROM etanah.model.Transaksi kt, etanah.model.DokumenKewangan kd " +
                            "WHERE kt.kodTransaksi.kod in ('61401','61402','76152','99011') " +
                            "AND kt.akaunDebit.noAkaun like :akaun " +
                            "AND kt.bayaranAgensi = 'N' " +
                            "AND kt.dokumenKewangan.idDokumenKewangan = kd.idDokumenKewangan " +
                            "AND (kd.tarikhTransaksi BETWEEN to_date(:tarikhDari,'dd/MM/yyyy') AND to_date(:tarikhHingga,'dd/MM/yyyy')) " +
//                            "WHERE kt.kodTransaksi.kod not in ('99000','99003','99001','99002','99004') " +
                            "GROUP by kt.kodTransaksi";
        
        Query q1 = s1.createQuery(sql1);
        if (!"JOMPY".equals(ak.getKod())) {
           q1.setString("akaun", a.getNoAkaun()); 
        } else {
           q1.setParameter("akaun", "%" + "JOMPY" + "%"); 
        }
        q1.setString("tarikhDari", tarikhDari);
        q1.setString("tarikhHingga", sdf.format(newDate));

        List<Object> tList = q1.list();
        logger.info("tList.size() : "+tList.size());
        tempList = tList;
        logger.info("tempList.size() : "+tempList.size());

        String kod = null;
        for (Iterator it = q1.iterate(); it.hasNext();) {
            Object[] row = (Object[]) it.next();

            kod = (String) row[1];
            if (kod.equals("61401")) {
                cukaiTanah = (BigDecimal) row[0];
            } if (kod.equals("76152")) {
                dendaLewat = (BigDecimal) row[0];
            }if (kod.equals("61402")) {
                tunggakanCukaiTanah = (BigDecimal) row[0];
            }if (kod.equals("99011")) {
                notis = (BigDecimal) row[0];
            }
            jumlah = jumlah.add((BigDecimal) row[0]);
        }
        temp = jumlah;
        logger.info("cukaiTanah : "+cukaiTanah);
        logger.info("tunggakanCukaiTanah : "+tunggakanCukaiTanah);
        logger.info("dendaLewat : "+dendaLewat);
        logger.info("jumlah : "+jumlah);
        logger.info("notis : "+notis);
//        showForm();
        senaraiKodPBT = senaraiPBT();
       return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran_agensi.jsp");
    }

    @HandlesEvent("Step3")
    public Resolution next() {
        logger.info("step 3");
        jumlahBayar = temp;
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
        return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran_agensi_1.jsp");
    }

    @Inject
    GeneratorNoResit2 noResitGenerator;

    @Inject
    KutipanHasilManager manager;

    @HandlesEvent("Step4")
    public Resolution bayar() throws ParseException {
        logger.info("step 4");
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        DokumenKewangan kewDokumen = new DokumenKewangan();
        
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        logger.info("cukaiTanah : "+cukaiTanah);
        logger.info("tunggakanCukaiTanah : "+tunggakanCukaiTanah);
        logger.info("dendaLewat : "+dendaLewat);
        logger.info("jumlah : "+jumlah);
        logger.info("notis : "+notis);
        logger.info("tempList.size : "+tempList.size());
        
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        KodAgensiKutipan ak = kodAgensiKutipanDAO.findById(agensi);
        

        tx = s.beginTransaction();
        try{
            DokumenKewangan dk = null;
    //        String resit = noResitGenerator.generateNoResit(ctx.getKodNegeri(), caw , pengguna);
            String resit = noResitGenerator.getAndLockSerialNo(pengguna);
            logger.info("resit : "+resit);
            // resit
            dk = new DokumenKewangan();
            dk.setIdDokumenKewangan(resit);
            ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
            dk.setAmaunBayaran(jumlahBayar);
            dk.setIsuKepada(ak.getNama());
    //        dk.setNoRujukan(dokumenKewangan.getNoRujukan());
            dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
            dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
    //        dk.setIsuKepada(dokumenKewangan.getIsuKepada());
            dk.setInfoAudit(ia);
            dk.setCawangan(caw);
            dk.setIdKaunter(pengguna.getIdKaunter());
            dk.setTarikhTransaksi(now);
            String mod = modKutip.loadPenyerahFromSession(ctx);
            if(mod.equalsIgnoreCase("Lewat"))
                dk.setMod(kodKutipanDAO.findById('L'));
            if(mod.equalsIgnoreCase("Biasa"))
                dk.setMod(kodKutipanDAO.findById('B'));

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
                        dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                        dkb.setInfoAudit(ia);
                        dkb.setAktif('Y');
                        adkb.add(dkb);
                    }
                }
            }
            String tmp = dk.getIdDokumenKewangan();
            dk.setSenaraiBayaran(adkb);
            manager.saveOrUpdate(dk);

            Akaun akaunKr = ak.getAkaun();
            akaunKr.setBaki(akaunKr.getBaki().subtract(temp));
            manager.saveOrUpdate(akaunKr);

            Akaun akh = new Akaun();
            akh = akaunDAO.findById(akaunKutipanHarian(pengguna));
            int year = Integer.parseInt(yy.format(now));

            String kod = null;
            for (Iterator it = tempList.iterator(); it.hasNext();) {
                Object[] row = (Object[]) it.next();

                kod = (String) row[1];
                transaksi = new Transaksi();
                transaksi.setCawangan(caw);
                if (kod.equals("61401")) {
                    transaksi.setKodTransaksi(kodTransaksiDAO.findById("61401"));
                    transaksi.setAmaun(cukaiTanah);
                } if (kod.equals("76152")) {
                    transaksi.setKodTransaksi(kodTransaksiDAO.findById("76152"));
                    transaksi.setAmaun(dendaLewat);
                }if (kod.equals("61402")) {
                    transaksi.setKodTransaksi(kodTransaksiDAO.findById("61402"));
                    transaksi.setAmaun(tunggakanCukaiTanah);
                }if (kod.equals("99011")) {
                    transaksi.setKodTransaksi(kodTransaksiDAO.findById("99011"));
                    transaksi.setAmaun(notis);
                }
                transaksi.setDokumenKewangan(dk);
                transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                transaksi.setInfoAudit(ia);
                transaksi.setUntukTahun(year);
                transaksi.setTahunKewangan(year);
                transaksi.setAkaunDebit(akh);
                transaksi.setAkaunKredit(akaunKr);
                transaksi.setBayaranAgensi("N");

                manager.save(transaksi);
            }
            pp.savePenyataPemungut(dk);

            updateColumnAgensiPymnt(akaunKr, tarikhDari, tarikhHingga, pengguna);
            transaksiList(resit);
            caraBayar(resit);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            tx.rollback();
            noResitGenerator.rollbackAndUnlockSerialNo(pengguna);
            addSimpleError("Pembayaran tidak berjaya.");
        }finally{
            noResitGenerator.commitAndUnlockSerialNo(pengguna);
        }
        
        return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran_agensi_2.jsp");
    }

    public void updateColumnAgensiPymnt(Akaun akaunAgensi, String tarikhMula, String tarikhAkhir, Pengguna pguna) throws ParseException {
        Date untill = sdf.parse(tarikhAkhir);
        Calendar c = Calendar.getInstance();
        c.setTime(untill);
        c.add(Calendar.DATE, 1);
        Date newDate = c.getTime();
        logger.info("akaunAgensi.getNoAkaun() : "+akaunAgensi.getNoAkaun());
        logger.info("tarikhMula : "+tarikhMula);
        logger.info("tarikhAkhir : "+tarikhAkhir);
        logger.info("pguna.getIdPengguna() : "+pguna.getIdPengguna());

        Session s = sessionProvider.get();
        String sql1 = "SELECT kt.idTransaksi, kt.kodTransaksi.kod "
                + "FROM etanah.model.Transaksi kt, etanah.model.DokumenKewangan kd "
                + "WHERE kt.kodTransaksi.kod in ('61401','61402','76152') "
                + "AND kt.akaunDebit.noAkaun = :akaun "
                + "AND kt.bayaranAgensi = 'N' "
                + "AND kt.dokumenKewangan.idDokumenKewangan = kd.idDokumenKewangan "
                + "AND (kd.tarikhTransaksi BETWEEN to_date(:tarikhDari,'dd/MM/yyyy') AND to_date(:tarikhHingga,'dd/MM/yyyy')) ";

        Query q1 = s.createQuery(sql1);
        q1.setString("akaun", akaunAgensi.getNoAkaun());
        q1.setString("tarikhDari", tarikhMula);
        q1.setString("tarikhHingga", sdf.format(newDate));

        List<Object> tList = q1.list();
        logger.info("tList.size() : "+tList.size());
        Long idTrans = 0L;
        for (Iterator it = q1.iterate(); it.hasNext();) {
            Object[] row = (Object[]) it.next();
            InfoAudit ia = new InfoAudit();

            idTrans = (Long) row[0];
            logger.info("idTrans : "+idTrans);
            String sqlTransaksi = "SELECT tr FROM etanah.model.Transaksi tr WHERE tr.idTransaksi = :idtrans";
            Query q1Transaksi = s.createQuery(sqlTransaksi);
            q1Transaksi.setLong("idtrans", idTrans);

            Transaksi t = (Transaksi) q1Transaksi.uniqueResult();
            logger.info("----------------------------------------------------- "+t.getIdTransaksi());
            t.setBayaranAgensi("Y");
                ia.setDimasukOleh(t.getInfoAudit().getDimasukOleh());
                ia.setTarikhMasuk(t.getInfoAudit().getTarikhMasuk());
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
            t.setInfoAudit(ia);
            manager.saveOrUpdate(transaksi);
        }
    }

    @HandlesEvent("Step5")
    public Resolution mainMenu() {
        tempList = new ArrayList<Object>();
        cukaiTanah = new BigDecimal(0);
        tunggakanCukaiTanah = new BigDecimal(0);
        dendaLewat = new BigDecimal(0);
        return new RedirectResolution(BayaranAgensiActionBean.class);
    }

    public void transaksiList(String resit){
        dokumenKewangan = new DokumenKewangan();
        dokumenKewangan = dokumenKewanganDAO.findById(resit);
        logger.info("dokumenKewangan.getIdDokumenKewangan() : "+dokumenKewangan.getIdDokumenKewangan());
        String [] n1 = {"dokumenKewangan"};
        Object [] v1 = {dokumenKewangan};
        senaraiTransaksi = transaksiDAO.findByEqualCriterias(n1, v1, null);
            }

    public void caraBayar(String resit){
        dokumenKewangan = dokumenKewanganDAO.findById(resit);
        String [] n1 = {"dokumenKewangan"};
        Object [] v1 = {dokumenKewangan};
        dkbList = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);
    }

    public String akaunKutipanHarian(Pengguna pguna){
        KodAkaun kodAkaun = kodAkaunDAO.findById("AKH");
        String noAkaun = "";
        String [] n1 = {"kodAkaun"};
        Object [] v1 = {kodAkaun};
        List<Akaun> akList = new ArrayList<Akaun>();
        akList = akaunDAO.findByEqualCriterias(n1, v1, null);
        for (Akaun acc : akList) {
            if(acc.getCawangan().getKod().equals(pguna.getKodCawangan().getKod())){
                noAkaun = acc.getNoAkaun();
            }
        }
        Akaun ak = akaunDAO.findById(noAkaun);
        ak.setBaki(ak.getBaki().add(temp));
        manager.saveOrUpdate(ak);
        return noAkaun;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getAgensi() {
        return agensi;
    }

    public void setAgensi(String agensi) {
        this.agensi = agensi;
    }

    public String getTarikhDari() {
        return tarikhDari;
    }

    public void setTarikhDari(String tarikhDari) {
        this.tarikhDari = tarikhDari;
    }

    public String getTarikhHingga() {
        return tarikhHingga;
    }

    public void setTarikhHingga(String tarikhHingga) {
        this.tarikhHingga = tarikhHingga;
    }

    public KodPBT getPbt() {
        return pbt;
    }

    public void setPbt(KodPBT pbt) {
        this.pbt = pbt;
    }

    public List<KodPBT> getSenaraiKodPBT() {
        return senaraiKodPBT;
    }

    public void setSenaraiKodPBT(List<KodPBT> senaraiKodPBT) {
        this.senaraiKodPBT = senaraiKodPBT;
    }

    public BigDecimal getCukaiTanah() {
        return cukaiTanah;
    }

    public void setCukaiTanah(BigDecimal cukaiTanah) {
        this.cukaiTanah = cukaiTanah;
    }

    public BigDecimal getDendaLewat() {
        return dendaLewat;
    }

    public void setDendaLewat(BigDecimal dendaLewat) {
        this.dendaLewat = dendaLewat;
    }

    public BigDecimal getTunggakanCukaiTanah() {
        return tunggakanCukaiTanah;
    }

    public void setTunggakanCukaiTanah(BigDecimal tunggakanCukaiTanah) {
        this.tunggakanCukaiTanah = tunggakanCukaiTanah;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
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

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public List<DokumenKewanganBayaran> getDkbList() {
        return dkbList;
    }

    public void setDkbList(List<DokumenKewanganBayaran> dkbList) {
        this.dkbList = dkbList;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }
}
