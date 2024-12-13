package etanah.view.stripes;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.*;
import etanah.view.etanahActionBeanContext;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.service.KaunterService;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import etanah.report.ReportUtil;
import java.io.FileInputStream;

/**
 *
 * @author abdul.hakim
 */
@Wizard(startEvents = {"displayList", "showEditPemohon", "cetak"})
@UrlBinding("/kutipanHasil")
public class KutipanHasilActionBean extends AbleActionBean {

    private Permohonan permohonan;
    private Akaun akaun;
    private KodUrusan kodUrusan;
    private KodCaraBayaran kodCaraBayaran;
    private KodCawangan kodCawangan;
    private Hakmilik hakmilik;
    private Transaksi transaksi;
    private DokumenKewanganBayaran dokKewBayaran;
    private InfoAudit infoAudit;
    private DokumenKewangan dokumenKewangan;
    private CaraBayaran caraBayaran;
    private Dokumen dokumen;
    private HakmilikDAO hakmilikDAO;
    private AkaunDAO accDAO;
    private CaraBayaranDAO caraBayaranDAO;
    private TransaksiDAO transaksiDAO;
    private DokumenKewanganBayaranDAO dokKewBayaranDAO;
    private PermohonanDAO permohonanDAO;
    private HakmilikPihakBerkepentinganDAO hakmilikPBDao;
    private KodTransaksiDAO kodTransaksiDAO;
    private KodDokumenDAO kodDokumenDAO;
    private DokumenKewanganDAO dokumenKewanganDAO;
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    private KodBankDAO kodBankDAO;
    private DokumenDAO dokumenDAO;
    private String idHakmilik;
    private String account;
    private String idKewDoc;
    private double baki;
    private double jumCaraBayar;
    private double total;
    private double amaun;
    private boolean visible = true;
    private boolean table = false;
    private boolean table1 = false;
    private String flag;
    private List<Akaun> list;
    private ArrayList<Hakmilik> hakmilikList = new ArrayList<Hakmilik>();
    private ArrayList<Akaun> accList = new ArrayList<Akaun>();
    private List<Transaksi> transList;
    private List<DokumenKewanganBayaran> dkbList;
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private double jumlahCaj = 0.0;
    private double jumlahCajPop;
    private double jumlah = 0.0;
    private int rbt;
    private int bilHakmilik = 6;

    @Inject
    public KutipanHasilActionBean(DokumenKewanganDAO dokumenKewanganDAO, KodDokumenDAO kodDokumenDAO, PermohonanDAO permohonanDAO,
            CaraBayaranDAO caraBayaranDAO, AkaunDAO accDAO, HakmilikDAO hakmilikDAO, TransaksiDAO transaksiDAO, DokumenKewanganBayaranDAO dokKewBayaranDAO,
            KodCaraBayaranDAO kodCaraBayaranDAO, KodBankDAO kodBankDAO, DokumenDAO dokumenDAO) {
        //this.tabManager = tabManager;
        this.permohonanDAO = permohonanDAO;
        this.accDAO = accDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.transaksiDAO = transaksiDAO;
        this.caraBayaranDAO = caraBayaranDAO;
        this.kodDokumenDAO = kodDokumenDAO;
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.dokKewBayaranDAO = dokKewBayaranDAO;
        this.kodCaraBayaranDAO = kodCaraBayaranDAO;
        this.kodBankDAO = kodBankDAO;
        this.dokumenDAO = dokumenDAO;
    }
    @Inject
    KutipanHasilManager manager;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private KaunterService kaunterService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private ReportUtil reportUtil;

    public String getIdKewDoc() {
        return idKewDoc;
    }

    public void setIdKewDoc(String idKewDoc) {
        this.idKewDoc = idKewDoc;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getRbt() {
        return rbt;
    }

    public void setRbt(int rbt) {
        this.rbt = rbt;
    }

    public List<KodUrusan> getPilihanKodUrusan() {
        return kaunterService.findAllUrusanByJabatan();
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bil) {
        this.bilHakmilik = bil;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public double getBaki() {
        return baki;
    }

    public void setBaki(double baki) {
        this.baki = baki;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public DokumenKewanganBayaran getDokKewBayaran() {
        return dokKewBayaran;
    }

    public void setDokKewBayarank(DokumenKewanganBayaran dokKewBayaran) {
        this.dokKewBayaran = dokKewBayaran;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public double getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(double jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

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

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public KodCaraBayaran getKodCaraBayaran() {
        return kodCaraBayaran;
    }

    public void setKodCaraBayaran(KodCaraBayaran kodCaraBayaran) {
        this.kodCaraBayaran = kodCaraBayaran;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
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

    public ArrayList<Akaun> getAccList() {
        return accList;
    }

    public void setAccList(ArrayList<Akaun> accList) {
        this.accList = accList;
    }

    public List<Transaksi> getTransList() {
        return (List<Transaksi>) transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transaksi = (Transaksi) transList;
    }

    public List<DokumenKewanganBayaran> getDkbList() {
        return (List<DokumenKewanganBayaran>) dkbList;
    }

    public void setDkbList(List<DokumenKewanganBayaran> dkbList) {
        this.dokKewBayaran = (DokumenKewanganBayaran) dkbList;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public double getJumlahCajPop() {
        return jumlahCajPop;
    }

    public void setJumlahCajPop(double jumlahCajPop) {
        this.jumlahCajPop = jumlahCajPop;
    }

    public double getAmaun() {
        return amaun;
    }

    public void setAmaun(double amaun) {
        this.amaun = amaun;
    }

    public boolean isTable() {
        return table;
    }

    public void setTable(boolean table) {
        this.table = table;
    }

    public boolean isTable1() {
        return table1;
    }

    public void setTable1(boolean table1) {
        this.table1 = table1;
    }

    /**************************************************************************************************
     **************************************************************************************************/
    @DefaultHandler
    public Resolution showForm() {
        dkbList = dokKewBayaranDAO.findAll();
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil.jsp");
    }

    public Resolution displayList() {

        if (hakmilik != null) {
            flag = "none";
            String[] name = {"hakmilik"};
            Object[] value = {getHakmilik()};

            list = accDAO.findByEqualCriterias(name, value, null);
            if (list.size() > 0) {
                visible = false;
            }
            for (Akaun ak : list) {
                jumlahCaj += ak.getBaki().doubleValue();
                account = ak.getNoAkaun();
            }
            System.out.println("account : " + account);
            collectPayment();
        }

        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

    @ValidationMethod(on = "displayList")
    public void validateHakmilik(ValidationErrors errors) {
        if (hakmilik == null) {
            errors.add("a", new SimpleError("Sila Masukkan Nombor Hakmilik atau Nombor Akaun"));
        }
    }

    public Resolution collectPayment() {
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
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

    public Resolution bayranPukal() {
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_3.jsp");
    }

    public Resolution updates() {
        for (int i = 0; i < bilHakmilik; i++) {
            Hakmilik hm = new Hakmilik();
            Akaun acc = new Akaun();
            hakmilikList.add(hm);
            accList.add(acc);
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_3.jsp");
    }

    public Resolution details() {
        List<Akaun> acList = new ArrayList<Akaun>();

        list = new ArrayList<Akaun>();
        for (Hakmilik hm : hakmilikList) {
            String temp = hm.getIdHakmilik();

            acList = accDAO.findAll();
            for (int x = 0; x < acList.size(); x++) {
                Akaun acc = acList.get(x);
                if (acc.getHakmilik().getIdHakmilik().equals(temp)) {
                    list.add(acc);
                }
            }
        }
        if (list.size() > 0) {
            visible = false;
        }
        for (int x = 0; x < list.size(); x++) {
            Akaun a = list.get(x);
            jumlahCaj += a.getBaki().doubleValue();
        }
        collectPayment();

        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

    @ValidationMethod(on = "details")
    public void validateList(ValidationErrors errors) {
        if ((hakmilikList.size() == 0) && (accList.size() == 0)) {
            errors.add("list", new SimpleError("** Sila Masukkan Senarai Nombor Akaun atau Nombor Hakmilik yang telibat."));
        }
    }

    public Resolution back() {
        if (hakmilik != null) {
            hakmilik = new Hakmilik();
            return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
        } else {
            hakmilikList = new ArrayList<Hakmilik>();
            accList = new ArrayList<Akaun>();
            return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_3.jsp");
        }
    }

    public Resolution popup() {

        return new JSP("common/pilih_pemohon.jsp").addParameter("tab", "true");

    }

    public Resolution showEditPemohon() {
        List<Akaun> akaunList = new ArrayList<Akaun>();
        List<Akaun> acList = accDAO.findAll();
        for (int x = 0; x < acList.size(); x++) {
            Akaun acc = acList.get(x);
            if (acc.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                akaunList.add(acc);
            }
        }
        transList = new ArrayList<Transaksi>();

        for (Akaun ak : akaunList) {
            String[] n1 = {"akaun"};
            Object[] v1 = {ak};
            List<Transaksi> trans = transaksiDAO.findByEqualCriterias(n1, v1, null);
            transList.addAll(trans);

            for (Transaksi tr : transList) {
                jumlahCajPop += tr.getAmaun().doubleValue();
//                account = tr.getAkaun().getNoAkaun();
            }
        }
        return new JSP("hasil/kutipan_hasil_carian.jsp").addParameter("popup", "true");
    }

    public Resolution save() {
        System.out.println("--Inside Save--");

        if (hakmilik != null) {
            table = true;
            System.out.println("ID Hakmilik : " + hakmilik.getIdHakmilik());
            System.out.println("acoount : " + account);
            displayList();
            simpan();
            caraBayaran();
            try1();
            System.out.println("Senarai Cara Bayaran : " + senaraiCaraBayaran.size());
        } else {
            table1 = true;
            System.out.println("hakimlikList.size() : " + hakmilikList.size());
            simpan2();
            caraBayaran();
            try2();
            System.out.println("Senarai Cara Bayaran : " + senaraiCaraBayaran.size());
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_resit.jsp");
    }

    @ValidationMethod(on = "save")
    public void validateAmount(ValidationErrors errors) {
        if (jumCaraBayar == 0.0) {
            if (hakmilik != null) {
                displayList();
            } else {
                details();
            }
            errors.add("amaun", new SimpleError("** Sila Masukkan Jumlah yang hendak Dibayar."));
        }
    }

    public void caraBayaran() {
        System.out.println("--Inside caraBayaran--");
        DokumenKewanganBayaran d = new DokumenKewanganBayaran();
        System.out.println("IDKewanganDokumen : " + idKewDoc);
        dkbList = new ArrayList<DokumenKewanganBayaran>();
        List<DokumenKewanganBayaran> dokList = dokKewBayaranDAO.findAll();
        System.out.println("dokList.size() : " + dokList.size());
        for (int x = 0; x < dokList.size(); x++) {
            d = dokList.get(x);

            if (d.getDokumenKewangan().getIdDokumenKewangan().equals(idKewDoc)) {
                dkbList.add(d);
            }
        }
        for (DokumenKewanganBayaran dk : dkbList) {
            System.out.println("cara Bayar : " + dk.getCaraBayaran().getKodCaraBayaran().getNama());
            System.out.println("kod cara Bayar : " + dk.getCaraBayaran().getKodCaraBayaran().getKod());
        }
    }

    public void try1() {
        System.out.println("--Inside Try--");
        System.out.println("ID Hakmilik : " + hakmilik.getIdHakmilik());

        hakmilik = hakmilikDAO.findById(getHakmilik().getIdHakmilik());

        if (hakmilik != null) {
            String[] name = {"hakmilik"};
            Object[] value = {getHakmilik()};
            List<Akaun> akaunList = accDAO.findByEqualCriterias(name, value, null);
            transList = new ArrayList<Transaksi>();
            for (Akaun ak : akaunList) {
                String[] n1 = {"akaun"};
                Object[] v1 = {ak};
                List<Transaksi> temp = transaksiDAO.findByEqualCriterias(n1, v1, null);
                String id = "";
                for (int x = 0; x < temp.size(); x++) {
                    Transaksi t = temp.get(x);
                    if (t.getStatus() != null) {
                        amaun += t.getAmaun().doubleValue();
                    }
                }
                for (int x = 0; x < temp.size(); x++) {
                    Transaksi t = temp.get(x);
//                    if (!(t.getAkaun().getNoAkaun().equals(id))) {
//                        if (t.getStatus() != null) {
//                            BigDecimal j = new BigDecimal(amaun);
//                            t.setAmaun(j);
//                            transList.add(t);
//                            id = t.getAkaun().getNoAkaun();
//                        }
//                    }
                }
                System.out.println("amaun : " + amaun);
            }
            jumlahCaj = 0.0;
            for (Transaksi tr : transList) {
                jumlahCaj += tr.getAmaun().doubleValue();
            }
        }
    }

    public void try2() {
        List<Akaun> acList = new ArrayList<Akaun>();

        list = new ArrayList<Akaun>();
        transList = new ArrayList<Transaksi>();
        for (int x = 0; x < hakmilikList.size(); x++) {
            amaun = 0.0;
            Hakmilik hm = hakmilikList.get(x);

            String[] name = {"hakmilik"};
            Object[] value = {hm};
            List<Akaun> aList = accDAO.findByEqualCriterias(name, value, null);
            System.out.println("aList.size()--try2-- : " + aList.size());
            List<Transaksi> listT = new ArrayList<Transaksi>();
            String tmp = "";
            for (Akaun ak : aList) {
                String[] n1 = {"akaun"};
                Object[] v1 = {ak};
                List<Transaksi> temp = transaksiDAO.findByEqualCriterias(n1, v1, null);
                listT.addAll(temp);
            }
            for (Transaksi tr : listT) {
                if (tr.getStatus() != null) {
                    amaun += tr.getAmaun().doubleValue();
                }
            }
            for (Transaksi t : listT) {
//                if (!(t.getAkaun().getNoAkaun().equals(tmp))) {
//                    BigDecimal j = new BigDecimal(amaun);
//                    t.setAmaun(j);
//                    transList.add(t);
//                    tmp = t.getAkaun().getNoAkaun();
//                }
            }
        }

        for (Transaksi t : transList) {
            jumlahCaj += t.getAmaun().doubleValue();
        }
    }

    public Resolution main() {

        hakmilik = new Hakmilik();
        akaun = new Akaun();
        hakmilikList = new ArrayList<Hakmilik>();
        accList = new ArrayList<Akaun>();
        senaraiCaraBayaran = new ArrayList<CaraBayaran>();

        return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
    }

    public void simpan() {
        // TODO validate amounts
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser(); //penggunaDAO.findById("admin");
        KodCawangan caw = pengguna.getKodCawangan();
        System.out.println("Kod Cawangan : " + caw.getKod());
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(new java.util.Date());

        // generate receipt & save into Folder
        DokumenKewangan dk = null;
        // resit
        Dokumen resit = new Dokumen();
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        dk = new DokumenKewangan();
        dk.setIdDokumenKewangan(tarikh);

        ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());    // set idResit to pageContext
        System.out.println("Jumlah Cara Bayar : " + jumCaraBayar);
        dk.setAmaunBayaran(new BigDecimal(jumCaraBayar));
        dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
        dk.setInfoAudit(ia);
        dk.setCawangan(caw);

        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();   // save cara bayaran
        for (CaraBayaran cb : senaraiCaraBayaran) {
            if (cb.getAmaun().intValue() > 0) {
                if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {    // clear if null
                    cb.setBank(null);
                    cb.setBankCawangan(null);
                }
                if (cb.getBank() != null) {
                    KodBank bank = kodBankDAO.findById(cb.getBank().getKod());
                    cb.setBank(bank);
                }

                KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                cb.setKodCaraBayaran(crByr);
                cb.setCawangan(caw);
                cb.setInfoAudit(ia);
                manager.saveOrUpdate(cb);
                DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                for (int i = 0; i < senaraiCaraBayaran.size(); i++) {
                    CaraBayaran c = senaraiCaraBayaran.get(i);
                    if (c.getKodCaraBayaran().getKod().equals("T")) {
                        dk.setAmaunTunai(c.getAmaun());
                    }
                }
                dkb.setCaraBayaran(cb);
                dkb.setDokumenKewangan(dk);
                dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                dkb.setInfoAudit(ia);
                adkb.add(dkb);
            }
        }
        dk.setSenaraiBayaran(adkb);
        manager.saveOrUpdate(dk);

        idKewDoc = dk.getIdDokumenKewangan();
        List<Transaksi> transaksiLsit = transaksiDAO.findAll();
        for (int a = 0; a < transaksiLsit.size(); a++) {
            Transaksi t = transaksiLsit.get(a);
//            if ((t.getAkaun().getNoAkaun().equalsIgnoreCase(account)) && (t.getStatus().getKod() != 'B')) {
//                t.setDokumenKewangan(dk);
//                ia.setDiKemaskiniOleh(pengguna);
//                ia.setTarikhKemaskini(now);
//                t.setInfoAudit(ia);
//                manager.saveOrUpdate(t);
//            }
        }
//        resit.setFormat("application/pdf");
//        resit.setInfoAudit(ia);
//        resit.setKlasifikasi(klasifikasiAm);
//        resit.setKodDokumen(kodDokumenDAO.findById("RBY")); // penyata perserahan
//        resit.setNoVersi("1.0");
//        resit.setTajuk("Resit Perserahan");
//        manager.save(resit);

        double tot = 0.0;
        List<Akaun> acList = accDAO.findAll();
        for (int i = 0; i < acList.size(); i++) {
            Akaun ak = acList.get(i);
            if (ak.getNoAkaun().equalsIgnoreCase(account)) {
                tot = ak.getBaki().doubleValue() - jumCaraBayar;
                ak.setBaki(new BigDecimal(tot));
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(now);
                ak.setInfoAudit(ia);
                manager.saveOrUpdate(ak);
            }
        }
        if (tot < 0) {
            Transaksi t = new Transaksi();
            akaun = new Akaun();
            akaun.setNoAkaun(account);
//            t.setAkaun(akaun);
            t.setAmaun(new BigDecimal(tot * -1));
            t.setCawangan(caw);
            KodTransaksi kt = new KodTransaksi();
            kt.setKod("20093");
            t.setKodTransaksi(kt);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
            t.setInfoAudit(ia);
            manager.save(t);
        }

//        String documentPath = conf.getProperty("document.path");
//        String path = tarikh + File.separator + String.valueOf(resit.getIdDokumen());
//        reportUtil.generateReport("RST_BAYARAN_001.rdf",
//                        new String [] {"p_id_kew_dok"},
//                        new String[] {idKewDoc},
//                        documentPath + path);
//        resit.setNamaFizikal(path);
//        dokumenDAO.update(resit);

        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
    }

    public void simpan2() {
        // TODO validate amounts
        System.out.println("--Inside Simpan2--");
        System.out.println("hakmilikList.size() : " + hakmilikList.size());
        ArrayList arr = new ArrayList();
        DokumenKewangan dk = null;

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser(); //penggunaDAO.findById("admin");
        KodCawangan caw = pengguna.getKodCawangan();
        System.out.println("Kod Cawangan : " + caw.getKod());
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        double rm = jumCaraBayar;
        for (int m = 0; m < hakmilikList.size(); m++) {
            double amnt = 0.0;
            double amnt1 = 0.0;
            Hakmilik h = hakmilikList.get(m);
            String[] name = {"hakmilik"};
            Object[] value = {h};
            List<Akaun> aList = accDAO.findByEqualCriterias(name, value, null);
            System.out.println("aList.size() : " + aList.size());
            List<Transaksi> listT = new ArrayList<Transaksi>();
            for (Akaun ak : aList) {
                String[] n1 = {"akaun"};
                Object[] v1 = {ak};
                List<Transaksi> temp = transaksiDAO.findByEqualCriterias(n1, v1, null);
                listT.addAll(temp);
            }
            double amt = 0.0;
            for (int e = 0; e < listT.size(); e++) {
                Transaksi tr = listT.get(e);
                if (tr.getAmaun().doubleValue() > 0) {
                    amt += tr.getAmaun().doubleValue();
                }
            }

            String tarikh = "";
            tarikh = (new SimpleDateFormat("ddMMyyyyhhmm")).format(new java.util.Date()) + m;
            // resit
            dk = new DokumenKewangan();
            dk.setIdDokumenKewangan(tarikh);
            // set idResit to pageContext
            ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
            System.out.println("Jumlah Cara Bayar : " + jumCaraBayar);
            dk.setAmaunBayaran(new BigDecimal(amt));
            dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
            dk.setInfoAudit(ia);
            dk.setCawangan(caw);
            // save cara bayaran

            ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
            for (CaraBayaran cb : senaraiCaraBayaran) {
                if (cb.getAmaun().intValue() > 0) {
                    if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {  // clear if null
                        cb.setBank(null);
                        cb.setBankCawangan(null);
                    }
                    if (cb.getBank() != null) {
                        KodBank bank = kodBankDAO.findById(cb.getBank().getKod());
                        cb.setBank(bank);
                    }
                    KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                    cb.setKodCaraBayaran(crByr);
                    cb.setCawangan(caw);
                    cb.setInfoAudit(ia);
                    manager.saveOrUpdate(cb);
                    DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                    for (int i = 0; i < senaraiCaraBayaran.size(); i++) {
                        CaraBayaran c = senaraiCaraBayaran.get(i);
                        if (c.getKodCaraBayaran().getKod().equals("T")) {
                            dk.setAmaunTunai(new BigDecimal(amt));
                        }
                    }
                    dkb.setCaraBayaran(cb);
                    dkb.setDokumenKewangan(dk);
                    dkb.setAmaun(new BigDecimal(amt)); // TODO: cheque handling
                    dkb.setInfoAudit(ia);
                    adkb.add(dkb);
                }
            }
            String tmp = dk.getIdDokumenKewangan();
            arr.add(tmp);
            dk.setSenaraiBayaran(adkb);
            manager.saveOrUpdate(dk);

            for (int e = 0; e < listT.size(); e++) {
                Transaksi tr = listT.get(e);

                tr.setDokumenKewangan(dk);
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(now);
                tr.setInfoAudit(ia);
                manager.saveOrUpdate(tr);
                if (tr.getAmaun().doubleValue() > 0) {
                    amnt += tr.getAmaun().doubleValue();
                } else if (tr.getAmaun().doubleValue() < 0) {
                    amnt1 += tr.getAmaun().doubleValue();
                }
            }
            System.out.println("rm *****: " + rm);
            rm = rm - amnt;
            System.out.println("rm : " + rm);
            double a = 0.0;
            System.out.println("amnt : " + amnt);
            for (int x = 0; x < aList.size(); x++) {
                Akaun ak = aList.get(x);

                a = ak.getBaki().doubleValue() - amt;
                ak.setBaki(new BigDecimal(a));
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(now);
                ak.setInfoAudit(ia);
                manager.saveOrUpdate(ak);
            }
        }
        idKewDoc = dk.getIdDokumenKewangan();
        System.out.println("arr.size() : " + arr.size());
        System.out.println("arr : " + arr);

        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
    }

    public Resolution cetak() throws FileNotFoundException {
        File f = null;
        if (hakmilik != null) {
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            Pengguna pengguna = ctx.getUser();
            KodCawangan caw = pengguna.getKodCawangan();
            Date now = new Date();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);


            System.out.println("--cetakResit(hm)--");
            String[] name = {"hakmilik"};
            Object[] value = {getHakmilik()};
            List<Akaun> akaunList = accDAO.findByEqualCriterias(name, value, null);
            List<Transaksi> listT = new ArrayList<Transaksi>();
            for (Akaun ak : akaunList) {
                String[] n1 = {"akaun"};
                Object[] v1 = {ak};
                List<Transaksi> temp = transaksiDAO.findByEqualCriterias(n1, v1, null);
                listT.addAll(temp);
            }
            String idKew = " ";
            for (Transaksi t : listT) {
                idKew = t.getDokumenKewangan().getIdDokumenKewangan();
            }
            String documentPath = File.separator + "tmp" + File.separator;
            String path = tarikh + File.separator + String.valueOf(hakmilik.getIdHakmilik());
            System.out.println("path : " + path);

            reportUtil.generateReport("HSL_RST_BIL_CUKAI_009.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{idKew},
                    documentPath + path, null);

            f = new File(documentPath + path);
        } else {
            System.out.println("hakmilikList.size() : " + hakmilikList.size());
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            Pengguna pengguna = ctx.getUser();
            KodCawangan caw = pengguna.getKodCawangan();
            Date now = new Date();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);


            System.out.println("--cetakResit(hm)--");
            for (int m = 0; m < hakmilikList.size(); m++) {

                Hakmilik h = hakmilikList.get(m);

                System.out.println("idHakmilik : " + h.getIdHakmilik());

                String[] name = {"hakmilik"};
                Object[] value = {h};
                List<Akaun> akaunList = accDAO.findByEqualCriterias(name, value, null);
                List<Transaksi> listT = new ArrayList<Transaksi>();
                for (Akaun ak : akaunList) {
                    String[] n1 = {"akaun"};
                    Object[] v1 = {ak};
                    List<Transaksi> temp = transaksiDAO.findByEqualCriterias(n1, v1, null);
                    listT.addAll(temp);
                }
                String idKew = " ";
                for (Transaksi t : listT) {
                    idKew = t.getDokumenKewangan().getIdDokumenKewangan();
                }

                String documentPath = File.separator + "tmp" + File.separator;
                String path = tarikh + File.separator + String.valueOf(h.getIdHakmilik());
                System.out.println("path : " + path);

                reportUtil.generateReport("HSL_RST_BIL_CUKAI_009.rdf",
                        new String[]{"p_id_kew_dok"},
                        new String[]{idKew},
                        documentPath + path, null);

                f = new File(documentPath + path);
            }
        }

        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public Resolution cetak2() throws FileNotFoundException {
        Date now = new Date();
        File f = null;
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            Pengguna pengguna = ctx.getUser();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        String idKew = getContext().getRequest().getParameter("idKew");
        String idHakmilik = getContext().getRequest().getParameter("idhakmilik");
        String documentPath = File.separator + "tmp" + File.separator;
        String path = tarikh + File.separator + String.valueOf(idHakmilik);
        reportUtil.generateReport("HSL_RST_BIL_CUKAI_009.rdf",
                new String[]{"p_id_kew_dok"},
                new String[]{idKew},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);

    }
}
