package etanah.view.stripes.hasil;

import java.text.ParseException;
import java.util.*;
import etanah.dao.*;
import able.stripes.*;
import etanah.model.*;
import java.math.BigDecimal;
import etanah.report.ReportUtil;
import com.google.inject.Inject;
import etanah.model.KodStatusTransaksiKewangan;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.*;
import etanah.view.etanahActionBeanContext;
import etanah.service.KaunterService;
import etanah.view.kaunter.PermohonanKaunter2;
import etanah.sequence.GeneratorNoResit2;
import etanah.util.StringUtils;
import java.util.ArrayList;
import net.sourceforge.stripes.util.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;

@HttpCache(allow = false)
//@Wizard(startEvents = {"details", "showEditPemohon", "delete", "chooseIDHakmilik", "cetak", "deleteSelected", "storeNoAkaun"})
@UrlBinding("/hasil/kutipan_hasil")
public class KutipanHasilActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KutipanHasilActionBean.class);
    private static final boolean debug = LOG.isDebugEnabled();

    private Akaun akaun;
    private KodUrusan kodUrusan;
    private KodCaraBayaran kodCaraBayaran;
    private KodCawangan kodCawangan;
    private KodAkaun kodAkaun;
    private Hakmilik hakmilik;
    private Transaksi transaksi;
    private Pengguna pguna = new Pengguna();
    private KumpulanAkaun kumpulanAkaun = new KumpulanAkaun();
    private DokumenKewanganBayaran dokKewBayaran;
    private InfoAudit infoAudit;
    private DokumenKewangan dokumenKewangan;
    private CaraBayaran caraBayaran;
    private HakmilikDAO hakmilikDAO;
    private AkaunDAO accDAO;
    private CaraBayaranDAO caraBayaranDAO;
    private TransaksiDAO transaksiDAO;
    private DokumenKewanganBayaranDAO dokKewBayaranDAO;
    private KodTransaksiDAO kodTransaksiDAO;
    private KodDokumenDAO kodDokumenDAO;
    private DokumenKewanganDAO dokumenKewanganDAO;
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    private PermohonanDAO permohonanDAO;
    private KodBankDAO kodBankDAO;
    private String idHakmilik;
    private String account;
    private String idKewDoc;
    private String idHakmilikSiriDari;
    private String idHakmilikSiriKe;
    private String noAkaunSiriDari;
    private String noAkaunSiriKe;
    private String kumpAkaun = null;
    private String rst = null;
    private String validateCek = null;
    private String validatePos = null;
    private boolean notis6a = false;
    private static BigDecimal balance = new BigDecimal(0);
    private boolean header = true;
    private BigDecimal baki;
    private BigDecimal jumCaraBayar;
    private BigDecimal total;
    private BigDecimal amaun;
    private boolean visible = true;
    private boolean del = true;
    private boolean button = false;
    private boolean login = false;
    private boolean hapusBtn = false;
    private String flag;
    private static String idHmBalance;
    private ArrayList kewDokID = new ArrayList();
    private List<Akaun> list = new ArrayList<Akaun>();
    private List<Akaun> dummyList = new ArrayList<Akaun>();
//    private List<Akaun> dummyAccList = new ArrayList<Akaun>();
    private ArrayList<Hakmilik> hakmilikList = new ArrayList<Hakmilik>();
    private ArrayList<Hakmilik> dummyHList = new ArrayList<Hakmilik>();
    private ArrayList<Hakmilik> hList = new ArrayList<Hakmilik>();
    private ArrayList<Akaun> accList = new ArrayList<Akaun>();
    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
    private ArrayList<DokumenKewangan> kewDokList = new ArrayList<DokumenKewangan>();
    private List<Transaksi> transList;
    private List<DokumenKewanganBayaran> dkbList;
    private ArrayList listing = new ArrayList();
    private List<String> tarikhCek = new ArrayList<String>();
    private List<String> amaunLebihList = new ArrayList<String>();
    private List<BigDecimal> amaunList = new ArrayList<BigDecimal>();
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private BigDecimal jumlahCaj = new BigDecimal(0.00);
    private BigDecimal jumlahCajPop = new BigDecimal(0.00);
    private BigDecimal jumlah = new BigDecimal(0.00);
    private int bilHakmilik = 6;
    private BigDecimal returnBalance = new BigDecimal(0.00);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    private boolean btn = true;
    private boolean flag1 = false;
    private String idDokumenKewangan;
    private BigDecimal jumlahCb = new BigDecimal(0.00);
    private KodStatusDokumenKewangan status;
    private String kodBatal;
    private static String kodNegeri;
    private Date tarikh;
    private String tarikhBatal;
    private BigDecimal jumlahCaj1 = new BigDecimal(0.00);
    private List<Akaun> akaunList;
    private int bil = 0;
    private int bilList = 0;
    private int bilAmaunList = 0;
    private Akaun akaunKredit;
    private Permohonan permohonan;
    private String IdPermohonan;
    private boolean ptg = true;
    private String mod = "Biasa";
//    private boolean balanceFlag = false;
    private boolean bakiFlag = false;
    private boolean collect = false;
    private String amn = null;
    private DokumenKewangan receipt = new DokumenKewangan();

    private int tahun = 0;
    private int min = 0;
    private int max = 0;
    private BigDecimal cukaiTaliAir = new BigDecimal(0);
    private BigDecimal tunggakanTaliAir = new BigDecimal(0);
    private BigDecimal remisyen = new BigDecimal(0);
    private BigDecimal notis = new BigDecimal(0);
    private BigDecimal denda = new BigDecimal(0);
    private BigDecimal dendaSemasa = new BigDecimal(0);
    private BigDecimal dendaTunggakan = new BigDecimal(0);
    private BigDecimal tunggakan = new BigDecimal(0);
    private BigDecimal amaunLebih = new BigDecimal(0);
    private BigDecimal amaunKurang = new BigDecimal(0);
    private BigDecimal cukaiTahunDepan = new BigDecimal(0);
    private String listHakmilik = "";

    @Inject
    public KutipanHasilActionBean(DokumenKewanganDAO dokumenKewanganDAO, KodDokumenDAO kodDokumenDAO, PermohonanDAO permohonanDAO,
            CaraBayaranDAO caraBayaranDAO, AkaunDAO accDAO, HakmilikDAO hakmilikDAO, TransaksiDAO transaksiDAO, DokumenKewanganBayaranDAO dokKewBayaranDAO,
            KodCaraBayaranDAO kodCaraBayaranDAO, KodBankDAO kodBankDAO) {
        this.accDAO = accDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.transaksiDAO = transaksiDAO;
        this.caraBayaranDAO = caraBayaranDAO;
        this.kodDokumenDAO = kodDokumenDAO;
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.dokKewBayaranDAO = dokKewBayaranDAO;
        this.kodCaraBayaranDAO = kodCaraBayaranDAO;
        this.kodBankDAO = kodBankDAO;
        this.permohonanDAO = permohonanDAO;

    }
    @Inject
    KutipanHasilManager manager;
    @Inject
    private ModKutipService modKutip;
    @Inject
    KutipanHasilService hasilService;
    @Inject
    private KodAkaunDAO kodAkaunDAO;
    @Inject
    TransactionService n9;
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
    private AkaunStrataDAO akaunStrataDAO;
    @Inject
    PembatalanEndorsanService batal;

    public String getIdKewDoc() {
        return idKewDoc;
    }

    public void setIdKewDoc(String idKewDoc) {
        this.idKewDoc = idKewDoc;
    }

    public ArrayList getListing() {
        return listing;
    }

    public void setListing(ArrayList listing) {
        this.listing = listing;
    }

    public String getKumpAkaun() {
        return kumpAkaun;
    }

    public void setKumpAkaun(String kumpAkaun) {
        this.kumpAkaun = kumpAkaun;
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

    public void setBilHakmilik(int bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getJumCaraBayar() {
        return jumCaraBayar;
    }

    public void setJumCaraBayar(BigDecimal jumCaraBayar) {
        this.jumCaraBayar = jumCaraBayar;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
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

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public KodAkaun getKodAkaun() {
        return kodAkaun;
    }

    public void setKodAkaun(KodAkaun kodAkaun) {
        this.kodAkaun = kodAkaun;
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

    public BigDecimal getJumlahCajPop() {
        return jumlahCajPop;
    }

    public void setJumlahCajPop(BigDecimal jumlahCajPop) {
        this.jumlahCajPop = jumlahCajPop;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String getIdHakmilikSiriDari() {
        return idHakmilikSiriDari;
    }

    public void setIdHakmilikSiriDari(String idHakmilikSiriDari) {
        this.idHakmilikSiriDari = idHakmilikSiriDari;
    }

    public String getIdHakmilikSiriKe() {
        return idHakmilikSiriKe;
    }

    public void setIdHakmilikSiriKe(String idHakmilikSiriKe) {
        this.idHakmilikSiriKe = idHakmilikSiriKe;
    }

    public boolean getHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    @DefaultHandler
    public Resolution showForm() {
//        dkbList = dokKewBayaranDAO.findAll();
//        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil.jsp");
        header = false;
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "negeriSembilan";
        }
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        pguna = new Pengguna();
        pguna = ctx.getUser();
        if (pguna.getKodCawangan().getKod().equals("00")) {
            ptg = false;
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

    public Resolution search() {
        flag = "none";
        login = true;
        del = false;
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "negeriSembilan";
        }

        if (hakmilik != null) {
            hakmilikList.add(hakmilik);
            bayaranPukal1();
            collectPayment();
            if (list.size() > 0) {
                bilList = list.size();
                visible = false;
            }
        } else if (akaun != null) {
            AkaunStrata as = akaunStrataDAO.findById(akaun.getNoAkaun().trim());

            if (as != null) {
                akaun = as.getHakmilik().getAkaunCukai();
            } else {
                akaun = accDAO.findById(akaun.getNoAkaun().trim());
            }

            if (akaun != null) {
                String hm = akaun.getHakmilik().getIdHakmilik();

                hakmilik = hakmilikDAO.findById(hm);
                hakmilikList.add(hakmilik);
                bayaranPukal1();
                collectPayment();
                if (list.size() > 0) {
                    bilList = list.size();
                    visible = false;
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

//    @ValidationMethod(on = "search")
//    public void validateHakmilik(ValidationErrors errors) {
//        if ((hakmilik == null)&&(akaun == null)) {
//            errors.add("a", new SimpleError("Sila Masukkan ID Hakmilik atau Nombor Akaun"));
//        }
//    }
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
        for (Akaun ak : list) {
            setAccount(ak.getNoAkaun());
        }

//         Date now = new Date();
//         String trhCek = (now.getMonth()-5)+"/"+now.getDate()+"/"+(now.getYear()+1900);
//         String trhPos = (now.getMonth()-2)+"/"+now.getDate()+"/"+(now.getYear()+1900);
//         validateCek = sdf.format(new Date(trhCek));
//         validatePos = sdf.format(new Date(trhPos));
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

    public Resolution bayranPukal() {
        button = true;
        dummyHList = new ArrayList<Hakmilik>();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        hasilService.resetAll(ctx);
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "negeriSembilan";
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_3.jsp");
    }

    public Resolution addCaraBayar() {
        collect = true;
        if ((hakmilik != null) || (akaun != null)) {
            search();
        } else {
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            hList = hasilService.getAllHakmilikFromSession(ctx);
            details();
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
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

//    public void resetData(){
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        hasilService.resetAll(ctx);
//    }
    public Resolution details() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        hasilService.resetAll(ctx);
        if (dummyHList.size() > 0) {
            hList = dummyHList;
        }
//        hList = dummyHList;
//        if(((hakmilikList.size()>0)||(accList.size() > 0))&&((idHakmilikSiriDari != null)||(noAkaunSiriDari != null))){
//            LOG.info("xtau");
//            int length = idHakmilikSiriDari.length();
//            String a = "";
//            String c = "";
//            String b = "";
//            if(length == 17){
//                a = idHakmilikSiriDari.substring(9, length);
//                c = idHakmilikSiriDari.substring(0, 9);
//                b = idHakmilikSiriKe.substring(9, length);
//            }else{
//                a = idHakmilikSiriDari.substring(8, length);
//                c = idHakmilikSiriDari.substring(0, 8);
//                b = idHakmilikSiriKe.substring(8, length);
//
//            }
//
//            int a1 = Integer.parseInt(a);
//            int b1 = Integer.parseInt(b);
//            int series = b1 - a1 +1;
//            String id = null;
//            int l1 = c.length();
//            int l = length - l1;
//            for(int x=0;x<series;x++){
//                int a2 = a1 + x;
//                id = String.format("%s%0"+l+"d", c, a2);
//
//                Hakmilik h = hakmilikDAO.findById(id);
//                if(h != null){
//                    hakmilikList.add(h);
//                }
//            }
//            dummyHList = hakmilikList;
//            bayaranPukal1();
//        }
        if ((hList.size() > 0) || (accList.size() > 0) && ((idHakmilikSiriDari == null) || (noAkaunSiriDari == null)) && (kumpulanAkaun == null)) {
            hakmilikList = new ArrayList<Hakmilik>();
            for (Hakmilik h : hList) {
                Hakmilik m = hakmilikDAO.findById(h.getIdHakmilik());
                hakmilikList.add(m);
            }
            dummyHList = hakmilikList;
            hasilService.addHakmilik(ctx, hList);
            bayaranPukal1();
        }
//        if(!kumpulanAkaun.getIdKumpulan().equals("")){
        if (kumpAkaun != null) {
            searchingByKumpulanAkaun();
//            dummyHList = hakmilikList;
//            bayaranPukal1();
            hapusBtn = true;
        }
        if ((idHakmilikSiriDari != null) && (idHakmilikSiriKe != null) && (((hList.size() < 1) || (accList.size() < 1)))) {
            int length = idHakmilikSiriDari.length();
            String a = idHakmilikSiriDari.substring(9, length);
            String c = idHakmilikSiriDari.substring(0, 9);
            String b = idHakmilikSiriKe.substring(9, length);

            int a1 = Integer.parseInt(a);
            int b1 = Integer.parseInt(b);
            int series = b1 - a1 + 1;
            String id = null;
            int l1 = c.length();
            int l = length - l1;
            for (int x = 0; x < series; x++) {
                int a2 = a1 + x;
                id = String.format("%s%0" + l + "d", c, a2);

                Hakmilik h = hakmilikDAO.findById(id);
                if (h != null) {
                    hakmilikList.add(h);
                }
            }
            dummyHList = hakmilikList;
            hasilService.addHakmilik(ctx, hakmilikList);
            bayaranPukal1();
            hapusBtn = true;
        }

        if (((noAkaunSiriDari != null) && (noAkaunSiriKe != null)) && (((hakmilikList.size() < 1) || (accList.size() < 1)))) {
            int length = noAkaunSiriDari.length();
            String a = noAkaunSiriDari.substring(2, length);
            String c = noAkaunSiriDari.substring(0, 2);
            String b = noAkaunSiriKe.substring(2, length);

            long a1 = Long.parseLong(a);
            long b1 = Long.parseLong(b);
            long series = b1 - a1 + 1;
            String id = null;
            int l1 = c.length();
            int l = length - l1;
            for (int x = 0; x < series; x++) {
                long a2 = a1 + x;
                id = String.format("%s%0" + l + "d", c, a2);

                Akaun h = accDAO.findById(id);
                if (h != null) {
                    hakmilikList.add(h.getHakmilik());
                }
            }
            dummyHList = hakmilikList;
            hasilService.addHakmilik(ctx, hakmilikList);
            bayaranPukal1();
        }
//        if(collect){
        collectPayment();
//        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

    public void searchingByKumpulanAkaun() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        hakmilikList = new ArrayList<Hakmilik>();
        List<Hakmilik> hmList = new ArrayList<Hakmilik>();
        hList = new ArrayList<Hakmilik>();

        String query = "SELECT a FROM etanah.model.Akaun a WHERE a.kumpulan.idKumpulan = :kump AND a.kodAkaun.kod = 'AC'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kump", kumpAkaun);
        List<Akaun> listAccount = q.list();

        for (Akaun ak : listAccount) {
            hmList.add(ak.getHakmilik());
        }

        for (Hakmilik hm : hmList) {
            Hakmilik m = hakmilikDAO.findById(hm.getIdHakmilik());
            hakmilikList.add(m);
        }
        hList = hakmilikList;
        dummyHList = hakmilikList;
        hasilService.addHakmilik(ctx, hakmilikList);
        bayaranPukal1();
        del = false;
    }

    public void bayaranPukal1() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();
        if ((akaun == null) && (hakmilik == null)) {
            button = true;
        }
        List<Akaun> acList = new ArrayList<Akaun>();
        list = new ArrayList<Akaun>();
        for (int x = 0; x < hakmilikList.size(); x++) {
            Hakmilik hm = hakmilikList.get(x);
            String[] n1 = {"hakmilik"};
            Object[] v1 = {hm};
            acList = accDAO.findByEqualCriterias(n1, v1, null);
            for (Akaun ak : acList) {
                if ((ak.getKodAkaun().getKod().equals("AC")) && (ak.getStatus().getKod().equals("A"))) {
                    list.add(ak);
                }
            }
        }
        if (list.size() > 0) {
            bilList = list.size();
            visible = false;
        }
        dummyList = new ArrayList<Akaun>();
        dummyList = list;
        for (int x = 0; x < list.size(); x++) {
            Akaun a = list.get(x);
            account = a.getNoAkaun();
            if (a.getBaki().compareTo(new BigDecimal(0)) >= 0) {
                amaunList.add(a.getBaki());
            } else {
                amaunList.add(new BigDecimal(0));
            }
        }
        for (BigDecimal bd : amaunList) {
            jumlahCaj = jumlahCaj.add(bd);
        }
    }

//    @ValidationMethod(on = "details")
//    public void validateList(ValidationErrors errors) {
//        if ((hakmilikList.size() == 0) && (accList.size() == 0)&&(idHakmilikSiriDari == null)&&(idHakmilikSiriKe ==null)
//                &&(noAkaunSiriDari == null)&&(noAkaunSiriKe ==null)&&(kumpulanAkaun == null)) {
//            errors.add("list", new SimpleError("** Sila Masukkan Senarai Nombor Akaun atau ID Hakmilik yang telibat."));
//        } else {
//            for (int i = 0; i < hakmilikList.size(); i++) {
//                Hakmilik hm = hakmilikList.get(i);
//                String id = hm.getIdHakmilik();
//                for (int m = i + 1; m < hakmilikList.size(); m++) {
//                    Hakmilik h = hakmilikList.get(m);
//                    if (h.getIdHakmilik().equals(id)) {
//                        errors.add("list", new SimpleError("Anda telah memasukkan " + hm.getIdHakmilik() + " lebih daripada sekali."));
//                    }
//                }
//            }
//        }
//    }
    public Resolution back() {
        if ((hakmilik != null) || (akaun != null)) {
            hakmilik = new Hakmilik();
            akaun = new Akaun();
            idHakmilikSiriDari = "";
            idHmBalance = "";
            idHakmilikSiriKe = "";
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
            return new RedirectResolution(PermohonanKaunter2.class);
        }
        if (hList.size() > 0) {
//            hakmilikList = new ArrayList<Hakmilik>();
//            accList = new ArrayList<Akaun>();
            idHakmilikSiriDari = "";
            idHmBalance = "";
            idHakmilikSiriKe = "";
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
            return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_3.jsp");
        } else {
            hakmilik = new Hakmilik();
            akaun = new Akaun();
            idHakmilikSiriDari = "";
            idHakmilikSiriKe = "";
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
            idHmBalance = "";
            return new RedirectResolution(PermohonanKaunter2.class);
        }
    }

    public Resolution popup() {
        return new JSP("common/pilih_pemohon.jsp").addParameter("tab", "true");

    }

    public Resolution showEditPemohon() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        transList = new ArrayList<Transaksi>();
        Date now = new Date();
        tahun = Integer.parseInt(yy.format(now));
        min = tahun;
        BigDecimal rem = new BigDecimal(0);
        List<Transaksi> tList = new ArrayList<Transaksi>();
        String[] n1 = {"hakmilik"};
        Object[] v1 = {getHakmilik()};
        List<Akaun> a = accDAO.findByEqualCriterias(n1, v1, null);
        for (Akaun ak : a) {
            if (ak.getKodAkaun().getKod().equals("AC")) {
                account = ak.getNoAkaun();
                tList = ak.getSemuaTransaksi();
            }
        }
        Akaun l = accDAO.findById(account);
        if (l.getBaki().compareTo(new BigDecimal(0)) < 0) {
            amaunLebih = l.getBaki();
        }
        boolean ck = false;
        for (Transaksi tr : tList) {
            if (tr.getAkaunKredit() != null) {
                if (tr.getAkaunKredit().getNoAkaun().equals(account)) {
                    double d = tr.getAmaun().doubleValue() * -1;
                    tr.setAmaun(new BigDecimal(d));
                    receipt = tr.getDokumenKewangan();
                    transList.add(tr);
                }
            }
            if (tr.getAkaunDebit() != null) {
                if (tr.getAkaunDebit().getNoAkaun().equals(account)) {
                    transList.add(tr);
                }
            }
            if (tr.getDokumenKewangan() == null) {
                if (kodNegeri.equals("negeriSembilan")) {
                    if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("notis6A"))) {
                        notis = tr.getAmaun();
                    }
                }
                if (kodNegeri.equals("melaka")) {
                    if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("notis6AMelaka"))) {
                        notis = tr.getAmaun();
                    }
                }
                if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("dendaLewat"))) {
                    if (tr.getAkaunKredit() == null) {
                        if (tr.getUntukTahun() == tahun) {
                            dendaSemasa = tr.getAmaun();
                        } else {
                            dendaTunggakan = dendaTunggakan.add(tr.getAmaun());
                        }
                        denda = denda.add(tr.getAmaun());
                    }
                }
                if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTaliAir"))) {
                    cukaiTaliAir = tr.getAmaun();
                }
                if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("tunggakanTaliAir"))) {
                    tunggakanTaliAir = tr.getAmaun();
                }
                if ((tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyen.remsb"))) || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyen.remts")))
                        || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyen.remri"))) || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyen.remtd")))
                        || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyenTanah"))) || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyenTunggak")))
                        || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyenDenda")))) {
                    rem = rem.add(tr.getAmaun());
                }
                if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTanahTunggakan"))) {
                    tunggakan = tunggakan.add(tr.getAmaun());
                    if (tr.getUntukTahun() < min) {
                        min = tr.getUntukTahun();
                    }
                    max = min;
                    if ((tr.getUntukTahun() > max) && (max != tahun)) {
                        max = tr.getUntukTahun();
                    }
                }
            }
        }
//        for (Transaksi tr : transList) {
//            if((tr.getStatus().getKod()== 'A')&&(tr.getAkaunKredit() == null))
//                jumlahCajPop = jumlahCajPop.add(tr.getAmaun());
//        }
        jumlahCajPop = l.getBaki();
        cukaiTahunDepan = hakmilik.getCukaiSebenar().add(jumlahCajPop);
        if (jumlahCajPop.compareTo(new BigDecimal(0)) == -1) {
            amaunLebih = jumlahCajPop.multiply(new BigDecimal(-1));
            cukaiTahunDepan = hakmilik.getCukaiSebenar().subtract(amaunLebih);
        }
        if (rem.compareTo(new BigDecimal(0)) < 0) {
            remisyen = rem.multiply(new BigDecimal(-1));
        }
        if (rem.compareTo(new BigDecimal(0)) > 0) {
            remisyen = rem;
        }
        return new JSP("hasil/kutipan_hasil_carian.jsp").addParameter("popup", "true");
    }

    public Resolution chooseIDHakmilik() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();
        List<Hakmilik> sessHakmilik = hasilService.getAllHakmilikFromSession(ctx);
        List<Akaun> sessAkaun = new ArrayList<Akaun>();
        for (Hakmilik h : sessHakmilik) {
            String query = "SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :hm AND a.kodAkaun.kod = 'AC'";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("hm", h.getIdHakmilik());
            Akaun a = (Akaun) q.uniqueResult();
            sessAkaun.add(a);
        }
        senaraiAkaun.addAll(sessAkaun);
        bilAmaunList = senaraiAkaun.size();
        String bal = getContext().getRequest().getParameter("balance");
        balance = new BigDecimal(bal);
        return new JSP("hasil/kutipan_hasil_2.jsp").addParameter("popup", "true");
    }

    public Resolution storeNoAkaun() {
        String nomborAkaun = getContext().getRequest().getParameter("nomborAkaun");
        String results = nomborAkaun;
        return new StreamingResolution("text/plain", results);
    }

    public Resolution delete() {
        List<Hakmilik> hList = new ArrayList<Hakmilik>();
        for (Akaun a : dummyList) {
            hList.add(a.getHakmilik());
        }
//        List<Akaun> acList = new ArrayList<Akaun>();
//        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
//        Hakmilik h = hakmilikDAO.findById(idHakmilik);
//
//        for(Hakmilik hm : hList){
//            if(!hm.getIdHakmilik().equals(idHakmilik)){
//                senaraiHakmilik.add(hm);
//            }
//        }
        List<Akaun> sAkaun = new ArrayList<Akaun>();
        List<Akaun> sAkaun1 = new ArrayList<Akaun>();
        sAkaun = dummyList;
        ArrayList<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for (Akaun ac : sAkaun) {
            if (!ac.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                sAkaun1.add(ac);
                senaraiHakmilik.add(ac.getHakmilik());
            }
        }

        list = sAkaun1;
        hakmilikList = senaraiHakmilik;
        bayaranPukal1();
        collectPayment();

        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

    public Resolution deleteSelected() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        kumpAkaun = null;
        idHakmilikSiriDari = null;
        idHakmilikSiriKe = null;

        String[] ids = getContext().getRequest().getParameterValues("chkbox");
        List<Hakmilik> hLists = new ArrayList<Hakmilik>();
        hList = new ArrayList<Hakmilik>();
        List<Hakmilik> hmList = new ArrayList<Hakmilik>();
        hmList = hasilService.getAllHakmilikFromSession(ctx);
        for (String id : ids) {
            for (Hakmilik hm : hmList) {
                if (!hm.getIdHakmilik().equals(id)) {
                    hLists.add(hm);
                }
            }
        }
        hakmilikList = new ArrayList<Hakmilik>();
        hakmilikList.addAll(hLists);
        hList = hakmilikList;
        hasilService.resetAll(ctx);
        hasilService.addHakmilik(ctx, hList);
        bayaranPukal1();
        bil = new Integer(0);
        senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        collectPayment();
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

    public Resolution save() throws ParseException {
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        if ((hakmilik != null) || (akaun != null)) {
            tx = s.beginTransaction();
            try {
                this.search();
                this.simpan2();
                this.caraBayaran();
                this.try1();
                tx.commit();
                addSimpleMessage("Maklumat Telah Berjaya Disimpan");
            } catch (Exception ex) {
                LOG.error(ex.getMessage(), ex);
                tx.rollback();
                noResitGenerator2.rollbackAndUnlockSerialNo(pguna);
                addSimpleError("Pembayaran tidak berjaya.");
            } finally {
                noResitGenerator2.commitAndUnlockSerialNo(pguna);
                batal.checkingDasarTuntutanCukaiHakmilik(hakmilik, pguna);
            }
        } else {
            tx = s.beginTransaction();
            try {
                etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
                hList = hasilService.getAllHakmilikFromSession(ctx);
                details();
                simpan3();
                caraBayaran();
                try2();
                tx.commit();
                addSimpleMessage("Maklumat Telah Berjaya Disimpan");
            } catch (Exception ex) {
                LOG.error(ex.getMessage(), ex);
                tx.rollback();
                noResitGenerator2.rollbackAndUnlockSerialNo(pguna);
                addSimpleError("Pembayaran tidak berjaya.");
            } finally {
                noResitGenerator2.commitAndUnlockSerialNo(pguna);
                etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
                hasilService.resetAll(ctx);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_resit.jsp");
    }

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public void caraBayaran() {
        dkbList = new ArrayList<DokumenKewanganBayaran>();
        DokumenKewangan dk = dokumenKewanganDAO.findById(idKewDoc);
        String[] n1 = {"dokumenKewangan"};
        Object[] v1 = {dk};
        dkbList = dokKewBayaranDAO.findByEqualCriterias(n1, v1, null);
    }

    public void try1() {
        String a = "";
        hakmilik = hakmilikDAO.findById(hakmilik.getIdHakmilik());
        transaksi = new Transaksi();
        transList = new ArrayList<Transaksi>();
        String[] name = {"hakmilik"};
        Object[] value = {getHakmilik()};
        List<Akaun> akaunList = accDAO.findByEqualCriterias(name, value, null);
        List<Transaksi> trnsList = new ArrayList<Transaksi>();
        for (Akaun ak : akaunList) {
            if (ak.getKodAkaun().getKod().equals("AC") && ak.getStatus().getKod().equals("A")) {
                a = ak.getNoAkaun();
            }
        }
        BigDecimal tot = (BigDecimal.ZERO);
        String akaunTry = "";
        if (jumlahCaj.doubleValue() > 0.0) {
            Akaun ak = accDAO.findById(a);

            DokumenKewangan dk = dokumenKewanganDAO.findById(idKewDoc);
            tot = dk.getAmaunBayaran();
            String query = "SELECT t FROM etanah.model.Transaksi t where t.dokumenKewangan.idDokumenKewangan = :resit AND t.status.kod = 'T'";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("resit", dk.getIdDokumenKewangan());
            trnsList = q.list();

            if (trnsList.size() > 1) {
                for (int i = 0; i < trnsList.size(); i++) {
                    Transaksi t = trnsList.get(i);
                    for (int y = i + 1; y < trnsList.size(); y++) {
                        Transaksi tr = trnsList.get(y);
                        if (!t.getAkaunKredit().getNoAkaun().equals(akaunTry)) {
                            t.setAkaunKredit(ak);
                            transList.add(t);
                            akaunTry = tr.getAkaunKredit().getNoAkaun();
                        }
                    }
                }
            } else {
                for (Transaksi tt : trnsList) {
                    tt.setAkaunKredit(ak);
                    transList.add(tt);
                }
            }
        }
        if (jumlahCaj.doubleValue() <= 0.0) {
            Akaun ak = accDAO.findById(a);
            DokumenKewangan dk = dokumenKewanganDAO.findById(idKewDoc);
            String[] n1 = {"dokumenKewangan"};
            Object[] v1 = {dk};
            List<Transaksi> tList = transaksiDAO.findByEqualCriterias(n1, v1, null);

            for (Transaksi tt : tList) {
                tt.setAkaunKredit(ak);
                transList.add(tt);
            }
        }
        DokumenKewangan dk = dokumenKewanganDAO.findById(idKewDoc);
        jumlahCaj = dk.getAmaunBayaran();
    }

    public void try2() {
        list = new ArrayList<Akaun>();
        transList = new ArrayList<Transaksi>();
        BigDecimal caj = new BigDecimal(0);
        for (int x = 0; x < kewDokID.size(); x++) {
            amaun = new BigDecimal(0);
            String a = "";
            Object idKew_dok = kewDokID.get(x);
            DokumenKewangan dk = dokumenKewanganDAO.findById(idKew_dok.toString());
//            Hakmilik hm = hakmilikList.get(x);
            Hakmilik hm = dk.getAkaun().getHakmilik();
            List<Transaksi> trnsList = new ArrayList<Transaksi>();

            String hakmilikQuery = "SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :idHakmilik AND a.kodAkaun.kod = 'AC' ";
            Query qHakmilik = sessionProvider.get().createQuery(hakmilikQuery);
            qHakmilik.setString("idHakmilik", hm.getIdHakmilik());
            Akaun ac = (Akaun) qHakmilik.uniqueResult();
            a = ac.getNoAkaun();
            BigDecimal tot = (BigDecimal.ZERO);

            tot = dk.getAmaunBayaran();
            caj = caj.add(tot);

            String query = "SELECT t FROM etanah.model.Transaksi t where t.dokumenKewangan.idDokumenKewangan = :resit AND t.status.kod = 'T'";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("resit", dk.getIdDokumenKewangan());
            trnsList = q.list();

            String accnt = "";
            if (trnsList.size() > 1) {
                for (int i = 0; i < trnsList.size(); i++) {
                    Transaksi t = trnsList.get(i);
                    for (int y = i + 1; y < trnsList.size(); y++) {
                        Transaksi tr = trnsList.get(y);
                        if (!t.getAkaunKredit().getNoAkaun().equals(accnt)) {
                            t.setAkaunKredit(ac);
                            transList.add(t);
                            accnt = tr.getAkaunKredit().getNoAkaun();
                        }
                    }
                }
            } else {
                for (Transaksi tt : trnsList) {
                    tt.setAkaunKredit(ac);
                    transList.add(tt);
                }
            }
        }
        jumlahCaj = caj;
    }

    public Resolution main() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        hasilService.resetAll(ctx);
        hakmilik = new Hakmilik();
        akaun = new Akaun();
        hakmilikList = new ArrayList<Hakmilik>();
        accList = new ArrayList<Akaun>();
        senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        amaunLebihList = new ArrayList<String>();
        tarikhCek = new ArrayList<String>();
        kumpulanAkaun = new KumpulanAkaun();
        idHakmilikSiriDari = "";
        idHakmilikSiriKe = "";
        bakiFlag = false;
//        return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
        return new RedirectResolution(PermohonanKaunter2.class);
    }
    @Inject
//    GeneratorNoResit noResitGenerator;
    private GeneratorNoResit2 noResitGenerator2;

    @Inject
    CaraBayarService caraBayarService;

    @Inject
    PenyataPemungutService pp;

    public void simpan2() throws ParseException {
        // TODO validate amounts
        ArrayList arr = new ArrayList();
        DokumenKewangan dk = null;

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        pguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        tahun = Integer.parseInt(yy.format(now));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        BigDecimal rm = jumCaraBayar;
        Akaun akn = new Akaun();
        String ac = "";
        String noAkaun = "";
        String resit = null;
        boolean rem = false;
        BigDecimal amaunRem = new BigDecimal(0);

        ArrayList<DokumenKewanganBayaran> listAdkb = new ArrayList<DokumenKewanganBayaran>();
        for (int m = 0; m < hakmilikList.size(); m++) {
            BigDecimal amnt = new BigDecimal(0);
            BigDecimal amnt1 = new BigDecimal(0);

            Hakmilik h = hakmilikList.get(m);
            String[] name = {"hakmilik"};
            Object[] value = {h};
            List<Akaun> aList = accDAO.findByEqualCriterias(name, value, null);
            List<Akaun> akaunList = new ArrayList<Akaun>();
            List<Transaksi> listT = new ArrayList<Transaksi>();
            for (Akaun ak : aList) {
                if ((ak.getKodAkaun().getKod().equals("AC")) && (ak.getStatus().getKod().equals("A"))) {
//                    List<Transaksi> temp = ak.getSemuaTransaksi();
//                    listT.addAll(temp);
                    noAkaun = ak.getNoAkaun();
                    akaunList.add(ak);
                }
            }

            String query = "SELECT tr FROM etanah.model.Transaksi tr where ((tr.akaunDebit.noAkaun =:noAkaun OR tr.akaunKredit.noAkaun = :noAkaun "
                    + "AND tr.status.kod ='A')) ORDER BY tr.kodTransaksi.keutamaan";
//                    "SELECT t FROM etanah.model.Transaksi t where t.akaunKredit.noAkaun = :noAkaun OR t.akaunDebit.noAkaun = :noAkaun";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("noAkaun", noAkaun);
            List<Transaksi> listTr = (List<Transaksi>) q.list();

            Akaun k = accDAO.findById(noAkaun);
            BigDecimal amt = new BigDecimal(0);
            for (Transaksi tr : listTr) {
                if (tr.getAmaun().doubleValue() > 0) {
                    if ((tr.getAkaunKredit() != null) && (tr.getAkaunKredit().getKodAkaun().getKod().equals("AKH"))) {
                        continue;
                    } else {
                        amt = amt.add(tr.getAmaun());
                    }
                }
                if (tr.getAkaunKredit() == null) {
                    listT.add(tr);
                }
                if (tr.getKodTransaksi().getKod().equals("99000") || tr.getKodTransaksi().getKod().equals("99001")
                        || tr.getKodTransaksi().getKod().equals("99002") || tr.getKodTransaksi().getKod().equals("99003") || tr.getKodTransaksi().getKod().equals("99030")) {
                    rem = true;
                    amaunRem = tr.getAmaun();
                    amt = amt.subtract(tr.getAmaun());
                }
            }

//            resit = noResitGenerator.generateNoResit(ctx.getKodNegeri(), caw , pengguna);
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
            dk.setIdKaunter(pengguna.getIdKaunter());
            // save cara bayaran
            BigDecimal bg = new BigDecimal(0);
            kewDokID.add(resit);

            ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
            adkb = caraBayarService.saveCaraBayaran(senaraiCaraBayaran, tarikhCek, ia, pengguna, noAkaun, bakiFlag, bg, dk, adkb, jumlahCaj);
            listAdkb = adkb;
            String tmp = dk.getIdDokumenKewangan();
            idKewDoc = dk.getIdDokumenKewangan();
            arr.add(tmp);
            dk.setIsuKepada((accDAO.findById(noAkaun).getPemegang().getNama()));
            dk.setSenaraiBayaran(adkb);
            dk.setAkaun(accDAO.findById(noAkaun));

            mod = modKutip.loadPenyerahFromSession(ctx);
            if (mod.equalsIgnoreCase("Lewat")) {
                dk.setMod(kodKutipanDAO.findById('L'));
            }
            if (mod.equalsIgnoreCase("Biasa")) {
                dk.setMod(kodKutipanDAO.findById('B'));
            }
            if (bakiFlag == true) {
                Akaun x = accDAO.findById(noAkaun);
                dk.setAmaunBayaran(x.getBaki());
            }
            if (bakiFlag == false) {
                dk.setAmaunBayaran(jumCaraBayar);
            }
            dk.setTarikhTransaksi(now);
            manager.saveOrUpdate(dk);

            //update akaunCukai Tanah
            BigDecimal tot = new BigDecimal(0);
//            for (int x = 0; x < akaunList.size(); x++) {
//                akn = aList.get(x);
            tot = k.getBaki().subtract(jumCaraBayar);
            if (bakiFlag == true) {
                k.setBaki(new BigDecimal(0));
            } else {
                k.setBaki(tot);
            }
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(now);
            k.setInfoAudit(ia);
            manager.saveOrUpdate(k);
//            }

            //update Akaun Kutipan Harian
            kodAkaun = kodAkaunDAO.findById("AKH");
            String[] n1 = {"kodAkaun"};
            Object[] v1 = {getKodAkaun()};
            List<Akaun> accountList = accDAO.findByEqualCriterias(n1, v1, null);
            for (Akaun acnt : accountList) {
                if (acnt.getCawangan().getKod().equals(caw.getKod())) {
                    ac = acnt.getNoAkaun();
                }
            }
            Akaun acc = accDAO.findById(ac);
            BigDecimal bal = new BigDecimal(0);
            if (bakiFlag) {
                bal = acc.getBaki().add(bg);
            } else {
                bal = acc.getBaki().add(jumCaraBayar);
            }
            acc.setBaki(bal);
            manager.saveOrUpdate(acc);
            BigDecimal totalTransList = new BigDecimal(0);
            ArrayList array = new ArrayList();
            List<Transaksi> senaraiTransaksiA = new ArrayList<Transaksi>();
            int count = 0;
            for (Transaksi t : listT) {
                count++;
                String a = t.getKodTransaksi().getKod();
                array.add(a);
                if (t.getKodTransaksi().getKod().equals("99000") || t.getKodTransaksi().getKod().equals("99001")
                        || t.getKodTransaksi().getKod().equals("99002") || t.getKodTransaksi().getKod().equals("99003") || t.getKodTransaksi().getKod().equals("99030")) {
                    rem = true;
                    amaunRem = t.getAmaun();
                }
                if (t.getStatus().getKod() == 'A') {
                    if ((t.getAkaunDebit() != null) && (t.getAkaunDebit().getNoAkaun().equals(noAkaun))) {
                        totalTransList = totalTransList.add(t.getAmaun());
                    } else {
                        totalTransList = totalTransList.subtract(t.getAmaun());
                    }
                    senaraiTransaksiA.add(t);
                }
            }
            List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
            //update transaction of Cukai Tanah
            if ((jumlahCaj.doubleValue() > 0.0) && (jumlahCaj.doubleValue() < totalTransList.doubleValue())) {
                //        Transaksi Kredit
                Query qTransKredit = sessionProvider.get().createQuery("SELECT t FROM etanah.model.Transaksi t where t.akaunKredit.noAkaun = :noAkaun AND t.status.kod = 'T' "
                        + "order by t.kodTransaksi.keutamaan");
                qTransKredit.setString("noAkaun", k.getNoAkaun());
                List<Transaksi> senaraiTransaksiKredit = qTransKredit.list();
                //        Transaksi Debit
                Query qTransDebit = sessionProvider.get().createQuery("SELECT t FROM etanah.model.Transaksi t where t.akaunDebit.noAkaun = :noAkaun AND t.status.kod = 'A' "
                        + "order by t.kodTransaksi.keutamaan");
                qTransDebit.setString("noAkaun", k.getNoAkaun());
                List<Transaksi> senaraiTransaksiDebit = qTransDebit.list();

                if (bakiFlag) {
                    returnBalance = tot.multiply(new BigDecimal(-1));
                    hasilService.bayaranKedua(senaraiTransaksiDebit, senaraiTransaksiKredit, pengguna, ia, acc, now, dk, tahun, k, jumlahCaj);
                } else {
                    returnBalance = BigDecimal.ZERO;
                    hasilService.bayaranKedua(senaraiTransaksiDebit, senaraiTransaksiKredit, pengguna, ia, acc, now, dk, tahun, k, jumCaraBayar);
                }
            } else if (((jumlahCaj.doubleValue() > 0.0) && (jumlahCaj.compareTo(jumCaraBayar) == 1))) {
                hasilService.bayarKurang(senaraiTransaksiA, pengguna, caw, dk, acc, account, jumCaraBayar);
            } else if (amt.compareTo(totalTransList) == 0) {
                if (jumlahCaj.doubleValue() <= 0.0) {
                    bayarSemula(pengguna, caw, dk, acc, account, jumCaraBayar);
                }
                if (((jumlahCaj.doubleValue() > 0.0) && (jumlahCaj.compareTo(jumCaraBayar) <= 0))) {
                    if (rem) {
                        for (int e = 0; e < listT.size(); e++) {
                            Akaun a = accDAO.findById(noAkaun);
                            Transaksi tr = listT.get(e);
                            if ((tr.getStatus().getKod() == 'A')) {
                                if ((tr.getKodTransaksi().getKod().equals("99000")) || (tr.getKodTransaksi().getKod().equals("99001"))
                                        || (tr.getKodTransaksi().getKod().equals("99002")) || (tr.getKodTransaksi().getKod().equals("99003")) || (tr.getKodTransaksi().getKod().equals("99030"))) {
                                } else {
                                    if (tr.getKodTransaksi().getKod().equals("76152")) {  //for NS
                                        //                            if(tr.getKodTransaksi().getKod().equals("61402")){  //for Melaka
                                        if ((tr.getAkaunKredit() != null) && (tr.getAkaunKredit().getNoAkaun().equals(noAkaun))) {
                                            tr.setAmaun(tr.getAmaun().multiply(new BigDecimal(-1)));
                                        }
                                        senaraiTransaksi.add(tr);
                                    } else {
                                        Transaksi trans = new Transaksi();
                                        trans.setCawangan(caw);
                                        trans.setKodTransaksi(tr.getKodTransaksi());
                                        if (trans.getKodTransaksi().getKod().equals("61401")) {
                                            trans.setAmaun(tr.getAmaun().subtract(amaunRem));
                                        } else {
                                            trans.setAmaun(tr.getAmaun());
                                        }
                                        trans.setDokumenKewangan(dk);
                                        trans.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                                        trans.setUntukTahun(tr.getUntukTahun());
                                        trans.setTahunKewangan(year);
                                        trans.setBayaranAgensi("N");
                                        ia.setDimasukOleh(pengguna);
                                        ia.setTarikhMasuk(now);
                                        trans.setInfoAudit(ia);
                                        if ((tr.getAkaunKredit() != null) && (tr.getAkaunDebit() != null)) {
                                            trans.setAkaunDebit(a);
                                        } else {
                                            trans.setAkaunDebit(acc);
                                        }
                                        if ((tr.getAkaunDebit() != null) && (tr.getAkaunDebit().getNoAkaun().equals(noAkaun))) {
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
                    } else {
                        for (int e = 0; e < listT.size(); e++) {
                            Akaun a = accDAO.findById(noAkaun);
                            Transaksi tr = listT.get(e);
                            if (tr.getStatus().getKod() == 'A') {
                                Transaksi trans = new Transaksi();
                                trans.setCawangan(caw);
                                trans.setKodTransaksi(tr.getKodTransaksi());
                                trans.setAmaun(tr.getAmaun());
                                trans.setDokumenKewangan(dk);
                                trans.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                                trans.setUntukTahun(tr.getUntukTahun());
                                trans.setTahunKewangan(year);
                                trans.setBayaranAgensi("N");
                                ia.setDimasukOleh(pengguna);
                                ia.setTarikhMasuk(now);
                                trans.setInfoAudit(ia);
                                if ((tr.getAkaunKredit() != null) && (tr.getAkaunDebit() != null)) {
                                    trans.setAkaunDebit(a);
                                } else {
                                    trans.setAkaunDebit(acc);
                                }
                                if ((tr.getAkaunDebit() != null) && (tr.getAkaunDebit().getNoAkaun().equals(noAkaun))) {
                                    trans.setAkaunKredit(a);
                                }
                                manager.save(trans);
                            }
                            if (tr.getAmaun().doubleValue() > 0) {
                                amnt = amnt.add(tr.getAmaun());
                            } else if (tr.getAmaun().doubleValue() < 0) {
                                amnt1 = amnt1.add(tr.getAmaun());
                            }
                        }
                    }
                    BigDecimal jumDenda = new BigDecimal(0);
                    for (Transaksi tr : senaraiTransaksi) {
                        jumDenda = jumDenda.add(tr.getAmaun());
                    }
                    String kodTransaksi = "";

                    if (bakiFlag) {
                        returnBalance = tot.multiply(new BigDecimal(-1));
                    } else {
                        returnBalance = BigDecimal.ZERO;
                    }

                    if (tot.doubleValue() < 0) {
                        if (bakiFlag == false) {
                            bayaranLebih(tot, caw, dk, pengguna, now, ia, year, acc, account);
                        }
                    }
                }
            }
        }
        DokumenKewangan dok = dokumenKewanganDAO.findById(resit);
        listHakmilik = "and kdok.id_kew_dok in ('" + dok.getIdDokumenKewangan() + "')";
        pp.savePenyataPemungut(dok);
    }

    public void bayaranLebih(BigDecimal tot, KodCawangan caw, DokumenKewangan dk, Pengguna pengguna, Date now, InfoAudit ia, int year, Akaun acc, String account) {
        if (bakiFlag == false) {
            Transaksi t = new Transaksi();
            akaun = new Akaun();
            akaun.setNoAkaun(account);
            t.setAkaunKredit(akaun);
            t.setAkaunDebit(acc);
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
            t.setPerihal("L");
            t.setUntukTahun(year + 1);
            t.setTahunKewangan(year);
            t.setBayaranAgensi("N");
            manager.save(t);
        }
    }

    public void bayarSemula(Pengguna pengguna, KodCawangan caw, DokumenKewangan dk, Akaun acc, String account, BigDecimal jumCaraBayar) {
        Transaksi t = new Transaksi();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        akaun = accDAO.findById(account);
//        akaun.setNoAkaun(account);
        t.setAkaunKredit(akaun);
        t.setAkaunDebit(acc);
        t.setAmaun(jumCaraBayar);
        t.setCawangan(caw);
        t.setDokumenKewangan(dokumenKewanganDAO.findById(dk.getIdDokumenKewangan()));
        KodTransaksi kt = new KodTransaksi();
        if (StringUtils.isBlank(t.getAkaunKredit().getHakmilik().getIdHakmilikInduk())) {
            kt.setKod(hasil.getProperty("cukaiTanahSemasa"));

        } else {
            kt.setKod(hasil.getProperty("cukaiTanahSemasaStrata"));

        }
        t.setKodTransaksi(kt);
        t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        t.setInfoAudit(ia);
        t.setUntukTahun(year);
        t.setTahunKewangan(year);
        t.setBayaranAgensi("N");
        manager.save(t);
    }

    public void simpan3() throws ParseException {
        // TODO validate amounts
        ArrayList arr = new ArrayList();
        DokumenKewangan dk = null;
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser(); //penggunaDAO.findById("admin");
        pguna = pengguna;
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        Akaun akn = new Akaun();
        BigDecimal amnt = jumCaraBayar;
        BigDecimal ac = new BigDecimal(0);
        BigDecimal amnt1 = new BigDecimal(0);
        String acc = "";
        BigDecimal totalAmaun = new BigDecimal(0);
        BigDecimal byr = jumCaraBayar;
        List<DokumenKewangan> senaraiDK = new ArrayList<DokumenKewangan>();

        String[] temp1;
        if (amn != null) {
            temp1 = amn.split(",");
            for (String s : temp1) {
                amaunLebihList.add(s);
            }
        }
        KodKutipan kodKutip = new KodKutipan();
        mod = modKutip.loadPenyerahFromSession(ctx);
        if (mod.equalsIgnoreCase("Lewat")) {
            kodKutip = kodKutipanDAO.findById('L');
        }
        if (mod.equalsIgnoreCase("Biasa")) {
            kodKutip = kodKutipanDAO.findById('B');
        }
        KodDokumen kd = kodDokumenDAO.findById("RBY");
        KodStatusDokumenKewangan statusKD = kodStatusDokumenKewanganDAO.findById("A");
        KodStatusTransaksiKewangan statusTrans = kodStatusTransaksiKewanganDAO.findById('T');

        /**
         * *** UPDATE Akaun Kutipan Harian START ****
         */
        String akaunAKH = "SELECT a FROM etanah.model.Akaun a WHERE a.kodAkaun = 'AKH' AND a.cawangan.kod =:kodCaw";
        Query qAKH = sessionProvider.get().createQuery(akaunAKH);
        qAKH.setString("kodCaw", caw.getKod());
        Akaun a = (Akaun) qAKH.uniqueResult();
        BigDecimal bal = new BigDecimal(0);
        if (bakiFlag) {
            bal = a.getBaki().add(jumlahCaj);
        } else {
            bal = a.getBaki().add(jumCaraBayar);
        }
        a.setBaki(bal);
        manager.saveOrUpdate(a);
        hakmilikList = dummyHList;
        /**
         * *** UPDATE Akaun Kutipan Harian FINISH****
         */
        String strHakmilik = "";

        for (int m = 0; m < hakmilikList.size(); m++) {
            Hakmilik h = hakmilikList.get(m);
            String noAkaun = "";
            boolean fLebih = false;
            BigDecimal bakiAwal = new BigDecimal(0);
            BigDecimal bakiDKAwal = new BigDecimal(0);
            List<Transaksi> listT = new ArrayList<Transaksi>();
            Akaun k = new Akaun();

            String hmQuery = "SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :idHakmilik AND a.kodAkaun.kod = 'AC' AND a.status.kod='A'";
            Query hmQ = sessionProvider.get().createQuery(hmQuery);
            hmQ.setString("idHakmilik", h.getIdHakmilik());
            k = (Akaun) hmQ.uniqueResult();
            ac = k.getBaki();
            noAkaun = k.getNoAkaun();

            String query = "SELECT tr FROM etanah.model.Transaksi tr where ((tr.akaunDebit.noAkaun =:noAkaun "
                    + "AND tr.status.kod ='A')) ORDER BY tr.kodTransaksi.keutamaan";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("noAkaun", noAkaun);
            List<Transaksi> listTr = (List<Transaksi>) q.list();

            for (Transaksi tr : listTr) {
                if (tr.getAkaunKredit() == null) {
                    listT.add(tr);
                }
            }
            String lbh = null;
            if (amaunLebihList.size() > 0) {
                lbh = amaunLebihList.get(m);
            }
            if ((k.getBaki().compareTo(new BigDecimal(0)) <= 0) && (lbh == null)) {
                continue;
            } else {
                BigDecimal bakiAkaun = k.getBaki();
                bakiAwal = k.getBaki();
                totalAmaun = totalAmaun.add(k.getBaki());
//                String resit = noResitGenerator.generateNoResit(ctx.getKodNegeri(), caw, pengguna);
                String resit = noResitGenerator2.getAndLockSerialNo(pengguna);
                // resit
                dk = new DokumenKewangan();
                dk.setIdDokumenKewangan(resit);
                // set idResit to pageContext
                ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
                dk.setAmaunBayaran(ac);
                dk.setKodDokumen(kd);
                dk.setStatus(statusKD);
                dk.setInfoAudit(ia);
                dk.setCawangan(caw);
                dk.setIdKaunter(pengguna.getIdKaunter());
                BigDecimal bg = new BigDecimal(0);
                // save cara bayaran
                ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
                adkb = caraBayarService.saveCaraBayaran(senaraiCaraBayaran, tarikhCek, ia, pengguna, noAkaun, bakiFlag, bg, dk, adkb, jumlahCaj);

                String tmp = dk.getIdDokumenKewangan();
                DokumenKewangan kewDokumen = dk;
                kewDokList.add(kewDokumen);
                kewDokID.add(tmp);
                idKewDoc = dk.getIdDokumenKewangan();
                arr.add(tmp);
                dk.setSenaraiBayaran(adkb);
                dk.setMod(kodKutip);
                if (bakiFlag == true) {
                    Akaun x = accDAO.findById(noAkaun);
                    dk.setAmaunBayaran(x.getBaki());
                }
                if (bakiFlag == false) {
                    if (byr.compareTo(k.getBaki()) == 1) {
                        if ((k.getBaki().compareTo(new BigDecimal(0)) <= 0) && (lbh != null)) {
                            fLebih = true;
                            double aa = Double.parseDouble(lbh);
                            BigDecimal lebih = BigDecimal.valueOf(aa);
                            dk.setAmaunBayaran(lebih);
                        } else {
                            dk.setAmaunBayaran(k.getBaki());
                        }
                    } else {
                        dk.setAmaunBayaran(byr);
                    }
                }
                dk.setAkaun(k);
                dk.setTarikhTransaksi(now);
                dk.setIsuKepada(k.getPemegang().getNama());
                manager.saveOrUpdate(dk);

                /**
                 * *** UPDATE Baki Akaun START ****
                 */
                BigDecimal tot = new BigDecimal(0);
                if (bakiFlag == true) {
                    k.setBaki(new BigDecimal(0));
                } else {
                    if ((m == (hakmilikList.size() - 1)) && (jumCaraBayar.doubleValue() < jumlahCaj.doubleValue())) {
                        k.setBaki(totalAmaun.subtract(jumCaraBayar));
                    } else {
                        k.setBaki(k.getBaki().subtract(dk.getAmaunBayaran()));
                    }
                }
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(now);
                k.setInfoAudit(ia);
                manager.saveOrUpdate(k);
                /**
                 * *** UPDATE Baki Akaun FINISH ****
                 */

                BigDecimal bd = new BigDecimal(0);
                BigDecimal amt = new BigDecimal(0);
                boolean rem = false;
                BigDecimal amaunRem = new BigDecimal(0);
                for (int e = 0; e < listT.size(); e++) {
                    Transaksi tr = listT.get(e);
                    if (tr.getAmaun().doubleValue() > 0) {
                        amt = amt.add(tr.getAmaun());
                    }
                    if (tr.getKodTransaksi().getKod().equals("99000") || tr.getKodTransaksi().getKod().equals("99001")
                            || tr.getKodTransaksi().getKod().equals("99002") || tr.getKodTransaksi().getKod().equals("99003") || tr.getKodTransaksi().getKod().equals("99030")) {
                        rem = true;
                        amaunRem = tr.getAmaun();
                    }
//                    if(tr.getStatus().getKod() == 'A'){
//                        bd = bd.add(tr.getAmaun());
//                    }
                    if (tr.getStatus().getKod() == 'A') {
                        if ((tr.getAkaunDebit() != null) && (tr.getAkaunDebit().getNoAkaun().equals(noAkaun))) {
                            bd = bd.add(tr.getAmaun());
                        } else {
                            bd = bd.subtract(tr.getAmaun());
                        }
                    }
                }
                List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if ((bakiAwal.doubleValue() > 0.0) && (bakiAwal.doubleValue() < bd.doubleValue())) {
                    //        Transaksi Kredit
                    Query qTransKredit = sessionProvider.get().createQuery("SELECT t FROM etanah.model.Transaksi t where t.akaunKredit.noAkaun = :noAkaun AND t.status.kod = 'T' "
                            + "order by t.kodTransaksi.keutamaan");
                    qTransKredit.setString("noAkaun", noAkaun);
                    List<Transaksi> senaraiTransaksiKredit = qTransKredit.list();
                    //        Transaksi Debit
                    Query qTransDebit = sessionProvider.get().createQuery("SELECT t FROM etanah.model.Transaksi t where t.akaunDebit.noAkaun = :noAkaun AND t.status.kod = 'A' "
                            + "order by t.kodTransaksi.keutamaan");
                    qTransDebit.setString("noAkaun", noAkaun);
                    List<Transaksi> senaraiTransaksiDebit = qTransDebit.list();

                    hasilService.bayaranKedua(senaraiTransaksiDebit, senaraiTransaksiKredit, pengguna, ia, a, now, dk, tahun, k, bakiAwal);
                } /**
                 * *** Bayaran Kurang ****
                 */
                else if ((m == (hakmilikList.size() - 1)) && (jumCaraBayar.doubleValue() < jumlahCaj.doubleValue())) {
                    hasilService.bayarKurang1(listT, pengguna, caw, dk, a, account, byr, year, ia);
                } else if (bakiAwal.doubleValue() == bd.doubleValue()) {
                    //            else{
                    if (rem == false) {
                        if (bakiAkaun.compareTo(new BigDecimal(0)) == 1) {
                            for (int e = 0; e < listT.size(); e++) {
                                Akaun accnt = k;
                                Transaksi tr = listT.get(e);
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
                                    trans.setBayaranAgensi("N");
                                    ia.setDimasukOleh(pengguna);
                                    ia.setTarikhMasuk(now);
                                    trans.setInfoAudit(ia);
                                    if ((tr.getAkaunKredit() != null) && (tr.getAkaunDebit() != null)) {
                                        trans.setAkaunDebit(accnt);
                                    } else {
                                        trans.setAkaunDebit(a);
                                    }
                                    if ((tr.getAkaunDebit() != null) && (tr.getAkaunDebit().getNoAkaun().equals(noAkaun))) {
                                        trans.setAkaunKredit(accnt);
                                    }
                                    manager.save(trans);
                                    //                            }
                                }
                            }
                        }
                    }

                    if (rem == true) {
                        for (int e = 0; e < listT.size(); e++) {
                            Akaun ak = k;
                            Transaksi tr = listT.get(e);
                            if ((tr.getStatus().getKod() != 'B')) {
                                if ((tr.getKodTransaksi().getKod().equals("99000")) || (tr.getKodTransaksi().getKod().equals("99001"))
                                        || (tr.getKodTransaksi().getKod().equals("99002")) || (tr.getKodTransaksi().getKod().equals("99003")) || tr.getKodTransaksi().getKod().equals("99030")) {
                                } else {
                                    if (tr.getKodTransaksi().getKod().equals("76152")) {      // for NS
                                        //                                if(tr.getKodTransaksi().getKod().equals("61402")){      // for Melaka
                                        if ((tr.getAkaunKredit() != null) && (tr.getAkaunKredit().getNoAkaun().equals(noAkaun))) {
                                            tr.setAmaun(tr.getAmaun().multiply(new BigDecimal(-1)));
                                        }
                                        senaraiTransaksi.add(tr);
                                    } else {
                                        Transaksi trans = new Transaksi();
                                        trans.setCawangan(caw);
                                        trans.setKodTransaksi(tr.getKodTransaksi());
                                        if (trans.getKodTransaksi().getKod().equals("61401")) {
                                            trans.setAmaun(tr.getAmaun().subtract(amaunRem));
                                        } else {
                                            trans.setAmaun(tr.getAmaun());
                                        }
                                        trans.setDokumenKewangan(dk);
                                        trans.setStatus(statusTrans);
                                        trans.setUntukTahun(tr.getUntukTahun());
                                        trans.setTahunKewangan(year);
                                        ia.setDimasukOleh(pengguna);
                                        ia.setTarikhMasuk(now);
                                        trans.setBayaranAgensi("N");
                                        trans.setInfoAudit(ia);
                                        if (trans.getKodTransaksi().getKod().equals("61401")) {
                                            trans.setAkaunDebit(a);
                                        }
                                        if ((tr.getAkaunKredit() != null) && (tr.getAkaunDebit() != null)) {
                                            trans.setAkaunDebit(ak);
                                        }
                                        if ((tr.getAkaunDebit() != null) && (tr.getAkaunDebit().getNoAkaun().equals(noAkaun))) {
                                            trans.setAkaunKredit(ak);
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

                    BigDecimal jumDenda = new BigDecimal(0);
                    for (Transaksi tr : senaraiTransaksi) {
                        jumDenda = jumDenda.add(tr.getAmaun());
                    }
                    String kodTransaksi = "";
                    BigDecimal tots = new BigDecimal(0);
                    tots = jumCaraBayar.subtract(jumlahCaj);
                    returnBalance = tots;
                }
                if ("04".equals(conf.getProperty("kodNegeri"))) {
//                    String[] n = {"hakmilik"};
//                    Object[] v = {h};
//                    List<Akaun> listAkaun = accDAO.findByEqualCriterias(n, v, null);
//                    String nomborAkaun = "";
//                    for (Akaun an : listAkaun) {
//                        if (an.getKodAkaun().getKod().equals("AC")) {
//                            nomborAkaun = an.getNoAkaun();
//                        }
//                    }
//                    if (jumCaraBayar.compareTo(jumlahCaj) == 1) {
//                        if (bakiFlag == false) {
//                            String a1 = amaunLebihList.get(m);
//                            double aa = Double.parseDouble(a1);
//                            BigDecimal lebih = BigDecimal.valueOf(aa);
//                            Long id = 0L;
//
//                            if(lebih.compareTo(new BigDecimal(0))>0){
//                                Long idtrans = bayarLebih(caw, pengguna, dk, a, k, lebih, fLebih);
//                            }
//                        }
//                    }
                    if ((jumCaraBayar.compareTo(jumlahCaj) == 1) && (amaunLebihList.size() > 0)) {
                        if (bakiFlag == false) {
                            String a1 = amaunLebihList.get(m);
                            double aa = Double.parseDouble(a1);
                            BigDecimal lebih = BigDecimal.valueOf(aa);
                            Long id = 0L;

                            if (lebih.compareTo(new BigDecimal(0)) > 0) {
                                if (!fLebih) {
                                    Akaun akn1 = k;
                                    akn1.setBaki(akn1.getBaki().subtract(lebih));
                                    manager.saveAkaun(akn1);
                                }

                                Long idtrans = bayarLebih(caw, pengguna, dk, a, k, lebih, fLebih);
                                // caw; pguna; dk; akh, akaun
                                String query1 = "SELECT tr FROM etanah.model.Transaksi tr where tr.idTransaksi =:noAkaun ";
                                Query q1 = sessionProvider.get().createQuery(query1);
                                q1.setLong("noAkaun", idtrans);
                                Transaksi tt = (Transaksi) q1.uniqueResult();
                                if (tt != null) {
                                }
                            }
                        }
                    }
                }
                if ("05".equals(conf.getProperty("kodNegeri"))) {
                    if ((jumCaraBayar.compareTo(jumlahCaj) == 1) && (amaunLebihList.size() > 0)) {
                        if (bakiFlag == false) {
                            String a1 = amaunLebihList.get(m);
                            double aa = Double.parseDouble(a1);
                            BigDecimal lebih = BigDecimal.valueOf(aa);
                            Long id = 0L;

                            if (lebih.compareTo(new BigDecimal(0)) > 0) {
                                if (!fLebih) {
                                    Akaun akn1 = k;
                                    akn1.setBaki(akn1.getBaki().subtract(lebih));
                                    manager.saveAkaun(akn1);
                                }

                                Long idtrans = bayarLebih(caw, pengguna, dk, a, k, lebih, fLebih);
                                // caw; pguna; dk; akh, akaun
                                String query1 = "SELECT tr FROM etanah.model.Transaksi tr where tr.idTransaksi =:noAkaun ";
                                Query q1 = sessionProvider.get().createQuery(query1);
                                q1.setLong("noAkaun", idtrans);
                                Transaksi tt = (Transaksi) q1.uniqueResult();
                                if (tt != null) {
                                }
                            }
                        }
                    }
                }

                byr = byr.subtract(bakiAwal);
                senaraiDK.add(dk);
            }

            if (m == 0) {
                rst = dk.getIdDokumenKewangan();
                strHakmilik = "'" + dk.getIdDokumenKewangan() + "'";
            } else {
                rst = rst + "," + dk.getIdDokumenKewangan();
                strHakmilik = strHakmilik + ",'" + dk.getIdDokumenKewangan() + "'";
            }
        }

        listHakmilik = "and kdok.id_kew_dok in (" + strHakmilik + ")";
        pp.savePenyataPemungut1(senaraiDK, dk);
    }

    public Long bayarLebih(KodCawangan caw, Pengguna pengguna, DokumenKewangan dk, Akaun akh, Akaun ak, BigDecimal lebih, boolean fLebih) {
        Long id = 0L;
        BigDecimal l = new BigDecimal(0);
        l = lebih;

        //set akaun START
//        Akaun akn = ak;
//        LOG.info(")))))))))))))))))))) : "+akn.getBaki());
//        akn.setBaki(akn.getBaki().subtract(lebih));
//        manager.saveAkaun(akn);
        //set akaun FINISH
        Transaksi t = new Transaksi();
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        t.setAkaunKredit(ak);
        t.setAkaunDebit(akh);
        t.setAmaun(l);
        t.setCawangan(caw);
        t.setDokumenKewangan(dk);
        KodTransaksi kt = new KodTransaksi();
        kt.setKod(hasil.getProperty("cukaiTanahSemasa"));     //for NS
        t.setKodTransaksi(kt);
        t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        t.setInfoAudit(ia);
        t.setUntukTahun(year + 1);
        t.setPerihal("L");
        t.setTahunKewangan(year);
        t.setBayaranAgensi("N");
        manager.save(t);
        id = t.getIdTransaksi();

        /**
         * *** UPDATE kew_dokumen Column amaun_byr ****
         */
        if (fLebih) {
            LOG.info(fLebih);
        } else {
            LOG.info("SANA");
            dk.setAmaunBayaran(dk.getAmaunBayaran().add(lebih));
        }
        manager.update(dk);
        return id;
    }


    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public boolean isFlag1() {
        return flag1;
    }

    public void setFlag1(boolean flag1) {
        this.flag1 = flag1;
    }

    public BigDecimal getJumlahCb() {
        return jumlahCb;
    }

    public void setJumlahCb(BigDecimal jumlahCb) {
        this.jumlahCb = jumlahCb;
    }

    public String getIdDokumenKewangan() {
        return idDokumenKewangan;
    }

    public void setIdDokumenKewangan(String idDokumenKewangan) {
        this.idDokumenKewangan = idDokumenKewangan;
    }

    public KodStatusDokumenKewangan getStatus() {
        return status;
    }

    public void setStatus(KodStatusDokumenKewangan status) {
        this.status = status;
    }

    public String getKodBatal() {
        return kodBatal;
    }

    public void setKodBatal(String kodBatal) {
        this.kodBatal = kodBatal;
    }

    public Date getTarikh() {
        return tarikh;
    }

    public void setTarikh(Date tarikh) {
        this.tarikh = tarikh;
    }

    public BigDecimal getJumlahCaj1() {
        return jumlahCaj1;
    }

    public void setJumlahCaj1(BigDecimal jumlahCaj1) {
        this.jumlahCaj1 = jumlahCaj1;
    }

    public List<Akaun> getAkaunList() {
        return akaunList;
    }

    public void setAkaunList(List<Akaun> akaunList) {
        this.akaunList = akaunList;
    }

    public ArrayList getKewDokID() {
        return kewDokID;
    }

    public void setKewDokID(ArrayList kewDokID) {
        this.kewDokID = kewDokID;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public ArrayList<DokumenKewangan> getKewDokList() {
        return kewDokList;
    }

    public void setKewDokList(ArrayList<DokumenKewangan> kewDokList) {
        this.kewDokList = kewDokList;
    }

    public List<String> getTarikhCek() {
        return tarikhCek;
    }

    public void setTarikhCek(List<String> tarikhCek) {
        this.tarikhCek = tarikhCek;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public List<Akaun> getDummyList() {
        return dummyList;
    }

    public void setDummyList(List<Akaun> dummyList) {
        this.dummyList = dummyList;
    }

    public String getIdHmBalance() {
        return idHmBalance;
    }

    public void setIdHmBalance(String idHmBalance) {
        this.idHmBalance = idHmBalance;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Akaun getAkaunKredit() {
        return akaunKredit;
    }

    public void setAkaunKredit(Akaun akaunKredit) {
        this.akaunKredit = akaunKredit;
    }

    /**
     * @return the tarikhBatal
     */
    public String getTarikhBatal() {
        return tarikhBatal;
    }

    /**
     * @param tarikhBatal the tarikhBatal to set
     */
    public void setTarikhBatal(String tarikhBatal) {
        this.tarikhBatal = tarikhBatal;
    }

    public String getNoAkaunSiriDari() {
        return noAkaunSiriDari;
    }

    public void setNoAkaunSiriDari(String noAkaunSiriDari) {
        this.noAkaunSiriDari = noAkaunSiriDari;
    }

    public String getNoAkaunSiriKe() {
        return noAkaunSiriKe;
    }

    public void setNoAkaunSiriKe(String noAkaunSiriKe) {
        this.noAkaunSiriKe = noAkaunSiriKe;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    /**
     * @return the permohonan
     */
    public Permohonan getPermohonan() {
        return permohonan;
    }

    /**
     * @param permohonan the permohonan to set
     */
    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    /**
     * @return the IdPermohonan
     */
    public String getIdPermohonan() {
        return IdPermohonan;
    }

    /**
     * @param IdPermohonan the IdPermohonan to set
     */
    public void setIdPermohonan(String IdPermohonan) {
        this.IdPermohonan = IdPermohonan;
    }

    public boolean isPtg() {
        return ptg;
    }

    public void setPtg(boolean ptg) {
        this.ptg = ptg;
    }

    public BigDecimal getReturnBalance() {
        return returnBalance;
    }

    public void setReturnBalance(BigDecimal returnBalance) {
        this.returnBalance = returnBalance;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public boolean isBakiFlag() {
        return bakiFlag;
    }

    public void setBakiFlag(boolean bakiFlag) {
        this.bakiFlag = bakiFlag;
    }

    public int getBilList() {
        return bilList;
    }

    public void setBilList(int bilList) {
        this.bilList = bilList;
    }

    public BigDecimal getAmaunKurang() {
        return amaunKurang;
    }

    public void setAmaunKurang(BigDecimal amaunKurang) {
        this.amaunKurang = amaunKurang;
    }

    public BigDecimal getAmaunLebih() {
        return amaunLebih;
    }

    public void setAmaunLebih(BigDecimal amaunLebih) {
        this.amaunLebih = amaunLebih;
    }

    public BigDecimal getCukaiTahunDepan() {
        return cukaiTahunDepan;
    }

    public void setCukaiTahunDepan(BigDecimal cukaiTahunDepan) {
        this.cukaiTahunDepan = cukaiTahunDepan;
    }

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public BigDecimal getTunggakan() {
        return tunggakan;
    }

    public void setTunggakan(BigDecimal tunggakan) {
        this.tunggakan = tunggakan;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public ArrayList<Hakmilik> getDummyHList() {
        return dummyHList;
    }

    public void setDummyHList(ArrayList<Hakmilik> dummyHList) {
        this.dummyHList = dummyHList;
    }

    public KumpulanAkaun getKumpulanAkaun() {
        return kumpulanAkaun;
    }

    public void setKumpulanAkaun(KumpulanAkaun kumpulanAkaun) {
        this.kumpulanAkaun = kumpulanAkaun;
    }

    public boolean isHapusBtn() {
        return hapusBtn;
    }

    public void setHapusBtn(boolean hapusBtn) {
        this.hapusBtn = hapusBtn;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getBilAmaunList() {
        return bilAmaunList;
    }

    public void setBilAmaunList(int bilAmaunList) {
        this.bilAmaunList = bilAmaunList;
    }

    public List<String> getAmaunLebihList() {
        return amaunLebihList;
    }

    public void setAmaunLebihList(List<String> amaunLebihList) {
        this.amaunLebihList = amaunLebihList;
    }

    public BigDecimal getRemisyen() {
        return remisyen;
    }

    public void setRemisyen(BigDecimal remisyen) {
        this.remisyen = remisyen;
    }

    public String getAmn() {
        return amn;
    }

    public void setAmn(String amn) {
        this.amn = amn;
    }

    public DokumenKewangan getReceipt() {
        return receipt;
    }

    public void setReceipt(DokumenKewangan receipt) {
        this.receipt = receipt;
    }

    public ArrayList<Hakmilik> gethList() {
        return hList;
    }

    public void sethList(ArrayList<Hakmilik> hList) {
        this.hList = hList;
    }

    public List<BigDecimal> getAmaunList() {
        return amaunList;
    }

    public void setAmaunList(List<BigDecimal> amaunList) {
        this.amaunList = amaunList;
    }

    public BigDecimal getCukaiTaliAir() {
        return cukaiTaliAir;
    }

    public void setCukaiTaliAir(BigDecimal cukaiTaliAir) {
        this.cukaiTaliAir = cukaiTaliAir;
    }

    public BigDecimal getTunggakanTaliAir() {
        return tunggakanTaliAir;
    }

    public void setTunggakanTaliAir(BigDecimal tunggakanTaliAir) {
        this.tunggakanTaliAir = tunggakanTaliAir;
    }

    public String getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(String listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    public BigDecimal getDendaSemasa() {
        return dendaSemasa;
    }

    public void setDendaSemasa(BigDecimal dendaSemasa) {
        this.dendaSemasa = dendaSemasa;
    }

    public BigDecimal getDendaTunggakan() {
        return dendaTunggakan;
    }

    public void setDendaTunggakan(BigDecimal dendaTunggakan) {
        this.dendaTunggakan = dendaTunggakan;
    }

    public String getRst() {
        return rst;
    }

    public void setRst(String rst) {
        this.rst = rst;
    }

    public boolean isNotis6a() {
        return notis6a;
    }

    public void setNotis6a(boolean notis6a) {
        this.notis6a = notis6a;
    }

    public String getValidateCek() {
        return validateCek;
    }

    public void setValidateCek(String validateCek) {
        this.validateCek = validateCek;
    }

    public String getValidatePos() {
        return validatePos;
    }

    public void setValidatePos(String validatePos) {
        this.validatePos = validatePos;
    }
}
