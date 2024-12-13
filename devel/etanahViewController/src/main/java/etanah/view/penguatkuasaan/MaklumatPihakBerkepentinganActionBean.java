package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.KodWarganegaraDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakAlamatTambDAO;
import etanah.dao.PihakDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakHubungan;
import etanah.model.Pihak;
import etanah.model.PihakAlamatTamb;
import etanah.model.PihakCawangan;
import etanah.service.BPelService;
import etanah.service.common.EnforcementService;
import etanah.service.common.LelongService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakHubunganService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PihakService;
import etanah.view.ListUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_pihak_berkepentingan")
public class MaklumatPihakBerkepentinganActionBean extends AbleActionBean {

    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KodJenisPihakBerkepentinganDAO KodJenisPihakBerkepentinganDAO;
    @Inject
    PihakService pihakService;
    @Inject
    LelongService lelongService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    PermohonanPihakHubunganService permohonanPihakHubunganService;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PihakAlamatTambDAO pihakAlamatDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    ListUtil listUtil;
    private static final Logger LOG = Logger.getLogger(MaklumatPihakBerkepentinganActionBean.class);
    private String idHakmilik;
    private Hakmilik hakmilik;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pengguna;
    private List<HakmilikPihakBerkepentingan> listPihakBerkepentingan = new ArrayList<HakmilikPihakBerkepentingan>();
    private List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private List<PermohonanPihak> permohonanPihakList = new ArrayList<PermohonanPihak>();
    private String urusan;
    private Pihak pihak;
    private List<Pemohon> listPemohon = new ArrayList<Pemohon>();
    private Pemohon pemohon;
    private List<HakmilikPihakBerkepentingan> listPihak = new ArrayList<HakmilikPihakBerkepentingan>();
    etanahActionBeanContext ctx;
    private List<Pihak> senaraiPihak;
    private List<HakmilikPermohonan> senaraiHakmilikTerlibat;
    private String kodUrusan;
    private List<PermohonanPihakHubungan> senaraiPihakHubungan;
    private PermohonanPihak permohonanPihak;
    private List<PihakCawangan> senaraiCawangan;
    private PihakAlamatTamb pihakAlamatTamb;
    private static final boolean isDebug = LOG.isDebugEnabled();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String valUrusanPilihan;
    private Boolean maklumatKaveator = Boolean.FALSE;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
    HashSet hsKod = new HashSet();
    private List<KodUrusan> senaraiKodUrusan = new ArrayList<KodUrusan>();
    private String taskId;
    private String stageId;
    private Boolean addPemohon = Boolean.FALSE;
    private Boolean addPermohonanPihak = Boolean.FALSE;
    private Boolean carianPermohonanPihak = Boolean.FALSE;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!searchPihak", "!selectPihak"})
    public void rehydrate() {
        LOG.info("-------------rehydrate------------------");
        ctx = (etanahActionBeanContext) getContext();


        pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) ctx.getRequest().getSession().getAttribute("idPermohonan");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");


        BPelService bpelService = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = bpelService.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("-------------stageId: BPEL ON ----" + stageId);
        } else {
            stageId = "terima_laporan2";
            LOG.info("-------------stageId: BPEL OFF ----" + stageId);
        }

        if (StringUtils.isNotBlank(stageId)) {
            if (stageId.equalsIgnoreCase("arah_endorsan")) {
                addPemohon = true;
            } else if (stageId.equalsIgnoreCase("bantahan_1tahun") || stageId.equalsIgnoreCase("keputusan_enkuiri_2ab2")) {
                addPermohonanPihak = true;
            }
        }

        idHakmilik = ctx.getRequest().getParameter("idHakmilik");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("422") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("423")) {
                maklumatKaveator = true;
            }

            permohonanPihakList = enforcementService.findByIdPermohonan(permohonan.getIdPermohonan());
            hakmilikPermohonanList = enforceService.findSenaraiMohonHakmilik(permohonan.getIdPermohonan());
            listPemohon = enforcementService.findListPemohon(permohonan.getIdPermohonan());
            ArrayList senaraiHakmilik = new ArrayList<String>();
            if (!hakmilikPermohonanList.isEmpty()) {
                for (int j = 0; j < hakmilikPermohonanList.size(); j++) {
                    HakmilikPermohonan hp = hakmilikPermohonanList.get(j);
                    if (hp.getHakmilik() != null) {
                        senaraiHakmilik.add(hp.getHakmilik().getIdHakmilik());
                    }
                }
                listPihakBerkepentingan = enforcementService.findPihakTerlibat(senaraiHakmilik, permohonan, "NOT IN", null);
                listPihak = enforcementService.findPihakTerlibat(senaraiHakmilik, permohonan, "IN", null);
                hakmilikPihakBerkepentinganList = enforcementService.findPihakTerlibat(senaraiHakmilik);

            }

            if (!listPemohon.isEmpty()) {
                for (Pemohon p : listPemohon) {
                    hsKod.add(p.getDalamanNilai1());
                    if (StringUtils.isNotBlank(p.getDalamanNilai1())) {
                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                            if (p.getDalamanNilai1().equalsIgnoreCase("TTWLB") || p.getDalamanNilai1().equalsIgnoreCase("TTWLM")) {
                                carianPermohonanPihak = true;
                            }
                        }
                    }
                }
            }

            Iterator it = hsKod.iterator();
            while (it.hasNext()) {
                String value = (String) it.next();
                KodUrusan h = kodUrusanDAO.findById(value);
                senaraiKodUrusan.add(h);
            }


            LOG.info(":::: size senaraiKodUrusan :" + senaraiKodUrusan.size());
            LOG.info(":::: carianPermohonanPihak :" + carianPermohonanPihak);
        }





    }

    public Resolution simpanPemohon() {
        LOG.info(":::::simpan");
        InfoAudit ia = new InfoAudit();
        for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
            for (int j = 0; j < listPihakBerkepentingan.size(); j++) {
                String pilihCheckBox = getContext().getRequest().getParameter("pilihCheckBox" + i + j); //will hold id pihak
                String syerPembilangVal = getContext().getRequest().getParameter("syerPembilangVal" + i + j);
                String syerPenyebutVal = getContext().getRequest().getParameter("syerPenyebutVal" + i + j);
                String jenisVal = getContext().getRequest().getParameter("jenisVal" + i + j);
                System.out.println("pilihCheckBox [" + i + j + "]: " + pilihCheckBox);
                if (StringUtils.isNotBlank(pilihCheckBox)) {
                    pihak = pihakDAO.findById(Long.parseLong(pilihCheckBox));
                    if (pihak != null) {
                        pemohon = new Pemohon();
                        pemohon.setCawangan(new KodCawangan());
                        pemohon.setPermohonan(permohonan);
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new java.util.Date());

                        if (StringUtils.isNotBlank(syerPembilangVal)) {
                            pemohon.setSyerPembilang(Integer.parseInt(syerPembilangVal));
                        }
                        if (StringUtils.isNotBlank(syerPenyebutVal)) {
                            pemohon.setSyerPenyebut(Integer.parseInt(syerPenyebutVal));
                        }
                        pemohon.setCawangan(pengguna.getKodCawangan());
                        pemohon.setPihak(pihak);
                        pemohon.setNama(pihak.getNama());
                        pemohon.setInfoAudit(ia);
                        pemohon.setDalamanNilai1(urusan);
                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                            pemohon.setDalamanNilai2("TTW");
                        } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                            pemohon.setDalamanNilai2("TT");
                        }

                        pemohon.setJenis(KodJenisPihakBerkepentinganDAO.findById(jenisVal));
                        pemohon.setWargaNegara(pihak.getWargaNegara());

                        Alamat alamat = new Alamat();
                        alamat.setAlamat1(pihak.getAlamat1());
                        alamat.setAlamat2(pihak.getAlamat2());
                        alamat.setAlamat3(pihak.getAlamat3());
                        alamat.setAlamat4(pihak.getAlamat4());
                        alamat.setPoskod(pihak.getPoskod());
                        alamat.setNegeri(pihak.getNegeri());

                        AlamatSurat alamatSurat = new AlamatSurat();
                        if (!pihak.getSenaraiAlamatTamb().isEmpty()) {
                            alamatSurat.setAlamatSurat1(pihak.getSuratAlamat1());
                            alamatSurat.setAlamatSurat2(pihak.getSuratAlamat2());
                            alamatSurat.setAlamatSurat3(pihak.getSuratAlamat3());
                            alamatSurat.setAlamatSurat4(pihak.getSuratAlamat4());
                            alamatSurat.setPoskodSurat(pihak.getSuratPoskod());
                            alamatSurat.setNegeriSurat(pihak.getSuratNegeri());
                        }

                        pemohon.setAlamat(alamat);
                        pemohon.setAlamatSurat(alamatSurat);
                        enforcementService.simpanMaklumatPihak(pemohon);
                    }


                }

            }

        }


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution deletePemohon() {
        LOG.info(":::: deletePemohon");
        String id = getContext().getRequest().getParameter("id");


        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            if (StringUtils.isNotBlank(id)) {
                pemohon = pemohonDAO.findById(Long.parseLong(id));
                if (pemohon != null) {
                    pemohonDAO.delete(pemohon);
                }
            }

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatPihakBerkepentinganActionBean.class, "locate");
    }

    public Resolution deletePermohonanPihak() {
        LOG.info(":::: deletePermohonanPihak");
        String id = getContext().getRequest().getParameter("id");


        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            if (StringUtils.isNotBlank(id)) {
                PermohonanPihak p = permohonanPihakDAO.findById(Long.parseLong(id));
                if (p != null) {
                    permohonanPihakDAO.delete(p);
                }
            }

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatPihakBerkepentinganActionBean.class, "locate");
    }

    public Resolution showSearchForm() {
        pihak = new Pihak();
        valUrusanPilihan = ctx.getRequest().getParameter("valUrusanPilihan");
        return new JSP("penguatkuasaan/carian_pihak_kepentingan.jsp").addParameter("popup", Boolean.TRUE);
    }

    public Resolution searchPihak() {
        ctx = (etanahActionBeanContext) getContext();
        String doSearch = ctx.getRequest().getParameter("doSearch");
        valUrusanPilihan = ctx.getRequest().getParameter("valUrusanPilihan");

        if (StringUtils.isNotBlank(doSearch)) {
            senaraiPihak = pihakService.findAll(ctx.getRequest().getParameterMap());
            if (senaraiPihak.isEmpty()) {
                getContext().getRequest().setAttribute("addNewPihak", "true");
            }
        }
        return new JSP("penguatkuasaan/carian_pihak_kepentingan.jsp").addParameter("popup", Boolean.TRUE);
    }

    public Resolution selectPihak() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) ctx.getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        valUrusanPilihan = ctx.getRequest().getParameter("valUrusanPilihan");
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
            infoAudit.setTarikhMasuk(new java.util.Date());
            pihakAlamatTamb.setInfoAudit(infoAudit);
            lelongService.saveOrUpdate(pihakAlamatTamb);
        } else {
            pihakAlamatTamb = senaraiAlamatTamb.get(0);
        }

        return new JSP("penguatkuasaan/kemasukan_pihak_popup.jsp").addParameter("popup", Boolean.TRUE);
    }

    public Resolution savePihakPopup() {

        String copyToAllHakmilik = getContext().getRequest().getParameter("copyToAll");
        String jenisPihak = getContext().getRequest().getParameter("jenisPihak");
        String tarikhLahir = getContext().getRequest().getParameter("tarikhLahir");
        String syerPembilang = getContext().getRequest().getParameter("syerPembilang");
        String syerPenyebut = getContext().getRequest().getParameter("syerPenyebut");
        String syerDikongsi = getContext().getRequest().getParameter("syerKongsi");
        String kodUrusan = getContext().getRequest().getParameter("kodUrusan");
        valUrusanPilihan = ctx.getRequest().getParameter("valUrusanPilihan");
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");

        System.out.println(":::::valUrusanPilihan : " + valUrusanPilihan);

        String pilih = ctx.getRequest().getParameter("pilih");

        if (isDebug) {
            LOG.debug("pilih =" + pilih);
        }



        if (StringUtils.isBlank(jenisPihak)) {
            addSimpleError("Sila masukan jenis pihak berkepentingan.");
            return new JSP("penguatkuasaan/kemasukan_pihak_popup.jsp").addParameter("popup", "true");
        }


        KodJenisPihakBerkepentingan jenis = kodJenisPihakBerkepentinganDAO.findById(jenisPihak);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
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

        PermohonanPihak pp = new PermohonanPihak();

        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        if (hakmilik != null) {
            pp.setHakmilik(hakmilik);
            pp.setCawangan(hakmilik.getCawangan());
        } else {
            pp.setCawangan(pengguna.getKodCawangan());
        }

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
            pp.setDalamanNilai2("TTW");
        } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
            pp.setDalamanNilai2("TT");
        }
        pp.setDalamanNilai1(valUrusanPilihan);
        pp.setJenis(jenis);
        pp.setInfoAudit(ia);
        if (permohonanPihak != null) {
            pp.setKaitan(permohonanPihak.getKaitan() != null ? permohonanPihak.getKaitan() : "");
            pp.setUmur(permohonanPihak.getUmur() != null ? permohonanPihak.getUmur() : 0);
            pp.setStatusKahwin(permohonanPihak.getStatusKahwin() != null ? permohonanPihak.getStatusKahwin() : "");
            pp.setPekerjaan(permohonanPihak.getPekerjaan() != null ? permohonanPihak.getPekerjaan() : "");
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

        pp.setNama(pihak.getNama());
        pp.setNoPengenalan(pihak.getNoPengenalan());
        pp.setJenisPengenalan(pihak.getJenisPengenalan());
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

        permohonanPihakService.saveOrUpdate(pp);

        PihakCawangan pc = null;

        if (StringUtils.isNotBlank(pilih)) {
            if (!pilih.startsWith("pc_")) {
                pc = pihakService.findPihakCawangan(pilih);
            }
            pp.setPihakCawangan(pc);
        }

        permohonanPihakService.saveOrUpdate(pp);

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
//                    pp.setSyerPembilang(permohonanPihak.getSyerPembilang());
//                    pp.setSyerPenyebut(permohonanPihak.getSyerPenyebut());
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
//                    List<PermohonanPihakHubungan> list = permohonanPihak.getSenaraiHubungan();
//                    if (!list.isEmpty()) pp.setSenaraiHubungan(list);
                }

                if (StringUtils.isNotBlank(syerDikongsi)) {
                    pp.setSyerBersama(syerDikongsi.charAt(0));
                }

                pp.setNama(pihak.getNama());
                pp.setNoPengenalan(pihak.getNoPengenalan());
                pp.setJenisPengenalan(pihak.getJenisPengenalan());
                pp.setWargaNegara(pihak.getWargaNegara());
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
                    pemohon =
                            pemohonService.findPemohonByPermohonanPihak(permohonan, pihakTemp, hm, jenis.getKod());

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
            }
        }


        return new RedirectResolution(MaklumatPihakBerkepentinganActionBean.class).addParameter("idHakmilik", idHakmilik);
    }

    private void savePihak(Pihak pihakTemp, InfoAudit ia, String type) {

        if (pihakTemp != null) {
            ia = pihakTemp.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

            if ("BARU".equals(type)) {

                pihakTemp.setNama(pihak.getNama());
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

                pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
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
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = alamatTambahan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
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
                        phk.setNama(StringUtils.isNotBlank(namaPAs[i]) ? namaPAs[i] : "");
                        phk.setWargaNegara(kodWarganegaraDAO.findById("MAL"));
                        phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp_));
                        phk.setNoPengenalan(noKp_);
                        phk.setInfoAudit(ia);
                        senaraiPihak.add(phk);
                    }

                    waris = new PermohonanPihakHubungan();
                    waris.setPihak(permohonanPihak);
                    waris.setInfoAudit(ia);
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

    private void saveWarisInfo(Map<String, String[]> param,
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
                        phk.setNama(StringUtils.isNotBlank(namaPAs[i]) ? namaPAs[i] : "");
                        phk.setWargaNegara(kodWarganegaraDAO.findById("MAL"));
                        phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp_));
                        phk.setNoPengenalan(noKp_);
                        phk.setInfoAudit(ia);
                        senaraiPihak.add(phk);
                    }

                    waris = new PermohonanPihakHubungan();
                    waris.setPihak(permohonanPihak);
                    waris.setInfoAudit(ia);
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

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
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

    public List<HakmilikPihakBerkepentingan> getListPihakBerkepentingan() {
        return listPihakBerkepentingan;
    }

    public void setListPihakBerkepentingan(List<HakmilikPihakBerkepentingan> listPihakBerkepentingan) {
        this.listPihakBerkepentingan = listPihakBerkepentingan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public List<HakmilikPihakBerkepentingan> getListPihak() {
        return listPihak;
    }

    public void setListPihak(List<HakmilikPihakBerkepentingan> listPihak) {
        this.listPihak = listPihak;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
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

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public List<PermohonanPihakHubungan> getSenaraiPihakHubungan() {
        return senaraiPihakHubungan;
    }

    public void setSenaraiPihakHubungan(List<PermohonanPihakHubungan> senaraiPihakHubungan) {
        this.senaraiPihakHubungan = senaraiPihakHubungan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<PihakCawangan> getSenaraiCawangan() {
        return senaraiCawangan;
    }

    public void setSenaraiCawangan(List<PihakCawangan> senaraiCawangan) {
        this.senaraiCawangan = senaraiCawangan;
    }

    public PihakAlamatTamb getPihakAlamatTamb() {
        return pihakAlamatTamb;
    }

    public void setPihakAlamatTamb(PihakAlamatTamb pihakAlamatTamb) {
        this.pihakAlamatTamb = pihakAlamatTamb;
    }

    public List<KodJenisPihakBerkepentingan> getSenaraiKodPenerima() {
        return listUtil.getSenaraiKodPihakPenerima(permohonan.getKodUrusan().getJabatanNama(), permohonan.getKodUrusan().getNama());
    }

    public String getValUrusanPilihan() {
        return valUrusanPilihan;
    }

    public void setValUrusanPilihan(String valUrusanPilihan) {
        this.valUrusanPilihan = valUrusanPilihan;
    }

    public Boolean getMaklumatKaveator() {
        return maklumatKaveator;
    }

    public void setMaklumatKaveator(Boolean maklumatKaveator) {
        this.maklumatKaveator = maklumatKaveator;
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakBerkepentinganList() {
        return hakmilikPihakBerkepentinganList;
    }

    public void setHakmilikPihakBerkepentinganList(List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList) {
        this.hakmilikPihakBerkepentinganList = hakmilikPihakBerkepentinganList;
    }

    public HashSet getHsKod() {
        return hsKod;
    }

    public void setHsKod(HashSet hsKod) {
        this.hsKod = hsKod;
    }

    public List<KodUrusan> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(List<KodUrusan> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public Boolean getAddPemohon() {
        return addPemohon;
    }

    public void setAddPemohon(Boolean addPemohon) {
        this.addPemohon = addPemohon;
    }

    public Boolean getAddPermohonanPihak() {
        return addPermohonanPihak;
    }

    public void setAddPermohonanPihak(Boolean addPermohonanPihak) {
        this.addPermohonanPihak = addPermohonanPihak;
    }

    public Boolean getCarianPermohonanPihak() {
        return carianPermohonanPihak;
    }

    public void setCarianPermohonanPihak(Boolean carianPermohonanPihak) {
        this.carianPermohonanPihak = carianPermohonanPihak;
    }
}
