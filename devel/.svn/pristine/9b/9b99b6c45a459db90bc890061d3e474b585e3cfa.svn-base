/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodFlagPihakDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodWarganegaraDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakKemaskiniDAO;
import etanah.dao.PihakAlamatTambDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodWarganegara;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakHubungan;
import etanah.model.PermohonanPihakKemaskini;
import etanah.model.Pihak;
import etanah.model.PihakAlamatTamb;
import etanah.model.PihakCawangan;
import etanah.model.HakmilikWaris;
import etanah.service.BPelService;
import etanah.service.RegService;
import etanah.service.SyerValidationService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.HakmilikWarisService;
import etanah.service.common.LelongService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.common.PermohonanAtasPihakBerkepentinganService;
import etanah.service.common.PermohonanPihakHubunganService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.service.daftar.PermohonanPihakKemaskiniService;
import etanah.view.ListUtil;
import static etanah.view.daftar.PihakKepentinganAction.TODAY;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.common.PihakBerkepentinganActionBean;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math.fraction.Fraction;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.HashSet;
/*import oracle.net.aso.p;*/

/**
 *
 * @author fikri
 */
@HttpCache(allow = false)
@UrlBinding("/daftar/pihak_kepentingan")
public class PihakKepentinganAction extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PihakKepentinganAction.class);
    private static final boolean isDebug = LOG.isDebugEnabled();
    private static String SEARCH_PAGE = "daftar/carian_pihak_kepentingan.jsp";
    private static String SEARCH_PAGE_MCL = "daftar/carian_syarikat_mcl.jsp";
    private static String MAIN_PAGE = "daftar/kemasukan_pihak_main.jsp";
    private static String MAIN_PAGE_POPUP = "daftar/kemasukan_pihak_popup.jsp";
    private static String MAIN_PAGE_MCL_POPUP = "daftar/kemasukan_pihak_mcl_popup.jsp";
    private static String MAIN_PAGE_TMAMD_POPUP = "daftar/kemasukan_pihak_tmamd_popup.jsp";
    private static String MAIN_PAGE_KVST = "daftar/kemasukan_pihak_main_kaveat_tanah.jsp";
    private static String MAIN_PAGE_TUKAR = "daftar/kemasukan_pihak_main_tukar.jsp";
    private static String MAIN_PAGE_MCL = "daftar/kemasukan_pihak_mcl.jsp";
    private String idPermohonan;
    private String idHp;
    private String idMohonSebelum;
    private String idBatal;
    private String sebabPembatalan;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private boolean isBerangkai = false;
    private String idKumpulan;
    private String kodUrusan;
    private Pihak pihak;
    private String idPihak1;
    private Pemohon pemohon;
    private PermohonanPihak permohonanPihak;
    private HakmilikPihakBerkepentingan hakmilikPihak;
    private String idHakmilik;
    private Hakmilik hakmilik;
    private List<HakmilikPermohonan> senaraiHakmilikTerlibat;
    private List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan;
    private List<HakmilikPihakBerkepentingan> senaraiKeempunyaan;
    private List<HakmilikWaris> senaraiPihakWaris;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    private List<PermohonanPihak> senaraiPermohonanPihakKongsi;
    private List<PermohonanPihak> senaraiPermohonanPihakKongsiBerkumpulan;
    private ArrayList<String> uniqueId = new ArrayList<String>();
    private List<PermohonanPihak> senaraiPermohonanPihakBerangkai;
    private List<PermohonanAtasPihakBerkepentingan> senaraiPermohonanAtasPihak;
    private List<PermohonanPihakKemaskini> senaraiKemaskini;
    private List<Pemohon> senaraiPemohon;
    private List<Pihak> senaraiPihak;
    private boolean moreThanOneHakmilik = false;
    private List<PermohonanPihakHubungan> senaraiPihakHubungan;
    private List<PihakCawangan> senaraiCawangan;
    private PihakAlamatTamb pihakAlamatTamb;
    private String stageId;
    private String taskId;

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakService;
    @Inject
    RegService regService;
    @Inject
    PihakService pihakService;
    @Inject
    PihakAlamatTambDAO pihakAlamatDAO;
    @Inject
    LelongService lelongService;
    @Inject
    PemohonService pemohonService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanAtasPerserahanService permohonanAtasPerserahanService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    PermohonanAtasPihakBerkepentinganService permohonanAtasPihakService;
    @Inject
    PermohonanPihakKemaskiniService permohonanPihakKemaskiniService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanService permohonanService;
    @Inject
    ListUtil listUtil;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    @Inject
    PermohonanPihakKemaskiniDAO permohonanPihakKemaskiniDAO;
    @Inject
    KodFlagPihakDAO kodFlagPihakDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakHubunganService permohonanPihakHubunganService;
    @Inject
    SyerValidationService syerService;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    HakmilikWarisService hakmilikWarisService;
    @Inject
    PermohonanPihakKemaskiniService permohonanPihakKkiniService;
    @Inject
    DokumenService dokumenService;
    @Inject
    private etanah.Configuration conf;
    private boolean melaka = false;
    etanahActionBeanContext ctx;
    private StringBuilder error = new StringBuilder();
    static Date TODAY = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static String[] SENARAI_URUSAN_PAPAR_PIHAK_BERKEPENTINGAN = {
        //    "PNPAB",
        "JAGAB",
        "KVAK",
        "KVSK",
        "KVPK",
        "KVLK",
        "JPGPJ",
        "GDPJ",
        "GDPJK",
        "TRPA",
        "JMGPJ",
        "PMP",
        "PMPJK",
        "PMG", //    "KVSPC"
        "JDGD",
        "GDL",
        "GDPJL",
        "PJSB",
        "PJKSB",
        "ISL",
        "ISB"
    };
    private static String[] SENARAI_URUSAN_PAPAR_SEMUA_PIHAK_BERKEPENTINGAN = {
        "PNPBK",
        "PHMMK",
        "PHMMS",
        "PHMMT",
        "PHD",
        "PHMM",
        "PNKP",
        "TN",
        "TA",
        "TRPA",
        "JMGPJ",
        "JMLK",
        "JML",
        "JMGD",
        "PJTM",
        "PMKMH",
        "PH30A",
        "PH30B",
        "PPBM",
        "JDGPJ",
        "PMBUD",
        "ROSB",
        "TMAMG",
        "KVSPC"
    };
    private static String[] URUSAN_TO_VALIDATE_TUAN_TANAH = {
        "PMT",
        "PJP",
        "GDPJ",
        "GD",
        "PHADB",
        "PHADS",
        "ADBSB",
        "ADBSS"
    };
    private static String[] URUSAN_TO_REPLACE_OWNER = {
        "PMT",
        "PMP",
        "JPGD",
        "JMGD",
        "JML",
        "JDS",
        "JDGD",
        "PNPA",
        "PHMMT",
        "PMTM",
        "PNPHB",
        "JMLK",
        "JMGPJ",
        "TMAME",
        "TMAMD",
        "TMAMF",
        "TMAMG",
        "TMAML",
        "TMAMT",
        "TMAMW",
        "PHMMS",
        "PHD",
        "JPGPJ",
        "PNPHB",
        "PMKMH",
        "PHMM",
        "PHADB",
        "PHADS",
        "ADBSB",
        "TRPA"
    };
    private String[] names;
    private String[] no;
    private String[] kod;
    private String[] warga;

    /*private String checkSyer = "false";*/

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!searchPihak", "!selectPihak"})
    public void rehydrate() {

        if ("04".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            melaka = true;
        }
        ctx = (etanahActionBeanContext) getContext();

        pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) ctx.getRequest().getSession().getAttribute("idPermohonan");

        idHakmilik = ctx.getRequest().getParameter("idHakmilik");

        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService service = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task task = null;
            task = service.getTaskFromBPel(taskId, pengguna);
            if (task != null) {
                stageId = task.getSystemAttributes().getStage();
            }
        } else {
            stageId = "semak2_laporan2";
        }

        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan != null) {

                senaraiKeempunyaan = new ArrayList<HakmilikPihakBerkepentingan>();
                senaraiPihakBerkepentingan = new ArrayList<HakmilikPihakBerkepentingan>();

                kodUrusan = permohonan.getKodUrusan().getKod();
                senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
                idKumpulan = permohonan.getIdKumpulan();

                if (StringUtils.isBlank(idHakmilik)) {
                    if (senaraiHakmilikTerlibat.size() > 0) {
                        hakmilik = senaraiHakmilikTerlibat.get(0).getHakmilik();
                        idHakmilik = hakmilik.getIdHakmilik();
                    }
                } else {
                    hakmilik = hakmilikDAO.findById(idHakmilik);
                }

                boolean bukan_tuan_tanah = ArrayUtils.contains(SENARAI_URUSAN_PAPAR_PIHAK_BERKEPENTINGAN, kodUrusan);
                boolean semua_pihak = ArrayUtils.contains(SENARAI_URUSAN_PAPAR_SEMUA_PIHAK_BERKEPENTINGAN, kodUrusan);

                LOG.debug("idhakmilik =" + idHakmilik);

                if (senaraiHakmilikTerlibat.size() > 1) {
                    moreThanOneHakmilik = true;
                    ctx.getRequest().setAttribute("moreThanOneHakmilik", "true");
                }

//                if (StringUtils.isNotBlank(idKumpulan) && permohonan.getPermohonanSebelum() != null) {
                //new spoc: just use idKumpulan and kumpulan no.
                if (StringUtils.isNotBlank(idKumpulan)) {
                    isBerangkai = true;
                    ctx.getRequest().setAttribute("isBerangkai", isBerangkai);
                }

                for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                    if (hp == null || hp.getHakmilik() == null) {
                        continue;
                    }
                    Hakmilik hm = hp.getHakmilik();

                    if (!kodUrusan.equals("IS") && !kodUrusan.equals("ISBLS")) {
                        if (StringUtils.isNotBlank(idHakmilik)
                                && (!hm.getIdHakmilik().equals(idHakmilik))) {
                            continue;
                        }
                    }

                    List<HakmilikPihakBerkepentingan> _senaraiPihak = new ArrayList<HakmilikPihakBerkepentingan>();

                    if (bukan_tuan_tanah) {

                        getContext().getRequest().setAttribute("bukan_tuan_tanah", bukan_tuan_tanah);
                        getContext().getRequest().setAttribute("no_syer", "true");

                        List<String> tmp = new ArrayList<String>();

                        if (kodUrusan.equals("JPGPJ")) {

                            tmp.add("PJ");
                            tmp.add("PJK");
                            tmp.add("PM");
                            tmp.add("PA");
                            _senaraiPihak = hakmilikPihakService.doCheckUrusan(hm.getIdHakmilik(), tmp);

                        } else if (kodUrusan.equals("TRPA") || kodUrusan.equals("PNPAB")) {

//                                senaraiPermohonanAtasPihak
//                                        = permohonan.getSenaraiPermohonanAtasPihakBerkepentingan();
                            senaraiPermohonanAtasPihak = permohonanAtasPihakService.findPihakByIdMohonIdHakmilik(permohonan, hakmilik);

                            tmp.add("PA");
                            _senaraiPihak = hakmilikPihakService.doCheckUrusan(hm.getIdHakmilik(), tmp);

                        } else if (kodUrusan.equals("KVPK") || kodUrusan.equals("KVAK")
                                || kodUrusan.equals("KVSK") || kodUrusan.equals("KVLK")) {
                            getContext().getRequest().setAttribute("hide", "hide");
                            _senaraiPihak = hakmilikPihakService.findHakmilikPihakBerkepentinganByIdHakmilik(hm.getIdHakmilik(), null);

                        } else if (kodUrusan.equals("JAGAB")) {
                            tmp.add("JAG");
                            _senaraiPihak = hakmilikPihakService.findHakmilikPihakBerkepentinganByIdHakmilik(hm.getIdHakmilik(), tmp);
                        } else if (kodUrusan.equals("PMP")) {
                            tmp.add("PJ");
                            _senaraiPihak = hakmilikPihakService.findHakmilikPihakBerkepentinganByIdHakmilik(hm.getIdHakmilik(), tmp);
                        } else if (kodUrusan.equals("GDPJ")) { //added 19/09/2012
                            tmp.add("PJ");
                            _senaraiPihak = hakmilikPihakService.findHakmilikPihakBerkepentinganByIdHakmilik(hm.getIdHakmilik(), tmp);
                        } else if (kodUrusan.equals("PMPJK")) { //added 19/09/2012
                            tmp.add("PJK");
                            _senaraiPihak = hakmilikPihakService.findHakmilikPihakBerkepentinganByIdHakmilik(hm.getIdHakmilik(), tmp);
                        } else if (kodUrusan.equals("PMG") || kodUrusan.equals("PNPBK")) {
                            tmp.add("PG");
                            _senaraiPihak = hakmilikPihakService.findHakmilikPihakBerkepentinganByIdHakmilik2(hm.getIdHakmilik(), tmp);

                        } else if (kodUrusan.equals("JDGD")) {
                            tmp.add("PG");
                            tmp.add("PJ");
                            _senaraiPihak = hakmilikPihakService.doCheckUrusan(hm.getIdHakmilik(), tmp);

                        } else {

                            List<PermohonanHubungan> senaraiHbgn = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), hm.getIdHakmilik());

                            if (senaraiHbgn.isEmpty()) {
//                                    addSimpleError("Sila pilih urusan terlibat di tab Maklumat Urusan.");
//                                    break;
                            } else {

                                List<HakmilikPihakBerkepentingan> senaraiHU = new ArrayList<HakmilikPihakBerkepentingan>();

                                for (PermohonanHubungan ph : senaraiHbgn) {
                                    if (ph == null) {
                                        continue;
                                    }
                                    HakmilikUrusan hu = hakmilikUrusanService.findByIdPerserahanIdHakmilik(ph.getPermohonanSasaran().getIdPermohonan(),
                                            hm.getIdHakmilik());
                                    if (hu == null) {
                                        continue;
                                    }

                                    senaraiHU = hakmilikPihakService.findHakmilikPihakByIdUrusan(hu, hm);

                                    if (senaraiHU.isEmpty() && kodUrusan.equals("GDL")) {
                                        List<HakmilikPihakBerkepentingan> senarai = hakmilikPihakService.findAllHakmilikPihakByIdUrusan(hu, hm);

                                        for (HakmilikPihakBerkepentingan hpk : senarai) {
                                            if (hpk.getPerserahanBatal() != null) {
                                                HakmilikUrusan urusanBatal = hpk.getPerserahanBatal();
                                                List<HakmilikPihakBerkepentingan> senaraiBaru = hakmilikPihakService.findHakmilikPihakByIdUrusan(urusanBatal, hm);
                                                for (HakmilikPihakBerkepentingan h : senaraiBaru) {
                                                    _senaraiPihak.add(h);
                                                }
                                            }
                                        }
                                    }

                                    for (HakmilikPihakBerkepentingan hpk : senaraiHU) {
                                        _senaraiPihak.add(hpk);
                                    }

                                }
                            }
                        }

                    } else if (semua_pihak) {
                        _senaraiPihak = hakmilikPihakService.findHakmilikAllPihakActiveByHakmilik(hm);
                    } else {
                        _senaraiPihak = hakmilikPihakService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hm);
                    }

                    //berangkai akan ambil permohonan sebelum punya mohon pihak
                    if (isBerangkai) {

//                        senaraiPermohonanPihakBerangkai = permohonanPihakService.
//                                getSenaraiTuanTanahForBerangkai( idKumpulan ,
//                                idPermohonan, idHakmilik, bukan_tuan_tanah,semua_pihak, permohonan.getKumpulanNo() );
//
//                        //jika terdapat 2 tuan tanah, hanya satu tuan saja terllibat, satu lg tidak terlibat
//                        //dalam urusan, so kena keluarkan juga yang tak terlibat utk urusan seterusnya.
//                        //sbg contoh PMT-GD so, dalam PMT hanya satu tuan saja terlibat dalam pindah milik tanah
//                        // so dalam urusan GD, kena keluarkan tuan tanah baru + tuan tanah lama yg tidak terlibat
                        List<HakmilikPihakBerkepentingan> senaraiPihakLama
                                = hakmilikPihakService.senaraiPBtidakBerkaitan(idHakmilik, idKumpulan,
                                        idPermohonan, bukan_tuan_tanah, semua_pihak);
//
                        List<PermohonanHubungan> senaraiHubungan = new ArrayList<PermohonanHubungan>();

                        List<Permohonan> senaraiPermohonanSeblm = permohonanService.getPermohonanSebelum(idKumpulan, permohonan.getKumpulanNo());

                        List<PermohonanPihak> senaraiPermohonanPihakBerangkaiRemove = new ArrayList<PermohonanPihak>();
                        List<PermohonanPihak> senaraiPermohonanPihakBerangkaiDEL = new ArrayList<PermohonanPihak>();

                        for (Permohonan ps : senaraiPermohonanSeblm) {
                            if (ps == null) {
                                continue;
                            }
                            List<PermohonanHubungan> tmp = permohonanService.getSenaraiHubungan(ps.getIdPermohonan(), idHakmilik);
                            for (PermohonanHubungan ph : tmp) {
                                senaraiHubungan.add(ph);
                            }
                        }
//
//
                        for (HakmilikPihakBerkepentingan hpb : senaraiPihakLama) {

                            PermohonanPihak pp = new PermohonanPihak();
                            pp.setPihak(hpb.getPihak());
                            pp.setSyerPembilang(hpb.getSyerPembilang() == null ? 1 : hpb.getSyerPembilang());
                            pp.setSyerPenyebut(hpb.getSyerPenyebut() == null ? 1 : hpb.getSyerPenyebut());
                            pp.setHakmilik(hpb.getHakmilik());
                            pp.setJenis(hpb.getJenis());
                            pp.setNama(hpb.getNama());
                            pp.setNoRujukan("Y");//todo: differ from hakmilikPihak and mohonPihak
                            senaraiPermohonanPihakBerangkaiRemove.add(pp);
                        }
//
//                        //fixme : workaround
//                        if (senaraiPermohonanPihakBerangkai.isEmpty()) {
//                            for (HakmilikPihakBerkepentingan hpb : _senaraiPihak) {
//                                if (hpb == null || hpb.getPihak() == null) continue;
//                                PermohonanPihak pp = new PermohonanPihak();
//                                pp.setPihak(hpb.getPihak());
//                                if (hpb.getSyerPembilang() != null)pp.setSyerPembilang(hpb.getSyerPembilang());
//                                if (hpb.getSyerPenyebut() != null)pp.setSyerPenyebut(hpb.getSyerPenyebut());
//                                pp.setHakmilik(hpb.getHakmilik());
//                                pp.setJenis(hpb.getJenis());
//                                senaraiPermohonanPihakBerangkai.add(pp);
//                            }
//                        }
// update untuk semua urusan yang berkaitan berangkai dan remove pemilik lama base on pemohon dan pemilik lama
                        senaraiPermohonanPihakBerangkai = permohonanPihakService.senaraiTuanTanahBerkumpulan(idKumpulan,
                                idPermohonan, idHakmilik, bukan_tuan_tanah, semua_pihak, permohonan.getKumpulanNo());
//                        for (PermohonanPihak mohonPihak : senaraiPermohonanPihakBerangkai) {
//                            for (PermohonanPihak mohonPihak2 : senaraiPermohonanPihakBerangkaiRemove) {
//                                if (mohonPihak.getNama().equals(mohonPihak2.getNama())) {
//                                    senaraiPermohonanPihakBerangkaiDEL.add(mohonPihak);
//                                }
//                            }
//                        }
//                        senaraiPermohonanPihakBerangkai.removeAll(senaraiPermohonanPihakBerangkaiDEL);
//                        senaraiPermohonanPihakBerangkai.removeAll(senaraiPermohonanPihakBerangkaiRemove);
                        //temp solution
                        if ("PNPAB".equals(kodUrusan)) {
                            List<PermohonanPihak> senaraiTemp = new ArrayList<PermohonanPihak>();
                            for (PermohonanPihak pp : senaraiPermohonanPihakBerangkai) {
                                if (pp == null) {
                                    continue;
                                }
                                if ("PA".equals(pp.getJenis().getKod())) {
                                    senaraiTemp.add(pp);
                                }
                            }
                            if (!senaraiTemp.isEmpty()) {
                                senaraiPermohonanPihakBerangkai = senaraiTemp;
                            }
                        }

                        //utk urusan yg menghidupkan pemilik lama contohnya PMTB
                        if (!senaraiHubungan.isEmpty()) {

                            for (Permohonan p : senaraiPermohonanSeblm) {
                                if ("PMTB".equals(p.getKodUrusan().getKod())) {
                                    for (PermohonanHubungan ph : senaraiHubungan) {
                                        if (ph.getPermohonanSumber().getIdPermohonan()
                                                .equals(p.getIdPermohonan())) {
                                            if (ph.getPermohonanSasaran() != null) {
                                                Permohonan pmhn = ph.getPermohonanSasaran();
                                                List<PermohonanPihak> s = pmhn.getSenaraiPihak();
                                                List<PermohonanPihak> tmp = new ArrayList<PermohonanPihak>();
                                                for (PermohonanPihak pp : s) {
                                                    for (PermohonanPihak pp2 : senaraiPermohonanPihakBerangkai) {
                                                        if (pp2.getPihak().equals(pp.getPihak())
                                                                && pp2.getHakmilik().equals(pp.getHakmilik())
                                                                && pp2.getJenis().equals(pp.getJenis())) {
                                                            continue;
                                                        }
                                                        tmp.add(pp2);
                                                    }
                                                }
                                                senaraiPermohonanPihakBerangkai = tmp;
                                                List<Pemohon> s2 = pmhn.getSenaraiPemohon();
                                                for (Pemohon pm : s2) {
                                                    PermohonanPihak pp = new PermohonanPihak();
                                                    pp.setPihak(pm.getPihak());
                                                    pp.setSyerPembilang(pm.getSyerPembilang() == null ? 1 : pm.getSyerPembilang());
                                                    pp.setSyerPenyebut(pm.getSyerPenyebut() == null ? 1 : pm.getSyerPenyebut());
                                                    pp.setHakmilik(pm.getHakmilik());
                                                    pp.setJenis(pm.getJenis());
                                                    pp.setNoRujukan("Y");//todo: differ from hakmilikPihak and mohonPihak
                                                    senaraiPermohonanPihakBerangkai.add(pp);

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        //utk senarai pertukaran nama, no kp, jenis kp
                        List<Permohonan> senaraiPermohonan = permohonanService.getPermohonanByIdKump(idKumpulan);
                        for (Permohonan p : senaraiPermohonan) {
                            if (p == null || p.getKumpulanNo() >= permohonan.getKumpulanNo()) {
                                continue;
                            }
                            List<PermohonanPihakKemaskini> senaraiKK = p.getSenaraiPihakKemaskini();
                            for (PermohonanPihak pp : senaraiPermohonanPihakBerangkai) {
                                for (PermohonanPihakKemaskini kk : senaraiKK) {
                                    if (kk.getPemohon() == null) {
                                        continue;
                                    }
                                    Pemohon pemhn = kk.getPemohon();
                                    if (pp.getPihak().getIdPihak() == pemhn.getPihak().getIdPihak()
                                            && pemhn.getHakmilik().getIdHakmilik().equals(pp.getHakmilik().getIdHakmilik())) {
                                        if (kk.getNamaMedan().equals("nama")) {
                                            pp.getPihak().setNama(kk.getNilaiBaru());
                                        }
                                        if (kk.getNamaMedan().equals("nokp")) {
                                            pp.getPihak().setNoPengenalan(kk.getNilaiBaru());
                                        }
                                        if (kk.getNamaMedan().equals("jeniskp")) {
                                            pp.getPihak().setJenisPengenalan(kodJenisPengenalanDAO.findById(kk.getNilaiBaru()));
                                        }
                                        if (kk.getNamaMedan().equals("kodWarganegara")) {
                                            pp.getPihak().setWargaNegara(kodWarganegaraDAO.findById(kk.getNilaiBaru()));
                                        }
                                    }
                                }
                            }
                        }

                    } else {
                        for (HakmilikPihakBerkepentingan hpkb : _senaraiPihak) {
                            if (hpkb == null || hpkb.getPihak() == null) {
                                continue;
                            }
                            senaraiKeempunyaan.add(hpkb);
                        }
                    }

                    _senaraiPihak = hakmilikPihakService.findHakmilikPihakBerkepentinganByIdHakmilik2(hm.getIdHakmilik(), null);

                    if (kodUrusan.equals("KVPT") || kodUrusan.equals("GDL")) { // tambah GDL kt sini 26/7/2016
                        for (HakmilikPihakBerkepentingan hpkb : _senaraiPihak) {
                            if (hpkb == null || hpkb.getPihak() == null) {
                                continue;
                            }
                            senaraiKeempunyaan.add(hpkb);
                        }
                    }

                    for (HakmilikPihakBerkepentingan hpkb : _senaraiPihak) {
                        if (hpkb == null || hpkb.getPihak() == null) {
                            continue;
                        }
                        senaraiPihakBerkepentingan.add(hpkb);
                    }
                }

                if (kodUrusan.equals("IS") || kodUrusan.equals("ISBLS")) {
                    senaraiPermohonanPihak = permohonan.getSenaraiPihak();
                    senaraiPemohon = permohonan.getSenaraiPemohon();
                } else if (kodUrusan.startsWith("SM")) {
                    if (kodUrusan.equalsIgnoreCase("SMBT")) {

                        LOG.info(":::::idPermohonan SMBT::::::" + permohonan.getIdPermohonan());
                        senaraiPermohonanPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(idPermohonan);

                        if (senaraiPermohonanPihak.size() == 0) {

                            Dokumen dokumen = dokumenService.findDok(idPermohonan, "SMB");

                            if (dokumen != null) {
                                List<PermohonanPihak> senaraiPerPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(dokumen.getNoRujukan());

                                for (PermohonanPihak perPihak : senaraiPerPihak) {
                                    PermohonanPihak perPihakSave = new PermohonanPihak();
                                    perPihakSave.setPermohonan(permohonan);
                                    perPihakSave.setCawangan(perPihak.getCawangan());
                                    perPihakSave.setPihak(perPihak.getPihak());
                                    perPihakSave.setJenis(perPihak.getJenis());
                                    perPihakSave.setInfoAudit(perPihak.getInfoAudit());
                                    permohonanPihakService.saveOrUpdate(perPihakSave);
                                }
                            }
                            senaraiPermohonanPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(idPermohonan);
                        }

                        if (permohonan.getSebab() != null) {
                            sebabPembatalan = permohonan.getSebab();
                        }
                    }
                    if (kodUrusan.equalsIgnoreCase("SMK")) {

                        LOG.info(":::::idPermohonan SMK::::::" + permohonan.getIdPermohonan());
                        senaraiPermohonanPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(idPermohonan);

                        if (senaraiPermohonanPihak.size() == 0) {

                            Dokumen dokumen = dokumenService.findDok(idPermohonan, "SMB");

                            if (dokumen != null) {
                                List<PermohonanPihak> senaraiPerPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(dokumen.getNoRujukan());

                                for (PermohonanPihak perPihak : senaraiPerPihak) {
                                    PermohonanPihak perPihakSave = new PermohonanPihak();
                                    perPihakSave.setPermohonan(permohonan);
                                    perPihakSave.setCawangan(perPihak.getCawangan());
                                    perPihakSave.setPihak(perPihak.getPihak());
                                    perPihakSave.setJenis(perPihak.getJenis());
                                    perPihakSave.setInfoAudit(perPihak.getInfoAudit());
                                    permohonanPihakService.saveOrUpdate(perPihakSave);
                                }
                            }
                            senaraiPermohonanPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(idPermohonan);
                        }
                    }
                    if (kodUrusan.equalsIgnoreCase("SMB")) {

                        LOG.info(":::::idPermohonan SMB::::::" + permohonan.getIdPermohonan());
                        senaraiPermohonanPihak = permohonan.getSenaraiPihak();

                    }
                } else if (StringUtils.isNotBlank(idHakmilik)) {
                    senaraiPermohonanPihak = permohonanPihakService.getSenaraiMohonPihakForMultipleHakmilik(idPermohonan, idHakmilik);
                    senaraiPemohon = pemohonService.senaraiPemohonByIdPermohonanIdHakmilik(idPermohonan, idHakmilik);
                } else {
                    senaraiPermohonanPihak = permohonan.getSenaraiPihak();
                    senaraiPemohon = permohonan.getSenaraiPemohon();
                }

            } else {
                if (error.length() > 0) {
                    error.append("\n");
                }
                error.append("Perserahan tidak dikenali. Sila hubungi IT Helpdesk untuk tindakan selanjutnya.");
            }

            if (error.length() > 0) {
                addSimpleError(error.toString());
            }
        }
    }

    private List validatePihakBerkepentingan(List senarai) {

        List<HakmilikPermohonan> senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();

        List senaraiPihakTerlibat = new ArrayList();

        for (Object object : senarai) {

            boolean flag = true;

            if (object instanceof HakmilikPihakBerkepentingan) {

                HakmilikPihakBerkepentingan hpk = (HakmilikPihakBerkepentingan) object;

                for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {

                    if (hp == null) {
                        continue;
                    }

                    HakmilikPihakBerkepentingan hpkb = hakmilikPihakService.findHakmilikPihakByIdPihak(hpk.getPihak(), hp.getHakmilik());
                    if (hpkb == null) {
                        flag = false;
                        break;
                    }

//                    List<HakmilikPihakBerkepentingan> senarai2 = hp.getHakmilik().getSenaraiPihakBerkepentingan();
//                    for (HakmilikPihakBerkepentingan hpkb : senarai2) {
//                        if (hpkb == null) continue;
//                        Pihak p1 = hpkb.getPihak();
//                        Pihak p2 = hpk.getPihak();
//                        if ( !p1.getNama().equalsIgnoreCase(p2.getNama())
//                                && !p1.getNoPengenalan().equals(p2.getNoPengenalan())
//                                && !p1.getJenisPengenalan().getKod().equals(p2.getJenisPengenalan().getKod())) {
//                            flag = false;
//                            break;
//                        }
//                    }
                }

                if (flag) {
                    senaraiPihakTerlibat.add(hpk);
                }

            } else if (object instanceof PermohonanPihak) {

                PermohonanPihak pp = (PermohonanPihak) object;

                for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {

                    if (hp == null) {
                        continue;
                    }

                    List<PermohonanPihak> senaraiPP = permohonanPihakService.getSenaraiPermohonanPihak(idKumpulan, pp.getPihak().getIdPihak(), hp.getHakmilik().getIdHakmilik());

                    if (senaraiPP.isEmpty()) {

                        HakmilikPihakBerkepentingan hpkb = hakmilikPihakService.findHakmilikPihakByIdPihak(pp.getPihak(), hp.getHakmilik());

                        if (hpkb == null) {
                            flag = false;
                            break;
                        }
//                        List<HakmilikPihakBerkepentingan> senarai2 = hp.getHakmilik().getSenaraiPihakBerkepentingan();
//                        for (HakmilikPihakBerkepentingan hpkb : senarai2) {
//                            if (hpkb == null) continue;
//                            Pihak p1 = hpkb.getPihak();
//                            Pihak p2 = pp.getPihak();
//                            if ( !p1.getNama().equalsIgnoreCase(p2.getNama())
//                                    && !p1.getNoPengenalan().equals(p2.getNoPengenalan())
//                                    && !p1.getJenisPengenalan().getKod().equals(p2.getJenisPengenalan().getKod())) {
//                                flag = false;
//                                break;
//                            }
//                        }
                    }
//                    else {
//                        for (PermohonanPihak ppihak : senaraiPP) {
//                            Pihak p1 = ppihak.getPihak();
//                            Pihak p2 = pp.getPihak();
//                            if ( !p1.getNama().equalsIgnoreCase(p2.getNama())
//                                    && !p1.getNoPengenalan().equals(p2.getNoPengenalan())
//                                    && !p1.getJenisPengenalan().getKod().equals(p2.getJenisPengenalan().getKod())) {
//                                flag = false;
//                                break;
//                            }
//
//                        }
//                    }
                }

                if (flag) {
                    senaraiPihakTerlibat.add(pp);
                }
            }
        }

        return senaraiPihakTerlibat;
    }

    @DefaultHandler
    public Resolution showForm() {

        /*idPermohonan = (String) ctx.getRequest().getSession().getAttribute("idPermohonan");*/
        if (ArrayUtils.contains(URUSAN_TO_VALIDATE_TUAN_TANAH, kodUrusan)) {
            /*permohonan = permohonanDAO.findById(idPermohonan);
            checkSyer(permohonan);*/
            if (senaraiPermohonanPihakBerangkai != null) {

//                senaraiPermohonanPihakBerangkai = validatePihakBerkepentingan(senaraiPermohonanPihakBerangkai);
                if (senaraiPermohonanPihakBerangkai.isEmpty()) {
                    addSimpleError("Sila pastikan tuan tanah adalah dari pihak yang SAMA.");
                } else {
                    ctx.getRequest().setAttribute("edit", Boolean.TRUE);
                }

            } else if (senaraiKeempunyaan != null) {

//                senaraiKeempunyaan = validatePihakBerkepentingan(senaraiKeempunyaan);
                if (senaraiKeempunyaan.isEmpty()) {
                    addSimpleError("Sila pastikan tuan tanah adalah dari pihak yang SAMA.");
                } else {
                    ctx.getRequest().setAttribute("edit", Boolean.TRUE);
                }
            }
        } else {
            ctx.getRequest().setAttribute("edit", Boolean.TRUE);
        }
        /*if (checkSyer.equals("true")){
            addSimpleError("Sila Betulkan Syer Untuk Hakmilik Terlibat Kerana Terdapat Kesalahan Pada Syer");
        }*/

        return new JSP(MAIN_PAGE).addParameter("tab", Boolean.TRUE);
    }

    //Added by Aizuddin for MCL
    /*public void checkSyer(Permohonan permohonan) {

        List<HakmilikPermohonan> senaraiPermohonan = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : senaraiPermohonan) {
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            int r = 0;
            try {
                r = syerService.validateSyerBaru(permohonan, hm);
                if (r != 0) {
                    checkSyer = "true";
                }
            } catch (Exception e) {

            }
        }
    }*/

    //Added by Aizuddin for MCL
    public Resolution showFormMCL() {
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SMB") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("SMK")) {
            ctx.getRequest().setAttribute("edit", Boolean.TRUE);
        } else {
            ctx.getRequest().setAttribute("edit", Boolean.FALSE);
        }
        return new JSP(MAIN_PAGE_MCL).addParameter("tab", Boolean.TRUE);
    }

    //Added by Aizuddin for MCL
    public Resolution cariPihakMCL() {
        //First checking Syarikat MCL
        LOG.info("%%%%%%%%Masuk Syarikat SM Kemaskini%%%%%%%%%%%");

        idMohonSebelum = getContext().getRequest().getParameter("idMohonSebelum");
        LOG.info("%%%%No Syarikat SM Dikemaskini%%%% " + idMohonSebelum);

        if (!(idMohonSebelum.isEmpty())) {
            //Mesti Syarikat MCL yang belum berdaftar
            Permohonan mohonTemp = new Permohonan();
            boolean valid = false;
            mohonTemp = permohonanService.findSyarikatSMDaftar(idMohonSebelum);
            if (mohonTemp == null) {
                valid = true;
            } else {
                addSimpleError("Syarikat MCL sudah didaftarkan, tidak boleh dikemaskini");
            }

            //Check pihak Syarikat MCL berdaftar TODO
            List<PermohonanPihak> listPihak = permohonanPihakService.getSenaraiPmohonPihak(idMohonSebelum);
            if (listPihak != null) {
                for (PermohonanPihak pp : listPihak) {
                    Pihak pihakTemp = new Pihak();
                    Long idPihak = pp.getPihak().getIdPihak();
                    pihakTemp = pihakService.findSyarikatMCLAktifByIdPihak(idPihak);
                    if (pihakTemp != null) {
                        valid = true;
                    } else {
                        valid = false;
                        addSimpleError("Syarikat MCL tidak aktif!");
                    }
                }
            } else {
                addSimpleError("Tiada Syarikat MCL didaftarkan");
            }

            if (valid) {
                //permohonan = mohonTemp bru ley pki
                Permohonan ptemp = permohonanDAO.findById(permohonan.getIdPermohonan());
                Permohonan mohon = permohonanDAO.findById(idMohonSebelum);
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(TODAY);
                ptemp.setInfoAudit(ia);
                ptemp.setPermohonanSebelum(mohon);
                permohonanService.saveOrUpdate(ptemp);
                addSimpleMessage("Syarikat MCL Boleh Dikemaskini");
            }
        } else {
            addSimpleError("Sila Masukkan No Syarikat MCL");
        }

        if (kodUrusan.equalsIgnoreCase("SMBT")) {
            if (StringUtils.isNotBlank(sebabPembatalan)) {
                Permohonan mohon = permohonan;
                mohon.setSebab(sebabPembatalan);
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(TODAY);
                mohon.setInfoAudit(ia);
                permohonanService.saveOrUpdate(mohon);
            }
        }

        rehydrate();
        ctx.getRequest().setAttribute("edit", Boolean.TRUE);
        LOG.info("%%%%%%%%Finish Masuk Syarikat SM Kemaskini%%%%%%%%%%%");

        return new JSP(MAIN_PAGE_MCL).addParameter("tab", Boolean.TRUE);
    }

    //Added by Aizuddin for MCL
    public Resolution batalPihakMCL() {
        //First checking Syarikat MCL
        LOG.info("%%%%%%%Masuk Syarikat SM Batal%%%%%%%%%%%");
//        idBatal = getContext().getRequest().getParameter("idMohonSebelum");

        Dokumen dokumen = dokumenService.findDok(idPermohonan, "SMB");

        if (dokumen != null) {
            idBatal = dokumen.getNoRujukan();

            LOG.info("%%%%No Syarikat SM Dibatalkan%%%% " + idBatal);

            if (!(idBatal.isEmpty())) {
                //Mesti Syarikat MCL yang belum berdaftar
                Permohonan mohonTemp = new Permohonan();
                boolean valid = false;
                mohonTemp = permohonanService.findSyarikatSMDaftar(idBatal);
                if (mohonTemp == null) {
                    valid = true;
                } else {
                    addSimpleError("Syarikat MCL sudah didaftarkan, tidak boleh dikemaskini");
                }

                //Check pihak Syarikat MCL berdaftar TODO
                List<PermohonanPihak> listPihak = permohonanPihakService.getSenaraiPmohonPihak(idBatal);
                if (listPihak != null) {
                    for (PermohonanPihak pp : listPihak) {
                        Pihak pihakTemp = new Pihak();
                        Long idPihak = pp.getPihak().getIdPihak();
                        pihakTemp = pihakService.findSyarikatMCLAktifByIdPihak(idPihak);
                        if (pihakTemp != null) {
                            valid = true;
                        } else {
                            valid = false;
                            addSimpleError("Syarikat MCL tidak aktif!");
                        }

                    }
                } else {
                    addSimpleError("Tiada Syarikat MCL didaftarkan");
                }

                if (valid) {
                    //Save table mohon
                    Permohonan mohon = permohonanDAO.findById(permohonan.getIdPermohonan());
                    Permohonan mohonBatal = permohonanDAO.findById(idBatal);
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(TODAY);
                    if (StringUtils.isNotBlank(sebabPembatalan)) {
                        mohon.setSebab(sebabPembatalan);
                    }
                    mohon.setInfoAudit(ia);
                    permohonanService.saveOrUpdate(mohon);

                    //Save table mohon_atas_urusan
                    PermohonanAtasPerserahan pap = new PermohonanAtasPerserahan();
                    pap.setPermohonan(mohon);
                    pap.setPermohonanBaru(mohonBatal);
                    pap.setInfoAudit(ia);
                    permohonanAtasPerserahanService.save(pap);
                    addSimpleMessage("Syarikat MCL Boleh Dibatalkan");
                }
            } else {
                addSimpleError("Sila Masukkan No Syarikat MCL");
            }
        } else {
            Permohonan permohonanSebelum = permohonan.getPermohonanSebelum();

            LOG.info("%%%%No Syarikat SM Dibatalkan%%%% " + idBatal);

            if (permohonanSebelum != null) {
                idBatal = permohonanSebelum.getIdPermohonan();
                Permohonan mohonTemp = new Permohonan();
                boolean valid = false;
                mohonTemp = permohonanService.findSyarikatSMDaftar(idBatal);
                if (mohonTemp == null) {
                    valid = true;
                } else {
                    addSimpleError("Syarikat MCL sudah didaftarkan, tidak boleh dikemaskini");
                }

                //Check pihak Syarikat MCL berdaftar TODO
                List<PermohonanPihak> listPihak = permohonanPihakService.getSenaraiPmohonPihak(idBatal);
                if (listPihak != null) {
                    for (PermohonanPihak pp : listPihak) {
                        Pihak pihakTemp = new Pihak();
                        Long idPihak = pp.getPihak().getIdPihak();
                        pihakTemp = pihakService.findSyarikatMCLAktifByIdPihak(idPihak);
                        if (pihakTemp != null) {
                            valid = true;
                        } else {
                            valid = false;
                            addSimpleError("Syarikat MCL tidak aktif!");
                        }

                    }
                } else {
                    addSimpleError("Tiada Syarikat MCL didaftarkan");
                }

                if (valid) {
                    //Save table mohon
                    Permohonan mohon = permohonanDAO.findById(permohonan.getIdPermohonan());
                    Permohonan mohonBatal = permohonanDAO.findById(idBatal);
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(TODAY);
                    if (StringUtils.isNotBlank(sebabPembatalan)) {
                        mohon.setSebab(sebabPembatalan);
                    }
                    mohon.setInfoAudit(ia);
                    permohonanService.saveOrUpdate(mohon);

                    //Save table mohon_atas_urusan
                    PermohonanAtasPerserahan pap = new PermohonanAtasPerserahan();
                    pap.setPermohonan(mohon);
                    pap.setPermohonanBaru(mohonBatal);
                    pap.setInfoAudit(ia);
                    permohonanAtasPerserahanService.save(pap);
                    addSimpleMessage("Syarikat MCL Boleh Dibatalkan");
                }
            } else {
                addSimpleError("Sila Masukkan No Syarikat MCL");
            }
        }
        if (kodUrusan.equalsIgnoreCase("SMBT")) {
        }

        rehydrate();
        ctx.getRequest().setAttribute("edit", Boolean.TRUE);
        LOG.info("%%%%%Finish Masuk Syarikat SM Batal%%%%%%%%%%%");

        return new JSP(MAIN_PAGE_MCL).addParameter("tab", Boolean.TRUE);
    }

    public Resolution showFormKVST() {

        ctx.getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP(MAIN_PAGE_KVST).addParameter("tab", Boolean.TRUE);
    }

    public Resolution showFormTukar() {

        ctx.getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP(MAIN_PAGE_TUKAR).addParameter("tab", Boolean.TRUE);
    }

    public Resolution searchPihak() {
        ctx = (etanahActionBeanContext) getContext();
        String doSearch = ctx.getRequest().getParameter("doSearch");

        if (StringUtils.isNotBlank(doSearch)) {
            senaraiPihak = pihakService.findAll(ctx.getRequest().getParameterMap());
            if (senaraiPihak.isEmpty()) {
                getContext().getRequest().setAttribute("addNewPihak", "true");
            }
        }
        return new JSP(SEARCH_PAGE).addParameter("popup", Boolean.TRUE);
    }

    //Added by Aizuddin to search syarikat mcl only
    public Resolution searchPihakMCL() {
        ctx = (etanahActionBeanContext) getContext();
        String doSearch = ctx.getRequest().getParameter("doSearch");

        if (StringUtils.isNotBlank(doSearch)) {
            senaraiPihak = pihakService.findAll(ctx.getRequest().getParameterMap());
            if (senaraiPihak.isEmpty()) {
                getContext().getRequest().setAttribute("addNewPihakMCL", "true");
            }
        }
        return new JSP(SEARCH_PAGE_MCL).addParameter("popup", Boolean.TRUE);
    }

    public Resolution showSearchForm() {
        pihak = new Pihak();

//    if (kodUrusan.equals("PNPAB")) {
//      return new RedirectResolution(PemohonActionBean.class, "showFormPopup").addParameter("popup", Boolean.TRUE).addParameter("idHakmilik", idHakmilik);
//    } else {
//      
//    }
        return new JSP(SEARCH_PAGE).addParameter("popup", Boolean.TRUE);
    }

    //Added by Aizuddin for search Syarikat MCL
    public Resolution showSearchFormMCL() {
        pihak = new Pihak();
        return new JSP(SEARCH_PAGE_MCL).addParameter("popup", Boolean.TRUE);
    }

    public Resolution selectPihak() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) ctx.getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
            if (senaraiHakmilikTerlibat.size() > 1) {
                ctx.getRequest().setAttribute("moreThanOneHakmilik", "true");
            }
            kodUrusan = permohonan.getKodUrusan().getKod();
            if (kodUrusan.equals("TRPA")) {
                ctx.getRequest().setAttribute("disabledWaris", "true");
            } else {
                ctx.getRequest().setAttribute("disabledWaris", "false");
            }
        }

        String idPihak = ctx.getRequest().getParameter("idPihak");
        String jenisPihak = ctx.getRequest().getParameter("jenisPihak");

        pihak = pihakService.findById(idPihak);
        if (pihak != null && StringUtils.isBlank(pihak.getAlamat1())
                && StringUtils.isBlank(pihak.getAlamat2())
                && StringUtils.isBlank(pihak.getAlamat3())
                && StringUtils.isBlank(pihak.getAlamat4())
                && StringUtils.isBlank(pihak.getPoskod())
                && pihak.getNegeri() == null) {
            ctx.getRequest().setAttribute("newPihak", "true");
        }

        if (StringUtils.isNotBlank(jenisPihak)
                && jenisPihak.equals("PA")) {

            senaraiPihakHubungan = new ArrayList<PermohonanPihakHubungan>();
            List<PermohonanPihak> list = permohonan.getSenaraiPihak();
            for (PermohonanPihak pp : list) {
                if (pp == null && pp.getSenaraiHubungan().isEmpty()) {
                    continue;
                }
                List<PermohonanPihakHubungan> tmp = pp.getSenaraiHubungan();
                for (PermohonanPihakHubungan pph : tmp) {
                    if (pph == null) {
                        continue;
                    }
                    senaraiPihakHubungan.add(pph);
                }
            }
        }

        if (permohonan != null) {
            FolderDokumen fd = permohonan.getFolderDokumen();
            if (fd != null) {
                List<KandunganFolder> skf = fd.getSenaraiKandungan();
                if (!skf.isEmpty()) {
                    for (KandunganFolder kf : skf) {
                        if (kf == null) {
                            continue;
                        }
                        Dokumen d = kf.getDokumen();
                        if (d == null) {
                            continue;
                        }
                        if (d.getKodDokumen().getKod().equals("SAB") || d.getKodDokumen().getKod().equals("SAD")) {
                            getContext().getRequest().setAttribute("SAB", "true");
                            if (permohonanPihak != null) {
                                permohonanPihak.setDalamanNilai1(d.getNoRujukan());
                                break;
                            }
                        }
                    }
                }
            }
        }

        senaraiCawangan = pihak.getSenaraiCawangan();
        List<PihakAlamatTamb> senaraiAlamatTamb = pihak.getSenaraiAlamatTamb();
        if (senaraiAlamatTamb.isEmpty()) {
            pihakAlamatTamb = new PihakAlamatTamb();
            pihakAlamatTamb.setAlamatKetiga1(StringUtils.isNotBlank(pihak.getSuratAlamat1()) ? pihak.getSuratAlamat1() : "");
            pihakAlamatTamb.setAlamatKetiga2(StringUtils.isNotBlank(pihak.getSuratAlamat2()) ? pihak.getSuratAlamat2() : "");
            pihakAlamatTamb.setAlamatKetiga3(StringUtils.isNotBlank(pihak.getSuratAlamat3()) ? pihak.getSuratAlamat3() : "");
            pihakAlamatTamb.setAlamatKetiga4(StringUtils.isNotBlank(pihak.getSuratAlamat4()) ? pihak.getSuratAlamat4() : "");
            pihakAlamatTamb.setAlamatKetigaPoskod(StringUtils.isNotBlank(pihak.getSuratPoskod()) ? pihak.getSuratPoskod() : "");
            pihakAlamatTamb.setAlamatKetigaNegeri(pihak.getSuratNegeri() != null ? pihak.getSuratNegeri() : null);
            pihakAlamatTamb.setPihak(pihak);
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(TODAY);
            pihakAlamatTamb.setInfoAudit(infoAudit);
            lelongService.saveOrUpdate(pihakAlamatTamb);
        } else {
            pihakAlamatTamb = senaraiAlamatTamb.get(0);
        }

        //syer?
        Fraction syerSemuaPemohon = Fraction.ZERO;
        Fraction syerSemuaPermohonanPihak = Fraction.ZERO;

        if (permohonan != null) {
            senaraiPemohon = permohonan.getSenaraiPemohon();
            senaraiPermohonanPihak = permohonan.getSenaraiPihak();
            for (Pemohon p : senaraiPemohon) {
                if (p.getSyerPenyebut() == null) {
                    continue;
                }
                if (p.getSyerPenyebut() == 0) {
                    continue;
                }
                if (StringUtils.isNotBlank(idHakmilik)
                        && p.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                    syerSemuaPemohon = syerSemuaPemohon.add(new Fraction(p.getSyerPembilang(), p.getSyerPenyebut()).abs());

                }
            }

            for (PermohonanPihak pp : senaraiPermohonanPihak) {
                if (pp.getSyerPenyebut() == null) {
                    continue;
                }
                if (pp.getSyerPenyebut() == 0) {
                    continue;
                }
                if (StringUtils.isNotBlank(idHakmilik)
                        && pp.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                    syerSemuaPermohonanPihak = syerSemuaPermohonanPihak.add(new Fraction(pp.getSyerPembilang(), pp.getSyerPenyebut()));
                }
            }

            Fraction baki = syerSemuaPemohon.subtract(syerSemuaPermohonanPihak);
            int bakiSyerPenyebut = 0;
            int bakiSyerPembilang = 0;
            if (baki.compareTo(Fraction.ZERO) > 0) {
                bakiSyerPenyebut = baki.getDenominator();
                bakiSyerPembilang = baki.getNumerator();
            }

            getContext().getRequest().setAttribute("bakiSyerPenyebut", bakiSyerPenyebut);
            getContext().getRequest().setAttribute("bakiSyerPembilang", bakiSyerPembilang);
        }

        //To cater urusan SM add by Aizuddin
        if (kodUrusan.startsWith("SM")) {
            return new JSP(MAIN_PAGE_MCL_POPUP).addParameter("popup", Boolean.TRUE);
        } else if (kodUrusan.equals("TMAMD")) {
            return new JSP(MAIN_PAGE_TMAMD_POPUP).addParameter("popup", Boolean.TRUE);
        } else {
            return new JSP(MAIN_PAGE_POPUP).addParameter("popup", Boolean.TRUE);
        }
    }

    public Resolution addNewPihak() {
        ctx.getRequest().setAttribute("newPihak", "true");
        if (kodUrusan.equals("TRPA")) {
            ctx.getRequest().setAttribute("disabledWaris", "true");
        } else {
            ctx.getRequest().setAttribute("disabledWaris", "false");
        }

        Fraction syerSemuaPemohon = Fraction.ONE;
        Fraction syerSemuaPermohonanPihak = Fraction.ONE;

        if (permohonan != null) {
            senaraiPemohon = permohonan.getSenaraiPemohon();
            senaraiPermohonanPihak = permohonan.getSenaraiPihak();
            for (Pemohon p : senaraiPemohon) {
                if (p.getSyerPenyebut() == null) {
                    continue;
                }
                if (p.getSyerPenyebut() == 0) {
                    continue;
                }
                if (StringUtils.isNotBlank(idHakmilik)
                        && p.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                    syerSemuaPemohon = syerSemuaPemohon.add(new Fraction(p.getSyerPembilang(), p.getSyerPenyebut()));
                }
            }

            for (PermohonanPihak pp : senaraiPermohonanPihak) {
                if (pp.getSyerPenyebut() == null) {
                    continue;
                }
                if (pp.getSyerPenyebut() == 0) {
                    continue;
                }
                if (StringUtils.isNotBlank(idHakmilik)
                        && pp.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                    syerSemuaPermohonanPihak = syerSemuaPermohonanPihak.add(new Fraction(pp.getSyerPembilang(), pp.getSyerPenyebut()));
                }
            }

            Fraction baki = syerSemuaPemohon.subtract(syerSemuaPermohonanPihak);
            int bakiSyerPenyebut = 0;
            int bakiSyerPembilang = 0;
            if (baki.compareTo(Fraction.ZERO) > 0) {
                bakiSyerPenyebut = baki.getDenominator();
                bakiSyerPembilang = baki.getNumerator();
            }

            getContext().getRequest().setAttribute("bakiSyerPenyebut", bakiSyerPenyebut);
            getContext().getRequest().setAttribute("bakiSyerPembilang", bakiSyerPembilang);
        }

        if (kodUrusan.equals("TMAMD")) {
            return new JSP(MAIN_PAGE_TMAMD_POPUP).addParameter("popup", Boolean.TRUE);
        }

        return new JSP(MAIN_PAGE_POPUP).addParameter("popup", Boolean.TRUE);
    }

    //Added by Aizuddin to return new page add new syarikat mcl
    public Resolution addNewPihakMCL() {
        ctx.getRequest().setAttribute("newPihak", "true");
        if (kodUrusan.equals("TRPA")) {
            ctx.getRequest().setAttribute("disabledWaris", "true");
        } else {
            ctx.getRequest().setAttribute("disabledWaris", "false");
        }
        return new JSP(MAIN_PAGE_MCL_POPUP).addParameter("popup", Boolean.TRUE);
    }

    public Resolution reload() {

        if (ArrayUtils.contains(URUSAN_TO_VALIDATE_TUAN_TANAH, kodUrusan)) {

            if (senaraiPermohonanPihakBerangkai != null) {

//                senaraiPermohonanPihakBerangkai = validatePihakBerkepentingan(senaraiPermohonanPihakBerangkai);
                if (senaraiPermohonanPihakBerangkai.isEmpty()) {
                    addSimpleError("Sila pastikan tuan tanah adalah dari pihak yang SAMA.");
                } else {
                    ctx.getRequest().setAttribute("edit", Boolean.TRUE);
                }

            } else if (senaraiKeempunyaan != null) {

//                senaraiKeempunyaan = validatePihakBerkepentingan(senaraiKeempunyaan);
                if (senaraiKeempunyaan.isEmpty()) {
                    addSimpleError("Sila pastikan tuan tanah adalah dari pihak yang SAMA.");
                } else {
                    ctx.getRequest().setAttribute("edit", Boolean.TRUE);
                }
            }
        }

        if (kodUrusan.equals("PNPA") || kodUrusan.equals("TRPA")) {
            return pihakPemegangAmanah();
        } else if (kodUrusan.equals("KVST") || kodUrusan.equals("KVSPT")) {
            return showFormKVST();
        } else if (kodUrusan.equals("TA") || kodUrusan.equals("TN") || (melaka && kodUrusan.equals("PH30A"))
                || kodUrusan.equals("PNKP")) {
            return showFormTukar();
        } else {
            return showForm();
        }
    }

    public Resolution pihakIsmen() {
        ctx.getRequest().setAttribute("edit", "true");
        return new JSP("daftar/kemasukan_pihak_main_ismen.jsp").addParameter("tab", Boolean.TRUE);
    }

    public Resolution pihakPemegangAmanah() {
        String err = getContext().getRequest().getParameter("err");
        ctx.getRequest().setAttribute("edit", "true");
        if (kodUrusan.equals("TRPA")) {

            List<HakmilikPihakBerkepentingan> senaraiPihakKeempunyaan = new ArrayList<HakmilikPihakBerkepentingan>();
            for (HakmilikPihakBerkepentingan hp : senaraiKeempunyaan) {
                if (hp == null) {
                    continue;
                }
                if (hp.getJenis().getKod().equals("PA")) {
                    senaraiPihakKeempunyaan.add(hp);
                }
            }

            senaraiKeempunyaan = senaraiPihakKeempunyaan;
        }
        if (StringUtils.isNotBlank(err)) {
            addSimpleError(err);
        }
        return new JSP("daftar/kemasukan_pihak_main_amanah.jsp").addParameter("tab", Boolean.TRUE);
    }

    public Resolution savePihakIsmen() {
        String[] uids = ctx.getRequest().getParameterValues("ids");
        String[] idHakmiliks = ctx.getRequest().getParameterValues("idHakmiliks");

        List<PermohonanPihak> senarai1 = new ArrayList<PermohonanPihak>();
        List<Pemohon> senarai2 = new ArrayList<Pemohon>();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);

        for (int i = 0; i < uids.length; i++) {
            String uid = uids[i];
            String id = idHakmiliks[i];

            Pihak p = pihakService.findById(uid);
            Hakmilik hm = hakmilikDAO.findById(id);
            if (p == null || hm == null) {
                continue;
            }
            LOG.debug("idHakmilik =" + hm.getIdHakmilik());
            HakmilikPermohonan hp = hakmilikPermohonanService.findHakmilikPermohonan(hm.getIdHakmilik(), idPermohonan);

            if (hp == null || hp.getHubunganHakmilik() == null) {
                continue;
            }

            LOG.debug("hubungan hakmilik = " + hp.getHubunganHakmilik().getKod());

            if (hp.getHubunganHakmilik().getKod().equals("LT")) {
                Pemohon pmhn = pemohonService.findPemohonByPermohonanPihak(permohonan, p);
                if (pmhn == null) {
                    pmhn = new Pemohon();
                    pmhn.setCawangan(hp.getHakmilik().getCawangan());
                    pmhn.setPihak(p);
                    pmhn.setPermohonan(permohonan);
                    pmhn.setInfoAudit(ia);
                    senarai2.add(pmhn);
                }
            } else if (hp.getHubunganHakmilik().getKod().equals("LK")) {
                PermohonanPihak pp = permohonanPihakService.getSenaraiPmohonPihakByIdPihak(idPermohonan, p.getIdPihak());
                if (pp == null) {
                    pp = new PermohonanPihak();
                    pp.setCawangan(hm.getCawangan());
                    pp.setHakmilik(hm);
                    pp.setInfoAudit(ia);
                    pp.setJenis(kodJenisPihakBerkepentinganDAO.findById("PI")); //pemasuk Ismen
                    pp.setPermohonan(permohonan);
                    pp.setPihak(p);
                    //TODO : fix syers
                    pp.setSyerPembilang(1);
                    pp.setSyerPenyebut(1);
                    senarai1.add(pp);
                }
            }
        }

        permohonanPihakService.saveOrUpdate(senarai1);
        pemohonService.saveOrUpdateMultiple(senarai2);

        return new RedirectResolution(PihakKepentinganAction.class, "pihakIsmen");
    }

    public Resolution delete() {
        String JENIS_PIHAK = ctx.getRequest().getParameter("jenisPihak");
        String[] uids = ctx.getRequest().getParameterValues("uids");
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        String copyToAllHakmiliks = getContext().getRequest().getParameter("copyToAll");
        LOG.debug("copyToAllHakmiliks = " + copyToAllHakmiliks);

        if (JENIS_PIHAK.equals("pemohon")) {

            if (StringUtils.isNotBlank(copyToAllHakmiliks)) {

                for (String id : uids) {
                    pemohon = pemohonService.findById(id);
                    for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                        if (hp == null) {
                            continue;
                        }
                        hakmilik = hp.getHakmilik();
                        if (hakmilik == null) {
                            continue;
                        }
                        idHakmilik = hakmilik.getIdHakmilik();

                        if (kodUrusan.equals("TA") || kodUrusan.equals("TN") || (melaka && kodUrusan.equals("PH30A"))
                                || kodUrusan.equals("PNKP")) {
                            List<PermohonanPihakKemaskini> senaraiKKini = permohonanPihakKemaskiniService.getSenaraiPihakKemaskini(idPermohonan, idHakmilik, pemohon.getIdPemohon(), 0);

                            if (kodUrusan.equals("TN")) {
                                HakmilikPihakBerkepentingan _hp = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(
                                        pemohon.getPihak(), hakmilik, pemohon.getJenis().getKod());
                                if (_hp != null) {
                                    List<PermohonanPihakKemaskini> senaraiKKini2 = permohonanPihakKemaskiniService.getKemaskiniWaris2(idPermohonan, String.valueOf(_hp.getIdHakmilikPihakBerkepentingan()));
                                    if (!senaraiKKini2.isEmpty()) {
                                        permohonanPihakKemaskiniService.delete(senaraiKKini2);
                                    }
                                }
                            }

                            if (!senaraiKKini.isEmpty()) {
                                permohonanPihakKemaskiniService.delete(senaraiKKini);
                            }
                        }

                        pemohon = pemohonService.findPemohonByPermohonanPihak(permohonan, pemohon.getPihak(), hakmilik, null);
                        if (pemohon != null) {
                            pemohonService.delete(pemohon);
                        }
                    }
                }
            } else {
                if (kodUrusan.equals("TA") || kodUrusan.equals("TN") || (melaka && kodUrusan.equals("PH30A"))
                        || kodUrusan.equals("PNKP")) {
                    for (String id : uids) {
                        pemohon = pemohonService.findById(id);
                        List<PermohonanPihakKemaskini> senaraiKKini = permohonanPihakKemaskiniService.getSenaraiPihakKemaskini(idPermohonan, idHakmilik, pemohon.getIdPemohon(), 0);

                        if (kodUrusan.equals("TN")) {

                            HakmilikPihakBerkepentingan _hp = pemohon.getHakmilikPihak();
                            if (_hp == null) {
                                _hp = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(
                                        pemohon.getPihak(), hakmilik, pemohon.getJenis().getKod());
                            }

                            if (_hp != null) {
                                List<PermohonanPihakKemaskini> senaraiKKini2 = permohonanPihakKemaskiniService.getKemaskiniWaris2(idPermohonan, String.valueOf(_hp.getIdHakmilikPihakBerkepentingan()));
                                if (!senaraiKKini2.isEmpty()) {
                                    permohonanPihakKemaskiniService.delete(senaraiKKini2);
                                }
                            }
                        }

                        if (!senaraiKKini.isEmpty()) {
                            permohonanPihakKemaskiniService.delete(senaraiKKini);
                        }
                    }
                }
                pemohonService.deleteSelectedPemohon(uids);
            }

        } else if (JENIS_PIHAK.equals("penerima")) {

            if (StringUtils.isNotBlank(copyToAllHakmiliks)) {

                for (String id : uids) {
                    PermohonanPihak pp = permohonanPihakService.findById(id);
                    if (pp == null) {
                        continue;
                    }
                    for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                        if (hp == null) {
                            continue;
                        }
                        hakmilik = hp.getHakmilik();
                        if (hakmilik == null) {
                            continue;
                        }
                        idHakmilik = hakmilik.getIdHakmilik();

                        if (kodUrusan.equals("KVAT")) {
                            Pemohon pmhn = pemohonService.findPemohonByPermohonanPihak(permohonan, pp.getPihak(), pp.getHakmilik(), pp.getJenis().getKod());
                            if (pmhn != null) {
                                pemohonService.delete(pmhn);
                            }
                        }
                        pp = permohonanPihakService.getSenaraiMohonPihakByIdPihakIdHakmilik(pp.getPihak().getIdPihak(), idHakmilik, idPermohonan);
                        if (pp != null) {
                            permohonanPihakService.delete(pp);
                        }
                    }
                }
            } else {
                if (kodUrusan.equals("KVAT")) {
                    for (String id : uids) {
                        PermohonanPihak pp = permohonanPihakService.findById(id);
                        if (pp == null) {
                            continue;
                        }
                        Pemohon pmhn = pemohonService.findPemohonByPermohonanPihak(permohonan, pp.getPihak(), pp.getHakmilik(), pp.getJenis().getKod());
                        if (pmhn != null) {
                            pemohonService.delete(pmhn);
                        }
                    }
                }

                permohonanPihakService.delete(uids);
            }

            if (StringUtils.isNotBlank(idHakmilik)) {
                senaraiPermohonanPihak = permohonanPihakService.getSenaraiMohonPihakForMultipleHakmilik(idPermohonan, idHakmilik);
            } else {
                senaraiPermohonanPihak = permohonan.getSenaraiPihak();
            }
        }

        if (kodUrusan.startsWith("IS")) {
            return new RedirectResolution(PihakKepentinganAction.class, "pihakIsmen");
        } else if (kodUrusan.equals("PNPA") || kodUrusan.equals("TRPA")) {
            return new RedirectResolution(PihakKepentinganAction.class, "pihakPemegangAmanah").addParameter("idHakmilik", idHakmilik);
        } else if (kodUrusan.equals("KVST") || kodUrusan.equals("KVSPT")) {
            return new RedirectResolution(PihakKepentinganAction.class, "showFormKVST").addParameter("idHakmilik", idHakmilik);
        } else if (kodUrusan.startsWith("SM")) {
            return new RedirectResolution(PihakKepentinganAction.class, "showFormMCL").addParameter("idHakmilik", idHakmilik);
        } else if (kodUrusan.equals("TA") || kodUrusan.equals("TN") || (melaka && kodUrusan.equals("PH30A"))
                || kodUrusan.equals("PNKP")) {
            return new RedirectResolution(PihakKepentinganAction.class, "showFormTukar").addParameter("idHakmilik", idHakmilik);
        } else if (kodUrusan.equals("PNPA")) {
            return new RedirectResolution(PihakKepentinganAction.class, "pihakPemegangAmanah").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(PihakKepentinganAction.class).addParameter("idHakmilik", idHakmilik);
        }
    }

    public Resolution addPP() {

        String JENIS_PIHAK = ctx.getRequest().getParameter("jenisPihak");
        String[] uids = ctx.getRequest().getParameterValues("uids");

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);

        List<PermohonanPihak> senaraiPermohonanPihakTerlibat = new ArrayList<PermohonanPihak>();

        List<Pemohon> senaraiPemohonTerlibat = new ArrayList<Pemohon>();

        if (JENIS_PIHAK.equals("pemohon")) {
            String copyToAllHakmiliks = getContext().getRequest().getParameter("copyToAll");
            LOG.debug("copyToAllHakmiliks = " + copyToAllHakmiliks);

            List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();

            for (String id : uids) {

                if (StringUtils.isBlank(id)) {
                    continue;
                }
                LOG.info("***********" + id);
                Map<String, Object> map = new HashMap<String, Object>();
                StringTokenizer st = new StringTokenizer(id, "-");
                if (st.hasMoreElements()) {
                    String idPihak = st.nextToken();
                    String kod = st.nextToken();
                    String YY = "";
                    String syerPembilang = "";
                    String syerPenyebut = "";
                    if (st.hasMoreElements()) {
                        syerPembilang = st.nextToken();
                    }
                    if (st.hasMoreElements()) {
                        syerPenyebut = st.nextToken();
                    }
                    if (st.hasMoreElements()) {
                        YY = st.nextToken();
                    }

                    LOG.info("YY =" + YY);

                    if (kodUrusan.equals("TRPA")) {
                        kod = "PA";
                    }

                    pihak = pihakService.findById(idPihak);
                    if (pihak == null) {
                        continue;
                    }

                    LOG.info("isBerangkai = " + isBerangkai);

                    if (StringUtils.isNotBlank(copyToAllHakmiliks)) {

                        for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {

                            map = new HashMap<String, Object>();
                            if (hp == null || hp.getHakmilik() == null) {
                                continue;
                            }
                            Hakmilik hm = hp.getHakmilik();
                            HakmilikPihakBerkepentingan hpk = null;
                            if (isBerangkai) {
                                //kalau berangkai, ada 2 kemungkinan
                                //1. pihak ditambah melalui hakmilikpihak
                                //2. pihak ditambah melalui mohonpihak
                                // kalau mohon pihak, hpk biar null.
                                if (StringUtils.isNotBlank(YY)) {
                                    hpk = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pihak, hm, kod);
                                }
                            } else {
                                hpk = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pihak, hm, kod);
                            }

                            map.put("pihakBerkepentingan", hpk);
                            map.put("hakmilik", hm);
                            map.put("pihak", pihak);
                            map.put("kodPihak", kod);
                            map.put("syerPembilang", syerPembilang);
                            map.put("syerPenyebut", syerPenyebut);
                            strList.add(map); //hpk null if berangkai..
                        }
                    } else {
                        HakmilikPihakBerkepentingan hpk = null;
                        if (isBerangkai) {
                            //kalau berangkai, ada 2 kemungkinan
                            //1. pihak ditambah melalui hakmilikpihak
                            //2. pihak ditambah melalui mohonpihak
                            // kalau mohon pihak, hpk biar null.
                            if (StringUtils.isNotBlank(YY)) {
                                hpk = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pihak, hakmilik, kod);
                            }
                        } else {
                            hpk = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pihak, hakmilik, kod);
                        }

                        map.put("pihakBerkepentingan", hpk);
                        map.put("hakmilik", hakmilik);
                        map.put("pihak", pihak);
                        map.put("kodPihak", kod);
                        map.put("syerPembilang", syerPembilang);
                        map.put("syerPenyebut", syerPenyebut);
                        strList.add(map); //hpk null if berangkai..
                    }
                }
            }

            LOG.debug("size pemohon = " + strList.size());

            for (Map<String, Object> map : strList) {
                if (map == null) {
                    continue;
                }

                boolean fg = true;
                int syerPembilang = 0;
                int syerPenyebut = 0;

                HakmilikPihakBerkepentingan hpk = (HakmilikPihakBerkepentingan) map.get("pihakBerkepentingan");
                String kod = (String) map.get("kodPihak");
                hakmilik = (Hakmilik) map.get("hakmilik");
                idHakmilik = hakmilik.getIdHakmilik();
                pihak = (Pihak) map.get("pihak");
                if (StringUtils.isNotBlank((String) map.get("syerPembilang"))) {
                    syerPembilang = Integer.parseInt((String) map.get("syerPembilang"));
                }
                if (StringUtils.isNotBlank((String) map.get("syerPenyebut"))) {
                    syerPenyebut = Integer.parseInt((String) map.get("syerPenyebut"));
                }
                if (isDebug) {
//                    LOG.debug("pihak = " + pihak.getNama());
                    LOG.debug("id hakmilik = " + idHakmilik);
                    LOG.debug("kod = " + kod);
                }

                KodJenisPihakBerkepentingan jenis = null;

                if (hpk == null) {
                    PermohonanPihak pp = null;

                    List<Permohonan> permohonanSebelum = permohonanService.getPermohonanSebelum(idKumpulan, permohonan.getKumpulanNo());

                    for (Permohonan permohonan1 : permohonanSebelum) {
                        if (permohonan1 == null) {
                            continue;
                        }
                        idPermohonan = permohonan1.getIdPermohonan();
                        pp = permohonanPihakService.getPmohonPihakByKodAndIdHakmilik(idPermohonan, idHakmilik, pihak.getIdPihak(), kod);
                        if (pp != null) {
                            jenis = pp.getJenis();
                            if (syerPembilang == 0) {
                                syerPembilang = pp.getSyerPembilang() != null ? pp.getSyerPembilang() : 0;
                            }
                            if (syerPenyebut == 0) {
                                syerPenyebut = pp.getSyerPenyebut() != null ? pp.getSyerPenyebut() : 0;
                            }
                            break;
                        }
                    }

                    if (pp == null) {
                        fg = false; // to make sure pihak kepentingan same for all hakmiliks...
                    }
                } else {
                    jenis = hpk.getJenis();
                    if (syerPembilang == 0) {
                        syerPembilang = hpk.getSyerPembilang() != null ? hpk.getSyerPembilang() : 0;
                    }
                    if (syerPenyebut == 0) {
                        syerPenyebut = hpk.getSyerPenyebut() != null ? hpk.getSyerPenyebut() : 0;
                    }
                }

                if (!fg) {
                    continue;
                }

                boolean contains = ArrayUtils.contains(URUSAN_TO_REPLACE_OWNER, kodUrusan);

                PermohonanPihak pp = permohonanPihakService.getPmohonPihakByKodAndIdHakmilik(permohonan.getIdPermohonan(), idHakmilik,
                        pihak.getIdPihak(), kod);
                if (pp == null) {
                    pp = new PermohonanPihak();

                    pp.setHakmilik(hakmilik);
                    pp.setCawangan(hakmilik.getCawangan());
                    if (jenis != null) {
                        pp.setJenis(jenis);
                    }
                    pp.setInfoAudit(ia);
                    pp.setSyerPembilang(syerPembilang);
                    pp.setSyerPenyebut(syerPenyebut);
                    pp.setPihak(pihak);
                    pp.setPermohonan(permohonan);
                    pp.setNoPengenalan(pihak.getNoPengenalan());
                    pp.setNama(pihak.getNama().toUpperCase());
                    pp.setJenisPengenalan(pihak.getJenisPengenalan());

                    Alamat alamat = new Alamat();
                    alamat.setAlamat1(pihak.getAlamat1());
                    alamat.setAlamat2(pihak.getAlamat2());
                    alamat.setAlamat3(pihak.getAlamat3());
                    alamat.setAlamat4(pihak.getAlamat4());
                    alamat.setPoskod(pihak.getPoskod());
                    alamat.setNegeri(pihak.getNegeri());

                    AlamatSurat alamatSurat = new AlamatSurat();
                    alamatSurat.setAlamatSurat1(pihak.getSuratAlamat1());
                    alamatSurat.setAlamatSurat2(pihak.getSuratAlamat2());
                    alamatSurat.setAlamatSurat3(pihak.getSuratAlamat3());
                    alamatSurat.setAlamatSurat4(pihak.getSuratAlamat4());
                    alamatSurat.setPoskodSurat(pihak.getSuratPoskod());
                    alamatSurat.setNegeriSurat(pihak.getSuratNegeri());

                    pp.setAlamat(alamat);
                    pp.setAlamatSurat(alamatSurat);
                }

                senaraiPermohonanPihakTerlibat.add(pp);

                Pemohon pmhn = pemohonService.findPemohonByPermohonanPihak(permohonan, pihak, hakmilik, kod);
                if (pmhn == null) {
                    LOG.debug("pemohon null. create new pemohon.");
                    pmhn = new Pemohon();
                    pmhn.setHakmilik(hakmilik);
                    pmhn.setCawangan(hakmilik.getCawangan());
                    pmhn.setInfoAudit(ia);
                    pmhn.setPihak(pihak);
                    pmhn.setPermohonan(permohonan);
                    pmhn.setSyerPembilang(syerPembilang);
                    pmhn.setSyerPenyebut(syerPenyebut);
                    if (jenis != null) {
                        pmhn.setJenis(jenis);
                    }
                    if (contains) {
                        pmhn.setJenisPemohon("X");
                    } else if (kodUrusan.equals("TA") || kodUrusan.equals("TN") || (melaka && kodUrusan.equals("PH30A"))
                            || kodUrusan.equals("PNKP")) {
                        pmhn.setJenisPemohon("Y"); //tukar nama, alamat dsb
                    }
                }
                senaraiPemohonTerlibat.add(pmhn);

            }
        } else if (JENIS_PIHAK.equals("penerima")) {
            //todo
        }

        if (!senaraiPermohonanPihakTerlibat.isEmpty()) {
            permohonanPihakService.saveOrUpdate(senaraiPermohonanPihakTerlibat);
        }

        if (!senaraiPemohonTerlibat.isEmpty()) {
            pemohonService.saveOrUpdateMultiple(senaraiPemohonTerlibat);
        }

        if (kodUrusan.equals("TA") || kodUrusan.equals("TN") || kodUrusan.equals("PNKP") || (melaka && kodUrusan.equals("PH30A"))) {
            return new RedirectResolution(PihakKepentinganAction.class, "showFormTukar").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(PihakKepentinganAction.class).addParameter("idHakmilik", idHakmilik);
        }
    }

    public Resolution add() {

        String JENIS_PIHAK = ctx.getRequest().getParameter("jenisPihak");
        String[] uids = ctx.getRequest().getParameterValues("uids");

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);
        List<Pemohon> senarai1 = new ArrayList<Pemohon>();
        List<PermohonanPihak> senarai2 = new ArrayList<PermohonanPihak>();
        List<PermohonanAtasPihakBerkepentingan> senarai3 = new ArrayList<PermohonanAtasPihakBerkepentingan>();
        if (JENIS_PIHAK.equals("pemohon")) {
            String copyToAllHakmiliks = getContext().getRequest().getParameter("copyToAll");
            LOG.debug("copyToAllHakmiliks = " + copyToAllHakmiliks);

            List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();

            for (String id : uids) {

                if (StringUtils.isBlank(id)) {
                    continue;
                }
                LOG.info("***********" + id);
                Map<String, Object> map = new HashMap<String, Object>();

                String[] str = id.split("-");
//        StringTokenizer st = new StringTokenizer(id, "-");
                if (str.length > 0) {
                    String idPihak = StringUtils.isNotBlank(str[0]) ? str[0] : "";
                    String kod = StringUtils.isNotBlank(str[1]) ? str[1] : "";
                    String syerPembilang = StringUtils.isNotBlank(str[2]) ? str[2] : "";
                    String syerPenyebut = StringUtils.isNotBlank(str[3]) ? str[3] : "";
                    String YY = StringUtils.isNotBlank(str[4]) ? str[4] : "";
                    String idHp = null;
                    if (str.length > 5) {
                        idHp = StringUtils.isNotBlank(str[5]) ? str[5] : "";
                    }

                    LOG.info("YY =" + YY);

                    if (kodUrusan.equals("TRPA")) {
                        kod = "PA";
                    }

                    pihak = pihakService.findById(idPihak);
                    if (pihak == null) {
                        continue;
                    }

                    LOG.info("isBerangkai = " + isBerangkai);

                    if (StringUtils.isNotBlank(copyToAllHakmiliks)) {

                        for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {

                            map = new HashMap<String, Object>();
                            if (hp == null || hp.getHakmilik() == null) {
                                continue;
                            }
                            Hakmilik hm = hp.getHakmilik();
                            HakmilikPihakBerkepentingan hpk = null;
                            if (isBerangkai) {
                                //kalau berangkai, ada 2 kemungkinan
                                //1. pihak ditambah melalui hakmilikpihak
                                //2. pihak ditambah melalui mohonpihak
                                // kalau mohon pihak, hpk biar null.
                                if (StringUtils.isNotBlank(YY)) {
                                    hpk = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pihak, hm, kod);
                                }
                            } else {
                                hpk = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pihak, hm, kod);
                            }

                            map.put("pihakBerkepentingan", hpk);
                            map.put("hakmilik", hm);
                            map.put("pihak", pihak);
                            map.put("kodPihak", kod);
                            map.put("syerPembilang", syerPembilang);
                            map.put("syerPenyebut", syerPenyebut);
                            strList.add(map); //hpk null if berangkai..
                        }
                    } else {
                        HakmilikPihakBerkepentingan hpk = null;
                        if (isBerangkai) {
                            //kalau berangkai, ada 2 kemungkinan
                            //1. pihak ditambah melalui hakmilikpihak
                            //2. pihak ditambah melalui mohonpihak
                            // kalau mohon pihak, hpk biar null.
                            if (StringUtils.isNotBlank(YY)) {
                                if (StringUtils.isNotBlank(idHp)) {
                                    hpk = hakmilikPihakService.findById(idHp);
                                } else {
                                    hpk = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pihak, hakmilik, kod);
                                }
//                hpk = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pihak, hakmilik, kod);
                            }
                        } else if (StringUtils.isNotBlank(idHp)) {
                            hpk = hakmilikPihakService.findById(idHp);
                        } else {
                            hpk = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pihak, hakmilik, kod);
                        }

                        map.put("pihakBerkepentingan", hpk);
                        map.put("hakmilik", hakmilik);
                        map.put("pihak", pihak);
                        map.put("kodPihak", kod);
                        map.put("syerPembilang", syerPembilang);
                        map.put("syerPenyebut", syerPenyebut);
                        strList.add(map); //hpk null if berangkai..
                    }
                }
            }

            LOG.debug("size pemohon = " + strList.size());

            for (Map<String, Object> map : strList) {
                if (map == null) {
                    continue;
                }

                boolean fg = true;
                int syerPembilang = 0;
                int syerPenyebut = 0;

                HakmilikPihakBerkepentingan hpk = (HakmilikPihakBerkepentingan) map.get("pihakBerkepentingan");
                String kod = (String) map.get("kodPihak");
                hakmilik = (Hakmilik) map.get("hakmilik");
                idHakmilik = hakmilik.getIdHakmilik();
                pihak = (Pihak) map.get("pihak");
                if (StringUtils.isNotBlank((String) map.get("syerPembilang"))) {
                    syerPembilang = Integer.parseInt((String) map.get("syerPembilang"));
                }
                if (StringUtils.isNotBlank((String) map.get("syerPenyebut"))) {
                    syerPenyebut = Integer.parseInt((String) map.get("syerPenyebut"));
                }
                if (isDebug) {
//                    LOG.debug("pihak = " + pihak.getNama());
                    LOG.debug("id hakmilik = " + idHakmilik);
                    LOG.debug("kod = " + kod);
                }

                KodJenisPihakBerkepentingan jenis = null;
                PermohonanPihak pp = null;

                if (hpk == null) {

                    List<Permohonan> permohonanSebelum = permohonanService.getPermohonanSebelum(idKumpulan, permohonan.getKumpulanNo());

                    for (Permohonan permohonan1 : permohonanSebelum) {
                        if (permohonan1 == null) {
                            continue;
                        }
                        idPermohonan = permohonan1.getIdPermohonan();
                        String ku = permohonan1.getKodUrusan().getKod();
                        if ("PMTB".equals(ku)) {
                            List<PermohonanHubungan> tmp = permohonanService.getSenaraiHubungan(permohonan1.getIdPermohonan(), idHakmilik);
                            for (PermohonanHubungan ph : tmp) {
                                List<Pemohon> senaraiPemohonBatal = ph.getPermohonanSasaran().getSenaraiPemohon();
                                for (Pemohon p : senaraiPemohonBatal) {
                                    if (p.getPihak().equals(pihak)
                                            && p.getHakmilik().equals(hakmilik)
                                            && p.getJenis().getKod().equals(kod)) {
                                        pp = new PermohonanPihak();
                                        pp.setNama(p.getNama().toUpperCase());
                                        pp.setWargaNegara(p.getWargaNegara());
                                        pp.setNoPengenalan(p.getNoPengenalan());
                                        pp.setJenisPengenalan(p.getJenisPengenalan());
                                        pp.setAlamat(p.getAlamat());
                                        jenis = p.getJenis();
                                        if (syerPembilang == 0) {
                                            syerPembilang = p.getSyerPembilang() != null ? p.getSyerPembilang() : 0;
                                        }
                                        if (syerPenyebut == 0) {
                                            syerPenyebut = p.getSyerPenyebut() != null ? p.getSyerPenyebut() : 0;
                                        }
                                        break;
                                    }
                                }
                            }
                        } else {
                            pp = permohonanPihakService.getPmohonPihakByKodAndIdHakmilik(idPermohonan, idHakmilik, pihak.getIdPihak(), kod);
                            if (pp != null) {
                                jenis = pp.getJenis();
                                if (syerPembilang == 0) {
                                    syerPembilang = pp.getSyerPembilang() != null ? pp.getSyerPembilang() : 0;
                                }
                                if (syerPenyebut == 0) {
                                    syerPenyebut = pp.getSyerPenyebut() != null ? pp.getSyerPenyebut() : 0;
                                }
                                break;
                            }
                        }
                    }

                    if (pp == null) {
                        fg = false; // to make sure pihak kepentingan same for all hakmiliks...
                    }
                } else {
                    jenis = hpk.getJenis();
                    if (syerPembilang == 0) {
                        syerPembilang = hpk.getSyerPembilang() != null ? hpk.getSyerPembilang() : 0;
                    }
                    if (syerPenyebut == 0) {
                        syerPenyebut = hpk.getSyerPenyebut() != null ? hpk.getSyerPenyebut() : 0;
                    }
                }

                if (!fg) {
                    continue;
                }

                boolean contains = ArrayUtils.contains(URUSAN_TO_REPLACE_OWNER, kodUrusan);

                Pemohon pmhn = null;
                if (hpk != null) {
                    pmhn = pemohonService.findPemohonByPermohonanPihak(permohonan, pihak, hakmilik, kod,
                            String.valueOf(hpk.getIdHakmilikPihakBerkepentingan()));
                } else {
                    pmhn = pemohonService.findPemohonByPermohonanPihak(permohonan, pihak, hakmilik, kod);
                }

                // utk urusan sblm melibatkan tukar nama, tukar alamat   dan ambil yg latest
                // contohnya , ada TN(1)-TN(2)-TN(3)-PMT, akan ambil nilai dari Tn(3) shj
                //todo, retest , redo, possible to connection leaking
                String nama = permohonanPihakKemaskiniService.getNilaiBaru(idPermohonan, idHakmilik, String.valueOf(pihak.getIdPihak()),
                        "nama", idKumpulan, String.valueOf(permohonan.getKumpulanNo()));
                String noPengenalan = permohonanPihakKemaskiniService.getNilaiBaru(idPermohonan, idHakmilik, String.valueOf(pihak.getIdPihak()),
                        "nokp", idKumpulan, String.valueOf(permohonan.getKumpulanNo()));
                String jenisKp = permohonanPihakKemaskiniService.getNilaiBaru(idPermohonan, idHakmilik, String.valueOf(pihak.getIdPihak()),
                        "jeniskp", idKumpulan, String.valueOf(permohonan.getKumpulanNo()));
                String kodWarganegara = permohonanPihakKemaskiniService.getNilaiBaru(idPermohonan, idHakmilik, String.valueOf(pihak.getIdPihak()),
                        "kodWarganegara", idKumpulan, String.valueOf(permohonan.getKumpulanNo()));

                KodJenisPengenalan kodJenisPengenalan = null;
                if (jenisKp != null) {
                    kodJenisPengenalanDAO.findById(jenisKp);
                }

                String rumahAlamat1 = permohonanPihakKemaskiniService.getNilaiBaru(idPermohonan, idHakmilik, String.valueOf(pihak.getIdPihak()),
                        "rumahAlamat1", idKumpulan, String.valueOf(permohonan.getKumpulanNo()));

                String rumahAlamat2 = permohonanPihakKemaskiniService.getNilaiBaru(idPermohonan, idHakmilik, String.valueOf(pihak.getIdPihak()),
                        "rumahAlamat2", idKumpulan, String.valueOf(permohonan.getKumpulanNo()));

                String rumahAlamat3 = permohonanPihakKemaskiniService.getNilaiBaru(idPermohonan, idHakmilik, String.valueOf(pihak.getIdPihak()),
                        "rumahAlamat3", idKumpulan, String.valueOf(permohonan.getKumpulanNo()));

                String rumahAlamat4 = permohonanPihakKemaskiniService.getNilaiBaru(idPermohonan, idHakmilik, String.valueOf(pihak.getIdPihak()),
                        "rumahAlamat4", idKumpulan, String.valueOf(permohonan.getKumpulanNo()));

                String rumahPoskod = permohonanPihakKemaskiniService.getNilaiBaru(idPermohonan, idHakmilik, String.valueOf(pihak.getIdPihak()),
                        "rumahPoskod", idKumpulan, String.valueOf(permohonan.getKumpulanNo()));

                String kodRumahNegeri = permohonanPihakKemaskiniService.getNilaiBaru(idPermohonan, idHakmilik, String.valueOf(pihak.getIdPihak()),
                        "rumahNegeri.kod", idKumpulan, String.valueOf(permohonan.getKumpulanNo()));

                KodNegeri kodNegeri = null;
                if (kodRumahNegeri != null) {
                    kodNegeri = kodNegeriDAO.findById(kodRumahNegeri);
                }

                if (pmhn == null) {
                    LOG.debug("pemohon null. create new pemohon.");
                    pmhn = new Pemohon();
                    pmhn.setHakmilik(hakmilik);
                    pmhn.setCawangan(hakmilik.getCawangan());
                    pmhn.setInfoAudit(ia);
                    pmhn.setPihak(pihak);
                    pmhn.setPermohonan(permohonan);
                    pmhn.setSyerPembilang(syerPembilang);
                    pmhn.setSyerPenyebut(syerPenyebut);
                    if (jenis != null) {
                        pmhn.setJenis(jenis);
                    }
                    if (contains) {
                        pmhn.setJenisPemohon("X");
                    } else if (kodUrusan.equals("TA") || kodUrusan.equals("TN") || (melaka && kodUrusan.equals("PH30A"))
                            || kodUrusan.equals("PNKP")) {
                        pmhn.setJenisPemohon("Y"); //tukar nama, alamat dsb
                    }

                    pmhn.setHakmilikPihak(hpk);

                    pmhn.setNama(nama != null ? nama : pp != null ? pp.getNama().toUpperCase() : hpk != null ? hpk.getNama().toUpperCase() : pihak.getNama().toUpperCase());
                    pmhn.setNoPengenalan(noPengenalan != null ? noPengenalan : pp != null ? pp.getNoPengenalan() : hpk != null ? hpk.getNoPengenalan() : pihak.getNoPengenalan());
                    pmhn.setJenisPengenalan(kodJenisPengenalan != null ? kodJenisPengenalan : pp != null ? pp.getJenisPengenalan() : hpk != null ? hpk.getJenisPengenalan() : pihak.getJenisPengenalan());
                    pmhn.setWargaNegara(pp != null ? pp.getWargaNegara() : hpk != null ? hpk.getWargaNegara() : pihak.getWargaNegara());
                    if (jenisKp != null) {
                        pmhn.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisKp));
                    }
                    Alamat alamat = new Alamat();
                    if (pp != null) {
                        alamat = pp.getAlamat();
                        if (rumahAlamat1 != null) {
                            alamat.setAlamat1(rumahAlamat1);
                        }
                        if (rumahAlamat2 != null) {
                            alamat.setAlamat2(rumahAlamat2);
                        }
                        if (rumahAlamat3 != null) {
                            alamat.setAlamat3(rumahAlamat3);
                        }
                        if (rumahAlamat4 != null) {
                            alamat.setAlamat4(rumahAlamat4);
                        }
                        if (rumahPoskod != null) {
                            alamat.setPoskod(rumahPoskod);
                        }
                        if (kodNegeri != null) {
                            alamat.setNegeri(kodNegeri);
                        }
                    }
                    if (hpk != null && hpk.getAlamat1() != null && hpk.getAlamat2() != null
                            && hpk.getNegeri() != null) {
                        alamat.setAlamat1(rumahAlamat1 != null ? rumahAlamat1 : hpk.getAlamat1());
                        alamat.setAlamat2(rumahAlamat2 != null ? rumahAlamat2 : hpk.getAlamat2());
                        alamat.setAlamat3(rumahAlamat3 != null ? rumahAlamat3 : hpk.getAlamat3());
                        alamat.setAlamat4(rumahAlamat4 != null ? rumahAlamat4 : hpk.getAlamat4());
                        alamat.setPoskod(rumahPoskod != null ? rumahPoskod : hpk.getPoskod());
                        alamat.setNegeri(kodNegeri != null ? kodNegeri : hpk.getNegeri());
                    }
//          else {
//            alamat.setAlamat1(rumahAlamat1 != null ? rumahAlamat1 : pihak.getAlamat1());
//            alamat.setAlamat2(rumahAlamat2 != null ? rumahAlamat2 :pihak.getAlamat2());
//            alamat.setAlamat3(rumahAlamat3 != null ? rumahAlamat3 :pihak.getAlamat3());
//            alamat.setAlamat4(rumahAlamat4 != null ? rumahAlamat4 :pihak.getAlamat4());
//            alamat.setPoskod(rumahPoskod != null ? rumahPoskod :pihak.getPoskod());
//            alamat.setNegeri(kodNegeri != null ? kodNegeri :pihak.getNegeri());
//          }
                    AlamatSurat alamatSurat = new AlamatSurat();
                    if (!pihak.getSenaraiAlamatTamb().isEmpty()) {
                        PihakAlamatTamb alamatTamb = pihak.getSenaraiAlamatTamb().get(0);
                        if (alamatTamb != null) {
                            alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(alamatTamb.getAlamatKetiga1()) ? alamatTamb.getAlamatKetiga1() : "");
                            alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(alamatTamb.getAlamatKetiga2()) ? alamatTamb.getAlamatKetiga2() : "");
                            alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(alamatTamb.getAlamatKetiga3()) ? alamatTamb.getAlamatKetiga3() : "");
                            alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(alamatTamb.getAlamatKetiga4()) ? alamatTamb.getAlamatKetiga4() : "");
                            alamatSurat.setPoskodSurat(StringUtils.isNotBlank(alamatTamb.getAlamatKetigaPoskod()) ? alamatTamb.getAlamatKetigaPoskod() : "");
                            alamatSurat.setNegeriSurat(alamatTamb.getAlamatKetigaNegeri() != null ? alamatTamb.getAlamatKetigaNegeri() : null);
                        }
                    } else {
                        alamatSurat.setAlamatSurat1(pihak.getSuratAlamat1());
                        alamatSurat.setAlamatSurat2(pihak.getSuratAlamat2());
                        alamatSurat.setAlamatSurat3(pihak.getSuratAlamat3());
                        alamatSurat.setAlamatSurat4(pihak.getSuratAlamat4());
                        alamatSurat.setPoskodSurat(pihak.getSuratPoskod());
                        alamatSurat.setNegeriSurat(pihak.getSuratNegeri());
                    }

                    pmhn.setAlamat(alamat);
                    pmhn.setAlamatSurat(alamatSurat);

                }

                Permohonan mohonBaru = pmhn.getPermohonan();
                if (mohonBaru.getIdKumpulan() != null) {
                    int idSusun = mohonBaru.getKumpulanNo() - 1;
                    if (idSusun != 0) {
                        Permohonan permohonanSblm = permohonanService.findByIdKumpAndKumpNo(mohonBaru.getIdKumpulan(), idSusun);
                        if (permohonanSblm != null) {
                            List<PermohonanPihakKemaskini> senaraiPermohonanPihakKemaskini = permohonanPihakKkiniService.findByIdPermohonan(permohonanSblm.getIdPermohonan());
                            if (permohonanSblm.getKodUrusan().getKod().equals("TN")) {
                                if (senaraiPermohonanPihakKemaskini.size() > 0) {
                                    for (PermohonanPihakKemaskini mpkk : senaraiPermohonanPihakKemaskini) {
                                        if (pmhn.getHakmilikPihak() != null) { //aku tambah sebab lepas wazer tambah bawah nie, TN lepas TN tak boleh tambah nama pihak error null pointer (yus) ...
                                            LOG.debug("pmhn.getHakmilikPihak()" + pmhn.getHakmilikPihak());
                                            LOG.debug("mpkk.getPihakTerlibat()" + mpkk.getPihakTerlibat());
                                            if (pmhn.getHakmilikPihak().equals(mpkk.getPihakTerlibat()) && mpkk.getPihakTerlibat() == null) { //yus add ==null 08102018 kes waris // aku tambah sebab ada tn dia replace 2 2 nama (wazer)....
                                                if (pmhn.getHakmilikPihak() != null) {
                                                    if (mpkk.getNamaMedan().equals("nama")) {
                                                        pmhn.setNama(mpkk.getNilaiBaru());
                                                    } else if (mpkk.getNamaMedan().equals("nokp")) {
                                                        pmhn.setNoPengenalan(mpkk.getNilaiBaru());
                                                    } else if (mpkk.getNamaMedan().equals("jeniskp")) {
                                                        pmhn.setJenisPengenalan(kodJenisPengenalanDAO.findById(mpkk.getNilaiBaru()));
                                                    } else if (mpkk.getNamaMedan().equals("kodWarganegara")) {
                                                        pmhn.setWargaNegara(kodWarganegaraDAO.findById(mpkk.getNilaiBaru()));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                senarai1.add(pmhn);
                if (hpk != null) {
                    List<PermohonanAtasPihakBerkepentingan> ppihakList = permohonanAtasPihakService.findByAtasPihakList(hpk.getIdHakmilikPihakBerkepentingan(), permohonan.getIdPermohonan());
                    for (PermohonanAtasPihakBerkepentingan pap : ppihakList) {
                        if (pap == null) {
                            pap = new PermohonanAtasPihakBerkepentingan();
                            ia.setTarikhMasuk(new java.util.Date());
                            pap.setInfoAudit(ia);
                            pap.setPermohonan(permohonan);
                            pap.setHakmilik(hakmilik);
                            pap.setPihakBerkepentingan(hpk);
                            senarai3.add(pap);
                        }
                    }
                }

            }

        } else if (JENIS_PIHAK.equals("penerima")) {
            //todo
        }

        if (senarai1.size() > 0) {
            pemohonService.saveOrUpdateMultiple(senarai1);
        }

        if (senarai3.size() > 0) {
            permohonanAtasPihakService.save(senarai3);
        }

        if (kodUrusan.equals("TA") || kodUrusan.equals("TN") || kodUrusan.equals("PNKP") || (melaka && kodUrusan.equals("PH30A"))) {
            return new RedirectResolution(PihakKepentinganAction.class, "showFormTukar").addParameter("idHakmilik", idHakmilik);
        } else if ((kodUrusan.equals("PNPA")) || (kodUrusan.equals("TRPA"))) {
            return new RedirectResolution(PihakKepentinganAction.class, "pihakPemegangAmanah").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(PihakKepentinganAction.class).addParameter("idHakmilik", idHakmilik);
        }
    }

    public Resolution showTukarForm() {

        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idPemohon = getContext().getRequest().getParameter("id");
        idPihak1 = getContext().getRequest().getParameter("d1");
        LOG.info("id Mohon Pihak = " + idPemohon);

        pihak = pihakDAO.findById(Long.valueOf(idPihak));

        senaraiPemohon = permohonan.getSenaraiPemohon();

        //TODO : get list from prev permohonan if berangkai
        List<Permohonan> list_sblm = permohonanService.getPermohonanSebelum(permohonan.getIdKumpulan(), permohonan.getKumpulanNo());

        for (Pemohon pmohon : senaraiPemohon) {
            if (pmohon == null
                    || !pmohon.getHakmilik().getIdHakmilik().equals(idHakmilik)
                    || !pmohon.getPihak().equals(pihak)) {
                continue;
            }
            String kodJenis = "";
            if (pmohon.getJenis() != null) {
                kodJenis = pmohon.getJenis().getKod();
            }
            hakmilikPihak = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pmohon.getPihak(), pmohon.getHakmilik(), kodJenis);
            if (hakmilikPihak != null) {
                break;
            }
        }

        if (idPemohon != null) {
            pemohon = pemohonService.findById(idPemohon);
        }

        if (hakmilikPihak != null) {
            pihak = hakmilikPihak.getPihak();
            pihak.setAlamat1(hakmilikPihak.getAlamat1());
            pihak.setAlamat2(hakmilikPihak.getAlamat2());
            pihak.setAlamat3(hakmilikPihak.getAlamat3());
            pihak.setAlamat4(hakmilikPihak.getAlamat4());
            pihak.setPoskod(hakmilikPihak.getPoskod());
            pihak.setNegeri(hakmilikPihak.getNegeri());
            pihak.setNama(hakmilikPihak.getNama());
            pihak.setNoPengenalan(hakmilikPihak.getNoPengenalan());

            //eda added on 20/12/2017 (URUSAN TN-PAPARAN PD NILAI LAMA )
            pihak.setJenisPengenalan(hakmilikPihak.getJenisPengenalan());

        } else {
            for (Permohonan p : list_sblm) {
                List<PermohonanPihak> list_pihak = p.getSenaraiPihak();
                for (PermohonanPihak pm : list_pihak) {
                    if (pm.getPihak().getIdPihak() == pihak.getIdPihak()
                            && pm.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                        pihak.setNama(pm.getNama());
                        pihak.setJenisPengenalan(pm.getJenisPengenalan());
                        pihak.setNoPengenalan(pm.getNoPengenalan());
                        break;
                    }
                }
            }
        }

        String namaBaruUbah = "";
        String noKPBaruUbah = "";
        String jenisKPBaruUbah = "";
        String kodWarganegaraBaruUbah = "";

        for (Permohonan p : list_sblm) {
            if (p == null) {
                continue;
            }

            List<PermohonanPihakKemaskini> senarai = p.getSenaraiPihakKemaskini();
            for (PermohonanPihakKemaskini pp : senarai) {
                if (pp.getPemohon() != null) {
                    if (!pp.getPemohon().getHakmilik().getIdHakmilik().equals(idHakmilik)
                            || !pp.getPemohon().getPihak().equals(pihak)) {
                        continue;
                    }
                }

                if (pp.getNamaMedan().equals("nama") && StringUtils.isBlank(namaBaruUbah)) {
                    namaBaruUbah = pp.getNilaiBaru();
                    pihak.setNama(pp.getNilaiBaru());
                }
                if (pp.getNamaMedan().equals("nokp") && StringUtils.isBlank(noKPBaruUbah)) {
                    noKPBaruUbah = pp.getNilaiBaru();
                    pihak.setNoPengenalan(pp.getNilaiBaru());
                }
                if (pp.getNamaMedan().equals("jeniskp") && StringUtils.isBlank(jenisKPBaruUbah)) {
                    jenisKPBaruUbah = pp.getNilaiBaru();
                    KodJenisPengenalan kod = kodJenisPengenalanDAO.findById(pp.getNilaiBaru());
                    pihak.setJenisPengenalan(kod);
                }
                if (pp.getNamaMedan().equals("kodWarganegara") && StringUtils.isBlank(kodWarganegaraBaruUbah)) {
                    kodWarganegaraBaruUbah = pp.getNilaiBaru();
                    KodWarganegara kod = kodWarganegaraDAO.findById(pp.getNilaiBaru());
                    pihak.setWargaNegara(kod);
                }
            }
        }

        senaraiKemaskini = permohonan.getSenaraiPihakKemaskini();
        for (PermohonanPihakKemaskini pp : senaraiKemaskini) {
            if (pp.getPemohon() != null) {
                if (!pp.getPemohon().getHakmilik().getIdHakmilik().equals(idHakmilik)
                        || !pp.getPemohon().getPihak().equals(pihak)) {
                    continue;
                }
            } else {
                continue;
            }

            if (kodUrusan.equals("TN")) {
                if (pemohon != null) {
                    if (pemohon.getIdPemohon() == pp.getPemohon().getIdPemohon()) {
                        if (pp.getNamaMedan().equals("nama")) {
                            getContext().getRequest().setAttribute("nama", pp.getNilaiBaru());
                        }
                        if (pp.getNamaMedan().equals("nokp")) {
                            getContext().getRequest().setAttribute("nokp", pp.getNilaiBaru());
                        }
                        if (pp.getNamaMedan().equals("jeniskp")) {
                            getContext().getRequest().setAttribute("jeniskp", pp.getNilaiBaru());
                        }
                        if (pp.getNamaMedan().equals("kodWarganegara")) {
                            getContext().getRequest().setAttribute("kodWarganegara", pp.getNilaiBaru());
                        }
                    }
                }
            } else {
                if (pp.getNamaMedan().equals("rumahAlamat1")) {
                    getContext().getRequest().setAttribute("alamat1", pp.getNilaiBaru());
                }
                if (pp.getNamaMedan().equals("rumahAlamat2")) {
                    getContext().getRequest().setAttribute("alamat2", pp.getNilaiBaru());
                }
                if (pp.getNamaMedan().equals("rumahAlamat3")) {
                    getContext().getRequest().setAttribute("alamat3", pp.getNilaiBaru());
                }
                if (pp.getNamaMedan().equals("rumahAlamat4")) {
                    getContext().getRequest().setAttribute("alamat4", pp.getNilaiBaru());
                }
                if (pp.getNamaMedan().equals("rumahPoskod")) {
                    getContext().getRequest().setAttribute("alamat5", pp.getNilaiBaru());
                }
                if (pp.getNamaMedan().equals("rumahNegeri.kod")) {
                    getContext().getRequest().setAttribute("alamat6", pp.getNilaiBaru());
                }
                if (pp.getNamaMedan().equals("nama")) {
                    getContext().getRequest().setAttribute("nama", pp.getNilaiBaru());
                }
                if (pp.getNamaMedan().equals("nokp")) {
                    getContext().getRequest().setAttribute("nokp", pp.getNilaiBaru());
                }
                if (pp.getNamaMedan().equals("jeniskp")) {
                    getContext().getRequest().setAttribute("jeniskp", pp.getNilaiBaru());
                }
                if (pp.getNamaMedan().equals("kodWarganegara")) {
                    getContext().getRequest().setAttribute("kodWarganegara", pp.getNilaiBaru());
                }
            }
        }

        if (kodUrusan.equals("TA")) {
            return new JSP("common/edit_alamat_pemohon.jsp").addParameter("popup", "true");
        } else {
            return new JSP("common/edit_nama_pemohon.jsp").addParameter("popup", "true");
        }
    }

    public Resolution showTukarFormWaris() {

        String idPihak = getContext().getRequest().getParameter("idPihak");

        String idPemohon = getContext().getRequest().getParameter("id");

        String kodJenisPihak = getContext().getRequest().getParameter("jenis_pihak");

        String idhp = getContext().getRequest().getParameter("idhp");

        pihak = pihakDAO.findById(Long.valueOf(idPihak));

//    Pemohon pemohon = pemohonService.findById(idPemohon);
        List<Permohonan> senaraiPermohonanSblm = permohonanService.getPermohonanSebelum(idKumpulan, permohonan.getKumpulanNo());

        if (StringUtils.isNotBlank(idhp)) {
            hakmilikPihak = hakmilikPihakService.findById(idhp);
        } else {
            hakmilikPihak = hakmilikPihakService
                    .findHakmilikPihakByIdPihakPMPPMG(pihak, hakmilik, kodJenisPihak);
        }

        if (hakmilikPihak != null) {
            pihak = hakmilikPihak.getPihak();
            pihak.setAlamat1(hakmilikPihak.getAlamat1());
            pihak.setAlamat2(hakmilikPihak.getAlamat2());
            pihak.setAlamat3(hakmilikPihak.getAlamat3());
            pihak.setAlamat4(hakmilikPihak.getAlamat4());
            pihak.setPoskod(hakmilikPihak.getPoskod());
            pihak.setNegeri(hakmilikPihak.getNegeri());
            pihak.setNama(hakmilikPihak.getNama());
            pihak.setNoPengenalan(hakmilikPihak.getNoPengenalan());
            //todo ! berangkai? setName, noKp, kodKp
            if (!senaraiPermohonanSblm.isEmpty()) {
                List<String> list = new ArrayList<String>();
                for (Permohonan p1 : senaraiPermohonanSblm) {
                    List<HakmilikWaris> senaraiWaris = hakmilikPihak.getSenaraiWaris();
                    for (HakmilikWaris waris : senaraiWaris) {
                        if (ArrayUtils.contains(list.toArray(), String.valueOf(waris.getIdWaris()))) {
                            continue;
                        }
                        List<PermohonanPihakKemaskini> senaraiKK = permohonanPihakKemaskiniService.getKemaskiniWaris(p1.getIdPermohonan(), String.valueOf(waris.getIdWaris()));
                        if (!senaraiKK.isEmpty()) {
                            list.add(String.valueOf(waris.getIdWaris()));
                        }
                        for (PermohonanPihakKemaskini kk : senaraiKK) {
                            if (kk.getNamaMedan().equals("nama")) {
                                waris.setNama(kk.getNilaiBaru());
                            }
                            if (kk.getNamaMedan().equals("nokp")) {
                                waris.setNoPengenalan(kk.getNilaiBaru());
                            }
                            if (kk.getNamaMedan().equals("jeniskp")) {
                                KodJenisPengenalan kj = kodJenisPengenalanDAO.findById(kk.getNilaiBaru());
                                waris.setJenisPengenalan(kj);
                            }
                            if (kk.getNamaMedan().equals("kodWarganegara")) {
                                KodWarganegara kw = kodWarganegaraDAO.findById(kk.getNilaiBaru());
                                waris.setWargaNegara(kw);
                            }
                        }
                    }
                }
            }
        }

        String namaBaruUbah = "";
        String noKPBaruUbah = "";
        String jenisKPBaruUbah = "";

        senaraiKemaskini = permohonan.getSenaraiPihakKemaskini();
        int i = 0;
        names = new String[hakmilikPihak.getSenaraiWaris().size()];
        no = new String[hakmilikPihak.getSenaraiWaris().size()];
        kod = new String[hakmilikPihak.getSenaraiWaris().size()];

        String currId = "";
        String prevId = "";
        for (PermohonanPihakKemaskini pp : senaraiKemaskini) {
            if (pp.getPemohon() != null) {
                if (!pp.getPemohon().getHakmilik().getIdHakmilik().equals(idHakmilik)
                        || pp.getWarisTerlibat() == null) {
                    continue;
                }
            }
            HakmilikPihakBerkepentingan _PA = pp.getPihakTerlibat();
            if (_PA != null) {
                boolean notSamePA = _PA.getIdHakmilikPihakBerkepentingan() != hakmilikPihak.getIdHakmilikPihakBerkepentingan();
                boolean notSameHm = !_PA.getHakmilik().getIdHakmilik().equals(idHakmilik);
                if (notSamePA || notSameHm) {
                    continue;
                }
            }

            currId = String.valueOf(pp.getWarisTerlibat().getIdWaris());
            if (!prevId.equals("") && !currId.equals(prevId)) {
                i++;
            }

            if (pp.getNamaMedan().equals("rumahAlamat1")) {
                getContext().getRequest()
                        .setAttribute("alamat1_" + pp.getWarisTerlibat().getIdWaris(), pp.getNilaiBaru());
            }
            if (pp.getNamaMedan().equals("rumahAlamat2")) {
                getContext().getRequest()
                        .setAttribute("alamat2_" + pp.getWarisTerlibat().getIdWaris(), pp.getNilaiBaru());
            }
            if (pp.getNamaMedan().equals("rumahAlamat3")) {
                getContext().getRequest().setAttribute("alamat3_" + pp.getWarisTerlibat().getIdWaris(), pp.getNilaiBaru());
            }
            if (pp.getNamaMedan().equals("rumahAlamat4")) {
                getContext().getRequest().setAttribute("alamat4_" + pp.getWarisTerlibat().getIdWaris(), pp.getNilaiBaru());
            }
            if (pp.getNamaMedan().equals("rumahPoskod")) {
                getContext().getRequest().setAttribute("alamat5_" + pp.getWarisTerlibat().getIdWaris(), pp.getNilaiBaru());
            }
            if (pp.getNamaMedan().equals("rumahNegeri.kod")) {
                getContext().getRequest().setAttribute("alamat6_" + pp.getWarisTerlibat().getIdWaris(), pp.getNilaiBaru());
            }
            if (pp.getNamaMedan().equals("nama")) {
                names[i] = pp.getNilaiBaru();
                getContext().getRequest().setAttribute("nama_" + pp.getWarisTerlibat().getIdWaris(), pp.getNilaiBaru());
            }
            if (pp.getNamaMedan().equals("nokp")) {
                no[i] = pp.getNilaiBaru();
                getContext().getRequest().setAttribute("nokp_" + pp.getWarisTerlibat().getIdWaris(), pp.getNilaiBaru());
            }
            if (pp.getNamaMedan().equals("jeniskp")) {
                kod[i] = pp.getNilaiBaru();
                getContext().getRequest().setAttribute("jeniskp_" + pp.getWarisTerlibat().getIdWaris(), pp.getNilaiBaru());
            }
            if (pp.getNamaMedan().equals("kodWarganegara")) {
                warga[i] = pp.getNilaiBaru();
                getContext().getRequest().setAttribute("kodWarganegara" + pp.getWarisTerlibat().getIdWaris(), pp.getNilaiBaru());
            }

            prevId = currId;
        }

        return new JSP("common/edit_nama_pemohon_waris.jsp").addParameter("popup", "true");
    }

    public Resolution saveTukar() {

        List<PermohonanPihakKemaskini> list = new ArrayList<PermohonanPihakKemaskini>();
        List<Pemohon> senaraiPemohonHakmilik = new ArrayList<Pemohon>();
        PermohonanPihakKemaskini mohonPihakKemaskini;
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);

        boolean add1 = false;
        boolean add2 = false;
        boolean add3 = false;
        boolean add4 = false;
        boolean add5 = false;
        boolean add6 = false;
        boolean nm = false;
        boolean noKp = false;
        boolean jenisKp = false;
        boolean jenisPB1 = false;
        boolean kodwarganegara = false;

        String copyToAllHakmiliks = getContext().getRequest().getParameter("copyToAll");

        String alamat1 = getContext().getRequest().getParameter("alamat1");
        String alamat2 = getContext().getRequest().getParameter("alamat2");
        String alamat3 = getContext().getRequest().getParameter("alamat3");
        String alamat4 = getContext().getRequest().getParameter("alamat4");
        String alamat5 = getContext().getRequest().getParameter("alamat5");
        String alamat6 = getContext().getRequest().getParameter("alamat6");

        String alamat1Lama = getContext().getRequest().getParameter("alamat1Lama");
        String alamat2Lama = getContext().getRequest().getParameter("alamat2Lama");
        String alamat3Lama = getContext().getRequest().getParameter("alamat3Lama");
        String alamat4Lama = getContext().getRequest().getParameter("alamat4Lama");
        String alamat5Lama = getContext().getRequest().getParameter("alamat5Lama");
        String alamat6Lama = getContext().getRequest().getParameter("alamat6Lama");

        String nama = getContext().getRequest().getParameter("nama");
        String jeniskp = getContext().getRequest().getParameter("jeniskp");
        String nokp = getContext().getRequest().getParameter("nokp");
        String jenisPB = getContext().getRequest().getParameter("jenisPB");
//        LOG.info("jenis PB = " + jenisPB);
        String kodWarganegara = getContext().getRequest().getParameter("kodWarganegara");

        String namaLama = getContext().getRequest().getParameter("namaLama");
        String jeniskpLama = getContext().getRequest().getParameter("jeniskpLama");
        String nokpLama = getContext().getRequest().getParameter("nokpLama");
        String kodWarganegaraLama = getContext().getRequest().getParameter("kodWarganegaraLama");
        String idPemohon = getContext().getRequest().getParameter("idPemohon");

        String idPihak = getContext().getRequest().getParameter("idPihak");

        if (StringUtils.isNotBlank(idPihak)) {
            pihak = pihakDAO.findById(Long.valueOf(idPihak));
        }

        senaraiPemohon = permohonan.getSenaraiPemohon();
        for (Pemohon pmh : senaraiPemohon) {
            if (pmh.getPihak().getIdPihak() == pihak.getIdPihak()
                    && pmh.getHakmilik().getIdHakmilik().equals(hakmilik.getIdHakmilik())) {
                pemohon = pmh;
                break;
            }
        }

        if (StringUtils.isNotBlank(copyToAllHakmiliks)) {

            KodJenisPihakBerkepentingan jenis = pemohon.getJenis();

            for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                if (hp == null || hp.getHakmilik() == null
                        || hp.getHakmilik().getIdHakmilik().equals(hakmilik.getIdHakmilik())) {
                    continue;
                }
                Hakmilik hm = hp.getHakmilik();
                HakmilikPihakBerkepentingan hpk = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pihak, hm, jenis.getKod());
                if (hpk == null) {
                    error.append("Pihak TIDAK sama pada hakmilik ").append(hm.getIdHakmilik());
                    return new RedirectResolution(PihakKepentinganAction.class, "showFormTukar").addParameter("idHakmilik", idHakmilik);
                } else {

                    boolean contains = ArrayUtils.contains(URUSAN_TO_REPLACE_OWNER, kodUrusan);
                    //semak dulu kalau2 pihak dah ada dalam pemohon
                    Pemohon pmhn = pemohonService.findPemohonByPermohonanPihak(permohonan, pihak, hm, jenis.getKod());
                    if (pmhn == null) {
                        pmhn = new Pemohon();
                        pmhn.setHakmilik(hm);
                        pmhn.setCawangan(hm.getCawangan());
                        pmhn.setInfoAudit(ia);
                        pmhn.setPihak(pihak);
                        pmhn.setPermohonan(permohonan);
                        pmhn.setSyerPembilang(hpk.getSyerPembilang() != null ? hpk.getSyerPembilang() : 0);
                        pmhn.setSyerPenyebut(hpk.getSyerPenyebut() != null ? hpk.getSyerPenyebut() : 0);
                        if (jenis != null) {
                            pmhn.setJenis(jenis);
                        }
                        if (contains) {
                            pmhn.setJenisPemohon("X");
                        } else if (kodUrusan.equals("TA") || kodUrusan.equals("TN") || (melaka && kodUrusan.equals("PH30A"))
                                || kodUrusan.equals("PNKP")) {
                            pmhn.setJenisPemohon("Y"); //tukar nama, alamat dsb
                        }

                        pmhn.setNama(pihak.getNama().toUpperCase());
                        pmhn.setNoPengenalan(pihak.getNoPengenalan());
                        pmhn.setJenisPengenalan(pihak.getJenisPengenalan());
                        pmhn.setWargaNegara(pihak.getWargaNegara());
                        Alamat alamat = new Alamat();
                        alamat.setAlamat1(pihak.getAlamat1());
                        alamat.setAlamat2(pihak.getAlamat2());
                        alamat.setAlamat3(pihak.getAlamat3());
                        alamat.setAlamat4(pihak.getAlamat4());
                        alamat.setPoskod(pihak.getPoskod());
                        alamat.setNegeri(pihak.getNegeri());
                        AlamatSurat alamatSurat = new AlamatSurat();
                        alamatSurat.setAlamatSurat1(pihak.getSuratAlamat1());
                        alamatSurat.setAlamatSurat2(pihak.getSuratAlamat2());
                        alamatSurat.setAlamatSurat3(pihak.getSuratAlamat3());
                        alamatSurat.setAlamatSurat4(pihak.getSuratAlamat4());
                        alamatSurat.setPoskodSurat(pihak.getSuratPoskod());
                        alamatSurat.setNegeriSurat(pihak.getSuratNegeri());
                        pmhn.setAlamat(alamat);
                        pmhn.setAlamatSurat(alamatSurat);

                        pemohonService.saveOrUpdate(pmhn);
                    }
                    senaraiPemohonHakmilik.add(pmhn);
                }
            }
        }

        senaraiPemohonHakmilik.add(pemohon);

        for (Pemohon pmhn : senaraiPemohonHakmilik) {
            if (pmhn == null) {
                continue;
            }
            pemohon = pmhn;
            hakmilik = pmhn.getHakmilik();
            idHakmilik = hakmilik.getIdHakmilik();
            senaraiKemaskini = permohonanPihakKkiniService.getSenaraiPihakKemaskini(idPermohonan, idHakmilik, 0, pihak.getIdPihak());

            if (kodUrusan.equals("TN")) {
                if (idPemohon != null) {
                    for (Pemohon pemhn : senaraiPemohon) {
                        if (String.valueOf(pemhn.getIdPemohon()).equals(idPemohon)) {
                            pemohon = pemhn;
                            senaraiKemaskini = permohonanPihakKkiniService.getSenaraiPihakKemaskiniTN(idPermohonan, idHakmilik, pemohon.getIdPemohon(), pihak.getIdPihak());
                        }
                    }
                }
            }
            
            if (kodUrusan.equals("TA")) {
                if (idPemohon != null) {
                    for (Pemohon pemhn : senaraiPemohon) {
                        if (String.valueOf(pemhn.getIdPemohon()).equals(idPemohon)) {
                            pemohon = pemhn;
                            senaraiKemaskini = permohonanPihakKkiniService.getSenaraiPihakKemaskiniTN(idPermohonan, idHakmilik, pemohon.getIdPemohon(), pihak.getIdPihak());
                            LOG.info("TA");
                        }
                    }
                }
            }
            List<PermohonanAtasPerserahan> mohonAtasPerserahanList = permohonanAtasPerserahanService.findMohonAtasUrusanByIDPermohonanAndIDHakmilikList(idPermohonan);
            LOG.info("mohonAtasPerserahanList = :" + mohonAtasPerserahanList.size());
            List<PermohonanPihakKemaskini> senaraiPihakKkini = permohonanPihakKemaskiniService.findByIdPermohonan(idPermohonan);
            for (PermohonanAtasPerserahan mohonAtasPerserahan : mohonAtasPerserahanList) {
                if (senaraiKemaskini.isEmpty()) {
                    if (alamat1 != null) {
                        if (StringUtils.isNotBlank(alamat1) && !alamat1.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat1");
                            mohonPihakKemaskini.setNilaiLama(alamat1Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat1);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        } else if (alamat1.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat1");
                            mohonPihakKemaskini.setNilaiLama(alamat1Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat2 != null) {
                        if (StringUtils.isNotBlank(alamat2) && !alamat2.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 2");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat2");
                            mohonPihakKemaskini.setNilaiLama(alamat2Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat2);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        } else if (alamat2.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat2");
                            mohonPihakKemaskini.setNilaiLama(alamat2Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat3 != null) {
                        if (StringUtils.isNotBlank(alamat3) && !alamat3.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 3");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat3");
                            mohonPihakKemaskini.setNilaiLama(alamat3Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat3);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        } else if (alamat3.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat3");
                            mohonPihakKemaskini.setNilaiLama(alamat3Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat4 != null) {
                        if (StringUtils.isNotBlank(alamat4) && !alamat4.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 4");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat4");
                            mohonPihakKemaskini.setNilaiLama(alamat4Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat4);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        } else if (alamat4.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat4");
                            mohonPihakKemaskini.setNilaiLama(alamat4Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat5 != null) {
                        if (StringUtils.isNotBlank(alamat5) && !alamat5.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 5");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahPoskod");
                            mohonPihakKemaskini.setNilaiLama(alamat5Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat5);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        } else if (alamat5.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahPoskod");
                            mohonPihakKemaskini.setNilaiLama(alamat5Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat6 != null) {
                        if (StringUtils.isNotBlank(alamat6) && !alamat6.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 6");
                            mohonPihakKemaskini.setNamaMedan("rumahNegeri.kod");

                            if (StringUtils.isNotBlank(alamat6Lama)) {
                                mohonPihakKemaskini.setNilaiLama(alamat6Lama);
                            } else {
                                mohonPihakKemaskini.setNilaiLama(null);
                            }
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNilaiBaru(alamat6);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        } else if (alamat6.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahNegeri.kod");
                            mohonPihakKemaskini.setNilaiLama(alamat6Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }

                            list.add(mohonPihakKemaskini);
                        }
                    }

                    if (StringUtils.isNotBlank(nama)) {

                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save nama");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("nama");
                        mohonPihakKemaskini.setNilaiLama(namaLama);
                        mohonPihakKemaskini.setNilaiBaru(nama);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);
                        if (mohonAtasPerserahan != null) {
                            LOG.info("idmohonBaru = " + mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                            LOG.info("idmohon = " + mohonAtasPerserahan.getPermohonan().getIdPermohonan());
                            mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                            mohonPihakKemaskini.setPihak(pihak);
                        }
                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(nokp)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save nokp");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("nokp");
                        mohonPihakKemaskini.setNilaiLama(nokpLama);
                        mohonPihakKemaskini.setNilaiBaru(nokp);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);
                        if (mohonAtasPerserahan != null) {
                            mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                            mohonPihakKemaskini.setPihak(pihak);
                        }
                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(jeniskp)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save jenisKP");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("jeniskp");
                        mohonPihakKemaskini.setNilaiLama(jeniskpLama);
                        mohonPihakKemaskini.setNilaiBaru(jeniskp);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);
                        if (mohonAtasPerserahan != null) {
                            mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                            mohonPihakKemaskini.setPihak(pihak);
                        }
                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(kodWarganegara)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save kodWarganegara");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("kodWarganegara");
                        mohonPihakKemaskini.setNilaiLama(kodWarganegaraLama);
                        mohonPihakKemaskini.setNilaiBaru(kodWarganegara);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);
                        if (mohonAtasPerserahan != null) {
                            mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                            mohonPihakKemaskini.setPihak(pihak);
                        }
                        list.add(mohonPihakKemaskini);
                    }
                } else {
                    permohonanPihakKemaskiniService.delete(senaraiKemaskini);
                    if (alamat1 != null) {
                        if (StringUtils.isNotBlank(alamat1) && !alamat1.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat1");
                            mohonPihakKemaskini.setNilaiLama(alamat1Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat1);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        } else if (alamat1.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat1");
                            mohonPihakKemaskini.setNilaiLama(alamat1Lama);
                            mohonPihakKemaskini.setNilaiBaru(null);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat2 != null) {
                        if (StringUtils.isNotBlank(alamat2) && !alamat2.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 2");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat2");
                            mohonPihakKemaskini.setNilaiLama(alamat2Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat2);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        } else if (alamat2.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat2");
                            mohonPihakKemaskini.setNilaiLama(alamat2Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat3 != null) {
                        if (StringUtils.isNotBlank(alamat3) && !alamat3.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 3");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat3");
                            mohonPihakKemaskini.setNilaiLama(alamat3Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat3);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        } else if (alamat3.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat3");
                            mohonPihakKemaskini.setNilaiLama(alamat3Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat4 != null) {
                        if (StringUtils.isNotBlank(alamat4) && !alamat4.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 4");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat4");
                            mohonPihakKemaskini.setNilaiLama(alamat4Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat4);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        } else if (alamat4.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat4");
                            mohonPihakKemaskini.setNilaiLama(alamat4Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat5 != null) {
                        if (StringUtils.isNotBlank(alamat5) && !alamat5.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 5");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahPoskod");
                            mohonPihakKemaskini.setNilaiLama(alamat5Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat5);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        } else if (alamat5.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahPoskod");
                            mohonPihakKemaskini.setNilaiLama(alamat5Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat6 != null) {
                        if (StringUtils.isNotBlank(alamat6) && !alamat6.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 6");
                            mohonPihakKemaskini.setNamaMedan("rumahNegeri.kod");

                            if (StringUtils.isNotBlank(alamat6Lama)) {
                                mohonPihakKemaskini.setNilaiLama(alamat6Lama);
                            } else {
                                mohonPihakKemaskini.setNilaiLama(null);
                            }
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNilaiBaru(alamat6);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        } else if (alamat6.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahNegeri.kod");
                            mohonPihakKemaskini.setNilaiLama(alamat6);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            if (mohonAtasPerserahan != null) {
                                mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                                mohonPihakKemaskini.setPihak(pihak);
                            }
                            list.add(mohonPihakKemaskini);
                        }
                    }

                    if (StringUtils.isNotBlank(nama)) {

                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save nama");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("nama");
                        mohonPihakKemaskini.setNilaiLama(namaLama);
                        mohonPihakKemaskini.setNilaiBaru(nama);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);
                        if (mohonAtasPerserahan != null) {
                            LOG.info("idmohonBaru = " + mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                            LOG.info("idmohon = " + mohonAtasPerserahan.getPermohonan().getIdPermohonan());
                            mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                            mohonPihakKemaskini.setPihak(pihak);
                        }
                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(nokp)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save nokp");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("nokp");
                        mohonPihakKemaskini.setNilaiLama(nokpLama);
                        mohonPihakKemaskini.setNilaiBaru(nokp);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);
                        if (mohonAtasPerserahan != null) {
                            mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                            mohonPihakKemaskini.setPihak(pihak);
                        }
                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(jeniskp)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save jenisKP");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("jeniskp");
                        mohonPihakKemaskini.setNilaiLama(jeniskpLama);
                        mohonPihakKemaskini.setNilaiBaru(jeniskp);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);
                        if (mohonAtasPerserahan != null) {
                            mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                            mohonPihakKemaskini.setPihak(pihak);
                        }
                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(kodWarganegara)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save kodWarganegara");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("kodWarganegara");
                        mohonPihakKemaskini.setNilaiLama(kodWarganegaraLama);
                        mohonPihakKemaskini.setNilaiBaru(kodWarganegara);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);
                        if (mohonAtasPerserahan != null) {
                            mohonPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                            mohonPihakKemaskini.setPihak(pihak);
                        }
                        list.add(mohonPihakKemaskini);
                    }
                }
            }
            if (mohonAtasPerserahanList.size() <= 0) {

                if (senaraiKemaskini.isEmpty()) {
                    if (alamat1 != null) {
                        if (StringUtils.isNotBlank(alamat1) && !alamat1.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat1");
                            mohonPihakKemaskini.setNilaiLama(alamat1Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat1);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());

                            list.add(mohonPihakKemaskini);
                        } else if (alamat1.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat1");
                            mohonPihakKemaskini.setNilaiLama(alamat1Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());

                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat2 != null) {
                        if (StringUtils.isNotBlank(alamat2) && !alamat2.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 2");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat2");
                            mohonPihakKemaskini.setNilaiLama(alamat2Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat2);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);

                            list.add(mohonPihakKemaskini);
                        } else if (alamat2.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat2");
                            mohonPihakKemaskini.setNilaiLama(alamat2Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());

                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat3 != null) {
                        if (StringUtils.isNotBlank(alamat3) && !alamat3.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 3");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat3");
                            mohonPihakKemaskini.setNilaiLama(alamat3Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat3);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);

                            list.add(mohonPihakKemaskini);
                        } else if (alamat3.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat3");
                            mohonPihakKemaskini.setNilaiLama(alamat3Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());

                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat4 != null) {
                        if (StringUtils.isNotBlank(alamat4) && !alamat4.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 4");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat4");
                            mohonPihakKemaskini.setNilaiLama(alamat4Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat4);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);

                            list.add(mohonPihakKemaskini);
                        } else if (alamat4.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat4");
                            mohonPihakKemaskini.setNilaiLama(alamat4Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());

                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat5 != null) {
                        if (StringUtils.isNotBlank(alamat5) && !alamat5.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 5");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahPoskod");
                            mohonPihakKemaskini.setNilaiLama(alamat5Lama);
                            mohonPihakKemaskini.setNilaiBaru(alamat5);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);

                            list.add(mohonPihakKemaskini);
                        } else if (alamat5.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahPoskod");
                            mohonPihakKemaskini.setNilaiLama(alamat5Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());

                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (alamat6 != null) {
                        if (StringUtils.isNotBlank(alamat6) && !alamat6.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 6");
                            mohonPihakKemaskini.setNamaMedan("rumahNegeri.kod");

                            if (StringUtils.isNotBlank(alamat6Lama)) {
                                mohonPihakKemaskini.setNilaiLama(alamat6Lama);
                            } else {
                                mohonPihakKemaskini.setNilaiLama(null);
                            }
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNilaiBaru(alamat6);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());
                            mohonPihakKemaskini.setPemohon(pemohon);

                            list.add(mohonPihakKemaskini);
                        } else if (alamat6.equals("-")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahNegeri.kod");
                            mohonPihakKemaskini.setNilaiLama(alamat6Lama);
                            mohonPihakKemaskini.setNilaiBaru(" ");
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());

                            list.add(mohonPihakKemaskini);
                        }
                    }

                    if (StringUtils.isNotBlank(nama)) {

                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save nama");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("nama");
                        mohonPihakKemaskini.setNilaiLama(namaLama);
                        mohonPihakKemaskini.setNilaiBaru(nama);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(nokp)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save nokp");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("nokp");
                        mohonPihakKemaskini.setNilaiLama(nokpLama);
                        mohonPihakKemaskini.setNilaiBaru(nokp);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(jeniskp)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save jenisKP");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("jeniskp");
                        mohonPihakKemaskini.setNilaiLama(jeniskpLama);
                        mohonPihakKemaskini.setNilaiBaru(jeniskp);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(kodWarganegara)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save kodWarganegara");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("kodWarganegara");
                        mohonPihakKemaskini.setNilaiLama(kodWarganegaraLama);
                        mohonPihakKemaskini.setNilaiBaru(kodWarganegara);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }
                } else {
                    permohonanPihakKemaskiniService.delete(senaraiKemaskini);

                    if (StringUtils.isNotBlank(alamat1)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save alamat 1");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("rumahAlamat1");
                        mohonPihakKemaskini.setNilaiLama(alamat1Lama);
                        mohonPihakKemaskini.setNilaiBaru(alamat1);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setPemohon(pemohon);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());

                        list.add(mohonPihakKemaskini);
                    } else if (StringUtils.isBlank(alamat1) || alamat1.equals("-")) {
                        if (!kodUrusan.equals("TN")) {
                            mohonPihakKemaskini = new PermohonanPihakKemaskini();
                            LOG.debug("save alamat 1");
                            mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                            mohonPihakKemaskini.setNamaMedan("rumahAlamat1");
                            mohonPihakKemaskini.setNilaiLama(alamat1Lama);
                            mohonPihakKemaskini.setNilaiBaru(null);
                            mohonPihakKemaskini.setPermohonan(permohonan);
                            mohonPihakKemaskini.setInfoAudit(ia);
                            mohonPihakKemaskini.setPemohon(pemohon);
                            mohonPihakKemaskini.setJenis(pemohon.getJenis());

                            list.add(mohonPihakKemaskini);
                        }
                    }
                    if (StringUtils.isNotBlank(alamat2)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save alamat 2");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("rumahAlamat2");
                        mohonPihakKemaskini.setNilaiLama(alamat2Lama);
                        mohonPihakKemaskini.setNilaiBaru(alamat2);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(alamat3)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save alamat 3");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("rumahAlamat3");
                        mohonPihakKemaskini.setNilaiLama(alamat3Lama);
                        mohonPihakKemaskini.setNilaiBaru(alamat3);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(alamat4)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save alamat 4");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("rumahAlamat4");
                        mohonPihakKemaskini.setNilaiLama(alamat4Lama);
                        mohonPihakKemaskini.setNilaiBaru(alamat4);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(alamat5)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save alamat 5");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("rumahPoskod");
                        mohonPihakKemaskini.setNilaiLama(alamat5Lama);
                        mohonPihakKemaskini.setNilaiBaru(alamat5);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(alamat6)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save alamat 6");
                        mohonPihakKemaskini.setNamaMedan("rumahNegeri.kod");

                        if (StringUtils.isNotBlank(alamat6Lama)) {
                            mohonPihakKemaskini.setNilaiLama(alamat6Lama);
                        } else {
                            mohonPihakKemaskini.setNilaiLama(null);
                        }
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNilaiBaru(alamat6);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }

                    if (StringUtils.isNotBlank(nama)) {

                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save nama");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("nama");
                        mohonPihakKemaskini.setNilaiLama(namaLama);
                        mohonPihakKemaskini.setNilaiBaru(nama);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(nokp)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save nokp");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("nokp");
                        mohonPihakKemaskini.setNilaiLama(nokpLama);
                        mohonPihakKemaskini.setNilaiBaru(nokp);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(jeniskp)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save jenisKP");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("jeniskp");
                        mohonPihakKemaskini.setNilaiLama(jeniskpLama);
                        mohonPihakKemaskini.setNilaiBaru(jeniskp);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }
                    if (StringUtils.isNotBlank(kodWarganegara)) {
                        mohonPihakKemaskini = new PermohonanPihakKemaskini();
                        LOG.debug("save kodWarganegara");
                        mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                        mohonPihakKemaskini.setNamaMedan("kodWarganegara");
                        mohonPihakKemaskini.setNilaiLama(kodWarganegaraLama);
                        mohonPihakKemaskini.setNilaiBaru(kodWarganegara);
                        mohonPihakKemaskini.setPermohonan(permohonan);
                        mohonPihakKemaskini.setInfoAudit(ia);
                        mohonPihakKemaskini.setJenis(pemohon.getJenis());
                        mohonPihakKemaskini.setPemohon(pemohon);

                        list.add(mohonPihakKemaskini);
                    }
                }

            }
        }
        if (!list.isEmpty()) {
            permohonanPihakKkiniService.save(list);

            for (PermohonanPihakKemaskini mohonPihakKkini : list) {
                if (mohonPihakKkini.getPemohon() != null) {
                    Pemohon pemohonEdit = pemohonService.findById(String.valueOf(mohonPihakKkini.getPemohon().getIdPemohon()));
                    if (pemohonEdit != null) {
                    }

                }

            }
//            for (PermohonanPihakKemaskini mohonPihakKkini : list) {
//                permohonanPihakKemaskiniDAO.save(mohonPihakKkini);
//            }

        }

        return new RedirectResolution(PihakKepentinganAction.class, "showFormTukar").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution saveTukarWaris() {

        String[] cbs = getContext().getRequest().getParameterValues("cb");

        List<PermohonanPihakKemaskini> list = new ArrayList<PermohonanPihakKemaskini>();

        if (cbs != null && cbs.length > 0) {
            int i = 0;
            for (String cb : cbs) {

                String _nama = getContext().getRequest().getParameter("name_waris_" + cb);
                String _kod = getContext().getRequest().getParameter("jeniskp_waris_" + cb);
                String _no = getContext().getRequest().getParameter("no_pengenalan_waris_" + cb);

                String _namalama = getContext().getRequest().getParameter("name_waris_lama_" + cb);
                String _kodlama = getContext().getRequest().getParameter("jeniskp_waris_lama_" + cb);
                String _nolama = getContext().getRequest().getParameter("no_pengenalan_waris_lama_" + cb);

                List<PermohonanPihakKemaskini> senarai = permohonanPihakKemaskiniService.getKemaskiniWaris(idPermohonan, cb);

                if (senarai != null && !senarai.isEmpty()) {
                    boolean _n = false;
                    boolean _k = false;
                    boolean _n1 = false;
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(TODAY);
                    HakmilikWaris waris = hakmilikWarisService.getWaris(cb);

                    for (PermohonanPihakKemaskini kk : senarai) {
                        if (kk.getNamaMedan().equals("nama")) {
                            _n = true;
                            kk.setNilaiBaru(_nama);
                        }
                        if (kk.getNamaMedan().equals("nokp")) {
                            _n1 = true;
                            kk.setNilaiBaru(_no);
                        }
                        if (kk.getNamaMedan().equals("jeniskp")) {
                            _k = true;
                            kk.setNilaiBaru(_kod);
                        }
                        list.add(kk);
                    }

                    if (!_n
                            && StringUtils.isNotBlank(_nama)) {
                        PermohonanPihakKemaskini kk = new PermohonanPihakKemaskini();
                        kk.setPermohonan(permohonan);
                        kk.setWarisTerlibat(waris);
                        kk.setPihakTerlibat(waris.getPemegangAmanah());
                        kk.setNamaMedan("nama");
                        kk.setNilaiLama(_namalama);
                        kk.setNilaiBaru(_nama);
                        kk.setInfoAudit(ia);
                        list.add(kk);
                    }

                    if (!_n1
                            && StringUtils.isNotBlank(_kod)) {
                        PermohonanPihakKemaskini kk = new PermohonanPihakKemaskini();
                        kk.setPermohonan(permohonan);
                        kk.setWarisTerlibat(waris);
                        kk.setPihakTerlibat(waris.getPemegangAmanah());
                        kk.setNamaMedan("nokp");
                        kk.setNilaiLama(_nolama);
                        kk.setNilaiBaru(_no);
                        kk.setInfoAudit(ia);
                        list.add(kk);
                    }

                    if (!_k
                            && StringUtils.isNotBlank(_no)) {
                        PermohonanPihakKemaskini kk = new PermohonanPihakKemaskini();
                        kk.setPermohonan(permohonan);
                        kk.setWarisTerlibat(waris);
                        kk.setPihakTerlibat(waris.getPemegangAmanah());
                        kk.setNamaMedan("jeniskp");
                        kk.setNilaiLama(_kodlama);
                        kk.setNilaiBaru(_kod);
                        kk.setInfoAudit(ia);
                        list.add(kk);
                    }

                } else {
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(TODAY);
                    HakmilikWaris waris = hakmilikWarisService.getWaris(cb);
                    PermohonanPihakKemaskini kk = new PermohonanPihakKemaskini();

                    if (StringUtils.isNotBlank(_nama)) {
                        kk = new PermohonanPihakKemaskini();
                        kk.setPermohonan(permohonan);
                        kk.setWarisTerlibat(waris);
                        kk.setPihakTerlibat(waris.getPemegangAmanah());
                        kk.setNamaMedan("nama");
                        kk.setNilaiLama(_namalama);
                        kk.setNilaiBaru(_nama);
                        kk.setInfoAudit(ia);
                        list.add(kk);
                    }

                    if (StringUtils.isNotBlank(_no)) {
                        kk = new PermohonanPihakKemaskini();
                        kk.setPermohonan(permohonan);
                        kk.setWarisTerlibat(waris);
                        kk.setPihakTerlibat(waris.getPemegangAmanah());
                        kk.setNamaMedan("nokp");
                        kk.setNilaiLama(_nolama);
                        kk.setNilaiBaru(_no);
                        kk.setInfoAudit(ia);
                        list.add(kk);
                    }

                    if (StringUtils.isNotBlank(_kod)) {
                        kk = new PermohonanPihakKemaskini();
                        kk.setPermohonan(permohonan);
                        kk.setWarisTerlibat(waris);
                        kk.setPihakTerlibat(waris.getPemegangAmanah());
                        kk.setNamaMedan("jeniskp");
                        kk.setNilaiLama(_kodlama);
                        kk.setNilaiBaru(_kod);
                        kk.setInfoAudit(ia);
                        list.add(kk);
                    }
                }
                i++;
            }
        }

        if (!list.isEmpty()) {
            permohonanPihakKkiniService.save(list);
        }

        return new RedirectResolution(PihakKepentinganAction.class, "showFormTukar")
                .addParameter("idHakmilik", idHakmilik);
    }

    public Resolution showEditPemohonPenerimaForm() {

        String id = ctx.getRequest().getParameter("id");
        String idPihak = ctx.getRequest().getParameter("idPihak");
        String JENIS_PIHAK = ctx.getRequest().getParameter("jenis_pihak");
        String RETURN_JSP = "daftar/kemaskini_pemohon.jsp";

        pihak = pihakService.findById(idPihak);
        LOG.debug("--> JENIS_PIHAK: " + JENIS_PIHAK);
        if (JENIS_PIHAK.equals("pemohon")) {
            pemohon = pemohonService.findById(id);
            if (pemohon == null) {
                PermohonanAtasPihakBerkepentingan papb = permohonanAtasPihakService.findById(id);
                if (papb != null) {
                    pemohon = new Pemohon();
                    pemohon.setPihak(papb.getPihakBerkepentingan().getPihak());
                }
            }

            if (isBerangkai) {
                LOG.debug("-------masuk isBerangkai");
                List<Permohonan> senaraiPermohonanSblm = permohonanService.getPermohonanSebelum(idKumpulan, permohonan.getKumpulanNo());

                for (Permohonan p : senaraiPermohonanSblm) {
                    if (p == null) {
                        continue;
                    }
                    idPermohonan = p.getIdPermohonan();
                    List<PermohonanPihakKemaskini> senaraiPPK = permohonanPihakKkiniService.getSenaraiPihakKemaskini(idPermohonan, idHakmilik, 0, pihak.getIdPihak());
                    if (senaraiPPK.size() > 0) {
                        for (int i = 0; i < senaraiPPK.size(); i++) {
                            PermohonanPihakKemaskini ppk = senaraiPPK.get(i);
                            if (ppk != null) {
                                if (ppk.getNamaMedan().equals("rumahAlamat1")) {
                                    pihak.setAlamat1(ppk.getNilaiBaru());
                                }
                                if (ppk.getNamaMedan().equals("rumahAlamat2")) {
                                    pihak.setAlamat2(ppk.getNilaiBaru());
                                }
                                if (ppk.getNamaMedan().equals("rumahAlamat3")) {
                                    pihak.setAlamat3(ppk.getNilaiBaru());
                                }
                                if (ppk.getNamaMedan().equals("rumahAlamat4")) {
                                    pihak.setAlamat4(ppk.getNilaiBaru());
                                }
                                if (ppk.getNamaMedan().equals("rumahPoskod")) {
                                    pihak.setPoskod(ppk.getNilaiBaru());
                                }
                                if (ppk.getNamaMedan().equals("rumahNegeri.kod")) {
                                    KodNegeri kn = kodNegeriDAO.findById(ppk.getNilaiBaru());
                                    pihak.setNegeri(kn);
                                }
                                if (ppk.getNamaMedan().equals("nama")) {
                                    String nama =  ppk.getNilaiBaru().replaceAll("'", "_"); 
                                    String namaBaru = nama.replaceAll("_", "'");
                                    pihak.setNama(namaBaru);
                                }
                                if (ppk.getNamaMedan().equals("nokp")) {
                                    pihak.setNoPengenalan(ppk.getNilaiBaru());
                                }
                                if (ppk.getNamaMedan().equals("jeniskp")) {
                                    KodJenisPengenalan kj = kodJenisPengenalanDAO.findById(ppk.getNilaiBaru());
                                    pihak.setJenisPengenalan(kj);
                                }
                            }
                        }
                    }
                }
            }

        } else if (JENIS_PIHAK.equals("penerima")) {
            RETURN_JSP = "daftar/kemaskini_penerima.jsp";
            permohonanPihak = permohonanPihakService.findById(id);
//            if (kodUrusan.equals("PNPA")) {

            if (permohonanPihak.getJenis() != null
                    && (permohonanPihak.getJenis().getKod().equals("PA")
                    || permohonanPihak.getJenis().getKod().equals("PP"))) {
                senaraiPihakHubungan = permohonanPihak.getSenaraiHubungan();
                ctx.getRequest().setAttribute("disabledWaris", "false");

            } else if (kodUrusan.equals("TRPA")) {
                ctx.getRequest().setAttribute("disabledWaris", "true");
            }

            if (isBerangkai) {

                List<Permohonan> senaraiPermohonanSblm = permohonanService.getPermohonanSebelum(idKumpulan, permohonan.getKumpulanNo());

                for (Permohonan p : senaraiPermohonanSblm) {
                    if (p == null) {
                        continue;
                    }
                    idPermohonan = p.getIdPermohonan();
                    List<PermohonanPihakKemaskini> senaraiPPK = permohonanPihakKkiniService.getSenaraiPihakKemaskini(idPermohonan, idHakmilik, 0, pihak.getIdPihak());
                    if (senaraiPPK.size() > 0) {
                        for (int i = 0; i < senaraiPPK.size(); i++) {
                            PermohonanPihakKemaskini ppk = senaraiPPK.get(i);
                            if (ppk != null) {
                                if (ppk.getNamaMedan().equals("rumahAlamat1")) {
                                    pihak.setAlamat1(ppk.getNilaiBaru());
                                }
                                if (ppk.getNamaMedan().equals("rumahAlamat2")) {
                                    pihak.setAlamat2(ppk.getNilaiBaru());
                                }
                                if (ppk.getNamaMedan().equals("rumahAlamat3")) {
                                    pihak.setAlamat3(ppk.getNilaiBaru());
                                }
                                if (ppk.getNamaMedan().equals("rumahAlamat4")) {
                                    pihak.setAlamat4(ppk.getNilaiBaru());
                                }
                                if (ppk.getNamaMedan().equals("rumahPoskod")) {
                                    pihak.setPoskod(ppk.getNilaiBaru());
                                }
                                if (ppk.getNamaMedan().equals("rumahNegeri.kod")) {
                                    KodNegeri kn = kodNegeriDAO.findById(ppk.getNilaiBaru());
                                    pihak.setNegeri(kn);
                                }
                                if (ppk.getNamaMedan().equals("nama")) {
                                    String nama =  ppk.getNilaiBaru().replaceAll("'", "_"); 
                                    String namaBaru = nama.replaceAll("_", "'");
                                    pihak.setNama(namaBaru);
                                }
                                if (ppk.getNamaMedan().equals("nokp")) {
                                    pihak.setNoPengenalan(ppk.getNilaiBaru());
                                }
                                if (ppk.getNamaMedan().equals("jeniskp")) {
                                    KodJenisPengenalan kj = kodJenisPengenalanDAO.findById(ppk.getNilaiBaru());
                                    pihak.setJenisPengenalan(kj);
                                }
                            }
                        }
                    }
                }
            }
        }

        kodUrusan = ctx.getRequest().getParameter("kodUrusan");
        LOG.info("kodUrusan" + kodUrusan);

        if (StringUtils.isNotBlank(kodUrusan) && kodUrusan.startsWith("SM")) {
            return new JSP("daftar/kemaskini_penerima_mcl.jsp").addParameter("popup", "true");
        } else {
            return new JSP(RETURN_JSP).addParameter("popup", "true");
        }
    }

    public Resolution savePihakPopup() {
        LOG.debug("----- Save pihak Popup start----------");
        String copyToAllHakmilik = getContext().getRequest().getParameter("copyToAll");
        String jenisPihak = getContext().getRequest().getParameter("jenisPihak");
        String tarikhLahir = getContext().getRequest().getParameter("tarikhLahir");
        String syerPembilang = getContext().getRequest().getParameter("syerPembilang");
        String syerPenyebut = getContext().getRequest().getParameter("syerPenyebut");
        String syerDikongsi = getContext().getRequest().getParameter("syerKongsi");
        String kodUrusan = getContext().getRequest().getParameter("kodUrusan");
        LOG.debug("----- copyToAllHakmilik: " + copyToAllHakmilik);
        LOG.debug("----- jenisPihak: " + jenisPihak);
        LOG.debug("----- tarikhLahir: " + tarikhLahir);
        LOG.debug("----- syerPembilang: " + syerPembilang);
        LOG.debug("----- syerPenyebut: " + syerPenyebut);
        LOG.debug("----- syerDikongsi: " + syerDikongsi);
        LOG.debug("----- kodUrusan: " + kodUrusan);

        String pilih = ctx.getRequest().getParameter("pilih");

        if (isDebug) {
            LOG.debug("pilih =" + pilih);
        }

        if (StringUtils.isBlank(jenisPihak)) {
            addSimpleError("Sila masukan jenis pihak berkepentingan.");
            return new JSP(MAIN_PAGE_POPUP).addParameter("popup", "true");
        }

        KodJenisPihakBerkepentingan jenis = kodJenisPihakBerkepentinganDAO.findById(jenisPihak);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);

        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
        if (pihakTemp == null) {

//            pihakTemp = pihakService
//                    .findPihakByNamaPengenalanKodPengenalan(pihak.getNama(),
//                            pihak.getNoPengenalan(), pihak.getJenisPengenalan().getKod());
            if (pihakTemp == null) {
                pihakTemp = new Pihak();
                pihakTemp.setInfoAudit(ia);

                if (StringUtils.isNotBlank(tarikhLahir)) {
                    try {
                        pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                    } catch (ParseException ex) {
                        LOG.error("exception: " + ex.getMessage());
                        addSimpleError("Sila masukan tarikh lahir mengikut format dd/mm/yyyy.");
                        return new ErrorResolution(404, "Sila masukan tarikh lahir mengikut format dd/mm/yyyy.");
                    }
                }
                savePihak(pihakTemp, ia, "BARU");
            } else {
                savePihak(pihakTemp, ia, "LAMA");
            }
        } else {
            savePihak(pihakTemp, ia, "LAMA");
        }

        PermohonanPihak pp = new PermohonanPihak();
        if (hakmilik != null) {
            pp.setHakmilik(hakmilik);
            pp.setCawangan(hakmilik.getCawangan());
        } else {
            pp.setCawangan(pengguna.getKodCawangan());
        }
        pp.setJenis(jenis);
        pp.setInfoAudit(ia);
        BigDecimal bd = new BigDecimal(0);
        if (permohonanPihak != null) {
            pp.setKaitan(permohonanPihak.getKaitan() != null ? permohonanPihak.getKaitan() : "");
            pp.setUmur(permohonanPihak.getUmur() != null ? permohonanPihak.getUmur() : 0);
            pp.setStatusKahwin(permohonanPihak.getStatusKahwin() != null ? permohonanPihak.getStatusKahwin() : "");
            pp.setPekerjaan(permohonanPihak.getPekerjaan() != null ? permohonanPihak.getPekerjaan() : "");
            pp.setPendapatan(permohonanPihak.getPendapatan() != null ? permohonanPihak.getPendapatan() : bd);
            pp.setTangungan(permohonanPihak.getTangungan() != null ? permohonanPihak.getTangungan() : 0);
            if (StringUtils.isNotBlank(syerPembilang)) {
                pp.setSyerPembilang(Integer.valueOf(syerPembilang));
            } else {
                pp.setSyerPembilang(1);
            }
            if (StringUtils.isNotBlank(syerPenyebut)) {
                pp.setSyerPenyebut(Integer.valueOf(syerPenyebut));
            } else {
                pp.setSyerPenyebut(1);
            }
        }

        pp.setNama(pihak.getNama() != null ? pihak.getNama().toUpperCase() : "");
        pp.setNoPengenalan(pihak.getNoPengenalan());
        pp.setJenisPengenalan(pihak.getJenisPengenalan());
        pp.setPenubuhanSyarikat(pihak.getPenubuhanSyarikat());
        pp.setWargaNegara(pihak.getWargaNegara());
        Alamat alamat = new Alamat();
        alamat.setAlamat1(pihak.getAlamat1());
        alamat.setAlamat2(pihak.getAlamat2());
        alamat.setAlamat3(pihak.getAlamat3());
        alamat.setAlamat4(pihak.getAlamat4());
        alamat.setPoskod(pihak.getPoskod());
        alamat.setNegeri(pihak.getNegeri());
        pp.setAlamat(alamat);
        if (pihakAlamatTamb != null) {
            AlamatSurat alamatSurat = new AlamatSurat();
            alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
            alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
            alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
            alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
            alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
            alamatSurat.setNegeriSurat(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
            pp.setAlamatSurat(alamatSurat);
        }

        if (StringUtils.isNotBlank(syerDikongsi)) {
            pp.setSyerBersama(syerDikongsi.charAt(0));
        }
        pp.setPihak(pihakTemp);
        pp.setPermohonan(permohonan);
        LOG.debug("---->  id mohon_pihak pp " + pp.getIdPermohonanPihak());
        LOG.debug("---->  id pihak pp " + pp.getPihak().getIdPihak());
        permohonanPihakService.saveOrUpdate(pp);

        PihakCawangan pc = null;

        if (StringUtils.isNotBlank(pilih)) {
            if (!pilih.startsWith("pc_")) {
                pc = pihakService.findPihakCawangan(pilih);
            }
            pp.setPihakCawangan(pc);
        }
        permohonanPihakService.saveOrUpdate(pp);

        //to calculate jumPembilang, jumPenyebut if pihak same
        permohonanPihakService.calculateJumSyer(idHakmilik, pihakTemp.getIdPihak(), jenisPihak, pp, isBerangkai);

        if (kodUrusan.equals("KVAT")) { //pemohon ada lah mohon_pihak
            pemohon = pemohonService.findPemohonByPermohonanPihak(permohonan, pihakTemp, hakmilik, jenis.getKod());

            if (pemohon == null) {
                pemohon = new Pemohon();
                pemohon.setPihak(pihakTemp);
                pemohon.setPermohonan(permohonan);
                pemohon.setJenis(jenis);
                pemohon.setHakmilik(hakmilik);
                pemohon.setCawangan(hakmilik.getCawangan());
                if (pc != null) {
                    pemohon.setPihakCawangan(pc);
                }
                if (StringUtils.isNotBlank(syerPembilang)) {
                    pemohon.setSyerPembilang(Integer.valueOf(syerPembilang));
                } else {
                    pemohon.setSyerPembilang(1);
                }
                if (StringUtils.isNotBlank(syerPenyebut)) {
                    pemohon.setSyerPenyebut(Integer.valueOf(syerPenyebut));
                } else {
                    pemohon.setSyerPenyebut(1);
                }

                pemohon.setNama(pihak.getNama());
                pemohon.setNoPengenalan(pihak.getNoPengenalan());
                pemohon.setJenisPengenalan(pihak.getJenisPengenalan());
                pemohon.setWargaNegara(pihak.getWargaNegara());
                alamat = new Alamat();
                alamat.setAlamat1(pihak.getAlamat1());
                alamat.setAlamat2(pihak.getAlamat2());
                alamat.setAlamat3(pihak.getAlamat3());
                alamat.setAlamat4(pihak.getAlamat4());
                alamat.setPoskod(pihak.getPoskod());
                alamat.setNegeri(pihak.getNegeri());
                if (pihakAlamatTamb != null) {
                    AlamatSurat alamatSurat = new AlamatSurat();
                    alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
                    alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
                    alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
                    alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
                    alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
                    alamatSurat.setNegeriSurat(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
                    pp.setAlamatSurat(alamatSurat);
                }
                pemohon.setAlamat(alamat);
                pemohon.setInfoAudit(ia);
                pemohonService.saveOrUpdate(pemohon);
            }
        }

        LOG.info("@:@:@:@:@:@ KOD URUSAN @:@:@:@:@:" + kodUrusan);
        if (kodUrusan.startsWith("SM")) {
            saveWarisInfo2(ctx.getRequest().getParameterMap(), ia, pp);
        } else {
            saveWarisInfo(ctx.getRequest().getParameterMap(), ia, pp);
        }

        if (StringUtils.isNotBlank(copyToAllHakmilik)) {
            for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik hm = hp.getHakmilik();
                if (hm.getIdHakmilik().equals(hakmilik.getIdHakmilik())) {
                    continue;
                }

                pp = new PermohonanPihak();
                pp.setHakmilik(hm);
                pp.setCawangan(hm.getCawangan());
                pp.setJenis(jenis);
                pp.setInfoAudit(ia);
                if (permohonanPihak != null) {

                    pp.setKaitan(permohonanPihak.getKaitan() != null ? permohonanPihak.getKaitan() : "");
                    pp.setUmur(permohonanPihak.getUmur() != null ? permohonanPihak.getUmur() : 0);
                    pp.setStatusKahwin(permohonanPihak.getStatusKahwin() != null ? permohonanPihak.getStatusKahwin() : "");
                    pp.setPekerjaan(permohonanPihak.getPekerjaan() != null ? permohonanPihak.getPekerjaan() : "");

                    senaraiPermohonanPihak = permohonanPihakService
                            .getAllSenaraiPmohonPihakByHakmilikAndMohon(permohonan.getIdPermohonan(), hm.getIdHakmilik());
                    senaraiPemohon = pemohonService
                            .senaraiPemohonByIdPermohonanIdHakmilik(permohonan.getIdPermohonan(), hm.getIdHakmilik());

                    Fraction syerSemuaPemohon = Fraction.ONE;
                    Fraction syerSemuaPermohonanPihak = Fraction.ONE;

                    for (Pemohon p : senaraiPemohon) {
                        if (p.getSyerPenyebut() == 0) {
                            continue;
                        }
                        if (StringUtils.isNotBlank(hm.getIdHakmilik())
                                && p.getHakmilik().getIdHakmilik().equals(hm.getIdHakmilik())) {
                            syerSemuaPemohon = syerSemuaPemohon.add(new Fraction(p.getSyerPembilang(), p.getSyerPenyebut()));
                        }
                    }

                    for (PermohonanPihak ppk : senaraiPermohonanPihak) {
                        if (ppk.getSyerPenyebut() == 0) {
                            continue;
                        }
                        if (StringUtils.isNotBlank(hm.getIdHakmilik())
                                && ppk.getHakmilik().getIdHakmilik().equals(hm.getIdHakmilik())) {
                            syerSemuaPermohonanPihak = syerSemuaPermohonanPihak.add(new Fraction(ppk.getSyerPembilang(), ppk.getSyerPenyebut()));
                        }
                    }

                    Fraction baki = syerSemuaPemohon.subtract(syerSemuaPermohonanPihak).abs();
                    int bakiSyerPenyebut = 0;
                    int bakiSyerPembilang = 0;
                    if (baki.compareTo(Fraction.ZERO) > 0) {
                        bakiSyerPenyebut = baki.getDenominator();
                        bakiSyerPembilang = baki.getNumerator();
                    }

                    pp.setSyerPembilang(bakiSyerPembilang);
                    pp.setSyerPenyebut(bakiSyerPenyebut);

//                    pp.setSyerPembilang(permohonanPihak.getSyerPembilang());
//                    pp.setSyerPenyebut(permohonanPihak.getSyerPenyebut());
//              if (StringUtils.isNotBlank(syerPembilang)) {
//                  pp.setSyerPembilang(Integer.valueOf(syerPembilang));
//              } else {
//                  pp.setSyerPembilang(1);
//              }
//              if (StringUtils.isNotBlank(syerPenyebut)) {
//                  pp.setSyerPenyebut(Integer.valueOf(syerPenyebut));
//              } else {
//                  pp.setSyerPenyebut(1);
//              }
//                    List<PermohonanPihakHubungan> list = permohonanPihak.getSenaraiHubungan();
//                    if (!list.isEmpty()) pp.setSenaraiHubungan(list);
                }

                if (StringUtils.isNotBlank(syerDikongsi)) {
                    pp.setSyerBersama(syerDikongsi.charAt(0));
                }

                pp.setNama(pihak.getNama() != null ? pihak.getNama().toUpperCase() : "");
                pp.setNoPengenalan(pihak.getNoPengenalan());
                pp.setJenisPengenalan(pihak.getJenisPengenalan());
                pp.setWargaNegara(pihak.getWargaNegara());
                pp.setPendapatan(permohonanPihak.getPendapatan());
                pp.setTangungan(permohonanPihak.getTangungan());
                alamat = new Alamat();
                alamat.setAlamat1(pihak.getAlamat1());
                alamat.setAlamat2(pihak.getAlamat2());
                alamat.setAlamat3(pihak.getAlamat3());
                alamat.setAlamat4(pihak.getAlamat4());
                alamat.setPoskod(pihak.getPoskod());
                alamat.setNegeri(pihak.getNegeri());
                if (pihakAlamatTamb != null) {
                    AlamatSurat alamatSurat = new AlamatSurat();
                    alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
                    alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
                    alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
                    alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
                    alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
                    alamatSurat.setNegeriSurat(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
                    pp.setAlamatSurat(alamatSurat);
                }
                pp.setAlamat(alamat);
                pp.setPihak(pihakTemp);
                pp.setPermohonan(permohonan);

                if (StringUtils.isNotBlank(pilih)) {
                    pp.setPihakCawangan(pc);
                }

                permohonanPihakService.saveOrUpdate(pp);

                saveWarisInfo(ctx.getRequest().getParameterMap(), ia, pp);

                if (kodUrusan.equals("KVAT")) { //pemohon ada lah mohon_pihak
                    pemohon = pemohonService.findPemohonByPermohonanPihak(permohonan, pihakTemp, hm, jenis.getKod());

                    if (pemohon == null) {
                        pemohon = new Pemohon();
                        pemohon.setPihak(pihakTemp);
                        pemohon.setPermohonan(permohonan);
                        pemohon.setJenis(jenis);
                        pemohon.setHakmilik(hm);
                        pemohon.setCawangan(hm.getCawangan());
                        if (pc != null) {
                            pemohon.setPihakCawangan(pc);
                        }
                        if (StringUtils.isNotBlank(syerPembilang)) {
                            pemohon.setSyerPembilang(Integer.valueOf(syerPembilang));
                        } else {
                            pemohon.setSyerPembilang(1);
                        }
                        if (StringUtils.isNotBlank(syerPenyebut)) {
                            pemohon.setSyerPenyebut(Integer.valueOf(syerPenyebut));
                        } else {
                            pemohon.setSyerPenyebut(1);
                        }

                        pemohon.setNama(pihak.getNama() != null ? pihak.getNama().toUpperCase() : "");
                        pemohon.setNoPengenalan(pihak.getNoPengenalan());
                        pemohon.setJenisPengenalan(pihak.getJenisPengenalan());
                        pemohon.setWargaNegara(pihak.getWargaNegara());
                        alamat = new Alamat();
                        alamat.setAlamat1(pihak.getAlamat1());
                        alamat.setAlamat2(pihak.getAlamat2());
                        alamat.setAlamat3(pihak.getAlamat3());
                        alamat.setAlamat4(pihak.getAlamat4());
                        alamat.setPoskod(pihak.getPoskod());
                        alamat.setNegeri(pihak.getNegeri());
                        if (pihakAlamatTamb != null) {
                            AlamatSurat alamatSurat = new AlamatSurat();
                            alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
                            alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
                            alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
                            alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
                            alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
                            alamatSurat.setNegeriSurat(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
                            pp.setAlamatSurat(alamatSurat);
                        }
                        pemohon.setAlamat(alamat);
                        pemohon.setInfoAudit(ia);
                        pemohonService.saveOrUpdate(pemohon);
                    }
                }
                permohonanPihakService.calculateJumSyer(hm.getIdHakmilik(), pihakTemp.getIdPihak(), jenisPihak, pp, isBerangkai);
            }
        }

        if (kodUrusan.equals("PNPA") || kodUrusan.equals("TRPA")) {
            return new RedirectResolution(PihakKepentinganAction.class, "pihakPemegangAmanah").addParameter("idHakmilik", idHakmilik);
        } else if (kodUrusan.equals("KVST") || kodUrusan.equals("KVSPT")) {
            return new RedirectResolution(PihakKepentinganAction.class, "showFormKVST").addParameter("idHakmilik", idHakmilik);
        } else if (kodUrusan.startsWith("SM")) {
//            return new RedirectResolution(PihakKepentinganAction.class, "showFormMCL").addParameter("idHakmilik", idHakmilik);
            return new RedirectResolution(PihakKepentinganAction.class, "showFormMCL");
        } else if (kodUrusan.equals("PJT") || kodUrusan.equals("PJTM") || kodUrusan.equals("PJKT")
                || kodUrusan.equals("PJKBT") || kodUrusan.equals("PJBT")
                || kodUrusan.equals("TEN")
                //                || kodUrusan.equals("TENBT")
                || kodUrusan.equals("TENPT")
                || kodUrusan.equals("PJLT")) {
            return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakKepentinganPajakanForm").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(PihakKepentinganAction.class).addParameter("idHakmilik", idHakmilik);
        }

    }

    @HandlesEvent("simpanKemaskiniPemohon")
    public Resolution savePemohon() {
        LOG.debug("--------- SAVE PEMOHON ");
        Session ses = sessionProvider.get();
        Transaction tx = ses.beginTransaction();
        tx.begin();
        InfoAudit ia = new InfoAudit();
        try {

            Pihak pihakTemp = pihakService.findById(String.valueOf(pihak.getIdPihak()));
            savePihak(pihakTemp, ia, "LAMA");

            if (pemohon != null && pemohon.getIdPemohon() > 0) {
                Pemohon pmhn = pemohonService.findById(String.valueOf(pemohon.getIdPemohon()));
                if (pmhn != null) {
                    ia = pmhn.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(TODAY);
                    pmhn.setInfoAudit(ia);
                    pmhn.setKaitan(pemohon.getKaitan());
                    pmhn.setTanggungan(pemohon.getTanggungan());
                    pmhn.setStatusKahwin(pemohon.getStatusKahwin());
                    pmhn.setPendapatan(pemohon.getPendapatan());
                    pmhn.setPekerjaan(pemohon.getPekerjaan());
                    pmhn.setUmur(pemohon.getUmur());
                    pmhn.setNama(pihak.getNama().toUpperCase());
                    pmhn.setNoPengenalan(pihak.getNoPengenalan());
                    pmhn.setJenisPengenalan(pihak.getJenisPengenalan());
                    Alamat alamat = new Alamat();
                    alamat.setAlamat1(pemohon.getAlamat().getAlamat1());
                    alamat.setAlamat2(pemohon.getAlamat().getAlamat2());
                    alamat.setAlamat3(pemohon.getAlamat().getAlamat3());
                    alamat.setAlamat4(pemohon.getAlamat().getAlamat4());
                    alamat.setPoskod(pemohon.getAlamat().getPoskod());
                    alamat.setNegeri(pemohon.getAlamat().getNegeri());
                    if (pemohon.getAlamatSurat() != null) {
                        AlamatSurat alamatSurat = new AlamatSurat();
                        alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pemohon.getAlamatSurat().getAlamatSurat1()) ? pemohon.getAlamatSurat().getAlamatSurat1() : "");
                        alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pemohon.getAlamatSurat().getAlamatSurat2()) ? pemohon.getAlamatSurat().getAlamatSurat2() : "");
                        alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pemohon.getAlamatSurat().getAlamatSurat3()) ? pemohon.getAlamatSurat().getAlamatSurat3() : "");
                        alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pemohon.getAlamatSurat().getAlamatSurat4()) ? pemohon.getAlamatSurat().getAlamatSurat4() : "");
                        alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pemohon.getAlamatSurat().getPoskodSurat()) ? pemohon.getAlamatSurat().getPoskodSurat() : "");
                        alamatSurat.setNegeriSurat(pemohon.getAlamatSurat().getNegeriSurat() != null ? pemohon.getAlamatSurat().getNegeriSurat() : null);
                        pmhn.setAlamatSurat(alamatSurat);
                    }
                    pmhn.setAlamat(alamat);

                    pemohonService.saveWithoutConnection(pmhn);
                }
            }

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        if (kodUrusan.startsWith("IS")) {
            return new RedirectResolution(PihakKepentinganAction.class, "pihakIsmen");
        } else if (kodUrusan.equals("PNPA") || kodUrusan.equals("TRPA")) {
            return new RedirectResolution(PihakKepentinganAction.class, "pihakPemegangAmanah").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(PihakKepentinganAction.class).addParameter("idHakmilik", idHakmilik);
        }
    }

    @HandlesEvent("simpanKemaskiniPenerima")
    public Resolution savePenerima() {

        LOG.debug("idHakmilik = " + idHakmilik);

        Session ses = sessionProvider.get();
        Transaction tx = ses.beginTransaction();
        tx.begin();
        InfoAudit ia = new InfoAudit();
        try {
            LOG.debug("idPihak =" + String.valueOf(pihak.getIdPihak()));

            Pihak pihakTemp = pihakService.findById(String.valueOf(pihak.getIdPihak()));
            savePihak(pihakTemp, ia, "LAMA");
            PermohonanPihak pp = permohonanPihakService.findById(String.valueOf(permohonanPihak.getIdPermohonanPihak()));
            if (pp != null) {

                ia = pp.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(TODAY);
                pp.setInfoAudit(ia);
                pp.setJenis(permohonanPihak.getJenis());
                pp.setUmur(permohonanPihak.getUmur());
                pp.setPekerjaan(permohonanPihak.getPekerjaan());
                pp.setPendapatan(permohonanPihak.getPendapatan());
                pp.setStatusKahwin(permohonanPihak.getStatusKahwin());
                pp.setTangungan(permohonanPihak.getTangungan());
                pp.setKaitan(permohonanPihak.getKaitan());
                pp.setNama(permohonanPihak.getNama().toUpperCase());
                pp.setNoPengenalan(permohonanPihak.getNoPengenalan());
                pp.setJenisPengenalan(permohonanPihak.getJenisPengenalan());
                pp.setWargaNegara(pihak.getWargaNegara());
                Alamat alamat = new Alamat();
                if (permohonanPihak.getAlamat() != null) {
                    alamat.setAlamat1(permohonanPihak.getAlamat().getAlamat1());
                    alamat.setAlamat2(permohonanPihak.getAlamat().getAlamat2());
                    alamat.setAlamat3(permohonanPihak.getAlamat().getAlamat3());
                    alamat.setAlamat4(permohonanPihak.getAlamat().getAlamat4());
                    alamat.setPoskod(permohonanPihak.getAlamat().getPoskod());
                    alamat.setNegeri(permohonanPihak.getAlamat().getNegeri());
                }
                AlamatSurat alamatSurat = new AlamatSurat();
                if (permohonanPihak.getAlamatSurat() != null) {
                    alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(permohonanPihak.getAlamatSurat().getAlamatSurat1()) ? permohonanPihak.getAlamatSurat().getAlamatSurat1() : "");
                    alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(permohonanPihak.getAlamatSurat().getAlamatSurat2()) ? permohonanPihak.getAlamatSurat().getAlamatSurat2() : "");
                    alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(permohonanPihak.getAlamatSurat().getAlamatSurat3()) ? permohonanPihak.getAlamatSurat().getAlamatSurat3() : "");
                    alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(permohonanPihak.getAlamatSurat().getAlamatSurat4()) ? permohonanPihak.getAlamatSurat().getAlamatSurat4() : "");
                    alamatSurat.setPoskodSurat(StringUtils.isNotBlank(permohonanPihak.getAlamatSurat().getPoskodSurat()) ? permohonanPihak.getAlamatSurat().getPoskodSurat() : "");
                    alamatSurat.setNegeriSurat(permohonanPihak.getAlamatSurat().getNegeriSurat() != null ? permohonanPihak.getAlamatSurat().getNegeriSurat() : null);
                }

                pp.setAlamat(alamat);
                pp.setAlamatSurat(alamatSurat);
                pp.setPenubuhanSyarikat(pihak.getPenubuhanSyarikat());
                pp.setNama(permohonanPihak.getNama().toUpperCase());
                pp.setJenisPengenalan(permohonanPihak.getJenisPengenalan());
                pp.setNoPengenalan(permohonanPihak.getNoPengenalan());
                permohonanPihakService.saveWithoutConnection(pp);

                saveWarisInfo(ctx.getRequest().getParameterMap(), ia, pp);
            }

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }

        if (kodUrusan.startsWith("IS")) {
            return new RedirectResolution(PihakKepentinganAction.class, "pihakIsmen");
        } else if (kodUrusan.equals("PNPA") || kodUrusan.equals("TRPA")) {
            return new RedirectResolution(PihakKepentinganAction.class, "pihakPemegangAmanah").addParameter("idHakmilik", idHakmilik);
        } else if (kodUrusan.equals("KVST") || kodUrusan.equals("KVSPT")) {
            return new RedirectResolution(PihakKepentinganAction.class, "showFormKVST").addParameter("idHakmilik", idHakmilik);
        } else if (kodUrusan.startsWith("SM")) {
            return new RedirectResolution(PihakKepentinganAction.class, "showFormMCL").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(PihakKepentinganAction.class).addParameter("idHakmilik", idHakmilik);
        }
    }

    public Resolution deletePihakCaw() {

        String idPihak = ctx.getRequest().getParameter("idPihak");
        String idPihakCaw = ctx.getRequest().getParameter("idPihakCaw");

        if (StringUtils.isNotBlank(idPihakCaw)) {
            PihakCawangan pc = pihakService.findPihakCawangan(idPihakCaw);
            if (pc != null) {
                pihakService.deletePihakCaw(pc);
            }
        }

        return new RedirectResolution(PihakKepentinganAction.class, "selectPihak").addParameter("idPihak", idPihak);
    }

    @HandlesEvent("saveOrUpdatePihakCaw")
    public Resolution simpanPihakCawangan() {

        String idPihak = ctx.getRequest().getParameter("idPihak");
        String idPihakCaw = ctx.getRequest().getParameter("idPihakCaw");

        String namaCaw = ctx.getRequest().getParameter("namaCaw");
        String add1 = ctx.getRequest().getParameter("add1");
        String add2 = ctx.getRequest().getParameter("add2");
        String add3 = ctx.getRequest().getParameter("add3");
        String add4 = ctx.getRequest().getParameter("add4");
        String poskod = ctx.getRequest().getParameter("poskod");
        String negeri = ctx.getRequest().getParameter("negeri");
        String addSurat1 = ctx.getRequest().getParameter("surat1");
        String addSurat2 = ctx.getRequest().getParameter("surat2");
        String addSurat3 = ctx.getRequest().getParameter("surat3");
        String addSurat4 = ctx.getRequest().getParameter("surat4");
        String poskodSurat = ctx.getRequest().getParameter("poskodSurat");
        String negeriSurat = ctx.getRequest().getParameter("negeriSurat");
        String noTel = ctx.getRequest().getParameter("telCaw");

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);

        if (StringUtils.isNotBlank(idPihak)) {
            pihak = pihakService.findById(idPihak);
        } else {
            Pihak pihakTemp = new Pihak();
            pihakTemp.setInfoAudit(ia);
            savePihak(pihakTemp, ia, "BARU");
            pihak = pihakTemp;
            idPihak = String.valueOf(pihak.getIdPihak());
        }

        PihakCawangan pc = null;
        if (StringUtils.isNotBlank(idPihakCaw)) {
            pc = pihakService.findPihakCawangan(idPihakCaw);
        } else {
            pc = new PihakCawangan();
        }

        pc.setIbupejabat(pihak);
        pc.setInfoAudit(ia);
        pc.setNamaCawangan(StringUtils.isNotBlank(namaCaw) ? namaCaw : "");
        pc.setAlamat1(StringUtils.isNotBlank(add1) ? add1 : "");
        pc.setAlamat2(StringUtils.isNotBlank(add2) ? add2 : "");
        pc.setAlamat3(StringUtils.isNotBlank(add3) ? add3 : "");
        pc.setAlamat4(StringUtils.isNotBlank(add4) ? add4 : "");
        pc.setPoskod(StringUtils.isNotBlank(poskod) ? poskod : "");
        pc.setNegeri(StringUtils.isNotBlank(negeri) ? kodNegeriDAO.findById(negeri) : null);
        pc.setSuratAlamat1(StringUtils.isNotBlank(addSurat1) ? addSurat1 : "");
        pc.setSuratAlamat2(StringUtils.isNotBlank(addSurat2) ? addSurat2 : "");
        pc.setSuratAlamat3(StringUtils.isNotBlank(addSurat3) ? addSurat3 : "");
        pc.setSuratAlamat4(StringUtils.isNotBlank(addSurat4) ? addSurat4 : "");
        pc.setSuratPoskod(StringUtils.isNotBlank(poskodSurat) ? poskodSurat : "");
        pc.setSuratNegeri(StringUtils.isNotBlank(negeriSurat) ? kodNegeriDAO.findById(negeriSurat) : null);
        pc.setNoTelefon1(StringUtils.isNotBlank(noTel) ? noTel : "");

        pihakService.saveOrUpdatePihakCawangan(pc);

        return new RedirectResolution(PihakKepentinganAction.class, "selectPihak").addParameter("idPihak", idPihak);
    }

    @HandlesEvent("saveOrUpdateWaris")
    public Resolution simpanWaris() {
        String idPihak = ctx.getRequest().getParameter("idPihak");
        String id = ctx.getRequest().getParameter("id");
        String jenisPihak = ctx.getRequest().getParameter("jenisPihak");

        PermohonanPihakHubungan waris = null;
        List<PermohonanPihakHubungan> senaraiWaris = new ArrayList<PermohonanPihakHubungan>();
        senaraiPihak = new ArrayList<Pihak>();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);

        String kp = ctx.getRequest().getParameter("kpPA");
        String noKP = ctx.getRequest().getParameter("noKpPA");
        String namaPA = ctx.getRequest().getParameter("namaPA");
        String warganegaraPA = ctx.getRequest().getParameter("wargaPA");
        String add1PA = ctx.getRequest().getParameter("add1PA");
        String add2PA = ctx.getRequest().getParameter("add2PA");
        String add3PA = ctx.getRequest().getParameter("add3PA");
        String add4PA = ctx.getRequest().getParameter("add4PA");
        String poskodPA = ctx.getRequest().getParameter("poskodPA");
        String negeriPA = ctx.getRequest().getParameter("negeriPA");
        String syerPembilang = ctx.getRequest().getParameter("syerPembilangAmanah");
        String syerPenyebut = ctx.getRequest().getParameter("syerPenyebutAmanah");

        if (StringUtils.isNotBlank(kp)
                && StringUtils.isNotBlank(noKP)) {
            Pihak phk = pihakService.findPihak(kp, noKP);
            if (phk == null) {
                phk = new Pihak();
                phk.setAlamat1(StringUtils.isNotBlank(add1PA) ? add1PA : "");
                phk.setAlamat2(StringUtils.isNotBlank(add2PA) ? add2PA : "");
                phk.setAlamat3(StringUtils.isNotBlank(add3PA) ? add3PA : "");
                phk.setAlamat4(StringUtils.isNotBlank(add4PA) ? add4PA : "");
                phk.setPoskod(StringUtils.isNotBlank(poskodPA) ? poskodPA : "");
                phk.setNegeri(StringUtils.isNotBlank(negeriPA) ? kodNegeriDAO.findById(negeriPA) : null);
                phk.setNama(StringUtils.isNotBlank(namaPA) ? namaPA.toUpperCase() : "");
                phk.setWargaNegara(kodWarganegaraDAO.findById("MAL"));
                phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp));
                phk.setNoPengenalan(noKP);
                phk.setInfoAudit(ia);
                senaraiPihak.add(phk);
            }

            if (StringUtils.isNotBlank(id)) {
                waris = permohonanPihakHubunganService.findById(id);
            } else {
                waris = new PermohonanPihakHubungan();
            }

            waris.setPihak(permohonanPihak);
            waris.setInfoAudit(ia);
            waris.setNama(StringUtils.isNotBlank(namaPA) ? namaPA.toUpperCase() : "");
            waris.setAlamat1(StringUtils.isNotBlank(add1PA) ? add1PA : "");
            waris.setAlamat2(StringUtils.isNotBlank(add2PA) ? add2PA : "");
            waris.setAlamat3(StringUtils.isNotBlank(add3PA) ? add3PA : "");
            waris.setAlamat4(StringUtils.isNotBlank(add4PA) ? add4PA : "");
            waris.setNegeri(StringUtils.isNotBlank(negeriPA) ? kodNegeriDAO.findById(negeriPA) : null);
            waris.setPoskod(StringUtils.isNotBlank(poskodPA) ? poskodPA : "");
            waris.setCawangan(pengguna.getKodCawangan());
            waris.setWargaNegara(StringUtils.isNotBlank(warganegaraPA) ? kodWarganegaraDAO.findById(warganegaraPA) : null);
            waris.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp));
            waris.setNoPengenalan(noKP);
            waris.setKaitan(permohonanPihak.getKaitan());
            waris.setSyerPembilang(StringUtils.isNotBlank(syerPembilang) ? Integer.parseInt(syerPembilang) : 0);
            waris.setSyerPenyebut(StringUtils.isNotBlank(syerPenyebut) ? Integer.parseInt(syerPenyebut) : 0);
            senaraiWaris.add(waris);
        }

        if (senaraiPihak.size() > 0) {
            pihakService.saveOrUpdate(senaraiPihak);
        }

        if (senaraiWaris.size() > 0) {
            permohonanPihakHubunganService.save(senaraiWaris);
        }

        return new RedirectResolution(PihakKepentinganAction.class, "selectPihak").addParameter("idPihak", idPihak).addParameter("jenisPihak", jenisPihak);
    }

    public Resolution deletePihakHubungan() {
        String id = ctx.getRequest().getParameter("id");
        String source = ctx.getRequest().getParameter("source");

        if (StringUtils.isNotBlank(id)) {
            PermohonanPihakHubungan pihakHubungan = permohonanPihakHubunganService.findById(id);
            permohonanPihakHubunganService.delete(pihakHubungan);
        }
        if ("kemaskiniPenerima".equals(source)) {

            String idPermohonanPihak = ctx.getRequest().getParameter("idPermohonPihak");
            String idPihak = ctx.getRequest().getParameter("idPihak");

            return new RedirectResolution(PihakKepentinganAction.class, "showEditPemohonPenerimaForm").addParameter("jenis_pihak", "penerima").addParameter("id", idPermohonanPihak).addParameter("idPihak", idPihak);
        }
        return new RedirectResolution(PihakKepentinganAction.class);
    }

    public Resolution semakSyer() {
        LOG.debug("semak syer invoked.");
        String msg = "Semakan berjaya.";

        Fraction syer1 = Fraction.ONE;
        Fraction syerBaru;

        for (PermohonanPihak pp : senaraiPermohonanPihak) {
            LOG.debug("syer =" + pp.getSyerPembilang() + "/" + pp.getSyerPenyebut());
            syerBaru = new Fraction(pp.getSyerPenyebut(), pp.getSyerPembilang());
            syer1.add(syerBaru);
        }

        LOG.debug("syer = " + syer1.getNumerator() + "/" + syer1.getDenominator());
        int result = syer1.compareTo(Fraction.ONE);

        switch (result) {
            case -1:
                msg = "Syer kurang.";
                break;
            case 1:
                msg = "Syer lebih";
                break;
            default:
                msg = "Semakan berjaya.";
        }
        return new StreamingResolution("text/plain", msg);
    }

    private void savePihak(Pihak pihakTemp, InfoAudit ia, String type) {
        LOG.debug("---- savePihak");
        if (pihakTemp != null) {
            ia = pihakTemp.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(TODAY);

            if ("BARU".equals(type)) {

                pihakTemp.setNama(pihak.getNama().toUpperCase());
                pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
                pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
                pihakTemp.setKodJantina(pihak.getKodJantina());
                pihakTemp.setBangsa(pihak.getBangsa());
                pihakTemp.setSuku(pihak.getSuku());
                pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
                pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
                pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
                pihakTemp.setWargaNegara(pihak.getWargaNegara());
                pihakTemp.setAlamat1(pihak.getAlamat1());
                pihakTemp.setAlamat2(pihak.getAlamat2());
                pihakTemp.setAlamat3(pihak.getAlamat3());
                pihakTemp.setAlamat4(pihak.getAlamat4());
                pihakTemp.setPoskod(pihak.getPoskod());
                pihakTemp.setNegeri(pihak.getNegeri());
                pihakTemp.setAlamat1(pihak.getAlamat1());
                pihakTemp.setAlamat2(pihak.getAlamat2());
                pihakTemp.setAlamat3(pihak.getAlamat3());
                pihakTemp.setAlamat4(pihak.getAlamat4());
                pihakTemp.setPoskod(pihak.getPoskod());
                pihakTemp.setNegeri(pihak.getNegeri());
                if (pihakAlamatTamb != null) {
                    pihakTemp.setSuratAlamat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
                    pihakTemp.setSuratAlamat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
                    pihakTemp.setSuratAlamat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
                    pihakTemp.setSuratAlamat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
                    pihakTemp.setSuratPoskod(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
                    pihakTemp.setSuratNegeri(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
                }

                pihakTemp.setInfoAudit(ia);
                pihakTemp.setTempatLahir(pihak.getTempatLahir());
                pihakTemp.setPenubuhanSyarikat(pihak.getPenubuhanSyarikat());
                //Added by Aizuddin for MCL
                if (pihak.getNoTelefon1() != null) {
                    pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
                }
                if (pihak.getNoTelefon2() != null) {
                    pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
                }
                if (pihak.getEmail() != null) {
                    pihakTemp.setEmail(pihak.getEmail());
                }
                if (pihak.getModalBerbayar() != null) {
                    pihakTemp.setModalBerbayar(pihak.getModalBerbayar());
                }

                pihakTemp.setKodFlagPihak(kodFlagPihakDAO.findById("AK"));

            } else {

                LOG.debug("alamat surat 1" + pihak.getSuratAlamat1());

                if (pihak.getWargaNegara() != null) {
                    pihakTemp.setWargaNegara(kodWarganegaraDAO.findById(pihak.getWargaNegara().getKod()));
                }
                if (pihak.getJenisPengenalan() != null) {
                    pihakTemp.setJenisPengenalan(kodJenisPengenalanDAO.findById(pihak.getJenisPengenalan().getKod()));
                }
                //Added by Aizuddin for MCL
                if (pihak.getNoTelefon1() != null) {
                    pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
                }
                if (pihak.getNoTelefon2() != null) {
                    pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
                }
                if (pihak.getEmail() != null) {
                    pihakTemp.setEmail(pihak.getEmail());
                }
                if (pihak.getModalBerbayar() != null) {
                    pihakTemp.setModalBerbayar(pihak.getModalBerbayar());
                }

//        pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
                pihakTemp.setAlamat1(pihak.getAlamat1());
                pihakTemp.setAlamat2(pihak.getAlamat2());
                pihakTemp.setAlamat3(pihak.getAlamat3());
                pihakTemp.setAlamat4(pihak.getAlamat4());
                pihakTemp.setPoskod(pihak.getPoskod());
                pihakTemp.setNegeri(pihak.getNegeri());
                //new : cannot update surat alamat !! 15/2/2013
//                pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
//                pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
//                pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
//                pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
//                pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
//                pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
                pihakTemp.setBangsa(pihak.getBangsa());
                pihakTemp.setPenubuhanSyarikat(pihak.getPenubuhanSyarikat());

                if (StringUtils.isNotBlank(pihak.getKodJantina())) {
                    pihakTemp.setKodJantina(pihak.getKodJantina());
                }
                if (pihak.getNegeriKelahiran() != null) {
                    pihakTemp.setNegeriKelahiran(pihak.getNegeriKelahiran());
                }

                pihakTemp.setInfoAudit(ia);
            }
            pihakService.saveOrUpdate(pihakTemp);
            savePihakAlamat(pihakTemp, pihakAlamatTamb);
        }

    }

    private PihakCawangan savePihakCaw(Map<String, String[]> param, InfoAudit ia, Pihak pihak, String caw) {

        PihakCawangan pihakCawangan = new PihakCawangan();

        int p = 0;

        if (StringUtils.isNotBlank(caw)) {
//            p = Integer.parseInt(caw.substring(caw.indexOf("pc_"), caw.length()));
            caw = caw.substring((caw.indexOf("_") + 1), caw.length());
            p = Integer.parseInt(caw);
        }

        String[] namaCaw = param.get("namaCaw");
        String[] add1 = param.get("add1Caw");
        String[] add2 = param.get("add2Caw");
        String[] add3 = param.get("add3Caw");
        String[] add4 = param.get("add4Caw");
        String[] poskod = param.get("poskodCaw");
        String[] negeri = param.get("negeriCaw");
        String[] addSurat1 = param.get("surat1");
        String[] addSurat2 = param.get("surat2");
        String[] addSurat3 = param.get("surat3");
        String[] addSurat4 = param.get("surat4");
        String[] poskodSurat = param.get("poskodSurat");
        String[] negeriSurat = param.get("negeriSurat");
        String[] noTel = param.get("telCaw");

        List<PihakCawangan> senaraiPihakCawangan = new ArrayList<PihakCawangan>();

        if (etanah.util.StringUtils.isNotBlank(namaCaw)) {
            for (int i = 0; i < namaCaw.length; i++) {

                PihakCawangan pc = new PihakCawangan();
                if (p == i) {
                    pihakCawangan = pc;
                }
                pc.setIbupejabat(pihak);
                pc.setInfoAudit(ia);
                pc.setNamaCawangan(StringUtils.isNotBlank(namaCaw[i]) ? namaCaw[i] : "");
                pc.setAlamat1(StringUtils.isNotBlank(add1[i]) ? add1[i] : "");
                pc.setAlamat2(StringUtils.isNotBlank(add2[i]) ? add2[i] : "");
                pc.setAlamat3(StringUtils.isNotBlank(add3[i]) ? add3[i] : "");
                pc.setAlamat4(StringUtils.isNotBlank(add4[i]) ? add4[i] : "");
                pc.setPoskod(StringUtils.isNotBlank(poskod[i]) ? poskod[i] : "");
                pc.setNegeri(StringUtils.isNotBlank(negeri[i]) ? kodNegeriDAO.findById(negeri[i]) : null);
                pc.setSuratAlamat1(StringUtils.isNotBlank(addSurat1[i]) ? addSurat1[i] : "");
                pc.setSuratAlamat2(StringUtils.isNotBlank(addSurat2[i]) ? addSurat2[i] : "");
                pc.setSuratAlamat3(StringUtils.isNotBlank(addSurat3[i]) ? addSurat3[i] : "");
                pc.setSuratAlamat4(StringUtils.isNotBlank(addSurat4[i]) ? addSurat4[i] : "");
                pc.setSuratPoskod(StringUtils.isNotBlank(poskodSurat[i]) ? poskodSurat[i] : "");
                pc.setSuratNegeri(StringUtils.isNotBlank(negeriSurat[i]) ? kodNegeriDAO.findById(negeriSurat[i]) : null);
                pc.setNoTelefon1(StringUtils.isNotBlank(noTel[i]) ? noTel[i] : "");
                senaraiPihakCawangan.add(pc);
            }
        }

        if (senaraiPihakCawangan.size() > 0) {
            pihakService.saveOrUpdatePihakCawangan(senaraiPihakCawangan);
        }
        return pihakCawangan;
    }

    private void saveWarisInfo(Map<String, String[]> param,
            InfoAudit ia, PermohonanPihak permohonanPihak) {

        PermohonanPihakHubungan waris = null;
        List<PermohonanPihakHubungan> senaraiWaris = new ArrayList<PermohonanPihakHubungan>();
        List<Pihak> senaraiPihak = new ArrayList<Pihak>();

        String[] kps = param.get("kpPA");
        String[] noKPs = param.get("noKpPA");
        String[] namaPAs = param.get("namaPA");
        String namaPAs1 = context.getRequest().getParameter("namaPA");
        String namaPAs2 = context.getRequest().getParameter("nama");
        String[] warganegaraPAs = param.get("wargaPA");
        String[] add1PAs = param.get("add1PA");
        String[] add2PAs = param.get("add2PA");
        String[] add3PAs = param.get("add3PA");
        String[] add4PAs = param.get("add4PA");
        String[] poskodPAs = param.get("poskodPA");
        String[] negeriPAs = param.get("negeriPA");
        String[] syerPembilangs = param.get("syerPembilangAmanah");
        String[] syerPenyebuts = param.get("syerPenyebutAmanah");

        if (etanah.util.StringUtils.isNotBlank(kps)) {
            for (int i = 0; i < kps.length; i++) {

                String kp_ = kps[i];
                String noKp_ = noKPs[i];
                if (StringUtils.isNotBlank(kp_)
                        && StringUtils.isNotBlank(noKp_)) {
                    Pihak phk = pihakService.findPihak(kp_, noKp_);
                    if (phk == null) {
                        phk = new Pihak();
                        phk.setAlamat1(StringUtils.isNotBlank(add1PAs[i]) ? add1PAs[i] : "");
                        phk.setAlamat2(StringUtils.isNotBlank(add2PAs[i]) ? add2PAs[i] : "");
                        phk.setAlamat3(StringUtils.isNotBlank(add3PAs[i]) ? add3PAs[i] : "");
                        phk.setAlamat4(StringUtils.isNotBlank(add4PAs[i]) ? add4PAs[i] : "");
                        phk.setPoskod(StringUtils.isNotBlank(poskodPAs[i]) ? poskodPAs[i] : "");
                        phk.setNegeri(StringUtils.isNotBlank(negeriPAs[i]) ? kodNegeriDAO.findById(negeriPAs[i]) : null);
                        phk.setNama(StringUtils.isNotBlank(namaPAs[i]) ? namaPAs[i].replaceAll("__&&", "'") : "");
                        phk.setWargaNegara(kodWarganegaraDAO.findById("MAL"));
                        phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp_));

                        KodJenisPengenalan kodJenis = kodJenisPengenalanDAO.findById(kp_);
                        if (kodJenis.getKod().equalsIgnoreCase("X") || kodJenis.getKod().equalsIgnoreCase("0")) {
                            phk.setNoPengenalan(null);
                        } else {
                            phk.setNoPengenalan(noKp_);
                        }

                        phk.setInfoAudit(ia);
                        senaraiPihak.add(phk);
                    }

                    waris = new PermohonanPihakHubungan();
                    waris.setPihak(permohonanPihak);
                    waris.setInfoAudit(ia);
//          waris.setNama(StringUtils.isNotBlank(namaPAs[i]) ? namaPAs[i].toUpperCase() : "");
                    waris.setNama(StringUtils.isNotBlank(namaPAs[i]) ? namaPAs[i].replaceAll("__&&", "'") : "");
                    waris.setAlamat1(StringUtils.isNotBlank(add1PAs[i]) ? add1PAs[i] : "");
                    waris.setAlamat2(StringUtils.isNotBlank(add2PAs[i]) ? add2PAs[i] : "");
                    waris.setAlamat3(StringUtils.isNotBlank(add3PAs[i]) ? add3PAs[i] : "");
                    waris.setAlamat4(StringUtils.isNotBlank(add4PAs[i]) ? add4PAs[i] : "");
                    waris.setNegeri(StringUtils.isNotBlank(negeriPAs[i]) ? kodNegeriDAO.findById(negeriPAs[i]) : null);
                    waris.setPoskod(StringUtils.isNotBlank(poskodPAs[i]) ? poskodPAs[i] : "");
                    waris.setCawangan(permohonanPihak.getCawangan());
                    waris.setWargaNegara(StringUtils.isNotBlank(warganegaraPAs[i]) ? kodWarganegaraDAO.findById(warganegaraPAs[i]) : null);
                    waris.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp_));

                    KodJenisPengenalan kodJenis = kodJenisPengenalanDAO.findById(kp_);
                    if (kodJenis.getKod().equalsIgnoreCase("X") || kodJenis.getKod().equalsIgnoreCase("0")) {
                        waris.setNoPengenalan(null);
                    } else {
                        waris.setNoPengenalan(noKp_);
                    }

                    waris.setKaitan(permohonanPihak.getKaitan());
                    waris.setSyerPembilang(StringUtils.isNotBlank(syerPembilangs[i]) ? Integer.parseInt(syerPembilangs[i]) : 0);
                    waris.setSyerPenyebut(StringUtils.isNotBlank(syerPenyebuts[i]) ? Integer.parseInt(syerPenyebuts[i]) : 0);
                    senaraiWaris.add(waris);
                }
            }

        }

        if (senaraiPihak.size() > 0) {
            pihakService.saveOrUpdate(senaraiPihak);
        }

        if (senaraiWaris.size() > 0) {
            permohonanPihakHubunganService.save(senaraiWaris);
        }
    }

    private void saveWarisInfo2(Map<String, String[]> param,
            InfoAudit ia, PermohonanPihak permohonanPihak) {

        PermohonanPihakHubungan waris = null;
        List<PermohonanPihakHubungan> senaraiWaris = new ArrayList<PermohonanPihakHubungan>();
        List<Pihak> senaraiPihak = new ArrayList<Pihak>();

        String[] kps = param.get("kpPA");
        String[] noKPs = param.get("noKpPA");
        String[] namaPAs = param.get("namaPA");
        String[] warganegaraPAs = param.get("wargaPA");
        String[] add1PAs = param.get("add1PA");
        String[] add2PAs = param.get("add2PA");
        String[] add3PAs = param.get("add3PA");
        String[] add4PAs = param.get("add4PA");
        String[] poskodPAs = param.get("poskodPA");
        String[] negeriPAs = param.get("negeriPA");

        if (etanah.util.StringUtils.isNotBlank(kps)) {
            for (int i = 0; i < kps.length; i++) {

                String kp_ = kps[i];
                String noKp_ = noKPs[i];
                if (StringUtils.isNotBlank(kp_)
                        && StringUtils.isNotBlank(noKp_)) {
                    Pihak phk = pihakService.findPihak(kp_, noKp_);
                    if (phk == null) {
                        phk = new Pihak();
                        phk.setAlamat1(StringUtils.isNotBlank(add1PAs[i]) ? add1PAs[i] : "");
                        phk.setAlamat2(StringUtils.isNotBlank(add2PAs[i]) ? add2PAs[i] : "");
                        phk.setAlamat3(StringUtils.isNotBlank(add3PAs[i]) ? add3PAs[i] : "");
                        phk.setAlamat4(StringUtils.isNotBlank(add4PAs[i]) ? add4PAs[i] : "");
                        phk.setPoskod(StringUtils.isNotBlank(poskodPAs[i]) ? poskodPAs[i] : "");
                        phk.setNegeri(StringUtils.isNotBlank(negeriPAs[i]) ? kodNegeriDAO.findById(negeriPAs[i]) : null);
                        phk.setNama(StringUtils.isNotBlank(namaPAs[i]) ? namaPAs[i].toUpperCase() : "");
                        phk.setWargaNegara(kodWarganegaraDAO.findById("MAL"));
                        phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp_));
                        phk.setNoPengenalan(noKp_);
                        phk.setInfoAudit(ia);
                        senaraiPihak.add(phk);
                    }

                    waris = new PermohonanPihakHubungan();
                    waris.setPihak(permohonanPihak);
                    waris.setInfoAudit(ia);
//          waris.setNama(StringUtils.isNotBlank(namaPAs[i]) ? namaPAs[i].toUpperCase() : "");
                    waris.setNama(StringUtils.isNotBlank(namaPAs[i]) ? namaPAs[i] : "");
                    waris.setAlamat1(StringUtils.isNotBlank(add1PAs[i]) ? add1PAs[i] : "");
                    waris.setAlamat2(StringUtils.isNotBlank(add2PAs[i]) ? add2PAs[i] : "");
                    waris.setAlamat3(StringUtils.isNotBlank(add3PAs[i]) ? add3PAs[i] : "");
                    waris.setAlamat4(StringUtils.isNotBlank(add4PAs[i]) ? add4PAs[i] : "");
                    waris.setNegeri(StringUtils.isNotBlank(negeriPAs[i]) ? kodNegeriDAO.findById(negeriPAs[i]) : null);
                    waris.setPoskod(StringUtils.isNotBlank(poskodPAs[i]) ? poskodPAs[i] : "");
                    waris.setCawangan(permohonanPihak.getCawangan());
                    waris.setWargaNegara(StringUtils.isNotBlank(warganegaraPAs[i]) ? kodWarganegaraDAO.findById(warganegaraPAs[i]) : null);
                    waris.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp_));
                    waris.setNoPengenalan(noKp_);
                    waris.setKaitan("LAIN-LAIN");
                    senaraiWaris.add(waris);
                }
            }

        }

        if (senaraiPihak.size() > 0) {
            pihakService.saveOrUpdate(senaraiPihak);
        }

        if (senaraiWaris.size() > 0) {
            permohonanPihakHubunganService.save(senaraiWaris);
        }
    }

    public Resolution agihSamaRata() {

        Fraction syerTerlibat = Fraction.ZERO;
        if (senaraiPemohon.size() > 0) {
            for (Pemohon pemoh : senaraiPemohon) {
                if (pemoh.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                    syerTerlibat = syerTerlibat.add(new Fraction(pemoh.getSyerPembilang(), pemoh.getSyerPenyebut()));
                }
            }
            if (syerTerlibat.compareTo(Fraction.ZERO) > 0) {
                permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
                int size = 0;
                for (PermohonanPihak pp1 : permohonan.getSenaraiPihak()) {
                    if (pp1.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                        size = size + 1;
                    }
                }
                if (size > 0) {
                    syerTerlibat = syerTerlibat.divide(size);
                    for (PermohonanPihak pp2 : permohonan.getSenaraiPihak()) {
                        if (pp2.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                            pp2.setSyerPembilang(syerTerlibat.getNumerator());
                            pp2.setSyerPenyebut(syerTerlibat.getDenominator());
                            permohonanPihakService.saveOrUpdate(pp2);
                        }
                    }
                }
            }
        }

        if (kodUrusan.startsWith("IS")) {
            return new RedirectResolution(PihakKepentinganAction.class, "pihakIsmen");
        } else if (kodUrusan.equals("PNPA") || kodUrusan.equals("TRPA")) {
            return new RedirectResolution(PihakKepentinganAction.class, "pihakPemegangAmanah").addParameter("idHakmilik", idHakmilik);
        } else if (kodUrusan.equals("KVST") || kodUrusan.equals("KVSPT")) {
            return new RedirectResolution(PihakKepentinganAction.class, "showFormKVST").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(PihakKepentinganAction.class).addParameter("idHakmilik", idHakmilik);
        }
    }

    private void savePihakAlamat(Pihak pihak, PihakAlamatTamb pihakAlamatTamb) {
        PihakAlamatTamb alamatTambahan = pihakService.getAlamatTambahan(pihak);
        InfoAudit ia = new InfoAudit();
        if (alamatTambahan == null) {
            alamatTambahan = new PihakAlamatTamb();
            alamatTambahan.setPihak(pihak);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(TODAY);
        } else {
            ia = alamatTambahan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(TODAY);
        }
        if (pihakAlamatTamb != null) {
            alamatTambahan.setAlamatKetiga1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
            alamatTambahan.setAlamatKetiga2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
            alamatTambahan.setAlamatKetiga3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
            alamatTambahan.setAlamatKetiga4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
            alamatTambahan.setAlamatKetigaPoskod(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
            alamatTambahan.setAlamatKetigaNegeri(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
        } else if (permohonanPihak != null && permohonanPihak.getAlamatSurat() != null) {
            alamatTambahan.setAlamatKetiga1(StringUtils.isNotBlank(permohonanPihak.getAlamatSurat().getAlamatSurat1()) ? permohonanPihak.getAlamatSurat().getAlamatSurat1() : "");
            alamatTambahan.setAlamatKetiga2(StringUtils.isNotBlank(permohonanPihak.getAlamatSurat().getAlamatSurat2()) ? permohonanPihak.getAlamatSurat().getAlamatSurat2() : "");
            alamatTambahan.setAlamatKetiga3(StringUtils.isNotBlank(permohonanPihak.getAlamatSurat().getAlamatSurat3()) ? permohonanPihak.getAlamatSurat().getAlamatSurat3() : "");
            alamatTambahan.setAlamatKetiga4(StringUtils.isNotBlank(permohonanPihak.getAlamatSurat().getAlamatSurat4()) ? permohonanPihak.getAlamatSurat().getAlamatSurat4() : "");
            alamatTambahan.setAlamatKetigaPoskod(StringUtils.isNotBlank(permohonanPihak.getAlamatSurat().getPoskodSurat()) ? permohonanPihak.getAlamatSurat().getPoskodSurat() : "");
            alamatTambahan.setAlamatKetigaNegeri(permohonanPihak.getAlamatSurat().getNegeriSurat() != null ? permohonanPihak.getAlamatSurat().getNegeriSurat() : null);
        } else if (pemohon != null && pemohon.getAlamatSurat() != null) {
            alamatTambahan.setAlamatKetiga1(StringUtils.isNotBlank(pemohon.getAlamatSurat().getAlamatSurat1()) ? pemohon.getAlamatSurat().getAlamatSurat1() : "");
            alamatTambahan.setAlamatKetiga2(StringUtils.isNotBlank(pemohon.getAlamatSurat().getAlamatSurat2()) ? pemohon.getAlamatSurat().getAlamatSurat2() : "");
            alamatTambahan.setAlamatKetiga3(StringUtils.isNotBlank(pemohon.getAlamatSurat().getAlamatSurat3()) ? pemohon.getAlamatSurat().getAlamatSurat3() : "");
            alamatTambahan.setAlamatKetiga4(StringUtils.isNotBlank(pemohon.getAlamatSurat().getAlamatSurat4()) ? pemohon.getAlamatSurat().getAlamatSurat4() : "");
            alamatTambahan.setAlamatKetigaPoskod(StringUtils.isNotBlank(pemohon.getAlamatSurat().getPoskodSurat()) ? pemohon.getAlamatSurat().getPoskodSurat() : "");
            alamatTambahan.setAlamatKetigaNegeri(pemohon.getAlamatSurat().getNegeriSurat() != null ? pemohon.getAlamatSurat().getNegeriSurat() : null);
        }

        alamatTambahan.setInfoAudit(ia);
        pihakAlamatDAO.saveOrUpdate(alamatTambahan);
    }

    public Resolution PaparPihakWaris() {
        idHp = (String) getContext().getRequest().getParameter("idHp");
        idPermohonan = (String) getContext().getRequest().getParameter("idPPermohonan");
        LOG.debug("-----id hp----------" + idHp);

        hakmilikPihak = hakmilikPihakService.findById(idHp);

        senaraiPihakWaris = hakmilikWarisService.getSenaraiWarisByIdHakmilikPihak(idHp);
        LOG.debug("----- Size Waris----------" + senaraiPihakWaris.size());

        return new JSP("daftar/papar_pihak_waris.jsp").addParameter("popup", "true");
    }

    public Resolution PaparPihakKongsi() {
        senaraiPermohonanPihakKongsi = permohonanPihakService.getSenaraiMohonPihakByIdMohonSyerBersama(idPermohonan);
        senaraiPermohonanPihakKongsiBerkumpulan = permohonanPihakService.getSenaraiMohonPihakByIdMohonSyerBersamaBerkumpulan(idPermohonan);
        senaraiPihakBerkepentingan = regService.searchPihakBerKepentinganPemilikKelompok(idHakmilik);

        for (int i = 0; i < senaraiPermohonanPihakKongsiBerkumpulan.size(); i++) {
            uniqueId.add(String.valueOf(senaraiPermohonanPihakKongsiBerkumpulan.get(i).getIdPihakKongsi()));
        }
        HashSet<String> listToSet = new HashSet<String>(uniqueId);
        uniqueId.clear();
        List<String> listWithoutDuplicates = new ArrayList<String>(listToSet);

        for (int i = 0; i < listWithoutDuplicates.size(); i++) {
            uniqueId.add(listWithoutDuplicates.get(i));
        }

        return new JSP("daftar/papar_pihak_kongsi.jsp").addParameter("tab", "true");
    }

    public Resolution deleteKumpulan() {
        String idPihakKongsi = ctx.getRequest().getParameter("idPihakKongsi");

        senaraiPermohonanPihak = permohonanPihakService.getSenaraiMohonPihakByIdMohonIdPihakKongsi(idPermohonan, Integer.parseInt(idPihakKongsi));
        for (PermohonanPihak mphk : senaraiPermohonanPihak) {
            mphk.setIdPihakKongsi(null);
            permohonanPihakService.saveOrUpdate(mphk);
        }

        senaraiPermohonanPihakKongsi = permohonanPihakService.getSenaraiMohonPihakByIdMohonSyerBersama(idPermohonan);
        senaraiPermohonanPihakKongsiBerkumpulan = permohonanPihakService.getSenaraiMohonPihakByIdMohonSyerBersamaBerkumpulan(idPermohonan);

        for (int i = 0; i < senaraiPermohonanPihakKongsiBerkumpulan.size(); i++) {
            uniqueId.add(String.valueOf(senaraiPermohonanPihakKongsiBerkumpulan.get(i).getIdPihakKongsi()));
        }
        HashSet<String> listToSet = new HashSet<String>(uniqueId);
        uniqueId.clear();
        List<String> listWithoutDuplicates = new ArrayList<String>(listToSet);

        for (int i = 0; i < listWithoutDuplicates.size(); i++) {
            uniqueId.add(listWithoutDuplicates.get(i));
        }
        addSimpleMessage("Kumpulan berjaya dihapuskan");
        return new JSP("daftar/papar_pihak_kongsi.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPihakKongsi() {
        String idGroup = (String) getContext().getRequest().getParameter("idGroup");
        String[] splitChar = idGroup.split("[,\\;]");
        boolean firstTimer = false;
        String[] idPihakKongiArray = new String[1];

        List<String> list = new ArrayList<String>();

        for (String s : splitChar) {
            if (s != null && s.length() > 0) {
                list.add(s);
            }
        }
        splitChar = list.toArray(new String[list.size()]);
        for (int i = 0; i < splitChar.length; i++) {
            LOG.debug("Str[" + i + "]:" + splitChar[i]);
            String idPihakKongsi = "";
            if (!permohonan.getKodUrusan().getKod().equals("BETPB")) {
                permohonanPihak = permohonanPihakDAO.findById(Long.parseLong(splitChar[i]));

                if (firstTimer == false) {
                    idPihakKongiArray[0] = Long.toString(permohonanPihak.getPihak().getIdPihak());
                    firstTimer = true;
                }
                idPihakKongsi = idPihakKongiArray[0];
                permohonanPihak.setIdPihakKongsi(Integer.parseInt(idPihakKongsi));
                permohonanPihakService.saveOrUpdate(permohonanPihak);

            } else if (permohonan.getKodUrusan().getKod().equals("BETPB")) {
                HakmilikPihakBerkepentingan hp = hakmilikPihakService.findById(splitChar[i]);
                if (firstTimer == false) {
                    idPihakKongiArray[0] = Long.toString(hp.getPihak().getIdPihak());
                    firstTimer = true;
                }
                idPihakKongsi = idPihakKongiArray[0];
                Pihak pihak = pihakDAO.findById(Long.parseLong(idPihakKongsi));
                hp.setPihakKongsiBersama(pihak);
                hakmilikPihakService.save(hp);
//               HakmilikPihakBerkepentingan hp = 
            }
        }

        addSimpleMessage("Pihak telah berjaya dikelompokkan");
        senaraiPermohonanPihakKongsi = permohonanPihakService.getSenaraiMohonPihakByIdMohonSyerBersama(idPermohonan);
        senaraiPermohonanPihakKongsiBerkumpulan = permohonanPihakService.getSenaraiMohonPihakByIdMohonSyerBersamaBerkumpulan(idPermohonan);

        for (int i = 0; i < senaraiPermohonanPihakKongsiBerkumpulan.size(); i++) {
            uniqueId.add(String.valueOf(senaraiPermohonanPihakKongsiBerkumpulan.get(i).getIdPihakKongsi()));
        }
        HashSet<String> listToSet = new HashSet<String>(uniqueId);
        uniqueId.clear();
        List<String> listWithoutDuplicates = new ArrayList<String>(listToSet);

        for (int i = 0; i < listWithoutDuplicates.size(); i++) {
            uniqueId.add(listWithoutDuplicates.get(i));
        }

        return new JSP("daftar/papar_pihak_kongsi.jsp").addParameter("tab", "true");
    }

    public Resolution viewPihakKumpulan() {
        String idPihakKongsi = ctx.getRequest().getParameter("idPihakKongsi");
        senaraiPermohonanPihak = permohonanPihakService.getSenaraiMohonPihakByIdMohonIdPihakKongsi(idPermohonan, Integer.parseInt(idPihakKongsi));

        ctx.getRequest().setAttribute("idPihakKongsi", idPihakKongsi);

        return new JSP("daftar/papar_pihak_kongsi_detail.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiKeempunyaan() {
        return senaraiKeempunyaan;
    }

    public void setSenaraiKeempunyaan(List<HakmilikPihakBerkepentingan> senaraiKeempunyaan) {
        this.senaraiKeempunyaan = senaraiKeempunyaan;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiPihakBerkepentingan() {
        return senaraiPihakBerkepentingan;
    }

    public void setSenaraiPihakBerkepentingan(List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan) {
        this.senaraiPihakBerkepentingan = senaraiPihakBerkepentingan;
    }

    public List<Pihak> getSenaraiPihak() {
        return senaraiPihak;
    }

    public void setSenaraiPihak(List<Pihak> senaraiPihak) {
        this.senaraiPihak = senaraiPihak;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTerlibat() {
        return senaraiHakmilikTerlibat;
    }

    public void setSenaraiHakmilikTerlibat(List<HakmilikPermohonan> senaraiHakmilikTerlibat) {
        this.senaraiHakmilikTerlibat = senaraiHakmilikTerlibat;
    }

    public List<Pemohon> getSenaraiPemohon() {
        return senaraiPemohon;
    }

    public void setSenaraiPemohon(List<Pemohon> senaraiPemohon) {
        this.senaraiPemohon = senaraiPemohon;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<KodJenisPihakBerkepentingan> getSenaraiKodPenerima() {
        return listUtil.getSenaraiKodPihakPenerima(permohonan.getKodUrusan().getJabatanNama(), permohonan.getKodUrusan().getNama());
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<PermohonanPihakHubungan> getSenaraiPihakHubungan() {
        return senaraiPihakHubungan;
    }

    public void setSenaraiPihakHubungan(List<PermohonanPihakHubungan> senaraiPihakHubungan) {
        this.senaraiPihakHubungan = senaraiPihakHubungan;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihakBerangkai() {
        return senaraiPermohonanPihakBerangkai;
    }

    public void setSenaraiPermohonanPihakBerangkai(List<PermohonanPihak> senaraiPermohonanPihakBerangkai) {
        this.senaraiPermohonanPihakBerangkai = senaraiPermohonanPihakBerangkai;
    }

    public List<PermohonanAtasPihakBerkepentingan> getSenaraiPermohonanAtasPihak() {
        return senaraiPermohonanAtasPihak;
    }

    public void setSenaraiPermohonanAtasPihak(List<PermohonanAtasPihakBerkepentingan> senaraiPermohonanAtasPihak) {
        this.senaraiPermohonanAtasPihak = senaraiPermohonanAtasPihak;
    }

    public List<PihakCawangan> getSenaraiCawangan() {
        return senaraiCawangan;
    }

    public void setSenaraiCawangan(List<PihakCawangan> senaraiCawangan) {
        this.senaraiCawangan = senaraiCawangan;
    }

    public List<PermohonanPihakKemaskini> getSenaraiKemaskini() {
        return senaraiKemaskini;
    }

    public void setSenaraiKemaskini(List<PermohonanPihakKemaskini> senaraiKemaskini) {
        this.senaraiKemaskini = senaraiKemaskini;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihak() {
        return hakmilikPihak;
    }

    public void setHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak) {
        this.hakmilikPihak = hakmilikPihak;
    }

    public String getIdMohonSebelum() {
        return idMohonSebelum;
    }

    public void setIdMohonSebelum(String idMohonSebelum) {
        this.idMohonSebelum = idMohonSebelum;
    }

    public String getSebabPembatalan() {
        return sebabPembatalan;
    }

    public void setSebabPembatalan(String sebabPembatalan) {
        this.sebabPembatalan = sebabPembatalan;
    }

    public String getIdBatal() {
        return idBatal;
    }

    public void setIdBatal(String idBatal) {
        this.idBatal = idBatal;
    }

    public PihakAlamatTamb getPihakAlamatTamb() {
        return pihakAlamatTamb;
    }

    public void setPihakAlamatTamb(PihakAlamatTamb pihakAlamatTamb) {
        this.pihakAlamatTamb = pihakAlamatTamb;
    }

    public String getIdHp() {
        return idHp;
    }

    public void setIdHp(String idHp) {
        this.idHp = idHp;
    }

    public List<HakmilikWaris> getSenaraiPihakWaris() {
        return senaraiPihakWaris;
    }

    public void setSenaraiPihakWaris(List<HakmilikWaris> senaraiPihakWaris) {
        this.senaraiPihakWaris = senaraiPihakWaris;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String[] getNo() {
        return no;
    }

    public void setNo(String[] no) {
        this.no = no;
    }

    public String[] getKod() {
        return kod;
    }

    public void setKod(String[] kod) {
        this.kod = kod;
    }

    public String getIdPihak1() {
        return idPihak1;
    }

    public void setIdPihak1(String idPihak1) {
        this.idPihak1 = idPihak1;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihakKongsi() {
        return senaraiPermohonanPihakKongsi;
    }

    public void setSenaraiPermohonanPihakKongsi(List<PermohonanPihak> senaraiPermohonanPihakKongsi) {
        this.senaraiPermohonanPihakKongsi = senaraiPermohonanPihakKongsi;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihakKongsiBerkumpulan() {
        return senaraiPermohonanPihakKongsiBerkumpulan;
    }

    public void setSenaraiPermohonanPihakKongsiBerkumpulan(List<PermohonanPihak> senaraiPermohonanPihakKongsiBerkumpulan) {
        this.senaraiPermohonanPihakKongsiBerkumpulan = senaraiPermohonanPihakKongsiBerkumpulan;
    }

    public ArrayList<String> getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(ArrayList<String> uniqueId) {
        this.uniqueId = uniqueId;
    }

    /*public String getCheckSyer() {
        return checkSyer;
    }

    public void setCheckSyer(String checkSyer) {
        this.checkSyer = checkSyer;
    }*/
    
}
