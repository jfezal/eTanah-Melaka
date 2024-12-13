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
import etanah.dao.KodPerananDAO;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodPeranan;
import etanah.model.KodStatusEnkuiri;
import etanah.model.KodStatusLelongan;
import etanah.model.Lelongan;
import etanah.model.Pembida;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanCarian;
import etanah.model.Transaksi;
import etanah.service.HakmilikService;
import etanah.service.common.LelongService;
import etanah.service.common.TaskDebugService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Session;

/**
 *
 * @author nur.aqilah
 */
@UrlBinding("/lelong/tangguhBatalPermohonan")
public class UtilitiPenangguhanPembatalanActionBean extends AbleActionBean {

    @Inject
    etanah.Configuration conf;
    @Inject
    private KodStatusEnkuiriDAO kodStatusEnkuiriDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private LelonganDAO lelonganDAO;
    @Inject
    private KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    private LelongService lelongService;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    KodPerananDAO kodPeranannDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    CalenderManager manager;
    private static final Logger LOG = Logger.getLogger(UtilitiPenangguhanPembatalanActionBean.class);
    private static boolean IS_DEBUG = LOG.isDebugEnabled();
    Permohonan permohonan;
    private FasaPermohonan fasaPermohonan;
    private Enkuiri enkuiri;
    private Dokumen dokumen;
    private Lelongan lelongan;
    private Pengguna pengguna;

    private KodStatusLelongan kodStatusLelongan;
    private List<Permohonan> senaraiPermohonan;
    private List<PermohonanCarian> senaraiPermohonanCarian;
    private List<PermohonanCarian> senaraiPermohonanCarian2;
    private List<Lelongan> listLel2;
    private List<Lelongan> sejarahLelongan;
    private List<Lelongan> aktifLelongan;
    private List<Lelongan> lelonganBaru;
    private List<Lelongan> checkTangguhBatalPermohonan;
    private List<Lelongan> listLelongan = new ArrayList<Lelongan>();
    private List<Lelongan> listLel = new ArrayList<Lelongan>();
    private List<Enkuiri> sejarahEnkuiri;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;
    private List<PenggunaPeranan> senaraiPT1;
    private List<Transaksi> listT = new ArrayList<Transaksi>();
    private List<Pengguna> senaraiPT = new ArrayList<Pengguna>();
//    private List<Pengguna> senaraiPT;
    private BigDecimal amaunTunggakan;
    private BigDecimal amaunTunggakan2;
    private BigDecimal baki;
    private boolean showTangguhBatal = false;
    private boolean tangguh = false;
    private boolean melaka = false;
    private boolean batal = false;
    private boolean showForm = false;
    private boolean view = false;
    private boolean view2 = false;
    private boolean showRadio = false;
    private long idDokumen;
    private String btn2;
    private String jenisPermohonan;
    private String tarikhEnkuiri;
    private String idPermohonan;
    private String idHak;
    private String tarikhLelong;
    private String tarikhAkhirBayaran;
    private String tarikhLelongTerdahulu;
    private String jam;
    private String minit;
    private String ampm;
    private String ulasan;
    private String taskId;
    private String idPengguna;
    private String stage;
    private String tindakan;
    private BigDecimal hargaRizab;
    private BigDecimal deposit;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    @Inject
    private TaskDebugService ts;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("default handler");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPenangguhanPembatalan.jsp");
    }

    //search id mohon by willcard
    public Resolution find() {
        LOG.info("search id mohon by willcard");
        LOG.info("idPermohonan : " + idPermohonan);

        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (isIS_DEBUG()) {
            LOG.debug("======= page: " + page);
        }
        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
            LOG.debug("======= StringUtils is not blank ");
        }

        if (StringUtils.isBlank(idPermohonan)) {
            LOG.debug("======= idPemohon is EMPTY: ");

            setListT(hakmilikService.findMohon(getContext().getRequest().getParameterMap()));
            for (Transaksi t : getListT()) {

                if (t.getPermohonan() != null) {
                    String idMohon = t.getPermohonan().getIdPermohonan();
                    idPermohonan = idMohon;
                } else {
                    addSimpleError("Tiada Maklumat dijumpai.");
                }
            }
        }

        if (isIS_DEBUG()) {
            LOG.debug("====== idPermohonan: " + idPermohonan);
        }

        if ((StringUtils.isNotBlank(idPermohonan))) {

            if (idPermohonan != null) {
                LOG.info("MASUK idPermohonan!=null");

                permohonan = permohonanDAO.findById(idPermohonan);

                if (permohonan == null) {
                    setShowForm(true);
                } else {
                    setShowForm(false);
                }

                LOG.debug("Permohonan " + permohonan);

                if (permohonan == null) {

                    permohonan = null;

                    LOG.info("MASUK permohonan==null");

                    senaraiPermohonan = lelongService.getSenaraiPermohonan(idPermohonan, pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records());

                    LOG.info("senaraiPermohonan " + senaraiPermohonan.size());
                    set__pg_total_records(lelongService.getTotalRecordFolderAction(idPermohonan, pengguna.getKodCawangan().getKod()).intValue());

                    //find in carian
                    if (senaraiPermohonan.isEmpty()) {
                        setSenaraiPermohonanCarian(lelongService.getSenaraiPermohonanCarian(idPermohonan,
                                pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records()));

                        LOG.info("senaraiPermohonanCarian " + getSenaraiPermohonanCarian().size());
                    }
                    if (senaraiPermohonan.isEmpty()
                            && getSenaraiPermohonanCarian().isEmpty()) {
                        LOG.error("senaraiPermohonan tiada");
                        addSimpleError("Tiada Maklumat dijumpai.");
                    }
                } else {

                    idPermohonan = getContext().getRequest().getParameter("idPermohonan");

                    if (StringUtils.isNotBlank(idPermohonan)) {
                        permohonan = permohonanDAO.findById(idPermohonan);

                        if (permohonan != null) {
                            LOG.debug("MASUK ID !=NULL");
                            LOG.info("IdPermohonan :" + permohonan.getIdPermohonan());
                            checkPermohonan();

                        } else {
                            LOG.debug("MASUK ID ==NULL");
                            addSimpleError("Id Permohonan tidak wujud");
                            return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPenangguhanPembatalan.jsp");
                        }
                    }
                    return new JSP("/lelong/UtilitiPenangguhanPembatalan.jsp");
                }
            }
        }

        LOG.info("Rehydrate - finish");
        return new JSP("/lelong/UtilitiPenangguhanPembatalan.jsp");
    }

    //checking the id mohon
    public Resolution checkPermohonan() {
        LOG.info("idPermohonan : " + getIdPermohonan());
        permohonan = null;

        permohonan = permohonanDAO.findById(idPermohonan);
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            LOG.info("== melako ==");
            try {
                if (permohonan != null) {
                    LOG.info("Permohonan Wujud");
                    //-------------checking-----------------
                    //only tarikhlelong>sysdate, idAliran IN (kmskJurulelong, rekodBidaan, rekodBidaanJLB, cetak16H) kodStatusLelongan ='AK'
                    checkTangguhBatalPermohonan = lelongService.checkTangguhBatalPermohonan(permohonan.getIdPermohonan());
                    LOG.info("checkTangguhBatalPermohonan :" + checkTangguhBatalPermohonan.size());

                    if (!checkTangguhBatalPermohonan.isEmpty()) {
                        showTangguhBatal = true;
                        LOG.info("checkTangguhBatalPermohonan :" + showTangguhBatal);
                    } else {
                        showTangguhBatal = false;
                        LOG.info("Permohonan - " + permohonan.getIdPermohonan() + " Tidak Dapat Di Teruskan");
                        addSimpleMessage("Permohonan - " + permohonan.getIdPermohonan() + " Tidak Dapat Di Teruskan");
                    }
                } else {
                    LOG.info("Id Mohon Tidak Wujud");
                    addSimpleError("Id Mohon Tidak Wujud");
                }
            } catch (Exception e) {
                LOG.error("Error " + e);
            }
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            LOG.info("== negori ==");
            try {
                if (permohonan != null) {
                    LOG.info("Permohonan Wujud");
                    //-------------checking-----------------
                    //only tarikhlelong>sysdate, idAliran IN (kmskJurulelong, rekodBidaan, rekodBidaanJLB, cetak16H) kodStatusLelongan ='AK'

                    checkTangguhBatalPermohonan = lelongService.checkTangguhBatalPermohonan2(permohonan.getIdPermohonan());

                    LOG.info("cek-----");
                    Map m = ts.traceTask(idPermohonan);
                    LOG.info("=== cekStage sblom = " + stage);
                    stage = (String) m.get("stage");
                    tindakan = (String) m.get("tindakan");
                    LOG.info("=== cekTindakan " + tindakan);
                    LOG.info("=== cekStage = " + stage);
                    LOG.info("checkTangguhBatalPermohonan :" + checkTangguhBatalPermohonan.size());

                    if (!checkTangguhBatalPermohonan.isEmpty()) {
                        showTangguhBatal = true;
                        LOG.info("checkTangguhBatalPermohonan notEmpty");
                    } else {
                        showTangguhBatal = true;
                        showRadio = true;
                        LOG.info("checkTangguhBatalPermohonan isEmpty");
                        addSimpleMessage("Permohonan - " + permohonan.getIdPermohonan() + " Hanya Boleh Melakukan Proses Pembatalan. "
                                + "ID Permohonan Berada Pada Peringkat " + tindakan);

//                            showTangguhBatal = false;
//                            LOG.info("Permohonan - " + permohonan.getIdPermohonan() + " Tidak Dapat Di Teruskan");
//                            addSimpleMessage("Permohonan - " + permohonan.getIdPermohonan() + " Tidak Dapat Di Teruskan");
                    }
                } else {
                    LOG.info("Id Mohon Tidak Wujud");
                    addSimpleError("Id Mohon Tidak Wujud");
                }
            } catch (Exception e) {
                LOG.error("Error " + e);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPenangguhanPembatalan.jsp");
    }

    //urusan tangguh/batal
    public Resolution jenisPermohonan() {
        LOG.info("+++ jenisPermohonan +++");
        setPengguna((Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER));
        jenisPermohonan = getContext().getRequest().getParameter("jenis");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        KodPeranan kodperanan = kodPeranannDAO.findById("pptlelong");
        LOG.info(kodperanan);
        senaraiPT1 = lelongService.capaiPgunPeranan(kodperanan.getKod());
        for (PenggunaPeranan kodP : senaraiPT1) {
            Pengguna pguna = kodP.getPengguna();
            if (pguna.getKodCawangan().getKod().equals(pengguna.getKodCawangan().getKod())) {
                if (pguna.getStatus().getKod().equals("A")) {
                    senaraiPT.add(pguna);
                }
            }
        }

        try {
            if (jenisPermohonan.equals("BP")) {
                LOG.info("Batal");
                batal = true;
                return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPenangguhanPembatalan.jsp");

            } else if (jenisPermohonan.equals("TG")) {
                LOG.info("Tangguh");
                aktifLelongan = lelongService.listLelonganAK1(idPermohonan);
                LOG.info("aktifLelongan " + aktifLelongan.size());
                tangguh = true;

                if (!aktifLelongan.isEmpty()) {
                    tarikhLelongTerdahulu = sdf.format(aktifLelongan.get(0).getTarikhLelong()).substring(0, 10);
                }

                Lelongan lelo = aktifLelongan.get(0);
                int bil = lelo.getBil();
                LOG.info("bil : " + bil);

                //update the status from AK to TG
                for (Lelongan l : aktifLelongan) {
                    LOG.info("Lelongan Id " + l.getIdLelong());
                    kodStatusLelongan = kodStatusLelonganDAO.findById("TG");
                    l.setKodStatusLelongan(kodStatusLelongan);
                    lelonganDAO.saveOrUpdate(l);
                }
                LOG.info("===dh sampai sini===");
                enkuiri = lelongService.findEnkuiri(idPermohonan);

                checkTangguhBatalPermohonan = lelongService.checkTangguhBatalPermohonan2(permohonan.getIdPermohonan());
                LOG.info("checkTangguhBatalPermohonan :" + checkTangguhBatalPermohonan.size());
                if (checkTangguhBatalPermohonan.size() > 1) {
                    listLelongan = lelongService.findListLelong(idPermohonan);
                    lelongan = listLelongan.get(0);
                } else {
                    LOG.info("== 1 id lelong==");
                    lelongan = lelongService.findLelong(idPermohonan);

                }

                if (enkuiri != null && enkuiri.getTarikhEnkuiri() != null) {
                    tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);
                }

//                senaraiPT = lelongService.capaiPTLelong(pengguna.getKodCawangan().getKod());
//                LOG.info("senaraiPT .size :" + senaraiPT.size());
//                if (lelongan.getPengguna() != null) {
//                    LOG.info("enkuiri not null");
//                    idPengguna = lelongan.getPengguna().getIdPengguna();
//                }
                //create new lelongan and set the information
                for (Lelongan ll : aktifLelongan) {
                    InfoAudit ial = new InfoAudit();
                    kodStatusLelongan = kodStatusLelonganDAO.findById("AK");
                    ial.setDimasukOleh(pengguna);
                    ial.setTarikhMasuk(new java.util.Date());
                    Lelongan lel = new Lelongan();
                    lel.setEnkuiri(enkuiri);
                    lel.setBil(lelo.getBil());
                    lel.setInfoAudit(ial);
                    lel.setPermohonan(permohonan);
                    lel.setKodStatusLelongan(kodStatusLelongan);
                    lel.setTarikhLelong(lelongan.getTarikhLelong());
                    lel.setTempat(lelongan.getTempat());
                    lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                    LOG.info("===CEK hrge rizab = " + ll.getHargaRizab());
                    LOG.info("===CEK id mohon ll = " + ll.getPermohonan().getIdPermohonan());
                    LOG.info("===CEK hrge rizab lel = " + lel.getHargaRizab());
                    if (ll.getPerihalTanah() != null) {
                        lel.setPerihalTanah(ll.getPerihalTanah());
                    }
                    if (ll.getPerihalTanahBahasaInggeris() != null) {
                        lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                    }
                    lel.setDeposit(ll.getDeposit());
                    lel.setHargaRizab(ll.getHargaRizab());
                    if (ll.getJurulelong() != null) {
                        lel.setJurulelong(ll.getJurulelong());
                    }
                    LOG.info("===CEK hrge rizab LL = " + ll.getHargaRizab());
                    LOG.info("===CEK id mohon ll = " + ll.getPermohonan().getIdPermohonan());
                    LOG.info("===CEK hrge rizab lel = " + lel.getHargaRizab());
                    LOG.info("===CEK hrge rizab lelongan = " + lelongan.getHargaRizab());

                    lelongService.saveOrUpdate(lel);
                }

                lelongan = lelongService.listLelonganAK1(idPermohonan).get(0);

                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    melaka = true;
                    if (enkuiri.getAmaunTunggakan() != null) {
                        setAmaunTunggakan(enkuiri.getAmaunTunggakan());
                    }
                    if (enkuiri.getAmaunGadaian() != null) {
                        setAmaunTunggakan2(enkuiri.getAmaunGadaian());
                    }
                }

                if ("05".equals(conf.getProperty("kodNegeri"))) {
                    melaka = false;
                    if (enkuiri.getAmaunGadaian() != null) {
                        setAmaunTunggakan(enkuiri.getAmaunGadaian());
                    }
                }

                listLel = lelongService.getLeloganASC(idPermohonan);
                LOG.info("listLel : " + listLel.size());

                //berasingan
                if (enkuiri.getCaraLelong().equals("A")) {
                    getContext().getRequest().setAttribute("same", Boolean.FALSE);
                    listLel2 = lelongService.listLelonganAK1(idPermohonan);
                }
                //bersama
                listLelongan = new ArrayList<Lelongan>();
                if (enkuiri.getCaraLelong().equals("B")) {
                    getContext().getRequest().setAttribute("same", Boolean.TRUE);
                    listLelongan.add(lelongan);
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
                    setIdHak(sb.toString());
                }

                //sejarah lelongan
                sejarahLelongan = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());
                //sejarah siasatan
                sejarahEnkuiri = lelongService.getEnkuiriNotAK(permohonan.getIdPermohonan());
                LOG.info("sejarahEnkuiri" + sejarahEnkuiri.size());
                return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPenangguhanPembatalan.jsp");
            }

        } catch (Exception e) {
            LOG.error("error " + e);
        }

        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPenangguhanPembatalan.jsp");
    }

    //urusan batal
    public Resolution simpanBatal() {

        ulasan = getContext().getRequest().getParameter("ulasan");
        setPengguna((Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER));
        LOG.info("Pengguna " + pengguna);
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("ID PERMOHONAN" + permohonan.getIdPermohonan());
        //JANA LAPORAN
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String gen = "";
        String code = "";

        try {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                LOG.info("masuk jana dokumen");
                gen = "LLGbtlPTD_MLK.rdf";
                code = "BPL";
                lelongService.reportGen(permohonan, gen, code, pengguna);
            } else if ("05".equals(conf.getProperty("kodNegeri"))) {
//                gen = "LLGbtlPTD_NS.rdf"; batal oleh PTD b4 tetap tarikh lelong 23/1/2014
                gen = "LLGSuratBatal_NS.rdf";
                code = "BPL";
                lelongService.reportGen(permohonan, gen, code, pengguna);
            }

            List<KandunganFolder> listKandunganFolder = lelongService.getListDokumenBPL(permohonan.getFolderDokumen().getFolderId());
            if (!listKandunganFolder.isEmpty()) {
                for (KandunganFolder ff : listKandunganFolder) {
                    if (ff.getDokumen().getKodDokumen().getKod().equals("BPL")) {
                        setDokumen(ff.getDokumen());
                        break;
                    }
                }
            }
            if (getDokumen() != null && getDokumen().getNamaFizikal() != null) {
                LOG.info("Masuk Sini");
                idDokumen = getDokumen().getIdDokumen();
                view = true;
            }
        } catch (Exception ex) {
            LOG.error("Dokumen Tidak Dapat Dijana " + ex.getMessage());
            addSimpleError("Dokumen Tidak Dapat Dijana - " + ex.getMessage());
        }

        try {
            LOG.debug("idPermohonan = " + idPermohonan);
            //withdraw the id mohon from list of task
            List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
            LOG.info("senaraiTask : " + senaraiTask.size());
            if (senaraiTask.isEmpty()) {
                LOG.info("-----idPermohonan tidak di jumpai-----");
            } else {

                Task task = (Task) senaraiTask.get(0);
                if (task != null) {
                    LOG.info("-----idPermohonan di jumpai-----");
                    LOG.info(task);
                    setTaskId(task.getSystemAttributes().getTaskId());
                }
            }
            WorkFlowService.withdrawTask(getTaskId());
            LOG.info("------withdrawTask Taskid sucessfull!!!----- ");
            //update the status of permohonan and ulasan
            permohonan = permohonanDAO.findById(idPermohonan);
            permohonan.setStatus("BP");
            permohonan.setCatatan(ulasan);
            lelongService.saveOrUpdate(permohonan);
            //update status enkuiri/siasatan
            KodStatusEnkuiri kodEnkuiri = kodStatusEnkuiriDAO.findById("BP");
            Enkuiri en = lelongService.findEnkuiri(permohonan.getIdPermohonan());
            en.setStatus(kodEnkuiri);
            lelongService.saveOrUpdate(en);
            //update status lelong
            listLel = lelongService.listLelonganAK1(permohonan.getIdPermohonan());
            for (Lelongan ll : listLel) {
                KodStatusLelongan ksl = new KodStatusLelongan();
                ksl.setKod("BP");
                ll.setKodStatusLelongan(ksl);
                lelongService.saveOrUpdatee(ll);
            }

            lelongService.saveOrUpdate(en);
        } catch (Exception ex) {
            LOG.error(ex);
        }
        batal = true;
        addSimpleMessage("Permohonan - " + permohonan.getIdPermohonan() + " Berjaya Di Batalkan");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPenangguhanPembatalan.jsp");
    }

    //urusan tangguh
    //simpan lelongan berasingan
    public Resolution simpanLelong() {
        LOG.info("...SIMPANLELONG...");
        LOG.info(getContext().getRequest().getParameter("idPermohonan"));
        permohonan = permohonanDAO.findById(idPermohonan);
        setPengguna((Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER));
        LOG.info("Pengguna " + pengguna);
        InfoAudit ia = new InfoAudit();
        try {
            listLel = lelongService.listLelonganAK1(idPermohonan);
            enkuiri = lelongService.findEnkuiri(idPermohonan);
            for (Lelongan lel : listLel) {
                LOG.info("------1----------");
                LOG.info("Lelong : " + lel.getIdLelong());
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                setTarikhLelong(getTarikhLelong() + " " + getJam() + ":" + " " + getMinit() + " " + getAmpm());
                LOG.info("tarikhLelong :" + getTarikhLelong());

                try {
                    lel.setTarikhLelong(sdf.parse(getTarikhLelong()));
                } catch (Exception ex) {
                    LOG.error(ex);
                }
                try {
                    lel.setTarikhAkhirBayaran(sdf1.parse(getTarikhAkhirBayaran()));
                } catch (Exception ex) {
                    LOG.error(ex);
                }
                LOG.info("==id lelong lel = " + lel.getIdLelong());
                LOG.info("==deposit lel sblom = " + lel.getDeposit());
                LOG.info("==rizab lel sblom = " + lel.getHargaRizab());
                LOG.info("==tempat lel sblom = " + lel.getTempat());
                LOG.info("==id lelong LELONG = " + lelongan.getIdLelong());
                LOG.info("==deposit lelong sblom = " + lelongan.getDeposit());
                LOG.info("==rizab lelong sblom = " + lelongan.getHargaRizab());
                LOG.info("==tempat lelong sblom = " + lelongan.getTempat());
                lel.setTempat(lelongan.getTempat());
                KodStatusLelongan ksl = new KodStatusLelongan();
                ksl.setKod("AK");
                lel.setDeposit(lel.getDeposit());
                LOG.info("== cek deposit lepas = " + lel.getDeposit());
                lel.setHargaRizab(lel.getHargaRizab());
                lel.setBaki(lel.getBaki());
                Pengguna peng = lelongService.findPT(getContext().getRequest().getParameter("idPengguna"));
                lel.setPengguna(peng);
                lel.setKodStatusLelongan(ksl);
                lel.setInfoAudit(ia);
                LOG.info("==deposit lel slps = " + lel.getDeposit());
                LOG.info("==rizab lel slps = " + lel.getHargaRizab());
                LOG.info("==tempat lel slps = " + lel.getTempat());
                LOG.info("==deposit lelong slps = " + lelongan.getDeposit());
                LOG.info("==rizab lelong slps = " + lelongan.getHargaRizab());
                LOG.info("==tempat lelong slps = " + lelongan.getTempat());

                senaraiPT = lelongService.capaiPTLelong(pengguna.getKodCawangan().getKod());
                LOG.info("senaraiPT .size :" + senaraiPT.size());
                if (lelongan.getPengguna() != null) {
                    LOG.info("enkuiri not null");
                    idPengguna = lelongan.getPengguna().getIdPengguna();
                }
                LOG.info("==senaraiPT = " + senaraiPT);
                LOG.info("==idPengguna = " + idPengguna);

                lelongService.saveOrUpdatee(lel);

                lelongan = lel;
            }
            tarikhLelong = sdf.format(lelongan.getTarikhLelong()).substring(0, 10);
            //negeri malake amaun hutang tertunggak
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                melaka = true;
                enkuiri.setAmaunTunggakan(amaunTunggakan);
                enkuiri.setAmaunGadaian(amaunTunggakan2);
                lelongService.saveOrUpdate(enkuiri);
            } else {
                melaka = false;
                enkuiri.setAmaunGadaian(amaunTunggakan);
                enkuiri.setDeposit(lelongan.getDeposit());
                enkuiri.setHargaRizab(lelongan.getHargaRizab());
                lelongService.saveOrUpdate(enkuiri);
            }
            //JANA LAPORAN
            LOG.info("genReport :: start");
            LOG.info("generate report start.");
            String gen = "";
            String code = "";

            try {
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    gen = "LLGsrtTgghPTD_MLK.rdf";
                    code = "TTL";
                    lelongService.reportGen(permohonan, gen, code, pengguna);
                } else if ("05".equals(conf.getProperty("kodNegeri"))) {
                    gen = "LLGsrtTgghPTD_NS.rdf";
                    code = "TTL";
                    lelongService.reportGen(permohonan, gen, code, pengguna);
                }

                List<KandunganFolder> listKandunganFolder = lelongService.getListDokumenTTL(permohonan.getFolderDokumen().getFolderId());
                if (!listKandunganFolder.isEmpty()) {
                    for (KandunganFolder ff : listKandunganFolder) {
                        if (ff.getDokumen().getKodDokumen().getKod().equals("TTL")) {
                            setDokumen(ff.getDokumen());
                            break;
                        }
                    }
                }
                if (getDokumen() != null && getDokumen().getNamaFizikal() != null) {
                    idDokumen = getDokumen().getIdDokumen();
                    view2 = true;
                }
            } catch (Exception ex) {
                LOG.error("Dokumen Tidak Dapat Dijana - " + ex.getMessage());
                addSimpleError("Dokumen Tidak Dapat Dijana - " + ex.getMessage());
            }

            //button
            //berasingan
            if (enkuiri.getCaraLelong().equals("A")) {
                getContext().getRequest().setAttribute("same", Boolean.FALSE);
                listLel2 = lelongService.listLelonganAK1(idPermohonan);
            }
            //sejarah lelongan
            sejarahLelongan = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());
            //sejarah siasatan
            sejarahEnkuiri = lelongService.getEnkuiriNotAK(permohonan.getIdPermohonan());
            LOG.info("sejarahEnkuiri" + sejarahEnkuiri.size());

        } catch (Exception e) {
            LOG.error("error " + e);
        }

        tangguh = true;
        addSimpleMessage("Permohonan - " + idPermohonan + " Berjaya Di Tangguhkan");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPenangguhanPembatalan.jsp");
    }

    //urusan tangguh
    //simpan lelongan bersama
    public Resolution saveBersama() throws ParseException, WorkflowException {
        LOG.info("-----Masuk SaveBersama-----");
        LOG.info(getContext().getRequest().getParameter("idPermohonan"));
        permohonan = permohonanDAO.findById(idPermohonan);
        setPengguna((Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER));
        LOG.info("Pengguna " + pengguna);
        InfoAudit ia = new InfoAudit();

        try {
            listLel = lelongService.listLelonganAK1(idPermohonan);
            enkuiri = lelongService.findEnkuiri(idPermohonan);
            for (int i = 0; i < listLel.size(); i++) {
                LOG.info("------1----------");
                Lelongan lel = listLel.get(i);
                LOG.info("Lelong : " + lel.getIdLelong());
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                tarikhLelong = tarikhLelong + " " + jam + ":" + " " + minit + " " + ampm;
                LOG.info("tarikhLelong :" + tarikhLelong);
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
                lel.setTempat(lelongan.getTempat());
                KodStatusLelongan ksl = new KodStatusLelongan();
                ksl.setKod("AK");
                lel.setDeposit(lelongan.getDeposit());
                lel.setHargaRizab(lelongan.getHargaRizab());
                lel.setBaki(lelongan.getBaki());
                Pengguna peng = lelongService.findPT(getContext().getRequest().getParameter("idPengguna"));
                lel.setPengguna(peng);
                lel.setKodStatusLelongan(ksl);
                lel.setInfoAudit(ia);
                lelongService.saveOrUpdatee(lel);

                lelongan = lel;
            }
            tarikhLelong = sdf.format(lelongan.getTarikhLelong()).substring(0, 10);
            //negeri malake amaun hutang tertunggak
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                melaka = true;
                enkuiri.setAmaunTunggakan(amaunTunggakan);
                enkuiri.setAmaunGadaian(amaunTunggakan2);
                enkuiri.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
                enkuiri.setDeposit(lelongan.getDeposit());
                enkuiri.setHargaRizab(lelongan.getHargaRizab());
                lelongService.saveOrUpdate(enkuiri);
            } else if ("05".equals(conf.getProperty("kodNegeri"))) {
                melaka = false;
                enkuiri.setBakiGadaian(amaunTunggakan);
                LOG.info("--amaun gadaian: " + amaunTunggakan);
                enkuiri.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
                enkuiri.setDeposit(lelongan.getDeposit());
                enkuiri.setHargaRizab(lelongan.getHargaRizab());
//                Pengguna peng = lelongService.findPT(getContext().getRequest().getParameter("idPengguna"));
//                enkuiri.setPengguna(peng);
                lelongService.saveOrUpdate(enkuiri);
            }

            //JANA LAPORAN
            LOG.info("genReport :: start");
            LOG.info("generate report start.");
            String gen = "";
            String code = "";

            try {
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    gen = "LLGsrtTgghPTD_MLK.rdf";
                    code = "TTL";
                    lelongService.reportGen(permohonan, gen, code, pengguna);
                } else if ("05".equals(conf.getProperty("kodNegeri"))) {
                    gen = "LLGsrtTgghPTD_NS.rdf";
                    code = "TTL";
                    lelongService.reportGen(permohonan, gen, code, pengguna);
                }
                List<KandunganFolder> listKandunganFolder = lelongService.getListDokumenTTL(permohonan.getFolderDokumen().getFolderId());
                if (!listKandunganFolder.isEmpty()) {
                    for (KandunganFolder ff : listKandunganFolder) {
                        if (ff.getDokumen().getKodDokumen().getKod().equals("TTL")) {
                            setDokumen(ff.getDokumen());
                            break;
                        }
                    }
                }
                if (getDokumen() != null && getDokumen().getNamaFizikal() != null) {
                    idDokumen = getDokumen().getIdDokumen();
                    view2 = true;
                }
            } catch (Exception ex) {
                LOG.error("Dokumen Tidak Dapat Dijana - " + ex.getMessage());
                addSimpleError("Dokumen Tidak Dapat Dijana - " + ex.getMessage());
            }
            //bersama
            listLelongan = new ArrayList<Lelongan>();
            if (enkuiri.getCaraLelong().equals("B")) {
                getContext().getRequest().setAttribute("same", Boolean.TRUE);
                listLelongan.add(lelongan);
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
            //sejarah lelongan
            sejarahLelongan = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());
            //sejarah siasatan
            sejarahEnkuiri = lelongService.getEnkuiriNotAK(permohonan.getIdPermohonan());
            LOG.info("sejarahEnkuiri" + sejarahEnkuiri.size());
        } catch (Exception e) {
            LOG.error("Exception " + e);
        }

        tangguh = true;
        senaraiPT = lelongService.capaiPTLelong(pengguna.getKodCawangan().getKod());
        LOG.info("senaraiPT .size :" + senaraiPT.size());
        if (lelongan.getPengguna() != null) {
            LOG.info("enkuiri not null");
            idPengguna = lelongan.getPengguna().getIdPengguna();
        }
        LOG.info("==senaraiPT = " + senaraiPT);
        LOG.info("==idPengguna = " + idPengguna);
        addSimpleMessage("Permohonan - " + idPermohonan + " Berjaya Di Tangguhkan");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPenangguhanPembatalan.jsp");
    }

    //kalendar
    public Resolution showFormB() {
        LOG.info("masuk showFormb====");
        listLelongan = lelongService.getTG(idPermohonan);
        tarikhLelongTerdahulu = sdf.format(listLelongan.get(0).getTarikhLelong()).substring(0, 10);
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        listCalender = manager.getALLEnkuri(permohonan.getCawangan().getKod());
        listCalender2 = manager.getALLLelongan(permohonan.getCawangan().getKod());
        return new JSP("lelong/calender_lelong10.jsp").addParameter("popup", "true");
    }

    //reset
    public Resolution reset() {
        LOG.info("---nk reset---");
        permohonan = new Permohonan();
        idPermohonan = null;
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPenangguhanPembatalan.jsp");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<Lelongan> getCheckTangguhBatalPermohonan() {
        return checkTangguhBatalPermohonan;
    }

    public void setCheckTangguhBatalPermohonan(List<Lelongan> checkTangguhBatalPermohonan) {
        this.checkTangguhBatalPermohonan = checkTangguhBatalPermohonan;
    }

    public boolean isShowTangguhBatal() {
        return showTangguhBatal;
    }

    public void setShowTangguhBatal(boolean showTangguhBatal) {
        this.showTangguhBatal = showTangguhBatal;
    }

    public String getJenisPermohonan() {
        return jenisPermohonan;
    }

    public void setJenisPermohonan(String jenisPermohonan) {
        this.jenisPermohonan = jenisPermohonan;
    }

    public List<Lelongan> getAktifLelongan() {
        return aktifLelongan;
    }

    public void setAktifLelongan(List<Lelongan> aktifLelongan) {
        this.aktifLelongan = aktifLelongan;
    }

    public KodStatusLelongan getKodStatusLelongan() {
        return kodStatusLelongan;
    }

    public void setKodStatusLelongan(KodStatusLelongan kodStatusLelongan) {
        this.kodStatusLelongan = kodStatusLelongan;
    }

    public boolean isTangguh() {
        return tangguh;
    }

    public void setTangguh(boolean tangguh) {
        this.tangguh = tangguh;
    }

    public Lelongan getLelongan() {
        return lelongan;
    }

    public void setLelongan(Lelongan lelongan) {
        this.lelongan = lelongan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<Lelongan> getLelonganBaru() {
        return lelonganBaru;
    }

    public void setLelonganBaru(List<Lelongan> lelonganBaru) {
        this.lelonganBaru = lelonganBaru;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public List<Lelongan> getListLelongan() {
        return listLelongan;
    }

    public void setListLelongan(List<Lelongan> listLelongan) {
        this.listLelongan = listLelongan;
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

    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }

    public BigDecimal getAmaunTunggakan() {
        return amaunTunggakan;
    }

    public void setAmaunTunggakan(BigDecimal amaunTunggakan) {
        this.amaunTunggakan = amaunTunggakan;
    }

    public BigDecimal getBakiGadaian() {
        return amaunTunggakan;
    }

    public void setBakiGadaian(BigDecimal amaunTunggakan) {
        this.amaunTunggakan = amaunTunggakan;
    }

    public BigDecimal getAmaunTunggakan2() {
        return amaunTunggakan2;
    }

    public void setAmaunTunggakan2(BigDecimal amaunTunggakan2) {
        this.amaunTunggakan2 = amaunTunggakan2;
    }

    public String getIdHak() {
        return idHak;
    }

    public void setIdHak(String idHak) {
        this.idHak = idHak;
    }

    public List<Lelongan> getListLel() {
        return listLel;
    }

    public void setListLel(List<Lelongan> listLel) {
        this.listLel = listLel;
    }

    public String getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(String tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
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

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public boolean isBatal() {
        return batal;
    }

    public void setBatal(boolean batal) {
        this.batal = batal;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTarikhLelongTerdahulu() {
        return tarikhLelongTerdahulu;
    }

    public void setTarikhLelongTerdahulu(String tarikhLelongTerdahulu) {
        this.tarikhLelongTerdahulu = tarikhLelongTerdahulu;
    }

    public List<Lelongan> getListLel2() {
        return listLel2;
    }

    public void setListLel2(List<Lelongan> listLel2) {
        this.listLel2 = listLel2;
    }

    public static boolean isIS_DEBUG() {
        return IS_DEBUG;
    }

    public static void setIS_DEBUG(boolean aIS_DEBUG) {
        IS_DEBUG = aIS_DEBUG;
    }

    public List<Transaksi> getListT() {
        return listT;
    }

    public void setListT(List<Transaksi> listT) {
        this.listT = listT;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public List<PermohonanCarian> getSenaraiPermohonanCarian() {
        return senaraiPermohonanCarian;
    }

    public void setSenaraiPermohonanCarian(List<PermohonanCarian> senaraiPermohonanCarian) {
        this.senaraiPermohonanCarian = senaraiPermohonanCarian;
    }

    public List<PermohonanCarian> getSenaraiPermohonanCarian2() {
        return senaraiPermohonanCarian2;
    }

    public void setSenaraiPermohonanCarian2(List<PermohonanCarian> senaraiPermohonanCarian2) {
        this.senaraiPermohonanCarian2 = senaraiPermohonanCarian2;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public boolean isView2() {
        return view2;
    }

    public void setView2(boolean view2) {
        this.view2 = view2;
    }

    public boolean isShowRadio() {
        return showRadio;
    }

    public void setShowRadio(boolean showRadio) {
        this.showRadio = showRadio;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public String getBtn2() {
        return btn2;
    }

    public void setBtn2(String btn2) {
        this.btn2 = btn2;
    }

    public List<Lelongan> getSejarahLelongan() {
        return sejarahLelongan;
    }

    public void setSejarahLelongan(List<Lelongan> sejarahLelongan) {
        this.sejarahLelongan = sejarahLelongan;
    }

    public List<Enkuiri> getSejarahEnkuiri() {
        return sejarahEnkuiri;
    }

    public void setSejarahEnkuiri(List<Enkuiri> sejarahEnkuiri) {
        this.sejarahEnkuiri = sejarahEnkuiri;
    }

    /**
     * @return the hargaRizab
     */
    public BigDecimal getHargaRizab() {
        return hargaRizab;
    }

    /**
     * @param hargaRizab the hargaRizab to set
     */
    public void setHargaRizab(BigDecimal hargaRizab) {
        this.hargaRizab = hargaRizab;
    }

    /**
     * @return the deposit
     */
    public BigDecimal getDeposit() {
        return deposit;
    }

    /**
     * @param deposit the deposit to set
     */
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    /**
     * @return the senaraiPT
     */
    public List<Pengguna> getSenaraiPT() {
        return senaraiPT;
    }

    /**
     * @param senaraiPT the senaraiPT to set
     */
    public void setSenaraiPT(List<Pengguna> senaraiPT) {
        this.senaraiPT = senaraiPT;
    }

    /**
     * @return the idPengguna
     */
    public String getIdPengguna() {
        return idPengguna;
    }

    /**
     * @param idPengguna the idPengguna to set
     */
    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public KodStatusEnkuiriDAO getKodStatusEnkuiriDAO() {
        return kodStatusEnkuiriDAO;
    }

    public void setKodStatusEnkuiriDAO(KodStatusEnkuiriDAO kodStatusEnkuiriDAO) {
        this.kodStatusEnkuiriDAO = kodStatusEnkuiriDAO;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public LelonganDAO getLelonganDAO() {
        return lelonganDAO;
    }

    public void setLelonganDAO(LelonganDAO lelonganDAO) {
        this.lelonganDAO = lelonganDAO;
    }

    public KodStatusLelonganDAO getKodStatusLelonganDAO() {
        return kodStatusLelonganDAO;
    }

    public void setKodStatusLelonganDAO(KodStatusLelonganDAO kodStatusLelonganDAO) {
        this.kodStatusLelonganDAO = kodStatusLelonganDAO;
    }

    public LelongService getLelongService() {
        return lelongService;
    }

    public void setLelongService(LelongService lelongService) {
        this.lelongService = lelongService;
    }

    public HakmilikService getHakmilikService() {
        return hakmilikService;
    }

    public void setHakmilikService(HakmilikService hakmilikService) {
        this.hakmilikService = hakmilikService;
    }

    public KodPerananDAO getKodPeranannDAO() {
        return kodPeranannDAO;
    }

    public void setKodPeranannDAO(KodPerananDAO kodPeranannDAO) {
        this.kodPeranannDAO = kodPeranannDAO;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public CalenderManager getManager() {
        return manager;
    }

    public void setManager(CalenderManager manager) {
        this.manager = manager;
    }

    public List<PenggunaPeranan> getSenaraiPT1() {
        return senaraiPT1;
    }

    public void setSenaraiPT1(List<PenggunaPeranan> senaraiPT1) {
        this.senaraiPT1 = senaraiPT1;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public SimpleDateFormat getSdf1() {
        return sdf1;
    }

    public void setSdf1(SimpleDateFormat sdf1) {
        this.sdf1 = sdf1;
    }

    public TaskDebugService getTs() {
        return ts;
    }

    public void setTs(TaskDebugService ts) {
        this.ts = ts;
    }

}
