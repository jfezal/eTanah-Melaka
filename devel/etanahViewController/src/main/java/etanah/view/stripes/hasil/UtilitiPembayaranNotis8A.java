package etanah.view.stripes.hasil;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import etanah.dao.*;
import java.util.Date;
import etanah.model.*;
import java.util.ArrayList;
import org.hibernate.Query;
import java.math.BigDecimal;
import org.hibernate.Session;
import com.google.inject.Inject;
import org.hibernate.LockMode;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import etanah.sequence.GeneratorNoResit2;
import net.sourceforge.stripes.action.Wizard;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Transaction;

/**
 *  @author abdul.hakim
 *  22-March-2011
 */
@Wizard(startEvents = {"showForm"})
@UrlBinding("/hasil/bayaran_8A")
public class UtilitiPembayaranNotis8A extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(UtilitiPembayaranNotis8A.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/utiliti_bayar_8A.jsp";
    private static final String PAYMENT_VIEW = "/WEB-INF/jsp/hasil/utiliti_bayar_8A_1.jsp";
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    
    private Akaun akaun;
    private Hakmilik hakmilik;
    
    private String kodNegeri;
    private String noAkaun;
    private String idHakmilik;
    private int bil = 0;
    private BigDecimal jumCaraBayar = new BigDecimal(0.00);

    private KodKutipanDAO kodKutipanDAO;
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    private KodDokumenDAO kodDokumenDAO;
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    private KodUrusanDAO kodUrusanDAO;
    private KodTransaksiDAO kodTransaksiDAO;

    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<String> tarikhCek = new ArrayList<String>();

    @Inject
    public UtilitiPembayaranNotis8A(KodKutipanDAO kodKutipanDAO, HakmilikDAO hakmilikDAO, KodDokumenDAO kodDokumenDAO, KodUrusanDAO kodUrusanDAO,
                                                          KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO, KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO,
                                                          KodTransaksiDAO kodTransaksiDAO) {
        this.kodStatusTransaksiKewanganDAO = kodStatusTransaksiKewanganDAO;
        this.kodKutipanDAO = kodKutipanDAO;
        this.kodDokumenDAO = kodDokumenDAO;
        this.kodStatusDokumenKewanganDAO = kodStatusDokumenKewanganDAO;
        this.kodUrusanDAO = kodUrusanDAO;
        this.kodTransaksiDAO = kodTransaksiDAO;
    }

    @Inject
    private etanah.Configuration conf;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    GeneratorNoResit2 noResitGenerator2;

    @Inject
    CaraBayarService caraBayarService;

    @Inject
    KutipanHasilManager manager;

    @Inject
    PenyataPemungutService pp;

    @Inject
    GenerateIdPermohonanWorkflow gipw;

    @Inject
    private etanah.kodHasilConfig hasil;

    @Inject
    NotisPeringatan6AManager notis;

    @DefaultHandler
    public Resolution showForm() {
        logger.info("showForm");

        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }else{
            kodNegeri = "negeriSembilan";
        }
        logger.info("kodNegeri : "+kodNegeri);
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution carian(){
        logger.info("carian");
        akaun = searching(getContext().getRequest().getParameterMap());

        if(akaun != null){
            logger.info("akaun.getNoAkaun() : "+akaun.getNoAkaun());

            senaraiTransaksi = akaun.getSemuaTransaksi();
            collectPayment();
        }
        return new ForwardResolution(PAYMENT_VIEW);
    }
    
    public Resolution collectPayment() {
        bil += 5;
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
        } else {
            for (int i = 0; i < bil; i++) {
                CaraBayaran cr = new CaraBayaran();
                senaraiCaraBayaran.add(cr);
            }
            bil = senaraiCaraBayaran.size();
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

    public Akaun searching(Map<String, String[]> param) {
        String query = "SELECT a FROM etanah.model.Akaun a WHERE a.kodAkaun.kod = 'AC' AND a. status.kod = 'B' ";

        if (isNotBlank(param.get("noAkaun"))) {
            query += "AND a.noAkaun = :noAkaun ";
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            query += "AND a.hakmilik.idHakmilik = :idHakmilik ";
        }

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("noAkaun"))) {
            q.setString("noAkaun", param.get("noAkaun")[0]);
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            q.setString("idHakmilik", param.get("idHakmilik")[0]);
        }

        return (Akaun) q.uniqueResult();
    }

    public static boolean isNotBlank(String[] str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String[] str) {
        if (str == null) {
            return true;
        }
        if (str.length > 0) {
            return isBlank(str[0]);
        }
        return true;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public Resolution back(){
        return new RedirectResolution(UtilitiPembayaranNotis8A.class);
    }

    public Resolution save() throws ParseException{
        logger.info("----------save--------"+akaun.getNoAkaun());

        DokumenKewangan dk = null;
        Session s = sessionProvider.get();

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        logger.info("pengguna.getNama() : "+pengguna.getNama());
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        BigDecimal rm = jumCaraBayar;
        Akaun akn = new Akaun();
        String ac = "";
        String noAkaun = "";
        String resit = null;
        String mod = "biasa";

        Transaction tx = s.beginTransaction();
        try{
            List<Transaksi> senaraiTransaksiDebit = new ArrayList<Transaksi>();
            // sorting transaksi debit based on priority
            String query = "SELECT t FROM etanah.model.Transaksi t where t.akaunDebit.noAkaun = :akaun" +
                    " AND t.status.kod = 'T' order by t.kodTransaksi.keutamaan";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("akaun", akaun.getNoAkaun());
            senaraiTransaksiDebit = q.list();

    //        resit = noResitGenerator.generateNoResit(ctx.getKodNegeri(), caw , pengguna);
            resit = noResitGenerator2.getAndLockSerialNo(pengguna);
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
            boolean bakiFlag = false;

            ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
            adkb = caraBayarService.saveCaraBayaran(senaraiCaraBayaran, tarikhCek, ia, pengguna, noAkaun, bakiFlag, bg, dk, adkb,jumCaraBayar);
            logger.info(adkb.size());

            String tmp = dk.getIdDokumenKewangan();
            dk.setIsuKepada(akaun.getPemegang().getNama());
            dk.setSenaraiBayaran(adkb);
            dk.setAkaun(akaun);
            dk.setTarikhTransaksi(now);
            if(mod.equalsIgnoreCase("Lewat"))
                dk.setMod(kodKutipanDAO.findById('L'));
            if(mod.equalsIgnoreCase("Biasa"))
                dk.setMod(kodKutipanDAO.findById('B'));
            if(bakiFlag == false){
                dk.setAmaunBayaran(jumCaraBayar);
            }
            manager.saveOrUpdate(dk);

            // find AKH
            String query1 = "SELECT a FROM etanah.model.Akaun a where a.kodAkaun.kod = 'AKH' AND a.cawangan.kod = :cawangan ";
            Query q1 = sessionProvider.get().createQuery(query1);
            q1.setString("cawangan", pengguna.getKodCawangan().getKod());
            Akaun akaunAKH = (Akaun) q1.uniqueResult();
                s.lock(akaunAKH, LockMode.UPGRADE);
                akaunAKH.setBaki(akaunAKH.getBaki().add(jumCaraBayar));
                manager.saveOrUpdate(akaunAKH);
            logger.info("akaunAKH.getNoAkaun() : "+akaunAKH.getNoAkaun());

            for (Transaksi t : senaraiTransaksiDebit) {
                Transaksi tr  = new Transaksi();

                logger.info("kodHasil : "+t.getKodTransaksi().getKod());
                tr.setKodTransaksi(t.getKodTransaksi());
                tr.setCawangan(caw);
                tr.setDokumenKewangan(dk);
                tr.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                tr.setUntukTahun(t.getUntukTahun());
                tr.setTahunKewangan(Integer.parseInt(yy.format(now)));
                tr.setAmaun(t.getAmaun());
                tr.setAkaunKredit(t.getAkaunDebit());
                tr.setAkaunDebit(akaunAKH);
                tr.setInfoAudit(ia);

                manager.save(tr);
            }

            s.lock(akaun, LockMode.UPGRADE);
                akaun.setBaki(akaun.getBaki().subtract(jumCaraBayar));
            manager.saveOrUpdate(akaun);

            Transaksi tr  = new Transaksi();

            tr.setKodTransaksi(kodTransaksiDAO.findById(hasil.getProperty("cukaiTanahSemasa")));
            tr.setCawangan(caw);
            tr.setDokumenKewangan(dk);
            tr.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
            tr.setUntukTahun(Integer.parseInt(yy.format(now)));
            tr.setTahunKewangan(Integer.parseInt(yy.format(now)));
            tr.setAmaun(akaun.getBaki().multiply(new BigDecimal(-1)));
            tr.setAkaunKredit(akaun);
            tr.setAkaunDebit(akaunAKH);
            tr.setInfoAudit(ia);

            manager.save(tr);

            pp.savePenyataPemungut(dk);
            SediaEndorsan(pengguna, akaun.getHakmilik(), ac);
            addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        }catch (Exception ex) {
            noResitGenerator2.rollbackAndUnlockSerialNo(pengguna);
            logger.error(ex.getMessage(), ex);
            tx.rollback();
            addSimpleError("Pembayaran tidak berjaya.");
        }finally{noResitGenerator2.commitAndUnlockSerialNo(pengguna);}

        return new ForwardResolution(PAYMENT_VIEW);
    }

    public void SediaEndorsan(Pengguna p,Hakmilik h, String ks) {
        String semuaTerima = null;
        String tidakTerima = null;
        KodUrusan ku = new KodUrusan();
        try {
            logger.info("hakmilik.getIdHakmilik() : "+h.getIdHakmilik());
            
        String query1 = "SELECT dch FROM etanah.model.DasarTuntutanCukaiHakmilik dch where dch.hakmilik.idHakmilik = :idHakmilik AND dch.status.kod = 'ST' ";
        Query q1 = sessionProvider.get().createQuery(query1);
        q1.setString("hakmilik", hakmilik.getIdHakmilik());
        DasarTuntutanCukaiHakmilik dch = (DasarTuntutanCukaiHakmilik) q1.uniqueResult();


            List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
            String[] name = {"hakmilik"};
            Object[] value = {h};
//            senaraiDasarTuntutanCukaiHakmilik = dasarHakmilikDAO.findByEqualCriterias(name, value, null);
            if("N8A".equals(ks)){
                logger.info("Endorsan untuk notis 8A.");
                ku = kodUrusanDAO.findById("ED8A");
//                for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarTuntutanCukaiHakmilik) {
//                    if(!dtch.getHakmilik().getAkaunCukai().getBaki().equals(BigDecimal.ZERO)){ // check if hakmilik have 'baki' or not
//                        for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
//                            if("N8A".equals(dtn.getNotis().getKod()) && "TM".equals(dtn.getStatusTerima().getKod())){ //check utk Notis 8A yg dapat disampaikan sahaja
//                                semuaTerima = "ya";
//                                logger.debug("idNotis terima:"+dtn.getIdNotis());
//                            }else if("N8A".equals(dtn.getNotis().getKod()) && "XT".equals(dtn.getStatusTerima().getKod())){
//                                tidakTerima = "ya";
//                                logger.debug("idNotis tidak terima:"+dtn.getIdNotis());
//                            }
//                        }
//                    }
//                }
                if(tidakTerima == null){
                    logger.debug("Semua idNotis dapat disampaikan. Generate permohonan diteruskan");
                    senaraiHakmilik.add(h);
                    if(gipw.generateIdPermohonanWorkflowInternal(ku, p, senaraiHakmilik, ""))
                        addSimpleMessage("Permohonan telah berjaya diwujudkan.");
                    else{
                        addSimpleError("Permohonan telah gagal diwujudkan");
                    }
                }else{
                    logger.error("Semua idNotis setengah dapat disampaikan. Generate permohonan tidak diteruskan");
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
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

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
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
}
