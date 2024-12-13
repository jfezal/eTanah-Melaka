/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.Configuration;
import etanah.dao.DokumenCapaianDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.JuruLelongDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.KodStsPembidaDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.PembidaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanFasaGisDAO;
import etanah.dao.PihakDAO;
import etanah.dao.SytJuruLelongDAO;
import etanah.dao.WakilPihakDAO;
import etanah.model.AkuanTerimaDeposit;
import etanah.model.Dokumen;
import etanah.model.DokumenCapaian;
import etanah.model.DokumenKewangan;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.JuruLelong;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodStatusLelongan;
import etanah.model.KodStsPembida;
import etanah.model.Lelongan;
import etanah.model.Notis;
import etanah.model.Pembida;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanCarian;
import etanah.model.PermohonanFasaGis;
import etanah.model.PermohonanPihak;
//import etanah.model.PermohonanFasa;
import etanah.model.PermohonanPihakKemaskini;
import etanah.model.Pihak;
import etanah.model.SytJuruLelong;
import etanah.model.Transaksi;
import etanah.model.WakilPihak;
import etanah.service.HakmilikService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.LelongService;
import etanah.service.common.PihakService;
import etanah.service.daftar.PembetulanService;
import etanah.util.DateUtil;
import etanah.util.FileUtil;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.metadata.routingslip.model.ParticipantsType;
import oracle.bpel.services.workflow.task.model.Task;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author nor wazer hasan
 */
@UrlBinding("/lelong/utilitiSemakanPembida")
public class UtilitiSemakanPembidaActionBean extends AbleActionBean {

    @Inject
    etanah.Configuration conf;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    private LelonganDAO lelonganDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    @Inject
    private PembidaDAO pembidaDAO;
    @Inject
    private WakilPihakDAO wakilPihakDAO;
    @Inject
    private KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    private KodStsPembidaDAO kodStsPembidaDAO;
    @Inject
    LelongService lelongService;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    EnkuiriService enkuiriService;
    @Inject
    PihakService pihakService;
    @Inject
    PembetulanService pService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    SytJuruLelongDAO sytJuruLelongDAO;
    @Inject
    JuruLelongDAO jurulelongDAO;

    private static final Logger LOG = Logger.getLogger(UtilitiSenaraiPermohonanLelonganPembidaActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    private static boolean IS_DEBUG = LOG.isDebugEnabled();
    FileBean fileToBeUploaded;
    private String status;
    private String participant;
    private String stage;
    private long idDokumen;
    private String idPermohonan;
    private String idHakmilik;
//    private String idLelong;
    private String idPembida;
    private String idPihak;
    private String kodJenis;
    private String noRujukan;
    private String kodsts;
    private String tarikhMula;
    private String tarikhTamat;
    public String noPengenalan;
    private String idMohonSebelum;
    private boolean showIdMohonSebelum = false;
    private String noic;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<DokumenKewangan> checkBayaranPerintah;
    private List<Permohonan> senaraiPermohonan;
    private List<Lelongan> listLel;
    private List<Lelongan> listLel1;
    private List<Lelongan> listLel2;
    private List<Permohonan> checkIdMohonSebelum;
    private List<PermohonanCarian> senaraiPermohonanCarian;
    private List<Transaksi> listT = new ArrayList<Transaksi>();
    private List<Pembida> list = new ArrayList<Pembida>();
    private List<Pembida> list2 = new ArrayList<Pembida>();
    private List<Pembida> listPembida;
    private List listBida = new ArrayList();
    private Permohonan permohonan;
    private WakilPihak wakilPihak;
    private Lelongan lelong;
    private Pembida pembida;
    private Pihak pihak;
    private Enkuiri enkuiri;
    private Enkuiri enkuiriBaru;
    private Enkuiri enkuiri1;
    private FasaPermohonan fasaPermohonan;
    private Pengguna pengguna;
    private boolean adaPembida = false;
    private boolean view = false;
    private boolean showForm;
    private boolean melaka;
    private boolean wakilPembida;
    private String caraLelong;
    private String idWakil;
    private List<Lelongan> listLelongHakmilik;
    private String Stageid = "semakPembida";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
    boolean flagPage = false;
    private static String kodNegeri;
    private String iframeURL;

//    private static final Logger LOG = Logger.getLogger(KeputusanLelongActionBean.class);
//    @Inject
//    PermohonanDAO permohonanDAO;
//    @Inject
//    PembidaDAO pembidaDAO;
//    @Inject
//    LelongService lelongService;
//    @Inject
//    private etanah.Configuration conf;
    @Inject
    EnkuiriService enService;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    CalenderManager manager;
    @Inject
    KodNotisDAO kodNotisDAO;
//    private String idPermohonan;
//    private Pengguna pengguna;
//    private Permohonan permohonan;
//    private Pembida pembida;
    private List<FasaPermohonan> listFasa;
//    private FasaPermohonan fasaPermohonan;
    private List<Lelongan> senaraiLelongan;
    private List<Lelongan> senaraiLelongan2;
//    private List<Lelongan> listLel = new ArrayList<Lelongan>();
    private List<Lelongan> senaraiLelongan3;
    private List<Lelongan> listLelongan;
    private List<Enkuiri> senaraiEnkuiri;
    private List<Enkuiri> senaraiEnkuiri3;
    private List<Pembida> senaraiPembida;
//    private Enkuiri enkuiri;
//    private Lelongan lelong;
//    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private boolean negori;
    private BigDecimal amaunTunggakan;
    private BigDecimal amaunTunggakan2;
    private String jam;
    private String minit;
    private String ampm;
    private String tarikhLelong;
    private String tarikhLelongTerdahulu;
    private String tarikhAkhirBayaran;
    private String disabled;
    private String disabled3;
    private String idHak;
    private boolean lel3 = false;
    private boolean error;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;
    private String tarikhEnkuiri;
    private long idLelong;
    private int bilLelong;
    private String selectedTab = "0";
    private boolean btn = false;
    private boolean flag = false;
    private boolean checking = false;
    private boolean checkingBaki = false;
//    private boolean btn = false;
    private boolean saveFlg = false;
    private boolean addFlg = false;
    private boolean button = false;
    private boolean mlk = true;
    private boolean show = true;
    private List<Lelongan> lelonganList = new ArrayList<Lelongan>();
    private boolean display = false;
    private boolean jual = true;
    private String perihal;
    private boolean stageJurulelongN9 = true;
    private List<Lelongan> lelonganListJLB;
    private List<Lelongan> lelonganList2;
    private List<Lelongan> lelonganList3;
    private Lelongan lelongan;
    private List<JuruLelong> listJurulelong;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;

    private String reportName;
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
    private List<Notis> listNotis;
    private Dokumen dokumen;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("default handler");

        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiSemakanPembida.jsp");

    }

    public Resolution checkPermohonan() {
        LOG.info("rehydrate - start");
        LOG.info("idPermohonan : " + idPermohonan);
        getContext().getRequest().setAttribute("semak16H", Boolean.FALSE);
        if (idPermohonan == null) {
            idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        }

//        ParamEncoder encoder = new ParamEncoder("line");
//        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (!permohonan.equals(null)) {
//            flagPage = true;
            List<FasaPermohonan> mohonFasaList = fasaPermohonanService.findByIdMohonAndStageIdDESC(idPermohonan, Stageid);

            if (mohonFasaList.size() > 0) {
                FasaPermohonan fasaSemakPembida = mohonFasaList.get(0);
                if (fasaSemakPembida.getIdAliran().equals(Stageid)) {
                    flagPage = true;
                } else {
                    addSimpleError("Id Permohonan " + idPermohonan + " tiada pada Stage Semak Rekod Bidaan");
                }
            }
        }
        selectedTab = getContext().getRequest().getParameter("selectedTab");
        LOG.info("Rehydrate - finish");
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiSemakanPembida.jsp");
    }

    public Resolution getSenaraiJurulelongBerlesan() {
        //list utk pilih jurulelong
        rehydrate3();

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            mlk = true;
            listJurulelong = new ArrayList<JuruLelong>();
//                listJurulelong = jurulelongDAO.findAll();
            listJurulelong = lelongService.findJuruLelong();
            LOG.info("listJurulelong.size() : " + listJurulelong.size());
            return new JSP("lelong/utiliti_senarai_pelelong_berlesen.jsp").addParameter("popup", "true");
        }
        return new JSP("lelong/utiliti_senarai_pelelong_berlesen.jsp").addParameter("popup", "true");

    }

    public Resolution simpanPemohonMelaka() {
        //lantik jurulelong
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        LOG.info("------simpan-------");
        String idjlb = getContext().getRequest().getParameter("jurulel");
        LOG.info("----------idjlb :" + idjlb);
        if (idjlb == null) {
            addSimpleError("Sila Pilih Jurulelong");
            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
        }
        LOG.info("idjlb : " + idjlb);
        JuruLelong jurulelong = jurulelongDAO.findById(Long.parseLong(idjlb));
        LOG.info("JuruLelong : " + jurulelong.getIdJlb());
        lelonganList = lelongService.listLelonganAK(idPermohonan);
        if (idjlb != null) {
            for (Lelongan ll : lelonganList) {
                ll.setJurulelong(jurulelong);
                lelongService.saveOrUpdate(ll);
                setFlag(true);
            }
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            mlk = false;
        }
        rehydrate3();
        getContext().getRequest().setAttribute("jurulelong", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan..");
        rehydrate();
        return new JSP("lelong/utiliti_senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public void rehydrate3() {
        LOG.info("rehydrate start");
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        lelonganListJLB = lelongService.listLelonganTB(idPermohonan);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getPermohonanSebelum() == null) {
                enkuiri = lelongService.findEnkuiri(idPermohonan);
            } else {
                List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (list2.isEmpty()) {
                    enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list3 = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (!list3.isEmpty()) {
                            enkuiri = list3.get(0);
                        }
                    }
                } else {
                    enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list3 = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                        if (!list3.isEmpty()) {
                            enkuiri = list3.get(0);
                        }
                    }
                }
            }

            if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
                lelonganList = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
            } else {
                lelonganList = lelongService.listLelonganAK(idPermohonan);
            }

            if ("05".equals(conf.getProperty("kodNegeri"))) {
                mlk = false;
                lelonganList2 = new ArrayList<Lelongan>();
                if (!lelonganList.isEmpty()) {
                    lelonganList2.add(lelonganList.get(0));
                    lelongan = lelonganList2.get(0);
                    if (lelongan.getSytJuruLelong() != null) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                    LOG.info("lelonganList.size() : " + lelonganList2.size());
                }
            } else if ("04".equals(conf.getProperty("kodNegeri"))) {
                mlk = true;
                lelonganList3 = new ArrayList<Lelongan>();
                if (!lelonganList.isEmpty()) {
                    lelonganList3.add(lelonganList.get(0));
                    lelongan = lelonganList3.get(0);
                    if (lelongan.getJurulelong() != null) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                    LOG.info("lelonganList3.size() : " + lelonganList3.size());
                }
            }

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (enkuiri.getCaraLelong().equals("B")) {
                    if (StringUtils.isNotEmpty(enkuiri.getPerihalTanah())) {
                        jual = true;
                        perihal = enkuiri.getPerihalTanah();
                    } else {
                        jual = false;
                    }
                    getContext().getRequest().setAttribute("B", Boolean.TRUE);
                    getContext().getRequest().setAttribute("A", Boolean.FALSE);

                }
                if (enkuiri.getCaraLelong().equals("A")) {
                    getContext().getRequest().setAttribute("A", Boolean.TRUE);
                    getContext().getRequest().setAttribute("B", Boolean.FALSE);
                    for (Lelongan ll : lelonganList) {
                        if (StringUtils.isNotEmpty(ll.getPerihalTanah())) {
                            jual = true;
                        } else {
                            jual = false;
                        }
                    }
                }
            }
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            mlk = false;
            for (Lelongan ll : lelonganList) {
                if (ll.getSytJuruLelong() == null) {
                    getContext().getRequest().setAttribute("jurulelong", Boolean.FALSE);
                    break;
                } else {
                    getContext().getRequest().setAttribute("jurulelong", Boolean.TRUE);
                    break;
                }
            }
        }
    }

    public Resolution showFormMaklumatLelongan() {
        LOG.info("---showForm---");
        if (permohonan.getPermohonanSebelum() == null) {
            enkuiri = lelongService.findEnkuiri(idPermohonan);
        } else {
            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (list2.isEmpty()) {
                enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (enkuiri == null) {
                    List<Enkuiri> list = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (!list.isEmpty()) {
                        enkuiri = list.get(0);
                    }
                }
            } else {
                enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                if (enkuiri == null) {
                    List<Enkuiri> list = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (!list.isEmpty()) {
                        enkuiri = list.get(0);
                    }
                }
            }
        }

        List<Lelongan> listLelong = lelongService.getLeloganALLDESC(idPermohonan);
        LOG.info("listLelong : " + listLelong.size());
        Lelongan lelo = listLelong.get(0);
        int bil = lelo.getBil();
        LOG.info("bil : " + bil);
        int bilLel = 0;
        //edit by nur.aqilah
        //if (bil <= 3)
        //edit by farizal (3july15)
        if (("TB".equals(lelo.getKodStatusLelongan().getKod()))) {
            if (fasaPermohonan.getKeputusan() == null) {
                error = false;
                addSimpleError("Sila masukkan keputusan terlebih dahulu di tab keputusan");
            } else {
                LOG.info("fasaPermohonan : " + fasaPermohonan.getKeputusan().getKod());
                if (fasaPermohonan.getKeputusan().getKod().equals("RM")) {
                    error = false;
                    addSimpleMessage("Keputusan ialah Rujuk Mahkamah,Sila Klik Butang Selesai");
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("LS") || fasaPermohonan.getKeputusan().getKod().equals("AA")) {
                    error = true;
                    InfoAudit ia = fasaPermohonan.getInfoAudit();
                    String a = sdf1.format(ia.getTarikhMasuk());
                    InfoAudit ia2 = null;
                    String b = null;
                    KodStatusLelongan kod = null;
                    InfoAudit ial = new InfoAudit();
                    ial.setDiKemaskiniOleh(pengguna);
                    ial.setTarikhKemaskini(new java.util.Date());
                    ial.setDimasukOleh(pengguna);
                    ial.setTarikhMasuk(new java.util.Date());
                    senaraiLelongan = lelongService.getLeloganASC(idPermohonan);

                    // MELAKA
//                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                    LOG.info("---showForm MLK---");
                    for (Lelongan ll : senaraiLelongan) {
                        if (ll.getBil() == bil) {
                            LOG.info("---Tiada Pembida---");
                            LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
                            idLelong = ll.getIdLelong();
                            Pembida pem = lelongService.getPembidaByIdLelong(idLelong);

                            if (ll.getTarikhLelong() == null) {
                                break;
                            }

//                                if (pem != null  && !ll.getKodStatusLelongan().getKod().equals("PL")) {
                            if (pem == null && !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                ia2 = ll.getInfoAudit();
                                b = sdf1.format(ia2.getTarikhMasuk());
                                LOG.info("a : " + a);
                                LOG.info("b : " + b);
                                if (a == null ? b != null : !a.equals(b)) {

                                    lelongService.saveOrUpdate(ll);
                                    kod = kodStatusLelonganDAO.findById("AK");

                                    if (enkuiri.getCaraLelong().equals("B")) {

                                        enkuiri.setDeposit(null);
                                        enkuiri.setHargaRizab(null);
                                        enkuiri.setHargaBida(null);
                                        enkuiri.setBaki(null);
                                        lelongService.saveOrUpdate(enkuiri);
                                    }

                                    List<Lelongan> senaraiLelong = lelongService.findListLelongan(idPermohonan);
                                    Lelongan lelong = senaraiLelong.get(0);
                                    if (lelong.getKodStatusLelongan().getKod().equals("TB")) {
                                        Lelongan lel = new Lelongan();
                                        lel.setEnkuiri(enkuiri);
                                        lel.setBil(bil + 1);
                                        lel.setPermohonan(permohonan);
                                        lel.setInfoAudit(ial);
                                        lel.setKodStatusLelongan(kod);
                                        if (ll.getPerihalTanah() != null) {
                                            lel.setPerihalTanah(ll.getPerihalTanah());
                                        }
                                        if (ll.getPerihalTanahBahasaInggeris() != null) {
                                            lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                        }
                                        lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                        lel.setJurulelong(ll.getJurulelong());
                                        lelongService.saveOrUpdate(lel);
                                        bilLel = lel.getBil();
                                        tarikhAkhirBayaran = null;
                                        tarikhLelong = null;
                                        jam = null;
                                        minit = null;
                                        ampm = null;
                                        kod = kodStatusLelonganDAO.findById("TB");
                                        ll.setKodStatusLelongan(kod);
                                        ll.setInfoAudit(ial);
                                    }
                                } else {
                                    Date c = ia.getTarikhMasuk();
                                    Date d = ia2.getTarikhMasuk();
                                    if (d.before(c)) {
                                        kod = kodStatusLelonganDAO.findById("TB");
                                        ll.setKodStatusLelongan(kod);
                                        ll.setInfoAudit(ial);
                                        lelongService.saveOrUpdate(ll);
                                        if (enkuiri.getCaraLelong().equals("A")) {

                                            enkuiri.setDeposit(null);
                                            enkuiri.setHargaRizab(null);
                                            enkuiri.setHargaBida(null);
                                            enkuiri.setBaki(null);
                                            lelongService.saveOrUpdate(enkuiri);
                                        }
                                        kod = kodStatusLelonganDAO.findById("AK");
                                        ial.setDimasukOleh(pengguna);
                                        ial.setTarikhMasuk(new java.util.Date());
                                        Lelongan lel = new Lelongan();
                                        lel.setEnkuiri(enkuiri);
                                        lel.setBil(bil + 1);
                                        lel.setInfoAudit(ial);
                                        lel.setPermohonan(permohonan);
                                        lel.setKodStatusLelongan(kod);
                                        lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                        if (ll.getPerihalTanah() != null) {
                                            lel.setPerihalTanah(ll.getPerihalTanah());
                                        }
                                        if (ll.getPerihalTanahBahasaInggeris() != null) {
                                            lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                        }
                                        lelongService.saveOrUpdate(lel);
                                        bilLel = lel.getBil();
                                        tarikhAkhirBayaran = null;
                                        tarikhLelong = null;
                                        jam = null;
                                        minit = null;
                                        ampm = null;
                                    }

                                    if (d.after(c)) {
                                        kod = kodStatusLelonganDAO.findById("TB");
                                        ll.setKodStatusLelongan(kod);
                                        ll.setInfoAudit(ial);
                                        lelongService.saveOrUpdate(ll);
                                        if (enkuiri.getCaraLelong().equals("A")) {

                                        }
                                        //bersama
                                        if (enkuiri.getCaraLelong().equals("B")) {

                                            enkuiri.setDeposit(null);
                                            enkuiri.setHargaRizab(null);
                                            enkuiri.setHargaBida(null);
                                            enkuiri.setBaki(null);
                                            lelongService.saveOrUpdate(enkuiri);
                                        }
                                        kod = kodStatusLelonganDAO.findById("AK");
                                        ial.setDimasukOleh(pengguna);
                                        ial.setTarikhMasuk(new java.util.Date());
                                        Lelongan lel = new Lelongan();
                                        lel.setEnkuiri(enkuiri);
                                        lel.setBil(bil + 1);
                                        lel.setInfoAudit(ial);
                                        lel.setPermohonan(permohonan);
                                        lel.setKodStatusLelongan(kod);
                                        lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                        if (ll.getPerihalTanah() != null) {
                                            lel.setPerihalTanah(ll.getPerihalTanah());
                                        }
                                        if (ll.getPerihalTanahBahasaInggeris() != null) {
                                            lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                        }
                                        lelongService.saveOrUpdate(lel);
                                        bilLel = lel.getBil();
                                        tarikhAkhirBayaran = null;
                                        tarikhLelong = null;
                                        jam = null;
                                        minit = null;
                                        ampm = null;
                                    }
                                }
                            } //                                if (pem.getPihak() != null) {
                            else {
                                LOG.info("---ada pembida---");
                                LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
//                                    if (pem == null || ll.getKodStatusLelongan().getKod().equals("AT") || !ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {

                                if (ll.getTarikhLelong() == null) {
                                    break;
                                }

                                if (pem != null || !ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                    ia2 = ll.getInfoAudit();
                                    b = sdf1.format(ia2.getTarikhMasuk());
                                    LOG.info("a : " + a);
                                    LOG.info("b : " + b);
                                    if (a == null ? b != null : !a.equals(b)) {
                                        kod = kodStatusLelonganDAO.findById("AT");
                                        ll.setKodStatusLelongan(kod);
                                        ll.setInfoAudit(ial);
                                        lelongService.saveOrUpdate(ll);
                                        kod = kodStatusLelonganDAO.findById("AK");
                                        if (enkuiri.getCaraLelong().equals("A")) {

                                        }
                                        //bersama
                                        if (enkuiri.getCaraLelong().equals("B")) {

                                            enkuiri.setDeposit(null);
                                            enkuiri.setHargaRizab(null);
                                            enkuiri.setHargaBida(null);
                                            enkuiri.setBaki(null);
                                            lelongService.saveOrUpdate(enkuiri);
                                        }
                                        Lelongan lel = new Lelongan();
                                        lel.setEnkuiri(enkuiri);
                                        lel.setBil(bil + 1);
                                        lel.setPermohonan(permohonan);
                                        lel.setInfoAudit(ial);
                                        lel.setKodStatusLelongan(kod);
                                        lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                        if (ll.getPerihalTanah() != null) {
                                            lel.setPerihalTanah(ll.getPerihalTanah());
                                        }
                                        if (ll.getPerihalTanahBahasaInggeris() != null) {
                                            lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                        }
                                        lelongService.saveOrUpdate(lel);
                                        bilLel = lel.getBil();
                                        tarikhAkhirBayaran = null;
                                        tarikhLelong = null;
                                        jam = null;
                                        minit = null;
                                        ampm = null;
                                    } else {
                                        Date c = ia.getTarikhMasuk();
                                        Date d = ia2.getTarikhMasuk();
                                        if (d.before(c)) {
                                            kod = kodStatusLelonganDAO.findById("AT");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                            lelongService.saveOrUpdate(ll);
                                            if (enkuiri.getCaraLelong().equals("A")) {

                                            }
                                            //bersama
                                            if (enkuiri.getCaraLelong().equals("B")) {

                                                enkuiri.setDeposit(null);
                                                enkuiri.setHargaRizab(null);
                                                enkuiri.setHargaBida(null);
                                                enkuiri.setBaki(null);
                                                lelongService.saveOrUpdate(enkuiri);
                                            }
                                            kod = kodStatusLelonganDAO.findById("AK");
                                            ial.setDimasukOleh(pengguna);
                                            ial.setTarikhMasuk(new java.util.Date());
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setInfoAudit(ial);
                                            lel.setPermohonan(permohonan);
                                            lel.setKodStatusLelongan(kod);
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                        }
                                    }
                                }
                            }
                        }
                    }
//                    }
                }
                LOG.info("bilLel : " + bilLel);
                if (bilLel == 4) {
                    lel3 = true;
                    addSimpleMessage("Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
                }
            }
        }
        if ((bil <= 3) && ("AK".equals(lelo.getKodStatusLelongan().getKod()))) {
            if (fasaPermohonan.getKeputusan() == null) {
                error = false;
                addSimpleError("Sila masukkan keputusan terlebih dahulu di tab keputusan");
            } else {
                LOG.info("fasaPermohonan : " + fasaPermohonan.getKeputusan().getKod());
                if (fasaPermohonan.getKeputusan().getKod().equals("RM")) {
                    error = false;
                    addSimpleMessage("Keputusan ialah Rujuk Mahkamah,Sila Klik Butang Selesai");
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("LS") || fasaPermohonan.getKeputusan().getKod().equals("AA")) {
                    error = true;
                    InfoAudit ia = fasaPermohonan.getInfoAudit();
                    String a = sdf1.format(ia.getTarikhMasuk());
                    InfoAudit ia2 = null;
                    String b = null;
                    KodStatusLelongan kod = null;
                    InfoAudit ial = new InfoAudit();
                    ial.setDiKemaskiniOleh(pengguna);
                    ial.setTarikhKemaskini(new java.util.Date());
                    ial.setDimasukOleh(pengguna);
                    ial.setTarikhMasuk(new java.util.Date());
                    senaraiLelongan = lelongService.getLeloganASC(idPermohonan);

                    // N9
                    // MELAKA
//                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                    LOG.info("---showForm MLK---");
                    for (Lelongan ll : senaraiLelongan) {
                        if (ll.getBil() == bil) {
                            LOG.info("---Tiada Pembida---");
                            LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
                            idLelong = ll.getIdLelong();
                            Pembida pem = lelongService.getPembidaByIdLelong(idLelong);

                            if (ll.getTarikhLelong() == null) {
                                break;
                            }

                            if (pem == null && !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                ia2 = ll.getInfoAudit();
                                b = sdf1.format(ia2.getTarikhMasuk());
                                LOG.info("a : " + a);
                                LOG.info("b : " + b);
                                if (a == null ? b != null : !a.equals(b)) {

                                    lelongService.saveOrUpdate(ll);
                                    kod = kodStatusLelonganDAO.findById("AK");

                                    List<Lelongan> senaraiLelong = lelongService.findListLelongan(idPermohonan);
                                    Lelongan lelong = senaraiLelong.get(0);
                                    if (lelong.getKodStatusLelongan().getKod().equals("TB")) {
                                        Lelongan lel = new Lelongan();
                                        lel.setEnkuiri(enkuiri);
                                        lel.setBil(bil + 1);
                                        lel.setPermohonan(permohonan);
                                        lel.setInfoAudit(ial);
                                        lel.setKodStatusLelongan(kod);
                                        if (ll.getPerihalTanah() != null) {
                                            lel.setPerihalTanah(ll.getPerihalTanah());
                                        }
                                        if (ll.getPerihalTanahBahasaInggeris() != null) {
                                            lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                        }
                                        lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                        lelongService.saveOrUpdate(lel);
                                        bilLel = lel.getBil();
                                        tarikhAkhirBayaran = null;
                                        tarikhLelong = null;
                                        jam = null;
                                        minit = null;
                                        ampm = null;
                                        kod = kodStatusLelonganDAO.findById("TB");
                                        ll.setKodStatusLelongan(kod);
                                        ll.setInfoAudit(ial);
                                    }
                                } else {
                                    Date c = ia.getTarikhMasuk();
                                    Date d = ia2.getTarikhMasuk();
                                    if (d.before(c)) {
                                        kod = kodStatusLelonganDAO.findById("TB");
                                        ll.setKodStatusLelongan(kod);
                                        ll.setInfoAudit(ial);
                                        lelongService.saveOrUpdate(ll);
                                        if (enkuiri.getCaraLelong().equals("A")) {

                                        }
                                        //bersama
                                        if (enkuiri.getCaraLelong().equals("B")) {

                                            enkuiri.setDeposit(null);
                                            enkuiri.setHargaRizab(null);
                                            enkuiri.setHargaBida(null);
                                            enkuiri.setBaki(null);
                                            lelongService.saveOrUpdate(enkuiri);
                                        }
                                        kod = kodStatusLelonganDAO.findById("AK");
                                        ial.setDimasukOleh(pengguna);
                                        ial.setTarikhMasuk(new java.util.Date());
                                        Lelongan lel = new Lelongan();
                                        lel.setEnkuiri(enkuiri);
                                        lel.setBil(bil + 1);
                                        lel.setInfoAudit(ial);
                                        lel.setPermohonan(permohonan);
                                        lel.setKodStatusLelongan(kod);
                                        lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                        if (ll.getPerihalTanah() != null) {
                                            lel.setPerihalTanah(ll.getPerihalTanah());
                                        }
                                        if (ll.getPerihalTanahBahasaInggeris() != null) {
                                            lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                        }
                                        lelongService.saveOrUpdate(lel);
                                        bilLel = lel.getBil();
                                        tarikhAkhirBayaran = null;
                                        tarikhLelong = null;
                                        jam = null;
                                        minit = null;
                                        ampm = null;
                                    }
                                }
                            } //                                if (pem.getPihak() != null) {
                            else {
                                LOG.info("---ada pembida---");
                                LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
//                                    if (pem == null || ll.getKodStatusLelongan().getKod().equals("AT") || !ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {

                                if (ll.getTarikhLelong() == null) {
                                    break;
                                }

                                if (pem != null || !ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                    ia2 = ll.getInfoAudit();
                                    b = sdf1.format(ia2.getTarikhMasuk());
                                    LOG.info("a : " + a);
                                    LOG.info("b : " + b);
                                    if (a == null ? b != null : !a.equals(b)) {
                                        kod = kodStatusLelonganDAO.findById("AT");
                                        ll.setKodStatusLelongan(kod);
                                        ll.setInfoAudit(ial);
                                        lelongService.saveOrUpdate(ll);
                                        kod = kodStatusLelonganDAO.findById("AK");
                                        if (enkuiri.getCaraLelong().equals("A")) {

                                        }
                                        //bersama
                                        if (enkuiri.getCaraLelong().equals("B")) {

                                            enkuiri.setDeposit(null);
                                            enkuiri.setHargaRizab(null);
                                            enkuiri.setHargaBida(null);
                                            enkuiri.setBaki(null);
                                            lelongService.saveOrUpdate(enkuiri);
                                        }
                                        Lelongan lel = new Lelongan();
                                        lel.setEnkuiri(enkuiri);
                                        lel.setBil(bil + 1);
                                        lel.setPermohonan(permohonan);
                                        lel.setInfoAudit(ial);
                                        lel.setKodStatusLelongan(kod);
                                        lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                        if (ll.getPerihalTanah() != null) {
                                            lel.setPerihalTanah(ll.getPerihalTanah());
                                        }
                                        if (ll.getPerihalTanahBahasaInggeris() != null) {
                                            lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                        }
                                        lelongService.saveOrUpdate(lel);
                                        bilLel = lel.getBil();
                                        tarikhAkhirBayaran = null;
                                        tarikhLelong = null;
                                        jam = null;
                                        minit = null;
                                        ampm = null;
                                    } else {
                                        Date c = ia.getTarikhMasuk();
                                        Date d = ia2.getTarikhMasuk();
                                        if (d.before(c)) {
                                            kod = kodStatusLelonganDAO.findById("AT");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                            lelongService.saveOrUpdate(ll);
                                            if (enkuiri.getCaraLelong().equals("A")) {

                                            }
                                            //bersama
                                            if (enkuiri.getCaraLelong().equals("B")) {

                                                enkuiri.setDeposit(null);
                                                enkuiri.setHargaRizab(null);
                                                enkuiri.setHargaBida(null);
                                                enkuiri.setBaki(null);
                                                lelongService.saveOrUpdate(enkuiri);
                                            }
                                            kod = kodStatusLelonganDAO.findById("AK");
                                            ial.setDimasukOleh(pengguna);
                                            ial.setTarikhMasuk(new java.util.Date());
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setInfoAudit(ial);
                                            lel.setPermohonan(permohonan);
                                            lel.setKodStatusLelongan(kod);
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                        }
                                    }
                                }
                            }
                        }
                    }
//                    }
                }
                LOG.info("bilLel : " + bilLel);
                if (bilLel == 4) {
                    lel3 = true;
                    addSimpleMessage("Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
                }
            }
        } else if ((bil == 3) && ("AK".equals(lelo.getKodStatusLelongan().getKod()))) {
            LOG.info("lelongan telah di lakukan sebanyak 3 kali");
            lel3 = true;
            addSimpleMessage("Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
        }
        rehydrate();
        return new JSP("lelong/tab_utiliti_lelong.jsp").addParameter("tab", "true");
    }

    public Resolution showFormRM() {
        listLel = lelongService.listLelonganRM(idPermohonan);
        senaraiLelongan = lelongService.listLelonganRM(idPermohonan);
        senaraiLelongan3 = lelongService.getLeloganNotInRM(enkuiri.getIdEnkuiri());

        for (Lelongan ll : senaraiLelongan) {
            if (ll.getKodStatusLelongan().getKod().equals("RM")) {
                lelong = ll;
                break;
            }
        }

        try {
            if (!senaraiLelongan.isEmpty()) {
                tarikhLelong = sdf.format(lelong.getTarikhLelong());
                if (lelong.getBil() <= 1) {
                    disabled3 = "disabeld3";
                }
                tarikhAkhirBayaran = sdf1.format(lelong.getTarikhAkhirBayaran());
            }
            if (lelong.getTarikhLelong() != null) {
                tarikhLelong = sdf.format(lelong.getTarikhLelong()).substring(0, 10);
                jam = sdf.format(lelong.getTarikhLelong()).substring(11, 13);
                minit = sdf.format(lelong.getTarikhLelong()).substring(14, 16);
                ampm = sdf.format(lelong.getTarikhLelong()).substring(17, 19);
            }

        } catch (Exception ex) {
            LOG.error(ex);
        }
        //berasingan
        if (enkuiri.getCaraLelong().equals("A")) {
            getContext().getRequest().setAttribute("same", Boolean.FALSE);
        }
        //bersama
        listLelongan = new ArrayList<Lelongan>();
        if (enkuiri.getCaraLelong().equals("B")) {
            getContext().getRequest().setAttribute("same", Boolean.TRUE);
            listLelongan.add(lelong);
            StringBuilder sb = new StringBuilder();
            if (permohonan != null) {
                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                    Hakmilik h = hp.getHakmilik();
                    if (sb.length() > 0) {
                        sb.append("<br>");
                    }
                    sb.append(h.getIdHakmilik());
                }
            }
            idHak = sb.toString();
        }
        return new JSP("lelong/kpsn_lelong_semak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm16I() {
        listLel = lelongService.listLelonganAP(idPermohonan);
        senaraiLelongan = lelongService.listLelonganAP(idPermohonan);
        senaraiLelongan3 = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());

        for (Lelongan ll : senaraiLelongan) {
            if (ll.getKodStatusLelongan().getKod().equals("AP")) {
                lelong = ll;
                break;
            }
        }

        try {
            if (!senaraiLelongan.isEmpty()) {
                tarikhLelong = sdf.format(lelong.getTarikhLelong());
                if (lelong.getBil() <= 1) {
                    disabled3 = "disabeld3";
                }
                tarikhAkhirBayaran = sdf1.format(lelong.getTarikhAkhirBayaran());
            }
            if (lelong.getTarikhLelong() != null) {
                tarikhLelong = sdf.format(lelong.getTarikhLelong()).substring(0, 10);
                jam = sdf.format(lelong.getTarikhLelong()).substring(11, 13);
                minit = sdf.format(lelong.getTarikhLelong()).substring(14, 16);
                ampm = sdf.format(lelong.getTarikhLelong()).substring(17, 19);
            }

        } catch (Exception ex) {
            LOG.error(ex);
        }
        //berasingan
        if (enkuiri.getCaraLelong().equals("A")) {
            getContext().getRequest().setAttribute("same", Boolean.FALSE);
        }
        //bersama
        listLelongan = new ArrayList<Lelongan>();
        if (enkuiri.getCaraLelong().equals("B")) {
            getContext().getRequest().setAttribute("same", Boolean.TRUE);
            listLelongan.add(lelong);
            StringBuilder sb = new StringBuilder();
            if (permohonan != null) {
                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                    Hakmilik h = hp.getHakmilik();
                    if (sb.length() > 0) {
                        sb.append("<br>");
                    }
                    sb.append(h.getIdHakmilik());
                }
            }
            idHak = sb.toString();
        }
        return new JSP("lelong/kpsn_lelong_semak.jsp").addParameter("tab", "true");
    }

    public Resolution showFormB() {

//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("idPermohonan : " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraiLelongan = lelongService.listLelonganAKAP2(permohonan.getIdPermohonan()); //lelongan yg AK
        LOG.info("Senarai Aktif " + senaraiLelongan.size());
//        LOG.info("Bil Lelongan Aktif " + senaraiLelongan.get(0).getBil());
        senaraiLelongan2 = lelongService.findLelonganTerdahulu(permohonan.getIdPermohonan(), senaraiLelongan.get(0).getBil());
        LOG.info("Senarai Lelongan Terdahulu " + senaraiLelongan2.size());
        tarikhLelongTerdahulu = sdf.format(senaraiLelongan2.get(0).getTarikhLelong()).substring(0, 10);
        bilLelong = senaraiLelongan.get(0).getBil();

//        listCalender = manager.getALLEnkuri(permohonan.getCawangan().getKod());
        listCalender2 = manager.getALLLelonganBaru(permohonan.getCawangan().getKod());

        return new JSP("lelong/calender_lelong2.jsp").addParameter("popup", "true");
    }

    public Resolution genReport() {
//        LOGGER.info("genReport :: start");
//        LOGGER.info("generate report start.");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        String gen2 = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            gen = "LLGNotisGantianLelong_MLK.rdf";
            gen2 = "LLGNotisGantianLelong_MLK.rdf";
//            LOGGER.info("report = " + gen);
//            LOGGER.info("report = " + gen2);
        }
        try {
            if (gen.equals("LLGNotisGantianLelong_MLK.rdf")) {
                String code = "SNG";
//                LOGGER.info("genReportFromXML");
                lelongService.reportGen(permohonan, gen, code, pengguna);
            }
            if (gen2.equals("LLGNotisGantianLelong_MLK.rdf")) {
                String code1 = "SNG";
//                LOGGER.info("genReportFromXML");
                lelongService.reportGen(permohonan, gen2, code1, pengguna);
            }
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
//        LOGGER.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public void simpanNotis() {

        Dokumen dokumen2 = null;
        KodNotis kodNotis = kodNotisDAO.findById("SNG");
        KodDokumen kd = kodDokumenDAO.findById("SNG");
        List<KandunganFolder> listFD = lelongService.getListDokumenSNG(permohonan.getFolderDokumen().getFolderId());
        if (!listFD.isEmpty()) {
            dokumen2 = listFD.get(0).getDokumen();
        } else {
            dokumen2 = lelongService.saveOrUpdateDokumen(permohonan.getFolderDokumen(), kd, idPermohonan, pengguna);
        }

//        LOGGER.info("kodNotis : " + kodNotis.getKod());
        InfoAudit info = pengguna.getInfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());

        List<String> listIDHakmilik = new ArrayList<String>();
        for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
            listIDHakmilik.add(hp.getHakmilik().getIdHakmilik());
        }
//        LOGGER.info("listIDHakmilik : " + listIDHakmilik.size());
        List<HakmilikPihakBerkepentingan> listHP = lelongService.getHakmilikPihakALL(idPermohonan, listIDHakmilik);
        Map<Long, HakmilikPihakBerkepentingan> pihakMap2 = new HashMap<Long, HakmilikPihakBerkepentingan>();
        for (HakmilikPihakBerkepentingan hp : listHP) {
            Long id = hp.getPihak().getIdPihak();
            if (pihakMap2.containsKey(id)) {
                continue;
            } else {
                pihakMap2.put(id, hp);
            }
        }
        senaraiHakmilikPihak = new ArrayList(pihakMap2.values());

        List<PermohonanAtasPerserahan> listPAP = lelongService.getPermohonanAtasPerserahan(idPermohonan);
        PermohonanAtasPerserahan pAP = listPAP.get(0);
    }

    public void genReport2() {
        String gen = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            gen = "LLGBorang16HPenyerah_MLK.rdf";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            gen = "LLGBorang16HPenyerah_NS.rdf";
        }
        String code = "16H";

        try {

            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {

        }

        rehydrate();
    }

    public Resolution viewPdf() {
        if (idDokumen == 0) {
            return new ErrorResolution(500, "Dokumen tidak dinyatakan");
        }

        Dokumen d = dokumenDAO.findById(idDokumen);
        if (d == null || d.getKodDokumen() == null) {
            return new ErrorResolution(500, "Dokumen tidak dijumpai");
        }

        // classification checking
        Pengguna p = ((etanahActionBeanContext) getContext()).getUser();
        if (d.getKlasifikasi() != null
                && p.getTahapSekuriti().getKod() < d.getKlasifikasi().getKod()) {
            return new ErrorResolution(401, "Pengguna tidak boleh mencapai dokumen ini.");
        }

        String docPath = conf.getProperty("document.path");
        if (docPath == null) {
            return new ErrorResolution(500,
                    "Konfigurasi \"document.path\" tidak dijumpai");
        }
        // log the view
        DokumenCapaian dc = new DokumenCapaian();
        dc.setDokumen(d);
        dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("PD"));
        dc.setAlasan("Paparan Dokumen");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new java.util.Date());
        dc.setInfoAudit(ia);
        dokumenCapaianDAO.save(dc);
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();
//        if (d.getBaru() == 'Y') {
//            ia = d.getInfoAudit();
//            ia.setTarikhKemaskini(new Date());
//            ia.setDiKemaskiniOleh(pengguna);
//            d.setInfoAudit(ia);
//            d.setBaru('T');
//            dokumenDAO.update(d);
//        }
//        tx.commit();

        String path = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal();
        File f = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
        } catch (Exception e) {
            LOG.error(e);
            return new ErrorResolution(500,
                    "Fail " + path + " tidak dijumpai");
        }

        getContext().getResponse().setContentType("application/octet-stream");

        String filename = null;
        int sep = d.getNamaFizikal().lastIndexOf(File.separator);
        if (sep >= 0) {
            d.getNamaFizikal().substring(sep);
        } else {
            filename = d.getNamaFizikal();
        }
        return new StreamingResolution(d.getFormat(), fis).setFilename(filename);
//
//
//        return new JSP("lelong/view_16H.jsp").addParameter("tab", "true");
    }

    public void rehydrate() {

        if (idPermohonan == null) {
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            if (idPermohonan == null) {
                idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
            }
        }
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            listFasa = lelongService.getPermonanFasaSemakPembida(idPermohonan);
            if (!listFasa.isEmpty()) {
                fasaPermohonan = listFasa.get(0);
            }
            if (permohonan.getPermohonanSebelum() == null) {
                enkuiri = lelongService.findEnkuiri(idPermohonan);
                senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(idPermohonan);
            } else {
                List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (list2.isEmpty()) {
                    enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (!list.isEmpty()) {
                            enkuiri = list.get(0);
                        }
                    }
                    senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                } else {
                    enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                        if (!list.isEmpty()) {
                            enkuiri = list.get(0);
                        }
                    }
                    senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                }
            }
            if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
                if (fasaPermohonan.getKeputusan().getKod().equals("LS") || fasaPermohonan.getKeputusan().getKod().equals("AA")) {
                    error = true;

                    senaraiLelongan3 = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());
                    senaraiLelongan = lelongService.getLeloganASC(idPermohonan);

                    for (Lelongan ll : senaraiLelongan) {
                        if (ll.getKodStatusLelongan().getKod().equals("AK")) {
                            lelong = ll;
                            break;
                        }
                    }
                    listLel = lelongService.getLeloganASC(idPermohonan);
                    LOG.info("listLel : " + listLel.size());

                    //for negeri melake
                    negori = true;
                    if (enkuiri.getAmaunTunggakan() != null) {
                        amaunTunggakan = enkuiri.getAmaunTunggakan();
                    }
                    if (enkuiri.getAmaunGadaian() != null) {
                        amaunTunggakan2 = enkuiri.getAmaunGadaian();
                    }

                    if (enkuiri.getCaraLelong() == null) {
                        getContext().getRequest().setAttribute("sblmPilih", Boolean.TRUE);
                    } else {
                        getContext().getRequest().setAttribute("sblmPilih", Boolean.FALSE);
                        //berasingan

                        if (enkuiri.getCaraLelong().equals("A")) {
                            LOG.info("-----A-----");
                            getContext().getRequest().setAttribute("same", Boolean.FALSE);
                            List<Lelongan> listLel2 = lelongService.getLeloganASC(idPermohonan);
                            listLel = new ArrayList<Lelongan>();
                            //n9

                            //MLK
                            LOG.info("----o4---");
                            for (Lelongan ll : listLel2) {
                                idLelong = ll.getIdLelong();
//                                    if (pembida != null) {
                                senaraiPembida = enService.getListPembida(idLelong);
                                if (senaraiPembida.isEmpty() && ll.getKodStatusLelongan().getKod().equals("AK")) {
                                    listLel.add(ll);
                                }

                            }

                        }

                        //bersama
                        listLelongan = new ArrayList<Lelongan>();
                        if (enkuiri.getCaraLelong().equals("B")) {
                            LOG.info("-----B-----");
                            getContext().getRequest().setAttribute("same", Boolean.TRUE);
                            listLelongan.add(lelong);
                            StringBuilder sb = new StringBuilder();
                            if (permohonan != null) {
                                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                                    Hakmilik h = hp.getHakmilik();
                                    if (sb.length() > 0) {
                                        sb.append("<br>");
                                    }
                                    sb.append(h.getIdHakmilik());
                                }
                            }
                            idHak = sb.toString();
                        }
                    }

                    if (enkuiri != null && enkuiri.getTarikhEnkuiri() != null) {
                        tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);
                    }
                    try {
                        if (!senaraiLelongan.isEmpty()) {
                            tarikhLelong = sdf.format(lelong.getTarikhLelong());
                            tarikhAkhirBayaran = sdf1.format(lelong.getTarikhAkhirBayaran());
                        }
                        if (lelong != null && lelong.getTarikhLelong() != null) {
                            tarikhLelong = sdf.format(lelong.getTarikhLelong()).substring(0, 10);
                            String tarikhLelong = sdf.format(lelong.getTarikhLelong());
                            jam = sdf.format(lelong.getTarikhLelong()).substring(11, 13);
                            minit = sdf.format(lelong.getTarikhLelong()).substring(14, 16);
                            ampm = sdf.format(lelong.getTarikhLelong()).substring(20, 22);
                        }

                    } catch (Exception ex) {
                        LOG.error(ex);
                    }

                    if (permohonan.getPermohonanSebelum() == null) {
                        enkuiri = lelongService.findEnkuiri(idPermohonan);
                    } else {
                        List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (list2.isEmpty()) {
                            enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                            if (enkuiri == null) {
                                List<Enkuiri> list = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                                if (!list.isEmpty()) {
                                    enkuiri = list.get(0);
                                }
                            }
                        } else {
                            enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                            if (enkuiri == null) {
                                List<Enkuiri> list = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                                if (!list.isEmpty()) {
                                    enkuiri = list.get(0);
                                }
                            }
                        }
                    }

                    List<Lelongan> listLelong = lelongService.findListLelongan(idPermohonan);
                    LOG.info("listLelong : " + listLelong.size());
                    Lelongan lelo = listLelong.get(0);
                    int bil = lelo.getBil();
                    LOG.info("bil : " + bil);
                    int bilLel = 0;
                    //edit by nur.aqilah
                    //if (bil <= 3)
                    //edit by farizal (3july15)
                    if (("TB".equals(lelo.getKodStatusLelongan().getKod()))) {
                        if (fasaPermohonan.getKeputusan() == null) {
                            error = false;
                            addSimpleError("Sila masukkan keputusan terlebih dahulu di tab keputusan");
                        } else {
                            LOG.info("fasaPermohonan : " + fasaPermohonan.getKeputusan().getKod());
                            if (fasaPermohonan.getKeputusan().getKod().equals("RM")) {
                                error = false;
                                addSimpleMessage("Keputusan ialah Rujuk Mahkamah,Sila Klik Butang Selesai");
                            }
                            if (fasaPermohonan.getKeputusan().getKod().equals("LS") || fasaPermohonan.getKeputusan().getKod().equals("AA")) {
                                error = true;
                                InfoAudit ia = fasaPermohonan.getInfoAudit();
                                String a = sdf1.format(ia.getTarikhMasuk());
                                InfoAudit ia2 = null;
                                String b = null;
                                KodStatusLelongan kod = null;
                                InfoAudit ial = new InfoAudit();
                                ial.setDiKemaskiniOleh(pengguna);
                                ial.setTarikhKemaskini(new java.util.Date());
                                ial.setDimasukOleh(pengguna);
                                ial.setTarikhMasuk(new java.util.Date());
                                senaraiLelongan = lelongService.getLeloganASC(idPermohonan);

                                // MELAKA
//                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                                LOG.info("---showForm MLK---");
                                for (Lelongan ll : senaraiLelongan) {
                                    if (ll.getBil() == bil) {
                                        LOG.info("---Tiada Pembida---");
                                        LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
                                        idLelong = ll.getIdLelong();
                                        Pembida pem = lelongService.getPembidaByIdLelong(idLelong);

                                        if (ll.getTarikhLelong() == null) {
                                            break;
                                        }

//                                if (pem != null  && !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                        if (pem == null && !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                            ia2 = ll.getInfoAudit();
                                            b = sdf1.format(ia2.getTarikhMasuk());
                                            LOG.info("a : " + a);
                                            LOG.info("b : " + b);
                                            if (a == null ? b != null : !a.equals(b)) {

                                                lelongService.saveOrUpdate(ll);
                                                kod = kodStatusLelonganDAO.findById("AK");

                                                if (enkuiri.getCaraLelong().equals("B")) {

                                                    enkuiri.setDeposit(null);
                                                    enkuiri.setHargaRizab(null);
                                                    enkuiri.setHargaBida(null);
                                                    enkuiri.setBaki(null);
                                                    lelongService.saveOrUpdate(enkuiri);
                                                }

//                                                List<Lelongan> senaraiLelong = lelongService.findListLelongan(idPermohonan);
                                                 List<Lelongan> senaraiLelong = lelongService.findListLelongan(idPermohonan);
                                                Lelongan lelong = senaraiLelong.get(0);
                                                LOG.info("TRY HERE = " + lelong.getIdLelong());
                                                if (lelong.getKodStatusLelongan().getKod().equals("TB")) {
                                                    Lelongan lel = new Lelongan();
                                                    lel.setEnkuiri(enkuiri);
                                                    lel.setBil(bil + 1);
                                                    lel.setPermohonan(permohonan);
                                                    lel.setInfoAudit(ial);
                                                    lel.setKodStatusLelongan(kod);
                                                    if (ll.getPerihalTanah() != null) {
                                                        lel.setPerihalTanah(ll.getPerihalTanah());
                                                    }
                                                    if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                        lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                    }
                                                    lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                    lel.setJurulelong(ll.getJurulelong());
                                                    lelongService.saveOrUpdate(lel);
                                                    bilLel = lel.getBil();
                                                    tarikhAkhirBayaran = null;
                                                    tarikhLelong = null;
                                                    jam = null;
                                                    minit = null;
                                                    ampm = null;
                                                    kod = kodStatusLelonganDAO.findById("TB");
                                                    ll.setKodStatusLelongan(kod);
                                                    ll.setInfoAudit(ial);
                                                }
                                            } else {
                                                Date c = ia.getTarikhMasuk();
                                                Date d = ia2.getTarikhMasuk();
                                                if (d.before(c)) {
                                                    kod = kodStatusLelonganDAO.findById("TB");
                                                    ll.setKodStatusLelongan(kod);
                                                    ll.setInfoAudit(ial);
                                                    lelongService.saveOrUpdate(ll);
                                                    if (enkuiri.getCaraLelong().equals("A")) {

                                                        enkuiri.setDeposit(null);
                                                        enkuiri.setHargaRizab(null);
                                                        enkuiri.setHargaBida(null);
                                                        enkuiri.setBaki(null);
                                                        lelongService.saveOrUpdate(enkuiri);
                                                    }
                                                    kod = kodStatusLelonganDAO.findById("AK");
                                                    ial.setDimasukOleh(pengguna);
                                                    ial.setTarikhMasuk(new java.util.Date());
                                                    Lelongan lel = new Lelongan();
                                                    lel.setEnkuiri(enkuiri);
                                                    lel.setBil(bil + 1);
                                                    lel.setInfoAudit(ial);
                                                    lel.setPermohonan(permohonan);
                                                    lel.setKodStatusLelongan(kod);
                                                    lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                    if (ll.getPerihalTanah() != null) {
                                                        lel.setPerihalTanah(ll.getPerihalTanah());
                                                    }
                                                    if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                        lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                    }
                                                    lelongService.saveOrUpdate(lel);
                                                    bilLel = lel.getBil();
                                                    tarikhAkhirBayaran = null;
                                                    tarikhLelong = null;
                                                    jam = null;
                                                    minit = null;
                                                    ampm = null;
                                                }

                                                if (d.after(c)) {
                                                    kod = kodStatusLelonganDAO.findById("TB");
                                                    ll.setKodStatusLelongan(kod);
                                                    ll.setInfoAudit(ial);
                                                    lelongService.saveOrUpdate(ll);
                                                    if (enkuiri.getCaraLelong().equals("A")) {

                                                    }
                                                    //bersama
                                                    if (enkuiri.getCaraLelong().equals("B")) {

                                                        enkuiri.setDeposit(null);
                                                        enkuiri.setHargaRizab(null);
                                                        enkuiri.setHargaBida(null);
                                                        enkuiri.setBaki(null);
                                                        lelongService.saveOrUpdate(enkuiri);
                                                    }
                                                    kod = kodStatusLelonganDAO.findById("AK");
                                                    ial.setDimasukOleh(pengguna);
                                                    ial.setTarikhMasuk(new java.util.Date());
                                                    Lelongan lel = new Lelongan();
                                                    lel.setEnkuiri(enkuiri);
                                                    lel.setBil(bil + 1);
                                                    lel.setInfoAudit(ial);
                                                    lel.setPermohonan(permohonan);
                                                    lel.setKodStatusLelongan(kod);
                                                    lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                    if (ll.getPerihalTanah() != null) {
                                                        lel.setPerihalTanah(ll.getPerihalTanah());
                                                    }
                                                    if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                        lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                    }
                                                    lelongService.saveOrUpdate(lel);
                                                    bilLel = lel.getBil();
                                                    tarikhAkhirBayaran = null;
                                                    tarikhLelong = null;
                                                    jam = null;
                                                    minit = null;
                                                    ampm = null;
                                                }
                                            }
                                        } //                                if (pem.getPihak() != null) {
                                        else {
                                            LOG.info("---ada pembida---");
                                            LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
//                                    if (pem == null || ll.getKodStatusLelongan().getKod().equals("AT") || !ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {

                                            if (ll.getTarikhLelong() == null) {
                                                break;
                                            }

                                            if (pem != null || !ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                                ia2 = ll.getInfoAudit();
                                                b = sdf1.format(ia2.getTarikhMasuk());
                                                LOG.info("a : " + a);
                                                LOG.info("b : " + b);
                                                if (a == null ? b != null : !a.equals(b)) {
                                                    kod = kodStatusLelonganDAO.findById("AT");
                                                    ll.setKodStatusLelongan(kod);
                                                    ll.setInfoAudit(ial);
                                                    lelongService.saveOrUpdate(ll);
                                                    kod = kodStatusLelonganDAO.findById("AK");
                                                    if (enkuiri.getCaraLelong().equals("A")) {

                                                    }
                                                    //bersama
                                                    if (enkuiri.getCaraLelong().equals("B")) {

                                                        enkuiri.setDeposit(null);
                                                        enkuiri.setHargaRizab(null);
                                                        enkuiri.setHargaBida(null);
                                                        enkuiri.setBaki(null);
                                                        lelongService.saveOrUpdate(enkuiri);
                                                    }
                                                    Lelongan lel = new Lelongan();
                                                    lel.setEnkuiri(enkuiri);
                                                    lel.setBil(bil + 1);
                                                    lel.setPermohonan(permohonan);
                                                    lel.setInfoAudit(ial);
                                                    lel.setKodStatusLelongan(kod);
                                                    lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                    if (ll.getPerihalTanah() != null) {
                                                        lel.setPerihalTanah(ll.getPerihalTanah());
                                                    }
                                                    if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                        lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                    }
                                                    lelongService.saveOrUpdate(lel);
                                                    bilLel = lel.getBil();
                                                    tarikhAkhirBayaran = null;
                                                    tarikhLelong = null;
                                                    jam = null;
                                                    minit = null;
                                                    ampm = null;
                                                } else {
                                                    Date c = ia.getTarikhMasuk();
                                                    Date d = ia2.getTarikhMasuk();
                                                    if (d.before(c)) {
                                                        kod = kodStatusLelonganDAO.findById("AT");
                                                        ll.setKodStatusLelongan(kod);
                                                        ll.setInfoAudit(ial);
                                                        lelongService.saveOrUpdate(ll);
                                                        if (enkuiri.getCaraLelong().equals("A")) {

                                                        }
                                                        //bersama
                                                        if (enkuiri.getCaraLelong().equals("B")) {

                                                            enkuiri.setDeposit(null);
                                                            enkuiri.setHargaRizab(null);
                                                            enkuiri.setHargaBida(null);
                                                            enkuiri.setBaki(null);
                                                            lelongService.saveOrUpdate(enkuiri);
                                                        }
                                                        kod = kodStatusLelonganDAO.findById("AK");
                                                        ial.setDimasukOleh(pengguna);
                                                        ial.setTarikhMasuk(new java.util.Date());
                                                        Lelongan lel = new Lelongan();
                                                        lel.setEnkuiri(enkuiri);
                                                        lel.setBil(bil + 1);
                                                        lel.setInfoAudit(ial);
                                                        lel.setPermohonan(permohonan);
                                                        lel.setKodStatusLelongan(kod);
                                                        lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                        if (ll.getPerihalTanah() != null) {
                                                            lel.setPerihalTanah(ll.getPerihalTanah());
                                                        }
                                                        if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                            lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                        }
                                                        lelongService.saveOrUpdate(lel);
                                                        bilLel = lel.getBil();
                                                        tarikhAkhirBayaran = null;
                                                        tarikhLelong = null;
                                                        jam = null;
                                                        minit = null;
                                                        ampm = null;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
//                    }
                            }
                            LOG.info("bilLel : " + bilLel);
                            if (bilLel == 4) {
                                lel3 = true;
                                addSimpleMessage("Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
                            }
                        }
                    }
                    if ((bil <= 3) && ("AK".equals(lelo.getKodStatusLelongan().getKod()))) {
                        if (fasaPermohonan.getKeputusan() == null) {
                            error = false;
                            addSimpleError("Sila masukkan keputusan terlebih dahulu di tab keputusan");
                        } else {
                            LOG.info("fasaPermohonan : " + fasaPermohonan.getKeputusan().getKod());
                            if (fasaPermohonan.getKeputusan().getKod().equals("RM")) {
                                error = false;
                                addSimpleMessage("Keputusan ialah Rujuk Mahkamah,Sila Klik Butang Selesai");
                            }
                            if (fasaPermohonan.getKeputusan().getKod().equals("LS") || fasaPermohonan.getKeputusan().getKod().equals("AA")) {
                                error = true;
                                InfoAudit ia = fasaPermohonan.getInfoAudit();
                                String a = sdf1.format(ia.getTarikhMasuk());
                                InfoAudit ia2 = null;
                                String b = null;
                                KodStatusLelongan kod = null;
                                InfoAudit ial = new InfoAudit();
                                ial.setDiKemaskiniOleh(pengguna);
                                ial.setTarikhKemaskini(new java.util.Date());
                                ial.setDimasukOleh(pengguna);
                                ial.setTarikhMasuk(new java.util.Date());
                                senaraiLelongan = lelongService.getLeloganASC(idPermohonan);

                                // N9
                                // MELAKA
//                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                                LOG.info("---showForm MLK---");
                                for (Lelongan ll : senaraiLelongan) {
                                    if (ll.getBil() == bil) {
                                        LOG.info("---Tiada Pembida---");
                                        LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
                                        idLelong = ll.getIdLelong();
                                        Pembida pem = lelongService.getPembidaByIdLelong(idLelong);

                                        if (ll.getTarikhLelong() == null) {
                                            break;
                                        }

                                        if (pem == null && !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                            ia2 = ll.getInfoAudit();
                                            b = sdf1.format(ia2.getTarikhMasuk());
                                            LOG.info("a : " + a);
                                            LOG.info("b : " + b);
                                            if (a == null ? b != null : !a.equals(b)) {

                                                lelongService.saveOrUpdate(ll);
                                                kod = kodStatusLelonganDAO.findById("AK");

                                                List<Lelongan> senaraiLelong = lelongService.findListLelongan(idPermohonan);
                                                Lelongan lelong = senaraiLelong.get(0);
                                                if (lelong.getKodStatusLelongan().getKod().equals("TB")) {
                                                    Lelongan lel = new Lelongan();
                                                    lel.setEnkuiri(enkuiri);
                                                    lel.setBil(bil + 1);
                                                    lel.setPermohonan(permohonan);
                                                    lel.setInfoAudit(ial);
                                                    lel.setKodStatusLelongan(kod);
                                                    if (ll.getPerihalTanah() != null) {
                                                        lel.setPerihalTanah(ll.getPerihalTanah());
                                                    }
                                                    if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                        lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                    }
                                                    lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                    lelongService.saveOrUpdate(lel);
                                                    bilLel = lel.getBil();
                                                    tarikhAkhirBayaran = null;
                                                    tarikhLelong = null;
                                                    jam = null;
                                                    minit = null;
                                                    ampm = null;
                                                    kod = kodStatusLelonganDAO.findById("TB");
                                                    ll.setKodStatusLelongan(kod);
                                                    ll.setInfoAudit(ial);
                                                }
                                            } else {
                                                Date c = ia.getTarikhMasuk();
                                                Date d = ia2.getTarikhMasuk();
                                                if (d.before(c)) {
                                                    kod = kodStatusLelonganDAO.findById("TB");
                                                    ll.setKodStatusLelongan(kod);
                                                    ll.setInfoAudit(ial);
                                                    lelongService.saveOrUpdate(ll);
                                                    if (enkuiri.getCaraLelong().equals("A")) {

                                                    }
                                                    //bersama
                                                    if (enkuiri.getCaraLelong().equals("B")) {

                                                        enkuiri.setDeposit(null);
                                                        enkuiri.setHargaRizab(null);
                                                        enkuiri.setHargaBida(null);
                                                        enkuiri.setBaki(null);
                                                        lelongService.saveOrUpdate(enkuiri);
                                                    }
                                                    kod = kodStatusLelonganDAO.findById("AK");
                                                    ial.setDimasukOleh(pengguna);
                                                    ial.setTarikhMasuk(new java.util.Date());
                                                    Lelongan lel = new Lelongan();
                                                    lel.setEnkuiri(enkuiri);
                                                    lel.setBil(bil + 1);
                                                    lel.setInfoAudit(ial);
                                                    lel.setPermohonan(permohonan);
                                                    lel.setKodStatusLelongan(kod);
                                                    lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                    if (ll.getPerihalTanah() != null) {
                                                        lel.setPerihalTanah(ll.getPerihalTanah());
                                                    }
                                                    if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                        lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                    }
                                                    lelongService.saveOrUpdate(lel);
                                                    bilLel = lel.getBil();
                                                    tarikhAkhirBayaran = null;
                                                    tarikhLelong = null;
                                                    jam = null;
                                                    minit = null;
                                                    ampm = null;
                                                }
                                            }
                                        } //                                if (pem.getPihak() != null) {
                                        else {
                                            LOG.info("---ada pembida---");
                                            LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
//                                    if (pem == null || ll.getKodStatusLelongan().getKod().equals("AT") || !ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {

                                            if (ll.getTarikhLelong() == null) {
                                                break;
                                            }

                                            if (pem != null || !ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                                ia2 = ll.getInfoAudit();
                                                b = sdf1.format(ia2.getTarikhMasuk());
                                                LOG.info("a : " + a);
                                                LOG.info("b : " + b);
                                                if (a == null ? b != null : !a.equals(b)) {
                                                    kod = kodStatusLelonganDAO.findById("AT");
                                                    ll.setKodStatusLelongan(kod);
                                                    ll.setInfoAudit(ial);
                                                    lelongService.saveOrUpdate(ll);
                                                    kod = kodStatusLelonganDAO.findById("AK");
                                                    if (enkuiri.getCaraLelong().equals("A")) {

                                                    }
                                                    //bersama
                                                    if (enkuiri.getCaraLelong().equals("B")) {

                                                        enkuiri.setDeposit(null);
                                                        enkuiri.setHargaRizab(null);
                                                        enkuiri.setHargaBida(null);
                                                        enkuiri.setBaki(null);
                                                        lelongService.saveOrUpdate(enkuiri);
                                                    }
                                                    Lelongan lel = new Lelongan();
                                                    lel.setEnkuiri(enkuiri);
                                                    lel.setBil(bil + 1);
                                                    lel.setPermohonan(permohonan);
                                                    lel.setInfoAudit(ial);
                                                    lel.setKodStatusLelongan(kod);
                                                    lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                    if (ll.getPerihalTanah() != null) {
                                                        lel.setPerihalTanah(ll.getPerihalTanah());
                                                    }
                                                    if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                        lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                    }
                                                    lelongService.saveOrUpdate(lel);
                                                    bilLel = lel.getBil();
                                                    tarikhAkhirBayaran = null;
                                                    tarikhLelong = null;
                                                    jam = null;
                                                    minit = null;
                                                    ampm = null;
                                                } else {
                                                    Date c = ia.getTarikhMasuk();
                                                    Date d = ia2.getTarikhMasuk();
                                                    if (d.before(c)) {
                                                        kod = kodStatusLelonganDAO.findById("AT");
                                                        ll.setKodStatusLelongan(kod);
                                                        ll.setInfoAudit(ial);
                                                        lelongService.saveOrUpdate(ll);
                                                        if (enkuiri.getCaraLelong().equals("A")) {

                                                        }
                                                        //bersama
                                                        if (enkuiri.getCaraLelong().equals("B")) {

                                                            enkuiri.setDeposit(null);
                                                            enkuiri.setHargaRizab(null);
                                                            enkuiri.setHargaBida(null);
                                                            enkuiri.setBaki(null);
                                                            lelongService.saveOrUpdate(enkuiri);
                                                        }
                                                        kod = kodStatusLelonganDAO.findById("AK");
                                                        ial.setDimasukOleh(pengguna);
                                                        ial.setTarikhMasuk(new java.util.Date());
                                                        Lelongan lel = new Lelongan();
                                                        lel.setEnkuiri(enkuiri);
                                                        lel.setBil(bil + 1);
                                                        lel.setInfoAudit(ial);
                                                        lel.setPermohonan(permohonan);
                                                        lel.setKodStatusLelongan(kod);
                                                        lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                        if (ll.getPerihalTanah() != null) {
                                                            lel.setPerihalTanah(ll.getPerihalTanah());
                                                        }
                                                        if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                            lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                        }
                                                        lelongService.saveOrUpdate(lel);
                                                        bilLel = lel.getBil();
                                                        tarikhAkhirBayaran = null;
                                                        tarikhLelong = null;
                                                        jam = null;
                                                        minit = null;
                                                        ampm = null;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
//                    }
                            }
                            LOG.info("bilLel : " + bilLel);
                            if (bilLel == 4) {
                                lel3 = true;
                                addSimpleMessage("Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
                            }
                        }
                    } else if ((bil == 3) && ("AK".equals(lelo.getKodStatusLelongan().getKod()))) {
                        LOG.info("lelongan telah di lakukan sebanyak 3 kali");
                        lel3 = true;
                        addSimpleMessage("Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
                    }

                }
            }

            LOG.info("rehydrate 2nd");
//            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            //added by syazwan(21/10/2013)
            //show sejarah jurulelong
            lelonganListJLB = lelongService.listLelonganTB(idPermohonan);

            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);

                if (permohonan.getPermohonanSebelum() == null) {
                    enkuiriBaru = lelongService.findEnkuiri(idPermohonan);
                } else {
                    List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (list2.isEmpty()) {
                        enkuiriBaru = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (enkuiriBaru == null) {
                            List<Enkuiri> list3 = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                            if (!list3.isEmpty()) {
                                enkuiriBaru = list3.get(0);
                            }
                        }
                    } else {
                        enkuiriBaru = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                        if (enkuiriBaru == null) {
                            List<Enkuiri> list3 = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                            if (!list3.isEmpty()) {
                                enkuiriBaru = list3.get(0);
                            }
                        }
                    }
                }

                if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
                    lelonganList = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                } else {
                    lelonganList = lelongService.listLelonganAK(idPermohonan);
                }

                if ("05".equals(conf.getProperty("kodNegeri"))) {
                    mlk = false;
                    lelonganList2 = new ArrayList<Lelongan>();
                    if (!lelonganList.isEmpty()) {
                        lelonganList2.add(lelonganList.get(0));
                        lelongan = lelonganList2.get(0);
                        if (lelongan.getSytJuruLelong() != null) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                        LOG.info("lelonganList.size() : " + lelonganList2.size());
                    }
                } else if ("04".equals(conf.getProperty("kodNegeri"))) {
                    mlk = true;
                    lelonganList3 = new ArrayList<Lelongan>();
                    if (!lelonganList.isEmpty()) {
                        lelonganList3.add(lelonganList.get(0));
                        lelongan = lelonganList3.get(0);
                        if (lelongan.getJurulelong() != null) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                        LOG.info("lelonganList3.size() : " + lelonganList3.size());
                    }
                }

                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    if (enkuiriBaru.getCaraLelong().equals("B")) {
                        if (StringUtils.isNotEmpty(enkuiriBaru.getPerihalTanah())) {
                            jual = true;
                            perihal = enkuiriBaru.getPerihalTanah();
                        } else {
                            jual = false;
                        }
                        getContext().getRequest().setAttribute("B", Boolean.TRUE);
                        getContext().getRequest().setAttribute("A", Boolean.FALSE);

                    }
                    if (enkuiriBaru.getCaraLelong().equals("A")) {
                        getContext().getRequest().setAttribute("A", Boolean.TRUE);
                        getContext().getRequest().setAttribute("B", Boolean.FALSE);
                        for (Lelongan ll : lelonganList) {
                            if (StringUtils.isNotEmpty(ll.getPerihalTanah())) {
                                jual = true;
                            } else {
                                jual = false;
                            }
                        }
                    }
                }
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                mlk = false;
                for (Lelongan ll : lelonganList) {
                    if (ll.getSytJuruLelong() == null) {
                        getContext().getRequest().setAttribute("jurulelong", Boolean.FALSE);
                        break;
                    } else {
                        getContext().getRequest().setAttribute("jurulelong", Boolean.TRUE);
                        break;
                    }
                }
            }

            LOG.info("----Maklumat Jurulelong-----");
            show = true;
            //FasaPermohonan mohonFasa = lelongService.findFasaPermohonanSemakPembida(idPermohonan);

            List<FasaPermohonan> mohonFasaList = lelongService.findListFasaPermohonanSemakPembida(idPermohonan);
            FasaPermohonan mohonFasa = null;
            if (!mohonFasaList.isEmpty()) {
                mohonFasa = mohonFasaList.get(0);
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                mlk = false;
            }
            if (mohonFasa != null && mohonFasa.getKeputusan() == null) {
                LOG.info("----null-----");
                addSimpleError("Keputusan Belum Dibuat.Sila Pilih Keputusan Di Tab Keputusan.");
                button = true;
//            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            } else {

                if (mohonFasa.getKeputusan().getKod().equals("AA")) {
                    LOG.info("------Jurulelong------");
                    List<Lelongan> lel = null;
                    if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
                        lel = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                    } else {
                        lel = lelongService.listLelonganAK(idPermohonan);
                    }
                    if (lel.isEmpty()) {
                        addSimpleError("Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan.");
                        button = true;
//                    return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                    } else {
                        for (Lelongan ll : lel) {
                            if (ll.getTarikhLelong() == null) {
                                addSimpleError("Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan.");
                                button = true;
//                            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                            }
                        }
                    }
                    button = false;
                    flag = true;
                    List<FasaPermohonan> senaraifasamohon = lelongService.getPermonanFasa2(idPermohonan);
                    LOG.info("senaraifasamohon : " + senaraifasamohon.size());
                    FasaPermohonan fas = null;
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    if (!senaraifasamohon.isEmpty()) {
                        fas = senaraifasamohon.get(0);
                        fas.setInfoAudit(ia);
                    } else {
                        fas = new FasaPermohonan();
                        fas.setInfoAudit(ia);
                        fas.setPermohonan(permohonan);
                        fas.setIdAliran("semak16HLantikJurulelong");
                        fas.setCawangan(pengguna.getKodCawangan());
                        fas.setIdPengguna(pengguna.getIdPengguna());
                    }
                    KodKeputusan kos = kodKeputusanDAO.findById("JL");
                    fas.setKeputusan(kos);
                    fas.setTarikhHantar(new Date());
                    fas.setTarikhKeputusan(new Date());
                    lelongService.saveUpdate2(fas);
//                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                }
                if (mohonFasa.getKeputusan().getKod().equals("LS")) {
                    LOG.info("-----Pentadbir Tanah-------");
                    List<Lelongan> lel = null;
                    if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
                        lel = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                    } else {
                        lel = lelongService.listLelonganAK(idPermohonan);
                    }
                    if (lel.isEmpty()) {
                        addSimpleError("Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan.");
                        button = true;
//                    return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                    } else {
                        for (Lelongan ll : lel) {
                            if (ll.getTarikhLelong() == null) {
                                addSimpleError("Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan.");
                                button = true;
//                            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                            }
                        }
                    }
                    button = false;
                    for (Lelongan ll : lelonganList) {
                        ll.setSytJuruLelong(null);
                        lelongService.saveOrUpdate(ll);
                    }
                    flag = false;

                    List<FasaPermohonan> senaraifasamohon = lelongService.getPermonanFasa2(idPermohonan);
                    LOG.info("senaraifasamohon : " + senaraifasamohon.size());
                    FasaPermohonan fas = null;
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    if (!senaraifasamohon.isEmpty()) {
                        fas = senaraifasamohon.get(0);
                        fas.setInfoAudit(ia);
                    } else {
                        fas = new FasaPermohonan();
                        fas.setInfoAudit(ia);
                        fas.setPermohonan(permohonan);
                        fas.setIdAliran("semak16HLantikJurulelong");
                        fas.setCawangan(pengguna.getKodCawangan());
                        fas.setIdPengguna(pengguna.getIdPengguna());
                    }
                    KodKeputusan kos = kodKeputusanDAO.findById("PH");
                    fas.setKeputusan(kos);
                    fas.setTarikhHantar(new Date());
                    fas.setTarikhKeputusan(new Date());
                    lelongService.saveUpdate2(fas);
//                    rehydrate();
//                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                }
                if (mohonFasa.getKeputusan().getKod().equals("RM")) {
                    LOG.info("-----Rujuk Mahkahmah-------");
                    addSimpleMessage("Keputusan Adalah Rujuk Mahkahmah.Sila Klik Butang Selesai...");
                    button = true;
//                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                }
            }

            LOG.info("####### rehydrate Jana #########"); // masuk rehydrate untuk jana

            List<PermohonanPihak> listHP = lelongService.getSenaraiPmohonPihak(idPermohonan);
            Map<Long, PermohonanPihak> pihakMap = new HashMap<Long, PermohonanPihak>();
            for (PermohonanPihak hp : listHP) {
                Long id = hp.getPihak().getIdPihak();
                if (pihakMap.containsKey(id)) {
                    continue;
                } else {
                    pihakMap.put(id, hp);
                }
            }
            senaraiPermohonanPihak = new ArrayList(pihakMap.values());
            listNotis = lelongService.getListNotis2(idPermohonan, "SNG");
            if (listNotis.isEmpty()) {
                simpanNotis();
                listNotis = lelongService.getListNotis2(idPermohonan, "SNG");
            }

//        LOGGER.info("----16H----");
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                reportName = "LLGNotisGantianLelong_MLK.rdf";
            }

            List<KandunganFolder> listKandunganFolder = lelongService.getListDokumenSNG(permohonan.getFolderDokumen().getFolderId());
            if (!listKandunganFolder.isEmpty()) {
                for (KandunganFolder ff : listKandunganFolder) {
                    if (ff.getDokumen().getKodDokumen().getKod().equals("SNG")) {
                        dokumen = ff.getDokumen();
                        break;
                    }
                }
            }

            if (dokumen != null && dokumen.getNamaFizikal() != null) {
                idDokumen = dokumen.getIdDokumen();
                view = true;
                viewPdf();
            } else {
                view = false;
            }
        }
    }

    //berasingan utk n.melaka
    public Resolution simpanLelong() {

        InfoAudit ia = new InfoAudit();
        listLel = lelongService.listLelonganAK(idPermohonan);
        for (int i = 0; i < listLel.size(); i++) {
            LOG.info("------1----------");
            Lelongan lel = listLel.get(i);
            LOG.info("Lelong : " + lel.getIdLelong());
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            tarikhLelong = tarikhLelong + " " + jam + ":" + " " + minit + " " + ampm;
            LOG.info("tarikhLelong2 :" + tarikhLelong);

            try {
                lel.setTarikhLelong(sdf.parse(tarikhLelong));
            } catch (Exception ex) {
                LOG.error(ex);
            }
            try {
                lel.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            } catch (Exception ex) {
                LOG.error(ex);
            }

            lel.setTempat(lelong.getTempat());
            KodStatusLelongan ksl = new KodStatusLelongan();
            ksl.setKod("AK");
            lel.setKodStatusLelongan(ksl);
            lel.setInfoAudit(ia);
            enService.simpan(lel);
        }
        //negeri malake amaun hutang tertunggak

        negori = true;
        enkuiri.setAmaunTunggakan(amaunTunggakan);
        enkuiri.setAmaunGadaian(amaunTunggakan2);
        lelongService.saveOrUpdate(enkuiri);

//        rehydrate();
//        showForm();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new JSP("lelong/kpsn_lelong.jsp").addParameter("tab", "true");
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return checkPermohonan();
    }

    //bersama utk n.melaka
    public Resolution saveBersama() throws ParseException {

        InfoAudit ia = new InfoAudit();
        listLel = lelongService.listLelonganAK(idPermohonan);
        SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (int i = 0; i < listLel.size(); i++) {
            LOG.info("------1----------");
            Lelongan lel = listLel.get(i);
            LOG.info("Lelong : " + lel.getIdLelong());
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            tarikhLelong = tarikhLelong + " " + jam + ":" + " " + minit + " " + ampm;
            LOG.info("tarikhLelong1 :" + tarikhLelong);
            try {
                lel.setTarikhLelong(sdfNew.parse(tarikhLelong));
            } catch (Exception ex) {
                LOG.error(ex);
            }
            try {
                lel.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            } catch (Exception ex) {
                LOG.error(ex);
            }
            lel.setTempat(lelong.getTempat());
            KodStatusLelongan ksl = new KodStatusLelongan();
            ksl.setKod("AK");
            lel.setDeposit(enkuiri.getDeposit());
            lel.setHargaRizab(enkuiri.getHargaRizab());
            lel.setKodStatusLelongan(ksl);
            lel.setInfoAudit(ia);
            enService.simpan(lel);
            
        }
//        LOG.info ("Tarik Lelong = " + listLel.get(0).getTarikhLelong());
//        LOG.info ("tarikhLelongBaru = " + tarikhLelongBaru);
        //negeri malake amaun hutang tertunggak

        negori = true;
        enkuiriBaru = lelongService.findEnkuiri(idPermohonan);
        enkuiriBaru.setAmaunTunggakan(amaunTunggakan);
        enkuiriBaru.setHargaRizab(enkuiri.getHargaRizab());
        enkuiriBaru.setDeposit(enkuiri.getDeposit());
        enkuiriBaru.setAmaunGadaian(amaunTunggakan2);
        enkuiriBaru.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
        lelongService.saveOrUpdate(enkuiriBaru);

//        rehydrate();
//        showForm();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return checkPermohonan();
//        return new JSP("lelong/UtilitiSemakanPembida.jsp").addParameter("tab", "true");
//        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiSemakanPembida.jsp");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public List<Lelongan> getListLel() {
        return listLel;
    }

    public void setListLel(List<Lelongan> listLel) {
        this.listLel = listLel;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public String getKodJenis() {
        return kodJenis;
    }

    public void setKodJenis(String kodJenis) {
        this.kodJenis = kodJenis;
    }

    public List<Pembida> getList() {
        return list;
    }

    public void setList(List<Pembida> list) {
        this.list = list;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<Lelongan> getListLel1() {
        return listLel1;
    }

    public void setListLel1(List<Lelongan> listLel1) {
        this.listLel1 = listLel1;
    }

    public Pembida getPembida() {
        return pembida;
    }

    public void setPembida(Pembida pembida) {
        this.pembida = pembida;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getKodsts() {
        return kodsts;
    }

    public void setKodsts(String kodsts) {
        this.kodsts = kodsts;
    }

    public String getIdPembida() {
        return idPembida;
    }

    public void setIdPembida(String idPembida) {
        this.idPembida = idPembida;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<Lelongan> getListLelongHakmilik() {
        return listLelongHakmilik;
    }

    public void setListLelongHakmilik(List<Lelongan> listLelongHakmilik) {
        this.listLelongHakmilik = listLelongHakmilik;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public List<Lelongan> getListLel2() {
        return listLel2;
    }

    public void setListLel2(List<Lelongan> listLel2) {
        this.listLel2 = listLel2;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(String tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public List<Pembida> getListPembida() {
        return listPembida;
    }

    public void setListPembida(List<Pembida> listPembida) {
        this.listPembida = listPembida;
    }

    public List<Pembida> getList2() {
        return list2;
    }

    public void setList2(List<Pembida> list2) {
        this.list2 = list2;
    }

    public Enkuiri getEnkuiri1() {
        return enkuiri1;
    }

    public void setEnkuiri1(Enkuiri enkuiri1) {
        this.enkuiri1 = enkuiri1;
    }

    public boolean isAdaPembida() {
        return adaPembida;
    }

    public void setAdaPembida(boolean adaPembida) {
        this.adaPembida = adaPembida;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<DokumenKewangan> getCheckBayaranPerintah() {
        return checkBayaranPerintah;
    }

    public void setCheckBayaranPerintah(List<DokumenKewangan> checkBayaranPerintah) {
        this.checkBayaranPerintah = checkBayaranPerintah;
    }

    public List<Permohonan> getCheckIdMohonSebelum() {
        return checkIdMohonSebelum;
    }

    public void setCheckIdMohonSebelum(List<Permohonan> checkIdMohonSebelum) {
        this.checkIdMohonSebelum = checkIdMohonSebelum;
    }

    public String getIdMohonSebelum() {
        return idMohonSebelum;
    }

    public void setIdMohonSebelum(String idMohonSebelum) {
        this.idMohonSebelum = idMohonSebelum;
    }

    public boolean isShowIdMohonSebelum() {
        return showIdMohonSebelum;
    }

    public void setShowIdMohonSebelum(boolean showIdMohonSebelum) {
        this.showIdMohonSebelum = showIdMohonSebelum;
    }

    public static boolean isIsDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean aIsDebug) {
        isDebug = aIsDebug;
    }

    public static boolean isIS_DEBUG() {
        return IS_DEBUG;
    }

    public static void setIS_DEBUG(boolean aIS_DEBUG) {
        IS_DEBUG = aIS_DEBUG;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public List<PermohonanCarian> getSenaraiPermohonanCarian() {
        return senaraiPermohonanCarian;
    }

    public void setSenaraiPermohonanCarian(List<PermohonanCarian> senaraiPermohonanCarian) {
        this.senaraiPermohonanCarian = senaraiPermohonanCarian;
    }

    public List<Transaksi> getListT() {
        return listT;
    }

    public void setListT(List<Transaksi> listT) {
        this.listT = listT;
    }

    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }

    public String getCaraLelong() {
        return caraLelong;
    }

    public void setCaraLelong(String caraLelong) {
        this.caraLelong = caraLelong;
    }

    public boolean isWakilPembida() {
        return wakilPembida;
    }

    public void setWakilPembida(boolean wakilPembida) {
        this.wakilPembida = wakilPembida;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public PihakDAO getPihakDAO() {
        return pihakDAO;
    }

    public void setPihakDAO(PihakDAO pihakDAO) {
        this.pihakDAO = pihakDAO;
    }

    public HakmilikService getHakmilikService() {
        return hakmilikService;
    }

    public void setHakmilikService(HakmilikService hakmilikService) {
        this.hakmilikService = hakmilikService;
    }

    public KodKlasifikasiDAO getKodKlasDAO() {
        return kodKlasDAO;
    }

    public void setKodKlasDAO(KodKlasifikasiDAO kodKlasDAO) {
        this.kodKlasDAO = kodKlasDAO;
    }

    public KodStatusDokumenCapaiDAO getKodStatusDokumenCapaiDAO() {
        return kodStatusDokumenCapaiDAO;
    }

    public void setKodStatusDokumenCapaiDAO(KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO) {
        this.kodStatusDokumenCapaiDAO = kodStatusDokumenCapaiDAO;
    }

    public DokumenCapaianDAO getDokumenCapaianDAO() {
        return dokumenCapaianDAO;
    }

    public void setDokumenCapaianDAO(DokumenCapaianDAO dokumenCapaianDAO) {
        this.dokumenCapaianDAO = dokumenCapaianDAO;
    }

    public KodDokumenDAO getKodDokumenDAO() {
        return kodDokumenDAO;
    }

    public void setKodDokumenDAO(KodDokumenDAO kodDokumenDAO) {
        this.kodDokumenDAO = kodDokumenDAO;
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public LelonganDAO getLelonganDAO() {
        return lelonganDAO;
    }

    public void setLelonganDAO(LelonganDAO lelonganDAO) {
        this.lelonganDAO = lelonganDAO;
    }

    public KodKeputusanDAO getKodKeputusanDAO() {
        return kodKeputusanDAO;
    }

    public void setKodKeputusanDAO(KodKeputusanDAO kodKeputusanDAO) {
        this.kodKeputusanDAO = kodKeputusanDAO;
    }

    public PembidaDAO getPembidaDAO() {
        return pembidaDAO;
    }

    public void setPembidaDAO(PembidaDAO pembidaDAO) {
        this.pembidaDAO = pembidaDAO;
    }

    public WakilPihakDAO getWakilPihakDAO() {
        return wakilPihakDAO;
    }

    public void setWakilPihakDAO(WakilPihakDAO wakilPihakDAO) {
        this.wakilPihakDAO = wakilPihakDAO;
    }

    public KodJenisPengenalanDAO getKodJenisPengenalanDAO() {
        return kodJenisPengenalanDAO;
    }

    public void setKodJenisPengenalanDAO(KodJenisPengenalanDAO kodJenisPengenalanDAO) {
        this.kodJenisPengenalanDAO = kodJenisPengenalanDAO;
    }

    public KodStsPembidaDAO getKodStsPembidaDAO() {
        return kodStsPembidaDAO;
    }

    public void setKodStsPembidaDAO(KodStsPembidaDAO kodStsPembidaDAO) {
        this.kodStsPembidaDAO = kodStsPembidaDAO;
    }

    public LelongService getLelongService() {
        return lelongService;
    }

    public void setLelongService(LelongService lelongService) {
        this.lelongService = lelongService;
    }

    public FasaPermohonanDAO getFasaPermohonanDAO() {
        return fasaPermohonanDAO;
    }

    public void setFasaPermohonanDAO(FasaPermohonanDAO fasaPermohonanDAO) {
        this.fasaPermohonanDAO = fasaPermohonanDAO;
    }

    public FasaPermohonanService getFasaPermohonanService() {
        return fasaPermohonanService;
    }

    public void setFasaPermohonanService(FasaPermohonanService fasaPermohonanService) {
        this.fasaPermohonanService = fasaPermohonanService;
    }

    public EnkuiriService getEnkuiriService() {
        return enkuiriService;
    }

    public void setEnkuiriService(EnkuiriService enkuiriService) {
        this.enkuiriService = enkuiriService;
    }

    public PihakService getPihakService() {
        return pihakService;
    }

    public void setPihakService(PihakService pihakService) {
        this.pihakService = pihakService;
    }

    public PembetulanService getpService() {
        return pService;
    }

    public void setpService(PembetulanService pService) {
        this.pService = pService;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public String getNoic() {
        return noic;
    }

    public void setNoic(String noic) {
        this.noic = noic;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List getListBida() {
        return listBida;
    }

    public void setListBida(List listBida) {
        this.listBida = listBida;
    }

    public WakilPihak getWakilPihak() {
        return wakilPihak;
    }

    public void setWakilPihak(WakilPihak wakilPihak) {
        this.wakilPihak = wakilPihak;
    }

    public String getIdWakil() {
        return idWakil;
    }

    public void setIdWakil(String idWakil) {
        this.idWakil = idWakil;
    }

    public String getStageid() {
        return Stageid;
    }

    public void setStageid(String Stageid) {
        this.Stageid = Stageid;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public boolean isFlagPage() {
        return flagPage;
    }

    public void setFlagPage(boolean flagPage) {
        this.flagPage = flagPage;
    }

    public EnkuiriService getEnService() {
        return enService;
    }

    public void setEnService(EnkuiriService enService) {
        this.enService = enService;
    }

    public KodStatusLelonganDAO getKodStatusLelonganDAO() {
        return kodStatusLelonganDAO;
    }

    public void setKodStatusLelonganDAO(KodStatusLelonganDAO kodStatusLelonganDAO) {
        this.kodStatusLelonganDAO = kodStatusLelonganDAO;
    }

    public CalenderManager getManager() {
        return manager;
    }

    public void setManager(CalenderManager manager) {
        this.manager = manager;
    }

    public List<FasaPermohonan> getListFasa() {
        return listFasa;
    }

    public void setListFasa(List<FasaPermohonan> listFasa) {
        this.listFasa = listFasa;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public List<Lelongan> getSenaraiLelongan2() {
        return senaraiLelongan2;
    }

    public void setSenaraiLelongan2(List<Lelongan> senaraiLelongan2) {
        this.senaraiLelongan2 = senaraiLelongan2;
    }

    public List<Lelongan> getSenaraiLelongan3() {
        return senaraiLelongan3;
    }

    public void setSenaraiLelongan3(List<Lelongan> senaraiLelongan3) {
        this.senaraiLelongan3 = senaraiLelongan3;
    }

    public List<Lelongan> getListLelongan() {
        return listLelongan;
    }

    public void setListLelongan(List<Lelongan> listLelongan) {
        this.listLelongan = listLelongan;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public List<Enkuiri> getSenaraiEnkuiri3() {
        return senaraiEnkuiri3;
    }

    public void setSenaraiEnkuiri3(List<Enkuiri> senaraiEnkuiri3) {
        this.senaraiEnkuiri3 = senaraiEnkuiri3;
    }

    public List<Pembida> getSenaraiPembida() {
        return senaraiPembida;
    }

    public void setSenaraiPembida(List<Pembida> senaraiPembida) {
        this.senaraiPembida = senaraiPembida;
    }

    public SimpleDateFormat getSdf1() {
        return sdf1;
    }

    public void setSdf1(SimpleDateFormat sdf1) {
        this.sdf1 = sdf1;
    }

    public boolean isNegori() {
        return negori;
    }

    public void setNegori(boolean negori) {
        this.negori = negori;
    }

    public BigDecimal getAmaunTunggakan() {
        return amaunTunggakan;
    }

    public void setAmaunTunggakan(BigDecimal amaunTunggakan) {
        this.amaunTunggakan = amaunTunggakan;
    }

    public BigDecimal getAmaunTunggakan2() {
        return amaunTunggakan2;
    }

    public void setAmaunTunggakan2(BigDecimal amaunTunggakan2) {
        this.amaunTunggakan2 = amaunTunggakan2;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(String tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
    }

    public String getTarikhLelongTerdahulu() {
        return tarikhLelongTerdahulu;
    }

    public void setTarikhLelongTerdahulu(String tarikhLelongTerdahulu) {
        this.tarikhLelongTerdahulu = tarikhLelongTerdahulu;
    }

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getDisabled3() {
        return disabled3;
    }

    public void setDisabled3(String disabled3) {
        this.disabled3 = disabled3;
    }

    public String getIdHak() {
        return idHak;
    }

    public void setIdHak(String idHak) {
        this.idHak = idHak;
    }

    public boolean isLel3() {
        return lel3;
    }

    public void setLel3(boolean lel3) {
        this.lel3 = lel3;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<CalenderLelong> getListCalender() {
        return listCalender;
    }

    public void setListCalender(List<CalenderLelong> listCalender) {
        this.listCalender = listCalender;
    }

    public List<CalenderLelong> getListCalender2() {
        return listCalender2;
    }

    public void setListCalender2(List<CalenderLelong> listCalender2) {
        this.listCalender2 = listCalender2;
    }

    public String getTarikhEnkuiri() {
        return tarikhEnkuiri;
    }

    public void setTarikhEnkuiri(String tarikhEnkuiri) {
        this.tarikhEnkuiri = tarikhEnkuiri;
    }

    public long getIdLelong() {
        return idLelong;
    }

    public void setIdLelong(long idLelong) {
        this.idLelong = idLelong;
    }

    public int getBilLelong() {
        return bilLelong;
    }

    public void setBilLelong(int bilLelong) {
        this.bilLelong = bilLelong;
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isChecking() {
        return checking;
    }

    public void setChecking(boolean checking) {
        this.checking = checking;
    }

    public boolean isCheckingBaki() {
        return checkingBaki;
    }

    public void setCheckingBaki(boolean checkingBaki) {
        this.checkingBaki = checkingBaki;
    }

    public boolean isSaveFlg() {
        return saveFlg;
    }

    public void setSaveFlg(boolean saveFlg) {
        this.saveFlg = saveFlg;
    }

    public boolean isAddFlg() {
        return addFlg;
    }

    public void setAddFlg(boolean addFlg) {
        this.addFlg = addFlg;
    }

    public static String getKodNegeri() {
        return kodNegeri;
    }

    public static void setKodNegeri(String kodNegeri) {
        UtilitiSemakanPembidaActionBean.kodNegeri = kodNegeri;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public boolean isMlk() {
        return mlk;
    }

    public void setMlk(boolean mlk) {
        this.mlk = mlk;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public List<Lelongan> getLelonganList() {
        return lelonganList;
    }

    public void setLelonganList(List<Lelongan> lelonganList) {
        this.lelonganList = lelonganList;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public boolean isJual() {
        return jual;
    }

    public void setJual(boolean jual) {
        this.jual = jual;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public boolean isStageJurulelongN9() {
        return stageJurulelongN9;
    }

    public void setStageJurulelongN9(boolean stageJurulelongN9) {
        this.stageJurulelongN9 = stageJurulelongN9;
    }

    public List<Lelongan> getLelonganListJLB() {
        return lelonganListJLB;
    }

    public void setLelonganListJLB(List<Lelongan> lelonganListJLB) {
        this.lelonganListJLB = lelonganListJLB;
    }

    public List<Lelongan> getLelonganList2() {
        return lelonganList2;
    }

    public void setLelonganList2(List<Lelongan> lelonganList2) {
        this.lelonganList2 = lelonganList2;
    }

    public List<Lelongan> getLelonganList3() {
        return lelonganList3;
    }

    public void setLelonganList3(List<Lelongan> lelonganList3) {
        this.lelonganList3 = lelonganList3;
    }

    public Lelongan getLelongan() {
        return lelongan;
    }

    public void setLelongan(Lelongan lelongan) {
        this.lelongan = lelongan;
    }

    public Enkuiri getEnkuiriBaru() {
        return enkuiriBaru;
    }

    public void setEnkuiriBaru(Enkuiri enkuiriBaru) {
        this.enkuiriBaru = enkuiriBaru;
    }

    public SytJuruLelongDAO getSytJuruLelongDAO() {
        return sytJuruLelongDAO;
    }

    public void setSytJuruLelongDAO(SytJuruLelongDAO sytJuruLelongDAO) {
        this.sytJuruLelongDAO = sytJuruLelongDAO;
    }

    public JuruLelongDAO getJurulelongDAO() {
        return jurulelongDAO;
    }

    public void setJurulelongDAO(JuruLelongDAO jurulelongDAO) {
        this.jurulelongDAO = jurulelongDAO;
    }

    public List<JuruLelong> getListJurulelong() {
        return listJurulelong;
    }

    public void setListJurulelong(List<JuruLelong> listJurulelong) {
        this.listJurulelong = listJurulelong;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getIframeURL() {
        return iframeURL;
    }

    public void setIframeURL(String iframeURL) {
        this.iframeURL = iframeURL;
    }

}
