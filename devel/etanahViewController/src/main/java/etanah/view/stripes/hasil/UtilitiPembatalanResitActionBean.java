/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import etanah.view.stripes.hasil.*;
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
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.hasil.LaporanPenyataPemungutItem;
import etanah.report.ReportUtil;
import etanah.service.NotifikasiService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.*;
import etanah.view.etanahActionBeanContext;
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
@UrlBinding("/utiliti/hasil/pembatalan_resit")
public class UtilitiPembatalanResitActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PembatalanResitActionBean.class);
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
        return new ForwardResolution("/WEB-INF/jsp/utiliti/pembatalan_resit_1.jsp");

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
            String[] name = {"idDokumenKewangan"};
            Object[] value = {dokumenKewangan.getIdDokumenKewangan()};
            List<DokumenKewangan> trans = dokumenKewanganDAO.findByEqualCriterias(name, value, null);

            dokKewList = new ArrayList<DokumenKewangan>();
            List<DokumenKewangan> trList = new ArrayList<DokumenKewangan>();
            for (DokumenKewangan tr : trans) {
                if (tr.getIdDokumenKewangan() != null) {
                    trList.add(tr);
                }
            }
            dokKewList = trList;

        }

        LOG.debug("dokKewList.size :" + dokKewList.size());

        // DokumenKewangan d = transList.get(0).getDokumenKewangan();
        //if (d.getAkaun() == null) {
        List<HakmilikPermohonan> senaraiPermohonanHakmilik = new ArrayList<HakmilikPermohonan>();
        if (dokKewList.size() > 0) {
            DokumenKewangan t = new DokumenKewangan();
            t = dokKewList.get(0);
            //akKredit = t.getAkaunKredit();

            String statusTK = "tiada";
            String statusTA = "tiada";
            String permohonanTK = "tiada";
            String cukaiMohon = "tiada";
            String cawSama = "betul";
            for (DokumenKewangan transTK : dokKewList) {

                if (transTK.getAkaun() != null) {
                    if (transTK.getAkaun().getPermohonan() != null) {    // pembayaran untuk permohonan
                        permohonanTK = "ada";
                        if (transTK.getAkaun().getPermohonan().getStatus().equals("TK")) // TK = TIDAK AKTIF (status permohonan for module pendaftaran)
                        {
                            statusTK = "ada";
                        }
                        if (transTK.getAkaun().getPermohonan().getStatus().equals("TA")) // TA = TUNGGU AMBIL (status permohonan for module pendaftaran)
                        {
                            statusTA = "ada";
                        }
                    } else {
                        if ((transTK.getAkaun() != null) && (transTK.getAkaun().getKodAkaun().getKod().equals("AC"))) { // pembayaran cukai tanah
                            String[] nama = {"hakmilik.idHakmilik"};
                            Object[] nilai = {transTK.getAkaun().getHakmilik().getIdHakmilik()};
                            senaraiPermohonanHakmilik.clear();
                            LOG.info("(clear) senaraiPermohonanHakmilik.size :" + senaraiPermohonanHakmilik.size());
                            senaraiPermohonanHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(nama, nilai, null);
                            LOG.info("(chk) senaraiPermohonanHakmilik.size :" + senaraiPermohonanHakmilik.size());
                            for (HakmilikPermohonan sph : senaraiPermohonanHakmilik) {
                                if (sph.getPermohonan().getStatus() == null) {
                                    continue;
                                }
                                if (!(sph.getPermohonan().getStatus().equals("SL") || sph.getPermohonan().getStatus().equals("TK"))) { // SL = Selesai, TK = Tidak Aktif
                                    cukaiMohon = "ada";
                                }
                                LOG.info("Permohonan/Perserahan :" + sph.getPermohonan().getIdPermohonan() + ", status :" + sph.getPermohonan().getStatus().toString());
                            }
                        }
                    }
                }

                //check if pembatalan resit pada cawangan pembayaran
                if (!p.getKodCawangan().getKod().equals(transTK.getCawangan().getKod())) { // tidak sama cawangan pembayaran dengan nk buat pembatalan
                    cawSama = "tidak";
                }
            }

            if (permohonanTK.equals("ada") && statusTK.equals("tiada")) {  //untuk permohonan/perserahan
                setBtn(true);
                addSimpleError("Terdapat permohonan yang BELUM dibatalkan.");
            }
//            if (permohonan.getKodUrusan().equals("HKTHK") && permohonan.getStatus().) {
            if (permohonanTK.equals("ada") && statusTA.equals("ada")) { //untuk permohonan/perserahan
                setBtn(false);
                addSimpleError("Terdapat permohonan yang masih AKTIF dan belum diambil. Jika diteruskan, mengakibatkan permohonan tersebut terbatal.");
            }
//        }
            LOG.info("statusTK :" + statusTK);
            LOG.info("statusTA :" + statusTA);
            LOG.info("permohonanTK :" + permohonanTK);
            LOG.info("cukaiMohon :" + cukaiMohon);
            LOG.info("cawSama :" + cawSama);
//            LOG.debug("urusan :" + permohonan.getKodUrusan().getNama());

            for (HakmilikPermohonan sph : senaraiPermohonanHakmilik) { // tambah untuk checking urusan HKTHK yang active     
                if (!sph.getPermohonan().getKodUrusan().getKod().equals("HKTHK") && (sph.getPermohonan().getStatus().equals("AK"))) {
                    if (cukaiMohon.equals("ada")) { //untuk cukai tanah
                        setBtn(true);
                        addSimpleError("Terdapat permohonan yang masih AKTIF. Tidak dibenarkan membuat pembatalan.");
                    }
                }
            }
            if (!p.getKodCawangan().getKod().equals("00")) {
                if (cawSama.equals("tidak")) {
                    setBtn(true);
                    addSimpleError("Resit BUKAN dikeluarkan dicawangan ini. Tidak dibenarkan membuat pembatalan1.");
                }
            }
        } else {
            setBtn(true);
        }
        //}
        setFlag(true);
        return new ForwardResolution("/WEB-INF/jsp/utiliti/pembatalan_resit_1.jsp");
    }

    public Resolution kembali() {
        return new RedirectResolution(UtilitiPembatalanResitActionBean.class);
    }

    @ValidationMethod(on = "selectList")
    public void validateRadio(ValidationErrors errors) {

        if (idDokumenKewangan == null) {
            errors.add("radioButton", new SimpleError("Sila Pilih Salah Satu. Tekan Butang Carian untuk Carian Semula."));

        }
    }

    public Resolution selectList() {

        jumlahCaj = new BigDecimal("0.00");

        dokumenKewangan = dokumenKewanganDAO.findById(idDokumenKewangan);

        transList = new ArrayList<Transaksi>();

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
//        }
        LOG.debug("listDK.size :" + listDK.size());

        if (listDK.size() > 1) {

            for (int j = 0; j < listDK.size(); j++) {

                DokumenKewanganBayaran tr = listDK.get(j);
//                LOG.debug("idDokumenKewanganBayaran :"+tr.getIdKewanganBayaran());

                if (tr.getDokumenKewangan().getIdDokumenKewangan().matches(idDokumenKewangan)) {
                    listDK.remove(j);
                    value = tr.getAmaun();

                }

            }
            setFlag(true);
        }

        tempList = listDK;

        dokumenKewangan = dokumenKewanganDAO.findById(idDokumenKewangan);
        String[] name = {"idDokumenKewangan"};
        Object[] value = {dokumenKewangan.getIdDokumenKewangan()};

        dokKewList = dokumenKewanganDAO.findByEqualCriterias(name, value, null);

        for (DokumenKewangan trans : dokKewList) {

            if (trans.getAkaun() != null && trans.getAkaun().getPermohonan() == null) {
                if (trans.getAkaun().getKodAkaun().getKod().equals("AC")) {
                    idHakmilik = trans.getAkaun().getHakmilik().getIdHakmilik();
                }
            }
//            if ((trans.getAkaunDebit() != null) && (trans.getAkaunKredit() != null)) {
//                akDebit = trans.getAkaunDebit();
//                double d = trans.getAmaun().doubleValue();
//                trans.setAmaun(new BigDecimal(d));
//
//            }

            jum = trans.getAmaunBayaran();

        }

        // check if idKewDok is cukaiTanah or else
        for (DokumenKewanganBayaran dkbayar : listDK) {
//            LOG.info("dkbayar.id :"+dkbayar.getIdKewanganBayaran());
            if (dkbayar.getDokumenKewangan().getAkaun() == null) {
                cukaiTanah = "bukan";
                LOG.debug("cukaiTanah :" + cukaiTanah);
            }
        }

        if (tempBtnPindah == null) {
            btnPindah = "false";
            tempBtnPindah = btnPindah;
        }
        return new ForwardResolution("/WEB-INF/jsp/utiliti/pembatalan_resit_2.jsp");

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

    public Resolution saveNoAkaun() {
        String results = "";

        account = getContext().getRequest().getParameter("account");
        noResit = getContext().getRequest().getParameter("idDokumenKewangan");
        tempAccount = account;

//        dkList = tempList;
//        jumAmaunBayaran = jum.add(amaun);
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//
//        Pengguna p = ctx.getUser();
//        Date now = new Date();
//        InfoAudit info = p.getInfoAudit();
//        info.setDimasukOleh(p);
//        info.setTarikhMasuk(new java.util.Date());
//        int year = Integer.parseInt(yy.format(now));
//        KodCawangan caw = p.getKodCawangan();
//        DokumenKewangan phk = dokumenKewanganDAO.findById(noResit);
//        Akaun ak = akaunDAO.findById(account);
//        ak.setBaki(jum.multiply(new BigDecimal(-1)));
//
//
//        phk.setAmaunBayaran(jumAmaunBayaran);
//
//        KodTransaksi kt = kodTransaksiDAO.findById("61401"); // kodTransaksi utk dijadikan bayar lebih
//
//        Transaksi t = new Transaksi();
//
//        t.setInfoAudit(info);
//        t.setCawangan(p.getKodCawangan());
//        t.setKodTransaksi(kt);
//        t.setUntukTahun(year);
//        t.setTahunKewangan(year);
//        t.setAkaunKredit(akaunDAO.findById(account));
//        t.setAmaun(jum);
//        t.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
//        t.setDokumenKewangan(phk);
//        try{
//            manager.update(phk, t);
//            addSimpleMessage("Pindahan telah berjaya.");
        btnPindah = "true";
        tempBtnPindah = btnPindah;
        LOG.info("Pindahan berjaya.");
        LOG.info("btnPindah :" + btnPindah);
        LOG.info("tempBtnPindah :" + tempBtnPindah);
        results = "Pindahan akan dijalankan keatas no. akaun : " + account;
//        }catch (Exception ex){
////            addSimpleError("Pindahan TIDAK berjaya.");
//            LOG.info("Pindahan TIDAK berjaya.");
//            ex.printStackTrace();
//            results = "Pindahan TIDAK berjaya.";
//        }
        setBtn(true);
        setBtn2(true);
//        return new JSP("/WEB-INF/jsp/hasil/pembatalan_resit_2.jsp").addParameter("error", error).addParameter("message", msg);
//        return new ForwardResolution("hasil/pembatalan_resit_3.jsp").addParameter("popup", "true");
//        return new ForwardResolution("/WEB-INF/jsp/hasil/pembatalan_resit_2.jsp");
        return new StreamingResolution("text/plain", results);

    }

    public Resolution test1() {
        selectList();
        return new ForwardResolution("/WEB-INF/jsp/hasil/pembatalan_resit_2.jsp");
    }

    public Resolution saveN9() {
        LOG.info("tempBtnPindah :" + tempBtnPindah);
        LOG.info("1) btnPindah :" + btnPindah);
        btnPindah = tempBtnPindah;
        setBtnPindah(btnPindah);
        LOG.info("2) btnPindah :" + btnPindah);

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna p = ctx.getUser();
        java.util.Date now = new java.util.Date();

        KodCawangan caw = p.getKodCawangan();
        KodBatalDokumenKewangan kbdk = dokumenKewangan.getKodBatal();
        KodStatusDokumenKewangan stat = kodStatusDokumenKewanganDAO.findById("B");

        String note = dokumenKewangan.getCatatan();

        dokumenKewangan = dokumenKewanganDAO.findById(idDokumenKewangan);
        InfoAudit info = dokumenKewangan.getInfoAudit();
        dokumenKewangan.setCatatan(note);
        dokumenKewangan.setKodBatal(kbdk);
        dokumenKewangan.setStatus(stat);
        info.setDiKemaskiniOleh(p);
        info.setTarikhKemaskini(now);
        dokumenKewangan.setDibatalOleh(p);
        dokumenKewangan.setTarikhBatal(now);

        manager.updateDokKew(dokumenKewangan);

        //Dokumen Bayar
        List<CaraBayaran> cbList = new ArrayList<CaraBayaran>();

        List<DokumenKewanganBayaran> listDKB = new ArrayList<DokumenKewanganBayaran>();
        String[] n1 = {"dokumenKewangan"};
        Object[] v1 = {dokumenKewangan};
        List<DokumenKewanganBayaran> dkbList = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);
        LOG.debug("dkbList:" + dkbList.size());
        for (DokumenKewanganBayaran dkb : dkbList) {
            CaraBayaran cb = dkb.getCaraBayaran();
            cbList.add(cb);
        }

        for (CaraBayaran cb : cbList) {
            listDKB.addAll(cb.getSenaraiDokumenKewanganBayaran());
            LOG.debug("ListDKB.size :" + listDKB.size());
        }

        ArrayList<String> listKewDok = new ArrayList<String>();
        for (DokumenKewanganBayaran ob : listDKB) {           
            ob.setDibatalOleh(p);
            ob.setTarikhBatal(now);
            ob.setAktif('T');

            listKewDok.add(ob.getDokumenKewangan().getIdDokumenKewangan());
            dkList.add(ob);
        }
        manager.updateDokKewBayar(dkList);
            LOG.debug("before listKewDok.size :" + listKewDok.size());
            listKewDok = removeDuplicate(listKewDok);
            LOG.debug("after listKewDok.size :" + listKewDok.size());
            for (String str : listKewDok) {
                LOG.info("str :" + str);
            }
        setBtn(true);
        setBtn2(true);
        setFlag1(false);
        selectList();

        addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");

        return new ForwardResolution("/WEB-INF/jsp/utiliti/pembatalan_resit_2.jsp");
    }

    public Resolution cetak() throws FileNotFoundException, ParseException {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();

        addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");
        Date now1 = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now1);
        String idKew = getContext().getRequest().getParameter("idKew");

        String documentPath = File.separator + "tmp" + File.separator;
        String path = tarikh + File.separator + String.valueOf(idKew);
        reportUtil.generateReport("hasilSuratGantiCekTakLakuResit.rdf",
                new String[]{"p_id_resit"},
                new String[]{idKew},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);

        setBtn2(true);
        setBtn(true);
//        selectList();
        setFlag1(false);
//        selectList();
        return new StreamingResolution("application/pdf", fis);

    }

    public Resolution savePelbagai() {
        LOG.info("tempBtnPindah :" + tempBtnPindah);
        LOG.info("savePelbagai) btnPindah :" + btnPindah);
        btnPindah = tempBtnPindah;
        setBtnPindah(btnPindah);
        LOG.info("savePelbagai) btnPindah :" + btnPindah);

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna p = ctx.getUser();
        java.util.Date now = new java.util.Date();

        KodCawangan caw = p.getKodCawangan();
        KodBatalDokumenKewangan kbdk = dokumenKewangan.getKodBatal();

        String note = dokumenKewangan.getCatatan();

        dokumenKewangan.setCatatan(note);
        dokumenKewangan.setKodBatal(kbdk);

        KodStatusTransaksiKewangan status = getKodStatusTransaksiKewanganDAO().findById('B');
        KodStatusDokumenKewangan stat = kodStatusDokumenKewanganDAO.findById("B");
        List<CaraBayaran> cbList = new ArrayList<CaraBayaran>();

        List<DokumenKewanganBayaran> listDKB = new ArrayList<DokumenKewanganBayaran>();
        String[] n1 = {"dokumenKewangan"};
        Object[] v1 = {dokumenKewangan};
        List<DokumenKewanganBayaran> dkbList = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);
        LOG.debug("dkbList:" + dkbList.size());
        for (DokumenKewanganBayaran dkb : dkbList) {
            CaraBayaran cb = dkb.getCaraBayaran();
            cbList.add(cb);
        }

        for (CaraBayaran cb : cbList) {
            listDKB.addAll(cb.getSenaraiDokumenKewanganBayaran());
            LOG.debug("ListDKB.size :" + listDKB.size());
        }

        List<Transaksi> t = new ArrayList<Transaksi>();
        ArrayList<String> listKewDok = new ArrayList<String>();
        for (DokumenKewanganBayaran ob : listDKB) {
            String[] key = {"dokumenKewangan"};
            Object[] value = {ob.getDokumenKewangan()};
            transList = transaksiDAO.findByEqualCriterias(key, value, null);
            akaunList = new ArrayList<Akaun>();
            t.addAll(transList);
            ob.setDibatalOleh(p);
            ob.setTarikhBatal(now);
            ob.setAktif('T');

            listKewDok.add(ob.getDokumenKewangan().getIdDokumenKewangan());
        }
        LOG.debug("before listKewDok.size :" + listKewDok.size());
        listKewDok = removeDuplicate(listKewDok);
        LOG.debug("after listKewDok.size :" + listKewDok.size());
        for (String str : listKewDok) {
            LOG.info("str :" + str);
        }
        senaraiLaporanPPI = new ArrayList<LaporanPenyataPemungutItem>();
        LOG.info("account :" + account);
        LOG.info("tempAccount :" + tempAccount);
        LOG.info("noResit :" + noResit);
        if (tempAccount != null && account == null) {
            account = tempAccount;
        } //batal semua sekiranya (cara bayaran > 1 || cara bayaran = 1 dan cara bayaran bukan tunai || resit > 1 dan cara bayaran > 1 || resit > 1 dan cara bayaran = 1 dan cara bayaran bukan tunai)
        else if ((cbList.size() > 1) || (cbList.size() == 1 && !cbList.get(0).getKodCaraBayaran().getKod().equals("T")) || (listKewDok.size() > 1 && cbList.size() > 1)
                || (listKewDok.size() > 1 && cbList.size() == 1 && !cbList.get(0).getKodCaraBayaran().getKod().equals("T"))) {

            for (Transaksi trans : t) {
                if (trans.getStatus().getKod() == 'T') {
                    InfoAudit info = trans.getInfoAudit();
                    info.setDiKemaskiniOleh(p);
                    info.setTarikhKemaskini(now);
                    trans.setKodBatal(kbdk);
                    trans.setStatus(status);
                    trans.setInfoAudit(info);
                    trans.getDokumenKewangan().setCatatan(note);
                    trans.getDokumenKewangan().setKodBatal(kbdk);
                    trans.getDokumenKewangan().setDibatalOleh(p);
                    trans.getDokumenKewangan().setStatus(stat);
                    trans.getDokumenKewangan().setTarikhBatal(now);
                    InfoAudit info2 = new InfoAudit();
                    info2.setDimasukOleh(p);
                    info2.setTarikhMasuk(now);
                    info2.setDiKemaskiniOleh(p);
                    info2.setTarikhKemaskini(now);
                    trans.getDokumenKewangan().setInfoAudit(info2);
                    trans.getDokumenKewangan().setDibatalOleh(p);

                    amaun = trans.getDokumenKewangan().getAmaunBayaran();
                    d = trans.getAmaun();
                    akDebit = trans.getAkaunDebit();
                    if (akDebit != null) {
                        akaunList.add(akDebit);
                    }
                    if (akDebit != null) {
                        baki1 = akDebit.getBaki();
                        tol = baki1.subtract(d);
                    }
                    if (amaun == null) {
                        amaun = BigDecimal.ZERO;
                    }
                    akDebit.setBaki(tol);

                    //create new transaksi for accounting
                    Transaksi transNew = new Transaksi();
                    transNew.setCawangan(trans.getCawangan());
                    transNew.setKodTransaksi(trans.getKodTransaksi());
                    transNew.setUntukTahun(trans.getUntukTahun());
                    transNew.setTahunKewangan(Integer.parseInt(yy.format(new java.util.Date())));
                    transNew.setAkaunKredit(trans.getAkaunDebit());
                    transNew.setKuantiti(1);
                    transNew.setAmaun(trans.getAmaun());
                    KodStatusTransaksiKewangan statusAktif = kodStatusTransaksiKewanganDAO.findById('A');
                    transNew.setStatus(statusAktif);
                    transNew.setInfoAudit(info2);
                    manager.save(transNew);
                    LOG.info("1) transNew: id =" + transNew.getIdTransaksi());
                }
                // to update penyata pemungut table
                String[] namePP = {"idTransaksi"};
                Object[] valuePP = {trans.getIdTransaksi()};
                List<LaporanPenyataPemungutItem> senaraiLaporan = laporanPenyataPemungutDAO.findByEqualCriterias(namePP, valuePP, null);
                for (LaporanPenyataPemungutItem LPPI : senaraiLaporan) {
                    LPPI.setStatus('B'); // B=Batal, A=aktif
                    senaraiLaporanPPI.add(LPPI);
                }

                // for cetakBatal parameter
                idKew.add(trans.getDokumenKewangan().getIdDokumenKewangan());
            }

            manager.updateAll(dokumenKewangan, akaunList, transList);
            // to update penyata pemungut table
            LOG.info("start update penyata pemungut");
            manager.updatePenyataPemungut(senaraiLaporanPPI);
            LOG.info("finish update penyata pemungut");

        } else {

            String[] key = {"dokumenKewangan"};
            Object[] value = {dokumenKewangan};
            transList = transaksiDAO.findByEqualCriterias(key, value, null);

            for (Transaksi trans : transList) {
                InfoAudit info = trans.getInfoAudit();
                info.setDiKemaskiniOleh(p);
                info.setTarikhKemaskini(now);
                trans.setKodBatal(kbdk);
                trans.setStatus(status);
                trans.setInfoAudit(info);
                trans.getDokumenKewangan().setCatatan(note);
                trans.getDokumenKewangan().setKodBatal(kbdk);
                trans.getDokumenKewangan().setStatus(stat);
                trans.getDokumenKewangan().setTarikhBatal(now);
                InfoAudit info2 = new InfoAudit();
                info2.setDimasukOleh(p);
                info2.setTarikhMasuk(now);
                info2.setDiKemaskiniOleh(p);
                info2.setTarikhKemaskini(now);
                trans.getDokumenKewangan().setInfoAudit(info);

                trans.getDokumenKewangan().setDibatalOleh(p);
                akDebit = trans.getAkaunDebit();
                amaun = trans.getDokumenKewangan().getAmaunBayaran();

                if (amaun == null) {
                    amaun = BigDecimal.ZERO;
                }
                if (akDebit != null) {
                    baki1 = akDebit.getBaki();

                }

                //create new transaksi for accounting
                Transaksi transNew = new Transaksi();
                transNew.setCawangan(trans.getCawangan());
                transNew.setKodTransaksi(trans.getKodTransaksi());
                transNew.setUntukTahun(trans.getUntukTahun());
                transNew.setTahunKewangan(Integer.parseInt(yy.format(new java.util.Date())));
                transNew.setAkaunKredit(trans.getAkaunDebit());
                transNew.setKuantiti(1);
                transNew.setAmaun(trans.getAmaun());
                KodStatusTransaksiKewangan statusAktif = kodStatusTransaksiKewanganDAO.findById('A');
                transNew.setStatus(statusAktif);
                transNew.setInfoAudit(info2);
                manager.save(transNew);
                LOG.info("2) transNew: id =" + transNew.getIdTransaksi());

                // to update penyata pemungut table
                String[] namePP = {"idTransaksi"};
                Object[] valuePP = {trans.getIdTransaksi()};
                List<LaporanPenyataPemungutItem> senaraiLaporan = laporanPenyataPemungutDAO.findByEqualCriterias(namePP, valuePP, null);
                for (LaporanPenyataPemungutItem LPPI : senaraiLaporan) {
                    LPPI.setStatus('B'); // B=Batal, A=aktif
                    senaraiLaporanPPI.add(LPPI);
                }

                // for cetakBatal parameter
                idKew.add(trans.getDokumenKewangan().getIdDokumenKewangan());
            }

            manager.update(dokumenKewangan, transList);

            tol = baki1.subtract(amaun);

            akDebit.setBaki(tol);
            manager.updateAkaunDebit(akDebit);

            // to update penyata pemungut table
            LOG.info("start update penyata pemungut");
            manager.updatePenyataPemungut(senaraiLaporanPPI);
            LOG.info("finish update penyata pemungut");
        }

        idKewDok = new String();
        idKewDokStatic = new String();
//        List<String> idKewTemp = new ArrayList<String>();
//        for (int i = 0; i < listDKB.size(); i++) {
//            DokumenKewanganBayaran tr = listDKB.get(i);
//            LOG.debug("tr.getIdKewanganBayaran() :"+tr.getIdKewanganBayaran());
//            if (tr.getDokumenKewangan().getStatus().getKod().equals("B")) {
//                String tmp1 = tr.getDokumenKewangan().getIdDokumenKewangan();
//                    idKew.add(tmp1);
//            }
//        }
        LOG.info("before | idKew.size :" + idKew.size());
        idKew = removeDuplicate(idKew);
        LOG.info("after | idKew.size :" + idKew.size());

        // start checking permohonan for notifikasi, 1resit=1notifikasi
        for (String string : idKew) {
            String[] namedk = {"dokumenKewangan.idDokumenKewangan"};
            Object[] valuedk = {string};
            List<Transaksi> tl = transaksiDAO.findByEqualCriterias(namedk, valuedk, null);
            ArrayList<String> pmohonan = new ArrayList();
            String kodCawangan = null;
            for (Transaksi tsaksi : tl) {
                if (tsaksi.getPermohonan() != null) {
                    pmohonan.add(tsaksi.getPermohonan().getIdPermohonan());
                    kodCawangan = tsaksi.getPermohonan().getCawangan().getKod();
                }
            }

            if (pmohonan.size() > 0) {
                pmohonan = removeDuplicate(pmohonan);
                String tajuk = "Makluman Pembatalan No. Resit " + string;
                String msg = "No. Resit " + string + " untuk perserahan/permohonan ";
                for (int i = 0; i < pmohonan.size(); i++) {
                    msg += pmohonan.get(i);
                    if (i + 1 < pmohonan.size()) {
                        msg += ", ";
                    }
                }
                msg += " telah dibatalkan.";
                //start pembatalan permohonan BPEL
                String hasilBatal = "";
                for (String pmohon : pmohonan) {
                    LOG.info("idPermohonan utk pembatalan :" + pmohon);
                    Permohonan mohon = permohonanDAO.findById(pmohon);
                    hasilBatal += gipw.getBatalPermohonan(mohon, note, p);
                    hasilBatal += ", ";
                }

                if (!hasilBatal.contentEquals("gagal")) {
                    msg += " Permohonan tersebut juga telah dibatalkan.";
                }
                //finish pembatalan permohonan BPEL
                KodCawangan kodC = kodCawanganDAO.findById(kodCawangan); // Cawangan for permohonan/perserahan
                Notifikasi notifikasi = new Notifikasi();
                notifikasi.setTajuk(tajuk);
                notifikasi.setCawangan(kodC);
                List<KodPeranan> senaraiPeranan = new ArrayList<KodPeranan>();
                senaraiPeranan.add(kodPerananDAO.findById(khconf.getProperty("kptDaftar"))); //KETUA PEMBANTU TADBIR PENDAFATRAN
                senaraiPeranan.add(kodPerananDAO.findById(khconf.getProperty("pendaftar"))); //Pendaftar //suggested by wan.fairul 20062011
                notifikasi.setMesej(msg);
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(p);
                ia.setTarikhMasuk(now);
                notifikasi.setInfoAudit(ia);
                if (senaraiPeranan.size() > 0) {
                    notifikasiService.addRolesToNotifikasi(notifikasi, kodC, senaraiPeranan);
                }
                LOG.info("mesej notifikasi :" + msg);
            }
        }
        // stop checking permohonan for notifikasi

        LOG.debug("idKew.size :" + idKew.size());
        for (int j = 0; j < idKew.size(); j++) {
            if (j > 0 && j < idKew.size()) {
                idKewDok += ",";
            }
            idKewDok += idKew.get(j);
        }
        idKewDokStatic = idKewDok;
        LOG.debug("idKewDokStatic:" + idKewDokStatic);

        setBtn(true);
        setBtn2(true);
        setFlag1(false);
        selectList();

        addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");

        return new ForwardResolution("/WEB-INF/jsp/hasil/pembatalan_resit_2.jsp");
    }

    public Resolution saveN91() throws FileNotFoundException, ParseException {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna p = ctx.getUser();
        Date now = new Date();
        InfoAudit info = p.getInfoAudit();
        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = p.getKodCawangan();
        KodBatalDokumenKewangan kbdk = dokumenKewangan.getKodBatal();

        String note = dokumenKewangan.getCatatan();
        dokumenKewangan.setCatatan(note);
        dokumenKewangan.setKodBatal(kbdk);

        KodStatusTransaksiKewangan status = getKodStatusTransaksiKewanganDAO().findById('B');
        KodStatusDokumenKewangan stat = kodStatusDokumenKewanganDAO.findById("B");

        List<CaraBayaran> cbList = new ArrayList<CaraBayaran>();
        List<DokumenKewanganBayaran> listDKB = new ArrayList<DokumenKewanganBayaran>();
        String[] n1 = {"dokumenKewangan"};
        Object[] v1 = {dokumenKewangan};
        List<DokumenKewanganBayaran> dkbList = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);
        for (DokumenKewanganBayaran dkb : dkbList) {
            CaraBayaran cb = dkb.getCaraBayaran();
            cbList.add(cb);
        }
        for (CaraBayaran cb : cbList) {
            listDKB.addAll(cb.getSenaraiDokumenKewanganBayaran());
        }

        List<Transaksi> t = new ArrayList<Transaksi>();
        for (DokumenKewanganBayaran ob : listDKB) {
            String[] key = {"dokumenKewangan"};
            Object[] value = {ob.getDokumenKewangan()};
            transList = transaksiDAO.findByEqualCriterias(key, value, null);
            akaunList = new ArrayList<Akaun>();
            t.addAll(transList);
            ob.setDibatalOleh(p);
            ob.setTarikhBatal(now);
            ob.setAktif('T');

        }
        if (cbList.size() > 1) {
            String temp1 = " ";
            for (int i = 0; i < t.size(); i++) {
                Transaksi tr = t.get(i);

                if (!tr.getDokumenKewangan().getIdDokumenKewangan().matches(temp1)) {

                    temp1 = tr.getDokumenKewangan().getIdDokumenKewangan();
                    t.remove(i);

                }
            }
        }
        for (Transaksi trans : t) {

            trans.setKodBatal(kbdk);
            trans.setStatus(status);
            trans.getDokumenKewangan().setCatatan(note);
            trans.getDokumenKewangan().setKodBatal(kbdk);
            trans.getDokumenKewangan().setDibatalOleh(p);
            trans.getDokumenKewangan().setStatus(stat);
            trans.getDokumenKewangan().setTarikhBatal(now);
            trans.getDokumenKewangan().setInfoAudit(info);
            trans.setInfoAudit(info);
            akKredit = trans.getAkaunKredit();
            akDebit = trans.getAkaunDebit();
//            debit = trans.getAmaun();

            if (akKredit != null) {
                akaunList.add(akKredit);
            }
            if (akDebit != null) {
                akaunList.add(akDebit);
            }

            if (amaun == null) {

                amaun = BigDecimal.ZERO;
            }

            if (akKredit != null) {
                amaun = trans.getAmaun();
                value = amaun.add(amaun);
                LOG.debug("amaun:" + amaun);
                baki = akKredit.getBaki();
                LOG.debug("BAKI:" + baki);
                baki = baki.add(value);
                LOG.debug("baki slps:" + baki);
                trans.getAkaunDebit().setBaki(baki);
                baki1 = akDebit.getBaki();
                tol = baki1.subtract(value);
                trans.getAkaunDebit().setBaki(tol);
            }
//            if (akDebit != null) {
//                baki1 = akDebit.getBaki();
//            }

//            if ((trans.getAkaunDebit() != null) && (trans.getAkaunKredit() != null)) {
//
////                amaun = trans.getDokumenKewangan().getAmaunBayaran();
//
//                System.out.println("amaun:"+amaun);
//
////                baki = baki.add(amaun);
//                tol = baki1.subtract(amaun);
//                trans.getAkaunKredit().setBaki(baki);
//                trans.getAkaunDebit().setBaki(tol);
//            }
        }

//        manager.updateAll(dokumenKewangan, akaunList, transList);
        setBtn(true);
        addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");

        return new ForwardResolution("/WEB-INF/jsp/hasil/pembatalan_resit_2.jsp");

    }

    public Resolution test() {
        addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");
        selectList();
        setFlag1(false);
        setBtn(true);
        setBtn2(true);
        idKewDok = idKewDokStatic;
        LOG.info("test: idKewDok :" + idKewDok);
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

    public static void setIdKewDokStatic(String idKewDokStatic) {
        UtilitiPembatalanResitActionBean.idKewDokStatic = idKewDokStatic;
    }

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

    public static void setTempBtnPindah(String tempBtnPindah) {
        UtilitiPembatalanResitActionBean.tempBtnPindah = tempBtnPindah;
    }

    public static String getTempAccount() {
        return tempAccount;
    }

    public static void setTempAccount(String tempAccount) {
        UtilitiPembatalanResitActionBean.tempAccount = tempAccount;
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
}
