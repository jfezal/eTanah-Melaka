/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.nota;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.Configuration;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodBangsaDAO;
import etanah.dao.KodFlagPihakDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPenubuhanSyarikatDAO;
import etanah.dao.KodWarganegaraDAO;
import etanah.dao.PermohonanAtasPihakBerkepentinganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakKemaskiniDAO;
import etanah.dao.PihakAlamatTambDAO;
import etanah.dao.PihakDAO;
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
import etanah.model.KodBangsa;
import etanah.model.KodPenubuhanSyarikat;
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
import etanah.service.daftar.PembetulanService;
import etanah.service.daftar.PermohonanPihakKemaskiniService;
import etanah.view.ListUtil;
import etanah.view.daftar.PihakKepentinganAction;
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
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math.fraction.Fraction;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fikri
 */
@HttpCache(allow = false)
@UrlBinding("/daftar/pembetulan/TambahPihakBerkepentinganActionBean")
public class TambahPihakBerkepentinganActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PihakKepentinganAction.class);
    private static final boolean isDebug = LOG.isDebugEnabled();
    private static String SEARCH_PAGE = "daftar/carian_pihak_kepentingan.jsp";
    private static String SEARCH_PAGE_MCL = "daftar/carian_syarikat_mcl.jsp";
    private static String MAIN_PAGE = "daftar/pembetulan/TambahBetulPihakBerkepentingan.jsp";
    private static String MAIN_PAGE_POPUP = "daftar/kemasukan_pihak_baru_BETPB.jsp";
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
    private List<HakmilikPihakBerkepentingan> senaraiKeempunyaanBETPB;
    private List<HakmilikWaris> senaraiPihakWaris;
    private List<PermohonanPihak> senaraiPermohonanPihak;
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
    private HakmilikPihakBerkepentingan hakmilikPihakBETPB;
    private List<HakmilikUrusan> hakmilikUrusanBETPB;

    @Inject
    PembetulanService pService;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;

    private List<HakmilikUrusan> hakmilikUrusanListY;
    private List<PermohonanPihak> senaraiMohonPihakBaru;
    private List<Pemohon> senaraiPemohonkBaru;
    private List<HakmilikPihakBerkepentingan> senaraiHPkBaru;
    private HakmilikUrusan Urusan;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList2;
    private HakmilikUrusan hakmilikUrusan;
    private List<Pemohon> listPemohon;
    private List<PermohonanPihak> listMohonPihak;
    private String[] syerPembilang;
    private String[] syerPenyebut;
    private String[] syerPembilang2;
    private String[] syerPenyebut2;
    private KodBangsa bangsa;
    private KodBangsa KodBangsa;
    private KodWarganegara kodWarganegara;
    private KodNegeri kodNegeri;
    private KodPenubuhanSyarikat kodPenubuhanSyarikat;
    private KodJenisPihakBerkepentingan kodJenisPihakBerkepentingan;

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO HakmilikPihakBerkepentinganDAO;
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
    PermohonanAtasPihakBerkepentinganDAO permohonanAtasPihakBerkepentinganDAO;
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
    KodBangsaDAO kodBangsaDAO;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    @Inject
    KodPenubuhanSyarikatDAO kodPenubuhanSyarikatDAO;
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
        "KVSPC"
    };
    private static String[] URUSAN_TO_VALIDATE_TUAN_TANAH = {
        "PMT",
        "GD",
        "PHADB",
        "PHADS",
        "ADBSB",
        "ADBSS"
    };
    private static String[] URUSAN_TO_REPLACE_OWNER = {
        "PMT",
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
                senaraiKeempunyaanBETPB = new ArrayList<HakmilikPihakBerkepentingan>();
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

                        List<PermohonanHubungan> senaraiHbgn = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), hm.getIdHakmilik());

                        if (senaraiHbgn.isEmpty()) {
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

                    } else if (semua_pihak) {
                        _senaraiPihak = hakmilikPihakService.findHakmilikAllPihakActiveByHakmilik(hm);
                    } else {
                        _senaraiPihak = hakmilikPihakService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hm);
                    }

                    for (HakmilikPihakBerkepentingan hpkb : _senaraiPihak) {
                        if (hpkb == null || hpkb.getPihak() == null) {
                            continue;
                        }
                        senaraiKeempunyaan.add(hpkb);
                    }

                    senaraiKeempunyaanBETPB = hakmilikPihakService.findHakmilikPihakBETPB(hm.getIdHakmilik(), permohonan.getIdPermohonan());

                    _senaraiPihak = hakmilikPihakService.findHakmilikPihakBerkepentinganByIdHakmilik2(hm.getIdHakmilik(), null);

                    for (HakmilikPihakBerkepentingan hpkb : _senaraiPihak) {
                        if (hpkb == null || hpkb.getPihak() == null) {
                            continue;
                        }
                        senaraiPihakBerkepentingan.add(hpkb);
                    }
                }

                if (StringUtils.isNotBlank(idHakmilik)) {
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

            senaraiMohonPihakBaru = permohonanPihakService.findMohonPihakByIdHmAndIdMohonBaru(idPermohonan, idHakmilik);
            senaraiPemohonkBaru = pemohonService.findPemohonByIdPermohonanAndHakmilikBaru(idPermohonan, idHakmilik);
            senaraiHPkBaru = hakmilikPihakService.findHakmilikPihakBETPBByHakmilikAndIdMohon(idHakmilik, idPermohonan);
            syerPembilang = new String[senaraiMohonPihakBaru.size()];
            syerPenyebut = new String[senaraiMohonPihakBaru.size()];
            syerPembilang2 = new String[senaraiPemohonkBaru.size()];
            syerPenyebut2 = new String[senaraiPemohonkBaru.size()];

            if (senaraiMohonPihakBaru != null) {
                int counter = 0;
                for (int i = 0; i < senaraiMohonPihakBaru.size(); i++) {
                    PermohonanPihak hpb = senaraiMohonPihakBaru.get(i);
                    syerPembilang[counter] = String.valueOf(hpb.getSyerPembilang());
                    syerPenyebut[counter] = String.valueOf(hpb.getSyerPenyebut());
                    counter = counter + 1;
                }
            }
            if (senaraiPemohonkBaru != null) {
                int counter = 0;
                for (int i = 0; i < senaraiPemohonkBaru.size(); i++) {
                    Pemohon hpb = senaraiPemohonkBaru.get(i);
                    syerPembilang2[counter] = String.valueOf(hpb.getSyerPembilang());
                    syerPenyebut2[counter] = String.valueOf(hpb.getSyerPenyebut());
                    counter = counter + 1;
                }
            }
        }
    }

    public Resolution deleteHakmilik() throws WorkflowException {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String idMohonPihak = getContext().getRequest().getParameter("idPermohonanPihak");
        if (idMohonPihak != null) {
            permohonanPihak = permohonanPihakService.findById(idMohonPihak);
            permohonanPihakService.delete(permohonanPihak);
        }

        msg = "Data Telah Berjaya diHapuskan";
        addSimpleMessage(msg);
//        form = true;
        rehydrate();
        return showForm();
    }

    public void simpanMohonAtasPihakPemohon(Pemohon pemohon, Pihak pihakTemp) {

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);
        PermohonanAtasPihakBerkepentingan mapk = new PermohonanAtasPihakBerkepentingan();
        mapk.setPermohonan(permohonan);
        mapk.setJenisTugasan("Tambah");
        mapk.setInfoAudit(ia);
        mapk.setHakmilik(pemohon.getHakmilik());
        mapk.setCatatan(pemohon.getPermohonan().getIdPermohonan());
        mapk.setPemohon(pemohon);
        permohonanAtasPihakService.save(mapk);

    }

    public void simpanMohonAtasPihakHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak, Pihak pihakTemp) {

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);
        PermohonanAtasPihakBerkepentingan mapk = new PermohonanAtasPihakBerkepentingan();
        mapk.setPermohonan(permohonan);
        mapk.setJenisTugasan("Tambah");
        mapk.setInfoAudit(ia);
        mapk.setHakmilik(hakmilikPihak.getHakmilik());
//        mapk.setCatatan();
        mapk.setPihakBerkepentingan(hakmilikPihak);
        permohonanAtasPihakService.save(mapk);

    }

    public void simpanMohonAtasPihakMohonPihak(PermohonanPihak pp, Pihak pihakTemp) {

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);
        PermohonanAtasPihakBerkepentingan mapk = new PermohonanAtasPihakBerkepentingan();
        mapk.setPermohonan(permohonan);
        mapk.setJenisTugasan("Tambah");
        mapk.setInfoAudit(ia);
        mapk.setHakmilik(pp.getHakmilik());
        mapk.setCatatan(pp.getPermohonan().getIdPermohonan());
        mapk.setPermohonanPihak(pp);
        permohonanAtasPihakService.save(mapk);

    }

    public Resolution deletePihak() {

        String idMhn = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idPermohonanPihak = getContext().getRequest().getParameter("idAtasPihak");
        LOG.info(idPermohonanPihak);
        LOG.debug("id atas pihak ~~~" + idPermohonanPihak);
        if (idPermohonanPihak != null) {
            if (idPermohonanPihak != null) {
                permohonanPihak = permohonanPihakService.findById(idPermohonanPihak);

                List<PermohonanAtasPihakBerkepentingan> mapList = permohonanAtasPihakService.findByPermohonanPihakAndIdMohon(String.valueOf(permohonanPihak.getIdPermohonanPihak()), permohonanPihak.getIdPermohonanLama());
                if (mapList.size() > 0) {
                    for (PermohonanAtasPihakBerkepentingan map : mapList) {
                        pService.delete(map);
                    }
                }
                permohonanPihakService.delete(permohonanPihak);
            }
            HakmilikPihakBerkepentingan hpBaru = hakmilikPihakService.findHakmilikPihakByIdPihakBETPB(permohonanPihak.getPihak(), permohonanPihak.getHakmilik());
            if (hpBaru != null) {
                List<HakmilikPihakBerkepentingan> senaraiHpBaru = hakmilikPihakService.findHakmilikPihakBETPBByHakmilikAndIdMohon(hpBaru.getHakmilik().getIdHakmilik(), permohonan.getIdPermohonan());
                for (HakmilikPihakBerkepentingan hpBaru1 : senaraiHpBaru) {
                    HakmilikPihakBerkepentinganDAO.delete(hpBaru1);
                }
//                pService.deleteHakmilikPihakBerkepentingan2(hpBaru);
            }
        }
        rehydrate();

        return new RedirectResolution(etanah.view.stripes.nota.TambahPihakBerkepentinganActionBean.class, "showForm");
    }

    public Resolution deletePihakPemohon() {

        String idMhn = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idPermohonanPihak = getContext().getRequest().getParameter("idAtasPihak");
        LOG.info(idPermohonanPihak);
        LOG.debug("id atas pihak ~~~" + idPermohonanPihak);
        if (idPermohonanPihak != null) {
            if (idPermohonanPihak != null) {
                pemohon = pemohonService.findById(idPermohonanPihak);
                List<PermohonanAtasPihakBerkepentingan> mapList = permohonanAtasPihakService.findByPemohonPihakAndIdMohon(String.valueOf(pemohon.getIdPemohon()), pemohon.getIdPermohonanLama());
                if (mapList.size() > 0) {
                    for (PermohonanAtasPihakBerkepentingan map : mapList) {
                        pService.delete(map);
                    }
                }

            }
        }
        pemohonService.delete(pemohon);
        rehydrate();

        return new RedirectResolution(etanah.view.stripes.nota.TambahPihakBerkepentinganActionBean.class, "showForm");
    }

    public Resolution deleteHakmilikPihak() {

        String idMhn = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idPermohonanPihak = getContext().getRequest().getParameter("idAtasPihak");
        LOG.info(idPermohonanPihak);
        LOG.debug("id atas pihak ~~~" + idPermohonanPihak);
        if (idPermohonanPihak != null) {
            if (idPermohonanPihak != null) {
                hakmilikPihak = HakmilikPihakBerkepentinganDAO.findById(Long.parseLong(idPermohonanPihak));
                List<PermohonanAtasPihakBerkepentingan> mapList = permohonanAtasPihakService.findByHakmilikPihakPihakAndIdMohon(String.valueOf(hakmilikPihak.getIdHakmilikPihakBerkepentingan()), hakmilikPihak.getIdPermohonan());
                if (mapList.size() > 0) {
                    for (PermohonanAtasPihakBerkepentingan map : mapList) {
                        pService.delete(map);
                    }
                }

            }
        }
        HakmilikPihakBerkepentinganDAO.delete(hakmilikPihak);
        List<HakmilikPihakBerkepentingan> senaraiHP = new ArrayList<HakmilikPihakBerkepentingan>();

        senaraiHP.add(hakmilikPihak);
        hakmilikPihakService.deleteHakmilikPihakBerkepentingan(senaraiHP);
        rehydrate();

        return new RedirectResolution(etanah.view.stripes.nota.TambahPihakBerkepentinganActionBean.class, "showForm");
    }

    public Resolution deletePihakHakmilikPihak() {

        String idMhn = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilikPihakBerkepentingan = getContext().getRequest().getParameter("idAtasPihak");
        LOG.info(idHakmilikPihakBerkepentingan);
        LOG.debug("id atas pihak ~~~" + idHakmilikPihakBerkepentingan);
        if (idHakmilikPihakBerkepentingan != null) {
            hakmilikPihak = HakmilikPihakBerkepentinganDAO.findById(Long.parseLong(idHakmilikPihakBerkepentingan));
            List<PermohonanAtasPihakBerkepentingan> mapList = permohonanAtasPihakService.findByPemohonPihakAndIdMohon(String.valueOf(hakmilikPihak.getIdHakmilikPihakBerkepentingan()), hakmilikPihak.getIdPermohonan());
            if (mapList.size() > 0) {
                for (PermohonanAtasPihakBerkepentingan map : mapList) {
                    pService.delete(map);
                }
            }

            HakmilikPihakBerkepentinganDAO.delete(hakmilikPihak);
        }
        rehydrate();

        return new RedirectResolution(etanah.view.stripes.nota.TambahPihakBerkepentinganActionBean.class, "showForm");
    }

    @DefaultHandler
    public Resolution showForm() {

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
        } else {
            ctx.getRequest().setAttribute("edit", Boolean.TRUE);
        }
        hakmilikUrusanListY = pService.findUrusanByidHNY(idHakmilik);
//        return new JSP(MAIN_PAGE).addParameter("tab", Boolean.TRUE);
        return new JSP("daftar/pembetulan/TambahBetulPihakBerkepentingan.jsp").addParameter("tab", Boolean.TRUE);
    }

    public Resolution caripihak2() {

        String idUrusan = ctx.getRequest().getParameter("idUrusan");
//        String[] param = getContext().getRequest().getParameterValues("idUrusan");
        if (idUrusan != null) {
            Urusan = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
            pihakKepentinganList2 = pService.findHakmilikPihakActiveByIdUrusan(idUrusan);

            LOG.info(getContext().getRequest().getParameterValues("idPermohonan"));
            LOG.info("sini");

            String param2 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

            Permohonan permohonan = permohonanDAO.findById(param2);
            LOG.info(permohonan.getIdPermohonan());
            LOG.info(permohonan.getKodUrusan().getKod());
            kodUrusan = permohonan.getKodUrusan().getKod();
            LOG.info(kodUrusan);
            hakmilikUrusan = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
            LOG.info(hakmilikUrusan.getIdPerserahan());
            listPemohon = pemohonService.findPemohonByIdPermohonanAndHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
            listMohonPihak = permohonanPihakService.getAllSenaraiPmohonPihakByHakmilikAndMohon(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
        }

        rehydrate();
        return new JSP("daftar/pembetulan/edit_PihakBerkepentingan_tambah.jsp").addParameter("popup", "true");
    }

    public Resolution kemaskiniHakmilikPihak() {

        String idHakmilikPihak = ctx.getRequest().getParameter("idHakmilikPihak");
//        String[] param = getContext().getRequest().getParameterValues("idUrusan");
        if (idHakmilikPihak != null) {

            hakmilikPihakBETPB = HakmilikPihakBerkepentinganDAO.findById(Long.parseLong(idHakmilikPihak));
            hakmilikUrusanBETPB = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilik(idHakmilik);
            pihak = pihakService.findById(String.valueOf(hakmilikPihakBETPB.getPihak().getIdPihak()));
            Hakmilik jenisHakmilik = hakmilikPihakBETPB.getHakmilik();

            if (jenisHakmilik.getNoBangunan() != null) {
                String hakmilikInduk = jenisHakmilik.getIdHakmilikInduk();
                List<HakmilikUrusan> listHUinduk = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilikStrata(hakmilikInduk);
                hakmilikUrusanBETPB.addAll(listHUinduk);
//                hakmilikUrusanBETPB = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilikStrata(hakmilikInduk);
            }

//            Urusan = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
//            pihakKepentinganList2 = pService.findHakmilikPihakActiveByIdUrusan(idUrusan);
//
//            LOG.info(getContext().getRequest().getParameterValues("idPermohonan"));
//            LOG.info("sini");
//
//            String param2 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//
//            Permohonan permohonan = permohonanDAO.findById(param2);
//            LOG.info(permohonan.getIdPermohonan());
//            LOG.info(permohonan.getKodUrusan().getKod());
//            kodUrusan = permohonan.getKodUrusan().getKod();
//            LOG.info(kodUrusan);
//            hakmilikUrusan = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
//            LOG.info(hakmilikUrusan.getIdPerserahan());
//            listPemohon = pemohonService.findPemohonByIdPermohonanAndHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
//            listMohonPihak = permohonanPihakService.getAllSenaraiPmohonPihakByHakmilikAndMohon(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
        }

        rehydrate();
        return new JSP("daftar/pembetulan/kemaskini_rekod_tuanPunya.jsp").addParameter("popup", "true");
    }

    public Resolution addNewKetuanPunyaan() throws WorkflowException {
        ctx.getRequest().setAttribute("newPihak", "true");
        if (kodUrusan.equals("TRPA")) {
            ctx.getRequest().setAttribute("disabledWaris", "true");
        } else {
            ctx.getRequest().setAttribute("disabledWaris", "false");
        }
        String idUrusan = ctx.getRequest().getParameter("idUrusan");
        String idMohonPihak = ctx.getRequest().getParameter("idMohonPihak");
        String idPemohon = ctx.getRequest().getParameter("idPemohon");
        String idMohon = ctx.getRequest().getParameter("idPemohon");
//        String idMohonPihak = ctx.getRequest().getParameter("idMohonPihak");
        if (idMohonPihak != null) {

        }
//        String idUrusan1 = getContext().getRequest().getParameter("idUrusan");
        Fraction syerSemuaPemohon = Fraction.ONE;
        Fraction syerSemuaPermohonanPihak = Fraction.ONE;
        if (idUrusan != null) {
            Urusan = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
            String mohon = Urusan.getIdPerserahan();
        }
        hakmilikUrusanListY = pService.findUrusanByidHNY(idHakmilik);

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

        if (idMohonPihak != null) {
            return new JSP("daftar/kemasukan_pihak_baru_BETPB _mohon_pihak.jsp").addParameter("popup", Boolean.TRUE);
        } else if (idPemohon != null) {
            return new JSP("daftar/kemasukan_pihak_baru_BETPB.jsp").addParameter("tab", Boolean.TRUE);
        } else {
            return new JSP("daftar/kemasukan_pihak_baru_BETPB_hakmilikPihak.jsp").addParameter("tab", Boolean.TRUE);
        }
    }

    public Resolution addNewPihak() throws WorkflowException {
        ctx.getRequest().setAttribute("newPihak", "true");
        if (kodUrusan.equals("TRPA")) {
            ctx.getRequest().setAttribute("disabledWaris", "true");
        } else {
            ctx.getRequest().setAttribute("disabledWaris", "false");
        }
        String idUrusan = ctx.getRequest().getParameter("idUrusan");
        String idMohonPihak = ctx.getRequest().getParameter("idMohonPihak");
        String idPemohon = ctx.getRequest().getParameter("idPemohon");
//        String idMohonPihak = ctx.getRequest().getParameter("idMohonPihak");
        if (idMohonPihak != null) {

        }
//        String idUrusan1 = getContext().getRequest().getParameter("idUrusan");
        Fraction syerSemuaPemohon = Fraction.ONE;
        Fraction syerSemuaPermohonanPihak = Fraction.ONE;
        if (idUrusan != null) {
            Urusan = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
            String mohon = Urusan.getIdPerserahan();
        }
        hakmilikUrusanListY = pService.findUrusanByidHNY(idHakmilik);

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

        if (idMohonPihak != null) {
            return new JSP("daftar/kemasukan_pihak_baru_BETPB _mohon_pihak.jsp").addParameter("popup", Boolean.TRUE);
        } else if (idPemohon != null) {
            return new JSP("daftar/kemasukan_pihak_baru_BETPB.jsp").addParameter("tab", Boolean.TRUE);
        } else {
            return new JSP("daftar/kemasukan_pihak_baru_BETPB_hakmilikPihak.jsp").addParameter("tab", Boolean.TRUE);
        }
    }

    public Resolution reload() {
        if (ArrayUtils.contains(URUSAN_TO_VALIDATE_TUAN_TANAH, kodUrusan)) {
            if (senaraiPermohonanPihakBerangkai != null) {
                if (senaraiPermohonanPihakBerangkai.isEmpty()) {
                    addSimpleError("Sila pastikan tuan tanah adalah dari pihak yang SAMA.");
                } else {
                    ctx.getRequest().setAttribute("edit", Boolean.TRUE);
                }
            } else if (senaraiKeempunyaan != null) {
                if (senaraiKeempunyaan.isEmpty()) {
                    addSimpleError("Sila pastikan tuan tanah adalah dari pihak yang SAMA.");
                } else {
                    ctx.getRequest().setAttribute("edit", Boolean.TRUE);
                }
            }
        }
        return showForm();

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
        String idUrusan = getContext().getRequest().getParameter("idUrusan");
        LOG.debug("----- copyToAllHakmilik: " + copyToAllHakmilik);
        LOG.debug("----- jenisPihak: " + jenisPihak);
        LOG.debug("----- tarikhLahir: " + tarikhLahir);
        LOG.debug("----- syerPembilang: " + syerPembilang);
        LOG.debug("----- syerPenyebut: " + syerPenyebut);
        LOG.debug("----- syerDikongsi: " + syerDikongsi);
        LOG.debug("----- kodUrusan: " + kodUrusan);
        LOG.debug("----- kodUrusan: " + idUrusan);

        String pilih = ctx.getRequest().getParameter("pilih");
        HakmilikUrusan hakmilikUrusanLama = hakmilikUrusanService.findById(Long.parseLong(idUrusan));
        Permohonan permohonanLama = permohonanService.findById(hakmilikUrusanLama.getIdPerserahan());

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
        Pemohon pemohon = new Pemohon();

        if (hakmilik != null) {
            pemohon.setHakmilik(hakmilik);
            pemohon.setCawangan(hakmilik.getCawangan());
        } else {
            pemohon.setCawangan(pengguna.getKodCawangan());
        }
        pemohon.setJenis(jenis);
        pemohon.setInfoAudit(ia);
        BigDecimal bd = new BigDecimal(0);
        pemohon.setNama(pihak.getNama() != null ? pihak.getNama().toUpperCase() : "");
        pemohon.setNoPengenalan(pihak.getNoPengenalan());
        pemohon.setJenisPengenalan(pihak.getJenisPengenalan());
        pemohon.setPenubuhanSyarikat(pihak.getPenubuhanSyarikat());
        pemohon.setWargaNegara(pihak.getWargaNegara());
        Alamat alamat = new Alamat();
        alamat.setAlamat1(pihak.getAlamat1());
        alamat.setAlamat2(pihak.getAlamat2());
        alamat.setAlamat3(pihak.getAlamat3());
        alamat.setAlamat4(pihak.getAlamat4());
        alamat.setPoskod(pihak.getPoskod());
        alamat.setNegeri(pihak.getNegeri());
        pemohon.setAlamat(alamat);
        pemohon.setIdPermohonanLama(permohonan.getIdPermohonan());
        pemohon.setKodStatus(String.valueOf('T'));
        if (pihakAlamatTamb != null) {
            AlamatSurat alamatSurat = new AlamatSurat();
            alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
            alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
            alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
            alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
            alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
            alamatSurat.setNegeriSurat(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
            pemohon.setAlamatSurat(alamatSurat);
        }
        pemohon.setPihak(pihakTemp);
        pemohon.setPermohonan(permohonanLama);

        pemohonService.saveOrUpdate(pemohon);
        simpanMohonAtasPihakPemohon(pemohon, pihakTemp);
        rehydrate();

        return new RedirectResolution(etanah.view.stripes.nota.TambahPihakBerkepentinganActionBean.class, "showForm");
//        return new RedirectResolution(TambahPihakBerkepentinganActionBean.class).addParameter("idHakmilik", idHakmilik);

    }

    public Resolution updateSyerPemohon() {
        String idPemohon = getContext().getRequest().getParameter("idpihak");
        String s1 = getContext().getRequest().getParameter("syer3");//pembilang
        String s2 = getContext().getRequest().getParameter("syer4");//penyebut
        if (StringUtils.isNotBlank(idPemohon) && StringUtils.isNotBlank(s1) && StringUtils.isNotBlank(s2)) {
            Pemohon pemohonBaru = pemohonService.findById(idPemohon);
            pemohonBaru.setJumlahPembilang(Integer.parseInt(s1));
            pemohonBaru.setJumlahPenyebut(Integer.parseInt(s2));
            pemohonBaru.setSyerPembilang(Integer.parseInt(s1));
            pemohonBaru.setSyerPenyebut(Integer.parseInt(s2));
            pemohonService.saveOrUpdate(pemohonBaru);
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution updateSyerHakmilikPihak() {
        String idHp = getContext().getRequest().getParameter("idpihak");
        String s1 = getContext().getRequest().getParameter("syer3");//pembilang
        String s2 = getContext().getRequest().getParameter("syer4");//penyebut
        if (StringUtils.isNotBlank(idHp) && StringUtils.isNotBlank(s1) && StringUtils.isNotBlank(s2)) {
            HakmilikPihakBerkepentingan hp = HakmilikPihakBerkepentinganDAO.findById(Long.parseLong(idHp));
            hp.setJumlahPembilang(Integer.parseInt(s1));
            hp.setJumlahPenyebut(Integer.parseInt(s2));
            hp.setSyerPembilang(Integer.parseInt(s1));
            hp.setSyerPenyebut(Integer.parseInt(s2));
            hakmilikPihakService.save(hp);
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution updateSyerMohonPihak() {
        String idPemohon = getContext().getRequest().getParameter("idpihak");
        String s1 = getContext().getRequest().getParameter("syer1");//pembilang
        String s2 = getContext().getRequest().getParameter("syer2");//penyebut
        if (StringUtils.isNotBlank(idPemohon) && StringUtils.isNotBlank(s1) && StringUtils.isNotBlank(s2)) {
            PermohonanPihak mpBaru = permohonanPihakService.findById(idPemohon);
            mpBaru.setJumlahPembilang(Integer.parseInt(s1));
            mpBaru.setJumlahPenyebut(Integer.parseInt(s2));
            mpBaru.setSyerPembilang(Integer.parseInt(s1));
            mpBaru.setSyerPenyebut(Integer.parseInt(s2));
            permohonanPihakService.saveOrUpdate(mpBaru);
            updateSyerhakmilikPihak(s1, s2, mpBaru.getHakmilik().getIdHakmilik());
        }
        return new StreamingResolution("text/plain", "1");
    }

    public void updateSyerhakmilikPihak(String s1, String s2, String idHakmilik) {
        String idPemohon = getContext().getRequest().getParameter("idpihak");
        List<HakmilikPihakBerkepentingan> senaraiHpBaru = hakmilikPihakService.findHakmilikPihakBETPBByHakmilikAndIdMohon(idHakmilik, permohonan.getIdPermohonan());
        for (HakmilikPihakBerkepentingan hpBaru : senaraiHpBaru) {
            hpBaru.setJumlahPembilang(Integer.parseInt(s1));
            hpBaru.setJumlahPenyebut(Integer.parseInt(s2));
            hpBaru.setSyerPembilang(Integer.parseInt(s1));
            hpBaru.setSyerPenyebut(Integer.parseInt(s2));
            hakmilikPihakService.save(hpBaru);
        }
    }

    public Resolution kemaskiniPihakBerkepentingan() {

        String nama = getContext().getRequest().getParameter("pihak.nama");
        String idHakmilikPihakBerkepentingan = getContext().getRequest().getParameter("idHakmilikPihakBerkepentingan");
        String perserahan = getContext().getRequest().getParameter("perserahan");
        String jenisPihak = getContext().getRequest().getParameter("jenisPihak");
//        String idpihak = getContext().getRequest().getParameter("idpihak");
//        String jenis_pihak = getContext().getRequest().getParameter("jenis_pihak");
        String jenisPengenalan = getContext().getRequest().getParameter("pihak.jenisPengenalan.kod");
        String wargaNegara = getContext().getRequest().getParameter("pihak.wargaNegara.kod");
        String noPengenalan = getContext().getRequest().getParameter("pihak.noPengenalan");
        String umur = getContext().getRequest().getParameter("pihak.umur");
        String gender = getContext().getRequest().getParameter("pihak.kodJantina");
        String tLahir = getContext().getRequest().getParameter("pihak.tarikhLahir");
        String bangsa = getContext().getRequest().getParameter("pihak.bangsa.kod");
        String daftarTubuh = getContext().getRequest().getParameter("pihak.penubuhanSyarikat.kod");
        String alamat1 = getContext().getRequest().getParameter("pihak.alamat1");
        String alamat2 = getContext().getRequest().getParameter("pihak.alamat2");
        String alamat3 = getContext().getRequest().getParameter("pihak.alamat3");
        String alamat4 = getContext().getRequest().getParameter("pihak.alamat4");
        String poskod = getContext().getRequest().getParameter("pihak.poskod");
        String negeri = getContext().getRequest().getParameter("pihak.negeri.kod");
        String suratAlamat1 = getContext().getRequest().getParameter("suratAlamat1");
        String suratAlamat2 = getContext().getRequest().getParameter("suratAlamat2");
        String suratAlamat3 = getContext().getRequest().getParameter("suratAlamat3");
        String suratAlamat4 = getContext().getRequest().getParameter("suratAlamat4");
        String suratPoskod = getContext().getRequest().getParameter("suratPoskod");
        String suratNegeri = getContext().getRequest().getParameter("suratNegeri");

//        Pihak pihak = pihak;
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);

        KodJenisPengenalan jp = kodJenisPengenalanDAO.findById(jenisPengenalan);
        if (bangsa != null) {
            KodBangsa = kodBangsaDAO.findById(bangsa);
        }
        if (wargaNegara != null) {
            kodWarganegara = kodWarganegaraDAO.findById(wargaNegara);
        }
        if (negeri != null) {
            kodNegeri = kodNegeriDAO.findById(negeri);
        }
        if (daftarTubuh != null) {
            kodPenubuhanSyarikat = kodPenubuhanSyarikatDAO.findById(daftarTubuh);
        }

        pihak.setNama(nama);
        pihak.setJenisPengenalan(jp);
        pihak.setNoPengenalan(noPengenalan);
        pihak.setKodJantina(gender);
        pihak.setBangsa(KodBangsa);
//        pihak.setSuku(pihak.getSuku());
//        pihak.setNoTelefon1(pihak.getNoTelefon1());
//        pihak.setNoTelefon2(pihak.getNoTelefon2());
//        pihak.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
        pihak.setWargaNegara(kodWarganegara);
        pihak.setAlamat1(alamat1);
        pihak.setAlamat2(alamat2);
        pihak.setAlamat3(alamat3);
        pihak.setAlamat4(alamat4);
        pihak.setPoskod(poskod);
        pihak.setNegeri(kodNegeri);
//        if (pihakAlamatTamb != null) {
//            pihak.setSuratAlamat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
//            pihak.setSuratAlamat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
//            pihak.setSuratAlamat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
//            pihak.setSuratAlamat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
//            pihak.setSuratPoskod(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
//            pihak.setSuratNegeri(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
//        }

        pihak.setInfoAudit(ia);
        pihak.setTempatLahir(tLahir);
        pihak.setPenubuhanSyarikat(kodPenubuhanSyarikat);
//        pihakDAO.update(pihak);
        //Added by Aizuddin for MCL
//        if (pihak.getNoTelefon1() != null) {
//            pihak.setNoTelefon1(pihak.getNoTelefon1());
//        }
//        if (pihak.getNoTelefon2() != null) {
//            pihak.setNoTelefon2(pihak.getNoTelefon2());
//        }
//        if (pihak.getEmail() != null) {
//            pihak.setEmail(pihak.getEmail());
//        }
//        if (pihak.getModalBerbayar() != null) {
//            pihak.setModalBerbayar(pihak.getModalBerbayar());
//        }

        simpanHakmilikPihakLama(pihak, idHakmilikPihakBerkepentingan, perserahan, jenisPihak);
        pihak.setKodFlagPihak(kodFlagPihakDAO.findById("AK"));

        return new RedirectResolution(etanah.view.stripes.nota.TambahPihakBerkepentinganActionBean.class, "showForm");
    }

    public Resolution savePihakBerkepentingan() {

        String nama = getContext().getRequest().getParameter("nama");
//        String idpihak = getContext().getRequest().getParameter("idpihak");
//        String jenis_pihak = getContext().getRequest().getParameter("jenis_pihak");
        String jenisPengenalan = getContext().getRequest().getParameter("jenisPengenalan");
        String wargaNegara = getContext().getRequest().getParameter("wargaNegara");
        String noPengenalan = getContext().getRequest().getParameter("noPengenalan");
        String umur = getContext().getRequest().getParameter("umur");
        String gender = getContext().getRequest().getParameter("gender");
        String tLahir = getContext().getRequest().getParameter("tLahir");
        String daftarTubuh = getContext().getRequest().getParameter("daftarTubuh");
        String bangsa = getContext().getRequest().getParameter("bangsa");
        String alamat1 = getContext().getRequest().getParameter("alamat1");
        String alamat2 = getContext().getRequest().getParameter("alamat2");
        String alamat3 = getContext().getRequest().getParameter("alamat3");
        String alamat4 = getContext().getRequest().getParameter("alamat4");
        String poskod = getContext().getRequest().getParameter("poskod");
        String negeri = getContext().getRequest().getParameter("negeri");
        String suratAlamat1 = getContext().getRequest().getParameter("suratAlamat1");
        String suratAlamat2 = getContext().getRequest().getParameter("suratAlamat2");
        String suratAlamat3 = getContext().getRequest().getParameter("suratAlamat3");
        String suratAlamat4 = getContext().getRequest().getParameter("suratAlamat4");
        String suratPoskod = getContext().getRequest().getParameter("suratPoskod");
        String suratNegeri = getContext().getRequest().getParameter("suratNegeri");

        Pihak pihak = new Pihak();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);

        KodJenisPengenalan jp = kodJenisPengenalanDAO.findById(jenisPengenalan);

        if (bangsa != null) {
            KodBangsa = kodBangsaDAO.findById(bangsa);
        }
        if (wargaNegara != null) {
            kodWarganegara = kodWarganegaraDAO.findById(wargaNegara);
        }
        if (negeri != null) {
            kodNegeri = kodNegeriDAO.findById(negeri);
        }
        if (daftarTubuh != null) {
            kodPenubuhanSyarikat = kodPenubuhanSyarikatDAO.findById(daftarTubuh);
        }

        pihak.setNama(nama);
        pihak.setJenisPengenalan(jp);
        pihak.setNoPengenalan(noPengenalan);
        pihak.setKodJantina(gender);
        pihak.setBangsa(KodBangsa);
//        pihak.setSuku(pihak.getSuku());
//        pihak.setNoTelefon1(pihak.getNoTelefon1());
//        pihak.setNoTelefon2(pihak.getNoTelefon2());
//        pihak.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
        pihak.setWargaNegara(kodWarganegara);
        pihak.setAlamat1(alamat1);
        pihak.setAlamat2(alamat2);
        pihak.setAlamat3(alamat3);
        pihak.setAlamat4(alamat4);
        pihak.setPoskod(poskod);
        pihak.setNegeri(kodNegeri);
//        if (pihakAlamatTamb != null) {
//            pihak.setSuratAlamat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
//            pihak.setSuratAlamat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
//            pihak.setSuratAlamat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
//            pihak.setSuratAlamat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
//            pihak.setSuratPoskod(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
//            pihak.setSuratNegeri(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
//        }

        pihak.setInfoAudit(ia);
        pihak.setTempatLahir(tLahir);
        pihak.setPenubuhanSyarikat(kodPenubuhanSyarikat);
        pihakService.saveOrUpdate(pihak);
        //Added by Aizuddin for MCL
//        if (pihak.getNoTelefon1() != null) {
//            pihak.setNoTelefon1(pihak.getNoTelefon1());
//        }
//        if (pihak.getNoTelefon2() != null) {
//            pihak.setNoTelefon2(pihak.getNoTelefon2());
//        }
//        if (pihak.getEmail() != null) {
//            pihak.setEmail(pihak.getEmail());
//        }
//        if (pihak.getModalBerbayar() != null) {
//            pihak.setModalBerbayar(pihak.getModalBerbayar());
//        }

        simpanHakmilikPihak(pihak);
        pihak.setKodFlagPihak(kodFlagPihakDAO.findById("AK"));

        return new RedirectResolution(etanah.view.stripes.nota.TambahPihakBerkepentinganActionBean.class, "showForm");
    }

    public void simpanHakmilikPihakLama(Pihak pihak, String idHakmilikPihakBerkepentingan, String perserahan, String jenisPihak) {

        HakmilikPihakBerkepentingan hakmilikPihak = hakmilikPihakService.findById(idHakmilikPihakBerkepentingan);
        if (perserahan != null || perserahan != "") {
            hakmilikUrusan = hakmilikUrusanDAO.findById(Long.parseLong(perserahan));
        }
        if (jenisPihak != null) {
            kodJenisPihakBerkepentingan = kodJenisPihakBerkepentinganDAO.findById(jenisPihak);
        }

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);
        if (hakmilik != null) {
            hakmilikPihak.setHakmilik(hakmilik);
            hakmilikPihak.setCawangan(hakmilik.getCawangan());
        } else {
            hakmilikPihak.setCawangan(pengguna.getKodCawangan());
        }
//        hakmilikPihak.setJenis(p);
        hakmilikPihak.setInfoAudit(ia);
        BigDecimal bdnew = new BigDecimal(0);
        hakmilikPihak.setNama(pihak.getNama() != null ? pihak.getNama().toUpperCase() : "");
        hakmilikPihak.setNoPengenalan(pihak.getNoPengenalan());
        hakmilikPihak.setJenisPengenalan(pihak.getJenisPengenalan());
        hakmilikPihak.setPenubuhanSyarikat(pihak.getPenubuhanSyarikat());
        hakmilikPihak.setWargaNegara(pihak.getWargaNegara());
        if (perserahan != null || perserahan != "") {
            hakmilikPihak.setPerserahan(hakmilikUrusan);
        }
        if (jenisPihak != null) {
            hakmilikPihak.setJenis(kodJenisPihakBerkepentingan);
        }
//        Alamat alamat = new Alamat();
        hakmilikPihak.setAlamat1(pihak.getAlamat1());
        hakmilikPihak.setAlamat2(pihak.getAlamat2());
        hakmilikPihak.setAlamat3(pihak.getAlamat3());
        hakmilikPihak.setAlamat4(pihak.getAlamat4());
        hakmilikPihak.setPoskod(pihak.getPoskod());
        hakmilikPihak.setNegeri(pihak.getNegeri());
//        hakmilikPihak.setAlamat1(idBatal);

//            hakmilikPihak.setAlamat(alamat);
//            hakmilikPihak.seti
        hakmilikPihak.setAktif('T');
        if (pihakAlamatTamb != null) {
            AlamatSurat alamatSurat = new AlamatSurat();
            alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
            alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
            alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
            alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
            alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
            alamatSurat.setNegeriSurat(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
            hakmilikPihak.setAlamatSurat(alamatSurat);
        }

        hakmilikPihak.setPihak(pihak);
//        hakmilikPihak.setPerserahan(hakmilikUrusanLama);
        hakmilikPihak.setIdPermohonan(permohonan.getIdPermohonan());
        hakmilikPihakService.save(hakmilikPihak);

    }

    public void simpanHakmilikPihak(Pihak pihak) {

        HakmilikPihakBerkepentingan hakmilikPihak = new HakmilikPihakBerkepentingan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(TODAY);
        if (hakmilik != null) {
            hakmilikPihak.setHakmilik(hakmilik);
            hakmilikPihak.setCawangan(hakmilik.getCawangan());
        } else {
            hakmilikPihak.setCawangan(pengguna.getKodCawangan());
        }

        //added by eda 23112017
        KodPenubuhanSyarikat penubuhanSyarikat = null;
        if (pihak.getPenubuhanSyarikat() != null) {
            penubuhanSyarikat = kodPenubuhanSyarikatDAO.findById(pihak.getPenubuhanSyarikat().getKod());
        }
//        hakmilikPihak.setJenis(p);
        hakmilikPihak.setInfoAudit(ia);
        BigDecimal bdnew = new BigDecimal(0);
        hakmilikPihak.setNama(pihak.getNama() != null ? pihak.getNama().toUpperCase() : "");
        hakmilikPihak.setNoPengenalan(pihak.getNoPengenalan());
        hakmilikPihak.setJenisPengenalan(pihak.getJenisPengenalan());
        hakmilikPihak.setPenubuhanSyarikat(penubuhanSyarikat);
        hakmilikPihak.setWargaNegara(pihak.getWargaNegara());

        Alamat alamat = new Alamat();
        alamat.setAlamat1(pihak.getAlamat1());
        alamat.setAlamat2(pihak.getAlamat2());
        alamat.setAlamat3(pihak.getAlamat3());
        alamat.setAlamat4(pihak.getAlamat4());
        alamat.setPoskod(pihak.getPoskod());
        alamat.setNegeri(pihak.getNegeri());

//            hakmilikPihak.setAlamat(alamat);
//            hakmilikPihak.seti
        hakmilikPihak.setAktif('T');
        if (pihakAlamatTamb != null) {
            AlamatSurat alamatSurat = new AlamatSurat();
            alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
            alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
            alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
            alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
            alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
            alamatSurat.setNegeriSurat(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
            hakmilikPihak.setAlamatSurat(alamatSurat);
        }

        hakmilikPihak.setPihak(pihak);
//        hakmilikPihak.setPerserahan(hakmilikUrusanLama);

        hakmilikPihak.setIdPermohonan(permohonan.getIdPermohonan());
        hakmilikPihakService.save(hakmilikPihak);
        simpanMohonAtasPihakHakmilikPihak(hakmilikPihak, pihak);

    }

    public Resolution savePihakPopupMohonPihak() {
        LOG.debug("----- Save pihak Popup start----------");
        String copyToAllHakmilik = getContext().getRequest().getParameter("copyToAll");
        String jenisPihak = getContext().getRequest().getParameter("jenisPihak");
        String tarikhLahir = getContext().getRequest().getParameter("tarikhLahir");
        String syerPembilang = getContext().getRequest().getParameter("syerPembilang");
        String syerPenyebut = getContext().getRequest().getParameter("syerPenyebut");
        String syerDikongsi = getContext().getRequest().getParameter("syerKongsi");
        String kodUrusan = getContext().getRequest().getParameter("kodUrusan");
        String idUrusan = getContext().getRequest().getParameter("idUrusan");

        LOG.debug("----- copyToAllHakmilik: " + copyToAllHakmilik);
        LOG.debug("----- jenisPihak: " + jenisPihak);
        LOG.debug("----- tarikhLahir: " + tarikhLahir);
        LOG.debug("----- syerPembilang: " + syerPembilang);
        LOG.debug("----- syerPenyebut: " + syerPenyebut);
        LOG.debug("----- syerDikongsi: " + syerDikongsi);
        LOG.debug("----- kodUrusan: " + kodUrusan);
        LOG.debug("----- kodUrusan: " + idUrusan);

        String pilih = ctx.getRequest().getParameter("pilih");
        HakmilikUrusan hakmilikUrusanLama = hakmilikUrusanService.findById(Long.parseLong(idUrusan));
        Permohonan permohonanLama = permohonanService.findById(hakmilikUrusanLama.getIdPerserahan());

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
        pp.setIdPermohonanLama(permohonan.getIdPermohonan());
        pp.setKodStatus(String.valueOf('T'));
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
        pp.setPermohonan(permohonanLama);
        LOG.debug("---->  id mohon_pihak pp " + pp.getIdPermohonanPihak());
        LOG.debug("---->  id pihak pp " + pp.getPihak().getIdPihak());
        permohonanPihakService.saveOrUpdate(pp);

//        HakmilikPihakBerkepentingan hpNew = new HakmilikPihakBerkepentingan();
//        if (hakmilik != null) {
//            hpNew.setHakmilik(hakmilik);
//            hpNew.setCawangan(hakmilik.getCawangan());
//        } else {
//            hpNew.setCawangan(pengguna.getKodCawangan());
//        }
//        hpNew.setJenis(jenis);
//        hpNew.setInfoAudit(ia);
//        BigDecimal bdnew = new BigDecimal(0);
//        hpNew.setNama(pihak.getNama() != null ? pihak.getNama().toUpperCase() : "");
//        hpNew.setNoPengenalan(pihak.getNoPengenalan());
//        hpNew.setJenisPengenalan(pihak.getJenisPengenalan());
//        hpNew.setPenubuhanSyarikat(pihak.getPenubuhanSyarikat());
//        hpNew.setWargaNegara(pihak.getWargaNegara());
//        Alamat alamatnew = new Alamat();
//        alamat.setAlamat1(pihak.getAlamat1());
//        alamat.setAlamat2(pihak.getAlamat2());
//        alamat.setAlamat3(pihak.getAlamat3());
//        alamat.setAlamat4(pihak.getAlamat4());
//        alamat.setPoskod(pihak.getPoskod());
//        alamat.setNegeri(pihak.getNegeri());
////            hpNew.setAlamat(alamat);
////            hpNew.seti
//        hpNew.setAktif('T');
//        if (pihakAlamatTamb != null) {
//            AlamatSurat alamatSurat = new AlamatSurat();
//            alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
//            alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
//            alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
//            alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
//            alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
//            alamatSurat.setNegeriSurat(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
//            hpNew.setAlamatSurat(alamatSurat);
//        }
//
//        hpNew.setPihak(pihakTemp);
//        hpNew.setPerserahan(hakmilikUrusanLama);
//        hpNew.setIdPermohonan(permohonan.getIdPermohonan());
//        hakmilikPihakService.save(hpNew);
        simpanMohonAtasPihakMohonPihak(pp, pihakTemp);

        rehydrate();
        return new RedirectResolution(etanah.view.stripes.nota.TambahPihakBerkepentinganActionBean.class, "showForm");

//        return new RedirectResolution(TambahPihakBerkepentinganActionBean.class).addParameter("idHakmilik", idHakmilik);
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

                pihakTemp.setAlamat1(pihak.getAlamat1());
                pihakTemp.setAlamat2(pihak.getAlamat2());
                pihakTemp.setAlamat3(pihak.getAlamat3());
                pihakTemp.setAlamat4(pihak.getAlamat4());
                pihakTemp.setPoskod(pihak.getPoskod());
                pihakTemp.setNegeri(pihak.getNegeri());
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

    public List<HakmilikUrusan> getHakmilikUrusanListY() {
        return hakmilikUrusanListY;
    }

    public void setHakmilikUrusanListY(List<HakmilikUrusan> hakmilikUrusanListY) {
        this.hakmilikUrusanListY = hakmilikUrusanListY;
    }

    public List<PermohonanPihak> getSenaraiMohonPihakBaru() {
        return senaraiMohonPihakBaru;
    }

    public void setSenaraiMohonPihakBaru(List<PermohonanPihak> senaraiMohonPihakBaru) {
        this.senaraiMohonPihakBaru = senaraiMohonPihakBaru;
    }

    public List<Pemohon> getSenaraiPemohonkBaru() {
        return senaraiPemohonkBaru;
    }

    public void setSenaraiPemohonkBaru(List<Pemohon> senaraiPemohonkBaru) {
        this.senaraiPemohonkBaru = senaraiPemohonkBaru;
    }

    public HakmilikUrusan getUrusan() {
        return Urusan;
    }

    public void setUrusan(HakmilikUrusan Urusan) {
        this.Urusan = Urusan;
    }

    public SyerValidationService getSyerService() {
        return syerService;
    }

    public void setSyerService(SyerValidationService syerService) {
        this.syerService = syerService;
    }

    public static String getSEARCH_PAGE() {
        return SEARCH_PAGE;
    }

    public static void setSEARCH_PAGE(String SEARCH_PAGE) {
        TambahPihakBerkepentinganActionBean.SEARCH_PAGE = SEARCH_PAGE;
    }

    public static String getSEARCH_PAGE_MCL() {
        return SEARCH_PAGE_MCL;
    }

    public static void setSEARCH_PAGE_MCL(String SEARCH_PAGE_MCL) {
        TambahPihakBerkepentinganActionBean.SEARCH_PAGE_MCL = SEARCH_PAGE_MCL;
    }

    public static String getMAIN_PAGE() {
        return MAIN_PAGE;
    }

    public static void setMAIN_PAGE(String MAIN_PAGE) {
        TambahPihakBerkepentinganActionBean.MAIN_PAGE = MAIN_PAGE;
    }

    public static String getMAIN_PAGE_POPUP() {
        return MAIN_PAGE_POPUP;
    }

    public static void setMAIN_PAGE_POPUP(String MAIN_PAGE_POPUP) {
        TambahPihakBerkepentinganActionBean.MAIN_PAGE_POPUP = MAIN_PAGE_POPUP;
    }

    public static String getMAIN_PAGE_MCL_POPUP() {
        return MAIN_PAGE_MCL_POPUP;
    }

    public static void setMAIN_PAGE_MCL_POPUP(String MAIN_PAGE_MCL_POPUP) {
        TambahPihakBerkepentinganActionBean.MAIN_PAGE_MCL_POPUP = MAIN_PAGE_MCL_POPUP;
    }

    public static String getMAIN_PAGE_TMAMD_POPUP() {
        return MAIN_PAGE_TMAMD_POPUP;
    }

    public static void setMAIN_PAGE_TMAMD_POPUP(String MAIN_PAGE_TMAMD_POPUP) {
        TambahPihakBerkepentinganActionBean.MAIN_PAGE_TMAMD_POPUP = MAIN_PAGE_TMAMD_POPUP;
    }

    public static String getMAIN_PAGE_KVST() {
        return MAIN_PAGE_KVST;
    }

    public static void setMAIN_PAGE_KVST(String MAIN_PAGE_KVST) {
        TambahPihakBerkepentinganActionBean.MAIN_PAGE_KVST = MAIN_PAGE_KVST;
    }

    public static String getMAIN_PAGE_TUKAR() {
        return MAIN_PAGE_TUKAR;
    }

    public static void setMAIN_PAGE_TUKAR(String MAIN_PAGE_TUKAR) {
        TambahPihakBerkepentinganActionBean.MAIN_PAGE_TUKAR = MAIN_PAGE_TUKAR;
    }

    public static String getMAIN_PAGE_MCL() {
        return MAIN_PAGE_MCL;
    }

    public static void setMAIN_PAGE_MCL(String MAIN_PAGE_MCL) {
        TambahPihakBerkepentinganActionBean.MAIN_PAGE_MCL = MAIN_PAGE_MCL;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public boolean isIsBerangkai() {
        return isBerangkai;
    }

    public void setIsBerangkai(boolean isBerangkai) {
        this.isBerangkai = isBerangkai;
    }

    public String getIdKumpulan() {
        return idKumpulan;
    }

    public void setIdKumpulan(String idKumpulan) {
        this.idKumpulan = idKumpulan;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public boolean isMoreThanOneHakmilik() {
        return moreThanOneHakmilik;
    }

    public void setMoreThanOneHakmilik(boolean moreThanOneHakmilik) {
        this.moreThanOneHakmilik = moreThanOneHakmilik;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public PembetulanService getpService() {
        return pService;
    }

    public void setpService(PembetulanService pService) {
        this.pService = pService;
    }

    public HakmilikUrusanDAO getHakmilikUrusanDAO() {
        return hakmilikUrusanDAO;
    }

    public void setHakmilikUrusanDAO(HakmilikUrusanDAO hakmilikUrusanDAO) {
        this.hakmilikUrusanDAO = hakmilikUrusanDAO;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList2() {
        return pihakKepentinganList2;
    }

    public void setPihakKepentinganList2(List<HakmilikPihakBerkepentingan> pihakKepentinganList2) {
        this.pihakKepentinganList2 = pihakKepentinganList2;
    }

    public HakmilikUrusan getHakmilikUrusan() {
        return hakmilikUrusan;
    }

    public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
        this.hakmilikUrusan = hakmilikUrusan;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public List<PermohonanPihak> getListMohonPihak() {
        return listMohonPihak;
    }

    public void setListMohonPihak(List<PermohonanPihak> listMohonPihak) {
        this.listMohonPihak = listMohonPihak;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public HakmilikPihakKepentinganService getHakmilikPihakService() {
        return hakmilikPihakService;
    }

    public void setHakmilikPihakService(HakmilikPihakKepentinganService hakmilikPihakService) {
        this.hakmilikPihakService = hakmilikPihakService;
    }

    public PihakService getPihakService() {
        return pihakService;
    }

    public void setPihakService(PihakService pihakService) {
        this.pihakService = pihakService;
    }

    public PihakAlamatTambDAO getPihakAlamatDAO() {
        return pihakAlamatDAO;
    }

    public void setPihakAlamatDAO(PihakAlamatTambDAO pihakAlamatDAO) {
        this.pihakAlamatDAO = pihakAlamatDAO;
    }

    public LelongService getLelongService() {
        return lelongService;
    }

    public void setLelongService(LelongService lelongService) {
        this.lelongService = lelongService;
    }

    public PemohonService getPemohonService() {
        return pemohonService;
    }

    public void setPemohonService(PemohonService pemohonService) {
        this.pemohonService = pemohonService;
    }

    public PermohonanPihakService getPermohonanPihakService() {
        return permohonanPihakService;
    }

    public void setPermohonanPihakService(PermohonanPihakService permohonanPihakService) {
        this.permohonanPihakService = permohonanPihakService;
    }

    public PermohonanAtasPerserahanService getPermohonanAtasPerserahanService() {
        return permohonanAtasPerserahanService;
    }

    public void setPermohonanAtasPerserahanService(PermohonanAtasPerserahanService permohonanAtasPerserahanService) {
        this.permohonanAtasPerserahanService = permohonanAtasPerserahanService;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public HakmilikPermohonanService getHakmilikPermohonanService() {
        return hakmilikPermohonanService;
    }

    public void setHakmilikPermohonanService(HakmilikPermohonanService hakmilikPermohonanService) {
        this.hakmilikPermohonanService = hakmilikPermohonanService;
    }

    public KodJenisPihakBerkepentinganDAO getKodJenisPihakBerkepentinganDAO() {
        return kodJenisPihakBerkepentinganDAO;
    }

    public void setKodJenisPihakBerkepentinganDAO(KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO) {
        this.kodJenisPihakBerkepentinganDAO = kodJenisPihakBerkepentinganDAO;
    }

    public PermohonanAtasPihakBerkepentinganService getPermohonanAtasPihakService() {
        return permohonanAtasPihakService;
    }

    public void setPermohonanAtasPihakService(PermohonanAtasPihakBerkepentinganService permohonanAtasPihakService) {
        this.permohonanAtasPihakService = permohonanAtasPihakService;
    }

    public PermohonanPihakKemaskiniService getPermohonanPihakKemaskiniService() {
        return permohonanPihakKemaskiniService;
    }

    public void setPermohonanPihakKemaskiniService(PermohonanPihakKemaskiniService permohonanPihakKemaskiniService) {
        this.permohonanPihakKemaskiniService = permohonanPihakKemaskiniService;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public ListUtil getListUtil() {
        return listUtil;
    }

    public void setListUtil(ListUtil listUtil) {
        this.listUtil = listUtil;
    }

    public KodJenisPengenalanDAO getKodJenisPengenalanDAO() {
        return kodJenisPengenalanDAO;
    }

    public void setKodJenisPengenalanDAO(KodJenisPengenalanDAO kodJenisPengenalanDAO) {
        this.kodJenisPengenalanDAO = kodJenisPengenalanDAO;
    }

    public KodNegeriDAO getKodNegeriDAO() {
        return kodNegeriDAO;
    }

    public void setKodNegeriDAO(KodNegeriDAO kodNegeriDAO) {
        this.kodNegeriDAO = kodNegeriDAO;
    }

    public KodWarganegaraDAO getKodWarganegaraDAO() {
        return kodWarganegaraDAO;
    }

    public void setKodWarganegaraDAO(KodWarganegaraDAO kodWarganegaraDAO) {
        this.kodWarganegaraDAO = kodWarganegaraDAO;
    }

    public PermohonanPihakKemaskiniDAO getPermohonanPihakKemaskiniDAO() {
        return permohonanPihakKemaskiniDAO;
    }

    public void setPermohonanPihakKemaskiniDAO(PermohonanPihakKemaskiniDAO permohonanPihakKemaskiniDAO) {
        this.permohonanPihakKemaskiniDAO = permohonanPihakKemaskiniDAO;
    }

    public KodFlagPihakDAO getKodFlagPihakDAO() {
        return kodFlagPihakDAO;
    }

    public void setKodFlagPihakDAO(KodFlagPihakDAO kodFlagPihakDAO) {
        this.kodFlagPihakDAO = kodFlagPihakDAO;
    }

    public PihakDAO getPihakDAO() {
        return pihakDAO;
    }

    public void setPihakDAO(PihakDAO pihakDAO) {
        this.pihakDAO = pihakDAO;
    }

    public PermohonanPihakHubunganService getPermohonanPihakHubunganService() {
        return permohonanPihakHubunganService;
    }

    public void setPermohonanPihakHubunganService(PermohonanPihakHubunganService permohonanPihakHubunganService) {
        this.permohonanPihakHubunganService = permohonanPihakHubunganService;
    }

    public HakmilikUrusanService getHakmilikUrusanService() {
        return hakmilikUrusanService;
    }

    public void setHakmilikUrusanService(HakmilikUrusanService hakmilikUrusanService) {
        this.hakmilikUrusanService = hakmilikUrusanService;
    }

    public HakmilikWarisService getHakmilikWarisService() {
        return hakmilikWarisService;
    }

    public void setHakmilikWarisService(HakmilikWarisService hakmilikWarisService) {
        this.hakmilikWarisService = hakmilikWarisService;
    }

    public PermohonanPihakKemaskiniService getPermohonanPihakKkiniService() {
        return permohonanPihakKkiniService;
    }

    public void setPermohonanPihakKkiniService(PermohonanPihakKemaskiniService permohonanPihakKkiniService) {
        this.permohonanPihakKkiniService = permohonanPihakKkiniService;
    }

    public DokumenService getDokumenService() {
        return dokumenService;
    }

    public void setDokumenService(DokumenService dokumenService) {
        this.dokumenService = dokumenService;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }

    public etanahActionBeanContext getCtx() {
        return ctx;
    }

    public void setCtx(etanahActionBeanContext ctx) {
        this.ctx = ctx;
    }

    public StringBuilder getError() {
        return error;
    }

    public void setError(StringBuilder error) {
        this.error = error;
    }

    public static Date getTODAY() {
        return TODAY;
    }

    public static void setTODAY(Date TODAY) {
        TambahPihakBerkepentinganActionBean.TODAY = TODAY;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public static String[] getSENARAI_URUSAN_PAPAR_PIHAK_BERKEPENTINGAN() {
        return SENARAI_URUSAN_PAPAR_PIHAK_BERKEPENTINGAN;
    }

    public static void setSENARAI_URUSAN_PAPAR_PIHAK_BERKEPENTINGAN(String[] SENARAI_URUSAN_PAPAR_PIHAK_BERKEPENTINGAN) {
        TambahPihakBerkepentinganActionBean.SENARAI_URUSAN_PAPAR_PIHAK_BERKEPENTINGAN = SENARAI_URUSAN_PAPAR_PIHAK_BERKEPENTINGAN;
    }

    public static String[] getSENARAI_URUSAN_PAPAR_SEMUA_PIHAK_BERKEPENTINGAN() {
        return SENARAI_URUSAN_PAPAR_SEMUA_PIHAK_BERKEPENTINGAN;
    }

    public static void setSENARAI_URUSAN_PAPAR_SEMUA_PIHAK_BERKEPENTINGAN(String[] SENARAI_URUSAN_PAPAR_SEMUA_PIHAK_BERKEPENTINGAN) {
        TambahPihakBerkepentinganActionBean.SENARAI_URUSAN_PAPAR_SEMUA_PIHAK_BERKEPENTINGAN = SENARAI_URUSAN_PAPAR_SEMUA_PIHAK_BERKEPENTINGAN;
    }

    public static String[] getURUSAN_TO_VALIDATE_TUAN_TANAH() {
        return URUSAN_TO_VALIDATE_TUAN_TANAH;
    }

    public static void setURUSAN_TO_VALIDATE_TUAN_TANAH(String[] URUSAN_TO_VALIDATE_TUAN_TANAH) {
        TambahPihakBerkepentinganActionBean.URUSAN_TO_VALIDATE_TUAN_TANAH = URUSAN_TO_VALIDATE_TUAN_TANAH;
    }

    public static String[] getURUSAN_TO_REPLACE_OWNER() {
        return URUSAN_TO_REPLACE_OWNER;
    }

    public static void setURUSAN_TO_REPLACE_OWNER(String[] URUSAN_TO_REPLACE_OWNER) {
        TambahPihakBerkepentinganActionBean.URUSAN_TO_REPLACE_OWNER = URUSAN_TO_REPLACE_OWNER;
    }

    public String[] getWarga() {
        return warga;
    }

    public void setWarga(String[] warga) {
        this.warga = warga;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHPkBaru() {
        return senaraiHPkBaru;
    }

    public void setSenaraiHPkBaru(List<HakmilikPihakBerkepentingan> senaraiHPkBaru) {
        this.senaraiHPkBaru = senaraiHPkBaru;
    }

    public String[] getSyerPembilang() {
        return syerPembilang;
    }

    public void setSyerPembilang(String[] syerPembilang) {
        this.syerPembilang = syerPembilang;
    }

    public String[] getSyerPenyebut() {
        return syerPenyebut;
    }

    public void setSyerPenyebut(String[] syerPenyebut) {
        this.syerPenyebut = syerPenyebut;
    }

    public String[] getSyerPembilang2() {
        return syerPembilang2;
    }

    public void setSyerPembilang2(String[] syerPembilang2) {
        this.syerPembilang2 = syerPembilang2;
    }

    public String[] getSyerPenyebut2() {
        return syerPenyebut2;
    }

    public void setSyerPenyebut2(String[] syerPenyebut2) {
        this.syerPenyebut2 = syerPenyebut2;
    }

    public HakmilikPihakBerkepentinganDAO getHakmilikPihakBerkepentinganDAO() {
        return HakmilikPihakBerkepentinganDAO;
    }

    public void setHakmilikPihakBerkepentinganDAO(HakmilikPihakBerkepentinganDAO HakmilikPihakBerkepentinganDAO) {
        this.HakmilikPihakBerkepentinganDAO = HakmilikPihakBerkepentinganDAO;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiKeempunyaanBETPB() {
        return senaraiKeempunyaanBETPB;
    }

    public void setSenaraiKeempunyaanBETPB(List<HakmilikPihakBerkepentingan> senaraiKeempunyaanBETPB) {
        this.senaraiKeempunyaanBETPB = senaraiKeempunyaanBETPB;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBETPB() {
        return hakmilikPihakBETPB;
    }

    public void setHakmilikPihakBETPB(HakmilikPihakBerkepentingan hakmilikPihakBETPB) {
        this.hakmilikPihakBETPB = hakmilikPihakBETPB;
    }

    public List<HakmilikUrusan> getHakmilikUrusanBETPB() {
        return hakmilikUrusanBETPB;
    }

    public void setHakmilikUrusanBETPB(List<HakmilikUrusan> hakmilikUrusanBETPB) {
        this.hakmilikUrusanBETPB = hakmilikUrusanBETPB;
    }

    public KodBangsa getKodBangsa() {
        return KodBangsa;
    }

    public void setKodBangsa(KodBangsa KodBangsa) {
        this.KodBangsa = KodBangsa;
    }

    public KodBangsa getBangsa() {
        return bangsa;
    }

    public void setBangsa(KodBangsa bangsa) {
        this.bangsa = bangsa;
    }

    public KodWarganegara getKodWarganegara() {
        return kodWarganegara;
    }

    public void setKodWarganegara(KodWarganegara kodWarganegara) {
        this.kodWarganegara = kodWarganegara;
    }

    public KodPenubuhanSyarikat getKodPenubuhanSyarikat() {
        return kodPenubuhanSyarikat;
    }

    public void setKodPenubuhanSyarikat(KodPenubuhanSyarikat kodPenubuhanSyarikat) {
        this.kodPenubuhanSyarikat = kodPenubuhanSyarikat;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public KodJenisPihakBerkepentingan getKodJenisPihakBerkepentingan() {
        return kodJenisPihakBerkepentingan;
    }

    public void setKodJenisPihakBerkepentingan(KodJenisPihakBerkepentingan kodJenisPihakBerkepentingan) {
        this.kodJenisPihakBerkepentingan = kodJenisPihakBerkepentingan;
    }

    public RegService getRegService() {
        return regService;
    }

    public void setRegService(RegService regService) {
        this.regService = regService;
    }

    public PermohonanAtasPihakBerkepentinganDAO getPermohonanAtasPihakBerkepentinganDAO() {
        return permohonanAtasPihakBerkepentinganDAO;
    }

    public void setPermohonanAtasPihakBerkepentinganDAO(PermohonanAtasPihakBerkepentinganDAO permohonanAtasPihakBerkepentinganDAO) {
        this.permohonanAtasPihakBerkepentinganDAO = permohonanAtasPihakBerkepentinganDAO;
    }

    public KodBangsaDAO getKodBangsaDAO() {
        return kodBangsaDAO;
    }

    public void setKodBangsaDAO(KodBangsaDAO kodBangsaDAO) {
        this.kodBangsaDAO = kodBangsaDAO;
    }

    public KodPenubuhanSyarikatDAO getKodPenubuhanSyarikatDAO() {
        return kodPenubuhanSyarikatDAO;
    }

    public void setKodPenubuhanSyarikatDAO(KodPenubuhanSyarikatDAO kodPenubuhanSyarikatDAO) {
        this.kodPenubuhanSyarikatDAO = kodPenubuhanSyarikatDAO;
    }

}
