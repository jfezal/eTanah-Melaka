package etanah.view.stripes.hasil;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import java.text.SimpleDateFormat;
import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.*;
import etanah.view.etanahActionBeanContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

@Wizard(startEvents = {"showForm"})
@UrlBinding("/hasil/pelarasan_cukai")
public class PelarasanCukaiActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PelarasanCukaiActionBean.class);
    SimpleDateFormat sdfWithTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Akaun akaun = new Akaun();
    private KodDaerah daerah;
    private Hakmilik hakmilik;
    private KodJabatan jabatan;
    private Transaksi transaksi;
    private KodTransaksi kodTransaksi;
    private DokumenKewangan dokumenKewangan;
    private KodBandarPekanMukim kodBandarPekanMukim;
    private AkaunDAO accDAO;
    private TransaksiDAO transaksiDAO;
    private KodTransaksiDAO kodTransaksiDAO;
    private List<Akaun> list;
    private List<Transaksi> transList;
    private List<DokumenKewangan> senaraiResit;
    private double jumlahCaj;
    private boolean flag = false;
    private boolean flag1 = true;
    private boolean flagSimpan = false;
    private String idTransaksi;
    private String idResit;
    private String kodTransaksiBaru;
    private String print = "none";
    private String dateFrom = "";
    private String dateTo = "";
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");

    @Inject
    public PelarasanCukaiActionBean(AkaunDAO accDAO, TransaksiDAO transaksiDAO, KodTransaksiDAO kodTransaksiDAO) {
        this.accDAO = accDAO;
        this.transaksiDAO = transaksiDAO;
        this.kodTransaksiDAO = kodTransaksiDAO;
    }
    
    @Inject private KutipanHasilManager manager;
    @Inject private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject private DokumenKewanganDAO dokumenKewanganDAO;

    public List<KodTransaksi> getSenaraiKodTransaksi() {
        return kodTransaksiDAO.findAll();
    }

    public String getKodTransaksiBaru() {
        return kodTransaksiBaru;
    }

    public void setKodTransaksiBaru(String kodTransaksiBaru) {
        this.kodTransaksiBaru = kodTransaksiBaru;
    }

    public boolean isFlag1() {
        return flag1;
    }

    public void setFlag1(boolean flag1) {
        this.flag1 = flag1;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public KodTransaksi getKodTransaksi() {
        return kodTransaksi;
    }

    public void setKodTransaksi(KodTransaksi kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public List<DokumenKewangan> getSenaraiResit() {
        return senaraiResit;
    }

    public void setSenaraiResit(List<DokumenKewangan> senaraiResit) {
        this.senaraiResit = senaraiResit;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public double getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(double jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public KodJabatan getJabatan() {
        return jabatan;
    }

    public void setJabatan(KodJabatan jabatan) {
        this.jabatan = jabatan;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public KodBandarPekanMukim getKodBandarPekanMukimh() {
        return kodBandarPekanMukim;
    }

    public void setKodBandarPekanMukim(KodBandarPekanMukim kodBandarPekanMukim) {
        this.kodBandarPekanMukim = kodBandarPekanMukim;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public List<Akaun> getList() {
        return list;
    }

    public void setList(List<Akaun> list) {
        this.list = list;
    }

    public String getIdResit() {
        return idResit;
    }

    public void setIdResit(String idResit) {
        this.idResit = idResit;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        transList = new ArrayList<Transaksi>();
        senaraiResit = new ArrayList<DokumenKewangan>();
        dokumenKewangan = new DokumenKewangan();
        return new ForwardResolution("/WEB-INF/jsp/hasil/pelarasan_cukai_1.jsp");
    }

    public Resolution search() throws ParseException, SQLException {
        transList = new ArrayList<Transaksi>();
        senaraiResit = new ArrayList<DokumenKewangan>();
        if (dokumenKewangan != null) {
            LOG.info("dokumenKewangan is not null");
            transList = searchByResit();
        } else {
            LOG.info("else");
            senaraiResit = searchByDate();
        }
        LOG.info("senaraiResit.size() : " + senaraiResit.size());
        LOG.info("transList.size() : " + transList.size());

        return new ForwardResolution("/WEB-INF/jsp/hasil/pelarasan_cukai_1.jsp");
    }

    public List<Transaksi> searchByResit() {
        List<Transaksi> listT = new ArrayList<Transaksi>();

        String query = "SELECT tr FROM etanah.model.Transaksi tr WHERE tr.dokumenKewangan.idDokumenKewangan = :resit";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("resit", dokumenKewangan.getIdDokumenKewangan());
        listT = q.list();
        LOG.info("listT.size() : " + listT.size());

        return listT;
    }

    public List<DokumenKewangan> searchByDate() throws ParseException, SQLException {
        Connection c = sessionProvider.get().connection();
        List<DokumenKewangan> listDK = new ArrayList<DokumenKewangan>();
        List<DokumenKewangan> tempLIst = new ArrayList<DokumenKewangan>();
        Date now = sdfWithTime.parse(dateFrom + " 00:00:00");
        Timestamp startDate = new Timestamp(now.getTime());

        now = sdfWithTime.parse(dateTo + " 23:59:59");
        Timestamp endDate = new Timestamp(now.getTime());

        String dr = dateFrom + " 00:00:00";
        String hg = dateTo + " 23:59:59";

        String query = "SELECT kd FROM etanah.model.DokumenKewangan kd WHERE kd.infoAudit.tarikhMasuk BETWEEN TO_DATE( :from, 'DD/MM/YYYY HH24:MI:SS') " +
                "AND TO_DATE( :to, 'DD/MM/YYYY HH24:MI:SS')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("from", dr);
        q.setString("to", hg);
        listDK = q.list();

//        PreparedStatement ps = c.prepareStatement(
//                "SELECT kd.id_kew_dok FROM kew_dokumen kd WHERE kd.trh_masuk between ? and ? ORDER BY kd.id_kew_dok asc");
//        ps.setTimestamp(1, startDate);
//        ps.setTimestamp(2, endDate);
//        ResultSet rs = ps.executeQuery();
//
//        DokumenKewangan dk = new DokumenKewangan();
//
//        while (rs.next()) {
//            dk = dokumenKewanganDAO.findById(rs.getString(1));
//            tempLIst.add(dk);
//        }
//        c.close();
//        listDK.addAll(tempLIst);

        return listDK;
    }

    public Resolution next() throws ParseException, SQLException{
        LOG.info("idResit : "+idResit);
        dokumenKewangan = dokumenKewanganDAO.findById(idResit);
        search();

        return new ForwardResolution("/WEB-INF/jsp/hasil/pelarasan_cukai_1.jsp");
    }

    public Resolution terus() throws ParseException, SQLException {
        LOG.info("idTransaksi : "+idTransaksi);
        long a = Long.parseLong(idTransaksi);

        String query = "SELECT tr FROM etanah.model.Transaksi tr WHERE tr.idTransaksi = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", a);
        transaksi = (Transaksi) q.uniqueResult();
        
//        Pelarasan ini tidak melibatkan Akaun Cukai
//        String queryAkaun = "SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :id";
//        Query qAkaun = sessionProvider.get().createQuery(queryAkaun);
//        qAkaun.setString("id", transaksi.getAkaunKredit().getHakmilik().getIdHakmilik());
//        akaun = (Akaun) qAkaun.uniqueResult();

        return new ForwardResolution("/WEB-INF/jsp/hasil/pelarasan_cukai_2.jsp");
    }

    public Resolution save() throws ParseException, SQLException {
        terus();
        print = null;
        flag1 = false;
        flagSimpan = true;

        balanceAccount();
//        newTransaction();
        Transaksi t = transaksi;
//        t.setKodTransaksi(kodTransaksiDAO.findById(kodTransaksiBaru));
//        t.setStatus(kodStatusTransaksiKewanganDAO.findById('L'));
//        manager.saveOrUpdate(t);

        DokumenKewangan dk = t.getDokumenKewangan();
            dk.setCatatan(dokumenKewangan.getCatatan());
        manager.update(dk);

//        akaun.setBaki(t.getAmaun());
//        manager.saveOrUpdate(akaun);

        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new ForwardResolution("/WEB-INF/jsp/hasil/pelarasan_cukai_2.jsp");
    }

    public void balanceAccount(){
        Transaksi t = transaksi;
        Transaksi trn = new Transaksi();

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));

        Pengguna pengguna = ctx.getUser(); //penggunaDAO.findById("admin");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        trn.setCawangan(t.getCawangan());
        trn.setKodTransaksi(kodTransaksiDAO.findById(kodTransaksiBaru));
        trn.setDokumenKewangan(t.getDokumenKewangan());
        trn.setAmaun(t.getAmaun());
        trn.setStatus(kodStatusTransaksiKewanganDAO.findById('J'));
        trn.setUntukTahun(t.getUntukTahun());
        trn.setTahunKewangan(year);
        trn.setInfoAudit(ia);
        trn.setAkaunDebit(t.getAkaunDebit());
        trn.setAkaunKredit(t.getAkaunDebit());
        trn.setBayaranAgensi("N");
        manager.save(trn);
        transaksi = trn;
    }

    public void newTransaction(){
        Transaksi t = transaksi;
        Transaksi trn = new Transaksi();

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser(); //penggunaDAO.findById("admin");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));

        trn.setCawangan(t.getCawangan());
        trn.setKodTransaksi(kodTransaksiDAO.findById(kodTransaksiBaru));
        trn.setAmaun(t.getAmaun());
        trn.setDokumenKewangan(t.getDokumenKewangan());
        trn.setStatus(kodStatusTransaksiKewanganDAO.findById('P'));
        trn.setInfoAudit(ia);
        trn.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
        trn.setUntukTahun(year);
        trn.setTahunKewangan(year);
        if ((t.getAkaunDebit() != null) && (t.getAkaunKredit() != null)) {
            trn.setAkaunKredit(t.getAkaunKredit());
            trn.setAkaunDebit(t.getAkaunDebit());
        }
        if (t.getAkaunKredit() != null) {
            trn.setAkaunKredit(t.getAkaunKredit());
        }
        if (t.getAkaunDebit() != null) {
            trn.setAkaunDebit(t.getAkaunDebit());
        }
        manager.save(trn);
    }

    public Resolution kembali() {
        hakmilik = new Hakmilik();
        idTransaksi = null;
        return new ForwardResolution("/WEB-INF/jsp/hasil/pelarasan_cukai_1.jsp");
    }

    public boolean isFlagSimpan() {
        return flagSimpan;
    }

    public void setFlagSimpan(boolean flagSimpan) {
        this.flagSimpan = flagSimpan;
    }
}
