/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import etanah.dao.KodStrukturTanahDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.KodJenisPendudukanDAO;
import etanah.dao.KodKecerunanTanahDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.dao.PermohonanLaporanCerunDAO;
import etanah.model.FasaPermohonan;
import etanah.model.PermohonanNota;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodRujukan;
import etanah.model.LaporanTanah;
import etanah.model.Hakmilik;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.ImejLaporan;
import etanah.model.KodStrukturTanah;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanLaporanCerun;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodRizab;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKecerunanTanah;
import etanah.model.TanahRizabPermohonan;
import etanah.model.Dokumen;
import etanah.model.KodBandarPekanMukim;
import etanah.service.PengambilanService;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.service.common.TanahRizabService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author syaiful
 */
@UrlBinding("/pembangunan/ns/laporanTanah")
public class LaporanTanahNSActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    TanahRizabPermohonanDAO tanahrizabPermohonanDAO;
    @Inject
    KodKecerunanTanahDAO kodKecerunanTanahDAO;
    @Inject
    KodStrukturTanahDAO kodStrukturTanahDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    @Inject
    KodJenisPendudukanDAO jenisPendudukanDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    PermohonanLaporanCerunDAO permohonanLaporanCerunDAO;
    @Inject
    PengambilanService service;
    @Inject
    PembangunanService pembangunanService;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    etanah.Configuration conf;
    private ArrayList imageList[] = new ArrayList[5];
    private List<HakmilikPermohonan> senaraiHakmilik;
    private List<FasaPermohonan> listFasa;
    private List<FasaPermohonan> listFasa2;
    private List<PermohonanNota> listNota;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<TanahRizabPermohonan> tanahRizabList;
    private List<Dokumen> dokumenList = new ArrayList<Dokumen>();
    private List<String> imej = new ArrayList<String>();
    private List<ImejLaporan> imejLaporanList;
    private List<ImejLaporan> utaraImejLaporanList;
    private List<ImejLaporan> selatanImejLaporanList;
    private List<ImejLaporan> timurImejLaporanList;
    private List<ImejLaporan> baratImejLaporanList;
    private List<KodKecerunanTanah> senaraiKodKecerunanTanah;
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanList;
    private List<PermohonanLaporanBangunan> permohonanLaporanTanamanList;
    private List<PermohonanLaporanCerun> listlaporCerun;
    private List<PermohonanLaporanCerun> findListlaporCerun;
    private List<PermohonanRujukanLuar> listRujukanLuar;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private TanahRizabPermohonan tanahrizabpermohonan;
    private TanahRizabPermohonan trizabpermohonan;
    private PermohonanLaporanCerun laporanCerun;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    private FasaPermohonan fasaPermohonan;
    private FasaPermohonan fasaPermohonan2;
    private PermohonanNota notaPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanLaporanBangunan permohonanLaporanBangunan;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanLaporanCerun permohonanLaporanCerun;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodKecerunanTanah kecerunanTanah;
    private KodDaerah daerah;
    private KodCawangan cawangan;
    private KodKategoriTanah KKtgT;
    private KodStrukturTanah KST;
    private KodRizab rizab;
    private Pengguna pengguna;
    private InfoAudit infoAudit;
    private Dokumen dokumen;
    private ImejLaporan imejLaporan;
    private String date;
    private String stageId;
//    private String stageId = "laporantanah";
    private String stageIdfound;
    private String noLot;
    private String noLitho;
    private String noWarta;
    private String lokasi;
    private String tarikhWarta;
    private String idtanahrizabPermohonan;
    private String ulasan;
    private String nota;
    private String noRujukan;
    private String idFasa;
    private char pandanganImej;
    private String idHakmilik;
    private String idPermohonan;
    private String idDokumen;
    private String idLaporBangunan;
    private String idLaporCerun;
    private String kecerunanTanahString;
    private String strukturTanahString;
    private String kategoriTanahBaruString;
    private String mohonLaporUlasan;
    private String idtanahRizabPermohonan;
    private String TB;
    private String kodNegeri;
    private static final Logger LOG = Logger.getLogger(LaporanTanahNSActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private BPelService serviceBPel;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String disabled;
    String disabled2;
    String format1 = "image/jpeg";
    String format2 = "image/pjpeg";
    String _MSG_SUCCES_SAVE = "Maklumat telah berjaya disimpan.";
    String _MSG_SUCCES_UPDATE = "Maklumat telah berjaya dikemaskini.";
    String _PAGE = "pembangunan/ns/laporan_tanah_ns.jsp";
    String _FWRD_PAGE = "/WEB-INF/jsp/" + _PAGE;
    String _REKOD_ULASAN_POP = "pembangunan/ns/rekod_ulasan_popup.jsp";
    String _PAGE_TRIZAB = "/WEB-INF/jsp/pengambilan/maklumat_trizab.jsp";
    String _PAGE_IN_TRIZAB = "pengambilan/kemasukan_tanahrizab.jsp";
    String _PAGE_EDIT_TRIZAB = "pengambilan/kemaskini_trizab.jsp";
    String _PAGE_EDIT_PNGMBLN = "pengambilan/maklumat_pengambilan_pemohon.jsp";
    String _PAGE_LT_POPUP = "pembangunan/ns/laporan_tanah_popup.jsp";

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start Laporan Tanah");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP(_PAGE).addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("Start Laporan Tanah");
        return new JSP(_PAGE).addParameter("tab", "true");
    }

    public Resolution showeditTanahRizab() {
        idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahrizabpermohonan = tanahrizabPermohonanDAO.findById(Long.valueOf(idtanahRizabPermohonan));
        return new JSP(_PAGE_EDIT_TRIZAB).addParameter("popup", "true");
    }

    public Resolution hakMilikPopup() {
        return new JSP(_PAGE_IN_TRIZAB).addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(LaporanTanahMelakaActionBean.class, "locate");
    }

//    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpanLaporanTanah"})
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("-------------rehydrate---------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getKodNegeri();
//        stageId = "laporantanah";
        pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        imejLaporanList = new ArrayList<ImejLaporan>();
        utaraImejLaporanList = new ArrayList<ImejLaporan>();
        selatanImejLaporanList = new ArrayList<ImejLaporan>();
        timurImejLaporanList = new ArrayList<ImejLaporan>();
        baratImejLaporanList = new ArrayList<ImejLaporan>();
        permohonanLaporanBangunanList = new ArrayList<PermohonanLaporanBangunan>();
        permohonanLaporanTanamanList = new ArrayList<PermohonanLaporanBangunan>();
        findListlaporCerun = new ArrayList<PermohonanLaporanCerun>();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilik = hakmilikDAO.findById(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            tanahRizabList = permohonan.getSenaraiTanahRizab();
            permohonanPengambilan = service.findByidPermohonan(idPermohonan);
            permohonanLaporanBangunanList = pembangunanService.getLaporBngnByIdLaporan(idPermohonan);
            permohonanLaporanTanamanList = pembangunanService.getLaporTnmnByIdLaporan(idPermohonan);
            laporanTanah = pembangunanService.findLaporanTanahByIdPermohonan(idPermohonan);

            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            System.out.println("###### Laporan tanah" + laporanTanah);
            System.out.println("######### rehydrate hakmilikpermohonan : " + hakmilikPermohonan.getKeteranganKadarPremium());
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            if (laporanTanah != null) {

                imejLaporanList = laporanTanahService.getLaporImejByLaporanId(laporanTanah.getIdLaporan());
                utaraImejLaporanList = laporanTanahService.getBngnLaporImejByLaporanId(laporanTanah.getIdLaporan());
                selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());
                listFasa2 = pembangunanService.findFasaPermohonanByIdPermohonan(permohonan.getIdPermohonan());
                listNota = pembangunanService.findNotaPermohonanByIdPermohonan(permohonan.getIdPermohonan());
                permohonanLaporanUlasan = pembangunanService.findPemohonLaporanUlasanByIdPermohonan(idPermohonan);

                if (laporanTanah.getStrukturTanah() != null) {
                    strukturTanahString = laporanTanah.getStrukturTanah().getKod();
                } else {
                    strukturTanahString = "";
                }
                if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                    kategoriTanahBaruString = hakmilikPermohonan.getKategoriTanahBaru().getKod();
                } else {
                    kategoriTanahBaruString = "0";
                }
            } else {
                laporanTanah = new LaporanTanah();
            }

            listFasa = pembangunanService.findFasaPermohonanByIdPermohonan2(permohonan.getIdPermohonan());
            listFasa2 = pembangunanService.findFasaPermohonanByIdPermohonan(permohonan.getIdPermohonan());

                        LOG.info("###### listFasa : " + listFasa.size());
            if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);
//                    if (fp.getIdPengguna().equals("kppt1")) {
//                        fasaPermohonan = listFasa.get(i);
//                    }
                    if (fp.getIdAliran().equals("laporantanah")) {
                        fasaPermohonan = listFasa.get(i);
                    }
                }
            }
            if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);

                    if (fp.getIdAliran().equals("semaklaporantanah")) {
                        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
                        fasaPermohonan2 = listFasa.get(i);
                    }
                }
            }
            senaraiKodKecerunanTanah = new ArrayList<KodKecerunanTanah>();
            senaraiKodKecerunanTanah = pembangunanService.findKodKecerunanFilterByidPermohonan(permohonan.getIdPermohonan());
            findListlaporCerun = pembangunanService.findLaporCerunListByIdPermohonan(permohonan.getIdPermohonan());    //BARU PNY
            listRujukanLuar = permohonanRujukanLuarDAO.findByEqualCriterias(tname, model, null);

            if (!(listRujukanLuar.isEmpty())) {
                for (int i = 0; i < listRujukanLuar.size(); i++) {
                    permohonanRujukanLuar = new PermohonanRujukanLuar();
                    permohonanRujukanLuar = listRujukanLuar.get(i);
                    if (permohonanRujukanLuar.getKodRujukan() != null) {
                        if (permohonanRujukanLuar.getKodRujukan().getKod().equalsIgnoreCase("WT") || permohonanRujukanLuar.getKodRujukan().getKod().equalsIgnoreCase("NF")) {
                            permohonanRujukanLuar = listRujukanLuar.get(i);
                        }
                    }
                }
            }
        }
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        dokumenList = laporanTanahService.getDokumenByIdPenguna(format1, format2, pengguna.getNama());

        // find StageId
//        BPelService service = new BPelService();
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//
//        System.out.println("---------taskId-------" + taskId);
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, pguna);
//            stageIdfound = t.getSystemAttributes().getStage();
//            stageId = stageIdfound;
//            System.out.println("-------------stageId--" + stageId);
//        }
        BpelServices(stageId, pengguna);
        if (stageId.equals("semaklaporantanah")) {
            disabled = "true";
        } else {
            disabled = "false";
        }
        if (stageId.equals("laporantanah")) {
            disabled2 = "true";
        } else {
            disabled2 = "false";
        }
    }

    public Resolution simpanLaporanTanah() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("###### simpan Laporan Tanah");
        infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        LOG.info("###### id Pengguna : " + idPermohonan);
        LaporanTanah laporanTanahcek = pembangunanService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanahcek != null) {
            LOG.info("not null");
            infoAudit = laporanTanahcek.getInfoAudit();
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDiKemaskiniOleh(pengguna);

            laporanTanah.setPermohonan(permohonanDAO.findById(idPermohonan));
            laporanTanah.setInfoAudit(infoAudit);
            if (laporanTanah.getAdaBangunan() != null) {
                if (laporanTanah.getAdaBangunan() == 'T') {
                    laporanTanah.setBilanganBangunan(0);
                }
            }
            if (strukturTanahString != null) {
                laporanTanah.setStrukturTanah(KST);
            } else {
                laporanTanah.setStrukturTanah(null);
            }
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            addSimpleMessage(_MSG_SUCCES_SAVE);
            System.out.println("-------------simpan if---------");
        } else {
            LOG.info("NULL");
//            laporanTanah = new LaporanTanah();
            System.out.println("NULL Laporan tanah" + laporanTanah);
            laporanTanah.setPermohonan(permohonanDAO.findById(idPermohonan));
            laporanTanah.setInfoAudit(infoAudit);
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            addSimpleMessage(_MSG_SUCCES_SAVE);
            System.out.println("-------------simpan else---");
        }

        if (kategoriTanahBaruString != null) {
            KKtgT = kodKategoriTanahDAO.findById(kategoriTanahBaruString);
        } else {
            KKtgT = null;
        }
        if (strukturTanahString != null) {
            KST = kodStrukturTanahDAO.findById(strukturTanahString);
            laporanTanah.setStrukturTanah(KST);
        } else {
            KST = null;
        }


//        hakmilikPermohonan.setKategoriTanahBaru(KKtgT);
//        pembangunanService.simpanHakmilikPermohonan(hakmilikPermohonan);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        System.out.println("####simpan###hakmilikPermohonanList : " + hakmilikPermohonanList.size());

        for (int a = 0; a < hakmilikPermohonanList.size(); a++) {
            HakmilikPermohonan hp = new HakmilikPermohonan();
            hp = hakmilikPermohonanList.get(a);
            System.out.println("#######hakmilikPermohonan : " + hakmilikPermohonan.getKeteranganKadarPremium());
            hp.setKeteranganKadarPremium(hakmilikPermohonan.getKeteranganKadarPremium());
            hp.setDendaPremium(hakmilikPermohonan.getDendaPremium());
            hp.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru());
            hp.setCatatan(hakmilikPermohonan.getCatatan());
            hp.setKosInfra(hakmilikPermohonan.getKosInfra());
            if (kategoriTanahBaruString != null) {
                hp.setKategoriTanahBaru(KKtgT);
            } else {
                hp.setKategoriTanahBaru(null);
            }
            pembangunanService.simpanHakmilikPermohonan(hp);
        }

//        for (int a = 0; a < hakmilikPermohonanList.size(); a++) {
//            hakmilikPermohonan = new HakmilikPermohonan();
//            System.out.println("###### simpan Syor " + hakmilikPermohonanList.size());
//            hakmilikPermohonan = hakmilikPermohonanList.get(a);
//            System.out.println("###### ID HP baru" + hakmilikPermohonan.getId());
//            hakmilikPermohonan.setId(hakmilikPermohonan.getId());
//            hakmilikPermohonan.setPermohonan(permohonan);
//            if (kategoriTanahBaruString != null) {
//                hakmilikPermohonan.setKategoriTanahBaru(KKtgT);
//            } else {
//                hakmilikPermohonan.setKategoriTanahBaru(null);
//            }
//            pembangunanService.simpanHakmilikPermohonan(hakmilikPermohonan);
//        }

        for (int i = 0; i < senaraiKodKecerunanTanah.size(); i++) {
            System.out.println("-------------senaraiKodKecerunanTanah.size---" + senaraiKodKecerunanTanah.size());
            KodKecerunanTanah kodCerunTanah = new KodKecerunanTanah();
            kodCerunTanah = senaraiKodKecerunanTanah.get(i);

            String kod = getContext().getRequest().getParameter(kodCerunTanah.getNama());
            if (kod != null && !kod.equals("")) {
                laporanCerun = new PermohonanLaporanCerun();
                laporanCerun.setIdPermohonan(permohonan);
                laporanCerun.setInfoAudit(infoAudit);
                laporanCerun.setCawangan(permohonan.getCawangan());
                laporanCerun.setKodCerunanTanah(kodKecerunanTanahDAO.findById(kod));
                pembangunanService.simpanPermohonanLaporCerun(laporanCerun);
            }
        }
//        BPelService service = new BPelService();
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        System.out.println("---------taskId-------" + taskId);
//
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, pengguna);
//            stageId = t.getSystemAttributes().getStage();
//            System.out.println("-------------stageId--" + stageId);
//        }
        //testing purpose
//        stageId = "laporantanah";
        BpelServices(stageId, pengguna);
        String lT = "semaklaporantanah";
        if (fasaPermohonan != null) {
            if (stageId.equalsIgnoreCase("laporantanah")) {
                LOG.info("###### laporantanah SAVE");
                fasaPermohonan.setPermohonan(permohonan);
                fasaPermohonan.setCawangan(permohonan.getCawangan());
                fasaPermohonan.setInfoAudit(infoAudit);
                fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan.setIdAliran(stageId);
//                laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
                pembangunanService.simpanFasaPermohonan2(fasaPermohonan);
            } else if (stageId.equalsIgnoreCase("semaklaporantanah")) {
                LOG.info("###### semaklaporantanah SAVE");
                fasaPermohonan2.setPermohonan(permohonan);
                fasaPermohonan2.setCawangan(permohonan.getCawangan());
                fasaPermohonan2.setInfoAudit(infoAudit);
                fasaPermohonan2.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan2.setIdAliran(lT);
//                laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
                //Ulasan Penolong Pegawai Tanah Kanan
                fasaPermohonan2.setIdAliran(stageId);
//                laporanTanahService.simpanFasaPermohonan(fasaPermohonan2);
                pembangunanService.simpanFasaPermohonan2(fasaPermohonan2);
            }
        } else {
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan2 = new FasaPermohonan();

            if (stageId.equalsIgnoreCase("laporantanah")) {
                fasaPermohonan.setPermohonan(permohonan);
                fasaPermohonan.setCawangan(permohonan.getCawangan());
                fasaPermohonan.setInfoAudit(infoAudit);
                fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan.setIdAliran(stageId);
                laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
            } else if (stageId.equalsIgnoreCase("semaklaporantanah")) {
                fasaPermohonan2.setPermohonan(permohonan);
                fasaPermohonan2.setCawangan(permohonan.getCawangan());
                fasaPermohonan2.setInfoAudit(infoAudit);
                fasaPermohonan2.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan2.setIdAliran(lT);
//                laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
                //Ulasan Penolong Pegawai Tanah Kanan
                fasaPermohonan2.setIdAliran(stageId);
                laporanTanahService.simpanFasaPermohonan(fasaPermohonan2);
            }
        }


        if (fasaPermohonan != null) {
            if (permohonanRujukanLuar != null) {
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                KodRujukan kodRuj = new KodRujukan();
                kodRuj.setKod("WT");//NF
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            } else {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                KodRujukan kodRuj = new KodRujukan();
                kodRuj.setKod("WT");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            }
        } else {
            if (permohonanRujukanLuar != null) {
                KodDokumen kodDokumenWarta = new KodDokumen();
                KodRujukan kodRuj = new KodRujukan();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                kodRuj.setKod("NF");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                kodDokumenWarta.setKod("WRKN");
                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            } else {
                KodDokumen kodDokumenWarta = new KodDokumen();
                KodRujukan kodRuj = new KodRujukan();
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                kodRuj.setKod("NF");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                kodDokumenWarta.setKod("WRKN");
                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            }
        }
        if (permohonanLaporanUlasan != null) {
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
            pembangunanService.simpanLaporanUlasan(permohonanLaporanUlasan);
        } else {
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            pembangunanService.simpanLaporanUlasan(permohonanLaporanUlasan);
        }
//        addSimpleMessage(_MSG_SUCCES_SAVE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new ForwardResolution(_FWRD_PAGE).addParameter("tab", "true");
        return new RedirectResolution(LaporanTanahNSActionBean.class, "showForm").addParameter("tab", "true");
    }

    public Resolution addHakmilikImage() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idDokumen = getContext().getRequest().getParameter("idDokumen");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
        imejLaporan = laporanTanahService.getLaporImejByHakmilikDokumen(idHakmilik, dokumen.getIdDokumen());
        if (imejLaporan == null) {
            imejLaporan = new ImejLaporan();
            imejLaporan.setCawangan(cawangan);
            imejLaporan.setDokumen(dokumen);
            imejLaporan.setHakmilik(hakmilik);
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            imejLaporan.setInfoAudit(infoAudit);
            imejLaporan.setLaporanTanah(laporanTanah);
            laporanTanahService.simpanHakmilikImej(imejLaporan);
            rehydrate();
            addSimpleMessage(_MSG_SUCCES_SAVE);
        } else {
            addSimpleMessage("Imej telah wujud untuk hakmilik ini.");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution(_FWRD_PAGE).addParameter("tab", "true");
    }

    public Resolution addLaporanImage() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idDokumen = getContext().getRequest().getParameter("idDokumen");

        pandanganImej = 'L';
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        System.out.println("idhakmilik test:" + idHakmilik);
        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        imejLaporan = laporanTanahService.getLaporImejByDokumen(laporanTanah, dokumen, pandanganImej);
        if (imejLaporan == null) {
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            imejLaporan = new ImejLaporan();
            imejLaporan.setCawangan(cawangan);
            imejLaporan.setDokumen(dokumen);
            imejLaporan.setPandanganImej(pandanganImej);
            imejLaporan.setInfoAudit(infoAudit);
            imejLaporan.setLaporanTanah(laporanTanah);
            imejLaporan.setHakmilik(hakmilik);
            laporanTanahService.simpanHakmilikImej(imejLaporan);
            rehydrate();
            addSimpleMessage(_MSG_SUCCES_SAVE);
        } else {
            addSimpleMessage("Imej already exist.");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution(_FWRD_PAGE).addParameter("tab", "true");
    }

    public Resolution simpanLaporanTanah2() throws Exception {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("###### simpanLaporanTanah2 ");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());

//        BPelService service = new BPelService();
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        System.out.println("---------taskId-------" + taskId);
//
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, pengguna);
//            stageId = t.getSystemAttributes().getStage();
//            System.out.println("-------------stageId--" + stageId);
//        }

        //testing purpose
//        stageId = "semaklaporantanah";
        BpelServices(stageId, pengguna);
        String lT = "semaklaporantanah";

        if (fasaPermohonan2 != null) {
            LOG.info("###### semaklaporantanah2 SAVE");
            fasaPermohonan2.setPermohonan(permohonan);
            fasaPermohonan2.setCawangan(permohonan.getCawangan());
            fasaPermohonan2.setInfoAudit(infoAudit);
            fasaPermohonan2.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan2.setIdAliran(lT);
            //Ulasan Penolong Pegawai Tanah Kanan
            fasaPermohonan2.setIdAliran(stageId);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan2);
        } else {
            fasaPermohonan2 = new FasaPermohonan();
            fasaPermohonan2.setPermohonan(permohonan);
            fasaPermohonan2.setCawangan(permohonan.getCawangan());
            fasaPermohonan2.setInfoAudit(infoAudit);
            fasaPermohonan2.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan2.setIdAliran(lT);
            //Ulasan Penolong Pegawai Tanah Kanan
            fasaPermohonan2.setIdAliran(stageId);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan2);
        }
        addSimpleMessage(_MSG_SUCCES_SAVE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution(_FWRD_PAGE).addParameter("tab", "true");
    }

    public Resolution tambahBangunanPopup() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        TB = getContext().getRequest().getParameter("TB");
        System.out.println("tambahBangunanPopup idPermohonan : " + idPermohonan + " TB : " + TB);
        permohonanLaporanBangunan = new PermohonanLaporanBangunan();
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP(_PAGE_LT_POPUP).addParameter("popup", "true");
    }

    public Resolution editBangunanPopup() {
        idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
        TB = getContext().getRequest().getParameter("TB");
        permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.valueOf(idLaporBangunan));
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP(_PAGE_LT_POPUP).addParameter("popup", "true");
    }

    public Resolution simpanBangunan() throws Exception {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
//        simpanLaporanTanahpop();
        System.out.println("###### simpanBangunan : idPermohonan = " + idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

//        BPelService service = new BPelService();
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        System.out.println("---------taskId-------" + taskId);
//
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, pengguna);
//            stageId = t.getSystemAttributes().getStage();
//            System.out.println("-------------stageId--" + stageId);
//        }

//        stageId = "laporantanah";
        BpelServices(stageId, pengguna);
        if (idPermohonan == null) {
            System.out.println("Tiada id Permohonan");
        }
        if (idPermohonan != null) {
            System.out.println("id Permohonan" + idPermohonan);
            laporanTanah = laporanTanahService.findLaporanTanahByIdMohon(permohonan.getIdPermohonan());
            permohonanLaporanBangunan.setPermohonan(permohonan);
            permohonanLaporanBangunan.setCawangan(permohonan.getCawangan());
            permohonanLaporanBangunan.setLaporanTanah(laporanTanah);
            permohonanLaporanBangunan.setKategori(TB);
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanLaporanBangunan.setInfoAudit(infoAudit);
            laporanTanahService.simpanLaporanTanahBangunan(permohonanLaporanBangunan);
        }
        addSimpleMessage(_MSG_SUCCES_SAVE);
        return new RedirectResolution(LaporanTanahNSActionBean.class, "showForm").addParameter("tab", "true");
//        return new ForwardResolution(LaporanTanahMelakaActionBean.class, "simpanLaporanTanah").addParameter("tab", "true");
    }

    public Resolution editBangunan() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
            permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.valueOf(idLaporBangunan));
            if (permohonanLaporanBangunan != null) {
                infoAudit = permohonanLaporanBangunan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                permohonanLaporanBangunan.setInfoAudit(infoAudit);
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.jenisBangunan") != null) {
                    permohonanLaporanBangunan.setJenisBangunan(getContext().getRequest().getParameter("permohonanLaporanBangunan.jenisBangunan"));
                }
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.nilai") != null) {
                    permohonanLaporanBangunan.setNilai(new BigDecimal(getContext().getRequest().getParameter("permohonanLaporanBangunan.nilai")));
                }
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.keteranganTahunBinaan") != null) {
                    permohonanLaporanBangunan.setKeteranganTahunBinaan(String.valueOf(getContext().getRequest().getParameter("permohonanLaporanBangunan.keteranganTahunBinaan")));
                }
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.namaPemunya") != null) {
                    permohonanLaporanBangunan.setNamaPemunya(getContext().getRequest().getParameter("permohonanLaporanBangunan.namaPemunya"));
                }
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.namaPenyewa") != null) {
                    permohonanLaporanBangunan.setNamaPenyewa(getContext().getRequest().getParameter("permohonanLaporanBangunan.namaPenyewa"));
                }
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.namaKetua") != null) {
                    permohonanLaporanBangunan.setNamaKetua(getContext().getRequest().getParameter("permohonanLaporanBangunan.namaKetua"));
                }
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.jenisPendudukan.kod") != null) {
                    permohonanLaporanBangunan.setJenisPendudukan(jenisPendudukanDAO.findById(getContext().getRequest().getParameter("permohonanLaporanBangunan.jenisPendudukan.kod")));
                }
                laporanTanahService.simpanLaporanTanahBangunan(permohonanLaporanBangunan);
                addSimpleMessage(_MSG_SUCCES_UPDATE);
            }
        }
        return new RedirectResolution(LaporanTanahNSActionBean.class, "showForm").addParameter("tab", "true");
    }

    public Resolution rekodUlasanPopup() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        System.out.println("tambahBangunanPopup idPermohonan : " + idPermohonan);
        notaPermohonan = new PermohonanNota();
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP(_REKOD_ULASAN_POP).addParameter("popup", "true");
    }

    public Resolution simpanRekodUlasan() {
//        simpanLaporanTanahpop();
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        System.out.println("###### simpanRekodUlasan : idPermohonan = " + idPermohonan);
        infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        if (idPermohonan == null) {
            System.out.println("Tiada id Permohonan");
        }
        if (idPermohonan != null) {

//            BPelService service = new BPelService();
//            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//            System.out.println("---------taskId-------" + taskId);
//
//            if (StringUtils.isNotBlank(taskId)) {
//                Task t = null;
//                t = service.getTaskFromBPel(taskId, pengguna);
//                stageId = t.getSystemAttributes().getStage();
//                System.out.println("-------------stageId--" + stageId);
//            }
            //testing purpose
//            stageId = "semaklaporantanah";
            BpelServices(stageId, pengguna);
            LOG.info("###### simpanRekodUlasan SAVE");
            notaPermohonan.setPermohonan(permohonanDAO.findById(idPermohonan));
            notaPermohonan.setCawangan(permohonan.getCawangan());
            notaPermohonan.setInfoAudit(infoAudit);
            notaPermohonan.setIdAliran(stageId);
            pembangunanService.simpanNotaPermohonan(notaPermohonan);
        }
        addSimpleMessage(_MSG_SUCCES_SAVE);
        return new RedirectResolution(LaporanTanahNSActionBean.class, "showForm").addParameter("tab", "true");
//        return new ForwardResolution(LaporanTanahMelakaActionBean.class, "showForm").addParameter("tab", "true");
    }

    public Resolution deleteLaporCerun() {
        System.out.println("###### deleteLaporCerun");
        idLaporCerun = getContext().getRequest().getParameter("idLaporCerun");
        permohonanLaporanCerun = permohonanLaporanCerunDAO.findById(Long.parseLong(idLaporCerun));
        pembangunanService.deleteLaporCerun(permohonanLaporanCerun);
        return new RedirectResolution(LaporanTanahNSActionBean.class, "locate");
    }

    public String BpelServices(String taskId, Pengguna pengguna) {
        System.out.println("####BpelServices : TaskID : " + taskId + "Pengguna :" + pengguna);
        serviceBPel = new BPelService();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        System.out.println("---------taskId-------" + taskId);

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBPel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("-------------stageId--" + stageId);
        }
        return stageId;
//        return stageId = "laporantanah";

    }

    public Resolution searchtrizab() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        infoAudit = pengguna.getInfoAudit();

        permohonan = permohonanDAO.findById(idPermohonan);
        if (idPermohonan != null) {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            if (StringUtils.isNotBlank(tarikhWarta)) {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta());
            }
            tanahrizabpermohonan.setInfoAudit(infoAudit);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahRizabList.add(tanahrizabpermohonan);
            tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
            addSimpleMessage(_MSG_SUCCES_SAVE);
        } else {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            if (StringUtils.isNotBlank(tarikhWarta)) {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta());
            }
            tanahrizabpermohonan.setInfoAudit(infoAudit);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahRizabList.add(tanahrizabpermohonan);
            tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
            addSimpleMessage(_MSG_SUCCES_SAVE);
        }
        return new ForwardResolution(_PAGE_TRIZAB).addParameter("tab", "true");
    }

    public Resolution edit() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idtanahrizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        infoAudit = new InfoAudit();
        if (infoAudit == null) {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(infoAudit);
        } else {
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(infoAudit);
            tanahrizabpermohonan.setPermohonan(permohonan);
        }
        tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
        addSimpleMessage("Data Telah Berjaya dikemaskini");
        return new JSP(_PAGE_EDIT_PNGMBLN).addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        infoAudit = new InfoAudit();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        tanahrizabpermohonan = new TanahRizabPermohonan();
        idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahrizabpermohonan = tanahRizabService.findTanahRizabByIdTanahRizab(Long.parseLong(idtanahRizabPermohonan));

        if (tanahrizabpermohonan != null) {
            infoAudit = pengguna.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(infoAudit);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahRizabService.deleteAll(tanahrizabpermohonan);
        }
        return new RedirectResolution(LaporanTanahNSActionBean.class, "locate");
    }

    public void simpanLaporanTanahpop() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        LOG.info("###### simpan Laporan Tanah pop");
        infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        LOG.info("###### id Pengguna : " + pengguna);
        laporanTanah = pembangunanService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
            LOG.info("not null");
//            laporanTanah.setPermohonan(permohonanDAO.findById(idPermohonan));
            laporanTanah.setInfoAudit(infoAudit);
            if (laporanTanah.getAdaBangunan() != null) {
                if (laporanTanah.getAdaBangunan() == 'T') {
                    laporanTanah.setBilanganBangunan(0);
                }
            }
            if (strukturTanahString != null) {
                laporanTanah.setStrukturTanah(KST);
            } else {
                laporanTanah.setStrukturTanah(null);
            }
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            System.out.println("-------------simpan if pop---------");
        } else {
            LOG.info("NULL");
            laporanTanah = new LaporanTanah();
            laporanTanah.setPermohonan(permohonanDAO.findById(idPermohonan));
            laporanTanah.setInfoAudit(infoAudit);
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            System.out.println("-------------simpan else pop---");
        }

        if (kategoriTanahBaruString != null) {
            KKtgT = kodKategoriTanahDAO.findById(kategoriTanahBaruString);
        } else {
            KKtgT = null;
        }
        if (strukturTanahString != null) {
            KST = kodStrukturTanahDAO.findById(strukturTanahString);
            laporanTanah.setStrukturTanah(KST);
        } else {
            KST = null;
        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        System.out.println("####simpan###hakmilikPermohonanList : " + hakmilikPermohonanList.size());

        for (int a = 0; a < hakmilikPermohonanList.size(); a++) {
            HakmilikPermohonan hp = new HakmilikPermohonan();
            hp = hakmilikPermohonanList.get(a);
            System.out.println("#######hakmilikPermohonan : " + hakmilikPermohonan.getKeteranganKadarPremium());
            hp.setKeteranganKadarPremium(hakmilikPermohonan.getKeteranganKadarPremium());
            hp.setDendaPremium(hakmilikPermohonan.getDendaPremium());
            hp.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru());
            hp.setCatatan(hakmilikPermohonan.getCatatan());
            hp.setKosInfra(hakmilikPermohonan.getKosInfra());
            if (kategoriTanahBaruString != null) {
                hp.setKategoriTanahBaru(KKtgT);
            } else {
                hp.setKategoriTanahBaru(null);
            }
            pembangunanService.simpanHakmilikPermohonan(hp);
        }

        for (int i = 0; i < senaraiKodKecerunanTanah.size(); i++) {
            System.out.println("-------------senaraiKodKecerunanTanah.size---" + senaraiKodKecerunanTanah.size());
            KodKecerunanTanah kodCerunTanah = new KodKecerunanTanah();
            kodCerunTanah = senaraiKodKecerunanTanah.get(i);

            String kod = getContext().getRequest().getParameter(kodCerunTanah.getNama());
            if (kod != null && !kod.equals("")) {
                laporanCerun = new PermohonanLaporanCerun();
                laporanCerun.setIdPermohonan(permohonan);
                laporanCerun.setInfoAudit(infoAudit);
                laporanCerun.setCawangan(permohonan.getCawangan());
                laporanCerun.setKodCerunanTanah(kodKecerunanTanahDAO.findById(kod));
                pembangunanService.simpanPermohonanLaporCerun(laporanCerun);
            }
        }
        BpelServices(stageId, pengguna);
        if (fasaPermohonan != null) {
            if (permohonanRujukanLuar != null) {
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                KodRujukan kodRuj = new KodRujukan();
                kodRuj.setKod("WT");//NF
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            } else {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                KodRujukan kodRuj = new KodRujukan();
                kodRuj.setKod("WT");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            }
        } else {
            if (permohonanRujukanLuar != null) {
                KodDokumen kodDokumenWarta = new KodDokumen();
                KodRujukan kodRuj = new KodRujukan();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                kodRuj.setKod("NF");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                kodDokumenWarta.setKod("WRKN");
                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            } else {
                KodDokumen kodDokumenWarta = new KodDokumen();
                KodRujukan kodRuj = new KodRujukan();
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                kodRuj.setKod("NF");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                kodDokumenWarta.setKod("WRKN");
                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            }
        }
        if (permohonanLaporanUlasan != null) {
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
            pembangunanService.simpanLaporanUlasan(permohonanLaporanUlasan);
        } else {
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            pembangunanService.simpanLaporanUlasan(permohonanLaporanUlasan);
        }
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new ForwardResolution(_FWRD_PAGE).addParameter("tab", "true");
//        return new RedirectResolution(LaporanTanahMelakaActionBean.class, "showForm").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public PermohonanPengambilan getpermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<TanahRizabPermohonan> getTanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public KodKecerunanTanah getKodkecerunanTanah() {
        return kecerunanTanah;
    }

    public void setKodkecerunanTanah(KodKecerunanTanah kecerunanTanah) {
        this.kecerunanTanah = kecerunanTanah;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public KodRizabDAO getKodRizabDAO() {
        return kodRizabDAO;
    }

    public void setKodRizabDAO(KodRizabDAO kodRizabDAO) {
        this.kodRizabDAO = kodRizabDAO;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public KodRizab getRizab() {
        return rizab;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public TanahRizabPermohonan getTanahrizabpermohonan() {
        return tanahrizabpermohonan;
    }

    public void setTanahrizabpermohonan(TanahRizabPermohonan tanahrizabpermohonan) {
        this.tanahrizabpermohonan = tanahrizabpermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdtanahrizabPermohonan() {
        return idtanahrizabPermohonan;
    }

    public void setIdtanahrizabPermohonan(String idtanahrizabPermohonan) {
        this.idtanahrizabPermohonan = idtanahrizabPermohonan;
    }

    public TanahRizabPermohonan getTrizabpermohonan() {
        return trizabpermohonan;
    }

    public void setTrizabpermohonan(TanahRizabPermohonan trizabpermohonan) {
        this.trizabpermohonan = trizabpermohonan;
    }

    public List<Dokumen> getDokumenList() {
        return dokumenList;
    }

    public void setDokumenList(List<Dokumen> dokumenList) {
        this.dokumenList = dokumenList;
    }

    public List<String> getImej() {
        return imej;
    }

    public void setImej(List<String> imej) {
        this.imej = imej;
    }

    public FasaPermohonan getFasaPermohonan2() {
        return fasaPermohonan2;
    }

    public void setFasaPermohonan2(FasaPermohonan fasaPermohonan2) {
        this.fasaPermohonan2 = fasaPermohonan2;
    }

    public String getIdFasa() {
        return idFasa;
    }

    public void setIdFasa(String idFasa) {
        this.idFasa = idFasa;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String norujukan) {
        this.noRujukan = noRujukan;
    }

    public List<ImejLaporan> getImejLaporanList() {
        return imejLaporanList;
    }

    public void setImejLaporanList(List<ImejLaporan> imejLaporanList) {
        this.imejLaporanList = imejLaporanList;
    }

    public List<ImejLaporan> getUtaraImejLaporanList() {
        return utaraImejLaporanList;
    }

    public void setUtaraImejLaporanList(List<ImejLaporan> utaraImejLaporanList) {
        this.utaraImejLaporanList = utaraImejLaporanList;
    }

    public List<ImejLaporan> getBaratImejLaporanList() {
        return baratImejLaporanList;
    }

    public void setBaratImejLaporanList(List<ImejLaporan> baratImejLaporanList) {
        this.baratImejLaporanList = baratImejLaporanList;
    }

    public List<ImejLaporan> getSelatanImejLaporanList() {
        return selatanImejLaporanList;
    }

    public void setSelatanImejLaporanList(List<ImejLaporan> selatanImejLaporanList) {
        this.selatanImejLaporanList = selatanImejLaporanList;
    }

    public List<ImejLaporan> getTimurImejLaporanList() {
        return timurImejLaporanList;
    }

    public void setTimurImejLaporanList(List<ImejLaporan> timurImejLaporanList) {
        this.timurImejLaporanList = timurImejLaporanList;
    }

    public ArrayList[] getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList[] imageList) {
        this.imageList = imageList;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public char getPandanganImej() {
        return pandanganImej;
    }

    public void setPandanganImej(char pandanganImej) {
        this.pandanganImej = pandanganImej;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getDisabled2() {
        return disabled2;
    }

    public void setDisabled2(String disabled2) {
        this.disabled2 = disabled2;
    }

    public PermohonanLaporanBangunan getPermohonanLaporanBangunan() {
        return permohonanLaporanBangunan;
    }

    public void setPermohonanLaporanBangunan(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        this.permohonanLaporanBangunan = permohonanLaporanBangunan;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanList() {
        return permohonanLaporanBangunanList;
    }

    public void setPermohonanLaporanBangunanList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanList) {
        this.permohonanLaporanBangunanList = permohonanLaporanBangunanList;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<FasaPermohonan> getListFasa() {
        return listFasa;
    }

    public void setListFasa(List<FasaPermohonan> listFasa) {
        this.listFasa = listFasa;
    }

    public List<FasaPermohonan> getListFasa2() {
        return listFasa2;
    }

    public void setListFasa2(List<FasaPermohonan> listFasa2) {
        this.listFasa2 = listFasa2;
    }

    public List<PermohonanNota> getListNota() {
        return listNota;
    }

    public void setListNota(List<PermohonanNota> listNota) {
        this.listNota = listNota;
    }

    public String getKecerunanTanahString() {
        return kecerunanTanahString;
    }

    public void setKecerunanTanahString(String kecerunanTanahString) {
        this.kecerunanTanahString = kecerunanTanahString;
    }

    public String getStrukturTanahString() {
        return strukturTanahString;
    }

    public void setStrukturTanahString(String strukturTanahString) {
        this.strukturTanahString = strukturTanahString;
    }

    public String getKategoriTanahBaruString() {
        return kategoriTanahBaruString;
    }

    public void setKategoriTanahBaruString(String kategoriTanahBaruString) {
        this.kategoriTanahBaruString = kategoriTanahBaruString;
    }

    public List<KodKecerunanTanah> getSenaraiKodKecerunanTanah() {
        return senaraiKodKecerunanTanah;
    }

    public void setSenaraiKodKecerunanTanah(List<KodKecerunanTanah> senaraiKodKecerunanTanah) {
        this.senaraiKodKecerunanTanah = senaraiKodKecerunanTanah;
    }

    public String getTB() {
        return TB;
    }

    public void setTB(String TB) {
        this.TB = TB;
    }

    public PermohonanNota getNotaPermohonan() {
        return notaPermohonan;
    }

    public void setNotaPermohonan(PermohonanNota notaPermohonan) {
        this.notaPermohonan = notaPermohonan;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanTanamanList() {
        return permohonanLaporanTanamanList;
    }

    public void setPermohonanLaporanTanamanList(List<PermohonanLaporanBangunan> permohonanLaporanTanamanList) {
        this.permohonanLaporanTanamanList = permohonanLaporanTanamanList;
    }

    public String getMohonLaporUlasan() {
        return mohonLaporUlasan;
    }

    public void setMohonLaporUlasan(String mohonLaporUlasan) {
        this.mohonLaporUlasan = mohonLaporUlasan;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public List<PermohonanLaporanCerun> getFindListlaporCerun() {
        return findListlaporCerun;
    }

    public void setFindListlaporCerun(List<PermohonanLaporanCerun> findListlaporCerun) {
        this.findListlaporCerun = findListlaporCerun;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
