
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

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
import java.util.List;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.model.KodBandarPekanMukim;
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
import etanah.dao.PihakDAO;
import etanah.model.SejarahTransaksi;
import etanah.dao.SejarahTransaksiDAO;
import java.util.ArrayList;
import etanah.model.Transaksi;
import etanah.model.Akaun;
import etanah.model.strata.*;
import etanah.dao.AkaunDAO;
import etanah.dao.AkaunStrataDAO;
import etanah.dao.BadanPengurusanDAO;
import java.math.BigDecimal;
import java.util.Date;
import etanah.model.InfoAudit;
import etanah.service.HakmilikService;
import etanah.service.StrataPtService;
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
import oracle.bpel.services.workflow.task.model.Task;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import etanah.manager.TabManager;
import etanah.model.AkaunStrata;
import etanah.model.CaraBayaran;//
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Dokumen;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikPetakAksesori;
import etanah.model.KodSeksyen;
import etanah.model.LogAkaunKewangan;
import etanah.model.strata.BadanPengurusan;
import etanah.service.RegService;
import etanah.service.common.HakmilikPihakKepentinganService;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/strata/pertanyaan_hakmilik")
public class PertanyaanHakmilikStrataActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(PertanyaanHakmilikStrataActionBean.class);
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
    private String noBangunan;
    private String noTingkat;
    private String noPetak;
    private String lot;
    private String tarikhtkrgnti;
    String namaBPM;
    int kodBPM;
    String daerah;
    String noLot;
    String jenisHakmilik;
    String jenisLot;
    private Hakmilik hakmilik;
    private Hakmilik hakmilikInduk;
    private Hakmilik hmInduk;
    private HakmilikPetakAksesori hakmilikPetak;
    private Pengguna pengguna;
    private Akaun akaunKredit;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private List<Dokumen> listDokumen;
    private List<KodSeksyen> senaraiKodSeksyen;
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
    HakmilikPihakKepentinganService hpkService;
    @Inject
    StrataPtService strataPtService;
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
    AkaunStrataDAO akaunStrataDAO;
    @Inject
    StrataPtService ptService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPihakBerkepentinganDAO hpDAO;
    @Inject
    TabManager tabManager;
    @Inject
    private etanah.Configuration conf;
    @Inject
    BadanPengurusanDAO badanPengurusanDAO;
    String idHakmilik;
    String idHakmilikInduk;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganListAktif;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganListTakAktif;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganList;
    private List<HakmilikUrusan> hakmilikUrusanList;
    private List<HakmilikUrusan> hakmilikUrusanIndukList;
    private List<HakmilikUrusan> hakmilikUrusanListTaktif;
    private List<HakmilikUrusan> hakmilikUrusanProsesList;
    private List<Permohonan> mohonTolakGantung;
    String idPihak;
    String idHakmilikPihakBerkepentingan;
    private String pegang;
    Pihak pihak;
    HakmilikPihakBerkepentingan hpk;
    private List<Transaksi> transList;
    private HakmilikTukarGantiStrata hmtkrGnti;
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
    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sd = new SimpleDateFormat("dd/MM");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    private List<Hakmilik> listHakmilikStrata = new ArrayList();
    private List<Hakmilik> listHakmilikInduk = new ArrayList();
    private List<Hakmilik> listHakmilikProv = new ArrayList();
    private boolean flag = false;
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
    private String badanPengurusan;
    private String petakAksesori;
    private String petakPelan;
    private List<HakmilikPihakBerkepentingan> l;
    private List<Akaun> senaraiAkaun;
    private List<LogAkaunKewangan> senaraiSejarah = new ArrayList<LogAkaunKewangan>();
    private List<Akaun> listAkaun = new ArrayList();
    private Pihak pemegang;
    private Transaksi idTransaksi;
    private boolean test = false;
    private SejarahDokumenKewangan dokumenKewangan;
    private List<HakmilikAsal> listHakmilikAsal = new ArrayList();
    private List<HakmilikSebelum> listHakmilikSebelum = new ArrayList();
    private List<HakmilikAsal> listSejarahHakmilikAsal;
    private List<HakmilikSebelum> listSejarahHakmilikSebelum;
    private BigDecimal a = new BigDecimal(0);
    private KumpulanAkaun kumpulan;
    private List<DasarTuntutanCukaiHakmilik> dasarTuntutanCukai = new ArrayList<DasarTuntutanCukaiHakmilik>();
    private DasarTuntutanCukaiHakmilik dasarTuntutan;
    private BadanPengurusan badanUrus;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("common/carian_hakmilik_terperinci.jsp").addParameter("popup", "true");
    }

    public Resolution pertanyaanHakmilik() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new JSP("strata/pertanyaan_hakmilik_strata.jsp");
    }

    public Resolution tabPertanyaanHakmilik() {
        return new JSP("strata/tab_maklumat_hakmilik_strata.jsp").addParameter("popup", "true");
    }

    public Resolution kembali() {
        return new ForwardResolution("/WEB-INF/jsp/hasil/kutipan_hasil_1.jsp");
    }

    public Resolution back() {
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

        return new ForwardResolution("/WEB-INF/jsp/strata/pertanyaan_hakmilik_strata.jsp").addParameter("popup", "true");

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

        return new ForwardResolution("/WEB-INF/jsp/strata/pertanyaan_hakmilik_strata.jsp");
    }

    public Resolution search() throws WorkflowException, ParseException {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String action = (String) getContext().getRequest().getParameter("action");

        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
//            __pg_start = (Integer.parseInt(page) - 1) * __pg_max_records;
//            __pg_max_records = __pg_start + __pg_max_records;
        }

        //Added by Aizuddin for hakmilik
        idHakmilikInduk = (String) getContext().getRequest().getParameter("idHakmilikInduk");
        if (StringUtils.isNotBlank(idHakmilikInduk)) {
            listHakmilikInduk = hakmilikService.findHakmilikStratabyIDHakmilikIndukMLK(idHakmilikInduk);
            List<String> nobgnn = strataPtService.findNoBangunanProv(idHakmilikInduk);
            for (int i = 0; i < nobgnn.size(); i++) {
                String nobgn = nobgnn.get(i);
                List<Hakmilik> hmbngn = strataPtService.findHm(idHakmilikInduk, nobgn);
                if (hmbngn != null) {
                    listHakmilikProv.add(hmbngn.get(0));
                }
            }
//            listHakmilikProv = strataPtService.findHakmilibyParentProv(idHakmilikInduk);
            logger.info("listHakmilikProv----" + listHakmilikProv.size());
            for (Hakmilik hm : listHakmilikInduk) {
                if (hm.getKodStatusHakmilik().getKod().equals("SU")) {
                    addSimpleError("Hakmilik Strata di atas Hakmilik " + idHakmilikInduk + " adalah Hakmilik Subsidiari.");
                    return new JSP("strata/pertanyaan_hakmilik_strata.jsp");
                }
            }
        } else {
            listHakmilikStrata = hakmilikService.findHakmilikStrata(getContext().getRequest().getParameterMap(), get__pg_start(), get__pg_max_records(), pengguna.getKodCawangan().getKod());
            set__pg_total_records(hakmilikService.getTotalRecordHakmilikStrata(getContext().getRequest().getParameterMap(), pengguna.getKodCawangan().getKod()).intValue());
            if (!listHakmilikStrata.isEmpty()) {
                List<String> nobgnn = strataPtService.findNoBangunanProv(listHakmilikStrata.get(0).getIdHakmilikInduk());
                for (int i = 0; i < nobgnn.size(); i++) {
                    String nobgn = nobgnn.get(i);
                    List<Hakmilik> hmbngn = strataPtService.findHm(listHakmilikStrata.get(0).getIdHakmilikInduk(), nobgn);
                    if (hmbngn != null) {
                        listHakmilikProv.add(hmbngn.get(0));
                    }
                }
            }
            if (isDebug) {
                logger.debug("page_start = " + get__pg_start());
                logger.debug("max_records = " + get__pg_max_records());
                logger.debug("total record = " + get__pg_total_records());
            }
        }
        setFlag(true);
        penyukatanBPM();

        if (listHakmilikStrata != null || listHakmilikInduk != null) {
            if (listHakmilikStrata.size() == 1) {
                idHakmilik = listHakmilikStrata.get(0).getIdHakmilik();
            }

            if (listHakmilikInduk.size() == 1) {
                idHakmilikInduk = listHakmilikInduk.get(0).getIdHakmilikInduk();
            }
            //add new function kembali !!
            if (StringUtils.isNotBlank(action)) {
                return new JSP("strata/pertanyaan_hakmilik_strata.jsp");
            } else {
                return papar();
            }
        } else {
            return new JSP("strata/pertanyaan_hakmilik_strata.jsp");
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
            idHakmilikInduk = getContext().getRequest().getParameter("idHakmilikInduk");
        }
        String noAkaunStrata = getContext().getRequest().getParameter("noAkaunStrata");
        if(StringUtils.isNotEmpty(noAkaunStrata)){
            AkaunStrata akaunStrata = akaunStrataDAO.findById(noAkaunStrata);
            if(akaunStrata!=null){
                idHakmilik = akaunStrata.getHakmilik().getIdHakmilik();
            }
        
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            logger.debug("::start looking for idhakmilik:" + idHakmilik);
            hakmilik = hakmilikDAO.findById(idHakmilik);
            listAkaun = regService.findAkaunByIdHakmilik(idHakmilik);
            noAkaun = listAkaun.get(0).getNoAkaun();
            listDokumen = strataPtService.findGeranStrata(idHakmilik);

            if (hakmilik.getKodKategoriBangunan() != null) {
                if (hakmilik.getKodKategoriBangunan().getKod().equals("P")) {
                    listDokumen = strataPtService.findGeranStrataProv(hakmilik.getIdHakmilikInduk());
                }
            }
            if (!listDokumen.isEmpty()) {
                tarikhtkrgnti = dt.format(listDokumen.get(0).getInfoAudit().getTarikhMasuk());

                hmtkrGnti = strataPtService.hakmilikTkrgantiStrata(idHakmilik);
                if (hmtkrGnti != null) {
                    tarikhtkrgnti = dt.format(hmtkrGnti.getTarikhTukarganti4k());
                }
            }

            String IdInduk = hakmilik.getIdHakmilikInduk();

            if (IdInduk == null) {
                logger.info("sdfs" + badanPengurusan);
                if (StringUtils.isNotBlank(badanPengurusan)) {
                    addSimpleError("Hakmilik Tidak Dijumpai.Sila Masukkan Nama Perbadanan Pengurusan Yang Sah.");
                } else {
                    addSimpleError("Hakmilik Tidak Dijumpai.Sila masukkan ID Hakmilik yang sah");
                }
                return new JSP("strata/pertanyaan_hakmilik_strata.jsp");
            } else {
                hmInduk = hakmilikDAO.findById(IdInduk);
            }

            //Added by Aizuddin for hakmilik induk
        } else if (StringUtils.isNotBlank(idHakmilikInduk)) {
            logger.debug("::start looking for idhakmilik:" + idHakmilikInduk);
            hakmilikInduk = hakmilikDAO.findById(idHakmilikInduk);

        } else if (!listHakmilikStrata.isEmpty()) {
            addSimpleMessage("Hakmilik Berjaya Dijumpai.");
            return new JSP("strata/pertanyaan_hakmilik_strata.jsp");
        } else {
            logger.info("sdfs" + badanPengurusan);
            if (StringUtils.isNotBlank(badanPengurusan)) {
                addSimpleError("Hakmilik Tidak Dijumpai.Sila Masukkan Nama Perbadanan Pengurusan Yang Sah.");
            } else {
                addSimpleError("Hakmilik Tidak Dijumpai.Sila masukkan ID Hakmilik yang sah");
            }
            return new JSP("strata/pertanyaan_hakmilik_strata.jsp");
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

            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//            IWorkflowContext ctx = WorkFlowService.authenticateAdmin();
//            IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
            //List<Permohonan> listpermohonan = permohonanService.getSenaraiPermohonanByIdHakmilik(hakmilik.getIdHakmilik());
            List<Permohonan> listpermohonan = permohonanService.getSenaraiMohonByIdHakmilik(hakmilik.getIdHakmilik());
            List<HakmilikSebelumPermohonan> lhsp = regService.searchMohonHakmilikSblmByIDHakmilik(idHakmilik);

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
            hakmilikUrusanIndukList = huService.findHakmilikUrusanByIdHakmilik(hakmilik.getIdHakmilikInduk());
            hakmilikUrusanListTaktif = huService.findHakmilikUrusanTAktif(hakmilik.getIdHakmilik());
            List<HakmilikPermohonan> listmohonHakmilik = regService.searchMohonHakmilik(hakmilik.getIdHakmilik());
            mohonTolakGantung = new ArrayList();
            for (HakmilikPermohonan hp : listmohonHakmilik) {
                Permohonan p = permohonanService.searchPermohonanTolakGantung(hp.getPermohonan().getIdPermohonan());
                if (p != null) {
                    mohonTolakGantung.add(p);
                }
            }
//            listSejarahHakmilikAsal = regService.cariHakmilikAsal(idHakmilik);
//            listSejarahHakmilikSebelum = regService.cariHakmilikSebelum(idHakmilik);
            listSejarahHakmilikAsal = hakmilik.getSenaraiHakmilikAsal();
            listSejarahHakmilikSebelum = hakmilik.getSenaraiHakmilikSebelum();
            sejarahList = new ArrayList<SejarahTransaksi>();
            if (hakmilik.getIdHakmilikInduk() != null) {
                badanUrus = hakmilik.getBadanPengurusan();
            }

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
                        if (transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("dendaLewatStrata")) && transaksi.getUntukTahun() == Integer.parseInt(yy.format(new Date()))
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
        } else if (hakmilikInduk != null) {
            getContext().getRequest().setAttribute("induk", true);
        } else {
            logger.debug("::HAKMILIK NULL::");
            addSimpleError("Hakmilik Tidak Dijumpai.Sila masukkan ID Hakmilik yang sah");
            return new JSP("strata/pertanyaan_hakmilik_strata.jsp");
        }

        logger.debug("::HAKMILIK NOT NULL::");
        /**
         * tulasi*
         */
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        List<HakmilikPetakAksesori> hakmilikPetak = ptService.findIDforPetak(idHakmilik);
        a = BigDecimal.ZERO;
        for (HakmilikPetakAksesori hpa : hakmilikPetak) {
//            if (hpa != null && hpa.getKegunaaanPetak() != null) {
            if (hpa.getNama() != null) {
//                petakAksesori = hpa.getKegunaaanPetak().getNama();
                String petakAksesorilist = "A" + hpa.getNama() + " ";
//                    petakAksesorilist = "A" + hpa.getNama() + " ";
                petakAksesori += petakAksesorilist;
                petakAksesori = petakAksesori.replace("null", "");
                a = a.add(hpa.getLuas() != null ? hpa.getLuas() : BigDecimal.ZERO);

            }
        }
        Hakmilik hm = hakmilikDAO.findById(idHakmilik);
        if (hm != null) {
            if (hm.getNoPelan() != null) {
                petakPelan += "PA(B)" + hm.getNoPelan() + " ";

                for (HakmilikPetakAksesori hpa : hakmilikPetak) {
//            if (hpa != null && hpa.getKegunaaanPetak() != null) {
                    if (hpa.getNoPelan() != null) {
//                petakAksesori = hpa.getKegunaaanPetak().getNama();
                        String petakPelanlist = "PA(B)" + hpa.getNoPelan() + " ";
                        petakPelan += petakPelanlist;
                    }
                }

                petakPelan = petakPelan.replace("null", "");
                logger.info("::: petakPelan " + petakPelan);

                if (petakAksesori == null) {
                    petakAksesori = "TIADA";
                }
            } else {
                petakPelan = "TIADA";
            }

            if (hm.getIdHakmilikInduk() != null) {
                if (hm.getKodKategoriBangunan().getKod().equals("P") || hm.getKodKategoriBangunan().getKod().equals("PL")) {
                    List<Hakmilik> listHM = strataPtService.findHakmilibyParent(hm.getIdHakmilikInduk());
                    BigDecimal syer = BigDecimal.ZERO;
                    for (Hakmilik hk : listHM) {
                        if (hk.getKodKategoriBangunan().getKod().equals("P") || hm.getKodKategoriBangunan().getKod().equals("PL")) {
                            if (hm.getNoBangunan().equals(hk.getNoBangunan())) {
                                syer = syer.add(hk.getUnitSyer());
                            }
                        }
                    }
                    btn2 = String.valueOf(syer);
                }
            }
        }

        if (hakmilikInduk != null) {
            return new JSP("strata/pertanyaan_hakmilik_strata.jsp");
        } else {
            //Nk check ade x die pass id_hakmilik_induk
            logger.info(":::Dari idHakmilikInduk " + idHakmilikInduk + " ke idHakmilikStrata " + idHakmilik);
            return new JSP("strata/tab_maklumat_hakmilik_strata.jsp");
        }
    }

    public Resolution paparMaklumatAsas() {
        return new JSP("strata/maklumat_asas_strata.jsp");
    }

    public Resolution paparPemilikan() {
        //hakmilik = hakmilikDAO.findById(idHakmilik);       
        return new JSP("common/maklumat_pemilik.jsp");
    }

    public Resolution paparBadanPengurusan() {
        return new JSP("strata/maklumat_badan_pengurusan.jsp");
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
        listBandarPekanMukim = regService.getSenaraiKodBPMByDaerah(daerah);

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

    private BigDecimal doCalculateBaki(Akaun akaun) {
        BigDecimal jumlahKasar = new BigDecimal(0.00);
        for (Transaksi trans : akaun.getSenaraiTransaksiDebit()) {
            logger.info("(doCalculateBaki) idtrans :" + trans.getIdTransaksi());
            if (trans.getStatus().getKod() == 'A' && trans.getAkaunKredit() == null) {
                if (trans.getAmaun() != null) {
                    jumlahKasar = jumlahKasar.add(trans.getAmaun());
                } else {
                    jumlahKasar = jumlahKasar.add(BigDecimal.ZERO);
                }
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Hakmilik> getListHakmilikStrata() {
        return listHakmilikStrata;
    }

    public void setListHakmilikStrata(List<Hakmilik> listHakmilikStrata) {
        this.listHakmilikStrata = listHakmilikStrata;
    }

    public String getBadanPengurusan() {
        return badanPengurusan;
    }

    public void setBadanPengurusan(String badanPengurusan) {
        this.badanPengurusan = badanPengurusan;
    }

    public String getPetakAksesori() {
        return petakAksesori;
    }

    public void setPetakAksesori(String petakAksesori) {
        this.petakAksesori = petakAksesori;
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

    public BadanPengurusan getBadanUrus() {
        return badanUrus;
    }

    public void setBadanUrus(BadanPengurusan badanUrus) {
        this.badanUrus = badanUrus;
    }

    public String getIdHakmilikInduk() {
        return idHakmilikInduk;
    }

    public void setIdHakmilikInduk(String idHakmilikInduk) {
        this.idHakmilikInduk = idHakmilikInduk;
    }

    public List<Hakmilik> getListHakmilikInduk() {
        return listHakmilikInduk;
    }

    public void setListHakmilikInduk(List<Hakmilik> listHakmilikInduk) {
        this.listHakmilikInduk = listHakmilikInduk;
    }

    public Hakmilik getHakmilikInduk() {
        return hakmilikInduk;
    }

    public void setHakmilikInduk(Hakmilik hakmilikInduk) {
        this.hakmilikInduk = hakmilikInduk;
    }

    public Hakmilik getHmInduk() {
        return hmInduk;
    }

    public void setHmInduk(Hakmilik hmInduk) {
        this.hmInduk = hmInduk;
    }

    public List<KodSeksyen> getSenaraiKodSeksyen() {
        return senaraiKodSeksyen;
    }

    public void setSenaraiKodSeksyen(List<KodSeksyen> senaraiKodSeksyen) {
        this.senaraiKodSeksyen = senaraiKodSeksyen;
    }

    public String getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }

    public String getNoBangunan() {
        return noBangunan;
    }

    public void setNoBangunan(String noBangunan) {
        this.noBangunan = noBangunan;
    }

    public String getNoTingkat() {
        return noTingkat;
    }

    public void setNoTingkat(String noTingkat) {
        this.noTingkat = noTingkat;
    }

    public String getNoPetak() {
        return noPetak;
    }

    public void setNoPetak(String noPetak) {
        this.noPetak = noPetak;
    }

    public List<Dokumen> getListDokumen() {
        return listDokumen;
    }

    public void setListDokumen(List<Dokumen> listDokumen) {
        this.listDokumen = listDokumen;
    }

    public String getTarikhtkrgnti() {
        return tarikhtkrgnti;
    }

    public void setTarikhtkrgnti(String tarikhtkrgnti) {
        this.tarikhtkrgnti = tarikhtkrgnti;
    }

    public String getPetakPelan() {
        return petakPelan;
    }

    public void setPetakPelan(String petakPelan) {
        this.petakPelan = petakPelan;
    }

    public HakmilikTukarGantiStrata getHmtkrGnti() {
        return hmtkrGnti;
    }

    public void setHmtkrGnti(HakmilikTukarGantiStrata hmtkrGnti) {
        this.hmtkrGnti = hmtkrGnti;
    }

    public List<Hakmilik> getListHakmilikProv() {
        return listHakmilikProv;
    }

    public void setListHakmilikProv(List<Hakmilik> listHakmilikProv) {
        this.listHakmilikProv = listHakmilikProv;
    }

    public List<HakmilikUrusan> getHakmilikUrusanIndukList() {
        return hakmilikUrusanIndukList;
    }

    public void setHakmilikUrusanIndukList(List<HakmilikUrusan> hakmilikUrusanIndukList) {
        this.hakmilikUrusanIndukList = hakmilikUrusanIndukList;
    }

}
