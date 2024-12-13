/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nurfaizati
 */
package etanah.view.stripes.hasil;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.Akaun;
import etanah.model.CaraBayaran;
import etanah.model.DokumenKewangan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelum;
import etanah.model.InfoAudit;
//import etanah.model.KodAgensiKutipan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.Pengguna;
import etanah.model.Pihak;
import etanah.model.SejarahTransaksi;
import java.util.List;
import etanah.model.Transaksi;
import etanah.model.etapp.AgensiHakmilik;
import etanah.report.ReportUtil;
import etanah.service.HakmilikService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Session;
import org.hibernate.Query;

@Wizard(startEvents = {"selectTransaction", "hakmilikDetail", "pembayarDetail", "tambah", "baru", "save", "penyukatanBPM", "infoPembayar", "hakmilikPindahan", "bilCetakPenyata"})
@UrlBinding("/hasil/pertanyaan_hakmilik")
public class PertanyaanHakmilikActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PertanyaanHakmilikActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    private String suratAlamat1;
    private String suratAlamat2;
    private String suratAlamat3;
    private String suratAlamat4;
    private String suratPoskod;
    private String suratNegeri;
    private String email;
    private String noTelefonBimbit;
    private String noTelefon1;
    private String warnaKP;
    private String idDokumenKewangan;
    private KodJenisPengenalan jenisPengenalan;
    private Hakmilik hakmilik;
    private DokumenKewangan dokumenKewangan;
    private String idHakmilik;
    private String noAkaun1;
    private Pihak pemegang;
    private Pihak pihak;
    private String pegang;
    private List<Akaun> list;
    private List<Akaun> list2;
    private Akaun akaun;
    private String noAkaun;
    private BigDecimal baki;
    private int radioBtn;
    private int bilCetakPenyata;
    private boolean flag = false;
    private boolean btn = true;
    private boolean editFlg = false;
    private String btn1;
    private String btn2;
    private BigDecimal amaun;
    private double denda;
    private BigDecimal y;
    private BigDecimal w;
    private BigDecimal z;
    private BigDecimal x;
    private BigDecimal jum = new BigDecimal(0.00);
    private BigDecimal amaunRemesyen = new BigDecimal(0.00);
    private BigDecimal jumDenda;
    private BigDecimal notis;
    private BigDecimal jumlahCaj = new BigDecimal(0.00);
    private List<Transaksi> transList;
    private List<HakmilikPihakBerkepentingan> pihakList;
    private List<SejarahTransaksi> sejarahList;
    private String daerah;
    private List<KodBandarPekanMukim> senaraiBPM;
    private long idPihak;
    private Pengguna pengguna;
    private boolean del = true;
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<HakmilikSebelum> listHakmilikSebelum = new ArrayList();
    private boolean visible = true;
    private int bil = 0;
    private boolean button = false;
    private List<HakmilikPihakBerkepentingan> listPihakP = new ArrayList();
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    private String kodNegeri;
    public String selectedTab = "0";
    private String kodDaerah;
    private String kodHakmilik;
    private boolean tukarGanti = false;
    private boolean datun;
    private AgensiHakmilik agensiHakmilik;
//    private KodAgensiKutipan kodAgensiKutipan;
//    @Inject
//    KodAgensiKutipanDAO kodAgensiKutipanDAO;
    @Inject
    private AkaunDAO akaunDAO;
    @Inject
    private KutipanHasilActionBean hasil;
    @Inject
    private PihakDAO pihakDAO;
    @Inject
    private SejarahTransaksiDAO sejarahTransaksiDAO;
    @Inject
    private TransaksiDAO transaksiDAO;
    @Inject
    private DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    etanah.kodHasilConfig khconf;
    @Inject
    HakmilikService hakmilikService;

    @DefaultHandler
    public Resolution selectTransaction() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";

        }

        return new ForwardResolution("/WEB-INF/jsp/hasil/pertanyaan_cukai_1.jsp");

    }

    public Resolution penyukatanBPM() {

        String kodDaerah = getDaerah();
        LOG.debug("kod Daerah :" + kodDaerah);
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodDaerah == null || kodDaerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah);
        }
        LOG.debug("sql :" + q.getQueryString());
        senaraiBPM = q.list();
        LOG.debug("q.list() :" + q.list().size());
        return new ForwardResolution("/WEB-INF/jsp/hasil/pertanyaan_cukai_1.jsp").addParameter("popup", "true");

    }

    public Resolution search() {
        setPengguna((Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER));

        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
//            __pg_start = (Integer.parseInt(page) - 1) * __pg_max_records;
//            __pg_max_records = __pg_start + __pg_max_records;
        }

        list = hakmilikService.findAll(getContext().getRequest().getParameterMap(), get__pg_start(), get__pg_max_records(), getPengguna().getKodCawangan().getKod());
        set__pg_total_records(hakmilikService.getTotalRecord(getContext().getRequest().getParameterMap(), getPengguna().getKodCawangan().getKod()).intValue());


        if (list.size() > 0) {
            Akaun ak = new Akaun();
            ak = getList().get(0);
            setBtn(false);
        }

        setFlag(true);
        penyukatanBPM();
        return new ForwardResolution("/WEB-INF/jsp/hasil/pertanyaan_cukai_1.jsp");
    }

    @ValidationMethod(on = "selectList")
    public void validateRadio(ValidationErrors errors) {

        if (idHakmilik == null || noAkaun == null) {

            errors.add("radioButton", new SimpleError("Sila Klik Pada Butang Pilihan. Isikan Maklumat Untuk Carian Semula"));
            search();
            setFlag(true);
        }
    }
//
//    @ValidationMethod(on = "search")
//    public void validateField(ValidationErrors errors) {
//
//        if ((hakmilik == null)) {
//            errors.add(" ", new SimpleError("Sila Masukkan Salah Satu Daripada Maklumat Dibawah "));
//        }
//    }

    public Resolution kembali1() {

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        return new JSP("/hasil/kutipan_hasil_1.jsp").addParameter("pop", "true");

    }

    public Resolution selectList() {
        setBtn1("none");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        akaun = akaunDAO.findById(noAkaun);
        transList = new ArrayList<Transaksi>();

        String[] name = {"hakmilik"};
        Object[] value = {getHakmilik()};
        List<Akaun> list = akaunDAO.findByEqualCriterias(name, value, null);
        LOG.debug("size:" + list.size());
        for (int i = 0; i < list.size(); i++) {
            Akaun ak = list.get(i);
            if (ak.getKodAkaun().getKod().equals("AC")) {
                if (ak.getBaki() == null) {
                    ak.setBaki(BigDecimal.ZERO);
                }
                akaun = akaunDAO.findById(ak.getNoAkaun());
                baki = ak.getBaki();
                pihak = ak.getPemegang();
                pihakList = ak.getHakmilik().getSenaraiPihakBerkepentingan();//
                List<Transaksi> tempList = new ArrayList<Transaksi>();
                if (!ak.getKodAkaun().getKod().equals("ACT")) {
                    tempList = ak.getSemuaTransaksi();
                    LOG.debug("tempList.size : "+tempList.size());
                }

                for (int x = 0; x < tempList.size(); x++) {
                    Transaksi t = tempList.get(x);
                    if (t.getAkaunKredit() != null) {
                        if (t.getAkaunKredit().getNoAkaun().equals(idHakmilik)) {

                            double d = t.getAmaun().doubleValue();
                            t.setAmaun(new BigDecimal(d));
                            transList.add(t);

                        }
                    }
                    if (t.getAkaunDebit() != null) {
                        if (t.getAkaunDebit().getNoAkaun().equals(idHakmilik)) {

                            transList.add(t);
                        }
                    }
                    if ("05".equals(conf.getProperty("kodNegeri"))) {
                        LOG.debug("here n9");

                        jumlahCaj = baki;
                        y = BigDecimal.valueOf(0.05);
//                    double x1 = (jumlahCaj.multiply(y).doubleValue());
//                    System.out.println("x1:" +x1);
                        if (baki.compareTo(BigDecimal.ZERO) <= 0) {
                            z = BigDecimal.valueOf(0.00);
                            jumlahCaj = jumlahCaj.multiply(BigDecimal.ZERO);
                            jumDenda = jumlahCaj.add(new BigDecimal(denda));
                            notis = jumDenda.add(z);
                            jumlahCaj = baki;
                            double x1 = (jumlahCaj.multiply(y).doubleValue());


                        } else if (baki.equals(BigDecimal.ZERO)) {
                            z = BigDecimal.valueOf(0.00);
                            jumDenda = jumlahCaj.add(new BigDecimal(denda));
                            notis = jumDenda.add(z);
//                    jumlahCaj = baki;
                            double x1 = (jumlahCaj.multiply(y).doubleValue());


                        } else if ((baki.compareTo(BigDecimal.ZERO) > 0)) {
                            if ((t.getAkaunDebit() != null) && (t.getAkaunDebit().getNoAkaun().equals(idHakmilik))) {


                                amaun = t.getAmaun();
                                jum = jum.add(amaun);
                                jumlahCaj = baki;
                            }
                            if (t.getPermohonan() != null) {

                                amaunRemesyen = t.getAmaun();
                                jum = jum.add(amaunRemesyen);


                            }
                            //substract with akaun kredit
                            LOG.debug("jum sblm :"+jum);
                            if ((t.getAkaunKredit() != null) && (t.getAkaunKredit().getNoAkaun().equals(idHakmilik))) {
                                jum = jum.subtract(t.getAmaun());
                                LOG.debug("jum :"+jum);
                            }
                            double x1 = (jum.multiply(y).doubleValue());

                            z = BigDecimal.valueOf(10.00);
                            denda = Math.ceil(x1);
                            jumDenda = jum.add(new BigDecimal(denda));
                            notis = jumDenda.add(z);
                        }
                    } else if ("04".equals(conf.getProperty("kodNegeri"))) { //melaka)
                        LOG.debug("here melaka");

                        if (ak.getHakmilik().getAkaunCukai().getBaki().intValue() > 100) {

                            y = BigDecimal.valueOf(0.10);
                            z = BigDecimal.valueOf(20.00);
                            jumlahCaj = jum;
                            jum = baki;
                            double x1 = (jumlahCaj.multiply(y).doubleValue());
                            denda = Math.round(x1);
                            jumDenda = jumlahCaj.add(new BigDecimal(denda));

                            notis = jumDenda.add(z);

                        } else if (ak.getHakmilik().getAkaunCukai().getBaki().intValue() > 50) {

                            double a = 10.00;
                            z = BigDecimal.valueOf(20.00);
                            jumlahCaj = jum;
                            jum = baki;
                            denda = a;
                            jumDenda = jumlahCaj.add(new BigDecimal(denda));
                            notis = jumDenda.add(z);

                        } else if (ak.getHakmilik().getAkaunCukai().getBaki().intValue() > 20) {

                            double a = 5.00;
                            z = BigDecimal.valueOf(20.00);
                            jumlahCaj = jum;
                            jum = baki;
//                    double x1 = jumlahCaj.add(y).doubleValue();
                            denda = a;
                            jumDenda = jumlahCaj.add(new BigDecimal(denda));
                            notis = jumDenda.add(z);

                        } else if (ak.getHakmilik().getAkaunCukai().getBaki().intValue() >= 5) {

                            double a = 1.50;
                            z = BigDecimal.valueOf(20.00);
                            jumlahCaj = jum;
                            jum = baki;
                            denda = a;
                            jumDenda = jumlahCaj.add(new BigDecimal(denda));
                            notis = jumDenda.add(z);

//                } else if (ak.getHakmilik().getAkaunCukai().getBaki().intValue() < 5) {
                        } else if (ak.getHakmilik().getAkaunCukai().getBaki().intValue() > 0) {
//                    y = BigDecimal.valueOf(1.00);
                            double a = 1.00;
                            z = BigDecimal.valueOf(20.00);
                            jumlahCaj = jum;
                            jum = baki;
                            denda = a;
                            jumDenda = jumlahCaj.add(new BigDecimal(denda));
                            notis = jumDenda.add(z);

                        } //klu bayar terlebih
                        else if (ak.getHakmilik().getAkaunCukai().getBaki().intValue() <= 0) {

                            double a = 1.00;
                            z = BigDecimal.valueOf(00.00);

                            jumlahCaj = baki;
                            jum = baki;
                            jum = jum.multiply(BigDecimal.ZERO);//jum x zero
                            denda = 0.00;
                            jumDenda = jum.add(new BigDecimal(denda));
                            notis = jumDenda.add(z);

                        }
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/pertanyaan_cukai_2.jsp");
    }

    public Resolution hakmilikDetail() {


        if (hakmilik != null) {
            LOG.debug("hakmilik :" + hakmilik.getIdHakmilik());
            hakmilik = hakmilikDAO.findById(hakmilik.getIdHakmilik());
        }

        return new JSP("hasil/pertanyaan_cukai_3.jsp").addParameter("popup", "true");
    }

    public Resolution hakmilikPindahan() {
//        List<KodAgensiKutipan> senaraiKodAgensiKutipan = new ArrayList<KodAgensiKutipan>();

        if (dokumenKewangan != null) {
            dokumenKewangan = dokumenKewanganDAO.findById(dokumenKewangan.getIdDokumenKewangan());
//            for (Transaksi transAll : dokumenKewangan.getAkaun().getSemuaTransaksi()) {
//                if(transAll.getDokumenKewangan() == dokumenKewangan && transAll.getAkaunDebit() != null){
//                    if(!transAll.getAkaunDebit().getKodAkaun().getKod().equals(khconf.getProperty("akaunKutipanHarian"))){
//                        String[] name = {"akaun.noAkaun"};
//                        Object[] value = {transAll.getAkaunDebit().getNoAkaun()};
//                        System.out.println("transAll.getAkaunDebit().getNoAkaun(): "+transAll.getAkaunDebit().getNoAkaun());
//                        senaraiKodAgensiKutipan = kodAgensiKutipanDAO.findByEqualCriterias(name, value, null);
//                        System.out.println("senaraiKodAgensiKutipan.size :"+senaraiKodAgensiKutipan.size());
//                        for (KodAgensiKutipan kak : senaraiKodAgensiKutipan) {
//                            kodAgensiKutipan = kak;
//                        }
//                    }
//                }
//            }
        }
//        System.out.println("kodAgensiKutipan.id :"+kodAgensiKutipan.getKod());
        return new JSP("hasil/pertanyaan_cukai_4.jsp").addParameter("popup", "true");
    }

    public Resolution infoPembayar() {

        pegang = (String) getContext().getRequest().getParameter("idPegang");
        pihak = pihakDAO.findById(Long.parseLong(pegang));


        return new JSP("hasil/info_pembayar.jsp").addParameter("popup", "true");
    }

    public Resolution pembayarDetail() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String xx = getContext().getRequest().getParameter("idPihak");
        LOG.debug("pihak:" + xx);
        pihak = pihakDAO.findById(Long.parseLong(xx));
        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        return new JSP("hasil/edit_maklumat_pembayar.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution tambah() {

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noAkaun = getContext().getRequest().getParameter("noAkaun");

        listPihakP = hakmilikService.findAkaun(noAkaun);

        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        return new JSP("hasil/edit_maklumat_pembayar_2.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution baru() {

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noAkaun = getContext().getRequest().getParameter("noAkaun");

        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        return new JSP("hasil/edit_maklumat_pembayar_3.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution saveTambah() {

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String xx = getContext().getRequest().getParameter("idPihak");
        LOG.debug("pihak:" + xx);
        pihak = pihakDAO.findById(Long.parseLong(xx));
//        noAkaun = getContext().getRequest().getParameter("noAkaun");
        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);


        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna p = ctx.getUser();
        Date now = new Date();
        InfoAudit info = p.getInfoAudit();
        info.setDiKemaskiniOleh(p);
        info.setTarikhKemaskini(new java.util.Date());
        int year = Integer.parseInt(yy.format(now));
        KodCawangan caw = p.getKodCawangan();
        Pihak phk = pihakDAO.findById(idPihak);
        Akaun ak = akaunDAO.findById(noAkaun);
        ak.setPemegang(phk);
        ak.setInfoAudit(info);
       // ak.setCawangan(caw); //komen by eda 8/1/2019 coz kod_caw <> kod caw hakmilik

        hakmilikService.update(ak);

        return new JSP("hasil/edit_maklumat_pembayar_2.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution sejarahTransaksi() {


        sejarahList = new ArrayList<SejarahTransaksi>();

        return new JSP("common/sejarah_transaksi.jsp");
    }

    public Resolution refreshpage() {
        LOG.debug("refreshPage : start");
        save();
        LOG.debug("idHakmilik :"+idHakmilik);
//        selectList();
        LOG.debug("refreshPage : finish");
        return new JSP("common/maklumat_asas.jsp");
//        return new RedirectResolution(CarianHakmilik.class,"papar").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution refreshTambah() {
        LOG.debug("refreshPageTambah : start");
        saveTambah();
//        selectList();
        LOG.debug("refreshPageTambah : finish");
        return new JSP("common/maklumat_asas.jsp");

    }

    public Resolution refreshBaru() {
        LOG.debug("refreshPageBaru : start");
        saveBaru();
//        selectList();
        LOG.debug("refreshPageBaru : finish");
        return new JSP("common/maklumat_asas.jsp");

    }

    public Resolution save() {
        LOG.debug("+++ masuk save +++");
        String xx = getContext().getRequest().getParameter("idPihak");
        LOG.debug("pihak:" + xx);
        Pihak phk = pihakDAO.findById(Long.parseLong(xx));

        LOG.debug("phk.getIdPihak() : " + phk.getIdPihak());
        LOG.debug("phk.getNama() : " + phk.getNama());
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna p = ctx.getUser();
        Date now = new Date();
        InfoAudit info = p.getInfoAudit();
        info.setDimasukOleh(p);
        InfoAudit ia = new InfoAudit();
        info.setTarikhMasuk(new java.util.Date());

//        phk.setNama(pihak.getNama());
        phk.setSuratAlamat1(pihak.getSuratAlamat1());
        phk.setSuratAlamat2(pihak.getSuratAlamat2());
        phk.setSuratAlamat3(pihak.getSuratAlamat3());
        phk.setSuratAlamat4(pihak.getSuratAlamat4());
        phk.setSuratPoskod(pihak.getSuratPoskod());
        phk.setSuratNegeri(pihak.getSuratNegeri());
        phk.setEmail(pihak.getEmail());
        phk.setNoTelefon1(pihak.getNoTelefon1());
        phk.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
        phk.setJenisPengenalan(pihak.getJenisPengenalan());
        phk.setInfoAudit(info);
        hakmilikService.updatePemegang(phk);
        editFlg = true;
        LOG.debug("+++ abes save +++");
        return new ForwardResolution("/WEB-INF/jsp/hasil/edit_maklumat_pembayar.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);

    }
    
    public Resolution saveBaru() {
        LOG.debug("++++ saveBaru ++++");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noAkaun = getContext().getRequest().getParameter("noAkaun");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna p = ctx.getUser();
        Date now = new Date();
        InfoAudit info = p.getInfoAudit();
        info.setDimasukOleh(p);
        InfoAudit ia = new InfoAudit();
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = p.getKodCawangan();
        
        pihak.setInfoAudit(info);


        List<Akaun> listNoAkaun = hakmilikService.findNoAkaun(noAkaun);
        akaun = listNoAkaun.get(0);
        akaun.setPemegang(pihak);
        hakmilikService.updatePihak(pihak,akaun);
        LOG.debug("++++ abes saveBaru ++++");
        addSimpleMessage("Data berjaya disimpan. Sila klik 'Tutup'.");
        return new ForwardResolution("/WEB-INF/jsp/hasil/edit_maklumat_pembayar_3.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution kembali() {

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";

        }

        if (hakmilik != null) {
            hakmilik.setIdHakmilik("");
        }

        return new JSP("common/pertanyaan_hakmilik.jsp");
    }

    public Resolution bilCetakPenyata() throws FileNotFoundException {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        String idKew = getContext().getRequest().getParameter("idHakmilik");
        String results = null;
        Pengguna pguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        hakmilik = hakmilikDAO.findById(idKew);
        if (hakmilik != null) {
            try{
                Akaun ak = hakmilik.getAkaunCukai();

                ak.setBilCetakPenyata(ak.getBilCetakPenyata() + 1);
                ak.setInfoAudit(ia);
                //hakmilikService.updateBilanganCetak(ak); //remove by nad (08/01/2020): bil_cetak number(2), max until 99 only. 
                results = "berjaya";
            }catch(Exception ex){
                results = "gagal";
            }
        }
        return new StreamingResolution("text/plain", results);
    }

    public Resolution cetak() throws FileNotFoundException {
        LOG.debug("hakmilik cetak:" + idHakmilik);

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser(); //penggunaDAO.findById("admin");
        KodCawangan caw = pengguna.getKodCawangan();
        Date now1 = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        hakmilik = hakmilikDAO.findById(idHakmilik);
        if (hakmilik != null) {

            String[] name = {"hakmilik"};
            Object[] value = {getHakmilik()};
            List<Akaun> list = akaunDAO.findByEqualCriterias(name, value, null);

            Akaun ac = new Akaun();
            for (Akaun ak : list) {
                if (ak.getKodAkaun().getKod().equals("AC")) {
                    ac = ak;

                }
                LOG.debug("bil:" + ak.getBilCetakPenyata());
                ak.setBilCetakPenyata(ak.getBilCetakPenyata() + 1);
                ak.setInfoAudit(ia);
                hakmilikService.updateBilanganCetak(ak);
                LOG.debug("bil1:" + ak.getBilCetakPenyata());
            }
        }

        LOG.debug("hakmilik :" + hakmilik.getIdHakmilik());
        Date now = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        String idKew = getContext().getRequest().getParameter("idKew");
//        String idHakmilik = getContext().getRequest().getParameter("idhakmilik");
        String idHakmilik = hakmilik.getIdHakmilik();
        LOG.debug("hakmilik.getIdHakmilik():" + hakmilik.getIdHakmilik());
        String documentPath = File.separator + "tmp" + File.separator;
        String path = tarikh + File.separator + String.valueOf(idHakmilik);
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            reportUtil.generateReport("HSLResitBilCukaiNS003.rdf",
                    //
                    new String[]{"p_id_hakmilik"},
                    new String[]{idHakmilik},
                    documentPath + path, null);
        } else if ("04".equals(conf.getProperty("kodNegeri"))) {
            reportUtil.generateReport("HSLResitBilCukaiMLK001.rdf",
                    //
                    new String[]{"p_id_hakmilik"},
                    new String[]{idHakmilik},
                    documentPath + path, null);
        }

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);


    }

    public Resolution bayar() {
        hasil.search();
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<Akaun> getList() {
        return list;
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

    public int getRadioBtn() {
        return radioBtn;
    }

    public void setRadioBtn(int radioBtn) {
        this.radioBtn = radioBtn;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public List<HakmilikPihakBerkepentingan> getPihakList() {
        return pihakList;
    }

    public void setPihakList(List<HakmilikPihakBerkepentingan> pihakList) {
        this.pihakList = pihakList;
    }

    public Pihak getPemegang() {
        return pemegang;
    }

    public void setPemegang(Pihak pemegang) {
        this.pemegang = pemegang;
    }

    public double getDenda() {
        return denda;
    }

    public void setDenda(Double denda) {
        this.denda = denda;
    }

    public BigDecimal getJumDenda() {
        return jumDenda;
    }

    public void setJumDenda(BigDecimal jumDenda) {
        this.jumDenda = jumDenda;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public BigDecimal getZ() {
        return z;
    }

    public void setZ(BigDecimal z) {
        this.z = z;
    }

    public String getBtn1() {
        return btn1;
    }

    public void setBtn1(String btn1) {
        this.btn1 = btn1;
    }

    public String getBtn2() {
        return btn2;
    }

    public void setBtn2(String btn2) {
        this.btn2 = btn2;
    }

    public String getSuratAlamat1() {
        return suratAlamat1;
    }

    public void setSuratAlamat1(String suratAlamat1) {
        this.suratAlamat1 = suratAlamat1;
    }

    public String getSuratAlamat2() {
        return suratAlamat2;
    }

    public void setSuratAlamat2(String suratAlamat2) {
        this.suratAlamat2 = suratAlamat2;
    }

    public String getSuratAlamat3() {
        return suratAlamat3;
    }

    public void setSuratAlamat3(String suratAlamat3) {
        this.suratAlamat3 = suratAlamat3;
    }

    public String getSuratAlamat4() {
        return suratAlamat4;
    }

    public void setSuratAlamat4(String suratAlamat4) {
        this.suratAlamat4 = suratAlamat4;
    }

    public String getSuratPoskod() {
        return suratPoskod;
    }

    public void setSuratPoskod(String suratPoskod) {
        this.suratPoskod = suratPoskod;
    }

    public String getSuratNegeri() {
        return suratNegeri;
    }

    public void setSuratNegeri(String suratNegeri) {
        this.suratNegeri = suratNegeri;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public String getNoAkaun1() {
        return noAkaun1;
    }

    public void setNoAkaun1(String noAkaun1) {
        this.noAkaun1 = noAkaun1;
    }

    public List<Akaun> getList2() {
        return list2;
    }

    public void setList2(List<Akaun> list2) {
        this.list2 = list2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelefonBimbit() {
        return noTelefonBimbit;
    }

    public void setNoTelefonBimbit(String noTelefonBimbit) {
        this.noTelefonBimbit = noTelefonBimbit;
    }

    public String getNoTelefon1() {
        return noTelefon1;
    }

    public void setNoTelefon1(String noTelefon1) {
        this.noTelefon1 = noTelefon1;
    }

    public String getWarnaKP() {
        return warnaKP;
    }

    public void setWarnaKP(String warnaKP) {
        this.warnaKP = warnaKP;
    }

    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public BigDecimal getJum() {
        return jum;
    }

    public void setJum(BigDecimal jum) {
        this.jum = jum;
    }

    public BigDecimal getAmaunRemesyen() {
        return amaunRemesyen;
    }

    public void setAmaunRemesyen(BigDecimal amaunRemesyen) {
        this.amaunRemesyen = amaunRemesyen;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public int getBilCetakPenyata() {
        return bilCetakPenyata;
    }

    public void setBilCetakPenyata(int bilCetakPenyata) {
        this.bilCetakPenyata = bilCetakPenyata;
    }

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    /**
     * @return the sejarahList
     */
    public List<SejarahTransaksi> getSejarahList() {
        return sejarahList;
    }

    /**
     * @param sejarahList the sejarahList to set
     */
    public void setSejarahList(List<SejarahTransaksi> sejarahList) {
        this.sejarahList = sejarahList;
    }

    /**
     * @return the pegang
     */
    public String getPegang() {
        return pegang;
    }

    /**
     * @param pegang the pegang to set
     */
    public void setPegang(String pegang) {
        this.pegang = pegang;
    }

    /**
     * @return the pengguna
     */
    public Pengguna getPengguna() {
        return pengguna;
    }

    /**
     * @param pengguna the pengguna to set
     */
    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    /**
     * @return the del
     */
    public boolean isDel() {
        return del;
    }

    /**
     * @param del the del to set
     */
    public void setDel(boolean del) {
        this.del = del;
    }

    /**
     * @return the senaraiCaraBayaran
     */
    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    /**
     * @param senaraiCaraBayaran the senaraiCaraBayaran to set
     */
    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the bil
     */
    public int getBil() {
        return bil;
    }

    /**
     * @param bil the bil to set
     */
    public void setBil(int bil) {
        this.bil = bil;
    }

    /**
     * @return the button
     */
    public boolean isButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(boolean button) {
        this.button = button;
    }

    /**
     * @return the idDokumenKewangan
     */
    public String getIdDokumenKewangan() {
        return idDokumenKewangan;
    }

    /**
     * @param idDokumenKewangan the idDokumenKewangan to set
     */
    public void setIdDokumenKewangan(String idDokumenKewangan) {
        this.idDokumenKewangan = idDokumenKewangan;
    }

    public List<HakmilikSebelum> getListHakmilikSebelum() {
        return listHakmilikSebelum;
    }

    public void setListHakmilikSebelum(List<HakmilikSebelum> listHakmilikSebelum) {
        this.listHakmilikSebelum = listHakmilikSebelum;
    }

    /**
     * @return the listPihakP
     */
    public List<HakmilikPihakBerkepentingan> getListPihakP() {
        return listPihakP;
    }

    /**
     * @param listPihakP the listPihakP to set
     */
    public void setListPihakP(List<HakmilikPihakBerkepentingan> listPihakP) {
        this.listPihakP = listPihakP;
    }
    
    

//    public KodAgensiKutipan getKodAgensiKutipan() {
//        return kodAgensiKutipan;
//    }
//
//    public void setKodAgensiKutipan(KodAgensiKutipan kodAgensiKutipan) {
//        this.kodAgensiKutipan = kodAgensiKutipan;
//    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }
    
    public String getKodHakmilik() {
        return kodHakmilik;
    }
    
    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }
    
    public boolean getTukarGanti() {
        return tukarGanti;
    }
    
    public void setTukarGanti(boolean tukarGanti) {
        this.tukarGanti = tukarGanti;
    }
    
    public Boolean getDatun() {
        return datun;
    }

    public void setDatun(Boolean datun) {
        this.datun = datun;
    }
    
    public AgensiHakmilik getAgensiHakmilik() {
        return agensiHakmilik;
    }

    public void setAgensiHakmilik(AgensiHakmilik agensiHakmilik) {
        this.agensiHakmilik = agensiHakmilik;
    }

    public boolean isEditFlg() {
        return editFlg;
    }

    public void setEditFlg(boolean editFlg) {
        this.editFlg = editFlg;
    }
}

