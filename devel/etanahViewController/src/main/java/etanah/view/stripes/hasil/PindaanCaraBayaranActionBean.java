package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.*;

/**
 *
 * @author abdul.hakim
 */
@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/pindaan_cara_bayaran")
public class PindaanCaraBayaranActionBean extends AbleActionBean{

    private Hakmilik hakmilik;
    private Akaun akaun;
    private CaraBayaran caraBayaran;
    private DokumenKewangan dokumenKewangan;
    private Transaksi transaksi;
    private DokumenKewanganBayaran dokumenKewanganBayaran;

    private AkaunDAO akaunDAO;
    private CaraBayaranDAO caraBayaranDAO;
    private DokumenKewanganDAO dokumenKewanganDAO;
    private TransaksiDAO transaksiDAO;
    private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    private KodBankDAO kodBankDAO;

    private boolean visible = false;
    private boolean flag = false;
    private String able;
    private String noResit;
    private String kewDokumenBayar;
    private String idCaraBayar;
    private String kodCaraBayar;
    private String kodBank;
    private String caw;
    private String noRujukan;
    private String tarikhCek;
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private List<Transaksi> senaraiTrans = new ArrayList<Transaksi>();
    private List<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<DokumenKewanganBayaran> senaraiBayaran = new ArrayList<DokumenKewanganBayaran>();

    @Inject
    public PindaanCaraBayaranActionBean(AkaunDAO akaunDAO, CaraBayaranDAO caraBayaranDAO,
                                        DokumenKewanganDAO dokumenKewanganDAO, TransaksiDAO transaksiDAO, KodBankDAO kodBankDAO,
                                        DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO, KodCaraBayaranDAO kodCaraBayaranDAO) {
        this.akaunDAO = akaunDAO;
        this.caraBayaranDAO = caraBayaranDAO;
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.transaksiDAO  = transaksiDAO;
        this.dokumenKewanganBayaranDAO = dokumenKewanganBayaranDAO;
        this.kodCaraBayaranDAO = kodCaraBayaranDAO;
        this.kodBankDAO = kodBankDAO;
    }

    @Inject
    KutipanHasilManager manager;

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public CaraBayaran getCaraBayaran() {
        return caraBayaran;
    }

    public void setCaraBayaran(CaraBayaran caraBayaran) {
        this.caraBayaran = caraBayaran;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
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

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public String getKewDokumenBayar() {
        return kewDokumenBayar;
    }

    public void setKewDokumenBayar(String kewDokumenBayar) {
        this.kewDokumenBayar = kewDokumenBayar;
    }

    public List<Transaksi> getSenaraiTrans() {
        return senaraiTrans;
    }

    public void setSenaraiTrans(List<Transaksi> senaraiTrans) {
        this.senaraiTrans = senaraiTrans;
    }

    public List<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(List<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public DokumenKewanganBayaran getDokumenKewanganBayaran() {
        return dokumenKewanganBayaran;
    }

    public void setDokumenKewanganBayaran(DokumenKewanganBayaran dokumenKewanganBayaran) {
        this.dokumenKewanganBayaran = dokumenKewanganBayaran;
    }

    public List<DokumenKewanganBayaran> getSenaraiBayaran() {
        return senaraiBayaran;
    }

    public void setSenaraiBayaran(List<DokumenKewanganBayaran> senaraiBayaran) {
        this.senaraiBayaran = senaraiBayaran;
    }

    public String getIdCaraBayar() {
        return idCaraBayar;
    }

    public void setIdCaraBayar(String idCaraBayar) {
        this.idCaraBayar = idCaraBayar;
    }

    public String getKodCaraBayar() {
        return kodCaraBayar;
    }

    public void setKodCaraBayar(String kodCaraBayar) {
        this.kodCaraBayar = kodCaraBayar;
    }

    public String getCaw() {
        return caw;
    }

    public void setCaw(String caw) {
        this.caw = caw;
    }

    public String getKodBank() {
        return kodBank;
    }

    public void setKodBank(String kodBank) {
        this.kodBank = kodBank;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getTarikhCek() {
        return tarikhCek;
    }

    public void setTarikhCek(String tarikhCek) {
        this.tarikhCek = tarikhCek;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getAble() {
        return able;
    }

    public void setAble(String able) {
        this.able = able;
    }

    @HandlesEvent ("Step1")
    @DefaultHandler
    public Resolution showForm(){
        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_cara_bayaran.jsp");
    }

    @HandlesEvent("Step2")
    public Resolution search(){
        List<Transaksi> transList = new ArrayList<Transaksi>();
        String dokKewangan = "";
        if(hakmilik != null){
            String [] n1 = {"hakmilik"};
            Object [] v1 = {getHakmilik()};
            List<Akaun> akaunList = akaunDAO.findByEqualCriterias(n1, v1, null);

            for(Akaun ak : akaunList){
                if(ak.getKodAkaun().getKod().equals("AC")){
                    akaun = ak;
                }
            }
            List<Transaksi> tList = akaun.getSemuaTransaksi();

            for(Transaksi t : tList){
                if((t.getDokumenKewangan()!=null)&&(t.getStatus().getKod()!='B')){
                    transList.add(t);
                }
            }
        }
        if(dokumenKewangan != null){
            String [] n1 = {"dokumenKewangan"};
            Object [] v1 = {getDokumenKewangan()};
            transList = transaksiDAO.findByEqualCriterias(n1, v1, null);
//            List<DokumenKewangan> dokKewList = manager.searchNoResit(dokumenKewangan.getIdDokumenKewangan());
//            for(DokumenKewangan dk : dokKewList){
//                String [] n1 = {"dokumenKewangan"};
//                Object [] v1 = {dk};
//                List<Transaksi>tList = transaksiDAO.findByEqualCriterias(n1, v1, null);
//                transList.addAll(tList);
//            }
        }

        for(int m=0; m<transList.size(); m++){
            Transaksi tr = transList.get(m);
            if(transList.size()>1){
                for (int n=m+1; n<transList.size(); n++) {
                    Transaksi t = transList.get(n);
                    if (!dokKewangan.equals(t.getDokumenKewangan().getIdDokumenKewangan())) {
                        senaraiTransaksi.add(t);
                        dokKewangan = tr.getDokumenKewangan().getIdDokumenKewangan();
                    }
                }
            }else{
                senaraiTransaksi.add(tr);
            }
        }
        visible = true;
        return new ForwardResolution("/WEB-INF/jsp/hasil/pindaan_cara_bayaran.jsp");
    }

    @ValidationMethod(on = "Step2")
    public void validateField(ValidationErrors errors) {
        if ((dokumenKewangan == null)&&(hakmilik == null)) {
            errors.add(" ", new SimpleError("Sila Masukkan Nombor Resit atau ID Hakmilik."));
        }
    }

    @HandlesEvent("Step3")
    public Resolution details(){
        String noAkaun = "";
        dokumenKewangan = dokumenKewanganDAO.findById(noResit);

        String [] n1 = {"dokumenKewangan"};
        Object [] v1 = {getDokumenKewangan()};
        List<Transaksi> listT = transaksiDAO.findByEqualCriterias(n1, v1, null);

        for (Transaksi tr : listT) {
            if((tr.getAkaunDebit()!=null)&&(tr.getAkaunKredit()!=null)){
                noAkaun = tr.getAkaunKredit().getNoAkaun();
            }
        }
        akaun = akaunDAO.findById(noAkaun);
        List<Transaksi> trnList = akaun.getSemuaTransaksi();
        for(Transaksi tr : trnList){
            if((tr.getAkaunKredit()!=null)&&(tr.getAkaunKredit().getNoAkaun().equals(akaun.getNoAkaun()))){
                tr.setAmaun(tr.getAmaun().multiply(new BigDecimal(-1)));
            }
            senaraiTrans.add(tr);
        }

        String [] n2 = {"dokumenKewangan"};
        Object [] v2 = {dokumenKewangan};
        senaraiBayaran = dokumenKewanganBayaranDAO.findByEqualCriterias(n2, v2, null);
        
        return new ForwardResolution ("/WEB-INF/jsp/hasil/pindaan_cara_bayaran_1.jsp");
    }

    @HandlesEvent("Step4")
    public Resolution caraBayar(){
        Long id = Long.parseLong(idCaraBayar);

        caraBayaran = caraBayaranDAO.findById(id);
        return new ForwardResolution ("/WEB-INF/jsp/hasil/pindaan_cara_bayaran_2.jsp");
    }

    @ValidationMethod(on = "Step4")
    public void validateStatus(ValidationErrors errors) {
        details();
        Long id = Long.parseLong(idCaraBayar);

        caraBayaran = caraBayaranDAO.findById(id);
        if (caraBayaran.getAktif() == 'X') {
            errors.add(" ", new SimpleError("Pilihan telah dibatalkan. Sila pilih semula."));
        }
    }

    @HandlesEvent("Step5")
    public Resolution save(){
        caraBayar();
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan cawangan = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        //update caraBayaran to 'Batal'
        Long id = Long.parseLong(idCaraBayar);
        CaraBayaran cb = caraBayaranDAO.findById(id);

        cb.setAktif('X');
        cb.setDibatalOleh(pengguna);
        cb.setTarikhBatal(now);
        manager.updateCaraBayaran(cb);

        CaraBayaran caraBayar = new CaraBayaran();

        caraBayar.setCawangan(cawangan);
        caraBayar.setKodCaraBayaran(kodCaraBayaranDAO.findById(kodCaraBayar));
        if(kodCaraBayar.equals("T")){
            caraBayar.setNoRujukan(null);
            caraBayar.setBank(null);
            caraBayar.setBankCawangan(null);
        }else{
            caraBayar.setNoRujukan(noRujukan);
            caraBayar.setBank(kodBankDAO.findById(kodBank));
            caraBayar.setBankCawangan(caw);
//            if((kodCaraBayar.equals("CB"))||(kodCaraBayar.equals("CL"))||(kodCaraBayar.equals("CT"))){
//                caraBayar.setTarikhCek();
//            }
        }
        caraBayar.setAmaun(cb.getAmaun());
        caraBayar.setAktif('Y');
        caraBayar.setInfoAudit(ia);
        manager.saveOrUpdate(caraBayar);

        //update dokumenKewanganBayaran to 'Batal'
        String [] n1 = {"caraBayaran"};
        Object [] v1 = {cb};
        List<DokumenKewanganBayaran> dkbList = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);
        for(DokumenKewanganBayaran dkb : dkbList){
            if(dkb.getDokumenKewangan().getIdDokumenKewangan().equals(noResit)){
                dkb.setAktif('X');
                dkb.setDibatalOleh(pengguna);
                dkb.setTarikhBatal(now);

                manager.updateDokumenKewanganBayaran(dkb);

                DokumenKewanganBayaran dkBayaran = new DokumenKewanganBayaran();
                CaraBayaran cb1 = caraBayaranDAO.findById(caraBayar.getIdCaraBayaran());

                dkBayaran.setDokumenKewangan(dkb.getDokumenKewangan());
                dkBayaran.setCaraBayaran(cb1);
                dkBayaran.setAmaun(dkb.getAmaun());
                dkBayaran.setInfoAudit(ia);
                dkBayaran.setAktif('Y');

                manager.saveOrUpdate(dkBayaran);
            }
        }
        able = "disable";
        flag = true;
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");

        return new ForwardResolution ("/WEB-INF/jsp/hasil/pindaan_cara_bayaran_2.jsp");
    }

}
