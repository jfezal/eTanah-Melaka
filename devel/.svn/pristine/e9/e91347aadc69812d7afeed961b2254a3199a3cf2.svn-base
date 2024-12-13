/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.service.common.HakmilikService;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.KodJenisPengenalan;
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
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
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
import etanah.model.KodCawangan;
import etanah.model.KodJenisPihakBerkepentingan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.RegService;
import etanah.service.common.PermohonanAtasPihakBerkepentinganService;
import etanah.service.common.CawanganService;
import etanah.view.ListUtil;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import net.sourceforge.stripes.action.HttpCache;
import org.apache.commons.math.fraction.Fraction;

/**
 *
 * @author nordiyana
 */
@HttpCache(allow = false)
@UrlBinding("/pengambilan/pengambilan_pemohon")
public class pActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(pActionBean.class);
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
    PengambilanService pengambilanService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    PermohonanPihakKemaskiniService mohonPihakKemaskiniService;
    @Inject
    PermohonanPihakKemaskiniDAO mohonPihakKemaskiniDAO;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    PermohonanDAO permohonanDAO;
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
    private Pemohon pemohon;
    private List<Pemohon> pemohonList;
    private List<PermohonanPihak> mohonPihakList;
    private List<PermohonanPihak> penerimaGadaianList;
    private List<PermohonanPihak> mohonPihakPemegangAmanah;
    private List<PihakCawangan> cawanganList;
    private List<PihakPengarah> pengarahList;
    private List<Pihak> pihakByNameList;
    private String idPermohonan;
    private String penyerahKod;
    private String kodPenyerah;
    private String idPenyerah;
    private String penyerahJenisPengenalan1;
    private String kod;
    private String pJenisPengenalan;
    private String jenisPengenalan;
    private String kodJenisPengenalan;
    private KodJenisPengenalan penyerahJenisPengenalan;
    private List<KodJenisPengenalan> senaraiKodJenisPengenalan;
    private String penyerahNoPengenalan;
    private String pNoPengenalan;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private String penyerahNegeri;
    private String penyerahNegeri1;
    private String penyerahNoTelefon;
    private String penyerahEmail;
    private String idPemohon;
    private Permohonan p;
    private Permohonan permohonan;
    private Pengguna pguna;
    private String[] syer1;
    private String[] syer2;
    private String[] syer1B;
    private String[] syer2B;
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    private PermohonanPihakKemaskini mohonPihakKemaskini;
    String tarikhLahir;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    boolean tambahCawFlag;
    boolean tambahPengarahFlag;
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
    private String maklumatPenyerah;
//    private boolean isLayak = true;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!deletePemohon", "!deletePemohon2", "!deleteSelectedPemohon"})
    public void rehydrate() throws NamingException {
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

//            if (hakmilikPermohonanList.size() > 0 && hakmilikPermohonanList.size() <= 1) {
            if (hakmilikPermohonanList.size() > 0) { //for multiple hakmilik
                //multiple hakmilik
                List<HakmilikPihakBerkepentingan> senaraiHpk = new ArrayList<HakmilikPihakBerkepentingan>();
                List<HakmilikPihakBerkepentingan> senaraiOtherHpk = new ArrayList<HakmilikPihakBerkepentingan>();
                pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
                othersPihakList = new ArrayList<HakmilikPihakBerkepentingan>();
                mohonPihakList = new ArrayList<PermohonanPihak>();
                penerimaGadaianList = new ArrayList<PermohonanPihak>();
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

            pemohonList = p.getSenaraiPemohon();

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
        }

        permohonan = permohonanService.findById(idPermohonan);
        if (permohonan != null) {
            LOG.info(":: permohonan x null ::");

            try {
                kodPenyerah = permohonan.getKodPenyerah().getNama();
                penyerahKod = permohonan.getKodPenyerah().getKod();
            } catch (Exception j) {
            }

            if (permohonan.getPenyerahJenisPengenalan() != null) {
                penyerahJenisPengenalan1 = permohonan.getPenyerahJenisPengenalan().getNama();
            }
//            kod = permohonan.getPenyerahJenisPengenalan().getNama();
//            LOG.info("kod  : " + kod);
//            if (permohonan.getKodPenyerah().getKod().equals("09")) {
//                kod = "BADAN KERAJAAN";
//            }
            penyerahNoPengenalan = permohonan.getPenyerahNoPengenalan();
            pNoPengenalan = permohonan.getPenyerahNoPengenalan();
            LOG.info("pNoPengenalan  : " + pNoPengenalan);
            penyerahNama = permohonan.getPenyerahNama();
            penyerahAlamat1 = permohonan.getPenyerahAlamat1();
            penyerahAlamat2 = permohonan.getPenyerahAlamat2();
            penyerahAlamat3 = permohonan.getPenyerahAlamat3();
            penyerahAlamat4 = permohonan.getPenyerahAlamat4();
            penyerahPoskod = permohonan.getPenyerahPoskod();
            penyerahNegeri = permohonan.getPenyerahNegeri()!=null?permohonan.getPenyerahNegeri().getKod():null;
            penyerahNegeri1 = permohonan.getPenyerahNegeri()!=null?permohonan.getPenyerahNegeri().getKod():null;
            penyerahNoTelefon = permohonan.getPenyerahNoTelefon1();
            penyerahEmail = permohonan.getPenyerahEmail();
        }

        if (isDebug) {
            LOG.debug("rehydrate finish");
        }
    }

    public Resolution deletePemohon() throws NamingException {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pmohon = pemohonService.findById(idPemohon);
            if (pmohon != null) {
                pemohonService.delete(pmohon);
                pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }

        rehydrate();
        return new JSP("pengambilan/common/maklumat_pemohon.jsp").addParameter("tab", "true");
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
            return new RedirectResolution(pActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(pActionBean.class, "getSenaraiHakmilikKepentingan");
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
        return new JSP("pengambilan/common/maklumat_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution pemohonPopup() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("pengambilan/common/maklumat_pemohon_popup.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        pemohon = pemohonService.findPemohonByPermohonanPihak(p, pihak);

        if (pihak.getJenisPengenalan() != null) {
            senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        }
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");

        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();

        if (pihak.getTarikhLahir() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            tarikhLahir = formatter.format(pihak.getTarikhLahir());
        }

        if (pihak.getTempatLahir() != null) {
            if (!pihak.getTempatLahir().equalsIgnoreCase("JOHOR") && !pihak.getTempatLahir().equalsIgnoreCase("KEDAH") && !pihak.getTempatLahir().equalsIgnoreCase("KELANTAN") && !pihak.getTempatLahir().equalsIgnoreCase("MELAKA") && !pihak.getTempatLahir().equalsIgnoreCase("NEGERI SEMBILAN") && !pihak.getTempatLahir().equalsIgnoreCase("PAHANG") && !pihak.getTempatLahir().equalsIgnoreCase("PERAK") && !pihak.getTempatLahir().equalsIgnoreCase("PERLIS") && !pihak.getTempatLahir().equalsIgnoreCase("PULAU PINANG") && !pihak.getTempatLahir().equalsIgnoreCase("SABAH") && !pihak.getTempatLahir().equalsIgnoreCase("SARAWAK") && !pihak.getTempatLahir().equalsIgnoreCase("SELANGOR") && !pihak.getTempatLahir().equalsIgnoreCase("TERENGGANU") && !pihak.getTempatLahir().equalsIgnoreCase("WP KUALA LUMPUR") && !pihak.getTempatLahir().equalsIgnoreCase("WP LABUAN") && !pihak.getTempatLahir().equalsIgnoreCase("WP PUTRAJAYA")) {
                tempatLahirLain = pihak.getTempatLahir();
                pihak.setTempatLahir("LAIN-LAIN");
            }
        }

        if (pemohon != null) {
            if (pemohon.getKaitan() != null) {
                if (!pemohon.getKaitan().equalsIgnoreCase("IBU BAPA KEPADA ANAK") && !pemohon.getKaitan().equalsIgnoreCase("ANAK KEPADA IBU BAPA") && !pemohon.getKaitan().equalsIgnoreCase("SUAMI KEPADA ISTERI") && !pemohon.getKaitan().equalsIgnoreCase("ISTERI KEPADA SUAMI") && !pemohon.getKaitan().equalsIgnoreCase("SAUDARA-MARA") && !pemohon.getKaitan().equalsIgnoreCase("PENJUAL / PEMBELI")) {
                    hubunganLain = pemohon.getKaitan();
                    pemohon.setKaitan("LAIN-LAIN");
                }
            }
        }

        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("multiple", Boolean.TRUE);
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
        }

        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
    }

    public Resolution penyerah() {

        penyerahNegeri1 = permohonan.getPenyerahNegeri().getKod();
        LOG.info("penyerahNegeri "+penyerahNegeri1);
//        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("pengambilan/common/penyerah_popup.jsp").addParameter("popup", "true");
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
                pihakTemp.setEmail(pihak.getEmail());
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
                }
                pemohonService.saveOrUpdate(pemohonTemp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            if (StringUtils.isNotBlank(idHakmilik)) {
                return new JSP("pengambilan/common/maklumat_pemohon.jsp").addParameter("tab", "true");
            }
        }
        tx.commit();
        addSimpleMessage("Kemaskini Data Berjaya.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (StringUtils.isNotBlank(idHakmilik)) {
            return new JSP("pengambilan/common/maklumat_pemohon.jsp").addParameter("tab", "true");
        }
        return new JSP("pengambilan/common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPihakKepentinganPopup() throws ParseException {
//        String tmp = getContext().getRequest().getParameter("urusan");
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
            pihakTemp.setEmail(pihak.getEmail());

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
        } else {
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setNoPengenalanLain(pihak.getNoPengenalanLain());
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
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setEmail(pihak.getEmail());
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
                pemohonService.saveOrUpdate(pemohonTemp);


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
            pihakTemp.setEmail(pihak.getEmail());

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

            Fraction f = Fraction.ZERO;
            List<Pemohon> senaraiPemohon = permohonan.getSenaraiPemohon();

            for (Pemohon pmhn : senaraiPemohon) {
                if (p == null) {
                    continue;
                }
                LOG.debug("pihak = " + pmhn.getPihak().getIdPihak());
                LOG.debug("hakmilik = " + hmk.getIdHakmilik());
                HakmilikPihakBerkepentingan hm = syerService.findSyerPihakFromHakmilikPihak(pmhn.getPihak().getIdPihak(), hmk.getIdHakmilik());
                if (hm == null) {
                    continue;
                }
                if (hm.getSyerPembilang() == 0 && hm.getSyerPenyebut() == 0) {
                    continue;
                }
                if (isDebug) {
                    LOG.debug("syer terlibat = " + hm.getSyerPembilang() + "/" + hm.getSyerPenyebut());
                }
                f = f.add(new Fraction(hm.getSyerPembilang(), hm.getSyerPenyebut()));
            }

            if (permohonanPihak == null) {
                permohonanPihak = new PermohonanPihak();
            }

            permohonanPihak.setPermohonan(permohonan);
            permohonanPihak.setPihak(pihakTemp);
            if (isDebug) {
                LOG.debug("syer terlibat =" + f.toString());
            }
            if (permohonan.getKodUrusan().getKod().equals("PJT") || permohonan.getKodUrusan().getKod().equals("PJKT")) {
                permohonanPihak.setSyerPembilang(1);
                permohonanPihak.setSyerPenyebut(1);
            } else {
                if (f.equals(Fraction.ZERO)) {
                    permohonanPihak.setSyerPembilang(0);
                    permohonanPihak.setSyerPenyebut(0);
                } else {

//                    permohonanPihak.setSyerPembilang(f.getNumerator());
//                    permohonanPihak.setSyerPenyebut(f.getDenominator());

                    //-------------------- add new---------
                    List<PermohonanPihak> senaraiMohonPihak = new ArrayList<PermohonanPihak>();
                    List<PermohonanPihak> senaraiMohonGadai = new ArrayList<PermohonanPihak>();

                    if (p.getKodUrusan().getKod().equals("PCPTD") || p.getKodUrusan().getKod().equals("PGDMB") || p.getKodUrusan().getKod().equals("PCMMK")) {
                        senaraiMohonPihak = permohonanPihakService.getSenaraiPmohonPihakByNotKod(idPermohonan, "PGA");
                        senaraiMohonGadai = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "PGA");
                    } else {
                        senaraiMohonPihak = permohonan.getSenaraiPihak();
                    }

                    if (!senaraiMohonPihak.isEmpty()) {
                        List<PermohonanPihak> senaraiMohonPihakTmp = null;
                        if (senaraiMohonPihak.size() > 0) {
                            senaraiMohonPihakTmp = new ArrayList<PermohonanPihak>();
                            int jumPemohon = senaraiMohonPihak.size() + 1;
                            f = f.divide(jumPemohon);
                            for (PermohonanPihak pp : senaraiMohonPihak) {
                                pp.setSyerPembilang(f.getNumerator());
                                pp.setSyerPenyebut(f.getDenominator());
                                pp.setInfoAudit(info);
                                senaraiMohonPihakTmp.add(pp);
                            }
                            permohonanPihakService.saveOrUpdate(senaraiMohonPihakTmp);
                        }
                        permohonanPihak.setSyerPembilang(f.getNumerator());
                        permohonanPihak.setSyerPenyebut(f.getDenominator());
                    }

                    if (!senaraiMohonGadai.isEmpty()) {
                        List<PermohonanPihak> senaraiMohonPihakTmp = null;
                        if (senaraiMohonGadai.size() > 0) {
                            senaraiMohonPihakTmp = new ArrayList<PermohonanPihak>();
                            int jumPemohon = senaraiMohonGadai.size() + 1;
                            f = f.divide(jumPemohon);
                            for (PermohonanPihak pp : senaraiMohonGadai) {
                                pp.setSyerPembilang(f.getNumerator());
                                pp.setSyerPenyebut(f.getDenominator());
                                pp.setInfoAudit(info);
                                senaraiMohonPihakTmp.add(pp);
                            }
                            permohonanPihakService.saveOrUpdate(senaraiMohonPihakTmp);
                        }
                        permohonanPihak.setSyerPembilang(f.getNumerator());
                        permohonanPihak.setSyerPenyebut(f.getDenominator());
                    }
                    //---------------------///---------
                }
            }

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


        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPemohonPopup() throws ParseException {

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
        pihakTemp.setEmail(pihak.getEmail());
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
            pemohonService.saveOrUpdate(pemohonTemp);


        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/common/maklumat_pemohon.jsp").addParameter("tab", "true");

    }

    public Resolution simpan() {

        LOG.debug("============masuk simpan================");

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        kodNegeri = conf.getProperty("kodNegeri");

        LOG.info("tambah dalam table pihak");

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        Pihak pihakTemp = new Pihak();
        LOG.debug("pihakTemp " + pihakTemp.getIdPihak());
        pihakTemp.setNama(penyerahNama);
        LOG.debug("=====Nama Penyerah======= " + penyerahNama);
        if (kodNegeri.equals("04")) {
            if (permohonan.getKodPenyerah() != null) {
                if (!(permohonan.getKodPenyerah().getKod().equalsIgnoreCase("10") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("09") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("02") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("04") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("03") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("01") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("07") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("00") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("06") || permohonan.getKodPenyerah() == null)) {
                    pihakTemp.setJenisPengenalan(kodJenisPengenalanDAO.findById(kod));
//        pihakTemp.setJenisPengenalan(penyerahJenisPengenalan);
//        pihakTemp.setNoPengenalan(penyerahNoPengenalan);
                    pihakTemp.setNoPengenalan(pNoPengenalan);
                }
            }
        } else if (kodNegeri.equals("05")) {
            if (permohonan.getPenyerahJenisPengenalan() == null) {
                LOG.info("05 kod penyerah null");
            } else {
                pihakTemp.setJenisPengenalan(kodJenisPengenalanDAO.findById(kod));
            }
            pihakTemp.setNoPengenalan(pNoPengenalan);
        }
        pihakTemp.setAlamat1(penyerahAlamat1);
        pihakTemp.setAlamat2(penyerahAlamat2);
        pihakTemp.setAlamat3(penyerahAlamat3);
        pihakTemp.setAlamat4(penyerahAlamat4);
        pihakTemp.setPoskod(penyerahPoskod);
        pihakTemp.setNegeri(kodNegeriDAO.findById(penyerahNegeri));
//            pihakTemp.setNegeri(permohonan.getPenyerahNegeri());
        pihakTemp.setNoTelefon1(penyerahNoTelefon);
        pihakTemp.setEmail(penyerahEmail);
        pihakTemp.setInfoAudit(ia);

        LOG.info("tambah dalam table pemohon");

        InfoAudit info = new InfoAudit();
        Pemohon pemohonTemp = new Pemohon();
        LOG.debug("pemohonTemp " + pemohonTemp.getIdPemohon());
        info.setTarikhMasuk(new java.util.Date());
        info.setDimasukOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        info.setDiKemaskiniOleh(pengguna);
        pemohonTemp.setInfoAudit(info);
        pemohonTemp.setPihak(pihakTemp);
        KodCawangan kod = pengguna.getKodCawangan();
        pemohonTemp.setCawangan(kod);
        pemohonTemp.setPermohonan(permohonan);


        pengambilanService.savePihakPemohon(pihakTemp, pemohonTemp);


        addSimpleMessage("Maklumat telah berjaya disimpan");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPenyerah() throws NamingException {

        LOG.debug("============masuk simpan penyerah================");

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        kodNegeri = conf.getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            InfoAudit ia = new InfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonan.setPenyerahNama(penyerahNama);
            if (permohonan.getKodPenyerah() != null) {
                if (!(permohonan.getKodPenyerah().getKod().equalsIgnoreCase("10") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("09") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("02") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("04") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("03") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("01") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("07") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("00") || permohonan.getKodPenyerah().getKod().equalsIgnoreCase("06"))) {
                    permohonan.setPenyerahJenisPengenalan(kodJenisPengenalanDAO.findById(kod));
//        pihakTemp.setJenisPengenalan(penyerahJenisPengenalan);
//        pihakTemp.setNoPengenalan(penyerahNoPengenalan);
                    permohonan.setPenyerahNoPengenalan(pNoPengenalan);
                }
            }
            permohonan.setPenyerahAlamat1(penyerahAlamat1);
            permohonan.setPenyerahAlamat2(penyerahAlamat2);
            permohonan.setPenyerahAlamat3(penyerahAlamat3);
            permohonan.setPenyerahAlamat4(penyerahAlamat4);
            permohonan.setPenyerahPoskod(penyerahPoskod);
            permohonan.setPenyerahNegeri(kodNegeriDAO.findById(penyerahNegeri1));
            permohonan.setPenyerahNoTelefon1(penyerahNoTelefon);
            permohonan.setPenyerahEmail(penyerahEmail);

        }

        pengambilanService.simpanPermohonan(permohonan);


        addSimpleMessage("Maklumat telah berjaya disimpan");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() throws NamingException {
        rehydrate();
        return new RedirectResolution(pActionBean.class, "locate");
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

            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {
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
            } else if (pihak.getJenisPengenalan() != null && pihak.getNama() != null) {
                pihakByNameList = new ArrayList<Pihak>();
                pihakByNameList = pihakService.findPihakByKodKpAndName(pihak.getJenisPengenalan().getKod(), pihak.getNama());

                if (pihakByNameList.isEmpty()) {
                    addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                    cariFlag = true;
                    tiadaDataFlag = true;
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsa();
                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
                }
            } else {

                if (pihak.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null && pihak.getNama() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan atau Nama.");
                }
            }

        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan Atau Nama.");
        }


        return new JSP("pengambilan/common/maklumat_pemohon_popup.jsp").addParameter("popup", "true");
    }

    public Resolution selectName() {

        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakService.findById(idPihak);
        cariFlag = true;
        tiadaDataFlag = false;
        cawanganList = pihak.getSenaraiCawangan();
        System.out.println("cawanganList :" + cawanganList.size());
        pengarahList = pihak.getSenaraiPengarah();
        System.out.println("pengarahList :" + pengarahList.size());
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

        return new JSP("pengambilan/common/maklumat_pemohon_popup.jsp").addParameter("popup", "true");
    }

    public Resolution selectNamePemohon() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakService.findById(idPihak);
        cariFlag = true;
        tiadaDataFlag = false;
        cawanganList = pihak.getSenaraiCawangan();
        System.out.println("cawanganList :" + cawanganList.size());
        pengarahList = pihak.getSenaraiPengarah();
        System.out.println("pengarahList :" + pengarahList.size());
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

        return new JSP("pengambilan/common/maklumat_pemohon_popup.jsp").addParameter("popup", "true");
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
            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {

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
            } else if (pihak.getJenisPengenalan() != null && pihak.getNama() != null) {
                pihakByNameList = new ArrayList<Pihak>();
//                pihakByNameList = pihakService.findPihakByKodKpAndName(pihak.getJenisPengenalan().getKod(), pihak.getNama());

//                pihakByNameList = new ArrayList<Pihak>();
                pihakByNameList = pihakService.findPihakByKodKpAndName(pihak.getJenisPengenalan().getKod(), pihak.getNama());

                if (pihakByNameList.isEmpty()) {
                    addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                    cariFlag = true;
                    tiadaDataFlag = true;
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsa();
                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
                }



//                Pihak pihakSearch = new Pihak();
//                pihakSearch = pihakService.findPihakByNameKp(pihak.getJenisPengenalan().getKod(), pihak.getNama());
//
//                if (pihakSearch == null) {
//                    addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
//                    cariFlag = true;
//                    tiadaDataFlag = true;
//                    senaraiKodBangsa = listUtil.getSenaraiKodBangsa();
//                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
//                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
//                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
//                }
            } else {

                if (pihak.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null && pihak.getNama() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan atau Nama.");
                }
            }
        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan Atau Nama.");
        }


        return new JSP("pengambilan/common/maklumat_pemohon_popup.jsp").addParameter("popup", "true");
    }

    public Resolution cariSemula() {
        pihak = new Pihak();
        return new JSP("pengambilan/common/maklumat_pemohon_popup.jsp").addParameter("popup", "true");
    }

    public Resolution backCawangan() {

//        pihak = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
        pihak = pihakService.findById(String.valueOf(pihak.getIdPihak()));
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
        cariFlag = true;
        tiadaDataFlag = false;

        return new JSP("pengambilan/common/maklumat_pemohon.jsp").addParameter("popup", "true");
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

        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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
        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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
        return new JSP("pengambilan/common/maklumat_pemohon.jsp").addParameter("popup", "true");
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

        return new JSP("pengambilan/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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

        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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

        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
    }

    public Resolution editCawanganPemohon() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan = cawanganService.findById(idCaw);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakCawangan.getIbupejabat().getIdPihak());

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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
        return new JSP("pengambilan/common/maklumat_pemohon.jsp").addParameter("popup", "true");
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

        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
    }

    public Resolution editPengarah() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        tambahPengarahFlag = true;
        pihakPengarah = new PihakPengarah();
        pihakPengarah = pihakService.findPengarahById(idPengarah);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakPengarah.getPihak().getIdPihak());

        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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

        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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

        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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
        return new JSP("pengambilan/common/maklumat_pemohon_popup.jsp").addParameter("popup", "true");
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
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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
        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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

        return new JSP("pengambilan/common/maklumat_pemohon.jsp").addParameter("popup", "true");
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

        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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
            return new RedirectResolution("/pengambilan/pengambilan_pemohon?getTambahCawanganPenerima&idPihak=" + id + "&jenisPB=" + jenisPihak).addParameter("popup", "true");
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
            return new RedirectResolution("/pengambilan/pengambilan_pemohon?getTambahCawanganPenerimaEdit&idPihak=" + id + "&jenisPB=" + jenisPihak + "&idPermPihak=" + id2).addParameter("popup", "true");
        }
        return new StreamingResolution("text/plain", "1");
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
        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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
            return new RedirectResolution("/pengambilan/pengambilan_pemohon?getTambahCawanganPemohon&idPihak=" + id + "&idPemohon=" + pemohon.getIdPemohon()).addParameter("popup", "true");
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
        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
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
            return new RedirectResolution("/pengambilan/pengambilan_pemohon?getTambahPengarahPenerima&idPihak=" + id + "&jenisPB=" + jenisPihak).addParameter("popup", "true");
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
            return new RedirectResolution("/pengambilan/pengambilan_pemohon?getTambahPengarahPemohon&idPihak=" + id + "&idPemohon=" + pemohon.getIdPemohon()).addParameter("popup", "true");
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
            return new RedirectResolution("/pengambilan/pengambilan_pemohon?getTambahPengarahPenerimaEdit&idPihak=" + id + "&jenisPB=" + jenisPihak + "&idPermPihak=" + id2).addParameter("popup", "true");
        }
        return new StreamingResolution("text/plain", "1");
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

        return new JSP("pengambilan/common/maklumat_pemohon_edit.jsp").addParameter("popup", "true");
    }

    public Resolution viewPihakDetail() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String jenis = getContext().getRequest().getParameter("jenis");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        pihak = pihakService.findById(idPihak);
        pengarahList = pihak.getSenaraiPengarah();

        if (jenis.equals("penerima")) {

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
            getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        }

        if (jenis.equals("pemohon")) {
            pemohon = pemohonService.findPemohonByPermohonanPihak(p, pihak);
            getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        }

        if (jenis.equals("tuanTanah")) {
            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
        }

        return new ForwardResolution("/WEB-INF/jsp/common/paparan_pihak.jsp").addParameter("popup", "true");
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
        return new RedirectResolution(pActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution cariSemulaPemohon() {
        pihak = new Pihak();
//        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("pengambilan/common/maklumat_pemohon_popup.jsp").addParameter("popup", "true");
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

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getMaklumatPenyerah() {
        return maklumatPenyerah;
    }

    public void setMaklumatPenyerah(String maklumatPenyerah) {
        this.maklumatPenyerah = maklumatPenyerah;
    }

    public String getIdPenyerah() {
        return idPenyerah;
    }

    public void setIdPenyerah(String idPenyerah) {
        this.idPenyerah = idPenyerah;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahEmail() {
        return penyerahEmail;
    }

    public void setPenyerahEmail(String penyerahEmail) {
        this.penyerahEmail = penyerahEmail;
    }

    public KodJenisPengenalan getPenyerahJenisPengenalan() {
        return penyerahJenisPengenalan;
    }

    public void setPenyerahJenisPengenalan(KodJenisPengenalan penyerahJenisPengenalan) {
        this.penyerahJenisPengenalan = penyerahJenisPengenalan;
    }

    public String getPenyerahJenisPengenalan1() {
        return penyerahJenisPengenalan1;
    }

    public void setPenyerahJenisPengenalan1(String penyerahJenisPengenalan1) {
        this.penyerahJenisPengenalan1 = penyerahJenisPengenalan1;
    }

    public String getPenyerahKod() {
        return penyerahKod;
    }

    public void setPenyerahKod(String penyerahKod) {
        this.penyerahKod = penyerahKod;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }

    public String getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(String penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;
    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;
    }

    public String getPenyerahNoTelefon() {
        return penyerahNoTelefon;
    }

    public void setPenyerahNoTelefon(String penyerahNoTelefon) {
        this.penyerahNoTelefon = penyerahNoTelefon;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public String getKodPenyerah() {
        return kodPenyerah;
    }

    public void setKodPenyerah(String kodPenyerah) {
        this.kodPenyerah = kodPenyerah;
    }

    public String getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(String idPemohon) {
        this.idPemohon = idPemohon;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(String kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public String getpNoPengenalan() {
        return pNoPengenalan;
    }

    public void setpNoPengenalan(String pNoPengenalan) {
        this.pNoPengenalan = pNoPengenalan;
    }

    public List<KodJenisPengenalan> getSenaraiKodJenisPengenalan() {
        return senaraiKodJenisPengenalan;
    }

    public void setSenaraiKodJenisPengenalan(List<KodJenisPengenalan> senaraiKodJenisPengenalan) {
        this.senaraiKodJenisPengenalan = senaraiKodJenisPengenalan;
    }

    public String getpJenisPengenalan() {
        return pJenisPengenalan;
    }

    public void setpJenisPengenalan(String pJenisPengenalan) {
        this.pJenisPengenalan = pJenisPengenalan;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getPenyerahNegeri1() {
        return penyerahNegeri1;
    }

    public void setPenyerahNegeri1(String penyerahNegeri1) {
        this.penyerahNegeri1 = penyerahNegeri1;
    }
}
