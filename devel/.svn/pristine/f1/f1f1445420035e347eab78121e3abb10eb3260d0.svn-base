/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
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
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBatalDokumenKewangan;
import etanah.model.KodCawangan;
import etanah.model.KodPeranan;
import etanah.model.KodStatusTransaksiKewangan;
import etanah.model.KodStatusDokumenKewangan;
import etanah.model.KodTransaksi;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.hasil.LaporanPenyataPemungutItem;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdLaporanPenyataPemungut;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanPtService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.*;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.hasil.kutipanAgensi.KutipanAgensiAction;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author nurfaizati
 */
//@Wizard(startEvents = {"selectTransaction", "search", "selectList", "selectIDHakmilik", "test1", "saveNoAkaun"})
@UrlBinding("/hasil/KemasukanResitCukaiTanahActionBean")
public class KemasukanResitCukaiTanahActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KemasukanResitCukaiTanahActionBean.class);
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String kod;
    private String noRujukan;
    private String noResitKew38;
    private BigDecimal amaunBayaran;
    private BigDecimal baki;
    private static BigDecimal jum;
    private static String noResit;
    private BigDecimal jumAmaunBayaran;
    private BigDecimal tol;
    private BigDecimal tol1;
    private BigDecimal baki1;
    private BigDecimal debit;
    private BigDecimal value;
    private BigDecimal amaun;
    private BigDecimal amaunBaru;
    private BigDecimal d;
    private String note;
    private boolean flag = false;
    private boolean flag1 = true;
    private boolean btn = false;
    private boolean btn2 = false;
    private String btnPindah;
    private static String tempBtnPindah;
    private String btn1;
    private InfoAudit infoAudit;
    private String account;
    private static String tempAccount;
    private String a;
    private String dokList;
    private String kodSts;
    private Character kodStsTrans;
    private KodBatalDokumenKewangan kodBatalDokumenKewangan;
    private Akaun akKredit;
    private Akaun akDebit;
    private Akaun akaunKredit;
    private BigDecimal z;
    private BigDecimal jumlahCaj;
    private KodUrusan kodUrusan;
    private long idKewanganBayaran;
    private static String idDokumenKewangan;
    private static String noAkaun;
    private String status;
    private DokumenKewanganBayaran dokumenKewanganBayaran;
    private KodCawangan cawangan;
    private CaraBayaran caraBayaran;
    private Akaun akaun;
    private DokumenKewangan dokumenKewangan;
    private KodStatusTransaksiKewangan st;
    private List<CaraBayaran> ArrayList;
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<Transaksi> transList;
    private List<Transaksi> transList3;
    private List<Akaun> akaunList;
    private List<Akaun> akList;
    private List<KodTransaksi> senaraiKodTrans;
    private List<DokumenKewanganBayaran> listDK;
    private ArrayList<String> idKew = new ArrayList();
    private String kodNegeri;
    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
    private static List<DokumenKewanganBayaran> tempList = new ArrayList<DokumenKewanganBayaran>();
    private List<DokumenKewanganBayaran> dkList = new ArrayList<DokumenKewanganBayaran>();
    private Permohonan permohonan;
    private String idKewDok;
    private String cukaiTanah = "ya";
    private int utkTahun;
    private KodTransaksi kodTrans;
    private Transaksi transaksi;
    private String kodTransbaru;
    private static String idKewDokStatic;
    private static List<Transaksi> transList2 = new ArrayList<Transaksi>();
    private ArrayList<DokumenKewangan> kewDokList = new ArrayList<DokumenKewangan>();
    private List<KodStatusTransaksiKewangan> senaraiKodStsTrans;
    private static List<LaporanPenyataPemungutItem> senaraiPenyataPemungut;
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<LaporanPenyataPemungutItem> senaraiLaporanPPI;
    private BigDecimal transBayar;
    private BigDecimal transBayarTotal;
    @Inject
    PembatalanResitManager manager;
    @Inject
    private AkaunDAO akaunDAO;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    @Inject
    private KodTransaksiDAO kodTransaksiDAO;
    @Inject
    private DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    private TransaksiDAO transaksiDAO;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private etanah.Configuration conf;
    @Inject
    LaporanPenyataPemungutDAO laporanPenyataPemungutDAO;
    @Inject
    KutipanHasilManager kutipanHasilManager;
    @Inject
    PelupusanPtService pelupusanPtService;
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
    GeneratorIdLaporanPenyataPemungut generator;

    @DefaultHandler
    public Resolution selectTransaction() {

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "negeriSembilan";
        }
        if (tempBtnPindah == null) {
            btnPindah = "false";
            tempBtnPindah = btnPindah;
        }
        transList2.clear();
        idDokumenKewangan = null;
        noAkaun = null;
//        transBayarTotal = null;
        return new ForwardResolution("/WEB-INF/jsp/hasil/kemasukan_resit_tanah.jsp");

    }

//    @ValidationMethod(on = "search")
    public void validateField(ValidationErrors errors) {
//
//        String resit = "";
//        dokumenKewangan = dokumenKewanganDAO.findById(resit);
//
//        noResit = (dokumenKewangan.getIdDokumenKewangan()).substring(6, 8);
//        if (!noResit.equals(caw)) {
//            errors.add(" ", new SimpleError("Resit Tidak Boleh Dibatalkan Di Cawangan Ini"));
////
//        }

        if ((idDokumenKewangan == null) && (noAkaun == null)) {
            errors.add(" ", new SimpleError("Sila Masukkan Salah Satu Maklumat Berikut"));
        }
    }

    public Resolution search() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        LOG.info("Pengguna :" + p.getNama());
        LOG.info("Cawangan Pengguna :" + p.getKodCawangan().getName());
//        senaraiPenyataPemungut.clear();
//        LOG.info("transList size" + transList.size());
        if (idDokumenKewangan != null && noAkaun != null) {
            dokumenKewangan = dokumenKewanganDAO.findById(idDokumenKewangan);
            if (dokumenKewangan != null) {
                senaraiPenyataPemungut = ppServis.findSenaraiPenyataPemungut(dokumenKewangan.getIdDokumenKewangan());
                transList = ppServis.findKewTransByIdKewTrans(dokumenKewangan.getIdDokumenKewangan(), dokumenKewangan.getAkaun().getNoAkaun());
                if (transList.size() > 0) {
                    BigDecimal amaunTotal = BigDecimal.ZERO;;
                    BigDecimal amaunTrans = BigDecimal.ZERO;;
                    for (Transaksi trans : transList) {

                        if (trans.getKodTransaksi().getKod().equals("61401")
                                || trans.getKodTransaksi().getKod().equals("61402")
                                || trans.getKodTransaksi().getKod().equals("76152")
                                || trans.getKodTransaksi().getKod().equals("72457")) {

                            amaunTrans = trans.getAmaun();
                            amaunTotal = amaunTrans.add(amaunTotal);
                            if (amaunTotal.equals(dokumenKewangan.getAmaunBayaran())) {
                                setBtn(true);
                                addSimpleError("No Resit Ini Tidak Boleh Buat Penambahan Lagi");

                            } else {
                                dokumenKewanganBayaran = ppServis.findKewDokumenBayar(dokumenKewangan.getIdDokumenKewangan());
                                if (dokumenKewanganBayaran == null) {
                                    addSimpleError("No Resit Ini Tidak Boleh Buat Penambahan Lagi");
                                    setBtn(true);
                                }
                            }
                        }
                    }
                } else {
                    akaun = akaunDAO.findById(noAkaun);
                    transList3 = akaun.getSemuaTransaksi();
                }
            } else {
                setBtn(true);
                addSimpleError("No Resit Yang Anda Masukan Tidak Wujud. Sila Pastikan No Resit Yang Dimasukan Adalah BETUL.");
            }

        } else {
            setBtn(true);
            addSimpleError("Sila Masukan no RESIT!!!");
        }

        setFlag(true);
        return new ForwardResolution("/WEB-INF/jsp/hasil/kemasukan_resit_tanah.jsp");
    }

    public Resolution tambahTransaksi() {
        if (noAkaun == null) {
            noAkaun = getContext().getRequest().getParameter("noAkaun");
        }

        if (idDokumenKewangan == null) {
            idDokumenKewangan = getContext().getRequest().getParameter("idKewDok");

        }
        BigDecimal zero = BigDecimal.ZERO;
//        dokumenKewanganBayaran = ppServis.findKewDokumenBayar(idDokumenKewangan);
        dokumenKewangan = dokumenKewanganDAO.findById(idDokumenKewangan);
        transBayarTotal = dokumenKewangan.getAmaunBayaran();
        senaraiKodStsTrans = kodStatusTransaksiKewanganDAO.findAll();
        List<KodTransaksi> senaraiKodTransBaru = kodTransaksiDAO.findAll();
        senaraiKodTrans = new ArrayList<KodTransaksi>();
        for (KodTransaksi kodt : senaraiKodTransBaru) {
            if (kodt.getKod().equals("61401")
                    || kodt.getKod().equals("61402")
                    || kodt.getKod().equals("76152")
                    || kodt.getKod().equals("72457")) {

                senaraiKodTrans.add(kodt);
            }
        }
        if (transList2.size() > 0) {
            for (Transaksi amaunTranBayar : transList2) {
                transBayar = amaunTranBayar.getAmaun();
                transBayarTotal = transBayarTotal.subtract(transBayar);
//                transBayarTotal = transBayarTotal1;
                btnPindah = "tambah";
            }
            if (transBayarTotal.equals(zero)) {
                setBtn(true);
                addSimpleError("Amaun Resit Telah Cukup");
                btnPindah = "ResitPenuh";
            }
        } else {
            btnPindah = "tambah";
        }


        return new ForwardResolution("/WEB-INF/jsp/hasil/kemasukan_resit_tanah.jsp");

    }

    public Resolution checkProcess() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        int idTransSementara = 0;

        Date now = new Date();
        int year = now.getYear() + 1900;
        dokumenKewangan = dokumenKewangan = dokumenKewanganDAO.findById(idDokumenKewangan);
        akaun = akaunDAO.findById(noAkaun);
        String akaunAKH = "AKH" + dokumenKewangan.getInfoAudit().getDimasukOleh().getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(dokumenKewangan.getInfoAudit().getDikemaskiniOleh());
        ia.setTarikhMasuk(dokumenKewangan.getInfoAudit().getTarikhMasuk());

        transaksi = new Transaksi();

        transaksi.setDokumenKewangan(dokumenKewangan);
        transaksi.setAmaun(amaunBaru);
        transaksi.setAkaunDebit(akaunDAO.findById(akaunAKH));
        transaksi.setAkaunKredit(akaunDAO.findById(noAkaun));
        transaksi.setCawangan(dokumenKewangan.getInfoAudit().getDimasukOleh().getKodCawangan());
        transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById(Character.valueOf('T')));
        transaksi.setTahunKewangan(year);
        transaksi.setKodTransaksi(kodTransaksiDAO.findById(kodTransbaru));
        transaksi.setUntukTahun(utkTahun);
        transaksi.setKuantiti(0);
        transList2.add(transaksi);

        for (Transaksi transLoop : transList2) {
            idTransSementara++;
            transLoop.setIdTransaksi(idTransSementara);
        }

//         return search();
        return new RedirectResolution(KemasukanResitCukaiTanahActionBean.class, "search").addParameter(akaun.getNoAkaun(), noAkaun);
    }

    public Resolution simpanPP() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idDokumenKewangan != null) {
            dokumenKewangan = dokumenKewanganDAO.findById(idDokumenKewangan);
            dokumenKewanganBayaran = ppServis.findKewDokumenBayar(dokumenKewangan.getIdDokumenKewangan());
            transList = ppServis.findKewTransByIdKewTrans(dokumenKewangan.getIdDokumenKewangan(), dokumenKewangan.getAkaun().getNoAkaun());

            if (transList.size() > 0) {
                for (Transaksi transPP : transList) {
                    LaporanPenyataPemungutItem ppTrans = ppServis.findIdTrans(String.valueOf(transPP.getIdTransaksi()));
                    if (ppTrans == null) {

                        BigDecimal amaunBaruPP = BigDecimal.ZERO;
                        amaunBaruPP = transPP.getAmaun();
                        Long idTrans = transPP.getIdTransaksi();
                        Long id = Long.parseLong(generator.generate(pguna.getKodCawangan().getKod(), dokumenKewangan.getCawangan(), dokumenKewangan.getInfoAudit().getDikemaskiniOleh()));
                        LaporanPenyataPemungutItem pp = new LaporanPenyataPemungutItem();
                        pp.setIdLaporan(id);
                        pp.setAmaun(amaunBaruPP);
                        pp.setIdDokumenKewangan(dokumenKewangan.getIdDokumenKewangan());
                        pp.setIdKewanganBayaran(dokumenKewanganBayaran.getIdKewanganBayaran());
                        pp.setIdTransaksi(idTrans);
                        pp.setStatus('A');
                        kutipanHasilManager.save(pp);
                    }
                }
            }
        }
        return search();
    }

    public Resolution simpanTrans() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        if (transList2.size() > 0) {
            for (Transaksi trans : transList2) {

                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(trans.getInfoAudit().getDikemaskiniOleh());
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhMasuk(trans.getInfoAudit().getTarikhMasuk());
                ia.setTarikhKemaskini(new java.util.Date());


                Transaksi transaksiSimpan = new Transaksi();
                transaksiSimpan.setDokumenKewangan(trans.getDokumenKewangan());
                transaksiSimpan.setAmaun(trans.getAmaun());
                transaksiSimpan.setAkaunDebit(trans.getAkaunDebit());
                transaksiSimpan.setAkaunKredit(trans.getAkaunKredit());
                transaksiSimpan.setCawangan(trans.getCawangan());
                transaksiSimpan.setStatus(trans.getStatus());
                transaksiSimpan.setTahunKewangan(trans.getTahunKewangan());
                transaksiSimpan.setKodTransaksi(trans.getKodTransaksi());
                transaksiSimpan.setUntukTahun(trans.getUntukTahun());
                transaksiSimpan.setKuantiti(trans.getKuantiti());
                transaksiSimpan.setInfoAudit(ia);
//                transaksiDAO.save(transaksiSimpan);
                pelupusanPtService.simpanTransaksi(transaksiSimpan);

                Akaun AkaunDeduct = akaunDAO.findById(trans.getAkaunKredit().getNoAkaun());
                BigDecimal BakiTotal = BigDecimal.ZERO;
                BakiTotal = AkaunDeduct.getBaki().subtract(transaksiSimpan.getAmaun());
                AkaunDeduct.setBaki(BakiTotal);
                akaunDAO.save(AkaunDeduct);
            }
            transList2.clear();
        }

        return search();
    }

    public Resolution deleteTrans() {
        String TransDel = getContext().getRequest().getParameter("transaksi");
        int Del = Integer.valueOf(TransDel);
        if (transList2.size() > 0) {
            for (Transaksi trans : transList2) {
                if (trans.getIdTransaksi() == Del) {
                    transList2.remove(trans);
                }
            }

        }
        return new RedirectResolution(KemasukanResitCukaiTanahActionBean.class, "search").addParameter(akaun.getNoAkaun(), noAkaun);
    }

    @ValidationMethod(on = "selectList")
    public void validateRadio(ValidationErrors errors) {

        if (idDokumenKewangan == null) {
            errors.add("radioButton", new SimpleError("Sila Pilih Salah Satu. Tekan Butang Carian untuk Carian Semula."));

        }
    }

    public Resolution selectIDHakmilik() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";

        }
        dokumenKewangan = dokumenKewanganDAO.findById(getContext().getRequest().getParameter("idResit"));
        dkList = tempList;

//        for (DokumenKewanganBayaran a : dkList) {
//            account = a.getDokumenKewangan().getAkaun().getNoAkaun();
//
//        }

        return new JSP("hasil/pembatalan_resit_3.jsp").addParameter("popup", "true");
    }

    public Resolution cariAkaun() {

        //Melaka sahaja
        if (getAkaunKredit() != null) {
            String[] name = {"akaunKredit"};
            Object[] value = {getAkaunKredit()};

            List<Transaksi> trans = transaksiDAO.findByEqualCriterias(name, value, "untukTahun");

            transList = new ArrayList<Transaksi>();
            List<Transaksi> trList = new ArrayList<Transaksi>();
            String akaun = "";
            for (Transaksi tr : trans) {
                if ((tr.getAkaunKredit() != null)) {
                    trList.add(tr);
                }
            }
            for (int i = 0; i < trList.size(); i++) {
                Transaksi t = trList.get(i);

                if (trList.size() > 1) {
                    for (int x = i + 1; x < trList.size(); x++) {
                        Transaksi t1 = trList.get(x);

                        if (!akaun.equals(t1.getAkaunKredit().getNoAkaun())) {

                            transList.add(t);
                            akaun = t.getAkaunKredit().getNoAkaun();
                        }
                    }
                } else {
                    transList.add(t);
                }

            }
        } else if (getHakmilik() != null) {

            String[] name = {"hakmilik"};
            Object[] value = {getHakmilik()};
            List<Akaun> akaunList = akaunDAO.findByEqualCriterias(name, value, null);
            transList = new ArrayList<Transaksi>();

            for (Akaun ak : akaunList) {
                String[] n1 = {"akaunKredit"};
                Object[] v1 = {ak};
                List<Transaksi> trans = transaksiDAO.findByEqualCriterias(n1, v1, null);
//
                for (int i = 0; i < trans.size(); i++) {
                    Transaksi t = trans.get(i);
                    if (t.getDokumenKewangan() != null) {
                        if ((t.getStatus() != null)) {
//                            && (t.getStatus().getKod() == 'A')
                            transList.add(t);
                            String temp = " ";

                            for (int j = 0; j < transList.size(); j++) {
                                Transaksi tr = transList.get(j);
                                if (tr.getDokumenKewangan().getIdDokumenKewangan().equalsIgnoreCase(temp)) {
                                    transList.remove(j);
                                }
                                temp = tr.getDokumenKewangan().getIdDokumenKewangan();
                            }
                        } else {
                            transList.add(t);
                        }
                    }
                }
            }

        }
        setFlag(true);
        return new JSP("hasil/pembatalan_resit_3.jsp").addParameter("popup", "true");
    }

    public Resolution test1() {
//        selectList();
        return new ForwardResolution("/WEB-INF/jsp/hasil/pembatalan_resit_2.jsp");
    }

    public Resolution saveN9() {


        return new ForwardResolution("/WEB-INF/jsp/hasil/pembatalan_resit_2.jsp");
    }

    public Resolution cetak() throws FileNotFoundException, ParseException {

        return new StreamingResolution("application/pdf");

    }

    public Resolution savePelbagai() {


        return new ForwardResolution("/WEB-INF/jsp/hasil/pembatalan_resit_2.jsp");
    }

    public Resolution saveN91() throws FileNotFoundException, ParseException {

        return new ForwardResolution("/WEB-INF/jsp/hasil/pembatalan_resit_2.jsp");

    }

    public Resolution test() {

        return new ForwardResolution("/WEB-INF/jsp/hasil/pembatalan_resit_2.jsp");
    }

    private ArrayList<String> removeDuplicate(ArrayList senarai) {
        //Create a HashSet which allows no duplicates
        HashSet hashSet = new HashSet(senarai);

        //Assign the HashSet to a new ArrayList
        ArrayList arrayList2 = new ArrayList(hashSet);

        //Ensure correct order, since HashSet doesn't
        Collections.sort(arrayList2);
        return arrayList2;
    }

    private List<Transaksi> removeDuplicateKewDok(List<Transaksi> senaraiTransaksi) {
        Map<DokumenKewangan, Transaksi> map = new HashMap<DokumenKewangan, Transaksi>();
        for (int i = 0; i < senaraiTransaksi.size(); i++) {
            DokumenKewangan dk = senaraiTransaksi.get(i).getDokumenKewangan();
            if (map.containsKey(dk)) {
                continue;
            } else {
                map.put(dk, senaraiTransaksi.get(i));
            }
        }
        senaraiTransaksi = new ArrayList<Transaksi>(map.values());
        return senaraiTransaksi;
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
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

    public KodStatusTransaksiKewanganDAO getKodStatusTransaksiKewanganDAO() {
        return kodStatusTransaksiKewanganDAO;
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

    public double getBaki() {
//       baki = baki.add(double amaun);
        return baki.doubleValue();
    }

    public void setBaki(double baki) {
        this.baki = BigDecimal.valueOf(baki);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getKod() {
        return kod;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public double getAmaun() {
        return amaun.doubleValue();
    }

    public void setAmaun(double amaun) {
        this.amaun = BigDecimal.valueOf(amaun);
    }

    public double getBaki1() {
        return baki1.doubleValue();
    }

    public void setBaki1(double baki1) {
        this.baki1 = BigDecimal.valueOf(baki1);
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

    public String getBtn1() {
        return btn1;
    }

    public void setBtn1(String btn1) {
        this.btn1 = btn1;
    }

    public void setKodStatusTransaksiKewanganDAO(KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO) {
        this.kodStatusTransaksiKewanganDAO = kodStatusTransaksiKewanganDAO;
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

    public BigDecimal getJum() {
        return jum;
    }

    public void setJum(BigDecimal jum) {
        this.jum = jum;
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

    public BigDecimal getJumAmaunBayaran() {
        return jumAmaunBayaran;
    }

    public void setJumAmaunBayaran(BigDecimal jumAmaunBayaran) {
        this.jumAmaunBayaran = jumAmaunBayaran;
    }

    public BigDecimal getAmaunBayaran() {
        return amaunBayaran;
    }

    public void setAmaunBayaran(BigDecimal amaunBayaran) {
        this.amaunBayaran = amaunBayaran;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
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

    /**
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * @return the tol1
     */
    public BigDecimal getTol1() {
        return tol1;
    }

    /**
     * @param tol1 the tol1 to set
     */
    public void setTol1(BigDecimal tol1) {
        this.tol1 = tol1;
    }

    /**
     * @return the z
     */
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
     * @return the d
     */
    public BigDecimal getD() {
        return d;
    }

    /**
     * @param d the d to set
     */
    public void setD(BigDecimal d) {
        this.d = d;
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
     * @return the flag1
     */
    public boolean isFlag1() {
        return flag1;
    }

    /**
     * @param flag1 the flag1 to set
     */
    public void setFlag1(boolean flag1) {
        this.flag1 = flag1;
    }

    /**
     * @return the btn2
     */
    public boolean isBtn2() {
        return btn2;
    }

    /**
     * @param btn2 the btn2 to set
     */
    public void setBtn2(boolean btn2) {
        this.btn2 = btn2;
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

//    public static void setIdKewDokStatic(String idKewDokStatic) {
//        PembatalanResitActionBean.idKewDokStatic = idKewDokStatic;
//    }
    public List<LaporanPenyataPemungutItem> getSenaraiLaporanPPI() {
        return senaraiLaporanPPI;
    }

    public void setSenaraiLaporanPPI(List<LaporanPenyataPemungutItem> senaraiLaporanPPI) {
        this.senaraiLaporanPPI = senaraiLaporanPPI;
    }

    public String getBtnPindah() {
        return btnPindah;
    }

    public void setBtnPindah(String btnPindah) {
        this.btnPindah = btnPindah;
    }

    public static String getTempBtnPindah() {
        return tempBtnPindah;
    }

//    public static void setTempBtnPindah(String tempBtnPindah) {
//        PembatalanResitActionBean.tempBtnPindah = tempBtnPindah;
//    }
    public static String getTempAccount() {
        return tempAccount;
    }

//    public static void setTempAccount(String tempAccount) {
//        PembatalanResitActionBean.tempAccount = tempAccount;
//    }
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

    public List<Transaksi> getTransList2() {
        return transList2;
    }

    public void setTransList2(List<Transaksi> transList2) {
        this.transList2 = transList2;
    }

    public List<Transaksi> getTransList3() {
        return transList3;
    }

    public void setTransList3(List<Transaksi> transList3) {
        this.transList3 = transList3;
    }

    public List<KodStatusTransaksiKewangan> getSenaraiKodStsTrans() {
        return senaraiKodStsTrans;
    }

    public void setSenaraiKodStsTrans(List<KodStatusTransaksiKewangan> senaraiKodStsTrans) {
        this.senaraiKodStsTrans = senaraiKodStsTrans;
    }

    public int getUtkTahun() {
        return utkTahun;
    }

    public void setUtkTahun(int utkTahun) {
        this.utkTahun = utkTahun;
    }

    public List<KodTransaksi> getSenaraiKodTrans() {
        return senaraiKodTrans;
    }

    public void setSenaraiKodTrans(List<KodTransaksi> senaraiKodTrans) {
        this.senaraiKodTrans = senaraiKodTrans;
    }

    public KodTransaksi getKodTrans() {
        return kodTrans;
    }

    public void setKodTrans(KodTransaksi kodTrans) {
        this.kodTrans = kodTrans;
    }

    public Character getKodStsTrans() {
        return kodStsTrans;
    }

    public void setKodStsTrans(Character kodStsTrans) {
        this.kodStsTrans = kodStsTrans;
    }

    public String getKodTransbaru() {
        return kodTransbaru;
    }

    public void setKodTransbaru(String kodTransbaru) {
        this.kodTransbaru = kodTransbaru;
    }

    public BigDecimal getAmaunBaru() {
        return amaunBaru;
    }

    public void setAmaunBaru(BigDecimal amaunBaru) {
        this.amaunBaru = amaunBaru;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public List<KodBatalDokumenKewangan> getSenaraiKodBatalDokumenKewFilter() {
        String sql = "From KodBatalDokumenKewangan kbdk Where kbdk.kod not in (:kod1,:kod2)";
        Query q = sessionProvider.get().createQuery(sql);
        q.setString("kod1", "CT"); // CT=Tarikh Tamat Tempoh
        q.setString("kod2", "CK"); // CK=Cek Tendang
        senaraiKodBatalDokumenKewFilter = q.list();
        return senaraiKodBatalDokumenKewFilter;
    }

    public void setSenaraiKodBatalDokumenKewFilter(List<KodBatalDokumenKewangan> senaraiKodBatalDokumenKewFilter) {
        this.senaraiKodBatalDokumenKewFilter = senaraiKodBatalDokumenKewFilter;
    }

    public BigDecimal getTransBayar() {
        return transBayar;
    }

    public void setTransBayar(BigDecimal transBayar) {
        this.transBayar = transBayar;
    }

    public BigDecimal getTransBayarTotal() {
        return transBayarTotal;
    }

    public void setTransBayarTotal(BigDecimal transBayarTotal) {
        this.transBayarTotal = transBayarTotal;
    }

    public List<LaporanPenyataPemungutItem> getSenaraiPenyataPemungut() {
        return senaraiPenyataPemungut;
    }

    public void setSenaraiPenyataPemungut(List<LaporanPenyataPemungutItem> senaraiPenyataPemungut) {
        this.senaraiPenyataPemungut = senaraiPenyataPemungut;
    }

    public KodTransaksiDAO getKodTransaksiDAO() {
        return kodTransaksiDAO;
    }

    public void setKodTransaksiDAO(KodTransaksiDAO kodTransaksiDAO) {
        this.kodTransaksiDAO = kodTransaksiDAO;
    }
}
