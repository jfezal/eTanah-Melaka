/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegaraDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodWarganegaraDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakAlamatTambDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodFlagPihak;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodNegara;
import etanah.model.KodNegeri;
import etanah.model.KodWarganegara;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakHubungan;
import etanah.model.Pihak;
import etanah.model.PihakAlamatTamb;
import etanah.model.PihakCawangan;
import etanah.model.PihakPengarah;
import etanah.service.ConsentPtdService;
import etanah.service.SyerValidationService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanAtasPihakBerkepentinganService;
import etanah.service.common.PermohonanPihakHubunganService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PihakService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
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
@UrlBinding("/consent/pihak_penerima")
public class PihakPenerimaActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PihakPenerimaActionBean.class);
    private static final boolean isDebug = LOG.isDebugEnabled();
    private static String SEARCH_PAGE = "consent/carian_pihak_penerima.jsp";
    private static String MAIN_PAGE = "consent/pihak_penerima.jsp";
    private static String MAIN_PAGE_POPUP = "consent/pihak_penerima_popup.jsp";
    private List<HakmilikPermohonan> senaraiHakmilikTerlibat;
    private List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan;
    private List<HakmilikPihakBerkepentingan> senaraiKeempunyaan;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    private List<PermohonanPihak> senaraiPermohonanPihakBerangkai;
    private List<PermohonanAtasPihakBerkepentingan> senaraiPermohonanAtasPihak;
    private List<Pemohon> senaraiPemohon;
    private List<Pihak> senaraiPihak;
    private List<PermohonanPihak> senaraiTuanTanahTerlibat;
    private List<PermohonanPihak> senaraiPenerimaGadaian;
    private List<PermohonanPihakHubungan> senaraiPihakHubungan;
    private List<PihakCawangan> cawanganList;
    private List<PihakPengarah> pengarahList;
    private List<HakmilikPihakBerkepentingan> senaraiTanahLain;
    private String idPermohonan;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private PihakAlamatTamb pihakAlamatTamb;
    private String idKumpulan;
    private String kodUrusan;
    private Pihak pihak;
    private Pemohon pemohon;
    private PermohonanPihak permohonanPihak;
    private String idHakmilik;
    private Hakmilik hakmilik;
    private String tarikhLahir;
    private String tempatLahirLain;
    private String hubunganLain;
    private String kodNegeri;
    private String jenisPihak;
    private String tarikhMati;
    private boolean moreThanOneHakmilik = false;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakService;
    @Inject
    PihakService pihakService;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    PermohonanAtasPihakBerkepentinganService permohonanAtasPihakService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    ListUtil listUtil;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodNegaraDAO kodNegaraDAO;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    @Inject
    PihakAlamatTambDAO pihakAlamatTambDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakHubunganService permohonanPihakHubunganService;
    @Inject
    SyerValidationService syerService;
    @Inject
    ConsentPtdService consentService;
    @Inject
    private etanah.Configuration conf;
    etanahActionBeanContext ctx;
    static Date TODAY = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!searchPihak", "!selectPihak", "!searchPihakPemohon", "!selectPihakPemohon", "!deletePemohon", "!savePengarahEdit"})
    public void rehydrate() {
        LOG.info("---------------START REHYDRATE---------------");
        ctx = (etanahActionBeanContext) getContext();
        kodNegeri = conf.getProperty("kodNegeri");

        pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) ctx.getRequest().getSession().getAttribute("idPermohonan");

        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        if (StringUtils.isNotBlank(idPermohonan)) {

            permohonan = permohonanDAO.findById(idPermohonan);

            if (StringUtils.isBlank(idHakmilik)) {
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            }

            LOG.info("id hakmilik : " + idHakmilik);

            senaraiKeempunyaan = new ArrayList<HakmilikPihakBerkepentingan>();
            senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
            hakmilik = hakmilikDAO.findById(idHakmilik);

            if (senaraiHakmilikTerlibat.size() > 1) {
                moreThanOneHakmilik = true;
                ctx.getRequest().setAttribute("moreThanOneHakmilik", "true");
            }

            senaraiKeempunyaan = hakmilikPihakService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakmilik);

            senaraiPemohon = permohonan.getSenaraiPemohon();

            //for urusan consent tanah adat
            if (permohonan.getKodUrusan().getKod().equals("PAADT") || permohonan.getKodUrusan().getKod().equals("CGADT") || permohonan.getKodUrusan().getKod().equals("PMADT") || permohonan.getKodUrusan().getKod().equals("TMADT") || permohonan.getKodUrusan().getKod().equals("BTADT")) {
                senaraiPermohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(idPermohonan, "WAR", "TER", idHakmilik);
            } //for urusan consent serentak
            else if (permohonan.getKodUrusan().getKod().equals("PCPTD") || permohonan.getKodUrusan().getKod().equals("PGDMB") || permohonan.getKodUrusan().getKod().equals("PCMMK")) {
                senaraiPermohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(idPermohonan, "PGA", "TER", idHakmilik);
                senaraiPenerimaGadaian = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(idPermohonan, "PGA", idHakmilik);
            }//for urusan consent biasa
            else {
                senaraiPermohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByNotKodAndIdHakmilik(idPermohonan, "TER", idHakmilik);
            }

            senaraiTuanTanahTerlibat = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(idPermohonan, "TER", idHakmilik);

            //AUTO SAVE PIHAK TERLIBAT (1 TUAN TANAH)
            if (senaraiKeempunyaan.size() == 1) {

                HakmilikPihakBerkepentingan hakPihak = new HakmilikPihakBerkepentingan();
                hakPihak = senaraiKeempunyaan.get(0);
                PermohonanPihak perPihak = permohonanPihakService.getPmohonPihakByKodAndIdHakmilik(permohonan.getIdPermohonan(), hakPihak.getHakmilik().getIdHakmilik(), hakPihak.getPihak().getIdPihak(), "TER");

                if (senaraiPemohon.size() > 0) {

                    if (perPihak == null) {

                        PermohonanPihak perPhak = new PermohonanPihak();
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setDimasukOleh(pengguna);
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        perPhak.setInfoAudit(infoAudit);
                        perPhak.setPermohonan(permohonan);
                        perPhak.setPihak(hakPihak.getPihak());
                        perPhak.setSyerPembilang(hakPihak.getSyerPembilang());
                        perPhak.setSyerPenyebut(hakPihak.getSyerPenyebut());
                        perPhak.setCawangan(permohonan.getCawangan());
                        perPhak.setHakmilik(hakPihak.getHakmilik());
                        KodJenisPihakBerkepentingan kodJenis = new KodJenisPihakBerkepentingan();
                        kodJenis.setKod("TER");
                        perPhak.setJenis(kodJenis);
                        perPhak.setKaitan(hakPihak.getJenis().getKod());
                        permohonanPihakService.saveOrUpdate(perPhak);
                    }
                }

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
                    }
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

//        if (senaraiKeempunyaan != null) {
//            senaraiKeempunyaan = validatePihakBerkepentingan(senaraiKeempunyaan);
//            if (senaraiKeempunyaan.isEmpty()) {
//                addSimpleError("Sila pastikan tuan tanah adalah dari pihak yang SAMA.");
//            } else {
//                ctx.getRequest().setAttribute("edit", Boolean.TRUE);
//            }
//
//        } else {

        //CHECK PIHAK TERLIBAT IF TUAN TANAH BUKAN PEMOHON (BANYAK TUAN TANAH)
        if (permohonan.getSenaraiHakmilik().size() == 1) {
            if (senaraiKeempunyaan.size() > 1) {
                if (senaraiPemohon.size() > 0) {
//                    boolean tuanTanahIsPemohon = false;
//                    for (HakmilikPihakBerkepentingan hakPihak : senaraiKeempunyaan) {
//                        for (Pemohon pmhon : senaraiPemohon) {
//                            if (pmhon.getPihak() == hakPihak.getPihak()) {
//                                tuanTanahIsPemohon = true;
//                            }
//                        }
//                    }
                    if (!permohonan.getKodUrusan().getKod().equals("BTADT")) {
//                        if (!tuanTanahIsPemohon) {
                        if (senaraiTuanTanahTerlibat.isEmpty()) {
                            addSimpleError("Sila pilih pemilik yang terlibat dengan permohonan ini.");
                        }
//                        }
                    }
                }
            }
        }

        ctx.getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP(MAIN_PAGE).addParameter("tab", Boolean.TRUE);
    }

    public Resolution showFormDisplay() {
        return new JSP(MAIN_PAGE).addParameter("tab", Boolean.TRUE);
    }

    public Resolution simpanPemohon() {
        List<Pemohon> senaraiPemohonSave = new ArrayList<Pemohon>();

        String[] param = getContext().getRequest().getParameterValues("idPihakBerkepentingan");


        for (String idPihakBerkepentingan : param) {

            HakmilikPihakBerkepentingan hakPihak = hakmilikPihakBerkepentinganDAO.findById(Long.parseLong(idPihakBerkepentingan));
            Pihak pihakTemp = hakPihak.getPihak();
            Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(permohonan, pihakTemp);
            if (pmohon == null) {
                Pemohon pemohonTemp = new Pemohon();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                pemohonTemp.setInfoAudit(infoAudit);
                pemohonTemp.setPermohonan(permohonan);
                pemohonTemp.setPihak(pihakTemp);
                pemohonTemp.setCawangan(permohonan.getCawangan());
                senaraiPemohonSave.add(pemohonTemp);
            }
        }
        if (senaraiPemohonSave.size() > 0) {
            pemohonService.saveOrUpdateMultiple(senaraiPemohonSave);
        }
        ctx.getRequest().setAttribute("edit", Boolean.TRUE);

        return new RedirectResolution(PihakPenerimaActionBean.class).addParameter("idHakmilik", idHakmilik);
    }

    public Resolution simpanPihakTerlibat() {
        List<PermohonanPihak> senaraiTerlibatSave = new ArrayList<PermohonanPihak>();

        String[] param = getContext().getRequest().getParameterValues("idPihakBerkepentingan");


        for (String idPihakBerkepentingan : param) {
            HakmilikPihakBerkepentingan hakPihak = hakmilikPihakBerkepentinganDAO.findById(Long.parseLong(idPihakBerkepentingan));
            Pihak pihakTemp = hakPihak.getPihak();

            PermohonanPihak perPihak = permohonanPihakService.getPmohonPihakByKodAndIdHakmilik(permohonan.getIdPermohonan(), hakPihak.getHakmilik().getIdHakmilik(), pihakTemp.getIdPihak(), "TER");

            if (perPihak == null) {
                PermohonanPihak perPhak = new PermohonanPihak();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                perPhak.setInfoAudit(infoAudit);
                perPhak.setPermohonan(permohonan);
                perPhak.setPihak(pihakTemp);
                perPhak.setCawangan(permohonan.getCawangan());
                perPhak.setSyerPembilang(hakPihak.getSyerPembilang());
                perPhak.setSyerPenyebut(hakPihak.getSyerPenyebut());
                KodJenisPihakBerkepentingan kodJenis = new KodJenisPihakBerkepentingan();
                kodJenis.setKod("TER");
                perPhak.setJenis(kodJenis);
                perPhak.setKaitan(hakPihak.getJenis().getKod());
                perPhak.setHakmilik(hakPihak.getHakmilik());
                senaraiTerlibatSave.add(perPhak);
            }

        }
        if (senaraiTerlibatSave.size() > 0) {
            permohonanPihakService.saveOrUpdate(senaraiTerlibatSave);
        }
        ctx.getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(PihakPenerimaActionBean.class).addParameter("idHakmilik", idHakmilik);
    }

    public Resolution viewPihakDetail() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String jenis = getContext().getRequest().getParameter("jenis");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        pihak = pihakService.findById(idPihak);
        pengarahList = pihak.getSenaraiPengarah();

        if (jenis.equals("penerima") || jenis.equals("pemohon")) {
            List<Pihak> pihakList = new ArrayList<Pihak>();
            pihakList = consentService.findSenaraiPihakByNama(pihak.getNama());

            senaraiTanahLain = new ArrayList<HakmilikPihakBerkepentingan>();
            int count = 1;
            for (Pihak pihakTemp : pihakList) {
                LOG.info(count + ". CHECK TANAH LAIN FOR ID PIHAK : " + pihakTemp.getIdPihak() + " : " + pihakTemp.getNama());
                List<HakmilikPihakBerkepentingan> hakPihakList = new ArrayList<HakmilikPihakBerkepentingan>();
                hakPihakList = consentService.findSenaraiHakPihakByNamaAndJenis(String.valueOf(pihakTemp.getIdPihak()), "PM");

                if (hakPihakList != null) {
                    senaraiTanahLain.addAll(hakPihakList);
                }
                count++;
            }
        }

        if (jenis.equals("penerima")) {

            if (idHakmilik != null) {
                permohonanPihak = permohonanPihakService.getMohonPihakByIdPihakIdHakmilikNotKod(Long.parseLong(idPihak), idHakmilik, permohonan.getIdPermohonan(), "TER");
            }

            if (permohonanPihak.getPihakCawangan() != null) {
                PihakCawangan pihakCawangan = pihakCawanganDAO.findById(permohonanPihak.getPihakCawangan().getIdPihakCawangan());
                cawanganList = new ArrayList<PihakCawangan>();
                cawanganList.add(pihakCawangan);
            }

            if (permohonanPihak.getSenaraiHubungan() != null) {
                senaraiPihakHubungan = permohonanPihak.getSenaraiHubungan();
            }

            getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        } else if (jenis.equals("terlibat")) {

            if (idHakmilik != null) {
                permohonanPihak = permohonanPihakService.getSenaraiMohonPihakByIdPihakIdHakmilik(Long.parseLong(idPihak), idHakmilik, permohonan.getIdPermohonan());
            } else {
                permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdPihak(permohonan.getIdPermohonan(), Long.parseLong(idPihak));

            }
            if (permohonanPihak.getPihakCawangan() != null) {
                PihakCawangan pihakCawangan = pihakCawanganDAO.findById(permohonanPihak.getPihakCawangan().getIdPihakCawangan());
                cawanganList = new ArrayList<PihakCawangan>();
                cawanganList.add(pihakCawangan);
            }
            getContext().getRequest().setAttribute("terlibat", Boolean.TRUE);
        } else if (jenis.equals("pemohon")) {
            pemohon = pemohonService.findPemohonByPermohonanPihak(permohonan, pihak);
            getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        } else if (jenis.equals("tuanTanah")) {
            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
        }

        return new ForwardResolution("/WEB-INF/jsp/common/paparan_pihak.jsp").addParameter("popup", "true");
    }

    public Resolution viewMohonPihakDetail() {
        String jenis = getContext().getRequest().getParameter("jenis");
        String idMohonPihak = getContext().getRequest().getParameter("idMohonPihak");

        permohonanPihak = permohonanPihakDAO.findById(Long.parseLong(idMohonPihak));
        pihak = pihakService.findById(String.valueOf(permohonanPihak.getPihak().getIdPihak()));
        pengarahList = pihak.getSenaraiPengarah();
        if (permohonanPihak.getPihakCawangan() != null) {
            PihakCawangan pihakCawangan = pihakCawanganDAO.findById(permohonanPihak.getPihakCawangan().getIdPihakCawangan());
            cawanganList = new ArrayList<PihakCawangan>();
            cawanganList.add(pihakCawangan);
        }

        if (jenis.equals("penerima")) {
            List<Pihak> pihakList = new ArrayList<Pihak>();
            pihakList = consentService.findSenaraiPihakByNama(pihak.getNama());

            senaraiTanahLain = new ArrayList<HakmilikPihakBerkepentingan>();
            int count = 1;
            for (Pihak pihakTemp : pihakList) {
                LOG.info(count + ". CHECK TANAH LAIN FOR ID PIHAK : " + pihakTemp.getIdPihak() + " : " + pihakTemp.getNama());
                List<HakmilikPihakBerkepentingan> hakPihakList = new ArrayList<HakmilikPihakBerkepentingan>();
                hakPihakList = consentService.findSenaraiHakPihakByNamaAndJenis(String.valueOf(pihakTemp.getIdPihak()), "PM");

                if (hakPihakList != null) {
                    senaraiTanahLain.addAll(hakPihakList);
                }
                count++;
            }
        }

        if (jenis.equals("penerima")) {

            if (permohonanPihak.getSenaraiHubungan() != null) {
                senaraiPihakHubungan = permohonanPihak.getSenaraiHubungan();
            }
            if ((kodNegeri.equals("04") && permohonan.getKodUrusan().getKod().equals("PMMK1"))
                    || (kodNegeri.equals("04") && permohonan.getKodUrusan().getKod().equals("PMMK2"))) {
                pemohon = pemohonService.findPemohonByPermohonanPihak(permohonanPihak.getPermohonan(), permohonanPihak.getPihak());
            }
            getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        } else if (jenis.equals("terlibat")) {

            getContext().getRequest().setAttribute("terlibat", Boolean.TRUE);
        }

        return new ForwardResolution("/WEB-INF/jsp/consent/paparan_pihak_popup.jsp").addParameter("popup", "true");
    }

    public Resolution searchPihak() {
        ctx = (etanahActionBeanContext) getContext();
        String doSearch = ctx.getRequest().getParameter("doSearch");
        kodNegeri = ctx.getKodNegeri();

        if (StringUtils.isNotBlank(doSearch)) {
            LOG.debug("Starting cari pihak...");
//            senaraiPihak = pihakService.findAll(ctx.getRequest().getParameterMap());
//            senaraiPihak = pihakService.findAllByDimasuk(ctx.getRequest().getParameterMap(), "MIG");
            senaraiPihak = pihakService.findAllByFlagPihak(ctx.getRequest().getParameterMap());

            if (senaraiPihak.isEmpty()) {
                getContext().getRequest().setAttribute("addNewPihak", "true");
            } else {
                LOG.debug("cari " + senaraiPihak.size() + " pihak Successfully...");
            }
        }
        ctx.getRequest().setAttribute("penerima", Boolean.TRUE);
        return new JSP(SEARCH_PAGE).addParameter("popup", Boolean.TRUE);
    }

    public Resolution searchPihakPemohon() {
        ctx = (etanahActionBeanContext) getContext();
        String doSearch = ctx.getRequest().getParameter("doSearch");
        kodNegeri = ctx.getKodNegeri();

        if (StringUtils.isNotBlank(doSearch)) {
            LOG.debug("Start find pihak...");
            senaraiPihak = pihakService.findAll(ctx.getRequest().getParameterMap());
            if (senaraiPihak.isEmpty()) {
                getContext().getRequest().setAttribute("addNewPihak", "true");
            }
        }
        ctx.getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP(SEARCH_PAGE).addParameter("popup", Boolean.TRUE);
    }

    public Resolution showSearchForm() {

        String TYPE = ctx.getRequest().getParameter("type");
        pihak = new Pihak();

        if (TYPE.equals("pemohon")) {
            ctx.getRequest().setAttribute("pemohon", Boolean.TRUE);
        } else if (TYPE.equals("penerima")) {
            ctx.getRequest().setAttribute("penerima", Boolean.TRUE);
        }

        return new JSP(SEARCH_PAGE).addParameter("popup", Boolean.TRUE);

    }

    public Resolution showSearchFormPenerima() {

        pihak = new Pihak();
        ctx.getRequest().setAttribute("penerima", Boolean.TRUE);
        return new JSP(SEARCH_PAGE).addParameter("popup", Boolean.TRUE);
    }

    public Resolution showSearchFormPemohon() {

        pihak = new Pihak();
        ctx.getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP(SEARCH_PAGE).addParameter("popup", Boolean.TRUE);
    }

    public Resolution selectPihak() {
        ctx = (etanahActionBeanContext) getContext();
        kodNegeri = ctx.getKodNegeri();
        idPermohonan = (String) ctx.getRequest().getSession().getAttribute("idPermohonan");
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
        pihak = pihakService.findById(idPihak);
        if (pihak != null && StringUtils.isBlank(pihak.getAlamat1()) && StringUtils.isBlank(pihak.getAlamat2()) && StringUtils.isBlank(pihak.getAlamat3()) && StringUtils.isBlank(pihak.getAlamat4()) && StringUtils.isBlank(pihak.getPoskod()) && pihak.getNegeri() == null) {
            ctx.getRequest().setAttribute("newPihak", "true");
        }
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();

        Pemohon pemohonData = pemohonService.findPemohonByPermohonanPihak(permohonan, pihak);

        if (pemohonData != null) {
            permohonanPihak = new PermohonanPihak();
            permohonanPihak.setUmur(pemohonData.getUmur());
            permohonanPihak.setPendapatan(pemohonData.getPendapatan());
            permohonanPihak.setPekerjaan(pemohonData.getPekerjaan());
            permohonanPihak.setTangungan(pemohonData.getTanggungan());
            permohonanPihak.setKaitan(pemohonData.getKaitan());
            permohonanPihak.setStatusKahwin(pemohonData.getStatusKahwin());
        }

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
            consentService.simpanPihakAlamatTamb(pihakAlamatTamb);
        } else {
            pihakAlamatTamb = senaraiAlamatTamb.get(0);
        }

        ctx.getRequest().setAttribute("penerima", Boolean.TRUE);
        return new JSP(MAIN_PAGE_POPUP).addParameter("popup", Boolean.TRUE);
    }

    public Resolution selectPihakPemohon() {
        ctx = (etanahActionBeanContext) getContext();
        kodNegeri = ctx.getKodNegeri();

        idPermohonan = (String) ctx.getRequest().getSession().getAttribute("idPermohonan");
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
            if (senaraiHakmilikTerlibat.size() > 1) {
                ctx.getRequest().setAttribute("moreThanOneHakmilik", "true");
            }
            kodUrusan = permohonan.getKodUrusan().getKod();

            ctx.getRequest().setAttribute("disabledWaris", "false");

        }

        String idPihak = ctx.getRequest().getParameter("idPihak");
        pihak = pihakService.findById(idPihak);
        if (pihak != null && StringUtils.isBlank(pihak.getAlamat1()) && StringUtils.isBlank(pihak.getAlamat2()) && StringUtils.isBlank(pihak.getAlamat3()) && StringUtils.isBlank(pihak.getAlamat4()) && StringUtils.isBlank(pihak.getPoskod()) && pihak.getNegeri() == null) {
            ctx.getRequest().setAttribute("newPihak", "true");
        }
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        ctx.getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP(MAIN_PAGE_POPUP).addParameter("popup", Boolean.TRUE);
    }

    public Resolution addNewPihak() {
        ctx.getRequest().setAttribute("newPihak", "true");
        ctx.getRequest().setAttribute("disabledWaris", "false");
        ctx.getRequest().setAttribute("penerima", Boolean.TRUE);
        return new JSP(MAIN_PAGE_POPUP).addParameter("popup", Boolean.TRUE);
    }

    public Resolution addNewPihakPemohon() {
        ctx.getRequest().setAttribute("newPihak", "true");
        ctx.getRequest().setAttribute("disabledWaris", "false");
        ctx.getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP(MAIN_PAGE_POPUP).addParameter("popup", Boolean.TRUE);
    }

    public Resolution reloadEdit() {
        return showForm();
    }

    public Resolution reload() {
        return showFormDisplay();
    }

    public Resolution pihakIsmen() {
        ctx.getRequest().setAttribute("edit", "true");
        return new JSP("daftar/kemasukan_pihak_main_ismen.jsp").addParameter("tab", Boolean.TRUE);
    }

    public Resolution pihakPemegangAmanah() {
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
        return new JSP("daftar/kemasukan_pihak_main_amanah.jsp").addParameter("tab", Boolean.TRUE);
    }

    public Resolution delete() {
        String JENIS_PIHAK = ctx.getRequest().getParameter("jenisPihak");
        String[] uids = ctx.getRequest().getParameterValues("uids");
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");

        if (JENIS_PIHAK.equals("pemohon")) {
            pemohonService.deleteSelectedPemohon(uids);
        } else if (JENIS_PIHAK.equals("penerima") || JENIS_PIHAK.equals("pihakTerlibat")) {
            permohonanPihakService.delete(uids);
        }
        return new RedirectResolution(PihakPenerimaActionBean.class).addParameter("idHakmilik", idHakmilik);

    }

    public Resolution deletePemohon() {
        String[] id = getContext().getRequest().getParameterValues("idPemohon");
        idHakmilik = getContext().getRequest().getParameter("hakmilik");

        if (isDebug) {
            LOG.debug("id length = " + id.length);
        }

        if (id.length > 0) {
            pemohonService.deleteSelectedPemohon(id);
        }

        return new RedirectResolution(PihakPenerimaActionBean.class).addParameter("idHakmilik", idHakmilik);
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
            for (String uid : uids) {
                if (uid == null) {
                    continue;
                }
                pihak = pihakService.findById(uid);
                if (pihak == null) {
                    continue;
                }

                KodJenisPihakBerkepentingan jenis = null;

                String kod = "PM";

                if (kodUrusan.equals("TRPA")) {
                    kod = "PA";
                }

                HakmilikPihakBerkepentingan hpk =
                        hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pihak, hakmilik, kod);

                if (hpk == null) {
                    PermohonanPihak pp = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, pihak.getIdPihak());
                    if (pp != null) {
                        jenis = pp.getJenis();
                    }
                } else {
                    jenis = hpk.getJenis();
                }



                if (kodUrusan.equals("TRPA")) {
                    PermohonanAtasPihakBerkepentingan papb = new PermohonanAtasPihakBerkepentingan();
                    papb.setPermohonan(permohonan);
                    papb.setInfoAudit(ia);
                    papb.setPihakBerkepentingan(hpk);
                    senarai3.add(papb);
                } else {
                    Pemohon pmhn = pemohonService.findPemohonByPermohonanPihak(permohonan, pihak, hakmilik, kod);
                    if (pmhn == null) {
                        pmhn = new Pemohon();
                        pmhn.setHakmilik(hakmilik);
                        pmhn.setCawangan(hakmilik.getCawangan());
                        pmhn.setInfoAudit(ia);
                        pmhn.setPihak(pihak);
                        pmhn.setPermohonan(permohonan);
                        if (jenis != null) {
                            pmhn.setJenis(jenis);
                        }

                    }
                    senarai1.add(pmhn);
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

        return new RedirectResolution(PihakPenerimaActionBean.class).addParameter("idHakmilik", idHakmilik);
    }

    public Resolution editTuanTanah() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));

        if (pihak.getTarikhMati() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            tarikhMati = formatter.format(pihak.getTarikhMati());
        }

        idHakmilik = getContext().getRequest().getParameter("hakmilik");
        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
        }

        return new JSP("consent/edit_pihak_mati.jsp").addParameter("popup", "true");
    }

    public Resolution editPemohon() {
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        pemohon = pemohonService.findById(idPemohon);
        pihak = pemohon.getPihak();

        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();

        if (pihak.getTarikhLahir() != null) {
            tarikhLahir = sdf.format(pihak.getTarikhLahir());
        }

        if (pihak.getTempatLahir() != null) {
            if (!pihak.getTempatLahir().equalsIgnoreCase("JOHOR") && !pihak.getTempatLahir().equalsIgnoreCase("KEDAH") && !pihak.getTempatLahir().equalsIgnoreCase("KELANTAN") && !pihak.getTempatLahir().equalsIgnoreCase("MELAKA") && !pihak.getTempatLahir().equalsIgnoreCase("NEGERI SEMBILAN") && !pihak.getTempatLahir().equalsIgnoreCase("PAHANG") && !pihak.getTempatLahir().equalsIgnoreCase("PERAK") && !pihak.getTempatLahir().equalsIgnoreCase("PERLIS") && !pihak.getTempatLahir().equalsIgnoreCase("PULAU PINANG") && !pihak.getTempatLahir().equalsIgnoreCase("SABAH") && !pihak.getTempatLahir().equalsIgnoreCase("SARAWAK") && !pihak.getTempatLahir().equalsIgnoreCase("SELANGOR") && !pihak.getTempatLahir().equalsIgnoreCase("TERENGGANU") && !pihak.getTempatLahir().equalsIgnoreCase("WP KUALA LUMPUR") && !pihak.getTempatLahir().equalsIgnoreCase("WP LABUAN") && !pihak.getTempatLahir().equalsIgnoreCase("WP PUTRAJAYA")) {
                tempatLahirLain = pihak.getTempatLahir();
                pihak.setTempatLahir("LAIN-LAIN");
            }
        }

        if (pemohon != null) {
            if (pemohon.getKaitan() != null) {

                if (permohonan.getKodUrusan().getKod().equals("PMADT") || permohonan.getKodUrusan().getKod().equals("CGADT") || permohonan.getKodUrusan().getKod().equals("PAADT") || permohonan.getKodUrusan().getKod().equals("TMADT") || permohonan.getKodUrusan().getKod().equals("BTADT")) {
                    if (!pemohon.getKaitan().equalsIgnoreCase("ANAK") && !pemohon.getKaitan().equalsIgnoreCase("ADIK BERADIK SESUKU") && !pemohon.getKaitan().equalsIgnoreCase("CUCU") && !pemohon.getKaitan().equalsIgnoreCase("IBU KANDUNG") && !pemohon.getKaitan().equalsIgnoreCase("IBU SAUDARA") && !pemohon.getKaitan().equalsIgnoreCase("MOYANG") && !pemohon.getKaitan().equalsIgnoreCase("NENEK") && !pemohon.getKaitan().equalsIgnoreCase("SEPUPU")) {
                        hubunganLain = pemohon.getKaitan();
                        pemohon.setKaitan("LAIN-LAIN");
                    }
                } else {
                    if (!pemohon.getKaitan().equalsIgnoreCase("IBU BAPA KEPADA ANAK") && !pemohon.getKaitan().equalsIgnoreCase("ANAK KEPADA IBU BAPA") && !pemohon.getKaitan().equalsIgnoreCase("SUAMI KEPADA ISTERI") && !pemohon.getKaitan().equalsIgnoreCase("ISTERI KEPADA SUAMI") && !pemohon.getKaitan().equalsIgnoreCase("SAUDARA-MARA") && !pemohon.getKaitan().equalsIgnoreCase("PENJUAL / PEMBELI")) {
                        hubunganLain = pemohon.getKaitan();
                        pemohon.setKaitan("LAIN-LAIN");
                    }
                }
            }
        }

        idHakmilik = getContext().getRequest().getParameter("hakmilik");
        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
        }

        ctx.getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("consent/pihak_penerima_edit.jsp").addParameter("popup", "true");
    }

    public Resolution editPenerima() {
        String idMohonPihak = getContext().getRequest().getParameter("idMohonPihak");

        permohonanPihak = permohonanPihakService.findById(idMohonPihak);

        pihak = permohonanPihak.getPihak();

        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();

        if (pihak.getTarikhLahir() != null) {
            tarikhLahir = sdf.format(pihak.getTarikhLahir());
        }

        idHakmilik = getContext().getRequest().getParameter("hakmilik");

        if (pihak.getTempatLahir() != null) {
            if (!pihak.getTempatLahir().equalsIgnoreCase("JOHOR") && !pihak.getTempatLahir().equalsIgnoreCase("KEDAH") && !pihak.getTempatLahir().equalsIgnoreCase("KELANTAN") && !pihak.getTempatLahir().equalsIgnoreCase("MELAKA") && !pihak.getTempatLahir().equalsIgnoreCase("NEGERI SEMBILAN") && !pihak.getTempatLahir().equalsIgnoreCase("PAHANG") && !pihak.getTempatLahir().equalsIgnoreCase("PERAK") && !pihak.getTempatLahir().equalsIgnoreCase("PERLIS") && !pihak.getTempatLahir().equalsIgnoreCase("PULAU PINANG") && !pihak.getTempatLahir().equalsIgnoreCase("SABAH") && !pihak.getTempatLahir().equalsIgnoreCase("SARAWAK") && !pihak.getTempatLahir().equalsIgnoreCase("SELANGOR") && !pihak.getTempatLahir().equalsIgnoreCase("TERENGGANU") && !pihak.getTempatLahir().equalsIgnoreCase("WP KUALA LUMPUR") && !pihak.getTempatLahir().equalsIgnoreCase("WP LABUAN") && !pihak.getTempatLahir().equalsIgnoreCase("WP PUTRAJAYA")) {
                tempatLahirLain = pihak.getTempatLahir();
                pihak.setTempatLahir("LAIN-LAIN");
            }
        }

        if (permohonanPihak != null) {
            jenisPihak = permohonanPihak.getJenis().getKod();
            if (permohonanPihak.getKaitan() != null) {
                if (permohonan.getKodUrusan().getKod().equals("PMADT") || permohonan.getKodUrusan().getKod().equals("CGADT") || permohonan.getKodUrusan().getKod().equals("PAADT") || permohonan.getKodUrusan().getKod().equals("TMADT") || permohonan.getKodUrusan().getKod().equals("BTADT")) {
                    if (!permohonanPihak.getKaitan().equalsIgnoreCase("ANAK") && !permohonanPihak.getKaitan().equalsIgnoreCase("ADIK BERADIK SESUKU") && !permohonanPihak.getKaitan().equalsIgnoreCase("CUCU") && !permohonanPihak.getKaitan().equalsIgnoreCase("IBU KANDUNG") && !permohonanPihak.getKaitan().equalsIgnoreCase("IBU SAUDARA") && !permohonanPihak.getKaitan().equalsIgnoreCase("MOYANG") && !permohonanPihak.getKaitan().equalsIgnoreCase("NENEK") && !permohonanPihak.getKaitan().equalsIgnoreCase("SEPUPU")) {
                        hubunganLain = permohonanPihak.getKaitan();
                        permohonanPihak.setKaitan("LAIN-LAIN");
                    }
                } else {
                    if (!permohonanPihak.getKaitan().equalsIgnoreCase("IBU BAPA KEPADA ANAK") && !permohonanPihak.getKaitan().equalsIgnoreCase("ANAK KEPADA IBU BAPA") && !permohonanPihak.getKaitan().equalsIgnoreCase("SUAMI KEPADA ISTERI") && !permohonanPihak.getKaitan().equalsIgnoreCase("ISTERI KEPADA SUAMI") && !permohonanPihak.getKaitan().equalsIgnoreCase("SAUDARA-MARA") && !permohonanPihak.getKaitan().equalsIgnoreCase("PENJUAL / PEMBELI")) {
                        hubunganLain = permohonanPihak.getKaitan();
                        permohonanPihak.setKaitan("LAIN-LAIN");
                    }
                }
            }
            senaraiPihakHubungan = permohonanPihak.getSenaraiHubungan();
        }

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
            consentService.simpanPihakAlamatTamb(pihakAlamatTamb);
        } else {
            pihakAlamatTamb = senaraiAlamatTamb.get(0);
        }

        ctx.getRequest().setAttribute("penerima", Boolean.TRUE);

        return new JSP("consent/pihak_penerima_edit.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohonPenerimaForm() {

        String id = ctx.getRequest().getParameter("id");
        String idPihak = ctx.getRequest().getParameter("idPihak");
        String JENIS_PIHAK = ctx.getRequest().getParameter("jenis_pihak");
        String RETURN_JSP = "daftar/kemaskini_pemohon.jsp";

        pihak = pihakService.findById(idPihak);

        if (JENIS_PIHAK.equals("pemohon")) {
            pemohon = pemohonService.findById(id);
            if (pemohon == null) {
                PermohonanAtasPihakBerkepentingan papb = permohonanAtasPihakService.findById(id);
                if (papb != null) {
                    pemohon = new Pemohon();
                    pemohon.setPihak(papb.getPihakBerkepentingan().getPihak());
                }
            }

        } else if (JENIS_PIHAK.equals("penerima")) {
            RETURN_JSP = "daftar/kemaskini_penerima.jsp";
            permohonanPihak = permohonanPihakService.findById(id);
            if (kodUrusan.equals("PNPA")) {
                senaraiPihakHubungan = permohonanPihak.getSenaraiHubungan();
                ctx.getRequest().setAttribute("disabledWaris", "false");
            } else if (kodUrusan.equals("TRPA")) {
                ctx.getRequest().setAttribute("disabledWaris", "true");
            }
        }
        return new JSP(RETURN_JSP).addParameter("popup", "true");
    }

    public Resolution savePihakPopup() throws ParseException {

        String copyToAllHakmilik = getContext().getRequest().getParameter("copyToAll");
        jenisPihak = getContext().getRequest().getParameter("jenisPihak");
        String pilih = ctx.getRequest().getParameter("pilih");
        PihakCawangan pihakCawangan;

        PermohonanPihak pp = permohonanPihakService.getPmohonPihakByKodAndIdHakmilik(idPermohonan, idHakmilik, pihak.getIdPihak(), jenisPihak);

        if (pp == null) {

            if (isDebug) {
                LOG.debug("pilih =" + pilih);
            }

            idHakmilik = ctx.getRequest().getParameter("idHakmilik");

            LOG.info("ID Hakmilik Saving : " + idHakmilik);

            hakmilik = hakmilikDAO.findById(idHakmilik);

            permohonan = null;

            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);
            }
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

            if (pihakTemp == null) {
                pihakTemp = new Pihak();
                pihakTemp.setSuratAlamat1(pihakAlamatTamb.getAlamatKetiga1());
                pihakTemp.setSuratAlamat2(pihakAlamatTamb.getAlamatKetiga2());
                pihakTemp.setSuratAlamat3(pihakAlamatTamb.getAlamatKetiga3());
                pihakTemp.setSuratAlamat4(pihakAlamatTamb.getAlamatKetiga4());
                pihakTemp.setSuratPoskod(pihakAlamatTamb.getAlamatKetigaPoskod());
                pihakTemp.setSuratNegeri(pihakAlamatTamb.getAlamatKetigaNegeri());
            }

            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setNoPengenalanLain(pihak.getNoPengenalanLain());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setSuku(pihak.getSuku());
            pihakTemp.setSubSuku(pihak.getSubSuku());
            pihakTemp.setKeturunan(pihak.getKeturunan());
            pihakTemp.setTempatSuku(pihak.getTempatSuku());
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

            KodFlagPihak kodFlagPihak = new KodFlagPihak();
            kodFlagPihak.setKod("AK");
            pihakTemp.setKodFlagPihak(kodFlagPihak);

            pihakTemp.setInfoAudit(infoAudit);
            pihakTemp.setTempatLahir(pihak.getTempatLahir());

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }

            if (pihak.getTempatLahir() != null) {
                if (pihak.getTempatLahir().equalsIgnoreCase("lain-lain")) {
                    pihakTemp.setTempatLahir(tempatLahirLain);
                } else {
                    pihakTemp.setTempatLahir(pihak.getTempatLahir());
                }
            }

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }

            if (permohonanPihak == null) {
                permohonanPihak = new PermohonanPihak();
            }

            permohonanPihak.setPermohonan(permohonan);
            permohonanPihak.setPihak(pihakTemp);



            if (permohonanPihak.getKaitan() != null) {
                if (permohonanPihak.getKaitan().equalsIgnoreCase("lain-lain")) {
                    permohonanPihak.setKaitan(hubunganLain);
                }
            }

            permohonanPihak.setCawangan(permohonan.getCawangan());
            permohonanPihak.setInfoAudit(infoAudit);
            permohonanPihak.setHakmilik(hakmilik);
            KodJenisPihakBerkepentingan kodJenisPihak = new KodJenisPihakBerkepentingan();
            kodJenisPihak.setKod(jenisPihak);
            permohonanPihak.setJenis(kodJenisPihak);

            if (jenisPihak.equals("PA")) {
                permohonanPihak.setSyerPembilang(0);
                permohonanPihak.setSyerPenyebut(0);
            }

            if (pihakAlamatTamb != null) {
                AlamatSurat alamatSurat = new AlamatSurat();
                alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
                alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
                alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
                alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
                alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
                alamatSurat.setNegeriSurat(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
                permohonanPihak.setAlamatSurat(alamatSurat);

            }

            permohonanPihak.setNama(pihak.getNama());
            permohonanPihak.setJenisPengenalan(pihak.getJenisPengenalan());
            permohonanPihak.setNoPengenalan(pihak.getNoPengenalan());
            permohonanPihak.setWargaNegara(pihak.getWargaNegara());

            Alamat alamatPenerima = new Alamat();
            alamatPenerima.setAlamat1(StringUtils.isNotBlank(pihak.getAlamat1()) ? pihak.getAlamat1() : "");
            alamatPenerima.setAlamat2(StringUtils.isNotBlank(pihak.getAlamat2()) ? pihak.getAlamat2() : "");
            alamatPenerima.setAlamat3(StringUtils.isNotBlank(pihak.getAlamat3()) ? pihak.getAlamat3() : "");
            alamatPenerima.setAlamat4(StringUtils.isNotBlank(pihak.getAlamat4()) ? pihak.getAlamat4() : "");
            alamatPenerima.setPoskod(StringUtils.isNotBlank(pihak.getPoskod()) ? pihak.getPoskod() : "");
            alamatPenerima.setNegeri(pihak.getNegeri() != null ? pihak.getNegeri() : null);
            permohonanPihak.setAlamat(alamatPenerima);

            if (StringUtils.isNotBlank(copyToAllHakmilik)) {

                pihakService.saveOrUpdatePihak(pihakTemp);
                savePihakAlamat(pihakTemp, pihakAlamatTamb);

                pihakCawangan = savePihakCaw(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp, pilih);

                if (StringUtils.isNotBlank(pilih)) {
                    if (!pilih.startsWith("pc_")) {
                        pihakCawangan = pihakService.findPihakCawangan(pilih);
                    }
                    permohonanPihak.setPihakCawangan(pihakCawangan);
                }

                pihakService.saveMohonPihakMultipleHakmilik(pihakTemp, permohonanPihak);

                for (PermohonanPihak permohonanPihakTemp : permohonan.getSenaraiPihak()) {
                    LOG.info(":: ID PERMOHONAN PIHAK :: " + permohonanPihakTemp.getIdPermohonanPihak());
                    saveWarisInfo(ctx.getRequest().getParameterMap(), infoAudit, permohonanPihakTemp);
                }
            } else {
                pihakService.saveOrUpdatePihak(pihakTemp);
                savePihakAlamat(pihakTemp, pihakAlamatTamb);

                pihakCawangan = savePihakCaw(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp, pilih);

                if (StringUtils.isNotBlank(pilih)) {
                    if (!pilih.startsWith("pc_")) {
                        pihakCawangan = pihakService.findPihakCawangan(pilih);
                    }
                    permohonanPihak.setPihakCawangan(pihakCawangan);
                }

                pihakService.saveOrUpdatePermohonanPihak(permohonanPihak);

                saveWarisInfo(ctx.getRequest().getParameterMap(), infoAudit, permohonanPihak);
            }
            savePengarahInfo(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp);

        } else {
            addSimpleError("Maklumat Sama!");
        }

        return new RedirectResolution(PihakPenerimaActionBean.class).addParameter("idHakmilik", idHakmilik);

    }

    public Resolution savePemohonPopup() throws ParseException {

        String pilih = ctx.getRequest().getParameter("pilih");

        if (isDebug) {
            LOG.debug("pilih =" + pilih);
        }

        permohonan = new Permohonan();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihakTemp == null) {
            pihakTemp = new Pihak();
        }

        pihakTemp.setNama(pihak.getNama());
        pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
        pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
        pihakTemp.setNoPengenalanLain(pihak.getNoPengenalanLain());
        pihakTemp.setKodJantina(pihak.getKodJantina());
        pihakTemp.setBangsa(pihak.getBangsa());
        pihakTemp.setSuku(pihak.getSuku());
        pihakTemp.setSubSuku(pihak.getSubSuku());
        pihakTemp.setKeturunan(pihak.getKeturunan());
        pihakTemp.setTempatSuku(pihak.getTempatSuku());
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
        pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
        pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
        pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
        pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
        pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
        pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
        pihakTemp.setKodJantina(pihak.getKodJantina());
        pihakTemp.setInfoAudit(infoAudit);
        KodFlagPihak kodFlagPihak = new KodFlagPihak();
        kodFlagPihak.setKod("AK");
        pihakTemp.setKodFlagPihak(kodFlagPihak);

        if (pihak.getTempatLahir() != null) {
            if (pihak.getTempatLahir().equalsIgnoreCase("lain-lain")) {
                pihakTemp.setTempatLahir(tempatLahirLain);
            } else {
                pihakTemp.setTempatLahir(pihak.getTempatLahir());
            }
        }

        if (tarikhLahir != null) {
            try {
                pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
            } catch (ParseException ex) {
                LOG.error("exception: " + ex.getMessage());
                throw ex;
            }
        }

        pihakService.saveOrUpdatePihak(pihakTemp);
        savePengarahInfo(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp);

        if (pihakTemp != null) {

            Pemohon pemohonTemp = new Pemohon();
            pemohonTemp.setPermohonan(permohonan);
            pemohonTemp.setPihak(pihakTemp);

            if (pemohon == null) {
                pemohon = new Pemohon();
            }

            if (pemohon.getKaitan() != null) {
                if (pemohon.getKaitan().equalsIgnoreCase("lain-lain")) {
                    pemohonTemp.setKaitan(hubunganLain);
                } else {
                    pemohonTemp.setKaitan(pemohon.getKaitan());
                }
            }
            pemohonTemp.setInfoAudit(infoAudit);
            pemohonTemp.setCawangan(permohonan.getCawangan());
            pemohonTemp.setStatusKahwin(pemohon.getStatusKahwin());
            pemohonTemp.setPekerjaan(pemohon.getPekerjaan());
            pemohonTemp.setUmur(pemohon.getUmur());
            pemohonTemp.setPendapatan(pemohon.getPendapatan());
            pemohonTemp.setTanggungan(pemohon.getTanggungan());

            if (pemohon != null) {
                pemohonTemp.setTempohMastautin(pemohon.getTempohMastautin());
            }

            PihakCawangan pc = savePihakCaw(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp, pilih);

            if (StringUtils.isNotBlank(pilih)) {
                if (!pilih.startsWith("pc_")) {
                    pc = pihakService.findPihakCawangan(pilih);
                }
                pemohonTemp.setPihakCawangan(pc);
            }


            pemohonService.saveOrUpdate(pemohonTemp);
        }

        return new RedirectResolution(PihakPenerimaActionBean.class);
    }

    public Resolution savePihak() throws ParseException {

        jenisPihak = getContext().getRequest().getParameter("jenisPihak");
        String pilih = ctx.getRequest().getParameter("pilih");

        idHakmilik = ctx.getRequest().getParameter("idHakmilik");

        LOG.info("ID Hakmilik Saving : " + idHakmilik);

        hakmilik = hakmilikDAO.findById(idHakmilik);

        permohonan = null;

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihakTemp == null) {
            pihakTemp = new Pihak();
        }

        pihakTemp.setNama(pihak.getNama());
        pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
        pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
        pihakTemp.setNoPengenalanLain(pihak.getNoPengenalanLain());
        pihakTemp.setKodJantina(pihak.getKodJantina());
        pihakTemp.setBangsa(pihak.getBangsa());
        pihakTemp.setSuku(pihak.getSuku());
        pihakTemp.setSubSuku(pihak.getSubSuku());
        pihakTemp.setKeturunan(pihak.getKeturunan());
        pihakTemp.setTempatSuku(pihak.getTempatSuku());
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
        pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
        pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
        pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
        pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
        pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
        pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
        pihakTemp.setInfoAudit(infoAudit);
        pihakTemp.setTempatLahir(pihak.getTempatLahir());

        KodFlagPihak kodFlagPihak = new KodFlagPihak();
        kodFlagPihak.setKod("AK");
        pihakTemp.setKodFlagPihak(kodFlagPihak);

        if (tarikhLahir != null) {
            try {
                pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
            } catch (ParseException ex) {
                LOG.error("exception: " + ex.getMessage());
                throw ex;
            }
        }

        if (pihak.getTempatLahir() != null) {
            if (pihak.getTempatLahir().equalsIgnoreCase("lain-lain")) {
                pihakTemp.setTempatLahir(tempatLahirLain);
            } else {
                pihakTemp.setTempatLahir(pihak.getTempatLahir());
            }
        }

        pihakService.saveOrUpdatePihak(pihakTemp);

        PihakCawangan pc = savePihakCaw(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp, pilih);

        savePengarahInfo(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp);

        return new RedirectResolution(PihakPenerimaActionBean.class, "selectPihak").addParameter("idPihak", pihakTemp.getIdPihak()).addParameter("idHakmilik", idHakmilik);

    }

    @HandlesEvent("simpanKemaskiniTuanTanah")
    public Resolution simpanTuanTanahEdit() {
        idHakmilik = getContext().getRequest().getParameter("hakmilik");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {

            pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

            InfoAudit infoAudit = new InfoAudit();

            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pihakTemp.setNoSijilMati(pihak.getNoSijilMati());
            pihakTemp.setSuku(pihak.getSuku());
            pihakTemp.setSubSuku(pihak.getSubSuku());
            pihakTemp.setKeturunan(pihak.getKeturunan());
            pihakTemp.setTempatSuku(pihak.getTempatSuku());
            pihakTemp.setInfoAudit(infoAudit);

            if (tarikhMati != null) {
                try {
                    pihakTemp.setTarikhMati(sdf.parse(tarikhMati));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                }
            }
            pihakService.saveOrUpdate(pihakTemp);

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new RedirectResolution(PihakPenerimaActionBean.class).addParameter("idHakmilik", idHakmilik);

        }
        tx.commit();
        addSimpleMessage("Kemaskini Data Berjaya.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(PihakPenerimaActionBean.class).addParameter("idHakmilik", idHakmilik);
    }

    @HandlesEvent("simpanKemaskiniPemohon")
    public Resolution simpanPemohonEdit() throws ParseException {
//        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        String pilih = ctx.getRequest().getParameter("pilih");
        if (isDebug) {
            LOG.debug("pilih =" + pilih);
        }

        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();
            Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

            if (pihakTemp != null) {

                infoAudit = pihakTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

                pihakTemp.setNama(pihak.getNama());
                pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
                pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
                pihakTemp.setNoPengenalanLain(pihak.getNoPengenalanLain());
                pihakTemp.setKodJantina(pihak.getKodJantina());
                pihakTemp.setBangsa(pihak.getBangsa());
                pihakTemp.setSuku(pihak.getSuku());
                pihakTemp.setSubSuku(pihak.getSubSuku());
                pihakTemp.setKeturunan(pihak.getKeturunan());
                pihakTemp.setTempatSuku(pihak.getTempatSuku());
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
                pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
                pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
                pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
                pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
                pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
                pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
                pihakTemp.setKodJantina(pihak.getKodJantina());
                pihakTemp.setInfoAudit(infoAudit);

                if (pihak.getTempatLahir() != null) {
                    if (pihak.getTempatLahir().equalsIgnoreCase("lain-lain")) {
                        pihakTemp.setTempatLahir(tempatLahirLain);
                    } else {
                        pihakTemp.setTempatLahir(pihak.getTempatLahir());
                    }
                }

                if (tarikhLahir != null) {
                    try {
                        pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                    } catch (ParseException ex) {
                        LOG.error("exception: " + ex.getMessage());
                        throw ex;
                    }
                }

                pihakService.saveOrUpdate(pihakTemp);
                savePengarahInfo(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp);

                Pemohon pemohonTemp = pemohonService.findPemohonByPermohonanPihak(permohonan, pihakTemp);
                infoAudit = new InfoAudit();
                infoAudit = pemohonTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pg);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                pemohonTemp.setInfoAudit(infoAudit);

                if (pemohon != null) {
                    if (pemohon.getKaitan() != null) {
                        if (pemohon.getKaitan().equalsIgnoreCase("lain-lain")) {
                            pemohonTemp.setKaitan(hubunganLain);
                        } else {
                            pemohonTemp.setKaitan(pemohon.getKaitan());
                        }
                    }

                    pemohonTemp.setStatusKahwin(pemohon.getStatusKahwin());
                    pemohonTemp.setPekerjaan(pemohon.getPekerjaan());
                    pemohonTemp.setUmur(pemohon.getUmur());
                    pemohonTemp.setPendapatan(pemohon.getPendapatan());
                    pemohonTemp.setTanggungan(pemohon.getTanggungan());
                    if (pemohon != null) {
                        pemohonTemp.setTempohMastautin(pemohon.getTempohMastautin());
                    }

                    PihakCawangan pc = savePihakCaw(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp, pilih);

                    if (StringUtils.isNotBlank(pilih)) {
                        if (!pilih.startsWith("pc_")) {
                            pc = pihakService.findPihakCawangan(pilih);
                        }
                        pemohonTemp.setPihakCawangan(pc);
                    }

                }

                pemohonService.saveOrUpdate(pemohonTemp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new RedirectResolution(PihakPenerimaActionBean.class).addParameter("idHakmilik", idHakmilik);
        }
        tx.commit();
        addSimpleMessage("Kemaskini Data Berjaya.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(PihakPenerimaActionBean.class).addParameter("idHakmilik", idHakmilik);
    }

    @HandlesEvent("simpanKemaskiniPenerima")
    public Resolution simpanPenerimaEdit() throws ParseException {

        String copyToAllHakmilik = getContext().getRequest().getParameter("copyToAll");
        String pilih = ctx.getRequest().getParameter("pilih");
        if (isDebug) {
            LOG.debug("pilih =" + pilih);
        }
        permohonan = null;

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        PihakCawangan pihakCawangan = new PihakCawangan();
        InfoAudit infoAudit = new InfoAudit();
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihakTemp != null) {

            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setNoPengenalanLain(pihak.getNoPengenalanLain());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setSuku(pihak.getSuku());
            pihakTemp.setSubSuku(pihak.getSubSuku());
            pihakTemp.setKeturunan(pihak.getKeturunan());
            pihakTemp.setTempatSuku(pihak.getTempatSuku());
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
            pihakTemp.setInfoAudit(infoAudit);

            if (pihak.getTempatLahir() != null) {
                if (pihak.getTempatLahir().equalsIgnoreCase("lain-lain")) {
                    pihakTemp.setTempatLahir(tempatLahirLain);
                } else {
                    pihakTemp.setTempatLahir(pihak.getTempatLahir());
                }
            }

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }

            pihakService.saveOrUpdate(pihakTemp);
            savePihakAlamat(pihakTemp, pihakAlamatTamb);

            //------------UPDATE MULTIPLE HAKMILIK-------------
            if (permohonan.getSenaraiHakmilik().size() > 1) {

                List<PermohonanPihak> permPihakSamaList = new ArrayList<PermohonanPihak>();
                permPihakSamaList = permohonanPihakService.getSenaraiMohonPihakByIdPihak(idPermohonan, pihak.getIdPihak());

                for (PermohonanPihak perPihak : permPihakSamaList) {
                    infoAudit = perPihak.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());

                    perPihak.setPekerjaan(permohonanPihak.getPekerjaan());
                    perPihak.setPendapatan(permohonanPihak.getPendapatan());
                    perPihak.setStatusKahwin(permohonanPihak.getStatusKahwin());
                    perPihak.setTangungan(permohonanPihak.getTangungan());
                    perPihak.setUmur(permohonanPihak.getUmur());

                    perPihak.setNama(pihak.getNama());
                    perPihak.setJenisPengenalan(pihak.getJenisPengenalan());
                    perPihak.setNoPengenalan(pihak.getNoPengenalan());
                    perPihak.setWargaNegara(pihak.getWargaNegara());

                    Alamat alamatPenerima = new Alamat();
                    alamatPenerima.setAlamat1(StringUtils.isNotBlank(pihak.getAlamat1()) ? pihak.getAlamat1() : "");
                    alamatPenerima.setAlamat2(StringUtils.isNotBlank(pihak.getAlamat2()) ? pihak.getAlamat2() : "");
                    alamatPenerima.setAlamat3(StringUtils.isNotBlank(pihak.getAlamat3()) ? pihak.getAlamat3() : "");
                    alamatPenerima.setAlamat4(StringUtils.isNotBlank(pihak.getAlamat4()) ? pihak.getAlamat4() : "");
                    alamatPenerima.setPoskod(StringUtils.isNotBlank(pihak.getPoskod()) ? pihak.getPoskod() : "");
                    alamatPenerima.setNegeri(pihak.getNegeri() != null ? pihak.getNegeri() : null);
                    perPihak.setAlamat(alamatPenerima);

                    if (pihakAlamatTamb != null) {
                        AlamatSurat alamatSurat = new AlamatSurat();
                        alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
                        alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
                        alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
                        alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
                        alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
                        alamatSurat.setNegeriSurat(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
                        perPihak.setAlamatSurat(alamatSurat);

                    }

                    if (perPihak.getJenis().getKod().equals(jenisPihak)) {
                        if (permohonanPihak.getKaitan() != null) {
                            if (permohonanPihak.getKaitan().equalsIgnoreCase("lain-lain")) {
                                perPihak.setKaitan(hubunganLain);
                            } else {
                                perPihak.setKaitan(permohonanPihak.getKaitan());
                            }
                        }

                        pihakCawangan = savePihakCaw(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp, pilih);

                        if (StringUtils.isNotBlank(pilih)) {
                            if (!pilih.startsWith("pc_")) {
                                pihakCawangan = pihakService.findPihakCawangan(pilih);
                            }
                            perPihak.setPihakCawangan(pihakCawangan);
                        }
                    }

                    pihakService.saveOrUpdatePermohonanPihak(perPihak);

                    saveWarisInfo(ctx.getRequest().getParameterMap(), infoAudit, perPihak);
//                    savePengarahInfo(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp);
                }
                savePengarahInfo(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp);
            } else {
                //------------UPDATE SINGLE HAKMILIK-------------
                PermohonanPihak permohonanPihakTemp = new PermohonanPihak();
                permohonanPihakTemp = permohonanPihakService.findById(String.valueOf(permohonanPihak.getIdPermohonanPihak()));

                infoAudit = permohonanPihakTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

                permohonanPihakTemp.setPekerjaan(permohonanPihak.getPekerjaan());
                permohonanPihakTemp.setPendapatan(permohonanPihak.getPendapatan());

                permohonanPihakTemp.setStatusKahwin(permohonanPihak.getStatusKahwin());
                permohonanPihakTemp.setTangungan(permohonanPihak.getTangungan());
                permohonanPihakTemp.setUmur(permohonanPihak.getUmur());

                permohonanPihakTemp.setPihak(pihakTemp);
                permohonanPihakTemp.setInfoAudit(infoAudit);

                permohonanPihakTemp.setNama(pihak.getNama());
                permohonanPihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
                permohonanPihakTemp.setNoPengenalan(pihak.getNoPengenalan());
                permohonanPihakTemp.setWargaNegara(pihak.getWargaNegara());

                Alamat alamatPenerima = new Alamat();
                alamatPenerima.setAlamat1(StringUtils.isNotBlank(pihak.getAlamat1()) ? pihak.getAlamat1() : "");
                alamatPenerima.setAlamat2(StringUtils.isNotBlank(pihak.getAlamat2()) ? pihak.getAlamat2() : "");
                alamatPenerima.setAlamat3(StringUtils.isNotBlank(pihak.getAlamat3()) ? pihak.getAlamat3() : "");
                alamatPenerima.setAlamat4(StringUtils.isNotBlank(pihak.getAlamat4()) ? pihak.getAlamat4() : "");
                alamatPenerima.setPoskod(StringUtils.isNotBlank(pihak.getPoskod()) ? pihak.getPoskod() : "");
                alamatPenerima.setNegeri(pihak.getNegeri() != null ? pihak.getNegeri() : null);
                permohonanPihakTemp.setAlamat(alamatPenerima);

                if (pihakAlamatTamb != null) {
                    AlamatSurat alamatSurat = new AlamatSurat();
                    alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
                    alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
                    alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
                    alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
                    alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
                    alamatSurat.setNegeriSurat(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
                    permohonanPihakTemp.setAlamatSurat(alamatSurat);

                }

                if (permohonanPihak.getKaitan() != null) {
                    if (permohonanPihak.getKaitan().equalsIgnoreCase("lain-lain")) {
                        permohonanPihakTemp.setKaitan(hubunganLain);
                    } else {
                        permohonanPihakTemp.setKaitan(permohonanPihak.getKaitan());
                    }
                }

                if (jenisPihak != null) {
                    KodJenisPihakBerkepentingan kodJenisPihak = new KodJenisPihakBerkepentingan();
                    kodJenisPihak.setKod(jenisPihak);
                    permohonanPihakTemp.setJenis(kodJenisPihak);
                }

                pihakCawangan = savePihakCaw(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp, pilih);

                if (StringUtils.isNotBlank(pilih)) {
                    if (!pilih.startsWith("pc_")) {
                        pihakCawangan = pihakService.findPihakCawangan(pilih);
                    }
                    permohonanPihakTemp.setPihakCawangan(pihakCawangan);
                }

                if (StringUtils.isNotBlank(copyToAllHakmilik)) {

                    pihakService.updateMultipleIdHakmilik(pihakTemp, permohonanPihakTemp);

                    for (PermohonanPihak perPihakTemp : permohonan.getSenaraiPihak()) {
                        LOG.info(":: ID PERMOHONAN PIHAK :: " + perPihakTemp.getIdPermohonanPihak());
                        saveWarisInfo(ctx.getRequest().getParameterMap(), infoAudit, perPihakTemp);
                    }
                } else {
                    pihakService.saveOrUpdatePihakKepentinganPihak(pihakTemp, permohonanPihakTemp);
                    saveWarisInfo(ctx.getRequest().getParameterMap(), infoAudit, permohonanPihakTemp);
                }
                savePengarahInfo(ctx.getRequest().getParameterMap(), infoAudit, pihakTemp);
            }
        }

        addSimpleMessage("Kemaskini Data Berjaya.");

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new RedirectResolution(PihakPenerimaActionBean.class).addParameter("idHakmilik", idHakmilik);
    }

    public Resolution simpanSiMati() {

        idHakmilik = getContext().getRequest().getParameter("hakmilik");

        InfoAudit infoAudit = new InfoAudit();
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihakTemp != null) {

            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setSuku(pihak.getSuku());
            pihakTemp.setSubSuku(pihak.getSubSuku());
            pihakTemp.setKeturunan(pihak.getKeturunan());
            pihakTemp.setTempatSuku(pihak.getTempatSuku());
            pihakTemp.setNoSijilMati(pihak.getNoSijilMati());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());

            KodFlagPihak kodFlagPihak = new KodFlagPihak();
            kodFlagPihak.setKod("AK");
            pihakTemp.setKodFlagPihak(kodFlagPihak);

            pihakTemp.setInfoAudit(infoAudit);

            if (tarikhMati != null) {
                try {
                    pihakTemp.setTarikhMati(sdf.parse(tarikhMati));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                }
            }

            pihakService.saveOrUpdatePihak(pihakTemp);
        }
        addSimpleMessage("Kemaskini Data Berjaya.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(PihakPenerimaActionBean.class).addParameter("idHakmilik", idHakmilik);

    }

    public Resolution deletePihakCaw() {

        String idPihak = ctx.getRequest().getParameter("idPihak");
        String idPihakCaw = ctx.getRequest().getParameter("idPihakCaw");
        String jenisAction = ctx.getRequest().getParameter("jenisAction");
        String idMohonPihak = getContext().getRequest().getParameter("idMohonPihak");
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");

        if (StringUtils.isNotBlank(idPihakCaw)) {
            PihakCawangan pc = pihakService.findPihakCawangan(idPihakCaw);
            if (pc != null) {
                pihakService.deletePihakCaw(pc);
            }
        }

        if (jenisAction.equals("editPenerima")) {
            if (idPemohon.isEmpty()) {
                return new RedirectResolution(PihakPenerimaActionBean.class, "editPenerima").addParameter("idMohonPihak", idMohonPihak);
            } else {
                return new RedirectResolution(PihakPenerimaActionBean.class, "editPemohon").addParameter("idPemohon", idPemohon);
            }
        } else {
            return new RedirectResolution(PihakPenerimaActionBean.class, "selectPihak").addParameter("idPihak", idPihak).addParameter("idHakmilik", idHakmilik);
        }
    }

    public Resolution deletePengarah() {

        String idPihak = ctx.getRequest().getParameter("idPihak");
        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        String jenisAction = ctx.getRequest().getParameter("jenisAction");
        String idMohonPihak = getContext().getRequest().getParameter("idMohonPihak");
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        if (StringUtils.isNotBlank(idPengarah)) {
            PihakPengarah pihakPengarah = pihakService.findPengarahById(idPengarah);
            if (pihakPengarah != null) {
                pihakService.deletePengarah(pihakPengarah);
            }
        }
        if (jenisAction.equals("editPenerima")) {
            if (idPemohon.isEmpty()) {
                return new RedirectResolution(PihakPenerimaActionBean.class, "editPenerima").addParameter("idMohonPihak", idMohonPihak);
            } else {
                return new RedirectResolution(PihakPenerimaActionBean.class, "editPemohon").addParameter("idPemohon", idPemohon);
            }
        } else {
            return new RedirectResolution(PihakPenerimaActionBean.class, "selectPihak").addParameter("idPihak", idPihak).addParameter("idHakmilik", idHakmilik);
        }
    }

    public Resolution deletePihakHubungan() {

        String idPihak = ctx.getRequest().getParameter("idPihak");
        String idHubungan = getContext().getRequest().getParameter("idHubungan");
        String jenisAction = ctx.getRequest().getParameter("jenisAction");
        String idMohonPihak = getContext().getRequest().getParameter("idMohonPihak");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        if (StringUtils.isNotBlank(idHubungan)) {

            PermohonanPihakHubungan pihakHubungan = permohonanPihakHubunganService.findById(idHubungan);

            if (pihakHubungan != null) {
                permohonanPihakHubunganService.delete(pihakHubungan);
            }
        }
        if (jenisAction.equals("editPenerima")) {
            return new RedirectResolution(PihakPenerimaActionBean.class, "editPenerima").addParameter("idMohonPihak", idMohonPihak);
        } else {
            return new RedirectResolution(PihakPenerimaActionBean.class, "selectPihak").addParameter("idPihak", idPihak).addParameter("idHakmilik", idHakmilik);
        }
    }

    private void savePihakAlamat(Pihak pihak, PihakAlamatTamb pihakAlamatTamb) {
        PihakAlamatTamb alamatTambahan = pihakService.getAlamatTambahan(pihak);

        InfoAudit infoAudit = new InfoAudit();
        if (alamatTambahan == null) {
            alamatTambahan = new PihakAlamatTamb();
            alamatTambahan.setPihak(pihak);
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(TODAY);

        } else {
            infoAudit = alamatTambahan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(TODAY);
        }

        alamatTambahan.setInfoAudit(infoAudit);

        alamatTambahan.setAlamatKetiga1(pihakAlamatTamb.getAlamatKetiga1());
        alamatTambahan.setAlamatKetiga2(pihakAlamatTamb.getAlamatKetiga2());
        alamatTambahan.setAlamatKetiga3(pihakAlamatTamb.getAlamatKetiga3());
        alamatTambahan.setAlamatKetiga4(pihakAlamatTamb.getAlamatKetiga4());
        alamatTambahan.setAlamatKetigaPoskod(pihakAlamatTamb.getAlamatKetigaPoskod());
        alamatTambahan.setAlamatKetigaNegeri(pihakAlamatTamb.getAlamatKetigaNegeri());

        pihakAlamatTambDAO.saveOrUpdate(alamatTambahan);
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

    public Resolution agihSamaRata() {
        String results = "0";
        String DELIM = "__^$__";
        idHakmilik = getContext().getRequest().getParameter("hakmilik");

        LOG.info("id hakmilik agih syer 1 :" + idHakmilik);

        String jenis = getContext().getRequest().getParameter("jenis");
        Hakmilik hm = null;
        if (StringUtils.isNotBlank(idHakmilik)) {
            hm = hakmilikDAO.findById(idHakmilik);
        } else {
            hm = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
        }

        LOG.info("id hakmilik agih syer 2 :" + idHakmilik);

        //TODO : multiple hakmilik
        Fraction sumAllTerlibat = Fraction.ZERO;
        Fraction samaRata = Fraction.ZERO;

        senaraiTuanTanahTerlibat = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(permohonan.getIdPermohonan(), "TER", hm.getIdHakmilik());

        if (hm != null) {
            LOG.debug("************");

            if (senaraiTuanTanahTerlibat.size() > 0) {
                for (PermohonanPihak perPihak : senaraiTuanTanahTerlibat) {
                    HakmilikPihakBerkepentingan hmk = hakmilikPihakService.findHakmilikPihakByPihak(perPihak.getPihak().getIdPihak(), hm);
                    if (hmk != null) {
                        LOG.debug("pihak = " + perPihak.getPihak().getNama());
                        LOG.debug("pembilang =" + hmk.getSyerPembilang());
                        LOG.debug("penyebut =" + hmk.getSyerPenyebut());

                        sumAllTerlibat = sumAllTerlibat.add(new Fraction(hmk.getSyerPembilang(), hmk.getSyerPenyebut()));
                        continue;
                    }
                }
            }
//            else {
//                for (Pemohon pmohon : senaraiPemohon) {
//                    HakmilikPihakBerkepentingan hmk = hakmilikPihakService.findHakmilikPihakByPihak(pmohon.getPihak().getIdPihak(), hm);
//                    if (hmk != null) {
//                        LOG.debug("pihak = " + pmohon.getPihak().getNama());
//                        LOG.debug("pembilang =" + hmk.getSyerPembilang());
//                        LOG.debug("penyebut =" + hmk.getSyerPenyebut());
//
//                        sumAllPemohon = sumAllPemohon.add(new Fraction(hmk.getSyerPembilang(), hmk.getSyerPenyebut()));
//                        continue;
//                    }
//                }
//            }
            LOG.debug("************");
        }
        LOG.debug("sum all = " + sumAllTerlibat);
        if (sumAllTerlibat.getDenominator() == 1) {
            sumAllTerlibat = Fraction.ONE;
        }
        LOG.debug("sum all = " + sumAllTerlibat);

//        ---------------------
        if (jenis.equals("penerima")) {

            if (senaraiPermohonanPihak != null && senaraiPermohonanPihak.size() > 0) {
                int pemohonListA = 0;

                for (PermohonanPihak pp : senaraiPermohonanPihak) {
                    if (pp == null || pp.getHakmilik() == null) {
                        continue;
                    }
                    Hakmilik h = pp.getHakmilik();
                    if (h.getIdHakmilik().equals(hm.getIdHakmilik())) {
                        pemohonListA++;
                    }
                }
                if (pemohonListA > 0) {
                    List<PermohonanPihak> senarai = new ArrayList<PermohonanPihak>();
                    samaRata = sumAllTerlibat.divide(pemohonListA);
                    for (PermohonanPihak pp : senaraiPermohonanPihak) {
                        if (pp == null || pp.getHakmilik() == null) {
                            continue;
                        }
                        Hakmilik h = pp.getHakmilik();
                        if (h.getIdHakmilik().equals(hm.getIdHakmilik())) {
                            pp.setSyerPembilang(samaRata.getNumerator());
                            pp.setSyerPenyebut(samaRata.getDenominator());
                            senarai.add(pp);
                        }
                    }
                    permohonanPihakService.saveOrUpdate(senarai);
                }

                StringBuilder s = new StringBuilder();
                s.append(samaRata.getNumerator()).append(DELIM).append(samaRata.getDenominator());
                results = s.toString();
                LOG.debug(results);
            }
        } else if (jenis.equals("gadaian")) {

            if (senaraiPenerimaGadaian != null && senaraiPenerimaGadaian.size() > 0) {
                int pemohonListA = 0;

                for (PermohonanPihak pp : senaraiPenerimaGadaian) {
                    if (pp == null || pp.getHakmilik() == null) {
                        continue;
                    }
                    Hakmilik h = pp.getHakmilik();
                    if (h.getIdHakmilik().equals(hm.getIdHakmilik())) {
                        pemohonListA++;
                    }
                }
                if (pemohonListA > 0) {
                    List<PermohonanPihak> senarai = new ArrayList<PermohonanPihak>();
                    samaRata = sumAllTerlibat.divide(pemohonListA);
                    for (PermohonanPihak pp : senaraiPenerimaGadaian) {
                        if (pp == null || pp.getHakmilik() == null) {
                            continue;
                        }
                        Hakmilik h = pp.getHakmilik();
                        if (h.getIdHakmilik().equals(hm.getIdHakmilik())) {
                            pp.setSyerPembilang(samaRata.getNumerator());
                            pp.setSyerPenyebut(samaRata.getDenominator());
                            senarai.add(pp);
                        }
                    }
                    permohonanPihakService.saveOrUpdate(senarai);
                }

                StringBuilder s = new StringBuilder();
                s.append(samaRata.getNumerator()).append(DELIM).append(samaRata.getDenominator());
                results = s.toString();
                LOG.debug(results);
            }
        }
        //-----------------------------------------------

        return new StreamingResolution("text/plain", results);
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
//        String[] noTel = param.get("telCaw");

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
                pc.setSuratAlamat1(StringUtils.isNotBlank(add1[i]) ? add1[i] : "");
                pc.setSuratAlamat2(StringUtils.isNotBlank(add2[i]) ? add2[i] : "");
                pc.setSuratAlamat3(StringUtils.isNotBlank(add3[i]) ? add3[i] : "");
                pc.setSuratAlamat4(StringUtils.isNotBlank(add4[i]) ? add4[i] : "");
                pc.setSuratPoskod(StringUtils.isNotBlank(poskod[i]) ? poskod[i] : "");
                pc.setSuratNegeri(StringUtils.isNotBlank(negeri[i]) ? kodNegeriDAO.findById(negeri[i]) : null);
//                pc.setNoTelefon1(StringUtils.isNotBlank(noTel[i]) ? noTel[i] : "");
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
        List<Pihak> senaraiPihakTemp = new ArrayList<Pihak>();

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
//        String[] negaraPAs = param.get("negaraPA");
        String[] syerPembilangs = param.get("syerPembilangAmanah");
        String[] syerPenyebuts = param.get("syerPenyebutAmanah");

        if (etanah.util.StringUtils.isNotBlank(kps)) {
            for (int i = 0; i < kps.length; i++) {

                String kp_ = kps[i];
                String noKp_ = noKPs[i];
                if (StringUtils.isNotBlank(kp_) && StringUtils.isNotBlank(noKp_)) {
                    Pihak phk = pihakService.findPihak(kp_, noKp_);
                    if (phk == null) {
                        phk = new Pihak();
                        phk.setAlamat1(StringUtils.isNotBlank(add1PAs[i]) ? add1PAs[i] : "");
                        phk.setAlamat2(StringUtils.isNotBlank(add2PAs[i]) ? add2PAs[i] : "");
                        phk.setAlamat3(StringUtils.isNotBlank(add3PAs[i]) ? add3PAs[i] : "");
                        phk.setAlamat4(StringUtils.isNotBlank(add4PAs[i]) ? add4PAs[i] : "");
                        phk.setPoskod(StringUtils.isNotBlank(poskodPAs[i]) ? poskodPAs[i] : "");

                        String namaNegeri = negeriPAs[i];
                        KodNegeri kodNegeriTemp = consentService.findKodNegeriByNama(namaNegeri);

                        if (kodNegeriTemp != null) {
                            phk.setNegeri(kodNegeriTemp);
                        } else {
                            phk.setAlamat4(namaNegeri);
                        }

//                        phk.setNegeri(StringUtils.isNotBlank(negeriPAs[i]) ? kodNegeriDAO.findById(negeriPAs[i]) : null);
                        phk.setNama(StringUtils.isNotBlank(namaPAs[i]) ? namaPAs[i] : "");
                        phk.setWargaNegara(kodWarganegaraDAO.findById("MAL"));
                        phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp_));
                        phk.setNoPengenalan(noKp_);
                        phk.setInfoAudit(ia);
                        senaraiPihakTemp.add(phk);
                    }

                    waris = new PermohonanPihakHubungan();
                    waris.setPihak(permohonanPihak);
                    waris.setInfoAudit(ia);
                    waris.setNama(StringUtils.isNotBlank(namaPAs[i]) ? namaPAs[i] : "");
                    waris.setAlamat1(StringUtils.isNotBlank(add1PAs[i]) ? add1PAs[i] : "");
                    waris.setAlamat2(StringUtils.isNotBlank(add2PAs[i]) ? add2PAs[i] : "");
                    waris.setAlamat3(StringUtils.isNotBlank(add3PAs[i]) ? add3PAs[i] : "");
                    waris.setAlamat4(StringUtils.isNotBlank(add4PAs[i]) ? add4PAs[i] : "");

                    if (StringUtils.isNotBlank(negeriPAs[i])) {
                        KodNegeri kodNegeriTemp = consentService.findKodNegeriByNama(negeriPAs[i]);

                        if (kodNegeriTemp != null) {
                            waris.setNegeri(kodNegeriTemp);
                        } else {
                            KodNegara kodNegaraTemp = consentService.findKodNegaraByNama(negeriPAs[i]);
                            if (kodNegaraTemp != null) {
                                waris.setNegara(kodNegaraTemp);
                            }
                        }
                    }

                    waris.setPoskod(StringUtils.isNotBlank(poskodPAs[i]) ? poskodPAs[i] : "");
                    waris.setCawangan(permohonanPihak.getCawangan());
                    waris.setWargaNegara(StringUtils.isNotBlank(warganegaraPAs[i]) ? kodWarganegaraDAO.findById(warganegaraPAs[i]) : null);
                    waris.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp_));
                    waris.setNoPengenalan(noKp_);
                    waris.setKaitan("PENERIMA AMANAH");
                    waris.setSyerPembilang(StringUtils.isNotBlank(syerPembilangs[i]) ? Integer.parseInt(syerPembilangs[i]) : 0);
                    waris.setSyerPenyebut(StringUtils.isNotBlank(syerPenyebuts[i]) ? Integer.parseInt(syerPenyebuts[i]) : 0);
                    senaraiWaris.add(waris);
                }
            }

        }

        if (senaraiPihakTemp.size() > 0) {
            pihakService.saveOrUpdate(senaraiPihakTemp);
        }

        if (senaraiWaris.size() > 0) {
            permohonanPihakHubunganService.save(senaraiWaris);
        }
    }

    private void savePengarahInfo(Map<String, String[]> param,
            InfoAudit infoAudit, Pihak pihak) {

        List<PihakPengarah> senaraiPihakPengarah = new ArrayList<PihakPengarah>();

        String[] namaPengarahs = param.get("namaPgrh");
        String[] kps = param.get("kpPgrh");
        String[] noKPs = param.get("noKpPgrh");
        String[] warganegaras = param.get("wargaPgrh");

        if (etanah.util.StringUtils.isNotBlank(kps)) {
            for (int i = 0; i < kps.length; i++) {

                String nama_ = namaPengarahs[i];
                String kp_ = kps[i];
                String noKp_ = noKPs[i];
                String warganegara_ = warganegaras[i];

                PihakPengarah pihakPengarah = new PihakPengarah();
                pihakPengarah.setNama(nama_);
                pihakPengarah.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp_));
                pihakPengarah.setNoPengenalan(noKp_);
                pihakPengarah.setWarganegara(kodWarganegaraDAO.findById(warganegara_));
                pihakPengarah.setInfoAudit(infoAudit);
                pihakPengarah.setPihak(pihak);
                pihakPengarah.setAktif('Y');
                senaraiPihakPengarah.add(pihakPengarah);

            }
        }
        if (senaraiPihakPengarah.size() > 0) {
            pihakService.savePihakPengarah(senaraiPihakPengarah);
        }
    }

    public Resolution savePengarahEdit() {

        LOG.info("saving edit ahli lembaga pengarah.....");

        String idMohonPihak = getContext().getRequest().getParameter("idMohonPihak");
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        String namaPengarah = getContext().getRequest().getParameter("namaPengarah");
        String kpPengarah = getContext().getRequest().getParameter("kpPengarah");
        String noKpPengarah = getContext().getRequest().getParameter("noKpPengarah");
        String wargaPengarah = getContext().getRequest().getParameter("wargaPengarah");
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idPengguna = getContext().getRequest().getParameter("idPengguna");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        PihakPengarah pihakPengarah = new PihakPengarah();

        if (StringUtils.isNotBlank(idPengarah)) {
            pihakPengarah = pihakService.findPengarahById(idPengarah);
        } else {
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(penggunaDAO.findById(idPengguna));
            infoAudit.setTarikhMasuk(new java.util.Date());
            pihakPengarah.setAktif('Y');
            pihakPengarah.setPihak(pihak);
            pihakPengarah.setInfoAudit(infoAudit);
        }

        pihakPengarah.setNama(namaPengarah);
        pihakPengarah.setJenisPengenalan(kodJenisPengenalanDAO.findById(kpPengarah));
        pihakPengarah.setNoPengenalan(noKpPengarah);
        pihakPengarah.setWarganegara(kodWarganegaraDAO.findById(wargaPengarah));
        pihakService.saveOrUpdatePihakPengarah(pihakPengarah);

        if (!idMohonPihak.isEmpty() && idPemohon.isEmpty()) {
            return new RedirectResolution(PihakPenerimaActionBean.class, "editPenerima").addParameter("idMohonPihak", idMohonPihak);
        } else if (!idPemohon.isEmpty() && idMohonPihak.isEmpty()) {
            return new RedirectResolution(PihakPenerimaActionBean.class, "editPemohon").addParameter("idPemohon", idPemohon);
        } else if (!idPihak.isEmpty() && !idHakmilik.isEmpty()) {
            return new RedirectResolution(PihakPenerimaActionBean.class, "selectPihak").addParameter("idPihak", idPihak).addParameter("idHakmilik", idHakmilik);
        } else {
            return new StreamingResolution("text/plain", "DATA TIDAK BETUL");
        }
    }

    public Resolution saveCawanganEdit() {

        LOG.info("saving edit cawangan.....");

        String idMohonPihak = getContext().getRequest().getParameter("idMohonPihak");
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        String idCawangan = getContext().getRequest().getParameter("idCawangan");
        String namaCawangan = getContext().getRequest().getParameter("namaCawangan");
        String alamat1 = getContext().getRequest().getParameter("alamat1");
        String alamat2 = getContext().getRequest().getParameter("alamat2");
        String alamat3 = getContext().getRequest().getParameter("alamat3");
        String alamat4 = getContext().getRequest().getParameter("alamat4");
        String poskod = getContext().getRequest().getParameter("poskod");
        String negeri = getContext().getRequest().getParameter("negeri");
        String idPihak = getContext().getRequest().getParameter("idPihak");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        PihakCawangan pihakCawangan = new PihakCawangan();

        if (StringUtils.isNotBlank(idCawangan)) {
            pihakCawangan = pihakService.findPihakCawangan(idCawangan);
        } else {
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            pihakCawangan = new PihakCawangan();
            pihakCawangan.setIbupejabat(pihak);
            pihakCawangan.setInfoAudit(infoAudit);
        }

        pihakCawangan.setNamaCawangan(namaCawangan);
        pihakCawangan.setAlamat1(alamat1);
        pihakCawangan.setAlamat2(alamat2);
        pihakCawangan.setAlamat3(alamat3);
        pihakCawangan.setAlamat4(alamat4);
        pihakCawangan.setPoskod(poskod);
        pihakCawangan.setNegeri(kodNegeriDAO.findById(negeri));
        pihakCawangan.setSuratAlamat1(alamat1);
        pihakCawangan.setSuratAlamat2(alamat2);
        pihakCawangan.setSuratAlamat3(alamat3);
        pihakCawangan.setSuratAlamat4(alamat4);
        pihakCawangan.setSuratPoskod(poskod);
        pihakCawangan.setSuratNegeri(kodNegeriDAO.findById(negeri));
        pihakService.saveOrUpdatePihakCawangan(pihakCawangan);


        if (!idMohonPihak.isEmpty() && idPemohon.isEmpty()) {
            return new RedirectResolution(PihakPenerimaActionBean.class, "editPenerima").addParameter("idMohonPihak", idMohonPihak);
        } else if (!idPemohon.isEmpty() && idMohonPihak.isEmpty()) {
            return new RedirectResolution(PihakPenerimaActionBean.class, "editPemohon").addParameter("idPemohon", idPemohon);
        } else if (!idPihak.isEmpty() && !idHakmilik.isEmpty()) {
            return new RedirectResolution(PihakPenerimaActionBean.class, "selectPihak").addParameter("idPihak", idPihak).addParameter("idHakmilik", idHakmilik);
        } else {
            return new StreamingResolution("text/plain", "DATA TIDAK BETUL");
        }
    }

    public Resolution savePgAmanahEdit() {

        LOG.info("saving edit pemegang amanah.....");

        String idMohonPihak = getContext().getRequest().getParameter("idMohonPihak");
        String idHubungan = getContext().getRequest().getParameter("idHubungan");
        String nama = getContext().getRequest().getParameter("nama");
        String jenisKp = getContext().getRequest().getParameter("jenisKp");
        String noKp = getContext().getRequest().getParameter("noKp");
        String warga = getContext().getRequest().getParameter("warga");
        String alamat1 = getContext().getRequest().getParameter("alamat1");
        String alamat2 = getContext().getRequest().getParameter("alamat2");
        String alamat3 = getContext().getRequest().getParameter("alamat3");
        String alamat4 = getContext().getRequest().getParameter("alamat4");
        String poskod = getContext().getRequest().getParameter("poskod");
        String negeri = getContext().getRequest().getParameter("negeri");
        String syerPembilang = getContext().getRequest().getParameter("syerPembilang");
        String syerPenyebut = getContext().getRequest().getParameter("syerPenyebut");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        PermohonanPihakHubungan pihakHubunganTemp = new PermohonanPihakHubungan();

        if (StringUtils.isNotBlank(idHubungan)) {
            pihakHubunganTemp = permohonanPihakHubunganService.findById(idHubungan);
        } else {

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            pihakHubunganTemp = new PermohonanPihakHubungan();
            pihakHubunganTemp.setPihak(permohonanPihak);
            pihakHubunganTemp.setCawangan(permohonan.getCawangan());
            pihakHubunganTemp.setKaitan("PENERIMA AMANAH");
            pihakHubunganTemp.setInfoAudit(infoAudit);
        }

        KodJenisPengenalan kodJenisKP = new KodJenisPengenalan();
        kodJenisKP.setKod(jenisKp);

        KodWarganegara kodWarga = new KodWarganegara();
        kodWarga.setKod(warga);

        pihakHubunganTemp.setNama(nama);
        pihakHubunganTemp.setJenisPengenalan(kodJenisKP);
        pihakHubunganTemp.setNoPengenalan(noKp);
        pihakHubunganTemp.setWargaNegara(kodWarga);
        pihakHubunganTemp.setAlamat1(alamat1);
        pihakHubunganTemp.setAlamat2(alamat2);
        pihakHubunganTemp.setAlamat3(alamat3);
        pihakHubunganTemp.setAlamat4(alamat4);
        pihakHubunganTemp.setPoskod(poskod);
        pihakHubunganTemp.setSyerPembilang(Integer.parseInt(syerPembilang));
        pihakHubunganTemp.setSyerPenyebut(Integer.parseInt(syerPenyebut));

        if (StringUtils.isNotBlank(negeri)) {
            KodNegeri kodNegeriTemp = consentService.findKodNegeriByNama(negeri);

            if (kodNegeriTemp != null) {
                pihakHubunganTemp.setNegara(null);
                pihakHubunganTemp.setNegeri(kodNegeriTemp);

            } else {
                KodNegara kodNegaraTemp = consentService.findKodNegaraByNama(negeri);
                if (kodNegaraTemp != null) {
                    pihakHubunganTemp.setNegeri(null);
                    pihakHubunganTemp.setNegara(kodNegaraTemp);
                }
            }
        }
        permohonanPihakHubunganService.save(pihakHubunganTemp);

        if (!idMohonPihak.isEmpty()) {
            return new RedirectResolution(PihakPenerimaActionBean.class, "editPenerima").addParameter("idMohonPihak", idMohonPihak);
        } else {
            return new StreamingResolution("text/plain", "DATA TIDAK BETUL");
        }
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

    public List<PermohonanPihak> getSenaraiPenerimaGadaian() {
        return senaraiPenerimaGadaian;
    }

    public void setSenaraiPenerimaGadaian(List<PermohonanPihak> senaraiPenerimaGadaian) {
        this.senaraiPenerimaGadaian = senaraiPenerimaGadaian;
    }

    public List<PermohonanPihak> getSenaraiTuanTanahTerlibat() {
        return senaraiTuanTanahTerlibat;
    }

    public void setSenaraiTuanTanahTerlibat(List<PermohonanPihak> senaraiTuanTanahTerlibat) {
        this.senaraiTuanTanahTerlibat = senaraiTuanTanahTerlibat;
    }

    public String getHubunganLain() {
        return hubunganLain;
    }

    public void setHubunganLain(String hubunganLain) {
        this.hubunganLain = hubunganLain;
    }

    public String getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(String tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public String getTempatLahirLain() {
        return tempatLahirLain;
    }

    public void setTempatLahirLain(String tempatLahirLain) {
        this.tempatLahirLain = tempatLahirLain;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<PihakCawangan> getCawanganList() {
        return cawanganList;
    }

    public void setCawanganList(List<PihakCawangan> cawanganList) {
        this.cawanganList = cawanganList;
    }

    public List<PihakPengarah> getPengarahList() {
        return pengarahList;
    }

    public void setPengarahList(List<PihakPengarah> pengarahList) {
        this.pengarahList = pengarahList;
    }

    public String getJenisPihak() {
        return jenisPihak;
    }

    public void setJenisPihak(String jenisPihak) {
        this.jenisPihak = jenisPihak;
    }

    public String getTarikhMati() {
        return tarikhMati;
    }

    public void setTarikhMati(String tarikhMati) {
        this.tarikhMati = tarikhMati;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiTanahLain() {
        return senaraiTanahLain;
    }

    public void setSenaraiTanahLain(List<HakmilikPihakBerkepentingan> senaraiTanahLain) {
        this.senaraiTanahLain = senaraiTanahLain;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public PihakAlamatTamb getPihakAlamatTamb() {
        return pihakAlamatTamb;
    }

    public void setPihakAlamatTamb(PihakAlamatTamb pihakAlamatTamb) {
        this.pihakAlamatTamb = pihakAlamatTamb;
    }
}
