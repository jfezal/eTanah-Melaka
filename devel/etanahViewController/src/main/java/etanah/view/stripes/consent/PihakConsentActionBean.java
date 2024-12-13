/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanPihakHubunganDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.service.common.HakmilikService;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakHubungan;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.PihakCawangan;
import etanah.service.SyerValidationService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.model.PermohonanPihakKemaskini;
import etanah.service.daftar.PermohonanPihakKemaskiniService;
import org.apache.log4j.Logger;
import etanah.dao.PermohonanPihakKemaskiniDAO;
import etanah.model.KodBangsa;
import etanah.model.KodJenisPihakBerkepentingan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.RegService;
import etanah.service.common.PermohonanAtasPihakBerkepentinganService;
import etanah.service.common.CawanganService;
import etanah.service.common.PermohonanPihakHubunganService;
import etanah.view.ListUtil;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import net.sourceforge.stripes.action.HttpCache;
import org.apache.commons.math.fraction.Fraction;

/**
 *
 * @author muhammad.amin
 */
@HttpCache(allow = false)
@UrlBinding("/consent/pihak_consent")
public class PihakConsentActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PihakConsentActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    @Inject
    HakmilikService hakmilikManager;
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PemohonService pemohonService;
    @Inject
    SyerValidationService syerService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PermohonanPihakHubunganDAO pihakHubunganDAO;
    @Inject
    PermohonanPihakHubunganService pihakHubunganService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanPihakKemaskiniService mohonPihakKemaskiniService;
    @Inject
    PermohonanPihakKemaskiniDAO mohonPihakKemaskiniDAO;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    RegService regService;
    @Inject
    PermohonanAtasPihakBerkepentinganService mapService;
    @Inject
    CawanganService cawanganService;
    @Inject
    ListUtil listUtil;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    HakmilikDAO hakmilikDAO;
    private List<PermohonanPihakKemaskini> mohonPihakKemaskiniList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> othersPihakList;
    private List<PermohonanAtasPihakBerkepentingan> mapList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList2;
    private List<PermohonanPihak> senaraiPemohonBerangkai;
    private List<KodBangsa> senaraiKodBangsa;
    private List<KodJenisPihakBerkepentingan> senaraiKodPenerima;
    private List<KodBangsa> senaraiKodBangsaOrang;
    private List<KodBangsa> senaraiKodBangsaSyarikat;
    private Pihak pihak;
    private PihakCawangan pihakCawangan;
    private PihakPengarah pihakPengarah;
    private PermohonanPihak permohonanPihak;
    private PermohonanPihakHubungan permohonanPihakHubungan;
    private PermohonanPihakHubungan permohonanPihakHubungan2;
    private PermohonanPihakHubungan permohonanPihakHubungan3;
    private PermohonanPihakHubungan permohonanPihakHubungan4;
    private PermohonanPihakHubungan permohonanPihakHubungan5;
    private PermohonanPihakHubungan permohonanPihakHubungan6;
    private PermohonanPihakHubungan permohonanPihakHubungan7;
    private PermohonanPihakHubungan permohonanPihakHubungan8;
    private PermohonanPihakHubungan permohonanPihakHubungan9;
    private PermohonanPihakHubungan permohonanPihakHubungan10;
    private PermohonanPihakHubungan permohonanPihakHubungan11;
    private Pemohon pemohon;
    private List<Pemohon> pemohonList;
    private List<PermohonanPihak> tuanTanahTerlibatList;
    private List<PermohonanPihak> mohonPihakList;
    private List<PermohonanPihak> penerimaGadaianList;
    private List<PermohonanPihak> mohonPihakPemegangAmanah;
    private List<PihakCawangan> cawanganList;
    private List<PihakPengarah> pengarahList;
    private List<Pihak> pihakByNameList;
    private String idPermohonan;
    private Permohonan p;
    private Pengguna pguna;
    private String[] syer1;
    private String[] syer2;
    private String[] syer1B;
    private String[] syer2B;
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    private PermohonanPihakKemaskini mohonPihakKemaskini;
    String tarikhLahir;
    String tarikhMati;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    boolean tambahCawFlag;
    boolean tambahPengarahFlag;
    boolean tambahAmanahFlag;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanAtasPerserahanService mohonAtasSerahService;
    private String idCawangan;
    private String kodNegeri;
    private String namaNegeriBaru;
    private String namaNegeriLama;
    private String jenisPihak;
    private String semuaIdHakmilik;
    private String hubunganLain;
    private String tempatLahirLain;
    private String bukanPemohon;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/common/senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!deletePemohon", "!deletePemohon2", "!deleteSelectedPemohon", "!simpanPemohonPopup"})
    public void rehydrate() {
        if (isDebug) {
            LOG.debug("rehydrate start");
        }
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        kodNegeri = conf.getProperty("kodNegeri");

        p = permohonanService.findById(idPermohonan);
        if (p != null) {

            List<HakmilikPermohonan> hakmilikPermohonanList = p.getSenaraiHakmilik();
            KodNegeri negeriBaru = new KodNegeri();
            KodNegeri negeriLama = new KodNegeri();
            mohonPihakKemaskiniList = p.getSenaraiPihakKemaskini();
            if (mohonPihakKemaskiniList.size() > 0) {
                mohonPihakKemaskini = p.getSenaraiPihakKemaskini().get(0);
                for (PermohonanPihakKemaskini mohonPihakKemaskiniBaru : mohonPihakKemaskiniList) {
                    if (mohonPihakKemaskiniBaru.getNamaMedan().equals("rumahNegeri.kod")) {
                        if (StringUtils.isNotBlank(mohonPihakKemaskiniBaru.getNilaiBaru())) {
                            negeriBaru = kodNegeriDAO.findById(mohonPihakKemaskiniBaru.getNilaiBaru());
                            namaNegeriBaru = negeriBaru.getNama();
                        }

                        if (StringUtils.isNotBlank(mohonPihakKemaskiniBaru.getNilaiLama())) {
                            negeriLama = kodNegeriDAO.findById(mohonPihakKemaskiniBaru.getNilaiLama());
                            namaNegeriLama = negeriLama.getNama();
                        }
                    }
                }
            }

            if (hakmilikPermohonanList.size() > 0) { //for multiple hakmilik
                List<HakmilikPihakBerkepentingan> senaraiHpk = new ArrayList<HakmilikPihakBerkepentingan>();
                List<HakmilikPihakBerkepentingan> senaraiOtherHpk = new ArrayList<HakmilikPihakBerkepentingan>();
                pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
                othersPihakList = new ArrayList<HakmilikPihakBerkepentingan>();
                mohonPihakList = new ArrayList<PermohonanPihak>();
                penerimaGadaianList = new ArrayList<PermohonanPihak>();
                tuanTanahTerlibatList = new ArrayList<PermohonanPihak>();
                pemohonList = new ArrayList<Pemohon>();
                for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                    Hakmilik hk = hp.getHakmilik();
                    if (hk == null) {
                        continue;
                    }
                    senaraiHpk = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hk);
                    senaraiOtherHpk = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(hk.getIdHakmilik(), null);
                    for (HakmilikPihakBerkepentingan hpk : senaraiHpk) {
                        if (hpk == null) {
                            continue;
                        }
                        pihakKepentinganList.add(hpk);
                    }
                    for (HakmilikPihakBerkepentingan hpk : senaraiOtherHpk) {
                        if (hpk == null) {
                            continue;
                        }
                        othersPihakList.add(hpk);
                    }
                }
            }

            //for urusan consent tanah adat
            if (p.getKodUrusan().getKod().equals("PAADT") || p.getKodUrusan().getKod().equals("CGADT") || p.getKodUrusan().getKod().equals("PMADT") || p.getKodUrusan().getKod().equals("TMADT") || p.getKodUrusan().getKod().equals("BTADT")) {
                mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKod(idPermohonan, "WAR", "TER");
            } //for urusan consent serentak
            else if (p.getKodUrusan().getKod().equals("PCPTD") || p.getKodUrusan().getKod().equals("PGDMB") || p.getKodUrusan().getKod().equals("PCMMK")) {
                mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKod(idPermohonan, "PGA", "TER");
                penerimaGadaianList = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "PGA");
            } else {
                mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByNotKod(idPermohonan, "TER");
            }

            pemohonList = p.getSenaraiPemohon();

            if (p.getSenaraiHakmilik().size() == 1) {
                HakmilikPermohonan hakmilikMohon = hakmilikPermohonanList.get(0);
                tuanTanahTerlibatList = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(idPermohonan, "TER", hakmilikMohon.getHakmilik().getIdHakmilik());
            }

            if (tuanTanahTerlibatList != null) {
                if (!tuanTanahTerlibatList.isEmpty()) {
                    bukanPemohon = "checked";
                }
            } else {
                bukanPemohon = null;
            }

            if (!mohonPihakList.isEmpty()) {
                syer1 = new String[mohonPihakList.size()];
                syer2 = new String[mohonPihakList.size()];
                for (int i = 0; i < mohonPihakList.size(); i++) {
                    PermohonanPihak pp = mohonPihakList.get(i);
                    syer1[i] = String.valueOf(pp.getSyerPembilang());
                    syer2[i] = String.valueOf(pp.getSyerPenyebut());
                }
            }

            if (!penerimaGadaianList.isEmpty()) {
                syer1B = new String[penerimaGadaianList.size()];
                syer2B = new String[penerimaGadaianList.size()];
                for (int i = 0; i < penerimaGadaianList.size(); i++) {
                    PermohonanPihak pp = penerimaGadaianList.get(i);
                    syer1B[i] = String.valueOf(pp.getSyerPembilang());
                    syer2B[i] = String.valueOf(pp.getSyerPenyebut());
                }
            }
            //CHECK PIHAK TERLIBAT IF TUAN TANAH BUKAN PEMOHON (1 TUAN TANAH)
            if (pihakKepentinganList.size() == 1) {

                HakmilikPihakBerkepentingan hakPihak = new HakmilikPihakBerkepentingan();
                hakPihak = pihakKepentinganList.get(0);
                PermohonanPihak perPihak = permohonanPihakService.getPmohonPihakByKodAndIdHakmilik(p.getIdPermohonan(), hakPihak.getHakmilik().getIdHakmilik(), hakPihak.getPihak().getIdPihak(), "TER");

                if (pemohonList.size() > 0) {

                    boolean isTuanTanah = false;
                    for (Pemohon pmhon : pemohonList) {
                        if (pmhon.getPihak() == hakPihak.getPihak()) {
                            isTuanTanah = true;
                        }
                    }
                    if (perPihak == null) {

                        if (!isTuanTanah) {
                            PermohonanPihak perPhak = new PermohonanPihak();
                            InfoAudit infoAudit = new InfoAudit();
                            infoAudit.setDimasukOleh(pguna);
                            infoAudit.setTarikhMasuk(new java.util.Date());
                            perPhak.setInfoAudit(infoAudit);
                            perPhak.setPermohonan(p);
                            perPhak.setPihak(hakPihak.getPihak());
                            perPhak.setCawangan(p.getCawangan());
                            perPhak.setHakmilik(hakPihak.getHakmilik());
                            KodJenisPihakBerkepentingan kodJenis = new KodJenisPihakBerkepentingan();
                            kodJenis.setKod("TER");
                            perPhak.setJenis(kodJenis);
                            permohonanPihakService.saveOrUpdate(perPhak);
                        }
                    } else {
                        if (isTuanTanah) {
                            permohonanPihakService.delete(perPihak);
                        }
                    }
                } else {
                    if (perPihak != null) {
                        permohonanPihakService.delete(perPihak);
                    }
                }
            }
        }
        if (isDebug) {
            LOG.debug("rehydrate finish");
        }
    }

    public Resolution getPaparanSenaraiHakmilikKepentingan() {
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        if (p.getSenaraiHakmilik().size() > 1) {
            return new ForwardResolution("/WEB-INF/jsp/consent/pihak_berkepentingan_multiple.jsp").addParameter("tab", "true");
        } else {
            return new ForwardResolution("/WEB-INF/jsp/consent/pihak_berkepentingan.jsp").addParameter("tab", "true");
        }
    }

    public Resolution getSenaraiHakmilikKepentingan() {

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (idPermohonan != null) {
            if (p != null && p.getPermohonanSebelum() != null) {
                Permohonan t = p.getPermohonanSebelum();
                if (t != null) {
                    getContext().getRequest().setAttribute("copy", Boolean.TRUE);
                }
            }
            if (p != null) {
                if (p.getSenaraiHakmilik().size() > 1 && (!p.getKodUrusan().getKod().startsWith("IS"))) {
                    checkPihakValidate(pihakKepentinganList);
                    if (pihakKepentinganList.size() == 0) {
                        addSimpleError("Hakmilik yang dipilih tidak mempunyai tuan tanah yang sama.");
                        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
                    }
                }
                //CHECK PIHAK TERLIBAT IF TUAN TANAH BUKAN PEMOHON (BANYAK TUAN TANAH)
                if (p.getSenaraiHakmilik().size() == 1) {
                    if (pihakKepentinganList.size() > 1) {
                        if (pemohonList.size() > 0) {
                            boolean tuanTanahIsPemohon = false;
                            for (HakmilikPihakBerkepentingan hakPihak : pihakKepentinganList) {
                                for (Pemohon pmhon : pemohonList) {
                                    if (pmhon.getPihak() == hakPihak.getPihak()) {
                                        tuanTanahIsPemohon = true;
                                    }
                                }
                            }
                            if (!p.getKodUrusan().getKod().equalsIgnoreCase("BTADT")) {
                                if (!tuanTanahIsPemohon) {
                                    if (tuanTanahTerlibatList.isEmpty()) {
                                        addSimpleError("Sila Pilih Tuan Tanah Yang Terlibat Dalam Permohonan Ini.");
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }

        if (p.getSenaraiHakmilik().size() > 1) {
            return new ForwardResolution("/WEB-INF/jsp/consent/pihak_berkepentingan_multiple.jsp").addParameter("tab", "true");
        } else {
            return new ForwardResolution("/WEB-INF/jsp/consent/pihak_berkepentingan.jsp").addParameter("tab", "true");
        }
    }

    public Resolution getSenaraiHakmilikKepentingan2() {

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (idPermohonan != null) {
            if (p != null && p.getPermohonanSebelum() != null) {
                Permohonan t = p.getPermohonanSebelum();
                if (t != null) {
                    getContext().getRequest().setAttribute("copy", Boolean.TRUE);
                }
            }
            if (p != null) {
                if (p.getSenaraiHakmilik().size() > 1 && (!p.getKodUrusan().getKod().startsWith("IS"))) {
                    checkPihakValidate(pihakKepentinganList);
                    if (pihakKepentinganList.size() == 0) {
                        addSimpleError("Hakmilik yang dipilih tidak mempunyai tuan tanah yang sama.");
                        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
                    }
                }
                //CHECK PIHAK TERLIBAT IF TUAN TANAH BUKAN PEMOHON (BANYAK TUAN TANAH)
//                if (p.getSenaraiHakmilik().size() == 1) {
//                    if (pihakKepentinganList.size() > 1) {
//                        if (pemohonList.size() > 0) {
//                            boolean tuanTanahIsPemohon = false;
//                            for (HakmilikPihakBerkepentingan hakPihak : pihakKepentinganList) {
//                                for (Pemohon pmhon : pemohonList) {
//                                    if (pmhon.getPihak() == hakPihak.getPihak()) {
//                                        tuanTanahIsPemohon = true;
//                                    }
//                                }
//                            }
//                            if (!p.getKodUrusan().getKod().equalsIgnoreCase("BTADT")) {
//                                if (!tuanTanahIsPemohon) {
//                                    if (tuanTanahTerlibatList.isEmpty()) {
//                                        addSimpleError("Sila Pilih Tuan Tanah Yang Terlibat Dalam Permohonan Ini.");
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/consent/pihak_berkepentingan_multiple.jsp").addParameter("tab", "true");

    }

    public Resolution simpanPihakTerlibat() {
        List<PermohonanPihak> senaraiTerlibat = new ArrayList<PermohonanPihak>();

        String[] param = getContext().getRequest().getParameterValues("idPihak");

        HakmilikPermohonan hakmilikMohon = new HakmilikPermohonan();

        if (p.getSenaraiHakmilik().size() == 1) {
            hakmilikMohon = p.getSenaraiHakmilik().get(0);
        }

        for (String idPihak : param) {
            Pihak phak = pihakService.findById(idPihak);

            PermohonanPihak perPihak = permohonanPihakService.getPmohonPihakByKodAndIdHakmilik(p.getIdPermohonan(), hakmilikMohon.getHakmilik().getIdHakmilik(), phak.getIdPihak(), "TER");

            if (perPihak == null) {
                PermohonanPihak perPhak = new PermohonanPihak();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                perPhak.setInfoAudit(infoAudit);
                perPhak.setPermohonan(p);
                perPhak.setPihak(phak);
                perPhak.setCawangan(p.getCawangan());
                KodJenisPihakBerkepentingan kodJenis = new KodJenisPihakBerkepentingan();
                kodJenis.setKod("TER");
                perPhak.setJenis(kodJenis);
                perPhak.setHakmilik(hakmilikMohon.getHakmilik());
                senaraiTerlibat.add(perPhak);
            }

        }
        if (senaraiTerlibat.size() > 0) {
            permohonanPihakService.saveOrUpdate(senaraiTerlibat);
        }

        return new RedirectResolution(PihakConsentActionBean.class, "getSenaraiHakmilikKepentingan");
    }

    public Resolution simpanPihakTerlibatMultipleHakmilik() {

        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        Hakmilik hm = hakmilikManager.findById(idHakmilik);
        List<HakmilikPihakBerkepentingan> senarai = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hm);
        checkPihakValidate(senarai);
        HakmilikPihakBerkepentingan hakmilikPihak = pihakKepentinganList.get(0);

        PermohonanPihak perPhak = permohonanPihakService.getPmohonPihakByKodAndIdHakmilik(p.getIdPermohonan(), idHakmilik, hakmilikPihak.getPihak().getIdPihak(), "TER");
        if (perPhak == null) {
            perPhak = new PermohonanPihak();
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            perPhak.setInfoAudit(infoAudit);
            perPhak.setPermohonan(p);
            perPhak.setPihak(hakmilikPihak.getPihak());
            perPhak.setCawangan(p.getCawangan());
            KodJenisPihakBerkepentingan kodJenis = new KodJenisPihakBerkepentingan();
            kodJenis.setKod("TER");
            perPhak.setJenis(kodJenis);
            perPhak.setHakmilik(hakmilikPihak.getHakmilik());
            permohonanPihakService.saveOrUpdate(perPhak);
        }

        return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution deletePihakTerlibatMultipleHakmilik() {

        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        Hakmilik hm = hakmilikManager.findById(idHakmilik);
        List<HakmilikPihakBerkepentingan> senarai = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hm);
        checkPihakValidate(senarai);
        HakmilikPihakBerkepentingan hakmilikPihak = pihakKepentinganList.get(0);

        PermohonanPihak perPhak = permohonanPihakService.getPmohonPihakByKodAndIdHakmilik(p.getIdPermohonan(), idHakmilik, hakmilikPihak.getPihak().getIdPihak(), "TER");
        if (perPhak != null) {

            permohonanPihakService.delete(perPhak);
        }

        return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution simpanPemohon() {
        List<Pemohon> senaraiPemohon = new ArrayList<Pemohon>();

        String[] param = getContext().getRequest().getParameterValues("idPihak");
        for (String idPihak : param) {
            Pihak pi = pihakService.findById(idPihak);
            Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(p, pi);
            if (pmohon == null) {
                Pemohon pe = new Pemohon();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
                pe.setInfoAudit(ia);
                pe.setPermohonan(p);
                pe.setPihak(pi);
                pe.setCawangan(p.getCawangan());
                senaraiPemohon.add(pe);
            }
        }
        if (senaraiPemohon.size() > 0) {
            pemohonService.saveOrUpdateMultiple(senaraiPemohon);
        }

        return new RedirectResolution(PihakConsentActionBean.class, "getSenaraiHakmilikKepentingan");
    }

    public Resolution simpanPemohon2() {

        String idPihak = getContext().getRequest().getParameter("idPihak");
        Pihak pi = pihakService.findById(idPihak);
        Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(p, pi);
        if (pmohon == null) {
            Pemohon pe = new Pemohon();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
            pe.setInfoAudit(ia);
            pe.setPermohonan(p);
            pe.setPihak(pi);
            pe.setCawangan(p.getCawangan());
            pemohonService.saveOrUpdate(pe);
        } else {
            addSimpleError(pi.getNama() + " telah memohon.");
        }
        return new RedirectResolution(PihakConsentActionBean.class, "senaraiHakmilikPindahMilikBerkepentingan");
    }

    public Resolution deletePemohon() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pmohon = pemohonService.findById(idPemohon);
            if (pmohon != null) {
                pemohonService.delete(pmohon);
                pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }
        rehydrate();
        return getSenaraiHakmilikKepentingan();
    }

    public Resolution deleteSelectedPemohon() {
        String[] id = getContext().getRequest().getParameterValues("idPihak");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");

        if (isDebug) {
            LOG.debug("id length = " + id.length);
        }

        if (id.length > 0) {
            pemohonService.deleteSelectedPemohon(id);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(PihakConsentActionBean.class, "getSenaraiHakmilikKepentingan");
        }
    }

    public Resolution pihakKepentinganPopup() {
        String urusan = getContext().getRequest().getParameter("urusan");
        String jenisPB = getContext().getRequest().getParameter("jenisPB");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        if (StringUtils.isNotBlank(jenisPB)) {
            getContext().getRequest().setAttribute("jenis", jenisPB);
        }
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
        }

        kodNegeri = conf.getProperty("kodNegeri");
        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution pemohonPopup() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        String urusan = getContext().getRequest().getParameter("urusan");
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }
        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        pemohon = pemohonService.findPemohonByPermohonanPihak(p, pihak);

        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");

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

                if (p.getKodUrusan().getKod().equals("PMADT") || p.getKodUrusan().getKod().equals("CGADT") || p.getKodUrusan().getKod().equals("PAADT") || p.getKodUrusan().getKod().equals("TMADT") || p.getKodUrusan().getKod().equals("BTADT")) {
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

        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("multiple", Boolean.TRUE);
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
        }

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPenerima() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));

        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();

        if (pihak.getTarikhLahir() != null) {
            tarikhLahir = sdf.format(pihak.getTarikhLahir());
        }

        getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
        if (StringUtils.isNotBlank(idHakmilik)) {
            permohonanPihak = permohonanPihakService.getMohonPihakByIdPihakIdHakmilikNotKod(Long.valueOf(idPihak), idHakmilik, idPermohonan, "TER");
            getContext().getRequest().setAttribute("multiple", Boolean.TRUE);
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
        } else {
            permohonanPihak = permohonanPihakService.getPmohonPihakByIdPihakNotKod(idPermohonan, Long.valueOf(idPihak), "TER");
        }

        if (pihak.getTempatLahir() != null) {
            if (!pihak.getTempatLahir().equalsIgnoreCase("JOHOR") && !pihak.getTempatLahir().equalsIgnoreCase("KEDAH") && !pihak.getTempatLahir().equalsIgnoreCase("KELANTAN") && !pihak.getTempatLahir().equalsIgnoreCase("MELAKA") && !pihak.getTempatLahir().equalsIgnoreCase("NEGERI SEMBILAN") && !pihak.getTempatLahir().equalsIgnoreCase("PAHANG") && !pihak.getTempatLahir().equalsIgnoreCase("PERAK") && !pihak.getTempatLahir().equalsIgnoreCase("PERLIS") && !pihak.getTempatLahir().equalsIgnoreCase("PULAU PINANG") && !pihak.getTempatLahir().equalsIgnoreCase("SABAH") && !pihak.getTempatLahir().equalsIgnoreCase("SARAWAK") && !pihak.getTempatLahir().equalsIgnoreCase("SELANGOR") && !pihak.getTempatLahir().equalsIgnoreCase("TERENGGANU") && !pihak.getTempatLahir().equalsIgnoreCase("WP KUALA LUMPUR") && !pihak.getTempatLahir().equalsIgnoreCase("WP LABUAN") && !pihak.getTempatLahir().equalsIgnoreCase("WP PUTRAJAYA")) {
                tempatLahirLain = pihak.getTempatLahir();
                pihak.setTempatLahir("LAIN-LAIN");
            }
        }

        if (permohonanPihak != null) {
            jenisPihak = permohonanPihak.getJenis().getKod();
            if (permohonanPihak.getKaitan() != null) {
                if (p.getKodUrusan().getKod().equals("PMADT") || p.getKodUrusan().getKod().equals("CGADT") || p.getKodUrusan().getKod().equals("PAADT") || p.getKodUrusan().getKod().equals("TMADT") || p.getKodUrusan().getKod().equals("BTADT")) {
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
        }

        if (permohonanPihak.getSenaraiHubungan() != null) {

            for (int i = 0; i < permohonanPihak.getSenaraiHubungan().size(); i++) {
                if (i == 1) {
                    permohonanPihakHubungan = new PermohonanPihakHubungan();
                    permohonanPihakHubungan = permohonanPihak.getSenaraiHubungan().get(i);
                } else if (i == 2) {
                    permohonanPihakHubungan2 = new PermohonanPihakHubungan();
                    permohonanPihakHubungan2 = permohonanPihak.getSenaraiHubungan().get(i);
                } else if (i == 3) {
                    permohonanPihakHubungan3 = new PermohonanPihakHubungan();
                    permohonanPihakHubungan3 = permohonanPihak.getSenaraiHubungan().get(i);
                } else if (i == 4) {
                    permohonanPihakHubungan4 = new PermohonanPihakHubungan();
                    permohonanPihakHubungan4 = permohonanPihak.getSenaraiHubungan().get(i);
                } else if (i == 5) {
                    permohonanPihakHubungan5 = new PermohonanPihakHubungan();
                    permohonanPihakHubungan5 = permohonanPihak.getSenaraiHubungan().get(i);
                } else if (i == 6) {
                    permohonanPihakHubungan6 = new PermohonanPihakHubungan();
                    permohonanPihakHubungan6 = permohonanPihak.getSenaraiHubungan().get(i);
                } else if (i == 7) {
                    permohonanPihakHubungan7 = new PermohonanPihakHubungan();
                    permohonanPihakHubungan7 = permohonanPihak.getSenaraiHubungan().get(i);
                } else if (i == 8) {
                    permohonanPihakHubungan8 = new PermohonanPihakHubungan();
                    permohonanPihakHubungan8 = permohonanPihak.getSenaraiHubungan().get(i);
                } else if (i == 9) {
                    permohonanPihakHubungan9 = new PermohonanPihakHubungan();
                    permohonanPihakHubungan9 = permohonanPihak.getSenaraiHubungan().get(i);
                } else if (i == 10) {
                    permohonanPihakHubungan10 = new PermohonanPihakHubungan();
                    permohonanPihakHubungan10 = permohonanPihak.getSenaraiHubungan().get(i);
                }
            }
        }

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution showEditSimati() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));

        if (pihak.getTarikhMati() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            tarikhMati = formatter.format(pihak.getTarikhMati());
        }

        String idHakmilik = getContext().getRequest().getParameter("hakmilik");

        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("multiple", Boolean.TRUE);
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
        }
        return new JSP("consent/edit_pihak_mati.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPemohonEdit() throws ParseException {
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();
            Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

            if (pihakTemp != null) {

                infoAudit = pihakTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pguna);
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

                Pemohon pemohonTemp = pemohonService.findPemohonByPermohonanPihak(p, pihakTemp);
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
                }
                pemohonService.saveOrUpdate(pemohonTemp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            if (StringUtils.isNotBlank(idHakmilik)) {
                return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
            } else {
                return new JSP("consent/pihak_berkepentingan.jsp").addParameter("tab", "true");
            }
        }
        tx.commit();
        addSimpleMessage("Kemaskini Data Berjaya.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            return new JSP("consent/pihak_berkepentingan.jsp").addParameter("tab", "true");
        }
    }

    public Resolution simpanPenerimaEdit() throws ParseException {

        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        Permohonan permohonan = null;

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }

        InfoAudit infoAudit = new InfoAudit();
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihakTemp != null) {

            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
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

            PermohonanPihak permohonanPihakTemp = new PermohonanPihak();
            permohonanPihakTemp = permohonanPihakService.findById(String.valueOf(permohonanPihak.getIdPermohonanPihak()));

            infoAudit = permohonanPihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            permohonanPihakTemp.setPekerjaan(permohonanPihak.getPekerjaan());
            permohonanPihakTemp.setPendapatan(permohonanPihak.getPendapatan());
            permohonanPihakTemp.setStatusKahwin(permohonanPihak.getStatusKahwin());
            permohonanPihakTemp.setTangungan(permohonanPihak.getTangungan());
            permohonanPihakTemp.setUmur(permohonanPihak.getUmur());

            permohonanPihakTemp.setPihak(pihakTemp);
            permohonanPihakTemp.setInfoAudit(infoAudit);

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

            if (idCawangan != null) {
                pihakCawangan = new PihakCawangan();
                pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
                permohonanPihakTemp.setPihakCawangan(pihakCawangan);
            }

            if (semuaIdHakmilik != null) {
                pihakService.saveMultipleIdHakmilik(pihakTemp, permohonanPihakTemp);
            } else {
                pihakService.saveOrUpdatePihakKepentinganPihak(pihakTemp, permohonanPihakTemp);
            }

            if (pihakCawangan != null) {
                pihakCawangan.setIbupejabat(pihakTemp);
                pihakCawangan.setInfoAudit(infoAudit);
                pihakService.saveOrUpdatePihakCawangan(pihakCawangan);
            }

            if (permohonanPihakHubungan != null) {
                permohonanPihakHubungan.setPihak(permohonanPihakTemp);
                permohonanPihakHubungan.setCawangan(p.getCawangan());
                permohonanPihakHubungan.setKaitan("DipegangAmanah");
                permohonanPihakHubungan.setInfoAudit(infoAudit);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan);
                }
            }

            if (permohonanPihakHubungan2 != null) {
                permohonanPihakHubungan2.setPihak(permohonanPihakTemp);
                permohonanPihakHubungan2.setCawangan(p.getCawangan());
                permohonanPihakHubungan2.setKaitan("DipegangAmanah");
                permohonanPihakHubungan2.setInfoAudit(infoAudit);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan2, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan2);
                }
            }

            if (permohonanPihakHubungan3 != null) {
                permohonanPihakHubungan3.setPihak(permohonanPihakTemp);
                permohonanPihakHubungan3.setCawangan(p.getCawangan());
                permohonanPihakHubungan3.setKaitan("DipegangAmanah");
                permohonanPihakHubungan3.setInfoAudit(infoAudit);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan3, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan3);
                }
            }

            if (permohonanPihakHubungan4 != null) {
                permohonanPihakHubungan4.setPihak(permohonanPihakTemp);
                permohonanPihakHubungan4.setCawangan(p.getCawangan());
                permohonanPihakHubungan4.setKaitan("DipegangAmanah");
                permohonanPihakHubungan4.setInfoAudit(infoAudit);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan4, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan4);
                }
            }

            if (permohonanPihakHubungan5 != null) {
                permohonanPihakHubungan5.setPihak(permohonanPihakTemp);
                permohonanPihakHubungan5.setCawangan(p.getCawangan());
                permohonanPihakHubungan5.setKaitan("DipegangAmanah");
                permohonanPihakHubungan5.setInfoAudit(infoAudit);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan5, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan5);
                }
            }

            if (permohonanPihakHubungan6 != null) {
                permohonanPihakHubungan6.setPihak(permohonanPihakTemp);
                permohonanPihakHubungan6.setCawangan(p.getCawangan());
                permohonanPihakHubungan6.setKaitan("DipegangAmanah");
                permohonanPihakHubungan6.setInfoAudit(infoAudit);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan6, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan6);
                }
            }

            if (permohonanPihakHubungan7 != null) {
                permohonanPihakHubungan7.setPihak(permohonanPihakTemp);
                permohonanPihakHubungan7.setCawangan(p.getCawangan());
                permohonanPihakHubungan7.setKaitan("DipegangAmanah");
                permohonanPihakHubungan7.setInfoAudit(infoAudit);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan7, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan7);
                }
            }

            if (permohonanPihakHubungan8 != null) {
                permohonanPihakHubungan8.setPihak(permohonanPihakTemp);
                permohonanPihakHubungan8.setCawangan(p.getCawangan());
                permohonanPihakHubungan8.setKaitan("DipegangAmanah");
                permohonanPihakHubungan8.setInfoAudit(infoAudit);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan8, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan8);
                }
            }

            if (permohonanPihakHubungan9 != null) {
                permohonanPihakHubungan9.setPihak(permohonanPihakTemp);
                permohonanPihakHubungan9.setCawangan(p.getCawangan());
                permohonanPihakHubungan9.setKaitan("DipegangAmanah");
                permohonanPihakHubungan9.setInfoAudit(infoAudit);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan9, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan9);
                }
            }

            if (permohonanPihakHubungan10 != null) {
                permohonanPihakHubungan10.setPihak(permohonanPihakTemp);
                permohonanPihakHubungan10.setCawangan(p.getCawangan());
                permohonanPihakHubungan10.setKaitan("DipegangAmanah");
                permohonanPihakHubungan10.setInfoAudit(infoAudit);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan10, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan10);
                }
            }
        }
        addSimpleMessage("Kemaskini Data Berjaya.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            return new JSP("consent/pihak_berkepentingan.jsp").addParameter("tab", "true");
        }
    }

    public Resolution simpanPihakKepentinganPopup() throws ParseException {
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        Hakmilik hmk = null;
        if (StringUtils.isNotBlank(idHakmilik)) {
            hmk = hakmilikDAO.findById(idHakmilik);
        } else {
            hmk = p.getSenaraiHakmilik().get(0).getHakmilik();
        }

        Permohonan permohonan = null;

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihakTemp == null) {

            pihakTemp = new Pihak();
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
            pihakTemp.setInfoAudit(info);
            pihakTemp.setTempatLahir(pihak.getTempatLahir());

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
        }

        if (jenisPihak != null) {

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

//            Fraction f = Fraction.ZERO;
//            List<Pemohon> senaraiPemohon = permohonan.getSenaraiPemohon();
//
//            for (Pemohon pmhn : senaraiPemohon) {
//                if (p == null) {
//                    continue;
//                }
//                LOG.debug("pihak = " + pmhn.getPihak().getIdPihak());
//                LOG.debug("hakmilik = " + hmk.getIdHakmilik());
//                HakmilikPihakBerkepentingan hm = syerService.findSyerPihakFromHakmilikPihak(pmhn.getPihak().getIdPihak(), hmk.getIdHakmilik());
//                if (hm == null) {
//                    continue;
//                }
//                if (hm.getSyerPembilang() == 0 && hm.getSyerPenyebut() == 0) {
//                    continue;
//                }
//                if (isDebug) {
//                    LOG.debug("syer terlibat = " + hm.getSyerPembilang() + "/" + hm.getSyerPenyebut());
//                }
//                f = f.add(new Fraction(hm.getSyerPembilang(), hm.getSyerPenyebut()));
//            }

            if (permohonanPihak == null) {
                permohonanPihak = new PermohonanPihak();
            }

            permohonanPihak.setPermohonan(permohonan);
            permohonanPihak.setPihak(pihakTemp);
//            if (isDebug) {
//                LOG.debug("syer terlibat =" + f.toString());
//            }
//            if (permohonan.getKodUrusan().getKod().equals("PJT") || permohonan.getKodUrusan().getKod().equals("PJKT")) {
//                permohonanPihak.setSyerPembilang(1);
//                permohonanPihak.setSyerPenyebut(1);
//            } else {
//                if (f.equals(Fraction.ZERO)) {
//                    permohonanPihak.setSyerPembilang(0);
//                    permohonanPihak.setSyerPenyebut(0);
//                } else {
//
//                    //-------------------- add new---------
//                    List<PermohonanPihak> senaraiMohonPihak = new ArrayList<PermohonanPihak>();
//                    List<PermohonanPihak> senaraiMohonGadai = new ArrayList<PermohonanPihak>();
//
//                    if (p.getKodUrusan().getKod().equals("PCPTD") || p.getKodUrusan().getKod().equals("PGDMB") || p.getKodUrusan().getKod().equals("PCMMK")) {
//                        senaraiMohonPihak = permohonanPihakService.getSenaraiPmohonPihakByNotKod(idPermohonan, "PGA");
//                        senaraiMohonGadai = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "PGA");
//                    } else {
//                        senaraiMohonPihak = permohonan.getSenaraiPihak();
//                    }
//
//                    if (!senaraiMohonPihak.isEmpty()) {
//                        List<PermohonanPihak> senaraiMohonPihakTmp = null;
//                        if (senaraiMohonPihak.size() > 0) {
//                            senaraiMohonPihakTmp = new ArrayList<PermohonanPihak>();
//                            int jumPemohon = senaraiMohonPihak.size() + 1;
//                            f = f.divide(jumPemohon);
//                            for (PermohonanPihak pp : senaraiMohonPihak) {
//                                pp.setSyerPembilang(f.getNumerator());
//                                pp.setSyerPenyebut(f.getDenominator());
//                                pp.setInfoAudit(info);
//                                senaraiMohonPihakTmp.add(pp);
//                            }
//                            permohonanPihakService.saveOrUpdate(senaraiMohonPihakTmp);
//                        }
//                        permohonanPihak.setSyerPembilang(f.getNumerator());
//                        permohonanPihak.setSyerPenyebut(f.getDenominator());
//                    }
//
//                    if (!senaraiMohonGadai.isEmpty()) {
//                        List<PermohonanPihak> senaraiMohonPihakTmp = null;
//                        if (senaraiMohonGadai.size() > 0) {
//                            senaraiMohonPihakTmp = new ArrayList<PermohonanPihak>();
//                            int jumPemohon = senaraiMohonGadai.size() + 1;
//                            f = f.divide(jumPemohon);
//                            for (PermohonanPihak pp : senaraiMohonGadai) {
//                                pp.setSyerPembilang(f.getNumerator());
//                                pp.setSyerPenyebut(f.getDenominator());
//                                pp.setInfoAudit(info);
//                                senaraiMohonPihakTmp.add(pp);
//                            }
//                            permohonanPihakService.saveOrUpdate(senaraiMohonPihakTmp);
//                        }
//                        permohonanPihak.setSyerPembilang(f.getNumerator());
//                        permohonanPihak.setSyerPenyebut(f.getDenominator());
//                    }
//                    //---------------------///---------
//                }
//            }

            if (permohonanPihak.getKaitan() != null) {
                if (permohonanPihak.getKaitan().equalsIgnoreCase("lain-lain")) {
                    permohonanPihak.setKaitan(hubunganLain);
                }
            }

            permohonanPihak.setInfoAudit(info);
            permohonanPihak.setHakmilik(hmk);
            KodJenisPihakBerkepentingan kodJenisPihak = new KodJenisPihakBerkepentingan();
            kodJenisPihak.setKod(jenisPihak);
            permohonanPihak.setJenis(kodJenisPihak);

            if (idCawangan != null) {
                pihakCawangan = new PihakCawangan();
                pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
                permohonanPihak.setPihakCawangan(pihakCawangan);
            }
            permohonanPihak.setCawangan(permohonan.getCawangan());

            if (semuaIdHakmilik != null) {
                pihakService.saveMultipleIdHakmilik(pihakTemp, permohonanPihak);

            } else {
                pihakService.saveOrUpdatePihakKepentinganPihak(pihakTemp, permohonanPihak);
            }

            if (pihakCawangan != null) {
                pihakCawangan.setIbupejabat(pihakTemp);
                pihakCawangan.setInfoAudit(info);
                pihakService.saveOrUpdatePihakCawangan(pihakCawangan);
            }

            if (permohonanPihakHubungan != null) {
                permohonanPihakHubungan.setPihak(permohonanPihak);
                permohonanPihakHubungan.setCawangan(p.getCawangan());
                permohonanPihakHubungan.setKaitan("DipegangAmanah");
                permohonanPihakHubungan.setInfoAudit(info);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan);
                }
            }

            if (permohonanPihakHubungan2 != null) {
                permohonanPihakHubungan2.setPihak(permohonanPihak);
                permohonanPihakHubungan2.setCawangan(p.getCawangan());
                permohonanPihakHubungan2.setKaitan("DipegangAmanah");
                permohonanPihakHubungan2.setInfoAudit(info);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan2, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan2);
                }
            }

            if (permohonanPihakHubungan3 != null) {
                permohonanPihakHubungan3.setPihak(permohonanPihak);
                permohonanPihakHubungan3.setCawangan(p.getCawangan());
                permohonanPihakHubungan3.setKaitan("DipegangAmanah");
                permohonanPihakHubungan3.setInfoAudit(info);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan3, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan3);
                }
            }

            if (permohonanPihakHubungan4 != null) {
                permohonanPihakHubungan4.setPihak(permohonanPihak);
                permohonanPihakHubungan4.setCawangan(p.getCawangan());
                permohonanPihakHubungan4.setKaitan("DipegangAmanah");
                permohonanPihakHubungan4.setInfoAudit(info);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan4, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan4);
                }
            }

            if (permohonanPihakHubungan5 != null) {
                permohonanPihakHubungan5.setPihak(permohonanPihak);
                permohonanPihakHubungan5.setCawangan(p.getCawangan());
                permohonanPihakHubungan5.setKaitan("DipegangAmanah");
                permohonanPihakHubungan5.setInfoAudit(info);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan5, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan5);
                }
            }

            if (permohonanPihakHubungan6 != null) {
                permohonanPihakHubungan6.setPihak(permohonanPihak);
                permohonanPihakHubungan6.setCawangan(p.getCawangan());
                permohonanPihakHubungan6.setKaitan("DipegangAmanah");
                permohonanPihakHubungan6.setInfoAudit(info);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan6, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan6);
                }
            }

            if (permohonanPihakHubungan7 != null) {
                permohonanPihakHubungan7.setPihak(permohonanPihak);
                permohonanPihakHubungan7.setCawangan(p.getCawangan());
                permohonanPihakHubungan7.setKaitan("DipegangAmanah");
                permohonanPihakHubungan7.setInfoAudit(info);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan7, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan7);
                }
            }

            if (permohonanPihakHubungan8 != null) {
                permohonanPihakHubungan8.setPihak(permohonanPihak);
                permohonanPihakHubungan8.setCawangan(p.getCawangan());
                permohonanPihakHubungan8.setKaitan("DipegangAmanah");
                permohonanPihakHubungan8.setInfoAudit(info);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan8, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan8);
                }
            }

            if (permohonanPihakHubungan9 != null) {
                permohonanPihakHubungan9.setPihak(permohonanPihak);
                permohonanPihakHubungan9.setCawangan(p.getCawangan());
                permohonanPihakHubungan9.setKaitan("DipegangAmanah");
                permohonanPihakHubungan9.setInfoAudit(info);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan9, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan9);
                }
            }

            if (permohonanPihakHubungan10 != null) {
                permohonanPihakHubungan10.setPihak(permohonanPihak);
                permohonanPihakHubungan10.setCawangan(p.getCawangan());
                permohonanPihakHubungan10.setKaitan("DipegangAmanah");
                permohonanPihakHubungan10.setInfoAudit(info);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan10, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan10);
                }
            }

            if (permohonanPihakHubungan11 != null) {
                permohonanPihakHubungan11.setPihak(permohonanPihak);
                permohonanPihakHubungan11.setCawangan(p.getCawangan());
                permohonanPihakHubungan11.setKaitan("DipegangAmanah");
                permohonanPihakHubungan11.setInfoAudit(info);

                if (semuaIdHakmilik != null) {
                    pihakService.saveMultiplePihakHubungan(permohonanPihakHubungan11, permohonan);
                } else {
                    pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan11);
                }
            }

            if (StringUtils.isNotBlank(idHakmilik)) {
                return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
            } else {
                return new RedirectResolution("/consent/pihak_consent?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
            }
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanPemohonPopup() throws ParseException {

        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");


        String idHakmilik = getContext().getRequest().getParameter("hakmilik");

        Permohonan permohonan = null;

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
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
        pihakTemp.setInfoAudit(info);

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

        if (pihakTemp != null) {

            if (permohonanPihak == null) {
                permohonanPihak = new PermohonanPihak();
            }

            Pemohon pemohonTemp = new Pemohon();
            pemohonTemp.setPermohonan(permohonan);
            pemohonTemp.setPihak(pihakTemp);

            if (permohonanPihak.getKaitan() != null) {
                if (permohonanPihak.getKaitan().equalsIgnoreCase("lain-lain")) {
                    pemohonTemp.setKaitan(hubunganLain);
                } else {
                    pemohonTemp.setKaitan(permohonanPihak.getKaitan());
                }
            }
            pemohonTemp.setInfoAudit(info);
            pemohonTemp.setCawangan(permohonan.getCawangan());
            pemohonTemp.setStatusKahwin(permohonanPihak.getStatusKahwin());
            pemohonTemp.setPekerjaan(permohonanPihak.getPekerjaan());
            pemohonTemp.setUmur(permohonanPihak.getUmur());
            pemohonTemp.setPendapatan(permohonanPihak.getPendapatan());
            pemohonTemp.setTanggungan(permohonanPihak.getTangungan());

            if (pemohon != null) {
                pemohonTemp.setTempohMastautin(pemohon.getTempohMastautin());
            }

            pemohonService.saveOrUpdate(pemohonTemp);

            if (StringUtils.isNotBlank(idHakmilik)) {
                return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
            } else {
                return new RedirectResolution(PihakConsentActionBean.class, "getSenaraiHakmilikKepentingan");
            }
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution cariPihak() {

        String tmp = getContext().getRequest().getParameter("tuanTanah");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
            getContext().getRequest().setAttribute("multiple", Boolean.TRUE);
        }
        if (StringUtils.isNotBlank(tmp)) {
            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
        }

        String jenisPB = getContext().getRequest().getParameter("jenisPB");
        if (StringUtils.isNotBlank(jenisPB)) {
            KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById(jenisPB);
            if (permohonanPihak == null) {
                permohonanPihak = new PermohonanPihak();
            }
            permohonanPihak.setJenis(kod);
            getContext().getRequest().setAttribute("jenis", jenisPB);
        } else {
            String kodUrusan = p.getKodUrusan().getKod();
            if (kodUrusan.equals("TMAML") || kodUrusan.equals("TMAMD")) {
                //if TMAML / TMAMD , jenis pihak must be pendaftar(PP)
                KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById("PP");
                if (permohonanPihak == null) {
                    permohonanPihak = new PermohonanPihak();
                }
                permohonanPihak.setJenis(kod);
            }
        }

        if (pihak != null) {

            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null && pihak.getNama() == null) {
                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

                if (pihakSearch != null && StringUtils.isBlank(idHakmilik)) {
                    if (!(mohonPihakList.isEmpty())) {
                        for (PermohonanPihak pp : mohonPihakList) {
                            if (pp.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {

                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (!duplicateFlag) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        pengarahList = pihak.getSenaraiPengarah();
                        if (pihak.getTempatLahir() != null) {
                            if (!pihak.getTempatLahir().equalsIgnoreCase("JOHOR") && !pihak.getTempatLahir().equalsIgnoreCase("KEDAH") && !pihak.getTempatLahir().equalsIgnoreCase("KELANTAN") && !pihak.getTempatLahir().equalsIgnoreCase("MELAKA") && !pihak.getTempatLahir().equalsIgnoreCase("NEGERI SEMBILAN") && !pihak.getTempatLahir().equalsIgnoreCase("PAHANG") && !pihak.getTempatLahir().equalsIgnoreCase("PERAK") && !pihak.getTempatLahir().equalsIgnoreCase("PERLIS") && !pihak.getTempatLahir().equalsIgnoreCase("PULAU PINANG") && !pihak.getTempatLahir().equalsIgnoreCase("SABAH") && !pihak.getTempatLahir().equalsIgnoreCase("SARAWAK") && !pihak.getTempatLahir().equalsIgnoreCase("SELANGOR") && !pihak.getTempatLahir().equalsIgnoreCase("TERENGGANU") && !pihak.getTempatLahir().equalsIgnoreCase("WP KUALA LUMPUR") && !pihak.getTempatLahir().equalsIgnoreCase("WP LABUAN") && !pihak.getTempatLahir().equalsIgnoreCase("WP PUTRAJAYA")) {
                                tempatLahirLain = pihak.getTempatLahir();
                                pihak.setTempatLahir("LAIN-LAIN");
                            }
                        }
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else if (pihak.getNama() != null && pihak.getNoPengenalan() == null) {

                pihakByNameList = new ArrayList<Pihak>();
                if (pihak.getJenisPengenalan() != null && pihak.getNama() != null) {
                    pihakByNameList = pihakService.findPihakByKodKpAndName(pihak.getJenisPengenalan().getKod(), pihak.getNama());
                } else if (pihak.getJenisPengenalan() == null && pihak.getNama() != null) {
                    pihakByNameList = pihakService.findPihakByNama(pihak.getNama());
                }

                if (pihakByNameList.isEmpty()) {
                    addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                    cariFlag = true;
                    tiadaDataFlag = true;
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsa();
                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
                }
            } else if (pihak.getJenisPengenalan() == null && pihak.getNoPengenalan() != null) {
                addSimpleError("Sila Masukkan Jenis Pengenalan.");
            } else if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() == null && pihak.getNama() == null) {
                addSimpleError("Sila Masukkan Nombor Pengenalan Atau Nama.");
            } else if (pihak.getJenisPengenalan() == null && pihak.getNoPengenalan() != null && pihak.getNama() != null) {
                addSimpleError("Sila Masukkan Jenis Pengenalan.");
            } else if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null && pihak.getNama() != null) {
                addSimpleError("Sila Pastikan Carian Berdasarkan Nombor Pengenalan Atau Nama.");
            }
        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan Atau Nama.");
        }

        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution selectName() {

        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");

        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
        }
        pihak = pihakService.findById(idPihak);
        cariFlag = true;
        tiadaDataFlag = false;
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution selectNamePemohon() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakService.findById(idPihak);
        cariFlag = true;
        tiadaDataFlag = false;
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution cariPihakPemohon() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
            getContext().getRequest().setAttribute("multiple", Boolean.TRUE);
        }

        String tmp = getContext().getRequest().getParameter("tuanTanah");
        if (StringUtils.isNotBlank(tmp)) {
            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
        }

        String jenisPB = getContext().getRequest().getParameter("jenisPB");
        if (StringUtils.isNotBlank(jenisPB)) {
            KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById(jenisPB);
            if (permohonanPihak == null) {
                permohonanPihak = new PermohonanPihak();
            }
            permohonanPihak.setJenis(kod);
            getContext().getRequest().setAttribute("jenis", jenisPB);
        }

        if (pihak != null) {
            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null && pihak.getNama() == null) {

                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

                if (pihakSearch != null && StringUtils.isBlank(idHakmilik)) {
                    if (!(pemohonList.isEmpty())) {
                        for (Pemohon pem : pemohonList) {
                            if (pem.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {

                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (!duplicateFlag) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        pengarahList = pihak.getSenaraiPengarah();
                        if (pihak.getTempatLahir() != null) {
                            if (!pihak.getTempatLahir().equalsIgnoreCase("JOHOR") && !pihak.getTempatLahir().equalsIgnoreCase("KEDAH") && !pihak.getTempatLahir().equalsIgnoreCase("KELANTAN") && !pihak.getTempatLahir().equalsIgnoreCase("MELAKA") && !pihak.getTempatLahir().equalsIgnoreCase("NEGERI SEMBILAN") && !pihak.getTempatLahir().equalsIgnoreCase("PAHANG") && !pihak.getTempatLahir().equalsIgnoreCase("PERAK") && !pihak.getTempatLahir().equalsIgnoreCase("PERLIS") && !pihak.getTempatLahir().equalsIgnoreCase("PULAU PINANG") && !pihak.getTempatLahir().equalsIgnoreCase("SABAH") && !pihak.getTempatLahir().equalsIgnoreCase("SARAWAK") && !pihak.getTempatLahir().equalsIgnoreCase("SELANGOR") && !pihak.getTempatLahir().equalsIgnoreCase("TERENGGANU") && !pihak.getTempatLahir().equalsIgnoreCase("WP KUALA LUMPUR") && !pihak.getTempatLahir().equalsIgnoreCase("WP LABUAN") && !pihak.getTempatLahir().equalsIgnoreCase("WP PUTRAJAYA")) {
                                tempatLahirLain = pihak.getTempatLahir();
                                pihak.setTempatLahir("LAIN-LAIN");
                            }
                        }
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else if (pihak.getNama() != null && pihak.getNoPengenalan() == null) {
                pihakByNameList = new ArrayList<Pihak>();
                if (pihak.getJenisPengenalan() != null && pihak.getNama() != null) {
                    pihakByNameList = pihakService.findPihakByKodKpAndName(pihak.getJenisPengenalan().getKod(), pihak.getNama());
                } else if (pihak.getJenisPengenalan() == null && pihak.getNama() != null) {
                    pihakByNameList = pihakService.findPihakByNama(pihak.getNama());
                }

                if (pihakByNameList.isEmpty()) {
                    addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                    cariFlag = true;
                    tiadaDataFlag = true;
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsa();
                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
                }
            } else if (pihak.getJenisPengenalan() == null && pihak.getNoPengenalan() != null) {
                addSimpleError("Sila Masukkan Jenis Pengenalan.");
            } else if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() == null && pihak.getNama() == null) {
                addSimpleError("Sila Masukkan Nombor Pengenalan Atau Nama.");
            } else if (pihak.getJenisPengenalan() == null && pihak.getNoPengenalan() != null && pihak.getNama() != null) {
                addSimpleError("Sila Masukkan Jenis Pengenalan.");
            } else if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null && pihak.getNama() != null) {
                addSimpleError("Sila Pastikan Carian Berdasarkan Nombor Pengenalan Atau Nama.");
            }
        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan Atau Nama.");
        }

        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution cariSemula() {
        pihak = new Pihak();
        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution cariSemulaPemohon() {
        pihak = new Pihak();
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution semakSyer() {
        String result = "Pembahagian tanah berjaya.";
        String jenis = getContext().getRequest().getParameter("jenis");
        int i = 0;
        List<PermohonanPihak> permPihak = new ArrayList<PermohonanPihak>();
        if (jenis.equals("penerima")) {
            for (PermohonanPihak pp : mohonPihakList) {
                pp.setSyerPembilang(Integer.parseInt(syer1[i]));
                pp.setSyerPenyebut(Integer.parseInt(syer2[i]));
                permPihak.add(pp);
                i = i + 1;
            }
        } else if (jenis.equals("gadaian")) {
            for (PermohonanPihak pp : penerimaGadaianList) {
                pp.setSyerPembilang(Integer.parseInt(syer1[i]));
                pp.setSyerPenyebut(Integer.parseInt(syer2[i]));
                permPihak.add(pp);
                i = i + 1;
            }
        }
        permohonanPihakService.saveOrUpdate(permPihak);

        List<HakmilikPermohonan> senaraiPermohonan = p.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : senaraiPermohonan) {
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            try {
                int r = syerService.doValidateSyerPortionConsent(idPermohonan, hm.getIdHakmilik(), jenis);
                if (r < 0) {
                    result = "Jumlah pembahagian tanah tidak mencukupi.";
                    break;
                } else if (r > 0) {
                    result = "Jumlah pembahagian tanah melebihi daripada bahagian pemohon.";
                    break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return new StreamingResolution("text/plain", "Pembahagian Tanah Gagal.");
            }
        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution semakSyerByIdHakmilik() {
        String result = "Pembahagian tanah berjaya.";
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        String jenis = getContext().getRequest().getParameter("jenis");
        Hakmilik hm = null;
        if (StringUtils.isNotBlank(idHakmilik)) {
            hm = hakmilikDAO.findById(idHakmilik);
        } else {
            hm = p.getSenaraiHakmilik().get(0).getHakmilik();
        }

        int i = 0;
        List<PermohonanPihak> permPihak = new ArrayList<PermohonanPihak>();
        if (jenis.equals("penerima")) {
            for (PermohonanPihak pp : mohonPihakList) {
                if (pp == null || pp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik h = pp.getHakmilik();
                if (!h.getIdHakmilik().equals(idHakmilik)) {
                    continue;
                }
                pp.setSyerPembilang(Integer.parseInt(syer1[i]));
                pp.setSyerPenyebut(Integer.parseInt(syer2[i]));
                permPihak.add(pp);
                i = i + 1;
            }
        } else if (jenis.equals("gadaian")) {
            for (PermohonanPihak pp : penerimaGadaianList) {
                if (pp == null || pp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik h = pp.getHakmilik();
                if (!h.getIdHakmilik().equals(idHakmilik)) {
                    continue;
                }
                pp.setSyerPembilang(Integer.parseInt(syer1[i]));
                pp.setSyerPenyebut(Integer.parseInt(syer2[i]));
                permPihak.add(pp);
                i = i + 1;
            }
        }

        permohonanPihakService.saveOrUpdate(permPihak);
        try {
            int r = syerService.doValidateSyerPortionConsent(idPermohonan, hm.getIdHakmilik(), jenis);
            if (r < 0) {
                result = "Jumlah pembahagian tanah tidak mencukupi.";
            } else if (r > 0) {
                result = "Jumlah pembahagian tanah melebihi daripada bahagian pemohon.";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StreamingResolution("text/plain", "Pembahagian Tanah Gagal.");
        }


        return new StreamingResolution("text/plain", result);
    }

    public Resolution agihSamaRata() {
        String results = "0";
        String DELIM = "__^$__";
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        String jenis = getContext().getRequest().getParameter("jenis");
        Hakmilik hm = null;
        if (StringUtils.isNotBlank(idHakmilik)) {
            hm = hakmilikDAO.findById(idHakmilik);
        } else {
            hm = p.getSenaraiHakmilik().get(0).getHakmilik();
        }

        //TODO : multiple hakmilik
        Fraction sumAllPemohon = Fraction.ZERO;
        Fraction samaRata = Fraction.ZERO;

        tuanTanahTerlibatList = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(p.getIdPermohonan(), "TER", hm.getIdHakmilik());

        if (hm != null) {
            LOG.debug("************");

            if (tuanTanahTerlibatList.size() > 0) {
                for (PermohonanPihak perPihak : tuanTanahTerlibatList) {
                    HakmilikPihakBerkepentingan hmk = hakmilikPihakKepentinganService.findHakmilikPihakByPihak(perPihak.getPihak().getIdPihak(), hm);
                    if (hmk != null) {
                        LOG.debug("pihak = " + perPihak.getPihak().getNama());
                        LOG.debug("pembilang =" + hmk.getSyerPembilang());
                        LOG.debug("penyebut =" + hmk.getSyerPenyebut());

                        sumAllPemohon = sumAllPemohon.add(new Fraction(hmk.getSyerPembilang(), hmk.getSyerPenyebut()));
                        continue;
                    }
                }
            } else {
                for (Pemohon pmohon : pemohonList) {
                    HakmilikPihakBerkepentingan hmk = hakmilikPihakKepentinganService.findHakmilikPihakByPihak(pmohon.getPihak().getIdPihak(), hm);
                    if (hmk != null) {
                        LOG.debug("pihak = " + pmohon.getPihak().getNama());
                        LOG.debug("pembilang =" + hmk.getSyerPembilang());
                        LOG.debug("penyebut =" + hmk.getSyerPenyebut());

                        sumAllPemohon = sumAllPemohon.add(new Fraction(hmk.getSyerPembilang(), hmk.getSyerPenyebut()));
                        continue;
                    }
                }
            }
            LOG.debug("************");
        }
        LOG.debug("sum all = " + sumAllPemohon);
        if (sumAllPemohon.getDenominator() == 1) {
            sumAllPemohon = Fraction.ONE;
        }
        LOG.debug("sum all = " + sumAllPemohon);

//        ---------------------
        if (jenis.equals("penerima")) {

            if (mohonPihakList != null && mohonPihakList.size() > 0) {
                int pemohonListA = 0;

                for (PermohonanPihak pp : mohonPihakList) {
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
                    samaRata = sumAllPemohon.divide(pemohonListA);
                    for (PermohonanPihak pp : mohonPihakList) {
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

            if (penerimaGadaianList != null && penerimaGadaianList.size() > 0) {
                int pemohonListA = 0;

                for (PermohonanPihak pp : penerimaGadaianList) {
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
                    samaRata = sumAllPemohon.divide(pemohonListA);
                    for (PermohonanPihak pp : penerimaGadaianList) {
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

    public Resolution updateSyerMohonPihak() {
        String idPihak = getContext().getRequest().getParameter("idpihak");
        String s1 = getContext().getRequest().getParameter("syer1");//pembilang
        String s2 = getContext().getRequest().getParameter("syer2");//penyebut
        LOG.debug("s1:" + s1);
        LOG.debug("s2:" + s2);
        LOG.debug("idpihak:" + idPihak);
        if (StringUtils.isNotBlank(idPihak) && StringUtils.isNotBlank(s1) && StringUtils.isNotBlank(s2)) {
            LOG.debug(idPihak);
            LOG.debug(s1);
            LOG.debug(s2);
            PermohonanPihak pp = permohonanPihakService.findById(idPihak);
            pp.setSyerPembilang(Integer.parseInt(s1));
            pp.setSyerPenyebut(Integer.parseInt(s2));
            permohonanPihakService.saveOrUpdate(pp);
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution backCawangan() {

        pihak = pihakService.findById(String.valueOf(pihak.getIdPihak()));
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
        cariFlag = true;
        tiadaDataFlag = false;

        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution backCawanganEdit() {

        pihak = pihakService.findById(String.valueOf(pihak.getIdPihak()));
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
        cariFlag = true;
        tiadaDataFlag = false;
        permohonanPihak = permohonanPihakService.findById(String.valueOf(permohonanPihak.getIdPermohonanPihak()));
        getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        getContext().getRequest().setAttribute("hakmilik", permohonanPihak.getHakmilik().getIdHakmilik());

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution backCawanganPemohon() {

        pihak = pihakService.findById(String.valueOf(pihak.getIdPihak()));
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        cariFlag = true;
        tiadaDataFlag = false;
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution deletePgAmanah() {

        String idHubungan = getContext().getRequest().getParameter("idHubungan");

        cariFlag = true;

        if (idHubungan != null) {

            permohonanPihakHubungan = pihakHubunganDAO.findById(Long.parseLong(idHubungan));
            Long id = permohonanPihakHubungan.getPihak().getIdPermohonanPihak();

            if (permohonanPihakHubungan != null) {
                pihakHubunganService.delete(permohonanPihakHubungan);
//                cawanganService.delete(pihakCaw);
                permohonanPihak = permohonanPihakService.findById(String.valueOf(id));
                Long idPhk = permohonanPihak.getPihak().getIdPihak();
                pihak = new Pihak();
                pihak = pihakDAO.findById(idPhk);
                if (pihak.getSenaraiCawangan() != null) {
                    cawanganList = pihak.getSenaraiCawangan();
                }
                if (pihak.getSenaraiPengarah() != null) {
                    pengarahList = pihak.getSenaraiPengarah();
                }
            }
        }
        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution deleteCawangan() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        cariFlag = true;

        if (idCaw != null) {

            PihakCawangan pihakCaw = cawanganService.findById(idCaw);
            Long id = pihakCaw.getIbupejabat().getIdPihak();

            if (pihakCaw != null) {
                cawanganService.delete(pihakCaw);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
                if (pihak.getSenaraiCawangan() != null) {
                    cawanganList = pihak.getSenaraiCawangan();
                }
                if (pihak.getSenaraiPengarah() != null) {
                    pengarahList = pihak.getSenaraiPengarah();
                }
            }
        }
        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution deleteCawanganPemohon() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        cariFlag = true;

        if (idCaw != null) {

            PihakCawangan pihakCaw = cawanganService.findById(idCaw);
            Long id = pihakCaw.getIbupejabat().getIdPihak();

            if (pihakCaw != null) {
                cawanganService.delete(pihakCaw);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
                if (pihak.getSenaraiCawangan() != null) {
                    cawanganList = pihak.getSenaraiCawangan();
                    pengarahList = pihak.getSenaraiPengarah();
                }
            }
        }
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution editPgAmanahEdit() {

        String idHubungan = getContext().getRequest().getParameter("idHubungan");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        String idPermPihak = getContext().getRequest().getParameter("idPermPihak");
        tambahAmanahFlag = true;
        permohonanPihakHubungan = new PermohonanPihakHubungan();
        permohonanPihakHubungan = pihakHubunganDAO.findById(Long.parseLong(idHubungan));
        pihak = new Pihak();
        permohonanPihak = permohonanPihakService.findById(idPermPihak);
        pihak = pihakDAO.findById(permohonanPihak.getPihak().getIdPihak());

        getContext().getRequest().setAttribute("hakmilik", permohonanPihak.getHakmilik().getIdHakmilik());
        getContext().getRequest().setAttribute("penerima", Boolean.TRUE);

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution editCawanganEdit() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        String idPermPihak = getContext().getRequest().getParameter("idPermPihak");
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan = cawanganService.findById(idCaw);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakCawangan.getIbupejabat().getIdPihak());
        permohonanPihak = permohonanPihakService.findById(idPermPihak);

        getContext().getRequest().setAttribute("hakmilik", permohonanPihak.getHakmilik().getIdHakmilik());
        getContext().getRequest().setAttribute("penerima", Boolean.TRUE);

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution editCawanganPemohonEdit() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan = cawanganService.findById(idCaw);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakCawangan.getIbupejabat().getIdPihak());
        pemohon = pemohonService.findById(idPemohon);

//        getContext().getRequest().setAttribute("hakmilik", permohonanPihak.getHakmilik().getIdHakmilik());
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution editCawanganPemohon() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan = cawanganService.findById(idCaw);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakCawangan.getIbupejabat().getIdPihak());

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution deletePengarah() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        cariFlag = true;

        if (idPengarah != null) {

            PihakPengarah pPengarah = pihakService.findPengarahById(idPengarah);
            Long id = pPengarah.getPihak().getIdPihak();

            if (pPengarah != null) {
                pihakService.deletePengarah(pPengarah);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
                if (pihak.getSenaraiCawangan() != null) {
                    cawanganList = pihak.getSenaraiCawangan();
                }
                if (pihak.getSenaraiPengarah() != null) {
                    pengarahList = pihak.getSenaraiPengarah();
                }
            }
        }
        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution deletePengarahPemohon() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        cariFlag = true;

        if (idPengarah != null) {

            PihakPengarah pPengarah = pihakService.findPengarahById(idPengarah);
            Long id = pPengarah.getPihak().getIdPihak();

            if (pPengarah != null) {
                pihakService.deletePengarah(pPengarah);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
                if (pihak.getSenaraiCawangan() != null) {
                    cawanganList = pihak.getSenaraiCawangan();
                }
                if (pihak.getSenaraiPengarah() != null) {
                    pengarahList = pihak.getSenaraiPengarah();
                }
            }
        }
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution editPengarah() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        tambahPengarahFlag = true;
        pihakPengarah = new PihakPengarah();
        pihakPengarah = pihakService.findPengarahById(idPengarah);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakPengarah.getPihak().getIdPihak());

        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution editPengarahEdit() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        String idPermPihak = getContext().getRequest().getParameter("idPermPihak");
        tambahPengarahFlag = true;
        pihakPengarah = new PihakPengarah();
        pihakPengarah = pihakService.findPengarahById(idPengarah);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakPengarah.getPihak().getIdPihak());
        permohonanPihak = permohonanPihakService.findById(idPermPihak);
        getContext().getRequest().setAttribute("hakmilik", permohonanPihak.getHakmilik().getIdHakmilik());
        getContext().getRequest().setAttribute("penerima", Boolean.TRUE);

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution editPengarahPemohon() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        tambahPengarahFlag = true;
        pihakPengarah = new PihakPengarah();
        pihakPengarah = pihakService.findPengarahById(idPengarah);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakPengarah.getPihak().getIdPihak());
        String idPemohon = getContext().getRequest().getParameter("idPemohon");

        pemohon = pemohonService.findById(idPemohon);

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution tambahPengarah() {

        if (!(pihakDAO.exists(pihak.getIdPihak()))) {
            Transaction tx = sessionProvider.get().getTransaction();
            tx.begin();
            try {
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                pihak.setInfoAudit(info);
                pihakService.saveOrUpdate(pihak);

            } catch (Exception ex) {
                tx.rollback();
            }
            tx.commit();
        }
        tambahPengarahFlag = true;
        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution tambahPengarahEdit() {

        if (!(pihakDAO.exists(pihak.getIdPihak()))) {
            Transaction tx = sessionProvider.get().getTransaction();
            tx.begin();
            try {
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                pihak.setInfoAudit(info);
                pihakService.saveOrUpdate(pihak);

            } catch (Exception ex) {
                tx.rollback();
            }
            tx.commit();
        }
        tambahPengarahFlag = true;
        getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution tambahPengarahPemohon() {

        if (!(pihakDAO.exists(pihak.getIdPihak()))) {
            Transaction tx = sessionProvider.get().getTransaction();
            tx.begin();
            try {
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                pihak.setInfoAudit(info);
                pihakService.saveOrUpdate(pihak);

            } catch (Exception ex) {
                tx.rollback();
            }
            tx.commit();
        }
        tambahPengarahFlag = true;
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution tambahCawangan() {
        if (!(pihakDAO.exists(pihak.getIdPihak()))) {
            Transaction tx = sessionProvider.get().getTransaction();
            tx.begin();
            try {
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                pihak.setInfoAudit(info);
                pihakService.saveOrUpdate(pihak);
            } catch (Exception ex) {
                tx.rollback();
            }
            tx.commit();
        }
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan.setNamaCawangan(pihak.getNama());

        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution tambahPgAmanahEdit() {
        permohonanPihakHubungan = new PermohonanPihakHubungan();
        tambahAmanahFlag = true;
        getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution tambahCawanganEdit() {
        if (!(pihakDAO.exists(pihak.getIdPihak()))) {
            Transaction tx = sessionProvider.get().getTransaction();
            tx.begin();
            try {
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                pihak.setInfoAudit(info);
                pihakService.saveOrUpdate(pihak);
            } catch (Exception ex) {
                tx.rollback();
            }
            tx.commit();
        }
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan.setNamaCawangan(pihak.getNama());
        getContext().getRequest().setAttribute("penerima", Boolean.TRUE);

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution simpanCawangan() {

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());

        pihakCawangan.setIbupejabat(pihak);
        pihakCawangan.setInfoAudit(info);
        pihakService.saveOrUpdatePihakCawangan(pihakCawangan);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/consent/pihak_consent?getTambahCawanganPenerima&idPihak=" + id + "&jenisPB=" + jenisPihak).addParameter("popup", "true");
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanCawanganEdit() {

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());

        pihakCawangan.setIbupejabat(pihak);
        pihakCawangan.setInfoAudit(info);
        pihakService.saveOrUpdatePihakCawangan(pihakCawangan);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());
        String id2 = String.valueOf(permohonanPihak.getIdPermohonanPihak());

        if (cariFlag) {
            return new RedirectResolution("/consent/pihak_consent?getTambahCawanganPenerimaEdit&idPihak=" + id + "&jenisPB=" + jenisPihak + "&idPermPihak=" + id2).addParameter("popup", "true");
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahCawanganPenerima() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
        if (pihak.getSenaraiCawangan() != null) {
            cawanganList = pihak.getSenaraiCawangan();
        }

        if (pihak.getSenaraiPengarah() != null) {
            pengarahList = pihak.getSenaraiPengarah();
        }

        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");

        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution getTambahCawanganPenerimaEdit() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idPermPihak = getContext().getRequest().getParameter("idPermPihak");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
        if (pihak.getSenaraiCawangan() != null) {
            cawanganList = pihak.getSenaraiCawangan();
        }

        if (pihak.getSenaraiPengarah() != null) {
            pengarahList = pihak.getSenaraiPengarah();
        }

        permohonanPihak = permohonanPihakService.findById(idPermPihak);
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");

        getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        getContext().getRequest().setAttribute("hakmilik", permohonanPihak.getHakmilik().getIdHakmilik());

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution tambahCawanganPemohon() {

        if (pihak.getNama() == null) {
            addSimpleError("Sila masukkan data yang bertanda *.");
            cariFlag = true;
            tiadaDataFlag = true;
        } else {
            if (!(pihakDAO.exists(pihak.getIdPihak()))) {
                Transaction tx = sessionProvider.get().getTransaction();
                tx.begin();
                try {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(pguna);
                    info.setTarikhMasuk(new java.util.Date());
                    pihak.setInfoAudit(info);
                    pihakService.saveOrUpdate(pihak);

                } catch (Exception ex) {
                    tx.rollback();
                }
                tx.commit();
            }
            tambahCawFlag = true;
            pihakCawangan = new PihakCawangan();
            pihakCawangan.setNamaCawangan(pihak.getNama());
        }
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution simpanCawanganPemohon() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());

        pihakCawangan.setIbupejabat(pihak);
        pihakCawangan.setInfoAudit(info);
        pihakService.saveOrUpdatePihakCawangan(pihakCawangan);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/consent/pihak_consent?getTambahCawanganPemohon&idPihak=" + id + "&idPemohon=" + pemohon.getIdPemohon()).addParameter("popup", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahCawanganPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        pemohon = pemohonService.findById(idPemohon);

        if (pihak.getSenaraiCawangan() != null) {
            cawanganList = pihak.getSenaraiCawangan();
        }

        if (pihak.getSenaraiPengarah() != null) {
            pengarahList = pihak.getSenaraiPengarah();
        }

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPengarah() {

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        pihakPengarah.setPihak(pihak);
        pihakPengarah.setAktif('Y');
        pihakPengarah.setInfoAudit(infoAudit);
        pihakService.saveOrUpdatePihakPengarah(pihakPengarah);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/consent/pihak_consent?getTambahPengarahPenerima&idPihak=" + id + "&jenisPB=" + jenisPihak).addParameter("popup", "true");
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanPengarahPemohon() {

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        pihakPengarah.setPihak(pihak);
        pihakPengarah.setAktif('Y');
        pihakPengarah.setInfoAudit(infoAudit);
        pihakService.saveOrUpdatePihakPengarah(pihakPengarah);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/consent/pihak_consent?getTambahPengarahPemohon&idPihak=" + id + "&idPemohon=" + pemohon.getIdPemohon()).addParameter("popup", "true");
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanPgAmanahEdit() {

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        permohonanPihakHubungan.setPihak(permohonanPihak);
        permohonanPihakHubungan.setCawangan(p.getCawangan());
        permohonanPihakHubungan.setKaitan("DipegangAmanah");
        permohonanPihakHubungan.setInfoAudit(infoAudit);
        pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());
        String id2 = String.valueOf(permohonanPihak.getIdPermohonanPihak());

        if (cariFlag) {
            return new RedirectResolution("/consent/pihak_consent?getTambahPengarahPenerimaEdit&idPihak=" + id + "&jenisPB=" + jenisPihak + "&idPermPihak=" + id2).addParameter("popup", "true");
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanPengarahEdit() {

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        pihakPengarah.setPihak(pihak);
        pihakPengarah.setAktif('Y');
        pihakPengarah.setInfoAudit(infoAudit);
        pihakService.saveOrUpdatePihakPengarah(pihakPengarah);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());
        String id2 = String.valueOf(permohonanPihak.getIdPermohonanPihak());

        if (cariFlag) {
            return new RedirectResolution("/consent/pihak_consent?getTambahPengarahPenerimaEdit&idPihak=" + id + "&jenisPB=" + jenisPihak + "&idPermPihak=" + id2).addParameter("popup", "true");
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahPengarahPenerima() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

        if (pihak.getSenaraiCawangan() != null) {
            cawanganList = pihak.getSenaraiCawangan();
        }

        if (pihak.getSenaraiPengarah() != null) {
            pengarahList = pihak.getSenaraiPengarah();
        }

        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");

        return new JSP("consent/pihak_berkepentingan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution getTambahPengarahPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

        if (pihak.getSenaraiCawangan() != null) {
            cawanganList = pihak.getSenaraiCawangan();
        }

        if (pihak.getSenaraiPengarah() != null) {
            pengarahList = pihak.getSenaraiPengarah();
        }

        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        pemohon = pemohonService.findById(idPemohon);

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution getTambahPengarahPenerimaEdit() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idPermPihak = getContext().getRequest().getParameter("idPermPihak");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

        if (pihak.getSenaraiCawangan() != null) {
            cawanganList = pihak.getSenaraiCawangan();
        }

        if (pihak.getSenaraiPengarah() != null) {
            pengarahList = pihak.getSenaraiPengarah();
        }

        permohonanPihak = permohonanPihakService.findById(idPermPihak);
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");

        getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        getContext().getRequest().setAttribute("hakmilik", permohonanPihak.getHakmilik().getIdHakmilik());

        return new JSP("consent/pihak_berkepentingan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution viewPihakDetail() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String jenis = getContext().getRequest().getParameter("jenis");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        pihak = pihakService.findById(idPihak);
        pengarahList = pihak.getSenaraiPengarah();

        if (jenis.equals("penerima")) {

            if (idHakmilik != null) {
                permohonanPihak = permohonanPihakService.getMohonPihakByIdPihakIdHakmilikNotKod(Long.parseLong(idPihak), idHakmilik, p.getIdPermohonan(), "TER");
            } else {
                permohonanPihak = permohonanPihakService.getPmohonPihakByIdPihakNotKod(p.getIdPermohonan(), Long.parseLong(idPihak), "TER");

            }
            if (permohonanPihak.getPihakCawangan() != null) {
                pihakCawangan = pihakCawanganDAO.findById(permohonanPihak.getPihakCawangan().getIdPihakCawangan());
                cawanganList = new ArrayList<PihakCawangan>();
                cawanganList.add(pihakCawangan);
            }
            getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        } else if (jenis.equals("terlibat")) {

            if (idHakmilik != null) {
                permohonanPihak = permohonanPihakService.getSenaraiMohonPihakByIdPihakIdHakmilik(Long.parseLong(idPihak), idHakmilik, p.getIdPermohonan());
            } else {
                permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdPihak(p.getIdPermohonan(), Long.parseLong(idPihak));

            }
            if (permohonanPihak.getPihakCawangan() != null) {
                pihakCawangan = pihakCawanganDAO.findById(permohonanPihak.getPihakCawangan().getIdPihakCawangan());
                cawanganList = new ArrayList<PihakCawangan>();
                cawanganList.add(pihakCawangan);
            }
            getContext().getRequest().setAttribute("terlibat", Boolean.TRUE);
        } else if (jenis.equals("pemohon")) {
            pemohon = pemohonService.findPemohonByPermohonanPihak(p, pihak);

            getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        } else if (jenis.equals("tuanTanah")) {
            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
        }

        return new ForwardResolution("/WEB-INF/jsp/common/paparan_pihak.jsp").addParameter("popup", "true");
    }

    public Resolution doGetPartialPage() {
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String valuePass = getContext().getRequest().getParameter("valuePass");
        if (isDebug) {
            LOG.debug("id hakmilik =" + idHakmilik);
            LOG.debug("id permohonan =" + idPermohonan);
        }
        if (StringUtils.isNotBlank(idHakmilik)) {
            Hakmilik hm = hakmilikManager.findById(idHakmilik);
            List<HakmilikPihakBerkepentingan> senarai = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hm);
            checkPihakValidate(senarai);

            //for urusan consent tanah adat
            if (p.getKodUrusan().getKod().equals("PAADT") || p.getKodUrusan().getKod().equals("CGADT") || p.getKodUrusan().getKod().equals("PMADT") || p.getKodUrusan().getKod().equals("TMADT") || p.getKodUrusan().getKod().equals("BTADT")) {
                mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(idPermohonan, "WAR", "TER", idHakmilik);
            } //for urusan consent serentak
            else if (p.getKodUrusan().getKod().equals("PCPTD") || p.getKodUrusan().getKod().equals("PGDMB") || p.getKodUrusan().getKod().equals("PCMMK")) {
                mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(idPermohonan, "PGA", "TER", idHakmilik);
                penerimaGadaianList = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(idPermohonan, "PGA", idHakmilik);
            } else {
                mohonPihakList = permohonanPihakService.getAllSenaraiPmohonPihakByHakmilikAndMohonNotKod(idPermohonan, idHakmilik, "TER");
            }

//            tuanTanahTerlibatList = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(idPermohonan, "TER", idHakmilik);
//            if (tuanTanahTerlibatList != null) {
//                if (!tuanTanahTerlibatList.isEmpty()) {
//                    bukanPemohon = "checked";
//                }
//            } else {
//                bukanPemohon = null;
//            }

            if (!mohonPihakList.isEmpty()) {
                syer1 = new String[mohonPihakList.size()];
                syer2 = new String[mohonPihakList.size()];
                for (int i = 0; i < mohonPihakList.size(); i++) {
                    PermohonanPihak pp = mohonPihakList.get(i);
                    syer1[i] = String.valueOf(pp.getSyerPembilang());
                    syer2[i] = String.valueOf(pp.getSyerPenyebut());
                }
            }

            if (!penerimaGadaianList.isEmpty()) {
                syer1B = new String[penerimaGadaianList.size()];
                syer2B = new String[penerimaGadaianList.size()];
                for (int i = 0; i < penerimaGadaianList.size(); i++) {
                    PermohonanPihak pp = penerimaGadaianList.get(i);
                    syer1B[i] = String.valueOf(pp.getSyerPembilang());
                    syer2B[i] = String.valueOf(pp.getSyerPenyebut());
                }
            }
            pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);

            //CHECK PIHAK TERLIBAT IF TUAN TANAH BUKAN PEMOHON (1 TUAN TANAH) --BYK HAMILIK
            if (pihakKepentinganList.size() == 1) {

                HakmilikPihakBerkepentingan hakPihak = new HakmilikPihakBerkepentingan();
                hakPihak = pihakKepentinganList.get(0);
                PermohonanPihak perPihak = permohonanPihakService.getPmohonPihakByKodAndIdHakmilik(p.getIdPermohonan(), idHakmilik, hakPihak.getPihak().getIdPihak(), "TER");

                if (pemohonList.size() > 0) {

                    boolean isTuanTanah = false;
                    for (Pemohon pmhon : pemohonList) {
                        if (pmhon.getPihak() == hakPihak.getPihak()) {
                            isTuanTanah = true;
                        }
                    }
                    if (perPihak == null) {

                        if (!isTuanTanah) {

                            Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                            PermohonanPihak perPhak = new PermohonanPihak();
                            InfoAudit infoAudit = new InfoAudit();
                            infoAudit.setDimasukOleh(pguna);
                            infoAudit.setTarikhMasuk(new java.util.Date());
                            perPhak.setInfoAudit(infoAudit);
                            perPhak.setPermohonan(p);
                            perPhak.setPihak(hakPihak.getPihak());
                            perPhak.setCawangan(p.getCawangan());
                            perPhak.setHakmilik(hakmilik);
                            KodJenisPihakBerkepentingan kodJenis = new KodJenisPihakBerkepentingan();
                            kodJenis.setKod("TER");
                            perPhak.setJenis(kodJenis);
                            permohonanPihakService.saveOrUpdate(perPhak);
                        }
                    } else {
                        if (isTuanTanah) {
                            permohonanPihakService.delete(perPihak);
                        }
                    }
                } else {
                    if (perPihak != null) {
                        permohonanPihakService.delete(perPihak);
                    }
                }
            }

            if (valuePass != null) {
                if (valuePass.equals("edit")) {
                    getContext().getRequest().setAttribute("edit", Boolean.TRUE);
                } else {
                    getContext().getRequest().setAttribute("display", Boolean.TRUE);
                }
            } else {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }

        }
        return new JSP("consent/pihak_berkepentingan_partial.jsp").addParameter("tab", "true");
    }

    public void doGetPartialPage2() {
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String valuePass = getContext().getRequest().getParameter("valuePass");
        if (isDebug) {
            LOG.debug("id hakmilik =" + idHakmilik);
            LOG.debug("id permohonan =" + idPermohonan);
        }
        if (StringUtils.isNotBlank(idHakmilik)) {
            Hakmilik hm = hakmilikManager.findById(idHakmilik);
            List<HakmilikPihakBerkepentingan> senarai = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hm);
            checkPihakValidate(senarai);

            //for urusan consent tanah adat
            if (p.getKodUrusan().getKod().equals("PAADT") || p.getKodUrusan().getKod().equals("CGADT") || p.getKodUrusan().getKod().equals("PMADT") || p.getKodUrusan().getKod().equals("TMADT") || p.getKodUrusan().getKod().equals("BTADT")) {
                mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(idPermohonan, "WAR", "TER", idHakmilik);
            } //for urusan consent serentak
            else if (p.getKodUrusan().getKod().equals("PCPTD") || p.getKodUrusan().getKod().equals("PGDMB") || p.getKodUrusan().getKod().equals("PCMMK")) {
                mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(idPermohonan, "PGA", "TER", idHakmilik);
                penerimaGadaianList = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(idPermohonan, "PGA", idHakmilik);
            } else {
                mohonPihakList = permohonanPihakService.getAllSenaraiPmohonPihakByHakmilikAndMohonNotKod(idPermohonan, idHakmilik, "TER");
            }

            if (!mohonPihakList.isEmpty()) {
                syer1 = new String[mohonPihakList.size()];
                syer2 = new String[mohonPihakList.size()];
                for (int i = 0; i < mohonPihakList.size(); i++) {
                    PermohonanPihak pp = mohonPihakList.get(i);
                    syer1[i] = String.valueOf(pp.getSyerPembilang());
                    syer2[i] = String.valueOf(pp.getSyerPenyebut());
                }
            }

            if (!penerimaGadaianList.isEmpty()) {
                syer1B = new String[penerimaGadaianList.size()];
                syer2B = new String[penerimaGadaianList.size()];
                for (int i = 0; i < penerimaGadaianList.size(); i++) {
                    PermohonanPihak pp = penerimaGadaianList.get(i);
                    syer1B[i] = String.valueOf(pp.getSyerPembilang());
                    syer2B[i] = String.valueOf(pp.getSyerPenyebut());
                }
            }
            pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);

            //CHECK PIHAK TERLIBAT IF TUAN TANAH BUKAN PEMOHON (1 TUAN TANAH) --BYK HAMILIK
            if (pihakKepentinganList.size() == 1) {

                HakmilikPihakBerkepentingan hakPihak = new HakmilikPihakBerkepentingan();
                hakPihak = pihakKepentinganList.get(0);
                PermohonanPihak perPihak = permohonanPihakService.getPmohonPihakByKodAndIdHakmilik(p.getIdPermohonan(), idHakmilik, hakPihak.getPihak().getIdPihak(), "TER");

                if (pemohonList.size() > 0) {

                    boolean isTuanTanah = false;
                    for (Pemohon pmhon : pemohonList) {
                        if (pmhon.getPihak() == hakPihak.getPihak()) {
                            isTuanTanah = true;
                        }
                    }
                    if (perPihak == null) {

                        if (!isTuanTanah) {

                            Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                            PermohonanPihak perPhak = new PermohonanPihak();
                            InfoAudit infoAudit = new InfoAudit();
                            infoAudit.setDimasukOleh(pguna);
                            infoAudit.setTarikhMasuk(new java.util.Date());
                            perPhak.setInfoAudit(infoAudit);
                            perPhak.setPermohonan(p);
                            perPhak.setPihak(hakPihak.getPihak());
                            perPhak.setCawangan(p.getCawangan());
                            perPhak.setHakmilik(hakmilik);
                            KodJenisPihakBerkepentingan kodJenis = new KodJenisPihakBerkepentingan();
                            kodJenis.setKod("TER");
                            perPhak.setJenis(kodJenis);
                            permohonanPihakService.saveOrUpdate(perPhak);
                        }
                    } else {
                        if (isTuanTanah) {
                            permohonanPihakService.delete(perPihak);
                        }
                    }
                } else {
                    if (perPihak != null) {
                        permohonanPihakService.delete(perPihak);
                    }
                }
            }
//
//            if (valuePass != null) {
//                if (valuePass.equals("edit")) {
//                    getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//                } else {
//                    getContext().getRequest().setAttribute("display", Boolean.TRUE);
//                }
//            } else {
//                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//            }

        }
        getSenaraiHakmilikKepentingan2();
        //        return new JSP("consent/pihak_berkepentingan_partial.jsp").addParameter("tab", "true");
    }

    public void checkPihakValidate(List<HakmilikPihakBerkepentingan> senarai) {
        pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
        long idPihak = 0;
        for (HakmilikPihakBerkepentingan hp : senarai) {
            boolean flag = true;
            if (hp == null || hp.getPihak() == null) {
                continue;
            }
            if (isDebug) {
                LOG.debug("prev ID pihak = " + idPihak);
                LOG.debug("current ID pihak = " + hp.getIdHakmilikPihakBerkepentingan());
            }
            if (idPihak == hp.getIdHakmilikPihakBerkepentingan()) {
                continue;
            }

            for (HakmilikPermohonan hpm : senaraiHakmilik) {
                if (hpm == null || hpm.getHakmilik() == null) {
                    continue;
                }
                HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihak(hp.getPihak(), hpm.getHakmilik());
                if (hpk == null) {
                    if (isDebug) {
                        LOG.debug("pihak NOT FOUND in hakmilik " + hpm.getHakmilik().getIdHakmilik());
                    }
                    flag = false;
                    break;
                }
            }
            if (flag) {
                pihakKepentinganList.add(hp);
            }
            idPihak = hp.getIdHakmilikPihakBerkepentingan();
        }
    }

    public Resolution simpanPemohonMultipleHakmilik() {
        List<Pemohon> senaraiPemohon = new ArrayList<Pemohon>();
        String[] param = getContext().getRequest().getParameterValues("idPihak");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        for (String idPihak : param) {
            Pihak pi = pihakService.findById(idPihak);
            Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(p, pi);
            if (pmohon == null) {
                Pemohon pe = new Pemohon();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
                pe.setInfoAudit(ia);
                pe.setPermohonan(p);
                pe.setPihak(pi);
                pe.setCawangan(p.getCawangan());
                senaraiPemohon.add(pe);
            }
        }
        if (senaraiPemohon.size() > 0) {
            pemohonService.saveOrUpdateMultiple(senaraiPemohon);
        }
//        return new JSP("daftar/kemaskini_pb_partial.jsp").addParameter("tab", "true");
        return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution simpanSiMati() {
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {

            Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

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
            if (StringUtils.isNotBlank(idHakmilik)) {
                return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
            } else {
                return new JSP("consent/pihak_berkepentingan.jsp").addParameter("tab", "true");
            }
        }
        tx.commit();
        addSimpleMessage("Kemaskini Data Berjaya.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            return new JSP("consent/pihak_berkepentingan.jsp").addParameter("tab", "true");
        }
    }

    public String getNamaNegeriLama() {
        return namaNegeriLama;
    }

    public void setNamaNegeriLama(String namaNegeriLama) {
        this.namaNegeriLama = namaNegeriLama;
    }

    public String getNamaNegeriBaru() {
        return namaNegeriBaru;
    }

    public void setNamaNegeriBaru(String namaNegeriBaru) {
        this.namaNegeriBaru = namaNegeriBaru;
    }

    public PermohonanPihakKemaskini getMohonPihakKemaskini() {
        return mohonPihakKemaskini;
    }

    public void setMohonPihakKemaskini(PermohonanPihakKemaskini mohonPihakKemaskini) {
        this.mohonPihakKemaskini = mohonPihakKemaskini;
    }

    public List<PermohonanPihakKemaskini> getMohonPihakKemaskiniList() {
        return mohonPihakKemaskiniList;
    }

    public void setMohonPihakKemaskiniList(List<PermohonanPihakKemaskini> mohonPihakKemaskiniList) {
        this.mohonPihakKemaskiniList = mohonPihakKemaskiniList;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public String[] getSyer1() {
        return syer1;
    }

    public void setSyer1(String[] syer1) {
        this.syer1 = syer1;
    }

    public String[] getSyer2() {
        return syer2;
    }

    public void setSyer2(String[] syer2) {
        this.syer2 = syer2;
    }

    public boolean isCariFlag() {
        return cariFlag;
    }

    public void setCariFlag(boolean cariFlag) {
        this.cariFlag = cariFlag;
    }

    public boolean isTiadaDataFlag() {
        return tiadaDataFlag;
    }

    public void setTiadaDataFlag(boolean tiadaDataFlag) {
        this.tiadaDataFlag = tiadaDataFlag;
    }

    public List<PermohonanAtasPihakBerkepentingan> getMapList() {
        return mapList;
    }

    public void setMapList(List<PermohonanAtasPihakBerkepentingan> mapList) {
        this.mapList = mapList;
    }

    public boolean isTambahCawFlag() {
        return tambahCawFlag;
    }

    public void setTambahCawFlag(boolean tambahCawFlag) {
        this.tambahCawFlag = tambahCawFlag;
    }

    public PihakCawangan getPihakCawangan() {
        return pihakCawangan;
    }

    public void setPihakCawangan(PihakCawangan pihakCawangan) {
        this.pihakCawangan = pihakCawangan;
    }

    public List<HakmilikPihakBerkepentingan> getOthersPihakList() {
        return othersPihakList;
    }

    public void setOthersPihakList(List<HakmilikPihakBerkepentingan> othersPihakList) {
        this.othersPihakList = othersPihakList;
    }

    public String getIdCawangan() {
        return idCawangan;
    }

    public void setIdCawangan(String idCawangan) {
        this.idCawangan = idCawangan;
    }

    public List<PihakCawangan> getCawanganList() {
        return cawanganList;
    }

    public void setCawanganList(List<PihakCawangan> cawanganList) {
        this.cawanganList = cawanganList;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList2() {
        return pihakKepentinganList2;
    }

    public void setPihakKepentinganList2(List<HakmilikPihakBerkepentingan> pihakKepentinganList2) {
        this.pihakKepentinganList2 = pihakKepentinganList2;
    }

    public List<PermohonanPihak> getSenaraiPemohonBerangkai() {
        return senaraiPemohonBerangkai;
    }

    public void setSenaraiPemohonBerangkai(List<PermohonanPihak> senaraiPemohonBerangkai) {
        this.senaraiPemohonBerangkai = senaraiPemohonBerangkai;
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public List<PermohonanPihak> getMohonPihakPemegangAmanah() {
        return mohonPihakPemegangAmanah;
    }

    public void setMohonPihakPemegangAmanah(List<PermohonanPihak> mohonPihakPemegangAmanah) {
        this.mohonPihakPemegangAmanah = mohonPihakPemegangAmanah;
    }

    public String getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(String tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public List<KodJenisPihakBerkepentingan> getSenaraiKodPenerima() {
        return senaraiKodPenerima;
    }

    public void setSenaraiKodPenerima(List<KodJenisPihakBerkepentingan> senaraiKodPenerima) {
        this.senaraiKodPenerima = senaraiKodPenerima;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getJenisPihak() {
        return jenisPihak;
    }

    public void setJenisPihak(String jenisPihak) {
        this.jenisPihak = jenisPihak;
    }

    public boolean isTambahPengarahFlag() {
        return tambahPengarahFlag;
    }

    public void setTambahPengarahFlag(boolean tambahPengarahFlag) {
        this.tambahPengarahFlag = tambahPengarahFlag;
    }

    public PihakPengarah getPihakPengarah() {
        return pihakPengarah;
    }

    public void setPihakPengarah(PihakPengarah pihakPengarah) {
        this.pihakPengarah = pihakPengarah;
    }

    public List<PihakPengarah> getPengarahList() {
        return pengarahList;
    }

    public void setPengarahList(List<PihakPengarah> pengarahList) {
        this.pengarahList = pengarahList;
    }

    public PermohonanPihakHubungan getPermohonanPihakHubungan() {
        return permohonanPihakHubungan;
    }

    public void setPermohonanPihakHubungan(PermohonanPihakHubungan permohonanPihakHubungan) {
        this.permohonanPihakHubungan = permohonanPihakHubungan;
    }

    public List<Pihak> getPihakByNameList() {
        return pihakByNameList;
    }

    public void setPihakByNameList(List<Pihak> pihakByNameList) {
        this.pihakByNameList = pihakByNameList;
    }

    public List<KodBangsa> getSenaraiKodBangsaOrang() {
        return senaraiKodBangsaOrang;
    }

    public void setSenaraiKodBangsaOrang(List<KodBangsa> senaraiKodBangsaOrang) {
        this.senaraiKodBangsaOrang = senaraiKodBangsaOrang;
    }

    public List<KodBangsa> getSenaraiKodBangsaSyarikat() {
        return senaraiKodBangsaSyarikat;
    }

    public void setSenaraiKodBangsaSyarikat(List<KodBangsa> senaraiKodBangsaSyarikat) {
        this.senaraiKodBangsaSyarikat = senaraiKodBangsaSyarikat;
    }

    public List<PermohonanPihak> getPenerimaGadaianList() {
        return penerimaGadaianList;
    }

    public void setPenerimaGadaianList(List<PermohonanPihak> penerimaGadaianList) {
        this.penerimaGadaianList = penerimaGadaianList;
    }

    public String[] getSyer1B() {
        return syer1B;
    }

    public void setSyer1B(String[] syer1B) {
        this.syer1B = syer1B;
    }

    public String[] getSyer2B() {
        return syer2B;
    }

    public void setSyer2B(String[] syer2B) {
        this.syer2B = syer2B;
    }

    public String getSemuaIdHakmilik() {
        return semuaIdHakmilik;
    }

    public void setSemuaIdHakmilik(String semuaIdHakmilik) {
        this.semuaIdHakmilik = semuaIdHakmilik;
    }

    public String getHubunganLain() {
        return hubunganLain;
    }

    public void setHubunganLain(String hubunganLain) {
        this.hubunganLain = hubunganLain;
    }

    public String getTempatLahirLain() {
        return tempatLahirLain;
    }

    public void setTempatLahirLain(String tempatLahirLain) {
        this.tempatLahirLain = tempatLahirLain;
    }

    public List<PermohonanPihak> getTuanTanahTerlibatList() {
        return tuanTanahTerlibatList;
    }

    public void setTuanTanahTerlibatList(List<PermohonanPihak> tuanTanahTerlibatList) {
        this.tuanTanahTerlibatList = tuanTanahTerlibatList;
    }

    public String getBukanPemohon() {
        return bukanPemohon;
    }

    public void setBukanPemohon(String bukanPemohon) {
        this.bukanPemohon = bukanPemohon;
    }

    public String getTarikhMati() {
        return tarikhMati;
    }

    public void setTarikhMati(String tarikhMati) {
        this.tarikhMati = tarikhMati;
    }

    public PermohonanPihakHubungan getPermohonanPihakHubungan10() {
        return permohonanPihakHubungan10;
    }

    public void setPermohonanPihakHubungan10(PermohonanPihakHubungan permohonanPihakHubungan10) {
        this.permohonanPihakHubungan10 = permohonanPihakHubungan10;
    }

    public PermohonanPihakHubungan getPermohonanPihakHubungan2() {
        return permohonanPihakHubungan2;
    }

    public void setPermohonanPihakHubungan2(PermohonanPihakHubungan permohonanPihakHubungan2) {
        this.permohonanPihakHubungan2 = permohonanPihakHubungan2;
    }

    public PermohonanPihakHubungan getPermohonanPihakHubungan3() {
        return permohonanPihakHubungan3;
    }

    public void setPermohonanPihakHubungan3(PermohonanPihakHubungan permohonanPihakHubungan3) {
        this.permohonanPihakHubungan3 = permohonanPihakHubungan3;
    }

    public PermohonanPihakHubungan getPermohonanPihakHubungan4() {
        return permohonanPihakHubungan4;
    }

    public void setPermohonanPihakHubungan4(PermohonanPihakHubungan permohonanPihakHubungan4) {
        this.permohonanPihakHubungan4 = permohonanPihakHubungan4;
    }

    public PermohonanPihakHubungan getPermohonanPihakHubungan5() {
        return permohonanPihakHubungan5;
    }

    public void setPermohonanPihakHubungan5(PermohonanPihakHubungan permohonanPihakHubungan5) {
        this.permohonanPihakHubungan5 = permohonanPihakHubungan5;
    }

    public PermohonanPihakHubungan getPermohonanPihakHubungan6() {
        return permohonanPihakHubungan6;
    }

    public void setPermohonanPihakHubungan6(PermohonanPihakHubungan permohonanPihakHubungan6) {
        this.permohonanPihakHubungan6 = permohonanPihakHubungan6;
    }

    public PermohonanPihakHubungan getPermohonanPihakHubungan7() {
        return permohonanPihakHubungan7;
    }

    public void setPermohonanPihakHubungan7(PermohonanPihakHubungan permohonanPihakHubungan7) {
        this.permohonanPihakHubungan7 = permohonanPihakHubungan7;
    }

    public PermohonanPihakHubungan getPermohonanPihakHubungan8() {
        return permohonanPihakHubungan8;
    }

    public void setPermohonanPihakHubungan8(PermohonanPihakHubungan permohonanPihakHubungan8) {
        this.permohonanPihakHubungan8 = permohonanPihakHubungan8;
    }

    public PermohonanPihakHubungan getPermohonanPihakHubungan9() {
        return permohonanPihakHubungan9;
    }

    public void setPermohonanPihakHubungan9(PermohonanPihakHubungan permohonanPihakHubungan9) {
        this.permohonanPihakHubungan9 = permohonanPihakHubungan9;
    }

    public PermohonanPihakHubungan getPermohonanPihakHubungan11() {
        return permohonanPihakHubungan11;
    }

    public void setPermohonanPihakHubungan11(PermohonanPihakHubungan permohonanPihakHubungan11) {
        this.permohonanPihakHubungan11 = permohonanPihakHubungan11;
    }

    public boolean isTambahAmanahFlag() {
        return tambahAmanahFlag;
    }

    public void setTambahAmanahFlag(boolean tambahAmanahFlag) {
        this.tambahAmanahFlag = tambahAmanahFlag;
    }
}
