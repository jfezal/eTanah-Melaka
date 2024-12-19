package etanah.view.stripes.hasil;

import etanah.dao.*;
import etanah.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.service.HakmilikService;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 * @author abdul.hakim
 * 01-April-2011
 **/
//@Wizard(startEvents = {"Step1","penyukatanBPM","Step3"})
@UrlBinding("/hasil/kemaskini_data")
public class KemaskiniMaklumatAkaunActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(KemaskiniMaklumatAkaunActionBean.class);
    private static boolean isDebug = logger.isDebugEnabled();
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/kemaskini_maklumat_akaun.jsp";
    private static final String NEXT_VIEW = "/WEB-INF/jsp/hasil/kemaskini_maklumat_akaun_1.jsp";
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    private Hakmilik hakmilik;
    private Akaun akaun;
    private LogAkaunKewangan logAkaun;
    private Pihak pihak;
    private Pengguna pengguna;
    private Transaksi transaksi;
    private static String kodNegeri;
    private String noAkaun;
    private String idHakmilik;
    private String daerah;
    private String bandarPekanMukim;
    private String kodStatusHakmilik;
    private String kodHakmilik;
    private String kodDaerah;
    private String caw;
    private String noHakmilik;
    private String lot;
    private String noLot;
    private String noPengenalan;
    private String namaPemilik;
    private String nama;
    private String rbtAkaun = "";
    private String catatan;
    private String selectedTab = "0";
    private String kodRemisyen = null;
    private String kodTransaksi = "";
    private String tahun = "";
    private String jenisTransaksi = "";
    private Long idTransaksi = 0L;
    private List<KodBandarPekanMukim> senaraiBPM = new ArrayList<KodBandarPekanMukim>();
    private List<Akaun> listAkaun = new ArrayList<Akaun>();
    private List<HakmilikPihakBerkepentingan> pihakList = new ArrayList<HakmilikPihakBerkepentingan>();
    private List<Transaksi> transList = new ArrayList<Transaksi>();
    private List<KodDaerah> senaraiKodDaerah = new ArrayList<KodDaerah>();
    private List<LogAkaunKewangan> senaraiSejarah = new ArrayList<LogAkaunKewangan>();
    private List<KodTransaksi> senaraiKodTransaksi = new ArrayList<KodTransaksi>();
    private BigDecimal cukai = new BigDecimal(0.00);
    private BigDecimal debit = new BigDecimal(0.00);
    private BigDecimal kredit = new BigDecimal(0.00);
    private BigDecimal denda = new BigDecimal(0.00);
    private BigDecimal tunggakan = new BigDecimal(0.00);
    private BigDecimal remisyen = new BigDecimal(0.00);
    private BigDecimal notis = new BigDecimal(0.00);
    private BigDecimal sum = new BigDecimal(0.00);
    private BigDecimal amaun = new BigDecimal(0.00);
    private static BigDecimal initCukai = new BigDecimal(0.00);
    private static BigDecimal initDebit = new BigDecimal(0.00);
    private static BigDecimal initKredit = new BigDecimal(0.00);
    private static BigDecimal initDenda = new BigDecimal(0.00);
    private static BigDecimal initTunggakan = new BigDecimal(0.00);
    private static BigDecimal initRemisyen = new BigDecimal(0.00);
    private static BigDecimal initNotis = new BigDecimal(0.00);
    private BigDecimal totalAmount= new BigDecimal(0.00);
    private boolean flag = false;
    private boolean checking = false;
    private boolean checkingBaki = false;
    private boolean btn = false;
    private boolean saveFlg = false;
    private boolean addFlg = false;
    @Inject
    private AkaunDAO akaunDAO;
    @Inject
    private PihakDAO pihakDAO;
    @Inject
    private KodTransaksiDAO kodTransaksiDAO;
    @Inject
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private etanah.kodHasilConfig hasil;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    KutipanHasilService hasilService;
    @Inject
    KutipanHasilManager manager;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    KodStatusAkaunDAO kodStatusAkaunDAO;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        idTransaksi = 0L;
        rbtAkaun = "";
        initCukai = new BigDecimal(0.00);
        saveFlg = false;
        addFlg = false;
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pguna = ctx.getUser();

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        } else {
            kodNegeri = "negeriSembilan";
        }

        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT a from etanah.model.KodDaerah a where a.kod = :kod");
        if(pguna.getKodCawangan().getDaerah() != null){
            q1.setString("kod", pguna.getKodCawangan().getDaerah().getKod());
        }else{
            q1.setString("kod", "00");
        }
        senaraiKodDaerah = q1.list();
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution penyukatanBPM() {
        logger.info("Kod Daerah : "+daerah);
        String kodDaerah = getDaerah();
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
        senaraiBPM = q.list();
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pguna = ctx.getUser();
        logger.info("pguna.getIdPengguna() : "+pguna.getIdPengguna());
        Query q1 = s.createQuery("SELECT a from etanah.model.KodDaerah a where a.kod = :kod");
        if(pguna.getKodCawangan().getDaerah() != null){
            logger.info("pguna.getKodCawangan().getDaerah().getKod() : "+pguna.getKodCawangan().getDaerah().getKod());
            q1.setString("kod", pguna.getKodCawangan().getDaerah().getKod());
        }else{
            q1.setString("kod", "00");
        }
            senaraiKodDaerah = q1.list();

        return new JSP("hasil/kemaskini_maklumat_akaun.jsp").addParameter("popup", "true");
//        return new ForwardResolution(DEFAULT_VIEW);

    }

    public Resolution pembayarDetail() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String xx = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.parseLong(xx));
        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        return new JSP("hasil/kemaskini_maklumat_pembayar.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution kemaskiniTransaksi() {
        String idTrans = getContext().getRequest().getParameter("idTrans");
        rbtAkaun = getContext().getRequest().getParameter("noAkaun");
        logger.info("idTrans : " + idTrans);
        transaksi = new Transaksi();
        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT tr FROM etanah.model.Transaksi tr WHERE tr.idTransaksi = :id");
        q1.setString("id", idTrans);
        transaksi = (Transaksi) q1.uniqueResult();
        idTransaksi = transaksi.getIdTransaksi();
        initCukai = transaksi.getAmaun();
        return new JSP("hasil/kemaskini_maklumat_akaun_2.jsp").addParameter("popup", "true").addParameter("idTrans", idTrans);
    }

    public Resolution kemaskiniTransaksi1(String idTrans) {
        logger.info("idTrans : " + idTrans);
        transaksi = new Transaksi();
        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT tr FROM etanah.model.Transaksi tr WHERE tr.idTransaksi = :id");
        q1.setString("id", idTrans);
        transaksi = (Transaksi) q1.uniqueResult();
        idTransaksi = transaksi.getIdTransaksi();
        initCukai = transaksi.getAmaun();
        return new JSP("hasil/kemaskini_maklumat_akaun_2.jsp").addParameter("popup", "true").addParameter("idTrans", idTrans);
    }

    public Resolution addNewTransaction() {
        rbtAkaun = getContext().getRequest().getParameter("noAkaun");
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT kt from etanah.model.KodTransaksi kt where kt.kod in ('61401','61402','76152','99011','99030','72457','61501','61502','76156') and kt.aktif = 'Y'");
        //untuk kod 99011 tidak digunakan SPEKS 16/06/2015
        senaraiKodTransaksi = q.list();

        return new JSP("hasil/kemaskini_maklumat_akaun_3.jsp").addParameter("popup", "true");
    }

    @HandlesEvent("Step2")
    public Resolution search() {
        rbtAkaun = "";
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
        }

//        listAkaun = hakmilikService.findAll(getContext().getRequest().getParameterMap(), get__pg_start(), get__pg_max_records(), pengguna.getKodCawangan().getKod());
//        set__pg_total_records(hakmilikService.getTotalRecord(getContext().getRequest().getParameterMap(), pengguna.getKodCawangan().getKod()).intValue());

        listAkaun = hasilService.findAll(getContext().getRequest().getParameterMap(), get__pg_start(), get__pg_max_records(), pengguna.getKodCawangan().getKod());
        set__pg_total_records(hasilService.getTotalRecord(getContext().getRequest().getParameterMap(), pengguna.getKodCawangan().getKod()).intValue());

        if (isDebug) {
            logger.debug("page_start = " + get__pg_start());
            logger.debug("max_records = " + get__pg_max_records());
            logger.debug("total record = " + get__pg_total_records());
        }

        for (int j = 1; j < listAkaun.size(); j++) {

            Akaun ak = new Akaun();
            ak = listAkaun.get(j);

            if (namaPemilik != null || noPengenalan != null) {
                pihakList = new ArrayList<HakmilikPihakBerkepentingan>();
                List<HakmilikPihakBerkepentingan> pihakList1 = new ArrayList<HakmilikPihakBerkepentingan>();

                pihakList = ak.getHakmilik().getSenaraiPihakBerkepentingan();
                pihakList1 = ak.getHakmilik().getSenaraiPihakBerkepentingan();

                String temp1 = namaPemilik;
                String temp2 = noPengenalan;

                for (int i = 0; i < pihakList.size(); i++) {
                    HakmilikPihakBerkepentingan hpk1 = pihakList.get(i);
                    nama = hpk1.getPihak().getNama();
                    noPengenalan = hpk1.getPihak().getNoPengenalan();
                    if ((temp1 != null) && (!hpk1.getPihak().getNama().equals(temp1))) {
                        pihakList1.remove(hpk1);
                    } else if ((temp2 != null) && (!hpk1.getPihak().getNoPengenalan().equals(temp2))) {
                        pihakList1.remove(hpk1);
                    }
                }
                if (pihakList != null) {
                    listAkaun.get(j).getHakmilik().setSenaraiPihakBerkepentingan(pihakList1);
                }
                namaPemilik = temp1;
                noPengenalan = temp2;
            }
        } //End of for loop (tulasi)
        flag = true;
        penyukatanBPM();

        return new JSP("hasil/kemaskini_maklumat_akaun.jsp");
    }

    @HandlesEvent("Step3")
    public Resolution papar() {
        cukai = new BigDecimal(0.00);
        debit = new BigDecimal(0.00);
        kredit = new BigDecimal(0.00);
        denda = new BigDecimal(0.00);
        tunggakan = new BigDecimal(0.00);
        remisyen = new BigDecimal(0.00);
        notis = new BigDecimal(0.00);

        initCukai = new BigDecimal(0.00);
        initDebit = new BigDecimal(0.00);
        initKredit = new BigDecimal(0.00);
        initDenda = new BigDecimal(0.00);
        initTunggakan = new BigDecimal(0.00);
        initRemisyen = new BigDecimal(0.00);
        initNotis = new BigDecimal(0.00);

        selectedTab = getContext().getRequest().getParameter("selectedTab");

        akaun = akaunDAO.findById(rbtAkaun);
        hakmilik = akaun.getHakmilik();
        pihak = akaun.getPemegang();

        if (akaun.getBaki().compareTo(new BigDecimal(0)) == -1) {
            kredit = akaun.getBaki().multiply(new BigDecimal(-1));
        }


        List<Transaksi> trList = akaun.getSemuaTransaksi();
        for (Transaksi tr : trList) {
            if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTanahSemasa"))) {
                cukai = tr.getAmaun();
                initCukai = cukai;
            }
            if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("dendaLewat"))) {
                denda = denda.add(tr.getAmaun());
                initDenda = denda;
            }
            if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTanahTunggakan"))) {
                tunggakan = tunggakan.add(tr.getAmaun());
                initTunggakan = tunggakan;
            }
            if (tr.getKodTransaksi().getKod().equals(hasil.getProperty("notis6A"))) {
                notis = tr.getAmaun();
                initNotis = notis;
            }
            if ((tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyen.remsb"))) || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyen.remts")))
                    || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyen.remri"))) || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyen.remtd")))
                    || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyenTanah"))) || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyenTunggak")))
                    || (tr.getKodTransaksi().getKod().equals(hasil.getProperty("remisyenDenda")))) {
                remisyen = tr.getAmaun();
                kodRemisyen = tr.getKodTransaksi().getKod();
                idTransaksi = tr.getIdTransaksi();
                initRemisyen = remisyen;
            }
        }

        boolean f = false;
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT t from etanah.model.Transaksi t where t.akaunKredit.noAkaun = :noAkaun AND t.status.kod = 'T' ");
        q.setString("noAkaun", akaun.getNoAkaun());
        List<Transaksi> tList = q.list();
        if (tList.size() > 0) {
            f = true;
            checking = true;
        }
        if (f) {
            List<Transaksi> listDebit = akaun.getSenaraiTransaksiDebit();
            List<Transaksi> listKredit = akaun.getSenaraiTransaksiKredit();

            BigDecimal totalDebit = new BigDecimal(0.00);
            BigDecimal totalKredit = new BigDecimal(0.00);

            for (Transaksi t : listDebit) {
                totalDebit = totalDebit.add(t.getAmaun());
            }

            for (Transaksi t : listKredit) {
                totalKredit = totalKredit.add(t.getAmaun());
            }

            debit = totalDebit.subtract(totalKredit);
            initDebit = debit;
        }
        
        
        BigDecimal totalDebit = new BigDecimal(0.00);
        BigDecimal totalKredit = new BigDecimal(0.00);
            
        List<Transaksi> listDebit = akaun.getSenaraiTransaksiDebit();
        List<Transaksi> listKredit = akaun.getSenaraiTransaksiKredit();
        for (Transaksi t : listDebit) {
            totalDebit = totalDebit.add(t.getAmaun());
        }

        for (Transaksi t : listKredit) {
            totalKredit = totalKredit.add(t.getAmaun());
        }
        totalAmount = totalDebit.subtract(totalKredit);
        if(totalAmount.compareTo(akaun.getBaki())!=0){
            checkingBaki = true;
        }

        Query qTrans = s.createQuery("SELECT tr FROM etanah.model.Transaksi tr WHERE tr.akaunDebit.noAkaun =:noAkaun "
                + "OR tr.akaunKredit.noAkaun =:noAkaun ORDER BY tr.infoAudit.tarikhMasuk");
        qTrans.setString("noAkaun", akaun.getNoAkaun());
        transList = qTrans.list();

        Query q1 = s.createQuery("SELECT a from etanah.model.LogAkaunKewangan a where a.akaun.noAkaun = :noAkaun ORDER BY a.tarikhMasuk");
        q1.setString("noAkaun", akaun.getNoAkaun());
        senaraiSejarah = q1.list();

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        pengguna = ctx.getUser();

        kodDaerah = (hakmilik.getIdHakmilik()).substring(2, 4);
        kodHakmilik = hakmilik.getKodHakmilik().getKod();
        caw = pengguna.getKodCawangan().getKod();

        return new JSP("hasil/tab_kemaskini_data.jsp");
//        return new ForwardResolution(NEXT_VIEW);
    }

    @HandlesEvent("Step4")
    public Resolution simpan() {
        Session s = sessionProvider.get();
        akaun = akaunDAO.findById(rbtAkaun);

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        pengguna = ctx.getUser();
//        KodCawangan caw = pengguna.getKodCawangan();
//        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if (cukai.compareTo(initCukai) != 0) {
            logger.info("inside if cukai");
            logAkaun = new LogAkaunKewangan();
            logAkaun.setCatatan(catatan);
            logAkaun.setDataLama(initCukai.toString());
            logAkaun.setDataBaru(cukai.toString());
            logAkaun.setPengguna(pengguna);
            logAkaun.setTarikhMasuk(new java.util.Date());
            logAkaun.setAkaun(akaun);
            logAkaun.setPerkara("61401 - CUKAI TANAH SEMASA");

            changeTax(akaun, s, cukai);

            manager.save(logAkaun);
        }

        if (tunggakan.compareTo(initTunggakan) != 0) {
            logger.info("inside if tunggakan");
            KodTransaksi kt = kodTransaksiDAO.findById(hasil.getProperty("cukaiTanahTunggakan"));

            logAkaun = new LogAkaunKewangan();
            logAkaun.setCatatan(catatan);
            logAkaun.setDataLama(initTunggakan.toString());
            logAkaun.setDataBaru(tunggakan.toString());
            logAkaun.setPengguna(pengguna);
            logAkaun.setTarikhMasuk(new java.util.Date());
            logAkaun.setAkaun(akaun);
            logAkaun.setPerkara(kt.getKod() + " - " + kt.getNama());

            BigDecimal tot = tunggakan.subtract(initTunggakan);
            if (initTunggakan.compareTo(new BigDecimal(0)) == 0) {
                createTransaction(akaun, tunggakan, pengguna, kt);
            } else {
                changeTransaction(akaun, s, tot, kt.getKod());
            }

            manager.save(logAkaun);
        }

        if (denda.compareTo(initDenda) != 0) {
            logger.info("inside if denda");
            KodTransaksi kt = kodTransaksiDAO.findById(hasil.getProperty("dendaLewat"));

            logAkaun = new LogAkaunKewangan();
            logAkaun.setCatatan(catatan);
            logAkaun.setDataLama(initDenda.toString());
            logAkaun.setDataBaru(denda.toString());
            logAkaun.setPengguna(pengguna);
            logAkaun.setTarikhMasuk(new java.util.Date());
            logAkaun.setAkaun(akaun);
            logAkaun.setPerkara(kt.getKod() + " - " + kt.getNama());

            BigDecimal tot = denda.subtract(initDenda);
            if (initDenda.compareTo(new BigDecimal(0)) == 0) {
                createTransaction(akaun, tot, pengguna, kt);
            } else {
                changeTransaction(akaun, s, tot, kt.getKod());
            }

            manager.save(logAkaun);
        }

        if (remisyen.compareTo(initRemisyen) != 0) {
            logger.info("inside if remisyen");
            KodTransaksi kt = kodTransaksiDAO.findById(hasil.getProperty("remisyenTanah"));

            logAkaun = new LogAkaunKewangan();
            logAkaun.setCatatan(catatan);
            logAkaun.setDataLama(initRemisyen.toString());
            logAkaun.setDataBaru(remisyen.toString());
            logAkaun.setPengguna(pengguna);
            logAkaun.setTarikhMasuk(new java.util.Date());
            logAkaun.setAkaun(akaun);
            logAkaun.setPerkara(kt.getKod() + " - " + kt.getNama());

            if (initRemisyen.compareTo(new BigDecimal(0)) == 0) {
                createTransactionRemisyen(akaun, remisyen, pengguna, kt);
//
//                kt = new KodTransaksi();
//                kt = kodTransaksiDAO.findById(kodRemisyen);
//                changeTransaction(akaun, s, tot, kt.getKod());
            } else if (!idTransaksi.toString().equals("0")) {
                Query q1 = s.createQuery("SELECT t FROM etanah.model.Transaksi t WHERE t.idTransaksi = :idTrans");
                q1.setLong("idTrans", idTransaksi);
                Transaksi tr = (Transaksi) q1.uniqueResult();
                Date now = new Date();

                tr.setAmaun(remisyen);
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(now);
                manager.save(tr);
            }

            manager.save(logAkaun);
        }

        if (notis.compareTo(initNotis) != 0) {
            logger.info("inside if notis");
            KodTransaksi kt = kodTransaksiDAO.findById(hasil.getProperty("notis6A"));

            logAkaun = new LogAkaunKewangan();
            logAkaun.setCatatan(catatan);
            logAkaun.setDataLama(initNotis.toString());
            logAkaun.setDataBaru(notis.toString());
            logAkaun.setPengguna(pengguna);
            logAkaun.setTarikhMasuk(new java.util.Date());
            logAkaun.setAkaun(akaun);
            logAkaun.setPerkara(kt.getKod() + " - " + kt.getNama());

            BigDecimal tot = notis.subtract(initNotis);
            if (initNotis.compareTo(new BigDecimal(0)) == 0) {
                createTransaction(akaun, tot, pengguna, kt);
            } else {
                changeTransaction(akaun, s, tot, kt.getKod());
            }

            manager.save(logAkaun);
        }
        papar();
        btn = true;

        logger.info("sum : " + sum);
        akaun.setBaki(sum);
        manager.saveOrUpdate(akaun);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("hasil/tab_kemaskini_data.jsp");
    }

    @HandlesEvent("Step6")
    public Resolution simpanNew() {
        DecimalFormat twoDForm = new DecimalFormat("#.00");
        logger.info("initCukai : " + initCukai);
        logger.info("Cukai : " + cukai);
        logger.info("Transaksi : " + idTransaksi);
        String utk_tahun = getContext().getRequest().getParameter("transaksi.untukTahun"); 
        logger.info("utk_tahun : " + utk_tahun);

        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT tr FROM etanah.model.Transaksi tr WHERE tr.idTransaksi = :id");
        q1.setLong("id", idTransaksi);
        Transaksi tr = (Transaksi) q1.uniqueResult();

        akaun = akaunDAO.findById(rbtAkaun);

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        pengguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if (cukai.compareTo(initCukai) != 0) {
            logger.info("inside if cukai");
            logAkaun = new LogAkaunKewangan();
            logAkaun.setCatatan(catatan);
            logAkaun.setDataLama((twoDForm.format(initCukai.floatValue())).toString());
            logAkaun.setDataBaru(cukai.toString());
            logAkaun.setPengguna(pengguna);
            logAkaun.setTarikhMasuk(new java.util.Date());
            logAkaun.setAkaun(akaun);
            logAkaun.setPerkara(tr.getKodTransaksi().getKod() + " - " + tr.getKodTransaksi().getNama());

            manager.save(logAkaun);
            tr.setUntukTahun(Integer.parseInt(utk_tahun));
            tr.setAmaun(cukai);
            manager.saveOrUpdate(tr);

            updateBaki(akaun);
        }
        
        if ((catatan.length()>0)&&(cukai.compareTo(initCukai) == 0)) {
            logger.info("inside if cukai 99999999999999");
            logAkaun = new LogAkaunKewangan();
            logAkaun.setCatatan(catatan);
//            logAkaun.setDataLama((twoDForm.format(initCukai.floatValue())).toString());
//            logAkaun.setDataBaru(cukai.toString());
            logAkaun.setPengguna(pengguna);
            logAkaun.setTarikhMasuk(new java.util.Date());
            logAkaun.setAkaun(akaun);
            logAkaun.setPerkara(tr.getKodTransaksi().getKod() + " - " + tr.getKodTransaksi().getNama());

            manager.save(logAkaun);
            tr.setUntukTahun(Integer.parseInt(utk_tahun));
            tr.setAmaun(cukai);
            manager.saveOrUpdate(tr);

//            updateBaki(akaun);
        }
        saveFlg = true;
        kemaskiniTransaksi1(idTransaksi.toString());
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("hasil/kemaskini_maklumat_akaun_2.jsp").addParameter("popup", "true");
    }

    @HandlesEvent("Step7")
    public Resolution addRow() {
        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT kt FROM etanah.model.KodTransaksi kt WHERE kt.kod = :id");
        q1.setString("id", kodTransaksi);
        KodTransaksi kt = (KodTransaksi) q1.uniqueResult();

        akaun = akaunDAO.findById(rbtAkaun);

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        pengguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

//        if (amaun.compareTo(new BigDecimal(0)) != 0) {
            logger.info("inside if cukai");
            logAkaun = new LogAkaunKewangan();
            logAkaun.setCatatan(catatan);
            logAkaun.setDataLama((new BigDecimal(0.00)).toString());
            logAkaun.setDataBaru(amaun.toString());
            logAkaun.setPengguna(pengguna);
            logAkaun.setTarikhMasuk(new java.util.Date());
            logAkaun.setAkaun(akaun);
            logAkaun.setPerkara(kt.getKod() + " - " + kt.getNama());

            manager.save(logAkaun);

            Transaksi newTrans = new Transaksi();

                newTrans.setCawangan(pengguna.getKodCawangan());
                newTrans.setKodTransaksi(kt);
                newTrans.setUntukTahun(Integer.parseInt(tahun));
                newTrans.setAmaun(amaun);
                if(kt.getKod().equals("99030")){
                    String ar = "AR"+pengguna.getKodCawangan().getKod();
                    Akaun akaunRemisyen = akaunDAO.findById(ar);
                    
                    newTrans.setAkaunKredit(akaun);
                    newTrans.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
                    newTrans.setAkaunDebit(akaunRemisyen);}
                else{
                    if(jenisTransaksi.equals("DT")){
                        newTrans.setAkaunDebit(akaun);
                        newTrans.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
                    }
                    if(jenisTransaksi.equals("KR")){
                        newTrans.setAkaunKredit(akaun);
                        newTrans.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                    }
                }
                newTrans.setTahunKewangan(Integer.parseInt(yy.format(new Date())));
                newTrans.setInfoAudit(ia);

            manager.save(newTrans);

            updateBaki(akaun);
//        }

        addFlg = true;
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("hasil/kemaskini_maklumat_akaun_3.jsp").addParameter("popup", "true");
    }

    @HandlesEvent("Step8")
    public Resolution updateBaki() {
        akaun = akaunDAO.findById(rbtAkaun);
        BigDecimal bakiAsal = akaun.getBaki();

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Akaun a  = akaun;
        akaun = new Akaun();
        pengguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(a.getInfoAudit().getDimasukOleh());
        ia.setTarikhMasuk(a.getInfoAudit().getTarikhMasuk());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new Date());

        a.setBaki(totalAmount);
        a.setInfoAudit(ia);
        
        manager.saveOrUpdate(a);

        logAkaun = new LogAkaunKewangan();
        logAkaun.setCatatan("Baki mengikut transaksi.");
        logAkaun.setDataLama(bakiAsal.toString());
        logAkaun.setDataBaru(totalAmount.toString());
        logAkaun.setPengguna(pengguna);
        logAkaun.setTarikhMasuk(new java.util.Date());
        logAkaun.setAkaun(a);
        logAkaun.setPerkara("Update Baki.");

        manager.save(logAkaun);

        akaun =a;
        return papar();
    }
    
    @HandlesEvent("Step9")
    public Resolution updateStatusAkaun() {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        akaun = akaunDAO.findById(rbtAkaun);
        String status = akaun.getStatus().getKod();
        Akaun a  = akaun;
        akaun = new Akaun();
        pengguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(a.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(a.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new Date());

        if(status.equals("A")){
            a.setStatus(kodStatusAkaunDAO.findById("B"));}
        if(status.equals("B")){
            a.setStatus(kodStatusAkaunDAO.findById("A"));}
         if(status.equals("F")){
            a.setStatus(kodStatusAkaunDAO.findById("A"));}
        a.setInfoAudit(ia);
        
        manager.saveOrUpdate(a);

        logAkaun = new LogAkaunKewangan();
        logAkaun.setCatatan(catatan);
        if(status.equals("A")){
            logAkaun.setDataLama(status +" : Aktif");
            logAkaun.setDataBaru("B : Batal");
        }
         if(status.equals("B")){
            logAkaun.setDataLama(status+" : Batal");
            logAkaun.setDataBaru("A : Aktif");
        }
         if(status.equals("F")){
            logAkaun.setDataLama(status+" : Batal");
            logAkaun.setDataBaru("A : Aktif");
        }
        logAkaun.setPengguna(pengguna);
        logAkaun.setTarikhMasuk(new java.util.Date());
        logAkaun.setAkaun(a);
        logAkaun.setPerkara("Status Akaun.");

        manager.save(logAkaun);

        return papar();
    }
     @HandlesEvent("StepBeku")
    public Resolution updateBeku() {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        akaun = akaunDAO.findById(rbtAkaun);
        String status = akaun.getStatus().getKod();
        Akaun a  = akaun;
        akaun = new Akaun();
        pengguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(a.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(a.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new Date());

//        if(status.equals("A")){
            a.setStatus(kodStatusAkaunDAO.findById("F"));
//        if(status.equals("B")){
//            a.setStatus(kodStatusAkaunDAO.findById("A"));}
        a.setInfoAudit(ia);
        
        manager.saveOrUpdate(a);

        logAkaun = new LogAkaunKewangan();
        logAkaun.setCatatan(catatan);
        if(status.equals("A")){
            logAkaun.setDataLama(status +" : Aktif");
            logAkaun.setDataBaru("B : Batal");
        }
         if(status.equals("B")){
            logAkaun.setDataLama(status+" : Batal");
            logAkaun.setDataBaru("A : Aktif");
        }
        logAkaun.setPengguna(pengguna);
        logAkaun.setTarikhMasuk(new java.util.Date());
        logAkaun.setAkaun(a);
        logAkaun.setPerkara("Status Akaun.");

        manager.save(logAkaun);

        return papar();
    }
    
    public void updateBaki(Akaun a) {
        BigDecimal dt = new BigDecimal(0.00);
        BigDecimal kr = new BigDecimal(0.00);
        BigDecimal tot = new BigDecimal(0.00);

        for (Transaksi t : a.getSemuaTransaksi()) {
            if ((t.getAkaunDebit() != null) && (t.getAkaunDebit().getNoAkaun().equals(a.getNoAkaun()))) {
                tot = tot.add(t.getAmaun());
            }
            if ((t.getAkaunKredit() != null) && (t.getAkaunKredit().getNoAkaun().equals(a.getNoAkaun()))) {
                tot = tot.subtract(t.getAmaun());
            }
        }
        logger.info("total : " + tot);
        a.setBaki(tot);
        manager.saveOrUpdate(a);
    }

    public void changeTax(Akaun ak, Session s, BigDecimal val) {
        Query q1 = s.createQuery("SELECT t FROM etanah.model.Transaksi t WHERE t.akaunDebit = :noAkaun AND t.status.kod='A' AND t.kodTransaksi='61401'");
        q1.setString("noAkaun", ak.getNoAkaun());
        Transaksi t = (Transaksi) q1.uniqueResult();

        t.setAmaun(val);
        
        // set dikemaskini & tkh_kemaskini
        InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(t.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(t.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        t.setInfoAudit(ia);
        manager.saveOrUpdate(t);
    }

    public void changeTransaction(Akaun ak, Session s, BigDecimal val, String kodTransaksi) {
        Query q1 = s.createQuery("SELECT t FROM etanah.model.Transaksi t WHERE t.akaunDebit = :noAkaun AND t.status.kod='A' AND t.kodTransaksi=:kodTrans "
                + "ORDER BY t.untukTahun");
        q1.setString("noAkaun", ak.getNoAkaun());
        q1.setString("kodTrans", kodTransaksi);
        List<Transaksi> trList = q1.list();

        Transaksi t = trList.get(0);
        t.setAmaun(t.getAmaun().add(val));
        
        // set dikemaskini & tkh_kemaskini
        InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(t.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(t.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        t.setInfoAudit(ia);
        
        manager.saveOrUpdate(t);
    }

    public void createTransaction(Akaun ak, BigDecimal val, Pengguna p, KodTransaksi kt) {
        Transaksi t = new Transaksi();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        Date now = new Date();
        int tahun = Integer.parseInt(yy.format(now));

        t.setAkaunDebit(ak);
        t.setAmaun(val);
        t.setCawangan(p.getKodCawangan());
        t.setInfoAudit(ia);
        t.setKodTransaksi(kt);
        t.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
        t.setTahunKewangan(tahun);
        t.setUntukTahun(tahun);

        manager.save(t);
    }

    public void createTransactionRemisyen(Akaun ak, BigDecimal val, Pengguna p, KodTransaksi kt) {
        Transaksi t = new Transaksi();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        Date now = new Date();
        int tahun = Integer.parseInt(yy.format(now));

        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT a FROM etanah.model.Akaun a WHERE a.kodAkaun.kod = 'AR' AND a.cawangan.kod= :caw");
        q1.setString("caw", pengguna.getKodCawangan().getKod());
        Akaun ar = (Akaun) q1.uniqueResult();

        t.setAkaunKredit(ak);
        t.setAkaunDebit(ar);
        t.setAmaun(val);
        t.setCawangan(p.getKodCawangan());
        t.setInfoAudit(ia);
        t.setKodTransaksi(kt);
        t.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
        t.setTahunKewangan(tahun);
        t.setUntukTahun(tahun);

        manager.save(t);
    }

    public Resolution reloadPage() {
        rbtAkaun = getContext().getRequest().getParameter("idTrans");
        papar();
        return new ForwardResolution("/WEB-INF/jsp/hasil/tab_kemaskini_data.jsp").addParameter("popup", "true");
    }

    @HandlesEvent("Step5")
    public Resolution mainMenu() {
        rbtAkaun = "";
        idTransaksi = 0L;
        initCukai = new BigDecimal(0.00);
        saveFlg = false;
        addFlg = false;
        return new RedirectResolution(KemaskiniMaklumatAkaunActionBean.class);
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

    public LogAkaunKewangan getLogAkaun() {
        return logAkaun;
    }

    public void setLogAkaun(LogAkaunKewangan logAkaun) {
        this.logAkaun = logAkaun;
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

    public String getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(String bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public String getKodStatusHakmilik() {
        return kodStatusHakmilik;
    }

    public void setKodStatusHakmilik(String kodStatusHakmilik) {
        this.kodStatusHakmilik = kodStatusHakmilik;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public List<HakmilikPihakBerkepentingan> getPihakList() {
        return pihakList;
    }

    public void setPihakList(List<HakmilikPihakBerkepentingan> pihakList) {
        this.pihakList = pihakList;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Akaun> getListAkaun() {
        return listAkaun;
    }

    public void setListAkaun(List<Akaun> listAkaun) {
        this.listAkaun = listAkaun;
    }

    public String getRbtAkaun() {
        return rbtAkaun;
    }

    public void setRbtAkaun(String rbtAkaun) {
        this.rbtAkaun = rbtAkaun;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public BigDecimal getCukai() {
        return cukai;
    }

    public void setCukai(BigDecimal cukai) {
        this.cukai = cukai;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public BigDecimal getKredit() {
        return kredit;
    }

    public void setKredit(BigDecimal kredit) {
        this.kredit = kredit;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public BigDecimal getRemisyen() {
        return remisyen;
    }

    public void setRemisyen(BigDecimal remisyen) {
        this.remisyen = remisyen;
    }

    public BigDecimal getTunggakan() {
        return tunggakan;
    }

    public void setTunggakan(BigDecimal tunggakan) {
        this.tunggakan = tunggakan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public BigDecimal getInitCukai() {
        return initCukai;
    }

    public void setInitCukai(BigDecimal initCukai) {
        this.initCukai = initCukai;
    }

    public BigDecimal getInitDebit() {
        return initDebit;
    }

    public void setInitDebit(BigDecimal initDebit) {
        this.initDebit = initDebit;
    }

    public BigDecimal getInitDenda() {
        return initDenda;
    }

    public void setInitDenda(BigDecimal initDenda) {
        this.initDenda = initDenda;
    }

    public BigDecimal getInitKredit() {
        return initKredit;
    }

    public void setInitKredit(BigDecimal initKredit) {
        this.initKredit = initKredit;
    }

    public BigDecimal getInitNotis() {
        return initNotis;
    }

    public void setInitNotis(BigDecimal initNotis) {
        this.initNotis = initNotis;
    }

    public BigDecimal getInitRemisyen() {
        return initRemisyen;
    }

    public void setInitRemisyen(BigDecimal initRemisyen) {
        this.initRemisyen = initRemisyen;
    }

    public BigDecimal getInitTunggakan() {
        return initTunggakan;
    }

    public void setInitTunggakan(BigDecimal initTunggakan) {
        this.initTunggakan = initTunggakan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public boolean isChecking() {
        return checking;
    }

    public void setChecking(boolean checking) {
        this.checking = checking;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public String getCaw() {
        return caw;
    }

    public void setCaw(String caw) {
        this.caw = caw;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public List<LogAkaunKewangan> getSenaraiSejarah() {
        return senaraiSejarah;
    }

    public void setSenaraiSejarah(List<LogAkaunKewangan> senaraiSejarah) {
        this.senaraiSejarah = senaraiSejarah;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public List<KodDaerah> getSenaraiKodDaerah() {
        return senaraiKodDaerah;
    }

    public void setSenaraiKodDaerah(List<KodDaerah> senaraiKodDaerah) {
        this.senaraiKodDaerah = senaraiKodDaerah;
    }

    public String getKodRemisyen() {
        return kodRemisyen;
    }

    public void setKodRemisyen(String kodRemisyen) {
        this.kodRemisyen = kodRemisyen;
    }

    public Long getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Long idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public boolean isSaveFlg() {
        return saveFlg;
    }

    public void setSaveFlg(boolean saveFlg) {
        this.saveFlg = saveFlg;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public String getKodTransaksi() {
        return kodTransaksi;
    }

    public void setKodTransaksi(String kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public List<KodTransaksi> getSenaraiKodTransaksi() {
        return senaraiKodTransaksi;
    }

    public void setSenaraiKodTransaksi(List<KodTransaksi> senaraiKodTransaksi) {
        this.senaraiKodTransaksi = senaraiKodTransaksi;
    }

    public boolean isAddFlg() {
        return addFlg;
    }

    public void setAddFlg(boolean addFlg) {
        this.addFlg = addFlg;
    }

    public boolean isCheckingBaki() {
        return checkingBaki;
    }

    public void setCheckingBaki(boolean checkingBaki) {
        this.checkingBaki = checkingBaki;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

}
