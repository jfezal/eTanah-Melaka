/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodAkaunDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.CaraBayaran;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodTransaksi;
import etanah.model.Pengguna;
import etanah.model.Transaksi;
import etanah.sequence.GeneratorNoResit;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.*;

/**
 *
 * @author abdul.hakim
 */
@Wizard(startEvents = {"showForm", "doCheckHakmilik"})
@UrlBinding("/hasil/pindaan_akaun_amanah")
public class PindaanAkaunAmanahActionBean extends AbleActionBean {

    private Akaun akaun;
    private Transaksi transaksi;
    private DokumenKewangan dokumenKewangan;
    private Hakmilik hakmilik;

    private AkaunDAO akaunDAO;
    private TransaksiDAO transaksiDAO;
    private HakmilikDAO hakmilikDAO;
    private KodAkaunDAO kodAkaunDAO;
    private DokumenKewanganDAO dokumenKewanganDAO;
    private CaraBayaranDAO caraBayaranDAO;
    private KodDokumenDAO kodDokumenDAO;
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    private KodCaraBayaranDAO kodCaraBayaranDAO;

    private List<Akaun> accList;
    private List<Transaksi> transList;
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();

    private String accNo = "";
    private String noAkaun;
    private String accBaru;
    private String idKewDok = "";
    private BigDecimal total = new BigDecimal(0);
    private static String kodNegeri;
    private double byran = 0.00;
    private boolean flag = false;
    private boolean visible = false;
    private boolean btn = true;
    private int rbt;
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");

    @Inject
    public PindaanAkaunAmanahActionBean(AkaunDAO akaunDAO, TransaksiDAO transaksiDAO,
                                    HakmilikDAO hakmilikDAO, KodAkaunDAO kodAkaunDAO,
                                    DokumenKewanganDAO dokumenKewanganDAO, CaraBayaranDAO caraBayaranDAO,
                                    KodDokumenDAO kodDokumenDAO,KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO,
                                    KodCaraBayaranDAO kodCaraBayaranDAO) {
        this.akaunDAO = akaunDAO;
        this.transaksiDAO = transaksiDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.kodAkaunDAO = kodAkaunDAO;
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.caraBayaranDAO = caraBayaranDAO;
        this.kodDokumenDAO = kodDokumenDAO;
        this.kodStatusTransaksiKewanganDAO = kodStatusTransaksiKewanganDAO;
        this.kodCaraBayaranDAO = kodCaraBayaranDAO;
    }

    @Inject
    KutipanHasilManager manager;

    @Inject
    KodTransaksiDAO kodTransaksiDAO;

    @Inject
    private etanah.Configuration conf;

    @Inject
    private etanah.kodHasilConfig hasil;

    @Inject
    private PindaanService pindaanService;

    @Inject
    GeneratorNoResit noResitGenerator;

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public List<Akaun> getAccList() {
        return accList;
    }

    public void setAccList(List<Akaun> accList) {
        this.accList = accList;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccBaru() {
        return accBaru;
    }

    public void setAccBaru(String accBaru) {
        this.accBaru = accBaru;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public double getByran() {
        return byran;
    }

    public void setByran(double byran) {
        this.byran = byran;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public int getRbt() {
        return rbt;
    }

    public void setRbt(int rbt) {
        this.rbt = rbt;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

    @DefaultHandler
    public Resolution showForm() {
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_akaun_amanah.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        accList = new ArrayList<Akaun>();
        List<Akaun> list1 = pindaanService.getPindaanAkaun();

        for (Akaun ak : list1) {
            double bal = ak.getBaki().doubleValue()*-1;
            ak.setBaki(new BigDecimal(bal));
            accList.add(ak);
        }
        if (accList.size() > 0) {
            setBtn(false);
        }
    }

    public Resolution search() {
        setVisible(true);
        if (hakmilik != null) {
            searchByIDHakmilik();
        } else if(akaun != null){
            searchByNoAkaun();
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_akaun_amanah.jsp");
    }

    @ValidationMethod(on = "search")
    public void validateField(ValidationErrors errors) {
        if ((hakmilik == null)&&(akaun == null)) {
            errors.add(" ", new SimpleError("Sila Masukkan Nombor Akaun Amanah atau Nombor Hakmilik."));
        }
    }

    public void searchByIDHakmilik() {
        accList = new ArrayList<Akaun>();
        List<Akaun> akList = new ArrayList<Akaun>();
        String [] n1 = {"hakmilik"};
        Object [] v1 = {getHakmilik()};
        akList = akaunDAO.findByEqualCriterias(n1, v1, null);
        for(Akaun ak : akList){
            if(ak.getKodAkaun().getKod().equals(hasil.getProperty("ansuranCukaiTanah"))){
                double bal = ak.getBaki().doubleValue() * -1;
                ak.setBaki(new BigDecimal(bal));
                accList.add(ak);
            }
        }
        if(accList.size()>0)
            setBtn(false);
    }

    public void searchByNoAkaun() {
        accList = new ArrayList<Akaun>();
        List<Akaun> list = manager.searchAkaun(akaun.getNoAkaun());
        for (Akaun a : list) {
            if (a.getKodAkaun().getKod().equals(hasil.getProperty("ansuranCukaiTanah"))) {
                double bal = a.getBaki().doubleValue() * -1;
                a.setBaki(new BigDecimal(bal));
                accList.add(a);
            }
        }
        if(accList.size()>0)
            setBtn(false);
    }

    public Resolution doCheckHakmilik() {
        String results = "";
        akaun = akaunDAO.findById(accBaru);
        if (akaun != null) {
            results = "0";
        } else {
            results = "1";
        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution kembali() {
        akaun = new Akaun();
        hakmilik = new Hakmilik();
        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_akaun_amanah.jsp");
    }

    public Resolution details() {
        
        akaun = akaunDAO.findById(noAkaun);
        hakmilik = akaun.getHakmilik();
        senaraiTransaksi = akaun.getSemuaTransaksi();
        for(Transaksi tr : senaraiTransaksi){
            if((tr.getStatus().getKod()!='B')||(tr.getStatus().getKod()!='P'))
                total = total.add(tr.getAmaun());
        }

        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_akaun_amanah_1.jsp");
    }

    @ValidationMethod(on = "details")
    public void validateRadio(ValidationErrors errors) {
        if (noAkaun == null) {
            errors.add("radioButton", new SimpleError("Sila Pilih Salah Satu. Tekan Butang Carian untuk Carian Semula."));
        }
        search();
    }
    
    public Resolution save(){
        Akaun ak = akaunDAO.findById(noAkaun);      //akaun Amanah
        Akaun acc = akaunDAO.findById(accBaru);     //akaun Cukai
        String kutHarian = "";

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser(); //penggunaDAO.findById("admin");

        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        int year = Integer.parseInt(yy.format(now));


        double bayaran = ak.getBaki().doubleValue();
        double amaun = acc.getBaki().doubleValue();
        double baki = bayaran + amaun;      //update baki for akaun amanah
        double total = amaun + bayaran;

        //akaun amanah
        ak.setBaki(new BigDecimal(baki));
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(now);
        ak.setInfoAudit(ia);
        manager.saveOrUpdate(ak);

        //akaun cukai
        acc.setBaki(new BigDecimal(baki));
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(now);
        acc.setInfoAudit(ia);
        manager.saveOrUpdate(acc);

        manager.saveOrUpdate(ak);

        List<Transaksi> tList = acc.getSemuaTransaksi();

         String date = (new SimpleDateFormat("ddMMyyyyhhmm")).format(new java.util.Date());
        // resit
        dokumenKewangan = new DokumenKewangan();
        String resit = noResitGenerator.generateResitJurnal(ctx.getKodNegeri(), caw , pengguna);
        dokumenKewangan.setIdDokumenKewangan(resit);
        dokumenKewangan.setAmaunBayaran(new BigDecimal(amaun));
        dokumenKewangan.setKodDokumen(kodDokumenDAO.findById("JNL"));
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
        dokumenKewangan.setInfoAudit(ia);
        dokumenKewangan.setCawangan(caw);
        idKewDok = dokumenKewangan.getIdDokumenKewangan();
        manager.saveOrUpdate(dokumenKewangan);

        CaraBayaran cara = new CaraBayaran();
        cara.setCawangan(caw);
        cara.setAmaun(new BigDecimal(amaun));
        cara.setKodCaraBayaran(kodCaraBayaranDAO.findById("J"));
        cara.setInfoAudit(ia);
        manager.saveOrUpdate(cara);

        DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
        dkb.setAmaun(new BigDecimal(amaun));
        dkb.setCaraBayaran(cara);
        dkb.setDokumenKewangan(dokumenKewangan);
        dkb.setInfoAudit(ia);
        dkb.setAktif('Y');
        manager.saveOrUpdate(dkb);
        DokumenKewangan dk = dokumenKewanganDAO.findById(idKewDok);
        for (Transaksi tr : tList) {
            Transaksi t = new Transaksi();
//            KodTransaksi kod = kodTransaksiDAO.findById(hasil.getProperty("cukaiTanahSemasa"));
            if(tr.getAmaun().compareTo(new BigDecimal (0) ) == 1){
                t.setCawangan(tr.getCawangan());
                t.setKodTransaksi(tr.getKodTransaksi());
                t.setAmaun(tr.getAmaun());
                t.setDokumenKewangan(dk);
                t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(now);
                t.setInfoAudit(ia);
                t.setUntukTahun(tr.getUntukTahun());
                t.setAkaunDebit(ak);
                t.setAkaunKredit(acc);
                t.setTahunKewangan(year);
                manager.save(t);
            }
        }
        display(ak);

        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_akaun_amanah_2.jsp");
    }

    public void display(Akaun ak){
        transList = new ArrayList<Transaksi>();
        hakmilik = hakmilikDAO.findById(accBaru);
        transList = ak.getSenaraiTransaksiDebit();
    }
}
