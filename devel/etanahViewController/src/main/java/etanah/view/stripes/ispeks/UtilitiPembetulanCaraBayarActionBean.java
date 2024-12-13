/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import etanah.view.stripes.hasil.*;
import java.util.List;
import java.util.ArrayList;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.dao.hasil.LaporanPenyataPemungutDAO;
import java.math.BigDecimal;
import etanah.model.Akaun;
import etanah.model.CaraBayaran;
import etanah.model.KodUrusan;
import etanah.model.Transaksi;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodBatalDokumenKewangan;
import etanah.model.KodCaraBayaran;
import etanah.model.KodCawangan;
import etanah.model.KodStatusTransaksiKewangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.hasil.LaporanPenyataPemungutItem;
import etanah.service.NotifikasiService;
import etanah.service.ispeks.IspeksService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.*;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Session;

@UrlBinding("/utiliti/ispeks/pembetulanCaraBayar")
public class UtilitiPembetulanCaraBayarActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(UtilitiPembetulanCaraBayarActionBean.class);
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String kod;
    private String caraBayar;
    private String noResitKew38;
    private InfoAudit infoAudit;
    private String account;
    private static String tempAccount;
    private String a;
    private String dokList;
    private String kodSts;
    private KodBatalDokumenKewangan kodBatalDokumenKewangan;
    private Akaun akKredit;
    private Akaun akDebit;
    private Akaun akaunKredit;
    private BigDecimal z;
    private BigDecimal jumlahCaj;
    private KodUrusan kodUrusan;
    private long idKewanganBayaran;
    private static String idDokumenKewangan;
    private String noAkaun;
    private String status;
    private DokumenKewanganBayaran dokumenKewanganBayaran;
    private KodCawangan cawangan;
    private CaraBayaran caraBayaran;
    private Akaun akaun;
    private DokumenKewangan dokumenKewangan;
    @Inject
    private KodCaraBayaranDAO caraBayaranDAO;
    private KodStatusTransaksiKewangan st;
    private List<CaraBayaran> ArrayList;
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<Transaksi> transList;
    private List<DokumenKewangan> dokKewList;
    private List<Akaun> akaunList;
    private List<Akaun> akList;
    private List<DokumenKewanganBayaran> listDK;
    private ArrayList<String> idKew = new ArrayList();
    private String kodNegeri;
    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
    private static List<DokumenKewanganBayaran> tempList = new ArrayList<DokumenKewanganBayaran>();
    private List<DokumenKewanganBayaran> dkList = new ArrayList<DokumenKewanganBayaran>();
    private Permohonan permohonan;
    private String idKewDok;
    private String cukaiTanah = "ya";
    private static String idKewDokStatic;
    private ArrayList<DokumenKewangan> kewDokList = new ArrayList<DokumenKewangan>();
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<LaporanPenyataPemungutItem> senaraiLaporanPPI;
    private boolean btn = false;
    @Inject
    IspeksService ispeksService;
    @Inject
    PembatalanResitManager manager;
    @Inject
    private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    @Inject
    private DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    LaporanPenyataPemungutDAO laporanPenyataPemungutDAO;
    @Inject
    etanah.kodHasilConfig khconf;
    @Inject
    PenyataPemungutService ppServis;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    //for list sebab batal
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    List<KodBatalDokumenKewangan> senaraiKodBatalDokumenKewFilter = new ArrayList<KodBatalDokumenKewangan>();
    //for notifikasi if pembatalan perserahan/permohonan
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    //for pembatalan permohonan BPEL
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    GenerateIdPermohonanWorkflow gipw;
    @Inject
    CaraBayaranDAO caraBayarDAO;

    @DefaultHandler
    public Resolution selectTransaction() {

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "negeriSembilan";
        }
        return new ForwardResolution("/WEB-INF/jsp/ispeks/pembetulan_caraBayar.jsp");

    }

    @ValidationMethod(on = "search")
    public void validateField(ValidationErrors errors) {

        if (idDokumenKewangan == null) {
            errors.add(" ", new SimpleError("Sila Masukkan No Resit"));
        }
    }

    public Resolution search() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        LOG.info("Pengguna :" + p.getNama());
        LOG.info("Cawangan Pengguna :" + p.getKodCawangan().getName());

        dokumenKewangan = dokumenKewanganDAO.findById(idDokumenKewangan);

        if (dokumenKewangan != null) {
            List<CaraBayaran> cbList = new ArrayList<CaraBayaran>();
            listDK = new ArrayList<DokumenKewanganBayaran>();
            String[] n1 = {"dokumenKewangan"};
            Object[] v1 = {dokumenKewangan};
            List<DokumenKewanganBayaran> dkbList = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);
            for (DokumenKewanganBayaran dkb : dkbList) {
                CaraBayaran cb = dkb.getCaraBayaran();
                cbList.add(cb);
            }

            for (CaraBayaran cb : cbList) {
                listDK.addAll(cb.getSenaraiDokumenKewanganBayaran());
            }

//        if (cbList.size() > 1) {
            Map<DokumenKewangan, DokumenKewanganBayaran> map = new HashMap<DokumenKewangan, DokumenKewanganBayaran>();
            for (int i = 0; i < listDK.size(); i++) {
                DokumenKewangan dk = listDK.get(i).getDokumenKewangan();
                if (map.containsKey(dk)) {
                    continue;
                } else {
                    map.put(dk, listDK.get(i));
                }
            }
            listDK = new ArrayList<DokumenKewanganBayaran>(map.values());
            tempList = listDK;
        } else {
            addSimpleError("No Resit Tidak Wujud.");
        }
        return new ForwardResolution("/WEB-INF/jsp/ispeks/pembetulan_caraBayar.jsp");
    }

    public Resolution kembali() {
        return new RedirectResolution(UtilitiPembatalanResitActionBean.class);
    }

    public Resolution kemaskiniCaraByr() {  

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna p = ctx.getUser();
        java.util.Date now = new java.util.Date();

        caraBayar = getContext().getRequest().getParameter("caraBayaran.kodCaraBayaran.kod");
        //Dokumen Bayar
        List<CaraBayaran> cbList = new ArrayList<CaraBayaran>();
        List<DokumenKewanganBayaran> listDKB = new ArrayList<DokumenKewanganBayaran>();
        dokumenKewangan = dokumenKewanganDAO.findById(idDokumenKewangan);

        List<DokumenKewanganBayaran> dkbList = ispeksService.findDokKewByr(dokumenKewangan.getIdDokumenKewangan());

        for (DokumenKewanganBayaran dkb : dkbList) {

            CaraBayaran cb = dkb.getCaraBayaran();
            caraBayaran = caraBayarDAO.findById(cb.getIdCaraBayaran());
            
            KodCaraBayaran kodCaraBayar = caraBayaranDAO.findById(caraBayar);
            
            if(kodCaraBayar != null){
                 cb.setKodCaraBayaran(kodCaraBayar);
            }

            InfoAudit info = p.getInfoAudit();
            info.setDimasukOleh(p);
            info.setTarikhMasuk(now);
            cb.setInfoAudit(info);
            cbList.add(cb);
        }
        manager.updateCaraBayar(cbList);
        addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");

        return new ForwardResolution("/WEB-INF/jsp/ispeks/pembetulan_caraBayar.jsp");
    }

    public Akaun getAkaun() {
        return akKredit;
    }

    public void setAkaun(Akaun akaun) {
        this.akKredit = akaun;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
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

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdDokumenKewangan() {
        return idDokumenKewangan;
    }

    public void setIdDokumenKewangan(String idDokumenKewangan) {
        this.idDokumenKewangan = idDokumenKewangan;
    }

    public CaraBayaran getCaraBayaran() {
        return caraBayaran;
    }

    public void setCaraBayaran(CaraBayaran caraBayaran) {
        this.caraBayaran = caraBayaran;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public KodBatalDokumenKewangan getKodBatalDokumenKewangan() {
        return kodBatalDokumenKewangan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public List<CaraBayaran> getArrayList() {
        return ArrayList;
    }

    public long getIdKewanganBayaran() {
        return idKewanganBayaran;
    }

    public void setIdKewanganBayaran(long idKewanganBayaran) {
        this.idKewanganBayaran = idKewanganBayaran;
    }

    public DokumenKewanganBayaran getDokumenKewanganBayaran() {
        return dokumenKewanganBayaran;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDokList() {
        return dokList;
    }

    public void setDokList(String dokList) {
        this.dokList = dokList;
    }

    public String getKod() {
        return kod;
    }

    public String getKodSts() {
        return kodSts;
    }

    public void setKodSts(String kodSts) {
        this.kodSts = kodSts;
    }

    public Akaun getAkaunKredit() {
        return akaunKredit;
    }

    public void setAkaunKredit(Akaun akaunKredit) {
        this.akaunKredit = akaunKredit;
    }

    public List<Akaun> getAkaunList() {
        return akaunList;
    }

    public void setAkaunList(List<Akaun> akaunList) {
        this.akaunList = akaunList;
    }

    public Akaun getAkaunDebit() {
        return akDebit;
    }

    public void setAkaunDebit(Akaun akaunDebit) {
        this.akDebit = akaunDebit;
    }

    public Akaun getAkaun1() {
        return akDebit;
    }

    public void setAkaun1(Akaun akaun1) {
        this.akDebit = akaun1;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<DokumenKewanganBayaran> getListDK() {
        return listDK;
    }

    public void setListDK(List<DokumenKewanganBayaran> listDK) {
        this.listDK = listDK;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.setSenaraiCaraBayaran(senaraiCaraBayaran);
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public List<DokumenKewanganBayaran> getTempList() {
        return tempList;
    }

    public void setTempList(List<DokumenKewanganBayaran> tempList) {
        this.tempList = tempList;
    }

    public List<DokumenKewanganBayaran> getDkList() {
        return dkList;
    }

    public void setDkList(List<DokumenKewanganBayaran> dkList) {
        this.dkList = dkList;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public BigDecimal getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(BigDecimal z) {
        this.z = z;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the a
     */
    public String getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(String a) {
        this.a = a;
    }

    /**
     * @return the akList
     */
    public List<Akaun> getAkList() {
        return akList;
    }

    /**
     * @param akList the akList to set
     */
    public void setAkList(List<Akaun> akList) {
        this.akList = akList;
    }

    /**
     * @return the idKew
     */
    public ArrayList<String> getIdKew() {
        return idKew;
    }

    /**
     * @param idKew the idKew to set
     */
    public void setIdKew(ArrayList<String> idKew) {
        this.idKew = idKew;
    }

    /**
     * @return the kewDokList
     */
    public ArrayList<DokumenKewangan> getKewDokList() {
        return kewDokList;
    }

    /**
     * @param kewDokList the kewDokList to set
     */
    public void setKewDokList(ArrayList<DokumenKewangan> kewDokList) {
        this.kewDokList = kewDokList;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

    public static String getIdKewDokStatic() {
        return idKewDokStatic;
    }

    public static void setIdKewDokStatic(String idKewDokStatic) {
        UtilitiPembetulanCaraBayarActionBean.idKewDokStatic = idKewDokStatic;
    }

    public List<LaporanPenyataPemungutItem> getSenaraiLaporanPPI() {
        return senaraiLaporanPPI;
    }

    public void setSenaraiLaporanPPI(List<LaporanPenyataPemungutItem> senaraiLaporanPPI) {
        this.senaraiLaporanPPI = senaraiLaporanPPI;
    }

    public static String getTempAccount() {
        return tempAccount;
    }

    public static void setTempAccount(String tempAccount) {
        UtilitiPembetulanCaraBayarActionBean.tempAccount = tempAccount;
    }

    public String getCukaiTanah() {
        return cukaiTanah;
    }

    public void setCukaiTanah(String cukaiTanah) {
        this.cukaiTanah = cukaiTanah;
    }

    public String getNoResitKew38() {
        return noResitKew38;
    }

    public void setNoResitKew38(String noResitKew38) {
        this.noResitKew38 = noResitKew38;
    }

    public List<DokumenKewangan> getDokKewList() {
        return dokKewList;
    }

    public void setDokKewList(List<DokumenKewangan> dokKewList) {
        this.dokKewList = dokKewList;
    }

    public String getCaraBayar() {
        return caraBayar;
    }

    public void setCaraBayar(String caraBayar) {
        this.caraBayar = caraBayar;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

}
