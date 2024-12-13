/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import etanah.service.RegService;
import java.util.List;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodHakmilik;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;
import etanah.model.Hakmilik;
import etanah.dao.HakmilikDAO;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.service.common.HakmilikUrusanService;
import etanah.model.Pihak;
import etanah.model.KumpulanAkaun;
import etanah.dao.KumpulanAkaunDAO;
import etanah.dao.PihakDAO;
import etanah.model.SejarahTransaksi;
import etanah.dao.SejarahTransaksiDAO;
import java.util.ArrayList;
import etanah.model.Transaksi;
import etanah.model.Akaun;
import etanah.dao.AkaunDAO;
import etanah.dao.HakmilikLamaDAO;
import java.math.BigDecimal;
import java.util.Date;
import etanah.model.InfoAudit;
import etanah.service.HakmilikService;
import java.util.Map;
import etanah.model.Pengguna;
import etanah.workflow.WorkFlowService;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.common.PermohonanService;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import org.hibernate.Session;
import org.hibernate.Query;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.model.SejarahDokumenKewangan;
import java.util.HashMap;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import etanah.manager.TabManager;
import etanah.model.CaraBayaran;//
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikLama;
import etanah.model.KodSeksyen;
import etanah.model.LogAkaunKewangan;
import etanah.model.PermohonanPihak;
import etanah.model.etapp.AgensiHakmilik;
import etanah.service.RegService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.stripes.hasil.KutipanHasilActionBean;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.HashSet;

/**
 *
 * @author khairil
 */
@UrlBinding("/common/carian_hakmilik")
public class CarianHakmilik extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(CarianHakmilik.class);
    private static boolean isDebug = logger.isDebugEnabled();
    String negeri;
    String kodDaerah;
    private String caw;
    private String noHakmilik;
    private String namaPembayar;
    private String noPengenalanP;
    private String kodHakmilik;
    private String bandarPekanMukim;
    private String seksyen;
    private String lot;
    private String note;
    String namaBPM;
    int kodBPM;
    String daerah;
    String noLot;
    String jenisHakmilik;
    String jenisLot;
    private Hakmilik hakmilik;
    private HakmilikLama hakmilikLama;
    private Pengguna pengguna;
    private Akaun akaunKredit;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    @Inject
    etanah.Configuration etanahConf;
    @Inject
    etanah.kodHasilConfig khconf;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    RegService regService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikLamaDAO hakmilikLamaDAO;
    @Inject
    HakmilikPihakKepentinganService hpkService;
    @Inject
    HakmilikUrusanService huService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    SejarahTransaksiDAO sejarahTransaksiDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    WorkFlowService WorkFlowService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPihakBerkepentinganDAO hpDAO;
    @Inject
    TabManager tabManager;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanPihakService permohonanPihakService;
    String idHakmilik;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganListAktif;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganListTakAktif;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganList;
    private List<HakmilikUrusan> hakmilikUrusanList;
    private List<HakmilikUrusan> hakmilikUrusanListTaktif;
    private List<HakmilikUrusan> hakmilikUrusanProsesList;
    private List<LogAkaunKewangan> senaraiSejarah = new ArrayList<LogAkaunKewangan>();
    private List<Permohonan> mohonTolakGantung;
    String idPihak;
    String idHakmilikPihakBerkepentingan;
    private String pegang;
    Pihak pihak;
    HakmilikPihakBerkepentingan hpk;
    private List<Transaksi> transList;
    private BigDecimal baki;
    private List<HakmilikPihakBerkepentingan> pihakList;
    private BigDecimal jumlahCaj = new BigDecimal(0.00);
    private BigDecimal jumCukai = new BigDecimal(0.00);
    private BigDecimal y;
    private BigDecimal w;
    private BigDecimal z;
    private BigDecimal r;
    private BigDecimal jum = new BigDecimal(0.00);
    private BigDecimal amaunRemesyen = new BigDecimal(0.00);
    private BigDecimal amaunDebit = new BigDecimal(0.00);
    private BigDecimal totalAmaunDebit = new BigDecimal(0.00);
    private BigDecimal jumDenda;
    private BigDecimal notis;
//    private double denda;
    private BigDecimal denda;
    private BigDecimal amaun;
    private BigDecimal amaunDenda;
    private String btn2;
    private List<Map<String, String>> senaraiUrusan;
    private List<Map<String, String>> senaraiUrusanProses;
    private List<Map<String, String>> senaraiUrusanProsesDistinct;
    public String selectedTab = "0";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    SimpleDateFormat sd = new SimpleDateFormat("dd/MM");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    private List<Akaun> listAkaun = new ArrayList();
    private boolean flag = false;
    private boolean flagTukarganti = false;
    private List<KodBandarPekanMukim> senaraiBPM;
    private String noAkaun;
    private String kodNegeri;
    private Akaun akaun;
    private List<Akaun> list;//
    private boolean del = true;//
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();//
    private boolean visible = true;//
    private int bil = 0;//
    private boolean button = false;//
    private List<SejarahTransaksi> sejarahList;
    private List<Hakmilik> senaraiHakmilikBerikut;
    private String kodPengenalan;
    private String kodStatusHakmilik;
    private String noPengenalan;
    private String nama;
    private String namaPemilik;
    private List<HakmilikPihakBerkepentingan> l;
    private List<Akaun> senaraiAkaun;
    private Pihak pemegang;
    private Transaksi idTransaksi;
    private boolean test = false;
    private boolean tukarGanti = false; // for utiliti tukar ganti
    private SejarahDokumenKewangan dokumenKewangan;
    private List<HakmilikAsal> listHakmilikAsal = new ArrayList();
    private List<HakmilikSebelum> listHakmilikSebelum = new ArrayList();
    private List<HakmilikAsal> listSejarahHakmilikAsal;
    private List<HakmilikSebelum> listSejarahHakmilikSebelum;
    private BigDecimal a = new BigDecimal(0);
    private KumpulanAkaun kumpulan;
    private List<DasarTuntutanCukaiHakmilik> dasarTuntutanCukai = new ArrayList<DasarTuntutanCukaiHakmilik>();
    private DasarTuntutanCukaiHakmilik dasarTuntutan;
    private List<KodSeksyen> senaraiKodSeksyen;
    private PermohonanPihak permohonanPihak;
//    private List<PermohonanPihak> permohonanPihakList = new ArrayList();
    private Permohonan permohonanPemilikan;
    private List<String> senaraiIdHakmilikAsal;
    private List<Hakmilik> senaraiHakmilikAsal = new ArrayList<Hakmilik>();
    private List<String> senaraiIdHakmilikSebelum;
    private List<Hakmilik> senaraiHakmilikSebelum = new ArrayList<Hakmilik>();
    private AgensiHakmilik agensiHakmilik;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("common/carian_hakmilik_terperinci.jsp").addParameter("popup", "true");
    }

    public Resolution pertanyaanHakmilik() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        logger.info("--> tukarGanti : " + tukarGanti);
        return new JSP("common/pertanyaan_hakmilik.jsp");
    }

    public Resolution tabPertanyaanHakmilik() {
        return new JSP("common/tab_maklumat_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution kembali() {
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

    public Resolution back() {
//search();
        return new JSP("common/pertanyaan_hakmilik.jsp");
    }

    public Resolution penyukatanBPM() {
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
        return new ForwardResolution("/WEB-INF/jsp/common/pertanyaan_hakmilik.jsp");
    }

    public Resolution penyukatanSeksyen() {
        String kodDaerah = getContext().getRequest().getParameter("daerah");
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
        String kodBPM = getBandarPekanMukim();
        logger.debug("BPM-->" + kodBPM);
//        String sql = null;
//        Session s = sessionProvider.get();
//        Query q = null;
        if (kodBPM == null || kodBPM.equals("")) {
            sql = "select sek from KodSeksyen sek ";
            q = s.createQuery(sql);
        } else {
            sql = "select sek from KodSeksyen sek where sek.kodBandarPekanMukim.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodBPM);
        }
        senaraiKodSeksyen = q.list();
//        logger.debug("senaraiKodSeksyen-->" + senaraiKodSeksyen.get(0).getNama());
        return new ForwardResolution("/WEB-INF/jsp/common/pertanyaan_hakmilik.jsp");
    }

    public Resolution search() throws WorkflowException, ParseException {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
        logger.debug("page = " + page);
        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
//            logger.debug("page = " + set__pg_start);
            set__pg_max_records(get__pg_max_records());
//            logger.debug("page = " + set__pg_start);
//            __pg_start = (Integer.parseInt(page) - 1) * __pg_max_records; 
//            __pg_max_records = __pg_start + __pg_max_records;
        }

        listAkaun = hakmilikService.findAll(getContext().getRequest().getParameterMap(), get__pg_start(), get__pg_max_records(), pengguna.getKodCawangan().getKod());
        set__pg_total_records(hakmilikService.getTotalRecord(getContext().getRequest().getParameterMap(), pengguna.getKodCawangan().getKod()).intValue());
//        listAkaun = hakmilikService.findAll(getContext().getRequest().getParameterMap());

        if (isDebug) {
            logger.debug("page_start = " + get__pg_start());
            logger.debug("max_records = " + get__pg_max_records());
            logger.debug("total record = " + get__pg_total_records());
        }

        // Added for loop (Tulasi)
        for (int j = 1; j < listAkaun.size(); j++) {
            Akaun ak = new Akaun();
            ak = getListAkaun().get(j);
            baki = ak.getBaki();
            jum = ak.getHakmilik().getCukaiSebenar();

            if (namaPemilik != null || noPengenalan != null) {
                test = true;
                pihakList = new ArrayList<HakmilikPihakBerkepentingan>();
                List<HakmilikPihakBerkepentingan> pihakList1 = new ArrayList<HakmilikPihakBerkepentingan>();
                //  List<HakmilikPihakBerkepentingan> listPihak = new ArrayList<HakmilikPihakBerkepentingan>();
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

        setFlag(true);
        setDaerah(null);
//        penyukatanBPM();
//        penyukatanSeksyen();

        if (idHakmilik != null) {
            listAkaun = regService.findAkaunByIdHakmilik(idHakmilik);
        }
        if (listAkaun.size() == 1) {
            idHakmilik = listAkaun.get(0).getHakmilik().getIdHakmilik();
            noAkaun = listAkaun.get(0).getNoAkaun();
            return papar();
        } else if (listAkaun.isEmpty()) {
            addSimpleError("Hakmilik " + idHakmilik + " tiada akaun cukai.");
            setFlag(false);
            return new JSP("common/pertanyaan_hakmilik.jsp");
        } else {
            return new JSP("common/pertanyaan_hakmilik.jsp");
        }

    }

    public Resolution popupPihak() {
        idHakmilikPihakBerkepentingan = (String) getContext().getRequest().getParameter("idHakmilikPihakBerkepentingan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        logger.debug("idHakmilikPihakBerkepentingan : " + idHakmilikPihakBerkepentingan);
        logger.debug("idHakmilik : " + idHakmilik);
        if (StringUtils.isNotBlank(idHakmilikPihakBerkepentingan) && StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            //hpk = hpkService.findHakmilikPihakByPihak(Long.parseLong(idPihak), hakmilik);
            hpk = hpDAO.findById(Long.parseLong(idHakmilikPihakBerkepentingan));
            if (hpk != null) {
                if (hpk.getJenis() != null && hpk.getJenis().getKod().equals("PM")) {
                    permohonanPihak = new PermohonanPihak();
                    permohonanPihak = permohonanPihakService.getSenaraiPermohonanPihak2(hpk.getPihak().getIdPihak(), hpk.getHakmilik().getIdHakmilik(), hpk.getJenis().getKod());
                    permohonanPemilikan = permohonanPihak != null ? permohonanPihak.getPermohonan() : null;
                }
            }
        }
        return new JSP("common/paparan_pihakberkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution papar() throws WorkflowException, ParseException {
        selectedTab = getContext().getRequest().getParameter("selectedTab");
        String popup = getContext().getRequest().getParameter("popup");
        Date now = new Date();

        senaraiHakmilikBerikut = new ArrayList<Hakmilik>();
        int year = Integer.parseInt(yy.format(now));
        InfoAudit ia = new InfoAudit();

        if (StringUtils.isNotEmpty(popup)) {
            idHakmilik = getContext().getRequest().getParameter("idHakmilik");
            noAkaun = getContext().getRequest().getParameter("noAkaun");
        }
        if (idHakmilik != null) {

            StringBuilder sb = new StringBuilder("SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :id");
            if (StringUtils.isNotBlank(noAkaun)) {
                sb.append(" AND a.noAkaun= :akaun");
            }
//        String query = "SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :id AND a.noAkaun= :akaun";
            Query q = sessionProvider.get().createQuery(sb.toString());
            q.setString("id", idHakmilik);
            if (StringUtils.isNotBlank(noAkaun)) {
                q.setString("akaun", noAkaun);
            }
            Akaun ak = (Akaun) q.uniqueResult();
            noAkaun = ak.getNoAkaun();
            logger.debug("::start looking for idhakmilik:" + idHakmilik + " noAkaun = " + noAkaun);
            hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilikLama = hakmilikLamaDAO.findById(idHakmilik); // use for utiliti tukar ganti
        } else {
            addSimpleError("Hakmilik Tidak Dijumpai.Sila masukkan ID Hakmilik yang sah");
            return new JSP("common/pertanyaan_hakmilik.jsp");
        }

        //List<Hakmilik> senaraiHakmilikBerikut = new ArrayList<Hakmilik>();
        if (hakmilik != null) {
            if (hakmilik.getKodStatusHakmilik().getKod().equals("B")) {
                listHakmilikAsal = regService.cariHakmilikAsal(idHakmilik);
                listHakmilikSebelum = regService.cariHakmilikSebelum(idHakmilik);
            }
            logger.debug("listHakmilikAsal : " + listHakmilikAsal.size());
            if (listHakmilikAsal.size() > 0) {
                for (int k = 0; k < listHakmilikAsal.size(); k++) {
                    senaraiHakmilikBerikut.add(listHakmilikAsal.get(k).getHakmilik());
                }
            }
            logger.debug("listHakmilikSebelum : " + listHakmilikSebelum.size());
            if (listHakmilikSebelum.size() > 0) {
                for (int l = 0; l < listHakmilikSebelum.size(); l++) {
                    senaraiHakmilikBerikut.add(listHakmilikSebelum.get(l).getHakmilik());
                }
            }

            logger.debug("size  senaraiHakmilikBerikut " + senaraiHakmilikBerikut.size());
            HashSet<Hakmilik> listToSet = new HashSet<Hakmilik>(senaraiHakmilikBerikut);
            senaraiHakmilikBerikut.clear();
            List<Hakmilik> listWithoutDuplicates = new ArrayList<Hakmilik>(listToSet);

//      logger.debug("size listToset "+listToSet.size());
//      logger.debug("size listWithoutDuplicates "+listWithoutDuplicates.size());
            for (int i = 0; i < listWithoutDuplicates.size(); i++) {
//          logger.debug("nilai list "+ listWithoutDuplicates.get(i).getIdHakmilik());
                senaraiHakmilikBerikut.add(listWithoutDuplicates.get(i));
            }

//      logger.debug("size  senaraiHakmilikBerikut lepas hashset"+ senaraiHakmilikBerikut.size());
            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//            IWorkflowContext ctx = WorkFlowService.authenticateAdmin();
            IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
            //List<Permohonan> listpermohonan = permohonanService.getSenaraiPermohonanByIdHakmilik(hakmilik.getIdHakmilik());
            List<Permohonan> listpermohonan = permohonanService.getSenaraiMohonByIdHakmilik(hakmilik.getIdHakmilik());
            List<HakmilikSebelumPermohonan> lhsp = regService.searchMohonHakmilikSblmByIDHakmilik(idHakmilik);
            List<HakmilikAsalPermohonan> lhap = regService.findIdHakmilikAsalMohonByIdHakmilikSej(idHakmilik);

//            List<HakmilikPermohonan> listhp = regService.searchMohonHakmilik(hakmilik.getIdHakmilik());
//            List<Permohonan> listpermohonan = new ArrayList();
//            for (HakmilikPermohonan hakmilikPermohonan : listhp) {
//                listpermohonan.add(hakmilikPermohonan.getPermohonan());
//            }
//            logger.debug("listpermohonan.size()" + listpermohonan.size());
            senaraiUrusan = new ArrayList();
            senaraiUrusanProses = new ArrayList();
            for (Permohonan p : listpermohonan) {
                Map<String, String> map = new HashMap<String, String>();
                //List<Task> taskList = WorkFlowService.queryAllTasks(ctx, p.getIdPermohonan());
//                List<Task> taskList = WorkFlowService.queryAllTasks(ctx, p.getIdPermohonan());
                List<Task> taskList = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
                if (taskList.size() > 0) {
                    map.put("idPermohonan", p.getIdPermohonan());
                    logger.debug("idPermohonan:" + p.getIdPermohonan());
                    map.put("kodUrusan", p.getKodUrusan().getKod());
                    map.put("urusan", p.getKodUrusan().getNama());
                    String tarikhMula = sdf.format(p.getInfoAudit().getTarikhMasuk());
                    logger.debug("tarikhMula:" + tarikhMula);
                    map.put("tarikhMula", tarikhMula);
                    Task t = taskList.get(0);
                    String stage = t.getSystemAttributes().getStage();
                    map.put("stage", tabManager.getCurrentAction(p.getKodUrusan().getIdWorkflow(), stage));
                    senaraiUrusanProses.add(map);
                }

//                if (!p.getStatus().equals("SL")) {
//                    Map<String, String> map = new HashMap<String, String>();
//                    map.put("idPermohonan", p.getIdPermohonan());
//                    map.put("kodUrusan", p.getKodUrusan().getKod());
//                    map.put("urusan", p.getKodUrusan().getNama());
//                    String tarikhMula = sdf.format(p.getInfoAudit().getTarikhMasuk());
//                    logger.debug("tarikhMula:" + tarikhMula);
//                    map.put("tarikhMula", tarikhMula);
//                    senaraiUrusanProses.add(map);
//                }
            }

            for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : lhsp) {
                Permohonan p = hakmilikSebelumPermohonan.getPermohonan();
                Map<String, String> map = new HashMap<String, String>();
                //List<Task> taskList = WorkFlowService.queryAllTasks(ctx, p.getIdPermohonan());
//                List<Task> taskList = WorkFlowService.queryAllTasks(ctx, p.getIdPermohonan());
                List<Task> taskList = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
                if (taskList.size() > 0) {
                    map.put("idPermohonan", p.getIdPermohonan());
                    logger.debug("idPermohonan:" + p.getIdPermohonan());
                    map.put("kodUrusan", p.getKodUrusan().getKod());
                    map.put("urusan", p.getKodUrusan().getNama());
                    String tarikhMula = sdf.format(p.getInfoAudit().getTarikhMasuk());
                    logger.debug("tarikhMula:" + tarikhMula);
                    map.put("tarikhMula", tarikhMula);
                    Task t = taskList.get(0);
                    String stage = t.getSystemAttributes().getStage();
                    map.put("stage", tabManager.getCurrentAction(p.getKodUrusan().getIdWorkflow(), stage));
                    senaraiUrusanProses.add(map);
                }

            }
            
            for (HakmilikAsalPermohonan hakmilikAsalPermohonan : lhap) {
                Permohonan p = hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan();
                Map<String, String> map = new HashMap<String, String>();
                //List<Task> taskList = WorkFlowService.queryAllTasks(ctx, p.getIdPermohonan());
//                List<Task> taskList = WorkFlowService.queryAllTasks(ctx, p.getIdPermohonan());
                List<Task> taskList = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
                if (taskList.size() > 0) {
                    map.put("idPermohonan", p.getIdPermohonan());
                    logger.debug("idPermohonan:" + p.getIdPermohonan());
                    map.put("kodUrusan", p.getKodUrusan().getKod());
                    map.put("urusan", p.getKodUrusan().getNama());
                    String tarikhMula = sdf.format(p.getInfoAudit().getTarikhMasuk());
                    logger.debug("tarikhMula:" + tarikhMula);
                    map.put("tarikhMula", tarikhMula);
                    Task t = taskList.get(0);
                    String stage = t.getSystemAttributes().getStage();
                    map.put("stage", tabManager.getCurrentAction(p.getKodUrusan().getIdWorkflow(), stage));
                    senaraiUrusanProses.add(map);
                }

            }

            senaraiUrusanProsesDistinct = new ArrayList();
            String idPermohonanTemp = "";
            for (int m = 0; m < senaraiUrusanProses.size(); m++) {
                Map<String, String> map = new HashMap<String, String>();
                if (senaraiUrusanProses.get(m).get("idPermohonan").equals(idPermohonanTemp)) {
                    logger.debug("idPermohonan sama detected");
                    continue;
                }
                map.put("idPermohonan", senaraiUrusanProses.get(m).get("idPermohonan"));
                logger.debug("idPermohonan:" + senaraiUrusanProses.get(m).get("idPermohonan"));
                map.put("kodUrusan", senaraiUrusanProses.get(m).get("kodUrusan"));
                map.put("urusan", senaraiUrusanProses.get(m).get("urusan"));
                String tarikhMula = senaraiUrusanProses.get(m).get("tarikhMula");
                //logger.debug("tarikhMula:" + tarikhMula);
                map.put("tarikhMula", tarikhMula);
                map.put("stage", senaraiUrusanProses.get(m).get("stage"));
                senaraiUrusanProsesDistinct.add(map);
                idPermohonanTemp = senaraiUrusanProses.get(m).get("idPermohonan");

            }

            kodDaerah = (hakmilik.getIdHakmilik()).substring(2, 4);
            kodHakmilik = hakmilik.getKodHakmilik().getKod();
            caw = pengguna.getKodCawangan().getKod();

            hakmilikPihakKepentinganListAktif = hpkService.findPertanyaanHmkActiveByHakmilik(hakmilik);
            hakmilikPihakKepentinganListTakAktif = hpkService.findPertanyaanHmNotActiveByHakmilik(hakmilik);
//            hakmilikPihakKepentinganList = hpkService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakmilik);
            //modify by m.fairul
            hakmilikPihakKepentinganList = hpkService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilikSemua(hakmilik);
            hakmilikUrusanList = huService.findHakmilikUrusanByIdHakmilik(hakmilik.getIdHakmilik());
            hakmilikUrusanListTaktif = huService.findHakmilikUrusanTAktif(hakmilik.getIdHakmilik());
            List<HakmilikPermohonan> listmohonHakmilik = regService.searchMohonHakmilik(hakmilik.getIdHakmilik());
            mohonTolakGantung = new ArrayList();
            for (HakmilikPermohonan hp : listmohonHakmilik) {
                Permohonan p = permohonanService.searchPermohonanTolakGantung(hp.getPermohonan().getIdPermohonan());
                if (p != null) {
                    mohonTolakGantung.add(p);
                }
            }

//            listSejarahHakmilikAsal = hakmilik.getSenaraiHakmilikAsal();
//            listSejarahHakmilikSebelum = hakmilik.getSenaraiHakmilikSebelum();
//          Hakmilik Asal
            senaraiIdHakmilikAsal = regService.findIdHakmilikAsalByIdHakmilik(idHakmilik);
            senaraiHakmilikAsal.clear();
            for (String sa : senaraiIdHakmilikAsal) {
                Hakmilik ha = new Hakmilik();
                ha.setIdHakmilik(sa);
                ha = hakmilikDAO.findById(ha.getIdHakmilik());
                if (ha == null) {
                    ha = new Hakmilik();
                    ha.setIdHakmilik(sa);
                }
                senaraiHakmilikAsal.add(ha);
            }
//          Hakmilik sebelum
            senaraiIdHakmilikSebelum = regService.findIdHakmilikSebelumByIdHakmilik(idHakmilik);
            senaraiHakmilikSebelum.clear();
            for (String ss : senaraiIdHakmilikSebelum) {
                Hakmilik hs = new Hakmilik();
                hs.setIdHakmilik(ss);
                hs = hakmilikDAO.findById(hs.getIdHakmilik());
                if (hs == null) {
                    hs = new Hakmilik();
                    hs.setIdHakmilik(ss);
                }
                senaraiHakmilikSebelum.add(hs);
            }

            sejarahList = new ArrayList<SejarahTransaksi>();

            String[] n = {"hakmilik"};
            Object[] v = {getHakmilik()};
            List<Akaun> alist = akaunDAO.findByEqualCriterias(n, v, null);

            for (int i = 0; i < alist.size(); i++) {
                Akaun ak = alist.get(i);
                if ((ak.getKodAkaun().getKod().equals("AC")) && (ak.getNoAkaun().equals(noAkaun))) {
                    if (ak.getBaki() == null) {
                        ak.setBaki(BigDecimal.ZERO);
                    }

                    akaun = akaunDAO.findById(ak.getNoAkaun());

                    List<SejarahTransaksi> temp = new ArrayList<SejarahTransaksi>();
//       ------- Notes: hide sekejap sementara fix bugs kat Sejarah Transaksi
                    if (!ak.getKodAkaun().getKod().equals("ACT")) {
                        temp = ak.getSemuaSejarahTransaksi();
                    }
                    for (int x = 0; x < temp.size(); x++) {
                        SejarahTransaksi t = temp.get(x);

                        if (t.getAkaunKredit() != null) {
                            if (t.getAkaunKredit().getNoAkaun().equals(akaun.getNoAkaun())) {

                                double d = t.getAmaun().doubleValue();
                                t.setAmaun(new BigDecimal(d));
                                sejarahList.add(t);

                            }
                        }
                        if (t.getAkaunDebit() != null) {
                            if (t.getAkaunDebit().getNoAkaun().equals(akaun.getNoAkaun())) {

                                sejarahList.add(t);
                            }
                        }
                    }
                }
            }
            transList = new ArrayList<Transaksi>();

            //** MAKLUMAT KEMASKINI CUKAI 04 MARCH 2015 START **
            Session s = sessionProvider.get();
            Query q1 = s.createQuery("SELECT a from etanah.model.LogAkaunKewangan a where a.akaun.noAkaun = :noAkaun ORDER BY a.tarikhMasuk");
            q1.setString("noAkaun", akaun.getNoAkaun());
            senaraiSejarah = q1.list();
            //** FINISH **

            String[] name = {"hakmilik"};
            Object[] value = {getHakmilik()};
            List<Akaun> list = akaunDAO.findByEqualCriterias(name, value, null);
            for (int i = 0; i < list.size(); i++) {
                Akaun ak = list.get(i);
                if (ak.getNoAkaun().equals(noAkaun)) {

                    List<Transaksi> tempList = new ArrayList<Transaksi>();

                    if ((!ak.getKodAkaun().getKod().equals("ACT"))) {
                        tempList = senaraiSemuaTransaksi(ak);
                        transList = tempList;
                        logger.info("tempList.size :" + tempList.size());
                    }
                    baki = ak.getBaki();
                    BigDecimal bakiKasar = new BigDecimal(0.00);
                    bakiKasar = doCalculateBaki(ak);
                    // to check denda semasa
                    BigDecimal dendaSemasa = new BigDecimal(0.00);
                    for (Transaksi transaksi : ak.getSemuaTransaksi()) {
                        if (transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("dendaLewat")) && transaksi.getUntukTahun() == Integer.parseInt(yy.format(new Date()))
                                && transaksi.getStatus().getKod() == 'A' && transaksi.getAkaunKredit() == null) {
                            dendaSemasa = dendaSemasa.add(transaksi.getAmaun());
                            logger.debug("already run denda semasa");
                        }
                    }
                    // to check notis 6A
                    BigDecimal notis6A = new BigDecimal(0.00);
                    for (Transaksi transaksi : ak.getSemuaTransaksi()) {
                        if (transaksi.getKodTransaksi().getKod().equals("72457")
                                && transaksi.getStatus().getKod() == 'A' && transaksi.getAkaunKredit() == null) {
                            notis6A = notis6A.add(transaksi.getAmaun());
                            logger.debug("already have notis 6A");
                        }
                    }

                    pihak = ak.getPemegang();
                    jumCukai = ak.getHakmilik().getCukaiSebenar();

                    pihakList = ak.getHakmilik().getSenaraiPihakBerkepentingan();
                    String adaBayar = "tiada";
                    for (Transaksi t : transList) {
                        if (t.getStatus().getKod() == 'T') {
                            adaBayar = "ada";
                            logger.debug("ade buat pembayaran :idTrans =" + t.getIdTransaksi());
                        }
                    }

                    if ("05".equals(etanahConf.getProperty("kodNegeri"))) {
                        denda = doCalculateDenda(bakiKasar, dendaSemasa);
                        z = doCalculateNotis6A(bakiKasar, notis6A);
                        if (!dendaSemasa.equals(new BigDecimal(0.00)) && !notis6A.equals(new BigDecimal(0.00))) {
                            logger.info("ada notis, ada denda");
                            if (adaBayar.equals("ada") && baki.compareTo(new BigDecimal(0.00)) > 0) {
                                logger.info("ade byar kurang");
                                denda = new BigDecimal(0.00);
                                z = new BigDecimal(0.00);
                                jum = baki;
                                jumDenda = baki;
                                notis = baki;
                            } else {
                                jum = baki.subtract(denda).subtract(z);
                                jumDenda = jum.add(denda);
                                notis = baki;
                            }
                        } else if (!dendaSemasa.equals(new BigDecimal(0.00)) && notis6A.equals(new BigDecimal(0.00))) {
                            logger.info("ada denda");
                            if (adaBayar.equals("ada") && baki.compareTo(new BigDecimal(0.00)) > 0) {
                                logger.info("ade byar kurang");
                                denda = new BigDecimal(0.00);
                                z = new BigDecimal(0.00);
                                jum = baki;
                                jumDenda = baki;
                                notis = new BigDecimal(0.00);
                            } else {
                                jum = baki.subtract(denda);
                                jumDenda = baki;
                                notis = jumDenda.add(z);
                            }
                        } else if (dendaSemasa.equals(new BigDecimal(0.00)) && !notis6A.equals(new BigDecimal(0.00))) {
                            logger.info("ada notis");
                            if (adaBayar.equals("ada") && baki.compareTo(new BigDecimal(0.00)) > 0) {
                                logger.info("ade byar kurang");
                                denda = new BigDecimal(0.00);
                                z = new BigDecimal(0.00);
                                jum = baki;
                                jumDenda = new BigDecimal(0.00);
                                notis = baki;
                            } else {
                                jum = baki.subtract(z);
                                jumDenda = jum.add(denda);
                                notis = jumDenda.add(z);
                            }
                        } else {
                            logger.info("tiada notis, tiada denda");
                            jum = baki;
                            jumDenda = jum.add(denda);
                            notis = jumDenda.add(z);
                        }
                    }
                    if ("04".equals(etanahConf.getProperty("kodNegeri"))) { //melaka)
                        logger.info("baki 1 ---------:" + baki);
                        denda = doCalculateDenda(baki, dendaSemasa);
                        z = doCalculateNotis6A(bakiKasar, notis6A);
                        if (!dendaSemasa.equals(new BigDecimal(0.00)) && !notis6A.equals(new BigDecimal(0.00))) {
                            logger.info("ada notis, ada denda");
                            if (adaBayar.equals("ada") && baki.compareTo(new BigDecimal(0.00)) > 0) {
                                logger.info("ade byar kurang");
                                denda = new BigDecimal(0.00);
                                z = new BigDecimal(0.00);
                                jum = baki;
                                jumDenda = baki;
                                notis = baki;
                            } else {
                                jum = baki.subtract(denda).subtract(z);
                                jumDenda = jum.add(denda);
                                notis = baki;
                            }
                        } else if (!dendaSemasa.equals(new BigDecimal(0.00)) && notis6A.equals(new BigDecimal(0.00))) {
                            logger.info("ada denda");
                            if (adaBayar.equals("ada") && baki.compareTo(new BigDecimal(0.00)) > 0) {
                                logger.info("ade byar kurang");
                                denda = new BigDecimal(0.00);
                                z = new BigDecimal(0.00);
                                jum = baki;
                                jumDenda = baki;
                                notis = new BigDecimal(0.00);
                            } else {
                                jum = baki.subtract(denda);
                                jumDenda = baki;
                                notis = jumDenda.add(z);
                            }
                        } else if (dendaSemasa.equals(new BigDecimal(0.00)) && !notis6A.equals(new BigDecimal(0.00))) {
                            logger.info("ada notis");
                            if (adaBayar.equals("ada") && baki.compareTo(new BigDecimal(0.00)) > 0) {
                                logger.info("ade byar kurang");
                                denda = new BigDecimal(0.00);
                                z = new BigDecimal(0.00);
                                jum = baki;
                                jumDenda = new BigDecimal(0.00);
                                notis = baki;
                            } else {
                                jum = baki.subtract(z);
                                jumDenda = jum.add(denda);
                                notis = jumDenda.add(z);
                            }
                        } else {
                            logger.info("tiada notis, tiada denda");
                            jum = baki;
                            jumDenda = jum.add(denda);
                            notis = jumDenda.add(z);
                        }
                    }
                }
            }

        } else {
            logger.debug("::HAKMILIK NULL::");
            addSimpleError("Hakmilik Tidak Dijumpai.Sila masukkan ID Hakmilik yang sah");
            return new JSP("common/pertanyaan_hakmilik.jsp");
        }

        logger.debug("::HAKMILIK NOT NULL::");
        /**
         * tulasi*
         */
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

//        dasarTuntutanCukai = hakmilikService.getdasarTuntuan();
//        for (DasarTuntutanCukaiHakmilik dt : dasarTuntutanCukai) {
//            if (idHakmilik == null ? dt.getHakmilik().getIdHakmilik().toString() == null : idHakmilik.equals(dt.getHakmilik().getIdHakmilik().toString())) {
//                for (DasarTuntutanNotis ds : dt.getSenaraiNotis()) {
//                    if (ds.getNotis().getKod() == null ? "N8A" == null : ds.getNotis().getKod().equals("N8A")) {
//                        setDatun(true);
//                    }
////                    else {
////                        setDatun(false);
////                    }
//                }
//            }
//        }
        agensiHakmilik = hakmilikService.findAgensiByHakmilik(idHakmilik);
        getContext().getRequest().setAttribute("datun", datun);
        /**
         * end*
         */

        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT m FROM etanah.model.Permohonan m, etanah.model.HakmilikPermohonan hp "
                + "WHERE m.kodUrusan.kod IN ('HSTHK','HKTHK') "
                + "AND m.idPermohonan = hp.permohonan.idPermohonan "
                + "AND m.status NOT IN ('SL','TK','BP') "
                + "AND hp.hakmilik.idHakmilik =:id");
        q.setString("id", akaun.getHakmilik().getIdHakmilik());
        q.setMaxResults(1);
        Permohonan p = (Permohonan) q.uniqueResult();

        if (p != null) {
            logger.info("--------------------------SINI----------------------------");
            logger.info("pengguna.getKodJabatan().getKod() : " + pengguna.getKodJabatan().getKod());
            if (pengguna.getKodJabatan().getKod().equals("16")) {
                flagTukarganti = true;
                note = "Hakmilik ini mempunyai urusan " + p.getKodUrusan().getNama() + " yang belum selesai.\n ID Perserahan : " + p.getIdPermohonan();
            }
        }

        return new JSP("common/tab_maklumat_hakmilik.jsp");
    }

    public Resolution save() throws WorkflowException, ParseException {

        String xx = getContext().getRequest().getParameter("idPihak");
        Pihak phk = pihakDAO.findById(Long.parseLong(xx));

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna p = ctx.getUser();
        Date now = new Date();
        InfoAudit info = p.getInfoAudit();
        info.setDimasukOleh(p);
        InfoAudit ia = new InfoAudit();
        info.setTarikhMasuk(new java.util.Date());
//        KodCawangan caw = p.getKodCawangan();

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
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        //idHakmilik =
        return papar();

    }

    public Resolution cetak() throws ParseException, java.io.FileNotFoundException {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();

        Date now1 = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now1);
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        String documentPath = conf.getProperty("document.path");
//        String documentPath = File.separator + "tmp" + File.separator;
        String path = tarikh + File.separator + String.valueOf(idHakmilik);
        reportUtil.generateReport("HSLPenyataCukaiTanah.rdf",
                new String[]{"p_id_hakmilik"},
                new String[]{idHakmilik},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);

        return new StreamingResolution("application/pdf", fis);

    }

    public Resolution paparMaklumatAsas() {
        return new JSP("common/maklumat_asas.jsp");
    }

    public Resolution paparPemilikan() {
        //hakmilik = hakmilikDAO.findById(idHakmilik);       
        return new JSP("common/maklumat_pemilik.jsp");
    }

    public Resolution paparUrusan() {
        return new JSP("common/maklumat_urusan.jsp");
    }

    public Resolution paparUrusanProses() {
        return new JSP("common/maklumat_urusan_proses.jsp");
    }

    public Resolution paparRekodSejarah() {
        return new JSP("common/rekod_sejarah.jsp");
    }

    public Resolution senaraiKodBPMByDaerah() {
        logger.debug(":::start search for kodbpm by daerah:::");
        String kodDaerah = (String) getContext().getRequest().getParameter("kodDaerah");
        if (StringUtils.isEmpty(kodDaerah)) {
            kodDaerah = daerah;
        }
        logger.debug("kodDaerah :" + kodDaerah);
        if (kodDaerah != null) {
            listBandarPekanMukim = regService.getSenaraiKodBPMByDaerah(kodDaerah);
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/partial_kodbpm2.jsp").addParameter("popup", "true");
    }

    public Resolution cari() {
        String result = "";
        if (namaBPM != null) {
            KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
            kbpm = regService.cariBPM(namaBPM, daerah);
            kodBPM = kbpm.getKod();
            //hakmilik.setBandarPekanMukim(kbpm);
        }
        logger.debug("start carian hakmilik");
        logger.debug("kodbpm : " + kodBPM);
        logger.debug("daerah : " + daerah);
        logger.debug("jenislot : " + jenisLot);
        logger.debug("nolot : " + noLot);
        String kodHakmilik = "";
        String noHakmilik = "";

        if (kodBPM != 0 && daerah != null && jenisLot != null && noLot != null) {

            Hakmilik h = regService.searchHakmilik(kodBPM, daerah, jenisLot, noLot, null, null, null);

            if (h != null) {
                logger.debug("found :" + h.getIdHakmilik());
                result = h.getIdHakmilik();
            } else {
                result = "0";
            }

        }
        if (kodBPM == 0) {
            //addSimpleError("Sila Pilih Kod Bandar Pekan Mukim");
            result = "1";
        }
        if (daerah == null) {
            //addSimpleError("Sila Pilih Daerah");
            result = "2";
        }
        if (jenisLot == null) {
            //addSimpleError("Sila Pilih Jenis Lot");
            result = "3";
        }
        if (noLot == null) {
            //addSimpleError("Sila Masukkan No Lot / No PT");
            result = "4";
        }
        //return new JSP("common/carian_hakmilik_terperinci.jsp").addParameter("tab", "true");
        return new StreamingResolution("text/plain", result);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        negeri = etanahConf.getProperty("kodNegeri");
        if ("04".equals(etanahConf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";

        }
        if ("05".equals(etanahConf.getProperty("kodNegeri"))) {
            kodNegeri = "negeri";

        }
        listBandarPekanMukim = regService.getSenaraiKodBPMByDaerah(daerah);

    }

    public Resolution bayar() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (idHakmilik != null) {
            logger.debug("::start looking for idhakmilik:" + idHakmilik);
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        return new ForwardResolution("/hasil/kutipan_hasil?search&idHakmilik=" + idHakmilik);
    }

    private List<Transaksi> senaraiSemuaTransaksi(Akaun akaun) {
        List<Transaksi> transList = new ArrayList<Transaksi>();
        Session ses = sessionProvider.get();
        String sql = "select t from etanah.model.Transaksi t ";
        sql += "where t.akaunDebit.noAkaun = :noAkaunDebit or t.akaunKredit.noAkaun = :noAkaunKredit ";
//               sql += "order by t.untukTahun, t.kodTransaksi.kod, t.infoAudit.tarikhMasuk ";
        sql += "order by t.infoAudit.tarikhMasuk, t.kodTransaksi.kod, t.untukTahun ";
        Query q = ses.createQuery(sql);
        q.setString("noAkaunDebit", akaun.getNoAkaun());
        q.setString("noAkaunKredit", akaun.getNoAkaun());
        transList = q.list();
        logger.debug("transList.size :" + transList.size());
        return transList;
    }

    public List<DasarTuntutanNotis> getDasarTuntutanNotisListByidNotis(long idHakmilik) {

        String query = "select dtn from etanah.model.DasarTuntutanNotis dtn, etanah.model.DasarTuntutanCukaiHakmilik dtch ";
        query += "where dtn.hakmilik.idDasarHakmili = dtch.idDasarHakmilik and dtn.notis='N8A' and dtch.hakmilik.idHakmilik=:idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<String> getSenaraiIdHakmilikAsal() {
        return senaraiIdHakmilikAsal;
    }

    public void setSenaraiIdHakmilikAsal(List<String> senaraiIdHakmilikAsal) {
        this.senaraiIdHakmilikAsal = senaraiIdHakmilikAsal;
    }

    public List<Hakmilik> getSenaraiHakmilikAsal() {
        return senaraiHakmilikAsal;
    }

    public void setSenaraiHakmilikAsal(List<Hakmilik> senaraiHakmilikAsal) {
        this.senaraiHakmilikAsal = senaraiHakmilikAsal;
    }

    public List<String> getSenaraiIdHakmilikSebelum() {
        return senaraiIdHakmilikSebelum;
    }

    public void setSenaraiIdHakmilikSebelum(List<String> senaraiIdHakmilikSebelum) {
        this.senaraiIdHakmilikSebelum = senaraiIdHakmilikSebelum;
    }

    public List<Hakmilik> getSenaraiHakmilikSebelum() {
        return senaraiHakmilikSebelum;
    }

    public void setSenaraiHakmilikSebelum(List<Hakmilik> senaraiHakmilikSebelum) {
        this.senaraiHakmilikSebelum = senaraiHakmilikSebelum;
    }

    public List<Permohonan> getMohonTolakGantung() {
        return mohonTolakGantung;
    }

    public void setMohonTolakGantung(List<Permohonan> mohonTolakGantung) {
        this.mohonTolakGantung = mohonTolakGantung;
    }

    public List<HakmilikUrusan> getHakmilikUrusanListTaktif() {
        return hakmilikUrusanListTaktif;
    }

    public void setHakmilikUrusanListTaktif(List<HakmilikUrusan> hakmilikUrusanListTaktif) {
        this.hakmilikUrusanListTaktif = hakmilikUrusanListTaktif;
    }

    public List<HakmilikAsal> getListSejarahHakmilikAsal() {
        return listSejarahHakmilikAsal;
    }

    public void setListSejarahHakmilikAsal(List<HakmilikAsal> listSejarahHakmilikAsal) {
        this.listSejarahHakmilikAsal = listSejarahHakmilikAsal;
    }

    public List<HakmilikSebelum> getListSejarahHakmilikSebelum() {
        return listSejarahHakmilikSebelum;
    }

    public void setListSejarahHakmilikSebelum(List<HakmilikSebelum> listSejarahHakmilikSebelum) {
        this.listSejarahHakmilikSebelum = listSejarahHakmilikSebelum;
    }

    public String getIdHakmilikPihakBerkepentingan() {
        return idHakmilikPihakBerkepentingan;
    }

    public void setIdHakmilikPihakBerkepentingan(String idHakmilikPihakBerkepentingan) {
        this.idHakmilikPihakBerkepentingan = idHakmilikPihakBerkepentingan;
    }

    public List<HakmilikAsal> getListHakmilikAsal() {
        return listHakmilikAsal;
    }

    public void setListHakmilikAsal(List<HakmilikAsal> listHakmilikAsal) {
        this.listHakmilikAsal = listHakmilikAsal;
    }

    public List<HakmilikSebelum> getListHakmilikSebelum() {
        return listHakmilikSebelum;
    }

    public void setListHakmilikSebelum(List<HakmilikSebelum> listHakmilikSebelum) {
        this.listHakmilikSebelum = listHakmilikSebelum;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public List<KodSeksyen> getSenaraiKodSeksyen() {
        return senaraiKodSeksyen;
    }

    public void setSenaraiKodSeksyen(List<KodSeksyen> senaraiKodSeksyen) {
        this.senaraiKodSeksyen = senaraiKodSeksyen;
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

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public List<Map<String, String>> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(List<Map<String, String>> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public List<Map<String, String>> getSenaraiUrusanProses() {
        return senaraiUrusanProses;
    }

    public void setSenaraiUrusanProses(List<Map<String, String>> senaraiUrusanProses) {
        this.senaraiUrusanProses = senaraiUrusanProses;
    }

    public String getBtn2() {
        return btn2;
    }

    public void setBtn2(String btn2) {
        this.btn2 = btn2;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public BigDecimal getAmaunRemesyen() {
        return amaunRemesyen;
    }

    public void setAmaunRemesyen(BigDecimal amaunRemesyen) {
        this.amaunRemesyen = amaunRemesyen;
    }

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public BigDecimal getJum() {
        return jum;
    }

    public void setJum(BigDecimal jum) {
        this.jum = jum;
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

    public BigDecimal getW() {
        return w;
    }

    public void setW(BigDecimal w) {
        this.w = w;
    }

    public BigDecimal getR() {
        return r;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getZ() {
        return z;
    }

    public void setZ(BigDecimal z) {
        this.z = z;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public List<HakmilikPihakBerkepentingan> getPihakList() {
        return pihakList;
    }

    public void setPihakList(List<HakmilikPihakBerkepentingan> pihakList) {
        this.pihakList = pihakList;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public HakmilikPihakBerkepentingan getHpk() {
        return hpk;
    }

    public void setHpk(HakmilikPihakBerkepentingan hpk) {
        this.hpk = hpk;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public List<HakmilikUrusan> getHakmilikUrusanProsesList() {
        return hakmilikUrusanProsesList;
    }

    public void setHakmilikUrusanProsesList(List<HakmilikUrusan> hakmilikUrusanProsesList) {
        this.hakmilikUrusanProsesList = hakmilikUrusanProsesList;
    }

    public List<Map<String, String>> getSenaraiUrusanProsesDistinct() {
        return senaraiUrusanProsesDistinct;
    }

    public void setSenaraiUrusanProsesDistinct(List<Map<String, String>> senaraiUrusanProsesDistinct) {
        this.senaraiUrusanProsesDistinct = senaraiUrusanProsesDistinct;
    }

    public List<HakmilikUrusan> getHakmilikUrusanList() {
        return hakmilikUrusanList;
    }

    public void setHakmilikUrusanList(List<HakmilikUrusan> hakmilikUrusanList) {
        this.hakmilikUrusanList = hakmilikUrusanList;
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakKepentinganListAktif() {
        return hakmilikPihakKepentinganListAktif;
    }

    public void setHakmilikPihakKepentinganListAktif(List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganListAktif) {
        this.hakmilikPihakKepentinganListAktif = hakmilikPihakKepentinganListAktif;
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakKepentinganListTakAktif() {
        return hakmilikPihakKepentinganListTakAktif;
    }

    public void setHakmilikPihakKepentinganListTakAktif(List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganListTakAktif) {
        this.hakmilikPihakKepentinganListTakAktif = hakmilikPihakKepentinganListTakAktif;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public String getJenisLot() {
        return jenisLot;
    }

    public void setJenisLot(String jenisLot) {
        this.jenisLot = jenisLot;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public int getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(int kodBPM) {
        this.kodBPM = kodBPM;
    }

    public String getNamaBPM() {
        return namaBPM;
    }

    public void setNamaBPM(String namaBPM) {
        this.namaBPM = namaBPM;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getKodPengenalan() {
        return kodPengenalan;
    }

    public void setKodPengenalan(String kodPengenalan) {
        this.kodPengenalan = kodPengenalan;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public List<HakmilikPihakBerkepentingan> getL() {
        return l;
    }

    public void setL(List<HakmilikPihakBerkepentingan> l) {
        this.l = l;
    }

    public Pihak getPemegang() {
        return pemegang;
    }

    public void setPemegang(Pihak pemegang) {
        this.pemegang = pemegang;
    }

    public Akaun getAkaunKredit() {
        return akaunKredit;
    }

    public void setAkaunKredit(Akaun akaunKredit) {
        this.akaunKredit = akaunKredit;
    }

    public List<SejarahTransaksi> getSejarahList() {
        return sejarahList;
    }

    public void setSejarahList(List<SejarahTransaksi> sejarahList) {
        this.sejarahList = sejarahList;
    }

    public String getPegang() {
        return pegang;
    }

    public void setPegang(String pegang) {
        this.pegang = pegang;
    }

    public Transaksi getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Transaksi idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public SejarahDokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(SejarahDokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public String getCaw() {
        return caw;
    }

    public void setCaw(String caw) {
        this.caw = caw;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getNamaPembayar() {
        return namaPembayar;
    }

    public void setNamaPembayar(String namaPembayar) {
        this.namaPembayar = namaPembayar;
    }

    public String getNoPengenalanP() {
        return noPengenalanP;
    }

    public void setNoPengenalanP(String noPengenalanP) {
        this.noPengenalanP = noPengenalanP;
    }

    public String getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public BigDecimal getAmaunDenda() {
        return amaunDenda;
    }

    public void setAmaunDenda(BigDecimal amaunDenda) {
        this.amaunDenda = amaunDenda;
    }

    public String getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(String bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonanPemilikan() {
        return permohonanPemilikan;
    }

    public void setPermohonanPemilikan(Permohonan permohonanPemilikan) {
        this.permohonanPemilikan = permohonanPemilikan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    /**
     * @return the kodStatusHakmilik
     */
    public String getKodStatusHakmilik() {
        return kodStatusHakmilik;
    }

    /**
     * @param kodStatusHakmilik the kodStatusHakmilik to set
     */
    public void setKodStatusHakmilik(String kodStatusHakmilik) {
        this.kodStatusHakmilik = kodStatusHakmilik;
    }

    /**
     * @return the jumCukai
     */
    public BigDecimal getJumCukai() {
        return jumCukai;
    }

    /**
     * @param jumCukai the jumCukai to set
     */
    public void setJumCukai(BigDecimal jumCukai) {
        this.jumCukai = jumCukai;
    }

    /**
     * @return the a
     */
    public BigDecimal getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(BigDecimal a) {
        this.a = a;
    }

    /**
     * @return the amaunDebit
     */
    public BigDecimal getAmaunDebit() {
        return amaunDebit;
    }

    /**
     * @param amaunDebit the amaunDebit to set
     */
    public void setAmaunDebit(BigDecimal amaunDebit) {
        this.amaunDebit = amaunDebit;
    }

    /**
     * @return the totalAmaunDebit
     */
    public BigDecimal getTotalAmaunDebit() {
        return totalAmaunDebit;
    }

    /**
     * @param totalAmaunDebit the totalAmaunDebit to set
     */
    public void setTotalAmaunDebit(BigDecimal totalAmaunDebit) {
        this.totalAmaunDebit = totalAmaunDebit;
    }

    /**
     * Tulasi*
     */
    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }
    String st;

    public Boolean getDatun() {
        return datun;
    }

    public void setDatun(Boolean datun) {
        this.datun = datun;
    }
    private Boolean datun = false;

    public DasarTuntutanCukaiHakmilik getDasarTuntutan() {
        return dasarTuntutan;
    }

    public void setDasarTuntutan(DasarTuntutanCukaiHakmilik dasarTuntutan) {
        this.dasarTuntutan = dasarTuntutan;
    }

    public List<DasarTuntutanCukaiHakmilik> getDasarTuntutanCukai() {
        return dasarTuntutanCukai;
    }

    public void setDasarTuntutanCukai(List<DasarTuntutanCukaiHakmilik> dasarTuntutanCukai) {
        this.dasarTuntutanCukai = dasarTuntutanCukai;
    }

    public DasarTuntutanNotis getDasarTuntutanNotis() {
        return dasarTuntutanNotis;
    }

    public void setDasarTuntutanNotis(DasarTuntutanNotis dasarTuntutanNotis) {
        this.dasarTuntutanNotis = dasarTuntutanNotis;
    }
    private DasarTuntutanNotis dasarTuntutanNotis;

    /**
     * @return the kumpulan
     */
    public KumpulanAkaun getKumpulan() {
        return kumpulan;
    }

    /**
     * @param kumpulan the kumpulan to set
     */
    public void setKumpulan(KumpulanAkaun kumpulan) {
        this.kumpulan = kumpulan;
    }

    /**
     * @return the list
     */
    public List<Akaun> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Akaun> list) {
        this.list = list;
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

    public List<Hakmilik> getSenaraiHakmilikBerikut() {
        return senaraiHakmilikBerikut;
    }

    public void setSenaraiHakmilikBerikut(List<Hakmilik> senaraiHakmilikBerikut) {
        this.senaraiHakmilikBerikut = senaraiHakmilikBerikut;
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakKepentinganList() {
        return hakmilikPihakKepentinganList;
    }

    public void setHakmilikPihakKepentinganList(List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganList) {
        this.hakmilikPihakKepentinganList = hakmilikPihakKepentinganList;
    }

    public HakmilikLama getHakmilikLama() {
        return hakmilikLama;
    }

    public void setHakmilikLama(HakmilikLama hakmilikLama) {
        this.hakmilikLama = hakmilikLama;
    }

    public boolean isTukarGanti() {
        return tukarGanti;
    }

    public void setTukarGanti(boolean tukarGanti) {
        this.tukarGanti = tukarGanti;
    }

    /**
     * Tulasi*
     */
    /**
     * * added by mansur 02062011**
     */
    private BigDecimal doCalculateBaki(Akaun akaun) {
        BigDecimal jumlahKasar = new BigDecimal(0.00);
        for (Transaksi trans : akaun.getSenaraiTransaksiDebit()) {
            logger.info("(doCalculateBaki) idtrans :" + trans.getIdTransaksi());
            if (trans.getStatus().getKod() == 'A' && trans.getAkaunKredit() == null) {
                jumlahKasar = jumlahKasar.add(trans.getAmaun());
                logger.debug("(doCalculateBaki) amaun tambah :" + trans.getAmaun());
            }
        }
        logger.debug("(doCalculateBaki) jumlahKasar :" + jumlahKasar);
        return jumlahKasar;
    }

    private BigDecimal doCalculateDenda(BigDecimal baki, BigDecimal dendaSemasa) {
        BigDecimal jumlah = new BigDecimal(0.00);
        if (!dendaSemasa.equals(new BigDecimal(0.00))) {
            logger.info("(doCalculateDenda) sudah run denda semasa.");
            jumlah = dendaSemasa;
        } else {
            logger.info("(doCalculateDenda) belum run denda semasa.");
            if ("05".equals(etanahConf.getProperty("kodNegeri"))) {
                BigDecimal percent = new BigDecimal(0.05);
                jumlah = baki.multiply(percent).setScale(0, RoundingMode.UP);
            }
            if ("04".equals(etanahConf.getProperty("kodNegeri"))) {
                if (baki.intValue() > 100) { // denda 10% drpd jumlah keseluruhan
                    BigDecimal percent = new BigDecimal(0.10);
                    logger.info("baki -----------------:" + baki);
//                    jumlah = baki.multiply(percent).setScale(1, RoundingMode.UP);
                    jumlah = baki.multiply(percent).setScale(4, RoundingMode.DOWN);
                    logger.info("jumlah :" + jumlah);
                    jumlah = jumlah.setScale(1, RoundingMode.UP);
                } else if (baki.intValue() > 50 && baki.intValue() <= 100) { // denda RM10
                    jumlah = new BigDecimal(10.00);
                } else if (baki.intValue() > 20 && baki.intValue() <= 50) { // denda RM5
                    jumlah = new BigDecimal(5.00);
                } else if (baki.intValue() >= 5 && baki.intValue() <= 20) { // denda RM1.50
                    jumlah = new BigDecimal(1.50);
                } else if (baki.intValue() > 0 && baki.intValue() < 5) { // denda RM1
                    jumlah = new BigDecimal(1.00);
                }
            }
        }
        logger.debug("(doCalculateDenda) denda semasa : RM" + jumlah);
        return jumlah;
    }

    private BigDecimal doCalculateNotis6A(BigDecimal baki, BigDecimal notis6Asemasa) {
        BigDecimal jumlah6A = new BigDecimal(0.00);
        if (!notis6Asemasa.equals(new BigDecimal(0.00))) {
            logger.info("(doCalculateNotis6A) sudah masuk notis 6A.");
            jumlah6A = notis6Asemasa;
        } else {
            logger.info("(doCalculateNotis6A) belum masuk notis 6A.");
            if ("05".equals(etanahConf.getProperty("kodNegeri"))) {
                jumlah6A = jumlah6A.add(new BigDecimal(10.00)); // fixed for N9 = RM10
            }
            if ("04".equals(etanahConf.getProperty("kodNegeri"))) {
                jumlah6A = jumlah6A.add(new BigDecimal(20.00)); // fixed for MLK = RM20
            }
        }
        logger.info("(doCalculateNotis6A) notis6A semasa : RM" + jumlah6A);
        return jumlah6A;
    }

    public AgensiHakmilik getAgensiHakmilik() {
        return agensiHakmilik;
    }

    public void setAgensiHakmilik(AgensiHakmilik agensiHakmilik) {
        this.agensiHakmilik = agensiHakmilik;
    }

    public List<LogAkaunKewangan> getSenaraiSejarah() {
        return senaraiSejarah;
    }

    public void setSenaraiSejarah(List<LogAkaunKewangan> senaraiSejarah) {
        this.senaraiSejarah = senaraiSejarah;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isFlagTukarganti() {
        return flagTukarganti;
    }

    public void setFlagTukarganti(boolean flagTukarganti) {
        this.flagTukarganti = flagTukarganti;
    }
}
